package com.hs.mobile.gw.adapter.squareplus;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MenuListHelper.SpSquareType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteSquare;
import com.hs.mobile.gw.openapi.squareplus.SpSquareConst;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.util.ApiLoaderEx;

import android.graphics.Color;
import android.graphics.Typeface;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpSquareListAdapter extends BaseAdapter {
	private FragmentActivity context;
	private List<SpSquareVO> m_data;
	private SpSquareType m_workGroupType;

	public static class ViewHolder {
		public LinearLayout m_llSquareAllLayout;
		public LinearLayout m_llSquareLayout;
		public ImageView m_imgBullet;
		public TextView m_tvGroupName;
		public ImageView m_imgFavorite;
		public TextView m_tvAdmin;
		public TextView m_imgAdminList;
		public TextView m_tvGroupState;
	}

	public SpSquareListAdapter(FragmentActivity context, List<SpSquareVO> data, SpSquareType t) {
		this.context = context;
		m_data = data;
		m_workGroupType = t;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			int templeteId = R.layout.list_item_squareplus_square;
			convertView = LayoutInflater.from(parent.getContext()).inflate(templeteId, null, false);
			holder = new ViewHolder();
			holder.m_llSquareAllLayout = (LinearLayout) convertView.findViewById(R.id.ID_LL_SP_SQUARE);
			holder.m_llSquareLayout = (LinearLayout) convertView.findViewById(R.id.ID_LL_SP_SQUARE_CASE);
			holder.m_imgBullet = (ImageView) convertView.findViewById(R.id.ID_IMG_GROUP_BULLET);
			holder.m_tvGroupName = (TextView) convertView.findViewById(R.id.ID_TV_WORKGROUP_NAME);
			holder.m_imgFavorite = (ImageView) convertView.findViewById(R.id.ID_IMG_FAVORITE);
			holder.m_tvAdmin = (TextView) convertView.findViewById(R.id.ID_TV_ADMIN_LABEL);
			holder.m_imgAdminList = (TextView) convertView.findViewById(R.id.ID_TV_ADMIN_LIST);
			holder.m_tvGroupState = (TextView) convertView.findViewById(R.id.ID_TV_GROUP_STATE);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		if(getItem(position) !=null){
			holder.m_tvGroupName.setText(getItem(position).getTitle());
			if (getItem(position).isAdmin()) {
				holder.m_tvAdmin.setTypeface(null, Typeface.NORMAL);
				holder.m_tvAdmin.setBackgroundColor(Color.argb(0xff,0xe6,0x7b,0x7a));
				holder.m_tvAdmin.setTextColor(Color.WHITE);
			} else {
				holder.m_tvAdmin.setTypeface(null, Typeface.BOLD);
				holder.m_tvAdmin.setBackground(null);
				holder.m_tvAdmin.setTextColor(Color.argb(0xff,0x78,0x78,0x78));
			}
			// 관리자 목록
			if (getItem(position).getDbMemberList() != null) {
				String adminList = "";
				for (SpSquareMemberVO squareMemberVO : getItem(position).getDbMemberList()) {
					if (squareMemberVO != null) {
						if (squareMemberVO.getMemberRightsEnum() == MemberRights.ADMIN_USER) {
							adminList += "," + squareMemberVO.getMemberName();
						}
					}
				}
				holder.m_imgAdminList.setText(adminList.replaceFirst(",", ""));
			}

			// 즐겨찾기
			if (getItem(position).isFavorite()) {
//				holder.m_imgFavorite.setVisibility(View.VISIBLE);
				holder.m_imgFavorite.setImageResource(R.drawable.sp_group_bookmark_on);
			} else if (SpSquareConst.SQ_STATUS_END.equals(getItem(position).getStatus())) {
				holder.m_imgFavorite.setVisibility(View.GONE);
			} else {
//				holder.m_imgFavorite.setVisibility(View.VISIBLE);
				holder.m_imgFavorite.setImageResource(m_workGroupType.getIconRes());
			}
			if (getItem(position)!=null){
				// 새로운글은 붉은점으로
				if (getItem(position).getNewCount() > 0) {
					holder.m_imgBullet.setVisibility(View.VISIBLE);
				} else {
					holder.m_imgBullet.setVisibility(View.GONE);
				}			
			}
			
		}




		// 즐겨찾기 링크
		holder.m_imgFavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final ImageView imageView = (ImageView)v;
				SpSquareCallBack spSquareCallBack = new SpSquareCallBack(context, SpSquareVO.class){
					@Override
					public void callback(String url, JSONObject json, AjaxStatus status) {
						super.callback(url, json, status);
						setItem(position, this.item);

						if (getItem(position).isFavorite()) {
							imageView.setImageResource(R.drawable.sp_group_bookmark_on);
						} else {
							imageView.setImageResource(R.drawable.sp_group_bookmark);
						}
					}
				};

				{ // API명
					HashMap<String,String> params = new HashMap<>();
					params.put("squareId", getItem(position).getSquareId());

					if (getItem(position).isFavorite()) {
						params.put("favoriteFlag", "false");		// 1은 활성화
					} else {
						params.put("favoriteFlag", "true");		// 1은 활성화
					}

					new ApiLoaderEx<>(new SpAddFavoriteSquare(context), context, spSquareCallBack, params).execute();
				}
			}
		});
		
		if (m_workGroupType == SpSquareType.FAVORITE) {
			if(getItem(position)!=null){
				holder.m_tvGroupState.setVisibility(View.VISIBLE);
				if (getItem(position).isDefaultDept()) {
					holder.m_tvGroupState.setBackgroundColor(context.getResources().getColor(R.color.squareplus_org_group_background));
					holder.m_tvGroupState.setText(context.getResources().getString(R.string.label_squareplus_org_group));
				}
				else if (!getItem(position).isSecurity()) {
					holder.m_tvGroupState.setBackgroundColor(context.getResources().getColor(R.color.squareplus_public_group_background));
					holder.m_tvGroupState.setText(context.getResources().getString(R.string.label_squareplus_public_group));
				}
				else {
					holder.m_tvGroupState.setVisibility(View.GONE);
				}
			}
		}

		if (getCount() == position + 1) {
			holder.m_llSquareLayout.setBackground(null);
		} else {
			holder.m_llSquareLayout.setBackgroundResource(R.drawable.style_squarelist_background);
		}

		if(getItem(position)!=null){
			// 목록의 마지막인 경우엔 background를 제거하여 border line을 제거한다.
			if (getItem(position).isSelected()) {
				holder.m_llSquareAllLayout.setBackgroundColor(Color.argb(0xff,0xea,0xf1,0xf9));
				holder.m_llSquareLayout.setBackground(null);
			} else {
				holder.m_llSquareAllLayout.setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
			}
		}

		return convertView;
	}

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SpSquareVO getItem(int position) {
		return m_data != null ? m_data.get(position) : null ;
	}

	public SpSquareVO setItem(int position, SpSquareVO squareVO) {
		return m_data.set(position, squareVO);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

}
