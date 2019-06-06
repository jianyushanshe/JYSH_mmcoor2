package com.jianyushanshe.jysh_mmcoor.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jianyushanshe.jysh_mmcoor.R;
import com.jianyushanshe.jysh_mmcoor.base.BaseActivity;
import com.jianyushanshe.jysh_mmcoor.utils.AnimatorUtil;
import com.jianyushanshe.jysh_mmcoor.utils.Util;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WelcomeActivity extends BaseActivity {
    private RelativeLayout rlWelcome;
    private TextView tvName;
    private TextView tvContent;

    @Override
    public void superBaseOnCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_welcome);
        rlWelcome = findViewById(R.id.rl_welcome);
        tvName = findViewById(R.id.tv_name);
        tvContent = findViewById(R.id.tv_content);
        AnimatorUtil.getInstance().setAlphaAnim(tvName);
        AnimatorUtil.getInstance().scaleAnimator(tvContent, 2000);
        Observable.timer(5000, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Util.openActivity(WelcomeActivity.this, LoginActivity.class);
                finish();
            }
        });
    }
}
