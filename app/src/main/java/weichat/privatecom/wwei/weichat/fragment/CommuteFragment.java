package weichat.privatecom.wwei.weichat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.activity.AddGroupActivity;
import weichat.privatecom.wwei.weichat.adapter.CommuteAdapter;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable;
import weichat.privatecom.wwei.weichat.dbflow.dbbean.FNameTable_Table;


/**
 * Created by Administrator on 2019/6/13.
 */

public class CommuteFragment extends BaseFragment {
    @BindView(R.id.tv_add)
    TextView tv_add;
     @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    CommuteAdapter commuteAdapter;
    List<FNameTable> list = new ArrayList<>();
    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        commuteAdapter = new CommuteAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter( commuteAdapter);
        initData();
    }
   public void initData()
   {
       List<FNameTable> tempo  = SQLite.select()
               .from(FNameTable.class)
               .orderBy(FNameTable_Table.username_en,true)
               .queryList();
       list.addAll(tempo);
       commuteAdapter.notifyDataSetChanged();
   }
   @OnClick({R.id.tv_add})
   void addGroup(View view)
   {
       switch (view.getId())
       {
           case R.id.tv_add:
             startActivity(new Intent(getHodingActivity(), AddGroupActivity.class));
               break;
       }
   }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commute;
    }
}
