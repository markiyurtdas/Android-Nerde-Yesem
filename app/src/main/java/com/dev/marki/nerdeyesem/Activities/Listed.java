package com.dev.marki.nerdeyesem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Utils.APIClient;
import com.dev.marki.nerdeyesem.Utils.ApiInterface;
import com.dev.marki.nerdeyesem.Utils.RecycleAdapter;
import com.dev.marki.nerdeyesem.Zomato.Restaurant;
import com.dev.marki.nerdeyesem.Zomato.Restaurant_;
import com.dev.marki.nerdeyesem.Zomato.Zomato;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Listed extends AppCompatActivity {

    private RecyclerView reciclerView;
    private RecyclerView.Adapter adapter;
    private List<Restaurant> listItems;
    Zomato zomato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed);

        final int restaurantCount = 5;

        listItems = new ArrayList<>();



        ApiInterface servis = APIClient.getClient().create(ApiInterface.class);
        //38.4623322,27.2212547 , 5
         servis.getRestaurantsBySearch(38.4623322,27.2212547,5);
        cagri.enqueue(new Callback<Zomato>() {
            @Override
            public void onResponse(Call<Zomato> call, Response<Zomato> response) {
                zomato = response.body();

                for (int i =0 ; i<restaurantCount;i++) {
                    Constant cos = new Constant();
                    cos.listItems.add(zomato.restaurants.get(i));
                }
            }

            @Override
            public void onFailure(Call<Zomato> call, Throwable t) {
            }
        });
        reciclerView = (RecyclerView) findViewById(R.id.reciclerView);
        reciclerView.setHasFixedSize(true);

        reciclerView.setLayoutManager(new LinearLayoutManager(this));

        Constant cos = new Constant();
        listItems = cos.listItems;
        Toast.makeText(this, listItems.size()+"", Toast.LENGTH_SHORT).show();

        adapter = new RecycleAdapter(this, listItems);
        reciclerView.setAdapter(adapter);
    }


    public void addItemToList(List<Restaurant> listItems, Restaurant o){
        listItems.add(o);
    }
}








/*
*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity {
    private RecyclerView reciclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reciclerView = (RecyclerView) findViewById(R.id.reciclerView);
        reciclerView.setHasFixedSize(true);
        //every item has a fixed size
        reciclerView.setLayoutManager(new
                LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i<10; i++) {
            ListItem listItem = new ListItem(
                    "Item " + (i+1),
                    "Description"
            );
            listItems.add(listItem);
        }

        adapter = new MyAdapter(this, listItems);

        reciclerView.setAdapter(adapter);

    }
}
* */