package com.example.viewbinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.viewbinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);기존의 findViewById를 사용할 때의 setContentView(레이아웃 파일과의 연결);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        mainBinding.tvHello.setText("연동 되었는지 확인");

        mainBinding.btnHello.setOnClickListener((v) -> {
            Toast.makeText(this, "버튼을 누르면 안녕하세요", Toast.LENGTH_SHORT).show();
        });

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, new TestFragment());
        fragmentTransaction.commit();
    }

    //view binding의 경우 onDestroy에서 null처리를 해주지 않으면 메모리 누수의 위험이 있기 때문에 처리해준다
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainBinding = null;
    }
}