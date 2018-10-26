package com.dev.marki.nerdeyesem.Utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.marki.nerdeyesem.Activities.Detail;
import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Zomato.Restaurant_;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    private List<Restaurant_> listItems;

    public RecycleAdapter(Context context, List<Restaurant_> listItem) {
        this.context = context;
        this.listItems = listItem;
        Log.d("itemvar",listItems.get(2).name);

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
        holder.starsCount.setText("("+listItem.userRating.aggregateRating+")");


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


        public ViewHolder(View view, Context ctx, ArrayList<Restaurant_> items) {
            super(view);
            listItems = items;
            //get the Activity Context
            context = ctx;

            view.setOnClickListener(this);


            restName = (TextView) view.findViewById(R.id.rest_name);
            address = (TextView) view.findViewById(R.id.address_name);
            starsCount = (TextView) view.findViewById(R.id.stars_count);
            ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);


        }

        @Override
        public void onClick(View v) {
            //Get int position
            int position = getAdapterPosition();
            Restaurant_ item = listItems.get(position);
            Intent intent = new Intent(context,Detail.class);

            context.startActivity(intent);
          //  Intent intent = new Intent(context, MyActivity.class);
        }
    }
}
