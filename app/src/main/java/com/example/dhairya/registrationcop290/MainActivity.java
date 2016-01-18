package com.example.dhairya.registrationcop290;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView tv1 = (TextView) findViewById(R.id.teamName);
        tv1.setTypeface(tf1);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView tv2 = (TextView) findViewById(R.id.member1);
        tv2.setTypeface(tf2);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView tv3 = (TextView) findViewById(R.id.member2);
        tv3.setTypeface(tf3);
        Typeface tf4 = Typeface.createFromAsset(getAssets(), "fonts/LobsterTwo-Bold.ttf");
        TextView tv4 = (TextView) findViewById(R.id.member3);
        tv4.setTypeface(tf4);
        Typeface tf5 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Lt.ttf");
        TextView tv5 = (TextView) findViewById(R.id.submitButton);
        tv5.setTypeface(tf5);
    }
}
