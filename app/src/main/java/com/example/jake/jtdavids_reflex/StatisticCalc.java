package com.example.jake.jtdavids_reflex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jake on 28/09/2015.
 */
public class StatisticCalc {
    List<Double> reaction_times = new ArrayList<Double>();
    public StatisticCalc() {
    }
    public void add(double time){
        reaction_times.add(time);
    }
    public void clear(){
        reaction_times.clear();
    }
    public String getAllTimeMin(){
        if (reaction_times.size() != 0) {
            double min = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i >= 0; i--) {
                if (reaction_times.get(i) < min) {
                    min = reaction_times.get(i);
                }
            }
            return String.valueOf(min);
        } else{
            return "N/A";
        }
    }
    public String getSpecifiedTimeMin(int length){

        if (reaction_times.size() != 0) {
            if(reaction_times.size() < length){
                length = reaction_times.size();
            }

            double min = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i > reaction_times.size()-length; i--) {
                if (reaction_times.get(i) < min) {
                    min = reaction_times.get(i);
                }
            }
            return String.valueOf(min);
        } else{
            return "N/A";
        }
    }
    public String getAllTimeMax(){
        if (reaction_times.size() !=0 ) {
            double max = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i >= 0; i--) {
                if (reaction_times.get(i) > max) {
                    max = reaction_times.get(i);
                }
            }
            return String.valueOf(max);
        } else{
            return "N/A";
        }
    }
    public String getSpecifiedTimeMax(int length){
        if (reaction_times.size() !=0 ) {
            if(reaction_times.size() < length){
                length = reaction_times.size();
            }

            double max = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i > reaction_times.size()-length; i--) {
                if (reaction_times.get(i) > max) {
                    max = reaction_times.get(i);
                }
            }
            return String.valueOf(max);
        }
        else{
            return "N/A";
        }
    }
    public String getAllTimeAvg(){
        if (reaction_times.size() !=0 ) {
            double avg = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i >= 0; i--) {
                avg = avg + reaction_times.get(i);
            }
            return String.valueOf((avg / reaction_times.size()));
        } else{
            return "N/A";
        }
    }
    public String getSpecifiedTimeAvg(int length){
        if (reaction_times.size() !=0 ) {
            if(reaction_times.size() < length){
                length = reaction_times.size();
            }

            double avg = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i > reaction_times.size()-length; i--) {
                avg = avg + reaction_times.get(i);
            }
            return String.valueOf((avg / length));
        }else{
            return "N/A";
        }
    }
    public String getAllTimeMed(){
        if (reaction_times.size() !=0 ) {
            List<Double> sorted_times = new ArrayList<Double>(reaction_times);
            Collections.sort(sorted_times);
            return String.valueOf((sorted_times.get(sorted_times.size() / 2)));
        }
        else{
            return "N/A";
        }
    }
    public String getSpecifiedTimeMed(int length){
        if (reaction_times.size() != 0 ) {
            if(reaction_times.size() < length){
                length = reaction_times.size();
            }
            List<Double> sorted_times = new ArrayList<Double>(reaction_times.subList(0, length-1));
            Collections.sort(sorted_times);

            return String.valueOf((sorted_times.get(sorted_times.size() / 2)));
        }else{
            return "N/A";
        }
    }

}
