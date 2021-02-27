package com.hs.mobile.gw.util;

import java.io.IOException;
import java.io.InputStream;

public class PropertiesUtils {
	public enum PropertiesKey {
		SERVER_URL("server_url"), GCM_SENDER_ID("gcm_sender_id"), USE_CRYPTO("use_crypto"), USE_ONLY_PWD_CRYPTO("use_only_pwd_crypto"), LOGIN_TYPE(
				"login_type"), USE_EMPCODE_MODE("use_empcode_mode"), USE_PUSH_SERVICE("use_push_service");
		private String m_keyword;

		private PropertiesKey(String s) {
			m_keyword = s;
		}

		@Override
		public String toString() {
			return m_keyword;
		}
	}

	private static volatile PropertiesUtils instance = null;
	private EncryptedProperties prop;

	public PropertiesUtils() {
		InputStream resourceAsStream = PropertiesUtils.class.getResourceAsStream("/mgw_config.properties");
		try {
			prop = new EncryptedProperties("Handysoft20150430");
			prop.load(resourceAsStream);
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		} catch (Exception e) {
			Debug.trace(e.getMessage());
		}
	}

	public String getStringProperty(PropertiesKey p) {
		return prop.getProperty(p.toString(), "");
	}

	public boolean getBooleanProperty(PropertiesKey p) {
		return Boolean.valueOf(getStringProperty(p));
	}

	public static PropertiesUtils getInstance() {
		synchronized (PropertiesUtils.class) {
			if (instance == null) {
				instance = new PropertiesUtils();
			}
		}
		return instance;
	}
}
