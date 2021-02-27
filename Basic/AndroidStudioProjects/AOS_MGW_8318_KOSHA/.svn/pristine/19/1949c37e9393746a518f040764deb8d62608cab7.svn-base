package com.hs.mobile.gw.adapter.squareplus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.fragment.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MenuListHelper.SpSquareType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.fragment.squareplus.SpFolderSelectFragment;
import com.hs.mobile.gw.openapi.squareplus.SpAddFavoriteSquare;
import com.hs.mobile.gw.openapi.squareplus.SpDeleteFolderSquareMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquareMenuList;
import com.hs.mobile.gw.openapi.squareplus.SpInitMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFolderListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFolderSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderSquareVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;

public class SpFolderListAdapter extends BaseExpandableListAdapter {
	public FragmentActivity context;
	private List<SpFolderVO> m_data;
	ViewHolder holder;

	public static class ViewHolder {
		public LinearLayout m_llFolderView;
		public ImageView m_llFolderIcon;
		public TextView m_tvFolderName;
		public ImageView m_imgIndicator;

		public LinearLayout m_llChildLayout;
		public LinearLayout m_llChildSquareLayout;
		public ImageView m_imgChildBullet;
		public TextView m_tvChildGroupName;
		public TextView m_tvChildGroupCount;
		public TextView m_tvChildAdmin;
		public ImageView m_imgChildFavorite;
		public TextView m_imgChildAdminList;
	}

	public SpFolderListAdapter(FragmentActivity context, List<SpFolderVO> data) {
		this.context = context;
		this.m_data = data;
	}

