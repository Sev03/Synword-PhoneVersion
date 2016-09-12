package bakkarbeit.SS16.code;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bakkarbeit.SS16.code.Data.Points;
import bakkarbeit.SS16.code.menuscreens.gamemodeActivity;

/**
 * Created by III-Sev-III on 17.02.2016.
 */
public class pophighscore extends Activity{

    int points;
    String username;
    EditText tempusername;
    String highscorenumber;
    String gamemode;
    String phoneID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(bakkarbeit.SS16.code.synword.R.layout.popuphighscore);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.85),(int) (height*.85));

        Intent intent = getIntent();
        points = ((Points) intent.getExtras().get("message")).getPointcounter();
        int round = ((Points) intent.getExtras().get("message")).getRound();
        highscorenumber = String.valueOf(points);
        gamemode = intent.getStringExtra("modi");
        phoneID = intent.getStringExtra("phoneid");


        TextView punkte = (TextView) this.findViewById(bakkarbeit.SS16.code.synword.R.id.txtPunkte);
        punkte.setText("" + points);

        tempusername = (EditText)findViewById(bakkarbeit.SS16.code.synword.R.id.editText);
        tempusername.setGravity(Gravity.CENTER);


    }



    private void insertToDatabase(String name, String hs, String modi, String phonenumber){
    class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
        InputStream is;
        String result=null;
        String line=null;
        int code;

        @Override
        protected String doInBackground(String... params) {
            String paramUsername = params[0];
            String paramAddress = params[1];
            String paramGamemodi = params[2];
            String paramPhoneID = params[3];

            String name = username;
            String hs = highscorenumber;
            String gamemodi= gamemode;
            String phoneid = phoneID;

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username", name));
            nameValuePairs.add(new BasicNameValuePair("highscorenumber", hs));
            nameValuePairs.add(new BasicNameValuePair("modi", gamemodi));
            nameValuePairs.add(new BasicNameValuePair("phoneid", phoneid));

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://synword.felf.io/insertplayer.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                Log.e("pass 1", "connection success ");
            }
            catch(Exception e)
            {
                Log.e("Fail 1", e.toString());
                Toast.makeText(getApplicationContext(), "Invalid IP Address",
                        Toast.LENGTH_LONG).show();
            }

            try
            {
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                is.close();
                result = sb.toString();
                Log.e("pass 2", "connection success ");
            }
            catch(Exception e)
            {
                Log.e("Fail 2", e.toString());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
//            super.onPostExecute(result);

            try
            {
                JSONObject json_data = new JSONObject(result);
                code=(json_data.getInt("code"));

                if(code==1)
                {
                    Toast.makeText(getBaseContext(), "Erfolgreich gespeichert",
                            Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Es ist ein Fehler aufgetreten",
                            Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e)
            {
                Log.e("Fail 3", e.toString());
            }

        }
    }
    SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
    sendPostReqAsyncTask.execute(name, hs, modi, phonenumber);
}

    public void onDismiss(View view) {
        // Do something in response to button
        if (tempusername.getText().toString().contains(" ")) {
            tempusername.setError(getText(bakkarbeit.SS16.code.synword.R.string.hserror));
        } else if (tempusername.length() == 0){
            tempusername.setError(getText(bakkarbeit.SS16.code.synword.R.string.hserror2));
        }else {
            username = tempusername.getText().toString();
            insertToDatabase(username, highscorenumber, gamemode, phoneID);
            Toast.makeText(getBaseContext(), "Erfolgreich gespeichert",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, gamemodeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, gamemodeActivity.class);
        startActivity(intent);
    }


}
