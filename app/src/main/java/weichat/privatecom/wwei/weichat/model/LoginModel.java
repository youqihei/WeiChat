package weichat.privatecom.wwei.weichat.model;

import android.util.Log;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.contract.LoginContract;
import weichat.privatecom.wwei.weichat.utils.NetManager;

public class LoginModel implements LoginContract.Model{
    @Override
    public Observable<BaseObjectBean<LoginBean>> login(String name, String password) {
        return NetManager.getInstance().getRetrofitServer().login(name,password);
    }
}
