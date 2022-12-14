package com.expert.ui.adapter;
/**
 * Created by VARUN on 01/01/19.
 */
import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.expert.R;
import com.expert.ui.activity.AppIntro;
import com.expert.utils.CustomTextView;

public class AppIntroPagerAdapter extends PagerAdapter {
    private Context mContext;
    LayoutInflater mLayoutInflater;
    private int[] mResources;
    private AppIntro activity;


    public AppIntroPagerAdapter(AppIntro appIntroActivity, Context mContext, int[] mResources) {
        this.mContext = mContext;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mResources = mResources;
        this.activity = appIntroActivity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.appintropager_adapter, container, false);
        ImageView ivImage = (ImageView) itemView.findViewById(R.id.ivImage);

        CustomTextView ctvText = (CustomTextView) itemView.findViewById(R.id.ctvText);
        CustomTextView ctvTextdecrib = (CustomTextView) itemView.findViewById(R.id.ctvTextdecrib);
        ivImage.setImageResource(mResources[position]);
        setDescText(position, ctvText, ctvTextdecrib);


        container.addView(itemView);
        ctvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int pos = position + 1;
                activity.scrollPage(pos);


            }
        });
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setDescText(int pos, TextView ctvText, TextView ctvTextdecrib) {
        switch (pos) {
            case 0:

                ctvTextdecrib.setText(mContext.getString(R.string.intro_1));
                break;
            case 1:

                ctvTextdecrib.setText(mContext.getString(R.string.intro_2));
                break;
            case 2:

                ctvTextdecrib.setText(mContext.getString(R.string.intro_3));
                break;
        }
    }
}