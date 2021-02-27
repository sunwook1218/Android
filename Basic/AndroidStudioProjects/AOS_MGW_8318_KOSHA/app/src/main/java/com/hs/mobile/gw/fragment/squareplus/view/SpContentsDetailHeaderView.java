package com.hs.mobile.gw.fragment.squareplus.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.ISpMRListener;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.squareplus.vo.ContentsType;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO.Status;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.Debug;

public class SpContentsDetailHeaderView extends LinearLayout
		 {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd aa h:mm", Locale.getDefault());
	private SimpleDateFormat titleSdf = new SimpleDateFormat("yyyy.MM.dd",
			Locale.getDefault());
	private SpCompletionView completionView;
	private ISpCompletionViewListener listener;
	protected SpContentsVO data;
	private ImageButton btnMore;
	private ISpMRListener mrListener;
	private Status m_squareStatus;
	public SpContentsDetailHeaderView(Context context, AttributeSet attrs, int defStyle, Status status) {
		super(context, attrs, defStyle);
		init();
		this.m_squareStatus = status;
	}

	public SpContentsDetailHeaderView(Context context, AttributeSet attrs, Status status) {
		super(context, attrs);
		init();
		this.m_squareStatus = status;
	}

	public SpContentsDetailHeaderView(Context context, Status status) {
		super(context);
		init();
		this.m_squareStatus = status;
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(
				R.layout.sp_content_detail_header_view, this);
		completionView = (SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW);
	}

	public void setData(final SpContentsVO item, ISpCompletionViewListener listener, final ISpClickListener clickListener) {
		data = item;
		completionView.setData(item, listener);
		if (item.getContentsTypeEnum() == ContentsType.REPORT) {
			findViewById(R.id.ID_TV_REPORT_TITLE).setVisibility(View.VISIBLE);
			((TextView) findViewById(R.id.ID_TV_REPORT_TITLE)).setText(item.getTitle());
		} else {
			findViewById(R.id.ID_TV_REPORT_TITLE).setVisibility(View.GONE);
		}
		final ImageButton btnFavorite = (ImageButton)findViewById(R.id.ID_BTN_FAVORITE);
		btnFavorite.setSelected("1".equals(data.getFavoriteFlag()) ? true
				: false);
		btnFavorite.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clickListener.onFavoriteClick(item, new IFavoriteCallbak() {
					@Override
					public void onResponse(SpContentsVO item) {
						if (item != null) {
							SpContentsDetailHeaderView.this.data = item;
							btnFavorite.setSelected("1".equals(data
									.getFavoriteFlag()) ? true : false);
						}
					}

					@Override
					public void onResponse(SpAttachVO item) {
						// Nothing to do
					}
				});				
			}
		});
		((TextView) findViewById(R.id.ID_TV_NAME)).setText(item.getAuthorName());
		((TextView) findViewById(R.id.ID_TV_DEPARTMENT)).setText("("+ item.getAuthorDeptName() + " " + item.getAuthorPositionName()+ ")");
		((TextView) findViewById(R.id.ID_TV_DATE)).setText(sdf.format(item.getCreateDate()));

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
		
		if (!item.mySquareMember || item.getMemberRightsEnum() == MemberRights.OBSERVER_USER || m_squareStatus == Status.END)
			((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD)).setVisibility(View.GONE);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD)).setBackgroundResource(getFavorRes(Integer.valueOf(item.getFavorType())));
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (v.isSelected()) {
					v.setSelected(false);
					((LinearLayout) findViewById(R.id.ID_LAY_L_EXPRESSION_EMOTICON)).setVisibility(GONE);
					Animation slideOutLeft = AnimationUtils.loadAnimation(getContext(), R.anim.anim_slide_out_emoti);
					((LinearLayout) findViewById(R.id.ID_LAY_L_EXPRESSION_EMOTICON)).startAnimation(slideOutLeft);
				}
				else {
					v.setSelected(true);
					((LinearLayout) findViewById(R.id.ID_LAY_L_EXPRESSION_EMOTICON)).setVisibility(VISIBLE);
					Animation slideInRight = AnimationUtils.loadAnimation(getContext(), R.anim.anim_slide_in_emoti);
					((LinearLayout) findViewById(R.id.ID_LAY_L_EXPRESSION_EMOTICON)).startAnimation(slideInRight);
				}
			}
		});
		
		OnClickListener emotiClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int type = 0;
				switch (v.getId()) {
				case R.id.ID_BTN_EXPRESSION_ADD1:
					type = 1;
					break;
				case R.id.ID_BTN_EXPRESSION_ADD2:
					type = 2;
					break;
				case R.id.ID_BTN_EXPRESSION_ADD3:
					type = 3;
					break;
				case R.id.ID_BTN_EXPRESSION_ADD4:
					type = 4;
					break;
				case R.id.ID_BTN_EXPRESSION_ADD5:
					type = 5;
					break;
				case R.id.ID_BTN_EXPRESSION_ADD6:
					type = 6;
					break;

				default:
					break;
				}
				
				// TODO Auto-generated method stub
				clickListener.onFavorClick(item, type);
			}
		};
		
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD1)).setOnClickListener(emotiClickListener);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD2)).setOnClickListener(emotiClickListener);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD3)).setOnClickListener(emotiClickListener);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD4)).setOnClickListener(emotiClickListener);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD5)).setOnClickListener(emotiClickListener);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD6)).setOnClickListener(emotiClickListener);
		
		setFavorSelect(Integer.valueOf(item.getFavorType()));
		
		((SpHashTagView) findViewById(R.id.ID_HASHTAG_VIEW)).setData(item, listener);
		for(SpAttachVO fileVO:item.getAttachList()){
			fileVO.setAuthorId(item.getAuthorId());
		}
		
		if (item.getOpenGraphList() != null && item.getOpenGraphList().size() > 0) {
			((SpUrlPreviewLayout)findViewById(R.id.ID_URLPREVIEW_LAYOUT)).setData(item.getOpenGraphList(), clickListener);
		}
		
		View thumbnailsContainer = (View) findViewById(R.id.ID_THUMBNAIL_CONTAINER);
		LinearLayout thumbnails = (LinearLayout) findViewById(R.id.container);
		thumbnails.removeAllViewsInLayout();
		if (item.getAttachList() != null && !item.getAttachList().isEmpty()) {

			ArrayList<SpAttachVO> images = new ArrayList<SpAttachVO>();
			for (SpAttachVO data : item.getAttachList()) {
				if (TextUtils.equals(data.getAttachType(), "1")) {
					images.add(data);
				}
			}

			if (images != null && images.size() > 0) {
				thumbnailsContainer.setVisibility(VISIBLE);

				for (int i = 0; i < images.size(); i++) {

					View v = LayoutInflater.from(getContext()).inflate(R.layout.sp_attach_thumbnail_view, null);

					ImageView view = (ImageView) v.findViewById(R.id.ID_IMG_ATTACH_THUMBNAIL);
					view.setScaleType(ScaleType.CENTER_CROP);

					view.setOnClickListener(onChagePageClickListener(images.get(i), clickListener));
					
					if(MainModel.getInstance().getOpenApiService()!=null){
						MainModel.getInstance().getOpenApiService().drawThumbnail(images.get(i).getAttachId(), view, getContext().getResources());
					}
					
					thumbnails.addView(v);
				}

			} else {
				thumbnailsContainer.setVisibility(GONE);
			}
		} else {
			thumbnailsContainer.setVisibility(GONE);
		}
		
		if(item.getAttachList()!=null)((SpFileViewLayout)findViewById(R.id.ID_FILE_LAYOUT)).setData(item.getAttachList(), clickListener);
		btnMore = ((ImageButton)findViewById(R.id.ID_BTN_MORE));
		
		if (!HMGWServiceHelper.userId.equals(item.getAuthorId())) {
			btnMore.setVisibility(View.INVISIBLE);
		} else {
			btnMore.setVisibility(View.VISIBLE);
		}

		btnMore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PopupMenu popupMenu = new PopupMenu(getContext(), btnMore);
				popupMenu.getMenuInflater().inflate(R.menu.squareplus_topic_list, popupMenu.getMenu());
				popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem menuItem) {
						switch (menuItem.getItemId()) {
						case R.id.ID_TOPIC_MODIFY:
							Debug.trace("ID_TOPIC_MODIFY");
							mrListener.onModify(data);
							break;
						case R.id.ID_TOPIC_DELETE:
							Debug.trace("ID_TOPIC_DELETE");
							mrListener.onRemove(data);
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

		Debug.trace("getAuthorId = ", item.getAuthorId());
		if(MainModel.getInstance().getOpenApiService()!=null)
			if(item.getAuthorId()!=null) MainModel.getInstance().getOpenApiService().drawUserPhoto(item.getAuthorId(), photo, getResources());

	}
	
	private View.OnClickListener onChagePageClickListener(final SpAttachVO vo, final ISpClickListener listener) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	listener.onOrgImgDown(vo);
            }
        };
    }
	
	private void clearEmotiSelect() {
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD1)).setSelected(false);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD2)).setSelected(false);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD3)).setSelected(false);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD4)).setSelected(false);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD5)).setSelected(false);
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD6)).setSelected(false);
	}
	
	public void setFavorSelect(int type) {
		Button btn = null;
		clearEmotiSelect();
		switch (type) {
		case 1:
			btn = (Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD1);
			break;
		case 2:
			btn = (Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD2);
			break;
		case 3:
			btn = (Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD3);
			break;
		case 4:
			btn = (Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD4);
			break;
		case 5:
			btn = (Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD5);
			break;
		case 6:
			btn = (Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD6);
			break;
		default:
			break;
		}
		
		if (btn != null)
			btn.setSelected(true);
		
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD)).setBackgroundResource(getFavorRes(type));
		
		((Button) findViewById(R.id.ID_BTN_EXPRESSION_ADD)).setSelected(false);
		
		LinearLayout emotiLayout = ((LinearLayout) findViewById(R.id.ID_LAY_L_EXPRESSION_EMOTICON));
		
		if (emotiLayout != null && emotiLayout.getVisibility() != View.GONE) {
			emotiLayout.setVisibility(GONE);
			Animation slideOutLeft = AnimationUtils.loadAnimation(getContext(), R.anim.anim_slide_out_emoti);
			emotiLayout.startAnimation(slideOutLeft);
		}
	}
	public int getFavorRes(int type) {
		int res = R.drawable.add_selector;
		switch (type) {
		case 1:
			res = R.drawable.love;
			break;
		case 2:
			res = R.drawable.happy;
			break;
		case 3:
			res = R.drawable.good;
			break;
		case 4:
			res = R.drawable.suprise;
			break;
		case 5:
			res = R.drawable.sad;
			break;
		case 6:
			res = R.drawable.angry;
			break;
		}
		 return res;
	}
	
	public void setFavorCountView(boolean isView, int count) {
		if (count == 0 && isView)
			((LinearLayout) findViewById(R.id.ID_LAY_CNT)).setVisibility(GONE);
		else {
			((LinearLayout) findViewById(R.id.ID_LAY_CNT)).setVisibility(VISIBLE);
			((View) findViewById(R.id.ID_V_DIVISION_LINE)).setVisibility(VISIBLE);
			if (count == 0) {
				((TextView) findViewById(R.id.ID_TV_EXPRESSION)).setVisibility(GONE);
				((TextView) findViewById(R.id.ID_TV_EXPRESSION_CNT)).setVisibility(GONE);
				((View) findViewById(R.id.ID_V_DIVISION_LINE)).setVisibility(GONE);
			}
			else {
				((TextView) findViewById(R.id.ID_TV_EXPRESSION)).setVisibility(VISIBLE);
				((TextView) findViewById(R.id.ID_TV_EXPRESSION_CNT)).setVisibility(VISIBLE);
				((TextView) findViewById(R.id.ID_TV_EXPRESSION_CNT)).setText(String.valueOf(count));
			}
			
			if (isView) {
				((View) findViewById(R.id.ID_V_DIVISION_LINE)).setVisibility(GONE);
				((View) findViewById(R.id.ID_TV_OPINION)).setVisibility(GONE);
				((View) findViewById(R.id.ID_TV_OPINION_CNT)).setVisibility(GONE);
			}
		}
	}

	public void setMRListener(ISpMRListener listener) {
		mrListener = listener;
	}
	
}
