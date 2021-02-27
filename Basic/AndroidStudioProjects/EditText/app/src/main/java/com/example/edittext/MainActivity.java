package com.example.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_id;
    private Button btn_test;
    private Button btn_move;
    private String query;
    private ImageView testImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);
        btn_test = findViewById(R.id.btn_search);
        btn_move = findViewById(R.id.btn_move);
        testImg = findViewById(R.id.testImg);


        btn_test.setOnClickListener((v) -> {
                et_id.setText("리스너를 통해 작성한 내용");
            }
        );


        btn_move.setOnClickListener((v) -> {
            query = et_id.getText().toString();
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            intent.putExtra("query", query);
            startActivity(intent); // Activity 이동
        });

        testImg.setOnClickListener((v) -> {
            Toast.makeText(getApplicationContext(), "Alert&Toast", Toast.LENGTH_LONG).show();
        });
    }
}