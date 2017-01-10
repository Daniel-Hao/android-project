package com.daniel.hao.finals;

import com.daniel.hao.app.MyApp;
import com.daniel.hao.utils.PreferenceConstants;
import com.daniel.hao.utils.PreferenceUtils;

/**
 * Created by 95 on 2016/9/22.
 */
public class FianlsDebug {

    //true正式环境,false为测试环境
    public static final boolean ServerFinal_release = true;
    public static final boolean FileContants_release = true;


    // 标识是否打印日志,true打印.
    public static short Log_IsDebug = -1;

    public static boolean getLog_IsDebug() {
        if (Log_IsDebug == -1) {
            if (!PreferenceUtils.getPrefBoolean(MyApp.getInstance(), PreferenceConstants.SET_LOG_ISDEBUG, false)) {
                Log_IsDebug = 0;
            } else {
                Log_IsDebug = 1;
            }

            if (Log_IsDebug == 0) {
                return false;
            } else if (Log_IsDebug == 1) {
                return true;
            }
        } else {
            if (Log_IsDebug == 0) {
                return false;
            } else if (Log_IsDebug == 1) {
                return true;
            }
        }
        return false;
    }

    public static void setLog_IsDebug(boolean isChecked) {
        Log_IsDebug = -1;
        if (isChecked) {
            PreferenceUtils.setPrefBoolean(MyApp.getInstance(),
                    PreferenceConstants.SET_LOG_ISDEBUG, true);
        } else {
            PreferenceUtils.setPrefBoolean(MyApp.getInstance(),
                    PreferenceConstants.SET_LOG_ISDEBUG, false);
        }

    }
}
