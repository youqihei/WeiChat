package weichat.privatecom.wwei.weichat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import butterknife.BindView;
import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.activity.LoginActivity;
import weichat.privatecom.wwei.weichat.service.WebSocketService;
import weichat.privatecom.wwei.weichat.utils.ServiceManager;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Administrator on 2019/4/23.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.tb1)
    TextView tb1;
    @BindView(R.id.tb2)
    TextView tb2;
    @BindView(R.id.tb3)
    TextView tb3;
    @BindView(R.id.tb4)
    TextView tb4;
    private static MainFragment mainfragment;
    public static String Mainfragment = "main_fragment";
    private int oCid  = -1;
    private ChatFragment chatFragment;
    private CommuteFragment commuteFragment;
    private FriendCircleFragment friendCircleFragment;
    private MineFragment mineFragment;
    private FragmentManager fm;
    private BaseFragment mCurrentFragment;

    public static BaseFragment newInstance(boolean isfiring)
    {
        if(mainfragment==null)
        {
            mainfragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(Mainfragment,isfiring);
            mainfragment.setArguments(bundle);
        }
        return mainfragment;
    }
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        if(null!=getArguments())
        {

        }
    }
    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        new Thread() {
            @Override
            public void run() {
               getHodingActivity().bindService( new Intent(getHodingActivity().getBaseContext(), WebSocketService.class), ServiceManager.newInstance().serviceConnection, BIND_AUTO_CREATE);
            }
        }.start();
        fm = getActivity().getSupportFragmentManager();
            selectHomeTab(R.id.tb1);
    }
    public void selectHomeTab(int checkedId) {
        if (checkedId == R.id.tb1&& oCid != R.id.tb1) {
            //首页
            if (chatFragment == null) {
                chatFragment = new ChatFragment();
            }
            repalce(chatFragment);
        } else if (checkedId == R.id.tb2&& oCid != R.id.tb2) {

            if (commuteFragment == null) {
                commuteFragment = new CommuteFragment();
            }
            repalce(commuteFragment);
        } else if (checkedId == R.id.tb3&& oCid != R.id.tb3) {
            //开奖信息
            if (friendCircleFragment == null) {
                friendCircleFragment = new FriendCircleFragment();
            }
            repalce(friendCircleFragment);
        } else if (checkedId == R.id.tb4&& oCid != R.id.tb4) {
            if (mineFragment == null) {
                mineFragment = new MineFragment();
            }
            repalce(mineFragment);
        }
        //默认设置选中的viewtag
        oCid = checkedId;
    }
    public  void repalce(BaseFragment fragment)
    {
        FragmentTransaction ft = fm.beginTransaction();
//        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        if (!fragment.isAdded())
            if (mCurrentFragment == null)
                ft.add(R.id.fragment_container, fragment).show(fragment);
            else
                ft.add(R.id.fragment_container, fragment).hide(mCurrentFragment).show(fragment);
        else
            ft.hide(mCurrentFragment).show(fragment);
        mCurrentFragment = fragment;
        ft.commitAllowingStateLoss();

    }
    public
    @OnClick({R.id.tb1,R.id.tb2,R.id.tb3,R.id.tb4})
    void showFragment(View view) {

        selectHomeTab(view.getId());
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_main;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
       mainfragment = null;
       chatFragment = null;
    }
}
