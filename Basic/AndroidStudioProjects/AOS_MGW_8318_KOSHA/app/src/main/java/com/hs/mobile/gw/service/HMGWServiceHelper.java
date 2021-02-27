package com.hs.mobile.gw.service;

import java.util.List;

import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.Define.LoginType;

public class HMGWServiceHelper {

	// Constants
	public static final String USER_AGENT = "Mozilla/5.0 (Linux; U; Android; ko-kr; Build) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
	public static final String GCM_SENDER_ID = Define.GCM_SENDER_ID;
	public static final boolean USE_CRYPTO = Define.USE_CRYPTO;
	public static final boolean USE_ONLY_PWD_CRYPTO = Define.USE_ONLY_PWD_CRYPTO;
	public static final LoginType LOGIN_TYPE = Define.LOGIN_TYPE;

	// 사번 로그인 디폴트 처리 여부
	public static final boolean USE_EMPCODE_MODE = Define.USE_EMPCODE_MODE;

	// Push 알리미 사용 여부
	public static final boolean USE_PUSH_SERVICE = Define.USE_PUSH_SERVICE;

	public static final String phoneOSType = "android";
	public static final int LOADING_TIMEOUT = 20;// second

	// 메뉴 상단 프로필 사진 표시 여부
	public static final boolean VIEW_PROFILE_PHOTO = true;

	// Common...
	public static String deviceId;
	public static String tokenId;
	public static String phoneNumber;
	public static String phoneOSVersion;
	public static String webServerURL;
	public static List<Cookie> cookies = null;
	public static String selectUserId;
	public static String userId;
	public static String mail;
	public static boolean hasAdditionalOfficer = false;
	public static String deptId;
	public static String uname;
	public static String name;
	public static String empcode;
	public static String password;
	public static String key;
	public static String deptName;
	public static String photoLink;
	public static boolean isGuest = false;
	public static String gw_version;
	public static String start_menu_id;
	public static String start_menu_title;
	public static String start_menu_url;
	public static String start_menu_custom_id;
	public static String mail_type;
	public static String schedule_type;
	public static String sign_doctype;
	public static String contact_type;
	public static String settings_type;
	public static String mail_search_use;
	public static String noti_mps_badge;
	public static String mail_directinputuser_use;
	public static String mail_replyall_use;
	public static boolean doc_transform_server_use;
	public static boolean list_escape_title_use;
	public static boolean bbs_dm_onlybody_use;
	public static boolean appr_linkdoc_use;
	public static boolean appr_approve_input_comment_use;
	public static boolean appr_approve_openapi_use;
	public static boolean appr_auth = false;
	public static boolean appr_approve_comment_intodocument = false;
	public static int count_pooling_interval = 60;
	public static int devicetype; // 0:phone, 1:tablet
	public static JSONArray additionalusers;

    public static JSONArray mgw_home_bbs_menu;    // home 게시판 메뉴 정보
	public static final String attachDownFolder = "/mgw/";

	public static void resetLoginInfo() {
		hasAdditionalOfficer = false;
		cookies = null;
		isGuest = false;
		deptId = null;
		uname = null;
		name = null;
		empcode = null;
		key = null;
		deptName = null;
		photoLink = null;
		userId = null;
	}

	public static JSONArray mgw_menu;

	public static JSONObject config;

	public static String notice_bid;


	//tkofs 세션종료시 확인창 증식 막기
	public static boolean forceLogoutFlag = true;
	public static boolean LogoutFlag = true;

	// otp 캔슬시 자동 로그인 캔슬하기위함 1 = OTP 사용, 2 = 인증성공
	public static int otp_cancle = 0;

	// OpenAPI
	public static class OpenAPI {
		// 80 or 443 Port 일 경우 Port 넣지말것
		// public static String SERVER_URL =
		// "http://so8mobile.handysoft.co.kr:8090/mgw";
		// public static String SERVER_URL = "http://10.30.4.214:8080/mgw";
		// public static String SERVER_URL = "http://123.212.190.161:8021/mgw";
		public static String SERVER_URL = Define.SERVER_URL;// =
		// "http://mobile.handysoft.co.kr:28080/mgw";
		// public static String SERVER_URL =
		// "https://mobile.handysoft.co.kr:3443/mgw";

