package com.bw.movie.event;


public class ComingFilmEvent {
    public int tyer;
    public int movieId;

    public ComingFilmEvent(int tyer, int movieId){
        this.tyer=tyer;
        this.movieId=movieId;
    }
}
