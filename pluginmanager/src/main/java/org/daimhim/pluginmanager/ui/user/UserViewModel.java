package org.daimhim.pluginmanager.ui.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import org.daimhim.distance.RetrofitManager;
import org.daimhim.pluginmanager.model.bean.UserBean;
import org.daimhim.pluginmanager.model.UserHelp;
import org.daimhim.pluginmanager.model.request.User;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.utils.StringUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.user
 * 项目版本：muster
 * 创建时间：2018/10/19 09:44  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 09:44  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class UserViewModel extends ViewModel {
    public static int SUCCESS = 1;
    public static int FAILURE = 0;

    MutableLiveData<Integer> mIntegerMutableLiveData;
    private User mUser;

    public UserViewModel() {
        mIntegerMutableLiveData = new MutableLiveData<>();
        mUser = RetrofitManager.getInstance().getRetrofit().create(User.class);
    }

    public LiveData<Integer> userLogin(String userName, String userPassWord) {
        mUser.userLogin(userName, userPassWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JavaResponse<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable pDisposable) {

                    }

                    @Override
                    public void onNext(JavaResponse<UserBean> pJavaResponse) {
                        if (StringUtils.equals(pJavaResponse.getError_code(), "0")) {
                            mIntegerMutableLiveData.setValue(FAILURE);
                        } else {
                            mIntegerMutableLiveData.setValue(SUCCESS);
                            UserBean lResult = pJavaResponse.getResult();
                            UserHelp.getInstance().upUserInfo(lResult);
                        }

                    }

                    @Override
                    public void onError(Throwable pThrowable) {
                        mIntegerMutableLiveData.setValue(FAILURE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mIntegerMutableLiveData;
    }

    public LiveData<Integer> userRegister(String userName, String userPassWord) {
        mUser.userRegistered(userName, userPassWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JavaResponse>() {
                    @Override
                    public void onSubscribe(Disposable pDisposable) {

                    }

                    @Override
                    public void onNext(JavaResponse pJavaResponse) {
                        if (StringUtils.equals(pJavaResponse.getError_code(), "0")) {
                            mIntegerMutableLiveData.setValue(FAILURE);
                        } else {
                            mIntegerMutableLiveData.setValue(SUCCESS);
                        }

                    }

                    @Override
                    public void onError(Throwable pThrowable) {
                        mIntegerMutableLiveData.setValue(FAILURE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mIntegerMutableLiveData;
    }
}
