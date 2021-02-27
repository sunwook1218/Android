package com.hs.mobile.gw.view;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener.ChangeType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.FileType;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AttachFile extends LinearLayout {
	public interface IAttachFileDeleteListener {
		void onDeletedAttachFile(AttachListItemVO item);
	}

	private ImageView imgFile;
	private TextView tvFileName;
	private TextView tvFileInfo;
	private ImageButton btnBookmark;
	private ImageButton btnMore;

	public AttachFile(Context c) {
		super(c);
		LayoutInflater.from(c).inflate(R.layout.square_contents_detail_file_item, this);
		imgFile = (ImageView) findViewById(R.id.ID_IMG_FILE);
		tvFileName = (TextView) findViewById(R.id.ID_TV_FILE_NAME);
		tvFileInfo = (TextView) findViewById(R.id.ID_TV_FILE_INFO);
		btnBookmark = (ImageButton) findViewById(R.id.ID_BTN_FILE_BOOKMARK);
		btnMore = (ImageButton) findViewById(R.id.ID_BTN_FILE_MORE);
	}

	public void setData(final MainContentsListItemVO mainContentsListItem, final AttachListItemVO attachListItem,
			final IBookmarkAndOptionViewListener optionListener, final IAttachFileDeleteListener listener) {		
		imgFile.setImageResource(FileType.valueOfExtention(attachListItem.fileExt).getRes());
		tvFileName.setText(attachListItem.fileName + "." + attachListItem.fileExt);
		tvFileInfo.setText(getResources().getString(R.string.label_square_contents_detail_file_info_format,
				MainModel.getInstance().readableFileSize(attachListItem.fileSize), attachListItem.fileExt));
		btnBookmark.setSelected(attachListItem.favoriteFlag);
		btnBookmark.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				optionListener.onFavoriteClick(attachListItem.squareId, attachListItem.attachId, FavoriteContentsType.SINGLE_FILE,
						!btnBookmark.isSelected(), new SquareDefaultCallback() {
							public void onResponse(String strRet) {
								super.onResponse(strRet);
								if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
									mainContentsListItem.attachList.get(mainContentsListItem.attachList.indexOf(attachListItem)).favoriteFlag = !btnBookmark
											.isSelected();
									MainModel.getInstance().notifyChanged(mainContentsListItem, ChangeType.MODIFY);
								}
							}
						});
			}
		});
		if (!TextUtils.equals(mainContentsListItem.authorId, HMGWServiceHelper.userId)) {
			btnMore.setVisibility(View.GONE);
		} else {
			btnMore.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Builder builder = new AlertDialog.Builder(getContext());
					builder.setItems(new String[] { getResources().getString(R.string.label_square_contents_option_delete) },
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									switch (which) {
									// 삭제
									case 0:
										MainModel.getInstance().deleteAttachFile((Activity) getContext(), attachListItem, listener);
										break;
									}
								}
							});
					builder.show();
				}
			});
		}
	}
}