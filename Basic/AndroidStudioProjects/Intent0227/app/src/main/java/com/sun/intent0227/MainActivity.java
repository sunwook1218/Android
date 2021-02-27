package com.sun.intent0227;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sun.intent0227.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bd;
    public static final int REQUEST_CODE_MENU = 101;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        bd.btn.setOnClickListener((v) -> {
            /*Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivityForResult(intent, REQUEST_CODE_MENU);*/
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            SimpleData simpleData = new SimpleData(100, "Hello World!!");
            intent.putExtra(KEY_SIMPLE_DATA, simpleData);
            startActivityForResult(intent, REQUEST_CODE_MENU);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            Toast.makeText(getApplicationContext(), "onActivityResult 메서드 호출됨. \n요청코드 : " + requestCode + " 결과 코드 : " + resultCode, Toast.LENGTH_LONG).show();
        }

        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            Toast.makeText(getApplicationContext(), "응답으로 전달된 name : " + name, Toast.LENGTH_LONG).show();
        }
    }
}