package com.ttxr.bean.request_model;

import java.io.Serializable;

/**
 * Created by Shen on 2015/5/30.
 */
public class BaseRequestDTO implements Serializable {

    public PageResquest page;  //分页信息 不能为空

    public PageResquest getPage() {
        return page;
    }
    public void setPage(PageResquest page) {
        this.page = page;
    }
}
