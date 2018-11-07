package org.daimhim.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.main
 * 项目版本：muster
 * 创建时间：2018/11/5 19:23  星期一
 * 创建人：Administrator
 * 修改时间：2018/11/5 19:23  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class FragmentStackManager {
    private SparseArray<Fragment> mFragmentSparseArray;
    private final FragmentManager mSupportFragmentManager;
    private int mRId;

    public FragmentStackManager(FragmentManager pFragmentManager, int rId) {
        mFragmentSparseArray = new SparseArray<>();
        mSupportFragmentManager = pFragmentManager;
        mRId = rId;
    }

    public void addToStack(Fragment pFragment){
        FragmentTransaction lFragmentTransaction = mSupportFragmentManager.beginTransaction();
        Fragment lTopFragment = getTopFragment();
        lFragmentTransaction.add(mRId,pFragment);
        if (null != lTopFragment) {
            lFragmentTransaction.hide(lTopFragment);
            lTopFragment.setUserVisibleHint(false);
        }
        pFragment.setUserVisibleHint(true);
        lFragmentTransaction.show(pFragment);
        lFragmentTransaction.commit();
        if (null != lTopFragment) {
            lTopFragment.onPause();
            lTopFragment.onStop();
        }
        mFragmentSparseArray.put(mFragmentSparseArray.size(),pFragment);
    }
    public void popBackStack(){
        Fragment lTopFragment = getTopFragment();
        if (null != lTopFragment) {
            FragmentTransaction lFragmentTransaction = mSupportFragmentManager.beginTransaction();
            lFragmentTransaction.hide(lTopFragment);
            lTopFragment.onPause();
            lFragmentTransaction.remove(lTopFragment);
            mFragmentSparseArray.remove(mFragmentSparseArray.size() - 1);
            lTopFragment = getTopFragment();
            lFragmentTransaction.show(lTopFragment);
            lFragmentTransaction.commit();
        }

    }

    public Fragment getTopFragment(){
        return mFragmentSparseArray.get(mFragmentSparseArray.size()-1);
    }

    public Fragment getLowFragment(){
        return mFragmentSparseArray.get(0);
    }

    public int getConut(){
        return mFragmentSparseArray.size();
    }


}
