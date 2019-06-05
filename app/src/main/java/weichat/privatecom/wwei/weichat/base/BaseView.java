package weichat.privatecom.wwei.weichat.base;

public interface BaseView {
    void showloading();//显示进度框

    void hideloading();//隐藏进度框

    void onError(Throwable throwable);//请求数据失败

}
