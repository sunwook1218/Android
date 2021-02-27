package com.hs.mobile.gw.fragment;

import org.apache.cordova.LOG;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.WebViewBridge;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DocViewFragment extends CordovaWebViewFragment implements OnClickListener {
	public static final String ARG_KEY_DATA = "data";
	public static final String ARG_KEY_SQUARE = "square";
	private AlertDialog mDocPagePopUpList;
	private RelativeLayout mDocHeader;
	private TextView mDocTitle;
	private TextView mDocPageNO;
	private int pageCount;
	private TextView mClose;
	private String[] pageNumItems;
	private int lastSelectedPos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		mDocHeader = (RelativeLayout) v.findViewById(R.id.docViewerHeader);
		mClose = (TextView) mDocHeader.findViewById(R.id.docViewerClose);
		mDocTitle = (TextView) mDocHeader.findViewById(R.id.docViewerFileName);
		mDocPageNO = (TextView) mDocHeader.findViewById(R.id.docViewerPageNO);
		LinearLayout docViewerWebViewContainer = (LinearLayout) v.findViewById(R.id.docViewerWebViewContainer);
		final GestureDetector gestureDetector = new GestureDetector(new CustomeGestureDetector());
		getWebView().getView().setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		});
		mClose.setOnClickListener(this);
		mDocPageNO.setOnClickListener(this);

		((WebView) getWebView().getEngine().getView()).getSettings().setJavaScriptEnabled(true);
		((WebView) getWebView().getEngine().getView()).addJavascriptInterface(new WebViewBridge((WebView) getWebView().getEngine().getView()), "WebViewBridge");
		getWebView().loadUrl("file:///android_asset/www/view/attach/doc_viewer_common.html");
		docViewerWebViewContainer.addView(getWebView().getView(), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		return v;
	}

	@Override
	public int getRootViewResourceId() {
		return R.layout.doc_viewer;
	}

	private class CustomeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (e1 == null || e2 == null)
				return false;
			if (e1.getPointerCount() > 1 || e2.getPointerCount() > 1)
				return false;
			else {
				// right to left swipe .. go to next page
				if (e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 800) {
					// do your stuff
					LOG.i("DEBUG", "Right to Left!!");
					moveToRight();
					return true;
				} // left to right swipe .. go to prev page
				else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 800) {
					// do your stuff
					LOG.i("DEBUG", "Left to Right!!");
					moveToLeft();
					return true;
				} // bottom to top, go to next document
				else if (e1.getY() - e2.getY() > 100
						&& Math.abs(velocityY) > 800
						&& ((WebView) getWebView().getView()).getScrollY() >= ((WebView) getWebView().getView()).getScale()
								* (((WebView) getWebView().getView()).getContentHeight() - ((WebView) getWebView().getView())
										.getHeight())) {
					// do your stuff
					return true;
				} // top to bottom, go to prev document
				else if (e2.getY() - e1.getY() > 100 && Math.abs(velocityY) > 800) {
					// do your stuff
					return true;
				}
				return false;
			}
		}
	}

	public void setDocViewerCurrentPage(int pageNo) {
		int pos = pageNo - 1;
		lastSelectedPos = pos;
		mDocPageNO.setText(pageNumItems[pos]);
	}

	public void showDocViewerHeader() {
		if (mDocHeader.getVisibility() != View.VISIBLE) {
			mDocHeader.setAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.fade_in));
			mDocHeader.setVisibility(View.VISIBLE);
		}
	}

	public void hideDocViewerHeader() {
		if (mDocHeader.getVisibility() != View.GONE) {
			mDocHeader.setAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(), android.R.anim.fade_out));
			mDocHeader.setVisibility(View.GONE);
		}
	}

	public void setDocViewerHeader(final String title, final int pageCount) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mDocTitle.setText(title);
				pageNumItems = new String[pageCount];
				if (pageCount > 1) {
					mDocPageNO.setVisibility(View.VISIBLE);
					for (int i = 0; i < pageCount; i++) {
						pageNumItems[i] = String.valueOf((i + 1) + "/" + pageCount);
					}
					mDocPageNO.setText(pageNumItems[0]);
				} else {
					mDocPageNO.setText("1");
				}
			}
		});
	}

	private void moveToLeft() {
		String left = "javascript:moveToLeft();";
		if (lastSelectedPos > 0) {
			lastSelectedPos--;
		}
		mDocPageNO.setText(pageNumItems[lastSelectedPos]);
		getWebView().loadUrl(left);
	}

	private void moveToRight() {
		String right = "javascript:moveToRight();";
		if (lastSelectedPos < (pageCount - 1)) {
			lastSelectedPos++;
		}
		mDocPageNO.setText(pageNumItems[lastSelectedPos]);
		getWebView().loadUrl(right);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.docViewerClose:
			getActivity().finish();
			break;
		case R.id.docViewerPageNO:
			if (pageNumItems != null && pageNumItems.length > 1) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle(R.string.message_docviewer_pickup_number);
				final String[] items = pageNumItems;
				builder.setSingleChoiceItems(items, lastSelectedPos, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int position) {
						getWebView().loadUrl("javascript:showDocViewerPage(" + (position + 1) + ");");
						lastSelectedPos = position;
						mDocPageNO.setText(items[position]);
						dialog.dismiss();
					}
				});
				mDocPagePopUpList = builder.create();
				mDocPagePopUpList.show();
			}
			break;
		}
	}

	@Override
	public boolean onPreBackKeyPressed() {
		return super.onPreBackKeyPressed();
	}
}
