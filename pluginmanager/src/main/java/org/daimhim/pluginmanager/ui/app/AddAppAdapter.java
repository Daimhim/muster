package org.daimhim.pluginmanager.ui.app;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.daimhim.pictureload.ImgLoadingUtil;
import org.daimhim.pluginmanager.R;
import org.daimhim.rvadapter.RecyclerContract;
import org.daimhim.rvadapter.RecyclerViewEmpty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddAppAdapter extends RecyclerViewEmpty<RecyclerViewEmpty.ClickViewHolder<Pair<String, String>>>
        implements RecyclerContract.SimpleContract<List<Pair<String, String>>, Pair<String, String>> {
    private List<Pair<String, String>> pairList;

    public AddAppAdapter() {
        pairList = new ArrayList<>();
    }

    @Override
    public void onRefresh(List<Pair<String, String>> pPairs) {
        pairList.clear();
        pairList.addAll(pPairs);
        notifyDataSetChanged();
    }

    @Override
    public Pair<String, String> getItem(int pI) {
        return pairList.get(pI);
    }

    @Override
    public void onLoad(List<Pair<String, String>> pPairs) {
        pairList.addAll(pPairs);
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
        pPairClickViewHolder.onRefresh(getItem(pI));
    }

    @Override
    public int getDataItemCount() {
        return pairList.size();
    }

    @Override
    public int getDataItemViewType(int position) {
        return super.getDataItemViewType(position);
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
            ImgLoadingUtil.loadImage(ivLogoPm,pStringStringPair.second);
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

        }
    }
}
