package com.example.pluginlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class PluguinActivity extends Activity implements IPlugin {
    private int mFrom = FROM_INTERNAL;
    private Activity mProxyActivyty;

    @Override
    public void attach(Activity proxyActivity) {
        this.mProxyActivyty = proxyActivity;
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
            mProxyActivyty.setContentView(layourResID);
        }
    }
}
