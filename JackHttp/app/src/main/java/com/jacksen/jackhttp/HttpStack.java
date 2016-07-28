package com.jacksen.jackhttp;

/**
 * Created by Admin on 2016/7/28.
 */

public interface HttpStack {

    /**
     * 执行http请求
     *
     * @param request
     * @return
     */
    public Response performRequest(Request<?> request);
}
