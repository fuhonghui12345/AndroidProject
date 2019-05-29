package com.example.thirdparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pluginlib.ProxyActivity;
import com.example.pluginlib.pluginManager;

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
                pluginManager.getInstance().loadApk(path);
            }
        });

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.example.neplugin.NePlugin");
                startActivity(intent);
            }
        });
    }
}
