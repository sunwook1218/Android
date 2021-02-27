package com.hs.mobile.gw.adapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener.ChangeType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment.CommandDetailHeader;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment.FileDetailHeader;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment.ISquareContentsDetailItem;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment.Participant;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment.Seperator;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment.SquareOpinion;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment.TopicDetailHeader;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.ContentsMemberListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO.OrderStatus;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.PixelUtils;
import com.hs.mobile.gw.view.AttachFile;
import com.hs.mobile.gw.view.AttachFile.IAttachFileDeleteListener;
import com.hs.mobile.gw.view.BookmarkAndOptionView;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.FileAttachArea.IFileAttachClickListener;
import com.hs.mobile.gw.view.NameAndDepartmentView;
import com.hs.mobile.gw.view.ParticipantView;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SquareDetailContentsAdapter extends BaseAdapter {
	public interface IWorkStatusChangeListener {

		void onMasterWorkStatusChange(View btnMasterWorkStatus, final MainContentsListItemVO item, boolean b, String strContentsId);

		void onWorkStatusChange(View btnWorkStatus, final MainContentsListItemVO item, boolean b, String strContentsId);

	}

	public IFileAttachClickListener m_fileClickListener;

	public class TopicDetailViewHolder extends ViewHolder {

		public LinearLayout fileAttachLayout;
		public TextView tvDate;
		public TextView tvBody;
		public TextView tvTitle;
		public BookmarkAndOptionView bookmarkAndOptionView;
		public NameAndDepartmentView nameAndDepartmentView;
		public ImageView imgProfile;

		public TopicDetailViewHolder(View convertView) {
			imgProfile = (ImageView) convertView.findViewById(R.id.ID_IMG_PROFILE);
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookmarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvTitle = (TextView) convertView.findViewById(R.id.ID_TV_TITLE);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			fileAttachLayout = (LinearLayout) convertView.findViewById(R.id.ID_LAY_L_FILE_ATTACH_AREA);
		}

		// public void setAttachFiles(String authorId, ArrayList<AttachListItem>
		// attachList, Context c, IBookmarkAndOptionViewListener addable) {
		// fileAttachLayout.removeAllViews();
		// if (attachList == null) {
		// return;
		// }
		// for (int i = 0; i < attachList.size(); ++i) {
		// AttachFile attachFile = new AttachFile(c);
		// attachFile.setData(authorId, attachList.get(i), addable);
		// fileAttachLayout.addView(attachFile,
		// LinearLayout.LayoutParams.MATCH_PARENT,
		// LinearLayout.LayoutParams.WRAP_CONTENT);
		// LinearLayout.LayoutParams p =
		// (android.widget.LinearLayout.LayoutParams)
		// attachFile.getLayoutParams();
		// p.topMargin = (int) PixelUtils.getDip(c.getResources(), 5);
		// p.bottomMargin = (int) PixelUtils.getDip(c.getResources(), 5);
		// if (i != 0) {
		// p.topMargin = 0;
		// }
		// attachFile.setLayoutParams(p);
		// }
		// }
	}

	public class FileDetailViewHolder extends ViewHolder {

		public LinearLayout fileAttachLayout;
		public TextView tvDate;
		public TextView tvBody;
		public BookmarkAndOptionView bookmarkAndOptionView;
		public NameAndDepartmentView nameAndDepartmentView;
		public ImageView imgProfile;

		public FileDetailViewHolder(View convertView) {
			imgProfile = (ImageView) convertView.findViewById(R.id.ID_IMG_PROFILE);
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookmarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			fileAttachLayout = (LinearLayout) convertView.findViewById(R.id.ID_LAY_L_FILE_ATTACH_AREA);
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

	private DateFormat m_dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault());
	private DateFormat m_yyyyMMdd = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());

	abstract static class ViewHolder {

	}

	public class CommandDetailViewHolder extends ViewHolder {

		private ImageView imgProfile;
		private NameAndDepartmentView nameAndDepartmentView;
		private BookmarkAndOptionView bookmarkAndOptionView;
		private TextView tvTitle;
		private TextView tvCompleteDate;
		private TextView tvDueDate;
		private ImageButton btnWorkStatus;
		private TextView tvBody;
		private TextView tvDate;
		private LinearLayout fileAttachLayout;
		private ImageView btnMasterWorkStatus;

		public CommandDetailViewHolder(View convertView) {
			imgProfile = (ImageView) convertView.findViewById(R.id.ID_IMG_PROFILE);
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookmarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvTitle = (TextView) convertView.findViewById(R.id.ID_TV_TITLE);
			tvCompleteDate = (TextView) convertView.findViewById(R.id.ID_TV_COMPLETE_DATE);
			tvDueDate = (TextView) convertView.findViewById(R.id.ID_TV_END_DATE);
			btnWorkStatus = (ImageButton) convertView.findViewById(R.id.ID_BTN_WORK_STATUS);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			fileAttachLayout = (LinearLayout) convertView.findViewById(R.id.ID_LAY_L_FILE_ATTACH_AREA);
			btnMasterWorkStatus = (ImageView) convertView.findViewById(R.id.ID_BTN_MASTER_WORK_STATUS);

		}

	}

	public static class SeperatorViewHolder extends ViewHolder {
		public TextView tvLabel;

		public SeperatorViewHolder(View convertView) {
			tvLabel = (TextView) convertView.findViewById(R.id.ID_TV_LABEL);
		}
	}

	public static class OpinionViewHolder extends ViewHolder {
		public NameAndDepartmentView nameAndDepartmentView;
		public TextView tvBody;
		public TextView tvDate;
		public BookmarkAndOptionView bookMarkAndOptionView;
		public ImageView imgProfile;
		public LinearLayout fileAttachLayout;

		public OpinionViewHolder(View convertView) {
			imgProfile = (ImageView) convertView.findViewById(R.id.ID_IMG_PROFILE);
			nameAndDepartmentView = (NameAndDepartmentView) convertView.findViewById(R.id.ID_LAY_L_NAME_AND_DEPARTMENT);
			bookMarkAndOptionView = (BookmarkAndOptionView) convertView.findViewById(R.id.ID_BOOKMARK_AND_OPTION_VIEW);
			tvBody = (TextView) convertView.findViewById(R.id.ID_TV_BODY);
			tvDate = (TextView) convertView.findViewById(R.id.ID_TV_DATE);
			fileAttachLayout = (LinearLayout) convertView.findViewById(R.id.ID_LAY_L_FILE_ATTACH_AREA);
		}
	}

	public static class ParticipantViewHolder extends ViewHolder {
		private ParticipantView participantView;

		public ParticipantViewHolder(View convertView) {
			participantView = (ParticipantView) convertView.findViewById(R.id.ID_PARTICIPANT_VIEW);
		}

		public void setData(String strAuthId, ArrayList<ContentsMemberListItemVO> memberList) {

			participantView.setData(strAuthId, memberList);
		}
	}

	private ArrayList<ISquareContentsDetailItem> m_data;
	private LayoutInflater m_inflater;
	private Activity m_activity;
	private IBookmarkAndOptionViewListener m_optionListener;
	private Status m_status;
	private IWorkStatusChangeListener m_workStatusChangeListener;

	public SquareDetailContentsAdapter(Status status, final Activity a, ArrayList<ISquareContentsDetailItem> data,
			IBookmarkAndOptionViewListener listener, IFileAttachClickListener fileClickListener,
			IWorkStatusChangeListener workStatusChangeListener) {
		m_activity = a;
		m_data = data;
		m_optionListener = listener;
		m_status = status;
		m_fileClickListener = fileClickListener;
		m_workStatusChangeListener = workStatusChangeListener;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public ISquareContentsDetailItem getItem(int position) {
		return m_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return 7;
	}

	@Override
	public int getItemViewType(int position) {
		if (m_data.get(position) instanceof CommandDetailHeader) {
			return 0;
		} else if (m_data.get(position) instanceof TopicDetailHeader) {
			return 1;
		} else if (m_data.get(position) instanceof FileDetailHeader) {
			return 2;
		} else if (m_data.get(position) instanceof SquareOpinion) {
			return 3;
		} else if (m_data.get(position) instanceof Seperator) {
			return 4;
		} else if (m_data.get(position) instanceof Participant) {
			return 5;
		}
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (m_inflater == null) {
			m_inflater = LayoutInflater.from(parent.getContext());
		}
		if (convertView == null) {
			if (m_data.get(position) instanceof CommandDetailHeader) {
				convertView = m_inflater.inflate(R.layout.list_item_square_command_detail, null);
				holder = new CommandDetailViewHolder(convertView);
			} else if (m_data.get(position) instanceof Seperator) {
				convertView = m_inflater.inflate(R.layout.list_item_square_detail_seperator, null);
				holder = new SeperatorViewHolder(convertView);
			} else if (m_data.get(position) instanceof Participant) {
				convertView = m_inflater.inflate(R.layout.list_item_square_participant, null);
				holder = new ParticipantViewHolder(convertView);
			} else if (m_data.get(position) instanceof SquareOpinion) {
				convertView = m_inflater.inflate(R.layout.list_item_square_opinion, null);
				holder = new OpinionViewHolder(convertView);
			} else if (m_data.get(position) instanceof TopicDetailHeader) {
				convertView = m_inflater.inflate(R.layout.list_item_square_topic_detail, null);
				holder = new TopicDetailViewHolder(convertView);
			} else if (m_data.get(position) instanceof FileDetailHeader) {
				convertView = m_inflater.inflate(R.layout.list_item_square_file_detail, null);
				holder = new FileDetailViewHolder(convertView);
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (holder != null) {
			if (holder instanceof CommandDetailViewHolder) {
				final CommandDetailHeader commandData = (CommandDetailHeader) m_data.get(position);
				final CommandDetailViewHolder commandViewHolder = (CommandDetailViewHolder) holder;
				commandViewHolder.nameAndDepartmentView.setData(commandData.m_commandDetailData.authorName,
						commandData.m_commandDetailData.authorDeptName, commandData.m_commandDetailData.authorPositionName);
				commandViewHolder.bookmarkAndOptionView.setData(m_status, commandData.m_commandDetailData);
				commandViewHolder.bookmarkAndOptionView.setBookmarkAndOptionViewListener(m_optionListener);
				commandViewHolder.tvTitle.setText(Html.fromHtml(getProcessText(commandData.m_commandDetailData.orderStatus, new Date(
						commandData.m_commandDetailData.dueDate))
						+ commandData.m_commandDetailData.title), TextView.BufferType.SPANNABLE);
				if (TextUtils.isEmpty(commandData.m_commandDetailData.body)) {
					commandViewHolder.tvBody.setVisibility(View.GONE);
				} else {
					commandViewHolder.tvBody.setVisibility(View.VISIBLE);
					commandViewHolder.tvBody.setText(Html.fromHtml(commandData.m_commandDetailData.body));
				}
				commandViewHolder.tvDate.setText(m_dateFormat.format(new Date(commandData.m_commandDetailData.createDate)));
				setAttachFiles(commandData.m_commandDetailData, commandViewHolder.fileAttachLayout, convertView.getContext(),
						m_optionListener);
				if (MainModel.getInstance().getDefaultDate().compareTo(new Date(commandData.m_commandDetailData.endDate)) == 0) {
					commandViewHolder.tvCompleteDate.setText(convertView.getResources().getString(
							R.string.label_square_complete_date_format, "-"));
				} else {
					commandViewHolder.tvCompleteDate.setText(convertView.getResources().getString(
							R.string.label_square_complete_date_format, m_yyyyMMdd.format(commandData.m_commandDetailData.endDate)));
				}
				if (MainModel.getInstance().getDefaultDate().compareTo(new Date(commandData.m_commandDetailData.dueDate)) == 0) {
					commandViewHolder.tvDueDate.setText(convertView.getResources().getString(R.string.label_square_end_date_format,
							convertView.getResources().getString(R.string.label_square_setting_permanent)));
				} else {
					commandViewHolder.tvDueDate.setText(convertView.getResources().getString(R.string.label_square_end_date_format,
							m_yyyyMMdd.format(commandData.m_commandDetailData.dueDate)));
				}

				if (MainModel.getInstance().isMyAdminWork(HMGWServiceHelper.userId, commandData.m_commandDetailData.authorId)) {
					commandViewHolder.btnMasterWorkStatus.setSelected(commandData.m_commandDetailData.orderStatus
							.equals(OrderStatus.COMPLETE));
					commandViewHolder.btnMasterWorkStatus.setVisibility(View.VISIBLE);
					// 작업 지시자면 작업 종료 버튼 표시
					commandViewHolder.btnMasterWorkStatus.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (m_workStatusChangeListener != null) {
								m_workStatusChangeListener.onMasterWorkStatusChange(commandViewHolder.btnMasterWorkStatus,
										commandData.m_commandDetailData, !commandViewHolder.btnMasterWorkStatus.isSelected(),
										commandData.m_commandDetailData.contentsId);
							}
						}
					});
				} else {
					commandViewHolder.btnMasterWorkStatus.setVisibility(View.GONE);
				}

				// 작업자 이면 이름 옆에 작업 완료 표시
				if (MainModel.getInstance().isMyWork(HMGWServiceHelper.userId, commandData.m_commandDetailData.contentsMemberList)) {
					commandViewHolder.btnWorkStatus.setVisibility(View.VISIBLE);
					// 작업이 진행중인지 완료인지 표시
					commandViewHolder.btnWorkStatus.setSelected(MainModel.getInstance().isWorkDone(HMGWServiceHelper.userId,
							commandData.m_commandDetailData.contentsMemberList));
					commandViewHolder.btnWorkStatus.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// 진행 상태가 종료가 아닐 경우만 처리한다.
							if (m_workStatusChangeListener != null
									&& !commandData.m_commandDetailData.orderStatus.equals(OrderStatus.COMPLETE)) {
								m_workStatusChangeListener.onWorkStatusChange(commandViewHolder.btnWorkStatus,
										commandData.m_commandDetailData, !commandViewHolder.btnWorkStatus.isSelected(),
										commandData.m_commandDetailData.contentsId);
							}
						}
					});
				} else {
					commandViewHolder.btnWorkStatus.setVisibility(View.GONE);
				}

			} else if (holder instanceof SeperatorViewHolder) {
				SeperatorViewHolder h = (SeperatorViewHolder) holder;
				Seperator data = (Seperator) m_data.get(position);
				h.tvLabel.setText(data.getLabelText());
			} else if (holder instanceof OpinionViewHolder) {
				OpinionViewHolder h = (OpinionViewHolder) holder;
				SquareOpinion data = (SquareOpinion) m_data.get(position);
				h.imgProfile.setImageResource(R.drawable.user_defalut);
				h.nameAndDepartmentView.setData(data.m_opinionData.authorName, data.m_opinionData.authorDeptName,
						data.m_opinionData.authorPositionName);
				h.bookMarkAndOptionView.setData(m_status, data.m_opinionData);
				h.bookMarkAndOptionView.setBookmarkAndOptionViewListener(m_optionListener);
				h.tvBody.setText(data.m_opinionData.body);
				h.tvDate.setText(m_dateFormat.format(new Date(data.m_opinionData.createDate)));
				setAttachFiles(data.m_opinionData, h.fileAttachLayout, convertView.getContext(),
						m_optionListener);
			} else if (holder instanceof ParticipantViewHolder) {
				final ParticipantViewHolder h = (ParticipantViewHolder) holder;
				final Participant data = (Participant) m_data.get(position);
				h.setData(data.m_authId, data.m_memberList);
			} else if (holder instanceof TopicDetailViewHolder) {
				TopicDetailViewHolder h = (TopicDetailViewHolder) holder;
				TopicDetailHeader data = (TopicDetailHeader) m_data.get(position);
				h.nameAndDepartmentView.setData(data.m_topicData.authorName, data.m_topicData.authorDeptName,
						data.m_topicData.authorPositionName);
				h.bookmarkAndOptionView.setData(m_status, data.m_topicData);
				h.bookmarkAndOptionView.setBookmarkAndOptionViewListener(m_optionListener);
				h.tvTitle.setText(data.m_topicData.title);
				if (TextUtils.isEmpty(data.m_topicData.body)) {
					h.tvBody.setVisibility(View.GONE);
				} else {
					h.tvBody.setVisibility(View.VISIBLE);
					h.tvBody.setText(Html.fromHtml(data.m_topicData.body));
				}

				h.tvDate.setText(m_dateFormat.format(new Date(data.m_topicData.createDate)));
				setAttachFiles(data.m_topicData, h.fileAttachLayout, convertView.getContext(), m_optionListener);
			} else if (holder instanceof FileDetailViewHolder) {
				FileDetailViewHolder h = (FileDetailViewHolder) holder;
				FileDetailHeader data = (FileDetailHeader) m_data.get(position);
				h.nameAndDepartmentView.setData(data.m_fileData.authorName, data.m_fileData.authorDeptName,
						data.m_fileData.authorPositionName);
				h.bookmarkAndOptionView.setData(m_status, data.m_fileData);
				h.bookmarkAndOptionView.setBookmarkAndOptionViewListener(m_optionListener);
				h.tvBody.setText(Html.fromHtml(data.m_fileData.title));
				h.tvDate.setText(m_dateFormat.format(new Date(data.m_fileData.createDate)));
				setAttachFiles(data.m_fileData, h.fileAttachLayout, convertView.getContext(), m_optionListener);
			}
		}
		if(convertView instanceof ViewGroup)
		{
			AndroidUtils.clearFocus((ViewGroup) convertView);
		}
		return convertView;
	}

	private String getProcessText(OrderStatus orderStatus, Date dueDate) {
		StringBuilder sb = new StringBuilder();
		switch (orderStatus) {
		case COMPLETE:
			sb.append("<font color='#707070'>");
			sb.append(m_activity.getResources().getString(R.string.label_square_order_status_end));
			sb.append("</font>");
			break;
		case NONE:
			break;
		case PROCESS:
			Date nowDate = new Date();
			if (dueDate.compareTo(MainModel.getInstance().getDefaultDate()) == 0) {
				return "";
			}
			if (nowDate.compareTo(dueDate) > 0) {
				sb.append("<font color='#e52727'>");
				sb.append(m_activity.getResources().getString(R.string.label_square_order_status_delay));
				sb.append("</font>");
			} else {
				sb.append("<font color='#00a651'>");
				sb.append(m_activity.getResources().getString(R.string.label_square_order_status_processing));
				sb.append("</font>");
			}
			break;
		}
		return sb.toString();
	}
}
