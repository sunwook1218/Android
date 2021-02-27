package com.hs.mobile.gw.fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieSyncManager;

import com.androidquery.util.AQUtility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.hs.mobile.crypto.Crypto;
import com.hs.mobile.gw.MainActivity;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.Views;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;

public class LoadingFragment extends CommonFragment {

    private ViewGroup mBGContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mgw_loading, container, false);
        mBGContainer = rootView.findViewById(R.id.bg_container);
        CookieSyncManager.createInstance(getActivity());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        startMGW();
        
    }

    private void startMGW() {
        // push 알리미 사용 여부 체크
        if (MainModel.getInstance().checkPushAvailable()) {
            String tokenId = MainModel.getInstance().pref.getString(getActivity().getString(R.string.key_registration_id), "");
            Debug.trace("FCM token: " + Crypto.getInstance().decrypt(tokenId));
            if (TextUtils.isEmpty(tokenId)) {
                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        try {
                            if (!task.isSuccessful()) {
                                Debug.trace("getInstanceId failed");
                                return;
                            }


                            String token = task.getResult().getToken();
                            ViewModel.Log("FCM token : " + token, "tkofs_token");
                            Debug.trace(token);
                            HMGWServiceHelper.tokenId = Crypto.getInstance().encrypt(token);

                            SharedPreferences shrdPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            SharedPreferences.Editor editor = shrdPref.edit();
                            editor.putString(getActivity().getString(R.string.key_registration_id), Crypto.getInstance().encrypt(token));
                            editor.commit();
                        } finally {
                            startLoginActivity();
                        }
                    }
                });
            } else {
                startLoginActivity();
            }
            // push 기능 사용 안할때
        } else {
            delayLoading();
        }
    }

    private void startLoginActivity() {

        String category = null;
        String event = null;
        if (getArguments() != null) {
            category = getArguments().getString(MainActivity.PUSH_MSG_CATEGORY);
            event = getArguments().getString(MainActivity.PUSH_MSG_EVENT);
        }

        MainModel.getInstance().showView(getActivity().getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOGIN, category != null ?
                new BundleUtils.Builder().add(MainActivity.PUSH_MSG_CATEGORY, category).add(MainActivity.PUSH_MSG_EVENT, event).build() : null);
    }

    private void delayLoading() {
        AQUtility.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startLoginActivity();
            }
        }, 2000);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mBGContainer.removeAllViewsInLayout();
        View.inflate(getContext(), R.layout.mgw_loading_bg, mBGContainer);
    }
}