	@Override
	public int getGroupCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		List<SpFolderSquareVO> folderSquareList = m_data.get(groupPosition).getFolderSquareVOList();
		return folderSquareList == null ? 0 : folderSquareList.size();
	}

	@Override
	public SpFolderVO getGroup(int groupPosition) {
		if (m_data != null && m_data.size() > 0)
			return m_data.get(groupPosition);
		else
			return null;
	}

	@Override
	public SpSquareVO getChild(int groupPosition, int childPosition) {
		if (getChildrenCount(groupPosition) > 0)
			return m_data.get(groupPosition).getFolderSquareVOList().get(childPosition).getSquareVO();
		else
			return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int position, boolean isExpanded, View convertView, ViewGroup parent) {
		View view = convertView;
		SpFolderVO folderVO = getGroup(position);

		// 미분류 그룹만 있을 경우엔 header를 숨긴다.
		if (getGroupCount() == 1) {
			if (view == null) {
				holder = new ViewHolder();
				view = LayoutInflater.from(context).inflate(R.layout.list_item_squareplus_folder_blank, parent, false);
				view.setTag(holder);
			} else {
				if (view.getId() == R.id.ID_LINEAR_FOLDERVIEW) {
					holder = new ViewHolder();
					view = LayoutInflater.from(context).inflate(R.layout.list_item_squareplus_folder_blank, parent, false);
					view.setTag(holder);
				}
				else {				
					holder = (ViewHolder) view.getTag();
				}
			}

			return view;
		} else {
			if (view == null) {
				holder = new ViewHolder();
				view = LayoutInflater.from(context).inflate(R.layout.list_item_squareplus_folder, parent, false);
				holder.m_llFolderView = (LinearLayout) view.findViewById(R.id.ID_LINEAR_FOLDERVIEW);
				holder.m_llFolderIcon = (ImageView) view.findViewById(R.id.ID_TV_FOLDER_ICON);
				holder.m_llFolderView.setSelected(false);
				holder.m_tvFolderName = (TextView) view.findViewById(R.id.ID_TV_FOLDER_NAME);
				holder.m_imgIndicator = (ImageView) view.findViewById(R.id.ID_IMG_INDICATOR);
	
				view.setTag(holder);
			} else {
				if (view.getId() == R.id.ID_LL_BLANK) {
					holder = new ViewHolder();
					view = LayoutInflater.from(context).inflate(R.layout.list_item_squareplus_folder, parent, false);
					holder.m_llFolderView = (LinearLayout) view.findViewById(R.id.ID_LINEAR_FOLDERVIEW);
					holder.m_llFolderIcon = (ImageView) view.findViewById(R.id.ID_TV_FOLDER_ICON);
					holder.m_llFolderView.setSelected(false);
					holder.m_tvFolderName = (TextView) view.findViewById(R.id.ID_TV_FOLDER_NAME);
					holder.m_imgIndicator = (ImageView) view.findViewById(R.id.ID_IMG_INDICATOR);
		
					view.setTag(holder);
				}
				else {	
					holder = (ViewHolder) view.getTag();
				}
			}
		}

		if (folderVO != null) {
			// folderView START
			holder.m_tvFolderName.setText(folderVO.getFolderName());
			
			if (folderVO.getFolderId() == null) {
				holder.m_llFolderIcon.setVisibility(View.GONE);
				holder.m_imgIndicator.setVisibility(View.GONE);
			} else if (folderVO.getFolderSquareVOList() == null || folderVO.getFolderSquareVOList().size() == 0) {
				holder.m_llFolderIcon.setVisibility(View.VISIBLE);
				holder.m_imgIndicator.setVisibility(View.GONE);
			} else {
				holder.m_llFolderIcon.setVisibility(View.VISIBLE);
				holder.m_imgIndicator.setVisibility(View.VISIBLE);
			}
			// folderView END
			
			if (isExpanded) {
				holder.m_imgIndicator.setSelected(true);
			} else {
				holder.m_imgIndicator.setSelected(false);
			}
		}

		return view;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			holder = new ViewHolder();
			int templeteId = R.layout.list_item_squareplus_square;
			view = LayoutInflater.from(context).inflate(templeteId, null);
			holder = new ViewHolder();
			holder.m_llChildLayout = (LinearLayout) view.findViewById(R.id.ID_LL_SP_SQUARE);
			holder.m_llChildSquareLayout = (LinearLayout) view.findViewById(R.id.ID_LL_SP_SQUARE_CASE);
			holder.m_imgChildBullet = (ImageView) view.findViewById(R.id.ID_IMG_GROUP_BULLET);
			holder.m_tvChildGroupName = (TextView) view.findViewById(R.id.ID_TV_WORKGROUP_NAME);
			holder.m_tvChildGroupCount = (TextView) view.findViewById(R.id.ID_TV_WORKGROUP_COUNT);
			holder.m_tvChildAdmin = (TextView) view.findViewById(R.id.ID_TV_ADMIN_LABEL);
			holder.m_imgChildFavorite = (ImageView) view.findViewById(R.id.ID_IMG_FAVORITE);
			holder.m_imgChildAdminList = (TextView) view.findViewById(R.id.ID_TV_ADMIN_LIST);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if (getChild(groupPosition, childPosition) != null) {
			holder.m_tvChildGroupName.setText(getChild(groupPosition, childPosition).getTitle());
			
			if (getChild(groupPosition, childPosition).isAdmin()) {
				holder.m_tvChildAdmin.setTypeface(null, Typeface.NORMAL);
				holder.m_tvChildAdmin.setBackgroundColor(Color.argb(0xff,0xe6,0x7b,0x7a));
				holder.m_tvChildAdmin.setTextColor(Color.WHITE);
			} else {
				holder.m_tvChildAdmin.setTypeface(null, Typeface.BOLD);
				holder.m_tvChildAdmin.setBackground(null);
				holder.m_tvChildAdmin.setTextColor(Color.argb(0xff,0x78,0x78,0x78));
			}
			
			// 관리자 목록
			String adminList = "";
			if (getChild(groupPosition, childPosition).getDbMemberList() != null) {
				for (SpSquareMemberVO squareMemberVO : getChild(groupPosition, childPosition).getDbMemberList()) {
					if (squareMemberVO.getMemberRightsEnum() == MemberRights.ADMIN_USER) {
						adminList += "," + squareMemberVO.getMemberName();
					}
				}
			}
			
			holder.m_imgChildAdminList.setText(adminList.replaceFirst(",", ""));
			
			// 새로운글은 붉은점으로
			if (getChild(groupPosition, childPosition).getNewCount() > 0) {
				holder.m_imgChildBullet.setVisibility(View.VISIBLE);
				holder.m_tvChildGroupCount.setVisibility(View.VISIBLE);
				holder.m_tvChildGroupCount.setText(String.valueOf(getChild(groupPosition, childPosition).getNewCount()));
			} else {
				holder.m_imgChildBullet.setVisibility(View.GONE);
				holder.m_tvChildGroupCount.setVisibility(View.GONE);
			}


			// 즐겨찾기
			if (getChild(groupPosition, childPosition).isFavorite()) {
				holder.m_imgChildFavorite.setImageResource(R.drawable.sp_group_bookmark_on);
			} else {
				holder.m_imgChildFavorite.setImageResource(R.drawable.sp_group_bookmark);
			}

			// 해당 그룹 링크
			holder.m_llChildLayout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					getChild(groupPosition, childPosition).setNewCount(0);

					// 선택된거 모두 초기화
					for (int groupPosition=0; groupPosition<getGroupCount(); groupPosition++) {
						for (int childPosition=0; childPosition<getChildrenCount(groupPosition); childPosition++) {
							getChild(groupPosition, childPosition).setSelected(false);
						}
					}

					getChild(groupPosition, childPosition).setSelected(true);
					MainModel.getInstance().goToGroup(context, getChild(groupPosition, childPosition));
					notifyDataSetChanged();
				}
			});

			holder.m_llChildLayout.setOnLongClickListener(new OnLongClickListener() {

				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					Debug.trace("");
					getChild(groupPosition, childPosition).setNewCount(0);

					// 선택된거 모두 초기화
					for (int groupPosition=0; groupPosition<getGroupCount(); groupPosition++) {
						for (int childPosition=0; childPosition<getChildrenCount(groupPosition); childPosition++) {
							getChild(groupPosition, childPosition).setSelected(false);
						}
					}

					getChild(groupPosition, childPosition).setSelected(true);

					boolean showCancel = true;
					if (TextUtils.isEmpty(getChild(groupPosition, childPosition).getFolderId()))
						showCancel = false;

					showItemLongClickDialog(context, getChild(groupPosition, childPosition), showCancel);

					return false;
				}
			});

			// 즐겨찾기 링크
			holder.m_imgChildFavorite.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final ImageView imageView = (ImageView)v;
					SpSquareCallBack spSquareCallBack = new SpSquareCallBack(context, SpSquareVO.class){
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);
							m_data.get(groupPosition).getFolderSquareVOList().get(childPosition).setSquareVO(this.item);

							if (getChild(groupPosition, childPosition).isFavorite()) {
								imageView.setImageResource(R.drawable.sp_group_bookmark_on);
							} else {
								imageView.setImageResource(R.drawable.sp_group_bookmark);
							}
						}
					};

					{ // API명
						HashMap<String,String> params = new HashMap<>();
						params.put("squareId", getChild(groupPosition, childPosition).getSquareId());

						if (getChild(groupPosition, childPosition).isFavorite()) {
							params.put("favoriteFlag", "false");		// 1은 활성화
						} else {
							params.put("favoriteFlag", "true");		// 1은 활성화
						}

						new ApiLoaderEx<>(new SpAddFavoriteSquare(context), context, spSquareCallBack, params).execute();
					}
				}
			});

			// 목록의 마지막인 경우엔 background를 제거하여 border line을 제거한다.
			if (getChildrenCount(groupPosition) == childPosition + 1) {
				holder.m_llChildSquareLayout.setBackground(null);
			} else {
				holder.m_llChildSquareLayout.setBackgroundResource(R.drawable.style_squarelist_background);
			}
		
			// 목록의 마지막인 경우엔 background를 제거하여 border line을 제거한다.
			if(getChild(groupPosition, childPosition)!=null){
				if (getChild(groupPosition, childPosition).isSelected()) {
					holder.m_llChildLayout.setBackgroundColor(Color.argb(0xff,0xea,0xf1,0xf9));
					holder.m_llChildSquareLayout.setBackground(null);
				} else {
					holder.m_llChildLayout.setBackgroundColor(Color.argb(0xff,0xff,0xff,0xff));
				}
			}
			
		}

		return view;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
