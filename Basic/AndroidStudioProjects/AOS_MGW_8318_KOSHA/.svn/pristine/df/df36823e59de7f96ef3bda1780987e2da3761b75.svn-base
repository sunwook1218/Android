package com.hs.mobile.gw.ext.mis;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.ext.ViewModel;
import com.softsecurity.transkey.TransKeyActivity;
import com.softsecurity.transkey.TransKeyCtrl;

public class MisHelper {

    Context context;                //LoginFragment Context
    View view;                      //LoginFragment View

    TransKeyCtrl[] m_tkMngr = null;     //가상 키패드
    boolean isViewCtrlKeypad = false;   //가상 키패드 표시여부

    private MisController misController;    //가상 키패드 이벤트 처리 Controller

    protected LinearLayout layoutLoginArea;
    protected LinearLayout hLayoutPassword;
    protected EditText mEdUserID;
    protected EditText mEdPassword;

    public MisHelper(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public void inint() {
        misController = new MisController(this);
        isViewCtrlKeypad = false;

        layoutLoginArea = (LinearLayout) view.findViewById(R.id.login_area);
        hLayoutPassword = (LinearLayout) view.findViewById(R.id.keylayout);
        mEdUserID= (EditText) view.findViewById(R.id.userid);
        mEdPassword = (EditText) view.findViewById(R.id.password);

        mEdPassword.setOnClickListener(misController);
        mEdPassword.setOnFocusChangeListener(misController);
        mEdPassword.setFocusable(false);

        m_tkMngr = new TransKeyCtrl[1];

        initTransKeyPad(0, MisConfig.MIS_KEY_PAD_TYPE,
                MisConfig.MIS_INPUT_TYPE,
                MisConfig.MIS_INPUT_LABEL,
                MisConfig.MIS_INPUT_HINT,
                MisConfig.MIS_INPUT_MAX_LENGTH,
                MisConfig.MIS_INPUT_MAX_LENGTH_MESSAGE,
                0,
                false,
                (FrameLayout)view.findViewById(R.id.keypadContainer),
                (EditText)(view.findViewById(R.id.inputlayout_mis)).findViewById(R.id.password),
                (HorizontalScrollView)view.findViewById(R.id.keyscroll),
                (LinearLayout)view.findViewById(R.id.keylayout),
                (ImageButton)(view.findViewById(R.id.inputlayout_mis)).findViewById(R.id.pwd_clear_btn),
                (RelativeLayout)view.findViewById(R.id.keypadBallon),
                null,false);
    }

    protected void showTransKeyPad(int index, int keyPadType) {

        mEdPassword.requestFocus();
        // 보안키패드 보이는 동안 사원번호 focus disable 처리;
        mEdUserID.setEnabled(false);
        hLayoutPassword.setVisibility(View.VISIBLE);
        mEdPassword.setText("");
        m_tkMngr[index].showKeypad(keyPadType);
        isViewCtrlKeypad = true;

        if(layoutLoginArea != null){
            //로그인 input 영역 정렬을 변경
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)layoutLoginArea.getLayoutParams();
            layoutParams.bottomMargin = dpToPx(context, 260);
            layoutLoginArea.setLayoutParams(layoutParams);
        }
    }

    protected void initTransKeyPad(int index, int keyPadType, int textType, String label, String hint,
                                int maxLength, String maxLengthMessage, int line3Padding, boolean bReArrange,
                                FrameLayout keyPadView, EditText editView, HorizontalScrollView scrollView,
                                LinearLayout inputView, ImageButton clearView, RelativeLayout ballonView, ImageView lastInput, boolean bUseAtmMode) {
        try {
            if (m_tkMngr[index] == null)
                m_tkMngr[index] = new TransKeyCtrl(context);
        } catch (Exception e) {
            ViewModel.Log("Exception : " + e.getMessage(), "tkofs_mis");
        }
        //Activity 로 처리할 때 처럼 파라미터를 인텐트에 넣어서 처리.
        Intent newIntent = getIntentParam(keyPadType, textType, label, hint, maxLength, maxLengthMessage, line3Padding, bUseAtmMode);
        m_tkMngr[index].init(newIntent, keyPadView, editView, scrollView, inputView, clearView, ballonView, lastInput);
        m_tkMngr[index].setReArrangeKeypad(bReArrange);
        m_tkMngr[index].setTransKeyListener(misController);
        m_tkMngr[index].setTransKeyListenerEx(misController);
        m_tkMngr[index].setTransKeyListenerCallback(misController);
    }


