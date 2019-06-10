package weichat.privatecom.wwei.weichat.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.contract.LoginContract;
import weichat.privatecom.wwei.weichat.presenter.LoginPresenter;

/**
 * Created by Administrator on 2019/4/23.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View{
    public static String LoginFragment = "login_fragment";
    private  LoginPresenter loginPresenter;
    private String username;
    private String password;
    public static BaseFragment newInstance(String username)
    {
            LoginFragment loginFragment = new LoginFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(LoginFragment,username);
            loginFragment.setArguments(bundle);
            return loginFragment;
    }
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        if(null!=getArguments())
        {
            username = (String)getArguments().getSerializable(LoginFragment);
        }
    }
    @Override
    protected void initView(View view, Bundle saveInstanceState) {
         loginPresenter = new LoginPresenter(this);
        loginPresenter.attachView(this);
        initData();
    }
    protected  void initData()
    {

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

    }
    //请求数据成功
    @Override
    public void onSuccess(LoginBean response)
    {

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
    @OnClick({R.id.btn_login})
    public  void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_login:
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                loginPresenter.login(username,password);
                break;
        }
    }
}
