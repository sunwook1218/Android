package com.hs.mobile.gw.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.MainController;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.OnScrollListView;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class MGWBaseAdapter extends BaseAdapter implements OnItemClickListener {
	ArrayList<JSONObject> mSrcArray = null;
	ArrayList<JSONObject> mSrcTmpArray = null;
	ArrayList<JSONObject> mDeletedItemArray = null;
	int layout;
	int emptyLayout = R.layout.template_blanklist;

	int PAGE_SIZE = 20;

	int total = 0;
	int pageSize;
	int currentPno;
	int initPno;
	int maxPno = -1;
	ApiCode apiCode;
	MGWBaseAdapter mAdapter;
	boolean isFirstPage = false;
	boolean isSearchMode = false;
	boolean isEditMode = false;
	boolean isShowFirstRow = false;

	public ArrayList<JSONObject> getDeletedItemArray() {
		return mDeletedItemArray;
	}

	public boolean isSearchMode() {
		return isSearchMode;
	}

	public void setEditMode(boolean editMode) {
		isEditMode = editMode;
	}

	public ApiCode getApiCode() {
		return apiCode;
	}
	public int getInitPno() {
		return initPno;
	}
	public void setInitPno(int initPno) {
		this.initPno = initPno;
	}

	public int getMaxPno() {
		return maxPno;
	}
	public void setMaxPno(int maxPno) {
		this.maxPno = maxPno;
	}


	String notityPageNoFormat;
	private DateFormat lastUpdatedDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());
	public boolean notifiedLastPage = false;

	public void notiLastPage() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (!notifiedLastPage) {
					notifiedLastPage = true;
					if (maxPno != 1) {
						PopupUtil.showToastMessage(m_view.getActivity(), R.string.message_notify_lastpage);
					}
				}
			}
		});
	}

	public int checkBoxVisibility = View.GONE;
	public int indicatorVisibility = View.VISIBLE;

	int selectedItemPosition = -1;
	String selectedItemID;
	boolean isSelectedItemDeleted = false;
	public boolean isPreventSearchDeleted = false;

	Boolean isLoadingInProgress = false;

	public boolean isLoadingInProgress() {
		return isLoadingInProgress;
	}

	// 로딩중이면 중복로딩 방지
	public boolean setLoadingInProgress(boolean showProgressDialog) {
		synchronized (isLoadingInProgress) {
			if (isLoadingInProgress) {
				return false;
			} else {
				isLoadingInProgress = true;
				if (showProgressDialog) {
					getController().showLoadingProgressDialog();
					String msgStr = m_view.getString(R.string.ptr_refreshing);
					updateLastUpdatedText(msgStr);
				} else {
					// 불러오는중 메시지
					/*
					 * Message msg = new Message(); msg.what =
					 * MainActivity.TOAST_MESSAGE_BY_RESID; msg.obj =
					 * R.string.message_notify_loading_data;
					 * MainActivity.mViewUpdateHandler.sendMessage(msg);
					 */
				}
				return true;
			}
		}
	}

	// 로딩이 끝나면
	public boolean setLoadingFinished() {
		synchronized (isLoadingInProgress) {
			if (isLoadingInProgress) {
				isLoadingInProgress = false;
				if (isFirstPage) {
					if (this.hasMoreData()) {
						getController().showListFooter();
					}
					getController().hideLoadingProgressDialog();
					String msgStr = String.format(m_view.getString(R.string.ptr_last_updated), lastUpdatedDateFormat.format(new Date()));
					updateLastUpdatedText(msgStr);
					isFirstPage = false;
				}
				return true;
			} else {
				return false;
			}
		}
	}

	OnScrollListView listView = null;

	public void setListView() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				int[] colors = { 0xBBDDDDDD, 0xBBDDDDDD, 0xBBDDDDDD };
				listView.setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
				listView.setDividerHeight(1);
			}
		});
	}

	public OnScrollListView getListView() {
		return listView;
	}

	List<NameValuePair> extraParams;
	private MainController m_controller;
	private MainFragment m_view;

	public MainFragment getMainFragment() {
		return m_view;
	}

	public List<NameValuePair> getExtraParams() {
		return extraParams;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public MGWBaseAdapter(MainFragment f) {
		m_view = f;
		m_controller = MainFragment.getController();
		notityPageNoFormat = m_view.getString(R.string.message_notify_newpageno);
	}

	public void updateLastUpdatedText(String str) {
		// 메인에 날짜 업데이트
		getController().updateLastUpdatedText(MainFragment.mListFooter, str);
	}

	public void toastNewPageNo() {
		PopupUtil.showToastMessage(m_view.getActivity(), String.format(notityPageNoFormat, currentPno));
	}

	public ArrayList<JSONObject> getmSrcArray() {
		return mSrcArray;
	}

	public void setmSrcArray(ArrayList<JSONObject> mSrcArray) {
		this.mSrcArray = mSrcArray;
	}

	// 필수
	abstract public void initData(); // 데이터 초기화 (refresh)

	abstract public void loadData(); // 데이터 로딩하기

	abstract public void getMoreData(); // 데이터 더 가져오기

	abstract public boolean hasMoreData(); // 더 가져올 데이터가 있는지 판단

	// 필요한경우 override
	public void showFirstRow() {
	}; // 목록의 첫번째 페이지 보여주기

	public void clearCheckBox() {
	};// 체크박스 초기화

	public void deleteSelectedItems() {
	}; // 체크된 아이템 삭제

	public void deleteItemsFromList(ArrayList<JSONObject> items) {
	}; // 체크된 아이템 삭제

	public void updateOtherViews() {
	}; // 해당 뷰가 화면에 보여질때 다른 뷰(버튼같은)를 업데이트

	public void deleteItemByID(String id) {
	} // id에 해당하는 아이템을 삭제

	public void showItem(int position) {
	} // position 에 해당하는 아이템을 웹뷰에 로딩

	public void saveContactItems() {
	}; // 주소록 로컬에 저장.

	public void showEmptyPage() {
		if (MainModel.getInstance().isTablet()) {
			getController().showEmptyPage();
		} else {
			getController().hideWebview(true);
		}
	}

	public void updateListview() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getController().updateListviewByAdapter(mAdapter);
			}
		});
	}

	public void addListViewToMiddleFlipper() {
		final ArrayList<Object> obj = new ArrayList<Object>();
		obj.add(listView);
		obj.add(mAdapter);
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getController().addViewIntoMiddleFlipper(obj);
			}
		});
	}

	public void setForEmptyList() throws JSONException {
		mSrcArray = new ArrayList<JSONObject>();
		JSONObject item = new JSONObject();
		item.putOpt("isEmptyList", true);
		mSrcArray.add(item);
	}

	@Override
	public int getCount() {
		if (mSrcArray == null) {
			return 0;
		} else {
			return this.mSrcArray.size();
		}
	}

	@Override
	public Object getItem(int position) {
		if (position >= mSrcArray.size()) {
			position = mSrcArray.size();
		}

		Object item = null;
		item = this.mSrcArray.get(position - listView.getHeaderViewsCount());

		return item;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public boolean isRefreshing() {
		return listView.isRefreshing() || listView.isResettingHeader();
	}

	public MainController getController() {
		return m_controller;
	}

	public FragmentActivity getActivity() {
		return m_view.getActivity();
	}
}
