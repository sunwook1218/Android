package com.hs.mobile.gw.fragment;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;

import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IActivityResultHandlerListener;
import com.hs.mobile.gw.MainModel.IChangeContentsListener.ChangeType;
import com.hs.mobile.gw.MainModel.ResultType;
import com.hs.mobile.gw.MainModel.SquareAction;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.MemberSelectFragment.MemberSelectType;
import com.hs.mobile.gw.openapi.square.AddOrder;
import com.hs.mobile.gw.openapi.square.MemberSuggest;
import com.hs.mobile.gw.openapi.square.ModifyContents;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.ContentsMemberListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.FileItemView;
import com.hs.mobile.gw.view.FileItemView.IFileItemViewDeleteListener;
import com.hs.mobile.gw.view.SquareMemberCompletionView;
import com.hs.mobile.gw.view.tokenautocomplete.FilteredArrayAdapter;
import com.hs.mobile.gw.view.tokenautocomplete.TokenCompleteTextView.TokenDeleteStyle;
import com.hs.mobile.gw.view.tokenautocomplete.TokenListener;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AddCommandFragment extends CommonFragment implements View.OnClickListener, OnDateSetListener, TextWatcher, TokenListener,
		OnItemClickListener, IActivityResultHandlerListener, IFileItemViewDeleteListener {

	private EditText m_edCommandTitle;
	private Button m_btnAdd;
	private SquareMemberCompletionView m_edParticipant;
	private CheckBox m_cbDateSetting;
	private TextView m_tvDueDate;
	private EditText m_edDescription;
	private Button m_btnMakeCommand;
	private LinearLayout m_dueDateLayout;
	private SimpleDateFormat m_dateFormat;
	private Button m_btnCancel;
	private FilteredArrayAdapter<SquareMemberVO> adapter;
	private ArrayList<SquareMemberVO> mData = new ArrayList<SquareMemberVO>();
	private Date m_date;
	private Button m_btnFileAdd;
	private LinearLayout m_fileAttachArea;
	private String m_strTargetSquareId;
	private LinearLayout m_fileAttachLayout;
	private ArrayList<String> m_filePathData = new ArrayList<String>();
	private TextView m_tvNaviTitle;
	private MainContentsListItemVO m_orignalData;
	private SquareAction m_mode;
	private MainContentsListItemVO m_squareData;

	private void setMode(SquareAction action) {
		switch (action) {
		case MODIFY:
			m_mode = SquareAction.MODIFY;
			m_tvNaviTitle.setText(R.string.label_square_add_command_modify_title);
			m_orignalData = (MainContentsListItemVO) getArguments().getSerializable(MainModel.ARG_KEY_SQUARE_ITEM);
			if (m_orignalData != null) {
				m_edCommandTitle.setText(m_orignalData.title);
				for (ContentsMemberListItemVO vo : m_orignalData.contentsMemberList) {
					m_edParticipant.addObject(MainModel.getInstance().convertContentsMemberToSquareMember(vo));
				}
				// 종료일이 설정되어 있지 않음.
				m_date = new Date(m_orignalData.dueDate);
				if (MainModel.getInstance().getDefaultDate().compareTo(m_date) == 0) {
					m_cbDateSetting.setChecked(false);
				} else {
					m_cbDateSetting.setChecked(true);
					m_tvDueDate.setText(m_dateFormat.format(m_date));
				}
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
		View v = inflater.inflate(R.layout.fragment_add_command, container, false);
		super.createHead(v); // create sub header
		m_dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
		m_tvNaviTitle = (TextView) v.findViewById(R.id.ID_TV_NAVI_TITLE);
		m_btnCancel = (Button) v.findViewById(R.id.ID_BTN_CANCEL);
		m_btnMakeCommand = (Button) v.findViewById(R.id.ID_BTN_MAKE_COMMAND);
		m_edCommandTitle = (EditText) v.findViewById(R.id.ID_ED_COMMAND_TITLE);
		m_edParticipant = (SquareMemberCompletionView) v.findViewById(R.id.ID_ED_PARTICIPANT);
		m_btnAdd = (Button) v.findViewById(R.id.ID_BTN_TO_ADD);
		m_cbDateSetting = (CheckBox) v.findViewById(R.id.ID_CB_DATE_SETTING);
		m_dueDateLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_WORK_DUE_DATE);
		m_tvDueDate = (TextView) v.findViewById(R.id.ID_TV_DUE_DATE);
		m_btnFileAdd = (Button) v.findViewById(R.id.ID_BTN_FILE_ADD);
		m_fileAttachLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_ATTACH_LAYOUT);
		m_fileAttachArea = (LinearLayout) v.findViewById(R.id.ID_LAY_L_FILE_ATTACH_AREA);
		m_edDescription = (EditText) v.findViewById(R.id.ID_ED_COMMAND_DESC);
		m_date = new Date();
		m_tvDueDate.setText(m_dateFormat.format(m_date));
		m_btnCancel.setOnClickListener(this);
		m_btnAdd.setOnClickListener(this);
		m_btnMakeCommand.setOnClickListener(this);
		m_tvDueDate.setOnClickListener(this);
		m_btnFileAdd.setOnClickListener(this);
		m_fileAttachLayout.setVisibility(View.GONE);

		adapter = new FilteredArrayAdapter<SquareMemberVO>(getActivity(), R.layout.person_layout, mData) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					LayoutInflater l = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
					convertView = l.inflate(R.layout.person_layout, parent, false);
				}
				SquareMemberVO p = getItem(position);
				((TextView) convertView.findViewById(R.id.name)).setText(p.memberName);
				String[] strBuf = p.deptName.split("\\.");
				String deptName = strBuf[strBuf.length - 1];
				((TextView) convertView.findViewById(R.id.email)).setText(deptName);
//					((TextView) convertView.findViewById(R.id.email)).setVisibility(View.GONE);

				return convertView;
			}

			@Override
			protected boolean keepObject(SquareMemberVO obj, String mask) {
				mask = mask.toLowerCase(Locale.getDefault());
				return obj.memberName.toLowerCase(Locale.getDefault()).startsWith(mask);
			}
		};

		m_edParticipant.addTextChangedListener(this);
		m_edParticipant.setThreshold(2);
		m_edParticipant.setDeletionStyle(TokenDeleteStyle.Clear);
		m_edParticipant.setAdapter(adapter);
		m_edParticipant.setOnItemClickListener(this);
		m_edParticipant.setTokenListener(this);
		m_edParticipant.performBestGuess(false);
		m_edParticipant.setDuplicateParentStateEnabled(false);

		m_dueDateLayout.setVisibility(View.GONE);
		m_cbDateSetting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				m_dueDateLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
			}
		});

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
		case R.id.ID_BTN_TO_ADD:
			// 구성원 추가.
			getMainModel().showSubActivity(
					AddCommandFragment.this,
					SubActivityType.MEMBER_SELECT,
					new BundleUtils.Builder().add(MemberSelectFragment.ARG_KEY_SQUARE_ID, m_strTargetSquareId)
							.add(MemberSelectFragment.ARG_KEY_MEMBER_SELECT_TYPE, MemberSelectType.REQ_RETURN)
							.add(MemberSelectFragment.ARG_KEY_SELECTED_ITEMS, (Serializable) m_edParticipant.getObjects()).build());
			break;
		case R.id.ID_BTN_MAKE_COMMAND:
			if (MainModel.getInstance().checkFileSize(m_filePathData) > 0) {
				PopupUtil.showDialog(getActivity(), R.string.square_file_limit_message);
				return;
			}
			switch (m_mode) {
			case ADD: {
				String strDueDate = "";
				if (m_cbDateSetting.isChecked()) {
					strDueDate = m_dateFormat.format(m_date);
				}
				Debug.trace("dueDate:", strDueDate);
				SquareMemberVO[] l = Arrays.asList(m_edParticipant.getObjects().toArray()).toArray(
						new SquareMemberVO[m_edParticipant.getObjects().size()]);
				ArrayList<SquareMemberVO> selectedList = (new ArrayList<SquareMemberVO>(Arrays.asList(l)));
				if (checkValidation(m_edCommandTitle.getText().toString().trim(), selectedList)) {
					StringBuilder bd = new StringBuilder();
					for (SquareMemberVO member : selectedList) {
						bd.append(member.toString());
					}
					AddOrder api = new AddOrder();
					if (m_filePathData.size() > 0) {
						m_filePathData.clear();
					}
					// 새로 올리는 첨부를 추가.
					if (m_filePathData.size() > 0) {
						api.setFilePath(m_filePathData);
					}

					new ApiLoader(api, getActivity(), new SquareDefaultCallback() {
						@Override
						public void onResponse(String strRet) {
							super.onResponse(strRet);
							if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
								MainContentsListItemVO item = new MainContentsListItemVO(getJsonObject().optJSONObject("responseData"));
								MainModel.getInstance().notifyChanged(item, ChangeType.ADD);
								getActivity().finish();
							}
						}
					}, m_strTargetSquareId, m_edCommandTitle.getText().toString().trim(), m_edDescription.getText().toString().trim(),
							strDueDate, bd.toString()).execute();
				}
			}
				break;
			case MODIFY: {
				String strDueDate = "";
				if (m_cbDateSetting.isChecked()) {
					strDueDate = m_dateFormat.format(m_date);
				}
				SquareMemberVO[] l = Arrays.asList(m_edParticipant.getObjects().toArray()).toArray(
						new SquareMemberVO[m_edParticipant.getObjects().size()]);
				ArrayList<SquareMemberVO> selectedList = (new ArrayList<SquareMemberVO>(Arrays.asList(l)));
				if (checkValidation(m_edCommandTitle.getText().toString().trim(), selectedList)) {
					// 그룹 맴버를 String으로 변환.
					StringBuilder sbGroupMember = new StringBuilder();
					for (SquareMemberVO member : selectedList) {
						sbGroupMember.append(member.toString());
					}
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
							m_edCommandTitle.getText().toString().trim(), sbAttach.toString(), strDueDate, sbGroupMember.toString())
							.execute();
					// "contentsId", "contentsType", "body", "title",
					// "orgAttachIdList", "dueDate", "member"
				}
			}
				break;
			}
			break;
		case R.id.ID_TV_DUE_DATE:
			Debug.trace("R.id.ID_TV_END_DATE click");
			Calendar c = Calendar.getInstance();
			c.setTime(m_date);
			new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
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

	private boolean checkValidation(String strTitle, ArrayList<SquareMemberVO> memberList) {
		if (TextUtils.isEmpty(strTitle)) {
			PopupUtil.showDialog(getActivity(), R.string.square_empty_title);
			return false;
		}
		if (memberList.size() == 0) {
			PopupUtil.showDialog(getActivity(), R.string.square_empty_participant);
			return false;
		}
		return true;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		String str = s.toString();
		if (!TextUtils.isEmpty(str) && str.contains(",")) {
			str = str.substring(str.lastIndexOf(",") + 1, str.length());
		}
		if (str.length() >= 2) {
			Debug.trace("substring", str);
			resetNetwork();
			MemberSuggest api = new MemberSuggest();
			final String query = str.trim();
			addNetworkRequst(api);
			MainModel.getInstance().setLoadingProgressShow(false);
			new ApiLoader(api, getActivity(), new DefaultCallback() {
				@Override
				public void onResponse(String strRet) {
					super.onResponse(strRet);
					JSONArray data;
					try {
						data = new JSONArray(getResponseString());
						if (data != null) {
							mData.clear();
							for (int i = 0; i < data.length(); ++i) {
								mData.add(new SquareMemberVO(data.optJSONObject(i)));
							}
							adapter.notifyDataSetChanged();
							adapter.getFilter().filter(query, null);
						}
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
					MainModel.getInstance().setLoadingProgressShow(true);
				}
			}, m_strTargetSquareId, query).execute();
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void onTokenAdded(Object token) {
		if (token != null && token instanceof SquareMemberVO) {
			Debug.trace("onTokenAdded", ((SquareMemberVO) token).memberName);
		}
		updateTokenConfirmation();
	}

	@Override
	public void onTokenRemoved(Object token) {
		if (token != null && token instanceof SquareMemberVO) {
			Debug.trace("onTokenRemoved", ((SquareMemberVO) token).memberName);
		}
		updateTokenConfirmation();
	}

	private void updateTokenConfirmation() {
		StringBuilder sb = new StringBuilder("Current tokens:\n");
		for (Object token : m_edParticipant.getObjects()) {
			sb.append(((SquareMemberVO) token).memberName);
			sb.append('\n');
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_ORG_SELECT:
				// String strOrgSelected =
				// data.getStringExtra(CustomWebViewFragment.INTENT_KEY_ORG_SELECT);
				// OrgSelectedVO vo = new OrgSelectedVO(strOrgSelected);
				// for (SelectedListItem item : vo.selectedlist) {
				// m_edParticipant.addObject(item, item.name);
				// }
				// Debug.trace(vo);
				break;
			case MainModel.REQ_CAMERA:
			case MainModel.REQ_VIDEO:
			case MainModel.REQ_ALBUM:
			case MainModel.REQ_FILE:
				MainModel.getInstance().activityResultMediaHandler(getActivity(), requestCode, resultCode, data, this);
				break;
			case MainModel.REQ_SELECT_MEMBER:
				m_edParticipant.clear();
				Serializable members = data.getSerializableExtra(MemberSelectFragment.INTENT_KEY_MEMBERS);
				if (members != null && members instanceof ArrayList<?> && ((ArrayList<?>) members).get(0) instanceof SquareMemberVO) {
					@SuppressWarnings("unchecked")
					ArrayList<SquareMemberVO> list = (ArrayList<SquareMemberVO>) members;
					for (SquareMemberVO m : list) {
						m_edParticipant.addObject(m, m.memberName);
					}
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		Calendar c = Calendar.getInstance();
		c.set(year, monthOfYear, dayOfMonth);
		m_date = c.getTime();
		m_tvDueDate.setText(m_dateFormat.format(m_date));
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
