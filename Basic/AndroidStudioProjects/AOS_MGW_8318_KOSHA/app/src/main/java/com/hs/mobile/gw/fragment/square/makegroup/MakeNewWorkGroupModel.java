package com.hs.mobile.gw.fragment.square.makegroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.hs.mobile.gw.MainModel.Member;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.mailwrite.MailWriteFilteredArrayAdapter;
import com.hs.mobile.gw.view.SelectedListItem;

import android.content.Context;
import android.text.TextUtils;

public class MakeNewWorkGroupModel {
	private ArrayList<SelectedListItem> mData = new ArrayList<SelectedListItem>();
	public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
	private Date date;
	private MailWriteFilteredArrayAdapter adapter;

	public MailWriteFilteredArrayAdapter getAdapter() {
		return adapter;
	}

	public MakeNewWorkGroupModel() {
		this.date = new Date();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return this.date;
	}

	public boolean checkValidation(String strTitle, ArrayList<Member> memberList) {
		if (TextUtils.isEmpty(strTitle)) {
			return false;
		}
		return true;
	}

	public List<SelectedListItem> getData() {
		return mData;
	}

	public void createFilterAdapter(final Context context) {
		adapter = new MailWriteFilteredArrayAdapter(context, R.layout.person_layout, mData);
	}
}
