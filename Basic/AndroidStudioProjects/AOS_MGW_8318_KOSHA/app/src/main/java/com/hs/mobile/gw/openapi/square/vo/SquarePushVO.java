package com.hs.mobile.gw.openapi.square.vo;

import org.json.JSONObject;

import android.text.TextUtils;

public class SquarePushVO {
	public enum SquarePushAction {
		GROUP_ADD("0"), GROUP_MODIFY("1"), GROUP_DELETE("2"), CONTENTS_ADD("3"), CONTENTS_MODIFY("4"), CONTENTS_DELETE("5"), COMMENT_ADD(
				"6"), WORK_COMPLETE("7"), WORK_COMPLETE_CANCEL("8"), ORDER_WORK_COMPLETE("9"), ORDER_WORK_COMPLETE_CANCEL("10"), SQUARE_MEMBER_CHANGE(
				"11"), SQURE_MEMBER_ADMIN_CHANGE("12"), GROUP_REOPEN("13"),ALERT_ACTION_BROADCAST("14");

		private String m_value;

		private SquarePushAction(String s) {
			m_value = s;
		}

		public static SquarePushAction valueOfCode(String optString) {
			for (SquarePushAction action : values()) {
				if (TextUtils.equals(action.m_value, optString)) {
					return action;
				}
			}
			return null;
		}
	}

	public String square_msg;
	public String square_id;
	public String square_contents_id;
	public String square_parent_id;
	public SquarePushAction square_action;
	private JSONObject m_json;
	public String square_original_parent_id;

	public SquarePushVO(JSONObject json) {
		if (json != null) {
			m_json = json;
			square_msg = json.optString("square_msg");
			square_id = json.optString("square_id");
			square_contents_id = json.optString("square_contents_id");
			square_parent_id = json.optString("square_parent_id");
			square_original_parent_id = json.optString("square_original_parent_id");
			square_action = SquarePushAction.valueOfCode(json.optString("square_action"));
		}
	}

	public JSONObject getJson() {
		return m_json;
	}

}
