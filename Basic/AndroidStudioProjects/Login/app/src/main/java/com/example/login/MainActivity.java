package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView tv_idResult;
    private TextView tv_pwResult;
    private TextView tv_nameResult;
    private TextView tv_ageResult;
    private Button btn_goLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Member member = new Member();

        tv_idResult = findViewById(R.id.tv_idResult);
        tv_pwResult = findViewById(R.id.tv_pwResult);
        tv_nameResult = findViewById(R.id.tv_nameResult);
        tv_ageResult = findViewById(R.id.tv_ageResult);
        btn_goLogin = findViewById(R.id.btn_goLogin);

        tv_idResult.setText(member.memberId);
        tv_pwResult.setText(member.memberPw);
        tv_nameResult.setText(member.memberName);
        tv_ageResult.setText(member.memberAge);

        btn_goLogin.setOnClickListener((view) -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}