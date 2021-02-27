
package com.hs.mobile.gw.fragment.squareplus;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.squareplus.SpFileListAdapter;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.MemberSelectFragment;
import com.hs.mobile.gw.fragment.squareplus.SpMemberSelectFragment.SpMemberSelectType;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.squareplus.SpGetAttachList;
import com.hs.mobile.gw.openapi.squareplus.callback.SpAttachListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SpFileFragment extends CommonFragment implements OnClickListener,
	OnLastItemVisibleListener, OnRefreshListener<ListView>, OnItemClickListener {
	public static final String ARG_KEY_SQUARE_ID = "arg_key_square_id";
	public LinearLayout m_btnFileAll;
	public LinearLayout m_btnFileMy;
	public LinearLayout m_btnFileOther;
	public PullToRefreshListView m_lvFile;
	private ImageView m_btnBack;
	private TextView m_btnFileType;
	public ImageButton m_btnMemberSelect;
	private List<SpAttachVO> m_data = new ArrayList<SpAttachVO>();
	private SpFileListAdapter m_adapter;
	private String m_strLastAttachId;
	private boolean loadingComplete;
	private String m_squareId;
	private SpFileFilter m_fileFilter = SpFileFilter.ALL;
	private SpFileType m_fileType = SpFileType.ALL;
	private String m_strSelectedUserId;

	public enum SpFileFilter {
		ALL("0"), MY("1"), OTHER("2");
		private String m_strType;

		private SpFileFilter(String s) {
			m_strType = s;
		}

		public String getValue() {
			return m_strType;
		}
	}

	public enum SpFileType {
		ALL("all"), DOC("document"), IMAGE("image"), ETC("other");
		private String m_strType;

		private SpFileType(String s) {
			m_strType = s;
		}

		public String getValue() {
			return m_strType;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 전달 받은 데이터 확인
		if (getArguments() != null) {
			if (getArguments().getSerializable(MainModel.ARG_KEY_SQUARE_ID) != null) {
				m_squareId = (String) getArguments().getSerializable(MainModel.ARG_KEY_SQUARE_ID);
//				MainModel.getInstance().setCurrentSquareId(m_squareId);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_squareplus_file, container, false);
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_btnMemberSelect = (ImageButton)v.findViewById(R.id.ID_BTN_SELECT_MEMBER);
		m_btnFileType = (TextView) v.findViewById(R.id.ID_BTN_TYPE);
		m_btnFileAll = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_ALL);
		m_btnFileMy = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_MINE);
		m_btnFileOther = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_OTHER);
		m_lvFile = (PullToRefreshListView) v.findViewById(R.id.ID_LV_FILE);
		m_adapter = new SpFileListAdapter(getActivity(), m_data);

		m_btnBack.setOnClickListener(this);
		m_btnMemberSelect.setOnClickListener(this);
		m_btnFileType.setOnClickListener(this);
		m_btnFileAll.setOnClickListener(this);
		m_btnFileMy.setOnClickListener(this);
		m_btnFileOther.setOnClickListener(this);
		m_lvFile.setEmptyView(v.findViewById(R.id.empty_list_item));
		m_lvFile.setAdapter(m_adapter);
		m_lvFile.setOnLastItemVisibleListener(this);
		m_lvFile.setOnItemClickListener(this);
		m_lvFile.setOnRefreshListener(this);
		setFilter(SpFileFilter.ALL);

		return v;
	}

	private void loadData(boolean first) {
		if (first) {
			m_data.clear();
		}

		SpAttachListCallBack spAttachListCallBack = new SpAttachListCallBack(getActivity(), SpAttachVO.class){
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);

				try {
					Debug.trace(json.toString(5));
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}

				if (dataList != null) {
					m_data.addAll(this.dataList);
					m_strLastAttachId = this.lastViewId;
					m_adapter.notifyDataSetChanged();
				}

				loadingComplete = true;
				m_lvFile.onRefreshComplete();
			}
		};

		spAttachListCallBack.progress(PopupUtil.getProgressDialog(getActivity()));

		{ // API명
			HashMap<String, String> params = new HashMap<>();
			params.put("squareId", m_squareId);

			if (!first && !TextUtils.isEmpty(m_strLastAttachId)) {
				params.put("lastViewAttachId", m_strLastAttachId);
			}

			params.put("fileType", m_fileType.getValue());
			params.put("authorIds", m_strSelectedUserId);

			new ApiLoaderEx<>(new SpGetAttachList(getActivity()), getActivity(), spAttachListCallBack, params).execute();
		}
	}

	public void setFilter(SpFileFilter t) {
		m_fileFilter = t;

		switch (t) {
		case ALL:
			m_btnMemberSelect.setVisibility(View.GONE);
			m_btnFileAll.setSelected(true);
			m_btnFileMy.setSelected(false);
			m_btnFileOther.setSelected(false);
			setFileType(m_fileType);
			break;
		case MY:
			m_btnMemberSelect.setVisibility(View.GONE);
			m_btnFileAll.setSelected(false);
			m_btnFileMy.setSelected(true);
			m_btnFileOther.setSelected(false);
			setFileType(m_fileType);
			break;
		case OTHER:
			m_btnMemberSelect.setVisibility(View.VISIBLE);
			m_btnFileAll.setSelected(false);
			m_btnFileMy.setSelected(false);
			m_btnFileOther.setSelected(true);

			if (!m_lvFile.isRefreshing()) {
				m_strSelectedUserId = null;
				MainModel.getInstance().showSubActivity(
						this,
						SubActivityType.SP_MEMBER_SELECT,
						new BundleUtils.Builder().add(MemberSelectFragment.ARG_KEY_MEMBER_SELECT_TYPE, SpMemberSelectType.REQ_RETURN)
								.add(MainModel.ARG_KEY_MEMBER_SELECT_OPTION, MainModel.REQ_SELECT_MEMBER)
								.add(MainModel.ARG_KEY_SQUARE_ID, m_squareId).build());
			} else {
				setFileType(m_fileType);
			}

			break;
		}
	}

	private void setFileType(SpFileType ft) {
		m_fileType = ft;

		switch (m_fileFilter) {
		case ALL:
			m_strSelectedUserId = "";
			break;
		case MY:
			m_strSelectedUserId = HMGWServiceHelper.userId;
			break;
		case OTHER:
//			strAuthId = m_strSelectedUserId;
			break;
		}

		m_data.clear();
		loadData(true);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_SELECT_MEMBER:
				List<SpSquareMemberVO> squareMemberList;

				try {
					Serializable members = null;
					members = data.getStringExtra(SpMemberSelectFragment.INTENT_KEY_MEMBERS);
					squareMemberList = Arrays.asList(new ObjectMapper().readValue((String)members, SpSquareMemberVO[].class));
				} catch (IOException e) {
					Debug.trace(e.getMessage());
					squareMemberList = null;
				}

				m_strSelectedUserId = "";

				for (SpSquareMemberVO squareMemberVO : squareMemberList) {
					m_strSelectedUserId += "," + squareMemberVO.getMemberId();
				}

				m_strSelectedUserId.replaceFirst(",", "");

				if (m_strSelectedUserId != null) {
					setFileType(SpFileType.ALL);
				}

				break;
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			if (MainModel.getInstance().isTablet()) {
				if (getFragmentManager().getBackStackEntryCount() > 0) {
					getFragmentManager().popBackStack();
				}
			} else {
				getActivity().finish();
			}
			break;
		case R.id.ID_BTN_MENU:
			break;
		case R.id.ID_BTN_TYPE:
			String[] items = new String[] {
					getString(R.string.favorite_file_type_all), getString(R.string.favorite_file_type_doc),
					getString(R.string.favorite_file_type_img), getString(R.string.favorite_file_type_etc) };
			new AlertDialog.Builder(getActivity()).setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					SpFileType ft = SpFileType.values()[which];
					setFileType(ft);
				}
			}).show();
			break;
		case R.id.ID_LAY_L_FILE_ALL:
			m_fileType = SpFileType.ALL;
			setFilter(SpFileFilter.ALL);
			break;
		case R.id.ID_LAY_L_FILE_MINE:
			m_fileType = SpFileType.ALL;
			setFilter(SpFileFilter.MY);
			break;
		case R.id.ID_LAY_L_FILE_OTHER:
			m_fileType = SpFileType.ALL;
			m_data.clear();
			m_adapter.notifyDataSetChanged();
			setFilter(SpFileFilter.OTHER);
			break;
		case R.id.ID_BTN_SELECT_MEMBER:
			setFilter(SpFileFilter.OTHER);
			break;
		default:
			break;
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		m_lvFile.setRefreshing();
		loadData(true);
	}

	@Override
	public void onLastItemVisible() {
		Debug.trace("LastItemVisible");
		if (!"00000000".equals(m_strLastAttachId) && loadingComplete == true) {
			loadingComplete = false;
			loadData(false);
		}
	}

	private void downloadFileOpen(SpAttachVO vo) {
		downloadFile(vo.getContentsId(), vo.getAttachId(),
				getTargetFilePath(vo.getFileName(), vo.getFileExt()));
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Debug.trace("onItemClick : " + --position);
		if (HMGWServiceHelper.doc_transform_server_use) {
			MainModel.getInstance().getOpenApiService().getDocHandlerResult(getActivity(), m_data.get(position));
		} else {
			// SEOJAEHWA : 권한체크 추가
			downloadFileOpen(m_data.get(position));
		}
	}
}