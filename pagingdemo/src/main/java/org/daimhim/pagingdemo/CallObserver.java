package org.daimhim.pagingdemo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018/11/26 10:25  星期一
 * 创建人：Administrator
 * 修改时间：2018/11/26 10:25  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public abstract class CallObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable pDisposable) {

    }

    @Override
    public abstract void onNext(T pT);

    @Override
    public void onError(Throwable pThrowable) {
        Timber.e(pThrowable);
    }

    @Override
    public void onComplete() {

    }
}
