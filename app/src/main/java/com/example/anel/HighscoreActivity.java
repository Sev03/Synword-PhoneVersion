package com.example.anel;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.anel.synword.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Anel on 14.12.2015.
 */
public class HighscoreActivity extends ActionBarActivity {

    ArrayList<String> rangliste = new ArrayList<String>();
    String username;
    String score;
    String gamemode;
    CharSequence rankText = "";
    CharSequence scoreText = "";
    CharSequence userText = "";
    CharSequence modeText = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        rangliste = intent.getStringArrayListExtra("highscore");
        fillInHighscore(rangliste);

        TextView scoreView = (TextView)findViewById(R.id.txtRang);
        scoreView.setText(rankText);
        TextView userView = (TextView)findViewById(R.id.txtNickname);
        userView.setText(userText);
        TextView modeView = (TextView)findViewById(R.id.txtModus);
        modeView.setText(modeText);
//        TextView rankView = (TextView)findViewByID(R.id.txtGesamtscore);
//        rankView.setText(scoreText);
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

//               this.text = this.text + "#" + (i+1) + ": " + getString(R.string.tab) + score + getString(R.string.tab) + " - " +
//                       getString(R.string.tab) + username + getString(R.string.tab) + " - " +
//                       getString(R.string.tab) + getString(R.string.tab) +  gamemode + "\n";
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
