package com.hs.mobile.gw.view;

import java.util.ArrayList;
import java.util.List;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.ContentsMemberListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MemberRight;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.util.PixelUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ParticipantView extends LinearLayout {
	public interface IParticipantViewListener {
		void onParticipantClick(ParticipantItem participantItem);
	}

	public class ParticipantItem extends RelativeLayout {

		private ImageView m_imgProfile;
		private ImageView m_imgMaster;
		private TextView m_tvUserName;
		private ImageView m_imgCheck;
		private SquareMemberVO m_squareMemberVo;
		private SpSquareMemberVO m_spSquareMemberVO;

		public ParticipantItem(Context context) {
			super(context);
			LayoutInflater.from(context).inflate(R.layout.participant_item, this);
			m_imgProfile = (ImageView) findViewById(R.id.ID_IMG_PROFILE);
			m_imgMaster = (ImageView) findViewById(R.id.ID_IMG_MASTER);
			m_tvUserName = (TextView) findViewById(R.id.ID_TV_USER_NAME);
			m_imgCheck = (ImageView) findViewById(R.id.ID_IMG_CHECK);
		}

		public void setData(ContentsMemberListItemVO vo, boolean bAdmin, boolean bWorker) {
			m_tvUserName.setText(vo.memberName);
			m_imgMaster.setVisibility(vo.charger ? View.VISIBLE : View.INVISIBLE);
			m_imgCheck.setVisibility(TextUtils.equals(vo.status, "2") ? View.VISIBLE : View.INVISIBLE);
		}

		public void setSquareMemberData(SquareMemberVO vo) {
			m_squareMemberVo = vo;
			m_tvUserName.setText(vo.memberName);
			m_imgMaster.setVisibility(vo.memberRights == MemberRight.ADMIN_USER ? View.VISIBLE : View.INVISIBLE);
			m_imgCheck.setVisibility(View.INVISIBLE);
		}

		public void setSquareMemberData(SpSquareMemberVO vo) {
			m_spSquareMemberVO = vo;

			if(MainModel.getInstance().getOpenApiService() == null) return;
			MainModel.getInstance().getOpenApiService().drawUserPhoto(vo.getMemberId(), m_imgProfile, getContext().getResources());
			
			m_tvUserName.setText(vo.getMemberName());
			m_imgMaster.setVisibility(vo.getMemberRightsEnum() == MemberRights.ADMIN_USER ? View.VISIBLE : View.INVISIBLE);
			m_imgCheck.setVisibility(View.INVISIBLE);
		}

		public SquareMemberVO getSquareMember() {
			return m_squareMemberVo;
		}

		public SpSquareMemberVO getSpSquareMember() {
			return m_spSquareMemberVO;
		}
	}

	private static int LIMIT_ONE_LINE_COUNT = 5;
	private IParticipantViewListener m_listener;

	public ParticipantView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public ParticipantView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public ParticipantView(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		setOrientation(VERTICAL);
	}

	public void setParticipantViewListener(IParticipantViewListener listener) {
		m_listener = listener;
	}

	public void setData(String strAuthId, ArrayList<ContentsMemberListItemVO> memberList) {
		removeAllViews();
		// 행 추가 감지 변수
		int nColumn = 0;
		LinearLayout rowLayout = new LinearLayout(getContext());
		rowLayout.setOrientation(HORIZONTAL);
		addView(rowLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < memberList.size(); ++i) {
			if (nColumn > LIMIT_ONE_LINE_COUNT) {
				nColumn = 0;
				rowLayout = new LinearLayout(getContext());
				rowLayout.setOrientation(HORIZONTAL);
				addView(rowLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				LayoutParams p = (LayoutParams) rowLayout.getLayoutParams();
				p.bottomMargin = (int) PixelUtils.getDip(getResources(), 5);
			}
			ParticipantItem participantItem = new ParticipantItem(getContext());
			boolean bAdmin = MainModel.getInstance().isAdmin(strAuthId, memberList);
			boolean bWorker = MainModel.getInstance().isWorker(memberList);
			participantItem.setData(memberList.get(i), bAdmin, bWorker);
			rowLayout.addView(participantItem, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			nColumn++;
		}
	}

	public void setSquareMemberData(ArrayList<SquareMemberVO> squareMemberList) {
		removeAllViews();
		// 행 추가 감지 변수
		int nColumn = 0;
		LinearLayout rowLayout = new LinearLayout(getContext());
		rowLayout.setOrientation(HORIZONTAL);
		addView(rowLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		for (int i = 0; i < squareMemberList.size(); ++i) {
			if (nColumn > LIMIT_ONE_LINE_COUNT) {
				nColumn = 0;
				rowLayout = new LinearLayout(getContext());
				rowLayout.setOrientation(HORIZONTAL);
				addView(rowLayout, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				LayoutParams p = (LayoutParams) rowLayout.getLayoutParams();
				p.bottomMargin = (int) PixelUtils.getDip(getResources(), 5);
			}
			final ParticipantItem participantItem = new ParticipantItem(getContext());
			if (m_listener != null) {
				participantItem.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						m_listener.onParticipantClick(participantItem);
					}
				});
			}
			participantItem.setBackgroundColor(0xFF00FF);
			if (squareMemberList.get(i)!= null){
				participantItem.setSquareMemberData(squareMemberList.get(i));
			}
			rowLayout.addView(participantItem, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			nColumn++;
		}
	}

	public void setSquareMemberData(List<SpSquareMemberVO> squareMemberList) {
		removeAllViews();
		// 행 추가 감지 변수
		int nColumn = 0;
		LinearLayout rowLayout = new LinearLayout(getContext());
		rowLayout.setOrientation(HORIZONTAL);
		addView(rowLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		LIMIT_ONE_LINE_COUNT = (getWidth()/ (int) PixelUtils.getDip(getResources(), 55))-1;
				
		for (int i = 0; i < squareMemberList.size(); ++i) {
			if (nColumn > LIMIT_ONE_LINE_COUNT) {
				nColumn = 0;
				rowLayout = new LinearLayout(getContext());
				rowLayout.setOrientation(HORIZONTAL);
				addView(rowLayout, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				LayoutParams p = (LayoutParams) rowLayout.getLayoutParams();
				p.bottomMargin = (int) PixelUtils.getDip(getResources(), 5);
			}
			final ParticipantItem participantItem = new ParticipantItem(getContext());
			if (m_listener != null) {
				participantItem.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						m_listener.onParticipantClick(participantItem);
					}
				});
			}
			participantItem.setBackgroundColor(0xFF00FF);
			if(squareMemberList.get(i)!=null) participantItem.setSquareMemberData(squareMemberList.get(i));
			rowLayout.addView(participantItem, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			nColumn++;
		}
	}
}
