package com.hs.mobile.gw.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.hs.mobile.gw.hsuco.R;

public class OptionMenuDialogFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {

    public static final String TAG = OptionMenuDialogFragment.class.getName();
    public static final String TAG_OPT_ITEM_EXIT = "exit";
    public static final String TAG_OPT_ITEM_INIT = "initial_menu";

    public static BottomSheetDialogFragment newInstance() {
        return new OptionMenuDialogFragment();
    }

    public interface Callback {
        void onOptionItemSelected(String tag);
    }

    private Callback mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OptionMenuDialogFragment.Callback");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                FrameLayout bottomSheet = bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior behavior;
                if (bottomSheet != null) {
                    behavior = BottomSheetBehavior.from(bottomSheet);
                    behavior.setSkipCollapsed(true);
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
        return bottomSheetDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_menu, container);
        view.findViewById(R.id.exit).setTag(TAG_OPT_ITEM_EXIT);
        view.findViewById(R.id.initial_menu).setTag(TAG_OPT_ITEM_INIT);
        view.findViewById(R.id.exit).setOnClickListener(this);
        view.findViewById(R.id.initial_menu).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            mCallback.onOptionItemSelected((String) v.getTag());
        }
        dismiss();
    }
}
