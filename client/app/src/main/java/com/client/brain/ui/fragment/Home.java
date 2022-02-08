package com.client.brain.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.client.brain.R;
import com.client.brain.network.NetworkManager;
import com.client.brain.ui.activity.BaseActivity;
import com.client.brain.ui.activity.PostJob;
import com.client.brain.utils.CustomTextView;
import com.client.brain.utils.ProjectUtils;


public class Home extends Fragment implements View.OnClickListener{
    private View view;
    private BaseActivity baseActivity;
    private FragmentManager fragmentManager;
    private DiscoverFragment discoverFragment = new DiscoverFragment();
    private NearByFragment nearByFragment = new NearByFragment();
    private CustomTextView CTVdiscover, CTVnearby;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        baseActivity.headerNameTV.setText(getResources().getString(R.string.fabartist));
        fragmentManager = getChildFragmentManager();
        CTVnearby = (CustomTextView) view.findViewById(R.id.CTVnearby);
        CTVdiscover = (CustomTextView) view.findViewById(R.id.CTVdiscover);
        ImageView ivPosthome = view.findViewById(R.id.ivPosthome);

        ivPosthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkManager.isConnectToInternet(getActivity())) {
                    startActivity(new Intent(getActivity(), PostJob.class));
                } else {
                    ProjectUtils.showToast(getActivity(), getResources().getString(R.string.internet_concation));
                }
            }
        });

        CTVdiscover.setOnClickListener(this);
        CTVnearby.setOnClickListener(this);

        fragmentManager.beginTransaction().add(R.id.frame, discoverFragment).commit();

        return view;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        baseActivity = (BaseActivity) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CTVdiscover:
                setSelected(true, false);
                fragmentManager.beginTransaction().replace(R.id.frame, discoverFragment).commit();
                break;
            case R.id.CTVnearby:
                setSelected(false, true);
                fragmentManager.beginTransaction().replace(R.id.frame, nearByFragment).commit();
                break;
        }

    }

    public void setSelected(boolean firstBTN, boolean secondBTN) {
        if (firstBTN) {
            CTVdiscover.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            CTVdiscover.setTextColor(getResources().getColor(R.color.white));
            CTVnearby.setBackgroundColor(getResources().getColor(R.color.white));
            CTVnearby.setTextColor(getResources().getColor(R.color.gray));
        }
        if (secondBTN) {
            CTVdiscover.setBackgroundColor(getResources().getColor(R.color.white));
            CTVdiscover.setTextColor(getResources().getColor(R.color.gray));
            CTVnearby.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            CTVnearby.setTextColor(getResources().getColor(R.color.white));


        }
        CTVdiscover.setSelected(firstBTN);
        CTVnearby.setSelected(secondBTN);
    }
}
