package com.expert.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.expert.DTO.ArtistBooking;
import com.expert.DTO.ArtistBookingDTO;
import com.expert.DTO.ArtistDetailsDTO;
import com.expert.DTO.UserDTO;
import com.expert.R;
import com.expert.https.HttpsRequest;
import com.expert.interfacess.Consts;
import com.expert.interfacess.Helper;
import com.expert.preferences.SharedPrefrence;
import com.expert.ui.adapter.AdapterAllBookings;
import com.expert.ui.adapter.AdapterAllHistory;
import com.expert.ui.adapter.PreviousworkPagerAdapter;
import com.expert.utils.CustomTextViewBold;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class History extends Fragment {
    private View view;
    private ArtistDetailsDTO artistDetailsDTO;
    private PreviousworkPagerAdapter previousworkPagerAdapter;
    private ArrayList<ArtistBookingDTO> artistBookingDTOList;
    private Bundle bundle;
    private LinearLayoutManager mLayoutManagerReview;
    private RecyclerView rvPreviousWork;
    private CustomTextViewBold tvNotFound;

    private UserDTO userDTO;
    private SharedPrefrence prefrence;
    private String TAG = NewBookings.class.getSimpleName();
    private ProgressBar pd;

    private HashMap<String, String> parms = new HashMap<>();
    private ArrayList<ArtistBooking> artistBookingsList;
    private AdapterAllHistory adapterAllBookings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_previous_work, container, false);
        bundle = this.getArguments();

        prefrence = SharedPrefrence.getInstance(getActivity());
        artistDetailsDTO = (ArtistDetailsDTO) bundle.getSerializable(Consts.ARTIST_DTO);
        prefrence = SharedPrefrence.getInstance(getActivity());
        userDTO = prefrence.getParentUser(Consts.USER_DTO);

        showUiAction(view);
        return view;
    }

    public void showUiAction(View v) {
        tvNotFound = (CustomTextViewBold) v.findViewById(R.id.tvNotFound);
        pd = v.findViewById(R.id.pd);
        tvNotFound.setText("No History Found");
        rvPreviousWork = v.findViewById(R.id.rvPreviousWork);
        mLayoutManagerReview = new LinearLayoutManager(getActivity().getApplicationContext());
        rvPreviousWork.setLayoutManager(mLayoutManagerReview);
        getBookings();
    }

    public void getBookings() {
        parms.put(Consts.ARTIST_ID, userDTO.getUser_id());
        parms.put(Consts.BOOKING_FLAG, "4");
        pd.setVisibility(View.VISIBLE);
        new HttpsRequest(Consts.GET_ALL_BOOKING_ARTIST_API, parms, getActivity()).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                pd.setVisibility(View.GONE);
                if (flag) {
                    tvNotFound.setVisibility(View.GONE);
                    rvPreviousWork.setVisibility(View.VISIBLE);
                    // binding.rlSearch.setVisibility(View.VISIBLE);
                    try {
                        artistBookingsList = new ArrayList<>();
                        Type getpetDTO = new TypeToken<List<ArtistBooking>>() {
                        }.getType();
                        artistBookingsList = (ArrayList<ArtistBooking>) new Gson().fromJson(response.getJSONArray("data").toString(), getpetDTO);
                        adapterAllBookings = new AdapterAllHistory(History.this, artistBookingsList, userDTO, LayoutInflater.from(getActivity()));
                        rvPreviousWork.setAdapter(adapterAllBookings);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {

                    tvNotFound.setVisibility(View.VISIBLE);
                    rvPreviousWork.setVisibility(View.GONE);
                    //binding.rlSearch.setVisibility(View.GONE);
                }
            }
        });
    }

    public void showData() {
        artistBookingDTOList = new ArrayList<>();
        artistBookingDTOList = artistDetailsDTO.getArtist_booking();
        if (artistBookingDTOList.size() > 0) {
            tvNotFound.setVisibility(View.GONE);
            rvPreviousWork.setVisibility(View.VISIBLE);
            previousworkPagerAdapter = new PreviousworkPagerAdapter(getActivity(), artistBookingDTOList);
            rvPreviousWork.setAdapter(previousworkPagerAdapter);
        } else {
            tvNotFound.setVisibility(View.VISIBLE);
            rvPreviousWork.setVisibility(View.GONE);
        }

    }


}
