package com.hs.mobile.gw.fragment.squareplus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.Define;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IActivityResultHandlerListener;
import com.hs.mobile.gw.MainModel.IChangeContentsListener;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.MainModel.ResultType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.adapter.squareplus.SpWriteThumbnailAdapter;
import com.hs.mobile.gw.adapter.squareplus.SpWriteThumbnailAdapter.IThumbnailClick;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.fragment.squareplus.view.CompletionSpan;
import com.hs.mobile.gw.fragment.squareplus.view.ExpandableHeightGridView;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpFileViewLayout;
import com.hs.mobile.gw.fragment.squareplus.view.SpHashTagView;
import com.hs.mobile.gw.fragment.squareplus.view.SpUrlPreviewLayout;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteCompletionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteCompletionView.IUpdateUrlPreviewLisenter;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteMentionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpWritePopupView.IWritePopupCloseListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpWritePopupView.IWritePopupResultListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpWriteTagView;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.squareplus.SpAddTopic;
import com.hs.mobile.gw.openapi.squareplus.SpGetContents;
import com.hs.mobile.gw.openapi.squareplus.SpGetRecommendTagList;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquareMentionList;
import com.hs.mobile.gw.openapi.squareplus.SpGetUrlPreview;
import com.hs.mobile.gw.openapi.squareplus.SpModifyContents;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsMentionListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpOpenGraphCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpTagListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.ContentsType;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpOpenGraphVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;
import com.hs.mobile.gw.openapi.vo.DefaultVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.ApiLoaderExMultipart;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;

public class SpContentWriteFragment extends CommonFragment implements
		IChangeContentsListener, ISquarePushListener, OnClickListener,
		IActivityResultHandlerListener, IWritePopupResultListener,
		IWritePopupCloseListener, ISpCompletionViewListener, IThumbnailClick {
	protected static final int UPLOAD_ITEM_CAMERA = 0;
	protected static final int UPLOAD_ITEM_VIDEO = 1;
	protected static final int UPLOAD_ITEM_ALBUM = 2;
	protected static final int UPLOAD_ITEM_FILE = 3;
	public static final int REQ_CAMERA = 0x05;
	public static final int REQ_VIDEO = 0x06;
	public static final int REQ_ALBUM = 0x07;
	public static final int REQ_FILE = 0x08;
	private int fontColor = 0xFF7086D3;

	public static final int RC_READ_STORAGE_PERM_GET_ALBUM_IMAGE = 1251;
	public static final int RC_EXT_STORAGE_PERM_GET_ALBUM_IMAGE = 1252;

	public enum PopupStatus {
		WRITE, MENTION, TAG
	}

	private String m_squareId;
	private SpWriteMentionView mentionLayout;
	private SpWriteTagView tagLayout;
	private TextView tvTitle;
	private SpWriteCompletionView edWrite;
	private PopupStatus mPopupStatus;
	private LinearLayout writeLayout;
	private SpUrlPreviewLayout urlPreviewLayout;
	private ExpandableHeightGridView thumbnailGridView;
	private SpWriteThumbnailAdapter thumbnailAdapter;
	private SpFileViewLayout fileLayout;
	private List<SpAttachVO> m_fileList = new ArrayList<>();
	private ArrayList<SpAttachVO> thumbnails = new ArrayList<SpAttachVO>();
	private List<SpOpenGraphVO> graphList = new ArrayList<SpOpenGraphVO>(); 
	private WorkOrNoticeType currentWorkOrNoticeType = WorkOrNoticeType.NONE;
	private ImageButton btnNotice;
	private ImageButton btnWork;
	private View workDateLayout;
	private TextView tvWorkDate;
	private int mYear;
	private int mMonth;
	private int mDay;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd",
			Locale.getDefault());
	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
			Date date = cal.getTime();
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			tvWorkDate.setText(sdf.format(date));
		}
	};
	private View btnSend;
	private SpHashTagView tagBoxLayout;
	private String contentsId;
	private SpContentsVO m_squareData;
