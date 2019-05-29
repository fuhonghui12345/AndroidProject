package com.example.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class pluginApk {

    //插件apk的实体对象
    public DexClassLoader mDexClassLoaer;
    public Resources mResource;
    public PackageInfo mPackageInfo;
    public AssetManager mAssetManager;

    public pluginApk(DexClassLoader mDexClassLoaer, Resources mResource, PackageInfo mPackageInfo, AssetManager mAssetManager) {
        this.mDexClassLoaer = mDexClassLoaer;
        this.mResource = mResource;
        this.mPackageInfo = mPackageInfo;
        this.mAssetManager = mAssetManager;
    }
}
