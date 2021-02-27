package com.hs.mobile.gw.fragment.squareplus.file;

import java.io.Serializable;
import java.util.ArrayList;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CommonFragmentController;
import com.hs.mobile.gw.fragment.MemberSelectFragment;
import com.hs.mobile.gw.fragment.MemberSelectFragment.MemberSelectType;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment;
import com.hs.mobile.gw.fragment.squareplus.SpFileFragment;
import com.hs.mobile.gw.fragment.squareplus.file.SpFileModel.ISquareFileResultListener;
import com.hs.mobile.gw.fragment.squareplus.file.SpFileModel.SpFileFilter;
import com.hs.mobile.gw.fragment.squareplus.file.SpFileModel.SpFileType;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SpFileController extends CommonFragmentController<SpFileFragment, SpFileModel> implements OnClickListener,
		IBookmarkAndOptionViewListener, OnItemClickListener, OnRefreshListener<ListView>, OnLastItemVisibleListener
{
	private String m_strSelectedUserId;

	public SpFileController(SpFileFragment view, SpFileModel model) {
		super(view, model);
	}

	public void checkArgument() {
		if (getView().getArguments() != null) {
			String strSquareId = null;
			if (getView().getArguments().containsKey(MainModel.ARG_KEY_SQUARE_ID)) {
				strSquareId = getView().getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
			}
			if (getView().getArguments().containsKey(MainModel.ARG_KEY_GROUP_STATUS)) {
				getModel().mGroupStatus = (Status) getView().getArguments().getSerializable(MainModel.ARG_KEY_GROUP_STATUS);
			}
			if (!TextUtils.isEmpty(strSquareId)) {
				getModel().mStrSquareId = strSquareId;
			}
		} else {
			getView().getActivity().finish();
		}
	}

	public void setFilter(SpFileFilter t) {
		getModel().mFileFilter = t;
		switch (t) {
		case ALL:
			getView().m_btnMemberSelect.setVisibility(View.GONE);
			getView().m_btnFileAll.setSelected(true);
			getView().m_btnFileMy.setSelected(false);
			getView().m_btnFileOther.setSelected(false);
			setFileType(getModel().mFileType);
			break;
		case MY:
			getView().m_btnMemberSelect.setVisibility(View.GONE);
			getView().m_btnFileAll.setSelected(false);
			getView().m_btnFileMy.setSelected(true);
			getView().m_btnFileOther.setSelected(false);
			setFileType(getModel().mFileType);
			break;
		case OTHER:
			getView().m_btnMemberSelect.setVisibility(View.VISIBLE);
			getView().m_btnFileAll.setSelected(false);
			getView().m_btnFileMy.setSelected(false);
			getView().m_btnFileOther.setSelected(true);
			if (!getView().m_lvFile.isRefreshing()) {
				m_strSelectedUserId = null;
				MainModel.getInstance().showSubActivity(
						getView(),
						SubActivityType.MEMBER_SELECT,
						new BundleUtils.Builder().add(MemberSelectFragment.ARG_KEY_MEMBER_SELECT_TYPE, MemberSelectType.SINGLE_CHOICE)
								.add(MainModel.ARG_KEY_MEMBER_SELECT_OPTION, MainModel.REQ_SELECT_MEMBER_SINGLE)
								.add(MainModel.ARG_KEY_SQUARE_ID, getModel().mStrSquareId).build());
			} else {
				setFileType(getModel().mFileType);
			}
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		case R.id.ID_BTN_SELECT_MEMBER:
			setFilter(getModel().mFileFilter);
			break;
		case R.id.ID_BTN_TYPE:
			String[] items = new String[] { getString(R.string.favorite_file_type_all), getString(R.string.favorite_file_type_doc),
					getString(R.string.favorite_file_type_img), getString(R.string.favorite_file_type_mov),
					getString(R.string.favorite_file_type_etc) };
			new AlertDialog.Builder(getActivity()).setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					SpFileType ft = SpFileType.values()[which];
					setFileType(ft);
				}
			}).show();
			break;
		case R.id.ID_LAY_L_FILE_ALL:
			getModel().mFileType = SpFileType.ALL;
			setFilter(SpFileFilter.ALL);
			break;
		case R.id.ID_LAY_L_FILE_MINE:
			getModel().mFileType = SpFileType.ALL;
			setFilter(SpFileFilter.MY);
			break;
		case R.id.ID_LAY_L_FILE_OTHER:
			getModel().mFileType = SpFileType.ALL;
			getModel().m_fileData.clear();
			getModel().getFileAdapter().notifyDataSetChanged();
			setFilter(SpFileFilter.OTHER);
			break;
		}
	}

	protected void setFileType(SpFileType ft) {
		String strAuthId = "";
		switch (getModel().mFileFilter) {
		case ALL:
			strAuthId = "";
			break;
		case MY:
			strAuthId = HMGWServiceHelper.userId;
			break;
		case OTHER:
			strAuthId = m_strSelectedUserId;
			break;
		}
		getModel().m_fileData.clear();
		getModel().loadFileList(getActivity(), getModel().mFileFilter, ft, strAuthId, "", new ISquareFileResultListener() {
			@Override
			public void onResult(SquareDefaultVO vo) {
				getView().m_lvFile.onRefreshComplete();
				getModel().getFileAdapter().notifyDataSetChanged();
			}
		});
	}

	@Override
	public void onDelete(MainContentsListItemVO item) {

	}

	@Override
	public void onCopy(MainContentsListItemVO item) {

	}

	@Override
	public void onModify(MainContentsListItemVO item) {

	}

	@Override
	public void onFavoriteClick(String squareId, String contentsId, FavoriteContentsType favorityType, boolean flag,
			SquareDefaultCallback squareDefaultCallback) {
		MainModel.getInstance().addFavoriteContents(getActivity(), squareDefaultCallback, squareId, contentsId, favorityType, flag);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int nPosition = position - getView().m_lvFile.getRefreshableView().getHeaderViewsCount();		
		BundleUtils.Builder bd = new BundleUtils.Builder();
		bd.add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO,new MainContentsListItemVO(getModel().m_fileData.get(nPosition)));
		bd.add(MainModel.ARG_KEY_GROUP_STATUS, getModel().mGroupStatus);
		MainModel.getInstance().showSubActivity(getView(), SubActivityType.SQUARE_CONTENTS_DETAIL, bd.build());
		
