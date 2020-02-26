package com.example.pluginlib;

/*作为插件apk的一个规范，插件apk需要继承自这个规范*/

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class PluguinActivity extends Activity implements IPlugin { //Iplugin
    private int mFrom = FROM_INTERNAL;
    private Activity mProxyActivyty;

    @Override
    public void attach(Activity proxyActivity) {
        this.mProxyActivyty = proxyActivity; //上下文
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null)
            mFrom = savedInstanceState.getInt("FROM");
        if(mFrom == FROM_INTERNAL){
            super.onCreate(savedInstanceState);
            mProxyActivyty = this;
        }
    }

    @Override
    public void setContentView(int layourResID) {
        if(mFrom == FROM_INTERNAL)super.setContentView(layourResID);
        else{
            mProxyActivyty.setContentView(layourResID);//如果是外部的apk文件，则直接用外部加载进来的resource
        }
    }

    @Override
    public View findViewById(int id) {
        if(mFrom == FROM_INTERNAL)return  super.findViewById(id);
        else return mProxyActivyty.findViewById(id);
    }
}
