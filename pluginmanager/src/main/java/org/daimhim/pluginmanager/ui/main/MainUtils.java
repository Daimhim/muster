package org.daimhim.pluginmanager.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.daimhim.pluginmanager.R;

import java.util.ArrayList;

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
//    Intent.FLAG_GRANT_READ_URI_PERMISSION,
//    Intent.FLAG_GRANT_WRITE_URI_PERMISSION,
//    Intent.FLAG_FROM_BACKGROUND,
//    Intent.FLAG_DEBUG_LOG_RESOLUTION,
//    Intent.FLAG_EXCLUDE_STOPPED_PACKAGES,
//    Intent.FLAG_INCLUDE_STOPPED_PACKAGES,
//    Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION,
//    Intent.FLAG_GRANT_PREFIX_URI_PERMISSION,
//    Intent.FLAG_ACTIVITY_NO_HISTORY,
//    Intent.FLAG_ACTIVITY_SINGLE_TOP,
//    Intent.FLAG_ACTIVITY_NEW_TASK,
//    Intent.FLAG_ACTIVITY_MULTIPLE_TASK,
//    Intent.FLAG_ACTIVITY_CLEAR_TOP,
//    Intent.FLAG_ACTIVITY_FORWARD_RESULT,
//    Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP,
//    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS,
//    Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT,
//    Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED,
//    Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY,
//    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET,
//    Intent.FLAG_ACTIVITY_NEW_DOCUMENT,
//    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET,
//    Intent.FLAG_ACTIVITY_NO_USER_ACTION,
//    Intent.FLAG_ACTIVITY_REORDER_TO_FRONT,
//    Intent.FLAG_ACTIVITY_NO_ANIMATION,
//    Intent.FLAG_ACTIVITY_CLEAR_TASK,
//    Intent.FLAG_ACTIVITY_TASK_ON_HOME,
//    Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS,
//    Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT,
//    Intent.FLAG_RECEIVER_REGISTERED_ONLY,
//    Intent.FLAG_RECEIVER_REPLACE_PENDING,
//    Intent.FLAG_RECEIVER_FOREGROUND,
//    Intent.FLAG_RECEIVER_NO_ABORT,
//    Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS

//    public static final int FLAG_ACTIVITY_CLEAR_TASK = 0X00008000;
//    public static final int FLAG_ACTIVITY_SINGLE_TOP = 0x20000000;
//    public static final int FLAG_ACTIVITY_NEW_TASK = 0x10000000;
//    public static final int FLAG_ACTIVITY_NO_HISTORY = 0x40000000;
//    public static final int FLAG_ACTIVITY_MULTIPLE_TASK = 0x08000000;
//    public static final int FLAG_ACTIVITY_CLEAR_TOP = 0x04000000;
//    public static final int FLAG_ACTIVITY_FORWARD_RESULT = 0x02000000;
//    public static final int FLAG_ACTIVITY_PREVIOUS_IS_TOP = 0x01000000;
//    public static final int FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS = 0x00800000;
//    public static final int FLAG_ACTIVITY_BROUGHT_TO_FRONT = 0x00400000;
//    public static final int FLAG_ACTIVITY_RESET_TASK_IF_NEEDED = 0x00200000;
//    public static final int FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY = 0x00100000;
//    public static final int FLAG_ACTIVITY_NEW_DOCUMENT = 0x00080000;
//    public static final int FLAG_ACTIVITY_REORDER_TO_FRONT = 0X00020000;
//    public static final int FLAG_ACTIVITY_NO_ANIMATION = 0X00010000;
//    public static final int FLAG_ACTIVITY_TASK_ON_HOME = 0X00004000;
//    public static final int FLAG_ACTIVITY_RETAIN_IN_RECENTS = 0x00002000;
//    public static final int FLAG_ACTIVITY_LAUNCH_ADJACENT = 0x00001000;
//    public static final int FLAG_ACTIVITY_MATCH_EXTERNAL = 0x00000800;
    // whether hide Previous
//    public static final int FLAG_ACTIVITY_NO_USER_ACTION = 0x00040000;
    /**
     * spare
     * public static final int FLAG_RECEIVER_REGISTERED_ONLY = 0x40000000;
     * public static final int FLAG_RECEIVER_REPLACE_PENDING = 0x20000000;
     * public static final int FLAG_RECEIVER_FOREGROUND = 0x10000000;
     * public static final int FLAG_RECEIVER_NO_ABORT = 0x08000000;
     * public static final int FLAG_RECEIVER_REGISTERED_ONLY_BEFORE_BOOT = 0x04000000;
     * public static final int FLAG_RECEIVER_BOOT_UPGRADE = 0x02000000;
     * public static final int FLAG_RECEIVER_INCLUDE_BACKGROUND = 0x01000000;
     * public static final int FLAG_RECEIVER_EXCLUDE_BACKGROUND = 0x00800000;
     * public static final int FLAG_RECEIVER_FROM_SHELL = 0x00400000;
     * public static final int FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS = 0x00200000;
     * public static final int FLAG_GRANT_READ_URI_PERMISSION = 0x00000001;
     * public static final int FLAG_GRANT_WRITE_URI_PERMISSION = 0x00000002;
     * public static final int FLAG_FROM_BACKGROUND = 0x00000004;
     * public static final int FLAG_DEBUG_LOG_RESOLUTION = 0x00000008;
     * public static final int FLAG_EXCLUDE_STOPPED_PACKAGES = 0x00000010;
     * public static final int FLAG_INCLUDE_STOPPED_PACKAGES = 0x00000020;
     * public static final int FLAG_GRANT_PERSISTABLE_URI_PERMISSION = 0x00000040;
     * public static final int FLAG_GRANT_PREFIX_URI_PERMISSION = 0x00000080;
     * public static final int FLAG_DEBUG_TRIAGED_MISSING = 0x00000100;
     * public static final int FLAG_IGNORE_EPHEMERAL = 0x00000200;
     **/
    private String TAG = getClass().getSimpleName();
    private FragmentManager mSupportFragmentManager;
    private ArrayList<FragmentStackManager> mFragmentStackManagerSparseArray;

    private MainUtils() {
        mFragmentStackManagerSparseArray = new ArrayList<>();
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
            mFragmentStackManagerSparseArray.add(new FragmentStackManager(mSupportFragmentManager, rId));
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
            boolean isHide = true;
            int lFlags = intent.getFlags();
            switch (lFlags) {
                case Intent.FLAG_ACTIVITY_NO_USER_ACTION:
                    isHide = false;
                    break;
                default:
                    break;
            }
            getFragmentStackManager().addToStack(lFragment, isHide);
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
        //If it is not at the top, don't restart
        boolean isRestart = getStackAndTopFragment() == pFragment;
        getFragmentStackManager().remove(pFragment);
        if (isRestart) {
            getFragmentStackManager().restart(requestCode, resultCode, pIntent);
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
        return mFragmentStackManagerSparseArray.get(getStackCount()-1);
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

    public void toast(String text){
        if (null == mContext){
            System.out.println(text);
        }
        if (mContext instanceof AppCompatActivity){
            Snackbar.make(((AppCompatActivity) mContext).findViewById(android.R.id.content),text,Snackbar.LENGTH_SHORT).show();
        }
    }
}
