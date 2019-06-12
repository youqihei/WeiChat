package weichat.privatecom.wwei.weichat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import butterknife.BindView;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.activity.LoginActivity;

/**
 * Created by Administrator on 2019/4/23.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.bt_register)
    Button bt_register;

    private static MainFragment mainfragment;
    public static BaseFragment newInstance()
    {
        if(mainfragment==null)
        {
            mainfragment = new MainFragment();
        }
        return mainfragment;
    }
    @Override
    protected void initView(View view, Bundle saveInstanceState) {

      bt_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getHodingActivity(), LoginActivity.class);
              Bundle data = new Bundle();
              data.putString("name","wwei");
              intent.putExtras(data);
              startActivityForResult(intent,0);
          }
      });

    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;
    }
}
