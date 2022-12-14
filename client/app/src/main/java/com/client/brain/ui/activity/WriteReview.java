package com.client.brain.ui.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.client.brain.DTO.HistoryDTO;
import com.client.brain.DTO.UserDTO;
import com.client.brain.R;
import com.client.brain.https.HttpsRequest;
import com.client.brain.interfacess.Consts;
import com.client.brain.interfacess.Helper;
import com.client.brain.network.NetworkManager;
import com.client.brain.preferences.SharedPrefrence;
import com.client.brain.utils.CustomButton;
import com.client.brain.utils.CustomEditText;
import com.client.brain.utils.CustomTextView;
import com.client.brain.utils.ProjectUtils;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        mContext = WriteReview.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        userDTO = prefrence.getParentUser(Consts.USER_DTO);
        if (getIntent().hasExtra(Consts.HISTORY_DTO)) {
            historyDTO = (HistoryDTO) getIntent().getSerializableExtra(Consts.HISTORY_DTO);
        }
        parms.put(Consts.USER_ID, userDTO.getUser_id());
        parms.put(Consts.ARTIST_ID, historyDTO.getArtist_id());
        parms.put(Consts.BOOKING_ID, historyDTO.getBooking_id());
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
            sendReview();
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

    public void sendReview() {
        parms.put(Consts.RATING, String.valueOf(myrating));
        parms.put(Consts.COMMENT, ProjectUtils.getEditTextValue(yourReviewET));
        new HttpsRequest(Consts.ADD_RATING_API, parms, mContext).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    ProjectUtils.showLong(mContext, msg);
                    finish();
                } else {
                    ProjectUtils.showLong(mContext, msg);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
