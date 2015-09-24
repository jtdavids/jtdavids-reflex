package com.example.jake.jtdavids_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplayer, menu);
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
    public void choseTwoPlayers(View view){
        Intent intent = new Intent(this, MultiplayerTwoPlayerActivity.class);
        intent.putExtra("playercount", 2);
        startActivity(intent);
    }
    public void choseThreePlayers(View view){
        Intent intent = new Intent(this, MultiplayerThreePlayerActivity.class);
        intent.putExtra("playercount", 3);
        startActivity(intent);
    }
    public void choseFourPlayers(View view){
        Intent intent = new Intent(this, MultiplayerFourPlayerActivity.class);
        intent.putExtra("playercount", 4);
        startActivity(intent);
    }

}
