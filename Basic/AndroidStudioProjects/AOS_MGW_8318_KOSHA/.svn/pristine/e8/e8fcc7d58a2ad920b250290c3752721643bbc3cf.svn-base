package com.hs.mobile.gw.adapter;

import java.util.ArrayList;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.FileType;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.PixelUtils;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FavoriteFileListAdapter extends BaseAdapter {
	public static final int MESSAGE = 0;
	public static final int OPINION = 1;
	public static final int COMMAND = 2;
	public static final int TOPIC = 3;
	public static final int FILE = 4;

	public static class ViewHolder {
		public ImageView imgFile;
		public TextView tvFileName;
		public TextView tvFileInfo;
		public ImageButton btnBookmark;
		public ImageButton btnMore;

		public ViewHolder(View v) {
			imgFile = (ImageView) v.findViewById(R.id.ID_IMG_FILE);
			tvFileName = (TextView) v.findViewById(R.id.ID_TV_FILE_NAME);
			tvFileInfo = (TextView) v.findViewById(R.id.ID_TV_FILE_INFO);
			btnBookmark = (ImageButton) v.findViewById(R.id.ID_BTN_FILE_BOOKMARK);
			btnMore = (ImageButton) v.findViewById(R.id.ID_BTN_FILE_MORE);
			btnBookmark.setFocusable(false);
			btnMore.setFocusable(false);
			btnMore.setVisibility(View.GONE);
		}
	}

	private ArrayList<AttachListItemVO> m_data;
	private IBookmarkAndOptionViewListener m_listener;

	public FavoriteFileListAdapter(Activity activity, ArrayList<AttachListItemVO> m_fileData, IBookmarkAndOptionViewListener listener) {
		m_data = m_fileData;
		m_listener = listener;
	}

	@Override
	public int getCount() {

		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public AttachListItemVO getItem(int position) {

		return m_data.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.square_contents_detail_file_item, null);
			int nPadding = (int) PixelUtils.getDip(parent.getResources(), 10);
			convertView.setPadding(nPadding, nPadding, nPadding, nPadding);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.imgFile.setImageResource(FileType.valueOfExtention(getItem(position).fileExt).getRes());
		holder.tvFileName.setText(getItem(position).fileName + "." + getItem(position).fileExt);
		holder.tvFileInfo.setText(parent.getResources().getString(R.string.label_square_contents_detail_file_info_format,
				MainModel.getInstance().readableFileSize(getItem(position).fileSize), getItem(position).fileExt));
		holder.btnBookmark.setSelected(getItem(position).favoriteFlag);
		holder.btnBookmark.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				m_listener.onFavoriteClick(getItem(position).squareId, getItem(position).attachId, FavoriteContentsType.SINGLE_FILE,
						!v.isSelected(), new SquareDefaultCallback() {
							public void onResponse(String strRet) {
								super.onResponse(strRet);
								v.setSelected(!v.isSelected());
							}
						});
			}
		});
		AndroidUtils.clearFocus((ViewGroup) convertView);
		return convertView;
	}
}
