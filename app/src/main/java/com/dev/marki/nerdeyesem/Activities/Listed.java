package com.dev.marki.nerdeyesem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.dev.marki.nerdeyesem.R;
import com.dev.marki.nerdeyesem.Utils.APIClient;
import com.dev.marki.nerdeyesem.Utils.ApiInterface;
import com.dev.marki.nerdeyesem.Utils.RecycleAdapter;
import com.dev.marki.nerdeyesem.Zomato.Restaurant_;
import com.dev.marki.nerdeyesem.Zomato.Zomato;
import java.util.ArrayList;
import java.util.List;


public class Listed extends AppCompatActivity {

    public int restaurantCount = 21;
    ApiInterface apiFace;
    private RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back button to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        Retrofit retrofit = APIClient.getClient();
        apiFace = retrofit.create(ApiInterface.class);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //runLayoutAnimation(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData() {
        //Def Location is Bornova's location
        final Intent intent = this.getIntent();
        double lat = 38.463795415621256;double lon = 28.42412240566057;

        lat  = Double.parseDouble(intent.getStringExtra("latitude"));
        lon = Double.parseDouble(intent.getStringExtra("longtitude"));

        compositeDisposable.add(apiFace.getRestaurantsBySearch(lat,lon,"real_distance",restaurantCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Zomato>() {
                    @Override
                    public void accept(Zomato zomato) throws Exception {

                        double lat  = Double.parseDouble(intent.getStringExtra("latitude"));
                        double lon = Double.parseDouble(intent.getStringExtra("longtitude"));

                        displayData(zomato, lat,lon);
                    }
                })
        );

    }
    private void displayData(Zomato zomato,double lat,double lon){
        List<Restaurant_> listItems = new ArrayList<>();
        for (int i = 0; i<zomato.restaurants.size();i++){
            double resLat = Double.parseDouble(zomato.restaurants.get(i).restaurant.location.latitude);
            double resLon = Double.parseDouble(zomato.restaurants.get(i).restaurant.location.longitude);
            zomato.restaurants.get(i).restaurant.distance = distance(lat,resLat,lon,resLon);
            listItems.add(zomato.restaurants.get(i).restaurant);
        }

        RecycleAdapter adapter  = new RecycleAdapter(this,listItems);
        runLayoutAnimation(recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Geri tuşu basıldı", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public  double distance(double lat1, double lat2, double lon1,
                            double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }





}