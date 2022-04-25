package com.expert.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.expert.DTO.ArtistDetailsDTO;
import com.expert.DTO.CategoryDTO;
import com.expert.DTO.UserDTO;
import com.expert.R;
import com.expert.databinding.ActivityProfileAlertBinding;
import com.expert.https.HttpsRequest;
import com.expert.interfacess.Consts;
import com.expert.interfacess.Helper;
import com.expert.network.NetworkManager;
import com.expert.preferences.SharedPrefrence;
import com.expert.ui.fragment.ProfileSetting;
import com.expert.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileAlertActivity extends AppCompatActivity {

    ActivityProfileAlertBinding binding;
    Context mContext;
    private ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
    private JSONObject catResponse =new JSONObject();

    private HashMap<String, String> paramsLogout = new HashMap<>();
    private DialogInterface dd;
    private SharedPrefrence prefrence;
    private UserDTO userDTO;
    private String TAG = ProfileAlertActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile_alert);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_profile_alert);
        mContext = ProfileAlertActivity.this;
        prefrence = SharedPrefrence.getInstance(this);
        userDTO = prefrence.getParentUser(Consts.USER_DTO);

        if (getIntent().hasExtra("catResponse")){
            try {
                catResponse=new JSONObject(getIntent().getStringExtra("catResponse"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            //logout
            logout();
        }

        if (getIntent().hasExtra(Consts.CATEGORY_list)) {
            categoryDTOS = (ArrayList<CategoryDTO>) getIntent().getSerializableExtra(Consts.CATEGORY_list);
        }else{
           //logout
            logout();
        }

        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkManager.isConnectToInternet(mContext)) {
//                    Log.e("categoryDTOS",catResponse.toString());
                    Intent intent = new Intent(mContext, EditPersnoalInfo.class);
                    intent.putExtra(Consts.CATEGORY_list, categoryDTOS);
                    intent.putExtra("catResponse", catResponse.toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up, R.anim.stay);
                } else {
                    ProjectUtils.showToast(mContext, getResources().getString(R.string.internet_concation));
                }
            }
        });

        binding.signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmLogout();
            }
        });
    }

    public void logout() {
        ProjectUtils.showProgressDialog(this, true, getResources().getString(R.string.please_wait));
        new HttpsRequest(Consts.ARTIST_LOGOUT_API, paramsLogout, this).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                ProjectUtils.pauseProgressDialog();
                if (flag) {
                    ProjectUtils.showToast(mContext, msg);

                    dd.dismiss();
                    prefrence.clearAllPreferences();
                    Intent intent = new Intent(ProfileAlertActivity.this, SignInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    ProjectUtils.showToast(ProfileAlertActivity.this, msg);
                }


            }
        });
    }

    public void confirmLogout() {
        try {
            new AlertDialog.Builder(ProfileAlertActivity.this)
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(getResources().getString(R.string.logout_msg))
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dd = dialog;
                            logout();

                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}