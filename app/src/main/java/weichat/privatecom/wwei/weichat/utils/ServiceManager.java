package weichat.privatecom.wwei.weichat.utils;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.net.URI;

import weichat.privatecom.wwei.weichat.JWebSocketClient;
import weichat.privatecom.wwei.weichat.service.WebSocketService;

/**
 * Created by Administrator on 2019/6/18.
 */

public class ServiceManager {
    public static ServiceManager serviceManager;

    public static WebSocketService.JWebSocketClientBinder binder;
    public static WebSocketService webSocketService;
    public static JWebSocketClient client;

    public static ServiceManager newInstance() {
        if (serviceManager == null) {
            synchronized (ServiceManager.class) {
                if (serviceManager == null) {
                    serviceManager = new ServiceManager();
                }
            }
        }
        return serviceManager;
    }

    ;

    public static ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            //服务与活动成功绑定
            Log.e("MainActivity", "服务与活动成功绑定");
            binder = (WebSocketService.JWebSocketClientBinder) iBinder;
            webSocketService = binder.getService();
            client = webSocketService.client;
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("MainActivity", "服务与活动成功断开");
        }
    };

    public static void clear()
    {
        binder = null;
        webSocketService = null;
        client = null;
    }
}
