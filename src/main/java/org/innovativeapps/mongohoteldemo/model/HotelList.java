package org.innovativeapps.mongohoteldemo.model;

import java.util.ArrayList;
import java.util.List;

public class HotelList {
    private List<Hotel> allHotels;

    public HotelList() { allHotels = new ArrayList<>(); }

    public List<Hotel> getAllHotels() { return allHotels; }

    public void setAllHotels(List<Hotel> allHotels) { this.allHotels.addAll(allHotels); }

}
