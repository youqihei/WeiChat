package weichat.privatecom.wwei.weichat.base;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2019/4/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract int getContentViewId();//获取布局ID

    protected abstract int getFragmentId();//获取fragmentID

    //添加fragment
    public void addFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(getFragmentId(), fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    //删除fragment
    public void removeFragment()
    {
        if(getSupportFragmentManager().getBackStackEntryCount()>1)
        {
            getSupportFragmentManager().popBackStack();
        }
        else
        {
            finish();
        }
    }
    @Override
    public boolean onKeyDown(int keyboard, KeyEvent keyEvent)
    {
        if(getSupportFragmentManager().getBackStackEntryCount()==1)
        {
           // removeFragment();
             return true;
        }
       return      super.onKeyDown(keyboard,keyEvent);
    }
}
