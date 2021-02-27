package com.hs.mobile.gw.fragment.squareplus.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.squareplus.vo.ContentsType;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpOpenGraphVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class SpTopicView extends LinearLayout {
	public interface ISpTopicViewListener {
		void onModify(SpContentsVO item);

		void onDelete(SpContentsVO item);
	}
	private ISpClickListener listener;
	private ISpCompletionViewListener spCompletionViewListener;
	private ISpTopicViewListener spTopicViewListener;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd aa h:mm",
			Locale.getDefault());
	private SpContentsVO data;
	private Context context;
	private MainModel m_model;

	public SpTopicView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		LayoutInflater.from(context).inflate(R.layout.sp_content_topic, this);
		this.context = context;
		m_model = MainModel.getInstance();
		m_model.createAqueryIntence(context);
	}

	public SpTopicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.sp_content_topic, this);
		this.context = context;
		m_model = MainModel.getInstance();
		m_model.createAqueryIntence(context);
	}

	public SpTopicView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.sp_content_topic, this);
		this.context = context;
		m_model = MainModel.getInstance();
		m_model.createAqueryIntence(context);
	}

	public void setSpClickListener(ISpClickListener listener) {
		this.listener = listener;
	}

	public void setSpCompletionViewListener(ISpCompletionViewListener listener) {
		this.spCompletionViewListener = listener;
	}

	public void setSpTopicViewListener(ISpTopicViewListener listener) {
		this.spTopicViewListener = listener;
	}

	public void setData(final SpContentsVO item, final boolean isOriginView, boolean openSquare) {
		this.data = item;
		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onDetailView(item, false);
			}
		});

		LinearLayout originLayout = (LinearLayout) findViewById(R.id.ID_LL_ORIGIN);
		TextView originTextView = (TextView) findViewById(R.id.ID_TV_ORIGIN);
		if (isOriginView) {
			originTextView.setText(item.getSquareTitle());
			originLayout.setVisibility(View.VISIBLE);
		} else {
			originLayout.setVisibility(View.GONE);
		}
		if(item.getContentsTypeEnum() == ContentsType.REPORT){
			((TextView) findViewById(R.id.ID_TV_REPORT_TITLE)).setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.ID_TV_REPORT_TITLE)).setText(item.getTitle());
		}
		((TextView) findViewById(R.id.ID_TV_NAME))
				.setText(item.getAuthorName());
		((TextView) findViewById(R.id.ID_TV_DEPARTMENT)).setText("("
				+ item.getAuthorDeptName() + " " + item.getAuthorPositionName()
				+ ")");
		((TextView) findViewById(R.id.ID_TV_DATE)).setText(sdf.format(item
				.getCreateDate()));
		
		if (item.getFavorCount() == 0 && item.getCommentCount() == 0)
			((LinearLayout) findViewById(R.id.ID_LAY_CNT)).setVisibility(GONE);
		else {
			((LinearLayout) findViewById(R.id.ID_LAY_CNT)).setVisibility(VISIBLE);
			((View) findViewById(R.id.ID_V_DIVISION_LINE)).setVisibility(VISIBLE);
			if (item.getFavorCount() == 0) {
				((TextView) findViewById(R.id.ID_TV_EXPRESSION)).setVisibility(GONE);
				((TextView) findViewById(R.id.ID_TV_EXPRESSION_CNT)).setVisibility(GONE);
				((View) findViewById(R.id.ID_V_DIVISION_LINE)).setVisibility(GONE);
			}
			else {
				((TextView) findViewById(R.id.ID_TV_EXPRESSION)).setVisibility(VISIBLE);
				((TextView) findViewById(R.id.ID_TV_EXPRESSION_CNT)).setVisibility(VISIBLE);
				((TextView) findViewById(R.id.ID_TV_EXPRESSION_CNT)).setText(String.valueOf(item.getFavorCount()));
			}
				
			if (item.getCommentCount() == 0) {
				((TextView) findViewById(R.id.ID_TV_OPINION)).setVisibility(GONE);
				((TextView) findViewById(R.id.ID_TV_OPINION_CNT)).setVisibility(GONE);
				((View) findViewById(R.id.ID_V_DIVISION_LINE)).setVisibility(GONE);
			}
			else {
				((TextView) findViewById(R.id.ID_TV_OPINION)).setVisibility(VISIBLE);
				((TextView) findViewById(R.id.ID_TV_OPINION_CNT)).setVisibility(VISIBLE);
				((TextView) findViewById(R.id.ID_TV_OPINION_CNT)).setText(String.valueOf(item.getCommentCount()));
			}
		}
		
		if (item.getFavorType().equals("0")) {
			((TextView) findViewById(R.id.ID_TV_FACIAL_EXPRESSION)).setText(R.string.label_squareplus_facial_expression);
		}
		else {
			((TextView) findViewById(R.id.ID_TV_FACIAL_EXPRESSION)).setText(R.string.label_squareplus_cancel_expression);
		}
			
		View thumbnailsContainer = (View) findViewById(R.id.ID_THUMBNAIL_CONTAINER);
		LinearLayout thumbnails = (LinearLayout) findViewById(R.id.container);
		thumbnails.removeAllViewsInLayout();
		LinearLayout fileLayout = (LinearLayout) findViewById(R.id.ID_FILE_LAYOUT);
		((LinearLayout)findViewById(R.id.ID_BTN_WRITE_OPINION)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onDetailView(item, true);
			}
		});
