package org.daimhim.pluginmanager.ui.app;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.daimhim.pictureload.ImgLoadingUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.pluginmanager.model.bean.AddAppMenuBean;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditAppAdapter extends RecyclerViewEmpty<RecyclerViewEmpty.ClickViewHolder<AddAppMenuBean>>
        implements RecyclerContract.SimpleContract<List<AddAppMenuBean>, AddAppMenuBean> {
    private List<AddAppMenuBean> mPairs;

    public EditAppAdapter() {
        mPairs = new ArrayList<>();
    }

    @Override
    public void onRefresh(List<AddAppMenuBean> pMap) {
        mPairs.clear();
        mPairs.addAll(pMap);
        notifyDataSetChanged();
    }

    @Override
    public AddAppMenuBean getItem(int pI) {
        return mPairs.get(pI);
    }

    @Override
    public void onLoad(List<AddAppMenuBean> pMap) {
        mPairs.addAll(pMap);
        notifyDataSetChanged();
    }

    @Override
    public ClickViewHolder<AddAppMenuBean> onCreateDataViewHolder(ViewGroup pViewGroup, int pI) {
        ClickViewHolder<AddAppMenuBean> lViewHolder = null;
        switch (pI) {
            case 0:
                lViewHolder = new AddAppLogoViewHolder(LayoutInflater.from(pViewGroup.getContext())
                        .inflate(R.layout.viewholder_addapp_logo, pViewGroup, false));
                break;
            case 1:
                lViewHolder = new AddAppViewHolder(LayoutInflater.from(pViewGroup.getContext())
                        .inflate(R.layout.viewholder_addapp, pViewGroup, false));
                break;
        }
        return lViewHolder;
    }

    @Override
    public void onBindDataViewHolder(ClickViewHolder<AddAppMenuBean> pPairClickViewHolder, int pI) {
        pPairClickViewHolder.onRefresh(getItem(pI));
    }

    @Override
    public int getDataItemCount() {
        return mPairs.size();
    }

    public List<AddAppMenuBean> getPairs() {
        return mPairs;
    }
    public String getValue(String key) {
        for (int i = 0; i < mPairs.size(); i++) {
            if (TextUtils.equals(mPairs.get(i).getKey(),key)){
                return mPairs.get(i).getVaue();
            }
        }
        return null;
    }

    @Override
    public int getDataItemViewType(int position) {
        return position==0?0:1;
    }

    class AddAppLogoViewHolder extends ClickViewHolder<AddAppMenuBean> {
        @BindView(R.id.iv_logo_pm)
        ImageView ivLogoPm;

        public AddAppLogoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onRefresh(AddAppMenuBean pStringStringPair) {
            ImgLoadingUtil.loadImage(ivLogoPm, pStringStringPair.getVaue());
        }
    }

    class AddAppViewHolder extends ClickViewHolder<AddAppMenuBean> {
        @BindView(R.id.et_input_pm)
        TextInputEditText etInputPm;
        @BindView(R.id.et_input_layout_pm)
        TextInputLayout etInputLayoutPm;

        public AddAppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onRefresh(AddAppMenuBean pStringStringPair) {
            etInputLayoutPm.setHint(pStringStringPair.getKey());
            etInputPm.setText(pStringStringPair.getVaue());
            if (etInputPm.getTag() == null){
                etInputPm.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        mPairs.get(getLayoutPosition()).setVaue(s.toString());
                    }
                });
                etInputPm.setTag("1");
            }

        }
    }


}
