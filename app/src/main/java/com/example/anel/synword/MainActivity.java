package com.example.anel.synword;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.*;
import android.content.Intent;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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

    public void showPlay(View view) {
        // Do something in response to button
        //((Button) view).setEnabled( false );
        Intent intent = new Intent(this, gamemodeActivity.class);
        startActivity(intent);
    }

    public void showHighscore(View view) {
        // Do something in response to button

        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }

    public void showHelp(View view) {
        // Do something in response to button

        Intent intent = new Intent(this, helpActivity.class);
        startActivity(intent);
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
