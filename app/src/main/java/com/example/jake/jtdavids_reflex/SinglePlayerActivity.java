package com.example.jake.jtdavids_reflex;

import android.app.AlertDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
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

public class SinglePlayerActivity extends AppCompatActivity {
    private ReactionTimer timer = new ReactionTimer();
    private StatisticCalc stats;
    private double wait_time;
    final Handler handler = new Handler();
    private boolean button_pressed_early;
    private static final String FILENAME = "com.example.jake.jtdavids_reflex.data";
    private Runnable reaction_wait = new Runnable(){

        public void run() {
            if(!button_pressed_early){
                //if button wasnt pressed too early, continue the reaction test
                timer.startTime();
                TurnOnReactionButton();
            }
            else{
                //if pressed too early, stop the reaction test and reset.
                TurnOffReactionButton();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        button_pressed_early = false;
        //load the statcalc instance
        loadFromFile();

        //build and show pop up containing instructions on how to play
        AlertDialog.Builder builder = new AlertDialog.Builder(SinglePlayerActivity.this);
        builder.setMessage("Click the grey button to start a reaction test. Wait for the button to turn red before clicking.")
                .setTitle("Instructions");
        AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_player, menu);
        return true;
    }
    @Override
    public void onPause() {
        super.onPause();
        saveInFile();
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

    public void start_time(View view){
        //Initialize conditions for a new reaction test
        DisplayReactionTime("...");
        DisplayReady("Get ready to click the button!");
        timer.startTime();
        wait_time = timer.getRandomStartTime();

        //Set button to check for an early press
        CheckForPrematureReaction();

        //after random wait time, handler will either proceed with the reaction test
        //or stop the reaction test depending if the button was pressed too early.
        //doesn't lock user out of UI while waiting
        handler.postDelayed(reaction_wait ,(long) wait_time);


    }
    public void stop_time(View view){
        //stop and record reaction time
        timer.stopTime();
        DisplayReactionTime(String.valueOf(timer.getStoppedTime() / 1000) + " s");
        stats.add(timer.getStoppedTime() / 1000);

        //turn off the test and wait for a new test to begin
        TurnOffReactionButton();
    }
    public void TurnOnReactionButton(){
        //prompt user to click the button
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setBackgroundResource(R.drawable.red_button_image);
        DisplayReactionTime("CLICK!");

        //switch button from checking for an early press to a valid press
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_time(v);
            }
        });

    }
    public void TurnOffReactionButton(){
        //swap reaction button back to grey and prompt user to start a new test
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setBackgroundResource(R.drawable.grey_button_image);
        DisplayReady("Click to start a new test!");

        //reset the button to wait for a new rest to begin
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_pressed_early = false;
                start_time(v);
            }
        });
    }
    public void CheckForPrematureReaction(){
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        //set button to complain if pressed too early
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayReactionTime("TOO SOON!");
                button_pressed_early = true;

            }
        });
    }
    public void DisplayReactionTime(String message){
        TextView time = (TextView) findViewById(R.id.ClickPromptText);
        time.setText(message);
    }
    public void DisplayReady(String message){
        TextView msg = (TextView) findViewById(R.id.GetReadyText);
        msg.setText(message);
    }
    private void saveInFile() {
        //*** code design sourced from the Cmput 301 Lab sessions ***
        //Save instance of StatisticCalc to data file
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
        //*** code design sourced from the Cmput 301 Lab sessions ***
        //load the instance of StatisticCalc from data file
        //contains list of all previous reaction times
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
