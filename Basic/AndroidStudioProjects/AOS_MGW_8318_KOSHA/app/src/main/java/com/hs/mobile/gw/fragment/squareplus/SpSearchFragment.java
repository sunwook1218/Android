package com.hs.mobile.gw.fragment.squareplus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsSubFragment.ContentsViewType;
import com.hs.mobile.gw.fragment.squareplus.SpContentsSubFragment.SpSearchType;
import com.hs.mobile.gw.openapi.squareplus.SpSearchResult;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;

public class SpSearchFragment extends CommonFragment implements OnClickListener {
	private EditText m_edSearch;
	private ImageButton m_btnCancel;
	private RelativeLayout m_searchInputLayout;
	private String m_strSquareId;
	private ImageView m_btnBack;
	private LinearLayout m_searchTypeLayout;
	private LinearLayout m_btnContents;
	private LinearLayout m_btnAuthor;
	private LinearLayout m_btnTag;
	private LinearLayout m_btnFile;
	private TextView m_tvContents;
	private TextView m_tvAuthor;
	private TextView m_tvTag;
	private TextView m_tvFile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Debug.trace("");
		super.onCreate(savedInstanceState);
		if (getArguments() != null && getArguments().containsKey(MainModel.ARG_KEY_SQUARE_ID)) {
			m_strSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
		} else {
			getActivity().finish();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Debug.trace("");
		View v = inflater.inflate(R.layout.fragment_sp_search, container, false);
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_searchInputLayout = (RelativeLayout) v.findViewById(R.id.ID_LAY_R_SEARCH);
		m_btnCancel = (ImageButton) v.findViewById(R.id.ID_BTN_CANCEL);
		m_edSearch = (EditText) v.findViewById(R.id.ID_ED_SEARCH);
		m_searchTypeLayout = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_TYPE_SECLECT);
		m_btnContents = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_CONTENTS);
		m_btnAuthor = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_AUTHOR);
		m_btnTag = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_TAG);
		m_btnFile = (LinearLayout) v.findViewById(R.id.ID_LL_SEARCH_FILE);
		m_tvContents = (TextView) v.findViewById(R.id.ID_TV_SEARCH_CONTENTS);
		m_tvAuthor = (TextView) v.findViewById(R.id.ID_TV_SEARCH_AUTHOR);
		m_tvTag = (TextView) v.findViewById(R.id.ID_TV_SEARCH_TAG);
		m_tvFile = (TextView) v.findViewById(R.id.ID_TV_SEARCH_FILE);
		
		m_btnBack.setOnClickListener(this);
		m_btnCancel.setOnClickListener(this);
		m_btnContents.setOnClickListener(this);
		m_btnAuthor.setOnClickListener(this);
		m_btnTag.setOnClickListener(this);
		m_btnFile.setOnClickListener(this);

		m_edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					callSearchResultView(SpSearchType.CONTENTS);
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
					TextViewUtils.hideKeyBoard(getActivity(), m_edSearch); 
				}
			}
		});
		
		m_edSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (m_edSearch.getEditableText().length() == 0) {
					m_searchTypeLayout.setVisibility(View.GONE);
					m_tvContents.setText("");
					m_tvAuthor.setText("");
					m_tvTag.setText("");
					m_tvFile.setText("");
				}
				else {
					m_searchTypeLayout.setVisibility(View.VISIBLE);
					String str = "'"+m_edSearch.getText().toString()+"'";
					m_tvContents.setText(str);
					m_tvAuthor.setText(str);
					m_tvTag.setText(str);
					m_tvFile.setText(str);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
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
			if (getFragmentManager().getBackStackEntryCount() > 0)
				getFragmentManager().popBackStack();
			else
				getActivity().finish();
			break;
		case R.id.ID_LL_SEARCH_CONTENTS:
			callSearchResultView(SpSearchType.CONTENTS);
			break;
		case R.id.ID_LL_SEARCH_AUTHOR:
			callSearchResultView(SpSearchType.AUTHOR);
			break;
		case R.id.ID_LL_SEARCH_TAG:
			callSearchResultView(SpSearchType.TAG);
			break;
		case R.id.ID_LL_SEARCH_FILE:
			callSearchResultView(SpSearchType.FILE);
			break;
		}
	}
	
	private void callSearchResultView(SpSearchType type) {
		if (m_edSearch.getText().length() == 0) {
			PopupUtil.showToastMessage(getActivity(), R.string.square_search_require_search_keyword);
		} else {
			Bundle bd = new BundleUtils.Builder()
			.add(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE, SpContentsSubFragment.ContentsViewType.SEARCH_RESULT)
			.add(SpContentsSubFragment.ARG_KEY_SP_SEARCH_KEYWORD, m_edSearch.getText().toString().trim())
			.add(SpContentsSubFragment.ARG_KEY_SP_SEARCH_TYPE, type)
			.add(MainModel.ARG_KEY_SQUARE_ID, m_strSquareId).build();
			if (getMainModel().isTablet()) {
				MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsSubFragment(), bd, R.id.ID_LAY_L_FRAGMENT_LAYOUT,
						true, null);
			} else {
				getMainModel().showSubActivity(getActivity(), SubActivityType.SP_SEARCH_RESULT, bd);
			}
		}
	}
}
