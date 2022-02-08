package com.client.brain.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.client.brain.DTO.UserDTO;
import com.client.brain.R;
import com.client.brain.databinding.ActivityAddMoneyBinding;
import com.client.brain.databinding.DailogPaymentOptionBinding;
import androidx.appcompat.app.AppCompatActivity;
import com.client.brain.https.HttpsRequest;
import com.client.brain.interfacess.Consts;
import com.client.brain.interfacess.Helper;
import com.client.brain.mpesa.API;
import com.client.brain.mpesa.ApiInstance;
import com.client.brain.mpesa.LNMResult;
import com.client.brain.network.NetworkManager;
import com.client.brain.network.Singleton;
import com.client.brain.preferences.SharedPrefrence;
import com.client.brain.utils.DecimalDigitsInputFilter;
import com.client.brain.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMoney extends AppCompatActivity implements View.OnClickListener {
    private String TAG = AddMoney.class.getSimpleName();
    private Context mContext;
    float rs = 0;
    float rs1 = 0;
    float final_rs = 0;
    private HashMap<String, String> parmas = new HashMap<>();
    private SharedPrefrence prefrence;
    private UserDTO userDTO;
    private String amt = "";
    private String currency = "";
    ProgressDialog progressDialog;
    private Dialog dialog;
    private ActivityAddMoneyBinding binding;
    String initialbal;
    boolean firstbal = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_money);
        mContext = AddMoney.this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Initializing Mpesa...");
        progressDialog.setCanceledOnTouchOutside(false);

        prefrence = SharedPrefrence.getInstance(mContext);
        userDTO = prefrence.getParentUser(Consts.USER_DTO);
        parmas.put(Consts.USER_ID, userDTO.getUser_id());
        setUiAction();
        checkBal();
    }

    public void setUiAction() {

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent().hasExtra(Consts.AMOUNT)) {
            amt = getIntent().getStringExtra(Consts.AMOUNT);
            currency = getIntent().getStringExtra(Consts.CURRENCY);

            binding.tvWallet.setText(currency + " " + amt);
        }

        binding.cbAdd.setOnClickListener(this);

        binding.etAddMoney.setSelection(binding.etAddMoney.getText().length());

        binding.tv1000.setOnClickListener(this);

        binding.tv1500.setOnClickListener(this);

        binding.tv2000.setOnClickListener(this);

        binding.tv1000.setText("+ " + currency + " 500");
        binding.tv1500.setText("+ " + currency + " 1000");
        binding.tv2000.setText("+ " + currency + " 2000");


        binding.etAddMoney.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(12, 2)});
        binding.etAddMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1 && s.toString().startsWith("0")) {
                    s.clear();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (binding.etAddMoney.getText().toString().trim().equalsIgnoreCase("")) {
            rs1 = 0;

        } else {
            rs1 = Float.parseFloat(binding.etAddMoney.getText().toString().trim());

        }

        switch (v.getId()) {
            case R.id.tv1000:
                rs = 500;
                final_rs = rs1 + rs;
                binding.etAddMoney.setText(final_rs + "");
                binding.etAddMoney.setSelection(binding.etAddMoney.getText().length());
                break;
            case R.id.tv1500:
                rs = 1000;
                final_rs = rs1 + rs;
                binding.etAddMoney.setText(final_rs + "");
                binding.etAddMoney.setSelection(binding.etAddMoney.getText().length());
                break;
            case R.id.tv2000:
                rs = 2000;
                final_rs = rs1 + rs;
                binding.etAddMoney.setText(final_rs + "");
                binding.etAddMoney.setSelection(binding.etAddMoney.getText().length());
                break;
            case R.id.cbAdd:
                if (binding.etAddMoney.getText().toString().length() > 0 && Float.parseFloat(binding.etAddMoney.getText().toString().trim()) > 0) {
                    if (NetworkManager.isConnectToInternet(mContext)) {
                        parmas.put(Consts.AMOUNT, ProjectUtils.getEditTextValue(binding.etAddMoney));
                        dialogPayment();


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
        } else if (prefrence.getValue(Consts.SURL).equalsIgnoreCase(Consts.PAYMENT_SUCCESS_paypal)) {
            prefrence.clearPreferences(Consts.SURL);
            addMoney();
        } else if (prefrence.getValue(Consts.FURL).equalsIgnoreCase(Consts.PAYMENT_FAIL_Paypal)) {
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

        final DailogPaymentOptionBinding binding1 = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dailog_payment_option, null, false);
        dialog.setContentView(binding1.getRoot());
        ///dialog.getWindow().setBackgroundDrawableResource(R.color.black);
        Log.d(TAG, "dialogPayment: " + userDTO.getMobile() );
        binding1.edmpesanumber.setText(userDTO.getMobile());

        dialog.setCancelable(false);
        dialog.show();
        AtomicBoolean twice = new AtomicBoolean(false);
        binding1.llCancel.setOnClickListener(v -> {
            String amountPay = ProjectUtils.getEditTextValue(binding.etAddMoney);
            String phone = ProjectUtils.getEditTextValue(binding1.edmpesanumber);
            String userId = userDTO.getUser_id();
            TextView tv_phone_error = binding1.getRoot().findViewById(R.id.tv_phone_error);
            if(phone.equals("")){
                tv_phone_error.setVisibility(View.VISIBLE);
            }else if(twice.get()){
                finish();
            }else {
                twice.set(true);
                binding1.tvclick.setText("Pause");
                binding1.edmpesanumber.setVisibility(View.GONE);
                binding1.pbdialog.setVisibility(View.VISIBLE);
                StringRequest sRequest = new StringRequest(Request.Method.GET, "http://api.joboo.co.ke/payments/?txn_key=wl7sKS6AhHoo9hfaoGoIrNyU4MAonsG0qIauZtVr" +
                        "&txn_amount="+amountPay+"&mpesa_number="+phone+"&user_id="+userId,
                        response -> {
                            Log.i("mpesa", "onResponse: " + response);
                            try {
                                JSONObject jobject = new JSONObject(response);
                                Toast.makeText(AddMoney.this, jobject.getString("ServerMessage"), Toast.LENGTH_SHORT).show();
                                checkBal();
                                timerHandler.postDelayed(timerRunnable, 0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> {
                            Log.i("mpesa", "error: " + error);
                            Toast.makeText(this, "Failed to initialize transaction", Toast.LENGTH_SHORT).show();
                        });
                sRequest.setRetryPolicy(new DefaultRetryPolicy(
                        0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Singleton.getmInstance(AddMoney.this).addToRequestQueue(sRequest);

//                stkpush(amountPay,phone, userId);
                //dialog.dismiss();
            }
        });

        binding1.llPaypall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Consts.MAKE_PAYMENT_paypal + "amount=" + ProjectUtils.getEditTextValue(binding.etAddMoney) + "&userId=" + userDTO.getUser_id();
                Intent in2 = new Intent(mContext, PaymetWeb.class);
                in2.putExtra(Consts.PAYMENT_URL, url);
                startActivity(in2);
                dialog.dismiss();

            }
        });

        binding1.llStripe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }

    private void stkpush(String amountPay,String phone, String userId) {

        ProgressDialog progressDialog = new ProgressDialog(AddMoney.this);
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
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "There was an error proccessing your request Please try again later ", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<LNMResult> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "There was an error proccessing your request Please try again later ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    Handler timerHandler = new Handler();
    int timeout = 40000;
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if(timeout > 0){
                timerHandler.postDelayed(this, 5000);
                checkBal();
                timeout -= 5000;
            }else{
                Toast.makeText(AddMoney.this, "Timeout", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    private void checkBal(){
        StringRequest sRequest = new StringRequest(Request.Method.POST, "https://backend.joboo.co.ke/Webservice/getWallet",
                response -> {
                    Log.e("walletcheck", "onResponse: " + response);
                    try {
                        JSONObject jobject = new JSONObject(response);
                        binding.tvWallet.setText(currency + " " + jobject.getJSONObject("data").getString("amount"));
                        if(firstbal){
                            initialbal = jobject.getJSONObject("data").getString("amount");
                            firstbal = false;
                        }
                        //Toast.makeText(AddMoney.this, jobject.getString("status"), Toast.LENGTH_SHORT).show();
                        if(!jobject.getJSONObject("data").getString("amount").equals(initialbal))
                            finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.e("walletcheck", "error: " + error);
                    Toast.makeText(this, "Failed to check status", Toast.LENGTH_SHORT).show();
                    //finish();
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Consts.USER_ID, userDTO.getUser_id());

                return params;
            }
        };
        Singleton.getmInstance(AddMoney.this).addToRequestQueue(sRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(timerRunnable);
    }

}
