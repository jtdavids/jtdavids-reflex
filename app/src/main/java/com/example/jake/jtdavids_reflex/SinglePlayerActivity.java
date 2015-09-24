package com.example.jake.jtdavids_reflex;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SinglePlayerActivity extends AppCompatActivity {
    private ReactionTimer timer = new ReactionTimer();
    private double wait_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
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
        DisplayReactionTime("...");
        timer.startTime();
        wait_time = timer.getRandomStartTime();
        CheckForPrematureReaction();
        while(timer.getTime() < wait_time){

        }

        timer.startTime();
        TurnOnReactionButton();
    }
    public void stop_time(View view){
        timer.stopTime();
        DisplayReactionTime(String.valueOf(timer.getStoppedTime()/1000) + " s");
        TurnOffReactionButton();
    }
    public void TurnOnReactionButton(){
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setBackgroundResource(R.drawable.button_icon_red);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_time(v);
            }
        });

    }
    public void TurnOffReactionButton(){
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setBackgroundResource(R.drawable.button_icon_grey);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_time(v);
            }
        });
    }
    public void CheckForPrematureReaction(){
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayReactionTime("TOO SOON!");
                timer.startTime();
                while(timer.getTime() < 2000){

                }
                start_time(v);
            }
        });
    }
    public void DisplayReactionTime(String message){
        TextView time = (TextView) findViewById(R.id.ClickPromptText);
        time.setText(message);
    }
}
