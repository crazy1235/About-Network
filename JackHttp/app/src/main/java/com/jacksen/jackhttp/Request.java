package com.jacksen.jackhttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 2016/7/27.
 */

public abstract class Request<T> implements Comparable<Request<T>> {

    // 默认编码方式
    private static final String DEFAULT_PARAMS_ENCODING = "URF-8";

    // 请求序列号
    protected int serialNum = 0;

    // 默认优先级
    protected Priority priority = Priority.NORMAL;

    // 是否取消请求
    protected boolean isCancel = false;

    // 是否缓存请求
    private boolean shouldCache = false;

    // 请求Listener
    protected RequestListener<T> requestListener;

    //
    private String url = "";

    //
    HttpMethod httpMethod = HttpMethod.GET;


    // 请求的header
    private Map<String, String> headers = new HashMap<>();


    // 请求的参数
    private Map<String, String> bodyParams = new HashMap<>();

    /**
     * @param method
     * @param url
     * @param listener
     */
    public Request(HttpMethod method, String url, RequestListener<T> listener) {
        this.httpMethod = method;
        this.url = url;
        this.requestListener = listener;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }


    public Map<String, String> getBodyParams() {
        return bodyParams;
    }

    public void setSerialNum(int serialNum) {
        this.serialNum = serialNum;
    }

    private int getSerialNum() {
        return this.serialNum;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    private Priority getPriority() {
        return priority;
    }

    public boolean isShouldCache() {
        return shouldCache;
    }

    public void setShouldCache(boolean shouldCache) {
        this.shouldCache = shouldCache;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public boolean isHttps() {
        return url.startsWith("https");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 从原生的网络请求中解析结果，子类需覆写此方法
     *
     * @param response
     * @return
     */
    public abstract T parseResponse(Response response);

    public final void deliveryResponse(Response response) {
        T result = parseResponse(response);
        if (requestListener != null) {
            int code = response != null ? response.getStatusCode() : -1;
            String msg = response != null ? response.getMessage() : "unkown error";
            requestListener.onComplete(code, result, msg);
        }
    }

    /**
     * @return
     */
    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    /**
     * @return
     */
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
    }

    /**
     * @return 返回POST / GET 请求时的Body参数字节数组
     */
    public byte[] getBody() {
        Map<String, String> params = getParams();
        if (params != null && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        }
        return null;
    }

    /**
     * 将参数转换为URL编码的参数串
     *
     * @param params
     * @param paramsEncoding
     * @return
     */
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * @return
     */
    private Map<String, String> getParams() {
        return bodyParams;
    }

    /**
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * 对请求进行排序处理，根据优先级和加入到队列的序号进行排序
     *
     * @param tRequest
     * @return
     */
    @Override
    public int compareTo(Request<T> tRequest) {
        Priority priority = this.getPriority();
        Priority anotherPriority = tRequest.getPriority();
        return priority.equals(anotherPriority)
                ? getSerialNum() - tRequest.getSerialNum()
                : priority.ordinal() - anotherPriority.ordinal();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = -1;
        result = prime * result + ((headers == null) ? 0 : headers.hashCode());
        result = prime * result + ((httpMethod == null) ? 0 : httpMethod.hashCode());
        result = prime * result + ((bodyParams == null) ? 0 : bodyParams.hashCode());
        result = prime * result + ((priority == null) ? 0 : priority.hashCode());
        result = prime * result + (shouldCache ? 1231 : 1237);
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Request<?> other = (Request<?>) obj;
        if (headers == null) {
            if (other.headers != null)
                return false;
        } else if (!headers.equals(other.headers))
            return false;
        if (httpMethod != other.httpMethod)
            return false;
        if (bodyParams == null) {
            if (other.bodyParams != null)
                return false;
        } else if (!bodyParams.equals(other.bodyParams))
            return false;
        if (priority != other.priority)
            return false;
        if (shouldCache != other.shouldCache)
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    /**
     * 网络请求方式
     */
    public static enum HttpMethod {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        private String mHttpMethod = "";

        private HttpMethod(String method) {
            this.mHttpMethod = method;
        }

        @Override
        public String toString() {
            return mHttpMethod;
        }
    }

    /**
     * 请求优先级
     */
    public static enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }
}



