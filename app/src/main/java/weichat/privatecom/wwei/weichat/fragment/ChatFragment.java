package weichat.privatecom.wwei.weichat.fragment;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;

import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import weichat.privatecom.wwei.weichat.JWebSocketClient;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.adapter.ChatAdapter;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.service.WebSocketService;

/**
 * Created by Administrator on 2019/6/13.
 */

public class ChatFragment extends BaseFragment {
    @BindView(R.id.ry_list)
    RecyclerView recyclerView;

    ChatAdapter chatAdapter;
    private List<ChatBean> list = new ArrayList<>();


    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        initData();
      chatAdapter = new ChatAdapter(getActivity(),list);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      recyclerView.setAdapter(chatAdapter);
      chatAdapter.notifyDataSetChanged();
    }

    private void initData()
    {

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }
}
