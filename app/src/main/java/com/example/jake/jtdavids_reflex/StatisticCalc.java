package com.example.jake.jtdavids_reflex;

import android.content.SharedPreferences;

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
        //gets the minimum time of all recorded reaction times
        //if no reaction times are recored, return 'N/A'
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
        //Gets the minimum reaction time of the last X reactions
        //a negative value should not be passed into this method
        //Since reactions are stored in a list, must iterate backwards through the list
        //to retrieve reaction times in chronological order
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
        //gets the maximum reaction time of all reactions
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
        //Gets the maximum reaction time of the last X reactions
        //a negative value should not be passed into this method
        //Since reactions are stored in a list, must iterate backwards through the list
        //to retrieve reaction times in chronological order
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
        //gets the average reaction time of all reactions
        if (reaction_times.size() !=0 ) {
            double avg = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i >= 0; i--) {
                avg = avg + reaction_times.get(i);
            }
            return String.valueOf((avg / reaction_times.size()));
        } else{
            return "N/A   ";
        }
    }
    public String getSpecifiedTimeAvg(int length){
        //Gets the average reaction time of the last X reactions
        //a negative value should not be passed into this method
        //Since reactions are stored in a list, must iterate backwards through the list
        //to retrieve reaction times in chronological order
        if (reaction_times.size() !=0 ) {
            if(reaction_times.size() < length){
                length = reaction_times.size();
            }

            double avg = reaction_times.get(reaction_times.size()-1);
            for (int i = reaction_times.size()-2; i >= reaction_times.size()-length; i--) {
                avg = avg + reaction_times.get(i);
            }
            return String.valueOf((avg / length));
        }else{
            return "N/A   ";
        }
    }
    public String getAllTimeMed(){
        //gets the median reaction time of all reactions
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
        //Gets the median reaction time of the last X reactions
        //a negative value should not be passed into this method
        if (reaction_times.size() != 0 ) {
            if(reaction_times.size() < length){
                length = reaction_times.size();
            }
            List<Double> sorted_times = new ArrayList<Double>(reaction_times.subList(0, length));
            Collections.sort(sorted_times);

            return String.valueOf((sorted_times.get(sorted_times.size() / 2)));
        }else{
            return "N/A";
        }
    }

    public String getStatsMessage(SharedPreferences twoplayers_score, SharedPreferences threeplayers_score, SharedPreferences fourplayers_score){

        return ("______SINGLEPLAYER______\n" +
                "      MIN TIME:\n" +
                "All Time: " + getAllTimeMin() + "\nLast 10 times: " + getSpecifiedTimeMin(10) + "\nLast 100 times: " + getSpecifiedTimeMin(100) + "\n" +
                "      MAX TIME:\n" +
                "All Time: " + getAllTimeMax() + "\nLast 10 times: " + getSpecifiedTimeMax(10) + "\nLast 100 times: " + getSpecifiedTimeMax(100) + "\n" +
                "      AVERAGE TIME:\n" +
                "All Time: " + getAllTimeAvg() + "\nLast 10 times: " + getSpecifiedTimeAvg(10) + "\nLast 100 times: " + getSpecifiedTimeAvg(100) + "\n" +
                "      MEDIAN TIME:\n" +
                "All Time: " + getAllTimeMed() + "\nLast 10 times: " + getSpecifiedTimeMed(10) + "\nLast 100 times: " + getSpecifiedTimeMed(100) + "\n" +
                "______PARTY PLAY______\n" +
                "      2 PLAYERS:\n" +
                "Player 1: " + String.valueOf(twoplayers_score.getInt("player1", 0)) + "\nPlayer 2: " + String.valueOf(twoplayers_score.getInt("player2", 0)) + "\n" +
                "      3 PLAYERS:\n" +
                "Player 1: " + String.valueOf(threeplayers_score.getInt("player1", 0)) + "\nPlayer 2: " + String.valueOf(threeplayers_score.getInt("player2", 0)) + "\nPlayer 3: " + String.valueOf(threeplayers_score.getInt("player3", 0)) + "\n" +
                "      4 PLAYERS:\n" +
                "Player 1: " + String.valueOf(fourplayers_score.getInt("player1", 0)) + "\nPlayer 2: " + String.valueOf(fourplayers_score.getInt("player2", 0)) + "\nPlayer 3: " + String.valueOf(fourplayers_score.getInt("player3", 0)) + "\nPlayer 4: " + String.valueOf(fourplayers_score.getInt("player4", 0)) + "\n");
    }

}
