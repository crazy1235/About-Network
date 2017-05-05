package com.jacksen.volley.encapsulate;

import com.android.volley.VolleyError;

/**
 * @author jacksen
 */

public interface BaseNetworkListener {

    void onNetworkStart(int requestId, String url);

    void onSuccessResponse(int requestId, String response);

    void onErrorResponse(int requestId, VolleyError error);

    void onNetworkFinished(int requestId);

}
