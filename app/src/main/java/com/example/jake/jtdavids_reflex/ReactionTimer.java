package com.example.jake.jtdavids_reflex;

/**
 * Created by Jake on 21/09/2015.
 */
public class ReactionTimer {
    private double time_started;
    private double time_stopped;
    private double max_wait_time = 2000;
    public ReactionTimer() {
    }

    public void startTime(){
        time_started = System.currentTimeMillis();
    }
    public void stopTime(){
        time_stopped = System.currentTimeMillis()-time_started;
    }
    public double getTime(){
        return System.currentTimeMillis()-time_started;
    }
    public double getStoppedTime(){
        return time_stopped;
    }
    public double getRandomStartTime(){
        return(Math.random()*max_wait_time);
    }
}
