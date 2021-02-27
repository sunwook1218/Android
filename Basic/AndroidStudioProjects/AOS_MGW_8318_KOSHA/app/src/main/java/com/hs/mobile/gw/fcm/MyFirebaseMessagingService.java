package com.hs.mobile.gw.fcm;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.androidquery.callback.AjaxStatus;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hs.mobile.crypto.BASE64Decoder;
import com.hs.mobile.crypto.Crypto;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainActivity;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MenuListHelper;
import com.hs.mobile.gw.PopupActivity;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.openapi.GetDocViewAuth;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO.SquarePushAction;
import com.hs.mobile.gw.openapi.squareplus.SpGetContents;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquare;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BadgeUtil;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;
import com.hs.mobile.gw.util.PopupUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static final String PREF_KEY_CURRENT_SENDER_ID = "current_sender_id";
    public static final String NOTI_CHANNEL_DEFAULT = "mgw_default";
    public static final String NOTI_CHANNEL_DEFAULT2 = "mgw_default2";
    public static final String NOTI_CHANNEL_SQUARE = "mgw_square";

    // Notification title
    public static String NOTIF_TITLE = "";
    // Notification id
    private static final int NOTIF_DEFAULT = 100; // default notification id
    private static final int NOTIFICATION_ID_DEFAULT_MAIL = NOTIF_DEFAULT + 1;
    private static final int NOTIFICATION_ID_DEFAULT_BOARD = NOTIF_DEFAULT + 2;
    private static final int NOTIFICATION_ID_DEFAULT_APRR = NOTIF_DEFAULT + 3;
    private static final int NOTIFICATION_ID_DEFAULT_SCHEDULE = NOTIF_DEFAULT + 4;
    private static final int NOTIFICATION_ID_DEFAULT_SQUARE_PLUS = NOTIF_DEFAULT + 5;

    private static final int NOTIF_SQUARE = 200; // square notification id

    // Notification manager to displaying arrived push notifications
    private NotificationManager mNotifMan;

    @Override
    public void onCreate() {
        super.onCreate();
        Debug.trace("");
        mNotifMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            List<NotificationChannel> channels = new ArrayList<>();
            channels.add(getDefaultChannel());
            channels.add(getDefaultChannel2());
            channels.add(getSquareChannel());
            mNotifMan.createNotificationChannels(channels);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel getDefaultChannel() {
        NotificationChannel channel = new NotificationChannel(NOTI_CHANNEL_DEFAULT,
                "기본 알림 채널",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setShowBadge(true);
        return channel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel getDefaultChannel2() {
        NotificationChannel channel = new NotificationChannel(NOTI_CHANNEL_DEFAULT2,
                "기본 알림 채널",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setShowBadge(false);
        return channel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel getSquareChannel() {
        NotificationChannel channel = new NotificationChannel(NOTI_CHANNEL_SQUARE,
                "스퀘어 알림 채널",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setShowBadge(false);
        return channel;
    }

    private void startPopupActivity(Context context, String text) {
        Intent intent = new Intent(context, PopupActivity.class);
        intent.putExtra("message", text);
        intent.putExtra("ok_text", getResources().getString(R.string.message_confirm));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }

    private Notification getNotification(String channel, String text, PendingIntent pIntent) {
        return getNotification(channel, text, pIntent, 0);
    }

    private Notification getNotification(String channel, String text, PendingIntent pIntent, int badgeCount) {
        return new NotificationCompat.Builder(this, channel)
                .setSmallIcon(R.drawable.icon_app_hu)
                .setTicker(getString(R.string.message_noti_alert_title))
                .setContentTitle(NOTIF_TITLE)
                .setContentText(text)
                .setDefaults(Notification.DEFAULT_ALL)
                .setVibrate(getVibrateTime())
                .setShowWhen(true)
                .setAutoCancel(true)
                .setNumber(badgeCount)
                .setContentIntent(pIntent)
                .build();
    }

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Debug.trace("From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Debug.trace("Message data payload: " + remoteMessage.getData());
            handleNow(remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Debug.trace("Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Debug.trace("Refreshed token: " + token);
        HMGWServiceHelper.tokenId = Crypto.getInstance().encrypt(token);
        sendRegistrationToServer(token);
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     *
     * @param data
     */
    private void handleNow(Map<String, String> data) {
        Debug.trace("Short lived task is done.");
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean useNoti = pref.getBoolean(getString(R.string.key_noti_mode), HMGWServiceHelper.USE_PUSH_SERVICE);

        Debug.trace("useNoti = " + useNoti);
        String msg = data.get("body");

        if (!TextUtils.isEmpty(msg)) {
            try {
                String decode_msg = new String(BASE64Decoder.decodeBuffer(msg));
                Debug.trace("GW_FCM decode msg[" + decode_msg + "] original msg[" + msg + "]");

                if (handleBadgeContainsMsg(decode_msg, useNoti)) {
                    return;
                }

                if (handleSquarePushMsg(decode_msg, useNoti)) {
                    return;
                }

                if (useNoti) {
                    showDefaultNotification(this, decode_msg, "", 0);
                }

            } catch (Exception e) {
                Debug.trace(e.getMessage());
            }
        }
    }

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        Debug.trace("token = " + token);

        SharedPreferences shrdPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = shrdPref.edit();
        editor.putString(getApplicationContext().getString(R.string.key_registration_id), Crypto.getInstance().encrypt(token));
        editor.commit();
    }

    private boolean handleBadgeContainsMsg(String strJson, boolean useNoti) {
        try {
            DefaultMessage message = new Gson().fromJson(strJson, DefaultMessage.class);
            BadgeUtil.updateCount(this, message.getBadgeCount());
            if (useNoti) {
                showDefaultNotification(this, message.getTitle(), message.getCategory(),
                        message.getBadgeCount(), message.getEvent());
            }
        } catch (JsonSyntaxException e) {
            Debug.trace(e.getMessage());
        }
        return false;
    }

    private boolean handleSquarePushMsg(String strJson, boolean useNoti) {
        try {
            JSONObject json = new JSONObject(strJson);
            SquarePushVO squarePushVO = new SquarePushVO(json);
            final String currentSquareId = MainModel.getInstance().getCurrentSquareId();
            Debug.trace("CurrentSquareId: " + currentSquareId);

            if (squarePushVO.square_action.equals(SquarePushAction.ALERT_ACTION_BROADCAST)
                    && !TextUtils.equals(currentSquareId, squarePushVO.square_id)) {
                if (useNoti) {
                    showSquareNotification(this, squarePushVO.square_msg);
                }
            } else {
                MainModel.getInstance().notifyPush(squarePushVO);
            }
            return true;
        } catch (JSONException e) {
            Debug.trace(e.getMessage());
        }

        return false;
    }

    private void showDefaultNotification(Context context, String text, String category, int badgeCount,
                                         @Nullable String event) {
        ViewModel.Log("showDefaultNotification", "tkofs_badge");
        final boolean isForeground = checkTopActivity(context);
        if (isForeground) {
            startPopupActivity(context, text);
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            if (!TextUtils.isEmpty(category)) {
                intent.putExtra(MainActivity.PUSH_MSG_CATEGORY, category);
            }
            // SEOJEEHWA click_view_url object 가 빈값으로 올 수 있으므로 extra 포함 여부 조건 추가 포함
            if (event != null && !(event.equals("") || event.equals("{}"))) {
                intent.putExtra(MainActivity.PUSH_MSG_EVENT, event);
            }
            int notiId;
            switch (category) {
                case "mail":
                    notiId = NOTIFICATION_ID_DEFAULT_MAIL;
                    break;
                case "board":
                    notiId = NOTIFICATION_ID_DEFAULT_BOARD;
                    break;
                case "appr":
                    notiId = NOTIFICATION_ID_DEFAULT_APRR;
                    break;
                case "hscalendar":
                    notiId = NOTIFICATION_ID_DEFAULT_SCHEDULE;
                    break;
                case "square":
                    notiId = NOTIFICATION_ID_DEFAULT_SQUARE_PLUS;
                    break;
                default:
                    return;
            }

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pi = PendingIntent.getActivity(context, notiId, intent, PendingIntent.FLAG_ONE_SHOT);

            int value = Integer.parseInt(HMGWServiceHelper.noti_mps_badge);
            value = 1;
            if (value != 0 && badgeCount != 0) {
                mNotifMan.notify(notiId, getNotification(NOTI_CHANNEL_DEFAULT, text, pi, badgeCount));
            }
            else {
                mNotifMan.notify(notiId, getNotification(NOTI_CHANNEL_DEFAULT2, text, pi));
            }
        }
    }

    private void showDefaultNotification(Context context, String text, String category, int badgeCount) {
        showDefaultNotification(context, text, category, badgeCount, null);
    }

    private void showSquareNotification(Context context, String text) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
        mNotifMan.notify(NOTIF_SQUARE, getNotification(NOTI_CHANNEL_SQUARE, text, pi));
    }

    private long[] getVibrateTime() {
        long[] vibrateTime = new long[1];
        vibrateTime[0] = 100;
        return vibrateTime;
    }

    private boolean checkTopActivity(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            Debug.trace("ActivityManager is null. return false");
            return false;
        }
        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

        for (int i = 0; i < taskInfo.size(); i++) {
            if (taskInfo.get(i).topActivity.getClassName().contains("com.hs.mobile.gw"))
                return true;
        }
        return false;
    }

    private static void showPopupWebView(String url) {
        Debug.trace("PopupWebView::URL: " + url);
        MainModel.getInstance().setPopupMode(true);
        MainFragment.getController().loadWebviewWithDelay(url);
    }

    public static void showPushDetail(@NonNull final MainFragment mainFragment,
                                      @NonNull MenuListHelper.MenuIDsMap menuID,
                                      @Nullable String event) {
        if (TextUtils.isEmpty(event)) {
            Debug.trace("Push message has no click event. Nothing will be to do.");
            return;
        }
        switch (menuID) {
            case mail_received: { // Mail
                DefaultMessage.MailClickEvent clickEvent = new Gson().fromJson(event, DefaultMessage.MailClickEvent.class);
                final String type = clickEvent.getBoxType();
                final String link = clickEvent.getLink();
                final String link2 = clickEvent.getLink2();
                final boolean isSecurity = clickEvent.isSecurity();
                final String openLink = TextUtils.equals(HMGWServiceHelper.mail_type, "db") ? link : link2;
                if (isSecurity) {
                    checkPasswordForMail(mainFragment, type, openLink);
                } else {
                    String url = "javascript:showMailDetailsPopup('" + type + "','" + openLink + "')";
                    showPopupWebView(url);
                }
                break;
            }
            case board_recent: {
                DefaultMessage.BoardClickEvent clickEvent = new Gson().fromJson(event, DefaultMessage.BoardClickEvent.class);
                final String link = clickEvent.getLink();
                final String commentCount = String.valueOf(clickEvent.getCommentCount());
                final String departmentId = clickEvent.getDepartmentId();
                String url = "javascript:showBoardDetailsPopup('" + link + "'," + commentCount + ",'" + departmentId + "');";
                showPopupWebView(url);
                break;
            }
            case approval_waiting: {
                DefaultMessage.ApprClickEvent clickEvent = new Gson().fromJson(event, DefaultMessage.ApprClickEvent.class);
                final String link = clickEvent.getLink();
                final String boxType = clickEvent.getBoxType();
                final String apprId = clickEvent.getApprId();
                final String participantApprType = clickEvent.getParticipantApprType();
                final String apprStatus = clickEvent.getApprStatus();
                final String attachCount = String.valueOf(clickEvent.getAttachCount());
//                final String statusImage = source.getQueryParameter("statusImage");
//                final String statusImageEx = source.getQueryParameter("statusImageEx");
                final String externalDoc = clickEvent.getExternalDoc();
                final String summaryDoc = clickEvent.getSummaryDoc();
                final String wordType = clickEvent.getWordType();
                final String additionalOfficeUserId = clickEvent.getAdditionalOfficeUserId();
                final boolean isSecurity = clickEvent.isSecurity();
                final String docViewAuthLink = clickEvent.getDocViewAuthLink();
                final String participantId = clickEvent.getParticipantId();

                boolean commentCk = clickEvent.hasOpinion();
                boolean postponeCk = false;
//                if (!TextUtils.isEmpty(statusImageEx)) {
//                    commentCk = statusImageEx.indexOf("opinion.gif") == -1 ? false : true;
//                }
//                if (!TextUtils.isEmpty(statusImage)) {
//                    postponeCk = statusImage.charAt(0) == 'n';
//                }
                final String strUrl = "javascript:showSignDetailsPopup('" + link + "','" + boxType + "','" + apprId + "','"
                        + participantApprType + "','" + apprStatus + "','" + attachCount + "'," + commentCk + "," + postponeCk + ",'"
                        + externalDoc + "','" + summaryDoc + "','" + wordType + "','" + additionalOfficeUserId + "','" + participantId + "',0)";

                if (TextUtils.isEmpty(docViewAuthLink)) {
                    // 보안메일 검사
                    if (isSecurity) {
                        checkPasswordForAppr(mainFragment, strUrl);
                    } else {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                showPopupWebView(strUrl);
//                            }
//                        }, 200);

                        showPopupWebView(strUrl);
                    }
                } else {
                    new ApiLoader<>(new GetDocViewAuth(), mainFragment.getActivity(), new DefaultCallback() {
                        @Override
                        public void onResponse(String strRet) {
                            super.onResponse(strRet);
                            boolean hasAuth = true;
                            if (getJsonObject() != null) {
                                hasAuth = getJsonObject().optJSONObject("channel")
                                        .optJSONArray("item").optJSONObject(0)
                                        .optBoolean("#text");
                            }
                            if (!hasAuth) {
                                PopupUtil.showDialog(mainFragment.getActivity(), R.string.error_sign_not_allowed_to_access);
                                return;
                            }
                            // 보안메일 검사
                            if (isSecurity) {
                                checkPasswordForAppr(mainFragment, strUrl);
                            } else {
                                showPopupWebView(strUrl);
                            }
                        }
                    }, docViewAuthLink).execute();
                }
                break;
            }
            case square_plus_my_group: {
                DefaultMessage.SquarePlusClickEvent clickEvent = new Gson().fromJson(event, DefaultMessage.SquarePlusClickEvent.class);
                Debug.trace(clickEvent.toString());
                final String squareId = clickEvent.getSquareId();
                final String contentsId = clickEvent.getContentsId();

                if (!TextUtils.isEmpty(squareId) && !TextUtils.isEmpty(contentsId)) {
                    SpSquareCallBack spSquareCallBack = new SpSquareCallBack(mainFragment.getActivity(), SpSquareVO.class) {
                        @Override
                        public void callback(String url, JSONObject json, AjaxStatus status) {
                            super.callback(url, json, status);
                            PopupUtil.hideLoading(mainFragment.getActivity());
                            final SpSquareVO m_squareData = item;

                            {
                                SpContentsCallBack spSquareCallBack = new SpContentsCallBack(
                                        mainFragment.getActivity(), SpContentsVO.class) {

                                    @Override
                                    public void callback(String url, JSONObject json,
                                                         AjaxStatus status) {
                                        super.callback(url, json, status);

                                        Bundle build = new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, squareId).add(MainModel.ARG_KEY_SP_CONTENTS_ITEM, item)
                                                .add(MainModel.ARG_KEY_SP_SQUARE_STATUS, m_squareData.getStatusEnum())
                                                .add(MainModel.ARG_KEY_SP_CONTENTS_ID, item.getContentsId()).add(MainModel.ARG_KEY_SHOW_KEYBOARD, false)
                                                .add(MainModel.ARG_KEY_SP_PUSH, true).build();
                                        MainModel.getInstance().showSubActivity(mainFragment.getActivity(), SubActivity.SubActivityType.SP_CONTENTS_DETAIL, build);
                                    }
                                };

                                { // API명
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("squareId", squareId);
                                    params.put("contentsId", contentsId);
                                    new ApiLoaderEx<>(new SpGetContents(mainFragment.getActivity()),
                                            mainFragment.getActivity(), spSquareCallBack, params).execute();
                                }
                            }
                        }
                    };

                    { // API명
                        HashMap<String, String> params = new HashMap<>();
                        params.put("squareId", squareId);
                        new ApiLoaderEx<>(new SpGetSquare(mainFragment.getActivity()), mainFragment.getActivity(), spSquareCallBack, params).execute();
                    }
                }
                break;
            }
            default:
                Debug.trace("Not supported message has been received!");
        }
    }

    private static void checkPasswordForAppr(@NonNull final MainFragment mainFragment,
                                             @NonNull final String url) {
        @SuppressLint("ResourceType") View dialog = LayoutInflater.from(mainFragment.getActivity()).inflate(R.layout.dialog_password,
                (ViewGroup) mainFragment.getActivity().findViewById(R.layout.main));
        AlertDialog.Builder builder = new AlertDialog.Builder(mainFragment.getActivity());
        final EditText inputBox = dialog.findViewById(R.id.chekcPassword);
        inputBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mainFragment.getPasswordDialogForMail().getWindow()
                            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        builder.setView(dialog);
        builder.setTitle(R.string.label_password_sign);
        builder.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = (inputBox.getText()).toString();
                if (TextUtils.isEmpty(password)) {
                    PopupUtil.showToastMessage(mainFragment.getActivity(), R.string.message_check_password_empty);
                } else {
                    boolean isValid = mainFragment.getMainModel().getOpenApiService().checkPassword(mainFragment.getActivity(),
                            OpenAPIService.ApiCode.check_login_password, password);
                    if (isValid) {
                        showPopupWebView(url);
                    } else {
                        PopupUtil.showDialog(mainFragment.getActivity(), R.string.message_check_login_password_invalid);
                        hideWebView(mainFragment);
                    }
                    inputBox.setText(null);
                }
            }
        });
        builder.setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputBox.setText(null);
                hideWebView(mainFragment);
            }
        });
        mainFragment.setPasswordDialogForMail(builder.create());
        mainFragment.getPasswordDialogForMail().show();
    }

    private static void checkPasswordForMail(@NonNull final MainFragment mainFragment,
                                             final String type, final String openLink) {
        @SuppressLint("ResourceType") View dialog = LayoutInflater.from(mainFragment.getActivity()).inflate(R.layout.dialog_password,
                (ViewGroup) mainFragment.getActivity().findViewById(R.layout.main));
        AlertDialog.Builder builder = new AlertDialog.Builder(mainFragment.getActivity());
        final EditText inputBox = dialog.findViewById(R.id.chekcPassword);
        inputBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mainFragment.getPasswordDialogForMail().getWindow()
                            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        builder.setView(dialog);
        builder.setTitle(R.string.label_password_mail);
        builder.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = (inputBox.getText()).toString();
                if (TextUtils.isEmpty(password)) {
                    PopupUtil.showToastMessage(mainFragment.getActivity(), R.string.message_check_password_empty);
                } else {
                    boolean isValid = mainFragment.getMainModel().getOpenApiService().checkPassword(mainFragment.getActivity(),
                            OpenAPIService.ApiCode.check_login_password, password);
                    if (isValid) {
                        String url = "javascript:showMailDetailsPopup('" + type + "','" + openLink + "','" + password + "')";
                        showPopupWebView(url);
                    } else {
                        PopupUtil.showDialog(mainFragment.getActivity(), R.string.message_check_login_password_invalid);
                        hideWebView(mainFragment);
                    }
                    inputBox.setText(null);
                }
            }
        });
        builder.setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputBox.setText(null);
                hideWebView(mainFragment);
            }
        });
        mainFragment.setPasswordDialogForMail(builder.create());
        mainFragment.getPasswordDialogForMail().show();
    }

    private static void hideWebView(@NonNull MainFragment mainFragment) {
        if (MainModel.getInstance().isTablet()) {
            // loadingView.setVisibility(View.VISIBLE);//빈화면 이미지-테블릿에서는 웹뷰가 항상
            // 보이기 때문에
            mainFragment.getCustomWebViewContainer().setVisibility(View.GONE);
            return; // 테블릿이면 웹뷰 숨김 없음!
        }
        if (mainFragment.getMiddleMenuFlipper().getChildCount() == 0) {
            mainFragment.getDrawerMenu().closeDrawers();
        } else {
            if (mainFragment.getWebviewContainer().getVisibility() == View.VISIBLE) {
                mainFragment.getWebviewContainer().setAnimation(
                        AnimationUtils.loadAnimation(mainFragment.getActivity(), android.R.anim.slide_out_right));
            }
            mainFragment.getWebviewContainer().setVisibility(View.GONE);
            mainFragment.getCustomWebViewContainer().setVisibility(View.GONE);
        }
    }
}
