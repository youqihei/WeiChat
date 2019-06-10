package weichat.privatecom.wwei.weichat.contract;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.base.BaseView;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;

public interface LoginContract {
  interface  Model
  {
      Observable<BaseObjectBean<LoginBean>> login(String name,String password);
  }

  interface View extends BaseView
  {
    void onSuccess(LoginBean esponse);
  }
  interface  Presenter
  {
      void login(String username,String password);
  }
}
