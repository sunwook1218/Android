package com.hs.mobile.gw.openapi.vo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hs.mobile.gw.openapi.GWErrorCode;
import com.hs.mobile.gw.util.Debug;

public class LoginVO extends DefaultVO<JSONObject> {

	public GWErrorCode code;
	public String uname;
	public String empcode;
	public String start_menu_id;
	public String photo;
	public String id;
	public String mail;
	public boolean isadditionalofficer;
	public boolean skipOtherOffice;
	public boolean appr_approval_enablereceiptdoc;
	public String appr_approve_comment_intodocument;
	public String list_escape_title_use;
	public String doc_transform_server_use;
	public String noti_mps_badge;
	public String mail_search_use;
	public String deptid;
	public String name;
	public String start_menu_title;
	public String start_menu_url;
	public String deptname;
	public String start_menu_custom_id;
	public String key;
	public ConfigVO config;
	public List<HomonymVO> homonym;
	public String message;
	public boolean isguest;
	public AdditionalOfficerVO additionalofficer;
	public List<AuthListVO> authList;

	public LoginVO(JSONObject json) {
		super(json);
		Debug.trace(json.toString());
		code = GWErrorCode.valueOfCode(json.optString("code", null));
		message = json.optString("message");
		if (getJson().has("homonym")) {
			Debug.trace("동명이인 존재");
			homonym = new ArrayList<HomonymVO>();
			JSONArray jarr = getJson().optJSONArray("homonym");
			if (jarr != null) {
				for (int i = 0; i < jarr.length(); ++i) {
					homonym.add(new HomonymVO(jarr.optJSONObject(i)));
				}
			}
		}

		if (json.has("additionalofficer")) {
			additionalofficer = new AdditionalOfficerVO(getJson().optJSONArray("additionalofficer"));
		}
		isguest = getJson().optBoolean("isguest", false);
		uname = getJson().optString("uname");
		empcode = getJson().optString("empcode");
		start_menu_id = getJson().optString("start.menu.id");
		photo = getJson().optString("photo");
		id = getJson().optString("id");
		mail = getJson().optString("mail");
		isadditionalofficer = getJson().optBoolean("isadditionalofficer");
		skipOtherOffice = getJson().optBoolean("skipOtherOffice");
		appr_approval_enablereceiptdoc = getJson().optBoolean("appr.approval.enablereceiptdoc");
		appr_approve_comment_intodocument = getJson().optString("appr.approve.comment.intodocument");
		Debug.trace("appr_approve_comment_intodocument = " + appr_approve_comment_intodocument);
		doc_transform_server_use = getJson().optString("doc.transform.server.use");
		Debug.trace("doc_transform_server_use = " + doc_transform_server_use);
		list_escape_title_use = getJson().optString("list.escape.title.use");
		Debug.trace("list_escape_title_use = " + list_escape_title_use);
		mail_search_use = getJson().optString("mail.search.use");
		noti_mps_badge = getJson().optString("noti.mps.badge");
		deptid = getJson().optString("deptid");
		// what is cn?
		// cn = json.optString("cn");
		name = getJson().optString("name");
		// config
		if (getJson().has("config")) {
			config = new ConfigVO(getJson().optJSONObject("config"));
		}
		start_menu_title = getJson().optString("start.menu.title");
		start_menu_url = getJson().optString("start.menu.url");
		deptname = getJson().optString("deptname");
		start_menu_custom_id = getJson().optString("start.menu.custom.id");
		key = getJson().optString("key");
		
		if (getJson().has("authlist")) {
			authList = new ArrayList<AuthListVO>();
			JSONArray jarr = getJson().optJSONArray("authlist");
			if (jarr != null) {
				for (int i = 0; i < jarr.length(); ++i) {
					authList.add(new AuthListVO(jarr.optJSONObject(i)));
				}
			}
		}
	}
}
