package weichat.privatecom.wwei.weichat.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.bean.RegisterBean;
import weichat.privatecom.wwei.weichat.contract.ChatRecordContract;
import weichat.privatecom.wwei.weichat.contract.LoginContract;
import weichat.privatecom.wwei.weichat.contract.RegisterContract;
import weichat.privatecom.wwei.weichat.model.ChatRecordModel;
import weichat.privatecom.wwei.weichat.model.RegisterModel;
import weichat.privatecom.wwei.weichat.utils.ResponseTransformer;

/**
 * Created by Administrator on 2019/7/1.
 */

public class ChatRecordPresenter extends BasePresenter<ChatRecordContract.View> implements ChatRecordContract.Presenter{

    public ChatRecordContract.View view;
    public ChatRecordModel model;
    private CompositeDisposable mdisposable;
    public ChatRecordPresenter(ChatRecordContract.View view)
    {
        this.view = view;
        this.model = new ChatRecordModel();
        mdisposable = new CompositeDisposable();
    }

    @Override
    public void getChatRecord(String name) {
        model.getChatRecord(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .compose(ResponseTransformer.<ChatRecordBean>handleResult()).subscribe(new Consumer<ChatRecordBean>(){
            @Override
            public void accept(ChatRecordBean chatRecordBean) throws  Exception
            {
                view.onSuccess(chatRecordBean);
            }
        }, new Consumer<Throwable>(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.onError(throwable);
            }
        });
    }
}
