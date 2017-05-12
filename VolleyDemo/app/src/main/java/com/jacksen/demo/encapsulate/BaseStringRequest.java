package com.jacksen.volleydemo.encapsulate;

import android.support.annotation.NonNull;

import com.android.volley.Request;

import java.util.Map;

/**
 * @author jacksen
 */

public class BaseStringRequest implements BaseNetworkRequest {

    @Override
    public void setBaseNetworkListener(BaseNetworkListener baseNetworkListener) {

    }

    @Override
    public Request getNetworkRequest(int requestId, @NonNull String url, Map<String, String> params, BaseNetworkListener networkListener) {
        return null;
    }

}
