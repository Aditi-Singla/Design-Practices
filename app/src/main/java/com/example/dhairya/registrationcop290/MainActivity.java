package com.example.dhairya.registrationcop290;

//import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

//import org.json.JSONException;
//import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
    public EditText ETteamname;
    public EditText ETentry1;
    public EditText ETentry2;
    public EditText ETentry3;
    public EditText ETname1;
    public EditText ETname2;
    public EditText ETname3;
    public Button submitbutton;

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
//        TextView tv5 = (TextView) findViewById(R.id.submitButton);
//        tv5.setTypeface(tf5);

        ETteamname = (EditText)findViewById(R.id.teamNameEdit);
        ETentry1 = (EditText)findViewById(R.id.Entry1Edit);
        ETentry2 = (EditText)findViewById(R.id.Entry2Edit);
        ETentry3 = (EditText)findViewById(R.id.Entry3Edit);
        ETname1 = (EditText)findViewById(R.id.Name1Edit);
        ETname2 = (EditText)findViewById(R.id.Name2Edit);
        ETname3 = (EditText)findViewById(R.id.Name3Edit);

        submitbutton = (Button)findViewById(R.id.submitButton);

        submitbutton.setOnClickListener(this);
    }

    private void register()
    {
        final String teamname = ETteamname.getText().toString().trim();
        final String entry1 = ETentry1.getText().toString().trim();
        final String entry2 = ETentry2.getText().toString().trim();
        final String entry3 = ETentry3.getText().toString().trim();
        final String name1 = ETname1.getText().toString().trim();
        final String name2 = ETname2.getText().toString().trim();
        final String name3 = ETname3.getText().toString().trim();

        StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response)
                    {
                        Intent intent = new Intent(MainActivity.this,Message.class);
                        intent.putExtra("response",response);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("teamname",teamname);
                params.put("entry1",entry1);
                params.put("name1",name1);
                params.put("entry2",entry2);
                params.put("name2",name2);
                params.put("entry3",entry3);
                params.put("name3",name3);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsObjRequest);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId()==R.id.submitButton)
            register();
    }
}
