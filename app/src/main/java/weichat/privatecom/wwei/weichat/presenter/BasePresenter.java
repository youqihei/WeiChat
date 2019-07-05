package weichat.privatecom.wwei.weichat.presenter;

import android.view.View;

import weichat.privatecom.wwei.weichat.base.BaseView;

public class  BasePresenter<V extends BaseView> {
    protected  V mView;
    public void attachView(V view)
    {
        mView = view;
    }
    public void detatchView()
    {
        mView = null;
    }

    public  boolean isAttachView()
    {
        return mView!=null?true:false;
    }

}
