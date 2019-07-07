package weichat.privatecom.wwei.weichat.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.adapter.ChatAdapter;
import weichat.privatecom.wwei.weichat.adapter.MsgAdapter;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatMessageBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.bean.ChatSendBean;
import weichat.privatecom.wwei.weichat.contract.Contract;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.presenter.Presenter;
import weichat.privatecom.wwei.weichat.utils.JsonTool;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ServiceManager;
import weichat.privatecom.wwei.weichat.utils.ToastUtil;

public class ChatFriendFragment extends BaseFragment implements Contract.ChatFriendView {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.msg)
    RecyclerView recyclerView;
    @BindView(R.id.input)
    EditText input;
    MsgAdapter msgAdapter;
    private List<ChatMessageBean> list = new ArrayList<>();

    private Presenter chatfriendPresenter;
    private static ChatFriendFragment chatFriendFragment;
    private String friendname ="";

    public static BaseFragment newInstance(String friendname)
    {
        if(chatFriendFragment==null)
        {
            chatFriendFragment = new ChatFriendFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("chatfriend_fragment",friendname);
            chatFriendFragment.setArguments(bundle);
        }
        return chatFriendFragment;
    }
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        if(null!=getArguments())
        {
           friendname = (String)getArguments().getSerializable("chatfriend_fragment");
        }
    }

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        tv_title.setText(friendname);
        msgAdapter = new MsgAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(msgAdapter);

    }
    public  ChatAdapter.ChatCallback chatCallback= new ChatAdapter.ChatCallback() {
        @Override
        public void startnewFragment(String friendname) {
            addFragment(ChatFriendFragment.newInstance(friendname));
        }
    };
    //显示进度框
    @Override
    public void showloading()
    {

    }
    //隐藏进度框
    @Override
    public  void hideloading()
    {

    }
    //请求数据失败
    @Override
    public void onError(Throwable throwable)
    {
        ApiException apiException = (ApiException) throwable;
        ToastUtil.showToast(getActivity(),apiException.getDisplayMessage());
    }
    //请求数据成功
    @Override
    public void onSuccess(List<ChatMessageBean> response)
    {
        list.clear();
       list.addAll(response);


        msgAdapter.notifyDataSetChanged();
    }
    private class ChatMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("chengon","ee");
            String message=intent.getStringExtra("message");
            ChatMessageBean chatMessageBean = JsonTool.getPerson(message,ChatMessageBean.class);
            if(chatMessageBean!=null) {
                list.add(chatMessageBean);
                msgAdapter.notifyItemChanged(list.size() - 1);
            }
        }
    }

    public
    @OnClick({R.id.iv_back,R.id.send})
    void addFriend(View view) {
        switch (view.getId())
        {
            case R.id.iv_back:
              deleteFragment();
                break;
            case R.id.send:
                if("".equals(input.getText().toString()))
                {
                    ToastUtil.showToast(getContext(),"发送内容不能为空");
                return;
                }
                ChatSendBean chatSendBean = new ChatSendBean();
                chatSendBean.setContent(input.getText().toString());
                chatSendBean.setGroupname("");
                chatSendBean.setFriendname(friendname);
                chatSendBean.setRequire("person");
                chatSendBean.setImv("");
                chatSendBean.setImv("");
                chatSendBean.setUser_id("");
                chatSendBean.setUsername(PreferenceUtil.getUserName(getHodingActivity()));
                Gson gson = new Gson();
                String gsonstring = gson.toJson(chatSendBean);
                ServiceManager.client.send(gsonstring);
                ChatMessageBean chatMessageBean = new ChatMessageBean();
                 chatMessageBean.setImv("");
                 chatMessageBean.setMessage(input.getText().toString());
                 chatMessageBean.setMessage_id("");
                 chatMessageBean.setMine(true);
                 chatMessageBean.setTitle(PreferenceUtil.getUserName(getHodingActivity()));
                        list.add(chatMessageBean);

                msgAdapter.notifyItemChanged(list.size()-1);
                recyclerView.scrollToPosition(msgAdapter.getItemCount() - 1);
                input.setText("");
                break;
        }

    }
    @Override
    public void onResume()
    {
        super.onResume();
        initData();
    }
    private void initData()
    {
        chatfriendPresenter= new Presenter(this);
        chatfriendPresenter.getChatFriendMessage(PreferenceUtil.getUserName(getHodingActivity()),friendname);
        doRegisterReceiver();
    }

    private ChatMessageReceiver chatMessageReceiver;
    private void doRegisterReceiver()
    {
        chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter filter = new IntentFilter("weichat.private.wei.weichat.content");
        getHodingActivity().registerReceiver(chatMessageReceiver, filter);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_friend;
    }
}
