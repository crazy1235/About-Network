package com.jacksen.volley.encapsulate;

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.toolbox.Volley;

/**
 * @author jacksen
 */

public class BaseNetworkUtil {

    @Deprecated
    public static final int DEFAULT_ERROR_CODE = -1;

    public static final String SUCCESS_NETWORK_REQUEST = "100";

    public static final String FAILURE_NETWORK_REQUEST = "101";

    private volatile static BaseNetworkUtil baseNetworkUtil;

    /**
     * @param context
     */
    public static void init(@NonNull Context context) {
        if (null == baseNetworkUtil) {
            synchronized (BaseNetworkUtil.class) {
                if (null == baseNetworkUtil) {
                    baseNetworkUtil = new BaseNetworkUtil(context);
                }
            }
        }
    }

    public static BaseNetworkUtil getInstance() {
        if (null == baseNetworkUtil) {
            throw new RuntimeException("please invoke init() method first.");
        } else {
            // TODO

        }
        return baseNetworkUtil;
    }

    private BaseNetworkUtil(Context context) {
        Volley.newRequestQueue(context);
        // TODO read the local data


    }

}
