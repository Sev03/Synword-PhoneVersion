package com.example.anel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.anel.gamescreens.loadscreenActivity;
import com.example.anel.synword.R;

import java.util.ArrayList;

/**
 * Created by Anel on 14.12.2015.
 */
public class HighscoreActivity_zeit extends ActionBarActivity{

    ArrayList<String> rangliste = new ArrayList<String>();
    String username;
    String score;
    CharSequence rankText = "\n";
    CharSequence scoreText = "\n";
    CharSequence userText = "\n";
    Button modus;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore_zeit);
        getSupportActionBar().hide();

        modus = (Button) findViewById(R.id.accuracyButton);

        Intent intent = getIntent();
        rangliste = intent.getStringArrayListExtra("highscore");
        fillInHighscore(rangliste);
        TextView scoreView = (TextView)findViewById(R.id.txtRang);
        scoreView.setText(rankText );
        TextView rankView = (TextView)findViewById(R.id.txtGesamtscore);
        rankView.setText(scoreText);
        TextView userView = (TextView)findViewById(R.id.txtNickname);
        userView.setText(userText);
    }

    private void   fillInHighscore(ArrayList<String> results) {
        int i=0;

           while (i<results.size()){

               String firstrow = results.get(i);
               String[] wordsplit = firstrow.split("\\s+");

               this.username = wordsplit[0];
               this.score = wordsplit[1];
               this.rankText = this.rankText + "#" + (i+1) + ": " +"\n";
               this.scoreText = this.scoreText + score +"\n";
               this.userText = this.userText + username + "\n";
               i++;
       }
    }

    public void onToggle(View view) {
        Intent intent = new Intent(this, highscoreLoadscreen.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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