//		Debug.trace("isChildSelectable method is on!! " + groupPosition + ", " + childPosition);
		return false;
	}
	
	public void showItemLongClickDialog(final FragmentActivity fragment, final SpSquareVO item,final boolean showCancle) {
		Debug.trace("squareId = ",item.getSquareId());
		Debug.trace("FolderId = ",item.getFolderId());
		
		String[] items;
		if (showCancle) {
			items = new String[] { fragment.getString(R.string.label_squareplus_menu_group_cancel) };
		}
		else {
			items = new String[] { fragment.getString(R.string.label_squareplus_menu_group_mov)};
		}
			
		new AlertDialog.Builder(fragment).setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				if (!showCancle) {
					MainModel
							.getInstance()
							.showSubActivity(
									fragment,
									SubActivityType.SP_GROUP_FOLDER,
									new BundleUtils.Builder()
											.add(SpFolderSelectFragment.ARG_KEY_SP_SELECT_TYPE,
													SpFolderSelectFragment.SpSelectType.SQUARE_ADD)
											.add(SpFolderSelectFragment.ARG_KEY_SP_FOLDER_LIST,
													(Serializable) MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData)
											.add(SpFolderSelectFragment.ARG_KEY_SP_SQUARE_ID, item.getSquareId())
											.add(SpFolderSelectFragment.ARG_KEY_SP_FOLDER_ID, item.getFolderId()).build());
				}
				else {
					PopupUtil.showLoading(fragment);
					SpFolderSquareCallBack spContentsCallBack = new SpFolderSquareCallBack(fragment, SpFolderSquareVO.class) {
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);
							Debug.trace(json.toString());
							PopupUtil.hideLoading(fragment);
							if (this.item != null) {
								Debug.trace("refresh!");
								HashMap<String, String> parameter = new HashMap<String, String>();
								parameter.put("squareType", SpSquareType.ING.getType());
								SpSquareListCallBack spSquareListCallBack = new SpSquareListCallBack(fragment, SpSquareVO.class) {
									@Override
									public void callback(String url, JSONObject json, AjaxStatus status) {
										super.callback(url, json, status);
										
										if (status.getCode() != 200) {
											MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
											.onRefreshComplete();
											return;
										} else {
											// 스퀘어목록을 폴더용으로 변환시킨다.
											final SpFolderVO folderVO = convertSquareToFolder(this.dataList);
											
											SpFolderListCallBack spFolderListCallBack = new SpFolderListCallBack(fragment, SpFolderVO.class) {
												@Override
												public void callback(String url, JSONObject json, AjaxStatus status) {
													super.callback(url, json, status);
													
													if (status.getCode() != 200) {
														MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
														.onRefreshComplete();
													}
													
													MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
													.clear();
													MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
													.addAll(this.dataList);
													MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
													.add(folderVO);
													
													String msgStr = String.format(fragment.getString(R.string.ptr_last_updated), MainModel
															.getInstance().getMainFragment().getMenuListHelper().lastUpdatedDateFormat
															.format(new Date()));
													MainFragment.mListFooter.setText(msgStr);
													
													if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
														try {
															int index = 0;
															while (MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
																	.get(index).getFolderSquareVOList().size() == 0) {
																index++;
															}
															
															MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
															.get(index).getFolderSquareVOList().get(0).getSquareVO()
															.setSelected(true);
															MainModel
															.getInstance()
															.goToGroup(
																	fragment,
																	MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
																	.get(index).getFolderSquareVOList().get(0)
																	.getSquareVO());
														} catch (NullPointerException e) {
															Debug.trace(e);
														} catch (IndexOutOfBoundsException e) {
															Debug.trace(e);
														} catch (Exception e) {
															Debug.trace(e);
														}
														
														// MainModel.getInstance().goToGroup(activity,
														// m_spSquareListData.get(0));
													}
													
													MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
													.post(new Runnable() {
														@Override
														public void run() {
															MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListAdapter
															.notifyDataSetChanged();
															
															for (int i = 0; i < MainModel.getInstance().getMainFragment()
																	.getMenuListHelper().m_spFolderListData.size(); i++) {
																MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
																.getRefreshableView().expandGroup(i);
															}
															
															MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
															.onRefreshComplete();
														}
													});
												}
											};
											
											{ // API명
												HashMap<String, String> params = new HashMap<>();
												new ApiLoaderEx<>(new SpInitMySquareMenu(fragment), fragment, spFolderListCallBack, params)
												.execute();
											}
										}
									}
									
									private SpFolderVO convertSquareToFolder(List<SpSquareVO> squareList) {
										SpFolderVO folderVO = new SpFolderVO();
										folderVO.setFolderName(fragment.getString(R.string.label_squareplus_nofolder_square));
										List<SpFolderSquareVO> folderSquareList = new ArrayList<SpFolderSquareVO>();
										
										for (SpSquareVO squareVO : squareList) {
											if (!squareVO.isFolder()) {
												SpFolderSquareVO folderSquareVO = new SpFolderSquareVO();
												folderSquareVO.setSquareVO(squareVO);
												folderSquareList.add(folderSquareVO);
											}
										}
										
										folderVO.setFolderSquareVOList(folderSquareList);
										
										return folderVO;
									}
								};
								new ApiLoaderEx<>(new SpGetSquareMenuList(fragment), fragment, spSquareListCallBack, parameter).execute();
							}
						}
					};
					
					HashMap<String, String> params = new HashMap<>();
					params.put("squareId", item.getSquareId());
					params.put("folderId", item.getFolderId());
					new ApiLoaderEx<>(new SpDeleteFolderSquareMySquareMenu(fragment), fragment, spContentsCallBack, params).execute();
				}
			}
		}).show();
	}
	
