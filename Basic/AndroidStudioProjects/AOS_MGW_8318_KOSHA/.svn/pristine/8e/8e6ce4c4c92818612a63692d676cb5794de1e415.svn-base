package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpOpenGraphVO;

public class SpUrlPreviewLayout extends LinearLayout {

	private ISpClickListener m_listener;
	private List<SpOpenGraphVO> data;

	public SpUrlPreviewLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SpUrlPreviewLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SpUrlPreviewLayout(Context context) {
		super(context);
	}

	public void setData(List<SpOpenGraphVO> items,
			ISpClickListener listener) {
		data = items;
		m_listener = listener;
		setOrientation(VERTICAL);
		removeAllViews();
		for (SpOpenGraphVO vo : items) {
			if (vo != null) {
				SpUrlPreviewView child = new SpUrlPreviewView(getContext());
				child.setData(vo, listener);
				addView(child, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			}
		}
	}
	public void addData(SpOpenGraphVO preview) {
		data.add(preview);
		SpUrlPreviewView child = new SpUrlPreviewView(getContext());
		child.setData(preview, m_listener);
		addView(child, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	}
}
