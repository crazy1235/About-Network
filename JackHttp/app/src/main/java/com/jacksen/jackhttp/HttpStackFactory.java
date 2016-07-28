package com.jacksen.jackhttp;

import android.os.Build;

/**
 * 创建不同的Http执行类
 * Created by Admin on 2016/7/28.
 */

public class HttpStackFactory {

    /**
     * 根据SDK版本号来创建不同的Http执行者
     *
     * @return
     */
    public static HttpStack createHttpStack() {
        int sdkApi = Build.VERSION.SDK_INT;
        if (sdkApi >= Build.VERSION_CODES.GINGERBREAD) {
            return new HttpUrlConnStack();
        }
        return new HttpClientStack();
    }


}
