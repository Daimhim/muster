package org.daimhim.pluginmanager.ui.base;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.Objects;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.base
 * 项目版本：muster
 * 创建时间：2018/10/29 16:14  星期一
 * 创建人：Administrator
 * 修改时间：2018/10/29 16:14  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class BaseFragment extends Fragment implements BackHandledInterface{
    protected final String TAG = getClass().getSimpleName();
    @Override
    public boolean onBackPressed() {
        return false;
    }
}
