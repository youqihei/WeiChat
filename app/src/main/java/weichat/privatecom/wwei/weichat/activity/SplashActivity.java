package weichat.privatecom.wwei.weichat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import butterknife.ButterKnife;
import weichat.privatecom.wwei.weichat.MainActivity;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.base.BaseActivity;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.fragment.RegisterFragment;
import weichat.privatecom.wwei.weichat.service.WebSocketService;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ServiceManager;

/**
 * Created by Administrator on 2019/7/1.
 */

public class SplashActivity extends AppCompatActivity {

    //获取第一个fragment

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(!PreferenceUtil.isLogin(SplashActivity.this)){
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                    finish();
                }else {
                    Intent intent = new Intent(getBaseContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.schedule(task, 500);
        Log.e("result",""+compareVersion("1.0001.002","1.0001"));
    }
    public int compareVersion(String version1, String version2) {
        int result = 0;
        String s1 = "";
        String s2 = "";
        int a1 = 0;
        int a2 = 0;
        //  String[] result =
        String[] c1 = version1.split("\\.");
        String[] c2 = version2.split("\\.");
        int maxlength = c1.length;
        int i=0;
        int j=0;
        for(i=0,j=0;i<c1.length&&j<c2.length;i++,j++)
        {
            a1 = Integer.parseInt(c1[i]);
            a2 = Integer.parseInt(c2[j]);
            if(a1==a2)
            {
                continue;
            }
            else if(a1>a2)
            {
                result = 1;
                break;
            }
            else
            {
                result = -1;
                break;
            }
        }
        if(i<c2.length)
        {
            maxlength = c2.length;
            for(i=c1.length;i<maxlength;i++)
            {
                a2 = Integer.parseInt(c2[i]);
                if(a2>0)
                {
                    result = -1;
                    break;
                }
            }
        }
        else
        {
            for(i=j;i<maxlength;i++)
            {
                a1 = Integer.parseInt(c1[i]);
                if(a1>0)
                {
                    result = 1;
                    break;
                }
            }
        }
     return result;

    }

}
