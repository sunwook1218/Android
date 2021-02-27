package com.hs.mobile.gw.ext.otp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;
import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.RootActivity;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OtpActivity extends RootActivity implements OnClickListener, TextWatcher {
    boolean cancelable = false;
    private final String APP_KEY = "cd2ec735ab7b688033f725e3fcbcb92df34c93bcd14f44049765ce173d43e882";

    public Button mBtnSubmit;
    public Button mBtnOtpReq;
    public EditText mEdOtpNum;
    public LinearLayout mBtnClose;
    private ProgressDialog initProgressDialog;
    private boolean isOtpSave = false;

    //private OTPRemoteService mOTPRemoteService; // OTP 호출/응답 위한 서비스
    /*private OTPRemoteListener listener = new OTPRemoteListener() {

        @Override
        public void handleSuccess(String otp) {
            // OTP 앱에서 OTP 전송 성공
            // 파라메터로 넘어오는 otp는 암호화 된 정보가 복호화 되어 넘어온다.
            ViewModel.Log(otp, "tkofs_otp");
            if(isOtpSave){
                mEdOtpNum.setText(otp);
                mBtnSubmit.performClick();
            }
        }

        @Override
        public void handleErrorInfo(String retCode) {
            // OTP 앱에서 OTP 전송 실패
            ViewModel.Log(retCode, "tkofs_otp");
            ViewModel.Log(RET_CODE.valueOf("R" + retCode).getRetCodeDesc(), "tkofs_otp");
            Toast.makeText(getBaseContext(), RET_CODE.valueOf("R" + retCode).getRetCodeDesc(), Toast.LENGTH_LONG).show();
            //mResultText.setText("retCode : " + retCode + " / errorMsg : " + ReturnCode.getReturnDesc(retCode));
        }
    };*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_otp);
        isOtpSave = getIntent().getBooleanExtra("isOtpSave", false);
        //mOTPRemoteService = new OTPRemoteService(this, listener); // OTP 요청 서비스 생성
        HMGWServiceHelper.otp_cancle = 1;
        mBtnSubmit = findViewById(R.id.otp_confirm);
        mEdOtpNum = findViewById(R.id.otp_num);
        mBtnClose = findViewById(R.id.otp_close);
        mBtnOtpReq = findViewById(R.id.otp_req);

        mBtnSubmit.setOnClickListener(this);
        //최초 로딩시 버튼 비활성화 추가
        if(!isOtpSave){
            mBtnSubmit.setEnabled(false);
        }
        mBtnClose.setOnClickListener(this);
        mBtnOtpReq.setOnClickListener(this);
        mEdOtpNum.addTextChangedListener(this);

        initProgressDialog = new ProgressDialog(this);
        initProgressDialog.setMessage("OTP 인증중");
        initProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        initProgressDialog.setCancelable(false);
        ViewModel.Log(isOtpSave, "tkofs_otp");

        if(isOtpSave){
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    // otp 자동인증
                    mBtnOtpReq.performClick();
                }
            }, 500);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mEdOtpNum.getText().length() == 0) {
            mBtnSubmit.setBackgroundDrawable(getResources().getDrawable(R.drawable.style_btn_otp_blue_hu));
            mBtnSubmit.setTextColor(Color.argb(0xff, 0xa8, 0xa8, 0xa8));
            mBtnSubmit.setEnabled(false);
        } else {
            mBtnSubmit.setEnabled(true);
            mBtnSubmit.setTextColor(getResources().getColor(R.color.white));
            mBtnSubmit.setBackgroundDrawable(getResources().getDrawable(R.drawable.style_btn_otp_blue_hu_on));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.otp_close) {
            finish();
        } else if (v.getId() == R.id.otp_confirm) {
            initProgressDialog.show();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("password", mEdOtpNum.getText().toString());
            OtpAuthCallBack callback = new OtpAuthCallBack() {
                @Override
                public void callback(String url, JSONObject json, AjaxStatus status) {
                    super.callback(url, json, status);
                    ViewModel.Log("url : " + url, "tkofs_otp");
                    ViewModel.Log("" + status.getCode(), "tkofs_otp");
                    ViewModel.Log(json, "tkofs_otp");
                    if (json != null && getVO() != null) {
                        if (!ViewModel.OTP_SUCCESS_CODE.equals(getVO().code)) {
                            ViewModel.Log(":실패:" + getVO().code, "tkofs_otp");
                            ViewModel.Log(getVO().message, "tkofs_otp");
                            Toast.makeText(getBaseContext(), getVO().message, Toast.LENGTH_LONG).show();
                        } else {
                            // 인증성공
                            Intent intent = new Intent();
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "OTP 인증에 실패하였습니다.[내부오류]", Toast.LENGTH_LONG).show();
                    }

                }
            };

            callback.progress(initProgressDialog);
            new ApiLoaderEx<JSONObject>(new OtpAuth(this), this, callback, paramMap).execute();
        } else if (v.getId() == R.id.otp_req) {
            /*Intent intent = getPackageManager().getLaunchIntentForPackage(OTPConstant.OTP_PACKAGE_NAME);
            if (intent != null) {
                String userId = HMGWServiceHelper.empcode.trim(); // 사용자 아이디
                if (!userId.isEmpty()) {
                    // OTP 요청
                    mOTPRemoteService.genOTPReq(APP_KEY, // APP KEY
                            userId, // 사용자 아이디
                            false, // OTP 앱의 PIN(비밀번호) 강제적용여부
                            isOtpSave, // OTP 자동반환여부
                            false);  // 사용자아이디 대소문자 구분 여부
                } else {
                    Toast.makeText(this, "사용자 아이디를 입력 해 주세요.", Toast.LENGTH_LONG).show();
                }
            } else {
                showInstallOtpApp();
            }*/
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        /*if(mOTPRemoteService.bind()){
            ViewModel.Log("anyOtp bind ok ", "tkofs_otp");
        }else{
            ViewModel.Log("anyOtp bind fail", "tkofs_otp");
        }*/
    }

    @Override
    public void onStop() {
        super.onStop();
        //mOTPRemoteService.unbind();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Debug.trace("");
    }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Debug.trace("");
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        /*if (mOTPRemoteService != null) {
            mOTPRemoteService.unbind();
        }*/
        super.onDestroy();
        Debug.trace("");
    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Debug.trace("");
        finish();
    }

    @Override
    public void finish() {
        Debug.trace("");
        // TODO Auto-generated method stub
        super.finish();
    }

    private void showInstallOtpApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("OTP 앱 설치가 필요합니다.");
        builder.setNegativeButton(R.string.label_cancel, null);
        builder.setPositiveButton("설치", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
                //marketLaunch.setData(Uri.parse("market://details?id=" + OTPConstant.OTP_PACKAGE_NAME));
                startActivity(marketLaunch);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}