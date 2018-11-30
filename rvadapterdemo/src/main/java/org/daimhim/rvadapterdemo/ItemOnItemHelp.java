package org.daimhim.rvadapterdemo;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;

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
    private String Tag = getClass().getSimpleName();
//    private RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    GestureDetectorCompat lGestureDetectorCompat = null;



    public ItemOnItemHelp() {
    }

    public void setOnItemClickListener(OnItemClickListener pOnItemClickListener) {
        mOnItemClickListener = pOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener pOnItemLongClickListener) {
        mOnItemLongClickListener = pOnItemLongClickListener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView pRecyclerView, @NonNull MotionEvent pMotionEvent) {
        View lChildViewUnder = pRecyclerView.findChildViewUnder(pMotionEvent.getX(), pMotionEvent.getY());
        RecyclerView.ViewHolder lContainingViewHolder = pRecyclerView.findContainingViewHolder(lChildViewUnder);
        int lLayoutPosition = lContainingViewHolder.getLayoutPosition();
        Log.i(Tag,"lLayoutPosition:"+lLayoutPosition);
        if (lChildViewUnder.isClickable()){
            return true;
        }
        if (lChildViewUnder instanceof ViewGroup){
            ViewGroup lViewGroup = (ViewGroup) lChildViewUnder;
            if (lViewGroup.isClickable()){

            }
            View lChildAt = null;
            for (int i = 0; i < lViewGroup.getChildCount(); i++) {
                lChildAt = lViewGroup.getChildAt(i);
                boolean lVictim = findVictim(lChildAt, pMotionEvent.getX(), pMotionEvent.getY());
                if (lVictim){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView pRecyclerView, @NonNull MotionEvent pMotionEvent) {
        Log.i(Tag,"onTouchEvent:");
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean pB) {
        Log.i(Tag,"onRequestDisallowInterceptTouchEvent:"+pB);
    }

    private boolean findVictim(View pView,float x,float y){
        Rect r = new Rect();
        pView.getLocalVisibleRect(r);
        if (x > r.left && x < r.right && y > r.top && y < r.bottom) {
            return true;
        }
        return false;
    }

    interface OnItemClickListener {
        void onItemClick(RecyclerView recyclerView, View view, int position);
    }

    interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView recyclerView, View view, int position);
    }
}
