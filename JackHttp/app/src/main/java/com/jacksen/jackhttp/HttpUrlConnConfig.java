package com.jacksen.jackhttp;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by Admin on 2016/7/28.
 */

public class HttpUrlConnConfig extends HttpConfig {

    private static HttpUrlConnConfig connConfig = new HttpUrlConnConfig();

    private SSLSocketFactory sslSocketFactory = null;
    private HostnameVerifier hostnameVerifier = null;


    private HttpUrlConnConfig() {

    }

    public static HttpUrlConnConfig getConfig() {
        return connConfig;
    }

    public void setHttpsConfig(SSLSocketFactory sslSocketFactory, HostnameVerifier hostnameVerifier) {
        this.sslSocketFactory = sslSocketFactory;
        this.hostnameVerifier = hostnameVerifier;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier() {
        return hostnameVerifier;
    }
}
