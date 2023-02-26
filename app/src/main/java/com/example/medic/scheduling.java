package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class scheduling extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText morningtime,noontime,nighttime;
    String mrng,noon,night;
    Button morningupbtn,noonupbtn,nightupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);
        //initializing
        morningtime = findViewById(R.id.morningtime);
        noontime = findViewById(R.id.noontime);
        nighttime = findViewById(R.id.nighttime);
        morningupbtn = findViewById(R.id.morningupdatebutton);
        noonupbtn = findViewById(R.id.noonupdatebutton);
        nightupbtn = findViewById(R.id.nightupdatebutton);
        morningupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mrng = morningtime.getText().toString();
                //make mrng in milliseconds
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm",Locale.getDefault());
                    Date date = sdf.parse(mrng);
                    long millis = date.getTime();
                    if(millis<0){
                        millis+=24 * 60 * 60 * 1000;
                    }
                    database.getReference().child("Schedule").child("time1").setValue(millis);
                    Toast.makeText(scheduling.this, "Sucessfully Morning Time Updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(scheduling.this, "Please Maintain HH:MM Format(24Hour)", Toast.LENGTH_SHORT).show();
                }
            }
        });
        noonupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noon = noontime.getText().toString();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm",Locale.getDefault());
                    Date date = sdf.parse(noon);
                    long millis2 = date.getTime();
                    if(millis2<0){
                        millis2+=24 * 60 * 60 * 1000;
                    }
                    database.getReference().child("Schedule").child("time2").setValue(millis2);
                    Toast.makeText(scheduling.this, "Sucessfully Noon Time Updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(scheduling.this, "Please Maintain HH:MM Format(24Hour)", Toast.LENGTH_SHORT).show();
                }

            }
        });
        nightupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                night = nighttime.getText().toString();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    Date date = sdf.parse(night);
                    long millis3 = date.getTime();
                    if(millis3<0){
                        millis3+=24 * 60 * 60 * 1000;
                    }
                    database.getReference().child("Schedule").child("time3").setValue(millis3);
                    Toast.makeText(scheduling.this, "Sucessfully Night Time Updated", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(scheduling.this, "Please Maintain HH:MM Format(24Hour)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}