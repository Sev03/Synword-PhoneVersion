package bakkarbeit.SS16.code.timescreens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import bakkarbeit.SS16.code.Points;
import bakkarbeit.SS16.code.gamemodeActivity;
import bakkarbeit.SS16.code.pophighscore;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Anel on 14.12.2015.
 */
public class ts10 extends ActionBarActivity {
    public int points;
    public int round;
    public String ankerword = "Angriff";
    public String syn1 = "offensive";
    public String syn2 = "attacke";
    public String nosyn1 = "attentat";
    public String nosyn2 = "unfall";
    public String nosyn3 = "schuss";
    public String nosyn4 = "hieb";
    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Button b5;
    public Button b6;
    private int timestamp;

    ProgressBar intervallBar;    // Interval <= 10 möglich
    static final int INTERVAL = 10;

    ArrayList<String> wordlist = new ArrayList<String>();
    Points pointcounter = new Points();
    Handler countdown = new Handler();
    CountDownTimer cdTimer;

    boolean btn1isclicked = false;
    boolean btn2isclicked = false;
    boolean btn3isclicked = false;
    boolean btn4isclicked = false;
    boolean btn5isclicked = false;
    boolean btn6isclicked = false;

    private void ShuffleArray(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }
    private void   fillInWords(ArrayList<String> results) {
        String firstrow = results.get(2);
        String[] wordsplit = firstrow.split("\\s+");

        this.ankerword = wordsplit[0];
        this.syn1 = wordsplit[1];
        this.syn2 = wordsplit[2];
        this.nosyn1 = wordsplit[3];
        this.nosyn2 = wordsplit[4];
        this.nosyn3 = wordsplit[5];
        this.nosyn4 = wordsplit[6];

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bakkarbeit.SS16.code.synword.R.layout.timescreen);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        points = ((Points) intent.getExtras().get("message")).getPointcounter();
        round = ((Points) intent.getExtras().get("message")).getRound();
        wordlist = intent.getStringArrayListExtra("words");
        fillInWords(wordlist);
        intervallBar = (ProgressBar) findViewById(bakkarbeit.SS16.code.synword.R.id.intervallBar);
        TextView test = (TextView) this.findViewById(bakkarbeit.SS16.code.synword.R.id.viewPoints);
        test.setText("" + points);
        test = (TextView) findViewById(bakkarbeit.SS16.code.synword.R.id.txtRunde);
        test.setText((round + 1) + "/10" );

        //stringarray mit den synonymen und nichtsynonymen
        String[] arr = {syn1, syn2, nosyn1, nosyn2, nosyn3, nosyn4};
        b1 = (Button) findViewById(bakkarbeit.SS16.code.synword.R.id.btnWord1);
        b2 = (Button) findViewById(bakkarbeit.SS16.code.synword.R.id.btnWord2);
        b3 = (Button) findViewById(bakkarbeit.SS16.code.synword.R.id.btnWord3);
        b4 = (Button) findViewById(bakkarbeit.SS16.code.synword.R.id.btnWord4);
        b5 = (Button) findViewById(bakkarbeit.SS16.code.synword.R.id.btnWord5);
        b6 = (Button) findViewById(bakkarbeit.SS16.code.synword.R.id.btnWord6);

        TextView hauptwort = (TextView) this.findViewById(bakkarbeit.SS16.code.synword.R.id.txtWord);
        hauptwort.setText(ankerword);
        //array für positionen
        int[] array = {0,1,2,3,4,5};
        //aufruf shufflefunktion
        ShuffleArray(array);
        b1.setText(arr[array[0]]);
        b2.setText(arr[array[1]]);
        b3.setText(arr[array[2]]);
        b4.setText(arr[array[3]]);
        b5.setText(arr[array[4]]);
        b6.setText(arr[array[5]]);
        pointcounter.setPointcounter(points);

        cdTimer = new CountDownTimer(15400, 1000) {

            public void onTick(long millisUntilFinished) {
                timestamp += 1;
            }

            public void onFinish() {
                TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                String phoneID = mngr.getDeviceId();
                String gamemodus = "Zeit";
                Intent intent = new Intent(ts10.this, pophighscore.class);
                intent.putExtra("message", pointcounter);
                intent.putExtra("phoneid", phoneID);
                intent.putExtra("modi", gamemodus);
                pointcounter.setRound(10);
                startActivity(intent);
                finish();
            }

        }.start();

