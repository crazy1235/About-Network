package com.jacksen.volleydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RequestQueue requestQueue;
    private Button openSecondBtn;

    private Button stringRequestBtn;
    private Button jsonRequestBtn;

    private TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        openSecondBtn = (Button) findViewById(R.id.open_second_btn);
        openSecondBtn.setOnClickListener(this);

        stringRequestBtn = (Button) findViewById(R.id.string_request_btn);
        jsonRequestBtn = (Button) findViewById(R.id.json_request_btn);

        stringRequestBtn.setOnClickListener(this);
        jsonRequestBtn.setOnClickListener(this);

        contentTv = (TextView) findViewById(R.id.content_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_second_btn:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.string_request_btn:
                contentTv.setText("");
                testStringRequest();
                break;
            case R.id.json_request_btn:
                contentTv.setText("");
                testJsonRequest();
                break;
            default:
                break;
        }
    }

    private void testStringRequest() {
        StringRequest stringRequest = new StringRequest(Constants.URL_GANK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MainActivity", response);
                        contentTv.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("MainActivity", error.getMessage());
                        contentTv.setText(error.getMessage());
                    }
                });
        requestQueue.add(stringRequest);
    }


    private void testJsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Constants.URL_GANK, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        contentTv.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                contentTv.setText(error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}

