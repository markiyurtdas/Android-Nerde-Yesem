package com.dev.marki.nerdeyesem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Utils.RecycleAdapter;
import com.dev.marki.nerdeyesem.Zomato.Restaurant;
import com.dev.marki.nerdeyesem.Zomato.Restaurant_;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class Detail extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        linearLayout = findViewById(R.id.linear_layout);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Restaurant_ restaurant_ ;
        restaurant_ =(Restaurant_) bundle.getSerializable("item");

        getTextViewsInfo(restaurant_);

    }

    private void getTextViewsInfo(final Restaurant_ restaurant) {

        TextView tvRestName= findViewById(R.id.detail_rest_name);
        TextView tvDistance = findViewById(R.id.distance);
        TextView tvID = findViewById(R.id.detail_id);
        TextView tvAddress = findViewById(R.id.detail_address);
        TextView tvCuisines = findViewById(R.id.detail_cuisines);
        TextView tvAvarageCost = findViewById(R.id.detail_avarage_cost);
        TextView tvAggRating = findViewById(R.id.detail_agg_rating);
        TextView tvAggText = findViewById(R.id.detail_text);
        TextView tvVotes = findViewById(R.id.detail_votes);
        TextView tvPhotos = findViewById(R.id.detail_photos);
        TextView tvMenu = findViewById(R.id.detail_menu);
        TextView tvEvents = findViewById(R.id.detail_events);
        TextView tvOnlineDelivery = findViewById(R.id.has_online);

        ImageView imageView = findViewById(R.id.detail_image);

        try{
            Picasso.get().load(restaurant.thumb).into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }

        int dist =(int )restaurant.distance;
        tvDistance.setText("("+dist+" m)");
        tvID.setText(getString(R.string.restaurant_id)+" " + restaurant.r.resId);
        tvRestName.setText(restaurant.name);
        tvAddress.setText(getString(R.string.detail_address) + restaurant.location.address);
        tvCuisines.setText(getString(R.string.cuisines )+ restaurant.cuisines);
        tvAvarageCost.setText(getString(R.string.avarage_cost)+ restaurant.averageCostForTwo + " "+restaurant.currency);
        if (restaurant.hasOnlineDelivery>0){
            tvOnlineDelivery.setText(getString(R.string.has_online) +" "+ getString(R.string.yes));
        }else {
            tvOnlineDelivery.setText(getString(R.string.has_online) +" "+ getString(R.string.no));
        }
        tvAggRating.setText(getString(R.string.aggregate_rating )+ restaurant.userRating.aggregateRating);
        tvAggText.setText(restaurant.userRating.ratingText);
        tvVotes.setText(getString(R.string.votes)+ restaurant.userRating.votes);

        tvPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(restaurant.photosUrl));
                startActivity(i);            }
        });
        tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(restaurant.menuUrl));
                startActivity(i);            }
        });
        tvEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(restaurant.eventsUrl));
                startActivity(i);            }
        });



    }
}
