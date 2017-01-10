package com.daniel.hao.finals;

import android.text.TextUtils;

public class FinalsServer {


    private static String WD_API_IP = null;
    private static String WD_API_IP2 = null;


    public static String getWDApiIP() {
        if (FianlsDebug.ServerFinal_release) {
            if (TextUtils.isEmpty(WD_API_IP)) {
                WD_API_IP = "http://192.168.1.40:8000/v1";
            }
        } else {
            if (TextUtils.isEmpty(WD_API_IP)) {
                WD_API_IP = "http://api.gopopon.com/v1";
            }
        }
        return WD_API_IP;
    }

    public static String getWDApiIPV2() {
        if (FianlsDebug.ServerFinal_release) {
            if (TextUtils.isEmpty(WD_API_IP2)) {
                WD_API_IP2 = "http://192.168.1.40:8000/v2";
            }
        } else {
            if (TextUtils.isEmpty(WD_API_IP2)) {
                WD_API_IP2 = "http://api.gopopon.com/v2";
            }
        }
        return WD_API_IP2;
    }

    private static String client_id = null;

    public static String getWDClientId() {
        if (FianlsDebug.ServerFinal_release) {
            if (TextUtils.isEmpty(client_id)) {
                client_id = "my_app_release";
            }
        } else {
            if (TextUtils.isEmpty(client_id)) {
                client_id = "my_app_debug";
            }
        }
        return client_id;
    }

    private static String client_secret = null;

    public static String getWDClientSecret() {
        if (FianlsDebug.ServerFinal_release) {
            if (TextUtils.isEmpty(client_secret)) {
                client_secret = "f13aba159cda57a0b68144d991952013";
            }
        } else {
            if (TextUtils.isEmpty(client_secret)) {
                client_secret = "474129e0e2a3854ef3a8e65885f5839c";
            }
        }
        return client_secret;
    }

    private static String WD_DB_NAME = null;

    public static String getWDDbName() {
        if (FianlsDebug.ServerFinal_release) {
            if (TextUtils.isEmpty(WD_DB_NAME)) {
                WD_DB_NAME = "my_db_release";
            }
        } else {
            if (TextUtils.isEmpty(WD_DB_NAME)) {
                WD_DB_NAME = "my_db_debug";
            }
        }
        return WD_DB_NAME;
    }

}
