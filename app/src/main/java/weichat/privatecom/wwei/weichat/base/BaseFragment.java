package weichat.privatecom.wwei.weichat.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/4/23.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void initView(View view, Bundle saveInstanceState);//初始化视图
    protected abstract int getLayoutId();//获取布局ID
    BaseActivity mActivity;
    Unbinder unbinder;
    protected BaseActivity getHodingActivity()
    {
        return mActivity;
    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }
    //添加fragment
    public void addFragment(BaseFragment fragment)
    {
        if(fragment!=null&&getHodingActivity()!=null)
        {
            getHodingActivity().addFragment(fragment);
        }
    }
    //删除fragment
    public void deleteFragment()
    {
        if(getHodingActivity()!=null)
        {
            getHodingActivity().removeFragment();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
    {
        View view = getLayoutInflater().inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this,view);
        initView(view,saveInstanceState);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