//		if (!(item.getMemberRightsEnum() != MemberRights.OBSERVER_USER && item.isMySquareMember())) {
//			((LinearLayout) findViewById(R.id.ID_LAY_L_WRITE_OPINION)).setVisibility(View.GONE);
//		}
		
		//url preview
		RelativeLayout urlPreviewLayout = (RelativeLayout) findViewById(R.id.ID_URLPREVIEW_LAYOUT);
		if (item.getOpenGraphList() != null && !item.getOpenGraphList().isEmpty()) {
			urlPreviewLayout.setVisibility(View.VISIBLE);
			SpOpenGraphVO openGraphVO = item.getOpenGraphList().get(0);
			((TextView) urlPreviewLayout.findViewById(R.id.ID_TV_PREVIEW_TITLE))
			.setText(openGraphVO.getTitle());
			((TextView) urlPreviewLayout.findViewById(R.id.ID_TV_PREVIEW_CONTENT))
			.setText(openGraphVO.getDescription());
			((TextView) urlPreviewLayout.findViewById(R.id.ID_TV_PREVIEW_URL))
			.setText(openGraphVO.getDomain());
			
			ImageView urlThumbnail = (ImageView) urlPreviewLayout.findViewById(R.id.ID_IMG_PREVIEW_THUMBNAIL);
			UrlImageViewHelper.setUrlDrawable(urlThumbnail, openGraphVO.getImage(), R.drawable.sp_no_image, new UrlImageViewCallback()
            {

                @Override
                public void onLoaded(ImageView imageView, Bitmap loadedDrawable, String url, boolean loadedFromCache)
                {
                    // TODO Auto-generated method stub

                    if (loadedDrawable == null) // 아이콘을 로드하지 못한 경우
                        return;

                    imageView.setImageBitmap(loadedDrawable);
                }

            });
		}
		else {
			urlPreviewLayout.setVisibility(View.GONE);
		}

		if (item.getAttachList() != null && !item.getAttachList().isEmpty()) {
			
			ArrayList<SpAttachVO> attachments = new ArrayList<SpAttachVO>();
			ArrayList<SpAttachVO> images = new ArrayList<SpAttachVO>();
			for (SpAttachVO data : item.getAttachList()) {
				if (TextUtils.equals(data.getAttachType(), "0")) {
					attachments.add(data);
				}
				else if (TextUtils.equals(data.getAttachType(), "1")) {
					images.add(data);
				}
			}
			
			if (attachments != null && attachments.size() > 0) {
				SpAttachVO attachVO = attachments.get(0);
				fileLayout.setVisibility(VISIBLE);
				((TextView) fileLayout.findViewById(R.id.ID_TV_FILE_CNT))
				.setText("파일 " + attachments.size() + "개");
				
				((TextView) fileLayout.findViewById(R.id.ID_TV_FILE_SIZE))
				.setText(MainModel.getInstance().readableFileSize(
						attachVO.getFileSize()));
				((TextView) fileLayout.findViewById(R.id.ID_TV_FILE_NAME))
				.setText(attachVO
						.getFileName()
						.concat(".")
						.concat(attachVO.getFileExt())
						.concat(attachments.size() > 1 ? " 외" : ""));
			}
			else {
				fileLayout.setVisibility(GONE);
			}
			
			if (images != null && images.size() > 0) {
				thumbnailsContainer.setVisibility(VISIBLE);

				for (int i = 0; i < images.size(); i++) {

					View v = LayoutInflater.from(getContext()).inflate(R.layout.sp_attach_thumbnail_view, null);

					ImageView view = (ImageView) v.findViewById(R.id.ID_IMG_ATTACH_THUMBNAIL);
					view.setScaleType(ScaleType.CENTER_CROP);

					view.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							listener.onDetailView(item, false);
						}
					});

					if(MainModel.getInstance().getOpenApiService()!=null)
						MainModel.getInstance().getOpenApiService().drawThumbnail(images.get(i).getAttachId(), view, getContext().getResources());
					thumbnails.addView(v);
				}
			} else {
				thumbnailsContainer.setVisibility(GONE);
			}
			
		} else {
			thumbnailsContainer.setVisibility(GONE);
			fileLayout.setVisibility(GONE);
		}

		final SpCompletionView spCompletionView = (SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW);
		spCompletionView.setData(item, spCompletionViewListener);
		spCompletionView.setMaxLines(3);
		spCompletionView.setEllipsize(TruncateAt.END);
		spCompletionView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onDetailView(item, false);
			}
		});
		spCompletionView.post(new Runnable() {
			@Override
			public void run() {
				findViewById(R.id.ID_TV_MORE_TEXT).setVisibility(
						spCompletionView.getLineCount() > 3 ? View.VISIBLE
								: View.GONE);
			}
		});

		final ImageButton btnFavorite = (ImageButton) findViewById(R.id.ID_BTN_FAVORITE);
		btnFavorite.setSelected("1".equals(data.getFavoriteFlag()) ? true
				: false);
		btnFavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onFavoriteClick(data, new IFavoriteCallbak() {
					@Override
					public void onResponse(SpContentsVO item) {
						SpTopicView.this.data = item;
						btnFavorite.setSelected("1".equals(data
								.getFavoriteFlag()) ? true : false);
					}

					@Override
					public void onResponse(SpAttachVO item) {
						// Nothing to do
					}
				});
			}
		});
		final ImageButton btnMore =(ImageButton) findViewById(R.id.ID_BTN_MORE);

		if (!HMGWServiceHelper.userId.equals(item.getAuthorId())) {
			btnMore.setVisibility(View.INVISIBLE);
		} else {
			btnMore.setVisibility(View.VISIBLE);
		}

		btnMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(context, btnMore);
				popupMenu.getMenuInflater().inflate(R.menu.squareplus_topic_list, popupMenu.getMenu());

				popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem menuItem) {
						switch (menuItem.getItemId()) {
						case R.id.ID_TOPIC_MODIFY:
							Debug.trace("ID_TOPIC_MODIFY");
							spTopicViewListener.onModify(item);

							break;
						case R.id.ID_TOPIC_DELETE:
							Debug.trace("ID_TOPIC_DELETE");
							spTopicViewListener.onDelete(item);

							break;
						}
						return true;
					}
				});
				popupMenu.show();
			}
		});

		// 사용자 사진
		final ImageView photo = (ImageView) findViewById(R.id.ID_IMG_USER);
		if (item.getAuthorId() != null) {
			Debug.trace("getAuthorId = ", item.getAuthorId());
			m_model.getOpenApiService().drawUserPhoto(item.getAuthorId(), photo, getResources());
		}
		else
			Debug.trace("getAuthorId = null");
			
	}
}
