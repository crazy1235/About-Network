package com.jacksen.jackhttp;

import org.apache.http.*;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 * HttpUrlConnection
 * Created by Admin on 2016/7/28.
 */

public class HttpUrlConnStack implements HttpStack {

    HttpUrlConnConfig connConfig = HttpUrlConnConfig.getConfig();

    @Override
    public Response performRequest(Request<?> request) {
        HttpURLConnection urlConnection = null;
        try {
            //
            urlConnection = createUrlConnection(request.getUrl());
            //
            setRequestHeaders(urlConnection, request);
            //
            setRequestParams(urlConnection, request);
            //
            setHttpsConfig(request);

            return fetchResponse(urlConnection);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    /**
     * @param url
     * @return HttpURLConnection
     */
    private HttpURLConnection createUrlConnection(String url) {
        try {
            URL urlUrl = new URL(url);
            URLConnection urlConnection = urlUrl.openConnection();
            urlConnection.setConnectTimeout(connConfig.connTimeout);
            urlConnection.setReadTimeout(connConfig.readTimeout);
            urlConnection.setDoInput(true);
            urlConnection.setUseCaches(false);
            return (HttpURLConnection) urlConnection;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置headers
     *
     * @param connection
     * @param request
     */
    private void setRequestHeaders(HttpURLConnection connection, Request<?> request) {
        Set<String> headerKeys = request.getHeaders().keySet();
        for (String headerName : headerKeys) {
            connection.addRequestProperty(headerName, request.getHeaders().get(headerName));
        }
    }

    /**
     * @param connection
     * @param request
     */
    private void setRequestParams(HttpURLConnection connection, Request<?> request) {
        Request.HttpMethod method = request.getHttpMethod();
        try {
            connection.setRequestMethod(method.toString());
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        byte[] body = request.getBody();
        if (body != null) {
            connection.setDoOutput(true);
            connection.addRequestProperty(Request.HEADER_CONTENT_TYPE, request.getBodyContentType());
            DataOutputStream dataOutputStream = null;
            try {
                dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.write(body);
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 设置Https配置
     *
     * @param request
     */
    private void setHttpsConfig(Request<?> request) {
        if (request.isHttps()) {
            SSLSocketFactory sslSocketFactory = connConfig.getSslSocketFactory();
            if (sslSocketFactory != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
                HttpsURLConnection.setDefaultHostnameVerifier(connConfig.getHostnameVerifier());
            }
        }
    }

    private Response fetchResponse(HttpURLConnection connection) {
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == -1) {
                throw new IOException("could not retrieve response code from HttpUrlConnection.");
            }
            StatusLine responseStatus = new BasicStatusLine(protocolVersion, connection.getResponseCode(), connection.getResponseMessage());
            Response response = new Response(responseStatus);
            response.setEntity(entityFromUrlConnection(connection));
            addHeadersToResponse(response, connection);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addHeadersToResponse(Response response, HttpURLConnection connection) {
        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
            if (header.getKey() != null) {
                Header h = new BasicHeader(header.getKey(), header.getValue().get(0));
                response.addHeader(h);
            }
        }
    }

    private HttpEntity entityFromUrlConnection(HttpURLConnection connection) {
        BasicHttpEntity entity = new BasicHttpEntity();
        InputStream inputStream = null;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            inputStream = connection.getErrorStream();
        }
        //
        entity.setContent(inputStream);
        entity.setContentLength(connection.getContentLength());
        entity.setContentEncoding(connection.getContentEncoding());
        entity.setContentType(connection.getContentType());

        return entity;
    }

}
