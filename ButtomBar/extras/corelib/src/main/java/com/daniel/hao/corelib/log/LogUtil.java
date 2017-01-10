package com.daniel.hao.corelib.log;

import org.apache.log4j.Logger;

/**
 * Created by zhuyifei on 15/12/4.
 * <p/>
 * 日志工具类 薄封装
 * <p/>
 * 日志级别
 * ALL<DEBUG<INFO<WARN<ERROR<FATAL<OFF
 */
public class LogUtil {
    /**
     * 记录详细的重要信息
     *
     * @param tag
     * @param message
     */
    public static void debug(String tag, String message) {
        Logger.getLogger(tag).debug(message);
    }

    /**
     * 记录详细的重要信息
     *
     * @param tag
     * @param message
     * @param tr
     */
    public static void debug(String tag, String message, Throwable tr) {
        Logger.getLogger(tag).debug(message, tr);
    }

    /**
     * 记录简略的重要信息
     *
     * @param tag
     * @param message
     */
    public static void info(String tag, String message) {
        Logger.getLogger(tag).info(message);
    }

    /**
     * 记录简略的重要信息
     *
     * @param tag
     * @param message
     * @param tr
     */
    public static void info(String tag, String message, Throwable tr) {
        Logger.getLogger(tag).info(message, tr);
    }

    /**
     * 记录异常数据信息，如无数据，服务器连接超时
     *
     * @param tag
     * @param message
     */
    public static void warn(String tag, String message) {
        Logger.getLogger(tag).warn(message);
    }

    /**
     * 记录异常数据信息，如无数据，服务器连接超时
     *
     * @param tag
     * @param message
     * @param tr
     */
    public static void warn(String tag, String message, Throwable tr) {
        Logger.getLogger(tag).warn(message, tr);
    }

    /**
     * 记录严重的异常
     *
     * @param tag
     * @param message
     */
    public static void error(String tag, String message) {
        Logger.getLogger(tag).error(message);
    }

    /**
     * 记录严重的异常,如exception
     * <p/>
     * message 为“”可能导致日志丢失
     *
     * @param tag
     * @param message
     * @param tr
     */
    public static void error(String tag, String message, Throwable tr) {
        Logger.getLogger(tag).error(message, tr);
    }

    /**
     * 系统崩溃日志
     *
     * @param tag
     * @param message
     */
    public static void fatal(String tag, String message) {
        Logger.getLogger(tag).fatal(message);
    }

    /**
     * 系统崩溃日志
     *
     * @param tag
     * @param message
     * @param tr      崩溃异常堆栈
     */
    public static void fatal(String tag, String message, Throwable tr) {
        Logger.getLogger(tag).fatal(message, tr);
    }
}
