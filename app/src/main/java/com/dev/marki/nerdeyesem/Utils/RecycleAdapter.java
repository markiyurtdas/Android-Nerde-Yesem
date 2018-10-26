package com.dev.marki.nerdeyesem.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Zomato.Restaurant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context context;
    private List<Restaurant> listItems;

    public RecycleAdapter(Context context, List listItem) {
        this.context = context;
        this.listItems = listItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new ViewHolder(v, context, (ArrayList<Restaurant>) listItems);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant listItem = listItems.get(position);
        holder.restName.setText(listItem.restaurant.name);
        holder.address.setText(listItem.restaurant.location.address);

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView restName;
        public TextView address;

        public ViewHolder(View view, Context ctx, ArrayList<Restaurant> items) {
            super(view);
            listItems = items;
            //get the Activity Context
            context = ctx;

            view.setOnClickListener(this);

            restName = (TextView) view.findViewById(R.id.title);
            address = (TextView) view.findViewById(R.id.address_name);

        }

        @Override
        public void onClick(View v) {
            //Get int position
            int position = getAdapterPosition();
            Restaurant item = listItems.get(position);
          //  Intent intent = new Intent(context, MyActivity.class);
            Toast.makeText(context, item.restaurant.name, Toast.LENGTH_SHORT).show();
        }
    }
}
