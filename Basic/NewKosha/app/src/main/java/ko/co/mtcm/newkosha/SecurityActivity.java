package ko.co.mtcm.newkosha;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SecurityActivity extends AppCompatActivity {

    private static boolean progress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SecurityActivity.this, "TOAST실행", Toast.LENGTH_SHORT).show();
                if(progress) {
                    Intent intent = new Intent(SecurityActivity.this,
                            LoginActivity.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Alert");
                    builder.setMessage("그럴 수 없습니다.");

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }

            }
        }, 1000);
    }
}