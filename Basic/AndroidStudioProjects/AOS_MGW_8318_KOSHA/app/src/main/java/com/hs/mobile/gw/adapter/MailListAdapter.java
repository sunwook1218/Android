package com.hs.mobile.gw.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.openapi.ReadMail;
import com.hs.mobile.gw.openapi.ReadMailCallBack;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.DateUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.MGWUtils;
import com.hs.mobile.gw.view.OnScrollListView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MailListAdapter extends MGWBaseAdapter implements OnItemLongClickListener{
	String mailBoxID = null;
	boolean isItemDeleted = false;

	public MailListAdapter(MainFragment f, ApiCode code, int initPno, List<NameValuePair> params, boolean isSearch) {
		super(f);
		this.layout = R.layout.template_maillist;
		this.apiCode = code;
		this.currentPno = initPno;
		this.initPno = initPno;
		this.mSrcArray = null;
		this.extraParams = params;
		this.mAdapter = this;
		this.isSearchMode = isSearch;
		if (setLoadingInProgress(true)) {
			initData();
		}
		showSearchMaillist();
	}

	public void setParameters(List<NameValuePair> params) {
		this.extraParams = params;
	}

	public void showSearchMaillist() {
		getController().showSearchMaillist();
	}

	public ArrayList<JSONObject> checkedItemList = new ArrayList<JSONObject>();

	@Override
	public void deleteSelectedItems() {
		new DeleteDataTask().execute();
	}

	@Override
	public void deleteItemsFromList(ArrayList<JSONObject> items) {
		isItemDeleted = true;
		for (Iterator<JSONObject> i = items.iterator(); i.hasNext();) {
			String id = i.next().optString("msgid").trim();
			for (int j = 0; j < mSrcArray.size(); j++) {
				JSONObject item = mSrcArray.get(j);
				if (selectedItemID != null && TextUtils.equals(selectedItemID, item.optString("msgid").trim())) {
					isSelectedItemDeleted = true;
				} else if (selectedItemID == null && !MainModel.getInstance().isTablet()) {
					isSelectedItemDeleted = true;
				}
				if (TextUtils.equals(id, item.optString("msgid").trim())) {
					mSrcArray.remove(j);
					total--;
					isItemDeleted = true;
				}
			}
		}
		mAdapter.updateListview();
		if (isSelectedItemDeleted) {
			if (MainModel.getInstance().isTablet()) {
				if (!isPreventSearchDeleted) {
					if (mSrcArray.size() > 0) {
						showItem(selectedItemPosition);
					}
					else {
						initData();
						showEmptyPage();
					}
				}
				isPreventSearchDeleted = false;
			} else {
				if (!isPreventSearchDeleted) {
					if (mSrcArray.size() <= 0) {
						Debug.trace("");
						initData();
					}
				}
				isPreventSearchDeleted = false;
				selectedItemPosition = -1;
				mAdapter.updateListview();
			}
			isSelectedItemDeleted = false;
		}

	}

	private class DeleteDataTask extends AsyncTask<String, String, String> {
		@Override
		protected String doInBackground(String... arg0) {

			if (checkedItemList == null || checkedItemList.size() == 0) {
				return null;
			}
			StringBuffer msgIDs = null;
			StringBuffer saveIDs = null;
			for (Iterator<JSONObject> i = checkedItemList.iterator(); i.hasNext();) {
				JSONObject item = i.next();
				if (msgIDs == null) {
					msgIDs = new StringBuffer();
					msgIDs.append(item.optString("msgid").trim());
				} else {
					msgIDs.append(',');
					msgIDs.append(item.optString("msgid").trim());
				}
				if (saveIDs == null) {
					saveIDs = new StringBuffer();
					saveIDs.append(item.optString("saveid"));
				} else {
					saveIDs.append(',');
					saveIDs.append(item.optString("saveid"));
				}
			}
			if(saveIDs==null||msgIDs==null) return null;
			String purge = "false";
			if (apiCode == ApiCode.mail_deletelist) {
				// purge = "true"; // 그룹웨어 OpenAPI에서 오류 발생으로 임시 보류(이 값에 의한 영향도가
				// 현재는 없는 상태)
			}
			boolean success = false;
			if(MainModel.getInstance().getOpenApiService()!=null){
				success = MainModel.getInstance().getOpenApiService().deleteMail(getActivity(), mailBoxID, msgIDs.toString(), saveIDs.toString(), purge);
			}
			if (success) {
				isItemDeleted = true;
				for (Iterator<JSONObject> i = checkedItemList.iterator(); i.hasNext();) {
					total--;
					JSONObject item = i.next();
					if (isSearchMode) {
						if (mDeletedItemArray == null) {
							mDeletedItemArray = new ArrayList<JSONObject>();
						}
						mDeletedItemArray.add(item);
					}
					if (selectedItemID != null && TextUtils.equals(selectedItemID, item.optString("msgid").trim())) {
						isSelectedItemDeleted = true;
					} else if (selectedItemID == null && !MainModel.getInstance().isTablet()) {
						isSelectedItemDeleted = true;
					}
					mSrcArray.remove(item);
				}
				// 검색 모드에서 삭제한 경우 이전 메일리스트에서도 삭제된 메일을 제거하기 위함
				if (isSearchMode && mDeletedItemArray != null) {
					getController().checkDeletedMailItems(mDeletedItemArray);
					mDeletedItemArray.clear();
				}

				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (isSelectedItemDeleted) {
							if (MainModel.getInstance().isTablet()) {
								if (!isPreventSearchDeleted) {
									if (mSrcArray.size() > 0) {
										showItem(selectedItemPosition);
									}
									else {
										initData();
										showEmptyPage();
									}
								}
								isPreventSearchDeleted = false;
							} else {
								if (!isPreventSearchDeleted) {
									if (mSrcArray.size() <= 0) {
										initData();
									}
								}
								isPreventSearchDeleted = false;
								selectedItemPosition = -1;
								mAdapter.updateListview();
							}
							isSelectedItemDeleted = false;
						}
					}
				});
			}
			clearCheckBox();
			hideDeletingDialog();
			mAdapter.updateListview();
			// updateRecommendationMessage();// 목록 업데이트 권고 메시지 - 임시(그룹웨어의 메일
			// 아이디가 바뀌는 문제로 인함)
			return null;
		}

	}

	public void hideDeletingDialog() {
		getController().hideLoadingProgressDialog();
	}

	@Override
	public void clearCheckBox() {
		if (mSrcArray == null)
			return;
		for (Iterator<JSONObject> i = mSrcArray.iterator(); i.hasNext();) {
			try {
				(i.next()).put("isChecked", false);
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			}
		}
		checkedItemList.clear();
		updateSelectedMailCount();
	}

	public void updateSelectedMailCount() {
		getController().updateDeleteMailCount(MainFragment.mDeleteMailFooter, checkedItemList.size());
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		if (total == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JSONObject item = mSrcArray.get(position);
		ImageView statusView;
		ImageView emergencyView;
		ImageView forwardView;
		ImageView securityView;
		TextView fromView;
		ImageView attachView;
		TextView dateView;
		TextView titleView;
		CheckBox checkBox;
		ImageView indicatorView;
		if (convertView == null) {
			if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
				convertView = LayoutInflater.from(parent.getContext()).inflate(emptyLayout, parent, false);
			} else {
				convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
			}
		}

		if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
			return convertView;
		}

		checkBox = (CheckBox) convertView.findViewById(R.id.mailCheckBox);
		checkBox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox cb = (CheckBox) v;
				JSONObject item = (JSONObject) cb.getTag();
				try {
					if (cb.isChecked()) {
						checkedItemList.add(item);
						if (mailBoxID == null) {
							mailBoxID = item.optString("boxid");
						}
					} else {
						checkedItemList.remove(item);
					}
					updateSelectedMailCount();
					item.put("isChecked", cb.isChecked());
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}
			}
		});

		checkBox.setTag(item);
		checkBox.setChecked(item.optBoolean("isChecked"));

		statusView = (ImageView) convertView.findViewById(R.id.mail_status);
		emergencyView = (ImageView) convertView.findViewById(R.id.mail_emergency);
		forwardView = (ImageView) convertView.findViewById(R.id.mail_forward);
		securityView = (ImageView) convertView.findViewById(R.id.mail_security);

		fromView = (TextView) convertView.findViewById(R.id.mail_from);
		attachView = (ImageView) convertView.findViewById(R.id.mail_attach);
		dateView = (TextView) convertView.findViewById(R.id.mail_date);
		titleView = (TextView) convertView.findViewById(R.id.mail_title);
		indicatorView = (ImageView) convertView.findViewById(R.id.mail_indicator);

		indicatorView.setVisibility(indicatorVisibility);
		checkBox.setVisibility(checkBoxVisibility);
		boolean isattachment = false;
		try {
			isattachment = item.getBoolean("isattachment");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Debug.trace(e.getMessage());
		}
		int status = item.optInt("status");
		boolean replyflag = item.optBoolean("replyflag");
		boolean readflag = item.optBoolean("readflag");
		int priority = item.optInt("priority");
		boolean security = item.optBoolean("security");

		String title = item.optString("title").trim();
		String date = DateUtils.getDateStringFromGWDate(item.optString("sentdateformat"));
		String from;
		if (apiCode == ApiCode.mail_sendlist) {
			from = item.optString("receiver").trim();
		} else {
			from = item.optString("sender").trim();
		}
		int emailIndex = from.indexOf("<");
		if (emailIndex > 1) {
			from = from.substring(0, emailIndex);
		}
		fromView.setText(from);
		titleView.setText(title);
		dateView.setText(date);

		// 메일 상태 아이콘 세팅
		int imageResourceID;
		if (status == 11) { // 회수
			imageResourceID = R.drawable.icon_mail_recovery;
		} else if (replyflag) { // 답장
			imageResourceID = R.drawable.icon_mail_reply;
		} else if (readflag) { // 읽음
			imageResourceID = R.drawable.icon_mail_read;
		} else { // if (status == 1 || status == 0){ //안 읽음 0:새메일,
			imageResourceID = R.drawable.icon_mail;
		}
		statusView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(imageResourceID));

		// 메일 긴급 아이콘
		if (priority == 1) {
			emergencyView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.icon_add_e));
		} else {
			emergencyView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
		}

		// 메일 보안 아이콘
		if (security) {
			securityView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.icon_add_k));
		} else {
			securityView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
		}

		// 메일 첨부 아이콘
		if (isattachment) {
			attachView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ic_mail_attach));
		} else {
			attachView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
		}

		if (selectedItemPosition != -1 && position == (selectedItemPosition - 1)) {
			convertView.setBackgroundResource(R.drawable.default_list_selected);
		} else {
			convertView.setBackgroundResource(R.drawable.style_list_background);
		}

		return convertView;

	}

	@Override
	public void initData() {
		isEditMode = false;
		isFirstPage = true;
		isShowFirstRow = true;
		currentPno = initPno;
		isItemDeleted = false;
		mSrcArray = new ArrayList<JSONObject>();
		clearCheckBox();
		if (checkBoxVisibility == View.VISIBLE)
			getController().editMailList(null);
		loadData();
	}

	@Override
	public void loadData() {
		new LoadDataTask().execute();
	}

	private class LoadDataTask extends AsyncTask<String, String, String> {

		private LoadDataTask() {
			super();
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			mAdapter.setLoadingFinished();
		}

		@Override
		protected String doInBackground(String... param) {
			JSONObject data = null;
			if(MainModel.getInstance().getOpenApiService()==null) return null;
			if(MainModel.getInstance().getOpenApiService().getMailList(getActivity(), apiCode, currentPno, extraParams, isSearchMode)!=null){
				data = MainModel.getInstance().getOpenApiService().getMailList(getActivity(), apiCode, currentPno, extraParams, isSearchMode);
			}
			try {
				if (data != null) {
					if (currentPno == initPno) {
						total = data.getJSONObject("channel").getInt("total");
						pageSize = data.getJSONObject("channel").getInt("pagesize");
					}
					if (total == 0) {
						setForEmptyList();
					} else {
						JSONArray jArray = data.getJSONObject("channel").optJSONArray("item");
						if (jArray == null) {
							Debug.trace("isItemDeleted = " + isItemDeleted);
							if (!isItemDeleted) {
								JSONObject item = data.getJSONObject("channel").optJSONObject("item");
								item.put("isChecked", false);
								if (TextUtils.equals(HMGWServiceHelper.mail_type, "db")) {
									item.put("msgid", item.optString("saveid"));
								}
								mSrcArray.add(item);
							}
						} else {
							int i = 0;
							if (isItemDeleted) {
								i = (mSrcArray.size() % pageSize); // 기존에 리스트에
																	// 삭제된 갯수만
																	// 추가하기 위함
							}
							for (; i < jArray.length(); i++) {
								JSONObject item = (JSONObject) jArray.get(i);
								item.put("isChecked", false);
								if (TextUtils.equals(HMGWServiceHelper.mail_type, "db")) {
									item.put("msgid", item.optString("saveid"));
								}
								mSrcArray.add(item);
							}
						}
					}
					if (listView == null) {
						listView = new OnScrollListView(getMainFragment().getActivity());
						listView.setAdapter(mAdapter);
						listView.setOnItemLongClickListener(MailListAdapter.this);
						setListView();
						mAdapter.addListViewToMiddleFlipper();
					} else {
						mAdapter.updateListview();
					}
				} else {
					if (currentPno != initPno) {
						currentPno--;
					}
				}
				isItemDeleted = false;
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						updateOtherViews();
					}
				});
				if (isShowFirstRow) {
					showFirstRow();
					isShowFirstRow = false;
				}

			}catch (JSONException e) {
				Debug.trace(e.getMessage());
			}catch (Exception e) {
				Debug.trace(e.getMessage());
			}
			return null;
		}
	}

	@Override
	public void getMoreData() {
		if (isItemDeleted) {
			currentPno = mSrcArray.size() / pageSize;
		}
		currentPno++;
		// toastNewPageNo();
		loadData();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		listView.setItemChecked(position, true);
		if (!isRefreshing()) {
			JSONObject item = (JSONObject) getItem(position);
			if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
				return;
			}
			showItem(position);
		}
	}

	@Override
	public void showFirstRow() {
		if (MainModel.getInstance().isTablet() && total > 0 && !MainModel.getInstance().isPopupMode()) {
			if (!MainModel.getInstance().isWebViewLoaded()) {
			} else {
				if (MainModel.getInstance().isTablet() ) {
					if (!isPreventSearchDeleted) {
						if (mSrcArray.size() > 0) {
							showItem(1);
						} else {
							// 테블릿의 경우 빈화면 필요...
							showEmptyPage();
						}
					}
					isPreventSearchDeleted = false;
				}
			}
		}
	}

	@Override
	public void showItem(final int position) {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				selectedItemPosition = position;
				JSONObject item = (JSONObject) getItem(selectedItemPosition);
				selectedItemID = item.optString("msgid").trim();
				String link = "";

				if (TextUtils.equals(HMGWServiceHelper.mail_type, "db")) {
					link = item.optString("link").trim();
				} else {
					link = item.optString("link2").trim();
				}
				String type = MGWUtils.getTypeStringByApiCode(apiCode);
				boolean security = item.optBoolean("security");
				if (security) {// 보안 메일 이면
					getController().checkPasswordForMail(link, type);// link,
				} else {
					getController().showMailDetails(link, type);// link,
				}
				mAdapter.updateListview();
			}
		});
	}

	public void setMailItemToRead(JSONObject item) {
		int status = item.optInt("status");
		boolean replyflag = item.optBoolean("replyflag");

		if (status != 11 && !replyflag) {
			try {
				item.putOpt("status", "2");
				item.putOpt("readflag", true);
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			}
		}
	}

	public void setMailItemToRead() {
		JSONObject item = (JSONObject) getItem(selectedItemPosition);
		setMailItemToRead(item);
		mAdapter.updateListview();
	}

	@Override
	public boolean hasMoreData() {
		if (maxPno == -1 && total != 0 && pageSize != 0) {
			maxPno = (int) Math.ceil((double) total / (double) pageSize);
		}
		if (currentPno == maxPno && total > pageSize) {
			if (MainModel.getInstance().isWebViewLoaded()) {
				notiLastPage();
			}
			notifiedLastPage = true;
		} else {
			notifiedLastPage = false;
		}
		return currentPno < maxPno;
	}

	@Override
	public void updateOtherViews() {
		if (total > 0) {// 목록이 있으면 편집버튼 보이기
			if (isEditMode)
				return; // 편집모드이면 그냥 리턴
			getController().initEditMailButton();
		} else {
			getController().hideEditMailButton();
		}
		// 메일쓰기버튼 보이기
		getController().setWriteButtonForMail();
		// 검색옵션 레이블 변경
		if (apiCode == ApiCode.mail_sendlist) {
			// 받는이
			getController().updateSearchOptionLabel(R.id.mailSearchOptionUser, R.string.label_mail_search_option_to);
		} else {
			// 보낸이
			getController().updateSearchOptionLabel(R.id.mailSearchOptionUser, R.string.label_mail_search_option_from);
		}
	}

	@Override
	public void deleteItemByID(String id) {
		int nextShowIndex = -1;
		int arraySize = mSrcArray.size();
		for (int i = 0; i < arraySize; i++) {
			JSONObject item = mSrcArray.get(i);

			if (TextUtils.equals(id, item.optString("msgid").trim())) {
				isItemDeleted = true;

				if (isSearchMode) {
					if (mDeletedItemArray == null) {
						mDeletedItemArray = new ArrayList<JSONObject>();
					}
					mDeletedItemArray.add(item);
				}

				if (arraySize > (i + 1)) { // 다음 목록이 더 있으면
					nextShowIndex = i;
				} else {
					if (arraySize == 1) { // 마지막 목록이었으면
						nextShowIndex = -1;// 없음
					} else {
						nextShowIndex = i - 1;
					}
				}
				mSrcArray.remove(i);
				mAdapter.updateListview();
				if (nextShowIndex == -1) {
					initData();
					showEmptyPage();
				} else {
					showItem(nextShowIndex + 1);
				}
				break;
			}
		}

		// 검색 모드에서 삭제한 경우 이전 메일리스트에서도 삭제된 메일을 제거하기 위함
		if (isSearchMode && mDeletedItemArray != null) {
			getController().checkDeletedMailItems(mDeletedItemArray);
			mDeletedItemArray.clear();
		}

		// updateRecommendationMessage();// 목록 업데이트 권고 메시지 - 임시(그룹웨어의 메일 아이디가
		// 바뀌는 문제로 인함)
	}

	public void updateRecommendationMessage() {
		getMainFragment().getString(R.string.message_alert_update_maillist);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		if(isEditMode || getListView().isScrolling)
			return false;

		final JSONObject jsonObject = mSrcArray.get(position -1);
		// status가 2일 경우 읽은 메일
		final int mailStatus = jsonObject.optInt("status");
		final boolean readflag = jsonObject.optBoolean("readflag");
		new AlertDialog.Builder(getActivity()).setItems(new String[]{
				getActivity().getResources().getString(R.string.label_square_contents_option_delete),
				readflag ?
//				mailStatus == 2 ?
						getActivity().getResources().getString(R.string.label_mark_not_read):
						getActivity().getResources().getString(R.string.label_mark_read)
						}, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch(which){
				// 삭제
				case 0:
					new Thread(new Runnable() {
						@Override
						public void run() {
							boolean success = MainModel.getInstance().getOpenApiService()
									.deleteMail(getActivity(), jsonObject.optString("boxid"), jsonObject.optString("msgid").trim(), jsonObject.optString("saveid").toString(),"false");
							if(success){
								if (isSearchMode) {
									if (mDeletedItemArray == null) {
										mDeletedItemArray = new ArrayList<JSONObject>();
									}
									mDeletedItemArray.add(mSrcArray.get(position-1));
								}
								mSrcArray.remove(position-1);

								// 검색 모드에서 삭제한 경우 이전 메일리스트에서도 삭제된 메일을 제거하기 위함
								if (isSearchMode && mDeletedItemArray != null) {
									getController().checkDeletedMailItems(mDeletedItemArray);
									mDeletedItemArray.clear();
								}

								getActivity().runOnUiThread(new Runnable() {
									@Override
									public void run() {
										mAdapter.updateListview();
										if (MainModel.getInstance().isTablet()) {
											if (!isPreventSearchDeleted) {
												if (mSrcArray.size() > 0) {
													showItem(position);
												}
												else {
													initData();
													showEmptyPage();
												}
											}
											isPreventSearchDeleted = false;
										}
										else {
											if (!isPreventSearchDeleted) {
												if (mSrcArray.size() <= 0) {
													initData();
												}
											}
											isPreventSearchDeleted = false;
											selectedItemPosition = -1;
											mAdapter.updateListview();
										}
									}
								});
							}
						}
					}).start();
					break;
				// 표시 처리
				case 1:
					ReadMailCallBack callback = new ReadMailCallBack(){
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);
							if (status.getCode() == 200) {
								try {
									jsonObject.put("status", mailStatus == 2? 0:2);
									jsonObject.put("readflag", readflag ? false:true);
								} catch (JSONException e) {
									Debug.trace(e.getMessage());
								}
							} else {
								// 실패 처리
							}
							mAdapter.updateListview();
						}
					};

					Map<String, String> params = new HashMap<String, String>();

					params.put("openapipath", "/wma/openapi.do?acton=readflagbymid");
					params.put("boxid", mailBoxID);
					params.put("msgid", jsonObject.optString("msgid").trim());
					params.put("readflag", readflag ?"false":"true");
					params.put("key", HMGWServiceHelper.key);
					new ApiLoaderEx<JSONObject>(new ReadMail(getActivity()), getActivity(),callback,params).execute();
					break;
				}
			}
		}).show();
