package com.example.jake.jtdavids_reflex;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MultiplayerFourPlayerActivity extends AppCompatActivity {
    private String my_pref_name;
    private int player1_score;
    private int player2_score;
    private int player3_score;
    private int player4_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_fourplayer_layout);

        Intent intent = getIntent();
        AdjustForPlayerCount(intent.getExtras().getInt("playercount"));

        AlertDialog.Builder builder = new AlertDialog.Builder(MultiplayerFourPlayerActivity.this);
        builder.setMessage("Be the first player to buzz their buzzer! The first buzzer hit will light up. Click RESET ROUND to start a new round. ")
                .setTitle("INSTRUCTIONS");
        AlertDialog dialog = builder.create();

        SharedPreferences score = getSharedPreferences(my_pref_name, MODE_PRIVATE);
        player1_score = score.getInt("player1", 0);
        player2_score = score.getInt("player2", 0);
        player3_score = score.getInt("player3", 0);
        player4_score = score.getInt("player4", 0);

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplayer_game, menu);
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
    public void onPause(){
        super.onPause();

        updatePlayerScores();
    }
    public void AdjustForPlayerCount(int players){
        if (players == 2){
            findViewById(R.id.Player3Button).setVisibility(View.GONE);
            findViewById(R.id.Player4Button).setVisibility(View.GONE);
            my_pref_name = "com.example.jake.jtdavids_reflex.2players_scores";
        }
        else if (players == 3){
            ViewGroup.LayoutParams change_weight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);

            findViewById(R.id.Player4Button).setVisibility(View.GONE);
            findViewById(R.id.tableRow3).setLayoutParams(change_weight);
            my_pref_name = "com.example.jake.jtdavids_reflex.3players_scores";
        } else{
            my_pref_name = "com.example.jake.jtdavids_reflex.4players_scores";
        }
    }
    public void player1_Buzzed(View view){
        view.setBackgroundResource(R.drawable.player1_button);
        DisplayWinner(1);
        disableBuzzers();
        player1_score++;
    }
    public void player2_Buzzed(View view){
        view.setBackgroundResource(R.drawable.player2_button);
        DisplayWinner(2);
        disableBuzzers();
        player2_score++;
    }
    public void player3_Buzzed(View view){
        view.setBackgroundResource(R.drawable.player3_button);
        DisplayWinner(3);
        disableBuzzers();
        player3_score++;
    }
    public void player4_Buzzed(View view){
        view.setBackgroundResource(R.drawable.player4_button);
        DisplayWinner(4);
        disableBuzzers();
        player4_score++;
    }
    public void DisplayWinner(int winner){
        Button player1 = (Button) findViewById(R.id.Player1Button);
        Button player2 = (Button) findViewById(R.id.Player2Button);
        Button player3 = (Button) findViewById(R.id.Player3Button);
        Button player4 = (Button) findViewById(R.id.Player4Button);

        if (winner==1){
            player2.setBackgroundColor(0x22DDDDDD);
            player3.setBackgroundColor(0x22DDDDDD);
            player4.setBackgroundColor(0x22DDDDDD);
        } else if (winner==2){
            player1.setBackgroundColor(0x22DDDDDD);
            player3.setBackgroundColor(0x22DDDDDD);
            player4.setBackgroundColor(0x22DDDDDD);
        } else if (winner==3){
            player2.setBackgroundColor(0x22DDDDDD);
            player1.setBackgroundColor(0x22DDDDDD);
            player4.setBackgroundColor(0x22DDDDDD);
        } else {
            player2.setBackgroundColor(0x22DDDDDD);
            player1.setBackgroundColor(0x22DDDDDD);
            player3.setBackgroundColor(0x22DDDDDD);
        }

    }
    public void disableBuzzers() {
        findViewById(R.id.Player1Button).setClickable(false);
        findViewById(R.id.Player2Button).setClickable(false);
        findViewById(R.id.Player3Button).setClickable(false);
        findViewById(R.id.Player4Button).setClickable(false);
    }
    public void ResetBuzzers(View view){
        findViewById(R.id.Player1Button).setBackgroundColor(0x44FF0000);
        findViewById(R.id.Player2Button).setBackgroundColor(0x4400FF00);
        findViewById(R.id.Player3Button).setBackgroundColor(0x440000FF);
        findViewById(R.id.Player4Button).setBackgroundColor(0x55F0F000);
        enableBuzzers();
    }
    public void enableBuzzers(){
        findViewById(R.id.Player1Button).setClickable(true);
        findViewById(R.id.Player2Button).setClickable(true);
        findViewById(R.id.Player3Button).setClickable(true);
        findViewById(R.id.Player4Button).setClickable(true);
    }

    public void updatePlayerScores(){
        SharedPreferences score = getSharedPreferences(my_pref_name, MODE_PRIVATE);
        SharedPreferences.Editor editor = score.edit();
        editor.putInt("player1", player1_score);
        editor.putInt("player2", player2_score);
        editor.putInt("player3", player3_score);
        editor.putInt("player4", player4_score);
        editor.commit();
    }
}
