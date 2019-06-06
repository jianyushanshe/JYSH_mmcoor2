package com.jianyushanshe.jysh_mmcoor.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.PopupWindow;


import com.jianyushanshe.jysh_mmcoor.BuildConfig;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:wangjianming
 * Time:2018/11/28 18:31
 * Description:Util
 */
public class Util {
    /**
     * @throws Exception
     * @功能：跳转到另外的Activity
     * @param：mClass:目标activity类的class
     * @return：
     */
    public static void openActivity(Activity context, Class mClass) {
        Intent intent = new Intent();
        intent.setClass(context, mClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);

    }

    public static void openActivityForResult(Activity context, Class mClass, int requestCode) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        intent.setClass(context, mClass);
        context.startActivityForResult(intent, requestCode);
    }

    public static void openActivityWithBundle(Activity context, Class mClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtras(bundle);
        intent.setClass(context, mClass);
        context.startActivity(intent);
    }

    //打开应用市场
    public static void openApplicationMarket(Context context) {
        try {
            Uri uri = Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Throwable throwable) {
        }
    }


    /**
     * 清除文件
     *
     * @param dir
     */
    public static void clearDir(File dir) {
        if (dir != null && dir.exists() && dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * @throws Exception
     * @功能：检查是否符手机格式
     * @param：
     * @return：
     */
    public static boolean isPhoneNum(String mobiles) {
        Pattern p = Pattern.compile("^[1][345789][0-9]{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isAccount(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (isCurrentAccount(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //非法字符
    private static boolean isCurrentAccount(char account) {
        if (((int) account >= 0 && (int) account <= 47)
                || ((int) account >= 58 && (int) account <= 64)
                || ((int) account >= 91 && (int) account <= 96)
                || ((int) account >= 123 && (int) account <= 127)
                || (int) account == 65292 || (int) account == 12290
                || (int) account == 65311 || (int) account == 65281
                || (int) account == 8220 || (int) account == 65306
                || (int) account == 65307) {
            return true;
        }
        return false;
    }

    /**
     * 获取图片地址
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 获取格式化后的企业电话
     *
     * @param tel 格式化前：4001118130
     *            格式化后：400-111-8130
     */
    public static String getFormatEnterprisePhone(String tel) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tel.length(); i++) {
            stringBuilder.append(tel.substring(i, i + 1));
            if (i == 2 || i == 5) {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * @param pw     popupWindow 适配8.0系统显示问题
     * @param anchor v
     * @param xoff   x轴偏移
     * @param yoff   y轴偏移
     */
    public static void showAsDropDown(final PopupWindow pw, final View anchor, final int xoff, final int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            pw.setHeight(height);
            pw.showAsDropDown(anchor, xoff, yoff);
        } else {
            pw.showAsDropDown(anchor, xoff, yoff);
        }
    }


}
