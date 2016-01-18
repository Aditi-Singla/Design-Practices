package com.example.dhairya.registrationcop290;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        TextView tv1 = (TextView) findViewById(R.id.titleText);
        tv1.setTypeface(tf1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Bd.ttf");
        TextView tv2 = (TextView) findViewById(R.id.courseTitle);
        tv2.setTypeface(tf2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Bd.ttf");
        TextView tv3 = (TextView) findViewById(R.id.sessionText);
        tv3.setTypeface(tf3);
        Typeface tf4 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
        TextView tv4 = (TextView) findViewById(R.id.sessionText);
        tv4.setTypeface(tf4);
    }
}
