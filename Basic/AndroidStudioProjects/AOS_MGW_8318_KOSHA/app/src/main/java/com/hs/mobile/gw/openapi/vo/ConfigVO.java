package com.hs.mobile.gw.openapi.vo;

import org.json.JSONObject;

public class ConfigVO extends DefaultVO<JSONObject> {
	public String mail_directinputuser_use;
	public String attach_filesize_unit;
	public String schedule_type;
	public String sign_doctype;
	public String board_notice_bid;
	public String mail_type;
	public boolean appr_approve_openapi_use;
	public String contact_type;
	public boolean bbs_dm_onlybody_use;
	public boolean appr_approve_junkyul_use;
	public boolean appr_linkdoc_use;
	public boolean appr_instructions_use;
	public String mail_replyall_use;
	public String settings_type;
	public String gw_version;
	public String appr_approve_input_comment_use;
	public String ip_checkrange_use;
	public int commmon_count_pooling_interval;

	public ConfigVO(JSONObject optJSONObject) {
		super(optJSONObject);
		mail_directinputuser_use = getJson().optString("mail.directinputuser.use");
		attach_filesize_unit = getJson().optString("attach.filesize.unit");
		schedule_type = getJson().optString("schedule.type");
		sign_doctype = getJson().optString("sign.doctype");
		board_notice_bid = getJson().optString("board.notice.bid");
		mail_type = getJson().optString("mail.type");
		appr_approve_openapi_use = getJson().optBoolean("appr.approve.openapi.use");
		contact_type = getJson().optString("contact.type");
		bbs_dm_onlybody_use = getJson().optBoolean("bbs.dm.onlybody.use");
		appr_approve_junkyul_use = getJson().optBoolean("appr.approve.junkyul.use");
		appr_linkdoc_use = getJson().optBoolean("appr.linkdoc.use");
		appr_instructions_use = getJson().optBoolean("appr.instructions.use");
		mail_replyall_use = getJson().optString("mail.replyall.use");
		settings_type = getJson().optString("settings.type");
		gw_version = getJson().optString("gw.version");
		appr_approve_input_comment_use = getJson().optString("appr.approve.input.comment.use");
		ip_checkrange_use = getJson().optString("ip.checkrange.use");
		commmon_count_pooling_interval = getJson().optInt("commmon.count.pooling.interval");
	}

}
