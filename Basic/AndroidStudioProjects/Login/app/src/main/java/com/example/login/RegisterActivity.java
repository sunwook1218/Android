package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_idForm;
    private EditText et_pwForm;
    private EditText et_nameForm;
    private EditText et_ageForm;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_idForm = findViewById(R.id.et_idForm);
        et_pwForm = findViewById(R.id.et_pwForm);
        et_nameForm = findViewById(R.id.et_nameForm);
        et_ageForm = findViewById(R.id.et_ageForm);
        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener((view) -> {
            String memberId = et_idForm.getText().toString();
            String memberPw = et_pwForm.getText().toString();
            String memberName = et_nameForm.getText().toString();
            Integer memberAge = Integer.parseInt(et_ageForm.getText().toString());
            new Member(memberId, memberPw, memberName, memberAge);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}