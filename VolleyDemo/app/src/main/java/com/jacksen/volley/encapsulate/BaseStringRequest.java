package com.jacksen.volley.encapsulate;

import android.support.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * String request
 *
 * @author jacksen
 */

public class BaseStringRequest extends BaseNetworkRequest {

    @Override
    protected Request getNetworkRequest(final int requestId, @NonNull String url, final Map<String, String> params, final BaseNetworkListener networkListener) {
        baseNetworkListener = networkListener != null ? networkListener : baseNetworkListener;
        if (baseNetworkListener != null) {
            baseNetworkListener.onNetworkStart(requestId, url);
        }
        return new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (baseNetworkListener != null) {
                    baseNetworkListener.onSuccessResponse(requestId, response);
                    baseNetworkListener.onNetworkFinished(requestId);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (baseNetworkListener != null) {
                    baseNetworkListener.onErrorResponse(requestId, error);
                    baseNetworkListener.onNetworkFinished(requestId);
                }
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params == null ? super.getParams() : params;
            }

        };
    }

    @Override
    protected Map<String, String> getHeader() {
        return null;
    }
}
