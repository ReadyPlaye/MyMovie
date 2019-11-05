package com.bw.movie.event;


public class MoviceCinemaEvent {
    public String tyer;
    public String cinemaId;

    public MoviceCinemaEvent(String tyer, String cinemaId){
        this.tyer=tyer;
        this.cinemaId=cinemaId;
    }
}
