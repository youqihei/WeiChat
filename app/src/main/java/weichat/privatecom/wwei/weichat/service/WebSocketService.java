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

/**
 * Created by Administrator on 2019/6/18.
 */

public class WebSocketService extends Service{
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();
    URI uri = URI.create("ws://echo.websocket.org");

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

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            client.connectBlocking();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    public JWebSocketClient client = new JWebSocketClient(uri) {
        @Override
        public void onMessage(String message) {
            //message就是接收到的消息
            Log.e("JWebSClientService", message);
        }

        @Override
        public void onOpen(ServerHandshake handshakedata) {
            super.onOpen(handshakedata);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            super.onClose(code, reason, remote);
        }

        @Override
        public void onError(Exception ex) {
            super.onError(ex);
        }
    };

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
        return mBinder;
    }
}
