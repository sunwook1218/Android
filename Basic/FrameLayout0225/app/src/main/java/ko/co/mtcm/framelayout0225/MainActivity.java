package ko.co.mtcm.framelayout0225;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ko.co.mtcm.framelayout0225.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

    }

    public void onButton1Clicked(View view) {
        changeImage();
    }

    private void changeImage() {

        if (bd.firstImage.getVisibility() == View.VISIBLE) {
            bd.firstImage.setVisibility(View.INVISIBLE);
            bd.secondImage.setVisibility(View.VISIBLE);

            Toast.makeText(this, "두번째 이미지가 표시됩니다.", Toast.LENGTH_SHORT).show();

        } else if (bd.firstImage.getVisibility() == View.INVISIBLE) {
            bd.firstImage.setVisibility(View.VISIBLE);
            bd.secondImage.setVisibility(View.INVISIBLE);

            Toast.makeText(this, "첫번째 이미지가 표시됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
