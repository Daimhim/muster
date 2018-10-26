package org.daimhim.pluginmanager.ui.app;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.bean.AddAppMenuBean;
import org.daimhim.pluginmanager.model.bean.ApplicationBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.app
 * 项目版本：muster
 * 创建时间：2018/10/26 10:54  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/26 10:54  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class EditAppFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.rl_recycler_view_pm)
    RecyclerView rlRecyclerView;
    private EditAppViewModel mEditAppViewModel;
    private EditAppAdapter mAddAppAdapter;

    public void upUI(ApplicationBean pApplicationBean) {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_app, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditAppViewModel = ViewModelProviders.of(this).get(EditAppViewModel.class);
        mAddAppAdapter = new EditAppAdapter();
        rlRecyclerView.setAdapter(mAddAppAdapter);
        mEditAppViewModel.getInputMenu()
                .subscribe(new ObserverCallBack<List<AddAppMenuBean>>() {
                    @Override
                    protected void onSuccess(List<AddAppMenuBean> pAddAppMenuBeans) {
                        mAddAppAdapter.onRefresh(pAddAppMenuBeans);
                    }

                    @Override
                    protected void onFailure(JavaResponse pJavaResponse) {
                        Snackbar.make(view, pJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_upload_apk, R.id.bt_download_apk, R.id.bt_select_apk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_apk:
                break;
            case R.id.bt_download_apk:
                String lApp_url =  mAddAppAdapter.getValue("appUrl");
                if (TextUtils.isEmpty(lApp_url)) {
                    Snackbar.make(view, "url can not be empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                mEditAppViewModel.upLoadApk(lApp_url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ObserverCallBack<ArrayMap<String, String>>() {
                            @Override
                            protected void onSuccess(ArrayMap<String, String> pStringStringArrayMap) {
                                List<AddAppMenuBean> lPairs = mAddAppAdapter.getPairs();
                                AddAppMenuBean lBean = null;
                                for (int i = 0; i < lPairs.size(); i++) {
                                    lBean = lPairs.get(i);
                                    lBean.setVaue(pStringStringArrayMap.get(lBean.getKey()));
                                }
                            }

                            @Override
                            protected void onFailure(JavaResponse pJavaResponse) {
                                Snackbar.make(view, pJavaResponse.getError_msg(), Snackbar.LENGTH_SHORT).show();
                            }
                        });

                break;
            case R.id.bt_select_apk:
                break;
        }
    }

    public void addApp() {

    }
}
