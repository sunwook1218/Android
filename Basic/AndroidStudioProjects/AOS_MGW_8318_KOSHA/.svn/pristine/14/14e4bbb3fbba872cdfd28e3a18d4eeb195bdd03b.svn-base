package com.hs.mobile.gw.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.legacy.app.ActionBarDrawerToggle;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.DownloadActivity;
import com.hs.mobile.gw.MainActivity;
import com.hs.mobile.gw.MainController;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.MenuListHelper;
import com.hs.mobile.gw.MenuListHelper.SpSquareType;
import com.hs.mobile.gw.WebViewHelper;
import com.hs.mobile.gw.adapter.MGWBaseAdapter;
import com.hs.mobile.gw.adapter.SignListAdapter;
import com.hs.mobile.gw.fragment.squareplus.SpFolderSelectFragment;
import com.hs.mobile.gw.fragment.squareplus.SpFolderSelectFragment.SpSelectType;
import com.hs.mobile.gw.gnb.GnbDataModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.GWOpenApi;
import com.hs.mobile.gw.openapi.square.GroupInfo;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquareMenuList;
import com.hs.mobile.gw.openapi.squareplus.SpInitMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFolderListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderSquareVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.plugin.HMGWPlugin;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.RestApiProgress;
import com.hs.mobile.gw.util.TextViewUtils;
import com.hs.mobile.gw.util.WebkitCookieManagerProxy;
import com.hs.mobile.gw.view.FooterToolBar;
import com.hs.mobile.gw.view.MiddleMenuContainer;
import com.hs.mobile.gw.view.OnScrollListView;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_OPEN;

/**
 * 메인 화면
 * 
 * @author C.H.Lee
 *
 */
public class MainFragment extends CommonFragment implements ISquarePushListener {
	public static final String HIDE_WEBVIEW_CONTAINER = "hide_webview";
	final public static int MAX_WEBVIEWCNT = 2;

	public static View pressedMenuView;
	private static MainController m_controller;
	public static WebView customWebView;
	public static LinearLayout loadingView;
	public static LinearLayout additionalListMenu;
	public static RelativeLayout mMainViewContainer;
	private MiddleMenuContainer middleMenuContainer;
	public static LinearLayout customWebViewContainer;
	public static RelativeLayout mCurrentUserInfo;
	public static int additionalLeftBoundary;
	public static LayoutInflater mInflater;
	public static ApiCode mCurrentApiCode;
	public static MenuListHelper menuListHelper;
	public static View mDeleteMailFooter;
	public static LinearLayout mSaveContactFooter;
	public static FooterToolBar mListFooter;
	public static LinearLayout mMailSearchMenu;
	
	
	public static LinearLayout mBoardSearchMenu;
	public static LinearLayout mContactSearchMenu;
	public static LinearLayout mSignSearchMenu;
	public static HorizontalScrollView mToolbarContainer;
	public static AlertDialog mSignFilter;
	public static ProgressDialog progressDialog;
	public static ProgressDialog progressDialogWithCancel;
	public static RelativeLayout mainRightContent;
	private LinearLayout m_menuContainer;
	private LinearLayout m_menuLayout;
	boolean isInboxThreadRunning = true;

	public static AlertDialog mPasswordDialogForMail;
	public static AlertDialog mPasswordDialogForSign;
	public CookieManager cookieManager;
	private static DrawerLayout m_drawerLayout;
	private ActionBarDrawerToggle m_drawerToggle;
	private final int DIALOG_LOADING = 1;
	private ProgressDialog mProgressDialog = null;
	private RestApiProgress m_restApiProgress;
	private MainModel m_model;
	private FrameLayout m_stageLayout;
	private FrameLayout m_container;
	private float lastTranslate = 0.0f;
	private FrameLayout mPopupWebViewContainer;
	
	// 20.06.23 tkofs
    public ImageView usePhoto;
    public TextView useName;
    public TextView useGroup;
    public LinearLayout useLogOut;
	public View rootView;
    
    public LinearLayout lnbLogo;
    public LinearLayout lnbClsBtn;
    
    public TextView btnMyJobs;
    
	Timer timer = null;
	TimerTask timerTask = null;

	public static DrawerLayout getDrawerLayout() {
		return m_drawerLayout;
	}

	public static int getAdditionalLeftBoundary() {
		if (MainModel.getInstance().isTablet()) {
			return additionalLeftBoundary;
		}
		return 0;
	}

	public static void setListFooter(FooterToolBar mListFooter) {
		MainFragment.mListFooter = mListFooter;
	}

