package com.dev.marki.nerdeyesem.Activities;

import com.dev.marki.nerdeyesem.Zomato.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Constant {


    public List<Restaurant> listItems = new ArrayList<>();

    public void addListItem(Restaurant item){
        listItems.add(item);
    }
}
