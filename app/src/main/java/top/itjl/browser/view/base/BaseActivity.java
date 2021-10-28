package top.itjl.browser.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.WindowManager;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;


import butterknife.ButterKnife;
import top.itjl.browser.BuildConfig;
import top.itjl.browser.R;
import top.itjl.browser.util.MLog;

/**
 * @author: Kerwin
 * @date: 2021/10/28
 */
public abstract class BaseActivity extends ComponentActivity {
    protected final MLog log = new MLog(this.getClass().getSimpleName());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(setStatusBarColor());
        setContentView();
        ButterKnife.bind(this);
        printLifeInfo("onCreate");
    }


    /**
     * 设置状态栏颜色
     *
     * @return 颜色值
     */
    private int setStatusBarColor() {
        return getDarkColorPrimary();
    }

    private int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorSecondaryVariant, typedValue, true);
        return typedValue.data;
    }

    protected abstract void setContentView(BaseActivity this);

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        printLifeInfo("onNewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        printLifeInfo("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        printLifeInfo("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        printLifeInfo("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printLifeInfo("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printLifeInfo("onDestroy");
    }

    private void printLifeInfo(String method) {
        if (BuildConfig.DEBUG) {
            log.info("[Activity LifeCycle]", this.getClass().getSimpleName() + " => " + method + "();");
        }
    }
}
