package com.expert.ui.fragment;

import android.app.Activity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;
import com.expert.DTO.ArtistBooking;
import com.expert.DTO.UserDTO;
import com.expert.R;
import com.expert.databinding.FragmentNewBookingsBinding;
import com.expert.https.HttpsRequest;
import com.expert.interfacess.Consts;
import com.expert.interfacess.Helper;
import com.expert.network.NetworkManager;
import com.expert.preferences.SharedPrefrence;
import com.expert.ui.activity.BaseActivity;
import com.expert.ui.adapter.AdapterAllBookings;
import com.expert.utils.ProjectUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewBookings extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    private BaseActivity baseActivity;
    private FragmentNewBookingsBinding binding;
    private ArrayList<ArtistBooking> artistBookingsList;
    private ArrayList<ArtistBooking> artistBookingsListSection;
    private ArrayList<ArtistBooking> artistBookingsListSection1;
    private LinearLayoutManager mLayoutManager;

    private String TAG = NewBookings.class.getSimpleName();
    private SharedPrefrence prefrence;
    private UserDTO userDTO;
    private HashMap<String, String> parms = new HashMap<>();
    private LayoutInflater myInflater;
    private AdapterAllBookings adapterAllBookings;
    private MaterialSheetFab materialSheetFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_bookings, container, false);
        prefrence = SharedPrefrence.getInstance(getActivity());
        userDTO = prefrence.getParentUser(Consts.USER_DTO);
        parms.put(Consts.BOOKING_FLAG, "0");
        myInflater = LayoutInflater.from(getActivity());
        setUiAction();
        return binding.getRoot();
    }

    private void setUiAction() {
        binding.tvStatus.setText(getResources().getString(R.string.pending));
        setupFab();
        baseActivity.headerNameTV.setText(getResources().getString(R.string.bookings));
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        binding.rvBooking.setLayoutManager(mLayoutManager);

        binding.svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    //  adapterAcceptedBooking.filter(newText.toString());

                } else {


                }
                return false;
            }
        });

        binding.swipeLayout.setOnRefreshListener(this);
        binding.swipeLayout.post(new Runnable() {
                                     @Override
                                     public void run() {

                                         Log.e("Runnable", "FIRST");
                                         if (NetworkManager.isConnectToInternet(getActivity())) {
                                             binding.swipeLayout.setRefreshing(true);
                                             getBookings();

                                         } else {
                                             ProjectUtils.showToast(getActivity(), getResources().getString(R.string.internet_concation));
                                         }
                                     }
                                 }
        );
    }

    /**
     * Sets up the Floating action button.
     */
    private void setupFab() {
        int sheetColor = getResources().getColor(R.color.background_card);
        int fabColor = getResources().getColor(R.color.theme_accent);

        // Create material sheet FAB
        materialSheetFab = new MaterialSheetFab<>(binding.fab, binding.fabSheet, binding.overlay, sheetColor, fabColor);

        // Set material sheet event listener
        materialSheetFab.setEventListener(new MaterialSheetFabEventListener() {
            @Override
            public void onShowSheet() {
                //statusBarColor = getStatusBarColor();
                //setStatusBarColor(getResources().getColor(R.color.theme_primary_dark2));
            }

            @Override
            public void onHideSheet() {
                //setStatusBarColor(statusBarColor);
            }
        });

        // Set material sheet item click listeners
        binding.tvPendingJobs.setOnClickListener(this);
        binding.tvAcceptedJobs.setOnClickListener(this);
        binding.tvRejectedJobs.setOnClickListener(this);
        binding.tvCompletedJobs.setOnClickListener(this);

        binding.tvPendings.setOnClickListener(this);
        binding.tvAccepted.setOnClickListener(this);
        binding.tvRejected.setOnClickListener(this);
        binding.tvCompleted.setOnClickListener(this);
    }

    public void getBookings() {
        parms.put(Consts.ARTIST_ID, userDTO.getUser_id());
        new HttpsRequest(Consts.GET_ALL_BOOKING_ARTIST_API, parms, getActivity()).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                binding.swipeLayout.setRefreshing(false);
                if (flag) {
                    binding.tvNo.setVisibility(View.GONE);
                    binding.rvBooking.setVisibility(View.VISIBLE);
                    // binding.rlSearch.setVisibility(View.VISIBLE);
                    try {
                        artistBookingsList = new ArrayList<>();
                        Type getpetDTO = new TypeToken<List<ArtistBooking>>() {
                        }.getType();
                        artistBookingsList = (ArrayList<ArtistBooking>) new Gson().fromJson(response.getJSONArray("data").toString(), getpetDTO);
                        showData();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {

                    binding.tvNo.setVisibility(View.VISIBLE);
                    binding.rvBooking.setVisibility(View.GONE);
                    //binding.rlSearch.setVisibility(View.GONE);
                }
            }
        });
    }

    public void showData() {
        adapterAllBookings = new AdapterAllBookings(NewBookings.this, artistBookingsList, userDTO, myInflater);
        binding.rvBooking.setAdapter(adapterAllBookings);
    }

    @Override
    public void onRefresh() {
        getBookings();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        baseActivity = (BaseActivity) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvPendingJobs:
                binding.tvPendingJobs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.tvPendingJobs.setTextColor(getResources().getColor(R.color.white));
                binding.tvAcceptedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvAcceptedJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvRejectedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvRejectedJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvCompletedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvCompletedJobs.setTextColor(getResources().getColor(R.color.gray));
                //binding.tvStatus.setText(getResources().getString(R.string.pending));
                parms.put(Consts.BOOKING_FLAG, "0");
                getBookings();
                break;
            case R.id.tvAcceptedJobs:
                binding.tvPendingJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvPendingJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvAcceptedJobs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.tvAcceptedJobs.setTextColor(getResources().getColor(R.color.white));
                binding.tvRejectedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvRejectedJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvCompletedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvCompletedJobs.setTextColor(getResources().getColor(R.color.gray));
                //binding.tvStatus.setText(getResources().getString(R.string.acc));
                parms.put(Consts.BOOKING_FLAG, "1");
                getBookings();
                break;
            case R.id.tvRejectedJobs:
                binding.tvPendingJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvPendingJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvAcceptedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvAcceptedJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvRejectedJobs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.tvRejectedJobs.setTextColor(getResources().getColor(R.color.white));
                binding.tvCompletedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvCompletedJobs.setTextColor(getResources().getColor(R.color.gray));
                //binding.tvStatus.setText(getResources().getString(R.string.rej));
                parms.put(Consts.BOOKING_FLAG, "2");
                getBookings();
                break;
            case R.id.tvCompletedJobs:
                binding.tvPendingJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvPendingJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvAcceptedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvAcceptedJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvRejectedJobs.setBackgroundColor(getResources().getColor(R.color.white));
                binding.tvRejectedJobs.setTextColor(getResources().getColor(R.color.gray));
                binding.tvCompletedJobs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                binding.tvCompletedJobs.setTextColor(getResources().getColor(R.color.white));
                //binding.tvStatus.setText(getResources().getString(R.string.com));
                parms.put(Consts.BOOKING_FLAG, "4");
                getBookings();
                break;

        }
        materialSheetFab.hideSheet();
    }

    public void setSection() {
        HashMap<String, ArrayList<ArtistBooking>> has = new HashMap<>();
        artistBookingsListSection = new ArrayList<>();
        for (int i = 0; i < artistBookingsList.size(); i++) {


            if (has.containsKey(ProjectUtils.changeDateFormate1(artistBookingsList.get(i).getBooking_date()))) {
                artistBookingsListSection1 = new ArrayList<>();
                artistBookingsListSection1 = has.get(ProjectUtils.changeDateFormate1(artistBookingsList.get(i).getBooking_date()));
                artistBookingsListSection1.add(artistBookingsList.get(i));
                has.put(ProjectUtils.changeDateFormate1(artistBookingsList.get(i).getBooking_date()), artistBookingsListSection1);


            } else {
                artistBookingsListSection1 = new ArrayList<>();
                artistBookingsListSection1.add(artistBookingsList.get(i));
                has.put(ProjectUtils.changeDateFormate1(artistBookingsList.get(i).getBooking_date()), artistBookingsListSection1);
            }
        }

        for (String key : has.keySet()) {
            ArtistBooking artistBooking = new ArtistBooking();
            artistBooking.setSection(true);
            artistBooking.setSection_name(key);
            artistBookingsListSection.add(artistBooking);
            artistBookingsListSection.addAll(has.get(key));

        }


        showData();

    }

}
