package com.jacksen.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private Button openSecondBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);


        openSecondBtn = (Button) findViewById(R.id.open_second_btn);
        openSecondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testStringRequest();
//                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }


    private void testStringRequest() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_WEATHER_INFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("MainActivity", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("MainActivity", "error");
                    }
                });
        requestQueue.add(stringRequest);
    }


    private void testJsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}

