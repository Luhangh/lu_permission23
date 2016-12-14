package com.premiss.lucky.lu_premissdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0; // 请求码
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    private Button plug_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mPermissionsChecker = new PermissionsChecker(this);
    }
    @Override protected void onResume() {
        super.onResume();

        // 缺少权限时, 进入权限配置页面
        if(!TextUtils.isEmpty(getIntent().getStringExtra("ismiss"))){
            if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                Intent inte =new Intent(this,MainActivity.class);
                inte.putExtra("start","请开启权限设置");
                startActivity(inte);
            }
        }else{
            if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                startPermissionsActivity();
            }
        }

    }


    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }
}
