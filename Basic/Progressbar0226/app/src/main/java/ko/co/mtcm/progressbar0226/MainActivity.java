package ko.co.mtcm.progressbar0226;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ko.co.mtcm.progressbar0226.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bd;
    private ProgressDialog progressDialog;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        bd.progressBar.setIndeterminate(false);
        bd.progressBar.setProgress(progress);

        bd.call.setOnClickListener((v) -> {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("데이터를 확인하는 중입니다.");
            progressDialog.show();
            bd.progressBar.setProgress(progress += 5);
        });

    }
}