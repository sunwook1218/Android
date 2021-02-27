package com.hs.mobile.gw.fragment.squareplus.view;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpSystemMessageView extends LinearLayout {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd aa h:mm",
			Locale.getDefault());
	public SpSystemMessageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		LayoutInflater.from(context).inflate(R.layout.sp_content_system, this);
	}

	public SpSystemMessageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.sp_content_system, this);
	}

	public SpSystemMessageView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.sp_content_system, this);
	}

	public void setData(final SpContentsVO item, final ISpCompletionViewListener spCompletionViewListener, boolean isOriginView) {
		((TextView) findViewById(R.id.ID_TV_DATE)).setText(sdf.format(item.getCreateDate()));
		final SpCompletionView spCompletionView = (SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW);
		spCompletionView.setData(item, spCompletionViewListener);
		spCompletionView.setMaxLines(3);
		spCompletionView.setEllipsize(TruncateAt.END);

		LinearLayout originLayout = (LinearLayout) findViewById(R.id.ID_LL_ORIGIN);
		TextView originTextView = (TextView) findViewById(R.id.ID_TV_ORIGIN);
		if (isOriginView) {
			originTextView.setText(item.getSquareTitle());
			originLayout.setVisibility(View.VISIBLE);
		} else {
			originLayout.setVisibility(View.GONE);
		}
	}
}
