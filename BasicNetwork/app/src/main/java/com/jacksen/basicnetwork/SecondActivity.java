package com.jacksen.basicnetwork;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jacksen.basicnetwork.bean.PhoneInfo;
import com.jacksen.basicnetwork.utils.Constants;
import com.jacksen.basicnetwork.utils.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient example
 *
 * @author jacksen
 */
public class SecondActivity extends AppCompatActivity {

    private EditText inputPhoneEt;
    private Button queryBtn;
    private TextView phoneContextTv;

    private static final int WHAT_MSG = 0x0002;

    private SecondHandler secondHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        inputPhoneEt = (EditText) findViewById(R.id.input_phone_et);
        queryBtn = (Button) findViewById(R.id.query_btn);
        phoneContextTv = (TextView) findViewById(R.id.phone_content_tv);

        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = inputPhoneEt.getText().toString();
                startQueryPhone(phoneNum);
            }
        });
        secondHandler = new SecondHandler(this);
    }

    /**
     * 开启线程查询手机号信息
     *
     * @param phoneNum
     */
    private void startQueryPhone(final String phoneNum) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                secondHandler.sendMessage(Message.obtain(secondHandler, WHAT_MSG, getPhoneContent(phoneNum)));
            }
        }).start();
    }

    /**
     * 设置手机信息
     *
     * @param result
     */
    public void setPhoneContent(String result) {
        PhoneInfo phoneInfo = JSON.parseObject(result, PhoneInfo.class);
        phoneContextTv.setText(phoneInfo.toString());
    }

    public String getPhoneContent(String phoneNum) {
        //
        String result = "";
        //
        HttpClient httpClient = new DefaultHttpClient();
        HttpParams httpParams = httpClient.getParams();
        // 请求超时时间
        httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // 读取超时时间
        httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);

        HttpGet httpGet = new HttpGet(Constants.URL_QUERY_PHONE + "phone=" + phoneNum);
        Log.d("SecondActivity", httpGet.getURI().toString());
        httpGet.setHeader("apikey", Constants.API_KEY);

        //
        try {
            HttpResponse response = httpClient.execute(httpGet);
            InputStream inputStream = response.getEntity().getContent();
            result = Utils.convertStreamToString(inputStream);
            Log.d("SecondActivity", result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    private void postData(String phone) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Constants.URL_QUERY_PHONE);

        httpPost.setHeader("apikey", Constants.API_KEY);


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


    private static class SecondHandler extends Handler {

        private WeakReference<SecondActivity> weakReference;

        public SecondHandler(SecondActivity secondActivity) {
            this.weakReference = new WeakReference<SecondActivity>(secondActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_MSG:
                    weakReference.get().setPhoneContent((String) msg.obj);
                    break;
            }
        }
    }

}
