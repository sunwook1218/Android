package com.hs.mobile.gw.fragment.square.file;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.square.file.SquareFileModel.FileFilter;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SquareFileFragment extends CommonFragment {
	public static final String ARG_KEY_SQUARE_ID = "arg_key_square_id";
	private SquareFileModel m_model;
	private SquareFileController m_controller;
	public LinearLayout m_btnFileAll;
	public LinearLayout m_btnFileMy;
	public LinearLayout m_btnFileOther;
	public PullToRefreshListView m_lvFile;
	private ImageView m_btnBack;
	private TextView m_btnFileType;
	public ImageButton m_btnMemberSelect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		m_model = new SquareFileModel();
		m_controller = new SquareFileController(this, m_model);
		m_controller.checkArgument();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_square_file, container, false);
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_btnMemberSelect = (ImageButton)v.findViewById(R.id.ID_BTN_SELECT_MEMBER);
		m_btnFileType = (TextView) v.findViewById(R.id.ID_BTN_TYPE);		
		m_btnFileAll = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_ALL);
		m_btnFileMy = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_MINE);
		m_btnFileOther = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_OTHER);
		m_lvFile = (PullToRefreshListView) v.findViewById(R.id.ID_LV_FILE);
		m_btnBack.setOnClickListener(m_controller);
		m_btnMemberSelect.setOnClickListener(m_controller);
		m_btnFileType.setOnClickListener(m_controller);
		m_btnFileAll.setOnClickListener(m_controller);
		m_btnFileMy.setOnClickListener(m_controller);
		m_btnFileOther.setOnClickListener(m_controller);
		m_model.createAdapter(getActivity(), m_controller);
		m_lvFile.setEmptyView(v.findViewById(R.id.empty_list_item));
		m_lvFile.setAdapter(m_model.getFileAdapter());
		m_lvFile.setOnItemClickListener(m_controller);
		m_lvFile.setOnRefreshListener(m_controller);
		m_lvFile.getRefreshableView().setDividerHeight(1);
		m_lvFile.setOnLastItemVisibleListener(m_controller);
		m_controller.setFilter(FileFilter.ALL);
		return v;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		m_controller.handleActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}
}
