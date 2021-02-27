package com.example.comebackexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tv_main;
    private Button btn_go;

    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_main = findViewById(R.id.tv_main);
        btn_go = findViewById(R.id.btn_go);

        btn_go.setOnClickListener((view) -> {
            Intent intent = new Intent(getApplicationContext(), SubActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "수신 성공", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "수신 실패", Toast.LENGTH_SHORT).show();;
        }

        if(requestCode == REQUEST_CODE) {
            String resultStr = data.getStringExtra("main");
            tv_main.setText(resultStr);
        }
    }
}