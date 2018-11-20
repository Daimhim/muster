package org.daimhim.pluginmanager.ui.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.utils.BlankPage;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

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
        if (v == null) {
            throw new NullPointerException("V can not be empty");
        }
        return v;
    }

    private V getViewHolder(ViewGroup pViewGroup, int pI) {
        View lInflate = LayoutInflater.from(pViewGroup.getContext()).inflate(getView(), pViewGroup, false);
        return create(preview(getClass()), lInflate);
    }

    private String preview(Class<? extends BaseAdapter> lClass) {
        Type genericSuperclass = lClass.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            Class<V> lActualTypeArgument = (Class<V>) actualTypeArguments[0];
            Timber.i(lActualTypeArgument.getName());
            return lActualTypeArgument.getName();
        }
        return null;
    }

    public <B> B create(String classPath, View pView) {
        if (classPath == null || "".equals(classPath)) {
            return null;
        }
        if (classPath.contains("$")) {
            return createInternal(classPath, pView);
        }
        try {
            return (B) create(Class.forName(classPath), pView);
        } catch (ClassNotFoundException pE) {
            pE.printStackTrace();
        } catch (ClassCastException pE) {
            pE.printStackTrace();
        }
        return null;
    }

    public <B> B create(Class<B> clazz, View pView) {
        try {
            Constructor<B> lDeclaredConstructor = clazz.getDeclaredConstructor(View.class);
            lDeclaredConstructor.setAccessible(true);
            return lDeclaredConstructor.newInstance(pView);
        } catch (InstantiationException pE) {
            Timber.i(pE);
            pE.printStackTrace();
        } catch (IllegalAccessException pE) {
            Timber.i(pE);
            pE.printStackTrace();
        } catch (InvocationTargetException pE) {
            Timber.i(pE);
            pE.printStackTrace();
        } catch (NoSuchMethodException pE) {
            Timber.i(pE);
            pE.printStackTrace();
        } catch (ClassCastException pE) {
            Timber.i(pE);
            pE.printStackTrace();
        }
        return null;
    }

    public <B> B createInternal(String classPath, View pView) {
        if (pattern(classPath, "$") > 1) {
            throw new NullPointerException(classPath + " can not be Instantiation");
        }
        int $ = classPath.indexOf("$");
        String superClass = classPath.substring(0, $);
        String internalClass = classPath.substring($ + 1);
        try {
            Class<?> aClass = Class.forName(superClass);
            Class<?>[] declaredClasses = aClass.getDeclaredClasses();
            for (int i = 0; i < declaredClasses.length; i++) {
                Class<B> declaredClass = (Class<B>) declaredClasses[i];
                if (declaredClass.getSimpleName().equals(internalClass)) {
                    if (Modifier.toString(declaredClass.getModifiers()).contains("static")) {
                        return create(declaredClass, pView);
                    } else {
                        Constructor<B> lDeclaredConstructor = declaredClass.getDeclaredConstructor(getClass(),View.class);
                        lDeclaredConstructor.setAccessible(true);
                        return lDeclaredConstructor.newInstance(this,pView);
                    }
                }
            }
            aClass.getGenericInterfaces();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException pE) {
            pE.printStackTrace();
        } catch (InstantiationException pE) {
            pE.printStackTrace();
        } catch (IllegalAccessException pE) {
            pE.printStackTrace();
        } catch (InvocationTargetException pE) {
            pE.printStackTrace();
        }
        return null;
    }

    /**
     * 通过正则表达式的方式获取字符串中指定字符的个数
     *
     * @param text 指定的字符串
     * @return 指定字符的个数
     */
    private int pattern(String text, String key) {
        // 根据指定的字符构建正则
        Pattern pattern = Pattern.compile(key);
        // 构建字符串和正则的匹配
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        // 循环依次往下匹配
        while (matcher.find()) { // 如果匹配,则数量+1
            count++;
        }
        return count;
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
