package ko.co.mtcm.fragmentsample0302;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    static int position = 0;
    static int maxPosition = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        Button button = rootView.findViewById(R.id.button);
        Button nextBtn = rootView.findViewById(R.id.nextBtn);
        ImageView imageView = rootView.findViewById(R.id.image);

        button.setOnClickListener((v) -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.onFragmentChanged(0);
        });

        List<Drawable> imageList = new ArrayList<>();
        imageList.add(ResourcesCompat.getDrawable(getResources(), R.drawable.one, null));
        imageList.add(ResourcesCompat.getDrawable(getResources(), R.drawable.two, null));
        imageList.add(ResourcesCompat.getDrawable(getResources(), R.drawable.three, null));

        nextBtn.setOnClickListener((v) -> {
            position++;
            position = position < maxPosition ? position : 0;
            imageView.setBackground(imageList.get(position));
        });

        return rootView;
    }
}