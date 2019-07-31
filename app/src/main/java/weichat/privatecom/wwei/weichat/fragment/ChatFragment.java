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
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.activity.AddFriendActivity;
import weichat.privatecom.wwei.weichat.activity.AddGroupActivity;
import weichat.privatecom.wwei.weichat.adapter.ChatAdapter;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatMessageBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.contract.Contract;
import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable;

import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable_Table;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.presenter.Presenter;
import weichat.privatecom.wwei.weichat.utils.JsonTool;
import weichat.privatecom.wwei.weichat.utils.Pinyin4jUtil;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ToastUtil;

/**
 * Created by Administrator on 2019/6/13.
 */

public class ChatFragment extends BaseFragment implements Contract.ChatView {
    @BindView(R.id.ry_list)
    RecyclerView recyclerView;
    @BindView(R.id.tv_add)
    TextView tv_add;
    ChatAdapter chatAdapter;
    private List<ChatBean> list = new ArrayList<>();
   private Presenter chatRecordPresenter;


    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        doRegisterReceiver();
        chatAdapter = new ChatAdapter(getHodingActivity(),list,chatCallback);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(chatAdapter);
    }
    public  ChatAdapter.ChatCallback chatCallback= new ChatAdapter.ChatCallback() {
        @Override
        public void startnewFragment(int type,String title,String groupid,String friendid) {
            if(type==0)
            {
                addFragment(ChatGroupFragment.newInstance(title,groupid));
            }
            else if(type ==1) {
                addFragment(ChatFriendFragment.newInstance(title,friendid));
            }
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
        if(throwable instanceof  ApiException) {
            ApiException apiException = (ApiException) throwable;
            ToastUtil.showToast(getActivity(), apiException.getDisplayMessage());
        }
    }

    private ChatReceiver chatReceiver;
    private void doRegisterReceiver()
    {
        chatReceiver = new ChatReceiver();
        IntentFilter filter = new IntentFilter("weichat.private.wei.weichat.chatinterface");
        getHodingActivity().registerReceiver(chatReceiver, filter);
    }
    public class ChatReceiver  extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message_chatinterface");
         if(message!=null)
         {
             initData();
         }
        }
    }

    //请求数据成功
    @Override
    public void onSuccess(ChatRecordBean response)
    {
        list.clear();
        int length = 0;
        String content = "";
        String userid = PreferenceUtil.getUserId(getHodingActivity());
        for(int i=0;i<response.getGroups().size();i++)
        {
            length =  response.getGroups().get(i).getGroupcontent().getContent().size();
            if(length>0)
            {
                content =  response.getGroups().get(i).getGroupcontent().getContent().get(length-1);
            }
            ChatBean chatBean = new ChatBean(0,response.getGroups().get(i).getGroupid(),
                     userid,"",
                    response.getGroups().get(i).getGroupphoto(),
                    response.getGroups().get(i).getGroupname(),content);
             list.add(chatBean);
        }
        for(int i=0;i<response.getUsers().size();i++) {
            length = response.getUsers().get(i).getUsercontent().size();
            if (length > 0) {
                content = response.getUsers().get(i).getUsercontent().get(length - 1);
            }
            String username = response.getUsers().get(i).getUsername();
            ChatBean chatBean = new ChatBean(1,"", userid,response.getUsers().get(i).getUserid(),
                    response.getUsers().get(i).getUserphoto(),
                    username, content);
            list.add(chatBean);
            FNameTable fNameTable = new FNameTable();
            String username_en  = Pinyin4jUtil.converterToSpell(username);
            fNameTable.setUserid(Integer.parseInt(response.getUsers().get(i).getUserid()));
            fNameTable.setUsername(username);
            fNameTable.setUserphoto(response.getUsers().get(i).getUsername());
            fNameTable.setUsername_en(username_en);
            fNameTable.save();

        }
         chatAdapter.notifyDataSetChanged();
    }
    public
    @OnClick({R.id.tv_add})
    void addFriend(View view) {
        switch (view.getId())
        {
            case R.id.tv_add:
                startActivity(new Intent(getHodingActivity(), AddFriendActivity.class));
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
        chatRecordPresenter = new Presenter(this);
        chatRecordPresenter.getchatrecord(PreferenceUtil.getUserId(getHodingActivity()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }
}
