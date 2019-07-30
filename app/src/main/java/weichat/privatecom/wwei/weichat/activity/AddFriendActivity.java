package weichat.privatecom.wwei.weichat.activity;

import android.content.Intent;
import android.os.Bundle;

import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.fragment.AddFriendFragment;
import weichat.privatecom.wwei.weichat.fragment.AddGroupFragment;

/**
 * Created by Administrator on 2019/7/29.
 */

public class AddFriendActivity extends AppActivity {
    @Override
    protected void HoldingIntent(Intent intent) {
        super.HoldingIntent(intent);
        Bundle bundle = intent.getExtras();
    }


    @Override
    protected BaseFragment getFirstFragment() {

        return AddFriendFragment.newInstance();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
