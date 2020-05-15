package com.example.wanderlust;

public class PlaceItem {

    private String placeName;
    private int placeImage;

    public PlaceItem(String placeName, int placeImage){
        this.placeName = placeName;
        this.placeImage = placeImage;
    }

    public String getPlaceName(){
        return placeName;
    }

    public int getPlaceImage(){
        return placeImage;
    }
}
