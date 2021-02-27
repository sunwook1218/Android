package com.hs.mobile.gw.fragment.squareplus.createsquare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.CustomWebViewFragment.OrgSelectedVO;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.ContactsCompletionView;
import com.hs.mobile.gw.view.SelectedListItem;

public class SpAddSquareFragment extends CommonFragment {
	public EditText m_edGroupTitle;
	public Button m_btnAdd;
	public ContactsCompletionView m_edMember;
	public CheckBox m_cbOpenCheck;
	public EditText m_edDescription;
	public Button m_btnMakeGroup;
	public Button m_btnCancel;

	private SpAddSquareModel model;
	private SpAddSquareController controller;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_squareplus_add_square, container, false);
		model = new SpAddSquareModel();
		controller = new SpAddSquareController(this, model);

		m_btnCancel = (Button) view.findViewById(R.id.ID_BTN_CANCEL);
		m_btnMakeGroup = (Button) view.findViewById(R.id.ID_BTN_ADD_SQUARE);
		m_edGroupTitle = (EditText) view.findViewById(R.id.ID_ED_GROUP_TITLE);
		m_edMember = (ContactsCompletionView) view.findViewById(R.id.ID_ED_MEMBER);
		m_btnAdd = (Button) view.findViewById(R.id.ID_BTN_TO_ADD);
		m_cbOpenCheck = (CheckBox) view.findViewById(R.id.ID_CB_OPEN_CHECK);
		m_edDescription = (EditText) view.findViewById(R.id.ID_ED_GROUP_DESC);

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
					m_edMember.addObject(item, item.name);
				}
				Debug.trace(vo);
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
