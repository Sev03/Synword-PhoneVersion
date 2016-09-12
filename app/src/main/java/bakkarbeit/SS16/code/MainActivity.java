package bakkarbeit.SS16.code;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import bakkarbeit.SS16.code.loadscreens.highscoreLoadscreen;
import bakkarbeit.SS16.code.synword.R;

import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    private ImageButton btnDE;
    private ImageButton btnEN;
    private ImageButton btnLang;
    private Button btnHelp;
    private Button btnPlay;
    private Button btnScore;
    public static boolean language = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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

    public void showPlay(View view) {
        // Do something in response to button
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnPlay.getBackground().setAlpha(128);
        Intent intent = new Intent(this, gamemodeActivity.class);
        startActivity(intent);
    }

    public void showHighscore(View view) {
        // Do something in response to button
        btnScore = (Button)findViewById(R.id.btnScore);
        btnScore.getBackground().setAlpha(128);
        Intent intent = new Intent(this, highscoreLoadscreen.class);
        startActivity(intent);
    }


    public void clickEN(View view) {
        if (language == false) {
            btnEN = (ImageButton) findViewById(R.id.btnLang);
            Locale locale = new Locale("en_EN");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale.ENGLISH;
            getApplicationContext().getResources().updateConfiguration(config, null);
            recreate();
            this.language = true;
        }
    }

    public void clickDE(View view) {
        if (language == true) {
            btnDE = (ImageButton) findViewById(R.id.btnDE);
            Locale locale = new Locale("de_DE");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale.GERMAN;
            getApplicationContext().getResources().updateConfiguration(config, null);
            recreate();
            this.language = false;
        }
    }

    public void showHelp(View view) {
        // Do something in response to button
        btnHelp = (Button)findViewById(R.id.btnHelp);
        btnHelp.getBackground().setAlpha(128);
        Intent intent = new Intent(this, helpActivity.class);
        startActivity(intent);
        finish();
    }

    public void showCredits(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, creditsActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
