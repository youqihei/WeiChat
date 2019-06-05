package weichat.privatecom.wwei.weichat.utils;



import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.bean.LoginBean;

public interface RetrofitServer {
   @POST("/LoginCheck")
   Observable<BaseObjectBean<LoginBean>> login(@Field("username") String name, @Field("password") String password);
}
