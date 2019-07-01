package weichat.privatecom.wwei.weichat.model;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.contract.ChatRecordContract;
import weichat.privatecom.wwei.weichat.utils.NetManager;

/**
 * Created by Administrator on 2019/7/1.
 */

public class ChatRecordModel implements ChatRecordContract.Model{
    @Override
    public Observable<BaseObjectBean<ChatRecordBean>> getChatRecord(String name) {
        return NetManager.getInstance().getRetrofitServer().getchatrecord(name);
    }
}

