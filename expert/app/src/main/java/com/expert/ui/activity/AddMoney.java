package com.expert.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.expert.DTO.UserDTO;
import com.expert.R;
import com.expert.https.HttpsRequest;
import com.expert.interfacess.Consts;
import com.expert.interfacess.Helper;
import com.expert.mpesa.API;
import com.expert.mpesa.ApiInstance;
import com.expert.mpesa.LNMResult;
import com.expert.network.NetworkManager;
import com.expert.network.Singleton;
import com.expert.preferences.SharedPrefrence;
import com.expert.utils.CustomButton;
import com.expert.utils.CustomEditText;
import com.expert.utils.CustomTextView;
import com.expert.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMoney extends AppCompatActivity implements View.OnClickListener {
    private String TAG = AddMoney.class.getSimpleName();
    private Context mContext;
    private CustomEditText etAddMoney;
    private CustomTextView tv1000, tv1500, tv2000;
    private CustomButton cbAdd;
    float rs = 0;
    float rs1 = 0;
    float final_rs = 0;
    private HashMap<String, String> parmas = new HashMap<>();
    private HashMap<String, String> paramsRequest = new HashMap<>();
    private SharedPrefrence prefrence;
    private UserDTO userDTO;
    private String amt = "";
    private String currency = "";
    private CustomTextView tvWallet;
    private ImageView ivBack;
    ProgressDialog progressDialog;
    private Dialog dialog;
    private LinearLayout llPaypall, llStripe, llCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        mContext = AddMoney.this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending Request...");
        progressDialog.setCanceledOnTouchOutside(false);

        prefrence = SharedPrefrence.getInstance(mContext);
        userDTO = prefrence.getParentUser(Consts.USER_DTO);
        paramsRequest.put(Consts.USER_ID, userDTO.getUser_id());
         parmas.put(Consts.USER_ID, userDTO.getUser_id());
        setUiAction();
    }

    public void setUiAction() {
        tvWallet = findViewById(R.id.tvWallet);
        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent().hasExtra(Consts.AMOUNT)) {
            amt = getIntent().getStringExtra(Consts.AMOUNT);
            currency = getIntent().getStringExtra(Consts.CURRENCY);

            tvWallet.setText(currency + " " + amt);
        }

        cbAdd = findViewById(R.id.cbAdd);
        cbAdd.setOnClickListener(this);

        etAddMoney = findViewById(R.id.etAddMoney);
        etAddMoney.setSelection(etAddMoney.getText().length());

        tv1000 = findViewById(R.id.tv1000);
        tv1000.setOnClickListener(this);

        tv1500 = findViewById(R.id.tv1500);
        tv1500.setOnClickListener(this);

        tv2000 = findViewById(R.id.tv2000);
        tv2000.setOnClickListener(this);

        tv1000.setText("+ " + currency + " 500");
        tv1500.setText("+ " + currency + " 1000");
        tv2000.setText("+ " + currency + " 2000");
    }

    @Override
    public void onClick(View v) {
        if (etAddMoney.getText().toString().trim().equalsIgnoreCase("")) {
            rs1 = 0;

        } else {
            rs1 = Float.parseFloat(etAddMoney.getText().toString().trim());

        }

        switch (v.getId()) {
            case R.id.tv1000:
                rs = 500;
                final_rs = rs1 + rs;
                etAddMoney.setText(final_rs + "");
                etAddMoney.setSelection(etAddMoney.getText().length());
                break;
            case R.id.tv1500:
                rs = 1000;
                final_rs = rs1 + rs;
                etAddMoney.setText(final_rs + "");
                etAddMoney.setSelection(etAddMoney.getText().length());
                break;
            case R.id.tv2000:
                rs = 2000;
                final_rs = rs1 + rs;
                etAddMoney.setText(final_rs + "");
                etAddMoney.setSelection(etAddMoney.getText().length());
                break;
            case R.id.cbAdd:
                if (etAddMoney.getText().toString().length() > 0 && Float.parseFloat(etAddMoney.getText().toString().trim())>0) {
                    if (NetworkManager.isConnectToInternet(mContext)) {
                        parmas.put(Consts.AMOUNT, ProjectUtils.getEditTextValue(etAddMoney));
//                        dialogPayment();
                        Log.i("mpesa", "params: " + paramsRequest);
                        progressDialog.show();
                        StringRequest sRequest = new StringRequest(Request.Method.POST, "https://backend.joboo.co.ke/Webservice/walletRequest",
                                response -> {
                                    progressDialog.dismiss();
                                    Log.i("mpesa", "onResponse: " + response);
                                    try {
                                        JSONObject jobject = new JSONObject(response);
                                        //Toast.makeText(AddMoney.this, jobject.getString("message"), Toast.LENGTH_SHORT).show();
                                        final Dialog dialog = new Dialog(AddMoney.this);
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        dialog.setCancelable(false);
                                        dialog.setContentView(R.layout.custom_dialog_mpesa_request);

                                        TextView tvtxtmsg = dialog.findViewById(R.id.textmsg);
                                        tvtxtmsg.setText(jobject.getString("message"));
                                        dialog.findViewById(R.id.tv_getbal_cancel).setOnClickListener(view -> dialog.dismiss());
                                        dialog.findViewById(R.id.tv_getbal_add).setOnClickListener(view -> {
                                            finish();
                                            dialog.dismiss();
                                        });
                                        dialog.show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                },
                                error -> {
                                    Log.i("mpesa", "error: " + error);
                                    progressDialog.dismiss();
                                    Toast.makeText(AddMoney.this, "Failed to send request", Toast.LENGTH_SHORT).show();
                                })
                        {
                            @Override
                            protected Map<String, String> getParams() {
                                paramsRequest.put("amount",ProjectUtils.getEditTextValue(etAddMoney));
                                return paramsRequest;
                            }
                        };
                        Singleton.getmInstance(AddMoney.this).addToRequestQueue(sRequest);

                    } else {
                        ProjectUtils.showLong(mContext, getResources().getString(R.string.internet_concation));
                    }
                } else {
                    ProjectUtils.showLong(mContext, getResources().getString(R.string.val_money));
                }
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (prefrence.getValue(Consts.SURL).equalsIgnoreCase(Consts.PAYMENT_SUCCESS)) {
            prefrence.clearPreferences(Consts.SURL);
            finish();
        } else if (prefrence.getValue(Consts.FURL).equalsIgnoreCase(Consts.PAYMENT_FAIL)) {
            prefrence.clearPreferences(Consts.FURL);
            finish();
        }else if (prefrence.getValue(Consts.SURL).equalsIgnoreCase(Consts.PAYMENT_SUCCESS_paypal)) {
            prefrence.clearPreferences(Consts.SURL);
            addMoney();
        }else if (prefrence.getValue(Consts.FURL).equalsIgnoreCase(Consts.PAYMENT_FAIL_Paypal)) {
            prefrence.clearPreferences(Consts.FURL);
            finish();
        }
    }


    public void addMoney() {
        new HttpsRequest(Consts.ADD_MONEY_API, parmas, mContext).stringPost(TAG, new Helper() {
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




    public void dialogPayment() {
        dialog = new Dialog(mContext/*, android.R.style.Theme_Dialog*/);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dailog_payment_option);

        EditText etnum = dialog.findViewById(R.id.edmpesanumber);
        etnum.setText(userDTO.getMobile());
        ///dialog.getWindow().setBackgroundDrawableResource(R.color.black);
        llPaypall = (LinearLayout) dialog.findViewById(R.id.llPaypall);
        llStripe = (LinearLayout) dialog.findViewById(R.id.llStripe);
        llCancel = (LinearLayout) dialog.findViewById(R.id.llCancel);

        dialog.show();
        dialog.setCancelable(false);
        llCancel.setOnClickListener(v -> {

            String amountPay = ProjectUtils.getEditTextValue(findViewById(R.id.etAddMoney));
            String phone = ProjectUtils.getEditTextValue(etnum);
            String userId = userDTO.getUser_id();
            progressDialog.show();
            StringRequest sRequest = new StringRequest(Request.Method.GET, "https://backend.joboo.co.ke/Webservice/walletRequest",
                    response -> {
                        progressDialog.dismiss();
                        Log.i("mpesa", "onResponse: " + response);
                        try {
                            JSONObject jobject = new JSONObject(response);
                            Toast.makeText(AddMoney.this, jobject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        Log.i("mpesa", "error: " + error);
                        progressDialog.dismiss();
                        Toast.makeText(AddMoney.this, "Failed to send request", Toast.LENGTH_SHORT).show();
                    })
            {
                @Override
                protected Map<String, String> getParams() {
                    return paramsRequest;
                }
            };
            Singleton.getmInstance(AddMoney.this).addToRequestQueue(sRequest);

//                stkpush(amountPay,phone, userId);
            dialog.dismiss();
        });


        llPaypall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Consts.MAKE_PAYMENT_paypal +"amount=" + ProjectUtils.getEditTextValue(etAddMoney)+"&userId="+ userDTO.getUser_id();
                Intent in2 = new Intent(mContext, PaymetWeb.class);
                in2.putExtra(Consts.PAYMENT_URL, url);
                startActivity(in2);
                dialog.dismiss();

            }
        });
        llStripe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Consts.MAKE_PAYMENT + userDTO.getUser_id() + "/" + ProjectUtils.getEditTextValue(etAddMoney);
                Intent in2 = new Intent(mContext, PaymetWeb.class);
                in2.putExtra(Consts.PAYMENT_URL, url);
                startActivity(in2);
                dialog.dismiss();

            }
        });

    }


    private void stkpush(String amountPay,String phone, String userId) {

        final ProgressDialog progressDialog = new ProgressDialog(AddMoney.this);
        progressDialog.setMessage("initiating mpesa...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        API mpesaApi  = ApiInstance.getRetrofitInstance().create(API.class);
        mpesaApi.payWithMpesa(phone,amountPay,userId).enqueue(new Callback<LNMResult>() {
            @Override
            public void onResponse(Call<LNMResult> call, Response<LNMResult> response) {

                if(response.body() != null){

                    if(response.body().getResponseCode().equals("0")){
                        progressDialog.dismiss();

                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "There was an error initiating your request", Toast.LENGTH_SHORT).show();
                    }



                }else{
                    Toast.makeText(getApplicationContext(), "There was an error proccessing your request Please try again", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<LNMResult> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
