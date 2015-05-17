package com.ttxr.application;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AM {
    private static Stack<Activity> activityStack;
    private static AM instance;

    private AM() {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
    }

    public static AM getActivityManager() {
        if (instance == null) {
            instance = new AM();
        }
        return instance;
    }

    public void popAllActivityExceptList(List<Class> cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (cls.contains(activity.getClass())) {
                break;
            }
            popActivity(activity);
        }
    }

    public void popActivity(Class<?> clazz) {
        ArrayList<Activity> ac = new ArrayList<Activity>();
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(clazz)) {
                ac.add(activityStack.get(i));
            }
        }
        if (ac.size() > 0) {
            for (Activity activity2 : ac) {
                activity2.finish();
                activityStack.remove(activity2);
                ac = null;
            }
        }
    }

    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    public Activity currentActivity() {
        if (activityStack != null && activityStack.size() > 0) {
            Activity activity = activityStack.lastElement();
            return activity;
        } else {
            return null;
        }
    }

    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (cls != null && activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }

    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    public boolean contains(Class<?> clazz) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(clazz)) {
                return true;
            }
        }
        return false;
    }
}
