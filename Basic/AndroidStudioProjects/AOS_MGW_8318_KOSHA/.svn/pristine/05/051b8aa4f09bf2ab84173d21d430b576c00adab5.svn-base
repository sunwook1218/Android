package com.hs.mobile.gw.fragment;

import java.util.ArrayList;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IActivityResultHandlerListener;
import com.hs.mobile.gw.MainModel.IChangeContentsListener.ChangeType;
import com.hs.mobile.gw.MainModel.ResultType;
import com.hs.mobile.gw.MainModel.SquareAction;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.AddTopic;
import com.hs.mobile.gw.openapi.square.ModifyContents;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.FileItemView;
import com.hs.mobile.gw.view.FileItemView.IFileItemViewDeleteListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AddTopicFragment extends CommonFragment implements View.OnClickListener, OnItemClickListener, IActivityResultHandlerListener,
		IFileItemViewDeleteListener {
	private EditText m_edTopicTitle;
	private EditText m_edDescription;
	private Button m_btnMakeTopic;
	private Button m_btnCancel;
	private Button m_btnFileAdd;
	private LinearLayout m_fileAttachArea;
	private String m_strTargetSquareId;
	private LinearLayout m_fileAttachLayout;
	private ArrayList<String> m_filePathData = new ArrayList<String>();
	private SquareAction m_mode;
	private MainContentsListItemVO m_orignalData;
	private MainContentsListItemVO m_squareData;
	private TextView m_tvNaviTitle;

	private void setMode(SquareAction action) {
		switch (action) {
		case MODIFY:
			m_mode = SquareAction.MODIFY;
			m_tvNaviTitle.setText(R.string.label_square_add_topic_modify_title);
			m_orignalData = (MainContentsListItemVO) getArguments().getSerializable(MainModel.ARG_KEY_SQUARE_ITEM);
			if (m_orignalData != null) {
				m_edTopicTitle.setText(m_orignalData.title);
				if (m_orignalData.attachList != null && m_orignalData.attachList.size() > 0) {
					if (m_fileAttachLayout.getVisibility() != View.VISIBLE) {
						m_fileAttachLayout.setVisibility(View.VISIBLE);
					}
					for (int i = 0; i < m_orignalData.attachList.size(); ++i) {
						FileItemView fiv = new FileItemView(getActivity());
						fiv.setDeleteListener(this);
						fiv.setData(m_orignalData.attachList.get(i));
						m_fileAttachArea.addView(fiv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					}
				}
				m_edDescription.setText(m_orignalData.body);
			}
			break;
		case ADD:
			m_mode = SquareAction.ADD;
			break;
		default:
			break;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_add_topic, container, false);
		super.createHead(v); // create sub header
		m_tvNaviTitle = (TextView) v.findViewById(R.id.ID_TV_NAVI_TITLE);
		m_btnCancel = (Button) v.findViewById(R.id.ID_BTN_CANCEL);
		m_btnMakeTopic = (Button) v.findViewById(R.id.ID_BTN_MAKE_TOPIC);
		m_edTopicTitle = (EditText) v.findViewById(R.id.ID_ED_TOPIC_TITLE);
		m_btnFileAdd = (Button) v.findViewById(R.id.ID_BTN_FILE_ADD);
		m_fileAttachLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_ATTACH_LAYOUT);
		m_fileAttachArea = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_ATTACH_AREA);
		m_edDescription = (EditText) v.findViewById(R.id.ID_ED_TOPIC_DESC);
		m_btnCancel.setOnClickListener(this);
		m_btnMakeTopic.setOnClickListener(this);
		m_btnFileAdd.setOnClickListener(this);
		m_fileAttachLayout.setVisibility(View.GONE);

		if (getArguments() != null) {
			if (getArguments().getString(MainModel.ARG_KEY_SQUARE_ID) != null) {
				m_strTargetSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
			}
			if (getArguments().getInt(MainModel.ARG_KEY_SQUARE_ACTION, -1) == MainModel.SQUARE_ACTION_MODIFY) {
				m_squareData = (MainContentsListItemVO) getArguments().getSerializable(MainModel.ARG_KEY_SQUARE_ITEM);
				m_strTargetSquareId = m_squareData.squareId;
				setMode(SquareAction.MODIFY);
			} else {
				setMode(SquareAction.ADD);
			}
		}

		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_CANCEL:
			getActivity().finish();
			break;
		case R.id.ID_BTN_MAKE_TOPIC:
			if (MainModel.getInstance().checkFileSize(m_filePathData) > 0) {
				PopupUtil.showDialog(getActivity(), R.string.square_file_limit_message);
				return;
			}
			if (checkValidation(m_edTopicTitle.getText().toString().trim())) {
				switch (m_mode) {
				case ADD: {
					AddTopic api = new AddTopic();
					if (m_filePathData.size() > 0) {
						api.setFilePath(m_filePathData);
					}
					new ApiLoader(api, getActivity(), new SquareDefaultCallback() {
						@Override
						public void onResponse(String strRet) {
							super.onResponse(strRet);
							if (this.responseHead.resultCode == SUCCESS) {
								MainContentsListItemVO item = new MainContentsListItemVO(getJsonObject().optJSONObject("responseData"));
								MainModel.getInstance().notifyChanged(item, ChangeType.ADD);
								getActivity().finish();
							}
						}
					}, m_strTargetSquareId, m_edTopicTitle.getText().toString().trim(), m_edDescription.getText().toString().trim())
							.execute();
				}
					break;
				case MODIFY:
					if (checkValidation(m_edTopicTitle.getText().toString().trim())) {
						ModifyContents api = new ModifyContents();
						// 기존 첨부를 추가.
						StringBuilder sbAttach = new StringBuilder();
						for (int i = 0; i < m_fileAttachArea.getChildCount(); ++i) {
							if (m_fileAttachArea.getChildAt(i) instanceof FileItemView
									&& ((FileItemView) m_fileAttachArea.getChildAt(i)).getData() != null) {
								FileItemView fiv = (FileItemView) m_fileAttachArea.getChildAt(i);
								if (fiv.isChecked()) {
									sbAttach.append(fiv.getData().attachId).append(';');
								}
							}
						}
						// 마지막의 ;을 제거한다.
						if (sbAttach.length() > 0) {
							sbAttach.replace(sbAttach.lastIndexOf(";"), sbAttach.length(), "");
						}
						// 새로 올리는 첨부를 추가.
						if (m_filePathData.size() > 0) {
							api.setFilePath(m_filePathData);
						}
						new ApiLoader(api, getActivity(), new SquareDefaultCallback() {
							@Override
							public void onResponse(String strRet) {
								super.onResponse(strRet);
								if (this.responseHead.resultCode == SUCCESS) {
									MainContentsListItemVO item = new MainContentsListItemVO(getJsonObject().optJSONObject("responseData"));
									MainModel.getInstance().notifyChanged(item, ChangeType.MODIFY);
									getActivity().finish();
								}
							}
						}, m_squareData.contentsId, m_squareData.contentsType.getCode(), m_edDescription.getText().toString().trim(),
								m_edTopicTitle.getText().toString().trim(), sbAttach.toString()).execute();
						// "contentsId", "contentsType", "body", "title",
						// "orgAttachIdList", "dueDate", "member"
					}
				}
			}
			break;
		case R.id.ID_BTN_FILE_ADD:
			if (m_filePathData.size() >= Define.FILE_UPLOAD_LIMIT_COUNT) {
				PopupUtil.showDialog(getActivity(), R.string.square_attach_file_limit_count);
				return;
			} else {
				MainModel.getInstance().showUploadDialog(this, m_strTargetSquareId, true);
			}
			break;
		}
	}

	private boolean checkValidation(String strTitle) {
		if (TextUtils.isEmpty(strTitle)) {
			PopupUtil.showDialog(getActivity(), R.string.square_empty_title);
			return false;
		}
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_CAMERA:
			case MainModel.REQ_VIDEO:
			case MainModel.REQ_ALBUM:
			case MainModel.REQ_FILE:
				MainModel.getInstance().activityResultMediaHandler(getActivity(), requestCode, resultCode, data, this);
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onLoadCompleteMedia(ResultType filePath, String strRet) {
		if (m_fileAttachLayout.getVisibility() != View.VISIBLE) {
			m_fileAttachLayout.setVisibility(View.VISIBLE);
		}
		FileItemView fiv = new FileItemView(getActivity());
		fiv.setDeleteListener(this);
		fiv.setFilePath(strRet);
		m_filePathData.add(strRet);
		m_fileAttachArea.addView(fiv, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	}

	@Override
	public void onDelete(View v) {
		if (v instanceof FileItemView) {
			m_filePathData.remove(((FileItemView) v).getFilePath());
			m_fileAttachArea.removeView(v);
		}
		if (m_fileAttachArea.getChildCount() == 0) {
			m_fileAttachLayout.setVisibility(View.GONE);
		}
	}
}
