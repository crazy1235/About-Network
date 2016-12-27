package com.jacksen.basicnetwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jacksen.basicnetwork.utils.Constants;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient example
 *
 * @author jacksen
 */
public class SecondActivity extends AppCompatActivity {

    private final String TEST_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }


    public InputStream getInputStreamFromUrl(String url) {
        //
        InputStream inputStream = null;
        //
        HttpClient httpClient = new DefaultHttpClient();

        //
        try {
            HttpResponse response = httpClient.execute(new HttpGet(TEST_URL));
            inputStream = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }


    private void postData(String phone) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Constants.URL_QUERY_PHONE);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("phone", phone));
        try {
            // 使用UTF-8格式对数据进行编码
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            //
            HttpResponse response = httpClient.execute(httpPost);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * InputStream to StringBuilder
     *
     * @param inputStream
     * @return
     */
    private StringBuilder inputStreamToStringBuilder(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    /**
     * InputStream to String
     *
     * @param inputStream
     * @return
     */
    private String inputStreamToString(InputStream inputStream) {
        String line = "";
        String result = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
