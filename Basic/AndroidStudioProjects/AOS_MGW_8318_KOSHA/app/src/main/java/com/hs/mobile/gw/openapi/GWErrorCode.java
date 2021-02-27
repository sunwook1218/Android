package com.hs.mobile.gw.openapi;

import com.hs.mobile.gw.hsuco.R;

import android.text.TextUtils;

public enum GWErrorCode {
	SUCCESS_0("0", 0), 
	SUCCESS_200("200", 0), 
	INVALID_PASSWORD_ERROR("101", R.string.error_account_password), 
	UNREGISTERED_USER_ERROR("102",R.string.error_account_unregistered), 
	HOMONYM_EXIST_ERROR("103", 0), 
	UNREGISTERED_IP_ERROR("104",R.string.error_ip_unregistered), 
	ALREADYLOGIN_EXIST("112", R.string.error_alreadylogin), 
	UNREGISTERED_NEW_MULTI_DEVICE("1012", R.string.error_unregistered_new_multi_device), 
	APPVERSION_NOTEQUAL_ERROR("1021", R.string.error_appversion_notequal),
	ACCESS_DENIED_ERROR("999",R.string.error_access_error),
	SYSTEM_ERROR("1001",R.string.error_system_error),
	INVALID_REQUEST("1002",R.string.error_invalid_request),
	SESSION_EXPIRED("1003",R.string.error_session_expired),
	ACCESS_DENIED("1004",R.string.error_access_denied),
	UNAUTHORIZED_PHONE_UID_NUMBER("1011",R.string.error_unauthorized_phone_uid_number),
	LICENSE_ERROR_FILENOTFOUND("1101",R.string.error_license_filenotfound),
	LICENSE_ERROR_INVALIDINFO("1102",R.string.error_license_invalid_info),
	LICENSE_ERROR_SERVERIP("1103",R.string.error_license_invalid_serverip),
	LICENSE_ERROR_EXPIRED("1104",R.string.error_license_expired),
	LICENSE_ERROR_EXCEEDUSER("1105",R.string.error_license_exceeduser),
	FILE_DOWNLOAD_ERROR("1201",R.string.error_file_download),
	FILE_TRANSFORM_ERROR("1202",R.string.error_file_transform),
	FILE_UNSUPPORTED_TYPE_ERROR("1203",R.string.error_file_unsupported_type),
	MDM_APPLICATION_PERMISSTION_ERROR("1301",R.string.error_mdm_application_permission),
	MDM_UNAPPROVED_DEVICE_ERROR("1302",R.string.error_mdm_unapproved_device),
	MDM_DUPLICATED_DEVICE_ERROR("1303",R.string.error_mdm_duplicated_device),
	MDM_MULTIDEVICE_ERROR("1304",R.string.error_mdm_multidevice),
	MDM_UNREGISTERED_USER_ERROR("1305",R.string.error_mdm_unregistered_user),
	MDM_LICENSE_EXCEEDUSER_ERROR("1306",R.string.error_mdm_license_exceeduser),
	MDM_UNREGISTERED_APP_ERROR("1307",R.string.error_mdm_unregistered_app),
	MDM_UNEXPECTED_ERROR("1308",R.string.error_mdm_unexpected),
	// tkofs 106, 105 코드 추가
	SERVICE_EXPIRED("106", R.string.error_service_expired),
	PASSWORD_ERROR_EXCEED("105", R.string.password_error_exceed),
	NETWORK_ERROR("", R.string.message_not_connection);

	private String m_code;
	private int m_nErrorRes;

	private GWErrorCode(String s, int nErrorRes) {
		m_code = s;
		m_nErrorRes = nErrorRes;
	}

	public static final int NONE = -1;
	public String getCode() {
		return m_code;
	}

	public static GWErrorCode valueOfCode(String s) {
		for (GWErrorCode c : values()) {
			if (TextUtils.equals(s, c.getCode())) {
				return c;
			}
		}
		return null;
	}

	public int getErrorRes() {
		return m_nErrorRes;
	}
}