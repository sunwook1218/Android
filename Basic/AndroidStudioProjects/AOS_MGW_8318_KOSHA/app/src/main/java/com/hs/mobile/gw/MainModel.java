package com.hs.mobile.gw;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MenuListHelper.SpSquareType;
import com.hs.mobile.gw.MenuListHelper.WorkGroupType;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.config.HMGWTask;
import com.hs.mobile.gw.config.HMGWTask.ConfigDataSet;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.DocViewFragment;
import com.hs.mobile.gw.fragment.HomeFragment;
import com.hs.mobile.gw.fragment.HtmlViewFragment;
import com.hs.mobile.gw.fragment.LoadingFragment;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.fragment.PopupWebViewFragment;
import com.hs.mobile.gw.fragment.SquareContentsFragment;
import com.hs.mobile.gw.fragment.WebViewFragment;
import com.hs.mobile.gw.fragment.login.LoginFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.AddFavoriteAttach;
import com.hs.mobile.gw.openapi.square.AddFavoriteContents;
import com.hs.mobile.gw.openapi.square.DeleteAttachFile;
import com.hs.mobile.gw.openapi.square.DeleteContents;
import com.hs.mobile.gw.openapi.square.DownloadAttach;
import com.hs.mobile.gw.openapi.square.GroupInfo;
import com.hs.mobile.gw.openapi.square.ModifyContents;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.ContentsMemberListItemVO;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareContentsType;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.squareplus.SpDeleteContents;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Callback;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DownloadCallBack;
import com.hs.mobile.gw.util.ImageHelper;
import com.hs.mobile.gw.util.PixelUtils;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.RestApiProgress;
import com.hs.mobile.gw.util.RestApiProgress.IProgressListener;
import com.hs.mobile.gw.util.TitleStack;
import com.hs.mobile.gw.view.AttachFile.IAttachFileDeleteListener;
import com.hs.mobile.gw.view.CustomTextButton;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginEntry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;

public class MainModel {
    public static final int REQ_ORG_SELECT = 0x01;
    public static final int REQ_PHONE_TO_SELECT = 0x02;
    public static final int REQ_PHONE_CC_SELECT = 0x03;
    public static final int REQ_PHONE_BCC_SELECT = 0x04;
    public static final int REQ_CAMERA = 0x05;
    public static final int REQ_VIDEO = 0x06;
    public static final int REQ_ALBUM = 0x07;
    public static final int REQ_FILE = 0x08;
    public static final int REQ_SELECT_MEMBER = 0x09;
    public static final int REQ_SELECT_MEMBER_SINGLE = 0x0a;
    public static final int REQ_FROM_SQUARE_CONTENTS_DETAIL = 0x0b;
    public static final int REQ_SP_SELECT_MEMBER = 0x0c;
    public static final int REQ_FROM_SP_CONTENTS_DETAIL = 0x0d;
    public static final int REQ_THUMBNAIL_CAMERA = 0x0e;
    public static final int REQ_THUMBNAIL_ALBUM = 0x0f;
    public static final int REQ_SP_GROUP_FOLDER = 0x10;

    protected static final int UPLOAD_ITEM_CAMERA = 0;
    protected static final int UPLOAD_ITEM_VIDEO = 1;
    protected static final int UPLOAD_ITEM_ALBUM = 2;
    protected static final int UPLOAD_ITEM_FILE = 3;
    protected static final int UPLOAD_ITEM_TOPIC = 4;
    protected static final int UPLOAD_ITEM_COMMAND = 5;
    public static final String ARG_KEY_SQUARE_ID = "square_id";
    public static final String ARG_KEY_OPEN_SQUARE = "square_open";
    public static final String ARG_KEY_SQUARE_ACTION = "square_action";
    public static final String ARG_KEY_SQUARE_ITEM = "square_contents_item";
    public static final String ARG_KEY_GROUP_STATUS = "group_status";
    public static final String ARG_KEY_MEMBER_SELECT_OPTION = "arg_key_member_select_option";
    public static final int SQUARE_ACTION_MODIFY = 1;
    public static final String ARG_KEY_SP_SQUARE_STATUS = "sp_square_status";
    public static final String ARG_KEY_SP_CONTENTS_ID = "sp_contents_id";
    public static final String ARG_KEY_SP_CONTENTS_ITEM = "sp_contents_item";
    public static final String ARG_KEY_SP_IS_SUB_CONTENTS = "sp_is_sub_contents";
    public static final String ARG_KEY_SP_IS_POPULAR_TAG = "sp_is_popular_tag";
    public static final String ARG_KEY_ORIGINAL_PARENT_CONTENTS_ID = "arg_key_original_parent_contents_id";
    public static final String ARG_KEY_SHOW_KEYBOARD = "arg_key_show_keyboard";
    public static final String ARG_KEY_SP_PUSH = "arg_key_sp_push";
    // private HMGWPlugin mPlugin;
    private boolean m_bTablet;

    public interface ISquarePushListener {
        void onPushReceive(SquarePushVO vo);
    }

    private AQuery m_aq;
    public ConfigDataSet dataSet;
    public HMGWTask task;

    public enum Views {
        // LOADING, LOGIN, MAIN
        LOADING, LOGIN, MAIN, HOME    // tkofs
    }

    // 조직도 탭바
    public static int m_nLastSelectedTabIndex; // memory last selected tap index
    // to

    public int getLastSelectedTabIndex() {
        return m_nLastSelectedTabIndex;
    }

    public void setLastSelectedTabIndex(int lastSelectedTabIndex) {
        m_nLastSelectedTabIndex = lastSelectedTabIndex;
    }

    // maintain the selection state of
    // tab
    public boolean m_bTabInitiation; // to avoid call java script function

    public boolean isTabInitiation() {
        return m_bTabInitiation;
    }

    public void setTabInitiation(boolean isTabInitiation) {
        m_bTabInitiation = isTabInitiation;
    }

    int nFindBackButtonCount = 0;

    public enum SquareAction {
        MODIFY, ADD
    }

    public interface IChangeContentsListener {
        public enum ChangeType {
            ADD, MODIFY, DELETE
        }

        void onChange(ChangeType t, MainContentsListItemVO item);
    }

    public interface IChangeSpContentsListener {
        public enum ChangeType {
            ADD, MODIFY, DELETE
        }

        void onChange(ChangeType t, SpContentsVO item);
    }

    public interface IContentsDeleteListener {

        void onDeleteComplete(MainContentsListItemVO item);

    }

    public interface ISpContentsDeleteListener {

        void onDeleteComplete(SpContentsVO item);

    }

    public TitleStack middleNavibarTitleStack = new TitleStack();

    public TitleStack getMiddleNavibarTitleStack() {
        return middleNavibarTitleStack;
    }

    public boolean isApprovalArchiveMenu;

    public boolean isApprovalArchiveMenu() {
        return isApprovalArchiveMenu;
    }

    public void setApprovalArchiveMenu(boolean isApprovalArchiveMenu) {
        this.isApprovalArchiveMenu = isApprovalArchiveMenu;
    }

    public boolean m_bListViewLoaded;

    public boolean isListViewLoaded() {
        return m_bListViewLoaded;
    }

    public void setListViewLoaded(boolean isListViewLoaded) {
        this.m_bListViewLoaded = isListViewLoaded;
    }

    public boolean m_bWasProcessKilled;

    public boolean isWasProcessKilled() {
        return m_bWasProcessKilled;
    }

    public void setWasProcessKilled(boolean wasProcessKilled) {
        this.m_bWasProcessKilled = wasProcessKilled;
    }

    public int m_nWebViewLoadedCnt;

    public int getWebViewLoadedCnt() {
        return m_nWebViewLoadedCnt;
    }

    public void setWebViewLoadedCnt(int webViewLoadedCnt) {
        this.m_nWebViewLoadedCnt = webViewLoadedCnt;
    }

