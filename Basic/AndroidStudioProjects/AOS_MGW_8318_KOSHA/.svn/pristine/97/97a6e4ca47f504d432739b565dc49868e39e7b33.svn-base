package com.hs.mobile.gw.view;

import java.util.ArrayList;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.FileType;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FileAttachArea extends LinearLayout {
	public interface IFileAttachClickListener {
		void onFileClick(AttachListItemVO item);
	}

	public static final int[] FILE_LAYOUT_RES = new int[] { R.id.ID_LAY_L_ATTACH_FILE0, R.id.ID_LAY_L_ATTACH_FILE1,
			R.id.ID_LAY_L_ATTACH_FILE2, R.id.ID_LAY_L_ATTACH_FILE3 };
	public static final int[] FILE_IMAGE_RES = new int[] { R.id.ID_IMG_ATTACH_FILE0, R.id.ID_IMG_ATTACH_FILE1, R.id.ID_IMG_ATTACH_FILE2,
			R.id.ID_IMG_ATTACH_FILE3 };
	public static final int[] FILE_SIZE_RES = new int[] { R.id.ID_TV_ATTACH_FILE0, R.id.ID_TV_ATTACH_FILE1, R.id.ID_TV_ATTACH_FILE2,
			R.id.ID_TV_ATTACH_FILE3 };

	class FileObject {
		public FileObject(int i, int j, int k) {
			layout = (LinearLayout) findViewById(i);
			imgFileIcon = (ImageView) findViewById(j);
			tvSize = (TextView) findViewById(k);
			layout.setVisibility(View.GONE);
		}

		public LinearLayout layout;
		public ImageView imgFileIcon;
		public int nFileSize;
		public TextView tvSize;
	}

	private ArrayList<FileObject> m_fileList = new ArrayList<FileObject>();
	private ImageView m_btnMore;
	private IFileAttachClickListener m_listener;

	public FileAttachArea(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public void setFileClickListener(IFileAttachClickListener listener) {
		m_listener = listener;
	}

	private void initView() {
		LayoutInflater.from(getContext()).inflate(R.layout.file_attach_layout, this);
		for (int i = 0; i < FILE_LAYOUT_RES.length; ++i) {
			m_fileList.add(new FileObject(FILE_LAYOUT_RES[i], FILE_IMAGE_RES[i], FILE_SIZE_RES[i]));
		}
		m_btnMore = (ImageView) findViewById(R.id.ID_BTN_MORE);
		m_btnMore.setVisibility(GONE);
	}

	public FileAttachArea(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public FileAttachArea(Context context) {
		super(context);
		initView();
	}

	public void setFileAttachInfo(ArrayList<AttachListItemVO> attachList) {
		if (attachList == null) {
			setVisibility(GONE);
			return;
		} else {
			setVisibility(VISIBLE);
		}
		for (int i = 0; attachList.size() > i; ++i) {
			if (i > FILE_LAYOUT_RES.length - 1) {
				m_btnMore.setVisibility(VISIBLE);
				break;
			}
			m_fileList.get(i).layout.setVisibility(View.VISIBLE);
			m_fileList.get(i).tvSize.setText(MainModel.getInstance().readableFileSize(attachList.get(i).fileSize));
			m_fileList.get(i).imgFileIcon.setImageResource(FileType.valueOfExtention(attachList.get(i).fileExt).getRes());
			final AttachListItemVO attachListItem = attachList.get(i);
			m_fileList.get(i).layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_listener.onFileClick(attachListItem);
				}
			});
		}

	}
}
