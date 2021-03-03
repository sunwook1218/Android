package ko.co.mtcm.fragmentsample0302;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();
    }

    public void onFragmentChanged(int index) {
        switch (index) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
                break;
        }
    }
}