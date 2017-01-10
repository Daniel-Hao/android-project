package com.daniel.hao.utils;

import android.text.TextUtils;

import com.daniel.hao.corelib.log.LogUtil;
import com.daniel.hao.finals.FianlsDebug;

/**
 * @author way
 */
public class L {
    private static final String TAG = "WordTalk";

    //################################i##################################
    public static void i(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.info(TAG, msg);
        }
    }

    public static void i(Class<?> _class, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.info(_class.getName(), msg);
        }
    }

    /**
     * 记录简略的重要信息
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.info(tag, msg);
        }
    }

    //################################d##################################
    public static void d(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.debug(TAG, msg);
        }
    }

    public static void d(Class<?> _class, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.info(_class.getName(), msg);
        }
    }

    /**
     * 记录详细的重要信息
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.info(tag, msg);
        }
    }

    //################################w##################################
    public static void w(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.warn(TAG, msg);
        }
    }

    public static void w(Class<?> _class, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.warn(_class.getName(), msg);
        }
    }

    /**
     * 记录异常数据信息，如无数据，服务器连接超时
     *
     * @param tag
     * @param message
     */
    public static void w(String tag, String message) {
        if (TextUtils.isEmpty(message))
            return;
        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.warn(tag, message);
        }
    }

    /**
     * 记录异常数据信息，如无数据，服务器连接超时
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg, Throwable ex) {

        if (FianlsDebug.getLog_IsDebug()) {
            LogUtil.warn(tag, msg, ex);
        }
    }

    //################################e##################################
    public static void e(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        LogUtil.error(TAG, msg);
    }

    public static void e(Class<?> _class, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        LogUtil.error(_class.getName(), msg);
    }

    /**
     * 记录严重的异常,如exception
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        LogUtil.error(tag, msg);
    }

    /**
     * 记录严重的异常,如exception
     * <p/>
     * message 为“”可能导致日志丢失
     *
     * @param tag
     * @param msg
     * @param ex
     */
    public static void e(String tag, String msg, Throwable ex) {
        LogUtil.error(tag, msg, ex);
    }

    //################################f##################################
    public static void f(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        LogUtil.fatal(TAG, msg);
    }

    public static void f(Class<?> _class, String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        LogUtil.fatal(_class.getName(), msg);
    }

    public static void f(Class<?> _class, String msg, Throwable ex) {
        LogUtil.fatal(_class.getName(), msg, ex);
    }

    /**
     * 系统崩溃日志
     *
     * @param tag
     * @param message
     */
    public static void f(String tag, String message) {
        if (TextUtils.isEmpty(message))
            return;
        LogUtil.fatal(tag, message);
    }

    /**
     * 系统崩溃日志
     *
     * @param tag
     * @param msg
     */
    public static void f(String tag, String msg, Throwable ex) {
        LogUtil.fatal(tag, msg, ex);
    }

}