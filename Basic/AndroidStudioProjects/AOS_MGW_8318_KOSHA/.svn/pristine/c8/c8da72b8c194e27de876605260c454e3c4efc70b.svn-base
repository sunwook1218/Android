package com.hs.mobile.gw.adapter.squareplus;

import java.util.ArrayList;
import java.util.List;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.squareplus.SpContentWriteFragment.PopupStatus;
import com.hs.mobile.gw.fragment.squareplus.view.SpWritePopupView.IWritePopupResultListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpTagAdapter extends BaseExpandableListAdapter {
	public static class ViewHolder {

		private TextView tvName;
		private LinearLayout llTag;

		public ViewHolder(View convertView) {
			tvName = (TextView) convertView.findViewById(R.id.ID_TV_NAME);
			llTag = (LinearLayout) convertView.findViewById(R.id.ID_LAY_L_TAG);
		}

		public void setName(String itemName) {
			tvName.setText(itemName);
		}

		public void setOnclickListener(final IWritePopupResultListener listener, final SpTagVO tagVO) {
			llTag.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onWritePopupResult(PopupStatus.TAG, tagVO);
				}
			});
		}

	}

	private Context context;
	private List<SpTagVO> m_recentlyData = new ArrayList<SpTagVO>();
	private List<SpTagVO> m_popularData = new ArrayList<SpTagVO>();
	private IWritePopupResultListener listener;

	public SpTagAdapter(Context context, List<SpTagVO> data) {
		this.context = context;
		setData(data);
	}

	public void setData(List<SpTagVO> data) {
		for (SpTagVO tagVO : data) {
			switch(tagVO.getTagTypeEnum()) {
			case POPULAR:
				m_popularData.add(tagVO);
				break;
			case RECENTLY:
				m_recentlyData.add(tagVO);
				break;
			}
		}
	}

	public void setData(List<SpTagVO> data, IWritePopupResultListener listener) {
		setData(data);
		this.listener = listener;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_squareplus_tag_header, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.setName(getGroup(groupPosition));

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sp_write_popup_tag_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.setName(getChild(groupPosition, childPosition).getTagName());
		holder.setOnclickListener(listener, getChild(groupPosition, childPosition));

		return convertView;
	}

	@Override
	public int getGroupCount() {
		return 2;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if (groupPosition == 0) {
			return m_recentlyData.size();
		} else {
			return m_popularData.size();
		}
	}

	@Override
	public String getGroup(int groupPosition) {
		if (groupPosition == 0) {
			return context.getString(R.string.label_squareplus_recently_tag);
		} else {
			return context.getString(R.string.label_squareplus_popular_tag);
		}
	}

	@Override
	public SpTagVO getChild(int groupPosition, int childPosition) {
		if (groupPosition == 0) {
			return m_recentlyData.get(childPosition);
		} else {
			return m_popularData.get(childPosition);
		}
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}
