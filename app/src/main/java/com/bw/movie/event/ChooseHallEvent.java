package com.bw.movie.event;


public class ChooseHallEvent {

    public String hallId;
    public String scheduleId;
    public double fare;

    public ChooseHallEvent(String hallId, String scheduleId, double fare){
        this.hallId=hallId;
        this.scheduleId=scheduleId;
        this.fare=fare;
    }
}
