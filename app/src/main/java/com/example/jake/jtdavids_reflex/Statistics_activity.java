package com.example.jake.jtdavids_reflex;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Statistics_activity extends AppCompatActivity {
    SharedPreferences twoplayers_score;
    SharedPreferences threeplayers_score;
    SharedPreferences fourplayers_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity_layout);
        twoplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.2players_scores", MODE_PRIVATE);
        threeplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.3players_scores", MODE_PRIVATE);
        fourplayers_score = getSharedPreferences("com.example.jake.jtdavids_reflex.4players_scores", MODE_PRIVATE);
        updatePartyScores();
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
}