		public static int MAX_CONNECTION_TIMEOUT = 20000;// (ms)
		public static String CONFIG_MAILLIST_PAGESIZE = "15";

		public static String LOGIN = SERVER_URL + "/rest/auth/login";
		public static String LOGOUT = SERVER_URL + "/rest/auth/logout";
		public static String LOGIN_ADDITIONALOFFICER = SERVER_URL + "/rest/auth/loginotherofficer";
		public static String REGIST_MULTI_DEVICE = SERVER_URL + "/rest/auth/result/registmultidevice";

		public static String MAILLIST = SERVER_URL + "/rest/openapi/mail/list";
		public static String MAILSEARCH = SERVER_URL + "/rest/openapi/mail/search";
		public static String MAILDELETE = SERVER_URL + "/rest/openapi/mail/delete";
		public static String MAIL_PERSONALBOX = SERVER_URL + "/rest/openapi/mail/personalbox";

		public static String BOARD_RECENTLIST = SERVER_URL + "/rest/openapi/board/recentlist";
		public static String BOARD_LIST = SERVER_URL + "/rest/openapi/board/list";
		public static String BOARD_FOLDER = SERVER_URL + "/rest/openapi/board/folder";
		public static String BOARD_FAVFOLDER = SERVER_URL + "/rest/openapi/board/favfolder";
		public static String BOARD_NOTICE_LIST = SERVER_URL + "/rest/openapi/board/noticelist";

		public static String SIGN_WAITLIST = SERVER_URL + "/rest/openapi/appr/list?type=wait";
		public static String SIGN_INPROGESS = SERVER_URL + "/rest/openapi/appr/list?type=now";
		public static String SIGN_REJECT = SERVER_URL + "/rest/openapi/appr/list?type=reject";		
		public static String SIGN_COMPLETE_TAGFREE_HWP6 = SERVER_URL + "/rest/openapi/appr/list?type=mycomplete";
		public static String SIGN_MY_COMPLETE_HTML_HWP8 = SERVER_URL + "/rest/openapi/appr/list?type=userprocessed";
		public static String SIGN_COMPLETE_BOX = SERVER_URL + "/rest/openapi/appr/list?type=complete";
		public static String SIGN_DRAFT_BOX = SERVER_URL + "/rest/openapi/appr/list?type=draft";
		public static String SIGN_RECEIPT_WAIT = SERVER_URL + "/rest/openapi/appr/list?type=receiptwait";
		public static String SIGN_GONGRAM_WAIT = SERVER_URL + "/rest/openapi/appr/list?type=gongram";
		public static String SIGN_GONGRAM_COMPLETE = SERVER_URL + "/rest/openapi/appr/list?type=gongramcomplete";
		public static String SIGN_COMPLETE_INFORMAL = SERVER_URL + "/rest/openapi/appr/list?type=complete_informal";
		public static String SIGN_CHECKDOCVIEW = SERVER_URL + "/rest/openapi/appr/checkdocview";
		public static String SIGN_SEARCH = SERVER_URL + "/rest/openapi/appr/search";
		public static String SIGN_SENDING_PROCESS = SERVER_URL + "/rest/openapi/appr/list?type=dispatch";

		public static String CONTACT_USER = SERVER_URL + "/rest/openapi/contact/list?type=user";
		public static String CONTACT_DEPT = SERVER_URL + "/rest/openapi/contact/list?type=dept";
		public static String CONTACT_GROUP = SERVER_URL + "/rest/openapi/contact/list?type=group";
		public static String CONTACT_GROUPLIST = SERVER_URL + "/rest/openapi/contact/group";
		public static String CONTACT_SEARCH = SERVER_URL + "/rest/openapi/contact/search";

		public static String CHECK_PASSWORD = SERVER_URL + "/rest/openapi/org/checkpasswd";
		public static String ENV_USERINFO = SERVER_URL + "/rest/openapi/env/userinfo";

		public static String COUNTS = SERVER_URL + "/rest/openapi/counts";
		
		public static String DOC_HANDLER = SERVER_URL + "/rest/doc/handle";
		public static String DOC_HANDLER_PROXY = SERVER_URL + "/rest/doc/proxy";

