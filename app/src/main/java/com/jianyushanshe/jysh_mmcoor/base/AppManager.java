package com.jianyushanshe.jysh_mmcoor.base;

import android.app.Activity;
import android.content.Context;

import com.jianyushanshe.jysh_mmcoor.ui.MainActivity;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Author:wangjianming
 * Time:2018/11/26 9:48
 * Description:AppManager,Activity管理类
 */
public class AppManager {
    public HashSet<Activity> activityStack;
    private static AppManager appManager;
    public MyApplication app;
    public Activity currentAct; //当前onResume的acvitity

    private AppManager() {

    }

    /**
     * AppManger单例
     *
     * @return AppManager
     */
    public static AppManager getInstance() {
        if (appManager == null) {
            synchronized (AppManager.class) {
                if (appManager == null) {
                    appManager = new AppManager();
                }
            }
        }
        return appManager;
    }

    /**
     * 将activity添加到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new HashSet<>();
        }
        activityStack.add(activity);
    }

    /**
     * 结束栈顶的activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (!activityStack.isEmpty()) {
            activityStack.remove(activity);
        }
    }

    /**
     * 用户登出，清除其他activity保留MainActivity
     */
    public void logout() {
        HashSet<Activity> tempHashSet = new HashSet<>();
        Iterator<Activity> activityIterator = activityStack.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (!(activity instanceof MainActivity)) {
                activity.finish();
            } else {
                tempHashSet.add(activity);
            }
        }
        activityStack.clear();
        activityStack.addAll(tempHashSet);

    }

    /**
     * 退出应用
     *
     * @param context
     */
    public void appExit(Context context) {

        try {
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
            //android.os.Process.killProcess(android.os.Process.myPid());
            //System.exit(0);
        } catch (Exception e) {
        }
    }

    /**
     * 获取当前交互的activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        return currentAct;
    }

    /**
     * 获取指定的Activity对象
     *
     * @param cla
     * @param <T>
     * @return
     */
    public <T extends Activity> T getActvity(Class<T> cla) {
        Activity act = null;
        for (Activity activity : activityStack) {
            if (activity.getClass().getSimpleName()
                    .equals(cla.getSimpleName())) {
                act = activity;
            }
        }
        return (T) act;
    }


}
