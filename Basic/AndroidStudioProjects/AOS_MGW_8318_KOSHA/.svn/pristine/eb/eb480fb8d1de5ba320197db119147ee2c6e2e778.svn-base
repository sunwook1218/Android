package com.hs.mobile.gw.fragment;

import java.util.ArrayList;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener;
import com.hs.mobile.gw.MainModel.IContentsDeleteListener;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.SquareDetailContentsAdapter;
import com.hs.mobile.gw.adapter.SquareDetailContentsAdapter.IWorkStatusChangeListener;
import com.hs.mobile.gw.adapter.SquareGroupContentsAdapter.ButtonType;
import com.hs.mobile.gw.openapi.square.AddComment;
import com.hs.mobile.gw.openapi.square.CanClose;
import com.hs.mobile.gw.openapi.square.DetailInfo;
import com.hs.mobile.gw.openapi.square.DetailInfoResult;
import com.hs.mobile.gw.openapi.square.SetContentsMemberStatus;
import com.hs.mobile.gw.openapi.square.SetContentsOrderStatus;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.ContentsMemberListItemVO;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO.OrderStatus;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareContentsType;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.FileAttachArea.IFileAttachClickListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.Mode;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SquareContentsDetailFragment extends CommonFragment implements OnClickListener, IBookmarkAndOptionViewListener,
		IChangeContentsListener, IFileAttachClickListener, IWorkStatusChangeListener, ISquarePushListener {
	public static final String ARG_KEY_CONTENTS_ID = "contents_id";
	public static final String ARG_KEY_CONTENTS_INFO = "contents_info";
	
	public class TopicDetailHeader implements ISquareContentsDetailItem {

		public MainContentsListItemVO m_topicData;

		public TopicDetailHeader(MainContentsListItemVO detailInfoVO) {
			m_topicData = detailInfoVO;
		}

		@Override
		public Object getVO() {
			return m_topicData;
		}
	}

	public class FileDetailHeader implements ISquareContentsDetailItem {

		public MainContentsListItemVO m_fileData;

		public FileDetailHeader(MainContentsListItemVO detailInfoVO) {
			m_fileData = detailInfoVO;
		}

		@Override
		public Object getVO() {
			return m_fileData;
		}
	}

	public class SquareOpinion implements ISquareContentsDetailItem {
		public MainContentsListItemVO m_opinionData;

		public SquareOpinion(MainContentsListItemVO detailInfoVO) {
			m_opinionData = detailInfoVO;
		}

		@Override
		public Object getVO() {
			return m_opinionData;
		}
	}

	public class Participant implements ISquareContentsDetailItem {

		public ArrayList<ContentsMemberListItemVO> m_memberList;
		public String m_contentsId;
		public String m_authId;

		public Participant(ArrayList<ContentsMemberListItemVO> memberList, String contentsId, String strAuthId) {
			m_memberList = memberList;
			m_contentsId = contentsId;
			m_authId = strAuthId;
		}

		@Override
		public Object getVO() {
			return m_memberList;
		}
	}

	public enum SeperatorType {
		OPINION, PARTICIPANT
	}

	private Seperator m_opinionSeperator;

	public class Seperator implements ISquareContentsDetailItem {

		private String m_strLabel;
		private SeperatorType m_type;

		public Seperator(String strLabel, SeperatorType t) {
			m_strLabel = strLabel;
			m_type = t;
		}

		public String getLabelText() {
			return m_strLabel;
		}

		public void setLabelText(String s) {
			m_strLabel = s;
		}

		public SeperatorType getSeperatorType() {
			return m_type;
		}

		@Override
		public Object getVO() {
			return m_strLabel;
		}
	}

	public class CommandDetailHeader implements ISquareContentsDetailItem {

		public MainContentsListItemVO m_commandDetailData;

		public CommandDetailHeader(MainContentsListItemVO data) {
			m_commandDetailData = data;
		}

		@Override
		public Object getVO() {
			return m_commandDetailData;
		}

	}

	public interface ISquareContentsDetailItem {
		public Object getVO();
	}

	private ImageView m_btnBack;
	private ImageView m_btnMenu;
	private TextView m_tvTitle;
	private ImageView m_btnMore;
	private PullToRefreshListView m_lvSquareContents;
	private Button m_btnUpload;
	private EditText m_edMessage;
	private Button m_btnSend;
	private ArrayList<ISquareContentsDetailItem> m_data = new ArrayList<SquareContentsDetailFragment.ISquareContentsDetailItem>();
	private SquareDetailContentsAdapter m_adapter;
	private int m_nCommentCount;

	private MainContentsListItemVO m_contentsInfo;

	private ButtonType m_initType = null;
	private Status m_status;
	private LinearLayout m_inputLayout;

	private String m_strFromSearchContentsId;

	private String m_strContentId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			if (getArguments().getSerializable(MainModel.ARG_KEY_GROUP_STATUS) != null) {
				m_status = (Status) getArguments().getSerializable(MainModel.ARG_KEY_GROUP_STATUS);
			}
			if (getArguments().getSerializable(ARG_KEY_CONTENTS_INFO) != null) {
				m_contentsInfo = (MainContentsListItemVO) getArguments().getSerializable(ARG_KEY_CONTENTS_INFO);
			}
			if (getArguments().getString(SquareContentsFragment.ARG_KEY_CLICKED_BUTTON_TYPE) != null) {
				m_initType = ButtonType.valueOf(getArguments().getString(SquareContentsFragment.ARG_KEY_CLICKED_BUTTON_TYPE));
			}
			if (getArguments().getString(ARG_KEY_CONTENTS_ID) != null) {
				m_strFromSearchContentsId = getArguments().getString(ARG_KEY_CONTENTS_ID);
				m_status = Status.COMPLETE;
			}
		}
		MainModel.getInstance().addContentsChangeListener(this);
	}

	public boolean isFromSearch() {
		return !TextUtils.isEmpty(m_strFromSearchContentsId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_square_contents_detail, container, false);
		super.createHead(v); // create sub header
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_btnMenu = (ImageView) v.findViewById(R.id.ID_BTN_MENU);
		m_tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
		m_btnMore = (ImageView) v.findViewById(R.id.ID_BTN_MORE);
		m_lvSquareContents = (PullToRefreshListView) v.findViewById(R.id.ID_LV_SQUARE_CONTENTS);
		m_inputLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_INPUT);
		m_btnUpload = (Button) v.findViewById(R.id.ID_BTN_UPLOAD);
		m_edMessage = (EditText) v.findViewById(R.id.ID_ED_MESSAGE);
		m_btnSend = (Button) v.findViewById(R.id.ID_BTN_SEND);

		m_btnSend.setOnClickListener(this);
		m_btnBack.setOnClickListener(this);
		m_btnMenu.setOnClickListener(this);

		m_lvSquareContents.setMode(Mode.DISABLED);
		m_lvSquareContents.getRefreshableView().setDividerHeight(0);
		m_lvSquareContents.getRefreshableView().setScrollingCacheEnabled(false);
		m_lvSquareContents.getRefreshableView().setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
		m_lvSquareContents.setFadingEdgeLength(0);
		m_adapter = new SquareDetailContentsAdapter(m_status, getActivity(), m_data, this, this, this);
		m_lvSquareContents.setAdapter(m_adapter);
		m_strContentId = "";
		if (m_contentsInfo != null && m_contentsInfo.contentsType != null
				&& (m_contentsInfo.contentsType.equals(SquareContentsType.SYSTEM_MESSAGE) || m_contentsInfo.contentsType
						.equals(SquareContentsType.OPINION))) {
			m_strContentId = m_contentsInfo.originalParentId;
		} else if (isFromSearch()) {
			m_strContentId = m_strFromSearchContentsId;
		} else {
			m_strContentId = m_contentsInfo.contentsId;
		}

		loadData();
		return v;
	}

	private void loadData() {
		if (m_data != null) {
			m_data.clear();
		}
		new ApiLoader(new DetailInfo(), getActivity(), new DetailInfoResult() {
			@Override
			public void onResponse(String strRet) {
				super.onResponse(strRet);
				
				for (int i = 0; i < dataList.size(); ++i) {
					switch (dataList.get(i).contentsType) {
					case COMMAND:
						m_data.add(new CommandDetailHeader(dataList.get(i)));
						m_data.add(new Seperator(getString(R.string.label_square_detail_participant), SeperatorType.PARTICIPANT));
						m_data.add(new Participant(dataList.get(i).contentsMemberList, dataList.get(i).contentsId, dataList.get(i).authorId));
						m_opinionSeperator = new Seperator(getString(R.string.label_square_detail_opinion) + " "
								+ String.valueOf(dataList.get(i).commentCount), SeperatorType.OPINION);
						m_nCommentCount = dataList.get(i).commentCount;
						m_data.add(m_opinionSeperator);

						break;
					case FILE_UPLOAD:
						m_data.add(new FileDetailHeader(dataList.get(i)));
						m_nCommentCount = dataList.get(i).commentCount;
						m_opinionSeperator = new Seperator(getString(R.string.label_square_detail_opinion) + " "
								+ String.valueOf(dataList.get(i).commentCount), SeperatorType.OPINION);
						m_data.add(m_opinionSeperator);
						break;
					case GROUPINFO_SYSTEM_MESSAGE:
						break;
					case MESSAGE:
						break;
					case OPINION:
						m_data.add(new SquareOpinion(dataList.get(i)));
						break;
					case SYSTEM_MESSAGE:
						break;
					case TOPIC:
						m_data.add(new TopicDetailHeader(dataList.get(i)));
						m_opinionSeperator = new Seperator(getString(R.string.label_square_detail_opinion) + " "
								+ String.valueOf(dataList.get(i).commentCount), SeperatorType.OPINION);
						m_data.add(m_opinionSeperator);
						break;
					}
				}
				m_adapter.notifyDataSetChanged();
				MainModel.getInstance().authorizeInputLayout(m_status, m_inputLayout);
				if (m_initType != null) {
					switch (m_initType) {
					case OPINION:
						// 입력 창을 열고, 포커스를 최 하단으로.
						if (m_status != Status.COMPLETE) {
							m_edMessage.requestFocus();
							TextViewUtils.showKeyboard(getActivity(), m_edMessage);
						}
						break;
					case FILE:
						m_lvSquareContents.getRefreshableView().setSelection(0);
						break;
					}
				} else {
					m_lvSquareContents.post(new Runnable() {
						@Override
						public void run() {
							m_lvSquareContents.getRefreshableView().setSelection(0);
						}
					});
				}
				MainModel.getInstance().addSquarePushListener(SquareContentsDetailFragment.this);
			}
		}, m_strContentId).execute();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().setResult(Activity.RESULT_OK);
			if (MainModel.getInstance().isTablet()) {
				TextViewUtils.hideKeyBoard(getActivity(), m_edMessage);
				if (getActivity().findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT) != null) {
					getActivity().findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).setVisibility(View.GONE);
				} else {
					getActivity().finish();
				}
			} else {
				getActivity().finish();
			}
			break;
		case R.id.ID_BTN_MENU:
			if (MainModel.getInstance().isTablet()) {
				MainFragment.getController().closeMenu();
				// if (MainActivity.viewActionsContentView.isActionsShown()) {
				// MainActivity.viewActionsContentView.showContent();
			} else {
				// MainActivity.viewActionsContentView.showActions();
				MainFragment.getController().closeMenu();
				// }
			}
			break;
		case R.id.ID_BTN_SEND:
			Debug.trace("전송");
			if (m_edMessage.getText().toString().trim().length() > 0 && m_status == Status.IN_PROGRESS) {
				AddComment api = new AddComment();
				// ArrayList<String> fileList = new ArrayList<String>();
				// fileList.add(strRet);
				// api.setImagePath(fileList);
				new ApiLoader(api, getActivity(), new SquareDefaultCallback() {
					@Override
					public void onResponse(String strRet) {
						super.onResponse(strRet);
						m_edMessage.getText().clear();
						MainContentsListItemVO detailInfoVO = new MainContentsListItemVO(getJsonObject().optJSONObject("responseData"));
						SquareOpinion item = new SquareOpinion(detailInfoVO);
						m_data.add(item);
						setOpinionCount(++m_nCommentCount);
						m_adapter.notifyDataSetChanged();
						MainModel.getInstance().notifyChanged(detailInfoVO, ChangeType.ADD);
						TextViewUtils.hideKeyBoard(getActivity(), m_edMessage);
					}
				}, m_contentsInfo.squareId, m_contentsInfo.originalParentId, m_edMessage.getText().toString().trim()).execute();
			}
			break;
		case R.id.ID_BTN_UPLOAD:
			if (m_status == Status.IN_PROGRESS) {
				MainModel.getInstance().showUploadDialog(this, m_contentsInfo.squareId, true);
			}
			break;
		}
	}

	public void setOpinionCount(int nCount) {
		m_opinionSeperator.setLabelText(getString(R.string.label_square_detail_opinion) + " " + String.valueOf(nCount));
		m_adapter.notifyDataSetChanged();
	}

	// @Override
	// public void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// if (resultCode == Activity.RESULT_OK) {
	// MainModel.getInstance().activityResultMediaHandler(getActivity(),
	// requestCode, resultCode, data, this);
	// }
	// }

	@Override
	public void onFavoriteClick(String squareId, String contentsId, FavoriteContentsType favorityType, boolean flag,
			SquareDefaultCallback squareDefaultCallback) {
		MainModel.getInstance().addFavoriteContents(getActivity(), squareDefaultCallback, squareId, contentsId, favorityType, flag);
	}

	@Override
	public void onDelete(MainContentsListItemVO item) {
		MainModel.getInstance().deleteContents(getActivity(), item, new IContentsDeleteListener() {
			@Override
			public void onDeleteComplete(MainContentsListItemVO item) {
				MainModel.getInstance().notifyChanged(item, ChangeType.DELETE);
			}
		});
	}

	@Override
	public void onCopy(MainContentsListItemVO item) {
		MainModel.getInstance().copyBodyText(getActivity(), item);
	}

	@Override
	public void onModify(MainContentsListItemVO item) {
		MainModel.getInstance().modifyContents(getActivity(), item, new SquareDefaultCallback() {
			@Override
			public void onResponse(String strRet) {
				super.onResponse(strRet);
				final MainContentsListItemVO item = new MainContentsListItemVO(getJsonObject().optJSONObject("responseData"));
				for (ISquareContentsDetailItem data : m_data) {
					if (data.getVO().equals(item)) {
						MainModel.getInstance().notifyChanged(item, ChangeType.MODIFY);
					}
				}
			}
		});
	}

	@Override
	public void onDestroy() {
		MainModel.getInstance().removeContentsChangeListener(this);
		MainModel.getInstance().removeSquarePushListener(this);
		super.onDestroy();
	}

	@Override
	public void onChange(ChangeType t, MainContentsListItemVO item) {
		switch (t) {
		case ADD:
			break;
		case DELETE:
			if (item.contentsType.equals(SquareContentsType.OPINION)) {
				MainContentsListItemVO oldVo = item;
				ISquareContentsDetailItem newItem = null;
				switch (oldVo.contentsType) {
				case COMMAND:
					newItem = new CommandDetailHeader(item);
					break;
				case FILE_UPLOAD:
					newItem = new FileDetailHeader(item);
					break;
				case GROUPINFO_SYSTEM_MESSAGE:
					break;
				case MESSAGE:
					break;
				case OPINION:
					newItem = new SquareOpinion(item);
					break;
				case SYSTEM_MESSAGE:
					break;
				case TOPIC:
					newItem = new TopicDetailHeader(item);
					break;
				case USER_SYSTEM_INFO:
					break;
				default:
					break;
				}
				int nRet = -1;
				for (int i = 0; i < m_data.size(); ++i) {
					if (m_data.get(i).getVO().equals(item)) {
						nRet = i;
					}
				}
				if (nRet != -1) {
					m_data.remove(nRet);
					m_adapter.notifyDataSetChanged();
					m_adapter.notifyDataSetInvalidated();
				}
			} else {
				if(MainModel.getInstance().isTablet()){
					getActivity().findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).setVisibility(View.GONE);
				}else{
					getActivity().finish();
				}
				
			}
			break;
		case MODIFY: {
			MainContentsListItemVO oldVo = item;
			ISquareContentsDetailItem newItem = null;
			switch (oldVo.contentsType) {
			case COMMAND:
				newItem = new CommandDetailHeader(item);
				break;
			case FILE_UPLOAD:
				newItem = new FileDetailHeader(item);
				break;
			case GROUPINFO_SYSTEM_MESSAGE:
				break;
			case MESSAGE:
				break;
			case OPINION:
				newItem = new SquareOpinion(item);
				break;
			case SYSTEM_MESSAGE:
				break;
			case TOPIC:
				newItem = new TopicDetailHeader(item);
				break;
			case USER_SYSTEM_INFO:
				break;
			default:
				break;
			}
			int nRet = -1;
			for (int i = 0; i < m_data.size(); ++i) {
				if (m_data.get(i).getVO().equals(item)) {
					nRet = i;
				}
			}
			if (nRet != -1) {
				m_data.remove(nRet);
				m_data.add(nRet, newItem);
				m_adapter.notifyDataSetChanged();
				m_adapter.notifyDataSetInvalidated();
			}
		}
			break;
		}
	}

	// @Override
	// public void onLoadCompleteMedia(ResultType filePath, String strRet) {
	// AddComment api = new AddComment();
	// ArrayList<String> fileList = new ArrayList<String>();
	// fileList.add(strRet);
	// api.setFilePath(fileList);
	// new ApiLoader(api, getActivity(), new SquareDefaultCallback() {
	// @Override
	// public void onResponse(String strRet) {
	// super.onResponse(strRet);
	// m_edMessage.getText().clear();
	// SquareOpinion item = new SquareOpinion(new
	// DetailInfoVO(getJsonObject().optJSONObject("responseData")));
	// m_data.add(item);
	// m_adapter.notifyDataSetChanged();
	// }
	// }, m_contentsInfo.squareId, m_contentsInfo.originalParentId,
	// m_edMessage.getText().toString().trim()).execute();
	// }

	@Override
	public void onFileClick(AttachListItemVO item) {
		if (HMGWServiceHelper.doc_transform_server_use) {
			MainModel.getInstance().getOpenApiService().getDocHandlerResult(getActivity(),item);
		}
		else {
			// SEOJAEHWA : 권한체크 추가
			downloadFile(item.contentsId, item.attachId, getTargetFilePath(item.fileName, item.fileExt));
		}
	}

	@Override
	public void onMasterWorkStatusChange(final View v, final MainContentsListItemVO item, final boolean b, final String contentsId) {
		new ApiLoader(new CanClose(), getActivity(), new SquareDefaultCallback() {
			@Override
			public void onResponse(String strRet) {
				super.onResponse(strRet);
				if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
					new ApiLoader(new SetContentsOrderStatus(), getActivity(), new SquareDefaultCallback() {
						public void onResponse(String strRet) {
							super.onResponse(strRet);
							if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
								item.orderStatus = b ? OrderStatus.COMPLETE : OrderStatus.PROCESS;
								if (item.orderStatus == OrderStatus.COMPLETE) {
									item.endDate = System.currentTimeMillis();
								} else {
									item.endDate = MainModel.getInstance().getDefaultDate().getTime();
								}
								MainModel.getInstance().notifyChanged(item, ChangeType.MODIFY);
							}
						};
					}, contentsId, b ? "2" : "1").execute();
				}
			}
		}, contentsId).execute();
	}

	@Override
	public void onWorkStatusChange(final View v, final MainContentsListItemVO item, final boolean b, final String contentsId) {
		new ApiLoader(new SetContentsMemberStatus(), getActivity(), new SquareDefaultCallback() {
			@Override
			public void onResponse(String strRet) {
				super.onResponse(strRet);
				if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
					for (ContentsMemberListItemVO vo : item.contentsMemberList) {
						if (TextUtils.equals(vo.userId, HMGWServiceHelper.userId)) {
							vo.status = b ? "2" : "1";
							MainModel.getInstance().notifyChanged(item, ChangeType.MODIFY);
							break;
						}
					}
				}
			}
		}, contentsId, b ? "2" : "1").execute();
	}

	@Override
	public boolean onPreBackKeyPressed() {
		return super.onPreBackKeyPressed();
	}

	@Override
	public void onPushReceive(SquarePushVO vo) {
		// 스퀘어 아이디와 콘텐츠 부모 아이디가 같아야 처리하는 경우
		if (TextUtils.equals(m_contentsInfo.squareId, vo.square_id) && TextUtils.equals(m_strContentId, vo.square_original_parent_id)) {
			switch (vo.square_action) {
			case CONTENTS_MODIFY:
			case ORDER_WORK_COMPLETE:
			case ORDER_WORK_COMPLETE_CANCEL:
			case WORK_COMPLETE:
			case WORK_COMPLETE_CANCEL:
			case COMMENT_ADD:
				loadData();
				break;
			case CONTENTS_DELETE:
				if (TextUtils.equals(vo.square_contents_id, vo.square_original_parent_id)) {
					PopupUtil.showDialog(getActivity(), "현재 콘텐츠가 삭제되었습니다. 이전화면으로 되돌아 갑니다.", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							getActivity().setResult(Activity.RESULT_OK);
							getActivity().finish();
						}
					});
				} else {
					loadData();
				}
				break;
			}
		}
		// 스퀘어 아이디만 같아도 처리해야 하는 액션
		else if (TextUtils.equals(m_contentsInfo.squareId, vo.square_id)) {
			switch (vo.square_action) {
			case SQUARE_MEMBER_CHANGE:
			case SQURE_MEMBER_ADMIN_CHANGE:
				getMainModel().loadGroupInfo(getActivity(), vo.square_id,
						new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(), GroupInfoVO.class) {
							@Override
							public void callback(String url, JSONObject json, AjaxStatus status) {
								super.callback(url, json, status);
								MainModel.getInstance().checkMember(getActivity(), getVO());
							}
						});
				break;
			case GROUP_DELETE:
				new AlertDialog.Builder(getActivity()).setTitle(R.string.message_confirm_title)
						.setMessage("현재 그룹이 삭제되었습니다. 스퀘어그룹 화면으로 되돌아 갑니다.")
						.setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								getActivity().setResult(Activity.RESULT_CANCELED);
								getActivity().finish();
							}
						}).setCancelable(false).show();
				break;
			}
		}
	}
}