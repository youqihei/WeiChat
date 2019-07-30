package weichat.privatecom.wwei.weichat.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.java_websocket.WebSocketFactory;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;

import weichat.privatecom.wwei.weichat.JWebSocketClient;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;

/**
 * Created by Administrator on 2019/6/18.
 */

public class WebSocketService extends Service{
    private JWebSocketClientBinder mBinder = new JWebSocketClientBinder();
    private static  boolean iscontinued = true;
    URI uri = URI.create("ws://192.168.0.108:8080/WeiChatWeb/serverWebsocket");
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
                iscontinued = false;
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client = null;
        }
    }

    private static final long HEART_BEAT_RATE = 10 * 1000;//每隔10秒进行一次对长连接的心跳检测
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (client != null) {
                if (client.isClosed()&&iscontinued) {
                    reconnectWs();
                }
            } else {
                //如果client已为空，重新初始化websocket
                initSocketClient();
            }
            //定时对长连接进行心跳检测
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };

    /**
     * 开启重连
     */
    private void reconnectWs() {
        mHandler.removeCallbacks(heartBeatRunnable);
        new Thread() {
            @Override
            public void run() {
                try {
                    //重连
                    client.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void initSocketClient()
    {
        client = new JWebSocketClient(uri) {
            @Override
            public void onMessage(String message) {
                //message就是接收到的消息
                Log.e("JWebSClientService", message);
                Intent intent = new Intent();
                intent.setAction("weichat.private.wei.weichat.content");
                intent.putExtra("message_friend", message);
                sendBroadcast(intent);
                Intent intent1 = new Intent();
                intent1.setAction("weichat.private.wei.weichat.chatinterface");
                intent1.putExtra("message_chatinterface",message);
                sendBroadcast(intent1);
            }

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.e("deroor","oopen");
                super.onOpen(handshakedata);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.e("deroor","onclose");
                //   super.onClose(code, reason, remote);
            }

            @Override
            public void onError(Exception ex) {
                Log.e("deroor","error");
            }
        };
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try {
            String username = PreferenceUtil.getUserName(getBaseContext());
            iscontinued = true;
            uri = URI.create("ws://192.168.0.108:8080/WeiChatWeb/serverWebsocket/"+username);
           initSocketClient();
            client.connectBlocking();
            mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e("e.prigntateetg",e.toString());
        }
        return mBinder;
    }
}
