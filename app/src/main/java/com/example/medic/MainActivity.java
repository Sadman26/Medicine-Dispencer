package com.example.medic;

import androidx.appcompat.app.AppCompatActivity;

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


    }
}