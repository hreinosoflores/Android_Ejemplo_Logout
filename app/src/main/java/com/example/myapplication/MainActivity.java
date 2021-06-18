package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnComenzarServicio;
    Button btnDetenerServicio;
    Button btnForm1;
    Button btnForm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnComenzarServicio = findViewById(R.id.btnComenzarServicio);
        this.btnDetenerServicio = findViewById(R.id.btnDetenerServicio);
        this.btnForm1 = findViewById(R.id.btnForm1);
        this.btnForm2 = findViewById(R.id.btnForm2);
        btnComenzarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ClearSharedPreferencesService.class);
                startService(intent);
            }
        });
        btnDetenerServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ClearSharedPreferencesService.class);
                stopService(intent);
            }
        });
        btnForm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Form1Activity.class);
                startActivity(intent);
            }
        });
        btnForm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Form2Activity.class);
                startActivity(intent);
            }
        });
    }


}