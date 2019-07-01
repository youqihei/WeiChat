package weichat.privatecom.wwei.weichat.contract;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.base.BaseView;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;


/**
 * Created by Administrator on 2019/7/1.
 */

public interface ChatRecordContract {
    interface  Model
    {
        Observable<BaseObjectBean<ChatRecordBean>> getChatRecord(String name);
    }
    interface View extends BaseView
    {
        void onSuccess(ChatRecordBean registerBean);
    }
    interface  Presenter
    {
        void  getChatRecord(String name);
    }
}
