package org.daimhim.muster;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.daimhim.getphoto.PictureResourceCaptureImp;
import org.daimhim.getphoto.PictureResourceContract;

/**
 * @Classname SelectPhotoFragment
 * @Description TODO
 * @Date 2019/4/14 19:44
 * @Created by Daimhim
 * Class description Daimhim太懒了什么都没有留下
 */
public class SelectPhotoFragment extends Fragment implements PictureResourceContract.FilesReceiving, PictureResourceContract.PictureCut {
    private PictureResourceContract.PictureResourceCapture mPictureResourceCapture;
    private int mInt;
    private ImageView mViewById;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View lView = new ImageView(getContext());
        lView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            lView.setId(View.generateViewId());
        }
        mInt = lView.getId();
        return lView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPictureResourceCapture = new PictureResourceCaptureImp(this,this);
//        mPictureResourceCapture.setPictureCut(this);
        mViewById = view.findViewById(mInt);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPictureResourceCapture.showDefaultSelectionBox(v.getContext());
            }
        });
    }

    @Override
    public void receivePicture(Uri uri) {
        mViewById.setImageURI(uri);
    }

    @Override
    public void errorMessage(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shearCriterion(Uri fromUri, Uri toUri, int requestCode) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPictureResourceCapture.onActivityResult(requestCode, resultCode, data);
    }
}
