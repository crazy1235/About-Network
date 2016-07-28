package com.jacksen.jackhttp;

import android.graphics.Bitmap;

/**
 * Created by Admin on 2016/7/28.
 */

public class ImageRequest extends Request<Bitmap> {
    /**
     * @param method
     * @param url
     * @param listener
     */
    public ImageRequest(HttpMethod method, String url, RequestListener<Bitmap> listener) {
        super(method, url, listener);
    }

    @Override
    public Bitmap parseResponse(Response response) {
        return null;
    }
}
