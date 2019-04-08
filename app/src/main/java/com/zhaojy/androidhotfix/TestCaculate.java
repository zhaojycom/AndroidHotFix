package com.zhaojy.androidhotfix;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Zhaojy
 * @date 2019/4/8.
 * email：770363647@qq.com
 * weixin:18774512067
 * description：
 */
public class TestCaculate {

    public int a = 10;
    public int b = 1;

    public void caculate(Context context) {
        Toast.makeText(context, "结果" + a / b, Toast.LENGTH_SHORT).show();
    }
}

