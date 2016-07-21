package com.jacksen.basicnetwork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jacksen.basicnetwork.utils.Constants;
import com.jacksen.basicnetwork.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText inputPhoneEt;
    private Button queryBtn;
    private Button downloadBtn;
    private ImageView imageView;

    private static final int WHAT_DOWNLOAD_IMG = 1;

    private static final int WHAT_QUERY_PHONE = 2;

    private MyHandler myHandler;

    private String imgName = Base64.encodeToString(Constants.URL_DOWNLOAD_IMG.getBytes(), Base64.DEFAULT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputPhoneEt = (EditText) findViewById(R.id.input_phone_et);

        queryBtn = (Button) findViewById(R.id.query_btn);

        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = inputPhoneEt.getText().toString();
                startQueryPhone(phoneNum);
            }
        });

        imageView = (ImageView) findViewById(R.id.image_view);
        downloadBtn = (Button) findViewById(R.id.download_btn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImage();
            }
        });

        myHandler = new MyHandler(this);

    }


    /**
     * @param phone
     */
    private void startQueryPhone(String phone) {

    }


    /**
     * 显示图片
     */
    private void showImage() {
        File file = new File(getCacheDir(), imgName);
        if (Utils.checkFileIsExist(file)) {
            Log.d("MainActivity", "image file exists");
            showImage(BitmapFactory.decodeFile(file.getAbsolutePath()));
        } else {
            startDownloadImg();
        }
    }


    /**
     * 另开线程下载图片
     */
    private void startDownloadImg() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 1.
                    URL url = new URL(Constants.URL_DOWNLOAD_IMG);
                    // 2.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    // 3.
                    int responseCode = connection.getResponseCode();
                    if (200 == responseCode) {
                        // 4.
                        InputStream inputStream = connection.getInputStream();
                        // 5.

                        // 6.
                        File file = new File(getCacheDir(), imgName);
                        if (!Utils.checkFileIsExist(file)) {
                            file.createNewFile();
                        }
                        Log.d("MainActivity", file.getAbsolutePath().toString());
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] buffer = new byte[1024];
                        int len = -1;
                        while ((len = inputStream.read(buffer)) != -1) {
                            Log.d("MainActivity", "buffer.length:" + len);
                            fileOutputStream.write(buffer, 0, len);
                        }
                        fileOutputStream.close();

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        inputStream.close();

                        // 7.
                        Message msg = Message.obtain(myHandler, WHAT_DOWNLOAD_IMG, bitmap);
                        myHandler.sendMessage(msg);

                    } else {
                        // error
                        Log.d("MainActivity", "responseCode:" + responseCode);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 显示bitmap
     *
     * @param bitmap
     */
    private void showImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }


    /**
     *
     */
    private static class MyHandler extends Handler {

        private final WeakReference<MainActivity> weakReference;

        public MyHandler(MainActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_DOWNLOAD_IMG:
                    MainActivity activity = Utils.checkNotNull(weakReference.get(), "activity 引用为空！");
                    activity.showImage((Bitmap) msg.obj);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
