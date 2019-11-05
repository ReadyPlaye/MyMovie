package com.bw.movie.event;


public class RegionEvent {

    public String regionId;
    public String tyer;

    public RegionEvent(String tyer, String regionId){
        this.tyer=tyer;
        this.regionId=regionId;
    }
}
