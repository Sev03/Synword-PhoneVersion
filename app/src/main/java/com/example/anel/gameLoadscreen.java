package com.example.anel;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.anel.synword.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by III-Sev-III on 11.04.2016.
 */
public class gameLoadscreen extends Activity {
    ConnectionDetector cd;
    Boolean isInternetPresent = false;
    private ProgressBar spinner;
    String version = "1.0.0";
    LoadscreenSub task = new LoadscreenSub();
    Thread welcomeThread;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadmain);

        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();
        if(isInternetPresent){
            welcomeThread = new Thread() {

                @Override
                public void run() {

                    try {
                        super.run();
                        sleep(1500); //Delay of 1.5 seconds
                        spinner = (ProgressBar) findViewById(R.id.progressBar1);
                        spinner.setVisibility(View.VISIBLE);

                    } catch (Exception e) {
                    } finally {
                        task.execute();
                    }
                }
            };welcomeThread.start();
        } else {
            // Internet connection is not present
            // Ask user to connect to Internet
            AlertDialog alertDialog = new AlertDialog.Builder(gameLoadscreen.this).create();
            alertDialog.setTitle(gameLoadscreen.this.getString(R.string.alert));
            alertDialog.setMessage(gameLoadscreen.this.getString(R.string.noInternet));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, gameLoadscreen.this.getString(R.string.tryagain),
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

    public class LoadscreenSub extends AsyncTask<String, Void, String> {
        InputStream is;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        Exception exception;

        @Override
        protected String doInBackground(String... strings) {

            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://felf.ga:25571/synword_version.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }catch(Exception e){
                Log.e("log_tag", "Fehler bei der http Verbindung " + e.toString());
            }

            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                is.close();
                result=sb.toString();
            }catch(Exception e){
                Log.e("log_tag", "Error converting result "+e.toString());
            }
            return result;
        }
        protected void onPostExecute(String result) {

            if(result.equals(version)){
                    AlertDialog alertDialog = new AlertDialog.Builder(gameLoadscreen.this).create();
                    alertDialog.setTitle(gameLoadscreen.this.getString(R.string.alert));
                    alertDialog.setMessage(gameLoadscreen.this.getString(R.string.versiontext));
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, gameLoadscreen.this.getString(R.string.versionclick),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();
            }
            else {
                Intent intent = new Intent(gameLoadscreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
