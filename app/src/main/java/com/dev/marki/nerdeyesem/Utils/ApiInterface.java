package com.dev.marki.nerdeyesem.Utils;

import com.dev.marki.nerdeyesem.Zomato.Zomato;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({
            "Accept: application/json","user-key: b1cd278b0379d67af40036bbf832f4f7"
    })
    @GET("/api/v2.1/search")
    Observable<Zomato> getRestaurantsBySearch(@Query("lat") Double latitude,
                                              @Query("lon") Double longtitude,
                                              @Query("sort") String string,
                                              @Query("count") int count);

}
