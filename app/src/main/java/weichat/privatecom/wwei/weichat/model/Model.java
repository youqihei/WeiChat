package weichat.privatecom.wwei.weichat.model;


import java.util.List;

import io.reactivex.Observable;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatMessageBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.contract.Contract;
import weichat.privatecom.wwei.weichat.utils.NetManager;

public class Model implements Contract.Model {
    @Override
    public Observable<BaseObjectBean<ChatRecordBean>> getchatrecord(String id) {
        return NetManager.getInstance().getRetrofitServer().getchatrecord(id);
    }

    @Override
    public Observable<BaseObjectBean<List<ChatMessageBean>>> getChatFriendMessage(String userid, String friendid) {
        return NetManager.getInstance().getRetrofitServer().getChatFriendMessage(userid, friendid);
    }

    @Override
    public Observable<BaseObjectBean<List<ChatMessageBean>>> getChatGroupMessage(String userid, String groupid) {
        return NetManager.getInstance().getRetrofitServer().getChatGroupMessage(userid, groupid);
    }

    @Override
    public Observable<BaseObjectBean<ChatBean>> addfriend(String name, String fname) {
        return NetManager.getInstance().getRetrofitServer().addfriend(name, fname);
    }

    @Override
    public Observable<BaseObjectBean<LoginBean>> addgroup(String name, String userid,String userphoto,String groupjson) {
        return NetManager.getInstance().getRetrofitServer().addgroup(name,userid ,userphoto,groupjson);
    }
}
