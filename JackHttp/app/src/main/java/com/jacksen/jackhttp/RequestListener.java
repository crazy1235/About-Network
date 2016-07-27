package com.jacksen.jackhttp;

/**
 * 网络请求Listener，在UI线程中执行
 * Created by Admin on 2016/7/27.
 */

public interface RequestListener<T> {

    /**
     * 请求完成回调
     *
     * @param code
     * @param response
     * @param errMsg
     */
    public void onComplete(int code, T response, String errMsg);

}
