package com.example.anel.synword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by III-Sev-III on 17.02.2016.
 */
public class pophighscore extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popuphighscore);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int) (height*.8));

        Intent intent = getIntent();
        int points = ((Points) intent.getExtras().get("message")).getPointcounter();
        int round = ((Points) intent.getExtras().get("message")).getRound();

        TextView test = (TextView) this.findViewById(R.id.txtPunkte);
        test.setText("" + points);

        EditText t = (EditText)findViewById(R.id.editText);
        t.setGravity(Gravity.CENTER);
    }

    public void onDismiss(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, gamemodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, gamemodeActivity.class);
        startActivity(intent);
    }
}
