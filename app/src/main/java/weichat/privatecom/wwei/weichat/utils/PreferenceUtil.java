package weichat.privatecom.wwei.weichat.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2019/7/1.
 */

public class PreferenceUtil {
    public final static String SHAREDPREFERENCES_NAME = "weichat_app_shared_prefs";
    public static boolean isLogin(Context cxt) {
        return readBoolean(cxt, "login_state", false);
    }

    public static void setLoginStatus(Context cxt, boolean value) {
        writeBoolean(cxt, "login_state", value);
    }
    public static boolean readBoolean(Context context, String k, boolean defValue) {
        SharedPreferences preference = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        return preference.getBoolean(k, defValue);
    }
    public static void writeBoolean(Context context, String key, boolean value) {
        SharedPreferences preference = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    //登录时输入的帐号 不是username
    public static String getUserName(Context cxt) {
        return readString(cxt, "username", "");
    }

    public static void setUserName(Context cxt, String value) {
        writeString(cxt, "username", value);
    }
    public static String readString(Context context, String k, String defV) {
        SharedPreferences preference = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        return preference.getString(k, defV);
    }
    public static void writeString(Context context, String key, String value) {
        SharedPreferences preference = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
