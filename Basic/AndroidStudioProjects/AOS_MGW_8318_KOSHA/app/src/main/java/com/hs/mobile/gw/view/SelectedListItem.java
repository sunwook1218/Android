package com.hs.mobile.gw.view;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.util.Debug;

import android.text.TextUtils;

public class SelectedListItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ObjectType {
		USER("@", "user"), DEPARTMENT_WITH_CHILDREN("$+", "dept_low"), DEPARTMENT("$", "dept"), CONTACTS_GROUP("#", ""), EMAIL("%", "email"), MAIL_GROUP(
				"*", ""), POSITION("~", "");
		private String m_strSimbol;
		private String m_strName;

		ObjectType(String strSimbol, String strName) {
			m_strSimbol = strSimbol;
			m_strName = strName;
		}

		@Override
		public String toString() {
			return m_strSimbol;
		}
		
		public String getName() {
			return m_strName;
		}

		public static ObjectType valueOfSimbol(String optString) {
			for (ObjectType t : values()) {
				if (TextUtils.equals(t.m_strSimbol, optString)) {
					return t;
				}
			}
			return null;
		}

		public static ObjectType valueOfName(String strObjectType) {
			for (ObjectType t : values()) {
				if (TextUtils.equals(t.m_strName, strObjectType)) {
					return t;
				}
			}
			return null;
		}
	}

	public enum RecipientType {
		TO(0), CC(1), BCC(2);
		private int m_nType;

		RecipientType(int n) {
			m_nType = n;
		}

		public int getRecipientType() {
			return m_nType;
		}
	}

	public String id;
	public RecipientType recipientType;
	public String name;
	public String email;
	public ObjectType objectType;
	public String dept_name;
	public boolean recursive;

	/**
	 * <pre>
	 * SearchAddress를 통해 받은 데이터는 아래와 같다.
	 * 
	 * 	 "eng_name": "Park Jisook",
	 * 	 "linkage": "groupware",
	 * 	 "id": "500446007",
	 * 	 "dept_name": "핸디그룹.핸디소프트(주).연구개발본부.제품지원연구소.BPM\/Mobile팀",
	 * 	 "type": "@",
	 * 	 "email": "jspark@handysoft.co.kr",
	 * 	 "name": "박지숙"
	 * </pre>
	 * 
	 * @param jsonObject
	 */
	public SelectedListItem(JSONObject jsonObject) {
		id = jsonObject.optString("id",jsonObject.optString("Id"));
		email = jsonObject.optString("email");
		objectType = computeObjectType(jsonObject.optString("type"));
		name = jsonObject.optString("name");
		dept_name = jsonObject.optString("dept_name");
		recursive = jsonObject.optBoolean("recursive");

		if (ObjectType.DEPARTMENT.equals(objectType)) { //low rank dept re-setting
			if (recursive) 
				objectType = ObjectType.DEPARTMENT_WITH_CHILDREN;
			name = objectType.toString().concat(name);
		}
	}

	public SelectedListItem(String strId, String strEmail, String strObjectType, String strName) {
		id = strId;
		email = strEmail;
		objectType = computeObjectType(strObjectType);
		name = strName;
	}
	public SelectedListItem(String strId, String strEmail, String strObjectType, String strName, String strDept) {
		id = strId;
		email = strEmail;
		objectType = computeObjectType(strObjectType);
		name = strName;
		dept_name = strDept; 
	}

	private ObjectType computeObjectType(String strObjectType) {
		if (strObjectType == null) {
			return null;
		} else {
			ObjectType retObjType = ObjectType.valueOfSimbol(strObjectType);
			if (retObjType == null) {
				retObjType = ObjectType.valueOfName(strObjectType);
			}
			
			if (retObjType == null) {
				if (TextUtils.equals("department", strObjectType) || TextUtils.equals("personal", strObjectType)) {
					retObjType = ObjectType.EMAIL;
				}
			}

			return retObjType;
		}
	}

	public JSONObject toJsonObject() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", id);
			json.put("email", email);
			json.put("name", name);
			if (objectType != null) {
				json.put("objectType", objectType.toString());
			}
			json.put("recipientType", recipientType.getRecipientType());
			return json;
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}

	public JSONObject toORGJsonObject() {
		JSONObject json = new JSONObject();
		try {
			json.put("id", id);
			json.put("email", email);

			if (name.trim().startsWith("$+")) {
				name = name.substring(2);
				recursive = true;
			} else if (name.trim().startsWith("$")) {
				name = name.substring(1);
			}
			json.put("name", name);
			if (objectType != null) {
				if (objectType == ObjectType.DEPARTMENT_WITH_CHILDREN) {
					objectType = ObjectType.DEPARTMENT;
				}
				json.put("type", objectType.getName());
			}
			json.put("recursive", recursive);
			return json;
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}
		return null;
	}
}