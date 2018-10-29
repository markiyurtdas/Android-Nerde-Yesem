package com.dev.marki.nerdeyesem.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.marki.nerdeyesem.Activities.Detail;
import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Zomato.Restaurant_;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    public List<Restaurant_> listItems;

    public RecycleAdapter( ) {    }

    public RecycleAdapter(Context context, List<Restaurant_> listItem) {
        this.context = context;
        this.listItems = listItem;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new ViewHolder(v, context, (ArrayList<Restaurant_>) listItems);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant_ listItem = listItems.get(position);

        holder.restName.setText(listItem.name);
        holder.restName.setPaintFlags(holder.restName.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.address.setText(holder.address.getText()+listItem.location.address);
        holder.ratingBar.setRating(Float.parseFloat(listItem.userRating.aggregateRating));
        try{
            Picasso.get().load(listItem.thumb).into(holder.image);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView restName;
        public TextView address;
        public RatingBar ratingBar;
        public TextView starsCount;
        public ImageView image;


        public ViewHolder(View view, Context ctx, ArrayList<Restaurant_> items) {
            super(view);
            listItems = items;
            //get the Activity Context
            context = ctx;

            view.setOnClickListener(this);


            restName = (TextView) view.findViewById(R.id.rest_name);
            address = (TextView) view.findViewById(R.id.address_name);
            //starsCount = (TextView) view.findViewById(R.id.stars_count);
            ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
            image =  view.findViewById(R.id.imageView);


        }

        @Override
        public void onClick(View v) {
            //Get int position
            int position = getAdapterPosition();
            Restaurant_ item = listItems.get(position);
            //ShowPopup(v,context);
            Intent intent = new Intent(context, Detail.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", listItems.get(position));
            intent.putExtras(bundle);


            context.startActivity(intent);

        }
        public void ShowPopup(View v,Context context) {

            final Dialog myDialog = new Dialog( context);

            myDialog.setContentView(R.layout.custompopup);
            //myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.80);
            int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.80);

            myDialog.getWindow().setLayout(width, height);

            myDialog.show();
        }

    }




}
