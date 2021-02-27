package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.SpTagAdapter;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment.PopupStatus;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SpWriteTagView extends SpWritePopupView implements TextWatcher {
	private List<SpTagVO> m_data;
	private SpTagAdapter adapter;
	private IWritePopupResultListener m_listener;
	private String uuid;
	private ExpandableListView m_lvTagExpandableGroup;


	public SpWriteTagView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SpWriteTagView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SpWriteTagView(Context context) {
		super(context);
	}

	@Override
	protected void init() {
		super.init();
		m_data = new ArrayList<SpTagVO>();
		adapter = new SpTagAdapter(getContext(), m_data);
		getTagListView().setAdapter(adapter);
		((TextView) findViewById(R.id.ID_TV_TYPE)).setText("#");
		((ListView) findViewById(R.id.ID_LV_SEARCH_RESULT_MENTION)).setVisibility(View.GONE);
		m_lvTagExpandableGroup = (ExpandableListView) findViewById(R.id.ID_LV_SEARCH_RESULT_TAG);
		m_lvTagExpandableGroup.setVisibility(View.VISIBLE);
		m_lvTagExpandableGroup.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				// 제목영역 눌러도 효과가 발생하지 않도록 막는다.
				return true;
			}
		});
		m_lvTagExpandableGroup.setOnChildClickListener(this);
		getEditView().setHint("새로운  태그를 입력하세요.");
		// getEditView().addTextChangedListener(this);
		uuid = UUID.randomUUID().toString();
		getEditView().setImeOptions(EditorInfo.IME_ACTION_DONE);
		getEditView().setInputType(InputType.TYPE_CLASS_TEXT);
		TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				SpTagVO spTagVO = new SpTagVO();
				spTagVO.setTagId(uuid);
				spTagVO.setTagName(getEditView().getText().toString());
				m_listener.onWritePopupResult(PopupStatus.TAG, spTagVO);
				return false;
			}
		};
		getEditView().setOnEditorActionListener(editorActionListener);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		sendTag(groupPosition, childPosition);
		return false;
	}

	private void sendTag(int groupPosition, int childPosition) {
		m_listener.onWritePopupResult(PopupStatus.TAG, adapter.getChild(groupPosition, childPosition));
		// Iterator<SpTagVO> iterator = m_data.iterator();
		// while (iterator.hasNext()) {
		// SpTagVO vo = iterator.next();
		// if (vo.getTagId().equals(uuid)) {
		// iterator.remove();
		// }
		// }
		// adapter.notifyDataSetChanged();
	}

	public void setData(List<SpTagVO> dataList) {
		m_data.clear();
		m_data.addAll(dataList);
		adapter.setData(m_data, m_listener);
		adapter.notifyDataSetChanged();
		m_lvTagExpandableGroup.expandGroup(0);
		m_lvTagExpandableGroup.expandGroup(1);
	}

	public void setResultListener(IWritePopupResultListener listener) {
		m_listener = listener;
	}

	@Override
	protected PopupStatus getPopStatus() {
		return PopupStatus.TAG;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		SpTagVO spTagVO = null;
		for (SpTagVO vo : m_data) {
			if (vo.getTagId().equals(uuid)) {
				spTagVO = vo;
			}
		}
		if (spTagVO == null && s.length() > 0) {
			spTagVO = new SpTagVO();
			spTagVO.setTagId(uuid);
			m_data.add(0, spTagVO);
		}
		if (spTagVO != null) {
			if (s.toString().lastIndexOf(' ') != -1) {
				sendTag(0, 0);
			} else {
				spTagVO.setTagName(s.toString());
				adapter.notifyDataSetChanged();
			}
		}
	}
}
