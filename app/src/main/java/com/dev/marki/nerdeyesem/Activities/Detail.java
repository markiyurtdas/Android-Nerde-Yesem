package com.dev.marki.nerdeyesem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Utils.RecycleAdapter;
import com.dev.marki.nerdeyesem.Zomato.Restaurant;
import com.dev.marki.nerdeyesem.Zomato.Restaurant_;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Detail extends AppCompatActivity {
    LinearLayout linearLayout;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        linearLayout = findViewById(R.id.linear_layout);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Restaurant_ restaurant_ ;
        restaurant_ =(Restaurant_) bundle.getSerializable("item");
        TextView detailRestName = findViewById(R.id.detail_rest_name);
        TextView distance = findViewById(R.id.distance);
        imageView = findViewById(R.id.detail_image);

        try{
            Picasso.get().load(restaurant_.thumb).into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }


//        String[] items = {"id","address","cuisines","average_cost_for_two","currency","user_rating"
//                            ,"photos_url","menu_url","events_url"};
//        for(int i=0;i<items.length-1;i++){
//            TextView tv = new TextView(this);
//            tv.setTextSize(16);
//            String str = items[i];
//            tv.setText(restaurant_.);
//            linearLayout.addView(tv);
//        }

        int dist = (int)restaurant_.distance;
        distance.setText("(~ "+dist+" m)");
        detailRestName.setText(restaurant_.name);
        String str = restaurant_.toString();
        TextView tv = new TextView(this);

        tv.setTextSize(16);
        tv.setText(str);
        linearLayout.addView(tv);


    }



    private void userRatin(){
        /*"user_rating": {
          "aggregate_rating": "3.7",
          "rating_text": "Good",
          "rating_color": "9ACD32",
          "votes": "11"
        },*/
    }



}
