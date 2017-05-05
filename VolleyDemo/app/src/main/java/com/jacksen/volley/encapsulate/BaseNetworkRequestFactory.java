package com.jacksen.volley.encapsulate;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * @author jacksen
 */

public class BaseNetworkRequestFactory {

    private BaseNetworkRequest networkRequest;

    public BaseNetworkRequestFactory(BaseNetworkRequest networkRequest) {
        this.networkRequest = networkRequest;
    }

    public void getNetworkRequest(int requestId, @NonNull String url, Map<String, String> params, BaseNetworkListener networkListener) {
        networkRequest.getNetworkRequest(requestId, url, params, networkListener);
    }

}
