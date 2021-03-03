package ko.co.mtcm.fragmentsample0302;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);
        Button button = rootView.findViewById(R.id.button2);

        button.setOnClickListener((v) -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.onFragmentChanged(1);
        });

        return rootView;
    }
}