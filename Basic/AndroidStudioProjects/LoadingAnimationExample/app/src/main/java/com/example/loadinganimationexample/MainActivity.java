package com.example.loadinganimationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg = findViewById(R.id.bg);
        bg.setBackgroundColor(0xFF00FF00);
    }
}