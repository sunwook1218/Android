package com.hs.mobile.gw.plugin;

import java.util.ArrayList;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.Views;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.fragment.login.LoginFragment;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;

import android.os.Handler;
import android.text.TextUtils;

public class HMGWPlugin extends CordovaPlugin {
	public static interface IPluginListener {
		void onEventFromWeb(actionCode ac, Object obj);
	}

	private static ArrayList<IPluginListener> mListenerList = new ArrayList<HMGWPlugin.IPluginListener>();

	public static void addListener(IPluginListener listener) {
		if (!mListenerList.contains(listener)) {
			mListenerList.add(listener);
			Debug.trace("Added Listener->", listener.getClass().getName());
		} else {
			Debug.trace("This listener was already added.");
		}
	}

	public static void removeListener(IPluginListener listener) {
		if (mListenerList.contains(listener)) {
			mListenerList.remove(listener);
			Debug.trace("Remove Listener->", listener.getClass().getName());
		} else {
			Debug.trace("That listener wasn't contained to List.");
		}
	}

	private void fireEvent(actionCode ac, Object obj, final CallbackContext callbackId) {
		boolean eventCatch = false; // 20.06.23 tkofs
        for (IPluginListener listener : mListenerList) {
            listener.onEventFromWeb(ac, obj);
            if(ac.equals(ac)) eventCatch = true; // 20.06.23 tkofs
        }
        // 20.06.23 tkofs webview call 이벤트 처리 안된거 에러 처리
        if(!eventCatch) callbackId.error(HMPPluginResult.Status.INVALID_ACTION.toString());
	}

	public enum actionCode {
		sessioninfo, 
		showSidebarMenu, 
		popView, 
		showLocalViewer, 
		showURL, 
		showLoginView, 
		showPhotoCamera, 
		setTitle, 
		setLeftBarButton, 
		setRightBarButton, 
		getImageDocViewerData, 
		setToolBarButton, 
		hideToolBar, 
		closePopupViewer, 
		setTabBarButton, 
		hideTabBar, 
		deleteMail, 
		deleteBoardMtrl, 
		completeAppr, 
		showWebView, 
		showPopupViewer, 
		notiLoadingCompleted, 
		notiLoadingError, 
		showWaitingView, 
		closeWaitingView, 
		setStateLeftBarButton, 
		setStateRightBarButton, 
		showHtmlDocViewer,
		setDocViewerHeader, 
		setDocViewerPageNo, 
		showImageDocViewer, 
		showDocViewerHeader, 
		closeImageDocViewer, 
		hideDocViewerHeader, 
		setSelectedTabBarItem, 
		showWriteComment, 
		showMenuItem, 
		showPopupMenu, 
		showCheckPasswdAnoBoard, 
		returnOrgSelect, 
		showMailEditorView, 
		onBackKeyPressed,
		menu_show_all, // 20.06.23 tkofs 전체메뉴 
        closeMenuAllPopupViewer, // 전체메뉴닫고메뉴이동
        getAppServerIP
	}

