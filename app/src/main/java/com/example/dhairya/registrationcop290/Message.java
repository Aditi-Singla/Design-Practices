package com.example.dhairya.registrationcop290;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        String message = getIntent().getStringExtra("response");
        TextView TVmessage = (TextView)findViewById(R.id.messageText);

        if (message.charAt(45)=='D')
            message = "Registration unsuccessful! \nRequired fields are missing.";
        else if (message.charAt(45)=='R')
            message = "Registration completed!";
        else if(message.charAt(45)=='U')
            message = "Registration unsuccessful! \nOne or more users with given details have already registered.";

        TVmessage.setText(message);

        Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/CormorantUpright-Semibold.ttf");
        TextView tv1 = (TextView) findViewById(R.id.messageText);
        tv1.setTypeface(tf1);
    }

    public void submitAnotherResponse(View v)
    {
        Intent back = new Intent(Message.this, MainActivity.class);
        back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(back);
    }

}
