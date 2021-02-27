package com.hs.mobile.gw.fragment.square.searchresult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.adapter.FavoriteFileListAdapter;
import com.hs.mobile.gw.adapter.FavoriteWorkListAdapter;
import com.hs.mobile.gw.openapi.square.SearchResult;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SearchResultVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;

import android.app.Activity;

public class SquareSearchResultModel {
	private FavoriteWorkListAdapter m_workAdapter;
	private FavoriteFileListAdapter m_fileAdapter;

	public interface ISquareSearchResultListener {
		void onResult(SearchResultVO vo);
	}

	public enum SearchType {
		WORK("0"), FILE("1");
		private String m_strType;

		private SearchType(String s) {
			m_strType = s;
		}

		public String getTypeString() {
			return m_strType;
		}

	}

	public static final String ARG_KEY_SQUARE_SEARCH_KEYWORD = "arg_key_square_search_keyword";
	public String mStrSearchKeyword;
	public String mStrSquareId;
	public ArrayList<MainContentsListItemVO> m_workData = new ArrayList<MainContentsListItemVO>();
	private ArrayList<AttachListItemVO> m_fileData = new ArrayList<AttachListItemVO>();
	public SearchType mSearchMode = SearchType.WORK;
	public Status mGroupStatus;
	public String mLastContentsId;
	public int m_strTotalCount;
	
	public void loadSearchResult(final Activity a, SearchType t, final ISquareSearchResultListener listener) {
		SquareDefaultAjaxCallBack<SearchResultVO> callBack = new SquareDefaultAjaxCallBack<SearchResultVO>(a, SearchResultVO.class) {

			public void callback(String url, JSONObject json, com.androidquery.callback.AjaxStatus status) {
				super.callback(url, json, status);
				m_workData.clear();
				m_fileData.clear();
				if (getVO().responseData.totalCount == 0) {
					// PopupUtil.showToastMessage(a, "검색 결과가 없습니다.");
					mLastContentsId = null;
					listener.onResult(null);
				} else {
					try {
						Debug.trace(getVO().getJson().toString(5));
						switch (mSearchMode) {
						case WORK:
							for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
								m_workData.add(new MainContentsListItemVO(getVO().responseData.dataList.optJSONObject(i)));
							}
							mLastContentsId = m_workData.get(m_workData.size() - 1).contentsId;
							m_strTotalCount = getVO().responseData.totalCount;
							break;
						case FILE:
							for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
								m_fileData.add(new AttachListItemVO(getVO().responseData.dataList.optJSONObject(i)));
							}
							mLastContentsId = m_fileData.get(m_fileData.size() - 1).contentsId;
							m_strTotalCount = getVO().responseData.totalCount;
							break;
						}
						listener.onResult(getVO());
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
				}
			};
		};
		callBack.progress(PopupUtil.getProgressDialog(a));
		Map<String, String> params = new HashMap<String, String>();
		params.put("squareId", mStrSquareId);
		params.put("resultType", t.getTypeString());
		params.put("searchWord", mStrSearchKeyword);
		new ApiLoaderEx<JSONObject>(new SearchResult(a), a, callBack, params).execute();
	}

	public void loadMoreData(final Activity a, SearchType t, final ISquareSearchResultListener listener) {
		SquareDefaultAjaxCallBack<SearchResultVO> callBack = new SquareDefaultAjaxCallBack<SearchResultVO>(a, SearchResultVO.class) {
			public void callback(String url, JSONObject json, com.androidquery.callback.AjaxStatus status) {
				super.callback(url, json, status);
				if (getVO().responseData.totalCount == 0) {
					// PopupUtil.showToastMessage(a, "검색 결과가 없습니다.");
					mLastContentsId = null;
					listener.onResult(null);
				} else {
					try {
						Debug.trace(getVO().getJson().toString(5));
						switch (mSearchMode) {
						case WORK:
							for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
								m_workData.add(new MainContentsListItemVO(getVO().responseData.dataList.optJSONObject(i)));
							}
							mLastContentsId = getVO().responseData.lastViewId;
							m_strTotalCount = getVO().responseData.totalCount;
							break;
						case FILE:

							for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
								m_fileData.add(new AttachListItemVO(getVO().responseData.dataList.optJSONObject(i)));
							}
							mLastContentsId = m_workData.get(m_workData.size() - 1).contentsId;
							m_strTotalCount = getVO().responseData.totalCount;
							break;
						}
						listener.onResult(getVO());
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
				}
			};
		};
		callBack.progress(PopupUtil.getProgressDialog(a));
		Map<String, String> params = new HashMap<String, String>();
		params.put("squareId", mStrSquareId);
		params.put("resultType", t.getTypeString());
		params.put("searchWord", mStrSearchKeyword);
		params.put("lastContentsId", mLastContentsId);
		new ApiLoaderEx<JSONObject>(new SearchResult(a), a, callBack, params).execute();
	}

	public void createAdapter(Activity activity, IBookmarkAndOptionViewListener listener) {
		m_workAdapter = new FavoriteWorkListAdapter(activity, mGroupStatus, m_workData, listener);
		m_fileAdapter = new FavoriteFileListAdapter(activity, m_fileData, listener);
	}

	public FavoriteWorkListAdapter getWorkAdapter() {
		return m_workAdapter;
	}

	public FavoriteFileListAdapter getFileAdapter() {
		return m_fileAdapter;
	}

}
