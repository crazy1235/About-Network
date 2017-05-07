package com.jacksen.basicnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button test_hurl_btn = (Button) findViewById(R.id.test_hurl_btn);
        Button test_client_btn = (Button) findViewById(R.id.test_client_btn);

        test_hurl_btn.setOnClickListener(this);
        test_client_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.test_hurl_btn:
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
            case R.id.test_client_btn:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            default:
                break;
        }
    }
}
