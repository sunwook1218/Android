package com.hs.mobile.gw.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.view.BookmarkAndOptionView;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.NameAndDepartmentView;

import android.app.Activity;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FavoriteWorkListAdapter extends BaseAdapter {
	public static final int MESSAGE = 0;
	public static final int OPINION = 1;
	public static final int COMMAND = 2;
	public static final int TOPIC = 3;
	public static final int FILE = 4;
	private DateFormat m_dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());

	public static class ViewHolder {
		public NameAndDepartmentView nameAndDepartmentView;
		public BookmarkAndOptionView bokmarkAndOptionView;
		public TextView tvTitle;
		public TextView tvBody;
		public TextView tvDate;
		public ImageView imgProfile;
		public TextView tvFileName;

		public ViewHolder(View convertView) {
			imgProfile = (ImageView) convertView.findViewById(R.id.ID_IMG_PROFILE);
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bokmarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvTitle = (TextView) convertView.findViewById(R.id.ID_TV_TITLE);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			tvFileName = (TextView) convertView.findViewById(R.id.ID_TV_FILE_NAME);
		}
	}

	private ArrayList<MainContentsListItemVO> m_data;
	private Status m_status;
	private IBookmarkAndOptionViewListener m_bookmarkAndOptionViewListener;

	public FavoriteWorkListAdapter(Activity activity, Status status, ArrayList<MainContentsListItemVO> data,
			IBookmarkAndOptionViewListener bookmarkAndOptionViewListener) {
		m_status = status;
		m_data = data;
		m_bookmarkAndOptionViewListener = bookmarkAndOptionViewListener;
	}

	@Override
	public int getCount() {

		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public MainContentsListItemVO getItem(int position) {
		return m_data.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return 5;
	}

	@Override
	public int getItemViewType(int position) {
		switch (getItem(position).contentsType) {
		case COMMAND:
			return COMMAND;
		case FILE_UPLOAD:
			return FILE;
		case GROUPINFO_SYSTEM_MESSAGE:
			break;
		case MESSAGE:
			return MESSAGE;
		case OPINION:
			return OPINION;
		case SYSTEM_MESSAGE:
			break;
		case TOPIC:
			return TOPIC;
		case USER_SYSTEM_INFO:
			break;
		}
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			switch (getItemViewType(position)) {
			case COMMAND:
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_square_favorite_command, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
				break;
			case FILE:
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_square_favorite_file_upload, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
				break;
			case MESSAGE:
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_square_favorite_message, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
				break;
			case OPINION:
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_square_favorite_opinion, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
				break;
			case TOPIC:
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_square_favorite_topic, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
				break;
			}
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if(holder == null) return null;

		holder.bokmarkAndOptionView.getBtnOption().setVisibility(View.GONE);
		if(getItem(position)!= null) holder.bokmarkAndOptionView.setData(m_status, getItem(position));
		holder.bokmarkAndOptionView.setBookmarkAndOptionViewListener(m_bookmarkAndOptionViewListener);
		if(getItem(position)!= null) holder.nameAndDepartmentView.setData(getItem(position).authorName, getItem(position).authorDeptName,getItem(position).authorPositionName);
		Date date = new Date(getItem(position).createDate);

		if (holder.tvBody != null) {
			if (!TextUtils.isEmpty(getItem(position).body)) {
				holder.tvBody.setText(Html.fromHtml(getItem(position).body));
			} else {
				holder.tvBody.setVisibility(View.GONE);
			}
		}
		holder.tvDate.setText(m_dateFormat.format(date));

		switch (getItemViewType(position)) {
		case COMMAND:
			if(getItem(position)!= null) holder.tvTitle.setText(getItem(position).title);
			break;
		case FILE:
			if(getItem(position)!= null) holder.tvFileName.setText(getItem(position).title);
			break;
		case MESSAGE:
			// holder.tvTitle.setText(getItem(position).title);
			break;
		case OPINION:
			holder.imgProfile.setImageResource(R.drawable.user_defalut);
			// holder.tvTitle.setText(getItem(position).title);
			break;
		case TOPIC:
			if(getItem(position)!= null) holder.tvTitle.setText(getItem(position).title);
			break;
		}
		AndroidUtils.clearFocus((ViewGroup) convertView);
		return convertView;
	}
}
