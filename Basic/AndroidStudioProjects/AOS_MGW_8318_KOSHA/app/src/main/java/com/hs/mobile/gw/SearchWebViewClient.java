package com.hs.mobile.gw;

import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.GetDocViewAuth;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.BundleUtils.Builder;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;
import com.hs.mobile.gw.util.MGWUtils;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class SearchWebViewClient extends WebViewClient {
	private static final String PROTOCOL_SHOWPOPUP = "showpopup";
	private Activity m_activity;

	public SearchWebViewClient(Activity activity) {
		m_activity = activity;
	}

	@Override
	public void onLoadResource(WebView view, String url) {
		super.onLoadResource(view, url);
		Debug.trace(url);
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		Debug.trace(url);
		super.onPageStarted(view, url, favicon);
		return;
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		Uri source = Uri.parse(url);
		if (TextUtils.equals(PROTOCOL_SHOWPOPUP, source.getScheme())) {
			view.stopLoading();
			Builder bd = new BundleUtils.Builder();
			bd.add(CustomWebViewFragment.ARG_KEY_IS_SEARCH_MODE, true);
			SubActivityType subActivityType = null;
			String host = source.getHost();
			if ("viewMail".equals(host)) {
				subActivityType = SubActivityType.CUSTOM_WEBVIEW;
				String link = "";
				if (TextUtils.equals(HMGWServiceHelper.mail_type, "db")) {
					link = source.getQueryParameter("link").trim();
				} else {
					link = source.getQueryParameter("link2").trim();
				}
				String type = MGWUtils.getTypeStringByApiCode(ApiCode.valueOf("mail_" + source.getQueryParameter("boxType")));
				final String f_link = link;
				final String f_type = type;
				final Builder f_bd = bd;
				final SubActivityType f_subActivityType = subActivityType;
				boolean security = source.getQueryParameter("isSecurity").equals("true");
				if (security) {// 보안 메일 이면
					AlertDialog.Builder builder;
					@SuppressLint("ResourceType") View dialog = LayoutInflater.from(m_activity).inflate(R.layout.dialog_password,
							(ViewGroup) m_activity.findViewById(R.layout.main));
					builder = new AlertDialog.Builder(m_activity);
					final EditText inputBox = (EditText) dialog.findViewById(R.id.chekcPassword);
					inputBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							if (hasFocus) {
								TextViewUtils.showKeyboard(m_activity, v);
							}
						}
					});
					builder.setView(dialog);
					builder.setTitle(R.string.label_password_mail);
					builder.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
						@SuppressLint("WrongConstant")
						@Override
						public void onClick(DialogInterface dialog, int which) {
							String password = (inputBox.getText()).toString();
							if (TextUtils.isEmpty(password)) {
								Toast.makeText(m_activity, R.string.message_check_password_empty, 0).show();
							} else {
								boolean isValid = MainModel.getInstance().getOpenApiService()
										.checkPassword(m_activity, ApiCode.check_login_password, password);
								if (isValid) {
									// MainActivity.m_controller.showMailDetails(link,
									// type);
									f_bd.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showMailDetailsPopup('" + f_type + "','" + f_link + "','" + password + "')");
									MainModel.getInstance().showSubActivity(m_activity, f_subActivityType, f_bd.build());
								} else {
									// Toast.makeText(mActivity,
									// R.string.message_check_login_password_invalid,
									// 0).show();
									PopupUtil.showDialog(m_activity, R.string.message_check_login_password_invalid);
								}
								inputBox.setText(null);
							}
							dialog.dismiss();
						}
					});
					builder.setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							inputBox.setText(null);
							dialog.dismiss();
							// hideWebview(true);
						}
					});

					try {
						builder.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					bd.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showMailDetailsPopup('" + type + "','" + link + "','" + "" + "')");
					MainModel.getInstance().showSubActivity(m_activity, subActivityType, bd.build());
				}
			} else if ("viewBoard".equals(host)) {
				subActivityType = SubActivityType.CUSTOM_WEBVIEW;
				bd.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showBoardDetailsPopup('" + source.getQueryParameter("link") + "',"
						+ source.getQueryParameter("commentCount") + ",'"+ source.getQueryParameter("deptId") +"');");
				MainModel.getInstance().showSubActivity(m_activity, subActivityType, bd.build());
			} else if ("viewAppr".equals(host)) {
				// link=open-api-appr-link&boxType=appr-box-type&apprId=appr-id&participantApprType=participant-appr-type&apprStatus=appr-status&attachCount=attach-count&statusImage=status-image&statusImageEx=status-image-ex&externalDoc=external-doc&summaryDoc=summary-doc&wordType=word-type&isSecurity=is-security&docViewAuthLink=doc-view-auth-open-api-link
				final String link = source.getQueryParameter("link");
				final String boxType = source.getQueryParameter("boxType");
				final String apprId = source.getQueryParameter("apprId");
				final String participantApprType = source.getQueryParameter("participantApprType");
				final String apprStatus = source.getQueryParameter("apprStatus");
				final String attachCount = source.getQueryParameter("attachCount");
				final String statusImage = source.getQueryParameter("statusImage");
				final String statusImageEx = source.getQueryParameter("statusImageEx");
				final String externalDoc = source.getQueryParameter("externalDoc");
				final String summaryDoc = source.getQueryParameter("summaryDoc");
				final String wordType = source.getQueryParameter("wordType");
				final String additionalOfficeUserId = source.getQueryParameter("additionalOfficeUserId");
				final String isSecurity = source.getQueryParameter("isSecurity");
				final String docViewAuthLink = source.getQueryParameter("docViewAuthLink");
				final String participantId = source.getQueryParameter("participantId");
				boolean commentCk = false;
				boolean postponeCk = false;
				if (!TextUtils.isEmpty(statusImageEx)) {
					commentCk = statusImageEx.indexOf("opinion.gif") == -1 ? false : true;
				}
				if (!TextUtils.isEmpty(statusImage)) {
					postponeCk = statusImage.charAt(0) == 'n';
				}
				final String strUrl = "javascript:showSignDetailsPopup('" + link + "','" + boxType + "','" + apprId + "','"
						+ participantApprType + "','" + apprStatus + "','" + attachCount + "'," + commentCk + "," + postponeCk + ",'"
						+ externalDoc + "','" + summaryDoc + "','" + wordType + "','" + additionalOfficeUserId + "','" + participantId + "',0)";
				// 권한 체크
				new ApiLoader(new GetDocViewAuth(), m_activity, new DefaultCallback() {
					@Override
					public void onResponse(String strRet) {
						super.onResponse(strRet);
						boolean hasAuth = true;
						if (getJsonObject() != null) {
							hasAuth = getJsonObject().optJSONObject("channel").optJSONArray("item").optJSONObject(0).optBoolean("#text");
						}

						if (!hasAuth) {
							PopupUtil.showDialog(m_activity, R.string.error_sign_not_allowed_to_access);
							return;
						}
						// 보안메일 검사
						if (TextUtils.equals(isSecurity, "true")) {
							AlertDialog.Builder builder;
							View dialog = LayoutInflater.from(m_activity).inflate(R.layout.dialog_password, null);
							builder = new AlertDialog.Builder(m_activity);
							final EditText inputBox = (EditText) dialog.findViewById(R.id.chekcPassword);
							builder.setView(dialog);
							builder.setTitle(R.string.label_password_sign);
							builder.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									String password = (inputBox.getText()).toString();
									if (TextUtils.isEmpty(password)) {
										PopupUtil.showToastMessage(m_activity, R.string.message_check_password_empty);
									} else {
										boolean isValid = MainModel.getInstance().getOpenApiService()
												.checkPassword(m_activity,ApiCode.check_sign_password, password);
										if (isValid) {

											Builder bd = new BundleUtils.Builder();
											bd.add(CustomWebViewFragment.ARG_KEY_URL, strUrl);
											MainModel.getInstance().showSubActivity(m_activity, SubActivityType.CUSTOM_WEBVIEW, bd.build());
											return;
										} else {
											PopupUtil.showDialog(m_activity, R.string.message_check_sign_password_invalid);
										}
										inputBox.setText(null);
									}
								}
							});
							builder.setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									inputBox.setText(null);
								}
							});
							builder.show();
						} else {
							Builder bd = new BundleUtils.Builder();
							bd.add(CustomWebViewFragment.ARG_KEY_URL, strUrl);
							MainModel.getInstance().showSubActivity(m_activity, SubActivityType.CUSTOM_WEBVIEW, bd.build());
						}
					}
				}, docViewAuthLink).execute();
			} else if ("viewSquare".equals(host)) {
				subActivityType = SubActivityType.SQUARE_CONTENTS_DETAIL;
				bd.add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_ID, source.getQueryParameter("contentsId"));
				MainModel.getInstance().showSubActivity(m_activity, subActivityType, bd.build());
			} else if ("viewUser".equals(host)) {
				subActivityType = SubActivityType.CUSTOM_WEBVIEW;
				bd.add(CustomWebViewFragment.ARG_KEY_URL, "javascript:showUserDetailsPopup('" + source.getQueryParameter("userId") + "');");
				MainModel.getInstance().showSubActivity(m_activity, subActivityType, bd.build());
			}
			return false;
		} else {
			return super.shouldOverrideUrlLoading(view, url);
		}

	}

	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		Debug.trace(url);
	}
}
