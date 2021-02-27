package com.hs.mobile.gw.view;

import org.json.JSONObject;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener.ChangeType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareContentsType;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class BookmarkAndOptionView extends LinearLayout implements OnClickListener {
	public interface IBookmarkAndOptionViewListener {
		void onDelete(MainContentsListItemVO item);

		void onCopy(MainContentsListItemVO item);

		void onModify(MainContentsListItemVO item);

		void onFavoriteClick(String squareId, String contentsId, FavoriteContentsType favorityType, boolean flag,
				SquareDefaultCallback squareDefaultCallback);
	}

	IBookmarkAndOptionViewListener m_listener;
	private ImageButton m_btnBookMark;
	private LinearLayout m_btnOptionLayout;
	private MainContentsListItemVO m_data;
	private ImageButton m_btnOption;
	private Status m_status;

	public ImageButton getBtnBookMark() {
		return m_btnBookMark;
	}

	public LinearLayout getBtnOption() {
		return m_btnOptionLayout;
	}

	public BookmarkAndOptionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public BookmarkAndOptionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public BookmarkAndOptionView(Context context) {
		super(context);
		initView();
	}

	public void setBookmarkAndOptionViewListener(IBookmarkAndOptionViewListener listener) {
		m_listener = listener;
	}

	private void initView() {
		LayoutInflater.from(getContext()).inflate(R.layout.bookmark_and_option_layout, this);
		m_btnBookMark = (ImageButton) findViewById(R.id.ID_BTN_BOOKMARK);
		m_btnOptionLayout = (LinearLayout) findViewById(R.id.ID_BTN_MORE_OPTION_LAYOUT);
		m_btnOption = (ImageButton) findViewById(R.id.ID_BTN_MORE_OPTION);
		m_btnOption.setFocusable(false);
		m_btnBookMark.setFocusable(false);
		m_btnOptionLayout.setFocusable(false);
		m_btnBookMark.setOnClickListener(this);
		m_btnOptionLayout.setOnClickListener(this);
	}

	public void setData(Status status, MainContentsListItemVO item) {
		if (item == null ) {
			return;
		}
		m_data = item;

		m_btnBookMark.setSelected(item.favoriteFlag);
		m_status = status;
		if (m_data.contentsType != null &&(m_data.contentsType.equals(SquareContentsType.USER_SYSTEM_INFO)
				|| m_data.contentsType.equals(SquareContentsType.SYSTEM_MESSAGE))) {
			m_btnBookMark.setVisibility(INVISIBLE);
			m_btnOption.setVisibility(INVISIBLE);
		} else {
			m_btnOption.setVisibility(m_status == Status.IN_PROGRESS ? View.VISIBLE : View.GONE);
		}
	}
	public void setData(com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status status, MainContentsListItemVO item) {
		if (item == null ) {
			return;
		}
		m_data = item;
		
		m_btnBookMark.setSelected(item.favoriteFlag);
		m_status = Status.valueOf(status.name());
		if (m_data.contentsType != null &&(m_data.contentsType.equals(SquareContentsType.USER_SYSTEM_INFO)
				|| m_data.contentsType.equals(SquareContentsType.SYSTEM_MESSAGE))) {
			m_btnBookMark.setVisibility(INVISIBLE);
			m_btnOption.setVisibility(INVISIBLE);
		} else {
			m_btnOption.setVisibility(m_status == Status.IN_PROGRESS ? View.VISIBLE : View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		if (m_data == null) {
			return;
		}

		switch (v.getId()) {
		case R.id.ID_BTN_BOOKMARK:
			if (m_listener != null) {
				if (m_data.contentsType == SquareContentsType.FILE_UPLOAD) {

				}
				m_listener.onFavoriteClick(m_data.squareId, m_data.contentsId,
						FavoriteContentsType.findObjectByContentsType(m_data.contentsType), !m_btnBookMark.isSelected(),
						new SquareDefaultCallback() {
							@Override
							public void onResponse(String strRet) {
								super.onResponse(strRet);
								if (responseHead.resultCode == SUCCESS) {
									JSONObject optJSONObject = getJsonObject().optJSONObject("responseData");
									if (optJSONObject != null) {
										MainContentsListItemVO newItem = new MainContentsListItemVO(optJSONObject);
										m_btnBookMark.setSelected(newItem.favoriteFlag);
										MainModel.getInstance().notifyChanged(newItem, ChangeType.MODIFY);
									}
								}
							}
						});
			}
			break;
		case R.id.ID_BTN_MORE_OPTION_LAYOUT:
			// 쓴사람과 누른사람이 같을경우와 그렇지 않을 경우 분기
			if (m_listener != null) {
				Builder builder = new AlertDialog.Builder(getContext());
				// 파일 타입의 경우 삭제만 띄운다.
				if (TextUtils.equals(m_data.authorId, HMGWServiceHelper.userId)
						&& m_data.contentsType.equals(SquareContentsType.FILE_UPLOAD)) {
					builder.setItems(new String[] { 
							getResources().getString(R.string.label_square_contents_option_file_copy),
							getResources().getString(R.string.label_square_contents_option_delete),
							},
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									switch (which) {
									// 복사:
									case 0:
										m_listener.onCopy(m_data);
										break;
									// 삭제
									case 1:
										m_listener.onDelete(m_data);
										break;
									}
								}
							});
				} 
				else if (TextUtils.equals(m_data.authorId, HMGWServiceHelper.userId)) {
					builder.setItems(
							new String[] { getResources().getString(R.string.label_square_contents_option_modify),
									getResources().getString(R.string.label_square_contents_option_copy),
									getResources().getString(R.string.label_square_contents_option_delete), },
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									switch (which) {
									// 수정
									case 0:
										m_listener.onModify(m_data);
										break;
									// 복사
									case 1:
										m_listener.onCopy(m_data);
										break;
									// 삭제
									case 2:
										m_listener.onDelete(m_data);
										break;
									}
								}
							});
				} else {
					builder.setItems(new String[] {
							m_data.contentsType == SquareContentsType.FILE_UPLOAD ? 
									getResources().getString(R.string.label_square_contents_option_file_copy):getResources().getString(R.string.label_square_contents_option_copy) 
							},
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									switch (which) {
									// 복사
									case 0:
										m_listener.onCopy(m_data);
										break;
									}
								}
							});
				}
				builder.show();
			}
		}
	}
}
