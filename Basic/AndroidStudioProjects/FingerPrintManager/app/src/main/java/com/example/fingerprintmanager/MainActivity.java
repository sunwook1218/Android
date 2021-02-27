package com.example.fingerprintmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;

import android.content.DialogInterface;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_dialog;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_dialog = findViewById(R.id.btn_dialog);
        tv_result = findViewById(R.id.tv_result);

        btn_dialog.setOnClickListener((view) -> {

            AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
            ad.setTitle("제목title");
            ad.setMessage("다이얼로그의 메세지? 묻는다");
            EditText et = new EditText(MainActivity.this);
            et.setText("확인을 눌렀을 때 나타는 String");

            ad.setPositiveButton("확인", (dialog, which) -> {
                String resultStr = et.getText().toString();
                tv_result.setText(resultStr);
                dialog.dismiss();
            });

            ad.setNegativeButton("취소", (dialog, which) -> {
                dialog.dismiss();
            });

            ad.show();
        });
    }
}