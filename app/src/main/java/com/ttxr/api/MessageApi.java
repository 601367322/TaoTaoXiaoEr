package com.ttxr.api;

import android.content.Context;

import com.ttxr.activity.base.BaseApi;
import com.ttxr.bean.request_model.UserMsgRequestDTO;
import com.ttxr.util.Url;

import java.io.Serializable;

/**
 * Created by Shen on 2015/5/30.
 */
public class MessageApi extends BaseApi implements Serializable {

    public MessageApi(Context context) {
        super(context);
        UserMsgRequestDTO request1 = new UserMsgRequestDTO();
        request1.setMsgType(null);
        request1.setStatus(null);
        setRequestDTO(request1);
    }

    @Override
    public String getUrl() {
        return Url.MESSAGE_LIST;
    }

}
