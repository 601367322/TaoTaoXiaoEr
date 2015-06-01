package com.ttxr.api;

import android.content.Context;

import com.ttxr.activity.base.BaseApi;
import com.ttxr.bean.request_model.OrderListRequestDTO;
import com.ttxr.util.Url;

/**
 * Created by Shen on 2015/5/30.
 */
public class OrderHistoryApi extends BaseApi {

    public OrderHistoryApi(Context context, String orderByStr, String statusStr) {
        super(context);
        OrderListRequestDTO request1 = new OrderListRequestDTO();
        request1.setTimeSort(orderByStr);
        request1.setStatusSort(statusStr);

        setRequestDTO(request1);
    }

    @Override
    public String getUrl() {
        return Url.GET_MY_ORDER_LIST;
    }

}
