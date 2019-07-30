package weichat.privatecom.wwei.weichat.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.adapter.AddGroupAdapter;
import weichat.privatecom.wwei.weichat.adapter.CommuteAdapter;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.contract.Contract;
import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable;
import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable_Table;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.presenter.Presenter;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ToastUtil;

/**
 * Created by Administrator on 2019/7/11.
 */

public class AddGroupFragment extends BaseFragment implements Contract.AddGroupView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    AddGroupAdapter addGroupAdapter;
    List<FNameTable> list = new ArrayList<>();
    private LinkedList<FNameTable> ischoose_list = new LinkedList<>();
    Presenter addGroupPresenter;

    public static BaseFragment newInstance()
    {
        AddGroupFragment addGroupFragment = new AddGroupFragment();
        Bundle bundle = new Bundle();
        return addGroupFragment;
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
    public void onSuccess(LoginBean response)
    {
        ToastUtil.showToast(getActivity(),"添加群成功");
        getActivity().finish();
    }
    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        addGroupAdapter = new AddGroupAdapter(getContext(),list,listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter( addGroupAdapter);
        addGroupPresenter = new Presenter(this);
        initData();
    }
    private AddGroupAdapter.Listener listener  = new AddGroupAdapter.Listener() {
        private int userid;
        @Override
        public void itemchecked(boolean ischecked,int position,int userid) {
            if(ischecked) {
                FNameTable tempo = SQLite.select()
                        .from(FNameTable.class)
                        .where(FNameTable_Table.userid.eq(userid))
                        .querySingle();
                ischoose_list.add(tempo);
            }
            else
            {
                for(int i=0;i<ischoose_list.size();i++) {
                    if(userid==ischoose_list.get(i).getUserid())
                    ischoose_list.remove(i);
                }
            }
        }
    };
    public void initData()
    {
        List<FNameTable> tempo  = SQLite.select()
                .from(FNameTable.class)
                .orderBy(FNameTable_Table.username_en,true)
                .queryList();
        list.addAll(tempo);
        addGroupAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.tv_complete})
    void addGroup(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_complete:
                Gson gson = new Gson();
                String strJson = gson.toJson(ischoose_list);
              addGroupPresenter.addgroup(PreferenceUtil.getUserName(getHodingActivity()),PreferenceUtil.getUserId(getHodingActivity()),
                      PreferenceUtil.getUserPhoto(getHodingActivity()),strJson);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_addgroup;
    }
}
