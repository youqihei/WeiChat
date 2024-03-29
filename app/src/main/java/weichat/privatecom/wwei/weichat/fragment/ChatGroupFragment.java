package weichat.privatecom.wwei.weichat.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class ChatGroupFragment extends BaseFragment implements Contract.ChatGroupView {

@BindView(R.id.tv_title)
    TextView tv_title;
@BindView(R.id.msg)
    RecyclerView recyclerView;
@BindView(R.id.tv_messagenumbers)
    TextView tv_messagenumber;
@BindView(R.id.input)
    EditText input;
            MsgAdapter msgAdapter;
private int count;
private List<ChatMessageBean> list = new ArrayList<>();

private Presenter chatgroupPresenter;
private static ChatGroupFragment chatGroupFragment;
    private String title = "好友";
    private String groupid = "";

public static BaseFragment newInstance(String title,String groupid) {
        if (chatGroupFragment == null) {
            chatGroupFragment = new ChatGroupFragment();
        Bundle bundle = new Bundle();
            bundle.putSerializable("chattitle_fragment",title);
            bundle.putSerializable("chatgroupid_fragment",groupid);
            chatGroupFragment.setArguments(bundle);
        }
        return chatGroupFragment;
        }

@Override
public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        if (null != getArguments()) {
            title = (String) getArguments().getSerializable("chattitle_fragment");
            groupid = (String) getArguments().getSerializable("chatgroupid_fragment");
        }
        }

@Override
protected void initView(View view, Bundle saveInstanceState) {
        tv_title.setText(title);
        msgAdapter = new MsgAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(msgAdapter);

        }

//显示进度框
@Override
public void showloading() {

        }

//隐藏进度框
@Override
public void hideloading() {

        }

//请求数据失败
@Override
public void onError(Throwable throwable) {
        ApiException apiException = (ApiException) throwable;
        ToastUtil.showToast(getActivity(), apiException.getDisplayMessage());
        }

//请求数据成功
@Override
public void onSuccess(List<ChatMessageBean> response) {
        list.clear();
        list.addAll(response);
        msgAdapter.notifyDataSetChanged();
        if(msgAdapter.getItemCount()!=0) {
        recyclerView.scrollToPosition(msgAdapter.getItemCount() - 1);
        }
        }

private class ChatMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message_friend");
        ChatMessageBean chatMessageBean = JsonTool.getPerson(message, ChatMessageBean.class);
        if (chatMessageBean != null) {
            if(chatMessageBean.getGroupid().equals(groupid)) {
                list.add(chatMessageBean);
                msgAdapter.notifyItemChanged(list.size() - 1);
                recyclerView.scrollToPosition(msgAdapter.getItemCount() - 1);
            }
            else
            {
                count++;
                tv_messagenumber.setText(count+"");
                tv_messagenumber.setVisibility(View.VISIBLE);
            }
        }
    }
}

    public
    @OnClick({R.id.iv_back, R.id.send})
    void addFriend(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                deleteFragment();
                break;
            case R.id.send:
                if ("".equals(input.getText().toString())) {
                    ToastUtil.showToast(getContext(), "发送内容不能为空");
                    return;
                }
                ChatSendBean chatSendBean = new ChatSendBean();
                chatSendBean.setContent(input.getText().toString());
                chatSendBean.setGroupid(groupid);
                chatSendBean.setFriendid("");
                chatSendBean.setRequire("group");
                chatSendBean.setImv("");
                chatSendBean.setUser_id(PreferenceUtil.getUserId(getHodingActivity()));
                chatSendBean.setUsername(PreferenceUtil.getUserName(getHodingActivity()));
                Gson gson = new Gson();
                String gsonstring = gson.toJson(chatSendBean);
                if (ServiceManager.client != null && ServiceManager.client.isOpen())
                    ServiceManager.client.send(gsonstring);
                ChatMessageBean chatMessageBean = new ChatMessageBean();
                chatMessageBean.setImv("");
                chatMessageBean.setMessage(input.getText().toString());
                chatMessageBean.setMessage_id(PreferenceUtil.getUserId(getHodingActivity()));
                chatMessageBean.setGroupid(groupid);
                chatMessageBean.setMine(true);
                chatMessageBean.setTitle(PreferenceUtil.getUserName(getHodingActivity()));
                list.add(chatMessageBean);
                msgAdapter.notifyItemChanged(list.size() - 1);
                recyclerView.scrollToPosition(msgAdapter.getItemCount() - 1);
                input.setText("");
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        chatgroupPresenter = new Presenter(this);
        chatgroupPresenter.getChatGroupMessage(PreferenceUtil.getUserId(getHodingActivity()),groupid);
        doRegisterReceiver();
        //   ListenerPan();
    }

    public void ListenerPan()
    {
        final LinearLayout layout = (LinearLayout)getHodingActivity().findViewById(R.id.ll_fcfriend);
        ViewTreeObserver observer = layout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                layout.getWindowVisibleDisplayFrame(rect);
                int screenHeight=layout.getRootView().getHeight();//获取屏幕高度
                int heightDiff=screenHeight-(rect.bottom-rect.top);//获取高度之差
                if (heightDiff>50){//50是因为我设置的editText高度为40，这个数值可以随时调整的
                    //此时软键盘弹出
                    recyclerView.scrollToPosition(msgAdapter.getItemCount() - 1);
                }

            }
        });
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

    @Override
    public void onDestroy() {
        getHodingActivity().unregisterReceiver(chatMessageReceiver);
        chatGroupFragment = null;
        super.onDestroy();
        chatMessageReceiver = null;
    }
}

