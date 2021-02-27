package com.hs.mobile.gw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.hs.mobile.crypto.Crypto;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel.Views;
import com.hs.mobile.gw.MenuListHelper.MenuIDsMap;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.BoardAdapter;
import com.hs.mobile.gw.adapter.BoardAdapter.DataForWrite;
import com.hs.mobile.gw.adapter.ContactListAdapter;
import com.hs.mobile.gw.adapter.DeptSpinnerAdapter;
import com.hs.mobile.gw.adapter.MGWBaseAdapter;
import com.hs.mobile.gw.adapter.MailListAdapter;
import com.hs.mobile.gw.adapter.SignListAdapter;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.DocViewFragment;
import com.hs.mobile.gw.fragment.HtmlViewFragment;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.fragment.login.LoginFragment;
import com.hs.mobile.gw.fragment.mailwrite.MailWriteFragment;
import com.hs.mobile.gw.fragment.squareplus.SpFolderSelectFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.ShowMailEditorViewVO;
import com.hs.mobile.gw.openapi.squareplus.SpAddFolderMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFolderCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderVO;
import com.hs.mobile.gw.openapi.vo.AuthListVO;
import com.hs.mobile.gw.openapi.vo.DeptVO;
import com.hs.mobile.gw.plugin.HMGWPlugin.IPluginListener;
import com.hs.mobile.gw.plugin.HMGWPlugin.actionCode;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BadgeUtil;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.MGWUtils;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;
import com.hs.mobile.gw.view.CustomImageButton;
import com.hs.mobile.gw.view.CustomTabHost;
import com.hs.mobile.gw.view.CustomTextButton;
import com.hs.mobile.gw.view.FooterToolBar;
import com.hs.mobile.gw.view.FooterToolBar.ButtonPosition;
import com.hs.mobile.gw.view.FooterToolBar.FooterToolBarMode;
import com.hs.mobile.gw.view.FooterToolBar.IFooterToolBarListener;
import com.hs.mobile.gw.view.IFunctionAble;
import com.hs.mobile.gw.view.OnScrollListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainController implements IPluginListener, OnClickListener {

	private MainFragment m_view;
	private MainModel m_model;
	private android.widget.LinearLayout.LayoutParams m_naviLeftButtonLayoutParam;
	private android.widget.LinearLayout.LayoutParams m_naviRightButtonLayoutParam;
	private int m_nNaviButtonMargin = 0;
	private AdapterView.OnItemSelectedListener slistener;
	private DialogInterface.OnClickListener m_logoutDialogListener = new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			doLogout();
		}
	};

	public MainController(MainFragment view, MainModel model) {
		ViewModel.Log("init maincontroller", "tkofs_push");
		m_view = view;
		m_model = model;
	}

	@Override
	public void onEventFromWeb(final actionCode ac, final Object obj) {
		Debug.trace("MainController::onEventFromWeb", ac.name(), obj);
		// 만약 메일 쓰기 화면이면 WebView로 가는 이벤트를 막는다.
		if (SubActivityType.MAIL_WRITE.equals(m_model.getCurrentSubActivityType())) {
			return;
		}
		if(m_view.getActivity() == null){
			// tkofs
			return;
		}
		m_view.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				switch (ac) {
				case closeImageDocViewer:
					break;
				case closePopupViewer:
					hidePopupWebView((JSONArray) obj);
					break;
				case closeWaitingView:
					hideWaitingProgressDialog();
					break;
				case completeAppr:
					if (m_model.isPopupMode()) {
						Debug.trace("No more loading next appr in PopupWebView, JUST CLOSE IT!");
						try {
							hidePopupWebView(new JSONArray("[\"\",false]"));
						} catch (JSONException e) {
							Debug.trace(e.getMessage());
						}
						break;
					}
					completeAppr(obj);
					break;
				case deleteBoardMtrl:
				case deleteMail:
					if (m_model.isPopupMode()) {
						Debug.trace("No more loading next item(board or mail) in PopupWebView");
						try {
							hidePopupWebView(new JSONArray("[\"\",false]"));
						} catch (JSONException e) {
							Debug.trace(e.getMessage());
						}
						break;
					}
					deleteItemFromList((String) obj);
					break;
				case getImageDocViewerData:
					break;
				case hideDocViewerHeader:
					break;
				case hideTabBar:
					break;
				case hideToolBar:
					hideWebviewToolbar();
					break;
				case notiLoadingCompleted:
					synchronized (MainModel.class) {
						m_model.setWebViewLoadedCnt(m_model.getWebViewLoadedCnt() + 1);
						if (m_model.getWebViewLoadedCnt() == MainFragment.MAX_WEBVIEWCNT) {
							m_model.setWebViewLoaded(true);
							hideLoadingImage();
							final String prefKeyInitialMenu = MenuListHelper.PREF_KEY_INITIAL_MENU;
							final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(m_view.getActivity());
							String initMenuId = pref.getString(prefKeyInitialMenu, null);
							String category = null;
							String event = null;

							if (m_view.getArguments() != null) {
								category = m_view.getArguments().getString(MainActivity.PUSH_MSG_CATEGORY);
								event = m_view.getArguments().getString(MainActivity.PUSH_MSG_EVENT);
								Debug.trace("Arguments::Category= " + category);
								Debug.trace("Arguments::Event= " + event);
							}

							if (!TextUtils.isEmpty(category)) {
								String menuIdStr = MenuIDsMap.mail_received.toString();

								switch (category) {
									case "mail":
										menuIdStr = MenuIDsMap.mail_received.toString();
										break;
									case "board":
										menuIdStr = MenuIDsMap.board_recent.toString();
										break;
									case "appr":
										menuIdStr = MenuIDsMap.approval_waiting.toString();
										break;
									case "square":
										menuIdStr = MenuIDsMap.square_plus_my_group.toString();     //스퀘어 플러스 이용 (default)
//									menuIdStr = MenuIDsMap.square_work_group.toString();        //스퀘어만 이용
										break;
									case "hscalendar":
										menuIdStr = MenuIDsMap.schedule_schdlist.toString();
										break;
								}

								MainFragment.menuListHelper.showMenuByID(menuIdStr, event);
							} else if (initMenuId == null) {
								// 시작메뉴 세팅 - 서버 설정
								MainFragment.menuListHelper.startMenuByID(HMGWServiceHelper.start_menu_id, HMGWServiceHelper.start_menu_title,
										HMGWServiceHelper.start_menu_url, HMGWServiceHelper.start_menu_custom_id);
							} else {
								MainFragment.menuListHelper.showMenuByID(initMenuId);
							}
							if (m_model.isTablet() && !m_model.isUserActionOccurred() && m_model.isListViewLoaded()) {
								showCurrentListFirstRow();
							}
						}
					}
					break;
				case notiLoadingError:
					forceLogout();
					break;
				case popView:
					hideWebview(true);
					break;
				case sessioninfo:
					break;
				case setDocViewerHeader:
					// new String[] { data.optString(0), data.optString(1) }
					break;
				case setDocViewerPageNo:
					break;
				case setLeftBarButton:
				case setRightBarButton:
					if (m_model.isPopupMode()) {
						setNavibarButton(m_model.getPopupWebViewFragment().getNaviBarContainer(), (JSONArray) obj);
					} else {
						setNavibarButton(m_model.getWebViewFragment().getNaviBarContainer(), (JSONArray) obj);
					}
					break;
				case setSelectedTabBarItem:
					break;
				case setStateLeftBarButton:
					break;
				case setStateRightBarButton:
					break;
				case setTabBarButton:
					setWebviewTabbar((JSONArray) obj);
					break;
				case setTitle:
					if (MainModel.getInstance().isPopupMode()) {
						m_model.getPopupWebViewFragment().getTvNaviBarTitle().setText((String) obj);
					} else {
						m_model.getWebViewFragment().getTvNaviBarTitle().setText((String) obj);
					}
					break;
				case setToolBarButton:
					if (MainModel.getInstance().isPopupMode()) {
						m_model.getPopupWebViewFragment().getWebviewToolBar().removeAllViews();
						m_model.setWebviewToolbar(m_view.getActivity().getApplicationContext(), m_model.getPopupWebViewFragment()
								.getWebView(), m_model.getPopupWebViewFragment().getWebviewToolBar(), (JSONArray) obj);
					} else {
						m_model.getWebViewFragment().getWebviewToolBar().removeAllViews();
						m_model.setWebviewToolbar(m_view.getActivity().getApplicationContext(), m_model.getWebViewFragment().getWebView(),
								m_model.getWebViewFragment().getWebviewToolBar(), (JSONArray) obj);
					}

					break;
				case showCheckPasswdAnoBoard:
					break;
				case showDocViewerHeader:
					break;
				case showImageDocViewer:
					break;
				case showLocalViewer:
					break;
				case showLoginView:
					break;
				case showMenuItem:
					break;
				case showPhotoCamera:
					break;
				case showPopupMenu:
					break;
				case showPopupViewer:
					ViewModel.backKey_Lock = true;// tkofs
					loadPopupWebView((String) obj);
					break;
				case showSidebarMenu:
					showMainMenu(null);
					break;
				case showURL:
					break;
				case showWaitingView:
					if (obj instanceof Integer) {
//						Integer n = (Integer) obj;
						showWaitingProgressDialog((Integer) obj);
					}
					break;
				case showWebView:
					showWebView();
					break;
				case showWriteComment:
					m_model.showWriteComment(m_view.getActivity(), (JSONArray) obj, false);
					break;
				case showMailEditorView:
					try {
						JSONArray jarr = (JSONArray) obj;
						m_model.showSubActivity(
								m_view,
								SubActivityType.MAIL_WRITE,
								new BundleUtils.Builder()
										.add(MailWriteFragment.INTENT_KEY_SHOW_MAIL_EDITOR_VIEW_VO,
												new ShowMailEditorViewVO(jarr.getString(0), jarr.getString(1), jarr.getString(2), jarr
														.getString(3), jarr.getString(4), jarr.getString(5))).build());
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
					break;
				case onBackKeyPressed:
					m_view.getActivity().onBackPressed();
					break;
				case getAppServerIP:
                        break;
                    case closeMenuAllPopupViewer:
                        ViewModel.backKey_Lock = false; // tkofs
                        hidePopupWebViewGoMenu((JSONArray) obj);
                        break;
				default:
					break;
				}
			}
		});
	}

	public void clearSquareContentsFromTablet() {
		if (MainModel.getInstance().isTablet()) {
			LinearLayout layout = (LinearLayout)MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT);
            LinearLayout subLayout = (LinearLayout) MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_SUB_LAYOUT);
            LinearLayout sub2Layout = (LinearLayout) MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_SUB2_LAYOUT);
            layout.setVisibility(View.VISIBLE);
            layout.removeAllViews();
            subLayout.setVisibility(View.GONE);
            subLayout.removeAllViews();
            sub2Layout.setVisibility(View.GONE);
            sub2Layout.removeAllViews();
		}
	}

	public void hidePopupWebView(JSONArray data) {
		String scriptFunction = data.optString(0);
		boolean isFullView = data.optBoolean(1);
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.popup_exit));
		m_view.getPopupWebViewContainer().setAnimation(set);
		m_view.getPopupWebViewContainer().setVisibility(View.GONE);
		hideWebviewTabbar();
		hideWebviewToolbar();
		m_model.getPopupWebViewFragment().loadUrl("javascript:$.mobile.changePage('#_TEMPLATE_empty_page');");
		if (m_model.isPopupMode()) {
			m_model.setPopupMode(false);
			onRefreshCurrentList(null);
		}

		// SEOJAEHWA: event data 를 삭제해서 동일 이벤트가 재발생하지 않도록 방어
		if (m_view.getArguments() != null) {
			m_view.getArguments().remove(MainActivity.PUSH_MSG_EVENT);
		}
		if (m_view.getActivity() != null) {
			m_view.getActivity().getIntent().removeExtra(MainActivity.PUSH_MSG_EVENT);
		}

		if (!TextUtils.isEmpty(scriptFunction)) {
			if (isFullView) {
				m_view.getMenuListHelper().initViewByMenuID(MenuIDsMap.custom_webview_menu);
			}
			loadWebviewWithDelay(scriptFunction);
		}
	}
	
	public void hidePopupWebViewGoMenu(JSONArray data) {
        JSONObject menuInfo = data.optJSONObject(0);
        boolean isGoMenu = data.optBoolean(1);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.popup_exit));
        m_view.getPopupWebViewContainer().setAnimation(set);
        m_view.getPopupWebViewContainer().setVisibility(View.GONE);
        hideWebviewTabbar();
        hideWebviewToolbar();
        m_model.getPopupWebViewFragment().loadUrl("javascript:var APP_INFO_SERVER='" + HMGWServiceHelper.OpenAPI.SERVER_URL + "'");
        m_model.getPopupWebViewFragment().loadUrl(CustomWebViewFragment.FILE_ANDROID_ASSET_WWW_MOBILE_INDEX_HTML);
        m_model.setPopupMode(false);
        if (isGoMenu) {
            String menuId = menuInfo.optString("menuId");
            MainFragment.menuListHelper.showMenuByID(menuId);
        }
    }

	public void hideWebviewTabbar() {
		m_view.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (m_model.isPopupMode()) {
					if (m_model.getPopupWebViewFragment().getTabHost() != null) {
						m_model.getPopupWebViewFragment().getTabHost().setVisibility(View.GONE);
					}
				} else {
					if (m_model.getWebViewFragment().getTabHost() != null) {
						m_model.getWebViewFragment().getTabHost().setVisibility(View.GONE);
					}
				}
			}
		});
	}

	public void loadWebviewWithDelay(final String url) {
		// 일정 등록 팝업모드 true가 아닐경우에도 팝업으로 뜸 | tkofs 임시추가 코드
        if(url.equals("popupSch2AddView")) ViewModel.backKey_Lock = true;
        
		if (!m_model.isPopupMode()) {
			m_model.setUserActionOccurred(true);
		}
		if (!m_model.isWebViewLoaded()) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (!m_model.isWebViewLoaded()) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							Debug.trace(e.getMessage());
						}
					}
					m_view.getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							loadURLInWebview(url);
						}
					});
				}
			}, "web view loaded thread").start();
		} else {

			m_view.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					loadURLInWebview(url);
				}
			});
		}
	}

	public void loadURLInWebview(String url) {
		if (m_model.isPopupMode()) {
			m_model.getPopupWebViewFragment().loadUrl(url);
			showPopupWebView();
		} else {
			m_view.getLoadingView().setVisibility(View.GONE);
			MainModel.getInstance().getWebViewFragment().loadUrl(url);
			cancelMailSearch(null);
			showWebView();
		}
	}

	public void loadCustomURLInWebview(String url) {
		m_view.getCustomWebView().loadUrl(url);
		showCustomWebView();
	}

	public void hideWebviewToolbar() {
		ViewModel.Log(m_view.getActivity(), "tkofs_push");
		m_view.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (m_model.isPopupMode()) {
					if (m_model.getPopupWebViewFragment() != null && m_model.getPopupWebViewFragment().getWebviewToolBar() != null) {
						m_model.getPopupWebViewFragment().getWebviewToolBar().setVisibility(View.GONE);
					}
				} else {
					if (m_model.getWebViewFragment() != null && m_model.getWebViewFragment().getWebviewToolBar() != null) {
						m_model.getWebViewFragment().getWebviewToolBar().setVisibility(View.GONE);
					}
				}
			}
		});
	}

	public void showPopupWebView() {
		if (!m_model.isWebViewLoaded()) {// 웹뷰가 로딩되지 않았으면 무시
			PopupUtil.showToastMessage(m_view.getActivity(), R.string.message_loading_webview);
			return;
		}
		if (m_view.getPopupWebViewContainer().getVisibility() != View.VISIBLE) {
			m_view.getPopupWebViewContainer().setAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.popup_enter));
			m_view.getPopupWebViewContainer().setVisibility(View.VISIBLE);
		}
		m_model.getPopupWebViewFragment().getView().requestFocus();
	}

	public void loadPopupWebView(String url) {
		m_model.setPopupMode(true);
		loadWebviewWithDelay(url);
	}

	public void showWebView() {
		if (!m_model.isTablet() && !isWebViewShowing() && !m_model.isCallByMenu()) {
			m_view.getWebviewContainer().setAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.slide_show_left));
		}
		if (!isWebViewShowing()) {
			m_view.getWebviewContainer().setVisibility(View.VISIBLE);
		}
		closeMenu();
		if (!m_model.isTablet()) {
			m_view.getMiddleMenuContainer().postDelayed(new Runnable() {
				@Override
				public void run() {
					m_view.getMiddleMenuContainer().setVisibility(View.VISIBLE);
					MainModel.getInstance().getWebViewFragment().getView().requestFocus();
					// m_view.getWebView().getView().requestFocus();
				}
			}, 250);
		}
	}

	public boolean isWebViewShowing() {
		return m_view.getWebviewContainer() != null && m_view.getWebviewContainer().getVisibility() == View.VISIBLE;
	}

	public void showCustomWebView() {
		if (m_view.getCustomWebViewContainer().getVisibility() != View.VISIBLE) {
			m_view.getCustomWebViewContainer().setVisibility(View.VISIBLE);
		}
		closeMenu();
	}

	public void showImageDocViewer(String data) {
		closeMenu();
		m_model.setDocData(data);
		m_model.showSubActivity(m_view.getActivity(), SubActivityType.DOC_VIEWER,
				new BundleUtils.Builder().add(DocViewFragment.ARG_KEY_DATA, data).build());
	}

	public void showHtmlDocViewer(String title, String url) {
		closeMenu();
		m_model.setDocData(title);
		m_model.showSubActivity(m_view.getActivity(), SubActivityType.HTML_VIEWER,
				new BundleUtils.Builder().add(HtmlViewFragment.ARG_KEY_URL, url).add(HtmlViewFragment.ARG_KEY_TITLE, title).build());
	}

	public void showEmptyPage() {
		loadWebviewWithDelay("javascript:showEmptyPage();");
	}

	public void resetStaticValue() {
		m_model.setWebViewLoadedCnt(0);
		m_model.setCallByMenu(false);
		m_model.setWebViewLoaded(false);
		m_model.setUserActionOccurred(false);
		m_model.setPopupMode(false);
		m_model.setListViewLoaded(false);
		m_model.setFirstLoading(true);
		m_model.setWasProcessKilled(false);
		m_model.setApprovalArchiveMenu(false);
	}

	public void doLogout() {
		closeMenu();

		AndroidUtils.deleteSaveFolder(null);
		if (m_model.getWebViewFragment().getWebView() != null) {
			m_model.getWebViewFragment().getWebView().clearCache();
		}
		if (m_model.getPopupWebViewFragment().getWebView() != null) {
			m_model.getPopupWebViewFragment().getWebView().clearCache();
		}
		if (m_view.getCustomWebView() != null) {
			m_view.getCustomWebView().destroy();
		}
		m_model.getOpenApiService().logout();
		resetStaticValue();
		HMGWServiceHelper.resetLoginInfo();
		if (Define.USE_SSO) {
			m_view.getActivity().finish();
		} else {
			if(m_view.getActivity() != null){
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						m_model.showView(m_view.getActivity().getSupportFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOGIN,
								new BundleUtils.Builder().add(LoginFragment.ARG_KEY_IS_LOGOUT, true).build());
					}
				}, 300);
			}
		}
	}

	public void hideLoadingProgressDialog() {
		m_model.setListViewLoaded(true); // 리스트뷰가 로딩되었음
		if (m_model.isWebViewLoaded()) {
			if (m_model.isFirstLoading()) {
				m_model.setFirstLoading(false);
				hideWaitingProgressDialog();
				m_view.getProgressDialogWithCancel().setMessage(m_view.getString(R.string.message_notify_loading_data));
			} else {
				m_view.getProgressDialog().dismiss();
			}
		}
	}

	public void hideWaitingProgressDialog() {
		m_view.getProgressDialogWithCancel().dismiss();
	}

	public void forceLogout() {
		forceLogout(R.string.message_alert_loading_error);
	}

	public void forceLogout(int messageResource) {
		/*(new AlertDialog.Builder(m_view.getActivity())).setMessage(messageResource)
		.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				doLogout();
			}
		}).show();*/
		// tkofs 세션 종료시 컨펌창 무한증식 방지.
		if(HMGWServiceHelper.forceLogoutFlag){
			HMGWServiceHelper.forceLogoutFlag = false;
			if(MainModel.getInstance().getHomeFragment() != null && MainModel.getInstance().getHomeFragment().getActivity() != null){
				(new AlertDialog.Builder(MainModel.getInstance().getHomeFragment().getActivity())).setMessage(messageResource)
						.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								HMGWServiceHelper.forceLogoutFlag = true;
								doLogout();
								MainModel.getInstance().getHomeFragment().getActivity().finish();	// tkofs 세션 종료시(ajax call방식) home 이면 홈종료
							}
						}).show();
			}else{
				(new AlertDialog.Builder(m_view.getActivity())).setMessage(messageResource)
						.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								HMGWServiceHelper.forceLogoutFlag = true;
								doLogout();
							}
						}).show();
			}
		}
	}

	public void showLoadingProgressDialog() {
		ViewModel.Log("showLoadingProgressDialog " + m_model.isFirstLoading(), "tkofs_progresss");
		if (m_model.isFirstLoading()) {
			showWaitingProgressDialog(HMGWServiceHelper.LOADING_TIMEOUT);
			m_view.getProgressDialogWithCancel().setMessage(m_view.getResources().getString(R.string.ptr_refreshing));
		} else {
			m_view.getProgressDialog().setMessage(m_view.getResources().getString(R.string.ptr_refreshing));
			m_view.getProgressDialog().setCancelable(true);
			m_view.getProgressDialog().show();
		}
	}

	public void showWaitingProgressDialog(final int waitingTimeOut) {
		m_view.getProgressDialogWithCancel().setCancelable(true);
		m_view.getProgressDialogWithCancel().show();

		(m_view.getProgressDialogWithCancel().getButton(DialogInterface.BUTTON_NEGATIVE)).setEnabled(false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(waitingTimeOut * 1000);
				enableWaitingProgressDialogCancelButton();
			}
		}, "waiting sleep thread").start();
	}

	public void enableWaitingProgressDialogCancelButton() {
		if (m_view.getActivity() != null) {
			m_view.getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (m_view.getProgressDialogWithCancel().isShowing()) {
						(m_view.getProgressDialogWithCancel().getButton(DialogInterface.BUTTON_NEGATIVE)).setEnabled(true);
					}
				}
			});
		}
	}

	// 메일 검색 취소시 - 옵션, 버튼 숨기고 포커스 초기화하기
	// 메일 편집 시, 메뉴 선택 시, 검색취소버튼 클릭시 호출됨
	public void cancelMailSearch(View v) {
		boolean isSearchMode = false;
		if (m_view.getCurrentListviewAdapter() instanceof MailListAdapter) {
			MailListAdapter adapter = (MailListAdapter) m_view.getCurrentListviewAdapter();
			isSearchMode = adapter.isSearchMode();
		}
		if (v != null || !isSearchMode) {// 검색모드일때는 메일편집액션에 의해 검색이 초기화되지 않도록
			EditText editText = (EditText) m_view.getMainRightContent().findViewById(R.id.inputKeywordMail);
			editText.setText(null);
			InputMethodManager imm = (InputMethodManager) m_view.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
			editText.clearFocus();
			m_view.getMiddleMenuContainer().requestFocus();
			(m_view.getMailSearchMenu().findViewById(R.id.cancelSearchMailList)).setVisibility(View.GONE);
			(m_view.getMailSearchMenu().findViewById(R.id.mailSearchOptions)).setVisibility(View.GONE);

			if (isSearchMode) {// 검색모드였으면, 이전목록으로 돌아가기
				showMiddleMenuPrevPage();
			}
		}
	}

	public void checkPasswordForSign(final JSONObject item) {
		checkPasswordForSign(item, "");
	}

	public void checkPasswordForSign(String url) {
		checkPasswordForSign(null, url);
	}

	public void checkPasswordForSign(final JSONObject item, final String url) {// 암호 확인
		cancelMailSearch(null);
		AlertDialog.Builder builder;
		@SuppressLint("ResourceType") View dialog = LayoutInflater.from(m_view.getActivity()).inflate(R.layout.dialog_password,
				(ViewGroup) m_view.getActivity().findViewById(R.layout.main));
		builder = new AlertDialog.Builder(m_view.getActivity());
		final EditText inputBox = (EditText) dialog.findViewById(R.id.chekcPassword);
		inputBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					m_view.getPasswordDialogForSign().getWindow()
							.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				}
			}
		});
		builder.setView(dialog);
		if (!TextUtils.isEmpty(url))
			builder.setTitle(R.string.label_password_sign2);
		else
			builder.setTitle(R.string.label_password_sign);

		builder.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which){}
		});
		builder.setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {}
		});
		m_view.setPasswordDialogForSign(builder.create());
		m_view.getPasswordDialogForSign().show();
		m_view.getPasswordDialogForSign().getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {

				String password = (inputBox.getText()).toString();
				inputBox.setText(null);

				if (TextUtils.isEmpty(password)) {
					PopupUtil.showToastMessage(m_view.getActivity(), R.string.message_check_password_empty);
					return;
				}

				boolean isValid = m_model.getOpenApiService().checkPassword(m_view.getActivity(), ApiCode.check_sign_password, password);

				if(!isValid){
					PopupUtil.showToastMessage(m_view.getActivity(), R.string.message_check_sign_password_invalid);
					return;
				}

				if (!TextUtils.isEmpty(url)) {
					Debug.trace("isValid = " , isValid);
					loadWebviewWithDelay(url);
				}else {
					showSignDetails(item);
				}

				m_view.getPasswordDialogForSign().dismiss();
			}
		});
	}

	public void showContactDetail(String id, String type) {
		if (type == null) {
			loadWebviewWithDelay("javascript:showOrgUserDetails('" + id + "')");
		} else {
			loadWebviewWithDelay("javascript:showContactDetails('" + id + "','" + type + "')");
		}
	}

	public void showMailDetails(String link, String type, String password) {
		loadWebviewWithDelay("javascript:showMailDetails('" + type + "','" + link + "','" + password + "')");

		if (!TextUtils.equals("sendlist", type)) {
			if(m_view.getCurrentListviewAdapter()!=null)((MailListAdapter) m_view.getCurrentListviewAdapter()).setMailItemToRead();
		}
	}

	public void showMailDetails(String link, String type) {
		loadWebviewWithDelay("javascript:showMailDetails('" + type + "','" + link + "')");

		if (!TextUtils.equals("sendlist", type)) {
			if(m_view.getCurrentListviewAdapter()!=null)((MailListAdapter) m_view.getCurrentListviewAdapter()).setMailItemToRead();
		}
	}

	public void showBoardMemoDetails(String link, int cmntCnt, String deptId) {
		loadWebviewWithDelay("javascript:showBoardDetails('" + link + "'," + cmntCnt + ",'" + deptId +"')");
	}

	public void searchContact(TextView v) {
		RadioGroup options = (RadioGroup) m_view.getContactSearchMenu().findViewById(R.id.contactSearchOptions);
		String keyword = v.getText().toString();
		String option = null;
		if (TextUtils.isEmpty(keyword)) {
			PopupUtil.showToastMessage(m_view.getActivity(), R.string.label_mail_search_hint);
			return;
		} else {// 검색어가 있으면 검색수행
			switch (options.getCheckedRadioButtonId()) {
			case R.id.contactSearchOptionName: {
				option = "txtName";
				break;
			}
			case R.id.contactSearchOptionEmail: {
				option = "txtEmail";
				break;
			}
			case R.id.contactSearchOptionCompany: {
				option = "txtOfficeName";
				break;
			}
			case R.id.contactSearchOptionPhone: {
				option = "txtTelephone";
				break;
			}
			}
			ContactListAdapter adapter = (ContactListAdapter) m_view.getCurrentListviewAdapter();
			List<NameValuePair> formparams;
			formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("searchkey", option));
			formparams.add(new BasicNameValuePair("searchvalue", keyword));
			if(adapter != null){
				if (adapter.getApiCode() == ApiCode.contact_group) {
					formparams.add(new BasicNameValuePair("groupid", adapter.getGroupID()));
				}
				if (adapter.isSearchMode()) {
					adapter.setParameters(formparams);
					adapter.initData();
				} else {
					String title = m_model.getMiddleNavibarTitleStack().getTop() + ' ' + m_view.getString(R.string.label_search_contact);
					m_model.getMiddleNavibarTitleStack().push(title);
					(new ContactListAdapter(m_view, adapter.getApiCode(), 1, formparams, true)).setGroupID(adapter.getGroupID());
				}
			}
		}
		TextViewUtils.hideKeyBoard(m_view.getActivity(), v);
	}

	public void searchMail(TextView v) {
		RadioGroup options = (RadioGroup) m_view.getMailSearchMenu().findViewById(R.id.mailSearchOptions);

		String keyword = v.getText().toString().trim();
		String option = null;
		if (TextUtils.isEmpty(keyword)) {
			PopupUtil.showToastMessage(m_view.getActivity(), R.string.label_mail_search_hint);
			return;
		} else {// 검색어가 있으면 검색수행
			switch (options.getCheckedRadioButtonId()) {
				case R.id.mailSearchOptionUser: {
					option = "U";
					break;
				}
				case R.id.mailSearchOptionTitle: {
					option = "T";
					break;
				}
			}

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("searchkey", option));
			formparams.add(new BasicNameValuePair("searchvalue", keyword));

			MailListAdapter adapter = (MailListAdapter) m_view.getCurrentListviewAdapter();
			// 이미 "personalboxid"가 있으면 기존 것을 가지고 온다.. 검색할때 new Arraylist 해버리는
			// 바람에 기존에 Adapter에서 가지고 있던 파라메터가 날아감.
			if (adapter != null && adapter.getExtraParams() != null) {
				for (NameValuePair item : adapter.getExtraParams()) {
					if (TextUtils.equals(item.getName(), "personalboxid")) {
						formparams.add(new BasicNameValuePair("personalboxid", item.getValue()));
						break;
					}
				}
			}
			if (adapter != null && adapter.isSearchMode()) {
				adapter.setParameters(formparams);
				adapter.initData();
			} else {
				String title = m_model.getMiddleNavibarTitleStack().getTop() + " " + m_view.getString(R.string.label_search_mail);
				m_model.getMiddleNavibarTitleStack().push(title);
				new MailListAdapter(m_view, adapter.getApiCode(), 1, formparams, true);
			}
		}
		TextViewUtils.hideKeyBoard(m_view.getActivity(), v);
	}

	public void searchBoard(TextView v) {
		RadioGroup options = (RadioGroup) m_view.getBoardSearchMenu().findViewById(R.id.boardSearchOptions);

		String keyword = v.getText().toString().trim();
		String option = null;
		if (TextUtils.isEmpty(keyword)) {
			PopupUtil.showToastMessage(m_view.getActivity(), R.string.label_board_search_hint);
			return;
		} else {// 검색어가 있으면 검색수행
			switch (options.getCheckedRadioButtonId()) {
			case R.id.boardSearchOptionTitle: {
				option = "TITLE";
				break;
			}
			case R.id.boardSearchOptionPoster: {
				option = "POSTERNAME";
				break;
			}
			case R.id.boardSearchOptionDept: {
				option = "DEPTNAME";
				break;
			}
			}

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair(option, keyword));

			BoardAdapter adapter = (BoardAdapter) m_view.getCurrentListviewAdapter();
			// 이미 "personalboxid"가 있으면 기존 것을 가지고 온다.. 검색할때 new Arraylist 해버리는
			// 바람에 기존에 Adapter에서 가지고 있던 파라메터가 날아감.
			if (adapter != null && adapter.isSearchMode()) {
				adapter.setParameters(formparams);
				adapter.initData();
			} else {

				String title = m_model.getMiddleNavibarTitleStack().getTop() + " " + m_view.getString(R.string.label_search_board);
				m_model.getMiddleNavibarTitleStack().push(title);
				if(adapter!=null) new BoardAdapter(m_view, adapter.folderId, adapter.getApiCode(), false, true, adapter.brdType).setParameters(formparams);
			}
		}
		TextViewUtils.hideKeyBoard(m_view.getActivity(), v);
	}

	public void searchSign(TextView v) {
		RadioGroup options = (RadioGroup) m_view.getSignSearchMenu().findViewById(R.id.signSearchOptions);

		String keyword = v.getText().toString().trim();
		String option = null;
		if (TextUtils.isEmpty(keyword)) {
			PopupUtil.showToastMessage(m_view.getActivity(), R.string.label_sign_search_hint);
			return;
		} else {// 검색어가 있으면 검색수행
			switch (options.getCheckedRadioButtonId()) {
				case R.id.signSearchOptionTitle:
					option = "sj";
					break;

				case R.id.signSearchOptionDrafter:
					option = "reportr";
					break;
			}

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair(option, keyword));

			SignListAdapter adapter = (SignListAdapter) m_view.getCurrentListviewAdapter();
			// 이미 "personalboxid"가 있으면 기존 것을 가지고 온다.. 검색할때 new Arraylist 해버리는
			// 바람에 기존에 Adapter에서 가지고 있던 파라메터가 날아감.
			if (adapter != null && adapter.isSearchMode()) {
				adapter.setParameters(formparams);
				adapter.initData();
			} else {
				String title = m_model.getMiddleNavibarTitleStack().getTop() + " " + m_view.getString(R.string.label_search_sign);
				m_model.getMiddleNavibarTitleStack().push(title);
				if(adapter != null) new SignListAdapter(m_view, adapter.getApiCode(), 1, null,adapter.getAuthDeptId(),adapter.getAuthFldrId(), true).setParameters(formparams);
			}
		}
		TextViewUtils.hideKeyBoard(m_view.getActivity(), v);
	}


	public void cancelSignSearch(View v) {
		SignListAdapter adapter = null;
		boolean isSearchMode = false;

		adapter = (SignListAdapter) m_view.getCurrentListviewAdapter();
		if (adapter != null) {
			isSearchMode = adapter.isSearchMode();
		}

		EditText editText = (EditText) m_view.getMainRightContent().findViewById(R.id.inputKeywordSign);
		editText.getText().clear();
		TextViewUtils.hideKeyBoard(m_view.getActivity(), v);
		m_view.getMiddleMenuContainer().requestFocus();
		((Button) m_view.getSignSearchMenu().findViewById(R.id.cancelSearchSign)).setVisibility(View.GONE);
		(m_view.getSignSearchMenu().findViewById(R.id.signSearchOptions)).setVisibility(View.GONE);
		if (isSearchMode) {// 검색모드였으면, 이전목록으로 돌아가기
			showMiddleMenuPrevPage();
		}
	}


	// 메일 검색 취소시 - 옵션, 버튼 숨기고 포커스 초기화하기
	// 메일 편집 시, 메뉴 선택 시, 검색취소버튼 클릭시 호출됨
	public void cancelContactSearch(View v) {
		ContactListAdapter adapter = null;
		boolean isSearchMode = false;

		adapter = (ContactListAdapter) m_view.getCurrentListviewAdapter();
		if (adapter != null) {
			isSearchMode = adapter.isSearchMode();
		}

		EditText editText = (EditText) m_view.getMainRightContent().findViewById(R.id.inputKeywordContact);
		editText.getText().clear();
		TextViewUtils.hideKeyBoard(m_view.getActivity(), v);
		m_view.getMiddleMenuContainer().requestFocus();
		((Button) m_view.getContactSearchMenu().findViewById(R.id.cancelSearchContact)).setVisibility(View.GONE);
		(m_view.getContactSearchMenu().findViewById(R.id.contactSearchOptions)).setVisibility(View.GONE);
		if (isSearchMode) {// 검색모드였으면, 이전목록으로 돌아가기
			showMiddleMenuPrevPage();
		}
	}

	// 게시물 검색 취소시 - 옵션, 버튼 숨기고 포커스 초기화하기
		// 게시물 편집 시, 메뉴 선택 시, 검색취소버튼 클릭시 호출됨
		public void cancelBoardSearch(View v) {
			boolean isSearchMode = false;
			if (m_view.getCurrentListviewAdapter() instanceof BoardAdapter) {
				BoardAdapter adapter = (BoardAdapter) m_view.getCurrentListviewAdapter();
				isSearchMode = adapter.isSearchMode();
			}
			if (v != null || !isSearchMode) {// 검색모드일때는 메일편집액션에 의해 검색이 초기화되지 않도록
				EditText editText = (EditText) m_view.getMainRightContent().findViewById(R.id.inputKeywordBoard);
				editText.setText(null);
				InputMethodManager imm = (InputMethodManager) m_view.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
				editText.clearFocus();
				m_view.getMiddleMenuContainer().requestFocus();
				(m_view.getBoardSearchMenu().findViewById(R.id.cancelSearchBoard)).setVisibility(View.GONE);
				(m_view.getBoardSearchMenu().findViewById(R.id.boardSearchOptions)).setVisibility(View.GONE);

				if (isSearchMode) {// 검색모드였으면, 이전목록으로 돌아가기
					showMiddleMenuPrevPage();
				}
			}
		}

	// 중간 리스트 이전 화면으로
	public void showMiddleMenuPrevPage() {
		if (m_view.getMiddleMenuFlipper().getDisplayedChild() - 1 == 0) {
			(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.GONE);
		} else {
			(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.VISIBLE);
		}
		m_view.getMiddleMenuFlipper().setAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.slide_show_right));
		m_model.getMiddleNavibarTitleStack().pop();
		setMiddleMenuTitle(m_model.getMiddleNavibarTitleStack().getTop());
		m_view.getMiddleMenuFlipper().showPrevious();
		if(m_view.getCurrentListviewAdapter()!=null) m_view.getCurrentListviewAdapter().updateOtherViews();// 이전으로갈 땐 기타 화면
		hideListFooter();
		// 업데이트(글쓰기, 편집버튼 등)을
		// 어뎁터에서 처리
		m_view.getMiddleMenuFlipper().removeViewAt(m_view.getMiddleMenuFlipper().getDisplayedChild() + 1);
	}
	// 중간 리스트 이전 화면으로
	public void showSquareMiddleMenuPrevPage() {
		if (m_view.getMiddleMenuFlipper().getDisplayedChild() - 1 == 0) {
			(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.GONE);
		} else {
			(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.VISIBLE);
		}
		m_view.getMiddleMenuFlipper().setAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.slide_show_right));
		m_view.getMiddleMenuFlipper().showPrevious();
		m_view.getMiddleMenuFlipper().removeViewAt(m_view.getMiddleMenuFlipper().getDisplayedChild() + 1);
	}

	// 중간 리스트 다음 화면으로
	public void showMiddleMenuNextPage() {
		m_view.getMiddleMenuFlipper().setAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.slide_show_left));
		setMiddleMenuTitle(m_model.getMiddleNavibarTitleStack().getTop());
		m_view.getMiddleMenuFlipper().showNext();

		if (m_view.getMiddleMenuFlipper().getDisplayedChild() > 0) {
			MGWBaseAdapter adapter = m_view.getCurrentListviewAdapter();
			if (adapter !=null && adapter.isSearchMode()) {// 검색일땐 이전버튼 숨김
				(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.GONE);
			} else {
				(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.VISIBLE);
			}
		}
	}
	public void showSquareMiddleMenuNextPage() {
		m_view.getMiddleMenuFlipper().setAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), R.anim.slide_show_left));
		m_view.getMiddleMenuFlipper().showNext();

		if (m_view.getMiddleMenuFlipper().getDisplayedChild() > 0) {
			(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.VISIBLE);
		}
	}

	public void setMiddleMenuTitle(String title) {
		TextView view = m_view.getMiddleMenuContainer().findViewById(R.id.middleNavibarTitle);
		ViewModel.Log(view.getVisibility(), "tkofs_mail_middleMenuvisibleity");
		ViewModel.Log(view.getClass().getName(), "tkofs_mail_middleMenuvisibleity");
		view.setText(title);
	}

	public void showSignDetails(JSONObject item) {
		String link = (item.optString("link")).trim();
		String applID = MGWUtils.getSignApplIDByApiCode(ApiCode.valueOf(item.optString("apiCode"))); // 이건
																										// 결재
																										// api
																										// 종류에
																										// 따라
																										// 구분...
		String apprID = item.optString("guid");
		String participantApprType = item.optString("participantapprtype");
		String apprStatus = item.optString("apprstatus");
		String attrCnt = item.optString("attachcnt");
		String externalDocFlag = item.optString("externalDocFlag");
		String summaryDocFlag = item.optString("summaryDocFlag");
		String wordtype = item.optString("wordtype");

		String additionalOfficeUserId = item.optString("additionalOfficeUserId");
		String participantId = item.optString("participantid");
		String examId = item.optString("examid");
		String examDeptId = item.optString("examDeptId");
		String enforceType = item.optString("enforcetype");
		boolean commentCk = item.optString("statusimageEx").indexOf("opinion.gif") == -1 ? false : true;
		//MGW-2324 원기안문 의견 버튼 팝업 기능 개선 - 접수대기  recdocstatus = 2이면   commentCk값을 false 로 set
		if (ApiCode.valueOf(item.optString("apiCode")) == ApiCode.sign_receipt_wait) {
			if (item.has("recdocstatus") && item.optInt("recdocstatus") == 2) {
				commentCk = false;
			}
		}
		boolean postponeCk = item.optString("statusimage").charAt(0) == 'n';
		loadWebviewWithDelay("javascript:showSignDetails('" + link + "','" + applID + "','" + apprID + "','" + participantApprType + "','"
				+ apprStatus + "','" + attrCnt + "'," + commentCk + "," + postponeCk + ",'" + externalDocFlag + "','" + summaryDocFlag
				+ "','" + wordtype + "','" + additionalOfficeUserId + "','" + participantId + "',0,'"+ examId +"','"+ examDeptId +"','"+ enforceType +"')");
	}

	// 하위폴더 보기
	public void showBoardFolders(String folderId, String title, boolean getSubFolders, String brdType) {
		new BoardAdapter(m_view, folderId, ApiCode.board_folder, getSubFolders, false, brdType);
		m_model.getMiddleNavibarTitleStack().push(title);
	}

	// 부서게시판 보기
	public void showDeptFolders(String folderId, String title, boolean getSubFolders, String brdType) {
		new BoardAdapter(m_view, folderId, ApiCode.dept_board_folders, getSubFolders, false, brdType);
		m_model.getMiddleNavibarTitleStack().push(title);
	}

	// 부서게시물 보기
	public void showDeptBoardMemoList(String folderId, String deptId, String title, boolean getSubFolders, String brdType) {
		new BoardAdapter(m_view, folderId, ApiCode.dept_board_memolist, getSubFolders, false, brdType, deptId);
		m_model.getMiddleNavibarTitleStack().push(title);
	}

	// 개인편지함의 편지 보기
	public void showPersonalBoxMailList(String path, String title) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("personalboxid", path));
		m_model.getMiddleNavibarTitleStack().push(title);
		new MailListAdapter(m_view, ApiCode.mail_personallist, 1, formparams, false);
	}

	public void showContactGroupMemberList(String gid, String name) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("groupid", gid));
		m_model.getMiddleNavibarTitleStack().push(name);
		ContactListAdapter adapter = new ContactListAdapter(m_view, ApiCode.contact_group, 1, formparams, false);
		adapter.setGroupID(gid);
	}

	// 게시판 글쓰기
	public void setWriteButtonForBoard(final DataForWrite data) {
		m_view.getListFooter().setMode(FooterToolBarMode.THREE_BUTTON);
		m_view.getListFooter().setLeftButtonImage(R.drawable.style_btn_reload);
		m_view.getListFooter().setRightButtonImage(R.drawable.style_btn_write);
		m_view.getListFooter().setFooterToolbarListener(new IFooterToolBarListener() {
			@Override
			public void onFooterToolBarClick(ButtonPosition bp, View v) {
				switch (bp) {
				case CENTER:
					break;
				case LEFT:
					onRefreshCurrentList(v);
					break;
				case RIGHT:
					MainModel.getInstance().setPopupMode(true);
					String isDeptBoard = "7".equals(data.brdType) ? "true" : "false";
					String deptId = data.deptId;
					loadWebviewWithDelay("javascript:showWriteBoard('" + data.folderId + "','" + data.title + "','" + data.ANONBOARD + "','" + deptId + "')");
					break;
				}
			}
		});
	}

	// Footer의 리프레쉬
	// Flipper의 현재 디스플레이중인 리스트뷰의 init 함수를 호출하여 리프레쉬하기
	public void onRefreshCurrentList(View v) {
		OnScrollListView view = (OnScrollListView) m_view.getMiddleMenuFlipper().getChildAt(
				m_view.getMiddleMenuFlipper().getDisplayedChild());
		if (view == null) {
			m_view.getMenuListHelper().refreshContentsByPressedMenu(m_view.getPressedMenuView());
		} else {
			if (view.getMGWBaseAdapter().setLoadingInProgress(true)) {
				hideListFooter();
				view.setSelectionFromTop(0, 0);
				view.getMGWBaseAdapter().initData();
			}
		}
	}

	// listfooter는 setAdapter를 호출하기 전에 추가 해 놓고 사용하여야 문제가 없음
	// 갱신문제로 인해 showListFooter(), hideListFooter() 사용안함 - 2019.10.21
	public void showListFooter() {
//		if(m_view.getCurrentListviewAdapter()!=null) m_view.getCurrentListviewAdapter().getListView().showFooter(true);
	}

	public void hideListFooter() {
//		if(m_view.getCurrentListviewAdapter()!=null)m_view.getCurrentListviewAdapter().getListView().showFooter(false);
	}

	public void hideLoadingImage() {
		if (m_view.getLoadingView().getVisibility() != View.GONE) {
			if (m_model.isListViewLoaded()) {
				hideLoadingProgressDialog();
			}
			m_view.getLoadingView().startAnimation(AnimationUtils.loadAnimation(m_view.getActivity(), android.R.anim.fade_out));
			m_view.getLoadingView().setVisibility(View.GONE);
		}
	}

	public void showCurrentListFirstRow() {
		if(m_view.getCurrentListviewAdapter()!=null) m_view.getCurrentListviewAdapter().showFirstRow();
	}

	public void showPopupMenu(JSONArray data) {
		// [1,["댓글쓰기"],[false],["javascript:boardCommentAction('addof', '000000j53');"],"{{210,24}, {10,10}}"]
		JSONArray jarr;
		try {
			jarr = data.getJSONArray(1);
			ArrayList<String> menuItems = new ArrayList<String>();
			for (int i = 0; i < jarr.length(); ++i) {
				menuItems.add(jarr.getString(i));
			}
			JSONArray jarrAction = data.getJSONArray(3);
			final ArrayList<String> menuActions = new ArrayList<String>();
			for (int i = 0; i < jarrAction.length(); ++i) {
				menuActions.add(jarrAction.getString(i));
			}

			new AlertDialog.Builder(m_view.getActivity())
					.setItems(menuItems.toArray(new String[menuItems.size()]), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							loadURLInWebview(menuActions.get(which));
						}
					}).setNegativeButton(R.string.label_cancel, null).show();
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
	}

	public void showPasswordPopup(final JSONArray obj) {
		LinearLayout dlgLayout = (LinearLayout) LayoutInflater.from(m_view.getActivity()).inflate(R.layout.dialog_password, null);
		final EditText et = (EditText) dlgLayout.findViewById(R.id.chekcPassword);
		et.setHint(R.string.anonymous_password_hint);
		AlertDialog dlg = new AlertDialog.Builder(m_view.getActivity()).create();
		dlg.setTitle(R.string.anonymous_password_title);
		dlg.setButton(AlertDialog.BUTTON_POSITIVE, m_view.getString(R.string.message_confirm), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					String strJs = obj.getString(0);
					loadURLInWebview(strJs.replace("%s", et.getText().toString()));
					dialog.dismiss();
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}
			}
		});
		dlg.setView(dlgLayout);
		dlg.setButton(AlertDialog.BUTTON_NEGATIVE, m_view.getString(R.string.message_cancel), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dlg.show();
	}

	public void checkPasswordForMail(final String link, final String type) {// 암호
		// 확인
		cancelMailSearch(null);
		AlertDialog.Builder builder;
		@SuppressLint("ResourceType") View dialog = LayoutInflater.from(m_view.getActivity()).inflate(R.layout.dialog_password,
				(ViewGroup) m_view.getActivity().findViewById(R.layout.main));
		builder = new AlertDialog.Builder(m_view.getActivity());
		final EditText inputBox = (EditText) dialog.findViewById(R.id.chekcPassword);
		inputBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					m_view.getPasswordDialogForMail().getWindow()
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
					PopupUtil.showToastMessage(m_view.getActivity(), R.string.message_check_password_empty);
				} else {
					boolean isValid = m_model.getOpenApiService().checkPassword(m_view.getActivity(), ApiCode.check_login_password,
							password);
					if (isValid) {
						showMailDetails(link, type, password);
					} else {
						// Toast.makeText(mActivity,
						// R.string.message_check_login_password_invalid,
						// 0).show();
						PopupUtil.showDialog(m_view.getActivity(), R.string.message_check_login_password_invalid);
						hideWebview(true);
					}
					inputBox.setText(null);
				}
			}
		});
		builder.setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				inputBox.setText(null);
				hideWebview(true);
			}
		});
		m_view.setPasswordDialogForMail(builder.create());
		m_view.getPasswordDialogForMail().show();
	}

	public void hideWebview(boolean showAnimation) {
		if (MainModel.getInstance().isTablet()) {
			// loadingView.setVisibility(View.VISIBLE);//빈화면 이미지-테블릿에서는 웹뷰가 항상
			// 보이기 때문에
			m_view.getCustomWebViewContainer().setVisibility(View.GONE);
			return; // 테블릿이면 웹뷰 숨김 없음!
		}
		if (m_view.getMiddleMenuFlipper().getChildCount() == 0) {
			m_view.getDrawerMenu().closeDrawers();
		} else {
			if (m_view.getWebviewContainer().getVisibility() == View.VISIBLE && showAnimation) {
				m_view.getWebviewContainer().setAnimation(
						AnimationUtils.loadAnimation(m_view.getActivity(), android.R.anim.slide_out_right));
			}
			m_view.getWebviewContainer().setVisibility(View.GONE);
			m_view.getCustomWebViewContainer().setVisibility(View.GONE);
		}
	}

	public void completeAppr(Object obj) {
		if (m_view.getCurrentListviewAdapter() instanceof SignListAdapter) {
			((SignListAdapter) m_view.getCurrentListviewAdapter()).completeAppr(obj);
		}
	}

	public void deleteItemFromList(String id) {
		if(m_view.getCurrentListviewAdapter()!=null) m_view.getCurrentListviewAdapter().deleteItemByID(id);
	}

	// 메일리스트 편집
	// 또는 주소록 로컬주소록에 저장.
	public void editMailList(View v) {
		ImageView btn = (ImageView) m_view.getActivity().findViewById(R.id.middleEditMailList);
		if (m_view.getCurrentListviewAdapter() instanceof MailListAdapter) {
			MailListAdapter adapter = (MailListAdapter) m_view.getCurrentListviewAdapter();
			if (adapter.checkBoxVisibility == View.VISIBLE) {// 일반모드 이면,
				setMailSearchEnabled(true);
				showSearchMaillist(); // 검색 필드를 보이고
				m_view.getDeleteMailFooter().setVisibility(View.GONE); // 메일삭제
																		// 메뉴를
																		// 숨기고
				m_view.getSaveContactFooter().setVisibility(View.GONE); // 주소록
																		// 저장
																		// 메뉴도
																		// 숨기고
				m_view.getListFooter().setVisibility(View.VISIBLE); // 기본 하단메류를
																	// 보이고
				adapter.checkBoxVisibility = View.GONE; // 체크박스를 숨기고
				adapter.indicatorVisibility = View.VISIBLE; // 인디케이터를 보이고
				adapter.clearCheckBox(); // 체크박스를 초기화하고
				adapter.setEditMode(false); // 에디트모드를 false로 바꾸고
				btn.setImageResource(R.drawable.style_btn_edit); // 취소버튼을 편집버튼으로
																	// 바꾸고
				Debug.trace("#1");
				adapter.notifyDataSetChanged(); // 화면을 업데이트 한다.
			} else {// 편집모드 이면,
				setMailSearchEnabled(false);
				cancelMailSearch(null); // 검색모드였으면 취소하고
				m_view.getListFooter().setVisibility(View.GONE); // 기본 하단메뉴를 숨기고
				adapter.checkBoxVisibility = View.VISIBLE; // 체크박스를 보이고
				adapter.indicatorVisibility = View.GONE; // 인디케이터를 숨기고
				adapter.clearCheckBox();// 체크박스를 초기화하고
				adapter.setEditMode(true); // 에디트모드로 설정하고
				btn.setImageResource(R.drawable.style_btn_cancel); // 편집버튼을 취소로
																	// 바꾸고
				Debug.trace("#2");
				adapter.notifyDataSetChanged(); // 화면을 업데이트한다.
			}
		} else if (m_view.getCurrentListviewAdapter() instanceof ContactListAdapter) {
			ContactListAdapter adapter = (ContactListAdapter) m_view.getCurrentListviewAdapter();
			if (adapter.checkBoxVisibility == View.VISIBLE) {// 일반모드 이면,
				m_view.getDeleteMailFooter().setVisibility(View.GONE); // 메일삭제
																		// 메뉴를
																		// 숨기고
				m_view.getSaveContactFooter().setVisibility(View.GONE);// 주소록 저장
																		// 메뉴도
																		// 숨기고
				m_view.getListFooter().setVisibility(View.VISIBLE); // 기본 하단메류를
																	// 보이고
				adapter.checkBoxVisibility = View.GONE; // 체크박스를 숨기고
				adapter.indicatorVisibility = View.VISIBLE; // 인디케이터를 보이고
				adapter.clearCheckBox(); // 체크박스를 초기화하고
				adapter.setEditMode(false); // 에디트모드를 false로 바꾸고
				btn.setImageResource(R.drawable.style_btn_edit); // 취소버튼을 편집버튼으로
																	// 바꾸고
				Debug.trace("#3");
				adapter.notifyDataSetChanged(); // 화면을 업데이트 한다.
			} else {// 편집모드 이면,
				setMailSearchEnabled(false);
				cancelMailSearch(null); // 검색모드였으면 취소하고
				m_view.getListFooter().setVisibility(View.GONE); // 기본 하단메뉴를 숨기고
				adapter.checkBoxVisibility = View.VISIBLE; // 체크박스를 보이고
				adapter.indicatorVisibility = View.GONE; // 인디케이터를 숨기고
				adapter.clearCheckBox();// 체크박스를 초기화하고
				adapter.setEditMode(true); // 에디트모드로 설정하고
				btn.setImageResource(R.drawable.style_btn_cancel); // 편집버튼을 취소로
																	// 바꾸고
				Debug.trace("#4");
				adapter.notifyDataSetChanged(); // 화면을 업데이트한다.
			}
		}

	}

	public void squarePlusGroupMenu (View v) {
		PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
		popupMenu.getMenuInflater().inflate(R.menu.squareplus_folder_list, popupMenu.getMenu());
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.ID_SP_MENU_FORDER_CREATE:
					final EditText edit = new EditText(m_view.getActivity());
					DialogInterface.OnClickListener dlgClick = new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if (TextUtils.isEmpty(edit.getEditableText().toString())) {
								PopupUtil.showToastMessage(m_view.getActivity(), R.string.squareplus_folder_name_empty_message);
							} else {
								SpFolderCallBack spContentsCallBack = new SpFolderCallBack(m_view.getActivity(), SpFolderVO.class) {
									@Override
									public void callback(String url, JSONObject json, AjaxStatus status) {
										super.callback(url, json, status);
										Debug.trace(json.toString());
										if (this.item != null) {

											int index = m_view.getMenuListHelper().m_spFolderListData.size() - 1;
											Debug.trace("index = ", index);
											if (m_view.getMenuListHelper().m_spFolderListData.size() > 0)
												m_view.getMenuListHelper().m_spFolderListData.add(index, item);
											m_view.getMenuListHelper().m_lvSquareExpandableGroup.post(new Runnable() {
												@Override
												public void run() {
													m_view.getMenuListHelper().m_spFolderListAdapter.notifyDataSetChanged();

													for (int i = 0; i < m_view.getMenuListHelper().m_spFolderListData.size(); i++) {
														m_view.getMenuListHelper().m_lvSquareExpandableGroup.getRefreshableView()
																.expandGroup(i);
													}

													m_view.getMenuListHelper().m_lvSquareExpandableGroup.onRefreshComplete();
												}
											});
										}
									}
								};

								HashMap<String, String> params = new HashMap<>();
								params.put("folderName", edit.getEditableText().toString());
								new ApiLoaderEx<>(new SpAddFolderMySquareMenu(m_view.getActivity()), m_view.getActivity(),
										spContentsCallBack, params).execute();
							}
						}
					};
					PopupUtil.showInputDialog(m_view.getActivity(), m_view.getString(R.string.squareplus_folder_create_message), edit,
							dlgClick);

					break;
				case R.id.ID_SP_MENU_FORDER_MOD:
					m_model.showSubActivity(
							m_view.getActivity(),
							SubActivityType.SP_GROUP_FOLDER,
							new BundleUtils.Builder()
									.add(SpFolderSelectFragment.ARG_KEY_SP_SELECT_TYPE, SpFolderSelectFragment.SpSelectType.MODIFY)
									.add(SpFolderSelectFragment.ARG_KEY_SP_FOLDER_LIST, (Serializable)m_view.getMenuListHelper().m_spFolderListData)
									.build());
					break;
				case R.id.ID_SP_MENU_FORDER_DEL:
					m_model.showSubActivity(
							m_view.getActivity(),
							SubActivityType.SP_GROUP_FOLDER,
							new BundleUtils.Builder()
									.add(SpFolderSelectFragment.ARG_KEY_SP_SELECT_TYPE, SpFolderSelectFragment.SpSelectType.DELETE)
									.add(SpFolderSelectFragment.ARG_KEY_SP_FOLDER_LIST,(Serializable) m_view.getMenuListHelper().m_spFolderListData)
									.build());
					break;
				}
				return true;
			}
		});
		popupMenu.show();
	}

	public void setMailSearchEnabled(boolean enabled) {
		(m_view.getMailSearchMenu().findViewById(R.id.inputKeywordMail)).setEnabled(enabled);
		(m_view.getMailSearchMenu().findViewById(R.id.cancelSearchMailList)).setEnabled(enabled);
		(m_view.getMailSearchMenu().findViewById(R.id.mailSearchOptionTitle)).setEnabled(enabled);
		(m_view.getMailSearchMenu().findViewById(R.id.mailSearchOptionUser)).setEnabled(enabled);
	}

	public void showSearchMaillist() {
		// UI 테스트를 위해 주석처리 - if문
		if (TextUtils.equals("Y", HMGWServiceHelper.mail_search_use)) {
			m_view.getMailSearchMenu().setVisibility(View.VISIBLE);
		}
	}

	public void showSearchSignlist() {
		m_view.getSignSearchMenu().setVisibility(View.VISIBLE);
	}

	public void showSearchBoard(String folderId) {
		if (folderId != null)
			m_view.getBoardSearchMenu().setVisibility(View.VISIBLE);
		else
			m_view.getBoardSearchMenu().setVisibility(View.GONE);
	}

	public void setNavibarButton(ViewGroup v, JSONArray data) {
		LinearLayout btnContainer;
		int count = data.optInt(0);
		JSONArray labels = data.optJSONArray(1);
		JSONArray functions = data.optJSONArray(2);
		String position = data.optString(3);

		LinearLayout.LayoutParams lp = null;
		if (m_nNaviButtonMargin == 0) {
			m_nNaviButtonMargin = AQUtility.dip2pixel(v.getContext(), 5);
		}

		if (TextUtils.equals(position, "L")) {
			btnContainer = (LinearLayout) v.findViewById(R.id.navibarLeftButtons);
			if (m_naviLeftButtonLayoutParam == null) {
				m_naviLeftButtonLayoutParam = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

				m_naviLeftButtonLayoutParam.leftMargin = m_nNaviButtonMargin;
			}
			lp = m_naviLeftButtonLayoutParam;
		} else {
			btnContainer = (LinearLayout) v.findViewById(R.id.navibarRightButtons);
			if (m_naviRightButtonLayoutParam == null) {
				m_naviRightButtonLayoutParam = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				m_naviRightButtonLayoutParam.rightMargin = m_nNaviButtonMargin;
			}
			lp = m_naviRightButtonLayoutParam;
		}

		btnContainer.removeAllViews();
		//팝업웹뷰 상세보기(FCM) 백버튼 활성화
		if (count == 0 && m_model.isPopupMode() && m_model.isTablet()) {
			if (m_view.getArguments() != null) {
				String event = m_view.getArguments().getString(MainActivity.PUSH_MSG_EVENT);
				if (!TextUtils.isEmpty(event) && TextUtils.equals(position, "L")) {
					CustomImageButton btn = (CustomImageButton) LayoutInflater.from(m_view.getActivity()).inflate(
							R.layout.template_back_btn_hu, v, false);
				/*	btn.setImageResource(R.drawable.style_btn_back);
					btn.setId(R.id.btn_navi_back);*/
					btn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							try {
								hidePopupWebView(new JSONArray("[\"\",false]"));
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});

					btnContainer.addView(btn, 0);
				}
			}
		}
		else {
			for (int i = 0; i < count; i++) {
				if (TextUtils.equals(labels.optString(i), "MENU")
						|| TextUtils.equals(labels.optString(i), "BACK")) { // 예약된
					// 이미지
					// 버튼
					/*
					 * if (isTablet){ return; // 테블릿이면 '뒤로', '메뉴' 버튼 숨기기 }
					 */
					// tkofs kosmes 레이아웃 변경에 따른 소스 수정
					CustomImageButton btn = null;
					if (TextUtils.equals(labels.optString(i), "MENU")) {
						// SEOJAEHWA 팝업웹뷰에서 상세보기(FCM) 들어왔을 때 메뉴버튼은 비활성화
						if (m_model.isPopupMode()) {
							continue;
						}
						btn = (CustomImageButton) LayoutInflater.from(m_view.getActivity()).inflate(
								R.layout.template_menu_btn_hu, v, false);
						//btn.setImageResource(R.drawable.style_btn_menu);
					} else if (TextUtils.equals(labels.optString(i), "BACK")) {
						btn = (CustomImageButton) LayoutInflater.from(m_view.getActivity()).inflate(
								R.layout.template_back_btn_hu, v, false);
						//btn.setImageResource(R.drawable.style_btn_back);
						//btn.setId(R.id.btn_navi_back);
					}
					btn.setOnclickFunction(functions.optString(i));
					btn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (m_model.isPopupMode()) {
								MainModel.getInstance().getPopupWebViewFragment().loadUrl(((IFunctionAble) v).getOnclickFunction());
							} else {
								MainModel.getInstance().getWebViewFragment().loadUrl(((IFunctionAble) v).getOnclickFunction());
							}
							TextViewUtils.hideKeyBoard(v.getContext(), v);
						}
					});
					btnContainer.addView(btn, 0);
				} else { // 일반 버튼
					CustomTextButton btn = null;
					// tkofs
					if(TextUtils.equals(position, "L")){
						btn = (CustomTextButton) LayoutInflater.from(
								m_view.getActivity()).inflate(R.layout.template_l_button_hu,
								v, false);
					}else{
						btn = (CustomTextButton) LayoutInflater.from(
								m_view.getActivity()).inflate(R.layout.template_r_button_hu,
								v, false);
					}

					// Popup의 일반 버튼일 경우 왼쪽 첫번째 항목을 BackKey를 눌렀을 때 동작하게 하기 위해 ID를
					// 부여한다.
					if (MainModel.getInstance().isPopupMode() && TextUtils.equals(position, "L") && i == 0) {
						btn.setId(R.id.btn_navi_back);
					} else if (TextUtils.equals(labels.optString(i), m_view.getString(R.string.label_cancel))) {
						btn.setId(R.id.btn_navi_back);
					}

					btn.setText(labels.optString(i));
					btn.setOnclickFunction(functions.optString(i));
					btn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (m_model.isPopupMode()) {
								MainModel.getInstance().getPopupWebViewFragment().loadUrl(((IFunctionAble) v).getOnclickFunction());
							} else {
								MainModel.getInstance().getWebViewFragment().loadUrl(((IFunctionAble) v).getOnclickFunction());
							}
							TextViewUtils.hideKeyBoard(v.getContext(), v);
						}
					});
					btnContainer.addView(btn, 0);
				}
			}
		}
		btnContainer.setVisibility(View.VISIBLE);
	}

	public void setCustomWebviewNavibarTitle(String title) {
		((TextView) m_view.getActivity().findViewById(R.id.customNavibarTitle)).setText(title);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cancelSearchMailList:
			cancelMailSearch(v);
			break;
		case R.id.cancelSearchBoard:
			cancelBoardSearch(v);
			break;
		case R.id.cancelSearchContact:
			cancelContactSearch(v);
			break;
		case R.id.cancelSearchSign:
			cancelSignSearch(v);
			break;
		case R.id.selectedSignFilterName:
			onClickSignFilter(v);
			break;
		case R.id.btnDeleteMail:
			onDeleteMail(v);
			break;
		case R.id.btnReadMail:
			onReadMail(v);
			break;
		case R.id.btnSaveContact:
			onSaveContact(v);
			break;
		case R.id.customMiddleGoMainMenuButton:
			showMainMenu(v);
			break;
		case R.id.middleBackButton:
			goBackToPrev(v);
			break;
		case R.id.middleGoMainMenuButton:
			showMainMenu(v);
			break;
		case R.id.middleEditMailList:
			editMailList(v);
			break;
		case R.id.middleSpMenu:
			squarePlusGroupMenu(v);
			break;
		case R.id.profile_image:
            logout(v);
            break;
		/* tkofs case R.id.currentUserPhoto:
			logout(v);
			break;
		case R.id.changeUser:
			changeUser(v);
			break;*/
		case R.id.btnLogout:
			logout(v);
			break;
		}
	}

	public void onClickSignFilter(View v) {
		m_view.getSignFilter().show();
	}

	public void onDeleteMail(View v) {
		// 선택된 메일리스트 삭제
		OnScrollListView view = (OnScrollListView) m_view.getMiddleMenuFlipper().getChildAt(
				m_view.getMiddleMenuFlipper().getDisplayedChild());
		MGWBaseAdapter adapter = view.getMGWBaseAdapter();
		m_view.getProgressDialog().setMessage(m_view.getResources().getString(R.string.message_progressbar_deletemail));
		m_view.getProgressDialog().show();
		adapter.deleteSelectedItems();
	}

	public void onReadMail(View v) {
		OnScrollListView view = (OnScrollListView) m_view.getMiddleMenuFlipper().getChildAt(
				m_view.getMiddleMenuFlipper().getDisplayedChild());
		final MailListAdapter adapter = (MailListAdapter) view.getMGWBaseAdapter();
		new AlertDialog.Builder(m_view.getActivity()).setItems(new String[]{
				m_view.getActivity().getString(R.string.label_mark_read),
				m_view.getActivity().getString(R.string.label_mark_not_read)
				}, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				adapter.readSelectedItems(which);
			}
		}).show();
	}

	public void onSaveContact(View v) {
		// 선택된 주소록 로컬 주소록으로 저장
		OnScrollListView view = (OnScrollListView) m_view.getMiddleMenuFlipper().getChildAt(
				m_view.getMiddleMenuFlipper().getDisplayedChild());
		MGWBaseAdapter adapter = view.getMGWBaseAdapter();
		m_view.getProgressDialog().setMessage(m_view.getResources().getString(R.string.message_progressbar_savecontact));
		m_view.getProgressDialog().show();
		adapter.saveContactItems();
	}

	public void showMainMenu(View v) {
		m_view.toggleMenu();
	}

	public void goBackToPrev(View v) {
		m_view.getMailSearchMenu().setVisibility(View.GONE);// 뒤로갈땐 일단 메일검색뷰를
															// 숨기도록
		m_view.getBoardSearchMenu().setVisibility(View.GONE);// 뒤로갈땐 일단 게시판검색뷰를
		// 숨기도록
		m_view.getContactSearchMenu().setVisibility(View.GONE);// 뒤로갈땐 일단
																// 주소록검색뷰를 숨기도록
		if (m_view.getMiddleMenuFlipper().getDisplayedChild() == 0) {
			m_view.toggleMenu();
		} else {

			JSONObject menuData = (JSONObject) m_view.getPressedMenuView().getTag();
			String menuType = menuData.optString(m_view.getMenuListHelper().MENU_TYPE);

			if (!m_view.getMenuListHelper().CUSTOM_MENU_TYPE.equalsIgnoreCase(menuType)) { // 제품 메뉴이면
				String menuIDString = menuData.optString(m_view.getMenuListHelper().MENU_ID);
				MenuIDsMap menuID;
				menuID = MenuIDsMap.valueOf(menuIDString);
//				menuID = MenuIDsMap.mail_received;
				if (menuID == MenuIDsMap.square_plus_org_group) {
					showSquareMiddleMenuPrevPage();
				}
				else
					showMiddleMenuPrevPage();
			}
			else
				showMiddleMenuPrevPage();
		}
	}

	public void logout(View v) {
		doLogout();
	}

	public void changeUser(View v) {
		int userCnt = HMGWServiceHelper.additionalusers.length();
		final List<String> userNames = new ArrayList<String>();
		final List<String> userEmpcodes = new ArrayList<String>();
		final List<String> userIds = new ArrayList<String>();

		userNames.add(HMGWServiceHelper.deptName + "." + HMGWServiceHelper.name);
		userEmpcodes.add(HMGWServiceHelper.empcode);
		userIds.add(HMGWServiceHelper.userId);

		for (int i = 0; i < userCnt; i++) {
			userNames.add(((JSONObject) HMGWServiceHelper.additionalusers.opt(i)).optString("department") + "."
					+ ((JSONObject) HMGWServiceHelper.additionalusers.opt(i)).optString("name"));
			userEmpcodes.add(((JSONObject) HMGWServiceHelper.additionalusers.opt(i)).optString("empcode"));
			userIds.add(((JSONObject) HMGWServiceHelper.additionalusers.opt(i)).optString("id"));
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(m_view.getActivity());
		TextView title = new TextView(m_view.getActivity());
		title.setText(R.string.additionalofficer_account_title);
		title.setPadding(10, 10, 10, 10);
		title.setTextSize(23);
		title.setGravity(Gravity.CENTER);
		builder.setCustomTitle(title);
		builder.setSingleChoiceItems(userNames.toArray(new String[userCnt]), 0, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				boolean loginBool = false; //반복적인 로그인메서드 호출을 막기위한 로그인 카운트
				HMGWServiceHelper.selectUserId = userIds.get(item);
				String param_userId = HMGWServiceHelper.userId;
				String param_selectUserId = HMGWServiceHelper.selectUserId;
				String param_phone_uid = HMGWServiceHelper.deviceId;
				HMGWServiceHelper.userId = userIds.get(item);
				HMGWServiceHelper.uname = userNames.get(item);
				HMGWServiceHelper.empcode = userEmpcodes.get(item);

				List<NameValuePair> formparams = new ArrayList<NameValuePair>();

				if (HMGWServiceHelper.USE_CRYPTO) {
					param_userId = Crypto.getInstance().encrypt(param_userId);
					param_selectUserId = Crypto.getInstance().encrypt(param_selectUserId);
					param_phone_uid = Crypto.getInstance().encrypt(param_phone_uid);
					formparams.add(new BasicNameValuePair("crypto", "true"));
				}

				formparams.add(new BasicNameValuePair("userid", param_userId));
				formparams.add(new BasicNameValuePair("selectuserid", param_selectUserId));
				formparams.add(new BasicNameValuePair("phone_uid", param_phone_uid));

				HMGWServiceHelper.hasAdditionalOfficer = true;
				String responseString = null;
				for(int i=0; i < 5; i++){
					if(loginBool) break;
					responseString = m_model.getOpenApiService().login(formparams);
					loginBool = true;
				}
				try {
					JSONObject jObj = new JSONObject(responseString);
					HMGWServiceHelper.additionalusers = jObj.optJSONArray("additionalofficer");
					HMGWServiceHelper.userId = jObj.optString("id");
					HMGWServiceHelper.deptId = jObj.optString("deptid");
					HMGWServiceHelper.uname = jObj.optString("uname");
					HMGWServiceHelper.empcode = jObj.optString("empcode");
					HMGWServiceHelper.name = jObj.optString("name");
					HMGWServiceHelper.key = jObj.optString("key");
					HMGWServiceHelper.deptName = jObj.optString("deptname");
					HMGWServiceHelper.photoLink = jObj.optString("photo");

                    List<AuthListVO> authList = null;
                    if (jObj.has("authlist")) {
                        authList = new ArrayList<AuthListVO>();
                        JSONArray jarr = jObj.optJSONArray("authlist");
                        if (jarr != null) {
                            for (int i = 0; i < jarr.length(); ++i) {
                                authList.add(new AuthListVO(jarr.optJSONObject(i)));
                            }
                        }
                    }

                    if (jObj.has("appr.approval.enablereceiptdoc") && jObj.optBoolean("appr.approval.enablereceiptdoc")) {
						HMGWServiceHelper.appr_auth = true;
					}
					else {
						if (authList != null && authList.size() > 0) {
							Debug.trace("getVO().authList = " + authList.toString());
							Debug.trace("authList != null");
							HMGWServiceHelper.appr_auth = false;
							for(AuthListVO vo : authList) {  // 접수대기 메뉴 권한 설정 -권한이 있어야 결재대기 메뉴 보임
								if ("D1".equals(vo.auth) || "D6".equals(vo.auth)) {
									HMGWServiceHelper.appr_auth = true;
									break;
								}
							}
						}
						else {
							Debug.trace("authList == null");
							HMGWServiceHelper.appr_auth = false;
						}
					}
				} catch (JSONException e) {
					if (Debug.isDebug())
						Debug.trace(e.getMessage());
				}
				PopupUtil.showToastMessage(m_view.getActivity(), R.string.message_user_changed);
				m_view.setCurrentUserInfo();
				m_view.reCreateMenuContainer();

				final String prefKeyInitialMenu = MenuListHelper.PREF_KEY_INITIAL_MENU;
				final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(m_view.getActivity());
				String initMenuId = pref.getString(prefKeyInitialMenu, null);

				if (initMenuId == null) {
					// 시작메뉴 세팅 - 서버 설정
					m_view.getMenuListHelper().startMenuByID(HMGWServiceHelper.start_menu_id, HMGWServiceHelper.start_menu_title,
							HMGWServiceHelper.start_menu_url, HMGWServiceHelper.start_menu_custom_id);
				} else {
					m_view.getMenuListHelper().showMenuByID(initMenuId);
				}

//				m_view.initCountUpdater();
//				m_view.getMenuListHelper().refreshContentsByPressedMenu(m_view.getPressedMenuView());
				dialog.dismiss();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void closeMenu() {
		m_view.getDrawerMenu().closeDrawers();
	}

	public void toggMiddleleListView(View v) {
		ImageView listToggleButton = (ImageView) v;
		listToggleButton.setSelected(!listToggleButton.isSelected());
		if (listToggleButton.isSelected()) {
			m_view.getMiddleMenuContainer().setVisibility(View.GONE);
		} else {
			m_view.getMiddleMenuContainer().setVisibility(View.VISIBLE);
		}
	}

	// @Override
	// public boolean onKey(View v, int keyCode, KeyEvent event) {
	// if (event.getAction() == KeyEvent.ACTION_UP && keyCode ==
	// KeyEvent.KEYCODE_BACK) {
	// // switch (v.getId()) {
	// // case R.id.ID_CORDOVA_WEBVIEW:
	// // Debug.trace("메인 코도바뷰에서 BackKey 눌림");
	// // // WebViewContainer에서 Back키를 탐색하여 찾는다.
	// // View backButton =
	// // m_model.findBackButton(m_view.getWebviewContainer());
	// // if (backButton != null && backButton instanceof IFunctionAble) {
	// // MainModel.getInstance().getWebViewFragment().loadUrl(((IFunctionAble)
	// // backButton).getOnclickFunction());
	// // } else {
	// // m_view.getActivity().onBackPressed();
	// // }
	// // break;
	// // case R.id.ID_POPUP_CORDOVA_WEBVIEW:
	// // Debug.trace("팝업 코도바뷰에서 BackKey 눌림");
	// // View popupBackButton =
	// // m_model.findBackButton(m_view.getPopupWebViewContainer());
	// // if (popupBackButton != null && popupBackButton instanceof
	// // IFunctionAble) {
	// // m_model.getPopupWebViewFragment().loadUrl(((IFunctionAble)
	// // popupBackButton).getOnclickFunction());
	// // } else {
	// // m_view.getActivity().onBackPressed();
	// // }
	// // break;
	// // }
	// }
	// return false;
	// }

	public void setSearchObserver(WebView customWebView, Activity a) {
		customWebView.setWebViewClient(m_model.getSearchObserverClient(a));
	}

	public void setWebviewTabbar(JSONArray data) {
		// 샘플 데이타: [3, ["Btn1", "Btn2", "Btn3"], ["javascript:alert(1);",
		// "javascript:alert(2);", "javascript:alert(3);"]]
		m_model.setTabInitiation(true);
		CustomTabHost tabHost;
		if (MainModel.getInstance().isPopupMode()) {
			tabHost = m_model.getPopupWebViewFragment().getTabHost();
		} else {
			tabHost = m_model.getWebViewFragment().getTabHost();
		}

		if (tabHost.getTabWidget().getChildCount() > 0) {
			tabHost.setCurrentTab(0);
			tabHost.clearAllTabs();
		}
		int count = data.optInt(0);
		JSONArray labels = data.optJSONArray(1);
		JSONArray images = data.optJSONArray(2);
		JSONArray functions = data.optJSONArray(3);
		tabHost.setFunctions(functions);

		tabHost.getTabContentView().addView(LayoutInflater.from(m_view.getActivity()).inflate(R.layout.dummy, tabHost.getTabContentView(), false));

		for (int i = 0; i < count; i++) {
			String imageName;
			imageName = (images.optString(i)).split("\\.")[0].trim();
//			imageName = "ico_org";
			int imageId = m_view.getResources().getIdentifier("style_" + imageName, "drawable", m_view.getActivity().getPackageName());
			tabHost.addTab(createTab(tabHost, String.valueOf(i)));
			((ImageView)tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tab_layout_img)).setImageResource(imageId);	// tkofs 탭디자인 수정.
			//tabHost.addTab(createTab(tabHost, String.valueOf(i), labels.optString(i), imageId)); // tkofs 원래 탭
		}

		/*	tkofs 탭 디자인 변경
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.style_tab_background);
			tabHost.getTabWidget().setStripEnabled(true);
			tabHost.getTabWidget().setRightStripDrawable(R.drawable.tab_bottom);
			tabHost.getTabWidget().setLeftStripDrawable(R.drawable.tab_bottom);
			TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			tv.setTextSize(14);
            tv.setTypeface(null, Typeface.BOLD);
            
			if (MainModel.getInstance().isTablet()) {
				tv.setPadding(0, 0, 0, 5);
			} else {
				tv.setPadding(0, 0, 0, 15);
			}
			tv.setTextColor(m_view.getResources().getColor(R.color.menu_text_color_up));
		}*/

		tabHost.setCurrentTab(m_model.getLastSelectedTabIndex());
		tabHost.setVisibility(View.VISIBLE);
		m_model.setTabInitiation(false);
	}

	// tkofs 탭디자인 변경에 따른 추가
	private TabSpec createTab(CustomTabHost tabHost, final String tag) {
		View widgetView = LayoutInflater.from(m_view.getActivity()).inflate(R.layout.style_tabwidget_hu, null);
		return tabHost.newTabSpec(tag).setIndicator(widgetView).setContent(R.id.dummy_view);
	}

	private TabSpec createTab(CustomTabHost tabHost, final String tag, final String title, final int drawable) {
		return tabHost.newTabSpec(tag).setIndicator(title, m_view.getResources().getDrawable(drawable)).setContent(R.id.dummy_view);
	}

	public void updateLastUpdatedText(FooterToolBar footerToolBar, String date) {
		footerToolBar.setText(date);
	}

	public void updateDeleteMailCount(final View deleteMailFooter, final int count) {
		m_view.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (count == 0) {
					deleteMailFooter.setVisibility(View.GONE);
				} else {
					@SuppressLint("StringFormatMatches") String label = String.format(m_view.getString(R.string.label_delete_maillist), count);
					((Button) deleteMailFooter.findViewById(R.id.btnDeleteMail)).setText(label);
					@SuppressLint("StringFormatMatches") String readMailLabel = String.format(m_view.getString(R.string.label_read_maillist), count);
					((Button) deleteMailFooter.findViewById(R.id.btnReadMail)).setText(readMailLabel);
					if (deleteMailFooter.getVisibility() == View.GONE) {
						deleteMailFooter.setVisibility(View.VISIBLE);
					}
				}
			}
		});
	}

	public void addViewIntoMiddleFlipper(ArrayList<Object> objs) {
		OnScrollListView listView = (OnScrollListView) objs.get(0);
		final MGWBaseAdapter adapter = (MGWBaseAdapter) objs.get(1);
		m_view.getMiddleMenuFlipper().addView(listView);
		adapter.notifyDataSetChanged();
		adapter.setLoadingFinished();
		hideWebview(false);
		closeMenu();
		showMiddleMenuNextPage();
	}

	public void updateListviewByAdapter(MGWBaseAdapter adapter) {
		adapter.notifyDataSetChanged();
		adapter.setLoadingFinished();
	}

	// 메일 쓰기
	public void setWriteButtonForMail() {
		MainFragment.mListFooter.setMode(FooterToolBarMode.THREE_BUTTON);
		MainFragment.mListFooter.setLeftButtonImage(R.drawable.style_btn_reload);
		MainFragment.mListFooter.setRightButtonImage(R.drawable.style_btn_write);
		MainFragment.mListFooter.setVisibility(View.VISIBLE);
		MainFragment.mListFooter.setFooterToolbarListener(new IFooterToolBarListener() {
			@Override
			public void onFooterToolBarClick(ButtonPosition bp, View v) {
				switch (bp) {
				case CENTER:
					break;
				case LEFT:
					onRefreshCurrentList(v);
					break;
				case RIGHT:
					closeMenu();
					MainModel.getInstance().showSubActivity(m_view.getActivity(), SubActivityType.MAIL_WRITE, null);
					break;
				}
			}
		});
	}

	public void hideWriteButton() {
		MainFragment.mListFooter.hideRightButton();
	}

	public void initEditMailButton() {
		ImageView btn = (ImageView) MainFragment.mainRightContent.findViewById(R.id.middleEditMailList);
		btn.setImageResource(R.drawable.style_btn_edit);
		btn.setVisibility(View.VISIBLE);
	}

	// 탑바의 편집 버튼은 메일에서 사용 하는것을 같이 사용한다.
	public void hideEditMailButton() {
		MainFragment.mainRightContent.findViewById(R.id.middleEditMailList).setVisibility(View.GONE);
	}



	//부서 권한 목록 관련 메소드
	public void initSendingDeptButton(ArrayList<JSONObject> deptArrList,int selectedPosition) throws JSONException {

		DeptSpinnerAdapter deptSpinnerAdapter;

		ArrayList<DeptVO> list = new ArrayList<>();
		for (int i = 0; i < deptArrList.size(); i++) {
			Debug.trace(" 부서명 : " + deptArrList.get(i).getString("deptName"));
			list.add(new DeptVO(deptArrList.get(i).getString("deptName"),deptArrList.get(i).getString("deptFullName"),deptArrList.get(i).getString("deptID"),deptArrList.get(i).getString("fldrID")));
		}
		final Spinner deptSpinner = (Spinner) MainFragment.mainRightContent.findViewById(R.id.middleSendingDept);
		deptSpinnerAdapter = new DeptSpinnerAdapter(m_view.getActivity(), list);
		deptSpinner.setSelection(selectedPosition);
		deptSpinner.setVisibility(View.VISIBLE);
		deptSpinner.setAdapter(deptSpinnerAdapter);

		slistener = new AdapterView.OnItemSelectedListener() {
	        @Override
	        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
				DeptVO deptVO = (DeptVO)adapterView.getSelectedItem();
				deptSpinner.setSelection(adapterView.getSelectedItemPosition());
				SignListAdapter adapter = (SignListAdapter) m_view.getCurrentListviewAdapter();
				adapter.setMaxPno(-1);
				adapter.setInitPno(1);
				adapter.setAuthDeptId(deptVO.getDeptID());
				adapter.setAuthFldrId(deptVO.getFldrID());
				adapter.initData();
	        }

	        @Override
	        public void onNothingSelected(AdapterView<?> adapterView) {

	        }
	    };

		deptSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
				deptSpinner.setOnItemSelectedListener(slistener);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}

	public void hideSendingDeptButton(){
		MainFragment.mainRightContent.findViewById(R.id.middleSendingDept).setVisibility(View.GONE);
	}
	public void initSpMenuButton() {
		ImageView btn = (ImageView) MainFragment.mainRightContent.findViewById(R.id.middleSpMenu);
		btn.setImageResource(R.drawable.btn_more_menu_set);
		btn.setVisibility(View.VISIBLE);
	}

	// 탑바의 편집 버튼은 메일에서 사용 하는것을 같이 사용한다.
	public void hideSpMenuButton() {
		MainFragment.mainRightContent.findViewById(R.id.middleSpMenu).setVisibility(View.GONE);
	}

	public void updateSearchOptionLabel(int optionID, int resID) {
		((RadioButton) MainFragment.mMailSearchMenu.findViewById(optionID)).setText(resID);
	}

	public void updateMainMenuCounts(JSONObject counts) {
		if (counts == null) {
			return;
		}

		if (m_view == null || m_view.getActivity() == null || m_view.getActivity().getApplicationContext() == null)
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

		Debug.trace("mailNew : " + mailNew + "mailUnread : " + mailUnread + "mtrlNew : " + mtrlNew + "apprWait : " + apprWait
				 + "approving : " + approving + "publicWait : " + publicWait + "recvWait : " + recvWait + "receiving : " + receiving
				 + "resCount : " + resCount + "schToday : " + schToday + "schShare : " + schShare + "squareNew : " + squareNew);
		// tkofs 뱃지 표시 여
		m_view.getMenuListHelper().setMenuCount(MenuIDsMap.mail_received.toString(), mailUnread);
		// m_view.getMenuListHelper().setMenuCount(MenuIDsMap.board_recent.toString(), mtrlNew); // tkofs 게시글 카운트 제거
		m_view.getMenuListHelper().setMenuCount(MenuIDsMap.gongram_waiting.toString(), publicWait);
		m_view.getMenuListHelper().setMenuCount(MenuIDsMap.approval_waiting.toString(), apprWait);
		m_view.getMenuListHelper().setMenuCount(MenuIDsMap.approval_receipt_waiting.toString(), recvWait);
		m_view.getMenuListHelper().setMenuCount(MenuIDsMap.square_plus_my_group.toString(), squareNew);


		if (Define.USE_BADGE) {
            int badgeCount = 0;
            if (!TextUtils.isEmpty(HMGWServiceHelper.noti_mps_badge)) {
                int value = Integer.valueOf(HMGWServiceHelper.noti_mps_badge);
                ViewModel.Log(value, "tkofs_badge");
                if((value & BadgeUtil.FLAG_MAIL_UNREAD) != 0 && !TextUtils.isEmpty(mailUnread)){
                    badgeCount += Integer.valueOf(mailUnread);
                }
                if((value & BadgeUtil.FLAG_APPR_WAIT) != 0 && !TextUtils.isEmpty(apprWait)){
                    badgeCount += Integer.valueOf(apprWait);
                }
                if((value & BadgeUtil.FLAG_MTRL_NEW) != 0 && !TextUtils.isEmpty(mtrlNew)){
                    badgeCount += Integer.valueOf(mtrlNew);
                }
            }else{
                if(!TextUtils.isEmpty(mailUnread)){
                    badgeCount += Integer.valueOf(mailUnread);
                }
                if(!TextUtils.isEmpty(apprWait)){
                    badgeCount += Integer.valueOf(apprWait);
                }
                if(!TextUtils.isEmpty(mtrlNew)){
                    badgeCount += Integer.valueOf(mtrlNew);
                }
            }
            BadgeUtil.updateCount(m_view.getActivity().getApplicationContext(), badgeCount);
		}
	}




	public void showAlertErrorMsg(String s) {
		if (TextUtils.equals(s, m_view.getString(R.string.error_session_expired))
				|| TextUtils.equals(s, m_view.getString(R.string.error_access_denied))) {
			PopupUtil.showDialog(m_view.getActivity(), s, m_logoutDialogListener);
		} else {
			PopupUtil.showDialog(m_view.getActivity(), s);
		}
	}

	public void showSearchContact() {
		m_view.getContactSearchMenu().setVisibility(View.VISIBLE);
	}

	public void showSignFilter() {
		(m_view.getMiddleMenuContainer().findViewById(R.id.signFilterContainer)).setVisibility(View.GONE);
		// if (!("html".equals(HMGWServiceHelper.sign_doctype) ||
		// "hwp8".equals(HMGWServiceHelper.sign_doctype))) {
		// ((View)
		// middleMenuContainer.findViewById(R.id.signFilterContainer)).setVisibility(View.VISIBLE);
		// }
	}

	public void checkDeletedMailItems(ArrayList<JSONObject> items) {
		((OnScrollListView) m_view.getMiddleMenuFlipper().getChildAt(m_view.getMiddleMenuFlipper().getDisplayedChild() - 1))
		.getMGWBaseAdapter().isPreventSearchDeleted = true;

		((OnScrollListView) m_view.getMiddleMenuFlipper().getChildAt(m_view.getMiddleMenuFlipper().getDisplayedChild() - 1))
				.getMGWBaseAdapter().deleteItemsFromList(items);
	}

	public void setNavibarButtonState(JSONArray data) {

		LinearLayout btnContainer;
		int count = data.optInt(0);
		JSONArray states = data.optJSONArray(1);
		String position = data.optString(2);

		if (TextUtils.equals("L", position)) {
			btnContainer = (LinearLayout) m_view.getMainRightContent().findViewById(R.id.navibarLeftButtons);
		} else {
			btnContainer = (LinearLayout) m_view.getMainRightContent().findViewById(R.id.navibarRightButtons);
		}

		for (int i = 0; i < count; i++) {
			boolean state = states.optBoolean(i);
			if (btnContainer.getChildAt(i) != null) {
				if (state) {
                    btnContainer.getChildAt(i).setVisibility(View.VISIBLE);
                } else {
                    btnContainer.getChildAt(i).setVisibility(View.GONE);
                } // 지금은 상태를 그냥
				// disable 하지만
				// 이미지 버튼의 경우
				// disable 상태
				// 이미지가 필요하고,
				// 그것으로 대체해야함.
			}
		}
	}

	/*
	 * updateHandler에서 호출하는 메소드
	 */

	// 주소록 저장 추가.
	public void updateSaveContactCount(int count) {
		if (count == 0) {
			m_view.getSaveContactFooter().setVisibility(View.GONE);
		} else {
			@SuppressLint("StringFormatMatches") String label = String.format(m_view.getString(R.string.label_save_contact), count);
			((Button) m_view.getSaveContactFooter().findViewById(R.id.btnSaveContact)).setText(label);
			if (m_view.getSaveContactFooter().getVisibility() == View.GONE) {
				m_view.getSaveContactFooter().setVisibility(View.VISIBLE);
				m_view.getSaveContactFooter().bringToFront();
			}
		}
	}

	// 첨부 다운로드
	public void onDownloadStart(String url, String fileName) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra("url", url);
		intent.putExtra("fileName", fileName);
		intent.setClass(m_view.getActivity(), DownloadActivity.class);
		m_view.getActivity().startActivity(intent);
	}

	// URL 보기
	public void showURL(String url) {
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		m_view.getActivity().startActivity(i);
	}

	public void setWebviewNavibarTitle(String s) {
		if (m_model.isPopupMode()) {
			m_model.getPopupWebViewFragment().getTvNaviBarTitle().setText(s);
		} else {
			m_model.getWebViewFragment().getTvNaviBarTitle().setText(s);
		}
	}

	public void setRefreshForListFooter() {
		// TODO Auto-generated method stub
		m_view.getListFooter().setLeftButtonImage(R.drawable.style_btn_reload);
		m_view.getListFooter().setFooterToolbarListener(new IFooterToolBarListener() {
			@Override
			public void onFooterToolBarClick(ButtonPosition bp, View v) {
				switch (bp) {
				case CENTER:
					break;
				case LEFT:
					onRefreshCurrentList(v);
					break;
				case RIGHT:
					break;
				}
			}
		});
	}
	
	public void statusNot200(){
        PopupUtil.showToastMessage(m_view.getActivity(), R.string.error_connect_timeout);
    }
}
