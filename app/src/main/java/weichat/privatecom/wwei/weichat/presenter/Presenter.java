package weichat.privatecom.wwei.weichat.presenter;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import weichat.privatecom.wwei.weichat.base.BaseView;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatMessageBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.contract.Contract;
import weichat.privatecom.wwei.weichat.contract.LoginContract;
import weichat.privatecom.wwei.weichat.model.LoginModel;
import weichat.privatecom.wwei.weichat.model.Model;
import weichat.privatecom.wwei.weichat.utils.ResponseTransformer;

public class Presenter<V extends BaseView> implements Contract.Presenter{
    protected  V mView;
    public void attachView(V view)
    {
        mView = view;
    }
    public void detatchView()
    {
        mView = null;
    }

    private Model model;
    private Contract.AddFriendView addFriendView;
    private Contract.AddGroupView addGroupView;
    private Contract.ChatFriendView chatFriendView;
    private Contract.ChatView chatView;
    private CompositeDisposable mdisposable;
    public Presenter( BaseView view )
    {
        if(view instanceof Contract.AddFriendView)
        {
            addFriendView = (Contract.AddFriendView) view;
        }
        else if(view instanceof  Contract.AddGroupView)
        {
            addGroupView = (Contract.AddGroupView) view;
        }
        else if(view instanceof  Contract.ChatFriendView)
        {
            chatFriendView = (Contract.ChatFriendView) view;
        }
        else if(view instanceof  Contract.ChatView)
        {
            chatView = (Contract.ChatView)view;
        }
        this.model = new Model();
        mdisposable = new CompositeDisposable();
    }
    public  boolean isAttachView()
    {
        return mView!=null?true:false;
    }
    @Override
    public void getchatrecord(String name) {
        model.getchatrecord(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                compose(ResponseTransformer.<ChatRecordBean>handleResult()).subscribe(new Consumer<ChatRecordBean>() {
            @Override
            public void accept(ChatRecordBean chatRecordBean) throws Exception {
                chatView.onSuccess(chatRecordBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                chatView.onError(throwable);
            }
        });
    }

    @Override
    public void getChatFriendMessage(String name, String fname) {
        model.getChatFriendMessage(name, fname).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                compose(ResponseTransformer.<List<ChatMessageBean>>handleResult()).subscribe(new Consumer<List<ChatMessageBean>>() {
            @Override
            public void accept(List<ChatMessageBean> chatMessageBeans) throws Exception {
                chatFriendView.onSuccess(chatMessageBeans);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                chatFriendView.onError(throwable);
            }
        });
    }

    @Override
    public void addfriend(String name, String fname) {
        model.addfriend(name, fname).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                compose(ResponseTransformer.<ChatBean>handleResult()).subscribe(new Consumer<ChatBean>() {
            @Override
            public void accept(ChatBean chatBean) throws Exception {
                addFriendView.onSuccess(chatBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                addFriendView.onError(throwable);
            }
        });
    }

    @Override
    public void addgroup(String name, String groupjson) {
        model.addgroup(name, groupjson).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                compose(ResponseTransformer.<LoginBean>handleResult()).subscribe(new Consumer<LoginBean>() {
            @Override
            public void accept(LoginBean chatBean) throws Exception {
                addGroupView.onSuccess(chatBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                addGroupView.onError(throwable);
            }
        });
    }
}
