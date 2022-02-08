package com.expert.ui.activity;

import android.app.Dialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.expert.DTO.HistoryDTO;
import com.expert.R;
import com.expert.databinding.ActivityViewInvoiceBinding;
import com.expert.interfacess.Consts;
import com.expert.network.Singleton;
import com.expert.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ViewInvoice extends AppCompatActivity {
    private ActivityViewInvoiceBinding binding;
    private HistoryDTO historyDTO;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_invoice);
        mContext = ViewInvoice.this;
        if (getIntent().hasExtra(Consts.HISTORY_DTO)) {
            historyDTO = (HistoryDTO) getIntent().getSerializableExtra(Consts.HISTORY_DTO);
        }
        TextView tvrate = findViewById(R.id.tvrate);
        setUiAction();

        StringRequest sRequest = new StringRequest(Request.Method.GET, "https://backend.joboo.co.ke/Webservice/getCommission",
                response -> {
                    Log.i("invoice", "onResponse: " + response);
                    try {
                        JSONObject jobject = new JSONObject(response);
                        String msg = getResources().getString(R.string.commis_msg);
                        tvrate.setText(msg +" "+ jobject.getJSONObject("data").getString("flat_amount")+" %");
                        tvrate.setVisibility(View.VISIBLE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.i("invoice", "error: " + error);
                    Toast.makeText(this, "Failed to get rate", Toast.LENGTH_SHORT).show();
                });

        Singleton.getmInstance(this).addToRequestQueue(sRequest);
    }

    public void setUiAction() {

        binding.ivCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Glide.with(mContext).
                load(historyDTO.getArtistImage())
                .placeholder(R.drawable.dummyuser_image)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivProfile);

        binding.tvInvoiceId.setText(mContext.getResources().getString(R.string.service) + " " + historyDTO.getInvoice_id());
        binding.tvName.setText(ProjectUtils.getFirstLetterCapital(historyDTO.getArtistName()));
        binding.tvServiceType.setText(historyDTO.getCategoryName());
        binding.tvWork.setText(historyDTO.getCategoryName());
        binding.tvPrice.setText(historyDTO.getCurrency_type() + historyDTO.getFinal_amount());
        binding.tvSubtotal.setText(historyDTO.getCurrency_type() + historyDTO.getTotal_amount());
        binding.tvTotal.setText(historyDTO.getCurrency_type() + historyDTO.getFinal_amount());
        binding.tvDiscount.setText(historyDTO.getCurrency_type()+historyDTO.getDiscount_amount());
        try {
            binding.tvInvoiceDate.setText(ProjectUtils.convertTimestampDateToTime(ProjectUtils.correctTimestamp(Long.parseLong(historyDTO.getCreated_at()))));
            binding.tvInvoiceDate1.setText(ProjectUtils.convertTimestampDateToTime(ProjectUtils.correctTimestamp(Long.parseLong(historyDTO.getCreated_at()))));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_down);
    }
}
