package com.hs.mobile.gw.adapter.squareplus;

import java.util.List;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SpMentionAdapter extends BaseAdapter {

	public static class ViewHolder {

		private TextView tvName;
		private TextView tvDepartment;

		public ViewHolder(View convertView) {
			tvName = (TextView) convertView.findViewById(R.id.ID_TV_NAME);
			tvDepartment = (TextView) convertView.findViewById(R.id.ID_TV_DEPARTMENT);
		}

		public void setName(String itemName) {
			tvName.setText(itemName);
		}

		public void setDepartment(String itemText) {
			tvDepartment.setText(itemText);
		}

	}

	private List<SpContentsMentionVO> m_data;

	public SpMentionAdapter(List<SpContentsMentionVO> data) {
		m_data = data;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SpContentsMentionVO getItem(int position) {
		return m_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sp_write_popup_item, null, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.setName(getItem(position).getItemName());
		holder.setDepartment(getItem(position).getItemText());
		return convertView;
	}

}