	@Override
	public boolean execute(final String action, final JSONArray data, final CallbackContext callbackId) throws JSONException {
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Debug.trace("HMGWPlugin - action[" + action + "]");
				actionCode ac = actionCode.valueOf(action);
				switch (ac) {
				case setSelectedTabBarItem:
					MainModel.getInstance().setLastSelectedTabIndex(data.optInt(0));
					callbackId.success();
					break;
				case setDocViewerHeader: {
					if (MainModel.getInstance().getDocViewer() != null) {
						MainModel.getInstance().getDocViewer().setDocViewerHeader(data.optString(0), Integer.parseInt(data.optString(1)));
					}
					callbackId.success();
					break;
				}
				case setDocViewerPageNo: {
					MainModel.getInstance().getDocViewer().setDocViewerCurrentPage(data.optInt(0));
					callbackId.success();
					break;
				}
				case showDocViewerHeader: {
					MainModel.getInstance().getDocViewer().showDocViewerHeader();
					callbackId.success();
					break;
				}
				case hideDocViewerHeader: {
					MainModel.getInstance().getDocViewer().hideDocViewerHeader();
					callbackId.success();
					break;
				}
				case showWaitingView: {
					// 샘플
					// webview.loadUrl("javascript:HMPHybrid.core.exec(function(){},function(){},'HMGWPlugin','showWaitingView',[10]);");
					fireEvent(ac, data.optInt(0), callbackId);
					callbackId.success();
					break;
				}
				case closeWaitingView: {
					fireEvent(ac, null, callbackId);
					callbackId.success();
					break;
				}
				case notiLoadingError: {
					fireEvent(ac, null, callbackId);
					callbackId.success();
					break;
				}
				case notiLoadingCompleted: {
					fireEvent(ac, null, callbackId);
					callbackId.success();
					break;
				}
				case showWebView: {
					fireEvent(ac, null, callbackId);
					callbackId.success();
					break;
				}
				case completeAppr: {
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case deleteBoardMtrl:
				case deleteMail: {
					String id = (String) data.opt(0);
					fireEvent(ac, id, callbackId);
					callbackId.success();
				}
					break;
				case sessioninfo: {
					JSONObject sessionInfo = new JSONObject();
					try {
						if (TextUtils.isEmpty(HMGWServiceHelper.OpenAPI.SERVER_URL)) {
							new HMGWServiceHelper.OpenAPI();
						}
						sessionInfo.put("id", HMGWServiceHelper.userId);
						sessionInfo.put("empcode", HMGWServiceHelper.empcode);
						sessionInfo.put("deptid", HMGWServiceHelper.deptId);
						sessionInfo.put("name", HMGWServiceHelper.name);
						sessionInfo.put("uname", HMGWServiceHelper.uname);
						sessionInfo.put("isadditionalofficer", HMGWServiceHelper.hasAdditionalOfficer);
						sessionInfo.put("start.menu.id", HMGWServiceHelper.start_menu_id);
						sessionInfo.put("isguest", HMGWServiceHelper.isGuest);
						sessionInfo.put("mail.search.use", HMGWServiceHelper.mail_search_use);
						sessionInfo.put("serverurl", HMGWServiceHelper.OpenAPI.SERVER_URL);
						sessionInfo.put("devicetype", HMGWServiceHelper.devicetype);
						sessionInfo.put("config", HMGWServiceHelper.config);
						callbackId.success(sessionInfo);
					} catch (JSONException e) {
						callbackId.error("잘못된 Action입니다.");
					}
				}
					break;
				case closePopupViewer: {
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case showPopupViewer: {
					fireEvent(ac, data.optString(0), callbackId);
					callbackId.success();
				}
					break;
				case popView: {
					// Message msg = new Message();
					// msg.what = MainActivity.HIDE_WEBVIEW;
					// MainActivity.mViewUpdateHandler.sendMessage(msg);
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case showSidebarMenu: {
					fireEvent(ac, null, callbackId);
					callbackId.success();
				}
					break;
				case showLocalViewer: {
					String url = "";
					String filename = "";

					try {
						url = (String) data.get(0);
						filename = (String) data.get(1);
					} catch (JSONException e) {
						callbackId.error(HMPPluginResult.Status.INVALID_ACTION.toString());
						// return result;
					}
					Debug.trace("HMGWPlugin - showAttach filename[" + filename + "] url[" + url + "]");
					MainFragment.getController().onDownloadStart(url, filename);
					callbackId.success();
				}
					break;
				case showURL: {
					String url = "";
					try {
						url = (String) data.get(0);
						Debug.trace("HMGWPlugin - showURL url[" + url + "]");
						MainFragment.getController().showURL(url);
					} catch (JSONException e) {
						callbackId.error(HMPPluginResult.Status.INVALID_ACTION.toString());
					}
					callbackId.success();
				}
					break;
				case showLoginView: {
					MainModel.getInstance().showView(MainModel.getInstance().getFragmentManager(), R.id.ID_LAY_L_STAGE, Views.LOGIN,
							new BundleUtils.Builder().add(LoginFragment.ARG_KEY_IS_LOGOUT, true).build());
					callbackId.success();
				}
					break;
				case showPhotoCamera: {
				}
					break;
				case setTitle: {
					try {
						fireEvent(ac, data.get(0), callbackId); // tkofs
					} catch (JSONException e) {
						callbackId.error(HMPPluginResult.Status.INVALID_ACTION.toString());
					}
					callbackId.success();
				}
					break;
				case setStateLeftBarButton:
				case setStateRightBarButton: {
					try {
						if (ac == actionCode.setStateRightBarButton) {
							data.put(2, "R");
						} else {
							data.put(2, "L");
						}
						MainFragment.getController().setNavibarButtonState(data);

					} catch (JSONException e) {
						callbackId.error(HMPPluginResult.Status.INVALID_ACTION.toString());
					}
					callbackId.success();
				}
					break;
				case setLeftBarButton:
				case setRightBarButton: {
					try {
						if (ac == actionCode.setRightBarButton) {
							data.put(3, "R");
						} else {
							data.put(3, "L");
						}
						fireEvent(ac, data, callbackId);
					} catch (JSONException e) {
						callbackId.success();
					}
					callbackId.success();
				}
					break;
				case setToolBarButton:// 툴바 설정
				{
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case hideToolBar: {
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case setTabBarButton:// 탭바 설정
				{
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case hideTabBar: {
					MainFragment.getController().hideWebviewTabbar();
					callbackId.success();
				}
					break;
				case showImageDocViewer: {
					try {
						MainFragment.getController().showImageDocViewer((String) data.get(0));
						callbackId.success();
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
				}
					break;
				case showHtmlDocViewer: {
					try {
						MainFragment.getController().showHtmlDocViewer((String) data.get(0), (String) data.get(1));
						callbackId.success();
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
				}
					break;
				case getImageDocViewerData: {
					try {
						JSONObject docData = new JSONObject();
						docData.put("doc_data", MainModel.getInstance().getDocData());
						callbackId.success(docData);
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
				}
					break;
				case closeImageDocViewer: {
					MainModel.getInstance().getDocViewer().getActivity().finish();
					MainModel.getInstance().setDocViewer(null);
					callbackId.success();
				}
					break;
				case showWriteComment: {
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case showMenuItem: {
					try {
						MainFragment.showMenuItem((String) data.get(0));
						callbackId.success();
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
				}
					break;
				case showPopupMenu: {
					MainFragment.getController().showPopupMenu(data);
					callbackId.success();
				}
					break;
				case showCheckPasswdAnoBoard: {
					// 팝업 발생 시킨후 다시 팝업 발생시킬 때 팝업이 안뜨고 멈추는 경우가 생겨 딜레이를 주어 팝업 발생
					// 멈추었을 때 화면 회전시 팝업 나타남 - OS or Dialog 문제로 추정...
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							MainFragment.getController().showPasswordPopup(data);
						}
					}, 200);
					callbackId.success();
				}
					break;
				case returnOrgSelect: {
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case showMailEditorView: {
					fireEvent(ac, data, callbackId);
					callbackId.success();
				}
					break;
				case onBackKeyPressed:
					fireEvent(ac, data, callbackId);
					callbackId.success();
					break;
				case menu_show_all: // 20.06.23 tkofs 전체메뉴
                    //fireEvent(ac, data, callbackId);
                    //callbackId.success();
                    //Toast.makeText( MainModel, "버튼", Toast.LENGTH_SHORT).show();
                    break;
                case closeMenuAllPopupViewer: // 18.11.14 tkofs
                    fireEvent(ac, data, callbackId);
                    callbackId.success();
                    break;
                case getAppServerIP: // 18.11.19 tkofs
                    callbackId.success(Define.SERVER_URL);
                    break;
				default: {
					callbackId.error(HMPPluginResult.Status.INVALID_ACTION.toString());
				}
					break;
				}
			}
		});
		return true;
	}
}