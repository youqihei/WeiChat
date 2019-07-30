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
    }


}
