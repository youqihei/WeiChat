package weichat.privatecom.wwei.weichat.utils;



import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;

public interface RetrofitServer {
  @FormUrlEncoded
   @POST("/WeiChatWeb/LoginCheck")
   Observable<BaseObjectBean<LoginBean>> login(@Field("name") String name,@Field("pwd") String password);
}
