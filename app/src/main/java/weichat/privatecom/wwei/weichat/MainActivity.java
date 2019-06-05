package weichat.privatecom.wwei.weichat;

import weichat.privatecom.wwei.weichat.activity.AppActivity;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.fragment.MainFragment;

public class MainActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {

        return MainFragment.newInstance();
    }
}
