package org.daimhim.pluginmanager.ui.user;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.daimhim.helpful.util.HLogUtil;
import org.daimhim.helpful.util.HSharedUtil;
import org.daimhim.pluginmanager.model.ObserverCallBack;
import org.daimhim.pluginmanager.model.bean.UserBean;
import org.daimhim.pluginmanager.model.response.JavaResponse;
import org.daimhim.pluginmanager.ui.main.MainUtils;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.ui.app.ApplicationFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 项目名称：org.daimhim.pluginmanager.ui.user
 * 项目版本：muster
 * 创建时间：2018/10/19 09:41  星期五
 * 创建人：Administrator
 * 修改时间：2018/10/19 09:41  星期五
 * 类描述：Administrator 太懒了，什么都没有留下
 * 修改备注：Administrator 太懒了，什么都没有留下
 *
 * @author：Administrator
 */
public class UserLoginFragment extends Fragment {

    @BindView(R.id.et_username_pm)
    TextInputEditText etUsernamePm;
    @BindView(R.id.et_password_pm)
    TextInputEditText etPasswordPm;
    Unbinder unbinder;
    private UserViewModel mUserViewModel;

    private String USER_NAME = "USER_NAME";
    private String USER_PASS = "USER_PASS";
    private ObserverCallBack<JavaResponse<UserBean>> mUserLoginObserver = new ObserverCallBack<JavaResponse<UserBean>>() {
        @Override
        public void onSuccess(JavaResponse<UserBean> pUserBeanJavaResponse) {
            HSharedUtil.putString(getContext(),USER_NAME,pUserBeanJavaResponse.getResult().getAccount_number());
            HSharedUtil.putString(getContext(),USER_PASS,pUserBeanJavaResponse.getResult().getPass_word());
            MainUtils.startFragment(getContext(), ApplicationFragment.class);
        }

        @Override
        public void onFailure(JavaResponse pJavaResponse) {
            Snackbar.make(etUsernamePm,pJavaResponse.getError_msg(),Snackbar.LENGTH_SHORT).show();
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainUtils.upTitleAndIco(getContext(),"登录",R.mipmap.ic_launcher,null);
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        if (!TextUtils.isEmpty(HSharedUtil.getString(getContext(),USER_NAME))&&!TextUtils.isEmpty(HSharedUtil.getString(getContext(),USER_PASS))){
            mUserViewModel.userLogin(HSharedUtil.getString(getContext(),USER_NAME),HSharedUtil.getString(getContext(),USER_PASS))
                    .subscribe(mUserLoginObserver);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.bt_login_pm, R.id.bt_cancel_pm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login_pm:
                if (TextUtils.isEmpty(etUsernamePm.getText())){
                    Snackbar.make(view,"Username can not be empty",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etPasswordPm.getText())){
                    Snackbar.make(view,"Password can not be empty",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                mUserViewModel.userLogin(etUsernamePm.getText().toString(),etPasswordPm.getText().toString())
                        .subscribe(mUserLoginObserver);
                break;
            case R.id.bt_cancel_pm:
                MainUtils.startFragment(getContext(),RegisterFragment.class);
                break;
        }
    }
}
