package com.jacksen.volley.encapsulate;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.VolleyError;

import java.lang.reflect.ParameterizedType;

/**
 * @author jacksen
 */

public abstract class StringNetworkListener<T extends BaseBean> implements BaseNetworkListener {

    @Override
    public void onNetworkStart(int requestId, String url) {

    }

    @Override
    public void onSuccessResponse(int requestId, String response) {
        T t = JSONObject.parseObject(response, ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        if (BaseNetworkUtil.SUCCESS_NETWORK_REQUEST.equals(t.getOperFlag())) {
            onSuccessResponse(requestId, t);
        } else if (BaseNetworkUtil.FAILURE_NETWORK_REQUEST.equals(t.getOperFlag())) {
            onErrorResponse("错误代码: " + t.getErrorCode());
        }
    }

    @Override
    public void onErrorResponse(int requestId, VolleyError error) {
        onErrorResponse(error != null ? error.getMessage() : "未知错误！");
    }

    @Override
    public void onNetworkFinished(int requestId) {

    }

    public abstract void onSuccessResponse(int requestId, T t);

    public abstract void onErrorResponse(String errorMsg);
}
