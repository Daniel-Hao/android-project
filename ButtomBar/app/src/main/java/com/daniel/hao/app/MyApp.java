package com.daniel.hao.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.multidex.MultiDexApplication;

import com.daniel.hao.corelib.log.AppInfo;
import com.daniel.hao.corelib.log.LogConfigure;
import com.daniel.hao.corelib.log.MyUncaughtExceptionHandler;
import com.daniel.hao.finals.FinalsCachePath;
import com.daniel.hao.utils.L;


public class MyApp extends MultiDexApplication {

    //region variable
    private static MyApp _instance;

    private static synchronized void syncInit() {
        if (null == _instance) {
            _instance = new MyApp();
        }
    }

    public static MyApp getInstance() {
        if (null == _instance) {
            syncInit();
        }
        return _instance;
    }

    // 线程同步/定时器部分
    Handler mUiHandler = null; // 用于向界面发送执行代码
    Handler mWorkHandler = null; // 用于向工作线程发送执行代码
    HandlerThread mWorkThread = null; // 工作线程处理耗时操作,防止在主线程中执行界面卡
    //endregion

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
        init();
    }

    private void init() {
        if ("com.daniel.hao.activity".equals(AppInfo.getCurProcessName(getApplicationContext()))) {
            initLogConfigure();
        }
    }

    /**
     * OnLowMemory被回调时，已经没有后台进程
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * onTrimMemory被回调时，还有后台进程
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        clearWorkThread();
        _instance = null;
        super.onTerminate();
    }


    //region ui/work handler & post

    /**
     * 获取Handler简化跨线程执行代码,实现同步/定时器等功能 1.获取指定线程Handler 2.实现Runnable:run()代码
     * 2.调用Handler:post/postAt postDelayed传入Runnable对 获取界面Handler
     */
    public Handler getUiHandler() {
        return (mUiHandler != null) ? (mUiHandler) : (mUiHandler = new Handler(
                getMainLooper()));
    }

    /**
     * 获取工作线程Handler
     *
     * @return WorkHandler
     */
    public Handler getWorkHandler() {
        return (mWorkHandler != null) ? (mWorkHandler)
                : (mWorkHandler = new Handler(getWorkLooper()));
    }

    /**
     * 获取Looper实现自定义Handler,在指定线程(非当前线程)处理消息 获取工作线程Looper,注:获取主线程Looper请调用
     * getMainLooper()
     */
    public Looper getWorkLooper() {
        if (mWorkThread == null) { // 如果工作线程未开启,则开启工作线程
            mWorkThread = new HandlerThread("Rtcclient_WorkThread");
            mWorkThread.start();
        }
        return mWorkThread.getLooper();
    }

    /**
     * 释放工作线程
     */
    public void clearWorkThread() {
        if (mWorkHandler != null)
            mWorkHandler = null;
        if (mWorkThread != null) {
            if (mWorkThread.isAlive()) {
                mWorkThread.quit();
                try {
                    mWorkThread.join(200);
                } catch (InterruptedException e) {
                    L.e("exception_clear_thread");
                }
            }
            mWorkThread = null;
        }
    }

    /**
     * 静态工具函数 免创建Handler实现同步和定时器 直接向ui线程执行代码
     */
    public boolean post2UIRunnable(Runnable r) {
        return (_instance != null) ? _instance.getUiHandler().post(r) : false;
    }

    /**
     * UI线程中的定时器
     *
     * @param r            Runnable
     * @param uptimeMillis 定时
     * @return boolean
     */
    public boolean post2UIAtTime(Runnable r, long uptimeMillis) {
        return (_instance != null) ? _instance.getUiHandler().postAtTime(r,
                uptimeMillis) : false;
    }

    public boolean post2UIDelayed(Runnable r, long delayMillis) {
        return (_instance != null) ? _instance.getUiHandler().postDelayed(r,
                delayMillis) : false;
    }

    /**
     * 直接向工作线程线程执行代码
     *
     * @param r Runnable
     */
    public boolean post2WorkRunnable(Runnable r) {
        if (_instance == null) return false;

        return _instance.getWorkHandler().post(r);
    }

    /**
     * 工作线程中的定时器
     *
     * @param r            Runnable
     * @param uptimeMillis 定时的时间
     * @return boolean
     */
    public boolean post2WorkAtTime(Runnable r, long uptimeMillis) {
        if (_instance == null) return false;

        return _instance.getWorkHandler().postAtTime(r, uptimeMillis);
    }

    public boolean post2WorkDelayed(Runnable r, long delayMillis) {
        if (_instance == null) return false;

        return _instance.getWorkHandler().postDelayed(r, delayMillis);
    }

    //endregion

    /**
     * 初始化日志
     */
    private void initLogConfigure() {
        LogConfigure.init(FinalsCachePath.LOG);
        //记录基本信息
        AppInfo.logDeviceAndAppInfo(this);
        //记录crash时的信息
        MyUncaughtExceptionHandler.getInstance().init(getApplicationContext(), FinalsCachePath.LOG_PATH);
    }




}