    public OpenAPIService openApiService;

    public OpenAPIService getOpenApiService() {
        return openApiService;
    }

    public void setOpenApiService(OpenAPIService openApiService) {
        this.openApiService = openApiService;
    }

    public boolean m_bFirstLoading;

    public boolean isFirstLoading() {
        return m_bFirstLoading;
    }

    public void setFirstLoading(boolean isFirstLoading) {
        this.m_bFirstLoading = isFirstLoading;
    }

    public boolean m_bWebViewLoaded;

    public boolean isWebViewLoaded() {
        return m_bWebViewLoaded;
    }

    public void setWebViewLoaded(boolean isWebViewLoaded) {
        this.m_bWebViewLoaded = isWebViewLoaded;
    }

    private boolean m_bUserActionOccurred;

    public boolean isUserActionOccurred() {
        return m_bUserActionOccurred;
    }

    public void setUserActionOccurred(boolean bUserActionOccurred) {
        m_bUserActionOccurred = bUserActionOccurred;
    }

    private boolean m_bPopupMode;

    public void setPopupMode(boolean b) {
        m_bPopupMode = b;
    }

    public boolean isPopupMode() {
        return m_bPopupMode;
    }

    public enum ResultType {
        FILE_PATH, THUMBNAIL_PATH;
    }

    public interface IActivityResultHandlerListener {

        void onLoadCompleteMedia(ResultType filePath, String strRet);

    }

    private static volatile MainModel instance = null;

    public static MainModel getInstance() {
        synchronized (MainModel.class) {
            if (instance == null) {
                instance = new MainModel();
            }
        }
        return instance;
    }


    public MainModel() {
        // mPlugin = new HMGWPlugin();
        // HMGWPlugin.addListener(this);
    }

