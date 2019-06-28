package weichat.privatecom.wwei.weichat.contract;



import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.base.BaseView;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.RegisterBean;

/**
 * Created by Administrator on 2019/6/28.
 */

public interface RegisterContract {
    interface  Model
    {
        Observable<BaseObjectBean<RegisterBean>> register(String name, String password, String photo);
    }
    interface View extends BaseView
    {
        void onSuccess(RegisterBean registerBean);
    }
    interface  Presenter
    {
       void  register(String name,String password,String photo);
    }
}
