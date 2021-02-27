package com.hs.mobile.gw.openapi;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;

public class ReadEmailResult extends DefaultCallback {

	public String version;
	public ArrayList<Channel> channel = new ArrayList<ReadEmailResult.Channel>();;

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
				cc = json.optString("cc").trim();
				bcc = json.optString("bcc").trim();			
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
				JSONArray attachJsonArr = json.optJSONArray("attaches");
				if (attachJsonArr != null) {
					for (int i = 0; i < attachJsonArr.length(); ++i) {
						attaches.add(new AttachItem(attachJsonArr.optJSONObject(i)));
					}
				}
			}
		}

		public ArrayList<AttachItem> attaches = new ArrayList<ReadEmailResult.Channel.AttachItem>();
		public String to;
		public String cc;
		public String bcc;
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


		public class AttachItem {
			public AttachItem(JSONObject json) {
				att_type = json.optString("att_type");
				att_size = json.optString("att_size");
				att_link2 = json.optString("att_link2");
				att_link = json.optString("att_link");
				att_title = json.optString("att_title");
				att_order = json.optString("att_order");
			}

			public String att_type;
			public String att_size;
			public String att_link2;
			public String att_link;
			public String att_title;
			public String att_order;

		}
	}
}