//		getController().onReadMail(view);
		return true;
	}

	public void readSelectedItems(final int which) {
		if (checkedItemList == null || checkedItemList.size() == 0) {
			return;
		}
		StringBuffer msgIDs = null;
		StringBuffer saveIDs = null;
		for (Iterator<JSONObject> i = checkedItemList.iterator(); i.hasNext();) {
			JSONObject item = i.next();
			if (msgIDs == null) {
				msgIDs = new StringBuffer();
				msgIDs.append(item.optString("msgid").trim());
			} else {
				msgIDs.append(',');
				msgIDs.append(item.optString("msgid").trim());
			}
			if (saveIDs == null) {
				saveIDs = new StringBuffer();
				saveIDs.append(item.optString("saveid"));
			} else {
				saveIDs.append(',');
				saveIDs.append(item.optString("saveid"));
			}
		}
		if(saveIDs==null||msgIDs==null) return;
		ReadMailCallBack callback = new ReadMailCallBack(){
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				if (status.getCode() == 200) {
					for (Iterator<JSONObject> i = checkedItemList.iterator(); i.hasNext();) {
						JSONObject item = i.next();
						try {
							item.put("status", which == 0? 2:0);
							item.put("readflag", which == 0 ? true:false);
						} catch (JSONException e) {
							Debug.trace(e.getMessage());
						}
					}
				} else {
					// 실패 처리
				}
				clearCheckBox();
				hideDeletingDialog();
				mAdapter.updateListview();
			}
		};

		Map<String, String> params = new HashMap<String, String>();

		params.put("openapipath", "/wma/openapi.do?acton=readflagbymid");
		params.put("boxid", mailBoxID);
		params.put("msgid", msgIDs.toString());
		params.put("readflag", which == 0?"true":"false");
		params.put("key", HMGWServiceHelper.key);
		new ApiLoaderEx<JSONObject>(new ReadMail(getActivity()), getActivity(),callback,params).execute();

	}
}
