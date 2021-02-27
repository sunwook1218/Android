package com.hs.mobile.gw.fragment.square.makegroup;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment.OrgSelectedVO;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.ContactsCompletionView;
import com.hs.mobile.gw.view.SelectedListItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MakeNewWorkGroupFragment extends CommonFragment {
	public EditText m_edGroupTitle;
	public Button m_btnAdd;
	public ContactsCompletionView m_edParticipant;
	public CheckBox m_cbDateSetting;
	public TextView m_tvEndDate;
	public EditText m_edDescription;
	public Button m_btnMakeGroup;
	public LinearLayout m_endDateLayout;
	public Button m_btnCancel;

	private MakeNewWorkGroupModel model;
	private MakeNewWorkGroupController controller;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_make_new_workgroup, container, false);
		model = new MakeNewWorkGroupModel();
		controller = new MakeNewWorkGroupController(this, model);

		m_btnCancel = (Button) view.findViewById(R.id.ID_BTN_CANCEL);
		m_btnMakeGroup = (Button) view.findViewById(R.id.ID_BTN_MAKE_GROUP);
		m_edGroupTitle = (EditText) view.findViewById(R.id.ID_ED_GROUP_TITLE);
		m_edParticipant = (ContactsCompletionView) view.findViewById(R.id.ID_ED_PARTICIPANT);
		m_btnAdd = (Button) view.findViewById(R.id.ID_BTN_TO_ADD);
		m_cbDateSetting = (CheckBox) view.findViewById(R.id.ID_CB_DATE_SETTING);
		m_endDateLayout = (LinearLayout) view.findViewById(R.id.ID_LAY_L_WORK_END_DATE);
		m_tvEndDate = (TextView) view.findViewById(R.id.ID_TV_END_DATE);
		m_edDescription = (EditText) view.findViewById(R.id.ID_ED_GROUP_DESC);
		m_tvEndDate.setText(MakeNewWorkGroupModel.YYYYMMDD.format(model.getDate()));

		controller.init();
		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_ORG_SELECT:
				String strOrgSelected = data.getStringExtra(CustomWebViewFragment.INTENT_KEY_ORG_SELECT);
				OrgSelectedVO vo = new OrgSelectedVO(strOrgSelected);
				for (SelectedListItem item : vo.selectedlist) {
					m_edParticipant.addObject(item, item.name);
				}
				Debug.trace(vo);
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