//	public void showItemLongClickDialog(final FragmentActivity fragment, final SpSquareVO item,final boolean showCancle) {
//		Debug.trace("squareId = ",item.getSquareId());
//		Debug.trace("FolderId = ",item.getFolderId());
//		
//		String[] items;
//		if (showCancle) {
////			items = new String[] { fragment.getString(R.string.label_squareplus_menu_group_mov),
////					fragment.getString(R.string.label_squareplus_menu_group_cancel) };
//			items = new String[] { fragment.getString(R.string.label_squareplus_menu_group_mov),
//					fragment.getString(R.string.label_squareplus_menu_group_cancel) };
//		}
//		else {
//			items = new String[] { fragment.getString(R.string.label_squareplus_menu_group_mov)};
//		}
//			
//		new AlertDialog.Builder(fragment).setItems(items, new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				
//				switch (which) {
//				case 0: {
//					MainModel
//							.getInstance()
//							.showSubActivity(
//									fragment,
//									SubActivityType.SP_GROUP_FOLDER,
//									new BundleUtils.Builder()
//											.add(SpFolderSelectFragment.ARG_KEY_SP_SELECT_TYPE,
//													showCancle ? SpFolderSelectFragment.SpSelectType.SQUARE_MOVE
//															: SpFolderSelectFragment.SpSelectType.SQUARE_ADD)
//											.add(SpFolderSelectFragment.ARG_KEY_SP_FOLDER_LIST,
//													(Serializable) MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData)
//											.add(SpFolderSelectFragment.ARG_KEY_SP_SQUARE_ID, item.getSquareId())
//											.add(SpFolderSelectFragment.ARG_KEY_SP_FOLDER_ID, item.getFolderId()).build());
//				}
//					break;
//				case 1: {
//					PopupUtil.showLoading(fragment);
//					SpFolderSquareCallBack spContentsCallBack = new SpFolderSquareCallBack(fragment, SpFolderSquareVO.class) {
//						@Override
//						public void callback(String url, JSONObject json, AjaxStatus status) {
//							super.callback(url, json, status);
//							Debug.trace(json.toString());
//							PopupUtil.hideLoading(fragment);
//							if (this.item != null) {
//								Debug.trace("refresh!");
//								HashMap<String, String> parameter = new HashMap<String, String>();
//								parameter.put("squareType", SpSquareType.ING.getType());
//								SpSquareListCallBack spSquareListCallBack = new SpSquareListCallBack(fragment, SpSquareVO.class) {
//									@Override
//									public void callback(String url, JSONObject json, AjaxStatus status) {
//										super.callback(url, json, status);
//
//										if (status.getCode() != 200) {
//											MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
//													.onRefreshComplete();
//											return;
//										} else {
//											// 스퀘어목록을 폴더용으로 변환시킨다.
//											final SpFolderVO folderVO = convertSquareToFolder(this.dataList);
//
//											SpFolderListCallBack spFolderListCallBack = new SpFolderListCallBack(fragment, SpFolderVO.class) {
//												@Override
//												public void callback(String url, JSONObject json, AjaxStatus status) {
//													super.callback(url, json, status);
//
//													if (status.getCode() != 200) {
//														MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
//																.onRefreshComplete();
//													}
//
//													MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
//															.clear();
//													MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
//															.addAll(this.dataList);
//													MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
//															.add(folderVO);
//
//													String msgStr = String.format(fragment.getString(R.string.ptr_last_updated), MainModel
//															.getInstance().getMainFragment().getMenuListHelper().lastUpdatedDateFormat
//															.format(new Date()));
//													MainFragment.mListFooter.setText(msgStr);
//
//													if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
//														try {
//															int index = 0;
//															while (MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
//																	.get(index).getFolderSquareVOList().size() == 0) {
//																index++;
//															}
//
//															MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
//																	.get(index).getFolderSquareVOList().get(0).getSquareVO()
//																	.setSelected(true);
//															MainModel
//																	.getInstance()
//																	.goToGroup(
//																			fragment,
//																			MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListData
//																					.get(index).getFolderSquareVOList().get(0)
//																					.getSquareVO());
//														} catch (NullPointerException e) {
//															Debug.trace(e);
//														} catch (IndexOutOfBoundsException e) {
//															Debug.trace(e);
//														} catch (Exception e) {
//															Debug.trace(e);
//														}
//
//														// MainModel.getInstance().goToGroup(activity,
//														// m_spSquareListData.get(0));
//													}
//
//													MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
//															.post(new Runnable() {
//																@Override
//																public void run() {
//																	MainModel.getInstance().getMainFragment().getMenuListHelper().m_spFolderListAdapter
//																			.notifyDataSetChanged();
//
//																	for (int i = 0; i < MainModel.getInstance().getMainFragment()
//																			.getMenuListHelper().m_spFolderListData.size(); i++) {
//																		MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
//																				.getRefreshableView().expandGroup(i);
//																	}
//
//																	MainModel.getInstance().getMainFragment().getMenuListHelper().m_lvSquareExpandableGroup
//																			.onRefreshComplete();
//																}
//															});
//												}
//											};
//
//											{ // API명
//												HashMap<String, String> params = new HashMap<>();
//												new ApiLoaderEx<>(new SpInitMySquareMenu(fragment), fragment, spFolderListCallBack, params)
//														.execute();
//											}
//										}
//									}
//
//									private SpFolderVO convertSquareToFolder(List<SpSquareVO> squareList) {
//										SpFolderVO folderVO = new SpFolderVO();
//										folderVO.setFolderName(fragment.getString(R.string.label_squareplus_nofolder_square));
//										List<SpFolderSquareVO> folderSquareList = new ArrayList<SpFolderSquareVO>();
//
//										for (SpSquareVO squareVO : squareList) {
//											if (!squareVO.isFolder()) {
//												SpFolderSquareVO folderSquareVO = new SpFolderSquareVO();
//												folderSquareVO.setSquareVO(squareVO);
//												folderSquareList.add(folderSquareVO);
//											}
//										}
//
//										folderVO.setFolderSquareVOList(folderSquareList);
//
//										return folderVO;
//									}
//								};
//								new ApiLoaderEx<>(new SpGetSquareMenuList(fragment), fragment, spSquareListCallBack, parameter).execute();
//							}
//						}
//					};
//
//					HashMap<String, String> params = new HashMap<>();
//					params.put("squareId", item.getSquareId());
//					params.put("folderId", item.getFolderId());
//					new ApiLoaderEx<>(new SpDeleteFolderSquareMySquareMenu(fragment), fragment, spContentsCallBack, params).execute();
//
//				}
//					break;
//				}
//			}
//		}).show();
//	}
}