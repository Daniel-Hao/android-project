package com.daniel.hao.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.daniel.hao.app.MyApp;

public class CommonUtil {

    public static MyApp getApp() {
        return MyApp.getInstance();
    }

    private static long lastClickTime;

    /**
     * 两次点击时间相隔小于minDisTimeMillis值（默认800ms）,便不会触发事件
     **/
    public static boolean isNotFastDoubleClick(long... minDisTimeMillis) {
        long disTimMills = minDisTimeMillis != null
                && minDisTimeMillis.length > 0 ? minDisTimeMillis[0] : 800;
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < disTimMills) {
            return false;
        }
        lastClickTime = time;
        return true;
    }

    /**
     * 检查网络
     */
    public boolean CheckNetwork() {
        boolean flag = false;
        try {
            ConnectivityManager cwjManager = (ConnectivityManager) getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cwjManager.getActiveNetworkInfo() != null)
                flag = cwjManager.getActiveNetworkInfo().isAvailable();
        } catch (Exception e) {
            L.e(e.getMessage());
        }
        return flag;
    }


}
