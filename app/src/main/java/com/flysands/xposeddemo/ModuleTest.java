package com.flysands.xposeddemo;


import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by chenxuesong on 2019/12/30.
 */

public class ModuleTest implements IXposedHookLoadPackage {

    public static final String TAG = "flysands";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        Log.d(TAG, "try to hook " + lpparam.packageName);
        if ("com.flysands.dronydemo".equals(lpparam.packageName)) {
            XposedBridge.log("ready to hook method.");
            XposedHelpers.findAndHookMethod("com.flysands.dronydemo.MainActivity",
                                            lpparam.classLoader, "getString",
                                            new XC_MethodHook() {
                                                @Override
                                                protected void afterHookedMethod(
                                                    MethodHookParam param) throws Throwable {
                                                    super.afterHookedMethod(param);
                                                    param.setResult("xposed test");
                                                }
                                            });
        }
    }
}
