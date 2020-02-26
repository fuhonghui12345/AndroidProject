package com.example.thirdparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pluginlib.ProxyActivity;
import com.example.pluginlib.pluginManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pluginManager.getInstance().init(this);

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = "/data/data/com.example.thirdparty/cache/plugin.apk";
                File pluginapk = new File(path);
                if(!pluginapk.exists()) {
                    Log.d("fhh","功能未上线，请等待。。。");
                    Toast.makeText(MainActivity.this, "功能未上线，请等待。。。", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "功能加载成功！", Toast.LENGTH_SHORT).show();
                    pluginManager.getInstance().loadApk(path);
                }
            }
        });

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.example.neplugin.NePlugin");//插件apk的类名
                startActivity(intent);
            }
        });
    }
}