    public void setIsTablet(Resources res) { /*
     * < 빌드옵션 >
     *
     * 1) phone 전용 2) tablet 전용 3)
     * phone/tablet 공용 (Default)
     */
        int buildOption = 3;

        switch (buildOption) {
            case 1: {
                m_bTablet = false;
                break;
            }
            case 2: {
                m_bTablet = true;
                break;
            }
            case 3:
            default: {
                boolean xlarge = ((res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
                boolean large = ((res.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
                Debug.trace("SDK_INT?" + android.os.Build.VERSION.SDK_INT + "/ is XLarge?" + xlarge + "/ is Large?" + large);
                m_bTablet = xlarge || large;
//			m_bTablet = xlarge;
                break;
            }
        }
        if (m_bTablet) {
            HMGWServiceHelper.devicetype = 1;
        } else {
            HMGWServiceHelper.devicetype = 0;
        }

        /*
         * DisplayMetrics metrics = new DisplayMetrics();
         * getWindowManager().getDefaultDisplay().getMetrics(metrics);
         * Debug.trace("Device DENSITY:"+metrics.densityDpi);
         */
    }

    public interface IMenuItem {

    }

    public boolean isTablet() {
        return m_bTablet;
    }

    public interface IMenuSelectedListener {
        void onMenuSelected(IMenuItem menuItem);
    }

    private List<IMenuSelectedListener> mMenuSelectedListener = new ArrayList<IMenuSelectedListener>();

    private IMenuItem mSelectedMenu;

    private SubActivityType m_currentSubActivityType;
    private boolean m_bCallByMenu;
    private ArrayList<IChangeContentsListener> m_modifyListeners = new ArrayList<MainModel.IChangeContentsListener>();
    private ArrayList<IChangeSpContentsListener> m_spModifyListeners = new ArrayList<MainModel.IChangeSpContentsListener>();
    private Date m_defaultDate;
    private boolean m_bLoadingProgressShow = true;
    private CordovaInterfaceImpl m_cordovaInterface;
    private CordovaInterface m_popupCordovaInterface;
    private CordovaPreferences m_cordovaPreferences;

    private SearchWebViewClient m_searchWebViewClient;
    private ArrayList<PluginEntry> m_pluginEntries;
    private WebViewFragment m_webViewFragment;
    private PopupWebViewFragment m_popupWebviewFragment;
    private MainFragment m_mainFragment;
    private DocViewFragment m_docViewFragment;
    private HtmlViewFragment m_htmlViewFragment;
    private String m_docData;
    private FragmentManager m_supportFragmentManager;
    public SharedPreferences pref;
    public Editor prefEdit;
    private List<ISquarePushListener> m_squarePushListener = new ArrayList<ISquarePushListener>();
    private WorkGroupType m_currentSquare;
    private SpSquareType m_currentSpSquare;
    private boolean m_bCurrentSquare;
    private String m_currentSquareId;
    private String m_strUserId;

    public SubActivityType getCurrentSubActivityType() {
        return m_currentSubActivityType;
    }

    //
    // public HMGWPlugin getPlugin() {
    // return mPlugin;
    // }

    public void addMenuSelectedListener(IMenuSelectedListener listener) {
        if (!mMenuSelectedListener.contains(listener)) {
            mMenuSelectedListener.add(listener);
        }
    }

    public void removeMenuSelectedListener(IMenuSelectedListener listener) {
        if (mMenuSelectedListener.contains(listener)) {
            mMenuSelectedListener.remove(listener);
        }
    }

    public void selectMenu(IMenuItem m) {
        mSelectedMenu = m;
        if (mMenuSelectedListener.size() > 0) {
            for (IMenuSelectedListener listener : mMenuSelectedListener) {
                listener.onMenuSelected(mSelectedMenu);
            }
        }
    }

    // getNavibarVisible 툴바 숨기기, 보기 20.06.23 tkofs
    public boolean navibarVisible;

    public boolean getNavibarVisible() {
        return navibarVisible;
    }

    public void setNavibarVisible(boolean state) {
        navibarVisible = state;
    }

    // public void createMenuHelper(MainActivity mainActivity, LinearLayout
    // menuContainer, JSONArray mgw_menu) {
    // menuListHelper = new MenuListHelper(mainActivity, menuContainer,
    // HMGWServiceHelper.mgw_menu);
    // menuListHelper.createMenuList();
    // }
    //
    // public MenuListHelper getMenuHelper() {
    // return menuListHelper;
    // }
    //
    public void showSubActivity(Fragment f, SubActivityType sa, Bundle bd) {
        m_currentSubActivityType = sa;
        Intent intent = new Intent(f.getActivity(), SubActivity.class);
        intent.putExtra(SubActivity.INTENT_KEY_FRAGMENT_NAME, sa.name());
        int reqCode = SubActivity.REQ_CLOSE;

        // tkofs
        ViewModel.backKey_Lock = false;
        switch (sa) {
            case MAIL_WRITE:
                ViewModel.backKey_Lock = true; // tkofs
                break;
            case ORG_CHART:
                reqCode = REQ_ORG_SELECT;
                break;
            case MEMBER_SELECT:
                if (bd.containsKey(MainModel.ARG_KEY_MEMBER_SELECT_OPTION)) {
                    reqCode = bd.getInt(MainModel.ARG_KEY_MEMBER_SELECT_OPTION);
                } else {
                    reqCode = REQ_SELECT_MEMBER;
                }
                break;
            case SQUARE_CONTENTS_DETAIL:
                reqCode = MainModel.REQ_FROM_SQUARE_CONTENTS_DETAIL;
                break;
            case SP_MEMBER_SELECT:
                if (bd.containsKey(MainModel.ARG_KEY_MEMBER_SELECT_OPTION)) {
                    reqCode = bd.getInt(MainModel.ARG_KEY_MEMBER_SELECT_OPTION);
                } else {
                    reqCode = REQ_SP_SELECT_MEMBER;
                }
                break;
            default:
                break;
        }
        if (bd != null) {
            intent.putExtra(SubActivity.INTENT_KEY_VALUE, bd);
        }
        f.startActivityForResult(intent, reqCode);
    }

    //
    public void showSubActivity(Activity a, SubActivityType sa, Bundle bd) {
        // tkofs | 특수페이지(기본 페이지위에 겹쳐 있는 페이지들) 서브 헤더 날림
        switch (sa) {
            case MAIL_WRITE:
            case MAKE_NEW_WORK_GROUP:
            case SP_CREATE:
                MainModel.getInstance().setNavibarVisible(true);
                break;
            default:
        }

        MainFragment.getController().closeMenu();
        m_currentSubActivityType = sa;
        Intent intent = new Intent(a, SubActivity.class);
        intent.putExtra(SubActivity.INTENT_KEY_FRAGMENT_NAME, sa.name());
        int reqCode = SubActivity.REQ_CLOSE;
        switch (sa) {
            case MAIL_WRITE:
                break;
            case ORG_CHART:
                reqCode = REQ_ORG_SELECT;
                break;
            case MEMBER_SELECT:
                if (bd.containsKey(MainModel.ARG_KEY_MEMBER_SELECT_OPTION)) {
                    reqCode = bd.getInt(MainModel.ARG_KEY_MEMBER_SELECT_OPTION);
                } else {
                    reqCode = REQ_SELECT_MEMBER;
                }
                break;
            case DOC_VIEWER:
                break;
            case HTML_VIEWER:
                break;
            case SQUARE_CONTENTS_DETAIL:
                reqCode = MainModel.REQ_FROM_SQUARE_CONTENTS_DETAIL;
                break;
            case SP_MEMBER_SELECT:
                if (bd.containsKey(MainModel.ARG_KEY_MEMBER_SELECT_OPTION)) {
                    reqCode = bd.getInt(MainModel.ARG_KEY_MEMBER_SELECT_OPTION);
                } else {
                    reqCode = REQ_SP_SELECT_MEMBER;
                }
                break;
            case SP_CONTENTS_DETAIL:
                reqCode = MainModel.REQ_FROM_SP_CONTENTS_DETAIL;
                break;
            case SP_GROUP_FOLDER:
                reqCode = MainModel.REQ_SP_GROUP_FOLDER;
                break;
            default:
                break;
        }
        if (bd != null) {
            intent.putExtra(SubActivity.INTENT_KEY_VALUE, bd);
        }
        a.startActivityForResult(intent, reqCode);
    }

    //
    public void setCurrentSubActivity(SubActivityType sa) {
        m_currentSubActivityType = sa;
    }


    public void clearStack() {
        /*
         * Here we are clearing back stack fragment entries
         */
        int backStackEntry = getFragmentManager().getBackStackEntryCount();
        Debug.trace("backStackEntry = ", backStackEntry);
        if (backStackEntry > 0) {
//			for (int i = 0; i < backStackEntry; i++) {
            getFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//			}
        }
        Debug.trace("backStackEntry = ", getFragmentManager().getBackStackEntryCount());
    }

    public void goToGroup(FragmentActivity a, MyWorkGroupMenuListItemVO workGroupItem) {
        Bundle build = new BundleUtils.Builder().add(SquareContentsFragment.ARG_KEY_WORKGROUP_ITEM, workGroupItem).build();
        if (isTablet()) {
            clearStack();
            MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
            MainFragment.mainRightContent.findViewById(R.id.webviewContainer).setVisibility(View.GONE);
            showFragmentToTarget(a, new SquareContentsFragment(), build, R.id.ID_LAY_L_FRAGMENT_LAYOUT);
        } else {
            showSubActivity(a, SubActivityType.SQUARE_CONTENTS, build);
        }
    }

    public void goToGroup(FragmentActivity a, SpSquareVO squareItem) {
        // 객체를 넘기는 것이 아니라 아이디와 제목만 넘긴다.
        ArrayList<String> strList = new ArrayList<String>();
        strList.add(squareItem.getSquareId());
        strList.add(squareItem.getTitle());
        strList.add(squareItem.getSecurityFlag());

        Bundle build = new BundleUtils.Builder().add(SpContentsFragment.ARG_KEY_WORKGROUP_ITEM, strList).build();
        if (isTablet()) {
            clearStack();
            MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
            MainFragment.mainRightContent.findViewById(R.id.webviewContainer).setVisibility(View.GONE);
            showFragmentToTarget(a, new SpContentsFragment(), build, R.id.ID_LAY_L_FRAGMENT_LAYOUT);
        } else {
            showSubActivity(a, SubActivityType.SP_CONTENTS, build);
        }
    }

    public boolean isGroupEnd(long endDate) {
        return System.currentTimeMillis() > endDate;
    }

    public void showFragmentToTarget(FragmentActivity a, Fragment f, Bundle build, int id) {
        f.setArguments(build);
        a.getSupportFragmentManager().beginTransaction().replace(id, f).commitAllowingStateLoss();
    }

    public void showFragmentToTarget(FragmentActivity a, Fragment f, Bundle build, int id, boolean bBackStack, String tagName) {
        f.setArguments(build);
        FragmentTransaction ft = a.getSupportFragmentManager().beginTransaction();
        if (bBackStack)
            ft.addToBackStack(tagName);
        ft.replace(id, f);
        ft.commitAllowingStateLoss();
    }

    public void addFavoriteContents(Context c, Callback listener, String strSquareId, String strContentsId, FavoriteContentsType ct,
                                    boolean bFlag) {
        if (ct.equals(FavoriteContentsType.SINGLE_FILE)) {
            new ApiLoader(new AddFavoriteAttach(), c, listener, strSquareId, strContentsId, ct.getCode(), bFlag ? "true" : "false")
                    .execute();
        } else {
            new ApiLoader(new AddFavoriteContents(), c, listener, strSquareId, strContentsId, ct.getCode(), bFlag ? "true" : "false")
                    .execute();
        }
    }

    public void showUploadDialog(final Fragment fragment, final String strSquareId, boolean isOnlyMedia) {
        String[] items = null;
        if (isOnlyMedia) {
            String[] strs = new String[]{fragment.getString(R.string.upload_media_camera),
                    fragment.getString(R.string.upload_media_record_video), fragment.getString(R.string.upload_media_album),
                    fragment.getString(R.string.upload_media_file)};
            items = strs;
        } else {
            String[] strs = new String[]{fragment.getString(R.string.upload_items_camera),
                    fragment.getString(R.string.upload_items_record_video), fragment.getString(R.string.upload_items_album),
                    fragment.getString(R.string.upload_items_file), fragment.getString(R.string.upload_items_topic),
                    fragment.getString(R.string.upload_items_order)};
            items = strs;
        }
        new AlertDialog.Builder(fragment.getActivity()).setItems(items, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri fileUri;
                switch (which) {
                    case UPLOAD_ITEM_CAMERA: {
                        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        fileUri = ImageHelper.getOutputMediaFileUri(fragment.getActivity(), ImageHelper.MEDIA_TYPE_IMAGE);
                        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        fragment.startActivityForResult(intentCamera, REQ_CAMERA);
                        dialog.dismiss();
                    }
                    break;
                    case UPLOAD_ITEM_VIDEO: {
                        Intent intentCamera = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        fileUri = ImageHelper.getOutputMediaFileUri(fragment.getActivity(), ImageHelper.MEDIA_TYPE_VIDEO);
                        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        fragment.startActivityForResult(intentCamera, REQ_VIDEO);
                        dialog.dismiss();
                    }
                    break;
                    case UPLOAD_ITEM_ALBUM: {
                        // SEOJAEHWA: [MGW-2146] 권한체크(READ_EXTERNAL_STORAGE)
                        // FIXME: 2019-02-21 SEOJAEHWA
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (hasExternalStoragePermission(fragment.getContext())) {
                                pickAlbumImage(fragment, REQ_ALBUM);
                                dialog.dismiss();
                            } else {
                                requestExternalStoragePermission(fragment, SpContentWriteFragment.RC_EXT_STORAGE_PERM_GET_ALBUM_IMAGE);
                            }
                        } else {
                            pickAlbumImage(fragment, REQ_ALBUM);
                            dialog.dismiss();
                        }
                        dialog.dismiss();
                    }
                    break;
                    case UPLOAD_ITEM_FILE: {
                        // SEOJAEHWA: 권한체크(READ_EXTERNAL_STORAGE)
                        // FIXME: 2019-02-21 SEOJAEHWA
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (hasExternalStoragePermission(fragment.getContext())) {
                                chooseFile(fragment);
                            } else {
                                requestExternalStoragePermission(fragment, CommonFragment.RC_EXT_STORAGE_PERM_GET_CONTENT);
                            }
                        } else {
                            chooseFile(fragment);
                        }
                        dialog.dismiss();
                    }
                    break;
                    case UPLOAD_ITEM_TOPIC:
                        showSubActivity(fragment.getActivity(), SubActivityType.SQUARE_ADD_TOPIC,
                                new BundleUtils.Builder().add(ARG_KEY_SQUARE_ID, strSquareId).build());
                        break;
                    case UPLOAD_ITEM_COMMAND:
                        showSubActivity(fragment.getActivity(), SubActivityType.SQUARE_ADD_COMMAND,
                                new BundleUtils.Builder().add(ARG_KEY_SQUARE_ID, strSquareId).build());
                        break;
                }
            }
        }).show();
    }

