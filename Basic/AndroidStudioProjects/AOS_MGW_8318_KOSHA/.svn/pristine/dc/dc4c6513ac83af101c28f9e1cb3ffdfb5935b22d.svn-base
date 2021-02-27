package com.hs.mobile.gw.fragment.squareplus.createsquare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.MemberType;
import com.hs.mobile.gw.MainModel.SpMember;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CommonFragmentController;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.openapi.SearchAddress;
import com.hs.mobile.gw.openapi.SearchAddressResult;
import com.hs.mobile.gw.openapi.squareplus.SpAddSquare;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.tokenautocomplete.TokenCompleteTextView.TokenDeleteStyle;
import com.hs.mobile.gw.view.tokenautocomplete.TokenListener;

import android.app.DatePickerDialog.OnDateSetListener;
import androidx.fragment.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;

public class SpAddSquareController extends CommonFragmentController<SpAddSquareFragment, SpAddSquareModel> implements
		OnDateSetListener, OnClickListener, TextWatcher, TokenListener, OnCheckedChangeListener {

	public SpAddSquareController(SpAddSquareFragment view, SpAddSquareModel model) {
		super(view, model);
	}

	public void init() {
		getModel().createFilterAdapter(getContenxt());
		getView().m_btnCancel.setOnClickListener(this);
		getView().m_btnAdd.setOnClickListener(this);
		getView().m_btnMakeGroup.setOnClickListener(this);
		getView().m_edMember.addTextChangedListener(this);
		getView().m_cbOpenCheck.setOnCheckedChangeListener(this);

		getView().m_edMember.setThreshold(2);
		getView().m_edMember.setDeletionStyle(TokenDeleteStyle.Clear);
		getView().m_edMember.setAdapter(getModel().getAdapter());
		getView().m_edMember.setTokenListener(this);
		getView().m_edMember.performBestGuess(false);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
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
		case R.id.ID_BTN_ADD_SQUARE:
			PopupUtil.showLoading(getActivity());
			if (getView().m_cbOpenCheck.isChecked()) {
//				strDueDate = SpCreateSquareModel.YYYYMMDD.format(getModel().getDate());
			}

			SelectedListItem[] l = Arrays.asList(getView().m_edMember.getObjects().toArray()).toArray(
					new SelectedListItem[getView().m_edMember.getObjects().size()]);
			ArrayList<SelectedListItem> selectedList = (new ArrayList<SelectedListItem>(Arrays.asList(l)));
			ArrayList<SpMember> memberList = new ArrayList<SpMember>();

			for (SelectedListItem item : selectedList) {
				Debug.trace(item.objectType.name());
				Debug.trace(item.id);
				Debug.trace(item.name);
				memberList.add(new SpMember(item.id, item.name, MemberType.valueOf(item.objectType.name()), MemberRights.NORMAL_USER));
			}

			if (getModel().checkValidation(getView().m_edGroupTitle.getText().toString().trim(), memberList)) {
				StringBuilder bd = new StringBuilder();
				for (SpMember member : memberList) {
					bd.append(member.toString());
				}

				HashMap<String, String> parameter = new HashMap<String, String>();
				String title = getView().m_edGroupTitle.getText().toString().trim();
				String description = getView().m_edDescription.getText().toString().trim();
				String member = bd.toString();
				String securityFlag = getView().m_cbOpenCheck.isChecked() ? SpSquareConst.FALSE_CH : SpSquareConst.TRUE_CH;
				parameter.put("title", title);
				parameter.put("desc", description);
				parameter.put("member", member);
				parameter.put("securityFlag", securityFlag);
				FragmentActivity activity = getView().getActivity();

				SpSquareCallBack callback = new SpSquareCallBack(activity, SpSquareVO.class) {
					@Override
					public void callback(String url, JSONObject json, AjaxStatus status) {
						super.callback(url, json, status);
						PopupUtil.hideLoading(getActivity());
						if (status.getCode() == 200) {
							MainFragment.menuListHelper.loadSpSquareIngList(MainModel.getInstance().getCurrentSpSquare(), this.item);
							getActivity().finish();
						}
					}
				};

				new ApiLoaderEx<>(new SpAddSquare(activity), activity, callback, parameter).execute();
			} else {
				PopupUtil.showDialog(getActivity(), R.string.square_empty_group_title);
			}

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
			str = str.substring(str.lastIndexOf(",") + 1, str.length());
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
					if (data.size() > 0) {
						getModel().getData().clear();
						getModel().getData().addAll(this.data);
						getModel().getAdapter().notifyDataSetChanged();
						getModel().getAdapter().getFilter().filter(query, null);
						// m_edTo.showDropDown();
					}
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
		// TODO : 공개 여부에 따른 사용자 변동이 일어날 수 있음 (확인필요)
//		getView().m_endDateLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
	}

	private void updateTokenConfirmation() {
		StringBuilder sb = new StringBuilder("Current tokens:\n");
		for (Object token : getView().m_edMember.getObjects()) {
			sb.append(((SelectedListItem) token).name);
			sb.append('\n');
		}
	}
}
