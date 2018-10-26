package com.dev.marki.nerdeyesem.Zomato;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Zomato {

    @SerializedName("results_found")
    @Expose
    public Integer resultsFound;
    @SerializedName("results_start")
    @Expose
    public Integer resultsStart;
    @SerializedName("results_shown")
    @Expose
    public Integer resultsShown;
    @SerializedName("restaurants")
    @Expose
    public List<Restaurant> restaurants = null;

}