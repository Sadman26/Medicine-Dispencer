package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    TextView morningtxt,noontxt,nighttxt;

    @SuppressLint("MissingInflatedId")
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
        morningtxt = findViewById(R.id.morningtxt);
        noontxt = findViewById(R.id.noontxt);
        nighttxt = findViewById(R.id.nighttxt);
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
                    database.getReference().child("schedule").child("time1").setValue(millis);
                    database.getReference().child("storetime").child("time1").setValue(mrng);
                    Toast.makeText(scheduling.this, "Sucessfully Morning Time Updated", Toast.LENGTH_SHORT).show();
                    database.getReference().child("storetime").child("time1").get().addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            morningtxt.setText("Previous Morning time: "+task.getResult().getValue());
                        }
                    });
                    morningtime.setText("");
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
                    database.getReference().child("schedule").child("time2").setValue(millis2);
                    database.getReference().child("storetime").child("time2").setValue(noon);
                    Toast.makeText(scheduling.this, "Sucessfully Noon Time Updated", Toast.LENGTH_SHORT).show();
                    database.getReference().child("storetime").child("time2").get().addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            noontxt.setText("Previous Noon time: "+task.getResult().getValue());
                        }
                    });
                    noontime.setText("");
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
                    database.getReference().child("schedule").child("time3").setValue(millis3);
                    database.getReference().child("storetime").child("time3").setValue(night);
                    Toast.makeText(scheduling.this, "Sucessfully Night Time Updated", Toast.LENGTH_SHORT).show();
                    database.getReference().child("storetime").child("time3").get().addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            nighttxt.setText("Previous Night time: "+task.getResult().getValue());
                        }
                    });
                    nighttime.setText("");
                }catch (Exception e){
                    Toast.makeText(scheduling.this, "Please Maintain HH:MM Format(24Hour)", Toast.LENGTH_SHORT).show();
                }
            }
        });
        database.getReference().child("storetime").child("time1").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                morningtxt.setText("Previous Morning time: "+task.getResult().getValue());
            }
        });
        database.getReference().child("storetime").child("time2").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                noontxt.setText("Previous Noon time: "+task.getResult().getValue());
            }
        });
        database.getReference().child("storetime").child("time3").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                nighttxt.setText("Previous Night time: "+task.getResult().getValue());
            }
        });
    }
}