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
    public Observable<BaseObjectBean<ChatRecordBean>> getchatrecord(String name) {
        return NetManager.getInstance().getRetrofitServer().getchatrecord(name);
    }

    @Override
    public Observable<BaseObjectBean<List<ChatMessageBean>>> getChatFriendMessage(String name, String fname) {
        return NetManager.getInstance().getRetrofitServer().getChatFriendMessage(name, fname);
    }

    @Override
    public Observable<BaseObjectBean<ChatBean>> addfriend(String name, String fname) {
        return NetManager.getInstance().getRetrofitServer().addfriend(name, fname);
    }

    @Override
    public Observable<BaseObjectBean<LoginBean>> addgroup(String name, String groupjson) {
        return NetManager.getInstance().getRetrofitServer().addgroup(name, groupjson);
    }
}
