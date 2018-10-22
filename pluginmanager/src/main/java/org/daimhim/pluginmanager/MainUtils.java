package org.daimhim.pluginmanager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

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
    public static void startFragment(Context pContext, Class pFragment) {
        if (pContext instanceof MainActivity) {
            try {
                ((MainActivity) pContext).replaceFragment((Fragment) pFragment.newInstance());
            } catch (IllegalAccessException pE) {
                pE.printStackTrace();
            } catch (InstantiationException pE) {
                pE.printStackTrace();
            }
        }
    }
}
