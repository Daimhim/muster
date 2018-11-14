package org.daimhim.pluginmanager.ui.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.base
 * 项目版本：muster
 * 创建时间：2018/11/14 15:42  星期三
 * 创建人：Administrator
 * 修改时间：2018/11/14 15:42  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class BaseViewModel extends ViewModel {
    private CompositeDisposable mDisposables;
    public BaseViewModel() {

    }

    @Override
    protected void onCleared() {
        if (mDisposables != null && mDisposables.isDisposed() && mDisposables.size() > 0) {
            mDisposables.clear();
        }
        mDisposables = null;
    }

    public void addSubscription(Disposable subscribe) {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(subscribe);
    }
}