        intervallBar = (ProgressBar) findViewById(bakkarbeit.SS16.code.synword.R.id.intervallBar);
        intervallBar.setProgress(150);
        intervallBar.setProgress(15 * INTERVAL);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                intervallBar.setProgress(intervallBar.getProgress() - 10 / INTERVAL);
                if (intervallBar.getProgress() == 0) {
                    countdown.removeCallbacks(this);
                    Log.e("THREAD", "CANCELED");

                    return;
                }
                countdown.postDelayed(this, 1000 / INTERVAL);
            }
        };
        countdown.postDelayed(runnable, 0);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public int calcPoints(int time){
        return Math.max(0, 6 - (int) (Math.floor(time / 2)));
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, gamemodeActivity.class);
        startActivity(intent);
        cdTimer.cancel();
        countdown.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == bakkarbeit.SS16.code.synword.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int pressed = 0;
    public void onClick1 (View view){
        view.setBackgroundResource(bakkarbeit.SS16.code.synword.R.drawable.buttongreen);
        if (btn1isclicked == false) {
            pressed++;
            btn1isclicked = true;
        }
        if(b1.getText().toString()==syn1 || b1.getText().toString()==syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + calcPoints(timestamp));
        }
        if (pressed == 2) {
            showNextScreen(view);
        }
    }

    public void onClick2 (View view){
        view.setBackgroundResource(bakkarbeit.SS16.code.synword.R.drawable.buttongreen);
        if (btn2isclicked == false) {
            pressed++;
            btn2isclicked = true;
        }
        if(b2.getText().toString()==syn1 || b2.getText().toString()==syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + calcPoints(timestamp));
        }
        if (pressed == 2){
            showNextScreen(view);
        }
    }

    public void onClick3 (View view){
        view.setBackgroundResource(bakkarbeit.SS16.code.synword.R.drawable.buttongreen);
        if (btn3isclicked == false) {
            pressed++;
            btn3isclicked = true;
        }
        if(b3.getText().toString()==syn1 || b3.getText().toString()==syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + calcPoints(timestamp));
        }
        if (pressed == 2){
            showNextScreen(view);
        }
    }

    public void onClick4 (View view){
        view.setBackgroundResource(bakkarbeit.SS16.code.synword.R.drawable.buttongreen);
        if (btn4isclicked == false) {
            pressed++;
            btn4isclicked = true;
        }
        if(b4.getText().toString()==syn1 || b4.getText().toString()==syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + calcPoints(timestamp));
        }
        if (pressed == 2){
            showNextScreen(view);
        }
    }

    public void onClick5 (View view){
        view.setBackgroundResource(bakkarbeit.SS16.code.synword.R.drawable.buttongreen);
        if (btn5isclicked == false) {
            pressed++;
            btn5isclicked = true;
        }
        if(b5.getText().toString()==syn1 || b5.getText().toString()==syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + calcPoints(timestamp));
        }
        if (pressed == 2){
            showNextScreen(view);
        }
    }

    public void onClick6 (View view){
        view.setBackgroundResource(bakkarbeit.SS16.code.synword.R.drawable.buttongreen);
        if (btn6isclicked == false) {
            pressed++;
            btn6isclicked = true;
        }
        if(b6.getText().toString()==syn1 || b6.getText().toString()==syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + calcPoints(timestamp));
        }
        if (pressed == 2){
            showNextScreen(view);
        }
    }

    public void showNextScreen(View view) {
        TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        mngr.getDeviceId();
        String gamemodus = "Zeit";
        Intent intent = new Intent(this, pophighscore.class);
        intent.putExtra("message", pointcounter);
        intent.putStringArrayListExtra("words", wordlist);
        intent.putExtra("modi", gamemodus);
        pointcounter.setRound(10);
        startActivity(intent);
        cdTimer.cancel();
        countdown.removeCallbacksAndMessages(null);
        finish();
    }
}
