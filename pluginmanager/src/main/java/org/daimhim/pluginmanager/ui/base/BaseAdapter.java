package org.daimhim.pluginmanager.ui.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.utils.BlankPage;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
public abstract class BaseAdapter<V extends RecyclerViewEmpty.ClickViewHolder<T1>, T, T1> extends RecyclerViewEmpty<V>
        implements RecyclerContract.SimpleContract<List<T>, T> {
    private List<T> mT1ArrayList;

    public BaseAdapter() {
        mT1ArrayList = new ArrayList<>();
    }

    @Override
    public V onCreateDataViewHolder(ViewGroup pViewGroup, int pI) {
        V v = getViewHolder(pViewGroup, pI);
        if (v == null){
            throw new NullPointerException("V can not be empty");
        }
        return v;
    }
    private V getViewHolder(ViewGroup pViewGroup, int pI){
        return create(preview(getClass()), LayoutInflater.from(pViewGroup.getContext()).inflate(getView(), pViewGroup, false));
    }
    private String preview(Class<? extends BaseAdapter> lClass) {
        Type genericSuperclass = lClass.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            return actualTypeArguments[0].getTypeName();
        }
        return null;
    }

    private  <B> B create(String classPath, Object... pObjects) {
        if (classPath == null || "".equals(classPath)){
            return null;
        }
        try {
            Class<B> clazz = (Class<B>) Class.forName(classPath);
            if (pObjects.length != 0) {
                Class<?>[] params = new Class[pObjects.length];
                for (int i = 0; i < params.length; i++) {
                    params[i] = pObjects[i].getClass();
                }
                return clazz.getDeclaredConstructor(params).newInstance(pObjects);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException pE) {
            pE.printStackTrace();
        } catch (InvocationTargetException pE) {
            pE.printStackTrace();
        } catch (NoSuchMethodException pE) {
            pE.printStackTrace();
        } catch (ClassNotFoundException pE) {
            pE.printStackTrace();
        } catch (ClassCastException pE){
            pE.printStackTrace();
        }
        return null;
    }

    public abstract int getView();

    @Override
    public int getDataItemCount() {
        return mT1ArrayList.size();
    }

    @Override
    public ClickViewHolder onCreateEmptyViewHolder(ViewGroup parent, int viewType) {
        return new ClickViewHolder(BlankPage.newBlankPage(parent.getContext(), "no data"));
    }

    @Override
    public int getEmptyViewType() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isEmptyView() {
        return getDataItemCount() == 0;
    }

    @Override
    public void onRefresh(List<T> pT1s) {
        mT1ArrayList.clear();
        mT1ArrayList.addAll(pT1s);
        notifyDataSetChanged();
    }

    @Override
    public T getItem(int pI) {
        return mT1ArrayList.get(pI);
    }

    @Override
    public void onLoad(List<T> pT1s) {
        mT1ArrayList.addAll(pT1s);
        notifyItemRangeInserted(getDataItemCount() - 1, pT1s.size());
    }
}
