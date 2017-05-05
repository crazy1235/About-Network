package com.jacksen.volley.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.jacksen.demo.R;

public class SecondActivity extends AppCompatActivity {

    private NetworkImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());


        imageView = (NetworkImageView) findViewById(R.id.image_view);
        imageView.setImageUrl(Constants.URL_DOWNLOAD_IMG, imageLoader);

    }
}
