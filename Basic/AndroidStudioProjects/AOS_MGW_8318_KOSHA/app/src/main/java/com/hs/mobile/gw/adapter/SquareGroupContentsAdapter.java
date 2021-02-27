package com.hs.mobile.gw.adapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.MainModel.IChangeContentsListener.ChangeType;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.ContentsMemberListItemVO;
import com.hs.mobile.gw.openapi.square.vo.IContentsListItem;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareContentsType;
import com.hs.mobile.gw.openapi.square.vo.SystemDateVO;
import com.hs.mobile.gw.openapi.square.vo.SystemNewMessageLabel;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PixelUtils;
import com.hs.mobile.gw.view.AttachFile;
import com.hs.mobile.gw.view.BookmarkAndOptionView;
import com.hs.mobile.gw.view.AttachFile.IAttachFileDeleteListener;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.FileAttachArea;
import com.hs.mobile.gw.view.FileAttachArea.IFileAttachClickListener;
import com.hs.mobile.gw.view.NameAndDepartmentView;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SquareGroupContentsAdapter extends BaseAdapter {
	public interface ISquareGroupContentItemListener {
		void onItemClick(MainContentsListItemVO item, ButtonType t);
	}

	public enum ButtonType {
		OPINION, FILE
	}

	private DateFormat m_dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());

	public static abstract class ViewHolder {
	}

	public static class TopicViewHolder extends ViewHolder {
		public TextView tvTitle;
		public NameAndDepartmentView nameAndDepartmentView;
		public FileAttachArea fileAttachArea;
		public TextView tvDate;
		public TextView btnFile;
		public TextView tvBody;
		public BookmarkAndOptionView bookMarkAndOptionView;
		public TextView btnOpinion;

		public TopicViewHolder(View convertView) {
			tvTitle = (TextView) convertView.findViewById(R.id.ID_TV_TITLE);
			fileAttachArea = (FileAttachArea) convertView.findViewById(R.id.ID_LAY_FILE_ATTACH_AREA);
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookMarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			btnFile = (TextView) convertView.findViewById(R.id.ID_BTN_FILE);
			btnOpinion = (TextView) convertView.findViewById(R.id.ID_BTN_OPINION);
		}
	}

	public static class FileUploadViewHolder extends ViewHolder {
		public NameAndDepartmentView nameAndDepartmentView;
		public TextView tvFileName;
		public FileAttachArea fileAttachArea;
		public TextView tvDate;
		public TextView btnFile;
		public BookmarkAndOptionView bookMarkAndOptionView;
		public TextView btnOpinion;

		public FileUploadViewHolder(View convertView) {
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookMarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvFileName = (TextView) convertView.findViewById(R.id.ID_TV_FILE_NAME);
			fileAttachArea = (FileAttachArea) convertView.findViewById(R.id.ID_LAY_FILE_ATTACH_AREA);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			btnFile = (TextView) convertView.findViewById(R.id.ID_BTN_FILE);
			btnOpinion = (TextView) convertView.findViewById(R.id.ID_BTN_OPINION);
		}

	}

	public static class OpinionViewHolder extends ViewHolder {
		public NameAndDepartmentView nameAndDepartmentView;
		public TextView tvBody;
		public TextView tvDate;
		public BookmarkAndOptionView bookMarkAndOptionView;
		public LinearLayout fileAttachLayout;

		public OpinionViewHolder(View convertView) {
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookMarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			fileAttachLayout = (LinearLayout) convertView.findViewById(R.id.ID_LAY_L_FILE_ATTACH_AREA);
		}
	}

	public static class CommandViewHolder extends ViewHolder {
		public NameAndDepartmentView nameAndDepartmentView;
		public TextView tvBody;
		public TextView tvDate;
		public FileAttachArea fileAttachArea;
		public TextView tvGroupMembers;
		public TextView btnFile;
		public TextView tvTitle;
		public BookmarkAndOptionView bookMarkAndOptionView;
		public TextView btnOpinion;

		public CommandViewHolder(View convertView) {
			tvTitle = (TextView) convertView.findViewById(R.id.ID_TV_TITLE);
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookMarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			fileAttachArea = (FileAttachArea) convertView.findViewById(R.id.ID_LAY_FILE_ATTACH_AREA);
			tvGroupMembers = (TextView) convertView.findViewById(R.id.ID_TV_MEMBERS);
			btnFile = (TextView) convertView.findViewById(R.id.ID_BTN_FILE);
			btnOpinion = (TextView) convertView.findViewById(R.id.ID_BTN_OPINION);
		}

	}

	public static class MessageViewHolder extends ViewHolder {
		public MessageViewHolder(View convertView) {
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookMarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
		}

		public BookmarkAndOptionView bookMarkAndOptionView;
		public NameAndDepartmentView nameAndDepartmentView;
		public TextView tvBody;
		public TextView tvDate;

	}

	public static class SystemDateViewHolder extends ViewHolder {
		public SystemDateViewHolder(View convertView) {
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_SYSTEM_DATE);
		}

		public TextView tvDate;
	}

	public static class SystemMessageViewHolder extends ViewHolder {
		public TextView tvTitle;
		public TextView tvBody;

		public SystemMessageViewHolder(View convertView) {
			tvTitle = (TextView) convertView.findViewById(R.id.ID_TV_SYSTEM_MESSAGE_TITLE);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_SYSTEM_MESSAGE_BODY);
		}
	}

	public static class GroupInfoSystemMessageViewHolder extends ViewHolder {
		public TextView tvGroupTitle;
		public TextView tvGroupMessage;
		public TextView tvGroupDescription;
		public TextView tvDate;
		public TextView tvGroupMembers;

		public GroupInfoSystemMessageViewHolder(View convertView) {
			tvGroupTitle = (TextView) convertView.findViewById(R.id.ID_TV_GROUP_TITLE);
			tvGroupMessage = (TextView) convertView.findViewById(R.id.ID_TV_GROUP_MESSAGE);
			tvGroupDescription = (TextView) convertView.findViewById(R.id.ID_TV_GROUP_DESCRIPTION);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			tvGroupMembers = (TextView) convertView.findViewById(R.id.ID_TV_MEMBERS);
		}
	}

	public static class SystemNewMessageLabelViewHolder extends ViewHolder {

	}

	private ArrayList<IContentsListItem> m_data;
	private LayoutInflater m_inflater;
	private IBookmarkAndOptionViewListener m_bookMarkAndOptionViewListener;
	private ISquareGroupContentItemListener m_listener;
	private Status m_status;
	private IFileAttachClickListener m_fileClickListener;

	public SquareGroupContentsAdapter(Status status, ArrayList<IContentsListItem> data, ISquareGroupContentItemListener listener,
			IFileAttachClickListener fileClickListener) {
		m_data = data;
		m_listener = listener;
		m_status = status;
		m_fileClickListener = fileClickListener;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public IContentsListItem getItem(int position) {
		return m_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return SquareContentsType.values().length + 3;
	}

	@Override
	public int getItemViewType(int position) {
		switch (m_data.get(position).getObjectType()) {
		case CONTENTS:
			MainContentsListItemVO contents = ((MainContentsListItemVO) m_data.get(position));
			if (contents.contentsType != null) {
				return contents.contentsType.ordinal();
			} else {
				return -1;
			}
		case SYSTEM_DATE:
			return SystemDateVO.CONTENTS_TYPE;
		case SYSTEM_NEW_MESSAGE_LABEL:
			return SystemNewMessageLabel.CONTENTS_TYPE;
		}
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			if (m_inflater == null) {
				m_inflater = LayoutInflater.from(parent.getContext());
			}
			switch (getItemViewType(position)) {
			case MainContentsListItemVO.TOPIC:
				convertView = m_inflater.inflate(R.layout.list_item_square_topic, null);
				holder = new TopicViewHolder(convertView);
				break;
			case MainContentsListItemVO.MESSAGE:
				convertView = m_inflater.inflate(R.layout.list_item_square_message, null);
				holder = new MessageViewHolder(convertView);
				break;
			case MainContentsListItemVO.FILE_UPLOAD:
				convertView = m_inflater.inflate(R.layout.list_item_square_file_upload, null);
				holder = new FileUploadViewHolder(convertView);
				break;
			case MainContentsListItemVO.OPINION:
				convertView = m_inflater.inflate(R.layout.list_item_square_opinion, null);
				holder = new OpinionViewHolder(convertView);
				break;
			case MainContentsListItemVO.SYSTEM_MESSAGE:
				convertView = m_inflater.inflate(R.layout.list_item_square_system_message, null);
				holder = new SystemMessageViewHolder(convertView);
				break;
			case MainContentsListItemVO.COMMAND:
				convertView = m_inflater.inflate(R.layout.list_item_square_command, null);
				holder = new CommandViewHolder(convertView);
				break;
			case MainContentsListItemVO.GROUPINFO_SYSTEM_MESSAGE:
				convertView = m_inflater.inflate(R.layout.list_item_square_group_info_system_message, null);
				holder = new GroupInfoSystemMessageViewHolder(convertView);
				break;
			case MainContentsListItemVO.USER_SYSTEM_MESSAGE:
				convertView = m_inflater.inflate(R.layout.list_item_square_message, null);
				holder = new MessageViewHolder(convertView);
				break;
			case SystemDateVO.CONTENTS_TYPE:
				convertView = m_inflater.inflate(R.layout.list_item_square_system_date, null);
				holder = new SystemDateViewHolder(convertView);
				break;
			case SystemNewMessageLabel.CONTENTS_TYPE:
				convertView = m_inflater.inflate(R.layout.list_item_square_system_new_message, null);
				holder = new SystemNewMessageLabelViewHolder();
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		switch (getItemViewType(position)) {
		case MainContentsListItemVO.TOPIC: {
			final MainContentsListItemVO item = (MainContentsListItemVO) getItem(position);
			TopicViewHolder h = (TopicViewHolder) holder;
			h.tvTitle.setText(item.title);
			h.fileAttachArea.setFileAttachInfo(item.attachList);
			h.fileAttachArea.setFileClickListener(m_fileClickListener);
			if (!TextUtils.isEmpty(item.body)) {
				h.tvBody.setText(item.body);
			} else {
				h.tvBody.setVisibility(View.GONE);
			}
			h.nameAndDepartmentView.setData(item.authorName, item.authorDeptName, item.authorPositionName);
			Date date = new Date(item.createDate);
			h.tvDate.setText(m_dateFormat.format(date));
			setFileCount(parent.getContext(), item, h.btnFile);
			h.bookMarkAndOptionView.setData(m_status, item);
			h.bookMarkAndOptionView.setBookmarkAndOptionViewListener(m_bookMarkAndOptionViewListener);
			h.btnOpinion.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_listener.onItemClick(item, ButtonType.OPINION);
				}
			});
			h.btnFile.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_listener.onItemClick(item, ButtonType.FILE);
				}
			});
		}
			break;
		case MainContentsListItemVO.USER_SYSTEM_MESSAGE:
		case MainContentsListItemVO.MESSAGE: {
			MainContentsListItemVO item = (MainContentsListItemVO) getItem(position);
			MessageViewHolder h = (MessageViewHolder) holder;
			h.nameAndDepartmentView.setData(item.authorName, item.authorDeptName, item.authorPositionName);
			h.tvBody.setText(item.body);
			Date date = new Date(item.createDate);
			h.tvDate.setText(m_dateFormat.format(date));
			h.bookMarkAndOptionView.setData(m_status, item);
			h.bookMarkAndOptionView.setBookmarkAndOptionViewListener(m_bookMarkAndOptionViewListener);
		}
			break;
		case MainContentsListItemVO.FILE_UPLOAD: {
			final MainContentsListItemVO item = (MainContentsListItemVO) getItem(position);
			FileUploadViewHolder h = (FileUploadViewHolder) holder;
			h.nameAndDepartmentView.setData(item.authorName, item.authorDeptName, item.authorPositionName);
			h.tvFileName.setText(item.title);
			h.fileAttachArea.setFileAttachInfo(item.attachList);
			h.fileAttachArea.setFileClickListener(m_fileClickListener);
			Date date = new Date(item.createDate);
			h.tvDate.setText(m_dateFormat.format(date));
			setFileCount(parent.getContext(), item, h.btnFile);
			h.bookMarkAndOptionView.setData(m_status, item);
			h.bookMarkAndOptionView.setBookmarkAndOptionViewListener(m_bookMarkAndOptionViewListener);
			h.btnOpinion.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_listener.onItemClick(item, ButtonType.OPINION);
				}
			});
			h.btnFile.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_listener.onItemClick(item, ButtonType.FILE);
				}
			});
		}
			break;
		case MainContentsListItemVO.OPINION: {
			// TODO 의견 파일 처리 할것.
			MainContentsListItemVO item = (MainContentsListItemVO) getItem(position);
			OpinionViewHolder h = (OpinionViewHolder) holder;
			h.nameAndDepartmentView.setData(item.authorName, item.authorDeptName, item.authorPositionName);
			h.tvBody.setText(item.body);
			Date date = new Date(item.createDate);
			h.tvDate.setText(m_dateFormat.format(date));
			h.bookMarkAndOptionView.setData(m_status, item);
			h.bookMarkAndOptionView.setBookmarkAndOptionViewListener(m_bookMarkAndOptionViewListener);
			setAttachFiles(item, h.fileAttachLayout, convertView.getContext(), m_bookMarkAndOptionViewListener);
		}
			break;
		case MainContentsListItemVO.SYSTEM_MESSAGE: {
			MainContentsListItemVO item = (MainContentsListItemVO) getItem(position);
			SystemMessageViewHolder h = (SystemMessageViewHolder) holder;
			h.tvTitle.setText(item.title);
			h.tvBody.setText(parent.getResources().getString(R.string.label_square_opinion_add_msg));
		}
			break;
		case MainContentsListItemVO.COMMAND: {
			final MainContentsListItemVO item = (MainContentsListItemVO) getItem(position);
			CommandViewHolder h = (CommandViewHolder) holder;
			h.nameAndDepartmentView.setData(item.authorName, item.authorDeptName, item.authorPositionName);
			h.tvTitle.setText(item.title);
			if (!TextUtils.isEmpty(item.body)) {
				h.tvBody.setText(Html.fromHtml(item.body));
			} else {
				h.tvBody.setVisibility(View.GONE);
			}
			Date date = new Date(item.createDate);
			h.tvDate.setText(m_dateFormat.format(date));
			h.tvGroupMembers.setText(Html.fromHtml(makeMemberString(item.contentsMemberList, false)));
			h.fileAttachArea.setFileAttachInfo(item.attachList);
			h.fileAttachArea.setFileClickListener(m_fileClickListener);
			setFileCount(parent.getContext(), item, h.btnFile);
			h.bookMarkAndOptionView.setData(m_status, item);
			h.bookMarkAndOptionView.setBookmarkAndOptionViewListener(m_bookMarkAndOptionViewListener);
			h.btnOpinion.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_listener.onItemClick(item, ButtonType.OPINION);
				}
			});
			h.btnFile.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_listener.onItemClick(item, ButtonType.FILE);
				}
			});
		}
			break;
		case MainContentsListItemVO.GROUPINFO_SYSTEM_MESSAGE: {
			MainContentsListItemVO item = (MainContentsListItemVO) getItem(position);
			GroupInfoSystemMessageViewHolder h = (GroupInfoSystemMessageViewHolder) holder;
			h.tvGroupTitle.setText(item.title);
			h.tvGroupDescription.setVisibility(TextUtils.isEmpty(item.body) ? View.GONE : View.VISIBLE);
			h.tvGroupDescription.setText(item.body);
			h.tvGroupMembers.setText(Html.fromHtml(makeMemberString(item.contentsMemberList, true)));
			Date date = new Date(item.createDate);
			h.tvDate.setText(m_dateFormat.format(date));
		}
			break;
		case SystemDateVO.CONTENTS_TYPE:
			((SystemDateViewHolder) holder).tvDate.setText(((SystemDateVO) getItem(position)).getDateString());
			break;
		case SystemNewMessageLabel.CONTENTS_TYPE:
			// Do nothing
			break;
		}
		if(convertView instanceof ViewGroup)
		{
			AndroidUtils.clearFocus((ViewGroup)convertView);
		}
		return convertView;
	}

	private void setFileCount(Context context, MainContentsListItemVO item, TextView tv) {
		if (item.attachList == null || item.attachList.size() == 0) {
			tv.setVisibility(View.GONE);
		} else {
			tv.setVisibility(View.VISIBLE);
			tv.setText(context.getString(R.string.label_square_attach_file, item.attachList.size()));
		}
	}
	
	public void setAttachFiles(final MainContentsListItemVO mainContentsListItem, final LinearLayout container, Context c,
			IBookmarkAndOptionViewListener favoriteAddable) {
		container.removeAllViews();
		if (mainContentsListItem == null || mainContentsListItem.attachList == null) {
			return;
		}
		for (int i = 0; i < mainContentsListItem.attachList.size(); ++i) {
			final AttachFile attachFile = new AttachFile(c);
			attachFile.setData(mainContentsListItem, mainContentsListItem.attachList.get(i), favoriteAddable,
					new IAttachFileDeleteListener() {
						@Override
						public void onDeletedAttachFile(AttachListItemVO item) {
							container.removeView(attachFile);
							mainContentsListItem.attachList.remove(item);
							// 첨부의 삭제는 ADD
							MainModel.getInstance().notifyChanged(mainContentsListItem, ChangeType.ADD);
						}
					});
			container.addView(attachFile, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			LinearLayout.LayoutParams p = (android.widget.LinearLayout.LayoutParams) attachFile.getLayoutParams();
			p.topMargin = (int) PixelUtils.getDip(c.getResources(), 5);
			p.bottomMargin = (int) PixelUtils.getDip(c.getResources(), 5);
			if (i != 0) {
				p.topMargin = 0;
			}
			attachFile.setLayoutParams(p);
			final AttachListItemVO attachFileItem = mainContentsListItem.attachList.get(i);
			attachFile.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					m_fileClickListener.onFileClick(attachFileItem);
				}
			});
		}
	}

	private String makeMemberString(ArrayList<ContentsMemberListItemVO> memberList, boolean bNameBold) {
		if (memberList == null) {
			return null;
		} else {
			StringBuilder sb = new StringBuilder();
			for (ContentsMemberListItemVO item : memberList) {
				if (bNameBold) {
					sb.append("<b>");
				}
				sb.append(item.memberName);
				if (bNameBold) {
					sb.append("</b>");
				}
				if (!TextUtils.isEmpty(item.deptName) && !TextUtils.isEmpty(item.positionName)) {
					sb.append('(');
					sb.append(item.deptName);
					sb.append('/');
					sb.append(item.positionName);
					sb.append(')');
				}
				sb.append(", ");
			}
			if (memberList.size() > 0) {
				if (sb.lastIndexOf(", ") != -1) {
					sb.replace(sb.lastIndexOf(", "), sb.length(), "");
				}
			}
			return sb.toString();
		}
	}

	public void setBookMarkAndOptionViewListener(IBookmarkAndOptionViewListener bookMarkAndOptionViewListener) {
		this.m_bookMarkAndOptionViewListener = bookMarkAndOptionViewListener;
	}
}
