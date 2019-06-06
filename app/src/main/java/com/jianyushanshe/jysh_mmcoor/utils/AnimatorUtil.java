package com.jianyushanshe.jysh_mmcoor.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


/**
 * Author:wangjianming
 * Time:2019/3/8 10:37
 * Description:AnimatorUtil 动画工具类
 */
public class AnimatorUtil {
    private static AnimatorUtil animatorUtil;

    public static AnimatorUtil getInstance() {
        if (animatorUtil == null) {
            synchronized (AnimatorUtil.class) {
                if (animatorUtil == null) {
                    animatorUtil = new AnimatorUtil();
                }
            }
        }
        return animatorUtil;
    }

    /**
     * 属性缩放动画
     *
     * @param view
     * @param time
     */
    public void scaleAnimator(View view, int time) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(time);
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }

    /**
     * View缩放动画
     *
     * @param view
     * @param time
     * @param count
     */
    public void scaleViewAnimator(View view, int time, int count) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(time);
        scaleAnimation.setRepeatCount(count);
        scaleAnimation.setFillAfter(true);
        view.startAnimation(scaleAnimation);
    }

    /**
     * 百度地图mark点击动画
     *
     * @param view
     * @param time
     * @param count
     */
    public void scaleViewAnimatorForBaiduMark(View view, int time, int count) {
        ScaleAnimation mScaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF,
                0.55f);
        mScaleAnimation.setDuration(time);
        mScaleAnimation.setRepeatCount(count);
        mScaleAnimation.setFillAfter(true);
        mScaleAnimation.setInterpolator(new DecelerateInterpolator());
        view.startAnimation(mScaleAnimation);
    }

    /**
     * 百度地图中心点站点数量信息条动画
     *
     * @param view
     * @param time
     * @param count
     */
    public void scaleViewAnimatorForBaiduCenter(View view, int time, int count) {
        ScaleAnimation mScaleAnimation = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mScaleAnimation.setDuration(time);
        mScaleAnimation.setRepeatCount(count);
        mScaleAnimation.setFillAfter(true);
        mScaleAnimation.setInterpolator(new DecelerateInterpolator());
        view.startAnimation(mScaleAnimation);
    }


    /**
     * View平移动画
     *
     * @param view
     * @param time
     * @param count
     * @param fromXValue
     * @param toXValue
     * @param fromYValue
     * @param toYValue
     */
    public void translateViewAnimator(View view, int time, int count, float fromXValue, float toXValue,
                                      float fromYValue, float toYValue) {
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, fromXValue, Animation.RELATIVE_TO_PARENT, toXValue, Animation.RELATIVE_TO_PARENT, fromYValue, Animation.RELATIVE_TO_PARENT, toYValue);
        translateAnimation.setDuration(time);
        translateAnimation.setRepeatCount(count);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(translateAnimation);
    }

    /**
     * 属性动画平移
     *
     * @param view
     * @param time
     * @param fromXValue
     * @param toXValue
     * @param fromYValue
     * @param toYValue
     */
    public void translateAnimator(View view, int time, float fromXValue, float toXValue,
                                  float fromYValue, float toYValue) {
        ObjectAnimator translateX = ObjectAnimator.ofFloat(view, "translationX", fromXValue, toXValue);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", fromYValue, toYValue);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translateX, translateY);
        animatorSet.setDuration(time);
        animatorSet.start();
    }

    /**
     * @throws Exception
     * @功能：设置控件的渐变动画
     * @param：
     * @return：
     */
    public void setAlphaAnim(View view) {
        AlphaAnimation aAnim = new AlphaAnimation(0.0f, 1.0f);
        aAnim.setDuration(1000);
        view.startAnimation(aAnim);
    }
}
