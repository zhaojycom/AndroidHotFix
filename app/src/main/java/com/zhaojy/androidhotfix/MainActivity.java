package com.zhaojy.androidhotfix;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import permison.PermissonUtil;
import permison.listener.PermissionListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private final static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.READ_EXTERNAL_STORAGE};

    private Button caculate;
    private Button fix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //申请权限
        applyPermission();
    }

    private void init() {
        findViewById();
        //设置监听器
        setListener();
    }

    private void findViewById() {
        caculate = findViewById(R.id.caculate);
        fix = findViewById(R.id.fix);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.caculate:
                TestCaculate testCaculate = new TestCaculate();
                testCaculate.caculate(this);
                break;
            case R.id.fix:
                fix();
                break;
            default:
                break;
        }
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        caculate.setOnClickListener(this);
        fix.setOnClickListener(this);
    }

    private void fix() {
        try {
            String dexPath = Environment.getExternalStorageDirectory() + "/classes2.dex";
            HotFixUtils.patch(this, dexPath, "com.aiiage.testhotfix.TestCaculate");

            Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "修复失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    /**
     * 申请权限
     */
    private void applyPermission() {
        PermissonUtil.checkPermission(MainActivity.this, new PermissionListener() {
            @Override
            public void havePermission() {
                init();
            }

            @Override
            public void requestPermissionFail() {

            }
        }, permissions);
    }

}
