package com.hs.mobile.gw.fragment.mailwrite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.openapi.ReadEmailResult;
import com.hs.mobile.gw.openapi.ReadEmailResult.Channel.AttachItem;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.SelectedListItem.RecipientType;

import android.text.TextUtils;

public class SendEmailParameterFactory {
	
	public static class Builder {
		private List<SelectedListItem> m_sendList;
		private boolean m_bWithOriginalMessage;
		private String m_strOriginalMessage;
		private List<AttachItem> m_attachs;
		private HashMap<String, String> m_params;
		private String m_strContent;
		private boolean m_bAttachReceivedFile = false;
		private String m_strMailID;
		private String m_strBoxId;
		private ArrayList<AttachItem> m_originalAttachFileData;

		public Builder() {
			m_sendList = new ArrayList<SelectedListItem>();
			m_params = new HashMap<String, String>();
			m_attachs = new ArrayList<ReadEmailResult.Channel.AttachItem>();
		}

		public Builder setMySelfMail(boolean checked) {
			m_params.put("isMySelfMail", checked ? "true" : "false");
			return this;
		}

		public Builder setSecure(boolean checked) {
			m_params.put("secure", checked ? "true" : "false");
			return this;
		}

		public Builder setEmergency(boolean checked) {
			m_params.put("priority", checked ? "1" : "3");
			return this;
		}
		
		public Builder setExcludingSender(boolean checked) {
			m_params.put("excludingsender", checked ? "true" : "false");
			return this;
		}

		public Builder setTitle(String strTitle) {
			m_params.put("subject", strTitle);
			return this;
		}

		public Builder setContent(String strContent) {
			m_strContent = strContent;
			return this;
		}

		public SendmailParameterVO create() {
			// 참여자 처리
			StringBuilder sbTo = new StringBuilder();
			StringBuilder sbCC = new StringBuilder();
			StringBuilder sbBCC = new StringBuilder();
			StringBuilder sbHwTo = new StringBuilder();
			StringBuilder sbHwCC = new StringBuilder();
			StringBuilder sbHwBCC = new StringBuilder();

			JSONArray recipientsJsonArr = new JSONArray();
			for (SelectedListItem item : m_sendList) {
				StringBuilder sb = null;
				StringBuilder hwsb = null;
				if(item!=null){
					switch (item.recipientType) {
						case BCC:
							sb = sbBCC;
							hwsb = sbHwBCC;
							break;
						case CC:
							sb = sbCC;
							hwsb = sbHwCC;
							break;
						case TO:
							sb = sbTo;
							hwsb = sbHwTo;
							break;
					}
					
					switch (item.objectType) {
						case CONTACTS_GROUP:
							break;
						case DEPARTMENT:
							if(sb!=null)sb.append(item.objectType.toString()).append(item.id);
							if(hwsb!=null)hwsb.append(item.name);
							break;
						case DEPARTMENT_WITH_CHILDREN:
							if(sb!=null)sb.append(item.objectType.toString()).append(item.id);
							if(hwsb!=null)hwsb.append(item.name);
							break;
						case EMAIL:
							if(sb!=null)sb.append(item.email);
							//-- 외부 메일로 보낼 때 name에 값이 들어가지 않도록 처리함					
							if(hwsb!=null)hwsb.append(item.name);
							item.name = "";
							break;
						case MAIL_GROUP:
							break;
						case POSITION:
							break;
						case USER:
							if(sb!=null)sb.append(item.objectType.toString()).append(item.id);
							if(hwsb!=null)hwsb.append(item.name);
							break;
					}
					if(sb!=null)sb.append(',');
					if(hwsb!=null)hwsb.append(',');
					recipientsJsonArr.put(item.toJsonObject());
				}
			}
			try {
				// 참여자 jsondata 처리
				JSONObject recipientsJson = new JSONObject().put("recipients", recipientsJsonArr);
				Debug.trace(recipientsJson.toString(5));
				m_params.put("jsondata", recipientsJson.toString());
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
				return null;
			}
			m_params.put("to", sbTo.toString());
			m_params.put("hwto", sbHwTo.toString());
			m_params.put("cc", sbCC.toString());
			m_params.put("hwcc", sbHwCC.toString());
			m_params.put("bcc", sbBCC.toString());
			m_params.put("hwbcc", sbHwBCC.toString());

			// 내용 처리
			if (m_bWithOriginalMessage && !TextUtils.isEmpty(this.m_strOriginalMessage)) {
				m_strContent += m_strOriginalMessage;
			}
			if (TextUtils.isEmpty(m_strContent)) {
				m_strContent = " ";
			}
			m_params.put("bodytext", m_strContent);

			// 기존 첨부파일 처리
			if (m_bAttachReceivedFile && !TextUtils.isEmpty(this.m_strMailID)) {
				m_params.put("boxid", m_strBoxId);
				m_params.put("number", this.m_strMailID);
				for (int i = 0; i < m_attachs.size(); ++i) {
					m_params.put("parts".concat(String.valueOf((Integer) m_originalAttachFileData.indexOf(m_attachs.get(i)) + 1)),
							m_attachs.get(i).att_order);
				}
			}

			String[] keys = m_params.keySet().toArray(new String[m_params.keySet().size()]);
			String[] values = m_params.values().toArray(new String[m_params.values().size()]);
			return new SendmailParameterVO(keys, values);
		}

		public Builder setWithOriginalMessage(boolean checked) {
			m_bWithOriginalMessage = checked;
			return this;
		}

		public Builder setOriginalMessage(String strOrgMessage) {
			this.m_strOriginalMessage = strOrgMessage;
			return this;
		}

		private void appendRecipientType(RecipientType recipientType, SelectedListItem[] array) {
			for (SelectedListItem item : array) {
				if (item != null) {
					item.recipientType = recipientType;
				}
			}
		}

		public Builder addRecipientList(List<Object> objects, RecipientType t) {
			if (objects != null && objects.size() > 0) {
				SelectedListItem[] l = Arrays.asList(objects.toArray()).toArray(new SelectedListItem[objects.size()]);
				appendRecipientType(t, l);
				m_sendList.addAll(new ArrayList<SelectedListItem>(Arrays.asList(l)));
			}
			return this;
		}

		public Builder addReceivedAttachFile(AttachItem item) {
			m_attachs.add(item);
			return this;
		}

		public void setReceivedAttachFileCount(int size) {
			if (size > 0) {
				m_bAttachReceivedFile = true;
			}
			m_params.put("partscount", String.valueOf(size));
		}

		public Builder setNumber(String strMailID) {
			// 기존 메일 아이디 전달
			this.m_strMailID = strMailID;
			return this;
		}

		public Builder setBoxId(String strBoxId) {
			this.m_strBoxId = strBoxId;
			return this;
		}

		public void setOriginalAttachFileData(ArrayList<AttachItem> attachItems) {
			m_originalAttachFileData = attachItems;
		}
	}

}