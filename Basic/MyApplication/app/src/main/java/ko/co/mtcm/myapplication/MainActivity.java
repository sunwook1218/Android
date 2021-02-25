package ko.co.mtcm.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ko.co.mtcm.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
    }

    public void func1(View view) {
        Toast.makeText(this, "button click", Toast.LENGTH_SHORT).show();
    }

    /**
     *
     * @param view
     */
    public void func2(View view) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }

    /**
     *
     * @param view
     */
    public void func3(View view) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + bd.numEt.getText()));
        startActivity(myIntent);
    }

}