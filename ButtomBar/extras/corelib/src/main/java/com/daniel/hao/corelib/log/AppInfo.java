package com.daniel.hao.corelib.log;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


/**
 * Created by hdl on 2016/5/18.
 */
public class AppInfo {

    private static final String TAG = "AppInfo";

    /**
     * 获取应用的包名
     */
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取应用的版本号
     */
    public static String getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode + "";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取应用的名称
     */
    public static String getAppName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getPackageName(context), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获得当前进程号
     *
     * @return string
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        if (activityManager != null) {
            for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                    .getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    return appProcess.processName;
                }
            }
        }
        return null;
    }

    public static void logDeviceAndAppInfo(Context context) {
        LogUtil.info(TAG, "deviceInfo:设备型号 " + Build.MODEL + ",设备SDK版本 " + Build.VERSION.SDK + ",设备的系统版本 " + Build.VERSION.RELEASE);
        LogUtil.info(TAG, "appName:应用名称" + getAppName(context) + ",当前版本名称 " + getVersionName(context) + ",版本号 " + getVersionCode(context));
    }

}
