package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class Scheduling extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    TimePicker tk;
    CheckBox cb1, cb2, cb3;
    Button updatebtn;
    String whichclicked;
    String time;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);
        tk= findViewById(R.id.timeinput);
        cb1 = findViewById(R.id.morning);
        cb2= findViewById(R.id.noon);
        cb3 = findViewById(R.id.night);
        updatebtn = findViewById(R.id.updatebtn);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    time = tk.getHour() + ":" + tk.getMinute();
                }
                if (cb1.isChecked()) {
                    whichclicked = "Morning";
                }
                if (cb2.isChecked()) {
                    whichclicked = "Noon";
                }
                if (cb3.isChecked()) {
                    whichclicked = "Night";
                }
                //show error if no checkbox is checked
                if (!cb1.isChecked() && !cb2.isChecked() && !cb3.isChecked()) {
                    Toast.makeText(Scheduling.this, "Please select a time", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(cb1.isChecked() && cb2.isChecked() && cb3.isChecked()){
                    Toast.makeText(Scheduling.this, "Please select only one time", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(cb1.isChecked() && cb2.isChecked()){
                    Toast.makeText(Scheduling.this, "Please select only one time", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(cb1.isChecked() && cb3.isChecked()){
                    Toast.makeText(Scheduling.this, "Please select only one time", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(cb2.isChecked() && cb3.isChecked()){
                    Toast.makeText(Scheduling.this, "Please select only one time", Toast.LENGTH_SHORT).show();
                    return ;
                }

               if(whichclicked.equals("Morning")){
                     database.getReference().child("storetime").child("time1").setValue(time);
                   Toast.makeText(Scheduling.this, "Updated Morning Time", Toast.LENGTH_SHORT).show();
                   //uncheck the checkbox
                     cb1.setChecked(false);
               }
                else if(whichclicked.equals("Noon")){
                    database.getReference().child("storetime").child("time2").setValue(time);
                    Toast.makeText(Scheduling.this, "Updated Noon Time", Toast.LENGTH_SHORT).show();
                    cb2.setChecked(false);
                }
                else if(whichclicked.equals("Night")){
                    database.getReference().child("storetime").child("time3").setValue(time);
                    Toast.makeText(Scheduling.this, "Updated Night Time", Toast.LENGTH_SHORT).show();
                    cb3.setChecked(false);
                }
            }
        });
    }
}