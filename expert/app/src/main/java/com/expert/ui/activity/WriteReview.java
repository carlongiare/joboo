package com.expert.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.expert.DTO.ArtistBooking;
import com.expert.DTO.HistoryDTO;
import com.expert.DTO.UserDTO;
import com.expert.R;
import com.expert.https.HttpsRequest;
import com.expert.interfacess.Consts;
import com.expert.interfacess.Helper;
import com.expert.network.NetworkManager;
import com.expert.preferences.SharedPrefrence;
import com.expert.utils.CustomButton;
import com.expert.utils.CustomEditText;
import com.expert.utils.CustomTextView;
import com.expert.utils.ProjectUtils;

import org.json.JSONObject;

import java.util.HashMap;

public class WriteReview extends AppCompatActivity implements View.OnClickListener {
    private String TAG = WriteReview.class.getSimpleName();
    private RatingBar rbReview;
    private CustomTextView tvCharReview;
    private CustomEditText yourReviewET;
    private CustomButton btnSubmit;
    private Context mContext;
    private float myrating;
    private String id = "";
    private HashMap<String, String> parms = new HashMap<>();
    private ImageView ivBack;
    private SharedPrefrence prefrence;
    private UserDTO userDTO;
    private HistoryDTO historyDTO;
    private ArtistBooking artistBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        mContext = WriteReview.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        userDTO = prefrence.getParentUser(Consts.USER_DTO);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String artistId = extras.getString(Consts.EXPERT_ID);
        String clientId = extras.getString(Consts.CLIENT_ID);
        String bookingId = extras.getString(Consts.BOOKING_ID);
        Log.d(TAG, "ArtistID: " + artistId + "ClientID: " + clientId + "BookingID" + bookingId);

        parms.put(Consts.ARTIST_ID, artistId);
        parms.put(Consts.USER_ID, clientId);
        parms.put(Consts.BOOKING_ID, bookingId);
        init();
    }

    public void init() {
        ivBack = (ImageView) findViewById(R.id.ivBack);
        rbReview = (RatingBar) findViewById(R.id.rbReview);
        tvCharReview = (CustomTextView) findViewById(R.id.tvCharReview);
        yourReviewET = (CustomEditText) findViewById(R.id.yourReviewET);
        btnSubmit = (CustomButton) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        /*
         *
         * handling rating bar
         *
         */

        rbReview.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                myrating = ratingBar.getRating();
            }
        });

        yourReviewET.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                tvCharReview.setText(String.valueOf(s.length()) + "/200");

            }
        });

    }

    public void submit() {
        if (!validateReview()) {
            return;
        } else {
//            sendReview();
        }
    }

    /////checking validation
    public boolean validateReview() {
        if (yourReviewET.getText().toString().trim().length() <= 0) {
            yourReviewET.setError(getResources().getString(R.string.val_comment));
            yourReviewET.requestFocus();
            return false;
        } else {
            yourReviewET.setError(null);
            yourReviewET.clearFocus();
            return true;
        }
    }


    /*
     *
     * method  onclick()  is handling  the button event
     *
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:
                if (NetworkManager.isConnectToInternet(mContext)) {
                    submit();
                } else {
                    ProjectUtils.showToast(mContext, getResources().getString(R.string.internet_concation));
                }

                break;
            case R.id.ivBack:
                finish();
                break;
        }
    }
    /*
     *
     * method  sendReview()  send reviews of users  to server
     *
     */

//    public void sendReview() {
//        parms.put(Consts.RATING, String.valueOf(myrating));
//        parms.put(Consts.REVIEW, ProjectUtils.getEditTextValue(yourReviewET));
//        new HttpsRequest(Consts.RATE_CLIENT, parms, mContext).stringPost(TAG, new Helper() {
//            @Override
//            public void backResponse(boolean flag, String msg, JSONObject response) {
//                if (flag) {
//                    ProjectUtils.showLong(mContext, msg);
//                    finish();
//                } else {
//                    ProjectUtils.showLong(mContext, msg);
//                }
//            }
//        });
//    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
