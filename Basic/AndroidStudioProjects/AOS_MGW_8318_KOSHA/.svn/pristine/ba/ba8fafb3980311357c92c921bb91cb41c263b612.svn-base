package com.hs.mobile.gw.fragment.square.makegroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.Member;
import com.hs.mobile.gw.MainModel.MemberType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CommonFragmentController;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.openapi.SearchAddress;
import com.hs.mobile.gw.openapi.SearchAddressResult;
import com.hs.mobile.gw.openapi.square.CreateSquare;
import com.hs.mobile.gw.openapi.square.CreateSquareResult;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.tokenautocomplete.TokenCompleteTextView.TokenDeleteStyle;
import com.hs.mobile.gw.view.tokenautocomplete.TokenListener;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;

public class MakeNewWorkGroupController extends CommonFragmentController<MakeNewWorkGroupFragment, MakeNewWorkGroupModel> implements
		OnDateSetListener, OnClickListener, TextWatcher, TokenListener, OnCheckedChangeListener {

	public MakeNewWorkGroupController(MakeNewWorkGroupFragment view, MakeNewWorkGroupModel model) {
		super(view, model);
	}

	public void init() {
		getModel().createFilterAdapter(getContenxt());
		getView().m_btnCancel.setOnClickListener(this);
		getView().m_btnAdd.setOnClickListener(this);
		getView().m_btnMakeGroup.setOnClickListener(this);
		getView().m_tvEndDate.setOnClickListener(this);
		getView().m_edParticipant.addTextChangedListener(this);
		getView().m_cbDateSetting.setOnCheckedChangeListener(this);

		getView().m_edParticipant.setThreshold(2);
		getView().m_edParticipant.setDeletionStyle(TokenDeleteStyle.Clear);
		getView().m_edParticipant.setAdapter(getModel().getAdapter());
		getView().m_edParticipant.setTokenListener(this);
		getView().m_edParticipant.performBestGuess(false);
		getView().m_endDateLayout.setVisibility(View.GONE);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		Calendar c = Calendar.getInstance();
		c.set(year, monthOfYear, dayOfMonth);
		getModel().setDate(c.getTime());
		getView().m_tvEndDate.setText(MakeNewWorkGroupModel.YYYYMMDD.format(getModel().getDate()));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_CANCEL:
			getActivity().finish();
			break;
		case R.id.ID_BTN_TO_ADD:
			getMainModel().showSubActivity(
					getView(),
					SubActivityType.ORG_CHART,
					new BundleUtils.Builder().add(CustomWebViewFragment.ARG_KEY_URL,
							"javascript:showOrgSelectForApp('square','to','{\"selectedlist\":[]}');").build());
			break;
		case R.id.ID_BTN_MAKE_GROUP:
			String strDueDate = "";
			if (getView().m_cbDateSetting.isChecked()) {
				strDueDate = MakeNewWorkGroupModel.YYYYMMDD.format(getModel().getDate());
			}
			SelectedListItem[] l = Arrays.asList(getView().m_edParticipant.getObjects().toArray()).toArray(
					new SelectedListItem[getView().m_edParticipant.getObjects().size()]);
			ArrayList<SelectedListItem> selectedList = (new ArrayList<SelectedListItem>(Arrays.asList(l)));
			ArrayList<Member> memberList = new ArrayList<Member>();
			for (SelectedListItem item : selectedList) {
				Debug.trace(item.objectType.name());
				Debug.trace(item.id);
				Debug.trace(item.name);
				memberList.add(new Member(item.id, item.name, MemberType.valueOf(item.objectType.name())));
			}
			if (getModel().checkValidation(getView().m_edGroupTitle.getText().toString().trim(), memberList)) {
				StringBuilder bd = new StringBuilder();
				for (Member member : memberList) {
					bd.append(member.toString());
				}
				new ApiLoader(new CreateSquare(), getActivity(), new CreateSquareResult() {
					@Override
					public void onResponse(String strRet) {
						super.onResponse(strRet);
						Debug.trace(strRet);
						if (this.responseHead.resultCode == SUCCESS) {
							MainFragment.menuListHelper.loadSquareGroupList(MainModel.getInstance().getCurrentSquare());
							getActivity().finish();
						}
					}
				}, getView().m_edGroupTitle.getText().toString().trim(), getView().m_edDescription.getText().toString().trim(), strDueDate,
						bd.toString()).execute();
			} else {
				PopupUtil.showDialog(getActivity(), R.string.square_empty_group_title);
			}

			break;
		case R.id.ID_TV_END_DATE:
			Calendar c = Calendar.getInstance();
			c.setTime(getModel().getDate());
			new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
			break;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String str = s.toString();
		if (!TextUtils.isEmpty(str) && str.contains(",")) {
			str = str.substring(str.lastIndexOf(",") + 1, str.length()).trim();
		}
		if (str.length() >= 2) {
			getView().resetNetwork();
			SearchAddress api = new SearchAddress();
			final String query = str.trim();
			getView().addNetworkRequst(api);
			MainModel.getInstance().setLoadingProgressShow(false);
			new ApiLoader(api, getActivity(), new SearchAddressResult() {
				@Override
				public void onResponse(String strRet) {
					super.onResponse(strRet);
					getModel().getData().clear();
					if (data.size() > 0) {
						getModel().getData().addAll(this.data);
						// m_edTo.showDropDown();
					}
					getModel().getAdapter().getFilter().filter(query, null);
					getModel().getAdapter().notifyDataSetChanged();
					MainModel.getInstance().setLoadingProgressShow(true);
				}
			}, query).execute();
		}
	}

	@Override
	public void onTokenAdded(Object token) {
		if (token != null && token instanceof SelectedListItem) {
			Debug.trace("onTokenAdded", ((SelectedListItem) token).name);
		}
		updateTokenConfirmation();
	}

	@Override
	public void onTokenRemoved(Object token) {
		if (token != null && token instanceof SelectedListItem) {
			Debug.trace("onTokenRemoved", ((SelectedListItem) token).name);
		}
		updateTokenConfirmation();
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		getView().m_endDateLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
	}

	private void updateTokenConfirmation() {
		StringBuilder sb = new StringBuilder("Current tokens:\n");
		for (Object token : getView().m_edParticipant.getObjects()) {
			sb.append(((SelectedListItem) token).name);
			sb.append('\n');
		}
	}
}
