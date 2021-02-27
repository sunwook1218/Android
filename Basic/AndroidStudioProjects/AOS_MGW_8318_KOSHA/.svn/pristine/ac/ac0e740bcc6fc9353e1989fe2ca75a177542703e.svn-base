package com.hs.mobile.gw.openapi.square.vo;

import java.io.Serializable;

import android.text.TextUtils;

public class ShowMailEditorViewVO implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public enum ReadType {
			READ("read", "0"), REPLY("reply", "1"), FORWARD("forward", "2"), REPLYALL("replyall", "3"), NEW("new", "4");
			private String m_strType;
			private String m_strCode;

			ReadType(String strType, String strCode) {
				m_strType = strType;
				m_strCode = strCode;
			}

			public String getType() {
				return m_strType;
			}

			public String getCode() {
				return m_strCode;
			}

			public static ReadType valueOfString(String s) {
				for (ReadType t : values()) {
					if (TextUtils.equals(t.getType(), s)) {
						return t;
					}
				}
				return null;
			}
		}

		public enum BoxId {
			SEND_BOX("000000001"), RECEIVE_BOX("000000002"), DELETE_BOX("000000004"), TEMP_BOX("000000005");
			private String m_boxId;

			BoxId(String s) {
				m_boxId = s;
			}

			@Override
			public String toString() {
				return m_boxId;
			}

			public static BoxId valueOfString(String s) {
				for (BoxId t : values()) {
					if (TextUtils.equals(t.toString(), s)) {
						return t;
					}
				}
				return null;
			}
		}

		private ReadType m_type;
		private BoxId m_boxId;
		private String m_strMessageId;
		private String m_strSelectedlist;
		private boolean m_bPopup;
		private String m_strPasswd;
		private String m_etcBoxId = new String();
		
		public ShowMailEditorViewVO(ReadType type, BoxId boxId, String strMessageId) {
			setType(type);
			setBoxId(boxId);
			setStrMessageId(strMessageId);
		}

		public ShowMailEditorViewVO(String strType, String strBoxId, String strMessageId, String strSelectedlist, String strIsPopup, String strPasswd) {
			setType(ReadType.valueOfString(strType));
			//-- box-id 가 enum 에 정의된 상수 값 외에 개인 편지함처럼 동적으로 생성되는 것은 한글로 전달되어 우회하는 코드를 삽입한다.
			if(BoxId.valueOfString(strBoxId) == null){
				this.m_etcBoxId  = strBoxId;
			}else{
				setBoxId(BoxId.valueOfString(strBoxId));
			}
				
			setStrSelectedlist(strSelectedlist);
			setStrMessageId(strMessageId);
			setPopup(TextUtils.equals("true", strIsPopup));
			setStrPasswd(strPasswd);
		}

		private void setPopup(boolean equals) {
			m_bPopup = equals;
		}
		public boolean isPopup()
		{
			return m_bPopup;
		}
		public ReadType getType() {
			return m_type;
		}

		public void setType(ReadType type) {
			m_type = type;
		}

		public BoxId getBoxId() {
			return m_boxId;
		}
		public String getEtcBoxId(){
			return m_etcBoxId;
		}
		public void setBoxId(BoxId boxId) {
			m_boxId = boxId;
		}

		public String getStrMessageId() {
			return m_strMessageId;
		}

		public void setStrMessageId(String strMessageId) {
			m_strMessageId = strMessageId;
		}
		
		public String getStrPasswd() {
			return m_strPasswd;
		}
		
		public void setStrPasswd(String strPasswd) {
			m_strPasswd = strPasswd;
		}

		public String getStrSelectedlist() {
			return m_strSelectedlist;
		}

		public void setStrSelectedlist(String strSelectedlist) {
			this.m_strSelectedlist = strSelectedlist;
		}
	}