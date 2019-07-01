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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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

    ChatAdapter chatAdapter;
    private List<ChatRecordBean.Group> grouplist = new ArrayList<>();
    private List<ChatRecordBean.User> userlist = new ArrayList<>();
   private ChatRecordPresenter chatRecordPresenter;

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        initData();
      chatAdapter = new ChatAdapter(getActivity(),grouplist,userlist);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      recyclerView.setAdapter(chatAdapter);
      chatAdapter.notifyDataSetChanged();
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
         grouplist.addAll(response.getGroups());
         userlist.addAll(response.getUsers());
         chatAdapter.notifyDataSetChanged();
    }
    private void initData()
    {
        chatRecordPresenter = new ChatRecordPresenter(this);
        chatRecordPresenter.attachView(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }
}
