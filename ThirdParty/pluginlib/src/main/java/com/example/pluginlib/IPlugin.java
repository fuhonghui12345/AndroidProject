package com.example.pluginlib;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    void onCreate(Bundle savedInstanceState);
}
