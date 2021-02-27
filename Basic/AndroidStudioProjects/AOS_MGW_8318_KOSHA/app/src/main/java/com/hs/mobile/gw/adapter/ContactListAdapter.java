package com.hs.mobile.gw.adapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.MGWUtils;
import com.hs.mobile.gw.view.OnScrollListView;

public class ContactListAdapter extends MGWBaseAdapter {
	/**
	 * 로컬의 주소록에 checkedItemList에 들어있는 내용을 저장하는 AsyncTask
	 * 
	 * @author C.H.Lee
	 * 
	 */
	public class SaveContactAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// 기록이 끝나면 편집모드를 풀어준다.
			hideProgressDialog();
			clearCheckBox();
			isEditMode = false;
			updateOtherViews();
			mAdapter.updateListview();
			getController().editMailList(null);
		}

		@Override
		protected Void doInBackground(Void... params) {
			for (JSONObject item : checkedItemList) {

				writeContact(getItemValue(item, "position"), getItemValue(item, "homePostalCode"), getItemValue(item, "mail"),
						getItemValue(item, "homePostalAddress"), getItemValue(item, "department"), getItemValue(item, "homePhone"),
						getItemValue(item, "homepage"), getItemValue(item, "postalCode"), getItemValue(item, "faxNumber"),
						getItemValue(item, "homeFaxNumber"), getItemValue(item, "telephoneNumber"), getItemValue(item, "company"),
						getItemValue(item, "name"), getItemValue(item, "postalAddress"), getItemValue(item, "gender"),
						getItemValue(item, "mobile"));
			}

			return null;
		}
	}

	public ArrayList<JSONObject> checkedItemList = new ArrayList<JSONObject>();

	public ContactListAdapter(MainFragment f, ApiCode code, int initPno, List<NameValuePair> params, boolean isSearchMode) {
		super(f);
		this.layout = R.layout.template_contact_memberlist;
		this.apiCode = code;
		this.currentPno = initPno;
		this.initPno = initPno;
		this.mSrcArray = null;
		this.extraParams = params;
		this.mAdapter = this;
		this.isSearchMode = isSearchMode;
		if (setLoadingInProgress(true)) {
			initData();
		}
	}

	String groupID;

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public void setParameters(List<NameValuePair> params) {
		this.extraParams = params;
	}

	public void showSearchContact() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				getController().showSearchContact();
			}
		});
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
			return convertView;
		}

		TextView nameView = (TextView) convertView.findViewById(R.id.contact_member_name);
		TextView extraView = (TextView) convertView.findViewById(R.id.contact_member_extrainfo);
		TextView phoneView = (TextView) convertView.findViewById(R.id.contact_member_phone);
		TextView mobileView = (TextView) convertView.findViewById(R.id.contact_member_mobile);
		ImageView phoneImg = (ImageView) convertView.findViewById(R.id.contact_member_img_phone);
		ImageView mobileImg = (ImageView) convertView.findViewById(R.id.contact_member_img_mobile);

		// 체크박스 추가.

		ImageView indicatorView = (ImageView) convertView.findViewById(R.id.contact_member_indicator);
		CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.contactCheckBox);
		checkBox.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CheckBox cb = (CheckBox) v;
				JSONObject item = (JSONObject) cb.getTag();
				try {
					if (cb.isChecked()) {
						checkedItemList.add(item);
					} else {
						checkedItemList.remove(item);
					}
					getController().updateSaveContactCount(checkedItemList.size());
					item.put("isChecked", cb.isChecked());
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}
			}
		});
		checkBox.setTag(item);
		checkBox.setChecked(item.optBoolean("isChecked"));

		checkBox.setVisibility(checkBoxVisibility);
		indicatorView.setVisibility(indicatorVisibility);

		String name = getItemValue(item, "name");
		String company = getItemValue(item, "company");
		String mail = getItemValue(item, "mail");
		String phone = getItemValue(item, "telephoneNumber");
		String mobile = getItemValue(item, "mobile");

		nameView.setText(name);

		if (!TextUtils.isEmpty(company)) {
			extraView.setText("[" + company + "] " + mail);
		} else {
			extraView.setText(mail);
		}

		if (!TextUtils.isEmpty(phone)) {
			phoneImg.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ic_phone));
			phoneView.setText(phone);
		} else {
			phoneView.setText("");
			phoneImg.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
		}
		if (!TextUtils.isEmpty(mobile)) {
			mobileImg.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ic_mobile));
			mobileView.setText(mobile);
		} else {
			mobileView.setText("");
			mobileImg.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
		}

		if (selectedItemPosition != -1 && position == (selectedItemPosition - 1)) {
			convertView.setBackgroundResource(R.drawable.default_list_selected);
		} else {
			convertView.setBackgroundResource(R.drawable.style_list_background);
		}

		return convertView;
	}

	public String getItemValue(JSONObject item, String name) {
		String value = "";
		value = item.optString(name);
		if (TextUtils.equals(value, "[]")) {
			value = "";
		}
		return value;
	}

	@Override
	public void initData() {
		isFirstPage = true;
		isShowFirstRow = true;
		currentPno = initPno;
		mSrcArray = new ArrayList<JSONObject>();
		this.notifyDataSetInvalidated();
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
			if(MainModel.getInstance().getOpenApiService().getContactList(getActivity(), apiCode, currentPno, extraParams, isSearchMode)!= null){
				data = MainModel.getInstance().getOpenApiService().getContactList(getActivity(), apiCode, currentPno, extraParams, isSearchMode);
			}
			if (data != null) {
				try{
					if (currentPno == initPno || mSrcArray == null) {
						mSrcArray = new ArrayList<JSONObject>();
						total = data.getInt("total");
						pageSize = data.getInt("pagesize");
					}
					if (total != 0) {
						JSONArray jArray = data.optJSONArray("contact");
						if (jArray == null) {
							JSONObject item = data.optJSONObject("contact");
							item.put("isChecked", false);
							mSrcArray.add(item);
						} else {
							for (int i = 0; i < jArray.length(); i++) {
								mSrcArray.add((JSONObject) jArray.get(i));
							}
						}
					} else {
						setForEmptyList();
					}
				}catch(JSONException e){
					Debug.trace(e.getMessage());
				}catch(Exception e){
					Debug.trace(e.getMessage());
				}
				if (listView == null) {
					listView = new OnScrollListView(getMainFragment().getActivity());
					listView.setAdapter(mAdapter);
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
			if (isShowFirstRow) {
				showSearchContact();
				showFirstRow();
				isShowFirstRow = false;
			}

			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
					updateOtherViews();
				}
			});

			return null;
		}
	}

	@Override
	public void getMoreData() {
		currentPno++;
		loadData();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (!isRefreshing()) {
			showItem(position);
		}
	}

	@Override
	public void showFirstRow() {
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (MainModel.getInstance().isTablet() && !MainModel.getInstance().isPopupMode()) {
					if (total > 0) {
						showItem(1);
					} else {
						// 테블릿의 경우 빈화면 필요...
						showEmptyPage();
					}
				}
			}
		});
	}

	@Override
	public void showItem(int position) {
		selectedItemPosition = position;
		JSONObject item = (JSONObject) getItem(selectedItemPosition);
		if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
			return;
		}
		String contactID = item.optString("id");
		String apiType = MGWUtils.getTypeStringByApiCode(apiCode);
		int memberType = item.optInt("memberType");
		if (memberType == 1) {
			apiType = null;
		}
		getController().showContactDetail(contactID, apiType);
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
		// MGW-2843 주소록에는 편집 기능이 스펙에 정의되어 있지않아 기능 제거
