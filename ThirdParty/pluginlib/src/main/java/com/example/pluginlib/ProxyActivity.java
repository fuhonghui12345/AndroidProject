package com.example.pluginlib;

/*管理插件apk生命周期*/

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

//代理Activity，管理插件的生命周期。
public class ProxyActivity extends Activity {

    private String mClassName; //主APP中传来的要打开的插件apk的classname
    private pluginApk mpluginApk;
    private IPlugin mIPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mpluginApk = pluginManager.getInstance().getMpluginApk();
        launchPluginActivity();
    }

    private void launchPluginActivity() { //
        if(mpluginApk == null) {
            Log.d("fhh", "please load third part app firstly!");
            //Toast.makeText(this,"功能未上线，请等待。。。",Toast.LENGTH_SHORT);
        }
        try {//加载插件apk的文件
            Class<?> clazz = mpluginApk.mDexClassLoaer.loadClass(mClassName);
            Object obj = clazz.newInstance();//实例化
            if(obj instanceof IPlugin){//看下是否符合规范
                mIPlugin = (IPlugin)obj;
                mIPlugin.attach(this);//主app的上下文传到插件
                Bundle bundle = new Bundle();
                bundle.putInt("FROM",IPlugin.FROM_EXTERNAL);//测试的时候 我们可能已经安装了插件app，这时候属于内部的，不需要去获取dexclassLoader,resource
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
