package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.Locale;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;

public class SpFileView extends LinearLayout {

	public enum FileType {
		DOC(R.drawable.sp_file_doc, "doc", "docx"),
		HWP(R.drawable.sp_file_hwp, "hwp"),
		IMAGE(R.drawable.sp_file_image, "png", "jpg", "gif"),
		MEDIA(R.drawable.sp_file_media, "avi", "mov", "mp4"),
		PPT(R.drawable.sp_file_ppt, "ppt", "pptx"),
		XLS(R.drawable.sp_file_xls, "xls", "xlsx"),
		ZIP(R.drawable.sp_file_zip, "zip"),
		ETC(R.drawable.sp_file_etc, "0xFF"),
		TXT(R.drawable.sp_file_txt, "txt");
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

	private SpAttachVO m_data;

	public SpFileView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.sp_fileview, this);
	}

	public SpFileView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.sp_fileview, this);
	}

	public SpFileView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.sp_fileview, this);
	}

	public void setData(final SpAttachVO item, final ISpClickListener listener) {
		m_data = item;
		((ImageView) findViewById(R.id.ID_IMG_FILE_TYPE))
				.setImageResource(FileType.valueOfExtention(m_data.getFileExt()).getRes());
		((TextView) findViewById(R.id.ID_TV_FILE_NAME)).setText(m_data.getFileName()
				.concat(".").concat(m_data.getFileExt()));
		((TextView) findViewById(R.id.ID_TV_FILE_SIZE)).setText(MainModel
				.getInstance().readableFileSize(m_data.getFileSize()));

		final ImageButton btnFavorite = (ImageButton) findViewById(R.id.ID_BTN_FAVORITE);
		btnFavorite.setSelected("1".equals(m_data.getFavoriteFlag())?true:false);

		btnFavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onFavoriteClick(m_data, new IFavoriteCallbak() {
					@Override
					public void onResponse(SpAttachVO item) {
						btnFavorite.setSelected("1".equals(item.getFavoriteFlag())?true:false);
						m_data = item;
					}

					@Override
					public void onResponse(SpContentsVO item) {
						// Nothing to do..

					}
				});
			}
		});
		
		final ImageButton btnMore = (ImageButton) findViewById(R.id.ID_BTN_MORE);
		if(TextUtils.equals(HMGWServiceHelper.userId, m_data.getAuthorId())){
			btnMore.setVisibility(View.VISIBLE);
			btnMore.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					PopupMenu popupMenu = new PopupMenu(getContext(), btnMore);
					popupMenu.getMenuInflater().inflate(
							R.menu.squareplus_attach_list, popupMenu.getMenu());
					popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem item) {
							switch (item.getItemId()) {
							case R.id.ID_ATTACH_DELETE:
								listener.onDeleteClick(m_data);
								setVisibility(GONE);
								break;
							}
							return true;
						}
					});
					popupMenu.show();
				}
			});
		}else{
			btnMore.setVisibility(View.INVISIBLE);
		}

		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onFileView(m_data);
			}
		});

		if (m_data.isDeleteShow()) {
			ImageButton imgDelete = (ImageButton) findViewById(R.id.ID_BTN_DELETE);
			imgDelete.setVisibility(View.VISIBLE);
			imgDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SpFileView.this.removeAllViews();
					listener.onDeleteClick(item);
				}
			});

			// 즐겨찾기와 더보기 버튼은 제거시킨다.
			((TextView)findViewById(R.id.ID_TV_SEPERATOR)).setVisibility(View.GONE);
			((TextView)findViewById(R.id.ID_TV_FILE_SIZE)).setVisibility(View.GONE);
			((ImageButton) findViewById(R.id.ID_BTN_FAVORITE)).setVisibility(View.GONE);
			btnMore.setVisibility(View.GONE);
		}
	}

}
