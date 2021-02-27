package com.sun.intent0227;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sun.intent0227.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding bd;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        bd.btn.setOnClickListener((v) -> {
            Intent intent = new Intent();
            intent.putExtra("name", "mike");
            setResult(RESULT_OK, intent);

            finish();
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            SimpleData data = bundle.getParcelable(KEY_SIMPLE_DATA);
            bd.textView.setText("전달받은 데이터\nNumber : " + data.number + "\nMessage : " + data.message);
        }
    }
}