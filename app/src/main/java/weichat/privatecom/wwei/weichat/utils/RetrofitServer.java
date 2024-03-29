package weichat.privatecom.wwei.weichat.utils;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.ChatBean;
import weichat.privatecom.wwei.weichat.bean.ChatMessageBean;
import weichat.privatecom.wwei.weichat.bean.ChatRecordBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;
import weichat.privatecom.wwei.weichat.bean.RegisterBean;

public interface RetrofitServer {
  @FormUrlEncoded
   @POST("/WeiChatWeb/LoginCheck")
   Observable<BaseObjectBean<LoginBean>> login(@Field("name") String name,@Field("pwd") String password);
    @FormUrlEncoded
    @POST("/WeiChatWeb/Register")
    Observable<BaseObjectBean<RegisterBean>> register(@Field("name") String name, @Field("pwd") String password, @Field("photo") String photo);
 @FormUrlEncoded
 @POST("/WeiChatWeb/GetChatRecord")
 Observable<BaseObjectBean<ChatRecordBean>> getchatrecord(@Field("userid") String userid);
 @FormUrlEncoded
 @POST("/WeiChatWeb/ChatFriendMes")
 Observable<BaseObjectBean<List<ChatMessageBean>>> getChatFriendMessage(@Field("userid") String userid, @Field("friendid") String friendid);
 @FormUrlEncoded
 @POST("/WeiChatWeb/ChatGroupMes")
 Observable<BaseObjectBean<List<ChatMessageBean>>> getChatGroupMessage(@Field("userid") String userid, @Field("groupid") String groupid);
 @FormUrlEncoded
 @POST("/WeiChatWeb/SearchUser")
 Observable<BaseObjectBean<ChatBean>> addfriend(@Field("username") String name, @Field("friendname") String fname);
 @FormUrlEncoded
 @POST("/WeiChatWeb/AddGroup")
 Observable<BaseObjectBean<LoginBean>> addgroup(@Field("username") String name,@Field("userid") String userid,@Field("userphoto") String userphoto, @Field("grouplist") String groupjson);
}
