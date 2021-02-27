package com.hs.mobile.gw.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.OnScrollListView;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

public class ContactGroupListAdapter extends MGWBaseAdapter {

	public ContactGroupListAdapter(MainFragment f, int layout, ApiCode code, int initPno, List<NameValuePair> params) {
		super(f);
		this.layout = layout;
		this.apiCode = code;
		this.currentPno = initPno;
		this.initPno = initPno;
		this.mSrcArray = null;
		this.extraParams = params;
		this.mAdapter = this;
		if (setLoadingInProgress(true)) {
			initData();
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JSONObject item = mSrcArray.get(position);
		if (convertView == null) {
			if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
				convertView = LayoutInflater.from(parent.getContext()).inflate(emptyLayout, parent, false);
			} else {
				convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
			}
		}

		if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
			return convertView;
		}
		TextView titleView = (TextView) convertView.findViewById(R.id.contact_group_name);
		String name = item.optString("name").trim();
		titleView.setText(name);

		return convertView;
	}

	@Override
	public void initData() {
		isFirstPage = true;
		currentPno = initPno;
		mSrcArray = new ArrayList<JSONObject>();
		loadData();
	}

	@Override
	public void loadData() {
		new LoadDataTask().execute();
	}

	private class LoadDataTask extends AsyncTask<String, String, String> {

		private LoadDataTask() {
			super();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			mAdapter.setLoadingFinished();
		}

		@Override
		protected String doInBackground(String... param) {
			try {
				JSONObject data = null;
				if(MainModel.getInstance().getOpenApiService()==null) return null;
				if(MainModel.getInstance().getOpenApiService().getContactList(getActivity(), apiCode, currentPno, extraParams, false) != null){
					data = MainModel.getInstance().getOpenApiService().getContactList(getActivity(), apiCode, currentPno, extraParams, false);
				}
				if (data != null) {
					if (currentPno == initPno || mSrcArray == null) {
						mSrcArray = new ArrayList<JSONObject>();
						total = data.getInt("total");
						pageSize = data.getInt("pagesize");
					}
					if (total != 0) {
						JSONArray jArray = data.optJSONArray("group");
						if (jArray == null) {
							JSONObject item = data.optJSONObject("group");
							mSrcArray.add(item);
						} else {
							for (int i = 0; i < jArray.length(); i++) {
								mSrcArray.add((JSONObject) jArray.get(i));
							}
						}
					} else {
						setForEmptyList();
					}
					if (listView == null) {
						listView = new OnScrollListView(getMainFragment().getActivity());
						listView.setAdapter(mAdapter);
						setListView();
						mAdapter.addListViewToMiddleFlipper();
					} else {
						mAdapter.updateListview();
					}
				} else {
					if (currentPno != initPno) {
						currentPno--;
					}
				}
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			}
			return null;
		}
	}

	@Override
	public void getMoreData() {
		currentPno++;
		loadData();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (!isRefreshing()) {
			JSONObject item = (JSONObject) getItem(position);
			if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
				return;
			}
			String gid = item.optString("id");
			String name = item.optString("name");
			getController().showContactGroupMemberList(gid, name);
		}
	}

	@Override
	public boolean hasMoreData() {
		if (maxPno == -1 && total != 0 && pageSize != 0) {
			maxPno = (int) Math.ceil((double) total / (double) pageSize);
		}
		if (currentPno == maxPno && total > pageSize) {
			notiLastPage();
		} else {
			notifiedLastPage = false;
		}
		return currentPno < maxPno;
	}
}
