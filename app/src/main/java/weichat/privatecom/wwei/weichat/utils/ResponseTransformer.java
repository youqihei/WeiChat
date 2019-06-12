package weichat.privatecom.wwei.weichat.utils;

import android.content.Intent;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.Response;
import weichat.privatecom.wwei.weichat.bean.BaseObjectBean;
import weichat.privatecom.wwei.weichat.exception.ApiException;
import weichat.privatecom.wwei.weichat.exception.CustomException;

/**
 * Created by Administrator on 2019/6/4.
 */

public class ResponseTransformer {

    public static <T> ObservableTransformer<BaseObjectBean<T>, T> handleResult() {
        return new ObservableTransformer<BaseObjectBean<T>, T>() {
                    @Override
                    public ObservableSource<T> apply(Observable<BaseObjectBean<T>> upstream) {
                        return upstream.onErrorResumeNext(new ErrorResumeFunction<T>())
                                .flatMap(new ResponseFunction<T>());
                    }
                };
    }

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<? extends BaseObjectBean<T>>> {
        @Override
        public ObservableSource<? extends BaseObjectBean<T>> apply(Throwable throwable) throws Exception {

            return Observable.error(CustomException.handleException(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<BaseObjectBean<T>, ObservableSource<T>> {

        @Override
        public ObservableSource<T> apply(BaseObjectBean<T> tResponse) throws Exception {
            String  code = tResponse.getStatus();
            String message = tResponse.getMessage();
            if (code.equals("0")) {
                return Observable.just(tResponse.getData());
            } else {
                return Observable.error(new ApiException(Integer.parseInt(code), message));
            }
        }
    }


}
