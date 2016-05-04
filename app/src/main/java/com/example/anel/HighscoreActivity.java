package com.example.anel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.anel.synword.R;
import java.util.ArrayList;

/**
 * Created by Anel on 14.12.2015.
 */
public class HighscoreActivity extends ActionBarActivity {

    ArrayList<String> rangliste = new ArrayList<String>();
    String username;
    String score;
    String gamemode;
    CharSequence rankText = "\n";
    CharSequence scoreText = "\n";
    CharSequence userText = "\n";
    CharSequence modeText = "\n";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        rangliste = intent.getStringArrayListExtra("highscore");
        fillInHighscore(rangliste);

        TextView scoreView = (TextView)findViewById(R.id.txtRang);
        scoreView.setText(rankText);
        //        TextView rankView = (TextView)findViewByID(R.id.txtGesamtscore);
//        rankView.setText(scoreText);
        TextView userView = (TextView)findViewById(R.id.txtNickname);
        userView.setText(userText);
        TextView modeView = (TextView)findViewById(R.id.txtModus);
        modeView.setText(modeText);

    }

    private void   fillInHighscore(ArrayList<String> results) {
        int i=0;

           while (i<results.size()){

               String firstrow = results.get(i);
               String[] wordsplit = firstrow.split("\\s+");

               this.username = wordsplit[0];
               this.score = wordsplit[1];
               this.gamemode = wordsplit[2];
               this.rankText = this.rankText + "#" + (i+1) + ": " +"\n";
               this.scoreText = this.scoreText + score +"\n";
               this.userText = this.userText + username + "\n";
               this.modeText = this.modeText + gamemode + "\n";
               i++;

       }

    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



}
