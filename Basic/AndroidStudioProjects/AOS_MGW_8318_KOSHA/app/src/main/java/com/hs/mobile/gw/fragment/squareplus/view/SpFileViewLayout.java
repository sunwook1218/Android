package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;

public class SpFileViewLayout extends LinearLayout {

	private ISpClickListener m_listener;
	private List<SpAttachVO> data;

	public SpFileViewLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SpFileViewLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SpFileViewLayout(Context context) {
		super(context);
	}

	public void setData(List<SpAttachVO> items,
			ISpClickListener listener) {
		data = items;
		m_listener = listener;
		setOrientation(LinearLayout.VERTICAL);
		removeAllViews();
		for (SpAttachVO vo : items) {		
			if (TextUtils.equals(vo.getAttachType(), "0")) {
				SpFileView child = new SpFileView(getContext());
				child.setData(vo, listener);
				addView(child, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			}
		}
	}
	public void addData(SpAttachVO file) {
		data.add(file);
		SpFileView child = new SpFileView(getContext());
		child.setData(file, m_listener);
		addView(child, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	}
}
