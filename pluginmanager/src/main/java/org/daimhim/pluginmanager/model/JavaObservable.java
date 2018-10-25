package org.daimhim.pluginmanager.model;

import org.daimhim.pluginmanager.model.response.JavaResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * 项目名称：org.daimhim.pluginmanager.model
 * 项目版本：muster
 * 创建时间：2018/10/23 11:54  星期二
 * 创建人：Administrator
 * 修改时间：2018/10/23 11:54  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class JavaObservable<T> extends Observable<JavaResponse<T>> {

    @Override
    protected void subscribeActual(Observer<? super JavaResponse<T>> observer) {

    }
}
