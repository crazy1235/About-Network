package com.jacksen.volleydemo.encapsulate;

import com.android.volley.VolleyError;

/**
 * @author jacksen
 */

public interface BaseNetworkListener {

    void onSuccessResponse(int requestId, String response);

    void onErrorResponse(int requestId, VolleyError error);

    void onNetworkFinished(int requestId);

}
