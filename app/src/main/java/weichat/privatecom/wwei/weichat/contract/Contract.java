package weichat.privatecom.wwei.weichat.contract;

import java.util.List;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.base.BaseView;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatMessageBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;

public interface Contract {
    interface  Model
    {
        Observable<BaseObjectBean<ChatRecordBean>> getchatrecord(String name);
        Observable<BaseObjectBean<List<ChatMessageBean>>> getChatFriendMessage(String name, String fname);
        Observable<BaseObjectBean<ChatBean>> addfriend(String name, String fname);
        Observable<BaseObjectBean<LoginBean>> addgroup(String name, String groupjson);
    }

    interface View extends BaseView
    {
        void onSuccess(LoginBean esponse);
    }
    interface AddFriendView extends BaseView
    {
        void onSuccess(ChatBean esponse);
    }
    interface ChatView extends BaseView
    {
        void onSuccess(ChatRecordBean esponse);
    }
    interface ChatFriendView extends BaseView
    {
        void onSuccess(List<ChatMessageBean> esponse);
    }
    interface AddGroupView extends BaseView
    {
        void onSuccess(LoginBean esponse);
    }
    interface  Presenter
    {
        void getchatrecord(String name);
        void  getChatFriendMessage(String name, String fname);
        void  addfriend(String name, String fname);
        void addgroup(String name,String groupjson);
    }
}
