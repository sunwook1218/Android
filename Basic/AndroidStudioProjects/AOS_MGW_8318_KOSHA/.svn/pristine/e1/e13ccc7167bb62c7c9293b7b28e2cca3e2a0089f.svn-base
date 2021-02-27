package com.hs.mobile.gw.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.crypto.Crypto;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.DocViewFragment;
import com.hs.mobile.gw.fragment.HtmlViewFragment;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.openapi.GWOpenApi;
import com.hs.mobile.gw.openapi.square.GetDocHandlerResult;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.openapi.squareplus.SpImageOriginThumbnail;
import com.hs.mobile.gw.openapi.squareplus.SpImageThumbnail;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.DateUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.ImgDownQueue;
import com.hs.mobile.gw.util.ImgDownQueue.Type;
import com.hs.mobile.gw.util.MGWUtils;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.WebkitCookieManagerProxy;
import com.hs.mobile.gw.util.ImgDownQueue.Info;
import com.hs.mobile.gw.util.filecache.FileCache;
import com.hs.mobile.gw.util.filecache.FileCacheFactory;
import com.hs.mobile.gw.util.filecache.FileEntry;

public class OpenAPIService {

	final static int ACCESS_DENIED_ERROR = 999;

	final static int SYSTEM_ERROR = 1001;
	final static int INVALID_REQUEST = 1002;
	final static int SESSION_EXPIRED = 1003;
	final static int ACCESS_DENIED = 1004;
	final static int UNAUTHORIZED_PHONE_UID_NUMBER = 1011;
	final static int UNREGISTERED_NEW_MULTI_DEVICE = 1012;
	final static int APPVERSION_NOTEQUAL_ERROR = 1021;

	final static int LICENSE_ERROR_FILENOTFOUND = 1101;
	final static int LICENSE_ERROR_INVALIDINFO = 1102;
	final static int LICENSE_ERROR_SERVERIP = 1103;
	final static int LICENSE_ERROR_EXPIRED = 1104;
	final static int LICENSE_ERROR_EXCEEDUSER = 1105;

	final static int FILE_DOWNLOAD_ERROR = 1201;
	final static int FILE_TRANSFORM_ERROR = 1202;
	final static int FILE_UNSUPPORTED_TYPE_ERROR = 1203;

	final static int MDM_APPLICATION_PERMISSTION_ERROR = 1301;
	final static int MDM_UNAPPROVED_DEVICE_ERROR = 1302;
	final static int MDM_DUPLICATED_DEVICE_ERROR = 1303;
	final static int MDM_MULTIDEVICE_ERROR = 1304;
	final static int MDM_UNREGISTERED_USER_ERROR = 1305;
	final static int MDM_LICENSE_EXCEEDUSER_ERROR = 1306;
	final static int MDM_UNREGISTERED_APP_ERROR = 1307;
	final static int MDM_UNEXPECTED_ERROR = 1308;

	public static enum ApiCode {
		mail_recvlist, mail_sendlist, mail_deletelist, mail_templist, mail_personallist, mail_delete, mail_selfboxlist,

		mail_personalbox,

		board_recent_memolist, board_memolist, dept_board_memolist, board_folder, board_favfolder, board_noticelist, dept_board_folders,

		sign_waitlist, sign_inprogess, sign_reject, sign_my_complete, sign_complete_box, sign_draft_box, sign_receipt_wait, complete_informal, sending_process,

		contact_group_list, contact_user, contact_dept, contact_group,

		check_login_password, check_sign_password,

		counts, sign_gongram_waiting, sign_gongram_complete,

		bypass_stream, bypass_json,

	}

	Context context;

	public FileCache userImgCache;
	public FileCache imgCache;
	public final String USER_IMG_CACHE = "userImgCache";
	public final String IMG_CACHE = "imgCache";
	
	ImgDownQueue clsQueue = new ImgDownQueue();
	boolean isWorking = false;
	
	public static boolean isSessionExpired = false;	

	public OpenAPIService(Context con) {
		context = con;
		FileCacheFactory.initialize(con);
		if (!FileCacheFactory.getInstance().has(USER_IMG_CACHE)) {
			FileCacheFactory.getInstance().create(USER_IMG_CACHE, 10 * 1024); // KByte
		}
		userImgCache = FileCacheFactory.getInstance().get(USER_IMG_CACHE);
		
		if (!FileCacheFactory.getInstance().has(IMG_CACHE)) {
			FileCacheFactory.getInstance().createExt(IMG_CACHE, 10 * 1024); // KByte
		}
		imgCache = FileCacheFactory.getInstance().get(IMG_CACHE);
	}

	/*
	 * 카운트
	 */
	public JSONObject getCounts(Activity a) {
		String url = HMGWServiceHelper.OpenAPI.COUNTS;
		return (JSONObject) invokeOpenAPIForObject(a, url, null, false);
	}
	
	

