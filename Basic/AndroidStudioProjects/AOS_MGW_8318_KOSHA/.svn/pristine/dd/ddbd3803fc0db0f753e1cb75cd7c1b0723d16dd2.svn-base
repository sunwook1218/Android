package com.hs.mobile.gw.fragment.square.searchresult;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.square.searchresult.SquareSearchResultModel.SearchType;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SquareSearchResultFragment extends CommonFragment {
	public static final String ARG_KEY_SQUARE_ID = "arg_key_square_id";
	private SquareSearchResultModel m_model;
	private SquareSearchResultController m_controller;
	public LinearLayout m_btnMessage;
	public LinearLayout m_btnFile;
	public PullToRefreshListView m_lvSearch;
	public TextView m_tvSearchTitle;
	private ImageView m_btnBack;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		m_model = new SquareSearchResultModel();
		m_controller = new SquareSearchResultController(this, m_model);
		m_controller.checkArgument();
		MainModel.getInstance().addContentsChangeListener(m_controller);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_square_search_result, container, false);
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_tvSearchTitle = (TextView) v.findViewById(R.id.ID_TV_SEARCH_RESULT_TITLE);
		m_btnMessage = (LinearLayout) v.findViewById(R.id.ID_LAY_L_WORK);
		m_btnFile = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE);
		m_lvSearch = (PullToRefreshListView) v.findViewById(R.id.ID_LV_SEARCH);
		m_lvSearch.getRefreshableView().setDividerHeight(0);
		m_btnBack.setOnClickListener(m_controller);
		m_btnMessage.setOnClickListener(m_controller);
		m_btnFile.setOnClickListener(m_controller);
		m_lvSearch.setEmptyView(v.findViewById(R.id.empty_list_item));
		m_controller.setSearchMode(SearchType.WORK);
		m_model.createAdapter(getActivity(), m_controller);
		m_lvSearch.setOnItemClickListener(m_controller);
		m_lvSearch.setOnRefreshListener(m_controller);
		m_lvSearch.setOnLastItemVisibleListener(m_controller);
		return v;
	}
	@Override
	public void onDestroy() {
		MainModel.getInstance().removeContentsChangeListener(m_controller);
		super.onDestroy();		
	}
}
