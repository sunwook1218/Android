package com.hs.mobile.gw.fragment.square.searchresult;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CommonFragmentController;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment;
import com.hs.mobile.gw.fragment.square.searchresult.SquareSearchResultModel.ISquareSearchResultListener;
import com.hs.mobile.gw.fragment.square.searchresult.SquareSearchResultModel.SearchType;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SearchResultVO;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SquareSearchResultController extends CommonFragmentController<SquareSearchResultFragment, SquareSearchResultModel> implements
		OnClickListener, IBookmarkAndOptionViewListener, OnItemClickListener, OnRefreshListener<ListView>, IChangeContentsListener,
		OnLastItemVisibleListener {

	public SquareSearchResultController(SquareSearchResultFragment view, SquareSearchResultModel model) {
		super(view, model);
	}

	public void checkArgument() {
		if (getView().getArguments() != null) {
			String strKeyword = null;
			String strSquareId = null;
			if (getView().getArguments().containsKey(SquareSearchResultModel.ARG_KEY_SQUARE_SEARCH_KEYWORD)) {
				strKeyword = getView().getArguments().getString(SquareSearchResultModel.ARG_KEY_SQUARE_SEARCH_KEYWORD);
			}
			if (getView().getArguments().containsKey(MainModel.ARG_KEY_SQUARE_ID)) {
				strSquareId = getView().getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
			}
			if (getView().getArguments().containsKey(MainModel.ARG_KEY_GROUP_STATUS)) {
				getModel().mGroupStatus = (Status) getView().getArguments().getSerializable(MainModel.ARG_KEY_GROUP_STATUS);
			}
			if (!TextUtils.isEmpty(strKeyword) && !TextUtils.isEmpty(strSquareId)) {
				getModel().mStrSearchKeyword = strKeyword;
				getModel().mStrSquareId = strSquareId;
			}
		} else {
			getView().getActivity().finish();
		}
	}

	public void setSearchMode(SearchType t) {
		getModel().mSearchMode = t;
		switch (t) {
		case FILE:
			getView().m_btnMessage.setSelected(false);
			getView().m_btnFile.setSelected(true);
			getModel().loadSearchResult(getActivity(), SearchType.FILE, new ISquareSearchResultListener() {
				@Override
				public void onResult(SearchResultVO vo) {
					getView().m_lvSearch.setAdapter(getModel().getFileAdapter());
					getModel().getFileAdapter().notifyDataSetChanged();
					getView().m_tvSearchTitle.setText(getResources().getString(R.string.label_square_search_result_title,
							getModel().mStrSearchKeyword, getModel().m_strTotalCount));
					getView().m_lvSearch.onRefreshComplete();
				}
			});
			break;
		case WORK:
			getView().m_btnMessage.setSelected(true);
			getView().m_btnFile.setSelected(false);
			getModel().loadSearchResult(getActivity(), SearchType.WORK, new ISquareSearchResultListener() {
				@Override
				public void onResult(SearchResultVO vo) {
					getView().m_lvSearch.setAdapter(getModel().getWorkAdapter());
					getModel().getWorkAdapter().notifyDataSetChanged();
					getView().m_tvSearchTitle.setText(getResources().getString(R.string.label_square_search_result_title,
							getModel().mStrSearchKeyword, getModel().m_strTotalCount));
					getView().m_lvSearch.onRefreshComplete();
				}
			});
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		case R.id.ID_LAY_L_FILE:
			setSearchMode(SearchType.FILE);
			break;
		case R.id.ID_LAY_L_WORK:
			setSearchMode(SearchType.WORK);
			break;
		}
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
		int nPosition = position - getView().m_lvSearch.getRefreshableView().getHeaderViewsCount();
		switch (getModel().mSearchMode) {
		case FILE:
			BundleUtils.Builder bd = new BundleUtils.Builder();
			bd.add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO,new MainContentsListItemVO(getModel().getFileAdapter().getItem(nPosition)));
			bd.add(MainModel.ARG_KEY_GROUP_STATUS, getModel().mGroupStatus);
			MainModel.getInstance().showSubActivity(getView(), SubActivityType.SQUARE_CONTENTS_DETAIL, bd.build());
//			MainModel.getInstance().downloadFile(
//					getActivity(),
//					getModel().getFileAdapter().getItem(nPosition).contentsId,
//					getModel().getFileAdapter().getItem(nPosition).attachId,
//					MainModel.getInstance().getExternamDownloadDirectory(getModel().getFileAdapter().getItem(nPosition).fileName,
//							getModel().getFileAdapter().getItem(nPosition).fileExt));
			break;
		case WORK:
			MainContentsListItemVO item = getModel().getWorkAdapter().getItem(nPosition);
			switch (item.contentsType) {
			case COMMAND:
			case FILE_UPLOAD:
			case TOPIC: {
				Bundle build = new BundleUtils.Builder().add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO, item)
						.add(MainModel.ARG_KEY_GROUP_STATUS, getModel().mGroupStatus).build();
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_CONTENTS_DETAIL, build);
			}
				break;
			case GROUPINFO_SYSTEM_MESSAGE:
				break;
			case MESSAGE:
				break;
			case OPINION: {
				// 의견의 경우 ParentsID를 찾아 가도록 한다.
				Bundle build = new BundleUtils.Builder().add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO, item)
						.add(MainModel.ARG_KEY_GROUP_STATUS, getModel().mGroupStatus).build();
				MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_CONTENTS_DETAIL, build);
			}
				break;
			case SYSTEM_MESSAGE:
				break;
			case USER_SYSTEM_INFO:
				break;
			}
			break;
		}

	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		setSearchMode(getModel().mSearchMode);
	}

	@Override
	public void onChange(ChangeType t, MainContentsListItemVO item) {
		switch (getModel().mSearchMode) {
		case FILE:
			break;
		case WORK:
			switch (t) {
			case ADD:
				getModel().m_workData.add(item);
				getModel().getWorkAdapter().notifyDataSetChanged();
				break;
			case DELETE:
				getModel().m_workData.remove(item);
				getModel().getWorkAdapter().notifyDataSetChanged();
				getView().m_tvSearchTitle.setText(getResources().getString(R.string.label_square_search_result_title,
						getModel().mStrSearchKeyword, getModel().m_workData.size()));
				break;
			case MODIFY:
				if (getModel().m_workData.contains(item)) {
					int n = getModel().m_workData.indexOf(item);
					getModel().m_workData.remove(n);
					getModel().m_workData.add(n, item);
					getModel().getWorkAdapter().notifyDataSetChanged();
				}
				break;
			}
			break;
		}
	}

	@Override
	public void onLastItemVisible() {
		if (!TextUtils.isEmpty(getModel().mLastContentsId)) {
			getModel().loadMoreData(getActivity(), getModel().mSearchMode, new ISquareSearchResultListener() {
				@Override
				public void onResult(SearchResultVO vo) {
					switch (getModel().mSearchMode) {
					case FILE:
						getModel().getFileAdapter().notifyDataSetChanged();
						getView().m_tvSearchTitle.setText(getResources().getString(R.string.label_square_search_result_title,
								getModel().mStrSearchKeyword,
								getModel().getFileAdapter() == null ? 0 : getModel().getFileAdapter().getCount()));
						getView().m_lvSearch.onRefreshComplete();
						break;
					case WORK:
						getModel().getWorkAdapter().notifyDataSetChanged();
						getView().m_tvSearchTitle.setText(getResources().getString(R.string.label_square_search_result_title,
								getModel().mStrSearchKeyword,
								getModel().getWorkAdapter() == null ? 0 : getModel().getWorkAdapter().getCount()));
						getView().m_lvSearch.onRefreshComplete();
						break;
					}
				}
			});
		}
	}
}