    public void showUploadCameraDialog(final Fragment fragment, final String strSquareId) {
        String[] items = new String[]{fragment.getString(R.string.upload_media_camera),
                // fragment.getString(R.string.upload_media_record_video),
                fragment.getString(R.string.upload_media_album)};
        new AlertDialog.Builder(fragment.getActivity()).setItems(items, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri fileUri;

                if (which == 1)
                    which = UPLOAD_ITEM_ALBUM;

                switch (which) {
                    case UPLOAD_ITEM_CAMERA: {
                        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        fileUri = ImageHelper.getOutputMediaFileUri(fragment.getActivity(), ImageHelper.MEDIA_TYPE_IMAGE);
                        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                        fragment.startActivityForResult(intentCamera, MainModel.REQ_THUMBNAIL_CAMERA);
                        dialog.dismiss();
                    }
                    break;
                    // case UPLOAD_ITEM_VIDEO: {
                    // Intent intentCamera = new Intent(
                    // android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
                    // fileUri = ImageHelper.getOutputMediaFileUri(
                    // fragment.getActivity(),
                    // ImageHelper.MEDIA_TYPE_VIDEO);
                    // intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,
                    // fileUri);
                    // fragment.startActivityForResult(intentCamera,
                    // REQ_VIDEO);
                    // dialog.dismiss();
                    // }
                    // break;
                    case UPLOAD_ITEM_ALBUM: {
                        // FIXME: 2019-02-21 SEOJAEHWA
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (hasExternalStoragePermission(fragment.getContext())) {
                                pickAlbumImage(fragment, REQ_THUMBNAIL_ALBUM);
                                dialog.dismiss();
                            } else {
                                requestExternalStoragePermission(fragment, SpContentWriteFragment.RC_EXT_STORAGE_PERM_GET_ALBUM_IMAGE);
                            }
                        } else {
                            pickAlbumImage(fragment, REQ_THUMBNAIL_ALBUM);
                            dialog.dismiss();
                        }
                        dialog.dismiss();
                    }
                    break;
                }
            }
        }).show();
    }

    /**
     * SEOJAEHWA: READ_EXTERNAL_STORAGE 권한획득 여부 체크
     *
     * @param context Context
     * @return If has it, return true, It not, return false.
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean hasReadStoragePermission(@Nullable Context context) {
        if (context == null) {
            Debug.trace("Context is null. Nothing to do.");
            return false;
        }
        return EasyPermissions.hasPermissions(context,
                Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    /**
     * SEOJAEHWA: READ_EXTERNAL_STORAGE 권한 요청
     *
     * @param fragment Caller Fragment
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestReadStoragePermission(Fragment fragment, int requestCode) {
        fragment.requestPermissions(
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode);
    }

    /**
     * SEOJAEHWA: READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE 권한획득 여부 체크
     *
     * @param context Context
     * @return If has it, return true, It not, return false.
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean hasExternalStoragePermission(@Nullable Context context) {
        if (context == null) {
            Debug.trace("Context is null. Nothing to do.");
            return false;
        }
        return EasyPermissions.hasPermissions(context,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * SEOJAEHWA: READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE 권한 요청
     *
     * @param fragment Caller Fragment
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestExternalStoragePermission(Fragment fragment, int requestCode) {
        fragment.requestPermissions(
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
    }

    /**
     * SEOJAEHWA: 권한 획득 결과 체크
     *
     * @param fragment     Caller Fragment
     * @param requestCode  RequestCode
     * @param permissions  Permissions
     * @param grantResults GrantResults
     */
    public void onRequestPermissionsResult(Fragment fragment,
                                           int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            // 외부저장소 읽기, 쓰기 권한을 모두 얻어오는 방법을 우선으로 하므로 특이 경우를 제외하면 해당 case 처리는 하지 않음
			/*case CommonFragment.RC_READ_STORAGE_PERM_GET_CONTENT:
				for (int i = 0; i < permissions.length; i++) {
					if (TextUtils.equals(permissions[i], Manifest.permission.READ_EXTERNAL_STORAGE)
							&& grantResults[i] == PackageManager.PERMISSION_GRANTED) {
						chooseFile(fragment);
					}
				}
				break;*/
            // 외부저장소 읽기, 쓰기 권한을 모두 얻어오는 방법을 우선으로 하므로 특이 경우를 제외하면 해당 case 처리는 하지 않음
			/*case SpContentWriteFragment.RC_READ_STORAGE_PERM_GET_ALBUM_IMAGE:
				for (int i = 0; i < permissions.length; i++) {
					if (TextUtils.equals(permissions[i], Manifest.permission.READ_EXTERNAL_STORAGE)
							&& grantResults[i] == PackageManager.PERMISSION_GRANTED) {
						pickAlbumImage(fragment, REQ_THUMBNAIL_ALBUM);
					}
				}
				break;*/
            case CommonFragment.RC_EXT_STORAGE_PERM_GET_CONTENT:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        return;
                    }
                }
                chooseFile(fragment);
                break;
            case SpContentWriteFragment.RC_EXT_STORAGE_PERM_GET_ALBUM_IMAGE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        return;
                    }
                }
                pickAlbumImage(fragment, REQ_THUMBNAIL_ALBUM);
                break;
        }
    }

    private void pickAlbumImage(Fragment fragment, int requestCode) {
        Intent intentAlbum = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fragment.startActivityForResult(intentAlbum, requestCode);
    }

    /**
     * SEOJAEHWA: 파일선택 인텐트 호출
     *
     * @param fragment Caller Fragment
     */
    private void chooseFile(@Nullable Fragment fragment) {
        if (fragment == null) {
            Debug.trace("Fragment is null. Nothing to do.");
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        fragment.startActivityForResult(intent, REQ_FILE);
    }


    public enum MemberType {
        USER("0"), DEPARTMENT("1");
        private String m_type;

        MemberType(String s) {
            m_type = s;
        }

        public String getType() {
            return m_type;
        }

        public static MemberType valueOfCode(String s) {
            for (MemberType t : values()) {
                if (TextUtils.equals(s, t.getType())) {
                    return t;
                }
            }
            return null;
        }
    }

    public static class Member {
        public String name;
        public String id;
        public MemberType memberType = MemberType.USER;

        public Member(String strId, String strName, MemberType t) {
            id = strId;
            name = strName;
            memberType = t;
        }

        public String toString() {
            StringBuilder bd = new StringBuilder();
            bd.append(id);
            bd.append(',');
            bd.append(name);
            bd.append(',');
            bd.append(memberType.getType());
            bd.append(';');
            return bd.toString();
        }
    }

    public static class SpMember {
        public String name;
        public String id;
        public MemberType memberType = MemberType.USER;
        public MemberRights memberRights = MemberRights.NORMAL_USER;

        public SpMember(String strId, String strName, MemberType t, MemberRights r) {
            id = strId;
            name = strName;
            memberType = t;
        }

        public String toString() {
            StringBuilder bd = new StringBuilder();
            bd.append(id);
            bd.append(',');
            bd.append(name);
            bd.append(',');
            bd.append(memberType.getType());
            bd.append(';');
            bd.append(memberRights.getCode());
            bd.append(';');
            return bd.toString();
        }
    }

    public void activityResultMediaHandler(Activity activity, int requestCode, int resultCode, Intent data,
                                           IActivityResultHandlerListener listener) {
        String strRet = "";
        boolean isThumbnail = false;
        switch (requestCode) {
            case MainModel.REQ_THUMBNAIL_CAMERA:
                isThumbnail = true;
            case MainModel.REQ_CAMERA: {
                if (data == null) {
                    Uri uri = null;
                    if (ImageHelper.getLastCaptureImageUri(activity) != null)
                        uri = ImageHelper.getLastCaptureImageUri(activity);
                    if (uri != null) strRet = new File(uri.getPath()).getAbsolutePath();
                } else {
                    try {
                        if (new File(new URI(data.getData().toString())).exists()) {
                            strRet = new File(new URI(data.getData().toString())).getAbsolutePath();
                        } else {
                            strRet = getPath(activity, data.getData());
                        }
                    } catch (URISyntaxException e) {
                        Debug.trace(e.getMessage());
                    }
                }
            }
            break;
            case MainModel.REQ_VIDEO: {
                // SEOJAEHWA : 기존 코드는 보호하고 'N' 이상 버전에서는 file path 로 세팅
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Uri uri = null;
                    if (ImageHelper.getLastCaptureImageUri(activity) != null)
                        uri = ImageHelper.getLastCaptureImageUri(activity);
                    if (uri != null) strRet = new File(uri.getPath()).getAbsolutePath();
                } else {
                    if (data == null) {
                        Uri uri = null;
                        if (ImageHelper.getLastCaptureImageUri(activity) != null)
                            uri = ImageHelper.getLastCaptureImageUri(activity);
                        if (uri != null) strRet = new File(uri.getPath()).getAbsolutePath();
                    } else {
                        try {
                            if (new File(new URI(data.getData().toString())).exists()) {
                                strRet = new File(new URI(data.getData().toString())).getAbsolutePath();
                            } else {
                                strRet = getPath(activity, data.getData());
                            }
                        } catch (URISyntaxException e) {
                            Debug.trace(e.getMessage());
                        }
                    }
                }
            }
            break;
            case MainModel.REQ_THUMBNAIL_ALBUM:
                isThumbnail = true;
            case MainModel.REQ_ALBUM: {
                strRet = getPath(activity, data.getData());
            }
            break;
            case MainModel.REQ_FILE: {
                strRet = getPath(activity, data.getData());
            }
            break;
        }
        if (TextUtils.isEmpty(strRet)) {
            PopupUtil.showToastMessage(activity, R.string.square_can_not_read_file);
        } else {

            if (isThumbnail) {
                //이미지 회전시켜 다시 저장
                Bitmap bm = ImageHelper.autoRotate(strRet, null);
                ImageHelper.SaveBitmapToFileCache(bm, strRet);

                listener.onLoadCompleteMedia(ResultType.THUMBNAIL_PATH, strRet);
            } else {
                listener.onLoadCompleteMedia(ResultType.FILE_PATH, strRet);
            }
        }
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    public static String getPath(final Context context, final Uri uri) {
        // SEOJAEHWA : remove lint error\
        Debug.trace("uri = " + uri);
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                Debug.trace(uri);
                final String id = DocumentsContract.getDocumentId(uri);
                Debug.trace(id);
                if (!TextUtils.isEmpty(id)) {
                    if (id.startsWith("raw:")) {
                        return id.replaceFirst("raw:", "");
                    }

                    try {
                        final Uri contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                        return getDataColumn(context, contentUri, null, null);
                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if ("com.estrongs.files".equalsIgnoreCase(uri.getHost())) {
                return uri.getPath();
            }
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        Debug.trace(uri.getAuthority());
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public String readableFileSize(long size) {
        if (size <= 0)
            return "0";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public String checkNullString(String string) {
        if (TextUtils.equals(string, "null")) {
            return null;
        } else {
            return string;
        }
    }

    public void setCallByMenu(boolean b) {
        m_bCallByMenu = b;
    }

    public boolean isCallByMenu() {
        return m_bCallByMenu;
    }

    public void deleteContents(final Activity activity, final MainContentsListItemVO item, final IContentsDeleteListener listener) {
        PopupUtil.showOkCancelDialog(activity, R.string.square_message_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new ApiLoader(new DeleteContents(), activity, new SquareDefaultCallback() {
                    public void onResponse(String strRet) {
                        super.onResponse(strRet);
                        listener.onDeleteComplete(item);
                    }

                    ;
                }, item.squareId, item.contentsId).execute();
            }
        });
    }

    public void deleteContents(final Activity activity, final SpContentsVO item, final ISpContentsDeleteListener listener) {
        PopupUtil.showOkCancelDialog(activity, R.string.square_message_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SpContentsCallBack spContentsCallBack = new SpContentsCallBack(activity, SpContentsVO.class) {
                    @Override
                    public void callback(String url, JSONObject json, AjaxStatus status) {
                        super.callback(url, json, status);
                        Debug.trace(getVO().toString());
                        Debug.trace(this.item);
                        listener.onDeleteComplete(item);
                    }
                };

                { // 컨텐츠를 삭제한다.
                    HashMap<String, String> params = new HashMap<>();
                    params.put("squareId", item.getSquareId());
                    params.put("contentsId", item.getContentsId());
                    new ApiLoaderEx<>(new SpDeleteContents(activity), activity, spContentsCallBack, params).execute();
                }
            }
        });
    }

    public void modifyContents(final Activity activity, final MainContentsListItemVO item, final Callback listener) {
        switch (item.contentsType) {
            case COMMAND:
                showSubActivity(activity, SubActivityType.SQUARE_ADD_COMMAND,
                        new BundleUtils.Builder().add(ARG_KEY_SQUARE_ITEM, item).add(ARG_KEY_SQUARE_ACTION, SQUARE_ACTION_MODIFY).build());
                break;
            case FILE_UPLOAD:
                break;
            case GROUPINFO_SYSTEM_MESSAGE:
                break;
            case MESSAGE: {
                final LinearLayout l = new LinearLayout(activity);
                l.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                final EditText edText = new EditText(activity);
                edText.setGravity(Gravity.LEFT | Gravity.TOP);
                l.addView(edText);
                edText.setMinLines(3);
                edText.setText(item.body);
                final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                final int nMargine = (int) PixelUtils.getDip(activity.getResources(), 10);
                params.setMargins(nMargine, nMargine, nMargine, nMargine);
                edText.setLayoutParams(params);
                new AlertDialog.Builder(activity).setTitle("메시지 수정").setView(l).setPositiveButton("수정", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String body = edText.getText().toString().trim();
                        if (TextUtils.isEmpty(body)) {
                            return;
                        }
                        // "contentsId", "contentsType", "body", "title",
                        // "orgAttachIdList", "dueDate", "member"
                        new ApiLoader(new ModifyContents(), activity, listener, item.contentsId, item.contentsType.getCode(), body).execute();
                    }
                }).setNegativeButton("취소", null).show();
            }
            break;
            case OPINION: {
                final LinearLayout l = new LinearLayout(activity);
                l.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                final EditText edText = new EditText(activity);
                edText.setGravity(Gravity.LEFT | Gravity.TOP);
                l.addView(edText);
                edText.setMinLines(3);
                edText.setText(item.body);
                final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                final int nMargine = (int) PixelUtils.getDip(activity.getResources(), 10);
                params.setMargins(nMargine, nMargine, nMargine, nMargine);
                edText.setLayoutParams(params);
                new AlertDialog.Builder(activity).setTitle("의견 수정").setView(l).setPositiveButton("수정", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String body = edText.getText().toString().trim();
                        if (TextUtils.isEmpty(body)) {
                            return;
                        }
                        // "contentsId", "contentsType", "body", "title",
                        // "orgAttachIdList", "dueDate", "member"
                        new ApiLoader(new ModifyContents(), activity, listener, item.contentsId, item.contentsType.getCode(), body).execute();

                    }
                }).setNegativeButton("취소", null).show();
            }
            break;
            case SYSTEM_MESSAGE:
                break;
            case TOPIC:
                showSubActivity(activity, SubActivityType.SQUARE_ADD_TOPIC,
                        new BundleUtils.Builder().add(ARG_KEY_SQUARE_ITEM, item).add(ARG_KEY_SQUARE_ACTION, SQUARE_ACTION_MODIFY).build());
                break;
            case USER_SYSTEM_INFO:
                break;
            default:
                break;
        }
    }

    // 갱신을 위한 메소드
    // 1. 콘텐츠 상세 화면에서 의견 추가시.
    public void addContentsChangeListener(IChangeContentsListener listener) {
        if (!m_modifyListeners.contains(listener)) {
            m_modifyListeners.add(listener);
        }
    }

    public void removeContentsChangeListener(IChangeContentsListener listener) {
        if (m_modifyListeners.contains(listener)) {
            m_modifyListeners.remove(listener);
        }
    }

    public void notifyChanged(MainContentsListItemVO item, IChangeContentsListener.ChangeType t) {
        for (IChangeContentsListener listener : m_modifyListeners) {
            listener.onChange(t, item);
        }
    }

    // 스퀘어 플러스용 리스너
    public void addContentsChangeListener(IChangeSpContentsListener listener) {
        if (!m_spModifyListeners.contains(listener)) {
            m_spModifyListeners.add(listener);
        }
    }

    public void removeContentsChangeListener(IChangeSpContentsListener listener) {
        if (m_spModifyListeners.contains(listener)) {
            m_spModifyListeners.remove(listener);
        }
    }

    public void notifyChanged(SpContentsVO item, IChangeSpContentsListener.ChangeType t) {
        for (IChangeSpContentsListener listener : m_spModifyListeners) {
            listener.onChange(t, item);
        }
    }

    public void notifyChanged(int pos, SpContentsVO item, IChangeSpContentsListener.ChangeType t) {
        if (m_spModifyListeners != null && pos < m_spModifyListeners.size())
            m_spModifyListeners.get(pos).onChange(t, item);
    }

    public void copyBodyText(final Activity activity, MainContentsListItemVO item) {
        ClipboardManager cm = (ClipboardManager) activity.getSystemService(Activity.CLIPBOARD_SERVICE);
        cm.addPrimaryClipChangedListener(new OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                PopupUtil.showToastMessage(activity, R.string.square_copy_complete);
            }
        });
        ClipData clip = cm.getPrimaryClip();
        ClipDescription description = new ClipDescription(null, new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN});
        String content = item.contentsType == SquareContentsType.FILE_UPLOAD ? item.title : item.body;
        Item clipItem = new Item(content);

        clip = new ClipData(description, clipItem);
        cm.setPrimaryClip(clip);
    }

    public Date getDefaultDate() {
        if (m_defaultDate == null) {
            m_defaultDate = new Date(0);
        }
        return m_defaultDate;
    }

    public SquareMemberVO convertContentsMemberToSquareMember(ContentsMemberListItemVO vo) {
        return new SquareMemberVO(vo.memberName, "0", vo.userId);
    }

    public void deleteAttachFile(final Activity activity, final AttachListItemVO attachListItem, final IAttachFileDeleteListener listener) {
        PopupUtil.showOkCancelDialog(activity, R.string.square_message_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new ApiLoader(new DeleteAttachFile(), activity, new SquareDefaultCallback() {
                    public void onResponse(String strRet) {
                        super.onResponse(strRet);
                        if (this.responseHead != null && responseHead.resultCode == SUCCESS) {
                            listener.onDeletedAttachFile(attachListItem);
                        }
                    }

                    ;
                }, attachListItem.attachId).execute();
            }
        });
    }

    // {"id" : "5000221", "name" : "박지숙", "type":"user", "email" :
    // "jspark@gmail.com", "recursive" : false}
    public String convertSquareMemberToJson(ArrayList<SquareMemberVO> selectedMemberData) {
        JSONArray jarr = new JSONArray();

        if (selectedMemberData != null) {
            for (SquareMemberVO item : selectedMemberData) {
                JSONObject json = new JSONObject();
                try {
                    json.putOpt("id", item.memberId);
                    json.putOpt("name", item.memberName);
                    json.putOpt("type", TextUtils.equals(item.memberType, "0") ? "user" : "dept");
                    json.putOpt("email", "");
                    json.putOpt("recursive", "false");
                } catch (JSONException e) {
                    Debug.trace(e.getMessage());
                }
                jarr.put(json);
            }
        }

        return jarr.toString();
    }

    public String convertSpSquareMemberToJson(List<SpSquareMemberVO> selectedMemberData) {
        JSONArray jarr = new JSONArray();

//		if (selectedMemberData != null) {
//			for (SpSquareMemberVO item : selectedMemberData) {
//				JSONObject json = new JSONObject();
//				try {
//					json.putOpt("id", item.getMemberId());
//					json.putOpt("name", item.getMemberName());
//					json.putOpt("type", TextUtils.equals(item.getMemberType(), "0") ? "user" : "dept");
//					json.putOpt("rights", TextUtils.equals(item.getMemberRights(), "0") ? "admin" : TextUtils.equals(item.getMemberRights(), "1") ? "member" : "observer");
//					json.putOpt("email", "");
//					json.putOpt("recursive", "false");
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				jarr.put(json);
//			}
//		}

        return jarr.toString();
    }

    public void authorizeInputLayout(Status status, LinearLayout inputLayout) {
        inputLayout.setVisibility(status == Status.IN_PROGRESS ? View.VISIBLE : View.GONE);
    }

    public void authorizeInputLayout(com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status status, LinearLayout inputLayout) {
        inputLayout.setVisibility(status == com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status.IN_PROGRESS ? View.VISIBLE : View.GONE);
    }

    private String fileExt(String url) {
        if (url.indexOf("?") > -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf("."));
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }

    public void downloadFile(final Activity a, String contentsId, String attachId, final String strTargetPath) {
        PopupUtil.showLoading(a);
        final Callback callBack = new Callback() {
            @Override
            public void onResponse(String result) {
                Debug.trace(result);
                File file = new File(strTargetPath);
                callActionView(file, a);
            }

            @Override
            public void onFailure(String err) {
                Debug.trace(err);
                PopupUtil.hideLoading(a);
            }
        };
        DownloadAttach api = new DownloadAttach();
        api.setCallBack(new DownloadCallBack(api, strTargetPath) {
            @Override
            public void onResponse(Response response) {
                super.onResponse(response);
                callBack.onResponse(response.message());
            }

            @Override
            public void onFailure(Request arg0, IOException arg1) {
                callBack.onResponse(arg1.getMessage());
            }
        });
        api.setRestApiProgress(new RestApiProgress(a, new IProgressListener() {

            @Override
            public void showProgress() {
                PopupUtil.showLoading(a);
            }

            @Override
            public void onProgress(int n) {

            }

            @Override
            public void hideProgress() {
                PopupUtil.hideLoading(a);
            }
        }));
        new ApiLoader(api, a, callBack, contentsId, attachId).execute();
    }

    public String getExternamDownloadDirectory(String strFileName, String strFileExt) {
        String strDownloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
                + File.separator + strFileName + "." + strFileExt;
        Debug.trace("getExternamDownloadDirectory", strDownloadPath);
        return strDownloadPath;
    }

    public void callActionView(File file, Activity a) {
        Intent newIntent = new Intent(Intent.ACTION_VIEW);
        String mime;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file.getAbsolutePath());
            mime = URLConnection.guessContentTypeFromStream(fis);
            if (mime == null) mime = URLConnection.guessContentTypeFromName(file.getAbsolutePath());

            Debug.trace(mime);
            // SEOJAEHWA : 기존 코드는 보호하고 'N' 이상 버전에서는 file path 로 세팅
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                final String authority = a.getPackageName() + ".fileprovider";
                final Uri uri = FileProvider.getUriForFile(a, authority, file);
                newIntent.setDataAndType(uri, mime);
                newIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                newIntent.setDataAndType(Uri.fromFile(file), mime);
            }
            a.startActivity(newIntent);

        } catch (FileNotFoundException e) {
            Debug.trace(e.getMessage());
            PopupUtil.showToastMessage(a, "No handler for this type of file.");
        } catch (IOException e) {
            Debug.trace(e.getMessage());
            PopupUtil.showToastMessage(a, "No handler for this type of file.");
        } catch (ActivityNotFoundException e) {
            Debug.trace(e.getMessage());
            PopupUtil.showToastMessage(a, "지원하는 뷰어가 설치되어있지 않습니다.");
        } catch (Exception e) {
            Debug.trace(e.getMessage());
            PopupUtil.showToastMessage(a, "알 수 없는 오류가 발생했습니다.");
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                Debug.trace(e.getMessage());
            }
        }
    }

    public boolean isLoadingProgressShow() {
        return m_bLoadingProgressShow;
    }

    public void setLoadingProgressShow(boolean b) {
        m_bLoadingProgressShow = b;
    }

    public int checkFileSize(ArrayList<String> filePathData) {
        if (filePathData == null) {
            return -1;
        } else {
            long nTotalFileSize = 0;
            for (int i = 0; i < filePathData.size(); ++i) {
                File file = new File(filePathData.get(i));
                if (file.exists()) {
                    nTotalFileSize += file.length();
                }
            }
            if (nTotalFileSize > Define.LIMIT_UPLOAD_FILE_SIZE) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public int checkFileSize(String strRet) {
        if (TextUtils.isEmpty(strRet)) {
            return -1;
        } else {
            File file = new File(strRet);
            if (file.exists()) {
                if (file.length() > Define.LIMIT_UPLOAD_FILE_SIZE) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        }
    }

    // 작업 요청자인지 판단
    public boolean isAdmin(String strAuthId, ArrayList<ContentsMemberListItemVO> contentsMemberList) {
        for (ContentsMemberListItemVO item : contentsMemberList) {
            if (TextUtils.equals(strAuthId, item.userId)) {
                return true;
            }
        }
        return false;
    }

    // 작업자인지 판단
    public boolean isWorker(ArrayList<ContentsMemberListItemVO> contentsMemberList) {
        for (ContentsMemberListItemVO item : contentsMemberList) {
            if (!TextUtils.equals(item.status, "0")) {
                return true;
            }
        }
        return false;
    }

    // 내가 작업요청자인지 판단
    public boolean isMyAdminWork(String strMyId, String strAdminId) {
        return TextUtils.equals(strAdminId, strMyId);
    }

    // 내 작업인지 판단
    public boolean isMyWork(String strMyId, ArrayList<ContentsMemberListItemVO> contentsMemberList) {
        if (!isWorker(contentsMemberList)) {
            return false;
        }
        for (ContentsMemberListItemVO item : contentsMemberList) {
            if (TextUtils.equals(strMyId, item.userId)) {
                return true;
            }
        }
        return false;
    }

    // 본인의 작업일 경우 진행 상태를 판단

    /**
     * "0": 사옹하지 않음 "1": 진행중 "2": 종료
     */
    public boolean isJobComplete(ArrayList<ContentsMemberListItemVO> contentsMemberList) {
        for (ContentsMemberListItemVO item : contentsMemberList) {
            if (TextUtils.equals(HMGWServiceHelper.userId, item.userId) && TextUtils.equals(item.status, "1")) {
                return false;
            } else if (TextUtils.equals(HMGWServiceHelper.userId, item.userId) && TextUtils.equals(item.status, "2")) {
                return true;
            }
        }
        return false;
    }

    public boolean isWorkDone(String userId, ArrayList<ContentsMemberListItemVO> contentsMemberList) {
        if (contentsMemberList == null) {
            return false;
        }
        for (ContentsMemberListItemVO vo : contentsMemberList) {
            if (TextUtils.equals(vo.userId, userId) && TextUtils.equals(vo.status, "2")) {
                return true;
            }
        }
        return false;
    }

    public SearchWebViewClient getSearchObserverClient(Activity activity) {
        if (m_searchWebViewClient == null) {
            m_searchWebViewClient = new SearchWebViewClient(activity);
        }
        return m_searchWebViewClient;
    }

    public View findBackButton(ViewGroup vg) {
        nFindBackButtonCount++;
        Debug.trace("findBackButton::", nFindBackButtonCount);
        View ret = null;
        if (vg.getChildCount() > 0) {
            for (int i = 0; i < vg.getChildCount(); ++i) {
                if (vg.getChildAt(i).getId() == R.id.btn_navi_back) {
                    return vg.getChildAt(i);
                }
                if (vg.getChildAt(i) instanceof ViewGroup) {
                    ret = findBackButton((ViewGroup) vg.getChildAt(i));
                    if (ret != null) {
                        return ret;
                    }
                }
            }
        }
        nFindBackButtonCount--;
        Debug.trace("findBackButton::", nFindBackButtonCount);
        return ret;
    }

    public void setWebviewToolbar(Context context, final CordovaWebView webView, ViewGroup toolBar, JSONArray data) {
        // 샘플 데이타: [3, ["Btn1", "Btn2", "Btn3"], ["javascript:alert(1);",
        // "javascript:alert(2);", "javascript:alert(3);"]]
        int count = data.optInt(0);
        JSONArray labels = data.optJSONArray(1);
        JSONArray images = data.optJSONArray(2);
        JSONArray functions = data.optJSONArray(3);
        for (int i = 0; i < count; i++) {
            CustomTextButton btn = (CustomTextButton) LayoutInflater.from(context).inflate(R.layout.template_textbutton, toolBar, false);    // tkofs template_textbutton 수정
            String imageName;
            try {    // tkofs
                imageName = (images.optString(i)).split("\\.")[0];
            } catch (Exception e) {
                imageName = "btn_tool_receive";
            }
            btn.setText(labels.optString(i));
			/*int imageId = context.getResources().getIdentifier("style_" + imageName, "drawable", context.getPackageName());
			btn.setBackgroundResource(imageId); tkofs 확인 필요.*/
            btn.setOnclickFunction(functions.optString(i));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CustomTextButton) v).getOnclickFunction().startsWith("javascript:apprApprove")
                            && !((CustomTextButton) v).getOnclickFunction().startsWith("javascript:apprApprove('gongram'")) {
                        boolean ret = getOpenApiService().checkUserInfo(getMainFragment().getActivity(), HMGWServiceHelper.userId);
                        if (ret) {
                            MainFragment.getController().checkPasswordForSign(((CustomTextButton) v).getOnclickFunction());
                        } else {
                            webView.loadUrl(((CustomTextButton) v).getOnclickFunction());
                        }
                    } else {
                        webView.loadUrl(((CustomTextButton) v).getOnclickFunction());
                    }
                }
            });
            toolBar.addView(btn);
        }
        toolBar.setVisibility(View.VISIBLE);
    }

    public void showWriteComment(Activity activity, JSONArray data, boolean isPopup) {
        Intent intent = new Intent();
        try {
            intent.putExtra("comment", data.getString(0));
            intent.putExtra("mustOpinion", data.getBoolean(1));
            intent.putExtra("isApprOpinion", data.getBoolean(2));
            intent.putExtra("wordType", data.getInt(3));
            intent.putExtra("callback", data.getString(4));
            intent.putExtra(WriteCommentViewer.INTENT_KEY_IS_POPUP, isPopup);
        } catch (JSONException e) {
            Debug.trace(e.getMessage());
        }
        intent.setClass(activity, WriteCommentViewer.class);
        activity.startActivity(intent);
    }

    public CordovaInterface getCordovaInterface(Activity activity) {
        if (m_cordovaInterface == null) {
            m_cordovaInterface = new CordovaInterfaceImpl(activity) {
                public Object onMessage(String id, Object data) {
                    return super.onMessage(id, data);
                }
            };
        }
        return m_cordovaInterface;
    }

    public CordovaInterface getPopupCordovaInterface(Activity activity) {
        if (m_popupCordovaInterface == null) {
            m_popupCordovaInterface = new CordovaInterfaceImpl(activity) {
                @Override
                public Object onMessage(String id, Object data) {
                    return super.onMessage(id, data);
                }
            };
        }
        return m_popupCordovaInterface;
    }

    public void setCordovaPreferences(CordovaPreferences preferences) {
        m_cordovaPreferences = preferences;
    }

    public CordovaPreferences getCordovaPreferences() {
        return m_cordovaPreferences;
    }

    public void setCordovaPluginEntrys(ArrayList<PluginEntry> pluginEntries) {
        m_pluginEntries = pluginEntries;
    }

    public ArrayList<PluginEntry> getCordovaPluginEntrys() {
        return m_pluginEntries;
    }

    public void showView(FragmentManager fragmentManager, int containerViewId, Views v, Bundle bd) {
        CommonFragment f = null;
        switch (v) {
            case LOADING:
                f = new LoadingFragment();
                break;
            case LOGIN:
                f = new LoginFragment();
                break;
            case MAIN:
            case HOME:
                f = new MainFragment();
                break;
        }
        if (f != null) {
            if (bd != null) {
                f.setArguments(bd);
            }
            f.setMainModel(this);
            fragmentManager.beginTransaction().replace(containerViewId, f).commitAllowingStateLoss();
        }
    }

    // tkofs
    private HomeFragment m_homeFragment;

    public void setHomeFragment(HomeFragment homeFragment) {
        m_homeFragment = homeFragment;
    }

    public HomeFragment getHomeFragment() {
        return m_homeFragment;
    }

    public void setWebView(WebViewFragment cordovaWebViewFragment) {
        m_webViewFragment = cordovaWebViewFragment;
    }

    public void setPopupWebView(PopupWebViewFragment cordovaWebViewFragment) {
        m_popupWebviewFragment = cordovaWebViewFragment;
    }

    public WebViewFragment getWebViewFragment() {
        return m_webViewFragment;
    }

    public PopupWebViewFragment getPopupWebViewFragment() {
        return m_popupWebviewFragment;
    }

    public void setTaskAndDataSet(Context context) {
        ViewModel.Log(context==null?"null":"not null", "tkofs_bluetooth");
        task = HMGWTask.getInstance();
        dataSet = task.getConfigDataSet(context);
    }

    public void setMainFragment(MainFragment mainFragment) {
        m_mainFragment = mainFragment;
    }

    public MainFragment getMainFragment() {
        return m_mainFragment;
    }

    public void createAqueryIntence(Context context) {
        m_aq = new AQuery(context);
    }

    public AQuery getAq() {
        return m_aq;
    }

    public DocViewFragment getDocViewer() {
        return m_docViewFragment;
    }

    public void setDocViewer(DocViewFragment docViewFragment) {
        m_docViewFragment = docViewFragment;
    }

    public HtmlViewFragment getHtmlViewer() {
        return m_htmlViewFragment;
    }

    public void setHtmlViewer(HtmlViewFragment htmlViewFragment) {
        m_htmlViewFragment = htmlViewFragment;
    }

    public String getDocData() {
        return m_docData;
    }

    public void setDocData(String s) {
        m_docData = s;
    }

    public void setFragmentManager(FragmentManager supportFragmentManager) {
        m_supportFragmentManager = supportFragmentManager;
    }

    public FragmentManager getFragmentManager() {
        return m_supportFragmentManager;
    }

    public boolean checkPushAvailable() {
        return HMGWServiceHelper.USE_PUSH_SERVICE && Build.VERSION.SDK_INT > 14;
    }

    public void createPreferenceManager(final Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        if (MainModel.getInstance().pref != null) prefEdit = MainModel.getInstance().pref.edit();
    }

    public String getVersionName(FragmentActivity activity) {
        String version = "";
        PackageInfo pInfo;
        try {
            pInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (NameNotFoundException e) {
            Debug.trace(e.getMessage());
        }
        return version;
    }

    public void addSquarePushListener(ISquarePushListener listener) {
        if (!m_squarePushListener.contains(listener)) {
            m_squarePushListener.add(listener);
        }
    }

    public void removeSquarePushListener(ISquarePushListener listener) {
        if (m_squarePushListener.contains(listener)) {
            m_squarePushListener.remove(listener);
        }
    }

    public void notifyPush(final SquarePushVO squarePushVO) {
        if (m_squarePushListener.size() > 0 && squarePushVO != null) {
            AQUtility.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    Debug.trace("notifyPush::", squarePushVO.square_action.name());
                    for (ISquarePushListener listener : m_squarePushListener) {
                        listener.onPushReceive(squarePushVO);
                    }
                }
            });
        }
    }

    public void setCurrentSquare(WorkGroupType t) {
        m_currentSquare = t;
    }

    public WorkGroupType getCurrentSquare() {
        return m_currentSquare;
    }

    public void setCurrentSpSquare(SpSquareType t) {
        m_currentSpSquare = t;
    }

    public SpSquareType getCurrentSpSquare() {
        return m_currentSpSquare;
    }

    public void loadGroupInfo(Activity a, String square_id, SquareDefaultAjaxCallBack<GroupInfoVO> callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("squareId", square_id);
        new ApiLoaderEx<JSONObject>(new GroupInfo(a), a.getApplicationContext(), callback, params).execute();
    }

    public boolean checkMember(final Activity a, GroupInfoVO vo) {
        // 일단 목록에 뿌릴 데이터를 가져와서 뿌리고
        boolean bExist = false;
        for (SquareMemberVO member : vo.squareMemberList) {
            if (TextUtils.equals(member.memberId, HMGWServiceHelper.userId)) {
                // 자신이 속해 있는 그룹인지 판단하여 refresh 여부 판단.
                bExist = true;
            }
        }
        if (!bExist) {
            new AlertDialog.Builder(a).setTitle(R.string.message_confirm_title).setMessage("현재 스퀘어에서 강퇴되었습니다. 스퀘어그룹 화면으로 돌아갑니다.")
                    .setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            a.setResult(Activity.RESULT_CANCELED);
                            a.finish();
                        }
                    }).setCancelable(false).show();
        }
        return bExist;
    }

    public void setCurrentSquareId(String squareId) {
        m_currentSquareId = squareId;
    }

    public String getCurrentSquareId() {
        return m_currentSquareId;
    }

    public String getUserId() {
        return m_strUserId;
    }

    public void setUserId(String id) {
        m_strUserId = id;
    }

    public SpSquareType getCurrentSquarePlus() {
        return m_currentSpSquare;
    }

    public void setCurrentSquarePlus(SpSquareType t) {
        m_currentSpSquare = t;
    }


}
