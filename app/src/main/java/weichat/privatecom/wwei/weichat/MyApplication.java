package weichat.privatecom.wwei.weichat;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Administrator on 2019/7/9.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化DBFlow
        FlowManager.init(new FlowConfig.Builder(getApplicationContext()).openDatabasesOnInit(true).build());
        //设置日志显示
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }
}
