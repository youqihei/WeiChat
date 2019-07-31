package interceptmessage.privatecom.wwei.multi_image_selector;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/7/31.
 */

public class MultiImageSelector {
    private static MultiImageSelector sSelector;
    // Single choice
    public static final int MODE_SINGLE = 0;
    // Multi choice
    public static final int MODE_MULTI = 1;
    private int mMode = MODE_MULTI;
    private ArrayList<String> mOriginData;
    private boolean hasPermission(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            // Permission was added in API Level 16
            return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }
    private Intent createIntent(Context context){
        Intent intent = new Intent(context, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
        if(mOriginData != null){
            intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mOriginData);
        }
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, mMode);
        return intent;
    }
    public static MultiImageSelector newInstance() {

        if(sSelector==null)
        {
            synchronized (MultiImageSelector.class)
            {
                if(sSelector==null)
                {
                    sSelector = new MultiImageSelector();
                }
            }
        }
        return sSelector;
    }

    public MultiImageSelector multi(){
        mMode = MODE_MULTI;
        return sSelector;
    }
    public MultiImageSelector origin(ArrayList<String> images){
        mOriginData = images;
        return sSelector;
    }
    public void start(Activity activity, int requestCode){
        final Context context = activity;
        if(hasPermission(context)) {
            activity.startActivityForResult(createIntent(context), requestCode);
        }else{
            Toast.makeText(context, "mis_error_no_permission", Toast.LENGTH_SHORT).show();
        }
    }

    public void start(Fragment fragment, int requestCode){
        final Context context = fragment.getContext();
        if(hasPermission(context)) {
            fragment.startActivityForResult(createIntent(context), requestCode);
        }else{
            Toast.makeText(context, "mis_error_no_permission", Toast.LENGTH_SHORT).show();
        }
    }


}