//		if (total > 0) {// 목록이 있으면 편집버튼 보이기
//			if (isEditMode)
//				return; // 편집모드이면 그냥 리턴
//			getController().initEditMailButton();
//		} else {
			getController().hideEditMailButton();
//		}
		getController().setRefreshForListFooter();
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
		getController().updateSaveContactCount(checkedItemList.size());
	}

	@Override
	public void saveContactItems() {
		super.saveContactItems();
		new SaveContactAsyncTask().execute();
	}

	public void hideProgressDialog() {
		getController().hideLoadingProgressDialog();
	}

	private void writeContact(String position, String homePostalCode, String mail, String homePostalAddress, String department,
			String homePhone, String homepage, String postalCode, String faxNumber, String homeFaxNumber, String telephoneNumber,
			String company, String name, String postalAddress, String gender, String mobile) {

		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

		ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
				.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
				.build());

		// ------------------------------------------------------ position
		if (!TextUtils.isEmpty(position)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Organization.TITLE, position)
					.withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
					.build());
		}

		// ------------------------------------------------------ homePostalCode
		if (!TextUtils.isEmpty(homePostalCode)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, homePostalCode)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME).build());
		}
		// ------------------------------------------------------ mail
		if (!TextUtils.isEmpty(mail)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Email.DATA, mail)
					.withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK).build());
		}

		// ------------------------------------------------------
		// homePostalAddress
		if (!TextUtils.isEmpty(homePostalAddress)) {
			ops.add(ContentProviderOperation
					.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.StructuredPostal.STREET, homePostalAddress)
					.withValue(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS,
							ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME).build());
		}
		// ------------------------------------------------------ department
		if (!TextUtils.isEmpty(department)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Organization.DEPARTMENT, department)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK).build());
		}
		// ------------------------------------------------------ homePhone
		if (!TextUtils.isEmpty(homePhone)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, homePhone)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME).build());
		}
		// ------------------------------------------------------ homepage
		if (!TextUtils.isEmpty(homepage)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Website.DATA, homepage)
					.withValue(ContactsContract.CommonDataKinds.Website.TYPE, ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE)
					.build());
		}
		// ------------------------------------------------------ postalCode
		if (!TextUtils.isEmpty(postalCode)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE, postalCode)
					.withValue(ContactsContract.CommonDataKinds.Website.TYPE, ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE)
					.build());
		}
		// ------------------------------------------------------ faxNumber
		if (!TextUtils.isEmpty(faxNumber)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, faxNumber)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK).build());
		}
		// ------------------------------------------------------ homeFaxNumber
		if (!TextUtils.isEmpty(homeFaxNumber)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, homeFaxNumber)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME).build());
		}
		// ------------------------------------------------------
		// telephoneNumber
		if (!TextUtils.isEmpty(telephoneNumber)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, telephoneNumber)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK).build());
		}

		// ------------------------------------------------------ company
		if (!TextUtils.isEmpty(company)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
					.withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
					.build());
		}
		// ------------------------------------------------------ name
		if (!TextUtils.isEmpty(name)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name).build());
		}
		// ------------------------------------------------------ postalAddress
		// 회사 주소
		if (!TextUtils.isEmpty(postalAddress)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS, postalAddress)
					.withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
					.build());
		}
		// ------------------------------------------------------ gender
		// 성별 못찾겠음. 추후 작업.
		if (!TextUtils.isEmpty(gender)) {
		}
		// ------------------------------------------------------ mobile
		if (!TextUtils.isEmpty(mobile)) {
			ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
					.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
					.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
					.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, mobile)
					.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
		}

		// Asking the Contact provider to create a new contact
		try {
			getMainFragment().getActivity().getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (RemoteException e) {
			Debug.trace(e.getMessage());
		} catch (OperationApplicationException e) {
			Debug.trace(e.getMessage());
		}
	}

}
