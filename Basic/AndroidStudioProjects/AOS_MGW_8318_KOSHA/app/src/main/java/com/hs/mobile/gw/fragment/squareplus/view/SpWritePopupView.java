package com.hs.mobile.gw.fragment.squareplus.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment.PopupStatus;
import com.hs.mobile.gw.openapi.vo.DefaultVO;

public abstract class SpWritePopupView extends LinearLayout implements
		OnItemClickListener, OnChildClickListener {
	public interface IWritePopupResultListener {
		void onWritePopupResult(PopupStatus popup, DefaultVO vo);
	}

	public interface IWritePopupCloseListener {
		void onWritePopupClose(PopupStatus status);
	}

	private ListView mentionListView;
	private ExpandableListView tagListView;
	private EditText editView;
	private IWritePopupCloseListener mPopupCloseListener;
	private LinearLayout searchLayout;

	public ListView getMentionListView() {
		return mentionListView;
	}

	public ExpandableListView getTagListView() {
		return tagListView;
	}

	public EditText getEditView() {
		return editView;
	}

	public SpWritePopupView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SpWritePopupView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();

	}

	public SpWritePopupView(Context context) {
		super(context);
		init();
	}

	protected void init() {
		LayoutInflater.from(getContext())
				.inflate(R.layout.sp_write_popup, this);
		searchLayout = (LinearLayout)findViewById(R.id.ID_LAY_L_SEARCH_LAYOUT);
		editView = (EditText) findViewById(R.id.ID_ED_SEARCH);
		mentionListView = (ListView) findViewById(R.id.ID_LV_SEARCH_RESULT_MENTION);
		tagListView = (ExpandableListView) findViewById(R.id.ID_LV_SEARCH_RESULT_TAG);
		editView.setFocusable(true);
		editView.setFocusableInTouchMode(true);
		mentionListView.setDivider(null);
		mentionListView.setDividerHeight(0);
		mentionListView.setOnItemClickListener(this);
		tagListView.setDivider(null);
		tagListView.setDividerHeight(0);
		tagListView.setOnItemClickListener(this);
		findViewById(R.id.ID_TV_CLOSE).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						mPopupCloseListener.onWritePopupClose(getPopStatus());
					}
				});
	}

	protected abstract PopupStatus getPopStatus();

	public void onShow() {
		editView.getText().clear();
		editView.requestFocus();
	}

	public void setPopupCloseListener(IWritePopupCloseListener listener) {
		mPopupCloseListener = listener;
	}
	
	public LinearLayout getSearchLayout(){
		return searchLayout;
	}
}
