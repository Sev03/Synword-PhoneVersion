package synteam.SS16.code.menuscreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import synteam.SS16.code.MainActivity;
import synteam.SS16.code.Data.Pop;
import synteam.SS16.code.loadscreens.loadscreenActivity;
import synteam.SS16.code.loadscreens.tsloadscreenActivity;

/**
 * Created by Anel on 14.12.2015.
 */
public class gamemodeActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener{

    Switch switchLang;
    String sprache = "DE";
    private Button btnFehlerfrei;
    private Button btnZeit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(synteam.SS16.code.synword.R.layout.gamemode);
        getSupportActionBar().hide();

        switchLang = (Switch) findViewById(synteam.SS16.code.synword.R.id.switchLang);
        switchLang.setOnCheckedChangeListener(this);
        Button b = (Button) findViewById(synteam.SS16.code.synword.R.id.btnInfo);

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
        } else {
            sprache = "DE";
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == synteam.SS16.code.synword.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showFehlerfrei(View view) {
        // Do something in response to button
        btnFehlerfrei = (Button)findViewById(synteam.SS16.code.synword.R.id.btnFehlerfrei);
        btnFehlerfrei.getBackground().setAlpha(128);
        Intent intent = new Intent(this, loadscreenActivity.class);
        intent.putExtra("sprache", sprache);
        startActivity(intent);
        finish();
    }

    public void showZeit(View view) {
        // Do something in response to button
        btnZeit = (Button)findViewById(synteam.SS16.code.synword.R.id.btnZeit);
        btnZeit.getBackground().setAlpha(128);
        Intent intent = new Intent(this, tsloadscreenActivity.class);
        intent.putExtra("sprache", sprache);
        startActivity(intent);
        finish();
    }

}
