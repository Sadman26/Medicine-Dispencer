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
        name = findViewById(R.id.nametxt);
        savebtn = findViewById(R.id.savebtn);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("Names").setValue(name.getText().toString());
                Toast.makeText(MainActivity.this, "Successfully Added !", Toast.LENGTH_SHORT).show();
                //and then nametxt value null
                name.setText("");
            }
        });
        morningbtn = findViewById(R.id.morningbtn);
        noonbtn=findViewById(R.id.noonbtn);
        nightbtn=findViewById(R.id.nightbtn);
        //when morningbtn clicked then it will store firebase to morning at value 1
        morningbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("Morning").setValue(1);
                Toast.makeText(MainActivity.this, "Manually Activated!", Toast.LENGTH_SHORT).show();
                db.getReference().child("Morning").setValue(0);
            }
        });
        //when noonbtn clicked then it will store firebase to noon at value 1
        noonbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("Noon").setValue(1);
                Toast.makeText(MainActivity.this, "Manually Activated!", Toast.LENGTH_SHORT).show();
                db.getReference().child("Noon").setValue(0);
            }
        });
        //when nightbtn clicked then it will store firebase to night at value 1
        nightbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.getReference().child("Night").setValue(1);
                Toast.makeText(MainActivity.this, "Manually Activated!", Toast.LENGTH_SHORT).show();
                db.getReference().child("Night").setValue(0);

            }
        });



    }
}