package com.hs.mobile.gw.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.DateUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.OnScrollListView;

import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class SignListAdapter extends MGWBaseAdapter {

	private String authDeptId; 
	private String authFldrId;	
	private boolean isRole = false;
	
	public SignListAdapter(MainFragment f, ApiCode code, int initPno, List<NameValuePair> params, String authDeptId,String authFldrId, boolean isSearch ) {
		super(f);
		this.layout = R.layout.template_signlist;
		this.apiCode = code;
		this.currentPno = initPno;
		this.initPno = initPno;
		this.authDeptId = authDeptId;
		this.authFldrId = authFldrId;
		this.mSrcArray = null;
		this.mSrcTmpArray = null;
		this.extraParams = params;
		this.mAdapter = this;
		this.isSearchMode = isSearch;
		
		
		try{initSendingDept();} catch(JSONException e) {Debug.trace(e.getMessage());};
		if (setLoadingInProgress(true)) initData();
		if(code != ApiCode.sending_process) showSearchSignlist();
		
	}
	public void initSendingDept() throws JSONException{ //부서 선택 스피너 달기
		
		int selectedPosition = 0;
		
		if(this.apiCode != ApiCode.sending_process) return; // 발송처리일 경우에만 나오게하기
		
		JSONObject deptData = MainModel.getInstance().getOpenApiService().getSendingDeptList(getActivity(),currentPno, extraParams);
		isRole = deptData.getJSONObject("channel").getBoolean("isRole"); // 발송처리 부서가 목록이 존재하는경우 true

		if(!isRole) return; // 발솔 처리 권한이 존재하는 경우에만 목록 노출되도록 설정
		
		JSONObject deptDefaultJSONArray = deptData.getJSONObject("channel").optJSONObject("defaultAuthInfo"); //발송처리 기본 부서
		if (this.authDeptId == null){
			this.authDeptId = deptDefaultJSONArray.getString("deptID");
			this.authFldrId = deptDefaultJSONArray.getString("fldrID");
		}

		JSONArray deptJSONArray = deptData.getJSONObject("channel").optJSONArray("authInfoList"); //발송처리 권한 부서 목록
		ArrayList<JSONObject> deptArrList = new ArrayList<JSONObject>();
		if(deptJSONArray.length() > 0){
			for(int i = 0; i < deptJSONArray.length(); i++){ 
				deptArrList.add((JSONObject) deptJSONArray.get(i));
			}
		}
		getController().initSendingDeptButton(deptArrList,selectedPosition);
	}
	public void setParameters(List<NameValuePair> params) {
		this.extraParams = params;
	}
	public String getAuthDeptId() {
		return authDeptId;
	}

	public void setAuthDeptId(String authDeptId) {
		this.authDeptId = authDeptId;
	}

	public String getAuthFldrId() {
		return authFldrId;
	}

	public void setAuthFldrId(String authFldrId) {
		this.authFldrId = authFldrId;
	}
	
	public void showSearchSignlist() {
		getController().showSearchSignlist();
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

			if (convertView == null) {
				if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
					convertView = LayoutInflater.from(parent.getContext()).inflate(emptyLayout, parent, false);
				} else {
					convertView = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
				}
			}

			if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
				convertView = LayoutInflater.from(parent.getContext()).inflate(emptyLayout, parent, false);
				return convertView;
			}

			ImageView statusView = (ImageView) convertView.findViewById(R.id.sign_status);
			ImageView attachView = (ImageView) convertView.findViewById(R.id.sign_attach);
			ImageView emergencyView = (ImageView) convertView.findViewById(R.id.sign_emergency);
			ImageView restrictedView = (ImageView) convertView.findViewById(R.id.sign_restricted);
			ImageView securityView = (ImageView) convertView.findViewById(R.id.sign_security);
			// ImageView commentView =
			// (ImageView)convertView.findViewById(R.id.sign_comment);

			TextView titleView = (TextView) convertView.findViewById(R.id.sign_title);

			TextView drafterView = (TextView) convertView.findViewById(R.id.extrainfo_drafter);
			TextView draftDateView = (TextView) convertView.findViewById(R.id.extrainfo_draftDate);
			TextView apprstatustextView = (TextView) convertView.findViewById(R.id.extrainfo_apprstatustext);
			TextView apprtypetextView = (TextView) convertView.findViewById(R.id.extrainfo_apprtypetext);
			TextView participantapprtypetextView = (TextView) convertView.findViewById(R.id.extrainfo_participantapprtypetext);
			TextView commentTextView = (TextView) convertView.findViewById(R.id.extrainfo_comment);

			String title = item.optString("title").trim();
			String drafter;
			if (this.apiCode == ApiCode.sign_receipt_wait) {
				drafter = item.optString("orgDrafterName").trim();
			} else {
				drafter = item.optString("drafter").trim();
			}
			String draftDate = item.optString("draftDate").trim();
			String apprstatustext = item.optString("apprstatustext").trim();
			String apprtypetext = item.optString("apprtypetext").trim();
			boolean commentCk = item.optString("statusimageEx").indexOf("opinion.gif") == -1 ? false : true;
			boolean participantapprCk = item.optString("statusimageEx").indexOf("s4.gif") == -1 ? false : true;

			String participantapprtypetext = item.optString("participantapprtypetext").trim();

			String statusimage = item.optString("statusimage");
			/*
			 * statusImageFilePath : nxxx.gif 첫째자리 : n,b : 기안, r,p : 수신, o,y :
			 * 비전자 둘째자리 : o : 첨부있음, x : 첨부없음 세째자리 : o : 긴급, x : 긴급아님 네째자리 : o :
			 * 열람제한, x : 열람제한아님
			 * 
			 * statusImageExFile :
			 * key.gif/s0.gif/s4.gif/s8.gif/s9.gif/opinion.gif key.gif : 열람시
			 * 암호(파싱 안함) => security값 참조 s0.gif : 반려 s4.gif : 공람 s8.gif : 회수
			 * s9.gif : 이력관리 opinion.gif : 의견
			 */
			boolean attach = false;
			boolean emergency = false;
			boolean restricted = false;
			attach = statusimage.charAt(1) == 'o' ? true : false;
			emergency = statusimage.charAt(2) == 'o' ? true : false;
			restricted = statusimage.charAt(3) == 'o' ? true : false;
			int security = item.optInt("security");

			titleView.setText(title);

			drafterView.setText(drafter);
			draftDateView.setText(DateUtils.getDateStringFromGWDate(draftDate));

			// 진행
			if (!TextUtils.isEmpty(apprstatustext)) {
				(convertView.findViewById(R.id.img_divider_apprstatustext)).setVisibility(View.VISIBLE);
				apprstatustextView.setText(apprstatustext);
			} else {
				(convertView.findViewById(R.id.img_divider_apprstatustext)).setVisibility(View.GONE);
				apprstatustextView.setText("");
			}

			// 보고
			if (!TextUtils.isEmpty(apprtypetext)) {
				(convertView.findViewById(R.id.img_divider_apprtypetext)).setVisibility(View.VISIBLE);
				apprtypetextView.setText(apprtypetext);
			} else {
				(convertView.findViewById(R.id.img_divider_apprtypetext)).setVisibility(View.GONE);
				apprtypetextView.setText("");
			}

			// 공람
			if (participantapprCk) {
				(convertView.findViewById(R.id.img_divider_participantapprtypetext)).setVisibility(View.VISIBLE);
				if (!TextUtils.isEmpty(participantapprtypetext)) {
					participantapprtypetextView.setText(participantapprtypetext);
				} else {
					participantapprtypetextView.setText(R.string.label_list_sign_participantapprtypetext);
				}
			} else {
				(convertView.findViewById(R.id.img_divider_participantapprtypetext)).setVisibility(View.GONE);
				participantapprtypetextView.setText("");
			}

			// 의견
			if (commentCk) {
				(convertView.findViewById(R.id.img_divider_comment)).setVisibility(View.VISIBLE);
				commentTextView.setText(R.string.label_board_comment);
			} else {
				(convertView.findViewById(R.id.img_divider_comment)).setVisibility(View.GONE);
				commentTextView.setText("");
			}

			// 상태 - b,n : 기안, p,r : 수신, y,o : 비전자
			int imageResourceID;
			if (statusimage.charAt(0) == 'n' || statusimage.charAt(0) == 'b') {
				imageResourceID = R.drawable.icon_approval_n;
			} else if (statusimage.charAt(0) == 'r' || statusimage.charAt(0) == 'p') {
				imageResourceID = R.drawable.icon_approval_r;
			} else if (statusimage.charAt(0) == 'o' || statusimage.charAt(0) == 'y') {
				imageResourceID = R.drawable.icon_approval_o;
			} else {
				imageResourceID = R.drawable.icon_approval_n;
			}
			statusView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(imageResourceID));

			// 보안
			if (security == 1) {
				securityView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.icon_add_k));
			} else {
				securityView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
			}

			// 열람 제한
			if (restricted) {
				restrictedView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.icon_add_l));
			} else {
				restrictedView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
			}

			// 긴급
			if (emergency) {
				emergencyView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.icon_add_e));
			} else {
				emergencyView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
			}

			// 첨부
			if (attach) {
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
		isFirstPage = true;
		isShowFirstRow = true;
		currentPno = initPno;
		mSrcArray = new ArrayList<JSONObject>();
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
			try {
				JSONObject data = null;
				
				if (mSrcArray == null) mSrcArray = new ArrayList<JSONObject>();
				if(MainModel.getInstance().getOpenApiService()==null) return null;
				if(!(!isRole && apiCode == ApiCode.sending_process)){
					if(MainModel.getInstance().getOpenApiService().getSignList(getActivity(),apiCode, currentPno, extraParams, isSearchMode , authDeptId, authFldrId )!=null){
						data = MainModel.getInstance().getOpenApiService().getSignList(getActivity(),apiCode, currentPno, extraParams, isSearchMode , authDeptId, authFldrId );
					}
				}

				if(data != null) {

					if (currentPno == initPno) {
						try {
							total = data.getJSONObject("channel").getInt("total");
							pageSize = data.getJSONObject("channel").getInt("pagesize");
						} catch (JSONException je) {
							getController().showAlertErrorMsg(data.getJSONObject("channel").getJSONObject("item").getString("message"));
							total = 0;
							pageSize = 10;
						}
					}
					
					
					if (total != 0) {
						JSONArray jArray = data.getJSONObject("channel").optJSONArray("item");
						if (jArray == null) {
							JSONObject item = data.getJSONObject("channel").optJSONObject("item");
							item.putOpt("examDeptId", authDeptId);
							mSrcArray.add(item);
						} else {
							for (int i = 0; i < jArray.length(); i++) {
								JSONObject item = (JSONObject) jArray.get(i);
								item.putOpt("examDeptId", authDeptId);
								mSrcArray.add(item);
							}
						}
					} else {
						setForEmptyList();
					}
					
					
					if (listView == null) {
						listView = new OnScrollListView(getMainFragment().getActivity());
						listView.setAdapter(mAdapter);
						setListView();
						mAdapter.addListViewToMiddleFlipper();
					} else {
						mAdapter.updateListview();
					}
					
					
				} else{
					if(!isRole && apiCode == ApiCode.sending_process){
						setForEmptyList();
						if (listView == null) {
							listView = new OnScrollListView(getMainFragment().getActivity());
							listView.setAdapter(mAdapter);
							setListView();
							mAdapter.addListViewToMiddleFlipper();
						} else {
							mAdapter.updateListview();
						}
					}
					if(currentPno != initPno) currentPno--;
					
					
				}
				
				
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
				
				
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			}catch (Exception e) {
				Debug.trace(e.getMessage());
			}
			return null;
		}
	}

	@Override
	public void getMoreData() {
		currentPno++;
		// toastNewPageNo();
		loadData();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (!isRefreshing()) {
			showItem(position);
		}
	}

	@Override
	public void showItem(int position) {
		selectedItemPosition = position;
		JSONObject item = (JSONObject) getItem(selectedItemPosition);
		if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
			return;
		}

		if (MainModel.getInstance().isApprovalArchiveMenu()) {
			// 열람제한 체크
			if (!TextUtils.isEmpty(item.optString("docViewAuth"))) {
				JSONObject data = null;
				if(MainModel.getInstance().getOpenApiService()==null) return;
				if(MainModel.getInstance().getOpenApiService().getDocViewAuth(getActivity(),item.optString("docViewAuth"))!=null){
					data = MainModel.getInstance().getOpenApiService().getDocViewAuth(getActivity(),item.optString("docViewAuth"));
				}

				boolean hasAuth = true;
				if (data != null) {
					hasAuth = data.optJSONObject("channel").optJSONArray("item").optJSONObject(0).optBoolean("#text");
				}

				if (!hasAuth) {
					getController().showAlertErrorMsg(getMainFragment().getString(R.string.error_sign_not_allowed_to_access));
					return;
				}
			}
			// if(("html".equals(HMGWServiceHelper.sign_doctype) ||
			// "hwp8".equals(HMGWServiceHelper.sign_doctype)) &&
			// !"".equals(item.optString("docViewAuth"))) {
			// JSONObject data =
			// MainActivity.openApiService.getDocViewAuth(item.optString("docViewAuth"));
			//
			// boolean hasAuth = true;
			// if(data != null) {
			// hasAuth =
			// data.optJSONObject("channel").optJSONArray("item").optJSONObject(0).optBoolean("#text");
			// }
			//
			// if(!hasAuth) {
			// Message msg = new Message();
			// msg.obj =
			// mCon.getString(R.string.error_sign_not_allowed_to_access);
			// msg.what = MainActivity.ALERT_ERROR_MESSAGE;
			// MainActivity.mViewUpdateHandler.sendMessage(msg);
			// return;
			// }
			//
			// } else if("tagfree".equals(HMGWServiceHelper.sign_doctype) ||
			// "hwp6".equals(HMGWServiceHelper.sign_doctype)) {
			// String apprID = item.optString("guid");
			// JSONObject data =
			// MainActivity.openApiService.checkDocView(apprID);
			//
			// boolean hasAuth = true;
			// if(data != null) {
			// hasAuth =
			// data.optJSONObject("channel").optJSONArray("item").optJSONObject(0).optBoolean("#text");
			// }
			//
			// if(!hasAuth){
			// Message msg = new Message();
			// msg.obj =
			// mCon.getString(R.string.error_sign_not_allowed_to_access);
			// msg.what = MainActivity.ALERT_ERROR_MESSAGE;
			// MainActivity.mViewUpdateHandler.sendMessage(msg);
			// return;
			// }
			// }
		}

		int security = item.optInt("security");
		try {
			item.put("apiCode", apiCode.toString());
		} catch (JSONException e) {
			Debug.trace(e.getMessage());
		}

		if (security == 1) {// 보안 결재이면
			getController().checkPasswordForSign(item);
		} else {
			getController().showSignDetails(item);
		}
		mAdapter.updateListview();
	}

	@Override
	public boolean hasMoreData() {
		if (maxPno == -1 && total != 0 && pageSize != 0) {
			maxPno = (int) Math.ceil((double) total / (double) pageSize);
		}
		if (currentPno == maxPno && total > pageSize) {
			notiLastPage();
		} else {
			notifiedLastPage = false;
		}
		return currentPno < maxPno;
	}

	@Override
	public void updateOtherViews() {
		getController().hideWriteButton();
		getController().setRefreshForListFooter();
//		if (apiCode == ApiCode.sign_waitlist || apiCode == ApiCode.sign_inprogess) {
//			getController().showSignFilter();
//		}
	}

	@Override
	public void showFirstRow() {
		if ((MainModel.getInstance().isTablet() || forceShowFirstRow) && !MainModel.getInstance().isPopupMode()) {
			forceShowFirstRow = false;
			if (total > 0) {
				showItem(1);
			} else {
				// 테블릿의 경우 빈화면 필요
				showEmptyPage();
			}
		}
	}

	public boolean forceShowFirstRow = false;

	@Override
	public void deleteItemByID(String id) {
		forceShowFirstRow = false;
		// initData();
		int nextShowIndex = -1;
		int arraySize = mSrcArray.size();
		for (int i = 0; i < arraySize; i++) {
			JSONObject item = mSrcArray.get(i);
			if (TextUtils.equals(id, item.optString("guid"))) {
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
					// 빈 목록 화면 보이기!
					initData();
					showEmptyPage();
				} else {
					Message msg = new Message();
					item = mSrcArray.get(nextShowIndex);
					try {
						item.put("apiCode", apiCode.toString());
					} catch (JSONException e) {
						Debug.trace(e.getMessage());
					}
					if (item.optInt("security") == 1) {// 보안 결재이면
						getController().checkPasswordForSign(item);
					} else {
						getController().showSignDetails(item);
					}
				}
				break;
			}
		}

	}

	public void completeAppr(Object obj) {
		if (obj instanceof JSONArray) {
			JSONArray jarr = (JSONArray) obj;
			String strId = jarr.optString(0);
			String strType = null;
			if (jarr.length() > 1) {
				strType = jarr.optString(1);
			}
			int nextShowIndex = 0;
			// 보류일 경우 목록에서 삭제하지 않고 남겨두고 리스트에서 상태를 보류로 바꿈.
			if (TextUtils.equals(strType, "postpone")) {
				for (int i = 0; i < mSrcArray.size(); ++i) {
					JSONObject item = mSrcArray.get(i);
					if (TextUtils.equals(strId, item.optString("guid"))) {
						try {
							item.put("apprstatustext", getMainFragment().getResources().getString(R.string.label_list_sign_holding));
						} catch (JSONException e) {
							Debug.trace(e.getMessage());
						}
						if (mSrcArray.size() > (i + 1)) { // 다음 목록이 더 있으면
							nextShowIndex = i + 1;
						} else {
							if (mSrcArray.size() == 1) { // 마지막 목록이었으면
								nextShowIndex = 0;// 없음
							} else {
								nextShowIndex = i - 1;
							}
						}
						mAdapter.updateListview();
						Message msg = new Message();
						item = mSrcArray.get(nextShowIndex);
						try {
							item.put("apiCode", apiCode.toString());
						} catch (JSONException e) {
							Debug.trace(e.getMessage());
						}
						if (item.optInt("security") == 1) {// 보안 결재이면
							getController().checkPasswordForSign(item);
						} else {
							getController().showSignDetails(item);
						}
						break;
					}
				}
			} else {
				deleteItemByID(strId);
			}
		}
	}
}
