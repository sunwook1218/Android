package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int result = 0;
        for (int i =0 ; i < 20; i++) {
            result += 100;
        }
    }

    private void method() {
        System.out.println("this is private method");
    }


}