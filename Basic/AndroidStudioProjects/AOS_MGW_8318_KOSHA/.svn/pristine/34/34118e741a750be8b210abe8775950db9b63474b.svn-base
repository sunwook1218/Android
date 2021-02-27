package com.hs.mobile.gw.adapter.squareplus;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;

public class SpNoticeAdapter extends BaseAdapter {
	public static class ViewHolder {
		public LinearLayout m_layout;
		public ImageView m_imgUserIcon;
		public TextView m_tvUserInfo;
		public SpCompletionView m_tvContentsBody;
	}

	private IOnClickListener listener;
	private ISpCompletionViewListener spCompletionViewListener;
	private List<SpContentsVO> m_data;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd aa h:mm", Locale.getDefault());
	public static final String MENTION_REGEX = "@[{]u(\\d+)[}]"; // 멘션 정규식
	public static final String TAG_REGEX = "#[{](.*?)[}]"; // 태그 정규식
	
	public interface IOnClickListener {
		public void OnItemClick(SpContentsVO item);
	}
	
	public void setOnClickListener(IOnClickListener listener) {
		this.listener = listener;
	}

	public SpNoticeAdapter(List<SpContentsVO> data, ISpCompletionViewListener spCompletionViewListener) {
		this.m_data = data;
		this.spCompletionViewListener = spCompletionViewListener;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SpContentsVO getItem(int position) {
		return m_data.get(position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			int templeteId = R.layout.list_item_squareplus_notice;
			convertView = LayoutInflater.from(parent.getContext()).inflate(templeteId, null, false);
			holder = new ViewHolder();
			holder.m_layout = (LinearLayout) convertView.findViewById(R.id.ID_LL_SP_SQUARE);
			holder.m_imgUserIcon = (ImageView) convertView.findViewById(R.id.ID_IMG_USER_ICON);
			holder.m_tvUserInfo = (TextView) convertView.findViewById(R.id.ID_TV_USER_INFO);
			holder.m_tvContentsBody = (SpCompletionView) convertView.findViewById(R.id.ID_TV_CONTENTS_BODY);
			holder.m_tvContentsBody.setData(getItem(position), spCompletionViewListener);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(holder==null) return null;
		if (getItem(position) != null) {
           OnClickListener click = new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if (listener != null)
						listener.OnItemClick(getItem(position));
				}
			};
			
			holder.m_imgUserIcon.setOnClickListener(click);
			// 사용자 사진
			if(MainModel.getInstance().getOpenApiService()!=null){
				MainModel.getInstance().getOpenApiService().drawUserPhoto(getItem(position).getAuthorId(), holder.m_imgUserIcon, parent.getContext().getResources());
			}

			holder.m_tvUserInfo.setText(getItem(position).getAuthorName() + " " + sdf.format(getItem(position).getCreateDate()));
			holder.m_tvUserInfo.setOnClickListener(click);
			holder.m_tvContentsBody.setData(getItem(position), spCompletionViewListener);
			holder.m_tvContentsBody.setOnClickListener(click);
			holder.m_layout.setOnClickListener(click);
		}

		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		return super.getItemViewType(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
