package com.jacksen.volleydemo.encapsulate;

import android.support.annotation.NonNull;

import com.android.volley.Request;

import java.util.Map;

/**
 * @author jacksen
 */

public interface BaseNetworkRequest {

    void setBaseNetworkListener(BaseNetworkListener baseNetworkListener);

    Request getNetworkRequest(int requestId, @NonNull String url, Map<String, String> params, BaseNetworkListener networkListener);
}
