package com.daniel.hao.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Iterator;
import java.util.Stack;


/**
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
        activityStack.add(activity);
    }

    /**
     */
    public Activity currentActivity() {
        if (null != activityStack && null != activityStack.lastElement()) {
            Activity activity = activityStack.lastElement();
            return activity;
        } else {
            return null;
        }

    }

    /**
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     */
    public void finishActivity(Class<?> cls) {
        Stack<Activity> activitys = new Stack<Activity>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activitys.add(activity);
            }
        }
        for (Activity activity : activitys) {
            finishActivity(activity);
        }
    }

    /**
     */
    public void finishAllActivity() {
        if (activityStack == null)
            return;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void finishAllActivityExcept(Class<?> cls) {
        if (activityStack == null)
            return;
        Iterator<Activity> sListIterator = activityStack.iterator();
        Activity _activity;
        while (sListIterator.hasNext()) {
            _activity = sListIterator.next();

            if (_activity != null
                    && !_activity.getClass().equals(cls)) {
                _activity.finish();
                sListIterator.remove();
            }
        }
    }

    public void finishAllActivityExcept(Activity activity) {
        if (activityStack == null)
            return;

        Iterator<Activity> sListIterator = activityStack.iterator();
        Activity _activity;
        while (sListIterator.hasNext()) {
            _activity = null;
            _activity = sListIterator.next();

            if (_activity != null
                    && !_activity.getClass().equals(activity.getClass())) {
                _activity.finish();
                sListIterator.remove();
            }
        }
    }


    /**
     */
    public void AppExit(final Context context, final Boolean isClearData,
                        final IAppExit iAppExit) {
        try {
            if (isClearData) {
            } else {
            }

            //finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //int pid = Process.myPid();
            //Process.killProcess(pid);
            //System.exit(0);

            if (iAppExit != null) {
                iAppExit.callBack();
            }
        }
    }

    public interface IAppExit {
        void callBack();
    }

    //多用户登录挤掉处理

}