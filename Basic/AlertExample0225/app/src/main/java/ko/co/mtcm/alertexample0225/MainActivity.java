package ko.co.mtcm.alertexample0225;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void alertOn(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(R.drawable.ic_launcher_foreground);

        builder.setPositiveButton("예", (dialog, which) -> {
            textView.setText("예 버튼이 눌렸습니다.");
        });

        builder.setNegativeButton("아니오", (dialog, which) -> {
            textView.setText("아니오 버튼이 눌렸습니다.");
        });

        builder.setNeutralButton("취소", (dialog, which) -> {
            textView.setText("취소 버튼이 눌렸습니다.");
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}