package com.jacksen.jackhttp;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * 网络请求结果投递给UI线程
 * Created by Admin on 2016/7/28.
 */

public class ResponseDelivery implements Executor {

    private Handler handler = new Handler(Looper.getMainLooper());

    public void deliveryResponse(final Request<?> request, final Response response) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                request.deliveryResponse(response);
            }
        };
        execute(runnable);
    }


    @Override
    public void execute(Runnable command) {
        handler.post(command);
    }
}
