package com.daniel.hao.utils;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hdl on 2016/4/1.
 */
public abstract class TickDown {
    private Timer timer;
    //倒计时剩下时间,单位秒
    private int leftSecond;

    Handler handler = new Handler();

    public abstract void onTick(int inLeftSecond);

    public abstract void onFinish();

    public TickDown(int inTotalSecond) {
        leftSecond = inTotalSecond;
    }

    public synchronized void startByTotalSecond(int inTotalSecond) {
        leftSecond = inTotalSecond;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        synchronized (TickDown.this) {
                            if (leftSecond < 0) {
                                cancel();

                                onFinish();
                            } else {
                                onTick(leftSecond);
                            }

                            leftSecond--;
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    public synchronized void start() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        synchronized (TickDown.this) {
                            if (leftSecond < 0) {
                                cancel();

                                onFinish();
                            } else {
                                onTick(leftSecond);
                            }

                            leftSecond--;
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    public synchronized void cancel() {
        handler.post(new Runnable() {
            public void run() {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }
        });
    }
}
