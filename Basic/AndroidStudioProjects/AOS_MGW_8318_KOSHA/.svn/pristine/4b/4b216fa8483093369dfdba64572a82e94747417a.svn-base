package com.hs.mobile.gw.adapter;

import java.util.ArrayList;

import com.hs.mobile.gw.MenuListHelper.WorkGroupType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyWorkGroupListAdapter extends BaseAdapter {

	private ArrayList<MyWorkGroupMenuListItemVO> m_data;
	private WorkGroupType m_workGroupType;

	public static class ViewHolder {
		public ImageView m_imgBullet;
		public TextView m_tvGroupName;
		public TextView m_tvCount;
		public ImageView m_imgIndicator;
	}

	public MyWorkGroupListAdapter(ArrayList<MyWorkGroupMenuListItemVO> data, WorkGroupType t) {
		m_data = data;
		m_workGroupType = t;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_square_group, null, false);
			holder = new ViewHolder();
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.m_imgBullet = (ImageView) convertView.findViewById(R.id.ID_IMG_GROUP_BULLET);
		holder.m_tvGroupName = (TextView) convertView.findViewById(R.id.ID_TV_WORKGROUP_NAME);
		holder.m_tvCount = (TextView) convertView.findViewById(R.id.ID_TV_NEW_COUNT);
		holder.m_imgIndicator = (ImageView) convertView.findViewById(R.id.ID_IMG_INDICATOR);

		holder.m_tvGroupName.setText(getItem(position).title);
		holder.m_tvCount.setText(String.valueOf(getItem(position).orderCount));
		if (getItem(position).orderCount == 0) {
			holder.m_tvCount.setVisibility(View.GONE);
		} else {
			holder.m_tvCount.setVisibility(View.VISIBLE);
		}

		holder.m_imgBullet.setImageResource(m_workGroupType.getIconRes());

		if (getItem(position).newCount > 0) {
			holder.m_tvGroupName.setTypeface(null, Typeface.BOLD);
		} else {
			holder.m_tvGroupName.setTypeface(null, Typeface.NORMAL);
		}

		return convertView;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public MyWorkGroupMenuListItemVO getItem(int position) {
		return m_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
