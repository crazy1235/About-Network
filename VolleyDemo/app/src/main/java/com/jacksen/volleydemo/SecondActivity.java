package com.jacksen.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private NetworkImageView networkImageView;

    private Button imageRequestBtn;

    private Button imageLoaderBtn;

    private Button networkIvBtn;

    private ImageView imageView;

    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        requestQueue = Volley.newRequestQueue(this);

        imageRequestBtn = (Button) findViewById(R.id.image_request_btn);
        imageLoaderBtn = (Button) findViewById(R.id.image_loader_btn);
        networkIvBtn = (Button) findViewById(R.id.network_iv_btn);

        imageView = (ImageView) findViewById(R.id.image_view);
        networkImageView = (NetworkImageView) findViewById(R.id.network_image_view);

        //
        imageRequestBtn.setOnClickListener(this);
        imageLoaderBtn.setOnClickListener(this);
        networkIvBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_request_btn:
                testImageRequest();
                break;
            case R.id.image_loader_btn:
                testImageLoader();
                break;
            case R.id.network_iv_btn:
                testNetworkImageView();
                break;
            default:
                break;
        }
    }

    /**
     * image request
     */
    private void testImageRequest() {
        ImageRequest imageRequest = new ImageRequest(Constants.URL_DOWNLOAD_IMG, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SecondActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //
        requestQueue.add(imageRequest);
    }


    /**
     * image loader
     */
    private void testImageLoader() {

        // 2.
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        };

        // 3.
        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);

        //4.
        ImageLoader.ImageListener imageListener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                imageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SecondActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        // 5.
        imageLoader.get(Constants.URL_DOWNLOAD_IMG, imageListener);
    }


    /**
     * NetworkImageView
     */
    private void testNetworkImageView(){

        ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        networkImageView.setDefaultImageResId(R.mipmap.ic_launcher);
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
        networkImageView.setImageUrl(Constants.URL_DOWNLOAD_IMG, imageLoader);
    }

}
