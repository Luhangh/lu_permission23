package com.premiss.lucky.lu_premissdemo;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * 展示启动页面
 * <p/>
 * Created by luhang on 16/11/15.
 */

public class MainActivity extends AppCompatActivity {
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void gopremiss(View view){
        startActivity(new Intent(this,FirstActivity.class));

    }

    public void gopremiss1(View view){
        Intent inte =new Intent(this,FirstActivity.class);
        inte.putExtra("ismiss","1");
        startActivity(inte);
    }
    // 启动应用的设置
    public void startAppSettings(View view) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }
}
