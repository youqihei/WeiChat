package weichat.privatecom.wwei.weichat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import weichat.privatecom.wwei.weichat.activity.AppActivity;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.fragment.MainFragment;
import weichat.privatecom.wwei.weichat.service.WebSocketService;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ServiceManager;

public class MainActivity extends AppActivity {
    private boolean isfiring ;
    @Override
    protected void HoldingIntent(Intent intent) {
        super.HoldingIntent(intent);
        Bundle bundle = intent.getExtras();
        if(bundle!=null)
        {
            isfiring = bundle.getBoolean("isfiring");
        }
    }
    @Override
    protected BaseFragment getFirstFragment() {
        new Thread() {
            @Override
            public void run() {
                bindService( new Intent(getBaseContext(), WebSocketService.class), ServiceManager.newInstance().serviceConnection, BIND_AUTO_CREATE);

            }
        }.start();
        return MainFragment.newInstance(isfiring);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ServiceManager.clear();
       unbindService(ServiceManager.newInstance().serviceConnection);
    }
}
