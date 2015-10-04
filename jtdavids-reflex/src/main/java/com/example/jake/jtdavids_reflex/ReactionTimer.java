/*
{{ jtdavids-reflex }}
Copyright (C) 2015 Jake Davidson

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
    public double getStoppedTime(){
        return time_stopped;
    }
    public double getRandomStartTime(){
        //gets a random time to wait between 10 and 2000 ms
        return(Math.random()*max_wait_time+10);
    }
}
