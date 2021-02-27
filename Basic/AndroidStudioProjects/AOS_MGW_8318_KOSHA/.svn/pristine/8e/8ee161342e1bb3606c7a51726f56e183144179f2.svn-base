package com.hs.mobile.gw.adapter.squareplus;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.squareplus.ISpContent;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpSystemMessageView;
import com.hs.mobile.gw.fragment.squareplus.view.SpTopicView;
import com.hs.mobile.gw.fragment.squareplus.view.SpTopicView.ISpTopicViewListener;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.util.Debug;

public class SpContentsAdapter extends BaseAdapter {

	public static class SpSystemMessageViewHolder extends ViewHolder {
		private SpSystemMessageView view;

		public SpSystemMessageViewHolder(View v) {
			super(v);
			this.view = (SpSystemMessageView) v;
		}

		public void setData(SpContentsVO item, boolean isOriginView, boolean openSquare) {
			super.setData(item, isOriginView, openSquare);
			view.setData(item, spCompletionViewListener, isOriginView);
		}
	}

	public static class ViewHolder {
		private View view;
		private SpContentsVO data;
		private boolean isOriginView;
		private boolean openSquare;

		public ViewHolder(View v) {
			this.view = v;
		}

		public void setData(SpContentsVO item, boolean isOriginView, boolean openSquare) {
			this.data = item;
			this.isOriginView = isOriginView;
			this.openSquare = openSquare;
		}
	}

	public static class TopicViewHolder extends ViewHolder {

		private SpTopicView view;

		public TopicViewHolder(View convertView) {
			super(convertView);
			this.view = (SpTopicView) convertView;
		}

		@Override
		public void setData(SpContentsVO item, boolean isOriginView, boolean openSquare) {
			super.setData(item, isOriginView, openSquare);
			view.setSpTopicViewListener(spTopicViewListener);
			view.setData(item, isOriginView, openSquare);
		}
	}
	
	public static class NoticeViewHolder extends ViewHolder {
		private View view;
		private ISpClickListener listener;
		
		public void setSpClickListener(ISpClickListener listener) {
			this.listener = listener;
		}
		public NoticeViewHolder(View convertView) {
			super(convertView);
			this.view = convertView;
		}
		
		@Override
		public void setData(final SpContentsVO item, boolean isOriginView, boolean openSquare) {
			RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.ID_LAY_L_NOTICE);
			SpCompletionView text = (SpCompletionView) view.findViewById(R.id.ID_TV_NOTICE_TITLE);
			text.setData(item, null);
			
			OnClickListener click = new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Debug.trace("");
					if (listener != null)
						listener.onNoticeView(item);
				}
			};
			
			view.setOnClickListener(click);
			layout.setOnClickListener(click);
			text.setOnClickListener(click);
		}
	}
	
	public void setTopicViewListener(ISpTopicViewListener lisnener){
		spTopicViewListener = lisnener;
	}

	private int[] ContentTypes = { SpSquareConst.CONTENTS_TYPE_TOPIC_INT,
			SpSquareConst.CONTENTS_TYPE_MESSAGE_INT,
			SpSquareConst.CONTENTS_TYPE_FILE_INT,
			SpSquareConst.CONTENTS_TYPE_COMMENT_INT,
			SpSquareConst.CONTENTS_TYPE_SYSTEM_INT,
			SpSquareConst.CONTENTS_TYPE_REPORT_INT,
			SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_INT,
			SpSquareConst.CONTENTS_TYPE_SYSTEM_USER_INT,
			SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_INTRO_INT,
			SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_NOTICE_INT};
	private ArrayList<ISpContent> m_data;
	private ISpClickListener listener;
	private static ISpCompletionViewListener spCompletionViewListener;
	private static ISpTopicViewListener spTopicViewListener;
	private boolean isOriginView = false;		// 출처여부 보일지 말지 결정
	private boolean openSquare = false;		// 출처여부 보일지 말지 결정

	public SpContentsAdapter(ArrayList<ISpContent> data,
			ISpClickListener listener,
			ISpCompletionViewListener spCompletionViewListener, ISpTopicViewListener spTopicViewListener, boolean openSquare) {
		m_data = data;
		this.spCompletionViewListener = spCompletionViewListener;
		this.spTopicViewListener = spTopicViewListener;
		this.listener = listener;
		this.openSquare = openSquare;
	}

	public SpContentsAdapter(ArrayList<ISpContent> data,
			ISpClickListener listener,
			ISpCompletionViewListener spCompletionViewListener,
			ISpTopicViewListener spTopicViewListener,
			boolean isOriginView,
			boolean openSquare) {
		m_data = data;
		this.spCompletionViewListener = spCompletionViewListener;
		this.spTopicViewListener = spTopicViewListener;
		this.listener = listener;
		this.isOriginView = isOriginView;
		this.openSquare = openSquare;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SpContentsVO getItem(int position) {
		return (SpContentsVO) m_data.get(position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			switch (getItemViewType(position)) {
			case SpSquareConst.CONTENTS_TYPE_TOPIC_INT:
			case SpSquareConst.CONTENTS_TYPE_REPORT_INT:
			case SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_INTRO_INT:
				convertView = new SpTopicView(parent.getContext());
				((SpTopicView) convertView).setSpClickListener(listener);
				holder = new TopicViewHolder(convertView);
				convertView.setTag(holder);
				break;
			case SpSquareConst.CONTENTS_TYPE_MESSAGE_INT:
			case SpSquareConst.CONTENTS_TYPE_FILE_INT:
			case SpSquareConst.CONTENTS_TYPE_COMMENT_INT:
			case SpSquareConst.CONTENTS_TYPE_SYSTEM_INT:
			case SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_INT:
			case SpSquareConst.CONTENTS_TYPE_SYSTEM_USER_INT:
				convertView = new SpSystemMessageView(parent.getContext());
				holder = new SpSystemMessageViewHolder(convertView);
				convertView.setTag(holder);
				break;
			case SpSquareConst.CONTENTS_TYPE_SYSTEM_SQUARE_NOTICE_INT:
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sp_notice, parent, false);
				holder = new NoticeViewHolder(convertView);
				((NoticeViewHolder) holder).setSpClickListener(listener);
				convertView.setTag(holder);
				break;
			default:
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_blanklist, parent, false);
				break;
			}
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		if (holder != null) {
			holder.setData(getItem(position), isOriginView, openSquare);
		}
		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return ContentTypes.length;
	}

	@Override
	public int getItemViewType(int position) {
		return Integer.valueOf(getItem(position).getContentsType());
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}
