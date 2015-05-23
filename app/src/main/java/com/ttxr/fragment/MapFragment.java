package com.ttxr.fragment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Url;
import com.ttxr.util.Util;
import com.ttxr.weight.swipe.SwipeRefreshLayout;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;

@EFragment(R.layout.activity_fragment)
@OptionsMenu(R.menu.emoticon_menu)
public class MapFragment extends BaseFragment implements IFragmentTitle, LocationSource, AMapLocationListener, SensorEventListener, AMap.InfoWindowAdapter,SwipeRefreshLayout.OnRefreshListener {

    @ViewById(R.id.map)
    public MapView mapView;//地图
    @ViewById
    SwipeRefreshLayout swipe;
    private AMap aMap;
    private OnLocationChangedListener mListener;
    private LocationManagerProxy mAMapLocationManager;
    private SensorManager mSensorManager;
    private Sensor mSensor;//gps转动箭头
    private Marker mGPSMarker;
    private long lastTime = 0;
    private final int TIME_SENSOR = 100;
    private float mAngle;
    private static final float zoom = 16;//缩放比例
    private View view;
    private AtomicBoolean toMyPosition = new AtomicBoolean(false);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化传感器
        mSensorManager = (SensorManager) getActivity()
                .getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    public void afterViews() {
        mapView.onCreate(null);
        swipe.setOnRefreshListener(this);
    }

    @UiThread
    public void refresh(){
        swipe.setRefreshing(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (aMap == null) {//初始化map控制器
            aMap = mapView.getMap();
            setUpMap();
        }
        mapView.onResume();
        registerSensorListener();
    }

    /**
     * 设置map参数
     */
    private void setUpMap() {
        mGPSMarker = aMap.addMarker(
                new MarkerOptions().icon(
                        BitmapDescriptorFactory
                                .fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.navi_map_gps_locked))
                ).anchor((float) 0.5, (float) 0.5));//设置可以转向的箭头

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.trans));//空白图标防止和上面转向箭头重叠
        myLocationStyle.radiusFillColor(0x1902bce4);
        myLocationStyle.strokeColor(0x3302bce4);
        myLocationStyle.strokeWidth(1);
        aMap.setMyLocationStyle(myLocationStyle);//设置我的位置样式
        aMap.setInfoWindowAdapter(this);
        aMap.setLocationSource(this);//设置监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//显示我的位置按钮
        aMap.setMyLocationEnabled(true);
        changeCamera(CameraUpdateFactory.zoomTo(zoom), null, false);//缩放
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 定位成功回调
     *
     * @param listener
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mAMapLocationManager == null) {
            mAMapLocationManager = LocationManagerProxy.getInstance(getActivity());
            mAMapLocationManager.requestLocationUpdates(
                    LocationProviderProxy.AMapNetwork, 5000, 10, this);
        }
    }

    /**
     * 定位失败回调
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mAMapLocationManager != null) {
            mAMapLocationManager.removeUpdates(this);
            mAMapLocationManager.destory();
        }
        mAMapLocationManager = null;
        unRegisterSensorListener();
    }

    /**
     * 注册传感器
     */
    public void registerSensorListener() {
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /**
     * 销毁传感器
     */
    public void unRegisterSensorListener() {
        mSensorManager.unregisterListener(this, mSensor);
    }


    /**
     * 位置变化回调
     *
     * @param aLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aLocation) {
        if (mListener != null && aLocation != null) {
            mListener.onLocationChanged(aLocation);// 显示系统小蓝点
            addMarker(aLocation);
            mGPSMarker.setPosition(new LatLng(aLocation.getLatitude(), aLocation.getLongitude()));

            if (toMyPosition.compareAndSet(false, true)) {
                toMyPosition(aLocation);
            }
        }
    }

    public void toMyPosition(final AMapLocation aLocation) {
        changeCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aLocation.getLatitude(), aLocation.getLongitude()), zoom), null, false);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * 传感器变化回调
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (System.currentTimeMillis() - lastTime < TIME_SENSOR) {
            return;
        }
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION: {
                float x = event.values[0];

                x += getScreenRotationOnPhone(getActivity());
                x %= 360.0F;
                if (x > 180.0F)
                    x -= 360.0F;
                else if (x < -180.0F)
                    x += 360.0F;
                if (Math.abs(mAngle - 90 + x) < 3.0f) {
                    break;
                }
                mAngle = x;
                if (mGPSMarker != null) {
                    mGPSMarker.setRotateAngle(-mAngle);
                }
                lastTime = System.currentTimeMillis();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * 获取当前屏幕旋转角度
     *
     * @return 0表示是竖屏; 90表示是左横屏; 180表示是反向竖屏; 270表示是右横屏
     */
    public static int getScreenRotationOnPhone(Context context) {
        final Display display = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                return 0;

            case Surface.ROTATION_90:
                return 90;

            case Surface.ROTATION_180:
                return 180;

            case Surface.ROTATION_270:
                return -90;
        }
        return 0;
    }

    /**
     * 调用函数animateCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update, AMap.CancelableCallback callback, boolean animate) {
        if (animate)
            aMap.animateCamera(update, 1000, callback);
        else
            aMap.moveCamera(update);
    }

    Marker locationMarker;

    /**
     * 往地图上添加一个Marker覆盖物
     */
    private void addMarker(AMapLocation location) {
        locationMarker = aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 40)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.trans))
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .snippet(location.getAddress())
                .title(getString(R.string.im_here)));

        locationMarker.showInfoWindow();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = LayoutInflater.from(getActivity()).inflate(
                R.layout.custom_info_window_my_location, mapView, false);
        ((TextView)infoWindow.findViewById(R.id.address)).setText(marker.getSnippet());
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public int getFragmentTitle() {
        return R.string.app_name;
    }

    @Override
    public void onRefresh() {
        ac.httpClient.post(Url.GET_ORDER_REQUEST, Util.getTokenRequestParams(getActivity(),null), new MyJsonHttpResponseHandler(getActivity()) {
            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                swipe.setRefreshing(false);
            }
        });
    }
}
