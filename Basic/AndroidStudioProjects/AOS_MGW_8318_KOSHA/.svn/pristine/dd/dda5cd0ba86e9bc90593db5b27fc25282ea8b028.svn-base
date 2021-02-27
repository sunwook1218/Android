package com.hs.mobile.gw.adapter.squareplus;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;

public class SpPopularTagAdapter extends BaseAdapter {

	private List<SpTagVO> m_popularData = new ArrayList<SpTagVO>();
	
	public void setData(List<SpTagVO> data) {
		for (SpTagVO tagVO : data) {
			m_popularData.add(tagVO);
		}
	}
	
	public SpPopularTagAdapter(List<SpTagVO> data) {
		this.m_popularData = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_popularData.size();
	}

	@Override
	public SpTagVO getItem(int position) {
		// TODO Auto-generated method stub
		return m_popularData.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sp_popular_tag_row, parent, false);
			holder = new ViewHolder();
			holder.m_tagName = (TextView) convertView.findViewById(R.id.ID_TV_TAG_NAME);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.m_tagName.setText("#"+getItem(position).getTagName());

		return convertView;
	}
	
	class ViewHolder {
		TextView m_tagName;
	}

}
