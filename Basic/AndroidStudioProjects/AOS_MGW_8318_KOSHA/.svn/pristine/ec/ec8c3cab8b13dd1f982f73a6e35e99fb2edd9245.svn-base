package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.SpMentionAdapter;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment.PopupStatus;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;

public class SpWriteMentionView extends SpWritePopupView implements TextWatcher {
	private SpMentionAdapter adapter;
	private List<SpContentsMentionVO> m_data;
	private List<SpContentsMentionVO> m_showData = new ArrayList<>();
	private IWritePopupResultListener m_listener;

	public SpWriteMentionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SpWriteMentionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SpWriteMentionView(Context context) {
		super(context);
		init();
	}

	protected void init() {
		super.init();
		((TextView) findViewById(R.id.ID_TV_TYPE)).setText("@");
		((ListView) findViewById(R.id.ID_LV_SEARCH_RESULT_MENTION)).setVisibility(View.VISIBLE);
		((ExpandableListView) findViewById(R.id.ID_LV_SEARCH_RESULT_TAG)).setVisibility(View.GONE);
		adapter = new SpMentionAdapter(m_showData);
		getMentionListView().setAdapter(adapter);
		getEditView().addTextChangedListener(this);
		getSearchLayout().setVisibility(View.GONE);
	}

	public void setData(List<SpContentsMentionVO> data) {
		m_data = data;
		m_showData.addAll(m_data);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// m_showData.clear();
		// if (!m_data.isEmpty()) {
		// for (SpContentsMentionVO data : m_data) {
		// if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(data.getItemName())
		// && data.getItemName().startsWith(s.toString())) {
		// m_showData.add(data);
		// }
		// }
		// }
		// adapter.notifyDataSetChanged();
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		m_listener.onWritePopupResult(PopupStatus.MENTION,
				adapter.getItem(position));
	}

	public void setResultListener(IWritePopupResultListener listener) {
		m_listener = listener;
	}

	@Override
	protected PopupStatus getPopStatus() {
		return PopupStatus.MENTION;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		return false;
	};
}
