package com.hs.mobile.gw.adapter.squareplus;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MenuListHelper.SpSquareType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.openapi.squareplus.SpGetDefaultSquareId;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.openapi.vo.OrgTreeItemVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.PopupUtil;

public class SpOrgGroupAdapter extends BaseAdapter {

	public SpOrgGroupAdapter(MainFragment f, List<OrgTreeItemVO> list) {

		this.mData = list;
		this.fragment = f;
	}

	List<OrgTreeItemVO> mData;
	MainFragment fragment;
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return mData.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout layout;
		TextView folderName;
		ImageButton imgBtn;
		if (convertView == null) {
			int templeteId = R.layout.list_item_squareplus_folder_org_row;
			convertView = LayoutInflater.from(fragment.getActivity()).inflate(templeteId, parent, false);
			layout = (LinearLayout) convertView.findViewById(R.id.ID_LINEAR_FOLDERVIEW);
			folderName = (TextView) convertView.findViewById(R.id.ID_TV_FOLDER_NAME);
			imgBtn = (ImageButton) convertView.findViewById(R.id.ID_BTN_INDICATOR);
			convertView.setTag(R.id.ID_LINEAR_FOLDERVIEW , layout);
			convertView.setTag(R.id.ID_TV_FOLDER_NAME , folderName);
			convertView.setTag(R.id.ID_BTN_INDICATOR , imgBtn);
		}
		else {
			layout = (LinearLayout) convertView.getTag(R.id.ID_LINEAR_FOLDERVIEW);
			folderName = (TextView) convertView.getTag(R.id.ID_TV_FOLDER_NAME);
			imgBtn = (ImageButton) convertView.getTag(R.id.ID_BTN_INDICATOR);
		}
		
		if (mData != null) {

			if (!TextUtils.isEmpty(mData.get(position).deptName))
				folderName.setText(mData.get(position).deptName);
			
			layout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					loadSquareIdData(mData.get(position).deptId, mData.get(position).deptName);
				}
			});
			
			imgBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					fragment.getMenuListHelper().goToSquarePlusOrgList(SpSquareType.ORG,mData.get(position).deptId);
				}
			});
			
			if (mData.get(position).hasChildren)
				imgBtn.setVisibility(View.VISIBLE);
			else
				imgBtn.setVisibility(View.GONE);
				
		}
			
		return convertView;
	}
	
	private void loadSquareIdData(String deptId, final String deptName) {

		PopupUtil.showLoading(fragment.getActivity());
		SpSquareCallBack spSquareCallBack = new SpSquareCallBack(fragment.getActivity(), SpSquareVO.class) {

			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				PopupUtil.hideLoading(fragment.getActivity());
				
				if (item != null) {
					if (TextUtils.isEmpty(item.getTitle())) {
						item.setTitle(deptName);
					}
					
					MainModel.getInstance().goToGroup(fragment.getActivity(), item);
				}
				
			}
		};
		spSquareCallBack.progress(PopupUtil.getProgressDialog(fragment.getActivity()));
		{ // API명
			HashMap<String, String> params = new HashMap<>();
			params.put("deptId", deptId);
			new ApiLoaderEx<>(new SpGetDefaultSquareId(fragment.getActivity()), fragment.getActivity(), spSquareCallBack, params).execute();
		}
	}


}
