package weichat.privatecom.wwei.weichat;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import weichat.privatecom.wwei.weichat.activity.AppActivity;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.fragment.MainFragment;

public class MainActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return MainFragment.newInstance();
    }
}
