package com.hs.mobile.gw.util;

import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;

import android.text.TextUtils;

public class MGWUtils {
	public static String getTypeStringByApiCode(ApiCode code) {
		String type = null;
		switch (code) {
			case mail_recvlist: {
				type = "recvlist";
				break;
			}
			case mail_selfboxlist: {
				type = "selfboxlist";
				break;
			}
			case mail_sendlist: {
				type = "sendlist";
				break;
			}
			case mail_deletelist: {
				type = "deletelist";
				break;
			}
			case mail_templist: {
				type = "templist";
				break;
			}
			case mail_personallist: {
				type = "personallist";
				break;
			}

			case contact_group: {
				type = "group";
				break;
			}
			case contact_dept: {
				type = "dept";
				break;
			}
			case contact_user: {
				type = "personal";
				break;
			}

			default:
			break;
		}

		return type;
	}

	public static String getContactSearchType(ApiCode code) {
		String type = null;
		switch (code) {
			case contact_group: {
				type = "group";
				break;
			}
			case contact_dept: {
				type = "dept";
				break;
			}
			case contact_user: {
				type = "user";
				break;
			}

			default:
			break;
		}

		return type;
	}

	public static String getSignApplIDByApiCode(ApiCode code) {
		String applID = null;
		switch (code) {
			case sign_inprogess: {
				applID = "now";
				break;
			}
			case sign_reject: {
				applID = "reject";
				break;
			}
			case sign_waitlist: {
				applID = "wait";
				break;
			}
			case sending_process: {
				applID = "dispatch";
				break;
			}
			case sign_gongram_waiting: {
				applID = "gongram";
				break;
			}
			case sign_gongram_complete: {
				applID = "gongramcomplete";
				break;
			}
			case sign_my_complete: {
				if (TextUtils.equals(HMGWServiceHelper.sign_doctype, "html")) { // 버전
																				// 8
					applID = "mycomplete";
				} else {// 버전 6, 7
					applID = "userprocessed";
				}
				break;
			}
			case sign_complete_box: {
				applID = "complete";
				break;
			}
			case sign_receipt_wait: {
				applID = "receiptwait";
				break;
			}
			case sign_draft_box: {
				applID = "draft";
				break;
			}
			default:
			break;
		}
		return applID;
	}

	public static String getSearchMailTitleByApiCode(ApiCode code) {
		String type = null;
		switch (code) {
			case mail_recvlist: {
				type = "recvlist";
				break;
			}
			case mail_selfboxlist: {
				type = "selfboxlist";
				break;
			}
			case mail_sendlist: {
				type = "sendlist";
				break;
			}
			case mail_deletelist: {
				type = "deletelist";
				break;
			}
			case mail_templist: {
				type = "templist";
				break;
			}
			case mail_personallist: {
				type = "personallist";
				break;
			}
			default:
			break;
		}

		return type;
	}
}
