package weichat.privatecom.wwei.weichat.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.JWebSocketClient;
import weichat.privatecom.wwei.weichat.MainActivity;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.adapter.ChatAdapter;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.contract.ChatRecordContract;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.presenter.ChatRecordPresenter;
import weichat.privatecom.wwei.weichat.presenter.LoginPresenter;
import weichat.privatecom.wwei.weichat.presenter.RegisterPresenter;
import weichat.privatecom.wwei.weichat.service.WebSocketService;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ToastUtil;

/**
 * Created by Administrator on 2019/6/13.
 */

public class ChatFragment extends BaseFragment implements ChatRecordContract.View {
    @BindView(R.id.ry_list)
    RecyclerView recyclerView;
    @BindView(R.id.tv_add)
    TextView tv_add;
    ChatAdapter chatAdapter;
    private List<ChatBean> list = new ArrayList<>();
   private ChatRecordPresenter chatRecordPresenter;

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
      chatAdapter = new ChatAdapter(getActivity(),list);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      recyclerView.setAdapter(chatAdapter);

    }
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
    public void onSuccess(ChatRecordBean response)
    {
        list.clear();
        int length = 0;
        for(int i=0;i<response.getGroups().size();i++)
        {
            length =  response.getGroups().get(i).getGroupcontent().getContent().size();
            ChatBean chatBean = new ChatBean(response.getGroups().get(i).getGroupphoto(),
                    response.getGroups().get(i).getGroupname(),
                    response.getGroups().get(i).getGroupcontent().getContent().get(length-1),"");
             list.add(chatBean);
        }
        for(int i=0;i<response.getUsers().size();i++)
        {
            length = response.getUsers().get(i).getUsercontent().size();
            ChatBean chatBean = new ChatBean(response.getUsers().get(i).getUserphoto(),
                    response.getUsers().get(i).getUsername(),
                    response.getUsers().get(i).getUsercontent().get(length-1),"");
            list.add(chatBean);
        }
         chatAdapter.notifyDataSetChanged();
    }
    public
    @OnClick({R.id.tv_add})
    void addFriend(View view) {
        switch (view.getId())
        {
            case R.id.tv_add:
              AddFriendFragment addfriendfragment = new AddFriendFragment();
              addFragment(addfriendfragment);
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
        chatRecordPresenter = new ChatRecordPresenter(this);
        chatRecordPresenter.attachView(this);
        chatRecordPresenter.getChatRecord(PreferenceUtil.getUserName(getHodingActivity()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }
}
