package com.example.dhairya.complaintsystem;

//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //The main Activiity where we have the navigation drawer
    //All the pages from the navigation drawer expand as fragments
    //in the container in the activity.
    //The name and usertype(Admin,SpecialUser or ordinaryUser) are received from the intent.
    //The drawer is populated according to the usertype.
    String name;
    int specialuser = 0; // 1 for specialuser
    TextView headerName;
    TextView viewProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String userType = getIntent().getStringExtra("usertype");
        name = getIntent().getStringExtra("name");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //specialUser = 0 for ordinary
        //            = 1 for Special Users - House Secy, Hostel Warden or Dean Academics
        //            = 2 for admin
        if(name.equals("admin"))
            specialuser = 2;
        else if(userType.equals("House Secretary")||userType.equals("Hostel Warden")||userType.equals("Dean Academics"))
            specialuser = 1;

        if(specialuser==2) { // for adder only Add User and logout options are valid! So the default fragment is the fragAddUser
            setTitle("Add User");
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = com.example.dhairya.complaintsystem.FragAddUser.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
            // Set action bar title

           //Populate the navigation View  with AddUser and Logout options.
            NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
            Menu m = navView.getMenu();
            m.add("Add User");
            m.getItem(0).setIcon(R.drawable.ic_add_user_black);
            m.add("Logout");
            m.getItem(1).setIcon(R.drawable.ic_logout_black);
        }
        else if (specialuser==1) { //If a special user, the default fragment is the AllComplaints fragment.
            setTitle("All Complaints");
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = com.example.dhairya.complaintsystem.FragAllComplaints.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
            // Set action bar title

            //NavigationView contains WriteComplaint,AllComplaints,Notifications,Add User and Logout
            NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
            Menu m = navView.getMenu();
            m.add("Write Complaint");
            m.getItem(0).setIcon(R.drawable.ic_write_black);
            m.add("All Complaints");
            m.getItem(1).setIcon(R.drawable.ic_all_black);
            m.add("Notifications");
            m.getItem(2).setIcon(R.drawable.ic_notifications_black);
            m.add("Add User");
            m.getItem(3).setIcon(R.drawable.ic_add_user_black);
            m.add("Logout");
            m.getItem(4).setIcon(R.drawable.ic_logout_black);
        }
        else{
            //The ordinary User: Default fragment is All Complaints
            setTitle("All Complaints");
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = com.example.dhairya.complaintsystem.FragAllComplaints.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
            // Set action bar title

            //NavigationView contains WriteComplaint,AllComplaints,Notifications and Logout
            NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
            Menu m = navView.getMenu();
            m.add("Write Complaint");
            m.getItem(0).setIcon(R.drawable.ic_write_black);
            m.add("All Complaints");
            m.getItem(1).setIcon(R.drawable.ic_all_black);
            m.add("Notifications");
            m.getItem(2).setIcon(R.drawable.ic_notifications_black);
            m.add("Logout");
            m.getItem(3).setIcon(R.drawable.ic_logout_black);
        }
        //Functioning of the DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() { //On backPressed, drawer closes
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //When created, the header view in drawer is populted with the Name and the option to view his/her profile (which opens in a new fragment)
        headerName = (TextView)findViewById(R.id.header_name);
        viewProfile = (TextView)findViewById(R.id.view_profile);
//        try{
        headerName.setText(name);
//        }catch(Exception e){
//            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
//        }
//        try{
            viewProfile.setText("View Profile");//On view Profile, the fragment of MyAccount opens
            viewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = null;
                    Class fragmentClass = null;
                    fragmentClass = com.example.dhairya.complaintsystem.FragMyAccount.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // Insert the fragment by replacing any existing fragment
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
                    // Set action bar title
                    setTitle(name);
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                }
            });
//        }
//        catch(Exception e){
//            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
//        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //When any option is clicked in the navigationView, the corresponding fragment opens.
        Fragment fragment = null;
        Class fragmentClass =null;
        if (item.getTitle()=="Write Complaint") {
            fragmentClass = com.example.dhairya.complaintsystem.FragWriteComplaint.class;
            //Toast.makeText(MainActivity.this, "Write Complaint", Toast.LENGTH_LONG).show();
        } else if (item.getTitle()=="All Complaints") {
            fragmentClass = com.example.dhairya.complaintsystem.FragAllComplaints.class;
            //Toast.makeText(MainActivity.this, "All Complaints", Toast.LENGTH_LONG).show();
        } else if (item.getTitle()=="Notifications") {
            fragmentClass = com.example.dhairya.complaintsystem.FragNotifications.class;
            //Toast.makeText(MainActivity.this,"Notifications", Toast.LENGTH_LONG).show();
        } else if (item.getTitle()=="Add User") {
            fragmentClass = com.example.dhairya.complaintsystem.FragAddUser.class;
            //Toast.makeText(MainActivity.this, "Add User", Toast.LENGTH_LONG).show();
        } else if (item.getTitle()=="Logout") {
            fragmentClass = com.example.dhairya.complaintsystem.FragLogout.class;
            //Toast.makeText(MainActivity.this,"Logout", Toast.LENGTH_LONG).show();
            //public void login(View v){
              //  final String usrnm = Username.getText().toString().trim();
               // final String pswd = Password.getText().toString().trim();
               // final String categ = category;

                //For the logout, the logout StringRequest is sent and returns to the LoginPage with the session ending for that user.
                String URL = "http://10.192.58.152:80/complaint_management/default/logout.php";

                StringRequest jsObjRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(jsObjRequest);
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

        // Set action bar title
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