	/*
	 * 메뉴 및 아이콘
	 */
	public JSONArray getMenuInfo(Activity a, String locale) {
		String url = com.hs.mobile.gw.service.HMGWServiceHelper.OpenAPI.MENU_INFO;
		ArrayList<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("locale", locale));
		return (JSONArray) invokeOpenAPIForObject(a, url, formparams);
	}

	public JSONArray getIconInfo(Activity a) {
		String url = com.hs.mobile.gw.service.HMGWServiceHelper.OpenAPI.ICON_INFO;
		return (JSONArray) invokeOpenAPIForObject(a, url, null);
	}

	public InputStream getIcon(String iconName) {
		String url = HMGWServiceHelper.OpenAPI.ICON_DOWNLOAD;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("ostype", "android"));
		formparams.add(new BasicNameValuePair("iconname", iconName));
		return invokeOpenAPIForStream(url, formparams);
	}

	/*
	 * 메일 관련 API
	 */
	public boolean deleteMail(Activity a, String boxID, String msgid, String saveID, String purge) {
		String url = HMGWServiceHelper.OpenAPI.MAILDELETE;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("boxid", boxID));
		formparams.add(new BasicNameValuePair("msgid", msgid));
		formparams.add(new BasicNameValuePair("saveid", saveID));
		formparams.add(new BasicNameValuePair("purge", purge));
		Object response = invokeOpenAPIForObject(a, url, formparams);
		if (response == null) {
			return false;
		}
		return true;
	}

	public JSONObject getMailList(Activity a, ApiCode code, int pno) {
		return getMailList(a, code, pno, null, false);
	}

	public JSONObject getMailList(Activity a, ApiCode code, int pno, List<NameValuePair> formparams, boolean isSearch) {
		String url;
		if (isSearch) {
			url = HMGWServiceHelper.OpenAPI.MAILSEARCH;
		} else {
			url = HMGWServiceHelper.OpenAPI.MAILLIST;
		}
		if (formparams == null) {
			formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("pno", String.valueOf(pno)));
			formparams.add(new BasicNameValuePair("row", HMGWServiceHelper.OpenAPI.CONFIG_MAILLIST_PAGESIZE));
			switch (code) {
			case mail_recvlist:
			case mail_selfboxlist:
			case mail_sendlist:
			case mail_deletelist:
			case mail_templist:
			case mail_personallist: {
				formparams.add(new BasicNameValuePair("mailbox", MGWUtils.getTypeStringByApiCode(code)));
				break;
			}
			default:
				break;
			}
		} else {
			// pno 체크			
			int nPnoIdx = -1;
			int nRowIdx = -1;
			int nMailBoxIdx = -1;
			for (int i = 0; i < formparams.size(); ++i) {
				if (TextUtils.equals("pno", formparams.get(i).getName())) {
					nPnoIdx = i;
				} else if (TextUtils.equals("row", formparams.get(i).getName())) {
					nRowIdx = i;
				} else if (TextUtils.equals("mailbox", formparams.get(i).getName())) {
					nMailBoxIdx = i;
				}
			}
			if (nPnoIdx == -1) {
				formparams.add(new BasicNameValuePair("pno", String.valueOf(pno)));
			} else {
				formparams.set(nPnoIdx, new BasicNameValuePair("pno", String.valueOf(pno)));
			}
			if (nRowIdx == -1) {
				formparams.add(new BasicNameValuePair("row", HMGWServiceHelper.OpenAPI.CONFIG_MAILLIST_PAGESIZE));
			} else {
				formparams.set(nRowIdx, new BasicNameValuePair("row", HMGWServiceHelper.OpenAPI.CONFIG_MAILLIST_PAGESIZE));
			}
			switch (code) {
			case mail_recvlist:
			case mail_selfboxlist:
			case mail_sendlist:
			case mail_deletelist:
			case mail_templist:
			case mail_personallist: {
				if (nMailBoxIdx == -1) {
					formparams.add(new BasicNameValuePair("mailbox", MGWUtils.getTypeStringByApiCode(code)));
				} else {
					formparams.set(nMailBoxIdx, new BasicNameValuePair("mailbox", MGWUtils.getTypeStringByApiCode(code)));
				}
				break;
			}
			default:
				break;
			}
		}
		return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
	}

	public Object getPersonalMailBoxList(Activity a) {
		String url = HMGWServiceHelper.OpenAPI.MAIL_PERSONALBOX;
		return invokeOpenAPIForObject(a, url, null);
	}

	/*
	 * 주소록 관련 API
	 */
	public JSONObject getContactList(Activity a, ApiCode code, int pno, List<NameValuePair> formparams, boolean isSearch) {
		String url;
		if (formparams == null) {
			formparams = new ArrayList<NameValuePair>();
		}
		formparams.add(new BasicNameValuePair("pno", String.valueOf(pno)));
		if (isSearch) {
			url = HMGWServiceHelper.OpenAPI.CONTACT_SEARCH;
			formparams.add(new BasicNameValuePair("type", MGWUtils.getContactSearchType(code)));
		} else {
			switch (code) {
			case contact_group_list: {
				url = HMGWServiceHelper.OpenAPI.CONTACT_GROUPLIST;
				break;
			}
			case contact_user: {
				url = HMGWServiceHelper.OpenAPI.CONTACT_USER;
				break;
			}
			case contact_dept: {
				url = HMGWServiceHelper.OpenAPI.CONTACT_DEPT;
				break;
			}
			case contact_group: {
				url = HMGWServiceHelper.OpenAPI.CONTACT_GROUP;
				break;
			}
			default:
				return null;
			}
		}
		return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
	}

	/*
	 * 결재 관련 API
	 */