//	protected boolean m_bDraw = true;

	private enum WorkOrNoticeType {
		NONE, NOTICE, WORK;
	}

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
		final View v = inflater.inflate(R.layout.fragment_sp_content_write,
				container, false);
		v.findViewById(R.id.ID_BTN_BACK).setOnClickListener(this);
		tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
		edWrite = (SpWriteCompletionView) v.findViewById(R.id.ID_ED_WRITE);
		if (MainModel.getInstance().isTablet())
			edWrite.setMinLines(10);
		edWrite.setBackgroundColor(getActivity().getResources().getColor(
				R.color.white));
		edWrite.setUpdateUrlPreviewLisenter(new IUpdateUrlPreviewLisenter() {
			
			@Override
			public void updateUrlPreview(ArrayList<String> urlList) {
				// TODO Auto-generated method stub
				String urls = "";
				
				for (SpOpenGraphVO orgVo : graphList) {
					boolean bDel = true;
					if (orgVo != null) {
						for (int i = 0; i < urlList.size(); i++) {
							Debug.trace(orgVo.getUrl());
							Debug.trace(urlList.get(i));
							if (orgVo.getUrl().equals(urlList.get(i))) {
								bDel = false;
								break;
							}
						}
						if (bDel) {
							Debug.trace("remove = ", orgVo.getUrl());
							graphList.remove(orgVo);
						}
					}
				}
				setDataUrlPreview();
				
				for (int i = 0; i < urlList.size(); i++) {
					boolean bAdd = true;
					for (SpOpenGraphVO orgVo : graphList) {
						if (orgVo != null) {
							if (orgVo.getUrl().equals(urlList.get(i))) {
								bAdd = false;
								break;
							}
						}
					}
					
					if (bAdd) {
						urls = urls.concat(urlList.get(i));
						if (i < urlList.size()-1) {
							urls = urls.concat(";");
						}
					}
				}
				Debug.trace(urls);
				HashMap<String, String> params = new HashMap<>();
				params.put("urls", urls);
				SpOpenGraphCallBack spGetUrlPreviewCallBack = new SpOpenGraphCallBack(getActivity(), SpOpenGraphVO.class) {
					@Override
					public void callback(String url, JSONObject json, AjaxStatus status) {
						super.callback(url, json, status);
						Debug.trace(json.toString());
						
						if (dataList != null && dataList.size() > 0) {

							boolean isExist = false;
							for (SpOpenGraphVO vo : dataList) {
								for (SpOpenGraphVO orgVo : graphList) {
									if (vo.getUrl().equals(orgVo.getUrl())) {
										isExist = true;
									}
								}
								if (!isExist) {
									graphList.add(vo);
								}
							}
							setDataUrlPreview();
						}
					}
				};
				if (!TextUtils.isEmpty(urls))
					new ApiLoaderEx<>(new SpGetUrlPreview(getActivity()), getActivity(), spGetUrlPreviewCallBack, params).execute();
			}
		});
		btnNotice = (ImageButton) v.findViewById(R.id.ID_BTN_NOTICE);
		btnWork = (ImageButton) v.findViewById(R.id.ID_BTN_WORK);
		btnNotice.setOnClickListener(this);
		btnWork.setOnClickListener(this);
		workDateLayout = v.findViewById(R.id.ID_LAY_L_WORK_DATE);
		tvWorkDate = (TextView) v.findViewById(R.id.ID_TV_WORK_DATE);
		btnSend = v.findViewById(R.id.ID_BTN_SEND);
		btnSend.setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_CAMERA).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_ADD_FILE).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_TAG).setOnClickListener(this);
		v.findViewById(R.id.ID_BTN_MENTION).setOnClickListener(this);
		tagBoxLayout = (SpHashTagView) v.findViewById(R.id.ID_HASHTAG_VIEW);
		tagBoxLayout.setListener(this);
		tagBoxLayout.setInputMode(true);
		workDateLayout.setOnClickListener(this);
		
		// url preview
		urlPreviewLayout = (SpUrlPreviewLayout) v.findViewById(R.id.ID_URLPREVIEW_LAYOUT);
		
		thumbnailGridView = (ExpandableHeightGridView) v.findViewById(R.id.ID_THUMBNAIL_VIEW);
		thumbnailGridView.setExpanded(true);
		thumbnailAdapter = new SpWriteThumbnailAdapter(getActivity(), thumbnails);
		thumbnailAdapter.setClickListener(this);
		thumbnailGridView.setAdapter(thumbnailAdapter);

		fileLayout = (SpFileViewLayout) v.findViewById(R.id.ID_FILE_LAYOUT);
		writeLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_WRITE);
		mentionLayout = (SpWriteMentionView) v
				.findViewById(R.id.ID_LAY_L_MENTION);
		mentionLayout.setResultListener(this);
		mentionLayout.setPopupCloseListener(this);
		tagLayout = (SpWriteTagView) v.findViewById(R.id.ID_LAY_L_TAG);
		tagLayout.setResultListener(this);
		tagLayout.setPopupCloseListener(this);
		showPopup(PopupStatus.WRITE);
		setWorkOrNotice(WorkOrNoticeType.NONE);
		resetWorkDate();
		PopupUtil.showLoading(getActivity());
