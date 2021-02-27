package com.example.listexample01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        List<String> strList = new ArrayList<>();
        for(int i =0 ; i < 4; i++) {
            strList.add("데이터" + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strList);
        list.setAdapter(adapter);

        strList.add("89");
        strList.add("123");
        strList.add("어댑터 선언 후 입력한 데이");
        adapter.notifyDataSetChanged();
    }
}