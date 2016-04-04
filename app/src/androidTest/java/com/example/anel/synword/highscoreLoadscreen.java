package com.example.anel.synword;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

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
 * Created by III-Sev-III on 23.03.2016.
 */
public class highscoreLoadscreen extends ActionBarActivity {
    ArrayList<String> rangliste = new ArrayList<String>();
    LoadscreenSub task = new LoadscreenSub();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadscreen);
        getSupportActionBar().hide();
        task.execute();

    }

    public void goToNextScreenWhenFinished(){
        Intent intent = new Intent(this, HighscoreActivity.class);
        intent.putStringArrayListExtra("highscore", rangliste);
        startActivity(intent);

    }

    public class LoadscreenSub extends AsyncTask<String, Void, String> {
        InputStream is;

        JSONObject json_data;
        String result = "";
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        Exception exception;

        @Override
        protected String doInBackground(String... strings) {

            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://felf.ga:25571/SynWord3_php.php");
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
                    sb.append(line + "n");
                }
                is.close();
                result=sb.toString();
            }catch(Exception e){
                Log.e("log_tag", "Error converting result "+e.toString());
            }


            return result;
        }
        protected void onPostExecute(String result) {

            try{
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    json_data = jArray.getJSONObject(i);

                    // Highscore (
                    rangliste.add((String) json_data.get("username") + " " + json_data.get("highscorenumber")
                            + " " + json_data.get("gamemode"));
                }
                goToNextScreenWhenFinished();

            }
            catch(JSONException e){
                Log.e("log_tag", "Error parsing data "+e.toString());
            }


        }
    }
}
