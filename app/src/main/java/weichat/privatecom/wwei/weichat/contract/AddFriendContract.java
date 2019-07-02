package weichat.privatecom.wwei.weichat.contract;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.base.BaseView;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;

/**
 * Created by Administrator on 2019/7/2.
 */

public interface AddFriendContract {
    interface  Model
    {
        Observable<BaseObjectBean<ChatBean>> addfriend(String name,String fname);
    }
    interface View extends BaseView
    {
        void onSuccess(ChatBean registerBean);
    }
    interface  Presenter
    {
        void  addfriend(String name,String fname);
    }
}