//		MainModel.getInstance().downloadFile(
//				getActivity(),
//				getModel().getFileAdapter().getItem(nPosition).contentsId,
//				getModel().getFileAdapter().getItem(nPosition).attachId,
//				MainModel.getInstance().getExternamDownloadDirectory(getModel().getFileAdapter().getItem(nPosition).fileName,
//						getModel().getFileAdapter().getItem(nPosition).fileExt));
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		setFilter(getModel().mFileFilter);
	}

	// @Override
	// public void onChange(ChangeType t, MainContentsListItemVO item) {
	// switch (t) {
	// case ADD:
	// getModel().m_workData.add(item);
	// getModel().getWorkAdapter().notifyDataSetChanged();
	// break;
	// case DELETE:
	// if (getModel().m_workData.contains(item)) {
	// getModel().m_workData.remove(item);
	// getModel().getWorkAdapter().notifyDataSetChanged();
	// }
	// break;
	// case MODIFY:
	// if (getModel().m_workData.contains(item)) {
	// int n = getModel().m_workData.indexOf(item);
	// getModel().m_workData.remove(n);
	// getModel().m_workData.add(n, item);
	// getModel().getWorkAdapter().notifyDataSetChanged();
	// }
	// break;
	// }
	// }

	@Override
	public void onLastItemVisible() {
		if (!TextUtils.isEmpty(getModel().mLastContentsId)) {
			getModel().loadFileList(getActivity(), getModel().mFileFilter, getModel().mFileType, m_strSelectedUserId,
					getModel().mLastContentsId, new ISquareFileResultListener() {
						@Override
						public void onResult(SquareDefaultVO vo) {
							getModel().getFileAdapter().notifyDataSetChanged();
						}
					});
		}
	}

	public void handleActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case MainModel.REQ_SELECT_MEMBER_SINGLE:
				Serializable members = data.getSerializableExtra(MemberSelectFragment.INTENT_KEY_MEMBERS);
				if (members != null && members instanceof ArrayList<?> && ((ArrayList<?>) members).get(0) instanceof SquareMemberVO) {
					@SuppressWarnings("unchecked")
					ArrayList<SquareMemberVO> list = (ArrayList<SquareMemberVO>) members;
					if (list != null && list.size() > 0) {
						m_strSelectedUserId = list.get(0).memberId;
					}
					if (m_strSelectedUserId != null) {
						setFileType(SpFileType.ALL);
					}
				}

				break;
			}
		}
	}
}
