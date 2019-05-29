package com.example.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class pluginManager {
    private static pluginManager instance = new pluginManager();
    public static pluginManager getInstance(){
            return instance;
    }

    private pluginManager(){ }
    private Context mContex;
    private pluginApk mpluginApk;

    public void init(Context context){
        this.mContex = context;
    }

    public pluginApk getMpluginApk(){
        return mpluginApk;
    }
    //加载插件Apk文件
    public void loadApk(String apkpath){
        PackageInfo packageInfo = mContex.getPackageManager().getPackageArchiveInfo(apkpath,
                PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);
        if(packageInfo == null)
            return;
        DexClassLoader classLoader = createDexClassLoader(apkpath);
        AssetManager am = createAssetManager(apkpath);
        Resources resources = createResources(am);
        mpluginApk = new pluginApk(classLoader,resources,packageInfo,am);
    }

    private Resources createResources(AssetManager am) {
        Resources res = mContex.getResources();
        return new Resources(am,res.getDisplayMetrics(),res.getConfiguration());
    }

    private AssetManager createAssetManager(String apkpath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(am,apkpath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createDexClassLoader(String apkpath) {
        File file = mContex.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkpath,file.getAbsolutePath(),null,mContex.getClassLoader());
    }

}
