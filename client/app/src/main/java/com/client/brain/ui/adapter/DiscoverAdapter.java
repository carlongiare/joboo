package com.client.brain.ui.adapter;



import android.content.Context;
import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.client.brain.DTO.AllAtristListDTO;
import com.client.brain.R;
import com.client.brain.interfacess.Consts;
import com.client.brain.preferences.SharedPrefrence;
import com.client.brain.ui.activity.ArtistProfile;
import com.client.brain.utils.CustomTextView;
import com.client.brain.utils.CustomTextViewBold;
import com.client.brain.utils.ProjectUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.MyViewHolder> {

    Context mContext;
    private ArrayList<AllAtristListDTO> allAtristListDTOList;
    private LayoutInflater inflater;
    private SharedPrefrence prefrence;
    private JSONArray categories;

    public DiscoverAdapter(Context mContext, ArrayList<AllAtristListDTO> allAtristListDTOList, LayoutInflater inflater, JSONArray categories) {
        this.mContext = mContext;
        this.allAtristListDTOList = allAtristListDTOList;
        this.inflater = inflater;
        this.categories = categories;
        prefrence = SharedPrefrence.getInstance(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater
                .inflate(R.layout.adapterdiscover, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.CTVartistwork.setText(allAtristListDTOList.get(position).getCategory_name());
        holder.CTVartistname.setText(allAtristListDTOList.get(position).getName());

        if(allAtristListDTOList.get(position).getSub_categories() != null){
            StringBuilder sb = new StringBuilder();

            try {
                for (int i = 0; i < categories.length(); i++) {
                    JSONObject item = categories.getJSONObject(i);
                    if (item.getString("id").equals(allAtristListDTOList.get(position).getCategory_id())) {
                        JSONArray subcat = item.getJSONArray("subcategories");
                        JSONArray dbsubcat = new JSONArray(allAtristListDTOList.get(position).getSub_categories());
                        for (int a = 0; a < dbsubcat.length(); a++) {
                            for (int b = 0; b < subcat.length(); b++) {
                                if(dbsubcat.getString(a).equals(subcat.getJSONObject(b).getString("id")))
                                    sb.append(subcat.getJSONObject(b).getString("name")).append(",");
                            }
                        }
                        break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            holder.CTVartistsubcategories.setText("Tags: " + sb.toString());
        }
        else
            holder.CTVartistsubcategories.setVisibility(View.GONE);

        if(allAtristListDTOList.get(position).getIs_online().equals("1"))
            holder.online_status.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.green));
        else
            holder.online_status.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.red));

        if (allAtristListDTOList.get(position).getArtist_commission_type().equalsIgnoreCase("0")) {
            if (allAtristListDTOList.get(position).getCommission_type().equalsIgnoreCase("0")) {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + mContext.getResources().getString(R.string.hr_add_on));
            } else if (allAtristListDTOList.get(position).getCommission_type().equalsIgnoreCase("1") && allAtristListDTOList.get(position).getFlat_type().equalsIgnoreCase("2")) {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + mContext.getResources().getString(R.string.hr_add_on));
            } else if (allAtristListDTOList.get(position).getCommission_type().equalsIgnoreCase("1") && allAtristListDTOList.get(position).getFlat_type().equalsIgnoreCase("1")) {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + mContext.getResources().getString(R.string.hr_add_on));
            } else {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + mContext.getResources().getString(R.string.hr_add_on));
            }
        } else {
            if (allAtristListDTOList.get(position).getCommission_type().equalsIgnoreCase("0")) {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + " "+mContext.getResources().getString(R.string.fixed_rate_add_on));
            } else if (allAtristListDTOList.get(position).getCommission_type().equalsIgnoreCase("1") && allAtristListDTOList.get(position).getFlat_type().equalsIgnoreCase("2")) {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + " "+mContext.getResources().getString(R.string.fixed_rate_add_on));
            } else if (allAtristListDTOList.get(position).getCommission_type().equalsIgnoreCase("1") && allAtristListDTOList.get(position).getFlat_type().equalsIgnoreCase("1")) {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + " "+mContext.getResources().getString(R.string.fixed_rate_add_on));
            } else {
                holder.CTVartistchargeprh.setText(allAtristListDTOList.get(position).getCurrency_type() + allAtristListDTOList.get(position).getPrice() + " "+mContext.getResources().getString(R.string.fixed_rate_add_on));
            }
        }


        holder.CTVlocation.setText(allAtristListDTOList.get(position).getLocation());
        holder.CTVdistance.setText(allAtristListDTOList.get(position).getDistance() + " "+mContext.getString(R.string.km));

        try {
            holder.CTVtime.setText(ProjectUtils.getDisplayableTime(ProjectUtils.correctTimestamp(Long.parseLong(allAtristListDTOList.get(position).getCreated_at()))));

        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.CTVjobdone.setText(allAtristListDTOList.get(position).getJobDone());
        holder.tvRating.setText("(" + allAtristListDTOList.get(position).getAva_rating() + "/5)");
        holder.CTVpersuccess.setText(allAtristListDTOList.get(position).getPercentages() + "%");
        Glide.with(mContext).
                load(allAtristListDTOList.get(position).getImage())
                .placeholder(R.drawable.dummyuser_image)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.IVartist);
        if (allAtristListDTOList.get(position).getFav_status().equalsIgnoreCase("1")) {
            holder.ivFav.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_fav_full));
        } else {
            holder.ivFav.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_fav_blank));
        }
        if (allAtristListDTOList.get(position).getFeatured().equalsIgnoreCase("1")) {
            holder.ivfeatured.setVisibility(View.VISIBLE);
        } else {
            holder.ivfeatured.setVisibility(View.GONE);
        }
        holder.ratingbar.setRating(Float.parseFloat(allAtristListDTOList.get(position).getAva_rating()));
        holder.rlClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mContext, ArtistProfile.class);
                in.putExtra(Consts.ARTIST_ID, allAtristListDTOList.get(position).getUser_id());
                mContext.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {

        return allAtristListDTOList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public CustomTextViewBold CTVartistname, CTVartistchargeprh;
        public CustomTextView CTVartistwork, CTVjobdone, CTVpersuccess, CTVlocation, CTVdistance, CTVtime, tvRating,CTVartistsubcategories;
        public CircleImageView IVartist;
        public RatingBar ratingbar;
        public RelativeLayout rlClick;
        public ImageView ivFav, ivfeatured;
        View online_status;

        public MyViewHolder(View view) {
            super(view);

            rlClick = view.findViewById(R.id.rlClick);
            CTVartistname = view.findViewById(R.id.CTVartistname);
            CTVartistsubcategories = view.findViewById(R.id.CTVartistsubcategories);
            CTVartistwork = view.findViewById(R.id.CTVartistwork);
            CTVjobdone = view.findViewById(R.id.CTVjobdone);
            CTVpersuccess = view.findViewById(R.id.CTVpersuccess);
            CTVartistchargeprh = view.findViewById(R.id.CTVartistchargeprh);
            CTVlocation = view.findViewById(R.id.CTVlocation);
            CTVdistance = view.findViewById(R.id.CTVdistance);
            CTVtime = view.findViewById(R.id.CTVtime);
            IVartist = view.findViewById(R.id.IVartist);
            tvRating = view.findViewById(R.id.tvRating);
            ratingbar = view.findViewById(R.id.ratingbar);
            ivFav = view.findViewById(R.id.ivFav);
            ivfeatured = view.findViewById(R.id.ivfeatured);
            online_status = view.findViewById(R.id.online_status);

        }
    }

}