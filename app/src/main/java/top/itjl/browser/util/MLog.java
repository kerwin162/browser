package top.itjl.browser.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author: Kerwin
 * @date: 2021/10/28
 */
public class MLog implements ILog {
    private final String TAG;

    public MLog(String tag) {
        TAG = TextUtils.isEmpty(tag) ? "MLog" : tag;
    }

    @Override
    public void info(String msg) {
        Log.i(TAG, msg);
    }

    public void info(String tag, String msg) {
        Log.i(tag, msg);
    }

    @Override
    public void debug(String msg) {
        Log.d(TAG, msg);
    }

    public void debug(String tag, String msg) {
        Log.d(tag, msg);
    }

    @Override
    public void warn(String msg) {
        Log.w(TAG, msg);
    }

    public void warn(String tag, String msg) {
        Log.w(tag, msg);
    }

    @Override
    public void error(String msg) {
        Log.e(TAG, msg);
    }

    public void error(String tag, String msg) {
        Log.e(tag, msg);
    }
}
