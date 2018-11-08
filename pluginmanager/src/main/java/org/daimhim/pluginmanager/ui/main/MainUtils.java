package org.daimhim.pluginmanager.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 项目名称：org.daimhim.pluginmanager
 * 项目版本：muster
 * 创建时间：2018/10/22 14:34  星期一
 * 创建人：Administrator
 * 修改时间：2018/10/22 14:34  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class MainUtils {
    public static final int FLAG_BEFORE_HIDE = 0x01100000;
    private String TAG = getClass().getSimpleName();
    private FragmentManager mSupportFragmentManager;
    private SparseArray<FragmentStackManager> mFragmentStackManagerSparseArray;

    private MainUtils() {
        mFragmentStackManagerSparseArray = new SparseArray<>();
    }

    public static MainUtils getI() {
        return MainUtils.SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final MainUtils sInstance = new MainUtils();
    }

    private Context mContext = null;

    public void init(Context pContext, int rId) {
        mContext = pContext;
        if (pContext instanceof AppCompatActivity) {
            mSupportFragmentManager = ((AppCompatActivity) pContext).getSupportFragmentManager();
            mFragmentStackManagerSparseArray.put(mFragmentStackManagerSparseArray.size(),
                    new FragmentStackManager(mSupportFragmentManager, rId));
        } else {
            throw new IllegalStateException("pContext not equals AppCompatActivity");
        }
    }

    public void startFragment(Intent intent) {
        starFragmentForResult(intent, -1);
    }


    public void starFragmentForResult(Intent intent, int requestCode) {
        ComponentName lComponent = intent.getComponent();
        try {
            Class<?> lClass = Class.forName(lComponent.getClassName());
            Fragment lFragment = (Fragment) lClass.newInstance();
            Bundle lExtras = intent.getExtras();
            if (null == lExtras) {
                lExtras = new Bundle();
            }
            lExtras.putInt("requestCode", requestCode);
            lFragment.setArguments(lExtras);
            boolean isHide = false;
            int lFlags = intent.getFlags();
            switch (lFlags) {
                case FLAG_BEFORE_HIDE:
                    isHide = true;
                    break;
                default:
                    break;
            }
            getFragmentStackManager().addToStack(lFragment,isHide);
        } catch (ClassNotFoundException pE) {
            pE.printStackTrace();
        } catch (IllegalAccessException pE) {
            pE.printStackTrace();
        } catch (InstantiationException pE) {
            pE.printStackTrace();
        } catch (ClassCastException pE) {
            pE.printStackTrace();
        } catch (NullPointerException pE) {
            pE.printStackTrace();
        }
    }

    /**
     * get Top Stack Count
     *
     * @return int
     */
    public int getTopStackCount() {
        return getFragmentStackManager().getConut();
    }

    public void finishFragment(Fragment pFragment) {
        finishFragment(pFragment, -1);
    }

    public void finishFragment(Fragment pFragment, int resultCode) {
        finishFragment(pFragment, resultCode, new Intent());
    }

    /**
     * close current Fragment
     *
     * @param pFragment  current Fragment
     * @param resultCode response Code
     * @param pIntent    Value
     */
    public void finishFragment(Fragment pFragment, int resultCode, Intent pIntent) {
        Bundle lArguments = pFragment.getArguments();
        int requestCode = -1;
        if (lArguments != null) {
            requestCode = lArguments.getInt("requestCode");
        }
        getFragmentStackManager().remove(pFragment);
        if (requestCode != -1) {
            getStackAndTopFragment().onActivityResult(requestCode, resultCode, pIntent);
            getStackAndTopFragment().onStart();
            getStackAndTopFragment().onResume();
        }
    }

    /**
     * @param pFragmentStackManager
     * @return
     */
    public int getStackCount(FragmentStackManager pFragmentStackManager) {
        for (int i = 0; i < getStackCount(); i++) {
            if (mFragmentStackManagerSparseArray.get(i) == pFragmentStackManager) {
                return mFragmentStackManagerSparseArray.get(i).getConut();
            }
        }
        return 0;
    }

    public FragmentStackManager getFragmentStackManager() {
        return mFragmentStackManagerSparseArray.get(mFragmentStackManagerSparseArray.size() - 1);
    }

    public int getStackCount() {
        return mFragmentStackManagerSparseArray.size();
    }

    public Fragment getStackAndTopFragment() {
        return getFragmentStackManager().getTopFragment();
    }

    public static void hideKeyboard(Context context) {
        if (context instanceof AppCompatActivity) {
            InputMethodManager lSystemService = (InputMethodManager) ((MainActivity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null != lSystemService) {
                View lCurrentFocus = ((MainActivity) context).getCurrentFocus();
                if (null != lCurrentFocus) {
                    lSystemService.hideSoftInputFromWindow(lCurrentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
    }

    public static void upTitleAndIco(Context pContext, String title, int rId, View.OnClickListener pClickListener) {
        if (pContext instanceof MainActivity) {
            ((MainActivity) pContext).upTitle(title);
            ((MainActivity) pContext).upTitleLiftIco(rId, pClickListener);
        }
    }

    public static void showUserInfo(Context pContext) {
        if (pContext instanceof MainActivity) {
            ((MainActivity) pContext).showUserInfo();
        }
    }
}
