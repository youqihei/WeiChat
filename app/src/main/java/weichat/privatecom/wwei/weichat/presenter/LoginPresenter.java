package weichat.privatecom.wwei.weichat.presenter;

import android.util.Log;

import weichat.privatecom.wwei.weichat.contract.LoginContract;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.contract.LoginContract;
import weichat.privatecom.wwei.weichat.model.LoginModel;
import weichat.privatecom.wwei.weichat.utils.ResponseTransformer;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter{

    private LoginModel model;
    private LoginContract.View view;
    private CompositeDisposable mdisposable;
    public LoginPresenter( LoginContract.View view )
    {
       this.model = new LoginModel();
       this.view = view;
       mdisposable = new CompositeDisposable();
    }

    @Override
    public void login(String username, String password) {
      model.login(username,password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
              compose(ResponseTransformer.<LoginBean>handleResult()).subscribe(new Consumer<LoginBean>() {
          @Override
          public void accept(LoginBean loginBean) throws Exception {
              view.onSuccess(loginBean);
          }
    }, new Consumer<Throwable>() {
          @Override
          public void accept(Throwable throwable) throws Exception {
                view.onError(throwable);
          }
      });
    }
}
