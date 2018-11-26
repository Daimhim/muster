package org.daimhim.pluginmanager.viewhelp;

import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/**
 * 项目名称：org.daimhim.pluginmanager.viewhelp
 * 项目版本：muster
 * 创建时间：2018/11/26 17:10  星期一
 * 创建人：Administrator
 * 修改时间：2018/11/26 17:10  星期一
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class ItemOnItemHelp implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    GestureDetectorCompat lGestureDetectorCompat = null;


    public ItemOnItemHelp(RecyclerView pRecyclerView) {
        mRecyclerView = pRecyclerView;
        mRecyclerView.addOnItemTouchListener(this);
    }

    public void setOnItemClickListener(OnItemClickListener pOnItemClickListener) {
        mOnItemClickListener = pOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener pOnItemLongClickListener) {
        mOnItemLongClickListener = pOnItemLongClickListener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView pRecyclerView, @NonNull MotionEvent pMotionEvent) {
        View lChildViewUnder = mRecyclerView.findChildViewUnder(pMotionEvent.getX(), pMotionEvent.getY());

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView pRecyclerView, @NonNull MotionEvent pMotionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean pB) {

    }

    private boolean findVictim(View pView,int x,int y){
        return false;
    }

    interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int position);
    }

    interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView recyclerView, View view, int position);
    }
}
