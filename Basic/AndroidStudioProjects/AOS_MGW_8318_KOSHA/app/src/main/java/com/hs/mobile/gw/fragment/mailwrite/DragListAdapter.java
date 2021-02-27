package com.hs.mobile.gw.fragment.mailwrite;

import java.util.List;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.view.SelectedListItem;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class DragListAdapter extends BaseAdapter {
	
	RemoveListener removeListener;
	
	public interface RemoveListener {
		void remove(int which);
	}
	
	public void setRemoveListener(RemoveListener listener) {
		removeListener = listener;
	}

	private List<Object> m_data;

	public List<Object> getData() {
		return m_data;
	}

	public void setData(List<Object> data) {
		m_data = data;
	}

	public DragListAdapter(List<Object> objects) {
		m_data = objects;
	}

	@Override
	public int getCount() {

		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SelectedListItem getItem(int position) {

		return (SelectedListItem) m_data.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mail_drag, null);
		((TextView) v.findViewById(R.id.ID_TV_NAME)).setText(getItem(position).name);
		if (!TextUtils.isEmpty(getItem(position).dept_name)) {
			String[] strBuf = getItem(position).dept_name.split("\\.");
			String deptName = strBuf[strBuf.length - 1];
			((TextView) v.findViewById(R.id.ID_TV_DEPARTMENT)).setText(deptName);
		} else {
			((TextView) v.findViewById(R.id.ID_TV_DEPARTMENT)).setVisibility(View.GONE);
		}
		
		ImageButton delete = (ImageButton) v.findViewById(R.id.ID_BTN_DELETE);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				removeListener.remove(position);
			}
		});
		
		return v;
	}
}