package com.example.joginderpal.csbooksdetails.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.joginderpal.csbooksdetails.R;
import com.squareup.picasso.Picasso;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by joginderpal on 25-04-2017.
 */
@SuppressLint("ValidFragment")
public class fragment_price extends Fragment {

    int i;


    public fragment_price(int i) {

        this.i=i;


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_price,container,false);
        return v;
    }
}
