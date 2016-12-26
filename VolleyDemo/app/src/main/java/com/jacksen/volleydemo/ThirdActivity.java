package com.jacksen.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.jacksen.volleydemo.bean.Weather;

/**
 * test fastjson request
 *
 * @author jacksen
 */
public class ThirdActivity extends AppCompatActivity {

    private Button getWeatherBtn;

    private TextView weatherInfoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        getWeatherBtn = (Button) findViewById(R.id.get_weather_btn);
        getWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testFastjsonRequest();
            }
        });

        weatherInfoTv = (TextView) findViewById(R.id.weather_info_tv);
    }


    /**
     *
     */
    private void testFastjsonRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        FastJsonRequest<Weather> fastJsonRequest = new FastJsonRequest<>(Constants.URL_WEATHER_INFO, Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather response) {
                weatherInfoTv.setText(response.getData().getWendu() + "\n" + response.getData().getGanmao());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThirdActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(fastJsonRequest);
    }
}
