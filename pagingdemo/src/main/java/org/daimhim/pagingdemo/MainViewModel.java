package org.daimhim.pagingdemo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * 项目名称：org.daimhim.pagingdemo
 * 项目版本：muster
 * 创建时间：2018.08.31 10:00  星期五
 * 创建人：Daimhim
 * 修改时间：2018.08.31 10:00  星期五
 * 类描述：Daimhim 太懒了，什么都没有留下
 * 修改备注：Daimhim 太懒了，什么都没有留下
 *
 * @author：Daimhim
 */
public class MainViewModel extends ViewModel {

    // Create a LiveData with a String
    private MutableLiveData<UserBean> mCurrentUser;

    public MutableLiveData<UserBean> getCurrentName() {
        if (mCurrentUser == null) {
            mCurrentUser = new MutableLiveData<>();
        }
        return mCurrentUser;
    }

// Rest of the ViewModel...
}
