package weichat.privatecom.wwei.weichat.presenter;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import weichat.privatecom.wwei.weichat.bean.RegisterBean;
import weichat.privatecom.wwei.weichat.contract.RegisterContract;
import weichat.privatecom.wwei.weichat.model.RegisterModel;
import weichat.privatecom.wwei.weichat.utils.ResponseTransformer;

/**
 * Created by Administrator on 2019/6/28.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    public RegisterContract.View view;
    public RegisterModel model;
    private CompositeDisposable mdisposable;
  public RegisterPresenter(RegisterContract.View view)
  {
     this.view = view;
     this.model = new RegisterModel();
      mdisposable = new CompositeDisposable();
  }

    @Override
    public void register(String name, String password, String photo) {
         model.register(name,password,photo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                 .compose(ResponseTransformer.<RegisterBean>handleResult()).subscribe(new Consumer<RegisterBean>(){
                     @Override
                   public void accept(RegisterBean registerBean) throws  Exception
                     {
                         view.onSuccess(registerBean);
                     }
         },new Consumer<Throwable>(){
             @Override
             public void accept(Throwable throwable) throws Exception {
                 view.onError(throwable);
             }
         });
    }
}
