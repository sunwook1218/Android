package com.hs.mobile.gw.view;

import java.io.File;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.FileType;
import com.hs.mobile.gw.util.Debug;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FileItemView extends LinearLayout {
	public interface IFileItemViewDeleteListener {

		void onDelete(View v);
	}

	private IFileItemViewDeleteListener m_listener;
	private ImageView m_imgFile;
	private TextView m_tvFileName;
	private TextView m_tvFileInfo;
	private CheckBox m_cbFile;
	private String m_strFilePath;
	private ImageButton m_btnFileDelete;
	private AttachListItemVO m_data;

	public FileItemView(Context context) {
		super(context);
		LayoutInflater.from(getContext()).inflate(R.layout.file_attach_item, this);
		m_imgFile = (ImageView) findViewById(R.id.ID_IMG_FILE);
		m_tvFileName = (TextView) findViewById(R.id.ID_TV_FILE_NAME);
		m_tvFileInfo = (TextView) findViewById(R.id.ID_TV_FILE_INFO);
		m_cbFile = (CheckBox) findViewById(R.id.ID_CB_FILE);
		m_btnFileDelete = (ImageButton) findViewById(R.id.ID_BTN_FILE_DELETE);
	}

	public void setDeleteListener(IFileItemViewDeleteListener listener) {
		m_listener = listener;
	}

	public void setFilePath(String strFilePath) {
		Debug.trace(strFilePath);
		m_strFilePath = strFilePath;
		File file = new File(strFilePath);
		if (file.exists()) {
			String fileName = Uri.parse(strFilePath).getLastPathSegment();
			String fileExt = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			m_imgFile.setImageResource(FileType.valueOfExtention(fileExt).getRes());
			m_tvFileName.setText(fileName);
			m_tvFileInfo.setText(MainModel.getInstance().readableFileSize(file.length()));
			m_btnFileDelete.setVisibility(View.VISIBLE);
			m_cbFile.setVisibility(View.GONE);
			m_btnFileDelete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (m_listener != null) {
						m_listener.onDelete(FileItemView.this);
					}
				}
			});
		}
	}

	public void setData(AttachListItemVO item) {
		m_data = item;
		m_imgFile.setImageResource(FileType.valueOfExtention(item.fileExt).getRes());
		m_tvFileName.setText(item.fileName + "." + item.fileExt);
		m_tvFileInfo.setText(MainModel.getInstance().readableFileSize(item.fileSize));
		m_btnFileDelete.setVisibility(View.GONE);
		m_cbFile.setVisibility(View.VISIBLE);
		m_cbFile.setChecked(true);
	}

	public AttachListItemVO getData() {
		return m_data;
	}

	public String getFilePath() {
		return m_strFilePath;
	}

	public boolean isChecked() {
		return m_cbFile.isChecked();
	}

}
