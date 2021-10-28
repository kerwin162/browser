package top.itjl.browser.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * @author Kerwin
 * @date 2019/10/22
 */
public class DisplayUtils {
    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * dp转换成px
     *
     * @param context
     * @param dpVale
     * @return
     */
    public static int dip2px(Context context, float dpVale) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

    /**
     * sp转换成px
     *
     * @param context
     * @param sp
     * @return
     */
    public static int dip2sp(Context context, float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * px转换成dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 基于在Huawei Pad 上适配好的px获取当前设备对应的px
     *
     * @param src HuaweiPad上使用的源px值
     * @param isX 是否是x轴坐标
     * @return 返回当前设备对应的px值
     */
    public static int pxBaseHuaWei(Context context, int src, boolean isX) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        //华为Pad显示属性为1920*1200  density=2；
//        return (int) ((isX ? w : h) * src / (isX ? 1920 : 1200) / 2 * dm.density);
        return (int) ((isX ? w : h) * src / (isX ? 1920 : 1200) / 2 * 1.5);
    }
}