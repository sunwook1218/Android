package com.hs.mobile.gw.view;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.util.Debug;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FooterToolBar extends LinearLayout implements OnClickListener {
	private ImageView m_btnLeft;
	private ImageView m_btnRight;
	private TextView m_tvCenter;

	public enum FooterToolBarMode {
		ONE_BUTTON, THREE_BUTTON
	}

	public enum ButtonPosition {
		LEFT, CENTER, RIGHT
	}

	public class FunctionButton {
		ButtonPosition buttonPosition;
		int nButtonResource;
		OnClickListener clickListener;
	}

	public interface IFooterToolBarListener {

		void onFooterToolBarClick(ButtonPosition bp, View v);
	}

	private IFooterToolBarListener m_listener;
	private FooterToolBarMode m_mode;

	public void setFooterToolbarListener(IFooterToolBarListener listener) {
		m_listener = listener;
	}

	public FooterToolBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FooterToolBar(Context context) {
		super(context);
		initView();
	}

	public FooterToolBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	protected void initView() {
		LayoutInflater.from(getContext()).inflate(R.layout.footer_layout, this);
		m_btnLeft = (ImageView) findViewById(R.id.ID_IMG_LEFT_FUNCTION);
		m_btnRight = (ImageView) findViewById(R.id.ID_IMG_RIGHT_FUNCTION);
		m_tvCenter = (TextView) findViewById(R.id.ID_TV_CENTER);
		m_btnLeft.setOnClickListener(this);
		m_btnRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (m_listener == null) {
			Debug.trace("Can't find footertoolbar listener");
			return;
		}
		switch (v.getId()) {
		case R.id.ID_IMG_LEFT_FUNCTION:
			m_listener.onFooterToolBarClick(ButtonPosition.LEFT, v);
			break;
		case R.id.ID_IMG_RIGHT_FUNCTION:
			m_listener.onFooterToolBarClick(ButtonPosition.RIGHT, v);
			break;
		}
	}

	public void setText(String date) {
		m_tvCenter.setText(date);
	}

	public void setMode(FooterToolBarMode mode) {
		m_mode = mode;
	}

	public void setLeftButtonImage(int nRes) {
		m_btnLeft.setImageResource(nRes);
		m_btnLeft.setVisibility(View.VISIBLE);
	}

	public void setRightButtonImage(int nRes) {
		m_btnRight.setImageResource(nRes);
		m_btnRight.setVisibility(View.VISIBLE);
	}

	public void hideRightButton() {
		m_btnRight.setVisibility(View.GONE);
	}
}
