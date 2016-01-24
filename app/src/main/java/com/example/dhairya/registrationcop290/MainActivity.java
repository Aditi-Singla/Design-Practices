package com.example.dhairya.registrationcop290;

//import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL = "http://agni.iitd.ernet.in/cop290/assign0/register/";
    private EditText ETteamname;
    private EditText ETentry1;
    private EditText ETentry2;
    private EditText ETentry3;
    private EditText ETname1;
    private EditText ETname2;
    private EditText ETname3;
    private Button submitbutton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        ETteamname = (EditText) findViewById(R.id.teamNameEdit);
        ETentry1 = (EditText) findViewById(R.id.Entry1Edit);
        ETentry2 = (EditText) findViewById(R.id.Entry2Edit);
        ETentry3 = (EditText) findViewById(R.id.Entry3Edit);
        ETname1 = (EditText) findViewById(R.id.Name1Edit);
        ETname2 = (EditText) findViewById(R.id.Name2Edit);
        ETname3 = (EditText) findViewById(R.id.Name3Edit);

        submitbutton = (Button) findViewById(R.id.submitButton);

        submitbutton.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void register() {
        final String teamname = ETteamname.getText().toString().trim();
        final String entry1 = ETentry1.getText().toString().trim();
        final String entry2 = ETentry2.getText().toString().trim();
        final String entry3 = ETentry3.getText().toString().trim();
        final String name1 = ETname1.getText().toString().trim();
        final String name2 = ETname2.getText().toString().trim();
        final String name3 = ETname3.getText().toString().trim();

        StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(MainActivity.this, Message.class);
                        intent.putExtra("response", response);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("teamname", teamname);
                params.put("entry1", entry1);
                params.put("name1", name1);
                params.put("entry2", entry2);
                params.put("name2", name2);
                params.put("entry3", entry3);
                params.put("name3", name3);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsObjRequest);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitButton)
            if (canSubmit())
                register();
    }

    public void showError(EditText et)
    {
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        et.startAnimation(shake);
    }

    public Boolean canSubmit() {
        if (ETteamname.getText().toString().trim().equalsIgnoreCase("")) {
            ETteamname.setError("This field can not be blank");
            showError(ETteamname);
            return false;
        }

        if (ETentry1.getText().toString().trim().equalsIgnoreCase("")) {
            ETentry1.setError("This field can not be blank");
            showError(ETentry1);
            return false;
        }
        if (!isValidEntry(ETentry1.getText().toString())) {
            ETentry1.setError("Invalid entry number");
            showError(ETentry1);
            return false;
        }

        if (ETname1.getText().toString().trim().equalsIgnoreCase("")) {
            ETname1.setError("This field can not be blank");
            showError(ETname1);
            return false;
        }
        if (!isValidName(ETname1.getText().toString())) {
            ETname1.setError("Invalid name");
            showError(ETname1);
            return false;
        }

        if (ETentry2.getText().toString().trim().equalsIgnoreCase("")) {
            ETentry2.setError("This field can not be blank");
            showError(ETentry2);
            return false;
        }
        if (!isValidEntry(ETentry2.getText().toString())) {
            ETentry2.setError("Invalid entry number");
            showError(ETentry2);
            return false;
        }

        if (ETname2.getText().toString().trim().equalsIgnoreCase("")) {
            ETname2.setError("This field can not be blank");
            showError(ETname2);
            return false;
        }
        if (!isValidName(ETname2.getText().toString())) {
            ETname2.setError("Invalid name");
            showError(ETname2);
            return false;
        }

        if (ETname3.getText().toString().trim().equalsIgnoreCase("") && ETentry3.getText().toString().trim().equalsIgnoreCase(""))
            return true;
        else {
            if (ETentry3.getText().toString().trim().equalsIgnoreCase("")) {
                ETentry3.setError("This field can not be blank");
                showError(ETentry3);
                return false;
            }
            if (!isValidEntry(ETentry3.getText().toString())) {
                ETentry3.setError("Invalid entry number");
                showError(ETentry3);
                return false;
            }

            if (ETname3.getText().toString().trim().equalsIgnoreCase("")) {
                ETname3.setError("This field can not be blank");
                showError(ETname3);
                return false;
            }
            if (!isValidName(ETname3.getText().toString())) {
                ETname3.setError("Invalid name");
                showError(ETname3);
                return false;
            }
        }
        return true;
    }

    public boolean isValidEntry(String str) {
        str = str.toUpperCase();
        String temp, pattern;
        char c;
        if (str.length() != 11)
            return false;
        if (!str.substring(0, 2).equals("20"))
            return false;
        c = str.charAt(3);
        if (str.charAt(2) == '0') {
            if (c < '6' || c > '9')
                return false;
        } else if (str.charAt(2) == '1') {
            if (c < '0' || c > '4')
                return false;
        } else
            return false;
        temp = str.substring(4, 7);
        pattern = "([C][H][1]||[C][S][1]||[C][E][1]||[E][E][1]||[E][E][2]||[M][E][1]||[M][E][2]||[P][H][1]||[T][T][1]";
        pattern += "||[B][E][5]||[C][H][7]||[C][S][5]||[E][E][5]||[M][T][5]";
        pattern += "||[A][M][X]||[C][E][X]||[C][Y][S]||[M][A][S]||[P][H][S]||[S][M][F]||[S][M][T]||[S][M][N]||[J][D][S]";
        pattern += "||[A][M][E]||[A][M][D]||[C][H][E]||[C][Y][M]||[C][E][G]||[C][E][U]||[C][E][S]||[C][E][W]||[C][E][T]||[C][E][C]";
        pattern += "||[C][E][V]||[C][E][P]||[M][C][S]||[E][E][E]||[E][E][T]||[E][E][A]||[E][E][N]||[E][E][P]||[E][E][S]";
        pattern += "||[M][E][D]||[M][E][E]||[M][E][P]||[M][E][T]||[P][H][A]||[P][H][M]||[T][T][F]||[T][T][E]||[C][R][F]";
        pattern += "||[A][S][T]||[J][E][S]||[J][C][A]||[J][E][N]||[J][I][T]||[J][I][D]||[J][O][P]||[J][P][T]||[J][T][M]||[J][V][L]";
        pattern += "||[A][M][Y]||[B][S][Y]||[B][E][Y]||[C][H][Y]||[C][S][Y]||[C][E][Y]||[E][E][Y]||[M][E][Y]||[S][I][Y]";
        pattern += "||[A][M][Z]||[C][R][Z]||[A][S][Z]||[B][E][Z]||[B][L][Z]||[B][M][Z]||[C][H][Z]||[C][Y][Z]||[C][E][Z]||[C][S][Z]||[E][E][Z]||[E][S][Z]";
        pattern += "||[H][U][Z]||[I][T][Z]||[S][I][Z]||[I][D][Z]||[S][M][Z]||[M][A][Z]||[M][E][Z]||[P][H][Z]||[P][S][Z]||[R][D][Z]||[B][S][Z]";
        pattern += "||[T][T][Z]||[V][E][Z])";
        if (!temp.matches(pattern))
            return false;
        temp = str.substring(7, 11);
        if (temp.equals("0000"))
            return false;
        if (!temp.matches("[0-9][0-9][0-9][0-9]"))
            return false;
        return true;
    }

    public boolean isValidName(String str) {
        str = str.toUpperCase();
        int l = str.length();
        char c;
        for (int i = 0; i < l; i++) {
            c = str.charAt(i);
            if (!Character.isLetter(c) && !(c == ' '))
                return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.dhairya.registrationcop290/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.dhairya.registrationcop290/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
