package com.hs.mobile.gw.fragment;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.square.searchresult.SquareSearchResultModel;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.PopupUtil;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SquareSearchFragment extends CommonFragment implements OnClickListener {
	private EditText m_edSearch;
	private ImageButton m_btnCancel;
	private RelativeLayout m_searchInputLayout;
	private String m_strSquareId;
	private ImageView m_btnBack;
	private Status m_groupStatus;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null && getArguments().containsKey(MainModel.ARG_KEY_SQUARE_ID)) {
			m_strSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
			if(getArguments().containsKey(MainModel.ARG_KEY_GROUP_STATUS))
			{
				m_groupStatus = (Status)getArguments().getSerializable(MainModel.ARG_KEY_GROUP_STATUS);
			}
		} else {
			getActivity().finish();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_square_search, container, false);
		super.createHead(v); // create sub header
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_searchInputLayout = (RelativeLayout) v.findViewById(R.id.ID_LAY_R_SEARCH);
		m_btnCancel = (ImageButton) v.findViewById(R.id.ID_BTN_CANCEL);
		m_edSearch = (EditText) v.findViewById(R.id.ID_ED_SEARCH);
		m_btnBack.setOnClickListener(this);
		m_btnCancel.setOnClickListener(this);

		m_edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					if (m_edSearch.getText().length() == 0) {
						PopupUtil.showToastMessage(getActivity(), R.string.square_search_require_search_keyword);
						return true;
					} else {
						getMainModel().showSubActivity(
								getActivity(),
								SubActivityType.SQUAR_SEARCH_RESULT,
								new BundleUtils.Builder()
										.add(SquareSearchResultModel.ARG_KEY_SQUARE_SEARCH_KEYWORD, m_edSearch.getText().toString().trim())
										.add(MainModel.ARG_KEY_SQUARE_ID, m_strSquareId)
										.add(MainModel.ARG_KEY_GROUP_STATUS, m_groupStatus).build());
					}
					return true;
				}
				return false;
			}
		});
		m_edSearch.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					m_searchInputLayout.setBackgroundResource(R.drawable.search_bar_hover);
				} else {
					m_searchInputLayout.setBackgroundResource(R.drawable.search_bar_default);
				}
			}
		});
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_CANCEL:
			m_edSearch.getText().clear();
			break;
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		default:getActivity().finish();super.onClick(v); // 20.06.23 tkofs
		}
	}
}