//	public JSONObject getSearchSignList(Activity a, ApiCode code, int pno, List<NameValuePair> formparams) {
//
//		if (formparams == null) {
//			formparams = new ArrayList<NameValuePair>();
//		}
//		
//		formparams.add(new BasicNameValuePair("target", "appr"));
//		formparams.add(new BasicNameValuePair("todo", "display"));
//		formparams.add(new BasicNameValuePair("type", "0"));
//		formparams.add(new BasicNameValuePair("K", HMGWServiceHelper.key));
//		formparams.add(new BasicNameValuePair("pno", String.valueOf(pno)));
//		formparams.add(new BasicNameValuePair("openapipath", HMGWServiceHelper.OpenAPI.OPEN_API_URL));
//		
//		switch(code){
//			case sign_inprogess: 
//				formparams.add(new BasicNameValuePair("APPLID", "2020"));
//				formparams.add(new BasicNameValuePair("customFolder", "true"));
//				break;
//			
//			case sign_waitlist: 
//				formparams.add(new BasicNameValuePair("APPLID", "2010"));
//				break;
//			
//			case sign_reject: 
//				formparams.add(new BasicNameValuePair("APPLID", "2030"));
//				break;
//			
//			case sign_my_complete: 
//				formparams.add(new BasicNameValuePair("APPLID", "6022"));
//				break;
//			
//			case sign_complete_box: 
//				formparams.add(new BasicNameValuePair("APPLID", "8010"));
//				break;
//			
//			case sign_draft_box: 
//				formparams.add(new BasicNameValuePair("APPLID", "6021"));
//				break;
//			
//			case sign_receipt_wait: 
//				formparams.add(new BasicNameValuePair("APPLID", "5010"));
//				break;
//			
//			case complete_informal: 
//				formparams.add(new BasicNameValuePair("APPLID", "8520"));
//				break;
//			
//			default:
//				return null;
//		}
//		return (JSONObject) invokeOpenAPIForObject(a, GWOpenApi.BYPASS_JSON, formparams);
//	}
	
	public JSONObject getSignList(Activity a, ApiCode code, int pno, List<NameValuePair> formparams, boolean isSearchMode,String authDeptId,String authFldrId) {
		String url;
		if (formparams == null) {
			formparams = new ArrayList<NameValuePair>();
		}

		formparams.add(new BasicNameValuePair("pno", String.valueOf(pno)));
		if (isSearchMode) {
			formparams.add(new BasicNameValuePair("reportDe3", DateUtils.getTodayDate()));
			formparams.add(new BasicNameValuePair("reportDe", DateUtils.getYearMiner()));
		}
		
		switch (code) {
		case sign_inprogess: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=now";
			else			
				url = HMGWServiceHelper.OpenAPI.SIGN_INPROGESS;
			break;
		}
		case sign_waitlist: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=wait";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_WAITLIST;
			break;
		}
		case sign_gongram_waiting:{
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=gongram";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_GONGRAM_WAIT;
			break;			
		}
		case sign_gongram_complete:{
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=gongramcomplete";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_GONGRAM_COMPLETE;
			break;
		}
		case sign_reject: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=reject";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_REJECT;
			break;
		}
		case sign_my_complete: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=userprocessed";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_MY_COMPLETE_HTML_HWP8;
			// if(HMGWServiceHelper.sign_doctype.equals("html") ||
			// HMGWServiceHelper.sign_doctype.equals("hwp8")){
			// url = HMGWServiceHelper.OpenAPI.SIGN_MY_COMPLETE_HTML_HWP8;
			// }else{
			// url = HMGWServiceHelper.OpenAPI.SIGN_COMPLETE_TAGFREE_HWP6;
			// }
			break;
		}
		case sign_complete_box: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=complete";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_COMPLETE_BOX;
			break;
		}
		case sign_draft_box: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=draft";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_DRAFT_BOX;
			break;
		}
		case sign_receipt_wait: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=receiptwait";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_RECEIPT_WAIT;
			break;
		}
		case complete_informal: {
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=complete_informal";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_COMPLETE_INFORMAL;
			break;
		}
		case sending_process: {
			formparams.add(new BasicNameValuePair("authDeptId", authDeptId));
			formparams.add(new BasicNameValuePair("authFldrId", authFldrId));
			if (isSearchMode)
				url = HMGWServiceHelper.OpenAPI.SIGN_SEARCH + "?type=dispatch";
			else
				url = HMGWServiceHelper.OpenAPI.SIGN_SENDING_PROCESS;
			break;
		}			
		default:
			return null;
		}
		return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
	}

	
	
	public JSONArray getDeptBoardFolderList(Activity a, String boardId) {
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		
		formparams.add(new BasicNameValuePair("target", "bbs"));
		formparams.add(new BasicNameValuePair("acton", "folder"));
		formparams.add(new BasicNameValuePair("todo", "department"));
		formparams.add(new BasicNameValuePair("BRDID", boardId));
		formparams.add(new BasicNameValuePair("openapipath", HMGWServiceHelper.OpenAPI.OPEN_API_URL));
		
		
		return (JSONArray) invokeOpenAPIForObject(a, GWOpenApi.BYPASS_JSON, formparams);
	}
	
	public JSONObject getDeptBoardMemoList(Activity a, ApiCode apiCode, int pno ,String boardId, String deptId) {
		
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		
		formparams.add(new BasicNameValuePair("PNO", String.valueOf(pno)));
		formparams.add(new BasicNameValuePair("target", "bbs"));
		formparams.add(new BasicNameValuePair("acton", "message"));
		formparams.add(new BasicNameValuePair("todo", "display"));
		formparams.add(new BasicNameValuePair("BRDID", boardId));
		formparams.add(new BasicNameValuePair("DEPTID", deptId));
		formparams.add(new BasicNameValuePair("openapipath", HMGWServiceHelper.OpenAPI.OPEN_API_URL));
		
		
		return (JSONObject) invokeOpenAPIForObject(a, GWOpenApi.BYPASS_JSON, formparams);
	}	
	
	public JSONObject getSendingDeptList(Activity a, int pno, List<NameValuePair> formparams) {
		if (formparams == null){
			formparams = new ArrayList<NameValuePair>();
		}
		int nPnoIdx = -1;
		for (int i = 0 ; i < formparams.size(); i++ ) {
			if (formparams.get(i).getName().equals("SPNO")) {
				nPnoIdx = i;
			}
		}
		
		if (nPnoIdx == -1)
		{
			formparams.add(new BasicNameValuePair("target", "appr"));
			formparams.add(new BasicNameValuePair("todo", "sndngProcAuthList"));
			formparams.add(new BasicNameValuePair("authDeptId", "000010501"));
			formparams.add(new BasicNameValuePair("resultType", "json"));
			formparams.add(new BasicNameValuePair("K", HMGWServiceHelper.key));
			formparams.add(new BasicNameValuePair("authFldrId", "JHOMS150560000046000"));
			formparams.add(new BasicNameValuePair("openapipath", HMGWServiceHelper.OpenAPI.OPEN_API_URL));
		}
		else {
			formparams.set(nPnoIdx, new BasicNameValuePair("SPNO", String.valueOf(pno)));
		}
		
		
		String url = GWOpenApi.BYPASS_JSON;
		return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
	}
	
	public JSONObject getSearchBoardList(Activity a, int pno, String boardId,List<NameValuePair> formparams) {
		if (formparams == null)
			formparams = new ArrayList<NameValuePair>();
		
		int nPnoIdx = -1;
		for (int i = 0 ; i < formparams.size(); i++ ) {
			if (formparams.get(i).getName().equals("SPNO")) {
				nPnoIdx = i;
			}
		}
		
		if (nPnoIdx == -1)
		{
			formparams.add(new BasicNameValuePair("target", "bbs"));
			formparams.add(new BasicNameValuePair("acton", "message"));
			formparams.add(new BasicNameValuePair("todo", "display"));
			formparams.add(new BasicNameValuePair("type", "litesearch"));
			formparams.add(new BasicNameValuePair("BRDID", boardId));
			formparams.add(new BasicNameValuePair("key", HMGWServiceHelper.key));
			formparams.add(new BasicNameValuePair("SPNO", String.valueOf(pno)));
			formparams.add(new BasicNameValuePair("openapipath", HMGWServiceHelper.OpenAPI.OPEN_API_URL));
		}
		else {
			formparams.set(nPnoIdx, new BasicNameValuePair("SPNO", String.valueOf(pno)));
		}
		
		
		String url = GWOpenApi.BYPASS_JSON;
		return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
	}
	
	public JSONObject getBoardMemoList(Activity a, ApiCode apiCode, int pno, String boardId, String brdType, String deptId) {
		ArrayList<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("pno", String.valueOf(pno)));
		String url;
		ViewModel.Log(apiCode, "tkofs_bbs_openapi");
		ViewModel.Log(String.valueOf(pno), "tkofs_bbs");

		if (apiCode == ApiCode.board_recent_memolist) {
			url = HMGWServiceHelper.OpenAPI.BOARD_RECENTLIST;

		} else if (apiCode == ApiCode.board_memolist) {
			formparams.add(new BasicNameValuePair("boardid", boardId));
			formparams.add(new BasicNameValuePair("brdType", brdType));
			url = HMGWServiceHelper.OpenAPI.BOARD_LIST;
		} else if (apiCode == ApiCode.dept_board_memolist) {
			formparams.add(new BasicNameValuePair("boardid", boardId));
			formparams.add(new BasicNameValuePair("deptid", deptId));
			formparams.add(new BasicNameValuePair("brdType", brdType));
			url = HMGWServiceHelper.OpenAPI.BOARD_LIST;
		} else if (apiCode == ApiCode.board_noticelist) {
			url = HMGWServiceHelper.OpenAPI.BOARD_NOTICE_LIST;
		} else {
			return null;
		}
		ViewModel.Log(url, "tkofs_bbs");
		if (invokeOpenAPIForObject(a, url, formparams) instanceof JSONObject) {
			return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
		} else {
			return null;
		}
	}

	public JSONArray getBoardFolders(Activity a, String folderId) {
		List<NameValuePair> formparams = null;
		if (folderId != null) {
			formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("folderid", folderId));
		}
		String url = HMGWServiceHelper.OpenAPI.BOARD_FOLDER;
		return (JSONArray) invokeOpenAPIForObject(a, url, formparams);
	}

	public JSONArray getBoardFavorFolders(Activity a) {
		String url = HMGWServiceHelper.OpenAPI.BOARD_FAVFOLDER;
		return (JSONArray) invokeOpenAPIForObject(a, url, null, true);
	}

	public boolean checkUserInfo(Activity a, String userid) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("id", userid));
		String url = HMGWServiceHelper.OpenAPI.ENV_USERINFO;
		JSONObject obj = (JSONObject) invokeOpenAPIForObject(a, url, formparams, HTTP_GET);
		try {
			if (obj != null && obj.has("sancPasswdChk")) {
				Debug.trace("sancPasswdChk = ", obj.getBoolean("sancPasswdChk"));
				return obj.getBoolean("sancPasswdChk");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Debug.trace(e.getMessage());
		}
		return false;
	}
	
	public boolean checkPassword(Activity a, ApiCode code, String password) {
		password = Crypto.getInstance().encrypt(password);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("passwd", password));
		switch (code) {
		case check_login_password: {
			formparams.add(new BasicNameValuePair("type", "login"));
			break;
		}
		case check_sign_password: {
			formparams.add(new BasicNameValuePair("type", "sanc"));
			break;
		}
		default:
			break;
		}
		String url = HMGWServiceHelper.OpenAPI.CHECK_PASSWORD;
		JSONObject obj = (JSONObject) invokeOpenAPIForObject(a, url, formparams);

		if (obj == null || obj.optInt("code") != 0 || obj.optInt("status") != 0) {
			return false;
		}
		return true;
	}

	public String registMultiDevice(List<NameValuePair> formparams) {
		String url;
		url = HMGWServiceHelper.OpenAPI.REGIST_MULTI_DEVICE;

		return invokeOpenAPIForJsonString(url, formparams);
	}

	public String login(List<NameValuePair> formparams) {
		String url;
		if (HMGWServiceHelper.hasAdditionalOfficer) {
			url = HMGWServiceHelper.OpenAPI.LOGIN_ADDITIONALOFFICER;
		} else {
			HMGWServiceHelper.cookies = null;
			url = HMGWServiceHelper.OpenAPI.LOGIN;
		}
		return invokeOpenAPIForJsonString(url, formparams);
	}

	public void logout() {
		String url = HMGWServiceHelper.OpenAPI.LOGOUT;
		invokeOpenAPIForJsonString(url, null);
	}

	public InputStream getUserPhotoStream() {
		String url = GWOpenApi.BYPASS_STREAM;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("openapipath", HMGWServiceHelper.photoLink));
		return invokeOpenAPIForStream(url, formparams);
	}

	public JSONObject getDocViewAuth(Activity a, String link) {
		String url = GWOpenApi.BYPASS_JSON;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("openapipath", link));
		return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
	}

	public JSONObject checkDocView(Activity a, String apprid) {
		String url = HMGWServiceHelper.OpenAPI.SIGN_CHECKDOCVIEW;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("apprid", apprid));
		return (JSONObject) invokeOpenAPIForObject(a, url, formparams);
	}
	
	/*
	 *  문서변환 관련 Api
	 */

	public void getDocHandlerResult (final Activity a, final AttachListItemVO attachListItemVO) {

		if(attachListItemVO.fileType.equals("0")||attachListItemVO.fileType.equals("3")){
			PopupUtil.showToastMessage(a, R.string.error_file_extension);
			return;
		}


		PopupUtil.showLoading(a);


		AjaxCallback<JSONObject> callBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(a,
				SquareDefaultVO.class) {
			@Override
			public void callback(String url, JSONObject JSONobj, AjaxStatus status) {
				Debug.trace(url);
				PopupUtil.hideLoading(a);

				JSONObject docLink;
				String htmlUrl;
				BundleUtils.Builder builder = new BundleUtils.Builder();

				try { 
					Debug.trace(JSONobj);
					
					switch(JSONobj.optString("docType")) {
					
						case "image":
						case "html":
						case "text": {
							docLink = JSONobj.optJSONObject("docLink");
							JSONArray linkList = docLink.optJSONArray("linkList");
							for (int i = 0; i < linkList.length(); i++) {
								String link = (String) linkList.get(i);
								Debug.trace("link = " + link);
								if (!(link.startsWith("http://") || link.startsWith("https://"))) {
									linkList.put(i, Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")).concat(link));
								}
								else {
									linkList.put(i, Define.SERVER_URL.concat("/rest/doc/proxy?proxyurl="+link));
								}

							}
							docLink.put("linkList", linkList);
							JSONobj.put("docLink", docLink);
							JSONobj.put("baseUrl", Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")));

							builder.add(DocViewFragment.ARG_KEY_DATA, JSONobj.toString());
							builder.add(DocViewFragment.ARG_KEY_SQUARE, true);
							MainModel.getInstance().setDocData(JSONobj.toString());
							MainModel.getInstance().showSubActivity(a, SubActivityType.DOC_VIEWER, builder.build());
						}
							break;
							
						case "synap":
							docLink = JSONobj.optJSONObject("docLink");
							htmlUrl = ((String) docLink.optJSONArray("linkList").get(0));
							builder.add(HtmlViewFragment.ARG_KEY_URL, Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")) + htmlUrl);
							builder.add(HtmlViewFragment.ARG_KEY_TITLE, attachListItemVO.fileName + "." + attachListItemVO.fileExt);
					    	MainModel.getInstance().showSubActivity(a, SubActivityType.HTML_VIEWER, builder.build());
							break;
							
						case "kaon":
							docLink = JSONobj.optJSONObject("docLink");
							htmlUrl = ((String) docLink.optJSONArray("linkList").get(0));
							String tempUrl = "";
							if (htmlUrl.contains("http://")) {
								String[] temp = htmlUrl.split("http://");
								tempUrl = temp[0] + URLEncoder.encode("http://".concat(temp[1]), "utf-8") + "/true";
							}
							if (url.contains("https://")) {
								String[] temp = htmlUrl.split("https://");
								tempUrl = temp[0] + URLEncoder.encode("https://".concat(temp[1]), "utf-8") + "/true";
							}
							Intent web = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(tempUrl));
							a.startActivity(web);
							break;
						default: {
							docLink = JSONobj.optJSONObject("docLink");
							htmlUrl = ((String) docLink.optJSONArray("linkList").get(0));
							builder.add(HtmlViewFragment.ARG_KEY_URL, Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")) + htmlUrl);
//							builder.add(HtmlViewFragment.ARG_KEY_URL, htmlUrl);
							builder.add(HtmlViewFragment.ARG_KEY_TITLE, attachListItemVO.fileName + "." + attachListItemVO.fileExt);
							MainModel.getInstance().showSubActivity(a, SubActivityType.HTML_VIEWER, builder.build());
						}
						break;
					}
					
					
				} catch (JSONException | UnsupportedEncodingException e) {
					Debug.trace(e.getMessage());
				}
			}
		};

		PopupUtil.showLoading(a);

		String link = "/square/downloadAttach.do?"
				+ "contentsId="+ attachListItemVO.contentsId
				+ "&attachId="+ attachListItemVO.attachId
				+ "&userID="+HMGWServiceHelper.userId
				+ "&K="+HMGWServiceHelper.key
				+ "&dataType=json";

		HashMap<String, String> params = new HashMap<>();
		params.put("openapi_doclink", link); // 첨부id
		params.put("openapi_category", "attach"); // 첨부id
		params.put("openapi_filename", attachListItemVO.fileName + "." + attachListItemVO.fileExt); // 첨부id
		new ApiLoaderEx<JSONObject>(new GetDocHandlerResult(a), a, callBack, params).execute();
	}

	public void getDocHandlerResult (final Activity a, final SpAttachVO spAttachVO) {

		if(spAttachVO.getFileType().equals("0")||spAttachVO.getFileType().equals("3")){
			PopupUtil.showToastMessage(a, R.string.error_file_extension);
			return;
		}


		PopupUtil.showLoading(a);


		AjaxCallback<JSONObject> callBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(a,
				SquareDefaultVO.class) {
			@Override
			public void callback(String url, JSONObject JSONobj, AjaxStatus status) {
				Debug.trace(url);
				PopupUtil.hideLoading(a);

				JSONObject docLink;
				String htmlUrl;
				BundleUtils.Builder builder = new BundleUtils.Builder();

				try { 
					Debug.trace(JSONobj);
					switch(JSONobj.optString("docType")) {
					
						case "image":
						case "html":
						case "text": {
							docLink = JSONobj.optJSONObject("docLink");
							JSONArray linkList = docLink.optJSONArray("linkList");
							for (int i = 0; i < linkList.length(); i++) {
								String link = (String) linkList.get(i);
								Debug.trace("link = " + link);
								if (!(link.startsWith("http://") || link.startsWith("https://"))) {
									linkList.put(i, Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")).concat(link));
								}
							}
							docLink.put("linkList", linkList);
							JSONobj.put("docLink", docLink);
							JSONobj.put("baseUrl", Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")));

							builder.add(DocViewFragment.ARG_KEY_DATA, JSONobj.toString());
							builder.add(DocViewFragment.ARG_KEY_SQUARE, true);
							MainModel.getInstance().setDocData(JSONobj.toString());
							MainModel.getInstance().showSubActivity(a, SubActivityType.DOC_VIEWER, builder.build());
						}
							break;
							
						case "synap":
							docLink = JSONobj.optJSONObject("docLink");
							htmlUrl = ((String) docLink.optJSONArray("linkList").get(0));
							builder.add(HtmlViewFragment.ARG_KEY_URL, Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")) + htmlUrl);
							builder.add(HtmlViewFragment.ARG_KEY_TITLE, spAttachVO.getFileName() + "." + spAttachVO.getFileExt());
					    	MainModel.getInstance().showSubActivity(a, SubActivityType.HTML_VIEWER, builder.build());
							break;
							
						case "kaon":
							docLink = JSONobj.optJSONObject("docLink");
							htmlUrl = ((String) docLink.optJSONArray("linkList").get(0));
							String tempUrl = "";
							if (htmlUrl.contains("http://")) {
								String[] temp = htmlUrl.split("http://");
								tempUrl = temp[0] + URLEncoder.encode("http://".concat(temp[1]), "utf-8") + "/true";
							}
							if (url.contains("https://")) {
								String[] temp = htmlUrl.split("https://");
								tempUrl = temp[0] + URLEncoder.encode("https://".concat(temp[1]), "utf-8") + "/true";
							}
							Intent web = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(tempUrl));
							a.startActivity(web);
							break;
						default: {
							docLink = JSONobj.optJSONObject("docLink");
							htmlUrl = ((String) docLink.optJSONArray("linkList").get(0));
							builder.add(HtmlViewFragment.ARG_KEY_URL, Define.SERVER_URL.substring(0, Define.SERVER_URL.lastIndexOf("/")) + htmlUrl);
							builder.add(HtmlViewFragment.ARG_KEY_TITLE, spAttachVO.getFileName() + "." + spAttachVO.getFileExt());
							MainModel.getInstance().showSubActivity(a, SubActivityType.HTML_VIEWER, builder.build());
						}
					}
				} catch (JSONException | UnsupportedEncodingException e) {
					Debug.trace(e.getMessage());
				}

			}
		};

		PopupUtil.showLoading(a);

		String link = "/square/downloadAttach.do?"
				+ "contentsId="+ spAttachVO.getContentsId()
				+ "&attachId="+ spAttachVO.getAttachId()
				+ "&userID="+HMGWServiceHelper.userId
				+ "&K="+HMGWServiceHelper.key
				+ "&dataType=json";

		HashMap<String, String> params = new HashMap<>();
		params.put("openapi_doclink", link); // 첨부id
		params.put("openapi_category", "attach"); // 첨부id
		params.put("openapi_filename", spAttachVO.getFileName() + "." + spAttachVO.getFileExt()); // 첨부id
		new ApiLoaderEx<JSONObject>(new GetDocHandlerResult(a), a, callBack, params).execute();
	}




	
	/*
	 *  Square Plus 관련 Api
	 */
	public void downloadOrgImg (final Activity a, final SpAttachVO item) {
		AjaxCallback<InputStream> spImageOriginThumbnailCallback = new AjaxCallback<InputStream>() {
			@Override
			public void callback(String url, InputStream inp, AjaxStatus status) {
				PopupUtil.hideLoading(a);
				if (inp == null) {
					Debug.trace("inp is null!! attachId = ", item.getAttachId());
					return;
				}

				synchronized (inp) {

					BufferedInputStream buf = new BufferedInputStream(inp);

					String key = "org_".concat(item.getAttachId()+"."+item.getFileExt()) ;
					if (inp != null) {
						try { // file cache save
							Debug.trace("img cache put attachId = ", item.getAttachId());
							if (imgCache.get(key) != null)
								imgCache.remove(key);
							imgCache.put(key, buf);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Debug.trace(e.getMessage());
						}
					}
					
					try {
						FileEntry fileEntry = imgCache.get(key);
						if (fileEntry != null) {
							Debug.trace("Origin Img Load. attachId = ", item.getAttachId());
							File file = fileEntry.getFile();
							MainModel.getInstance().callActionView(file, a);
						}

						if (buf != null)
							buf.close();
						if (inp != null)
							inp.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Debug.trace(e.getMessage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Debug.trace(e.getMessage());
					}
				}
				
			}
		};

		PopupUtil.showLoading(a);
		{ // 썸네일 가져오기 (원본의 축소 형태)
			HashMap<String, String> params = new HashMap<>();
			params.put("attachId", item.getAttachId()); // 첨부id
			SpImageOriginThumbnail api = new SpImageOriginThumbnail(context);
			new ApiLoaderEx<>(api, context, spImageOriginThumbnailCallback, params).execute();
		}
	}
	
	public void drawThumbnail(final String attachId, final ImageView photo, final Resources res) {
		try {
			FileEntry fileEntry = imgCache.get(attachId);
			if (fileEntry != null) {
				Debug.trace("Img Load Form FileCahce!! AttachId = ", attachId);
				InputStream inp = fileEntry.getInputStream();
				setThumbnailView(photo, inp, res);
				inp.close();
			} else {
				if (!isWorking) {
					isWorking = true;
					downloadThumbnail(attachId, photo, res);
				}else {
					clsQueue.setInfo(attachId, photo, res, Type.THUMBNAIL);
					clsQueue.getQueue().offer(clsQueue.getInfo());
				}
			}
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		} 
	}
	
	public void downloadThumbnail(final String attachId, final ImageView photo, final Resources res) {
		AjaxCallback<InputStream> spImageThumbnailCallback = new AjaxCallback<InputStream>() {
			@Override
			public void callback(String url, InputStream inp, AjaxStatus status) {

				if (inp == null) {
					Debug.trace("inp is null!! attachId = ", attachId);
					return;
				}

				synchronized (inp) {

					BufferedInputStream buf = new BufferedInputStream(inp);

					if (inp != null) {
						try { // file cache save
							Debug.trace("img cache put attachId = ", attachId);
							imgCache.put(attachId, buf);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Debug.trace(e.getMessage());
						}
					}
					SystemClock.sleep(10);
					try {
						FileEntry fileEntry = imgCache.get(attachId);
						if (fileEntry != null) {
							Debug.trace("Img Load Form FileCahce!! attachId = ", attachId);
							InputStream is = fileEntry.getInputStream();
							if (!setThumbnailView(photo, is, res)) {
								imgCache.remove(attachId);
							}

							if (is != null)
								is.close();
						}

						if (buf != null)
							buf.close();
						if (inp != null)
							inp.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Debug.trace(e.getMessage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Debug.trace(e.getMessage());
					}
					SystemClock.sleep(10);
				}
				
				
				isWorking = false;
				if (clsQueue.getQueue().peek() != null) {
					Info info = (Info) clsQueue.getQueue().poll();
					if (info != null) {
						isWorking = true;
						switch (info.getType()) {
						case THUMBNAIL:
							downloadThumbnail(info.getId(), info.getView(), info.getRes());
							break;
						case USER_PHOTO:
							downloadUserPhoto(info.getId(), info.getView(), info.getRes());
							break;
						}
					}
				}
			}
		};

		{ // 썸네일 가져오기 (원본의 축소 형태)
			HashMap<String, String> params = new HashMap<>();
			params.put("attachId", attachId); // 첨부id
			new ApiLoaderEx<>(new SpImageThumbnail(context), context, spImageThumbnailCallback, params).execute();
		}
	}
	
	public boolean setThumbnailView(ImageView photo, InputStream is, Resources res) {
		// TODO Auto-generated method stub
		boolean ret = false;
		BitmapDrawable bitmapDrawable = new BitmapDrawable(res, is);

		if (bitmapDrawable != null && bitmapDrawable.getBitmap() != null) {
			ret = true;
			MainModel.getInstance().getAq().id(photo).image(bitmapDrawable);
		} else {
			Debug.trace("not bitmap!!!!!!@@");
		}
		return ret;
	}

	public void drawUserPhoto(final String userId, final ImageView photo, final Resources res) {

		if (HMGWServiceHelper.VIEW_PROFILE_PHOTO) {
			try {
				FileEntry fileEntry = userImgCache.get(userId);
				if (fileEntry != null) {
					
					Debug.trace("Img Load Form FileCahce!! UserId = ", userId);
					InputStream inp = fileEntry.getInputStream();
					setUserPhotoView(photo, inp, res);
					inp.close();
					
				}else{
					
					if (!isWorking) {
						isWorking = true;
						downloadUserPhoto(userId, photo, res);
					} else {
						clsQueue.setInfo(userId, photo, res, Type.USER_PHOTO);
						clsQueue.getQueue().offer(clsQueue.getInfo());
					}
					
				}
			} catch (IOException e) {
				Debug.trace(e.getMessage());
			}
		} else {
			photo.setVisibility(View.GONE);
		}
	}
	
	public void downloadUserPhoto(final String userId, final ImageView photo, final Resources res) {

		final String imageUrl = HMGWServiceHelper.OpenAPI.PHOTO_URL + "?user_id=" + userId + "&K" + HMGWServiceHelper.key;

		// MainModel.getInstance().getAq().id(photo).progress(R.drawable.loading_progress).image(GWOpenApi.BYPASS_STREAM
		// + imageUrl, false, false);
		AjaxCallback<InputStream> cb = new AjaxCallback<InputStream>() {
			@Override
			public void callback(String url, InputStream inp, AjaxStatus status) {

				if (inp == null) {
					Debug.trace("inp is null!! userId = ", userId);
					return;
				}

				synchronized (inp) {

					BufferedInputStream buf = new BufferedInputStream(inp);

					if (inp != null) {
						try { // file cache save
							Debug.trace("img cache put userId = ", userId);
							userImgCache.put(userId, buf);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Debug.trace(e.getMessage());
						}
					}
					SystemClock.sleep(10);
					try {
						FileEntry fileEntry = userImgCache.get(userId);
						if (fileEntry != null) {
							Debug.trace("Img Load Form FileCahce!! UserId = ", userId);
							InputStream is = fileEntry.getInputStream();
							if (!setUserPhotoView(photo, is, res)) {
								userImgCache.remove(userId);
							}

							if (is != null)
								is.close();
						}

						if (buf != null)
							buf.close();
						if (inp != null)
							inp.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Debug.trace(e.getMessage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Debug.trace(e.getMessage());
					}
					SystemClock.sleep(10);
				}

				isWorking = false;
				if (clsQueue.getQueue().peek() != null) {
					Info info = (Info)clsQueue.getQueue().poll();
					if (info != null) {
						isWorking = true;
						switch (info.getType()) {
						case THUMBNAIL:
							downloadThumbnail(info.getId(), info.getView(), info.getRes());
							break;

						case USER_PHOTO:
							downloadUserPhoto(info.getId(), info.getView(), info.getRes());
							break;
						}
					}
				}
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

		cb.param("openapipath", imageUrl);
		MainModel.getInstance().createAqueryIntence(context);
		MainModel.getInstance().getAq().ajax(GWOpenApi.BYPASS_STREAM, InputStream.class, cb);
	}

	public boolean setUserPhotoView(ImageView photo, InputStream inp, Resources res) {
		boolean ret = false;
		BitmapDrawable bitmapDrawable = new BitmapDrawable(res, inp);

		if (bitmapDrawable != null && bitmapDrawable.getBitmap() != null) {
			ret = true;
			MainModel.getInstance().getAq().id(photo).image(bitmapDrawable);
		} else {
			Debug.trace("not bitmap!!!!!!@@");
			MainModel.getInstance().getAq().id(photo).image(res.getDrawable(R.drawable.sp_user_no_img));
		}
		return ret;
	}
	
	final static public int HTTP_POST = 0;
	final static public int HTTP_GET = 1;

	/*
	 * Open API Invoke Method
	 */
	public Object invokeOpenAPIForObject(Activity a, String url, List<NameValuePair> formparams) {
		return getObjectFromResStr(a, invokeOpenAPIForJsonString(url, formparams), true);
	}

	public Object invokeOpenAPIForObject(Activity a, String url, List<NameValuePair> formparams, int HttpType) {
		return getObjectFromResStr(a, invokeOpenAPIForJsonString(url, formparams, HttpType), true);
	}
	
	public Object invokeOpenAPIForObject(Activity a, String url, List<NameValuePair> formparams, boolean showErrorMessage) {
		return getObjectFromResStr(a, invokeOpenAPIForJsonString(url, formparams), showErrorMessage);
	}
	
	public InputStream invokeOpenAPIForStream(String url, List<NameValuePair> formparams) {
		InputStream responseStream = null;
		HttpEntity responseEntity = invokeOpenAPI(url, formparams);
		if (responseEntity != null) {
			try {
				responseStream = responseEntity.getContent();
			} catch (IllegalStateException e) {
				Debug.trace(e.getMessage());
				Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
			} catch (IOException e) {
				Debug.trace(e.getMessage());
				Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
			} catch (Exception e) {
				Debug.trace(e.getMessage());
				Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
			}
		}
		return responseStream;
	}
	
	public String invokeOpenAPIForJsonString(String url, List<NameValuePair> formparams) {
		return invokeOpenAPIForJsonString(url, formparams, HTTP_POST);
	}

	public String invokeOpenAPIForJsonString(String url, List<NameValuePair> formparams, int httpType) {
		String responseString = null;
		HttpEntity responseEntity = invokeOpenAPI(url, formparams, httpType);
		if (responseEntity != null) {
			try {
				responseString = EntityUtils.toString(responseEntity);
				Debug.trace("OpenAPI Response String:" + responseString);
			} catch (ParseException e) {
				Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
			} catch (IOException e) {
				Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
			}catch (Exception e) {
				Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
			}
		}
		return responseString;
	}
	
	public HttpEntity invokeOpenAPI(String url, List<NameValuePair> formparams) {
		return invokeOpenAPI(url, formparams, HTTP_POST);
	}

	public HttpEntity invokeOpenAPI(String url, List<NameValuePair> formparams, int httpType) {
		Debug.trace("OpenAPI invoke url : " + url);
		HttpEntity responseEntity = null;

		try {
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, HMGWServiceHelper.OpenAPI.MAX_CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, HMGWServiceHelper.OpenAPI.MAX_CONNECTION_TIMEOUT);
			HttpConnectionParams.setTcpNoDelay(params, true);
			HttpPost httppost = null;
			HttpGet httpget = null;
			if (httpType == HTTP_GET) {
				if (formparams != null) {
					httpget = new HttpGet(url+"?"+URLEncodedUtils.format(formparams, "UTF-8"));
					Debug.trace(httpget.getURI().toString());
					for (int i = 0; i < formparams.size(); i++) {
						Debug.trace("Parameter => Name: " + formparams.get(i).getName() + " - Value: " + formparams.get(i).getValue());
					}
				}
				else
					httpget = new HttpGet(url);
			}
			else {
				httppost = new HttpPost(url);
				if (formparams != null) {
					UrlEncodedFormEntity entity;
					entity = new UrlEncodedFormEntity(formparams, "UTF-8");
					httppost.setEntity(entity);
					for (int i = 0; i < formparams.size(); i++) {
						Debug.trace("Parameter => Name: " + formparams.get(i).getName() + " - Value: " + formparams.get(i).getValue());
					}
				}
			}
			DefaultHttpClient httpclient = new DefaultHttpClient(params);
			
			httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
				public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
					String locale = Locale.getDefault().getLanguage();
					if (TextUtils.isEmpty(locale))
						locale = Locale.getDefault().getLanguage();

					Debug.trace("locale:" + locale);

					request.addHeader("User-Agent", HMGWServiceHelper.USER_AGENT);
					request.addHeader("Accept", "application/json");
					request.addHeader("Accept-Language", locale);
				}
			});

			HttpContext localContext = new BasicHttpContext();
			List<Cookie> cookies;
			CookieStore cookieStore;
			if (HMGWServiceHelper.cookies != null) {
				cookies = HMGWServiceHelper.cookies;
				cookieStore = new BasicCookieStore();
				for (Cookie cookie : cookies) {
					cookieStore.addCookie(cookie);
				}
				localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			}

			Debug.trace("Before executing:" + new Date());
			Date a = new Date();
			HttpResponse response;
			if (httpType == HTTP_GET)
				response = httpclient.execute(httpget, localContext);
			else
				response = httpclient.execute(httppost, localContext);
			if (HMGWServiceHelper.cookies == null) {
				HMGWServiceHelper.cookies = httpclient.getCookieStore().getCookies();
			}
			if (!HMGWServiceHelper.cookies.isEmpty()) {
				CookieSyncManager.createInstance(context);
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
			Debug.trace("After executing:" + new Date() + "/ Elapsed time:" + ((new Date()).getTime() - a.getTime()) + "(ms)");

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				return null;
			}
			responseEntity = response.getEntity();

		} catch (ClientProtocolException e) {
			Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
		} catch (IOException e) {
			Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
		} catch (Exception e) {
			Debug.trace(getClass().getSimpleName(), "Message: " + e.getMessage());
		}
		return responseEntity;
	}

	public JSONObject checkErrorCode(JSONObject data) {
		int errorCode = data.optInt("code");
		if (errorCode != 0) {
			String errorMessage = getErrorMessage(errorCode);
			if (errorMessage == null) {
				// 10: invaild request
				if (errorCode == 10) {
					return null;
				}
				errorMessage = errorCode + ":" + data.optString("message");
				PopupUtil.showToastMessage((Activity) context, errorMessage);
			} else {
				if (errorCode == SESSION_EXPIRED || errorCode == ACCESS_DENIED) {
					DialogInterface.OnClickListener m_logoutDialogListener = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							HMGWServiceHelper.LogoutFlag = true;
							MainFragment.getController().doLogout();
							if(MainModel.getInstance().getHomeFragment() != null && MainModel.getInstance().getHomeFragment().getActivity() != null){
								MainModel.getInstance().getHomeFragment().getActivity().finish();	// tkofs 홈 에서 로그아웃시 home 종료
							}
						}
					};
					if(HMGWServiceHelper.LogoutFlag) {
						HMGWServiceHelper.LogoutFlag = false;
						// tkofs 세션 아웃시 home 일떄와 아닐떄 구분
						if(MainModel.getInstance().getHomeFragment() != null && MainModel.getInstance().getHomeFragment().getActivity() != null){
							PopupUtil.showDialog(MainModel.getInstance().getHomeFragment().getActivity(), errorMessage, m_logoutDialogListener);
						}else{
							PopupUtil.showDialog((Activity) context, errorMessage, m_logoutDialogListener);
						}
					}
				} else {
					PopupUtil.showDialog((Activity) context, errorMessage);
				}
			}
			return null;
		} else {
			return data;
		}
	}

	public String getErrorMessage(int code) {
		int messageResID = 0;
		switch (code) {
		case ACCESS_DENIED_ERROR: {
			messageResID = R.string.error_access_error;
			break;
		}
		// SYSTEM
		case SYSTEM_ERROR: {
			messageResID = R.string.error_system_error;
			break;
		}
		case INVALID_REQUEST: {
			messageResID = R.string.error_invalid_request;
			break;
		}
		case SESSION_EXPIRED: {
			messageResID = R.string.error_session_expired;
			isSessionExpired = true;
			break;
		}
		case ACCESS_DENIED: {
			messageResID = R.string.error_access_denied;
			isSessionExpired = true;
			break;
		}

		// APP & DEVICE
		case UNAUTHORIZED_PHONE_UID_NUMBER: {
			messageResID = R.string.error_unauthorized_phone_uid_number;
			break;
		}
		case UNREGISTERED_NEW_MULTI_DEVICE: {
			messageResID = R.string.error_unregistered_new_multi_device;
			break;
		}
		case APPVERSION_NOTEQUAL_ERROR: {
			messageResID = R.string.error_appversion_notequal;
			break;
		}

		// LICENSE
		case LICENSE_ERROR_FILENOTFOUND: {
			messageResID = R.string.error_license_filenotfound;
			break;
		}
		case LICENSE_ERROR_INVALIDINFO: {
			messageResID = R.string.error_license_invalid_info;
			break;
		}
		case LICENSE_ERROR_SERVERIP: {
			messageResID = R.string.error_license_invalid_serverip;
			break;
		}
		case LICENSE_ERROR_EXPIRED: {
			messageResID = R.string.error_license_expired;
			break;
		}
		case LICENSE_ERROR_EXCEEDUSER: {
			messageResID = R.string.error_license_exceeduser;
			break;
		}

		// FILE
		case FILE_DOWNLOAD_ERROR: {
			messageResID = R.string.error_file_download;
			break;
		}
		case FILE_TRANSFORM_ERROR: {
			messageResID = R.string.error_file_transform;
			break;
		}
		case FILE_UNSUPPORTED_TYPE_ERROR: {
			messageResID = R.string.error_file_unsupported_type;
			break;
		}

		// MDM
		case MDM_APPLICATION_PERMISSTION_ERROR: {
			messageResID = R.string.error_mdm_application_permission;
			break;
		}
		case MDM_UNAPPROVED_DEVICE_ERROR: {
			messageResID = R.string.error_mdm_unapproved_device;
			break;
		}
		case MDM_DUPLICATED_DEVICE_ERROR: {
			messageResID = R.string.error_mdm_duplicated_device;
			break;
		}
		case MDM_MULTIDEVICE_ERROR: {
			messageResID = R.string.error_mdm_multidevice;
			break;
		}
		case MDM_UNREGISTERED_USER_ERROR: {
			messageResID = R.string.error_mdm_unregistered_user;
			break;
		}
		case MDM_LICENSE_EXCEEDUSER_ERROR: {
			messageResID = R.string.error_mdm_license_exceeduser;
			break;
		}
		case MDM_UNREGISTERED_APP_ERROR: {
			messageResID = R.string.error_mdm_unregistered_app;
			break;
		}
		case MDM_UNEXPECTED_ERROR: {
			messageResID = R.string.error_mdm_unexpected;
			break;
		}
		default: {
			return null;
		}
		}
		return context.getString(messageResID);
	}

	// ToDo: 스트링을 직접 파싱해서 어레이 인지 오브젝트인지 판단해서 처리해야한다;;
	// 뭐 이러냐.. 어레이로 오기도하고 오브젝트로 오기도하고;;;
	// return type: JSONObject or JSONArray or null
	public Object getObjectFromResStr(Activity a, String responseString) {
		return getObjectFromResStr(a, responseString, true);
	}

	public Object getObjectFromResStr(Activity a, String responseString, boolean showErrorMessage) {

		if (responseString == null || TextUtils.equals("timeout", responseString) || TextUtils.equals("error", responseString)) {
			if (showErrorMessage) {
				String message = context.getString(R.string.error_connect_timeout);
				PopupUtil.showDialog(a, message);
			}
			return null;
		}

		JSONObject jObj = null;
		JSONArray jArray = null;
		try {
			if (responseString.charAt(0) == '[') {
				jArray = new JSONArray(responseString);
				return jArray;
			} else {
				jObj = new JSONObject(responseString);
				// tkofs
				String tempCode = jObj.optString("code");
				if (tempCode != null && tempCode != ""){
					if(tempCode.equals(String.valueOf(SESSION_EXPIRED)) || tempCode.equals(String.valueOf(ACCESS_DENIED))) {
						showErrorMessage = true;
					}
				}else{
					showErrorMessage = false;
				}

				if (showErrorMessage) {
					return checkErrorCode(jObj);
				} else {
					return jObj;
				}
			}
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
			String message = "UnKnowError in parsing response data.";
			PopupUtil.showToastMessage(a, message);
			return null;
		}
	}
}
