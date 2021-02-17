package com.client.brain.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.client.brain.DTO.ArtistDetailsDTO;
import com.client.brain.DTO.ReviewsDTO;
import com.client.brain.R;
import com.client.brain.interfacess.Consts;
import com.client.brain.ui.adapter.ReviewAdapter;
import com.client.brain.utils.CustomTextViewBold;

import java.util.ArrayList;

public class Reviews extends Fragment {
    private View view;
    private RecyclerView rvReviews;
    private ArtistDetailsDTO artistDetailsDTO;
    private ReviewAdapter reviewAdapter;
    private LinearLayoutManager mLayoutManagerReview;
    private ArrayList<ReviewsDTO> reviewsDTOList;
    private CustomTextViewBold tvReviewsText;
    private Bundle bundle;
    private CustomTextViewBold tvNotFound;
    private LinearLayout llList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reviews, container, false);
        bundle = this.getArguments();
        artistDetailsDTO = (ArtistDetailsDTO) bundle.getSerializable(Consts.ARTIST_DTO);
        showUiAction(view);
        return view;
    }

    public void showUiAction(View v) {
        tvNotFound = (CustomTextViewBold) v.findViewById(R.id.tvNotFound);
        llList = v.findViewById(R.id.llList);

        tvReviewsText = v.findViewById(R.id.tvReviewsText);
        rvReviews = v.findViewById(R.id.rvReviews);
        mLayoutManagerReview = new LinearLayoutManager(getActivity().getApplicationContext());

        rvReviews.setLayoutManager(mLayoutManagerReview);
        showData();
    }


    public void showData() {
        reviewsDTOList = new ArrayList<>();
        reviewsDTOList = artistDetailsDTO.getReviews();
        if (reviewsDTOList.size() > 0) {
            llList.setVisibility(View.VISIBLE);
            tvNotFound.setVisibility(View.GONE);
            reviewAdapter = new ReviewAdapter(getActivity(), reviewsDTOList);
            rvReviews.setAdapter(reviewAdapter);
            tvReviewsText.setText(getString(R.string.reviews) + reviewsDTOList.size() + ")");
        } else {
            llList.setVisibility(View.GONE);
            tvNotFound.setVisibility(View.VISIBLE);
        }

    }
}
