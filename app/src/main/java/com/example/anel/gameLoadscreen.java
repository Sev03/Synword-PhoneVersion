package com.example.anel;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;

import com.example.anel.synword.R;

/**
 * Created by III-Sev-III on 11.04.2016.
 */
public class gameLoadscreen extends Activity {
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    private ProgressBar spinner;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadmain);

        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if(isInternetPresent){
            Thread welcomeThread = new Thread() {

                @Override
                public void run() {
                    try {
                        super.run();
                        sleep(1500); //Delay of 1,5 seconds
                        spinner = (ProgressBar)findViewById(R.id.progressBar1);
                        spinner.setVisibility(View.VISIBLE);
                    } catch (Exception e) {

                    } finally {

                        Intent i = new Intent(gameLoadscreen.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            };
            welcomeThread.start();
        } else {
            // Internet connection is not present
            // Ask user to connect to Internet
            AlertDialog alertDialog = new AlertDialog.Builder(gameLoadscreen.this).create();
            alertDialog.setTitle("Achtung");
            alertDialog.setMessage("Es besteht keine Internetverbindung! Bitte überprüfe!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Erneut versuchen ",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    });
            alertDialog.show();
        }

    }
}
