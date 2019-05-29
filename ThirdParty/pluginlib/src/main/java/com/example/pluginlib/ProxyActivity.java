package com.example.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

//代理Activity，管理插件的生命周期。
public class ProxyActivity extends Activity {

    private String mClassName;
    private pluginApk mpluginApk;
    private IPlugin mIPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mpluginApk = pluginManager.getInstance().getMpluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if(mpluginApk == null)
            Log.d("fhh","please load third part app firstly!");
        try {
            Class<?> clazz = mpluginApk.mDexClassLoaer.loadClass(mClassName);
            Object obj = clazz.newInstance();
            if(obj instanceof IPlugin){
                mIPlugin = (IPlugin)obj;
                mIPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);
                mIPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return mpluginApk != null ? mpluginApk.mResource:super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mpluginApk != null ? mpluginApk.mAssetManager:super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mpluginApk != null ? mpluginApk.mDexClassLoaer:super.getClassLoader();
    }
}
