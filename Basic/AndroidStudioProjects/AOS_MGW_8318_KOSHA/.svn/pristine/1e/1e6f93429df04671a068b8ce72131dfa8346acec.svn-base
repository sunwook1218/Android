package com.hs.mobile.gw.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.ext.vpn.VPNConnectHelper;

public class ForecdTerminationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) { //핸들링 하는 부분
        ViewModel.Log("강제종료", "tkofs_mdm_disconn");

        stopSelf(); //서비스 종료
    }
}

