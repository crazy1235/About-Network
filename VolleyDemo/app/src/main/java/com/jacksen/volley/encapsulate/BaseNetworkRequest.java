package com.jacksen.volley.encapsulate;

import android.support.annotation.NonNull;

import com.android.volley.Request;

import java.util.Map;

/**
 * @author jacksen
 */

public abstract class BaseNetworkRequest {

    protected BaseNetworkListener baseNetworkListener;

    protected void setBaseNetworkListener(BaseNetworkListener baseNetworkListener) {
        this.baseNetworkListener = baseNetworkListener;
    }

    protected abstract Request getNetworkRequest(int requestId, @NonNull String url, Map<String, String> params, BaseNetworkListener networkListener);

    protected abstract Map<String, String> getHeader();
}
