package org.daimhim.pluginmanager.ui.version;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import org.daimhim.afilechooser.ipaulpro.afilechooser.utils.FileUtils;
import org.daimhim.helpful.util.HViewUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.app.EditAppViewModel;
import org.daimhim.pluginmanager.ui.base.BaseFragment;
import org.daimhim.pluginmanager.ui.main.MainUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.version
 * 项目版本：muster
 * 创建时间：2018/11/14 16:39  星期三
 * 创建人：Administrator
 * 修改时间：2018/11/14 16:39  星期三
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class VersionEditFragment extends BaseFragment {
    public static final int REQUEST_CODE = 6384;
    @BindView(R.id.fl_layout_pm)
    FlexboxLayout flLayoutPm;
    @BindView(R.id.et_plugin_url_input_pm)
    TextInputEditText etPluginUrlInputPm;
    @BindView(R.id.et_plugin_description_input_pm)
    TextInputEditText etPluginDescriptionInputPm;
    Unbinder unbinder;
    private EditAppViewModel mEditAppViewModel;
    private VersionViewModel mVersionViewModel;
    private ObserverCallBack<ArrayMap<String, String>> mObserver;
    private HashMap<String,String> mHashMap;
    private String mPluginId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_version_edit, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditAppViewModel = ViewModelProviders.of(this).get(EditAppViewModel.class);
        mVersionViewModel = ViewModelProviders.of(this).get(VersionViewModel.class);
        Bundle lArguments = getArguments();
        if (null != lArguments){
            mPluginId = lArguments.getString("pluginId");
        }
        mObserver = new ObserverCallBack<ArrayMap<String, String>>() {
            @Override
            public void onSuccess(ArrayMap<String, String> pStringStringArrayMap) {
                flLayoutPm.removeAllViews();
                Set<Map.Entry<String, String>> lEntries = pStringStringArrayMap.entrySet();
                Iterator<Map.Entry<String, String>> lIterator = lEntries.iterator();
                while (lIterator.hasNext()) {
                    Map.Entry<String, String> lNext = lIterator.next();
                    if (!TextUtils.isEmpty(lNext.getValue())) {
                        flLayoutPm.addView(getApkTag(getContext(), lNext.getKey() + ":" + lNext.getValue()));
                    }
                }
                if (mHashMap == null){
                    mHashMap = new HashMap<>();
                }
                mHashMap.clear();
                mHashMap.putAll(pStringStringArrayMap);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        MainUtils.upTitleAndIco(getContext(), "版本编辑", R.drawable.ic_arrow_back_black_24dp, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainUtils.getI().finishFragment(VersionEditFragment.this);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_upload_apk_pm, R.id.bt_download_apk_pm, R.id.bt_app_pm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_upload_apk_pm:
                showChooser();
                break;
            case R.id.bt_download_apk_pm:
                String lApp_url = etPluginUrlInputPm.getText().toString();
                if (TextUtils.isEmpty(lApp_url)) {
                    Snackbar.make(view, "url can not be empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (!lApp_url.startsWith("http")) {
                    Snackbar.make(view, "url not http startsWith", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                mEditAppViewModel.upLoadApk(lApp_url)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mObserver);
                break;
            case R.id.bt_app_pm:
                String pluginDescription = etPluginDescriptionInputPm.getText().toString();
                if (TextUtils.isEmpty(pluginDescription)) {
                    Snackbar.make(view, "Description can not be empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                mHashMap.put("apkDescription",pluginDescription);
                mHashMap.put("pluginId",mPluginId);
                mHashMap.put("apkPath",etPluginUrlInputPm.getText().toString());
                mVersionViewModel.registerVersion(mHashMap)
                        .subscribe(new ObserverCallBack<JavaResponse<Void>>() {
                            @Override
                            public void onSuccess(JavaResponse<Void> pVoidJavaResponse) {
                                finishFragment();
                            }
                        });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        mEditAppViewModel.analyzeLocal(new File(FileUtils.getPath(getContext(), uri)))
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(mObserver);
                    }
                }
                break;
        }
    }

    /**
     * go chooser
     */
    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, "上传APK");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    TextView getApkTag(Context pContext, String text) {
        TextView lTextView = new TextView(pContext);
        RelativeLayout.LayoutParams lLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lTextView.setLayoutParams(lLayoutParams);
        int lV = (int) HViewUtil.dip2px(pContext, 5);
        lLayoutParams.setMargins(
                lV,
                lV,
                lV,
                lV);
        lTextView.setPadding(lV, 0, lV, 0);
        lTextView.setGravity(Gravity.CENTER);
        lTextView.setText(text);
        lTextView.setBackgroundResource(R.drawable.shape_stroke_666666_corners_3);
        lTextView.setTextColor(ContextCompat.getColor(pContext, R.color.cl_666666));
        return lTextView;
    }
}
