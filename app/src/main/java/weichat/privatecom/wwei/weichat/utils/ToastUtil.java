package weichat.privatecom.wwei.weichat.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import weichat.privatecom.wwei.weichat.R;

/**
 * Created by Administrator on 2019/6/12.
 */

public class ToastUtil {
    private static Toast toast;

    /**
     * @param context
     * @param str
     */
    public static void showToast(Context context, int str) {
        if(context!=null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.toast, null);
            TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);

            if (null == toast) {
                toast = new Toast(context);
            }
            toast.setGravity(Gravity.BOTTOM, 0, 60);
            toast.setDuration(Toast.LENGTH_SHORT);

            int length = str;


            if (length < 7) {
                LinearLayout layout = new LinearLayout(context);
                LinearLayout.LayoutParams paramsLL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layout.setGravity(Gravity.CENTER);
                layout.setLayoutParams(paramsLL);
                TextView textView = new TextView(context);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(20, 10, 20, 10);
                textView.setBackgroundResource(R.drawable.shape_toast);
                textView.setTextSize(14);
                textView.setTextColor(Color.WHITE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setText(str);
                layout.addView(textView);
                toast.setView(layout);
                toast.show();
            } else {
                tv_toast.setText(str);
                toast.setView(view);
                toast.show();
            }
        }
    }
    /**
     * @param context
     * @param str
     */
    public static void showToast(Context context, String str) {
        if(context!=null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.toast, null);
            TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);

            if (null == toast) {
                toast = new Toast(context);
            }
            toast.setGravity(Gravity.BOTTOM, 0, 60);
            toast.setDuration(Toast.LENGTH_SHORT);
            int length = 0;
            if(str!=null) {
               length = str.length();
            }

            if (length < 7) {
                LinearLayout layout = new LinearLayout(context);
                LinearLayout.LayoutParams paramsLL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layout.setGravity(Gravity.CENTER);
                layout.setLayoutParams(paramsLL);
                TextView textView = new TextView(context);
                textView.setGravity(Gravity.CENTER);
                textView.setPadding(20, 10, 20, 10);
                textView.setBackgroundResource(R.drawable.shape_toast);
                textView.setTextSize(14);
                textView.setTextColor(Color.WHITE);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setText(str);
                layout.addView(textView);
                toast.setView(layout);
                toast.show();
            } else {
                tv_toast.setText(str);
                toast.setView(view);
                toast.show();
            }
        }
    }
}
