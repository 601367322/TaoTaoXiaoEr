package com.ttxr.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.overlay.WalkRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.ttxr.activity.R;
import com.ttxr.activity.base.BaseFragment;
import com.ttxr.activity.order.OrderDetailActivity_;
import com.ttxr.bean.UserMsg;
import com.ttxr.bean.request_model.MyOrderRequestDTO;
import com.ttxr.bean.request_model.MyOrderResponseDTO;
import com.ttxr.interfaces.IFragmentTitle;
import com.ttxr.util.AMapUtil;
import com.ttxr.util.AnimUtil;
import com.ttxr.util.LogUtil;
import com.ttxr.util.MyJsonHttpResponseHandler;
import com.ttxr.util.Url;
import com.ttxr.util.Util;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;

@EFragment(R.layout.activity_fragment)
@OptionsMenu(R.menu.emoticon_menu)
public class MapFragment extends BaseFragment implements IFragmentTitle, AMap.OnMapLoadedListener, LocationSource, GeocodeSearch.OnGeocodeSearchListener, AMapLocationListener, RouteSearch.OnRouteSearchListener, SensorEventListener, AMap.InfoWindowAdapter {

    private static final String TAG = "MapFragment";

    @ViewById(R.id.map)
    public MapView mapView;//地图
    @OptionsMenuItem
    MenuItem message;
    @FragmentArg
    UserMsg msg;
    @ViewById
    TextView title;
    @ViewById
    TextView money;
    @ViewById
    TextView address;
    @ViewById
    TextView state;
    @ViewById
    TextView time;
    @ViewById
    LinearLayout detail;
    @ViewById
    LinearLayout detail_bottom;
    @ViewById
    ImageView callBtn;
    @ViewById
    Button statusBtn;
    @ViewById
    ImageView positionBtn;
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
    private AMapLocation myLocation;
    private int busMode = RouteSearch.BusDefault;// 公交默认模式
    private int drivingMode = RouteSearch.DrivingDefault;// 驾车默认模式
    private int walkMode = RouteSearch.WalkDefault;// 步行默认模式
    private GeocodeSearch geocoderSearch; //地理编码
    private RouteSearch routeSearch;//路径规划
    private MyOrderResponseDTO bean;
    private ProgressDialog getLatLngDialog;//地理编码
    private ProgressDialog getRouteDialog;//路径规划
    private LatLng endPosition;//终点

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, "onCreate");
        //初始化传感器
        mSensorManager = (SensorManager) getActivity()
                .getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        geocoderSearch = new GeocodeSearch(getActivity());
        geocoderSearch.setOnGeocodeSearchListener(this);

        routeSearch = new RouteSearch(getActivity());
        routeSearch.setRouteSearchListener(MapFragment.this);
    }

    @OptionsItem
    public void message() {
        if (bean != null) {
            OrderDetailActivity_.intent(this).orderId(bean.getOrderId()).start();
        }
    }

    @Override
    public void afterViews() {
        LogUtil.d(TAG, "afterViews");
        mapView.onCreate(null);
    }

    /**
     * 请求网络数据
     */
    @UiThread(delay = 100)
    public void getData() {
        LogUtil.d(TAG, "getData");
        if (msg != null) {//如果是从消息进来的
            MyOrderRequestDTO request1 = new MyOrderRequestDTO();
            request1.setOrderId(msg.getOrderId());
            request1.setStatus(msg.getOrderStatus());
            post(Url.GET_MY_ORDER, Util.getTokenRequestParams(getActivity(), request1));
        } else {
            post(Url.GET_ORDER_REQUEST, Util.getTokenRequestParams(getActivity(), null));
        }
    }

    public void post(String url, RequestParams params) {
        LogUtil.d(TAG, "post");
        ac.httpClient.post(url, params, new MyJsonHttpResponseHandler(getActivity()) {

            @Override
            public void onStart() {
                if (message != null) {
                    message.setVisible(true);
                    message.setActionView(R.layout.actionbar_progress);
                }
            }

            @Override
            public void onSuccessRetCode(JSONObject jo) throws Throwable {
                if (message != null) {
                    message.setVisible(true);
                }
                getOrderSuccessCallBack(jo);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (message != null) {
                    message.setActionView(null);
                    message.setVisible(false);
                }
            }
        });
    }

    /**
     * 获取订单成功后
     *
     * @param jo
     */
    public void getOrderSuccessCallBack(JSONObject jo) {
        MyOrderResponseDTO temp = new Gson().fromJson(jo.toString(), new TypeToken<MyOrderResponseDTO>() {
        }.getType());
        AtomicBoolean isFirstIn = new AtomicBoolean(false);
        if (bean == null) {
            isFirstIn.set(true);
        }
        this.bean = temp;
        if (temp != null) {
            if (temp.getStatus() == 0) {
                if (message != null) {
                    message.setVisible(false);
                }
            }else{
                if (message != null) {
                    message.setVisible(true);
                }
            }
            setDetail(temp);
            if (temp.getStatus() == 0 || temp.getStatus() == 5) {
                endPosition = new LatLng(Double.valueOf(temp.getStoreLatitude()), Double.valueOf(temp.getStoreLongitude()));
                addStoreMarket(endPosition);
                loadBounds(endPosition);
                if (!isFirstIn.get() && temp.getStatus() == 5) {
                    OrderDetailActivity_.intent(getActivity()).orderId(bean.getOrderId()).start();
                }
            } else if (temp.getStatus() < 3) {//如果状态在送餐以前，以餐厅位置为终点
                searchRoute(AMapUtil.convertToLatLonPoint(new LatLng(Double.valueOf(temp.getStoreLatitude()), Double.valueOf(temp.getStoreLongitude()))));
            } else if (temp.getStatus() < 5) {
                getLatLngDialog = Util.progress(getActivity(), getString(R.string.getting_lat_lng));
                GeocodeQuery query = new GeocodeQuery(temp.getPerAddress(), null);
                geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
            }
        }
    }

    /**
     * 添加商店图标
     *
     * @param latLng
     */
    public void addStoreMarket(LatLng latLng) {
        LogUtil.d(TAG, "addStoreMarket");
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(latLng);
        markerOption.draggable(true);
        markerOption.icon(
                // BitmapDescriptorFactory
                // .fromResource(R.drawable.location_marker)
                BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.store_position_icon)));
        aMap.addMarker(markerOption);
    }

    /**
     * 查询行走路径
     *
     * @param endPoint 终点
     */
    @Background
    public void searchRoute(LatLonPoint endPoint) {
        LogUtil.d(TAG, "searchRoute");
        endPosition = AMapUtil.convertToLatLng(endPoint);
        try {
            if (myLocation != null) {
                showRoteDialog();
                LatLonPoint startPoint = new LatLonPoint(myLocation.getLatitude(), myLocation.getLongitude());
                final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                        startPoint, endPoint);
                RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, walkMode);
                routeSearch.calculateWalkRouteAsyn(query);
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @UiThread
    public void showRoteDialog() {
        LogUtil.d(TAG, "showRoteDialog");
        getRouteDialog = Util.progress(getActivity(), getString(R.string.getting_route));
    }

    /**
     * 设置数据到界面
     *
     * @param bean 订单详情
     */
    public void setDetail(MyOrderResponseDTO bean) {
        LogUtil.d(TAG, "setDetail");
        try {
            title.setText(bean.getStoreName());
            money.setText("￥" + bean.getDeliverPrice());
            address.setText(bean.getPerAddress());
            state.setText(bean.getStatusStr());
            time.setText(bean.getDateStr() + " | " + bean.getDeliverHour());

            if (bean.getStatus() == 1) {
                statusBtn.setEnabled(false);
            } else {
                statusBtn.setEnabled(true);
            }
            statusBtn.setText(bean.getStatusBtnStr(getActivity()));
            showAndHideView(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAndHideView(boolean visible) {
        LogUtil.d(TAG, "showAndHideView");
        if (detail.getVisibility() == View.VISIBLE && visible) {
            return;
        }
        if (bean != null) {
            if (detail.getVisibility() == View.VISIBLE) {
                ValueAnimator animator1 = ObjectAnimator.ofFloat(detail, AnimUtil.TRANSLATIONY, 0, -detail.getMeasuredHeight()).setDuration(200);
                animator1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        detail.setVisibility(View.GONE);
                    }
                });
                animator1.start();

                ValueAnimator animator2 = ObjectAnimator.ofFloat(detail_bottom, AnimUtil.TRANSLATIONY, 0, detail_bottom.getMeasuredHeight()).setDuration(200);
                animator2.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        detail_bottom.setVisibility(View.GONE);
                    }
                });
                animator2.start();
            } else {
                detail.setVisibility(View.VISIBLE);
                detail.measure(0, 0);
                ObjectAnimator.ofFloat(detail, AnimUtil.TRANSLATIONY, -detail.getMeasuredHeight(), 0).setDuration(200).start();

                detail_bottom.setVisibility(View.VISIBLE);
                detail_bottom.measure(0, 0);
                ObjectAnimator.ofFloat(detail_bottom, AnimUtil.TRANSLATIONY, detail_bottom.getMeasuredHeight(), 0).setDuration(200).start();
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        LogUtil.d(TAG, "onCreateOptionsMenu");
        message.setVisible(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume");
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
        LogUtil.d(TAG, "setUpMap");
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
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.setMyLocationEnabled(true);
        aMap.setOnMapLoadedListener(this);
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                LogUtil.d("onMapClick");
                showAndHideView(false);
            }
        });
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
        LogUtil.d(TAG, "activate");
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
        LogUtil.d(TAG, "deactivate");
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
        LogUtil.d(TAG, "registerSensorListener");
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

            this.myLocation = aLocation;

            mListener.onLocationChanged(aLocation);// 显示系统小蓝点
            addMarker(aLocation);
            mGPSMarker.setPosition(new LatLng(aLocation.getLatitude(), aLocation.getLongitude()));

            if (toMyPosition.compareAndSet(false, true)) {
                toMyPosition(aLocation);
            }
        }
    }

    public void toMyPosition(final AMapLocation aLocation) {
        LogUtil.d(TAG, "toMyPosition");
        changeCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aLocation.getLatitude(), aLocation.getLongitude()), zoom), null, false);
    }

    @Override
    public void onLocationChanged(Location location) {
        LogUtil.d(TAG, "onLocationChanged(Location location)");
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
        if (locationMarker == null) {
            locationMarker = aMap.addMarker(new MarkerOptions()
                    .anchor(0.5f, 40)
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.trans))
                    .title(getString(R.string.im_here)));
        }
        locationMarker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
        locationMarker.setSnippet(location.getAddress());
        locationMarker.showInfoWindow();
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = LayoutInflater.from(getActivity()).inflate(
                R.layout.custom_info_window_my_location, mapView, false);
        ((TextView) infoWindow.findViewById(R.id.address)).setText(marker.getSnippet());
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


    //路径规划
    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    WalkRouteOverlay walkRouteOverlay;

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int rCode) {
        LogUtil.d(TAG, "onWalkRouteSearched");
        try {
            getRouteDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rCode == 0) {
            if (result != null && result.getPaths() != null && result.getPaths().size() > 0) {
                WalkPath walkPath = result.getPaths().get(0);

                if (walkRouteOverlay != null) {
                    walkRouteOverlay.removeFromMap();
                }
                walkRouteOverlay = new WalkRouteOverlay(
                        getActivity(), aMap, walkPath, result.getStartPos(),
                        result.getTargetPos());
                walkRouteOverlay.removeFromMap();
                walkRouteOverlay.addToMap();
                walkRouteOverlay.zoomToSpan();
                return;
            }
        }
        Util.toast(getActivity(), getString(R.string.search_fail));
    }

    //地理编码搜索
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
        LogUtil.d(TAG, "onGeocodeSearched");
        try {
            getLatLngDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rCode == 0) {
            if (result != null && result.getGeocodeAddressList() != null
                    && result.getGeocodeAddressList().size() > 0) {
                GeocodeAddress address = result.getGeocodeAddressList().get(0);
//                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                        AMapUtil.convertToLatLng(address.getLatLonPoint()), 15));
                searchRoute(address
                        .getLatLonPoint());
                return;
            }
        }
        Util.toast(getActivity(), getString(R.string.search_fail));
    }

    @Override
    public void onMapLoaded() {
        LogUtil.d(TAG, "onMapLoaded");
        getData();
    }

    /**
     * 显示所有标记
     *
     * @param latLng
     */
    public void loadBounds(LatLng latLng) {
        // 设置所有maker显示在当前可视区域地图中
        LatLngBounds.Builder build = new LatLngBounds.Builder();
        if (myLocation != null) {
            build.include(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
        }
        build.include(latLng);

        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(build.build(), 150));
    }

    /**
     * 电话点击事件
     */
    @Click
    public void call_btn() {
        if (bean != null && !TextUtils.isEmpty(bean.getPerPhone())) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bean.getPerPhone()));
            startActivity(intent);
        }
    }

    /**
     * 位置点击事件
     */
    @Click
    public void position_btn() {
        if (endPosition != null)
            changeCamera(CameraUpdateFactory.newLatLngZoom(endPosition, zoom), null, true);
    }

    /**
     * 接单按钮点击事件
     */
    @Click
    public void status_btn() {
        if (bean != null) {
            if (bean.getStatus() == 5) {
                OrderDetailActivity_.intent(getActivity()).orderId(bean.getOrderId()).start();
                return;
            }
            MyOrderRequestDTO request1 = new MyOrderRequestDTO();
            request1.setOrderId(bean.getOrderId());
            request1.setStatus(String.valueOf(bean.getStatus() + 1));
            ac.httpClient.post(Url.CHANGE_ORDER_STATUS, Util.getTokenRequestParams(getActivity(), request1), new MyJsonHttpResponseHandler(getActivity(), getString(R.string.please_wait)) {
                @Override
                public void onSuccessRetCode(JSONObject jo) throws Throwable {
                    getOrderSuccessCallBack(jo);
                }
            });
        }
    }
}
