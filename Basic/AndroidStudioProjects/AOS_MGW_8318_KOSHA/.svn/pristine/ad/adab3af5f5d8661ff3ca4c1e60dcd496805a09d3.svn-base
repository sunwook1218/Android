package com.hs.mobile.gw.fragment.mailwrite;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment.OrgSelectedVO;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.ContactsCompletionView;
import com.hs.mobile.gw.view.DndListView;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.SelectedListItem.ObjectType;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Contacts;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * 
 * @author C.H.Lee
 * 
 */

public class MailWriteFragment extends CommonFragment {
	

	public static final String INTENT_KEY_SHOW_MAIL_EDITOR_VIEW_VO = "show_mail_editor_view_vo";

	public LinearLayout m_toLayout;
	public LinearLayout m_ccbccLayout;
	public LinearLayout m_ccLayout;
	public LinearLayout m_bccLayout;
	public EditText m_edTitle;
	public EditText m_edContent;
	public ContactsCompletionView m_edTo;
	public ContactsCompletionView m_edCC;
	public ContactsCompletionView m_edBCC;
	public LinearLayout m_ccAndBccVisibleLayout;

	public Button m_btnAddTo;
	public Button m_btnAddCC;
	public Button m_btnAddBCC;
	public Button m_btnMoreTo;
	public Button m_btnMoreCC;
	public Button m_btnMoreBCC;
	public LinearLayout m_dndLayout;
	public DndListView m_lvDnd;
	public ScrollView m_scrollView;
	public LinearLayout m_innerLinearLayout;
	public ContactsCompletionView m_selectedPrecipientEditText;	
	public LinearLayout m_originalTvLayout;
	public TextView m_tvOriginal;
	public CheckBox m_cbOriginal;
	public LinearLayout m_originalCbLayout;
	public TextView m_tvOriginalAttachIndicator;
	public LinearLayout m_originalReceivedAttachFileAreaLayout;
	public String m_strOrgMessage;
	public CheckBox m_cbSelfMail;
	public CheckBox m_cbSecure;
	public CheckBox m_cbEmergency;
	public CheckBox m_cbExcludingSender;
	public LinearLayout m_originalAttachOuterLayout;
	public LinearLayout contentLayout;
	public TextView tvCC;
	public TextView tvBCC;
	public Button btnCancel;
	public Button btnSendMail;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_mail_write, container, false);
		MailWriteModel model = new MailWriteModel();
		MailWriteController controller = new MailWriteController(this, model);
		model.setMailWriteModelListener(controller);
		
		m_scrollView = (ScrollView) rootView.findViewById(R.id.ID_SCROLL_VIEW);
		m_innerLinearLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_INNERSCROLL);
		m_toLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_TO);
		m_toLayout.requestFocus();
		m_ccAndBccVisibleLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_CC_AND_BCC_BTN);
		m_ccbccLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_CC_BCC);
		m_ccLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_CC);
		m_bccLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_BCC);
		m_edTo = (ContactsCompletionView) rootView.findViewById(R.id.ID_ED_TO);
		m_edCC = (ContactsCompletionView) rootView.findViewById(R.id.ID_ED_CC);
		m_edBCC = (ContactsCompletionView) rootView.findViewById(R.id.ID_ED_BCC);
		contentLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_CONTENT);
		m_edTitle = (EditText) rootView.findViewById(R.id.ID_ED_TITLE);
		m_edContent = (EditText) rootView.findViewById(R.id.ID_ED_CONTENT);
		tvCC = (TextView) rootView.findViewById(R.id.ID_TV_CC);
		tvBCC = (TextView) rootView.findViewById(R.id.ID_TV_BCC);
		btnCancel = (Button) rootView.findViewById(R.id.ID_BTN_CANCEL);
		btnSendMail = (Button) rootView.findViewById(R.id.ID_BTN_SENDMAIL);
		m_btnAddTo = (Button) rootView.findViewById(R.id.ID_BTN_TO_ADD);
		m_btnAddCC = (Button) rootView.findViewById(R.id.ID_BTN_CC_ADD);
		m_btnAddBCC = (Button) rootView.findViewById(R.id.ID_BTN_BCC_ADD);
		m_btnMoreTo = (Button) rootView.findViewById(R.id.ID_BTN_TO_MORE);
		m_btnMoreCC = (Button) rootView.findViewById(R.id.ID_BTN_CC_MORE);
		m_btnMoreBCC = (Button) rootView.findViewById(R.id.ID_BTN_BCC_MORE);
		m_cbSelfMail = (CheckBox) rootView.findViewById(R.id.ID_CB_SELF_MAIL);
		m_cbSecure = (CheckBox) rootView.findViewById(R.id.ID_CB_SECURITY);
		m_cbEmergency = (CheckBox) rootView.findViewById(R.id.ID_CB_EMERGENCY);
		m_cbExcludingSender = (CheckBox) rootView.findViewById(R.id.ID_CB_EXCLUDING_SENDER);
		m_cbExcludingSender.setChecked(true);
		m_originalCbLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_CB_ORIGNAL);
		m_cbOriginal = (CheckBox) rootView.findViewById(R.id.ID_CB_ORIGINAL);
		m_originalTvLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_ORIGINAL);
		m_tvOriginal = (TextView) rootView.findViewById(R.id.ID_TV_ORIGINAL);
		m_tvOriginalAttachIndicator = (TextView) rootView.findViewById(R.id.ID_TV_ORIGIN_ATTACH_INDICATOR);
		m_originalReceivedAttachFileAreaLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_RECEIVED_ATTACH_FILE_AREA);
		m_originalAttachOuterLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_ORIGINAL_ATTACH_FILE);
		m_dndLayout = (LinearLayout) rootView.findViewById(R.id.ID_LAY_L_DND);
		m_lvDnd = (DndListView) rootView.findViewById(R.id.ID_LV_DND);

		controller.init();
		return rootView;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_ORG_SELECT:
				String strOrgSelected = data.getStringExtra(CustomWebViewFragment.INTENT_KEY_ORG_SELECT);
				OrgSelectedVO vo = new OrgSelectedVO(strOrgSelected);
				switch (vo.callbackid) {
				case bcc:
					m_edBCC.clear();
					for (SelectedListItem item : vo.selectedlist) {
						m_edBCC.addObject(item, item.name);
					}
					break;
				case cc:
					m_edCC.clear();
					for (SelectedListItem item : vo.selectedlist) {
						m_edCC.addObject(item, item.name);
					}
					break;
				case to:
					m_edTo.clear();
					for (SelectedListItem item : vo.selectedlist) {
						m_edTo.addObject(item, item.name);
					}
					break;
				default:
					break;
				}
				break;
			case MainModel.REQ_PHONE_TO_SELECT:
				String id = data.getData().getLastPathSegment();
				Cursor cursor = getActivity().getContentResolver().query(Email.CONTENT_URI, null, Email.CONTACT_ID + "=?",
						new String[] { id }, null);
				if (cursor != null && cursor.getCount() > 0) {
					cursor.moveToFirst();
					m_edTo.addObject(new SelectedListItem(null, cursor.getString(cursor.getColumnIndex(Email.DATA)), ObjectType.EMAIL
							.toString(), cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME))));
					cursor.close();
				} else {
					PopupUtil.showToastMessage(getActivity(), R.string.error_empty_email);
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		if (requestCode == RC_CONTACTS_PERM) {
			for (int i = 0; i < permissions.length; i++) {
				if (TextUtils.equals(permissions[i], Manifest.permission.READ_CONTACTS)
						&& grantResults[i] == PackageManager.PERMISSION_GRANTED) {
					Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
					startActivityForResult(intent, MainModel.REQ_PHONE_TO_SELECT);
				}
			}
		}
	}

	@Override
	public void onStop() {
		//MainFragment.getController().hideWebviewTabbar();
		super.onStop();
	}

	@Override
	public boolean onPreBackKeyPressed() {
		PopupUtil.showOkCancelDialog(getActivity(), R.string.mail_write_cancel, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// tkofs mail_cancle
				ViewModel.Log("mail backkey pressed", "tkofs_mail");
				JSONObject cmenuview = (JSONObject) MainFragment.pressedMenuView.getTag();
				//if (cmenuview.optString("menu-id").equals("home_home")) {
					MainFragment.pressedMenuView.performClick();
					//MainFragment.pressedMenuView = null;
				//}
				getActivity().finish();
			}
		});
		return false;
	}
}
