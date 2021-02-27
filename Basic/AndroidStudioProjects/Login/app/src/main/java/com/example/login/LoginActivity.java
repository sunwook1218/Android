package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText et_login_idForm;
    private EditText et_login_pwForm;
    private Button btn_login;

    private Button btn_login_to_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_idForm = findViewById(R.id.et_login_idForm);
        et_login_pwForm = findViewById(R.id.et_login_pwForm);
        btn_login = findViewById(R.id.btn_login);
        btn_login_to_signIn = findViewById(R.id.btn_login_to_signIn);

        btn_login.setOnClickListener((view) -> {
            String idParam = et_login_idForm.getText().toString();
            String pwParam = et_login_pwForm.getText().toString();
            doLogin(idParam, pwParam);
        });

        btn_login_to_signIn.setOnClickListener((view) -> {
            Toast.makeText(this, R.string.error_deaccurateLogininfo, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void doLogin(String idParam, String pwParam) {
        Member member = new Member();
        if(member.memberId.equals(idParam) && member.memberPw.equals(pwParam)) {
            //login Success
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            //login Fail
            Toast.makeText(this, R.string.error_deaccurateLogininfo, Toast.LENGTH_SHORT).show();
        }
    }
}