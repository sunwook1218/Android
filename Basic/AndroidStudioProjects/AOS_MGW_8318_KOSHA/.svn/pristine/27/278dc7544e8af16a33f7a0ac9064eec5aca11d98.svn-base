package com.hs.mobile.gw.adapter.squareplus;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.ISpMRListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView;
import com.hs.mobile.gw.fragment.squareplus.view.SpContentsDetailHeaderView;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteAttach;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteContents;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpAttachCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpContentsCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoaderEx;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class SpOpinionListAdapter extends BaseAdapter {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd aa h:mm",
			Locale.getDefault());
	private ISpCompletionViewListener  spCompletionViewListener;

	public class ViewHolder {
		private View view;
		private ImageView ivUserImg;
		private TextView tvName;
		private TextView tvDepartment;
		private TextView tvDate;
		private SpCompletionView tvOpinion;
		private ImageButton btnMore;
		private ImageButton btnFavorite;

		public ViewHolder(View v) {
			view = v;
			ivUserImg = (ImageView) view.findViewById(R.id.ID_IMG_USER);
			tvName = (TextView) view.findViewById(R.id.ID_TV_NAME);
			tvDepartment = (TextView) view.findViewById(R.id.ID_TV_DEPARTMENT);
			tvDate = (TextView) view.findViewById(R.id.ID_TV_DATE);
			tvOpinion = (SpCompletionView) view
					.findViewById(R.id.ID_COMPLETION_VIEW);
			tvOpinion.setTagEnable(false);
			btnFavorite = (ImageButton)view.findViewById(R.id.ID_BTN_FAVORITE);
			btnMore = (ImageButton) view.findViewById(R.id.ID_BTN_MORE);
		}

		public void setData(SpContentsVO item, ISpCompletionViewListener listener) {

			tvName.setText(item.getAuthorName());
			tvDepartment.setText("(" + item.getAuthorDeptName() + " "
					+ item.getAuthorPositionName() + ")");
			tvDate.setText(sdf.format(item.getCreateDate()));
			tvOpinion.setData(item, listener);
		}

		public ImageButton getBtnMore() {
			return btnMore;
		}
		
		public ImageButton getBtnFavorite(){
			return btnFavorite;
		}
	}

	private List<SpContentsVO> data;
	private ISpMRListener listener;
	private ISpClickListener clickListener;

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public SpContentsVO getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	public void setListener(ISpMRListener listener) {
		this.listener = listener;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.list_item_sp_opinion, null, false);
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if(viewHolder == null) return null;
		if (getItem(position) != null) {
			// 사용자 사진
			if(MainModel.getInstance().getOpenApiService()==null)return null;
			MainModel.getInstance().getOpenApiService().drawUserPhoto(getItem(position).getAuthorId(), viewHolder.ivUserImg, parent.getContext().getResources());
			
			viewHolder.setData(getItem(position),spCompletionViewListener);
				
			if(TextUtils.equals(HMGWServiceHelper.userId, getItem(position).getAuthorId())){			
				viewHolder.getBtnMore().setVisibility(View.VISIBLE);
				viewHolder.getBtnMore().setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						PopupMenu popupMenu = new PopupMenu(parent.getContext(), v);
						popupMenu.getMenuInflater().inflate(
								R.menu.squareplus_topic_list, popupMenu.getMenu());
						popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
							@Override
							public boolean onMenuItemClick(MenuItem item) {
								switch (item.getItemId()) {
								case R.id.ID_TOPIC_MODIFY:
									listener.onModify(getItem(position));
									break;
								case R.id.ID_TOPIC_DELETE:
									listener.onRemove(getItem(position));
									break;
								}
								return true;
							}
						});
						popupMenu.show();
					}
				});
			}else
			{
				viewHolder.getBtnMore().setVisibility(View.INVISIBLE);
			}
			viewHolder.getBtnFavorite().setSelected("1".equals(getItem(position).getFavoriteFlag()) ? true
					: false);
			viewHolder.getBtnFavorite().setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					clickListener.onFavoriteClick(getItem(position), new IFavoriteCallbak() {
						public void onResponse(SpContentsVO item) {
							getItem(position).setFavoriteFlag(item.getFavoriteFlag());
							notifyDataSetChanged();
						}
						@Override
						public void onResponse(SpAttachVO item) {
							
						}
					});
				}
			});
		}
		
		return convertView;
	}

	public void setData(List<SpContentsVO> commentList,ISpCompletionViewListener listener) {
		data = commentList;
		spCompletionViewListener = listener;
	}

	public void setClickListener(ISpClickListener clickListener) {
		this.clickListener = clickListener;
	}
}
