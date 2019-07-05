package weichat.privatecom.wwei.weichat.fragment;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.adapter.ChatAdapter;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.contract.Contract;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.presenter.Presenter;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ToastUtil;

/**
 * Created by Administrator on 2019/6/13.
 */

public class ChatFragment extends BaseFragment implements Contract.ChatRecordView {
    @BindView(R.id.ry_list)
    RecyclerView recyclerView;
    @BindView(R.id.tv_add)
    TextView tv_add;
    ChatAdapter chatAdapter;
    private List<ChatBean> list = new ArrayList<>();
   private Presenter chatRecordPresenter;

    @Override
    protected void initView(View view, Bundle saveInstanceState) {
      chatAdapter = new ChatAdapter(getHodingActivity(),list,chatCallback);
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      recyclerView.setAdapter(chatAdapter);

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
    public void onSuccess(ChatRecordBean response)
    {
        list.clear();
        int length = 0;
        String content = "";
        for(int i=0;i<response.getGroups().size();i++)
        {
            length =  response.getGroups().get(i).getGroupcontent().getContent().size();
            if(length>0)
            {
                content =  response.getGroups().get(i).getGroupcontent().getContent().get(length-1);
            }
            ChatBean chatBean = new ChatBean(response.getGroups().get(i).getGroupphoto(),
                    response.getGroups().get(i).getGroupname(),content,"");
             list.add(chatBean);
        }
        for(int i=0;i<response.getUsers().size();i++)
        {
            length = response.getUsers().get(i).getUsercontent().size();
            if(length>0)
            {
                content =  response.getUsers().get(i).getUsercontent().get(length-1);
            }
            ChatBean chatBean = new ChatBean(response.getUsers().get(i).getUserphoto(),
                    response.getUsers().get(i).getUsername(),content
                   ,"");
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
        chatRecordPresenter = new Presenter(this);
        chatRecordPresenter.getChatRecord(PreferenceUtil.getUserName(getHodingActivity()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }
}
