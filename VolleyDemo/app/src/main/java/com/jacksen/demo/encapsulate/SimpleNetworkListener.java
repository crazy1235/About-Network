package com.jacksen.volleydemo.encapsulate;

import com.android.volley.VolleyError;

/**
 * @author jacksen
 */

public abstract class SimpleNetworkListener implements BaseNetworkListener {


    @Override
    public void onSuccessResponse(int requestId, String response) {
//        this.onSuccessResponse(requestId, t);
    }

    @Override
    public void onErrorResponse(int requestId, VolleyError error) {

    }

    @Override
    public void onNetworkFinished(int requestId) {

    }

//    public abstract void onSuccessResponse(int requestId, T t);

    public abstract void onErrorResponse(String errorMsg);
}
