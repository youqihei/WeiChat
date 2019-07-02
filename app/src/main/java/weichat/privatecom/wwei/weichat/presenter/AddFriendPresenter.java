package weichat.privatecom.wwei.weichat.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.RegisterBean;
import weichat.privatecom.wwei.weichat.contract.AddFriendContract;
import weichat.privatecom.wwei.weichat.contract.RegisterContract;
import weichat.privatecom.wwei.weichat.model.AddFriendModel;
import weichat.privatecom.wwei.weichat.model.RegisterModel;
import weichat.privatecom.wwei.weichat.utils.ResponseTransformer;

/**
 * Created by Administrator on 2019/7/2.
 */

public class AddFriendPresenter extends BasePresenter<AddFriendContract.View> implements AddFriendContract.Presenter {

    public AddFriendContract.View view;
    public AddFriendModel model;
    private CompositeDisposable mdisposable;
    public AddFriendPresenter(AddFriendContract.View view)
    {
        this.view = view;
        this.model = new AddFriendModel();
        mdisposable = new CompositeDisposable();
    }

    @Override
    public void addfriend(String name, String fname) {
        model.addfriend(name,fname).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(ResponseTransformer.<ChatBean>handleResult()).subscribe(new Consumer<ChatBean>(){
            @Override
            public void accept(ChatBean chatBean) throws  Exception
            {
                view.onSuccess(chatBean);
            }
        }, new Consumer<Throwable>(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.onError(throwable);
            }
        });
    }
}
