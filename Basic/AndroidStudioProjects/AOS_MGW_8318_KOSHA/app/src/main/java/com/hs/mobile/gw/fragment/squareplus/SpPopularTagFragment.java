package com.hs.mobile.gw.fragment.squareplus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.squareplus.SpPopularTagAdapter;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.openapi.squareplus.SpGetPopularTagList;
import com.hs.mobile.gw.openapi.squareplus.SpGetRecommendTagList;
import com.hs.mobile.gw.openapi.squareplus.callback.SpTagListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;

public class SpPopularTagFragment extends CommonFragment implements OnClickListener, OnItemClickListener {
	private String m_strSquareId;
	private List<SpTagVO> m_data;
	private SpPopularTagAdapter adapter;
	
	private ImageView m_btnBack;
	private ListView m_tagListView;

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Debug.trace("");
		super.onCreate(savedInstanceState);
		if (getArguments() != null && getArguments().containsKey(MainModel.ARG_KEY_SQUARE_ID)) {
			m_strSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
		} 
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Debug.trace("");
		View v = inflater.inflate(R.layout.fragment_sp_popular_tag, container, false);
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_tagListView = (ListView) v.findViewById(R.id.ID_LV_POPULAR_TAG);
		
		m_data = new ArrayList<SpTagVO>();
		adapter = new SpPopularTagAdapter(m_data);
		m_tagListView.setAdapter(adapter);
		m_tagListView.setOnItemClickListener(this);
		
		m_btnBack.setOnClickListener(this);
		
		callPopularTagApi();
		
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			if (getFragmentManager().getBackStackEntryCount() > 0)
				getFragmentManager().popBackStack();
			else
				getActivity().finish();
			break;
		}
	}
	
	public void callPopularTagApi() {
		HashMap<String, String> params = new HashMap<>();
		params.put("squareId", m_strSquareId);
		SpTagListCallBack spTagListCallBack = new SpTagListCallBack(
				getActivity(), SpTagVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				Debug.trace(this.dataList);
				m_data.addAll(this.dataList);
				adapter.notifyDataSetChanged();
			}
		};
		new ApiLoaderEx<>(new SpGetPopularTagList(getActivity()),
				getActivity(), spTagListCallBack, params).execute();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

		Bundle bd = new BundleUtils.Builder()
				.add(SpContentsSubFragment.ARG_KEY_CONTENTS_VIEW_TYPE, SpContentsSubFragment.ContentsViewType.POPULAR_TAG)
				.add(SpContentsSubFragment.ARG_KEY_SP_TAG_KEYWORD, m_data.get(position).getTagName())
				.add(MainModel.ARG_KEY_SQUARE_ID, m_strSquareId).build();
		if (getMainModel().isTablet()) {
			MainModel.getInstance().showFragmentToTarget(getActivity(), new SpContentsSubFragment(), bd, R.id.ID_LAY_L_FRAGMENT_LAYOUT,
					true, getString(SpContentsSubFragment.ContentsViewType.POPULAR_TAG.getTitleRes()));
		} else {
			getMainModel().showSubActivity(getActivity(), SubActivityType.SP_POPULAR_TAG_RESULT, bd);
		}
	}
	
}
