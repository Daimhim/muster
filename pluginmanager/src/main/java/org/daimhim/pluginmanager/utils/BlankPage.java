package org.daimhim.pluginmanager.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.daimhim.pluginmanager.R;

/**
 * 项目名称：org.daimhim.pluginmanager.utils
 * 项目版本：muster
 * 创建时间：2018/11/13 15:06  星期二
 * 创建人：Administrator
 * 修改时间：2018/11/13 15:06  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class BlankPage {
    public static View newBlankPage(Context pContext, String text){
        TextView lTextView = new TextView(pContext);
        RelativeLayout.LayoutParams lLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lTextView.setLayoutParams(lLayoutParams);
        lTextView.setGravity(Gravity.CENTER);
        lTextView.setText(text);
        lTextView.setTextColor(ContextCompat.getColor(pContext, R.color.cl_666666));
        return lTextView;
    }
}