    protected Intent getIntentParam(int keyPadType, int textType, String label, String hint,
                                 int maxLength, String maxLengthMessage, int line3Padding, boolean useAtmMode) {

        Intent newIntent = new Intent(context.getApplicationContext(), TransKeyActivity.class);

        newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_TYPE, keyPadType);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_TYPE, textType);

        //키패드입력화면의 입력 라벨
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_NAME_LABEL, label);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SPACE, false);

        //최대 입력값 설정 1 - 16
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_MAXLENGTH, maxLength);

        //인터페이스 - maxLength시에 메시지 박스 보여주기. 기본은 메시지 안나옴.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_MAX_LENGTH_MESSAGE, maxLengthMessage);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_TOAST_MAX_ALERT, false);

        //해당 Hint 메시지를 보여준다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SET_HINT, hint);

        //Hint 테스트 사이즈를 설정한다.(단위 dip, 0이면 디폴트 크기로 보여준다.)
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SET_HINT_TEXT_SIZE, MisConfig.MIS_INPUT_HINT_TEXT_SIZE);

        //커서를 보여준다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SHOW_CURSOR, true);

        //커스텀 이미지 커서를 사용한다
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_CUSTOM_CURSOR, false);

        //에디트 박스안의 글자 크기를 조절한다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_EDIT_CHAR_REDUCE_RATE, MisConfig.MIS_INPUT_EDIT_CHAR_REDUCE_RATE);

        //심볼 변환 버튼을 비활성화 시킨다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SYMBOL, false);

        //심볼 변환 버튼을 비활성화 시킬 경우 팝업 메시지를 설정한다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SYMBOL_MESSAGE, "심볼키는 사용할 수 없습니다.");

        newIntent.putExtra(TransKeyActivity.mTK_PARAM_PREVENT_CAPTURE, false);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_HIDE_TIMER_DELAY, 1);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_KEYPAD_ANIMATION, false);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_ATM_MODE, useAtmMode);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_NUMPAD_USE_CANCEL_BUTTON, true);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_QWERTY_USE_CANCEL_BUTTON, true);

        newIntent.putExtra(TransKeyActivity.mTK_PARAM_MIN_LENGTH_MESSAGE, MisConfig.MIS_INPUT_MIN_LENGTH_MESSAGE);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_MINLENGTH, MisConfig.MIS_INPUT_MIN_LENGTH);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_SHIFT_OPTION, true);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_TALKBACK, true);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_AUTO_FOCUSING, false);	//자동 포커싱 옵션 16.06.02 jiwan
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_LANGUAGE, TransKeyActivity.mTK_Language_Korean);

        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_CLEAR_BUTTON, true);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SUPPORT_ORIENTATION, TransKeyActivity.mTK_Orientation_Landscape);
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_PLAY_RES_BUTTON_SOUND, true);

        //라이센스 적용
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_LICENSE_FILE_NAME, "license_mtranskey");

        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SUPPORT_ORIENTATION, TransKeyActivity.mTK_Orientation_Landscape);

        // 쿼티 키패드 심볼/재배열 위치 변경
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_QWERTY_SWAP_SYMBOL_REARRANGE, true);
        //가로모드 시 쿼티키패드 높낮이를 비율로 변경
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_QWERTY_HORIZONTAL_HEIGHT, MisConfig.MIS_HORIZONTAL_HEIGHT);

        return newIntent;
    }

    protected int dpToPx(Context context, float dp){
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }
}
