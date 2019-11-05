package com.bw.movie.event;


public class MovieTicketsEvent {
    public String stor;
    public String seat;

    public MovieTicketsEvent(String stor, String seat) {
        this.stor = stor;
        this.seat = seat;
    }
}
