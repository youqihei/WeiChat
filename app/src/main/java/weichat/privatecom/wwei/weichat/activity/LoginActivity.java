package weichat.privatecom.wwei.weichat.activity;

import android.content.Intent;
import android.os.Bundle;

import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.fragment.LoginFragment;

/**
 * Created by Administrator on 2019/4/23.
 */

public class LoginActivity extends AppActivity {

   private String username = "";
    @Override
    protected void HoldingIntent(Intent intent) {
        super.HoldingIntent(intent);
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            username = bundle.getString("name");
        }
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return LoginFragment.newInstance(username);
    }
}
