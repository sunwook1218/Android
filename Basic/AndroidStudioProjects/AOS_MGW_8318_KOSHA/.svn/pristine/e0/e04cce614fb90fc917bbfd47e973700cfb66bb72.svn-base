package com.hs.mobile.gw.adapter.squareplus;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteAttach;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpAttachCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.util.ApiLoaderEx;

import androidx.fragment.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpFileListAdapter extends BaseAdapter {
	private FragmentActivity context;
	private List<SpAttachVO> m_data;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

	public enum FileType {
		DOC(R.drawable.sp_filter_file_doc, "doc", "docx"),
		HWP(R.drawable.sp_filter_file_hwp, "hwp"),
		IMAGE(R.drawable.sp_filter_file_image, "png", "jpg", "gif"),
		MEDIA(R.drawable.sp_filter_file_media, "avi", "mov", "mp4"),
		PPT(R.drawable.sp_filter_file_ppt, "ppt", "pptx"),
		XLS(R.drawable.sp_filter_file_excel, "xls", "xlsx"),
		ZIP(R.drawable.sp_filter_file_zip, "zip"),
		ETC(R.drawable.sp_filter_file_etc, "0xFF"),
		TXT(R.drawable.sp_filter_file_txt, "txt");
		private int m_nRes;
		private String[] m_strExtention;

		private FileType(int nRes, String... strExtention) {
			m_nRes = nRes;
			m_strExtention = strExtention;
		}

		public static FileType valueOfExtention(String fileExt) {
			if (!TextUtils.isEmpty(fileExt)) {
				fileExt = fileExt.toLowerCase(Locale.getDefault());
				for (FileType t : values()) {
					for (String s : t.m_strExtention) {
						if (fileExt.endsWith(s)) {
							return t;
						}
					}
				}
			}
			return ETC;
		}

		public int getRes() {
			return m_nRes;
		}
	}

	public static class ViewHolder {
		public LinearLayout m_llAttachLayout;
		public ImageView m_imgFileType;
		public TextView m_tvFileName;
		public TextView m_tvFileDescription;
		public TextView m_tvFileAuthorName;
		public ImageView m_imgFavorite;
		public ImageView m_imgMore;
	}

	public SpFileListAdapter(FragmentActivity context, List<SpAttachVO> data) {
		this.context = context;
		m_data = data;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			int templeteId = R.layout.list_item_squareplus_file;
			convertView = LayoutInflater.from(parent.getContext()).inflate(templeteId, null, false);
			holder = new ViewHolder();
			holder.m_llAttachLayout = (LinearLayout) convertView.findViewById(R.id.ID_LL_SP_ATTACH_CASE);
			holder.m_imgFileType = (ImageView) convertView.findViewById(R.id.ID_IMG_FILE_TYPE);
			holder.m_tvFileName = (TextView) convertView.findViewById(R.id.ID_TV_FILENAME);
			holder.m_tvFileDescription = (TextView) convertView.findViewById(R.id.ID_TV_DESCRIPTION);
			holder.m_tvFileAuthorName = (TextView) convertView.findViewById(R.id.ID_TV_AUTHORNAME);
			holder.m_imgFavorite = (ImageView) convertView.findViewById(R.id.ID_BTN_FAVORITE);
			holder.m_imgMore = (ImageView) convertView.findViewById(R.id.ID_BTN_MORE);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (getItem(position).getAttachId().equals("isEmpty")) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_blanklist, parent, false);
			return convertView;
		}

		holder.m_imgFileType.setImageResource(FileType.valueOfExtention(getItem(position).getFileExt()).getRes());
		holder.m_tvFileName.setText(getItem(position).getFileName() + "." + getItem(position).getFileExt());
		holder.m_tvFileDescription.setText(MainModel.getInstance().readableFileSize(getItem(position).getFileSize())
				+ ", " + sdf.format(getItem(position).getCreateDate()));
		holder.m_tvFileAuthorName.setText(getItem(position).getAuthorName());

		if (getItem(position).isFavorite()) {
			holder.m_imgFavorite.setImageResource(R.drawable.sp_cont_bookmark_on);
		} else {
			holder.m_imgFavorite.setImageResource(R.drawable.sp_cont_bookmark_set);
		}

		// 즐겨찾기 링크
		holder.m_imgFavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final ImageView imageView = (ImageView)v;
				SpAttachCallBack spSquareCallBack = new SpAttachCallBack(context, SpAttachVO.class){
					@Override
					public void callback(String url, JSONObject json, AjaxStatus status) {
						super.callback(url, json, status);
						setItem(position, this.item);

						if (getItem(position).isFavorite()) {
							imageView.setImageResource(R.drawable.sp_cont_bookmark_on);
						} else {
							imageView.setImageResource(R.drawable.sp_cont_bookmark_set);
						}
					}
				};

				{ // API명
					HashMap<String,String> params = new HashMap<>();
					params.put("squareId", getItem(position).getSquareId());
					params.put("attachType", SpSquareConst.FAVORITE_TARGET_ATTACH);
					params.put("attachId", getItem(position).getAttachId());

					if (getItem(position).isFavorite()) {
						params.put("favoriteFlag", SpSquareConst.FALSE_CH);		// 1은 활성화
					} else {
						params.put("favoriteFlag", SpSquareConst.TRUE_CH);		// 1은 활성화
					}

					new ApiLoaderEx<>(new SpAddFavoriteAttach(context), context, spSquareCallBack, params).execute();
				}
			}
		});
/*
		if (HMGWServiceHelper.userId.equals(getItem(position).getAuthorId())) {
			holder.m_imgMore.setVisibility(View.VISIBLE);
			holder.m_imgMore.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					PopupMenu popupMenu = new PopupMenu(context, holder.m_imgMore);
					popupMenu.getMenuInflater().inflate(
							R.menu.squareplus_attach_list, popupMenu.getMenu());
					popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem item) {
							switch (item.getItemId()) {
							case R.id.ID_ATTACH_DELETE:
								Debug.trace("R.id.ID_ATTACH_DELETE");
								SpAttachCallBack spAttachCallBack = new SpAttachCallBack(context, SpAttachVO.class){
									@Override
									public void callback(String url, JSONObject json, AjaxStatus status) {
										super.callback(url, json, status);
										deleteItem(position);
										notifyDataSetChanged();
									}
								};

								{ // 첨부에 즐겨찾기를 한다.
									HashMap<String,String> params = new HashMap<>();
									params.put("attachId", getItem(position).getAttachId());	// 첨부id
									new ApiLoaderEx<>(new SpDeleteAttachFile(context), context, spAttachCallBack, params).execute();
								}

								break;
							}
							return true;
						}
					});
					popupMenu.show();
				}
			});
		} else {
			holder.m_imgMore.setVisibility(View.INVISIBLE);
		}
*/
		// 목록의 마지막인 경우엔 background를 제거하여 border line을 제거한다.
		if (getCount() == position + 1) {
			holder.m_llAttachLayout.setBackground(null);
		} else {
			holder.m_llAttachLayout.setBackgroundResource(R.drawable.style_squarelist_background);
		}

		return convertView;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SpAttachVO getItem(int position) {
		return m_data.get(position);
	}

	public SpAttachVO setItem(int position, SpAttachVO spAttachVO) {
		return m_data.set(position, spAttachVO);
	}

	public SpAttachVO deleteItem(int position) {
		return m_data.remove(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
