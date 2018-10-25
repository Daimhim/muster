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
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAppAdapter extends RecyclerViewEmpty<RecyclerViewEmpty.ClickViewHolder<Pair<String, String>>>
        implements RecyclerContract.SimpleContract<Map<String,String>, Pair<String, String>> {
    private ArrayMap<String, String> mArrayMap;

    public AddAppAdapter() {
        mArrayMap = new ArrayMap<>();
    }

    @Override
    public void onRefresh(Map<String,String> pMap) {
        mArrayMap.clear();
        mArrayMap.putAll(pMap);
        notifyDataSetChanged();
    }

    @Override
    public Pair<String, String> getItem(int pI) {
        return new Pair<>(mArrayMap.keyAt(pI),mArrayMap.get(mArrayMap.keyAt(pI)));
    }

    @Override
    public void onLoad(Map<String,String> pPairs) {
        mArrayMap.putAll(pPairs);
        notifyDataSetChanged();
    }

    @Override
    public ClickViewHolder<Pair<String, String>> onCreateDataViewHolder(ViewGroup pViewGroup, int pI) {
        ClickViewHolder<Pair<String, String>> lViewHolder = null;
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
    public void onBindDataViewHolder(ClickViewHolder<Pair<String, String>> pPairClickViewHolder, int pI) {
        if (pI == 0){
            pPairClickViewHolder.onRefresh(new Pair<>("app_logo",mArrayMap.get("app_logo")));
        }else {
            pPairClickViewHolder.onRefresh(getItem(pI));
        }
    }

    @Override
    public int getDataItemCount() {
        return mArrayMap.size();
    }

    public Map<String,String> getData(){
        return mArrayMap;
    }

    @Override
    public int getDataItemViewType(int position) {
        return position==0?0:1;
    }

    class AddAppLogoViewHolder extends ClickViewHolder<Pair<String, String>> {
        @BindView(R.id.iv_logo_pm)
        ImageView ivLogoPm;

        public AddAppLogoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onRefresh(Pair<String, String> pStringStringPair) {
            ImgLoadingUtil.loadImage(ivLogoPm, pStringStringPair.second);
        }
    }

    class AddAppViewHolder extends ClickViewHolder<Pair<String, String>> {
        @BindView(R.id.et_input_pm)
        TextInputEditText etInputPm;
        @BindView(R.id.et_input_layout_pm)
        TextInputLayout etInputLayoutPm;

        public AddAppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onRefresh(Pair<String, String> pStringStringPair) {
            etInputLayoutPm.setHint(pStringStringPair.first);
            etInputPm.setText(pStringStringPair.second);
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
                        mArrayMap.put(mArrayMap.keyAt(getLayoutPosition()),s.toString());
                    }
                });
                etInputPm.setTag("1");
            }

        }
    }


}
