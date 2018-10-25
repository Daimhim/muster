package org.daimhim.pluginmanager.model;

import android.text.TextUtils;

import org.daimhim.pluginmanager.model.response.JavaResponse;

import io.reactivex.Observer;
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
public abstract class ObserverCallBack<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T pTJavaResponse) {
       if (pTJavaResponse instanceof JavaResponse){
           if (TextUtils.equals(((JavaResponse) pTJavaResponse).getError_code(),"1")) {
               onSuccess(pTJavaResponse);
           }else {
               onFailure((JavaResponse) pTJavaResponse);
           }
       }else {
           JavaResponse<T> lVoidJavaResponse = new JavaResponse<>();
           lVoidJavaResponse.setResult(pTJavaResponse);
            onFailure(lVoidJavaResponse);
       }
    }

    @Override
    public void onError(Throwable e) {
        JavaResponse<Void> lVoidJavaResponse = new JavaResponse<>();
        e.printStackTrace();
        onFailure(lVoidJavaResponse);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onSuccess(T pT);
    protected abstract void onFailure(JavaResponse pJavaResponse);

}
