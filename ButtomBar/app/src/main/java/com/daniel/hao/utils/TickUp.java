package com.daniel.hao.utils;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hdl on 2016/6/4
 */
public abstract class TickUp {

    private Timer timer;

    private int leftSecond = 0;
    //定时器总时间
    private int totalSecond;

    private boolean mIsPause = false;

    Handler handler = new Handler();

    public abstract void onTick(int inLeftSecond);

    public abstract void onFinish();

    public TickUp(int inTotalSecond) {

        totalSecond = inTotalSecond;
    }


    public synchronized void start() {

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        mIsPause = false;
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {

                handler.post(new Runnable() {
                    public void run() {
                        synchronized (TickUp.this) {

                            onTick(leftSecond);
                            leftSecond++;
                        }
                    }
                });

                do {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                } while (mIsPause);
            }
        }, 0, 1000);
    }

    public synchronized void starTotal() {

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        mIsPause = false;
        timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {

                handler.post(new Runnable() {
                    public void run() {
                        synchronized (TickUp.this) {
                            if (leftSecond > totalSecond) {
                                cancel();
                                onFinish();
                            } else {
                                onTick(leftSecond);
                            }
                            leftSecond++;
                        }
                    }
                });

                do {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                } while (mIsPause);
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

    public void pause() {
        if (timer != null) {
            this.mIsPause = true;
        }
    }

    public boolean isPause() {
        return mIsPause;
    }

    public void resume() {
        if (timer != null) {
            this.mIsPause = false;
        }
    }
}
