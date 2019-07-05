package weichat.privatecom.wwei.weichat.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

import weichat.privatecom.wwei.weichat.JWebSocketClient;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;

/**
 * Created by Administrator on 2019/6/18.
 */

public class WebSocketService extends Service{
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();
    URI uri = URI.create("ws://192.168.0.178:8080/WeiChatWeb/serverWebsocket");
    public  class  JWebSocketClientBinder extends Binder{
        public WebSocketService getService()
        {
            return WebSocketService.this;
        }
    }
    public WebSocketService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }



    public JWebSocketClient client ;

    @Override
    public void onDestroy() {
        super.onDestroy();
        closeConnect();
    }
    public void  closeConnect()
    {
        try {
            if (null != client) {
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try {
            String username = PreferenceUtil.getUserName(getBaseContext());
            uri = URI.create("ws://192.168.0.178:8080/WeiChatWeb/serverWebsocket/"+username);
            client = new JWebSocketClient(uri) {
                @Override
                public void onMessage(String message) {
                    //message就是接收到的消息
                    Log.e("JWebSClientService", message);
                }

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    Log.e("deroor","oopen");
                    super.onOpen(handshakedata);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.e("deroor","close");
                    super.onClose(code, reason, remote);
                }

                @Override
                public void onError(Exception ex) {
                    Log.e("deroor","error");
                    super.onError(ex);
                }
            };
            Log.e("kankinee","1");
            client.connectBlocking();
            Log.e("kankinee","2");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e("e.prigntateetg",e.toString());
        }
        return mBinder;
    }
}
