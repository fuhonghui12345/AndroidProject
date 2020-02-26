package com.example.pluginlib;

/*约束接口*/

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity); //传递主APP的上下文

    void onCreate(Bundle savedInstanceState);

}
