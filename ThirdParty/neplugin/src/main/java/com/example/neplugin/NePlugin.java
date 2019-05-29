package com.example.neplugin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pluginlib.PluguinActivity;
import com.example.pluginlib.ProxyActivity;

public class NePlugin extends PluguinActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ne_plugin);
    }
}
