package com.example.anel.synword;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.anel.synword.gamescreens.gamescreenActivity;
import com.example.anel.synword.gamescreens.loadscreenActivity;
import com.example.anel.synword.timescreens.timescreen;

/**
 * Created by Anel on 14.12.2015.
 */
public class gamemodeActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemode);
        getSupportActionBar().hide();

        Button b = (Button) findViewById(R.id.btnInfo);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(gamemodeActivity.this,Pop.class));
            }
        });

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

    public void showFehlerfrei(View view) {
        // Do something in response to button

        Intent intent = new Intent(this, loadscreenActivity.class);
        startActivity(intent);
    }

    public void showZeit(View view) {
        // Do something in response to button

        Intent intent = new Intent(this, timescreen.class);
        startActivity(intent);
    }

}
