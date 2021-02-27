package com.hs.mobile.gw.ext.mdm;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.RootActivity;

public class MdmActivity extends RootActivity {

    private ProgressDialog initProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdm);
        ViewModel.Log("MdmActivity onCreate", "tkofs_mdm");
        MdmHelper.setMdmActivity(this);
        PopupUtil.showLoading(this, "MDM 정책 적용중입니다.");
    }
    @Override
    protected void onResume() {
        ViewModel.Log("MdmActivity onResume", "tkofs_mdm");
        super.onResume();

        MdmHelper.setContext(this);
        MdmHelper.applyMdm();
    }

    @Override
    protected void onDestroy() {
        PopupUtil.hideLoading(this);
        super.onDestroy();
    }
}
