package com.hs.mobile.gw.fragment.square.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.adapter.FavoriteWorkListAdapter;
import com.hs.mobile.gw.openapi.square.InstructionsList;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;

import android.app.Activity;

public class SquareOrderModel {
	private FavoriteWorkListAdapter m_workAdapter;

	public interface ISquareOrderResultListener {
		void onResult(SquareDefaultVO vo);
	}

	public enum OrderFilter {
		ALL("0"), SEND("1"), RECEIVE("2");
		private String m_strType;

		private OrderFilter(String s) {
			m_strType = s;
		}

		public String getValue() {
			return m_strType;
		}
	}

	public enum OrderStatus {
		ALL(""), PROCESS_OR_DELAY("1"), COMPLETE("2");
		private String m_strOrderStatus;

		private OrderStatus(String s) {
			m_strOrderStatus = s;
		}

		public String getValue() {
			return m_strOrderStatus;
		}
	}

	public enum OrderDelayFlag {
		PROCESS_OR_COMPLETE("0"), ALL_OR_DELAY("1");
		private String m_strOrderDelayFlag;

		private OrderDelayFlag(String s) {
			m_strOrderDelayFlag = s;
		}

		public String getValue() {
			return m_strOrderDelayFlag;
		}
	}

	public enum OrderType {
		ALL(OrderStatus.ALL, OrderDelayFlag.ALL_OR_DELAY), 
		PROCESS(OrderStatus.PROCESS_OR_DELAY, OrderDelayFlag.PROCESS_OR_COMPLETE), 
		DELAY(OrderStatus.PROCESS_OR_DELAY, OrderDelayFlag.ALL_OR_DELAY), 
		COMPLETE(OrderStatus.COMPLETE,	OrderDelayFlag.PROCESS_OR_COMPLETE);
		private OrderStatus m_os;
		private OrderDelayFlag m_odf;

		private OrderType(OrderStatus os, OrderDelayFlag odf) {
			m_os = os;
			m_odf = odf;
		}

		public OrderStatus getOrderStatus() {
			return m_os;
		}

		public OrderDelayFlag getOrderDelayFlag() {
			return m_odf;
		}
	}

	public String mStrSquareId;
	public ArrayList<MainContentsListItemVO> m_workData = new ArrayList<MainContentsListItemVO>();
	public OrderFilter mOrderFilter = OrderFilter.ALL;
	public OrderType mOrderType = OrderType.ALL;
	public Status mGroupStatus;
	public String mLastContentsId;

	public void loadInstructionsList(final Activity a, OrderFilter of, OrderType ot, final ISquareOrderResultListener listener) {
		SquareDefaultAjaxCallBack<SquareDefaultVO> callBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(a, SquareDefaultVO.class) {
			public void callback(String url, JSONObject json, com.androidquery.callback.AjaxStatus status) {
				super.callback(url, json, status);
				m_workData.clear();
				if (getVO().responseData.totalCount == 0) {
					// PopupUtil.showToastMessage(a, "검색 결과가 없습니다.");
					mLastContentsId = null;
					listener.onResult(null);
				} else {
					try {
						Debug.trace(getVO().getJson().toString(5));
						for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
							m_workData.add(new MainContentsListItemVO(getVO().responseData.dataList.optJSONObject(i)));
						}
						mLastContentsId = m_workData.get(m_workData.size() - 1).contentsId;
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
		params.put("orderType", of.getValue());
		params.put("status", ot.getOrderStatus().getValue());
		params.put("isDelayFlag", ot.getOrderDelayFlag().getValue());
		new ApiLoaderEx<JSONObject>(new InstructionsList(a), a, callBack, params).execute();
	}

	public void createAdapter(Activity activity, IBookmarkAndOptionViewListener listener) {
		m_workAdapter = new FavoriteWorkListAdapter(activity, mGroupStatus, m_workData, listener);
	}

	public FavoriteWorkListAdapter getWorkAdapter() {
		return m_workAdapter;
	}

	public void loadMoreData(final Activity a,  final ISquareOrderResultListener listener) {
		SquareDefaultAjaxCallBack<SquareDefaultVO> callBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(a, SquareDefaultVO.class) {
			public void callback(String url, JSONObject json, com.androidquery.callback.AjaxStatus status) {
				super.callback(url, json, status);
				if (getVO().responseData.totalCount == 0) {
					// PopupUtil.showToastMessage(a, "검색 결과가 없습니다.");
					mLastContentsId = null;
					listener.onResult(null);
				} else {
					try {
						Debug.trace(getVO().getJson().toString(5));
						for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
							m_workData.add(new MainContentsListItemVO(getVO().responseData.dataList.optJSONObject(i)));
						}
						mLastContentsId = m_workData.get(m_workData.size() - 1).contentsId;
						listener.onResult(getVO());
						return;
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
					listener.onResult(getVO());
				}
			};
		};
		callBack.progress(PopupUtil.getProgressDialog(a));
		Map<String, String> params = new HashMap<String, String>();
		params.put("squareId", mStrSquareId);
		params.put("orderType", mOrderFilter.getValue());
		params.put("status", mOrderType.getOrderStatus().getValue());
		params.put("isDelayFlag", mOrderType.getOrderDelayFlag().getValue());
		params.put("lastContentsId", mLastContentsId);
		new ApiLoaderEx<JSONObject>(new InstructionsList(a), a, callBack, params).execute();
	}
}
