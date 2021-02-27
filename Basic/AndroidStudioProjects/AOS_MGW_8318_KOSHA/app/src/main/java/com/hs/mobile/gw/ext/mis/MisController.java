package com.hs.mobile.gw.ext.mis;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.util.TextViewUtils;
import com.softsecurity.transkey.Global;
import com.softsecurity.transkey.ITransKeyActionListener;
import com.softsecurity.transkey.ITransKeyActionListenerEx;
import com.softsecurity.transkey.ITransKeyCallbackListener;
import com.softsecurity.transkey.TransKeyActivity;
import com.softsecurity.transkey.TransKeyCipher;

public class MisController implements View.OnClickListener, View.OnFocusChangeListener , ITransKeyActionListener, ITransKeyActionListenerEx, ITransKeyCallbackListener {

    MisHelper helper;

    public MisController(MisHelper helper) {
        this.helper = helper;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password:
                ViewModel.Log("MisController password click", "tkofs_mis");
                if(helper.isViewCtrlKeypad == false){
                    helper.showTransKeyPad(0, MisConfig.MIS_KEY_PAD_TYPE);
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.password:
                TextViewUtils.hideKeyBoard(helper.view.getContext().getApplicationContext(), v);

                if (!hasFocus) {
                    ViewModel.Log("MisController onFocusChange :" + hasFocus, "tkofs_mis");
                    if(helper.isViewCtrlKeypad == false){
                        helper.mEdPassword.requestFocus();
                        helper.isViewCtrlKeypad = true;
                        //help.showTransKeyPad(0, TransKeyActivity.mTK_TYPE_KEYPAD_QWERTY_LOWER);
                    }
                }

                break;
        }
    }

    @Override
    public void cancel(Intent intent) {
        helper.isViewCtrlKeypad = false;
        helper.m_tkMngr[0].clearAllData();
        setData("");
        ViewModel.Log("cancel", "tkofs_mis");
    }

    @Override
    public void done(Intent data) {
        helper.isViewCtrlKeypad = false;
        if (data == null)
            return;
        String cipherData = data.getStringExtra(TransKeyActivity.mTK_PARAM_CIPHER_DATA);
        String dummyData = data.getStringExtra(TransKeyActivity.mTK_PARAM_DUMMY_DATA);
        byte[] secureKey = data.getByteArrayExtra(TransKeyActivity.mTK_PARAM_SECURE_KEY);

        ViewModel.Log( "cipherData : " + cipherData, "tkofs_mis");
        String encryptedData = data.getStringExtra(TransKeyActivity.mTK_PARAM_RSA_DATA);
        ViewModel.Log("encryptedData : : " + encryptedData , "tkofs_mis");
        int iRealDataLength = data.getIntExtra(TransKeyActivity.mTK_PARAM_DATA_LENGTH, 0);

        if (iRealDataLength == 0)
            return;

        StringBuffer plainData = null;
        try {
            TransKeyCipher tkc = new TransKeyCipher("SEED");
            tkc.setSecureKey(secureKey);

            byte pbPlainData[] = new byte[iRealDataLength];

            if (tkc.getDecryptCipherData(cipherData, pbPlainData)) {
                plainData = new StringBuffer(new String(pbPlainData));

                ViewModel.Log("plainData : " + plainData, "tkofs_mis");
                for(int i=0;i<pbPlainData.length;i++)
                    pbPlainData[i]=0x01;
            } else {
                // 복호화 실패
                plainData = new StringBuffer("plainData decode fail...");
            }

        } catch (Exception e) {
            if (Global.debug) Log.d("STACKTRACE", e.getStackTrace().toString());
        }

        setData(plainData.toString());
    }

    @Override
    public void input(int type) {

    }

    @Override
    public void minTextSizeCallback() {
    }

    @Override
    public void maxTextSizeCallback() {

    }

    private void setData(String plainText) {
        ViewModel.Log( "set data", "tkofs_mis");
        helper.mEdPassword.setText(plainText);
        helper.mEdUserID.setEnabled(true);
        Intent newIntent = new Intent(helper.view.getContext().getApplicationContext(), TransKeyActivity.class);
        helper.hLayoutPassword.setVisibility(View.GONE);

        if(helper.layoutLoginArea != null){
            //로그인 input 영역 정렬을 변경
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)helper.layoutLoginArea.getLayoutParams();
            layoutParams.bottomMargin = helper.dpToPx(helper.view.getContext(), 40);
            helper.layoutLoginArea.setLayoutParams(layoutParams);
        }
    }
}
