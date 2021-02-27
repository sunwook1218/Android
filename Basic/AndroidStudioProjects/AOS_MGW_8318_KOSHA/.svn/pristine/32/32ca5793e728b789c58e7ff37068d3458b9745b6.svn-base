package com.hs.mobile.gw.fragment.squareplus.file;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.adapter.FavoriteFileListAdapter;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;

import android.app.Activity;

public class SpFileModel {
	private FavoriteFileListAdapter m_fileAdapter;

	public interface ISquareFileResultListener {
		void onResult(SquareDefaultVO vo);
	}

	public enum SpFileFilter {
		ALL("0"), MY("1"), OTHER("2");
		private String m_strType;

		private SpFileFilter(String s) {
			m_strType = s;
		}

		public String getValue() {
			return m_strType;
		}
	}

	public enum SpFileType {
		ALL(""), DOC("1"), IMAGE("2"), VIDEO("3"), ETC("0");
		private String m_strType;

		private SpFileType(String s) {
			m_strType = s;
		}

		public String getValue() {
			return m_strType;
		}
	}

	public String mStrSquareId;
	public ArrayList<AttachListItemVO> m_fileData = new ArrayList<AttachListItemVO>();
	public SpFileFilter mFileFilter = SpFileFilter.ALL;
	public SpFileType mFileType = SpFileType.ALL;
	public Status mGroupStatus;
	public String mLastContentsId;

	public void loadFileList(final Activity a, SpFileFilter ff, SpFileType ft, String strAuthId, String strLastAttachId,
			final ISquareFileResultListener listener) {
		SquareDefaultAjaxCallBack<SquareDefaultVO> callBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(a, SquareDefaultVO.class) {
			public void callback(String url, JSONObject json, com.androidquery.callback.AjaxStatus status) {
				super.callback(url, json, status);
				if (getVO().responseData.totalCount == 0) {
					mLastContentsId = null;
					listener.onResult(null);
				} else {
					try {
						Debug.trace(getVO().getJson().toString(5));
						for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
							m_fileData.add(new AttachListItemVO(getVO().responseData.dataList.optJSONObject(i)));
						}
						mLastContentsId = m_fileData.get(m_fileData.size() - 1).contentsId;
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
		params.put("fileType", ft.getValue());
		params.put("authorId", strAuthId);
		params.put("sortType", "date");
		params.put("lastAttachId", strLastAttachId);

	}

	public void createAdapter(Activity activity, IBookmarkAndOptionViewListener listener) {
		m_fileAdapter = new FavoriteFileListAdapter(activity,  m_fileData, listener);
	}

	public FavoriteFileListAdapter getFileAdapter() {
		return m_fileAdapter;
	}

}
