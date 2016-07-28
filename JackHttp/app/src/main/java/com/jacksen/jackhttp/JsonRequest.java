package com.jacksen.jackhttp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 2016/7/28.
 */

public class JsonRequest extends Request<JSONObject> {

    /**
     * @param method
     * @param url
     * @param listener
     */
    public JsonRequest(HttpMethod method, String url, RequestListener<JSONObject> listener) {
        super(method, url, listener);
    }

    @Override
    public JSONObject parseResponse(Response response) {
        String jsonString = new String(response.getRawData());
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
