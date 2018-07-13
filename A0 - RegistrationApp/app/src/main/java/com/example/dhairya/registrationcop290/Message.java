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
<<<<<<< HEAD
        String teamname = getIntent().getStringExtra("teamname");
        String entry1 = getIntent().getStringExtra("entry1");
        String name1 = getIntent().getStringExtra("name1");
        String entry2 = getIntent().getStringExtra("entry2");
        String name2 = getIntent().getStringExtra("name2");
        String entry3 = getIntent().getStringExtra("entry3");
        String name3 = getIntent().getStringExtra("name3");
        TextView TVmessage = (TextView)findViewById(R.id.messageText);
        TextView TVmessage1 = (TextView)findViewById(R.id.dataText);
        String message1 = "";


        if (message.charAt(45)=='D')
            message = "REGISTRATION UNSUCCESSFUL! \nRequired fields are missing.";
        else if (message.charAt(45)=='R') {
            message = "Registration Successful!";
            message1 = "\nTeam Name: " + teamname + "\n" + entry1 + " " + name1 + "\n" + entry2 + " " + name2 + "\n" + entry3 + " " + name3;

        }
        else if(message.charAt(45)=='U')
            message = "Registration Unsuccessful! \nOne or more users with given details have already registered.\n";

        TVmessage.setText(message);
        TVmessage1.setText(message1);

        Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/CormorantUpright-Semibold.ttf");
        TVmessage.setTypeface(tf1);

        Typeface tf2 = Typeface.createFromAsset(getAssets(), "fonts/Aller_Rg.ttf");
        TVmessage1.setTypeface(tf2);
=======
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
>>>>>>> 5175820c07b078763ac1803c81da5a06a044109b
    }

    public void submitAnotherResponse(View v)
    {
        Intent back = new Intent(Message.this, MainActivity.class);
        back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(back);
    }

}
