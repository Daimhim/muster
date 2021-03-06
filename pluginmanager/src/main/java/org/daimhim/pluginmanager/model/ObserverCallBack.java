package org.daimhim.pluginmanager.model;



import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.main.MainUtils;


import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称：org.daimhim.pluginmanager.model
 * 项目版本：muster
 * 创建时间：2018/10/24 10:02  星期三
 * 创建人：Administrator
 * 修改时间：2018/10/24 10:02  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public abstract class ObserverCallBack<T> implements Observer<T>, SingleObserver<T> {


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T pTJavaResponse) {
        if (pTJavaResponse instanceof JavaResponse) {
            if ("1".equals(((JavaResponse) pTJavaResponse).getError_code())) {
                onSuccess(pTJavaResponse);
            } else {
                onFailure((JavaResponse) pTJavaResponse);
            }
        } else {
            onSuccess(pTJavaResponse);
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        JavaResponse<Throwable> lVoidJavaResponse = new JavaResponse<>();
        lVoidJavaResponse.setError_code("0");
        lVoidJavaResponse.setError_msg(e.getMessage());
        lVoidJavaResponse.setResult(e);
        onFailure(lVoidJavaResponse);
    }

    @Override
    public void onComplete() {

    }

    public void onFailure(JavaResponse pJavaResponse){
        MainUtils.getI().toast(pJavaResponse.getError_msg());
    }

    public abstract void onSuccess(T pT);
}
