package com.jacksen.jackhttp;

import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Admin on 2016/7/27.
 */

public class Response extends BasicHttpResponse {

    // 原始的response主体数据
    private byte[] rawData = new byte[0];

    public Response(org.apache.http.StatusLine statusline) {
        super(statusline);
    }

    public Response(ProtocolVersion ver, int code, String reason) {
        super(ver, code, reason);
    }

    @Override
    public void setEntity(HttpEntity entity) {
        super.setEntity(entity);
        rawData = entityToBytes(getEntity());
    }

    public byte[] getRawData() {
        return rawData;
    }

    public int getStatusCode() {
        return getStatusLine().getStatusCode();
    }

    public String getMessage() {
        return getStatusLine().getReasonPhrase();
    }

    /**
     * @param entity
     * @return
     */
    private byte[] entityToBytes(HttpEntity entity) {
        try {
            EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
