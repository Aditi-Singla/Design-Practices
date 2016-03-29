package com.example.dhairya.complaintsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Complaint extends AppCompatActivity {
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        id = getIntent().getIntExtra("complaintId",-1);
        Toast.makeText(Complaint.this,""+id,Toast.LENGTH_SHORT).show();
    }
}
