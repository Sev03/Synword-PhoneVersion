package com.example.anel.synword;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by III-Sev-III on 17.02.2016.
 */
public class pophighscore extends Activity{

    int points;
    String username;
    EditText tempusername;
    String highscorenumber;

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
        points = ((Points) intent.getExtras().get("message")).getPointcounter();
        int round = ((Points) intent.getExtras().get("message")).getRound();
        highscorenumber = String.valueOf(points);


        TextView points = (TextView) this.findViewById(R.id.txtPunkte);
        points.setText("" + points);

        tempusername = (EditText)findViewById(R.id.editText);
        tempusername.setGravity(Gravity.CENTER);
        username = tempusername.getText().toString();
    }

    private void insertToDatabase(String name, String add){
    class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String paramUsername = params[0];
            String paramAddress = params[1];

            String add = username;
            String name = highscorenumber;


            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username", username));
            nameValuePairs.add(new BasicNameValuePair("highscorenumber", highscorenumber));

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(
                        "http://simplifiedcoding.16mb.com/insert-db.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity entity = response.getEntity();



            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return "success";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }
    }
    SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
    sendPostReqAsyncTask.execute(name, add);
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
