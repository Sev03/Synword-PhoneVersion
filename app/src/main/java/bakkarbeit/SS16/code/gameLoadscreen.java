package bakkarbeit.SS16.code;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import bakkarbeit.SS16.code.synword.R;

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
            alertDialog.setTitle(getString(R.string.alert));
            alertDialog.setMessage(getString(R.string.noInternet));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.tryagain),
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
