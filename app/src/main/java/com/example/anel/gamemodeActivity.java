package com.example.anel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.anel.synword.R;
import com.example.anel.gamescreens.loadscreenActivity;
import com.example.anel.timescreens.tsloadscreenActivity;

/**
 * Created by Anel on 14.12.2015.
 */
public class gamemodeActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener{

    Switch switchLang;
    String sprache = "DE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemode);
        getSupportActionBar().hide();

        switchLang = (Switch) findViewById(R.id.switchLang);
        switchLang.setOnCheckedChangeListener(this);
        Button b = (Button) findViewById(R.id.btnInfo);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(gamemodeActivity.this,Pop.class));
            }
        });

    }


    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            sprache = "EN";
            Toast.makeText(getApplicationContext(), "Sprache ist "+sprache, Toast.LENGTH_SHORT).show();

        } else {
            sprache = "DE";
            Toast.makeText(getApplicationContext(), "Sprache ist "+sprache, Toast.LENGTH_SHORT).show();

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

    public void showFehlerfrei(View view) {
        // Do something in response to button
        Toast.makeText(getApplicationContext(), "Sprache ist "+sprache, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, loadscreenActivity.class);
        intent.putExtra("sprache", sprache);
        startActivity(intent);
    }

    public void showZeit(View view) {
        // Do something in response to button
        Toast.makeText(getApplicationContext(), "Sprache ist "+sprache, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, tsloadscreenActivity.class);
        intent.putExtra("sprache", sprache);
        startActivity(intent);
    }

}