	public static void setSignFilter(AlertDialog mSignFilter) {
		MainFragment.mSignFilter = mSignFilter;
	}

	public static void showMenuItem(String menuIDString) {
		menuListHelper.showMenuByID(menuIDString);
	}

	public static void loadingProgress(boolean finished) {
		if (finished) {
			m_controller.hideLoadingProgressDialog();
		} else {
			m_controller.showLoadingProgressDialog();
		}
	}

	// URL 보기
	public void showURL(String url) {
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		getActivity().startActivity(i);
	}

	// 첨부 다운로드
	public void onDownloadStart(String url, String fileName) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra("url", url);
		intent.putExtra("fileName", fileName);
		intent.setClass(getActivity(), DownloadActivity.class);
		getActivity().startActivity(intent);
	}

	public void initWebviewNavibar() {
		if (m_model.getWebViewFragment() != null && m_model.getWebViewFragment().getWebViewContainer() != null
				&& ((LinearLayout) m_model.getWebViewFragment().getWebViewContainer().findViewById(R.id.navibarLeftButtons)) != null
				&& ((LinearLayout) m_model.getWebViewFragment().getWebViewContainer().findViewById(R.id.navibarRightButtons)) != null) {
			((LinearLayout) m_model.getWebViewFragment().getWebViewContainer().findViewById(R.id.navibarLeftButtons)).removeAllViews();
			((LinearLayout) m_model.getWebViewFragment().getWebViewContainer().findViewById(R.id.navibarRightButtons)).removeAllViews();
		}
	}

	@Override
	public void onResume() {
		ViewModel.Log("MainFrg blue 해제", "bluetooth");
		super.onResume();
		HMGWPlugin.addListener(m_controller);
	}

	@Override
	public void onPause() {
		super.onPause();
//		HMGWPlugin.removeListener(m_controller);
	}
	
	public void tiggerLogout() {
        try {
            useLogOut.performClick();
        } catch (Exception e) {
        }
    }
    
    /**
     * @Autor tkofs
     * @Date 20.06.23
     * @name onDestroyView
     * @description
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_main, container, false);
		m_model = MainModel.getInstance();
		m_controller = new MainController(this, m_model);
		m_model.setMainFragment(this);
		m_drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);
		m_menuContainer = (LinearLayout) m_drawerLayout.findViewById(R.id.menu_list_container);
		// m_menuLayout = m_menuContainer.findViewById(R.id.menu_layout); tkofs
		m_stageLayout = (FrameLayout) m_drawerLayout.findViewById(R.id.stage);
		
		// tkofs 20.06.23
        GnbDataModel.mainController = m_controller;
        //GnbDataModel.menuListHelper = menuListHelper;
        GnbDataModel.mainFragment = this;

		if (MainModel.getInstance().isTablet()) {
			m_drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_OPEN);
		}else{
			m_drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED);
		}

		
		usePhoto = (ImageView) m_drawerLayout.findViewById(R.id.profile_image);
        useName = (TextView) m_drawerLayout.findViewById(R.id.lnb_user_name);
        useGroup = (TextView) m_drawerLayout.findViewById(R.id.lnb_user_group);
        useLogOut = (LinearLayout) m_drawerLayout.findViewById(R.id.btnLogout);//(TextView) m_drawerLayout.findViewById(R.id.btn_log_out);
        useLogOut.setOnClickListener(m_controller);
		
		lnbLogo = (LinearLayout) m_drawerLayout.findViewById(R.id.layout_logo);
		lnbClsBtn = m_drawerLayout.findViewById(R.id.BTN_MENU_CLOSE);
		lnbClsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toggleMenu();
			}
		});

        useName.setTextSize(14);
        useGroup.setTextSize(13);

        useGroup.setText(HMGWServiceHelper.deptName);
        useName.setText(HMGWServiceHelper.name);
		
		TextView logoutText = (TextView) m_drawerLayout.findViewById(R.id.btn_log_out);
        logoutText.setTextSize(12);

        // 프로필 이미지 원형 적용
        //GradientDrawable drawable = (GradientDrawable) getActivity().getDrawable(R.drawable.border_rouding);
        //usePhoto.setBackground(new ShapeDrawable(new OvalShape()));
        /*if (Build.VERSION.SDK_INT >= 21) {
            usePhoto.setClipToOutline(true);
        }*/
		
		m_drawerToggle = new ActionBarDrawerToggle(getActivity(), m_drawerLayout, R.drawable.ic_drawer, R.drawable.ic_drawer,
				R.drawable.ic_drawer) {
			@SuppressLint("NewApi")
			public void onDrawerSlide(View drawerView, float slideOffset) {
				float moveFactor = (m_menuContainer.getWidth() * slideOffset);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					m_stageLayout.setTranslationX(moveFactor);
				} else {
					TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
					anim.setDuration(0);
					anim.setFillAfter(true);
					m_stageLayout.startAnimation(anim);
					lastTranslate = moveFactor;
				}
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				// super.onDrawerClosed(drawerView);
			}
		};

		m_drawerLayout.setDrawerListener(m_drawerToggle);
		
		if (MainModel.getInstance().isTablet()) { // 테블릿이면 오른쪽 컨텐츠 레이아웃을 테블릿용으로
			mainRightContent = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.main_right_content_tablet, null, false);
		} else {
			mainRightContent = (RelativeLayout) LayoutInflater.from(getActivity()).inflate(R.layout.main_right_content, null, false);
		}
		m_stageLayout.addView(mainRightContent, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mPopupWebViewContainer = (FrameLayout) rootView.findViewById(R.id.popupWebviewContainer);
		loadingView = (LinearLayout) rootView.findViewById(R.id.mainBg);
		mMainViewContainer = (RelativeLayout) rootView.findViewById(R.id.mainViewContainer);
		// webviewContainer = (WebViewContainer)
		// mainRightContent.findViewById(R.id.webviewContainer);

		if (HMGWServiceHelper.cookies != null && !HMGWServiceHelper.cookies.isEmpty()) {
			CookieSyncManager.createInstance(getActivity());
			// unrelated, just make sure cookies are generally allowed
			android.webkit.CookieManager.getInstance().setAcceptCookie(true);
			// magic starts here
			WebkitCookieManagerProxy coreCookieManager = new WebkitCookieManagerProxy(null, java.net.CookiePolicy.ACCEPT_ALL);
			java.net.CookieHandler.setDefault(coreCookieManager);

			CookieManager cookieManager = CookieManager.getInstance();
			Cookie sessionInfo = null;
			for (Cookie cookie : HMGWServiceHelper.cookies) {
				// Debug.trace("--------login-------------("+cookie.getName()+":"+cookie.getValue());
				sessionInfo = cookie;
				String cookieString = sessionInfo.getName() + "=" + sessionInfo.getValue() + "; path=" + sessionInfo.getPath();
				cookieManager.setCookie(sessionInfo.getDomain(), cookieString);
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
	                //noinspection deprecation
	                CookieSyncManager.getInstance().sync();
	            } else {
	                // 롤리팝 이상에서는 CookieManager의 flush를 하도록 변경됨.
	                CookieManager.getInstance().flush();
	            }
			}
		}
		
		// WebView 설정
		// 로딩 Fragment에서 CordovaWebViewFragment를 두개 생성한다.
		MainModel.getInstance().setWebView(new WebViewFragment());
		MainModel.getInstance().setPopupWebView(new PopupWebViewFragment());

		getFragmentManager().beginTransaction()
					.add(mainRightContent.findViewById(R.id.webviewContainer).getId(), MainModel.getInstance().getWebViewFragment())
					.commitAllowingStateLoss();
		getFragmentManager().beginTransaction().add(mPopupWebViewContainer.getId(), MainModel.getInstance().getPopupWebViewFragment())
					.commitAllowingStateLoss();

		m_model.setWebViewLoadedCnt(0);
		customWebView = (WebView) mainRightContent.findViewById(R.id.customWebView);
		middleMenuContainer = (MiddleMenuContainer) mainRightContent.findViewById(R.id.middleMenuContainer);
		customWebViewContainer = (LinearLayout) mainRightContent.findViewById(R.id.customWebviewContainer);
		//mCurrentUserInfo = (RelativeLayout) m_menuContainer.findViewById(R.id.userInfoContainer);
		m_model.setLastSelectedTabIndex(0);
		
		//mCurrentUserInfo.findViewById(R.id.btnLogout).setOnClickListener(m_controller);
		//mCurrentUserInfo.findViewById(R.id.changeUser).setOnClickListener(m_controller);
		
		setCurrentUserInfo();
		initWebView();
		m_controller.setSearchObserver(customWebView, getActivity());
		/*
		 * 메인 뷰: mMainViewContainer 팝업 웹뷰: mPopupWebViewContainer
		 */

		{// 키보드가 있을때는 우선 없애도록 해야함...
			middleMenuContainer.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						EditText editText = (EditText) getActivity().findViewById(R.id.inputKeywordMail);
						if (editText.isFocused()) {
							Rect outRect = new Rect();
							editText.getGlobalVisibleRect(outRect);
							if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
								editText.clearFocus();
								InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
								imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
							}
						}
						EditText editText2 = (EditText) getActivity().findViewById(R.id.inputKeywordContact);
						if (editText2.isFocused()) {
							Rect outRect = new Rect();
							editText.getGlobalVisibleRect(outRect);
							if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
								editText.clearFocus();
								InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
								imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
							}
						}
					}
					return false;
				}
			});
		}

		{// 새로 불러오는중.. 메시지를 보여줄 프로그레스바
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.setMessage(getString(R.string.message_notify_loading_data));
			progressDialog.setCancelable(false);
		}

		{ // 취소가 가능한 프로그레스바 - 첨부 불러오기 결재본문 가져오기, 결재 처리 에서 사용
			progressDialogWithCancel = new ProgressDialog(getActivity());
			progressDialogWithCancel.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialogWithCancel.setMessage(getString(R.string.message_notify_loading_data));

			progressDialogWithCancel.setCancelable(false);
			/*progressDialogWithCancel.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.label_cancel),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							if (m_model.isFirstLoading()) {
								m_controller.doLogout();
							} else {
								progressDialogWithCancel.dismiss();
							}
							return;
						}
					});*/
		}
		mListFooter = (FooterToolBar) mainRightContent.findViewById(R.id.listFooter);
		mMailSearchMenu = (LinearLayout) middleMenuContainer.findViewById(R.id.mailSearchContainer);
		mBoardSearchMenu = (LinearLayout) middleMenuContainer.findViewById(R.id.boardSearchContainer);
		mContactSearchMenu = (LinearLayout) middleMenuContainer.findViewById(R.id.contactSearchContainer);
		mSignSearchMenu = (LinearLayout) middleMenuContainer.findViewById(R.id.signSearchContainer);
		mDeleteMailFooter = (View) mainRightContent.findViewById(R.id.deleteMailFooter);
		mSaveContactFooter = (LinearLayout) mainRightContent.findViewById(R.id.saveContactFooter);
		customWebViewContainer.findViewById(R.id.customMiddleGoMainMenuButton).setOnClickListener(m_controller);
		mDeleteMailFooter.findViewById(R.id.btnDeleteMail).setOnClickListener(m_controller);
		mDeleteMailFooter.findViewById(R.id.btnReadMail).setOnClickListener(m_controller);
		mSaveContactFooter.findViewById(R.id.btnSaveContact).setOnClickListener(m_controller);
		middleMenuContainer.findViewById(R.id.middleBackButton).setOnClickListener(m_controller);
		middleMenuContainer.findViewById(R.id.middleGoMainMenuButton).setOnClickListener(m_controller);
		middleMenuContainer.findViewById(R.id.middleEditMailList).setOnClickListener(m_controller);
		middleMenuContainer.findViewById(R.id.middleSpMenu).setOnClickListener(m_controller);

		{// 메일 검색 필드 선택시 키보드에 검색 버튼 생성
			EditText editText = (EditText) mainRightContent.findViewById(R.id.inputKeywordMail);
			editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
			editText.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						View cancelButton = mMailSearchMenu.findViewById(R.id.cancelSearchMailList);
						cancelButton.setVisibility(View.VISIBLE);
						cancelButton.setOnClickListener(m_controller);
						(mMailSearchMenu.findViewById(R.id.mailSearchOptions)).setVisibility(View.VISIBLE);
					}
					return false;
				}
			});
			RadioGroup options = (RadioGroup) mMailSearchMenu.findViewById(R.id.mailSearchOptions);
			options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					m_controller.searchMail((TextView) rootView.findViewById(R.id.inputKeywordMail));
				}
			});

			editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					if (actionId == EditorInfo.IME_ACTION_SEARCH) {
						m_controller.searchMail(v);
						return true;
					}
					return false;
				}
			});
		}

		{// 게시판 검색 필드 선택시 키보드에 검색 버튼 생성
			EditText editText = (EditText) mainRightContent.findViewById(R.id.inputKeywordBoard);
			editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
			editText.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						View cancelButton = mBoardSearchMenu.findViewById(R.id.cancelSearchBoard);
						cancelButton.setVisibility(View.VISIBLE);
						cancelButton.setOnClickListener(m_controller);
						(mBoardSearchMenu.findViewById(R.id.boardSearchOptions)).setVisibility(View.VISIBLE);
					}
					return false;
				}
			});
			RadioGroup options = (RadioGroup) mBoardSearchMenu.findViewById(R.id.boardSearchOptions);
			options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					m_controller.searchBoard((TextView) rootView.findViewById(R.id.inputKeywordBoard));
				}
			});
			
			editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					if (actionId == EditorInfo.IME_ACTION_SEARCH) {
						m_controller.searchBoard(v);
						return true;
					}
					return false;
				}
			});
		}

		{// 주소록 검색 필드 선택시 키보드에 검색 버튼 생성
			EditText editText = (EditText) mainRightContent.findViewById(R.id.inputKeywordContact);
			editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
			editText.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						Button cancelButton = (Button) mContactSearchMenu.findViewById(R.id.cancelSearchContact);
						cancelButton.setOnClickListener(m_controller);
						cancelButton.setVisibility(View.VISIBLE);
						(mContactSearchMenu.findViewById(R.id.contactSearchOptions)).setVisibility(View.VISIBLE);
					}
					return false;
				}
			});

			RadioGroup options = (RadioGroup) mContactSearchMenu.findViewById(R.id.contactSearchOptions);
			options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					m_controller.searchContact((TextView) rootView.findViewById(R.id.inputKeywordContact));
				}
			});
			editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					if (actionId == EditorInfo.IME_ACTION_SEARCH) {
						m_controller.searchContact(v);
						return true;
					}
					return false;
				}
			});
		}
		
		{// 결재문서 검색 필드 선택시 키보드에 검색 버튼 생성
			EditText editText = (EditText) mainRightContent.findViewById(R.id.inputKeywordSign);
			editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
			editText.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						Button cancelButton = (Button) mSignSearchMenu.findViewById(R.id.cancelSearchSign);
						cancelButton.setVisibility(View.VISIBLE);
						cancelButton.setOnClickListener(m_controller);
						(mSignSearchMenu.findViewById(R.id.signSearchOptions)).setVisibility(View.VISIBLE);
					}
					return false;
				}
			});

			RadioGroup options = (RadioGroup) mSignSearchMenu.findViewById(R.id.signSearchOptions);
			options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					m_controller.searchSign((TextView) rootView.findViewById(R.id.inputKeywordSign));
				}
			});
			editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					if (actionId == EditorInfo.IME_ACTION_SEARCH) {
						m_controller.searchSign(v);
						return true;
					}
					return false;
				}
			});
		}		

		// 결재 필터
		{
			final String[] items = { getString(R.string.label_filter_sign_all), getString(R.string.label_filter_sign_1),
					getString(R.string.label_filter_sign_3), getString(R.string.label_filter_sign_2),
					getString(R.string.label_filter_sign_7), getString(R.string.label_filter_sign_5),
					getString(R.string.label_filter_sign_4) };

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(R.string.label_filter_sign_message);
			((TextView) mainRightContent.findViewById(R.id.selectedSignFilterName)).setOnClickListener(m_controller);
			((TextView) mainRightContent.findViewById(R.id.selectedSignFilterName)).setText(items[0]);
			builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int position) {
					String type = null;
					switch (position) {
					case 0: {
						type = null;
						break;
					}
					case 1: {
						type = "1";
						break;
					}
					case 2: {
						type = "3";
						break;
					}
					case 3: {
						type = "2";
						break;
					}
					case 4: {
						type = "7";
						break;
					}
					case 5: {
						type = "5";
						break;
					}
					case 6: {
						type = "4";
						break;
					}
					}

					List<NameValuePair> formparams = null;
					if (type != null) {
						formparams = new ArrayList<NameValuePair>();
						formparams.add(new BasicNameValuePair("apprtype", type));
					}
					SignListAdapter adapter = (SignListAdapter) getCurrentListviewAdapter();
					adapter.setParameters(formparams);
					adapter.initData();
					((TextView) getActivity().findViewById(R.id.selectedSignFilterName)).setText(items[position]);
					dialog.dismiss();
				}
			});
			mSignFilter = builder.create();

		}

		menuListHelper = new MenuListHelper(this, m_menuContainer, HMGWServiceHelper.mgw_menu);	// tkofs m_menuContainer 로 변경
		menuListHelper.setController(m_controller);
		menuListHelper.createMenuList();
		initCountUpdater();// 카운트 업데이터
		m_model.addSquarePushListener(this);
		return rootView;
	}

	public static MainController getController() {
		return m_controller;
	}

	public void setCurrentUserInfo() {
		final ImageView photo = usePhoto;

		if (HMGWServiceHelper.VIEW_PROFILE_PHOTO && !TextUtils.isEmpty(HMGWServiceHelper.photoLink)) {
		    AjaxCallback<InputStream> cb = new AjaxCallback<InputStream>() {
		        @Override
		        public void callback(String url, InputStream inp, AjaxStatus status) {     
		        	m_model.getAq().id(photo).image(new BitmapDrawable(getResources(), inp));
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
		    m_model.getAq().ajax(GWOpenApi.BYPASS_STREAM,InputStream.class, cb);
		} else {
			photo.setVisibility(View.GONE);
		}

		// 20.06.23 tkofs 디자인변경으로 인해 미사용
        //TextView name = (TextView) mCurrentUserInfo.findViewById(R.id.currentUserName);
        //TextView deptName = (TextView) mCurrentUserInfo.findViewById(R.id.currentUserDeptName);
        //ImageView changeButton = (ImageView) mCurrentUserInfo.findViewById(R.id.changeUser);
		/*
		if (HMGWServiceHelper.hasAdditionalOfficer) {
			changeButton.setVisibility(View.VISIBLE);
		} else {
			changeButton.setVisibility(View.GONE);
		}
		deptName.setText(HMGWServiceHelper.deptName);
		name.setText(HMGWServiceHelper.name);
		*/
	}

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

	private class UpdateCountsTask extends  AsyncTask<String, JSONObject, JSONObject> {
		private UpdateCountsTask() {
			super();
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			super.onPostExecute(result);
			m_controller.updateMainMenuCounts(result);
		}

		@Override
		protected JSONObject doInBackground(String... params) {
			return m_model.getOpenApiService().getCounts(getActivity());
		}
	}

	public MGWBaseAdapter getCurrentListviewAdapter() {
		OnScrollListView view = (OnScrollListView) getMiddleMenuFlipper().getChildAt(getMiddleMenuFlipper().getDisplayedChild());
		if (view == null) {
			return null;
		}
		return view.getMGWBaseAdapter();
	}

	/**
	 * WebView 설정
	 * 
	 * @param
	 * @return void
	 */
	public void initWebView() {
		// WebViewHelper.initCordovarWebView((MainActivity) getActivity(),
		// webView);
		// WebViewHelper.initCordovarPopupWebView((MainActivity) getActivity(),
		// popupWebView);
		WebViewHelper.initCustomWebView((MainActivity) getActivity(), customWebView);
		//
		// if (TextUtils.isEmpty(HMGWServiceHelper.OpenAPI.SERVER_URL)) {
		// new HMGWServiceHelper.OpenAPI();
		// }
		// // 웹뷰의 첫 페이지 호출 (뷰들을 로딩하는 등의 초기화 처리를 한다)
		// webView.loadUrl("javascript:var APP_INFO_SERVER='" +
		// OpenAPI.SERVER_URL + "'");
		// webView.loadUrl("file:///android_asset/www/mobile_index.html");
		//
		// popupWebView.loadUrl("javascript:var APP_INFO_SERVER='" +
		// OpenAPI.SERVER_URL + "'");
		// popupWebView.loadUrl("file:///android_asset/www/mobile_index.html");
		// webView.getView().requestFocus();
		//
		// webView.getView().setOnKeyListener(m_controller);
		// popupWebView.getView().setOnKeyListener(m_controller);
	}

	public View getPressedMenuView() {
		return pressedMenuView;
	}
	
	public static void setPressedMenuView(View pressedMenuView) {
        MainFragment.pressedMenuView = pressedMenuView;
    }

	public WebView getCustomWebView() {
		return customWebView;
	}

	public LinearLayout getLoadingView() {
		return loadingView;
	}

	public MiddleMenuContainer getMiddleMenuContainer() {
		return middleMenuContainer;
	}

	public void reCreateMenuContainer() {
		menuListHelper.createMenuList();
		initCountUpdater();// 카운트 업데이터
	}

	public LinearLayout getCustomWebViewContainer() {
		return customWebViewContainer;
	}

	public ViewFlipper getMiddleMenuFlipper() {
		return middleMenuContainer.getMiddleMenuFlipper();
	}

	public MenuListHelper getMenuListHelper() {
		return menuListHelper;
	}

	public ProgressDialog getProgressDialogWithCancel() {
		return progressDialogWithCancel;
	}

	public AlertDialog getPasswordDialogForMail() {
		return mPasswordDialogForMail;
	}

	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	public RelativeLayout getMainRightContent() {
		return mainRightContent;
	}

	public LinearLayout getMailSearchMenu() {
		return mMailSearchMenu;
	}
	public LinearLayout getBoardSearchMenu() {
		return mBoardSearchMenu;
	}

	public AlertDialog getPasswordDialogForSign() {
		return mPasswordDialogForSign;
	}

	public void setPasswordDialogForSign(AlertDialog mPasswordDialogForSign) {
		MainFragment.mPasswordDialogForSign = mPasswordDialogForSign;
	}

	public View getDeleteMailFooter() {
		return mDeleteMailFooter;
	}

	public LinearLayout getSaveContactFooter() {
		return mSaveContactFooter;
	}

	public FooterToolBar getListFooter() {
		return mListFooter;
	}

	public LinearLayout getContactSearchMenu() {
		return mContactSearchMenu;
	}

	public LinearLayout getSignSearchMenu() {
		return mSignSearchMenu;
	}
	

	public AlertDialog getSignFilter() {
		return mSignFilter;
	}

	public void setPasswordDialogForMail(AlertDialog mPasswordDialogForMail) {
		MainFragment.mPasswordDialogForMail = mPasswordDialogForMail;
	}

	@SuppressLint("WrongConstant")
	public void toggleMenu() {
		TextViewUtils.hideKeyBoard(getActivity(), m_drawerLayout);
		if (m_drawerLayout.isDrawerOpen(Gravity.START)) {
			m_drawerLayout.closeDrawers();
		} else {
			m_drawerLayout.openDrawer(Gravity.START);
		}
	}

	public DrawerLayout getDrawerMenu() {
		return m_drawerLayout;
	}

	@Override
	public void onDestroy() {
		if(progressDialog != null){
			progressDialog.dismiss();
		}
		HMGWPlugin.removeListener(m_controller);
		m_model.removeSquarePushListener(this);
		stopTimer();
		super.onDestroy();
	}

	public View getWebviewContainer() {
		return mainRightContent.findViewById(R.id.webviewContainer);
	}

	public FrameLayout getPopupWebViewContainer() {
		return mPopupWebViewContainer;
	}

	@Override
	public void onPushReceive(final SquarePushVO vo) {
		if (vo != null) {
			if (menuListHelper.m_myWorkGroupListData != null && menuListHelper.m_lvSquareGroup != null) {
				switch (vo.square_action) {
				case GROUP_ADD:
				case GROUP_DELETE:
				case GROUP_MODIFY:
				case GROUP_REOPEN:
					menuListHelper.loadSquareGroupList(MainModel.getInstance().getCurrentSquare());
					break;
				case SQURE_MEMBER_ADMIN_CHANGE:
				case SQUARE_MEMBER_CHANGE:
					SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(),
							GroupInfoVO.class) {
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);
									menuListHelper.loadSquareGroupList(MainModel.getInstance().getCurrentSquare());
							}
					};
					Map<String, String> params = new HashMap<String, String>();
					params.put("squareId", vo.square_id);
					new ApiLoaderEx<JSONObject>(new GroupInfo(getActivity()), getActivity().getApplicationContext(), callback, params)
							.execute();
					break;
				}
			}
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Debug.trace(requestCode);
		
		if (requestCode == MainModel.REQ_SP_GROUP_FOLDER) {
			if (resultCode == Activity.RESULT_OK) {
				SpSelectType type = (SpSelectType) data.getSerializableExtra(SpFolderSelectFragment.ARG_KEY_SP_SELECT_TYPE);
				switch (type) {
				case MODIFY:
					SpFolderVO item = (SpFolderVO) data.getSerializableExtra(SpFolderSelectFragment.ARG_KEY_SP_FOLDER_ITEM);
					for (int i = 0; i < getMenuListHelper().m_spFolderListData.size(); i++) {
						if (getMenuListHelper().m_spFolderListData.get(i).getFolderId().equals(item.getFolderId())) {
							Debug.trace(getMenuListHelper().m_spFolderListData.get(i).getFolderName());
							Debug.trace(item.getFolderName());
							getMenuListHelper().m_spFolderListData.get(i).setFolderName(item.getFolderName());
							
							getMenuListHelper().m_lvSquareExpandableGroup.post(new Runnable() {
								@Override
								public void run() {
									getMenuListHelper().m_spFolderListAdapter.notifyDataSetChanged();

									for (int i = 0; i < getMenuListHelper().m_spFolderListData.size(); i++) {
										getMenuListHelper().m_lvSquareExpandableGroup.getRefreshableView()
												.expandGroup(i);
									}

									getMenuListHelper().m_lvSquareExpandableGroup.onRefreshComplete();
								}
							});
							
							break;
						}
					}
					break;
				case DELETE:
				case SQUARE_MOVE:
				case SQUARE_ADD:
//					for (int i = 0; i < getMenuListHelper().m_spFolderListData.size(); i++) {
//						if (getMenuListHelper().m_spFolderListData.get(i).getFolderId().equals(item.getFolderId())) {
//							getMenuListHelper().m_spFolderListData.remove(i);
//							
//							
//							
//							getMenuListHelper().m_lvSquareExpandableGroup.post(new Runnable() {
//								@Override
//								public void run() {
//									getMenuListHelper().m_spFolderListAdapter.notifyDataSetChanged();
//
//									for (int i = 0; i < getMenuListHelper().m_spFolderListData.size(); i++) {
//										getMenuListHelper().m_lvSquareExpandableGroup.getRefreshableView()
//												.expandGroup(i);
//									}
//
//									getMenuListHelper().m_lvSquareExpandableGroup.onRefreshComplete();
//								}
//							});
//							break;
//						}
//					}
					Debug.trace("refresh!");
					HashMap<String, String> parameter = new HashMap<String, String>();
					parameter.put("squareType", SpSquareType.ING.getType());
					
					PopupUtil.showLoading(getActivity());
					SpSquareListCallBack spSquareListCallBack = new SpSquareListCallBack(getActivity(), SpSquareVO.class) {
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);
							PopupUtil.hideLoading(getActivity());
							if (status.getCode() != 200) {
								getMenuListHelper().m_lvSquareExpandableGroup.onRefreshComplete();
								return;
							} else {
								// 스퀘어목록을 폴더용으로 변환시킨다.
								final SpFolderVO folderVO = convertSquareToFolder(this.dataList);

								SpFolderListCallBack spFolderListCallBack = new SpFolderListCallBack(getActivity(), SpFolderVO.class){
									@Override
									public void callback(String url, JSONObject json, AjaxStatus status) {
										super.callback(url, json, status);

										if (status.getCode() != 200) {
											getMenuListHelper().m_lvSquareExpandableGroup.onRefreshComplete();
										}

										getMenuListHelper().m_spFolderListData.clear();
										getMenuListHelper().m_spFolderListAdapter.notifyDataSetChanged();
										
										getMenuListHelper().m_spFolderListData.addAll(this.dataList);
										getMenuListHelper().m_spFolderListData.add(folderVO);

										String msgStr = String.format(getActivity().getString(R.string.ptr_last_updated), getMenuListHelper().lastUpdatedDateFormat.format(new Date()));
										MainFragment.mListFooter.setText(msgStr);

										if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
											try {
												int index = 0;
												while (getMenuListHelper().m_spFolderListData.get(index).getFolderSquareVOList().size() == 0) {
													index++;
												}

												getMenuListHelper().m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO().setSelected(true);
												MainModel.getInstance().goToGroup(getActivity(), getMenuListHelper().m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO());
											} catch (NullPointerException e) {
												Debug.trace(e);
											} catch (IndexOutOfBoundsException e) {
												Debug.trace(e);
											} catch (Exception e) {
												Debug.trace(e);
											}

//											MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(0));
										}

										getMenuListHelper().m_lvSquareExpandableGroup.post(new Runnable() {
											@Override
											public void run() {
												getMenuListHelper().m_spFolderListAdapter.notifyDataSetChanged();

												for (int i=0; i<getMenuListHelper().m_spFolderListData.size(); i++) {
													getMenuListHelper().m_lvSquareExpandableGroup.getRefreshableView().expandGroup(i);
												}

												getMenuListHelper().m_lvSquareExpandableGroup.onRefreshComplete();
											}
										});
									}
								};

								{ // API명
									HashMap<String,String> params = new HashMap<>();
									new ApiLoaderEx<>(new SpInitMySquareMenu(getActivity()), getActivity(), spFolderListCallBack, params).execute();
								}
							}
						}

						private SpFolderVO convertSquareToFolder(List<SpSquareVO> squareList) {
							SpFolderVO folderVO = new SpFolderVO();
							folderVO.setFolderName(getActivity().getString(R.string.label_squareplus_nofolder_square));
							List<SpFolderSquareVO> folderSquareList = new ArrayList<SpFolderSquareVO>();

							for (SpSquareVO squareVO : squareList) {
								if (!squareVO.isFolder()) {
									SpFolderSquareVO folderSquareVO = new SpFolderSquareVO();
									folderSquareVO.setSquareVO(squareVO);
									folderSquareList.add(folderSquareVO);
								}
							}

							folderVO.setFolderSquareVOList(folderSquareList);

							return folderVO;
						}
					};
					new ApiLoaderEx<>(new SpGetSquareMenuList(getActivity()), getActivity(), spSquareListCallBack, parameter).execute();
					break;
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		loadingView.removeAllViewsInLayout();
		View.inflate(getContext(), R.layout.mgw_loading_bg, loadingView);
	}
}
