package weichat.privatecom.wwei.weichat.model;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.RegisterBean;
import weichat.privatecom.wwei.weichat.contract.RegisterContract;
import weichat.privatecom.wwei.weichat.utils.NetManager;

/**
 * Created by Administrator on 2019/6/28.
 */

public class RegisterModel implements RegisterContract.Model{
    @Override
    public Observable<BaseObjectBean<RegisterBean>> register(String name, String password, String photo) {
        return NetManager.getInstance().getRetrofitServer().register(name,password,photo);
    }
}
