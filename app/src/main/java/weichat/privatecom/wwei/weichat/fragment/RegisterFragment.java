package weichat.privatecom.wwei.weichat.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import interceptmessage.privatecom.wwei.multi_image_selector.MultiImageSelector;
import weichat.privatecom.wwei.weichat.MainActivity;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.activity.LoginActivity;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.bean.RegisterBean;
import weichat.privatecom.wwei.weichat.contract.LoginContract;
import weichat.privatecom.wwei.weichat.contract.RegisterContract;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.presenter.LoginPresenter;
import weichat.privatecom.wwei.weichat.presenter.RegisterPresenter;
import weichat.privatecom.wwei.weichat.service.WebSocketService;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ServiceManager;
import weichat.privatecom.wwei.weichat.utils.ToastUtil;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by Administrator on 2019/7/1.
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.View{
    private RegisterPresenter registerPresenter;
    private String username;
    private String password;
    private String password2;
    public static BaseFragment newInstance()
    {
        RegisterFragment registerFragment = new RegisterFragment();
        return registerFragment;
    }
    @BindView(R.id.btn_register)
    Button btn_register;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_ps)
    EditText et_ps;
    @BindView(R.id.et_psconfirm)
    EditText et_psconfirm;
    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }
    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.attachView(this);
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
        ApiException apiException = (ApiException) throwable;
        ToastUtil.showToast(getActivity(),apiException.getDisplayMessage());
    }
    //请求数据成功
    @Override
    public void onSuccess(RegisterBean response)
    {
        ToastUtil.showToast(getActivity(),"注册成功");
        PreferenceUtil.setLoginStatus(getHodingActivity(),true);
        PreferenceUtil.setUserName(getHodingActivity(),username);
        Intent intent = new Intent(getHodingActivity(),MainActivity.class);
        PreferenceUtil.setPassWord(getHodingActivity(),response.getPassword());
        PreferenceUtil.setUserId(getHodingActivity(),response.getUserid());
        PreferenceUtil.setUserPhoto(getHodingActivity(),response.getUserphoto());
        startActivity(intent);
       getActivity().finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
    @OnClick({R.id.btn_register,R.id.ll_choose,R.id.iv_choose})
    public  void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ll_choose:
            case R.id.iv_choose:
                pickImage();
                break;
            case R.id.btn_register:
                username = et_username.getText().toString();
                password = et_ps.getText().toString();
                password2 = et_psconfirm.getText().toString();
                if("".equals(username))
                {
                    ToastUtil.showToast(getActivity(),"用户名不能为空");
                    return;
                }
                if("".equals(password))
                {
                    ToastUtil.showToast(getActivity(),"密码不能为空");
                    return;
                }
                if("".equals(password2))
                {
                    ToastUtil.showToast(getActivity(),"请再次确认密码");
                    return;
                }
                if(!password.equals(password2))
                {
                    ToastUtil.showToast(getActivity(),"两次密码不一样");
                    return;
                }
                registerPresenter.register(username,password,"");
                break;
        }
    }
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    private ArrayList<String> mSelectPath;
    private static final int REQUEST_IMAGE = 2;
    private void pickImage()
    {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.JELLY_BEAN// Permission was added in API Level 16
            && ActivityCompat.checkSelfPermission(getHodingActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getHodingActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        }else
        {
            MultiImageSelector selector = MultiImageSelector.newInstance();
            selector.origin(mSelectPath);
            selector.start(this, REQUEST_IMAGE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

