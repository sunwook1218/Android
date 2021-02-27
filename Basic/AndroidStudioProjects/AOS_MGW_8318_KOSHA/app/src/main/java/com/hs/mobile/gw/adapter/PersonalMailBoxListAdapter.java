package com.hs.mobile.gw.adapter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.OnScrollListView;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

public class PersonalMailBoxListAdapter extends MGWBaseAdapter{

	public PersonalMailBoxListAdapter(MainFragment f, int layout) {
		super(f);
		this.layout = layout;
		this.mAdapter = this;
		if(setLoadingInProgress(true)){
			initData();
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JSONObject item = mSrcArray.get(position);
		if(convertView == null){
			convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
		}
		TextView titleView = (TextView)convertView.findViewById(R.id.mailbox_title);
		String title = item.optString("@text").trim();
		titleView.setText(title);
		return convertView;
	}

	@Override
	public void initData() {
		isFirstPage = true;
		mSrcArray = new ArrayList<JSONObject>();
		loadData();
	}
	
	public void addTreeBox(JSONObject data) throws JSONException{		
		mSrcArray.add(data);
		if(data.optBoolean("@hasChildren")){
			JSONObject item = data.optJSONObject("tree");
			if(item == null){
				JSONArray items = null;
				items = data.optJSONArray("tree");
				if(items != null){
					for(int i = 0; i < items.length(); i++){						
						addTreeBox((JSONObject)items.get(i));
					}
				}
			}else{
				addTreeBox(item);				
			}			
		}		
	}
	
	@Override
	public void loadData(){
		new LoadDataTask().execute();
	}
	
	private class LoadDataTask extends AsyncTask<String, String, String> {

		private LoadDataTask() {
			super();
		}

		@Override
		protected String doInBackground(String... param) {
			try{
				Object data = null;
				if(MainModel.getInstance().getOpenApiService()==null) return null;
				if(MainModel.getInstance().getOpenApiService().getPersonalMailBoxList(getActivity())!=null){
					data = MainModel.getInstance().getOpenApiService().getPersonalMailBoxList(getActivity());
				}
				if(data != null){
					mSrcArray = new ArrayList<JSONObject>();
					if (data instanceof JSONObject) {
						addTreeBox((JSONObject) data);				
					}else if(data instanceof JSONArray){
						JSONArray jArray = (JSONArray) data;
						for(int i = 0; i < jArray.length();i++ ){
							addTreeBox((JSONObject) jArray.get(i));
						}
					}
					if(listView == null){					
						listView = new OnScrollListView(getMainFragment().getActivity());
						listView.setAdapter(mAdapter);
						setListView();
						addListViewToMiddleFlipper();					
					} else{
						mAdapter.updateListview();
					}
				} else{
					mAdapter.setLoadingFinished();
				}
			}catch(JSONException e){
				Debug.trace(e.getMessage());
			}	
			return null;
		}
	}
	
	@Override
	public void getMoreData() {
				
	}
	
	@Override
	public boolean hasMoreData() {	
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(!isRefreshing()){
			JSONObject item = (JSONObject)getItem(position);
			String path = item.optString("@path");
			String title = item.optString("@text");			
			getController().showPersonalBoxMailList(path, title);
		}
	}
}
