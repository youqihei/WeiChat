package weichat.privatecom.wwei.weichat.model;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.contract.AddFriendContract;
import weichat.privatecom.wwei.weichat.contract.ChatRecordContract;
import weichat.privatecom.wwei.weichat.utils.NetManager;

/**
 * Created by Administrator on 2019/7/2.
 */

public class AddFriendModel implements AddFriendContract.Model{
    @Override
    public Observable<BaseObjectBean<ChatBean>> addfriend(String name,String fname) {
        return NetManager.getInstance().getRetrofitServer().addfriend(name,fname);
    }
}


