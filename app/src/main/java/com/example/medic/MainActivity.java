package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    TextView name;
    Button savebtn;
    Button morningbtn, noonbtn, nightbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        db = FirebaseDatabase.getInstance();
        morningbtn = findViewById(R.id.morningbtn);
        noonbtn=findViewById(R.id.noonbtn);
        nightbtn=findViewById(R.id.nightbtn);
        //when morningbtn clicked then it will store firebase to morning at value 1
        morningbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("Manual").child("Morning").setValue("true");
                Toast.makeText(MainActivity.this, "Manually Activated!", Toast.LENGTH_SHORT).show();

            }
        });
        //when noonbtn clicked then it will store firebase to noon at value 1
        noonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("Manual").child("Noon").setValue("true");
                Toast.makeText(MainActivity.this, "Manually Activated!", Toast.LENGTH_SHORT).show();

            }
        });
        //when nightbtn clicked then it will store firebase to night at value 1
        nightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("Manual").child("Night").setValue("true");
                Toast.makeText(MainActivity.this, "Manually Activated!", Toast.LENGTH_SHORT).show();


            }
        });



    }
}