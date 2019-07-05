package weichat.privatecom.wwei.weichat.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import weichat.privatecom.wwei.weichat.R;
import weichat.privatecom.wwei.weichat.base.BaseFragment;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.contract.Contract;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.presenter.Presenter;
import weichat.privatecom.wwei.weichat.utils.PreferenceUtil;
import weichat.privatecom.wwei.weichat.utils.ToastUtil;

/**
 * Created by Administrator on 2019/7/2.
 */

public class AddFriendFragment extends BaseFragment implements Contract.AddFriendView {
    @BindView(R.id.et_add)
    EditText et_add;
    @BindView(R.id.bt_add)
    Button bt_add;

    Presenter addFriendPresenter ;

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
    public void onSuccess(ChatBean response)
    {
        ToastUtil.showToast(getActivity(),"添加好友成功");
        deleteFragment();
    }
    @Override
    protected void initView(View view, Bundle saveInstanceState) {
        addFriendPresenter = new Presenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_addfriend;
    }
    @OnClick({R.id.tv_back,R.id.bt_add})
    void addFriend(View view) {
        switch (view.getId())
        {
            case R.id.tv_back:
                deleteFragment();
                break;
            case R.id.bt_add:
                if("".equals(et_add.getText().toString()))
                {
                    ToastUtil.showToast(getActivity(),"wwei号不能为空");
                return;
                }
                addFriendPresenter.addfriend(PreferenceUtil.getUserName(getHodingActivity()),et_add.getText().toString());
                break;
        }
    }
}

