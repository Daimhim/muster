package org.daimhim.pluginmanager.ui.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;

import org.daimhim.pluginmanager.BuildConfig;

import java.util.ArrayList;

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
    private String TAG = getClass().getSimpleName();
    private ArrayList<Fragment> mFragmentSparseArray;
    private final FragmentManager mSupportFragmentManager;
    private int mRId;

    public FragmentStackManager(FragmentManager pFragmentManager, int rId) {
        mFragmentSparseArray = new ArrayList<>();
        mSupportFragmentManager = pFragmentManager;
        mRId = rId;
    }

    public void addToStack(Fragment pFragment,boolean isHide){
        FragmentTransaction lFragmentTransaction = mSupportFragmentManager.beginTransaction();
        Fragment lTopFragment = getTopFragment();
        lFragmentTransaction.add(mRId,pFragment);
        if (null != lTopFragment) {
            lFragmentTransaction.hide(lTopFragment);
            lTopFragment.setUserVisibleHint(false);
            if (isHide) {
                lFragmentTransaction.hide(lTopFragment);
            }
        }
        pFragment.setUserVisibleHint(true);
        lFragmentTransaction.show(pFragment);
        lFragmentTransaction.commit();
        if (null != lTopFragment) {
            lTopFragment.onPause();
            lTopFragment.onStop();
        }
        mFragmentSparseArray.add(pFragment);
        if (BuildConfig.DEBUG){
            Log.d(TAG,"addToStack:"+mFragmentSparseArray.toString());
        }
    }
    public void popBackStack(){
        Fragment lTopFragment = getTopFragment();
        if (null != lTopFragment) {
            FragmentTransaction lFragmentTransaction = mSupportFragmentManager.beginTransaction();
            lFragmentTransaction.hide(lTopFragment);
            lFragmentTransaction.remove(lTopFragment);
            mFragmentSparseArray.remove(mFragmentSparseArray.size());
            lTopFragment = getTopFragment();
            lFragmentTransaction.show(lTopFragment);
            lFragmentTransaction.commit();
        }
    }
    public void restart(int requestCode,int resultCode, Intent pIntent){
        Fragment lTopFragment = getTopFragment();
        if (null != lTopFragment) {
            FragmentTransaction lFragmentTransaction = mSupportFragmentManager.beginTransaction();
            lFragmentTransaction.show(lTopFragment);
            lFragmentTransaction.commit();
            if (requestCode != -1) {
                lTopFragment.onActivityResult(requestCode, resultCode, pIntent);
            }
            lTopFragment.onStart();
            lTopFragment.onResume();
        }
    }
    public void remove(Fragment pFragment){
        FragmentTransaction lFragmentTransaction = mSupportFragmentManager.beginTransaction();
        if (pFragment.getUserVisibleHint()){
            lFragmentTransaction.hide(pFragment);
        }
        lFragmentTransaction.remove(pFragment);
        lFragmentTransaction.commit();
        if (BuildConfig.DEBUG){
            Log.d(TAG,"remove:"+mFragmentSparseArray.toString());
        }
        mFragmentSparseArray.remove(pFragment);
        if (BuildConfig.DEBUG){
            Log.d(TAG,"remove:"+mFragmentSparseArray.toString());
        }
    }

    public Fragment  getTopFragment(){
        return mFragmentSparseArray.isEmpty() ? null : mFragmentSparseArray.get(mFragmentSparseArray.size()-1);
    }

    public Fragment getLowFragment(){
        return mFragmentSparseArray.get(0);
    }

    public int getConut(){
        return mFragmentSparseArray.size();
    }
}
