package com.example.anel;

import android.app.Activity;
import android.graphics.Outline;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.example.anel.synword.R;

/**
 * Created by Minato on 27.12.2015.
 */
public class Pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .8));


    }
}
