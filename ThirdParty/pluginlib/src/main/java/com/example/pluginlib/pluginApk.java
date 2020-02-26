package com.example.pluginlib;

/*插件APk的实体对象*/

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class pluginApk {

    //插件apk的实体对象
    public DexClassLoader mDexClassLoaer;  //插件apk的dex文件
    public Resources mResource;  //插件apk的资源文件
    public PackageInfo mPackageInfo;  //插件apk包的信息 packageName ..
    public AssetManager mAssetManager;  //插件apk包的assert

    public pluginApk(DexClassLoader mDexClassLoaer, Resources mResource, PackageInfo mPackageInfo, AssetManager mAssetManager) {
        this.mDexClassLoaer = mDexClassLoaer;
        this.mResource = mResource;
        this.mPackageInfo = mPackageInfo;
        this.mAssetManager = mAssetManager;
    }
}
