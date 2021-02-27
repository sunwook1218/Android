package com.example.comebackexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    private EditText et_sub;
    private Button btn_return;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        et_sub = findViewById(R.id.et_sub);
        btn_return = findViewById(R.id.btn_return);

        btn_return.setOnClickListener((view) -> {
            String resultStr = et_sub.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("main", resultStr);
            setResult(RESULT_OK, intent);
            finish();
        });

    }
}
