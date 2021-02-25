package ko.co.mtcm.sms_example0225;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ko.co.mtcm.sms_example0225.databinding.ActivityMainBinding;

/**
 * TODO 80 바이트 바인딩
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bd;

    private static int byteSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        bd.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                byteSize = 0;

                for (int i = 0; i < s.length(); i++) {
                    byteSize += String.valueOf(s.charAt(i)).getBytes().length;
                }

                if (byteSize <= 80) {
                    bd.tvByte.setText(byteSize + " / 80 바이트");
                } else {
                    Toast.makeText(MainActivity.this, "80바이트 이상 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bd.btnSend.setOnClickListener((v) -> {
            Toast.makeText(this, byteSize + "", Toast.LENGTH_SHORT).show();
        });

    }
}