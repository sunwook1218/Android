package com.hs.mobile.gw;

import java.net.URLEncoder;

import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class WriteCommentViewer extends Activity implements TextWatcher, OnClickListener {

	public static final String INTENT_KEY_IS_POPUP = "is_popup";
	public static final String INTENT_KEY_TARGET = "target";
	EditText commentBody;
	public static boolean wasProcessKilled = false;
	private boolean m_bMustOpinion;
	private boolean m_bApprOpinion;
	private boolean m_bHWPType;
	private String m_strCallBack;
	private Button cancelWriteComment;
	private Button confirmWriteComment;
	private CheckBox commentIntoDocument;
	private boolean m_bPopup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			Debug.trace("MainActivity is creating from the killed state.");
			wasProcessKilled = true;
			return;
		}
		setContentView(R.layout.write_comment);

		cancelWriteComment = (Button) findViewById(R.id.cancelWriteComment);
		confirmWriteComment = (Button) findViewById(R.id.confirmWriteComment);
		commentIntoDocument = (CheckBox) findViewById(R.id.CommentIntoDocument);

		commentBody = (EditText) findViewById(R.id.writeCommentBody);
		String comment = intent.getStringExtra("comment");
		m_bMustOpinion = intent.getBooleanExtra("mustOpinion", false);
		if (m_bMustOpinion) {
			confirmWriteComment.setEnabled(false);
		} else {
			confirmWriteComment.setEnabled(true);
		}
		m_bApprOpinion = intent.getBooleanExtra("isApprOpinion", false);
		// 결재의견 작성이면서 appr.approve.comment.intodocument = 'Y' 인 경우 의견본문 반영 여부 체크박스 표시
		Debug.trace("m_bApprOpinion = " + m_bApprOpinion);
		Debug.trace("HMGWServiceHelper.appr_approve_comment_intodocument = " + HMGWServiceHelper.appr_approve_comment_intodocument);

		m_bHWPType = (intent.getIntExtra("wordType", 0) == 3);
		Debug.trace("wordType = " + intent.getIntExtra("wordType", 0));

		if (m_bApprOpinion && HMGWServiceHelper.appr_approve_comment_intodocument && m_bHWPType) {
			commentIntoDocument.setVisibility(View.VISIBLE);
		}
		else {
			commentIntoDocument.setChecked(false);
			commentIntoDocument.setVisibility(View.GONE);
		}

		m_strCallBack = intent.getStringExtra("callback");
		m_bPopup = intent.getBooleanExtra(INTENT_KEY_IS_POPUP, false);

		commentBody.addTextChangedListener(this);
		// SEOJAEHWA : TextChangedListener 를 register 후 setText 를 해줌
		commentBody.setText(comment);
		cancelWriteComment.setOnClickListener(this);
		confirmWriteComment.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		if (wasProcessKilled) {
			wasProcessKilled = false;
			finish();
		}
		super.onStart();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.toString().trim().length() == 0 && m_bMustOpinion) {
			confirmWriteComment.setEnabled(false);
		} else {
			confirmWriteComment.setEnabled(true);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cancelWriteComment:
			finish();
			break;
		case R.id.confirmWriteComment:
			String data = URLEncoder.encode(commentBody.getText().toString().trim()).replace("+", "%20");
			data = data.replace("%0A","%250A");

			if (m_bApprOpinion) {
				if (m_bPopup) {
					CustomWebViewFragment.getWebView().loadUrl(m_strCallBack + "('" + data + "','"+ commentIntoDocument.isChecked() +"');");
				} else {
					MainFragment.getController().loadWebviewWithDelay(m_strCallBack + "('" + data + "','"+ commentIntoDocument.isChecked() +"');");
				}
			}
			else {
				if (m_bPopup) {
					CustomWebViewFragment.getWebView().loadUrl(m_strCallBack + "('" + data + "');");
				} else {
					MainFragment.getController().loadWebviewWithDelay(m_strCallBack + "('" + data + "');");
				}
			}
			finish();
			break;
		}
	}
}