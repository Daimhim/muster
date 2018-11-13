package org.daimhim.pluginmanager.ui.base;

import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.utils.BlankPage;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.base
 * 项目版本：muster
 * 创建时间：2018/11/13 17:33  星期二
 * 创建人：Administrator
 * 修改时间：2018/11/13 17:33  星期二
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public abstract class BaseAdapter<V extends RecyclerViewEmpty.ClickViewHolder,T,T1> extends RecyclerViewEmpty<V>
implements RecyclerContract.SimpleContract<ArrayList<T1>,T1> {
    private ArrayList<T1> mT1ArrayList;

    public BaseAdapter() {
        mT1ArrayList = new ArrayList<>();
    }

    @Override
    public V onCreateDataViewHolder(ViewGroup pViewGroup, int pI) {
        Type[] lGenericInterfaces = this.getClass().getGenericInterfaces();
        Type[] lActualTypeArguments = ((ParameterizedType) lGenericInterfaces[0]).getActualTypeArguments();
        return null;
    }

    public abstract int getView();

    @Override
    public int getDataItemCount() {
        return mT1ArrayList.size();
    }

    @Override
    public ClickViewHolder onCreateEmptyViewHolder(ViewGroup parent, int viewType) {
        return new ClickViewHolder(BlankPage.newBlankPage(parent.getContext(),"no data"));
    }

    @Override
    public int getEmptyViewType() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isEmptyView() {
        return getDataItemCount()==0;
    }

    @Override
    public void onRefresh(ArrayList<T1> pT1s) {
        mT1ArrayList.clear();
        mT1ArrayList.addAll(pT1s);
        notifyDataSetChanged();
    }

    @Override
    public T1 getItem(int pI) {
        return mT1ArrayList.get(pI);
    }

    @Override
    public void onLoad(ArrayList<T1> pT1s) {
        mT1ArrayList.addAll(pT1s);
        notifyItemRangeInserted(getDataItemCount()-1,pT1s.size());
    }
}
