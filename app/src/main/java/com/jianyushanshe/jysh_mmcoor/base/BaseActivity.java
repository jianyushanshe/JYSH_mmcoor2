package com.jianyushanshe.jysh_mmcoor.base;


import android.app.Activity;

import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.haylion.rxbuspublic.rxbuslib.RxBus;
import com.jaeger.library.StatusBarUtil;
import com.jianyushanshe.jysh_mmcoor.R;
import com.jianyushanshe.jysh_mmcoor.utils.StatusBarUtils;

/**
 * Author:wangjianming
 * Time:2019/6/6 15:26
 * Description:BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * context
     */
    protected Activity mContext;

    /**
     * activity切换动画的枚举
     */
    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }

    /**
     * 获取activity播放动画的类型
     *
     * @return TransitionMode枚举类型
     */
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    /**
     * 是否activity跳转的时候添加动画
     *
     * @return boolean
     */
    public boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (toggleOverridePendingTransition()) {

            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(R.anim.left_in, 0);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.none_move);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, 0);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, 0);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, 0);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, 0);
                    break;
            }
        }
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this); //添加activity到栈管理
        RxBus.getInstance().register(this, true);//注册RxBus
        mContext = this;//contextfu赋值
        superBaseOnCreate(savedInstanceState);//父类暴露给自己的初始化方法，在子类中重写
    }


    /**
     * 父类暴露给自己初始化的方法
     *
     * @param savedInstanceState Bundle
     */
    public abstract void superBaseOnCreate(@Nullable Bundle savedInstanceState);

    @Override
    public void finish() {
        super.finish();
        if (toggleOverridePendingTransition()) {
            switch (getOverridePendingTransitionMode()) {
                case LEFT:
                    overridePendingTransition(0, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.none_move, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(0, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(0, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(0, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(0, R.anim.fade_out);
                    break;
            }
        }
    }

    /**
     * 重写setContentView方法
     *
     * @param layoutResID 布局资源的id
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initStatusBar();//设置沉浸式状态栏
    }

    /**
     * 设置沉浸式状态栏
     */
    public void initStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtils.setStatusTextColor(true, mContext);
    }
}
