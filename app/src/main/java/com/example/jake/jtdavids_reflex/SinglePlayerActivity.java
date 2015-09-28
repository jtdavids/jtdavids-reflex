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

public class SinglePlayerActivity extends AppCompatActivity {
    private ReactionTimer timer = new ReactionTimer();
    private double wait_time;
    final Handler handler = new Handler();
    private boolean button_pressed_early;
    private Runnable reaction_wait = new Runnable(){

        public void run() {
            if(!button_pressed_early){
                timer.startTime();
                TurnOnReactionButton();
            }
            else{
                TurnOffReactionButton();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        button_pressed_early = false;
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
        DisplayReady("Get ready to click the button!");
        timer.startTime();
        wait_time = timer.getRandomStartTime();
        CheckForPrematureReaction();
        //Need to find way to delay by wait_time

        handler.postDelayed(reaction_wait ,(long) wait_time);


    }
    public void stop_time(View view){
        timer.stopTime();
        DisplayReactionTime(String.valueOf(timer.getStoppedTime() / 1000) + " s");
        TurnOffReactionButton();
    }
    public void TurnOnReactionButton(){
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setBackgroundResource(R.drawable.red_button_image);
        DisplayReactionTime("CLICK!");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_time(v);
            }
        });

    }
    public void TurnOffReactionButton(){
        ImageButton button = (ImageButton) findViewById(R.id.imageButton);
        button.setBackgroundResource(R.drawable.grey_button_image);
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
}
