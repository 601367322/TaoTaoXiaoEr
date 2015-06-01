package com.ttxr.activity.base;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.ttxr.bean.request_model.BaseRequestDTO;
import com.ttxr.bean.request_model.PageResquest;
import com.ttxr.util.Util;

import java.io.Serializable;

/**
 * Created by Shen on 2015/5/30.
 */
public abstract class BaseApi implements Serializable {

    private Context context;

    private BaseRequestDTO requestDTO;

    public BaseApi(Context context) {
        this.context = context;
    }

    public abstract String getUrl();

    public void setPageRequest(int currentPage, int pageSize) {
        requestDTO.setPage(new PageResquest(currentPage, pageSize));
    }

    public RequestParams getParams() {
        return Util.getTokenRequestParams(context, requestDTO);
    }

    public BaseRequestDTO getRequestDTO() {
        return requestDTO;
    }

    public void setRequestDTO(BaseRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

}
