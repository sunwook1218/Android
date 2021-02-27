package com.hs.mobile.gw.adapter.squareplus;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderVO;

public class SpSelectFolderAdapter extends BaseAdapter {
	Context context;
	List<SpFolderVO> folderData;
	
	int selectPosition = -1;
	
	public int getSelectPosition() {
		return selectPosition;
	}

	public void setSelectPosition(int selectPosition) {
		this.selectPosition = selectPosition;
	}

	public SpSelectFolderAdapter(Context context,List<SpFolderVO> folderData) {
		this.context = context;
		if (folderData == null)
			folderData = new ArrayList<SpFolderVO>();
		
		this.folderData = folderData;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return folderData.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return folderData.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout layout;
		TextView folderName;
		if (convertView == null) {
			int templeteId = R.layout.list_item_squareplus_folder_edit_row;
			convertView = LayoutInflater.from(context).inflate(templeteId, parent, false);
			layout = (LinearLayout) convertView.findViewById(R.id.ID_LINEAR_FOLDERVIEW);
			folderName = (TextView) convertView.findViewById(R.id.ID_TV_FOLDER_NAME);
			convertView.setTag(R.id.ID_LINEAR_FOLDERVIEW , layout);
			convertView.setTag(R.id.ID_TV_FOLDER_NAME , folderName);
		}
		else {
			layout = (LinearLayout) convertView.getTag(R.id.ID_LINEAR_FOLDERVIEW);
			folderName = (TextView) convertView.getTag(R.id.ID_TV_FOLDER_NAME);
		}
		
		layout.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
		if (position == selectPosition) {
			layout.setBackgroundColor(Color.argb(0xff,0xea,0xf1,0xf9));
		}
		
		if (folderData != null) {

			if (!TextUtils.isEmpty(folderData.get(position).getFolderName()))
				folderName.setText(folderData.get(position).getFolderName());
		}
		
		return convertView;
	}

}
