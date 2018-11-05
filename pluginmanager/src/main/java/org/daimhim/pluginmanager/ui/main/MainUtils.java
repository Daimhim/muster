package org.daimhim.pluginmanager.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.ui.plugin.PluginListFragment;
import org.daimhim.pluginmanager.utils.CacheFileUtils;

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

    private FragmentManager mSupportFragmentManager;

    private MainUtils() {

    }

    public static MainUtils getI() {
        return MainUtils.SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final MainUtils sInstance = new MainUtils();
    }
    private Context mContext = null;
    private FragmentStackManager mFragmentStackManager;
    public void init(Context pContext,int rId){
        mContext = pContext;
        if (pContext instanceof AppCompatActivity) {
            mSupportFragmentManager = ((AppCompatActivity) pContext).getSupportFragmentManager();
            mFragmentStackManager = new FragmentStackManager(mSupportFragmentManager,rId);
        }else {
            throw new IllegalStateException("pContext not equals AppCompatActivity");
        }
    }

    public void startFragment(Intent intent){
        ComponentName lComponent = intent.getComponent();
//        lComponent.getPackageName() + lComponent.getClassName();

    }

    public void back(){
        mFragmentStackManager.popBackStack();

    }


    public static void startFragment(Context pContext, Class pFragment) {
        if (pContext instanceof MainActivity) {
            try {
                hideKeyboard(pContext);
                ((MainActivity) pContext).replaceFragment((Fragment) pFragment.newInstance());
            } catch (IllegalAccessException pE) {
                pE.printStackTrace();
            } catch (InstantiationException pE) {
                pE.printStackTrace();
            }
        }
    }
    public static void startFragment(Context pContext, Class pFragment, Bundle args) {
        if (pContext instanceof MainActivity) {
            try {
                hideKeyboard(pContext);
                Fragment lFragment = (Fragment) pFragment.newInstance();
                lFragment.setArguments(args);
                ((MainActivity) pContext).replaceFragment(lFragment);
            } catch (IllegalAccessException pE) {
                pE.printStackTrace();
            } catch (InstantiationException pE) {
                pE.printStackTrace();
            }
        }
    }

    public static void superimposedFragment(Context pContext, Class pFragment) {
        if (pContext instanceof MainActivity) {
            try {
                hideKeyboard(pContext);
                ((MainActivity) pContext).superimposedFragment((Fragment) pFragment.newInstance());
            } catch (IllegalAccessException pE) {
                pE.printStackTrace();
            } catch (InstantiationException pE) {
                pE.printStackTrace();
            }
        }
    }
    public static void superimposedFragment(Context pContext, Class pFragment,Bundle args) {
        if (pContext instanceof MainActivity) {
            try {
                hideKeyboard(pContext);
                Fragment lFragment = (Fragment) pFragment.newInstance();
                lFragment.setArguments(args);
                ((MainActivity) pContext).superimposedFragment(lFragment);
            } catch (IllegalAccessException pE) {
                pE.printStackTrace();
            } catch (InstantiationException pE) {
                pE.printStackTrace();
            }
        }
    }
    public static void superimposedFragment(Context pContext, int requestCode, Class pFragment) {
        if (pContext instanceof MainActivity) {
            try {
                hideKeyboard(pContext);
                Fragment lFragment = (Fragment) pFragment.newInstance();
                Bundle lArgs = new Bundle();
                lArgs.putInt("requestCode",requestCode);
                lFragment.setArguments(lArgs);
                ((MainActivity) pContext).superimposedFragment(lFragment);
            } catch (IllegalAccessException pE) {
                pE.printStackTrace();
            } catch (InstantiationException pE) {
                pE.printStackTrace();
            }
        }
    }
    public static void superimposedFragment(Context pContext,int requestCode, Class pFragment,Bundle args) {
        if (pContext instanceof MainActivity) {
            try {
                hideKeyboard(pContext);
                Fragment lFragment = (Fragment) pFragment.newInstance();
                Bundle lArgs = new Bundle();
                lFragment.setArguments(args);
                lArgs.putInt("requestCode",requestCode);
                lFragment.setArguments(lArgs);
                ((MainActivity) pContext).superimposedFragment(lFragment);
            } catch (IllegalAccessException pE) {
                pE.printStackTrace();
            } catch (InstantiationException pE) {
                pE.printStackTrace();
            }
        }
    }
    public static void backFragment(Context pContext){
        if (pContext instanceof MainActivity) {
            hideKeyboard(pContext);
            Fragment lFragmentById = null;
            lFragmentById = ((MainActivity) pContext)
                    .getSupportFragmentManager()
                    .findFragmentById(R.id.content_main);
            int lRequestCode = -1;
            if (lFragmentById !=null && lFragmentById.getArguments() != null){
                lRequestCode = lFragmentById.getArguments().getInt("requestCode");
            }
            ((MainActivity) pContext).backFragment();
            lFragmentById = ((MainActivity) pContext)
                    .getSupportFragmentManager()
                    .findFragmentById(R.id.content_main);
            if (lFragmentById != null) {
                lFragmentById.setUserVisibleHint(true);
                lFragmentById.onActivityResult(lRequestCode,-1,null);
            }
        }
    }
    public static void backFragment(Context pContext,Bundle pBundle){
        if (pContext instanceof MainActivity) {
            hideKeyboard(pContext);
            Fragment lFragmentById = null;
            lFragmentById = ((MainActivity) pContext)
                    .getSupportFragmentManager()
                    .findFragmentById(R.id.content_main);
            int lRequestCode = -1;
            if (lFragmentById !=null && lFragmentById.getArguments() != null){
                lRequestCode = lFragmentById.getArguments().getInt("requestCode");
            }

            ((MainActivity) pContext).backFragment();
            lFragmentById = ((MainActivity) pContext)
                    .getSupportFragmentManager()
                    .findFragmentById(R.id.content_main);
            if (lFragmentById != null) {
                if (lFragmentById.getArguments() != null){
                    lFragmentById.getArguments().putAll(pBundle);
                }else {
                    lFragmentById.setArguments(pBundle);
                }
                lFragmentById.setUserVisibleHint(true);
                if (lRequestCode!=-1){
                    lFragmentById.onActivityResult(lRequestCode,-1,new Intent().putExtras(pBundle));
                }
            }
        }
    }

    public static void hideKeyboard(Context context){
        if (context instanceof AppCompatActivity) {
            InputMethodManager lSystemService = (InputMethodManager) ((MainActivity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
            if (null!=lSystemService) {
                View lCurrentFocus = ((MainActivity) context).getCurrentFocus();
                if (null!=lCurrentFocus) {
                    lSystemService.hideSoftInputFromWindow(lCurrentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
    }

    public static void upTitleAndIco(Context pContext, String title, int rId, View.OnClickListener pClickListener){
        if (pContext instanceof MainActivity) {
            ((MainActivity) pContext).upTitle(title);
            ((MainActivity) pContext).upTitleLiftIco(rId,pClickListener);
        }
    }
    public static void showUserInfo(Context pContext){
        if (pContext instanceof MainActivity) {
            ((MainActivity) pContext).showUserInfo();
        }
    }
}
