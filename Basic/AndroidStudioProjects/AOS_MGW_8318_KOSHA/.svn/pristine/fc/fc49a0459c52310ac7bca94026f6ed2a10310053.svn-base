package com.hs.mobile.gw.fragment.squareplus.view;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;

public class SquareView extends LinearLayout {

	private ISpClickListener listener;
	private ISpCompletionViewListener spCompletionViewListener;

	public SquareView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		LayoutInflater.from(context).inflate(R.layout.sp_content_topic, this);
	}

	public SquareView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.sp_content_topic, this);
	}

	public SquareView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.sp_content_topic, this);
	}

	public void setSpClickListener(ISpClickListener listener) {
		this.listener = listener;
	}
	public void setSpCompletionViewListener (ISpCompletionViewListener listener) {
		this.spCompletionViewListener = listener;
	}

	public void setData(final SpContentsVO item) {
		((TextView) findViewById(R.id.ID_TV_NAME)).setText(item.getAuthorName());
		((TextView) findViewById(R.id.ID_TV_DEPARTMENT))
				.setText(item.getAuthorDeptName() + " " + item.getAuthorPositionName());
		((TextView) findViewById(R.id.ID_TV_DATE)).setText(
				new SimpleDateFormat(item.getCreateDateFormat(), Locale.getDefault()).format(item.getCreateDate()));
		((SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW)).setData(item,spCompletionViewListener);
		((SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW)).setMaxLines(3);
		((SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW)).setEllipsize(TruncateAt.END);
		((SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onDetailView(item, false);
			}
		});
	}

}
