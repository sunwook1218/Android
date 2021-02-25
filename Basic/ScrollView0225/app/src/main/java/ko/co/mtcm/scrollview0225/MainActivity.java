package ko.co.mtcm.scrollview0225;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ko.co.mtcm.scrollview0225.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding bd;

    BitmapDrawable bitmap;

    List<BitmapData> bitmapDataList;

    private static int curDataindex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());

        bitmapDataList = new ArrayList<>();
        bitmapDataList.add(generateBitmapData(R.drawable.grass));
        bitmapDataList.add(generateBitmapData(R.drawable.river));

        init(bitmapDataList);

    }

    private void init(List<BitmapData> bitmapDataList) {
        BitmapData bitmapData = bitmapDataList.get(curDataindex);
        bd.imageView.setImageDrawable(bitmapData.getBitmapDrawable());
        bd.imageView.getLayoutParams().width = bitmapData.getWidth();
        bd.imageView.getLayoutParams().height = bitmapData.getHeight();
    }

    private BitmapData generateBitmapData(int resourceId) {

        BitmapData bitmapData = new BitmapData();

        bitmap = (BitmapDrawable) getResources().getDrawable(resourceId);
        bitmapData.setBitmapDrawable(bitmap);
        bitmapData.setWidth(bitmap.getIntrinsicWidth());
        bitmapData.setHeight(bitmap.getIntrinsicHeight());

        return bitmapData;
    }


    public void onButton1Clicked(View view) {
        changeImage(bitmapDataList);
    }

    private void changeImage(List<BitmapData> bitmapDataList) {

        curDataindex = curDataindex + 1 < bitmapDataList.size() ? curDataindex + 1 : 0;

        BitmapData bitmapData = bitmapDataList.get(curDataindex);

        bd.imageView.setImageDrawable(bitmapData.getBitmapDrawable());
        bd.imageView.getLayoutParams().width = bitmapData.getWidth();
        bd.imageView.getLayoutParams().height = bitmapData.getHeight();

    }
}