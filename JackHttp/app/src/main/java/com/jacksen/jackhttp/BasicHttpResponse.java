package com.jacksen.jackhttp;

/**
 * Created by Admin on 2016/7/27.
 */

public abstract class BasicHttpResponse {

    protected int getStatusCode() {
        return 0;
    }

    protected String getMessage() {
        return "";
    }
}
