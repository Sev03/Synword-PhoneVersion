package com.example.anel.synword.timescreens;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.anel.synword.Points;
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
import java.util.Random;

/**
 * Created by Anel on 14.12.2015.
 */
public class timescreen extends ActionBarActivity {

    InputStream is;
    ArrayList<String> results = new ArrayList<String>();
    JSONObject json_data;
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

    ProgressBar intervallBar;
    // Interval <= 10 möglich
    static final int INTERVAL = 10;

    Handler countdown = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timescreen);
        getSupportActionBar().hide();
        getData();

        intervallBar = (ProgressBar) findViewById(R.id.intervallBar);
        b1 = (Button) findViewById(R.id.btnWord1);
        b2 = (Button) findViewById(R.id.btnWord2);
        b3 = (Button) findViewById(R.id.btnWord3);
        b4 = (Button) findViewById(R.id.btnWord4);
        b5 = (Button) findViewById(R.id.btnWord5);
        b6 = (Button) findViewById(R.id.btnWord6);

        //stringarray mit den synonymen und nichtsynonymen
        String[] arr = {syn1, syn2, nosyn1, nosyn2, nosyn3, nosyn4};

        TextView test = (TextView) this.findViewById(R.id.txtWord);
        test.setText(ankerword);
        //array für positionen
        int[] array = {0, 1, 2, 3, 4, 5};
        //aufruf shufflefunktion
        ShuffleArray(array);
        b1.setText(arr[array[0]]);
        b2.setText(arr[array[1]]);
        b3.setText(arr[array[2]]);
        b4.setText(arr[array[3]]);
        b5.setText(arr[array[4]]);
        b6.setText(arr[array[5]]);

        intervallBar.setProgress(100);

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
        countdown.postDelayed(runnable, 1000 / INTERVAL);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getData() {
        String result = "";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        try {
            HttpClient httpclient = new DefaultHttpClient();
            // TODO SERVER EINFÜGEN
            HttpPost httppost = new HttpPost("http://deinehomepage.de/deinephpDatei.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log.e("log_tag", "Fehler bei der http Verbindung " + e.toString());
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
        }

        try {
            JSONArray jArray = new JSONArray(result);
            for (int i = 0; i < jArray.length(); i++) {
                json_data = jArray.getJSONObject(i);

                // TODO Ankerword, Syn1, Syn2, NoSyn1, NoSyn2, NoSyn3, NoSyn4,
                results.add((String) json_data.get("id") + " " + json_data.get("name"));
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }

    private void ShuffleArray(int[] array) {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }

    boolean btn1isclicked = false;
    boolean btn2isclicked = false;
    boolean btn3isclicked = false;
    boolean btn4isclicked = false;
    boolean btn5isclicked = false;
    boolean btn6isclicked = false;
    Points pointcounter = new Points();


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

    int pressed = 0;

    public void onClick1(View view) {
        view.setBackgroundColor(Color.parseColor("#FF3798D9"));
        if (btn1isclicked == false) {
            pressed++;
            btn1isclicked = true;
        }
        if (b1.getText().toString() == syn1 || b1.getText().toString() == syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + 5);
        }

        if (pressed == 2) {
            showNextScreen(view);
        }


    }

    public void onClick2(View view) {
        view.setBackgroundColor(Color.parseColor("#FF3798D9"));
        if (btn2isclicked == false) {
            pressed++;
            btn2isclicked = true;
        }

        if (b2.getText().toString() == syn1 || b2.getText().toString() == syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + 5);
        }

        if (pressed == 2) {
            showNextScreen(view);
        }


    }

    public void onClick3(View view) {
        view.setBackgroundColor(Color.parseColor("#FF3798D9"));
        if (btn3isclicked == false) {
            pressed++;
            btn3isclicked = true;

        }

        if (b3.getText().toString() == syn1 || b3.getText().toString() == syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + 5);
        }

        if (pressed == 2) {
            showNextScreen(view);
        }


    }

    public void onClick4(View view) {
        view.setBackgroundColor(Color.parseColor("#FF3798D9"));
        if (btn4isclicked == false) {
            pressed++;
            btn4isclicked = true;

        }

        if (b4.getText().toString() == syn1 || b4.getText().toString() == syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + 5);
        }

        if (pressed == 2) {
            showNextScreen(view);
        }


    }

    public void onClick5(View view) {
        view.setBackgroundColor(Color.parseColor("#FF3798D9"));
        if (btn5isclicked == false) {
            pressed++;
            btn5isclicked = true;

        }

        if (b5.getText().toString() == syn1 || b5.getText().toString() == syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + 5);
        }

        if (pressed == 2) {
            showNextScreen(view);
        }


    }

    public void onClick6(View view) {
        view.setBackgroundColor(Color.parseColor("#FF3798D9"));
        if (btn6isclicked == false) {
            pressed++;
            btn6isclicked = true;

        }

        if (b6.getText().toString() == syn1 || b6.getText().toString() == syn2) {
            pointcounter.setPointcounter(this.pointcounter.getPointcounter() + 5);
        }


        if (pressed == 2) {
            showNextScreen(view);
        }
    }

    public void showNextScreen(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ts2.class);
        intent.putExtra("message", pointcounter);
        pointcounter.setRound(1);
        startActivity(intent);

    }
}
