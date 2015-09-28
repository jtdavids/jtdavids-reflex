package com.example.jake.jtdavids_reflex;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


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
        twoplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.2players_scores", MODE_PRIVATE);
        threeplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.3players_scores", MODE_PRIVATE);
        fourplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.4players_scores", MODE_PRIVATE);
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

        edit_avgtime_all.setText(stats.getAllTimeAvg().substring(0,5));
        edit_avgtime_10.setText(stats.getSpecifiedTimeAvg(10).substring(0,5));
        edit_avgtime_100.setText(stats.getSpecifiedTimeAvg(100).substring(0,5));

        edit_medtime_all.setText(stats.getAllTimeMed());
        edit_medtime_10.setText(stats.getSpecifiedTimeMed(10));
        edit_medtime_100.setText(stats.getSpecifiedTimeMed(100));
    }
    private void saveInFile() {
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
}
