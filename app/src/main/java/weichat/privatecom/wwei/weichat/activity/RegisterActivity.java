package weichat.privatecom.wwei.weichat.activity;

import android.content.Intent;
import android.os.Bundle;

import weichat.privatecom.wwei.weichat.base.BaseActivity;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.fragment.RegisterFragment;

/**
 * Created by Administrator on 2019/7/1.
 */

public class RegisterActivity extends AppActivity {
    @Override
    protected void HoldingIntent(Intent intent) {
        super.HoldingIntent(intent);
        Bundle bundle = intent.getExtras();
    }
    @Override
    protected BaseFragment getFirstFragment() {
        return RegisterFragment.newInstance();
    }
}
