package weichat.privatecom.wwei.weichat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import butterknife.ButterKnife;
import weichat.privatecom.wwei.weichat.base.BaseActivity;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.R;

/**
 * Created by Administrator on 2019/4/23.
 */

public abstract class AppActivity extends BaseActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected int getFragmentId() {
        return R.id.fragment_container;
    }
    //处理Intent
    protected void HoldingIntent(Intent intent)
    {

    }
    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();
    @Override
    public void onCreate(Bundle saveInstanceState)
        {
            super.onCreate(saveInstanceState);
            setContentView(getContentViewId());
            ButterKnife.bind(this);
            if(getIntent()!=null)
            {
                HoldingIntent(getIntent());
            }
            if(getSupportFragmentManager().getFragments().size()==0)
            {
                BaseFragment firstFragment = getFirstFragment();
                if(firstFragment!=null)
               addFragment(firstFragment);
            }
        }


}
