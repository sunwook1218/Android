/**
 * @Autor tkofs
 * @date 20.06.23
 */

package com.hs.mobile.gw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.ext.vac.VacHelper;
import com.hs.mobile.gw.ext.vpn.VPNConnectHelper;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.gnb.GnbDataModel;
import com.hs.mobile.gw.gnb.Group;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.GWOpenApi;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.RootActivity;

import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class LnbActivity extends RootActivity implements View.OnClickListener {

    public ImageView btn_single;
    public ImageView btn_double;
    public Boolean doubleMode = true;
    public String parentActivity;
    private ArrayList<Group> GroupList;
    public MainModel m_model;
    private Activity ac;
    private boolean onPause = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        try {
            parentActivity = intent.getStringExtra("activity");
        } catch (Exception e) {
        }

        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_lnb);
        ac = this;

        final ImageView profile_img = (ImageView) findViewById(R.id.profile_image);
        TextView uname = (TextView) findViewById(R.id.lnb_user_name);
        TextView ugroup = (TextView) findViewById(R.id.lnb_user_group);

        uname.setText(HMGWServiceHelper.name);
        ugroup.setText(HMGWServiceHelper.deptName);

        m_model = MainModel.getInstance();
        m_model.createAqueryIntence(getApplicationContext());

        GnbDataModel.menuCountMap = new HashMap<String, View>();
        //profile_img.setImageURI( HMGWServiceHelper.photoLink );
        if (HMGWServiceHelper.VIEW_PROFILE_PHOTO && !TextUtils.isEmpty(HMGWServiceHelper.photoLink)) {
            try {
                AjaxCallback<InputStream> cb = new AjaxCallback<InputStream>() {
                    @Override
                    public void callback(String url, InputStream inp, AjaxStatus status) {
                        m_model.getAq().id(profile_img).image(new BitmapDrawable(getResources(), inp));
                    }
                };
                cb.header("User-Agent", HMGWServiceHelper.USER_AGENT);
                cb.header("Accept", "application/json");
                cb.header("Accept-Language", Locale.getDefault().toString());
                List<Cookie> cookies;
                HashMap<String, String> cookieStore;
                if (HMGWServiceHelper.cookies != null) {
                    cookies = HMGWServiceHelper.cookies;
                    cookieStore = new HashMap<String, String>();
                    for (Cookie cookie : cookies) {
                        cookieStore.put(cookie.getName(), cookie.getValue());
                    }
                    cb.cookies(cookieStore);
                }
                cb.param("openapipath", HMGWServiceHelper.photoLink);
                m_model.getAq().ajax(GWOpenApi.BYPASS_STREAM, InputStream.class, cb);
            }catch(Exception e){

            }
        }

        // 프로필 이미지 원형 적용
        //GradientDrawable drawable = (GradientDrawable) this.getDrawable(R.drawable.border_rouding);
        //profile_img.setBackground(drawable);
        //profile_img.setBackground(new ShapeDrawable(new OvalShape()));
        //if (Build.VERSION.SDK_INT >= 21) profile_img.setClipToOutline(true);

        LinearLayout btn_close = findViewById(R.id.BTN_MENU_CLOSE); // 닫기버튼
        btn_close.setOnClickListener(this);

        LinearLayout gnbContainer = (LinearLayout) findViewById(R.id.GNB_BTNS_CONTAINER);

        /* tkofs btn_single = (ImageView) findViewById(R.id.btn_mode_single);
        btn_double = (ImageView) findViewById(R.id.btn_mode_double);
        btn_single.setOnClickListener(this);
        btn_double.setOnClickListener(this);*/

        TextView logout = (TextView) findViewById(R.id.btn_log_out);
        logout.setOnClickListener(this);

        init();
    }

    public void init(){

        if (HMGWServiceHelper.mgw_menu != null && HMGWServiceHelper.mgw_menu.length() > 0) {
            try {
                createGroup(HMGWServiceHelper.mgw_menu);
            } catch (Exception e) {
            }
        }
    }

    @Override
    protected void onStart() {
        ViewModel.isLiveLnbAct = true;
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        onPause = true;
        stopTimer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if(onPause){
            initCountUpdater();
        }
        super.onResume();
    }

    private void createGroup(JSONArray data) {

        GroupList = new ArrayList();
        boolean menuInitChk = true;
        for (int i = 0; i < data.length(); ++i) {
            try {
                JSONObject json = new JSONObject(data.get(i).toString());
                GroupList.add(new Group(this, json, parentActivity));
            } catch (Exception e) {
                menuInitChk = false;
                ViewModel.Log("== menu 생성 오류");
            }
        }
        if(menuInitChk){
            initCountUpdater();// 카운트 업데이터
        }
    }

    Timer timer = null;
    TimerTask timerTask = null;
    public void initCountUpdater() {
        stopTimer();
        timer =  new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                new UpdateCountsTask().execute("");
            }
        };
        timer.schedule(timerTask, 0, HMGWServiceHelper.count_pooling_interval*1000);
    }

    public void stopTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
    private class UpdateCountsTask extends AsyncTask<String, JSONObject, JSONObject> {
        private UpdateCountsTask() {
            super();
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            updateMainMenuCounts(result);
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            return m_model.getOpenApiService().getCounts(ac);
        }
    }
    public void updateMainMenuCounts(JSONObject counts) {
        if (counts == null) {
            return;
        }
        if (ac == null)
        {
            Debug.trace("do not update count");
            return;
        }

        String mailNew = counts.optInt("mailNew") != 0 ? counts.optString("mailNew") : null;
        String mailUnread = counts.optInt("mailUnread") != 0 ? counts.optString("mailUnread") : null;
        String mtrlNew = counts.optInt("mtrlNew") != 0 ? counts.optString("mtrlNew") : null;
        String apprWait = counts.optInt("apprWait") != 0 ? counts.optString("apprWait") : null;
        String approving = counts.optInt("approving") != 0 ? counts.optString("approving") : null;
        String publicWait = counts.optInt("publicWait") != 0 ? counts.optString("publicWait") : null;
        String recvWait = counts.optInt("recvWait") != 0 ? counts.optString("recvWait") : null;
        String receiving = counts.optInt("receiving") != 0 ? counts.optString("receiving") : null;
        String resCount = counts.optInt("resCount") != 0 ? counts.optString("resCount") : null;
        String schToday = counts.optInt("schToday") != 0 ? counts.optString("schToday") : null;
        String schShare = counts.optInt("schShare") != 0 ? counts.optString("schShare") : null;
        String squareNew = counts.optInt("squareNew") != 0 ? counts.optString("squareNew") : null;

        setMenuCount(MenuListHelper.MenuIDsMap.mail_received.toString(), mailUnread);
        //setMenuCount(MenuListHelper.MenuIDsMap.board_recent.toString(), mtrlNew); // tkofs 게시판 카운트 제거
        setMenuCount(MenuListHelper.MenuIDsMap.gongram_waiting.toString(), publicWait);
        setMenuCount(MenuListHelper.MenuIDsMap.approval_waiting.toString(), apprWait);
        setMenuCount(MenuListHelper.MenuIDsMap.approval_receipt_waiting.toString(), recvWait);
        setMenuCount(MenuListHelper.MenuIDsMap.square_plus_my_group.toString(), squareNew);
    }
    public void setMenuCount(String menuID, String count) {
        View view = GnbDataModel.menuCountMap.get(menuID);
        if (view != null){
            TextView menuCountView = (TextView) view.findViewById(R.id.badge_count); // tkofs R.id.menu_count
            if (menuCountView != null) {
                if (count == null || count.equals("0") || Integer.parseInt(count) == 0) {
                    menuCountView.setVisibility(View.GONE);
                } else {
                    menuCountView.setVisibility(View.VISIBLE);
                    menuCountView.setText(count);
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LinearLayout menuContainer = findViewById(R.id.GNB_BTNS_CONTAINER);
        menuContainer.removeAllViews();
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * @상단버튼
             */
            case R.id.BTN_MENU_CLOSE: // 메뉴닫기
                if (parentActivity.equals("Main"))
                    MainFragment.menuListHelper.showMenuByID("home_home");
                finish();
                break;

           /* case R.id.btn_mode_single: // column 1
                if (!doubleMode) return;
                doubleMode = false;
                //modeChange();
                break;

            case R.id.btn_mode_double: // column 2
                if (doubleMode) return;
                doubleMode = true;
                //modeChange();
                break;*/

            case R.id.btn_log_out: // 로그아웃
                /*Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "LOGOUT");
                setResult(RESULT_OK, resultIntent);*/
                if(ViewModel.currentActivity != null){
                    new AlertDialog.Builder(ViewModel.currentActivity).setTitle(R.string.menu_logout).setMessage(R.string.alert_message_app_logout)
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    AndroidUtils.deleteSaveFolder(null);
                                    if (parentActivity.equals("Main")) {
                                        ViewModel.Log("main", "tkofs_logout");
                                        MainFragment.getController().logout(null);
                                    } else {
                                        ViewModel.Log("trigger main", "tkofs_logout");
                                        GnbDataModel.mainFragment.tiggerLogout();
                                    }
                                    finish();
                                }
                            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).create().show();
                }
                break;
        }
    }

    /*private void modeChange() {
        if (doubleMode) {
            btn_double.setImageResource(R.drawable.list_ic_02_on);
            btn_single.setImageResource(R.drawable.list_ic_01_off);
        } else {
            btn_double.setImageResource(R.drawable.list_ic_02_off);
            btn_single.setImageResource(R.drawable.list_ic_01_on);
        }
        updateMenuView();
    }*/

    private void updateMenuView() {
        for (int i = 0; i < GroupList.size(); ++i) GroupList.get(i).alignChange(doubleMode);
    }

    @Override
    public void finish() {
        stopTimer();
        overridePendingTransition(R.anim.slide_hide_right, R.anim.slide_show_left);
        super.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    @Override
    public void onBackPressed() {
        if( MainModel.getInstance().isPopupMode() || ViewModel.backKey_Lock ) return;

        try {
            final Context mc = this;
            new AlertDialog.Builder(this).setTitle(R.string.menu_exit).setMessage("alert_message_app_exit 33")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            AndroidUtils.deleteSaveFolder(null);
                            //moveTaskToBack(true);
                            if(!ViewModel.apiName.VPN.isApiPass()) {
                                ViewModel.Log("VPN 종료 LnbActivity", "tkofs_vpn");
                                VPNConnectHelper.sslvpnDisconnect();
                            }
                            if(!ViewModel.apiName.VAC.isApiPass()) {
                                ViewModel.Log("VAC 종료 LnbActivity", "tkofs_vac");
                                VacHelper.release();
                            }

                            if(ViewModel.apiName.VPN.isApiPass()) {
                                finishAffinity();
                            }
                            //finish();
                            //android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) { }
            }).create().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        stopTimer();
        ViewModel.isLiveLnbAct = false;
        super.onDestroy();
    }
}
