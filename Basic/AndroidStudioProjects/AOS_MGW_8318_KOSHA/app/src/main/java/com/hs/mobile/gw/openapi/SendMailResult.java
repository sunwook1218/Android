package com.hs.mobile.gw.openapi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;

public class SendMailResult extends DefaultCallback {
	public String version;
	public ArrayList<Channel> channel = new ArrayList<SendMailResult.Channel>();;

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if (!channel.isEmpty()) {
			channel.clear();
		}
		try {
			JSONObject json = new JSONObject(getResponseString());
			version = json.optString("@version");
			JSONArray arr = json.optJSONArray("channel");
			if (arr != null) {
				for (int i = 0; i < arr.length(); ++i) {
					Channel item = new Channel(arr.optJSONObject(i));
					if (item != null) {
						channel.add(item);
					}
				}
			}
			Debug.trace(json.toString(5));
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
	}

	public class Channel {
		public Channel(JSONObject json) {
			if (json != null) {
				to = json.optString("to").trim();
				senderid = json.optString("senderid").trim();
				sender = json.optString("sender").trim();
				mailid = json.optString("mailid").trim();
				body = json.optString("body").trim();
				title = json.optString("title").trim();
				msgid = json.optString("msgid").trim();
				priority = json.optString("priority").trim();
				sentdate = json.optString("sentdate").trim();
				security = json.optString("security").trim();
				sentdateformat = json.optString("sentdateformat").trim();
				boxid = json.optString("boxid").trim();
			}
		}

		public String to;
		public String senderid;
		public String sender;
		public String mailid;
		public String body;
		public String title;
		public String msgid;
		public String priority;
		public String sentdate;
		public String security;
		public String sentdateformat;
		public String boxid;
	}
}
