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

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Statistics_activity extends AppCompatActivity {
    SharedPreferences twoplayers_score;
    SharedPreferences threeplayers_score;
    SharedPreferences fourplayers_score;
    StatisticCalc stats;
    private static final String FILENAME = "com.example.jake.jtdavids_reflex.data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity_layout);

        //load party play scores
        twoplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.2players_scores", MODE_PRIVATE);
        threeplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.3players_scores", MODE_PRIVATE);
        fourplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.4players_scores", MODE_PRIVATE);
        //load StatisticCalc instance from data file
        loadFromFile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart(){
        super.onStart();
        updatePartyScores();
        updateSingleScore();
    }
    public void updatePartyScores(){
        //Updates all the scores of Party Mode
        TextView edit_twoplayers_1 = (TextView)findViewById(R.id.stats_t2_r2_c2);
        TextView edit_twoplayers_2 = (TextView)findViewById(R.id.stats_t2_r2_c3);

        TextView edit_threeplayers_1 = (TextView)findViewById(R.id.stats_t2_r3_c2);
        TextView edit_threeplayers_2 = (TextView)findViewById(R.id.stats_t2_r3_c3);
        TextView edit_threeplayers_3 = (TextView)findViewById(R.id.stats_t2_r3_c4);

        TextView edit_fourplayers_1 = (TextView)findViewById(R.id.stats_t2_r4_c2);
        TextView edit_fourplayers_2 = (TextView)findViewById(R.id.stats_t2_r4_c3);
        TextView edit_fourplayers_3 = (TextView)findViewById(R.id.stats_t2_r4_c4);
        TextView edit_fourplayers_4 = (TextView)findViewById(R.id.stats_t2_r4_c5);

        edit_twoplayers_1.setText(String.valueOf(twoplayers_score.getInt("player1",0)));
        edit_twoplayers_2.setText(String.valueOf(twoplayers_score.getInt("player2",0)));

        edit_threeplayers_1.setText(String.valueOf(threeplayers_score.getInt("player1",0)));
        edit_threeplayers_2.setText(String.valueOf(threeplayers_score.getInt("player2",0)));
        edit_threeplayers_3.setText(String.valueOf(threeplayers_score.getInt("player3",0)));

        edit_fourplayers_1.setText(String.valueOf(fourplayers_score.getInt("player1",0)));
        edit_fourplayers_2.setText(String.valueOf(fourplayers_score.getInt("player2",0)));
        edit_fourplayers_3.setText(String.valueOf(fourplayers_score.getInt("player3",0)));
        edit_fourplayers_4.setText(String.valueOf(fourplayers_score.getInt("player4",0)));

    }
    public void updateSingleScore(){
        //Updates all the reaction times from SingePlayer mode
        TextView edit_mintime_all = (TextView)findViewById(R.id.stats_t1_r2_c2);
        TextView edit_mintime_10 = (TextView)findViewById(R.id.stats_t1_r2_c3);
        TextView edit_mintime_100 = (TextView)findViewById(R.id.stats_t1_r2_c4);

        TextView edit_maxtime_all = (TextView)findViewById(R.id.stats_t1_r3_c2);
        TextView edit_maxtime_10 = (TextView)findViewById(R.id.stats_t1_r3_c3);
        TextView edit_maxtime_100 = (TextView)findViewById(R.id.stats_t1_r3_c4);

        TextView edit_avgtime_all = (TextView)findViewById(R.id.stats_t1_r4_c2);
        TextView edit_avgtime_10 = (TextView)findViewById(R.id.stats_t1_r4_c3);
        TextView edit_avgtime_100 = (TextView)findViewById(R.id.stats_t1_r4_c4);

        TextView edit_medtime_all = (TextView)findViewById(R.id.stats_t1_r5_c2);
        TextView edit_medtime_10 = (TextView)findViewById(R.id.stats_t1_r5_c3);
        TextView edit_medtime_100 = (TextView)findViewById(R.id.stats_t1_r5_c4);

        edit_mintime_all.setText(stats.getAllTimeMin());
        edit_mintime_10.setText(stats.getSpecifiedTimeMin(10));
        edit_mintime_100.setText(stats.getSpecifiedTimeMin(100));

        edit_maxtime_all.setText(stats.getAllTimeMax());
        edit_maxtime_10.setText(stats.getSpecifiedTimeMax(10));
        edit_maxtime_100.setText(stats.getSpecifiedTimeMax(100));

        edit_avgtime_all.setText(stats.getAllTimeAvg().substring(0, 5));
        edit_avgtime_10.setText(stats.getSpecifiedTimeAvg(10).substring(0, 5));
        edit_avgtime_100.setText(stats.getSpecifiedTimeAvg(100).substring(0, 5));

        edit_medtime_all.setText(stats.getAllTimeMed());
        edit_medtime_10.setText(stats.getSpecifiedTimeMed(10));
        edit_medtime_100.setText(stats.getSpecifiedTimeMed(100));
    }
    private void saveInFile() {
        //*** gson code design sourced from Cmput 301 Lab sessions. Author: Joshua Campbell ***
        //saves StatisticCalc instance back into data file
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(stats, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    private void loadFromFile() {
        //*** gson code design sourced from Cmput 301 Lab Sessions. Author: Joshua Campbell ***
        //load StatisticCalc instance from data file which contains a list
        //of all recorded reaction times
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            stats = gson.fromJson(in, StatisticCalc.class);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            stats = new StatisticCalc();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
    public void clearStats(View view){
        //Clears all data from StatisticCalc and for PartyPlay
        //Saves the cleared changes of StatisticCalc instance
        //Updates the Statistics table to reflect these changes
        SharedPreferences.Editor twoplayer_editor = twoplayers_score.edit();
        SharedPreferences.Editor threeplayer_editor = threeplayers_score.edit();
        SharedPreferences.Editor fourplayer_editor = fourplayers_score.edit();

        twoplayer_editor.putInt("player1", 0);
        twoplayer_editor.putInt("player2", 0);
        threeplayer_editor.putInt("player1", 0);
        threeplayer_editor.putInt("player2", 0);
        threeplayer_editor.putInt("player3", 0);
        fourplayer_editor.putInt("player1", 0);
        fourplayer_editor.putInt("player2", 0);
        fourplayer_editor.putInt("player3", 0);
        fourplayer_editor.putInt("player4", 0);
        twoplayer_editor.commit();
        threeplayer_editor.commit();
        fourplayer_editor.commit();

        stats.clear();
        saveInFile();

        updatePartyScores();
        updateSingleScore();
    }
    public void sendEmail(View view){
        //Sends email with statistics contained within the body
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"<email>"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Statistics");
        intent.putExtra(Intent.EXTRA_TEXT, stats.getStatsMessage(twoplayers_score, threeplayers_score, fourplayers_score));
        startActivity(intent);
    }
}
