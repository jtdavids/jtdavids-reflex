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
        findViewById(R.id.Player1Button).setBackgroundColor(0xAFFF000);
        builder.setMessage("Click to reset the buzzers.")
                .setTitle("Player 1 Buzzed First!");
        DisplayWinner();
    }
    public void player2_Buzzed(View view){
        findViewById(R.id.Player2Button).setBackgroundColor(0xAFFF000);
        builder.setMessage("Click to reset the buzzers.")
                .setTitle("Player 2 Buzzed First!");
        DisplayWinner();
    }
    public void player3_Buzzed(View view){
        findViewById(R.id.Player3Button).setBackgroundColor(0xAFF0000);
        builder.setMessage("Click to reset the buzzers.")
                .setTitle("Player 3 Buzzed First!");
        DisplayWinner();
    }
    public void player4_Buzzed(View view){
        findViewById(R.id.Player4Button).setBackgroundColor(0xAFF0000);
        builder.setMessage("Click to reset the buzzers.")
                .setTitle("Player 4 Buzzed First!");
        DisplayWinner();
    }
    public void DisplayWinner(){
        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ResetBuzzers();
            }
        });
        dialog.show();

    }
    public void ResetBuzzers(){
       findViewById(R.id.Player1Button).setBackgroundResource(android.R.drawable.btn_default);
       findViewById(R.id.Player2Button).setBackgroundResource(android.R.drawable.btn_default);
       findViewById(R.id.Player3Button).setBackgroundResource(android.R.drawable.btn_default);
       findViewById(R.id.Player4Button).setBackgroundResource(android.R.drawable.btn_default);
    }
}
