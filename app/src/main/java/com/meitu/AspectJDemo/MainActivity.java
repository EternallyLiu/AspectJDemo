package com.meitu.AspectJDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_double;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_double = findViewById(R.id.btn_double);
        btn_double.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其实前面已经加入了双击判断代码
                Log.e("DoubleClick", "防抖动操作完成");
            }
        });
        testArgs(10, "hahaha");
    }

    /**
     * 使用AspectJ拦截并打印传入的参数
     * @param x
     * @param str
     */
    public void testArgs(int x, String str) {
        Log.e("PrintArg", "这是从外部执行的输出");
    }
}
