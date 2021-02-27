package com.hs.mobile.gw.fragment.squareplus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment.PopupStatus;
import com.hs.mobile.gw.fragment.squareplus.view.CompletionSpan;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteCompletionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteMentionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpWritePopupView.IWritePopupCloseListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpWritePopupView.IWritePopupResultListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteTagView;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.squareplus.SpGetContents;
import com.hs.mobile.gw.openapi.squareplus.SpGetRecommendTagList;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquareMentionList;
import com.hs.mobile.gw.openapi.squareplus.SpModifyContents;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsMentionListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpTagListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.ApiLoaderExMultipart;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;

public class SpOpinionModifyFragment extends CommonFragment implements
		IChangeContentsListener, ISquarePushListener, OnClickListener,
		IWritePopupResultListener, IWritePopupCloseListener,
		ISpCompletionViewListener {
	private int fontColor = 0xFF7086D3;
	private String m_squareId;
	private SpWriteMentionView mentionLayout;
	private SpWriteTagView tagLayout;
	private TextView tvTitle;
	private SpWriteCompletionView edWrite;
	private LinearLayout writeLayout;
	private View btnSend;
	private String contentsId;
	private SpContentsVO m_squareData;
	private boolean m_bDraw = true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 전달 받은 데이터 확인

		if (getArguments() != null) {
			if (getArguments().getString(MainModel.ARG_KEY_SQUARE_ID) != null) {
				m_squareId = getArguments().getString(
						MainModel.ARG_KEY_SQUARE_ID);
			}
			if (getArguments().getString(MainModel.ARG_KEY_SP_CONTENTS_ID) != null) {
				contentsId = getArguments().getString(
						MainModel.ARG_KEY_SP_CONTENTS_ID);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_sp_opinion_modify,
				container, false);
		v.findViewById(R.id.ID_BTN_BACK).setOnClickListener(this);
		tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
		edWrite = (SpWriteCompletionView) v.findViewById(R.id.ID_ED_WRITE);
		edWrite.setBackgroundColor(getActivity().getResources().getColor(
				R.color.white));
		btnSend = v.findViewById(R.id.ID_BTN_SEND);
		btnSend.setOnClickListener(this);
//		v.findViewById(R.id.ID_BTN_TAG).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_MENTION).setOnClickListener(this);
		writeLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_WRITE);
		mentionLayout = (SpWriteMentionView) v
				.findViewById(R.id.ID_LAY_L_MENTION);
		mentionLayout.setResultListener(this);
		mentionLayout.setPopupCloseListener(this);
		tagLayout = (SpWriteTagView) v.findViewById(R.id.ID_LAY_L_TAG);
		tagLayout.setResultListener(this);
		tagLayout.setPopupCloseListener(this);
		showPopup(PopupStatus.WRITE);
		edWrite.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			@Override
            public boolean onPreDraw() {
                return m_bDraw;
            }
        });
		SpContentsMentionListCallBack spGetSquareMentionListCallBack = new SpContentsMentionListCallBack(
				getActivity(), SpContentsMentionVO.class) {

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				mentionLayout.setData(this.dataList);
				edWrite.setMentionData(this.dataList);
				if (!TextUtils.isEmpty(contentsId)) {
					SpContentsCallBack spSquareCallBack = new SpContentsCallBack(
							getActivity(), SpContentsVO.class) {

						@Override
						public void callback(String url, JSONObject json,
								AjaxStatus status) {
							super.callback(url, json, status);
							m_squareData = item;
							edWrite.setData(m_squareData);
							edWrite.setText(m_squareData.getBody(),BufferType.SPANNABLE);
						}
					};

					{ // API명
						HashMap<String, String> params3 = new HashMap<>();
						params3.put("squareId", m_squareId);
						params3.put("contentsId", contentsId);
						new ApiLoaderEx<>(new SpGetContents(getActivity()),
								getActivity(), spSquareCallBack, params3)
								.execute();
					}
				}
			}
		};

		HashMap<String, String> params = new HashMap<>();
		params.put("squareId", m_squareId);
		new ApiLoaderEx<>(new SpGetSquareMentionList(getActivity()),
				getActivity(), spGetSquareMentionListCallBack, params)
				.execute();
		HashMap<String, String> params2 = new HashMap<>();
		params2.put("squareId", m_squareId);
		SpTagListCallBack spTagListCallBack = new SpTagListCallBack(
				getActivity(), SpTagVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				Debug.trace(this.dataList);
				tagLayout.setData(this.dataList);
			}
		};
		new ApiLoaderEx<>(new SpGetRecommendTagList(getActivity()),
				getActivity(), spTagListCallBack, params2).execute();

		return v;
	}

	@Override
	public void onPushReceive(SquarePushVO vo) {

	}

	@Override
	public void onChange(ChangeType t, MainContentsListItemVO item) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			// switch (mPopupStatus) {
			// case MENTION:
			// case TAG:
			// showPopup(PopupStatus.WRITE);
			// break;
			// case WRITE:
			getActivity().finish();
			// break;
			// }
			break;
		case R.id.ID_BTN_SEND:
			if (TextUtils.isEmpty(edWrite.getText().toString())) {
				PopupUtil.showDialog(getActivity(), "글이 없습니다.");
			} else {
				send(edWrite.getText());
			}
			break;
		case R.id.ID_BTN_MENTION:
			// 맨션
			showPopup(PopupStatus.MENTION);
			break;
		case R.id.ID_BTN_TAG:
			// 테그
			showPopup(PopupStatus.TAG);
			break;
		}
	}

	private void send(Editable text) {
		TextViewUtils.hideKeyBoard(getActivity(), edWrite);
		PopupUtil.showLoading(getActivity());
		m_bDraw = false;
		String sb = new String(text.toString());
		CompletionSpan[] tokens = text.getSpans(0, text.length(), CompletionSpan.class);
		for (CompletionSpan token : tokens) {
			if (token.getToken() instanceof SpContentsMentionVO) {
				sb = sb.replace("@" + ((SpContentsMentionVO) token.getToken()).getItemName(),
						"@{u" + ((SpContentsMentionVO) token.getToken()).getItemId() + "}");
			}
		}
		edWrite.setText(text.toString(), BufferType.SPANNABLE);
		SpContentsCallBack spContentsCallBack = new SpContentsCallBack(getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				PopupUtil.hideLoading(getActivity());
				// 글이 수정되면 상위 Fragment에 이벤트를 전달 한다.
				MainModel.getInstance().notifyChanged(this.item, com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
				getActivity().finish();
			}
		};
		{ // API명
			HashMap<String, Object> params = new HashMap<>();
			params.put("userID", HMGWServiceHelper.userId);
			params.put("squareId", m_squareId); // 스퀘어id
			params.put("contentsType", m_squareData.getContentsType());
			params.put("contentsId", contentsId);
			params.put("body", sb.toString()); // 의견내용
			new ApiLoaderExMultipart<>(new SpModifyContents(getActivity()), getActivity(), spContentsCallBack, params).execute();
		}
	}

	public byte[] readFile(String strFilePath) {
		byte[] bArData = null;
		FileInputStream oInputStream = null;
		try {
			oInputStream = new FileInputStream(strFilePath);
			int nCount = oInputStream.available();
			if (nCount > 0) {
				bArData = new byte[nCount];
				oInputStream.read(bArData);
			}

			
		} catch (FileNotFoundException e) {
			Debug.trace(e.getMessage());
		} catch (IOException e) {
			Debug.trace(e.getMessage());
		} catch (Exception e){
			Debug.trace(e.getMessage());
		} finally{
			if (oInputStream != null) {
				try {
					oInputStream.close();
				} catch (IOException e) {
					Debug.trace(e.getMessage());
				}
			}
		}
		return bArData;
	}

	private void showPopup(PopupStatus popupStatus) {
		switch (popupStatus) {
		case WRITE:
			TextViewUtils.showKeyboard(getActivity(), edWrite);
			tvTitle.setText(R.string.label_squareplus_opinion_write);
			writeLayout.setVisibility(View.VISIBLE);
			mentionLayout.setVisibility(View.GONE);
			tagLayout.setVisibility(View.GONE);
			btnSend.setVisibility(View.VISIBLE);
			edWrite.requestFocus();
			break;
		case MENTION:
			tvTitle.setText("맨션");
			writeLayout.setVisibility(View.GONE);
			mentionLayout.setVisibility(View.VISIBLE);
			tagLayout.setVisibility(View.GONE);
			btnSend.setVisibility(View.GONE);
			mentionLayout.onShow();
			break;
		case TAG:
			tvTitle.setText("테그");
			writeLayout.setVisibility(View.GONE);
			mentionLayout.setVisibility(View.GONE);
			tagLayout.setVisibility(View.VISIBLE);
			btnSend.setVisibility(View.GONE);
			tagLayout.onShow();
			break;
		default:
			break;
		}
	}

	@Override
	public void onMentionClick(SpContentsMentionVO item) {

	}

	@Override
	public void onTagClick(final String replace) {
	}

	@Override
	public void onUserClick(String itemId) {

	}

	@Override
	public void onBtnTagAddClick() {
		showPopup(PopupStatus.TAG);
	}

	@Override
	public void onWritePopupClose(PopupStatus status) {
		switch (status) {
		case MENTION:
		case TAG:
			showPopup(PopupStatus.WRITE);
			break;
		case WRITE:
			break;

		}

	}

	@Override
	public void onWritePopupResult(PopupStatus popup, DefaultVO vo) {
		showPopup(PopupStatus.WRITE);
		switch (popup) {
		case MENTION:
			SpContentsMentionVO spContentsMentionVO = (SpContentsMentionVO) vo;
			String itemName = "@" + spContentsMentionVO.getItemName();
			SpannableString ssb = new SpannableString(itemName);

			ssb.setSpan(new CompletionSpan(fontColor, spContentsMentionVO), 0,
					itemName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			edWrite.getText().insert(edWrite.getSelectionStart(), ssb);
			break;
		case TAG:
			break;
		}
	}
}