		public static String MENU_INFO = SERVER_URL + "/rest/openapi/menuinfo";
		public static String ICON_INFO = SERVER_URL + "/rest/openapi/iconinfo";
		public static String ICON_DOWNLOAD = SERVER_URL + "/rest/openapi/geticon";

		public static String PHOTO_URL = "/jsp/org/view/ViewPicture.jsp";
		public static String OPEN_API_URL = "/jsp/openapi/OpenApi.jsp";

		public OpenAPI() {
			SERVER_URL = Define.SERVER_URL;

			LOGIN = SERVER_URL + "/rest/auth/login";
			LOGOUT = SERVER_URL + "/rest/auth/logout";
			LOGIN_ADDITIONALOFFICER = SERVER_URL + "/rest/auth/loginotherofficer";
			REGIST_MULTI_DEVICE = SERVER_URL + "/rest/auth/result/registmultidevice";

			MAILLIST = SERVER_URL + "/rest/openapi/mail/list";
			MAILSEARCH = SERVER_URL + "/rest/openapi/mail/search";
			MAILDELETE = SERVER_URL + "/rest/openapi/mail/delete";
			MAIL_PERSONALBOX = SERVER_URL + "/rest/openapi/mail/personalbox";

			BOARD_RECENTLIST = SERVER_URL + "/rest/openapi/board/recentlist";
			BOARD_LIST = SERVER_URL + "/rest/openapi/board/list";
			BOARD_FOLDER = SERVER_URL + "/rest/openapi/board/folder";
			BOARD_FAVFOLDER = SERVER_URL + "/rest/openapi/board/favfolder";
			BOARD_NOTICE_LIST = SERVER_URL + "/rest/openapi/board/noticelist";

			SIGN_WAITLIST = SERVER_URL + "/rest/openapi/appr/list?type=wait";
			SIGN_INPROGESS = SERVER_URL + "/rest/openapi/appr/list?type=now";
			SIGN_REJECT = SERVER_URL + "/rest/openapi/appr/list?type=reject";		
			SIGN_COMPLETE_TAGFREE_HWP6 = SERVER_URL + "/rest/openapi/appr/list?type=mycomplete";
			SIGN_MY_COMPLETE_HTML_HWP8 = SERVER_URL + "/rest/openapi/appr/list?type=userprocessed";
			SIGN_COMPLETE_BOX = SERVER_URL + "/rest/openapi/appr/list?type=complete";
			
			SIGN_DRAFT_BOX = SERVER_URL + "/rest/openapi/appr/list?type=draft";
			SIGN_RECEIPT_WAIT = SERVER_URL + "/rest/openapi/appr/list?type=receiptwait";
			SIGN_GONGRAM_WAIT = SERVER_URL + "/rest/openapi/appr/list?type=gongram";
			SIGN_CHECKDOCVIEW = SERVER_URL + "/rest/openapi/appr/checkdocview";
			SIGN_SEARCH = SERVER_URL + "/rest/openapi/appr/search";
			SIGN_SENDING_PROCESS = SERVER_URL + "/rest/openapi/appr/list?type=dispatch";

			CONTACT_USER = SERVER_URL + "/rest/openapi/contact/list?type=user";
			CONTACT_DEPT = SERVER_URL + "/rest/openapi/contact/list?type=dept";
			CONTACT_GROUP = SERVER_URL + "/rest/openapi/contact/list?type=group";
			CONTACT_GROUPLIST = SERVER_URL + "/rest/openapi/contact/group";
			CONTACT_SEARCH = SERVER_URL + "/rest/openapi/contact/search";

			CHECK_PASSWORD = SERVER_URL + "/rest/openapi/org/checkpasswd";

			COUNTS = SERVER_URL + "/rest/openapi/counts";
			
			DOC_HANDLER = SERVER_URL + "/rest/doc/handle";
			DOC_HANDLER_PROXY = SERVER_URL + "/rest/doc/proxy";

			MENU_INFO = SERVER_URL + "/rest/openapi/menuinfo";
			ICON_INFO = SERVER_URL + "/rest/openapi/iconinfo";
			ICON_DOWNLOAD = SERVER_URL + "/rest/openapi/geticon";

			PHOTO_URL = "/jsp/org/view/ViewPicture.jsp";
			
			OPEN_API_URL = "/jsp/openapi/OpenApi.jsp";
		}
	}
}