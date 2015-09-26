package com.example.jake.jtdavids_reflex;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MultiplayerFourPlayerActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_fourplayer_layout);
        Intent intent = getIntent();
        AdjustForPlayerCount(intent.getExtras().getInt("playercount"));
        builder = new AlertDialog.Builder(MultiplayerFourPlayerActivity.this);
        builder.setMessage("Be the first player to buzz their buzzer! The first buzzer hit will light up. Click RESET ROUND to start a new round. ")
                .setTitle("INSTRUCTIONS");
        AlertDialog dialog = builder.create();
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
    public void AdjustForPlayerCount(int players){
        if (players == 2){
            findViewById(R.id.Player3Button).setVisibility(View.GONE);
            findViewById(R.id.Player4Button).setVisibility(View.GONE);
        }
        else if (players == 3){
            ViewGroup.LayoutParams change_weight = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT, 0.5f);

            findViewById(R.id.Player4Button).setVisibility(View.GONE);
            findViewById(R.id.tableRow3).setLayoutParams(change_weight);
        }
    }
    public void player1_Buzzed(View view){
        view.setBackgroundColor(0xAFFF0000);
        DisplayWinner(1);
        disableBuzzers();
    }
    public void player2_Buzzed(View view){
        view.setBackgroundColor(0xAF00FF00);
        DisplayWinner(2);
        disableBuzzers();
    }
    public void player3_Buzzed(View view){
        view.setBackgroundColor(0xAF0000FF);
        DisplayWinner(3);
        disableBuzzers();
    }
    public void player4_Buzzed(View view){
        view.setBackgroundColor(0xAFF0F000);
        DisplayWinner(4);
        disableBuzzers();
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
       // findViewById(R.id.Player1Button).setEnabled(false);
        findViewById(R.id.Player2Button).setEnabled(false);
        findViewById(R.id.Player3Button).setEnabled(false);
        findViewById(R.id.Player4Button).setEnabled(false);
    }
    public void ResetBuzzers(View view){
        findViewById(R.id.Player1Button).setBackgroundColor(0x44FF0000);
        findViewById(R.id.Player2Button).setBackgroundColor(0x4400FF00);
        findViewById(R.id.Player3Button).setBackgroundColor(0x440000FF);
        findViewById(R.id.Player4Button).setBackgroundColor(0x55F0F000);
        enableBuzzers();
    }
    public void enableBuzzers(){
      //  findViewById(R.id.Player1Button).setEnabled(true);
        findViewById(R.id.Player2Button).setEnabled(true);
        findViewById(R.id.Player3Button).setEnabled(true);
        findViewById(R.id.Player4Button).setEnabled(true);
    }
}
