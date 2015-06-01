package com.ttxr.api;

import android.content.Context;

import com.ttxr.activity.base.BaseApi;
import com.ttxr.bean.request_model.OrderStatusRequestDTO;
import com.ttxr.util.Url;

/**
 * Created by Shen on 2015/5/30.
 */
public class OrderStatusApi extends BaseApi {

    public OrderStatusApi(Context context, String orderId) {
        super(context);
        OrderStatusRequestDTO request1 = new OrderStatusRequestDTO();
        request1.setOrderId(orderId);
        setRequestDTO(request1);
    }

    @Override
    public String getUrl() {
        return Url.getInstance().getURL(Url.GET_ORDER_STATUS);
    }
}