//		edWrite.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
//			@Override
//            public boolean onPreDraw() {
//                return m_bDraw;
//            }
//        });
		SpContentsMentionListCallBack spGetSquareMentionListCallBack = new SpContentsMentionListCallBack(
				getActivity(), SpContentsMentionVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				PopupUtil.hideLoading(getActivity());
				mentionLayout.setData(this.dataList);
				edWrite.setMentionData(this.dataList);
				if (!TextUtils.isEmpty(contentsId)) {
					PopupUtil.showLoading(getActivity());
					SpContentsCallBack spSquareCallBack = new SpContentsCallBack(
							getActivity(), SpContentsVO.class) {

						@Override
						public void callback(String url, JSONObject json,
								AjaxStatus status) {
							super.callback(url, json, status);
							PopupUtil.hideLoading(getActivity());
							m_squareData = item;
							if(m_squareData.getContentsTypeEnum() == ContentsType.REPORT){
								setWorkOrNotice(WorkOrNoticeType.WORK);
								Calendar cal = Calendar.getInstance();
								cal.setTime(m_squareData.getReportDate());
								mYear = cal.get(Calendar.YEAR);
								mMonth = cal.get(Calendar.MONTH) ;
								mDay = cal.get(Calendar.DAY_OF_MONTH);
								tvWorkDate.setText(sdf.format(m_squareData.getReportDate()));
							}else if(SpSquareConst.TRUE_CH.equals(m_squareData.getNoticeFlag())){
								setWorkOrNotice(WorkOrNoticeType.NOTICE);
							}else{
								setWorkOrNotice(WorkOrNoticeType.NONE);
							}
							edWrite.setData(m_squareData);
							edWrite.setText(m_squareData.getBody(), BufferType.SPANNABLE);
							
							if (m_squareData.getOpenGraphList() != null && m_squareData.getOpenGraphList().size() > 0) {
								graphList = m_squareData.getOpenGraphList();
								setDataUrlPreview();
							}
							
							thumbnails.clear();
							for(SpAttachVO attachItem :m_squareData.getAttachList()){						
								if (TextUtils.equals(attachItem.getAttachType(), "0")) {
									attachItem.setDeleteShow(true);
									fileLayout.addData(attachItem);
								}
								else if (TextUtils.equals(attachItem.getAttachType(), "1")) {
									attachItem.setDeleteShow(true);
									thumbnails.add(attachItem);
								}
							}
							
							if (thumbnails.size() > 0) {
								thumbnailGridView.setVisibility(View.VISIBLE);
								thumbnailAdapter.notifyDataSetChanged();
							}
							else
								thumbnailGridView.setVisibility(View.GONE);
							for (SpTagVO tag : m_squareData.getTagList()) {
								if (tag != null) {
									tagBoxLayout.setVisibility(View.VISIBLE);
									tagBoxLayout.appendTag(tag);
								}
							}
						}
					};

					{ // API명
						HashMap<String, String> params3 = new HashMap<>();
						params3.put("squareId", m_squareId);
						params3.put("contentsId", contentsId);
						new ApiLoaderEx<>(new SpGetContents(getActivity()),
								getActivity(), spSquareCallBack, params3).execute();
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

		fileLayout.setData(m_fileList, new ISpClickListener() {

			@Override
			public void onFileView(SpAttachVO item) {

			}

			@Override
			public void onFavoriteClick(SpContentsVO item,
					IFavoriteCallbak favoriteCallback) {

			}

			@Override
			public void onFavoriteClick(SpAttachVO vo,
					IFavoriteCallbak favoriteCallback) {

			}

			@Override
			public void onDetailView(SpContentsVO item, boolean showKeyboard) {

			}

			@Override
			public void onDeleteClick(SpAttachVO item) {
				m_fileList.remove(item);
			}

			@Override
			public void onMoveToUrl(String url) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onOrgImgDown(SpAttachVO spAttachVO) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFavorClick(SpContentsVO item, int Type) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onNoticeView(SpContentsVO item) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return v;
	}

	private void resetWorkDate() {
		Calendar calendar = Calendar.getInstance();
		mYear = calendar.get(Calendar.YEAR);
		mMonth = calendar.get(Calendar.MONTH);
		mDay = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(mYear, mMonth, mDay, 0, 0);
		tvWorkDate.setText(sdf.format(calendar.getTime()));
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
			if(TextUtils.isEmpty(edWrite.getText().toString())){
				PopupUtil.showDialog(getActivity(), "글이 없습니다.");
			}else{
				ArrayList<String> filePathList = new ArrayList<String>();

				for (SpAttachVO f : m_fileList) {
					if(f.getTempFilePath() != null){
						filePathList.add(f.getTempFilePath());
					}
				}
				// 파일 용량 체크
				if (1 == MainModel.getInstance().checkFileSize(filePathList)) {
					PopupUtil.showDialog(getActivity(), R.string.square_file_limit_message);
					break;
				}
				
				ArrayList<String> thumbnailPathList = new ArrayList<String>();

				for (SpAttachVO t : thumbnails) {
					if(t.getTempFilePath() != null){
						Debug.trace("t.getTempFilePath() = " , t.getTempFilePath());
						thumbnailPathList.add(t.getTempFilePath());
					}
				}
				// 이미지 용량 체크
				if (1 == MainModel.getInstance().checkFileSize(thumbnailPathList)) {
					PopupUtil.showDialog(getActivity(), R.string.squareplus_thumbnail_limit_message);
					break;
				}

				send(edWrite.getText());
			}
			break;
		case R.id.ID_BTN_WORK:
			// 업무지시
			setWorkOrNotice(WorkOrNoticeType.WORK);
			break;
		case R.id.ID_BTN_NOTICE:
			// 공지
			setWorkOrNotice(WorkOrNoticeType.NOTICE);
			break;
		case R.id.ID_BTN_CAMERA:
			// 카메라
			int createCount = 0;
			for (SpAttachVO t : thumbnails) {
				if (t.isCreateState())
					createCount++;
			}

			if (createCount >= Define.FILE_UPLOAD_LIMIT_COUNT) {
				PopupUtil.showDialog(getActivity(), R.string.squareplus_thumbnail_limit_count);
				return;
			} else {
				getMainModel().showUploadCameraDialog(this, m_squareId);
			}
			break;
		case R.id.ID_BTN_ADD_FILE:
			// 파일 추가
			if (m_fileList.size() >= Define.FILE_UPLOAD_LIMIT_COUNT) {
				PopupUtil.showDialog(getActivity(), R.string.square_attach_file_limit_count);
				return;
			} else {
				// SEOJAEHWA: 메인모델에서 호출하고 코드 중복 제거
//				showUploadFileDialog(this, m_squareId);
				getMainModel().showUploadDialog(this, m_squareId, true);
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
		case R.id.ID_LAY_L_WORK_DATE:
			new DatePickerDialog(getActivity(), dateSetListener, mYear, mMonth,
					mDay).show();
			break;
		}
	}

	private void setWorkOrNotice(WorkOrNoticeType work) {
		switch (work) {
		case NOTICE:
			if (currentWorkOrNoticeType == WorkOrNoticeType.WORK) {
				btnWork.setSelected(false);
				btnNotice.setSelected(true);
				workDateLayout.setVisibility(View.GONE);
				currentWorkOrNoticeType = WorkOrNoticeType.NOTICE;
			} else if (currentWorkOrNoticeType == WorkOrNoticeType.NOTICE) {
				btnNotice.setSelected(false);
				workDateLayout.setVisibility(View.GONE);
				currentWorkOrNoticeType = WorkOrNoticeType.NONE;
			} else {
				btnNotice.setSelected(true);
				btnWork.setSelected(false);
				workDateLayout.setVisibility(View.GONE);
				currentWorkOrNoticeType = WorkOrNoticeType.NOTICE;
			}
			break;
		case WORK:
			resetWorkDate();
			if (currentWorkOrNoticeType == WorkOrNoticeType.NOTICE) {
				btnNotice.setSelected(false);
				btnWork.setSelected(true);
				currentWorkOrNoticeType = WorkOrNoticeType.WORK;
				workDateLayout.setVisibility(View.VISIBLE);
			} else if (currentWorkOrNoticeType == WorkOrNoticeType.WORK) {
				btnWork.setSelected(false);
				currentWorkOrNoticeType = WorkOrNoticeType.NONE;
				workDateLayout.setVisibility(View.GONE);
			} else {
				workDateLayout.setVisibility(View.VISIBLE);
				btnNotice.setSelected(false);
				btnWork.setSelected(true);
				currentWorkOrNoticeType = WorkOrNoticeType.WORK;
			}
			break;
		case NONE:
			workDateLayout.setVisibility(View.GONE);
			btnNotice.setSelected(false);
			btnWork.setSelected(false);
			currentWorkOrNoticeType = WorkOrNoticeType.NONE;
			break;
		}
	}

	private void send(Editable text) {
//		m_bDraw = false;
//		TextViewUtils.hideKeyBoard(getActivity(), edWrite);
//		edWrite.removeTextChangedListener();
		
		PopupUtil.showLoading(getActivity());
		String sb = new String(text.toString());
		CompletionSpan[] tokens = text.getSpans(0, text.length(),
				CompletionSpan.class);
		for (CompletionSpan token : tokens) {
			if (token.getToken() instanceof SpContentsMentionVO) {
				sb = sb.replace("@"+((SpContentsMentionVO) token.getToken()).getItemName(), "@{u" + ((SpContentsMentionVO) token.getToken()).getItemId() + "}");
			} else if (token.getToken() instanceof SpTagVO) {
				sb = sb.replace("#"+((SpTagVO) token.getToken()).getTagName(), "#{" + ((SpTagVO) token.getToken()).getTagName() + "}");
			}
		}
		SpContentsCallBack spContentsCallBack = new SpContentsCallBack(
				getActivity(), SpContentsVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				PopupUtil.hideLoading(getActivity());
				
				if (responseHead != null && responseHead.resultCode == SUCCESS) {
					if(TextUtils.isEmpty(contentsId)){
						// 글이 추가되면 상위 Fragment에 이벤트를 전달 한다.
						MainModel.getInstance().notifyChanged(this.item, com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.ADD);
					}else{
						// 글이 수정되면 상위 Fragment에 이벤트를 전달 한다.
						MainModel.getInstance().notifyChanged(this.item, com.hs.mobile.gw.MainModel.IChangeSpContentsListener.ChangeType.MODIFY);
					}
					getActivity().finish();
				}
			}
		};
		{ // API명
			HashMap<String, Object> params = new HashMap<>();
			params.put("userID", HMGWServiceHelper.userId);
			params.put("squareId", m_squareId); // 스퀘어id
			if (!TextUtils.isEmpty(contentsId)) {
				params.put("contentsId", contentsId);
				params.put("contentsType", ContentsType.TOPIC.getCode());
				switch (currentWorkOrNoticeType) {
				case NONE:
					params.put("noticeFlag", SpSquareConst.FALSE_CH);
					break;
				case NOTICE:
					params.put("noticeFlag", SpSquareConst.TRUE_CH);
					break;
				case WORK:
					params.put("reportFlag", SpSquareConst.TRUE_CH); 					
					Calendar calendar = Calendar.getInstance();
					calendar.set(mYear, mMonth, mDay, 0, 0);
					params.put("reportDate", sdf.format(calendar.getTime()));
					break;
				}
			}else{
				params.put("contentsType", ContentsType.TOPIC.getCode());
				switch (currentWorkOrNoticeType) {
				case NONE:
					params.put("noticeFlag", SpSquareConst.FALSE_CH);
					break;
				case NOTICE:
					params.put("noticeFlag", SpSquareConst.TRUE_CH); // 공지 활성화할때는
																		// reportFlag는
																		// 보내서는 안된다.
					break;
				case WORK:
					params.put("reportFlag", SpSquareConst.TRUE_CH); // 업무보고 활성화할때는
																		// noticeFlag는
																		// 보내서는 안된다.
					Calendar calendar = Calendar.getInstance();
					calendar.set(mYear, mMonth, mDay, 0, 0);
					params.put("reportDate", sdf.format(calendar.getTime())); // 업무보고
																				// 날짜
					break;
				}
			}
			params.put("body", sb.toString()); // 의견내용
		
			int n = 0;
			StringBuilder orgFile = new StringBuilder();

			for (SpAttachVO f : m_fileList) {
				if(f.getTempFilePath() != null){
					params.put("att" + n++, new File(f.getTempFilePath()));
				}
				if(!TextUtils.isEmpty(f.getAttachId())){
					orgFile.append(f.getAttachId());
					orgFile.append(";");
				}
			}
			
			int imgNum = 0;
			for (SpAttachVO f : thumbnails) {
				if(f.getTempFilePath() != null){
					params.put("imgAtt" + imgNum++, new File(f.getTempFilePath()));
				}
				if(!TextUtils.isEmpty(f.getAttachId())){
					orgFile.append(f.getAttachId());
					orgFile.append(";");
				}
			}

			if(orgFile.length() > 0){
				orgFile.replace(orgFile.length() -1, orgFile.length(), "");
			}
			if(!TextUtils.isEmpty(contentsId) && orgFile.length()>0){
				params.put("orgAttachIdList",orgFile.toString() );					
			}
			params.put("attachCount", n);
			params.put("imageAttachCount", imgNum);
			
			if (graphList != null && graphList.size() > 0) {

				try {
					JSONArray jsonArrayList = new JSONArray();
					
					for (SpOpenGraphVO vo : graphList) {
						JSONObject jobj = new JSONObject();
						jobj.put("domain", vo.getDomain());
						jobj.put("url", vo.getUrl());
						jobj.put("title", vo.getTitle());
						jobj.put("image", vo.getImage());
						jobj.put("description", vo.getDescription());
						jsonArrayList.put(jobj);
					}
							
					params.put("urlPreview", jsonArrayList.toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				}
			}
			
			if (TextUtils.isEmpty(contentsId)) {
				new ApiLoaderExMultipart<>(new SpAddTopic(getActivity()),
						getActivity(), spContentsCallBack, params).execute();
			} else {
				new ApiLoaderExMultipart<>(new SpModifyContents(getActivity()),
						getActivity(), spContentsCallBack, params).execute();
			}
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
			try {
				if (oInputStream != null) oInputStream.close();
			} catch (IOException e) {
				Debug.trace(e.getMessage());
			}
		}
		return bArData;
	}

	// SEOJAEHWA: 중복코드 제거
//	public void showUploadFileDialog(final Fragment fragment, final String strSquareId) {
//
//		String[] items = new String[] { fragment.getString(R.string.upload_media_camera),
//				fragment.getString(R.string.upload_media_record_video), fragment.getString(R.string.upload_media_album),
//				fragment.getString(R.string.upload_media_file) };
//		new AlertDialog.Builder(fragment.getActivity()).setItems(items, new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Uri fileUri;
//				switch (which) {
//				case UPLOAD_ITEM_CAMERA: {
//					Intent intentCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//					fileUri = ImageHelper.getOutputMediaFileUri(fragment.getActivity(), ImageHelper.MEDIA_TYPE_IMAGE);
//					intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//					fragment.startActivityForResult(intentCamera, MainModel.REQ_CAMERA);
//					dialog.dismiss();
//				}
//					break;
//				case UPLOAD_ITEM_VIDEO: {
//					Intent intentCamera = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
//					fileUri = ImageHelper.getOutputMediaFileUri(fragment.getActivity(), ImageHelper.MEDIA_TYPE_VIDEO);
//					intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//					fragment.startActivityForResult(intentCamera, REQ_VIDEO);
//					dialog.dismiss();
//				}
//					break;
//				case UPLOAD_ITEM_ALBUM: {
//					Intent intentAlbum = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//					fragment.startActivityForResult(intentAlbum, MainModel.REQ_ALBUM);
//					dialog.dismiss();
//				}
//					break;
//				case UPLOAD_ITEM_FILE: {
//					Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//					intent.setType("*/*");
//					intent.addCategory(Intent.CATEGORY_OPENABLE);
//					fragment.startActivityForResult(intent, REQ_FILE);
//					dialog.dismiss();
//				}
//					break;
//				}
//			}
//		}).show();
//	}

//	public void showUploadCameraDialog(final Fragment fragment, final String strSquareId) {
//		String[] items = new String[] { fragment.getString(R.string.upload_media_camera),
//				// fragment.getString(R.string.upload_media_record_video),
//				fragment.getString(R.string.upload_media_album) };
//		new AlertDialog.Builder(fragment.getActivity()).setItems(items, new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Uri fileUri;
//
//				if (which == 1)
//					which = UPLOAD_ITEM_ALBUM;
//
//				switch (which) {
//				case UPLOAD_ITEM_CAMERA: {
//					Intent intentCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//					fileUri = ImageHelper.getOutputMediaFileUri(fragment.getActivity(), ImageHelper.MEDIA_TYPE_IMAGE);
//					intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//					fragment.startActivityForResult(intentCamera, MainModel.REQ_THUMBNAIL_CAMERA);
//					dialog.dismiss();
//				}
//					break;
//				// case UPLOAD_ITEM_VIDEO: {
//				// Intent intentCamera = new Intent(
//				// android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
//				// fileUri = ImageHelper.getOutputMediaFileUri(
//				// fragment.getActivity(),
//				// ImageHelper.MEDIA_TYPE_VIDEO);
//				// intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,
//				// fileUri);
//				// fragment.startActivityForResult(intentCamera,
//				// REQ_VIDEO);
//				// dialog.dismiss();
//				// }
//				// break;
//				case UPLOAD_ITEM_ALBUM: {
//					Intent intentAlbum = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//					fragment.startActivityForResult(intentAlbum, MainModel.REQ_THUMBNAIL_ALBUM);
//					dialog.dismiss();
//				}
//					break;
//				}
//			}
//		}).show();
//	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_CAMERA:
			case MainModel.REQ_VIDEO:
			case MainModel.REQ_ALBUM:
			case MainModel.REQ_FILE:
			case MainModel.REQ_THUMBNAIL_CAMERA:
			case MainModel.REQ_THUMBNAIL_ALBUM:
				MainModel.getInstance().activityResultMediaHandler(getActivity(), requestCode, resultCode, data, this);
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void showPopup(PopupStatus popupStatus) {
		mPopupStatus = popupStatus;
		switch (popupStatus) {
		case WRITE:
			TextViewUtils.showKeyboard(getActivity(), edWrite);
			tvTitle.setText("글쓰기");
			writeLayout.setVisibility(View.VISIBLE);
			mentionLayout.setVisibility(View.GONE);
			tagLayout.setVisibility(View.GONE);
			btnNotice.setVisibility(View.VISIBLE);
			btnWork.setVisibility(View.VISIBLE);
			btnSend.setVisibility(View.VISIBLE);
			edWrite.requestFocus();
			break;
		case MENTION:
			tvTitle.setText("맨션");
			writeLayout.setVisibility(View.GONE);
			mentionLayout.setVisibility(View.VISIBLE);
			tagLayout.setVisibility(View.GONE);
			btnNotice.setVisibility(View.GONE);
			btnWork.setVisibility(View.GONE);
			btnSend.setVisibility(View.GONE);
			mentionLayout.onShow();
			break;
		case TAG:
			tvTitle.setText("태그");
			writeLayout.setVisibility(View.GONE);
			mentionLayout.setVisibility(View.GONE);
			tagLayout.setVisibility(View.VISIBLE);
			btnNotice.setVisibility(View.GONE);
			btnWork.setVisibility(View.GONE);
			btnSend.setVisibility(View.GONE);
			tagLayout.onShow();
			break;
		default:
			break;
		}
	}

	@Override
	public void onLoadCompleteMedia(ResultType resultType, String strRet) {
		SpAttachVO file = new SpAttachVO();
		String strFileExt = strRet.substring(strRet.lastIndexOf(".") + 1,
				strRet.length());
		file.setAttachId(strRet);
		file.setFileName(strRet.substring(strRet.lastIndexOf("/") + 1,
				strRet.lastIndexOf(".")));
		file.setFileExt(strFileExt);
		file.setTempFilePath(strRet);
		file.setDeleteShow(true);
		file.setCreateState(true);
		switch (resultType) {
		case FILE_PATH:
			fileLayout.addData(file);
			break;
		case THUMBNAIL_PATH:
			Debug.trace(thumbnailGridView.getVisibility());
			if (thumbnailGridView.getVisibility() != View.VISIBLE)
				thumbnailGridView.setVisibility(View.VISIBLE);
			thumbnails.add(file);
			thumbnailAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
		
		// if (m_fileAttachLayout.getVisibility() != View.VISIBLE) {
		// m_fileAttachLayout.setVisibility(View.VISIBLE);
		// }
		// FileItemView fiv = new FileItemView(getActivity());
		// fiv.setDeleteListener(this);
		// fiv.setFilePath(strRet);
		// m_filePathData.add(strRet);
		// m_fileAttachArea.addView(fiv, LayoutParams.MATCH_PARENT,
		// LayoutParams.WRAP_CONTENT);
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
			edWrite.addMentionData(spContentsMentionVO);
			break;
		case TAG:
			SpTagVO tagVO = (SpTagVO) vo;
			String tagName = "#" + tagVO.getTagName();
			SpannableString ssb2 = new SpannableString(tagName);

			ssb2.setSpan(new CompletionSpan(fontColor, tagVO), 0,
					tagName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			edWrite.getText().insert(edWrite.getSelectionStart(), ssb2);
			tagBoxLayout.setVisibility(View.VISIBLE);
			tagBoxLayout.appendTag(tagVO);
			break;
		}
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
	public void onMentionClick(SpContentsMentionVO item) {

	}

	@Override
	public void onTagClick(final String replace) {
		new AlertDialog.Builder(getActivity())
				.setMessage("선택한 해시태그를 삭제하시겠습니까?")
				.setPositiveButton("예", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						edWrite.removeTag(replace);
						tagBoxLayout.removeTag(replace);
						if (tagBoxLayout.isTagEmpty())
							tagBoxLayout.setVisibility(View.GONE);
					}
				}).setNegativeButton("아니오", null).show();
	}

	@Override
	public void onUserClick(String itemId) {

	}

	@Override
	public void onBtnTagAddClick() {
		showPopup(PopupStatus.TAG);
	}

	@Override
	public void onItemClick(SpAttachVO item) {
		// TODO Auto-generated method stub
		if (item.isCreateState()) {
			MainModel.getInstance().callActionView(new File(item.getTempFilePath()), getActivity());
		}
		else {
			if (MainModel.getInstance().getOpenApiService()!=null && item != null) MainModel.getInstance().getOpenApiService().downloadOrgImg(getActivity(), item);
		}
	}
	
	private void setDataUrlPreview() {
		
		urlPreviewLayout.setData(graphList, new ISpClickListener() {
			
			@Override
			public void onOrgImgDown(SpAttachVO spAttachVO) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onNoticeView(SpContentsVO item) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onMoveToUrl(String url) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				if (!url.startsWith("http://") && !url.startsWith("https://")) {
					url = "http://" + url;
				}
				
				Intent call_browser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				getActivity().startActivity(call_browser);
			}
			
			@Override
			public void onFileView(SpAttachVO item) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFavoriteClick(SpContentsVO item, IFavoriteCallbak favoriteCallback) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFavoriteClick(SpAttachVO vo, IFavoriteCallbak favoriteCallback) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFavorClick(SpContentsVO item, int Type) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDetailView(SpContentsVO item, boolean showKeyboard) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDeleteClick(SpAttachVO item) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
