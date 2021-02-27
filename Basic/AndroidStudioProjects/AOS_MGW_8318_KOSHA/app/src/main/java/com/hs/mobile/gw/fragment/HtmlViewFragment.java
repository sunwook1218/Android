package com.hs.mobile.gw.fragment;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HtmlViewFragment extends CordovaWebViewFragment  implements OnClickListener{
	public static final String ARG_KEY_URL = "url";
	public static final String ARG_KEY_TITLE = "title";
	private LinearLayout m_webViewContainer;
	private RelativeLayout mHtmlHeader;
	private TextView mHtmlTitle;
	private TextView mClose;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		String url = (String) this.getActivity().getIntent().getExtras().getBundle(SubActivity.INTENT_KEY_VALUE).get(ARG_KEY_URL);
		String title = (String) this.getActivity().getIntent().getExtras().getBundle(SubActivity.INTENT_KEY_VALUE).get(ARG_KEY_TITLE);

		View v = super.onCreateView(inflater, container, savedInstanceState);
		super.createHead(v); // create sub header
		
		mHtmlHeader = (RelativeLayout) v.findViewById(R.id.htmlViewerHeader);
		mClose = (TextView) mHtmlHeader.findViewById(R.id.htmlViewerClose);
		mHtmlTitle = (TextView) mHtmlHeader.findViewById(R.id.htmlViewerFileName);
		m_webViewContainer = (LinearLayout) v.findViewById(R.id.htmlViewerWebViewContainer);
		setHtmlViewerHeader(title);
		showHtmlViewerHeader();
		mClose.setOnClickListener(this);
		((WebView) getWebView().getEngine().getView()).getSettings().setLoadWithOverviewMode(true);
		((WebView) getWebView().getEngine().getView()).getSettings().setUseWideViewPort(true);

		getWebView().loadUrl(url);
		m_webViewContainer.addView(getWebView().getView(), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		return v;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.htmlViewerClose:
			getActivity().finish();
			break;
		}
	}

	@Override
	public int getRootViewResourceId() {
		return R.layout.fragment_html_viewer;
	}

	@Override
	public boolean onPreBackKeyPressed() {
		return super.onPreBackKeyPressed();
	}
	
	public void showHtmlViewerHeader() {
		if (mHtmlHeader.getVisibility() != View.VISIBLE) {
			mHtmlHeader.setAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.fade_in));
			mHtmlHeader.setVisibility(View.VISIBLE);
		}
	}

	public void hideHtmlViewerHeader() {
		if (mHtmlHeader.getVisibility() != View.GONE) {
			mHtmlHeader.setAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.fade_out));
			mHtmlHeader.setVisibility(View.GONE);
		}
	}
	public void setHtmlViewerHeader(final String title) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mHtmlTitle.setText(title);
//				pageNumItems = new String[pageCount];
//				if (pageCount > 1) {
//					mHtmlPageNO.setVisibility(View.VISIBLE);
//					for (int i = 0; i < pageCount; i++) {
//						pageNumItems[i] = String.valueOf((i + 1) + "/" + pageCount);
//					}
//					mHtmlPageNO.setText(pageNumItems[0]);
//				} else {
//					mHtmlPageNO.setText("1");
//				}
			}
		});
	}
}
