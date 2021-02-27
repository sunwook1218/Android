package com.hs.mobile.gw.fragment.mailwrite;

import java.util.List;
import java.util.Locale;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.tokenautocomplete.FilteredArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MailWriteFilteredArrayAdapter extends FilteredArrayAdapter<SelectedListItem> {

	public MailWriteFilteredArrayAdapter(Context contenxt, int res, List<SelectedListItem> data) {
		super(contenxt, res, data);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater l = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = l.inflate(R.layout.person_layout, parent, false);
		}
		SelectedListItem p = getItem(position);
		((TextView) convertView.findViewById(R.id.name)).setText(p.name);
		String[] strBuf = p.dept_name.split("\\.");
		String deptName = strBuf[strBuf.length - 1];
		((TextView) convertView.findViewById(R.id.email)).setText(deptName);
//			((TextView) convertView.findViewById(R.id.email)).setVisibility(View.GONE);

		return convertView;
	}

	@Override
	protected boolean keepObject(SelectedListItem obj, String mask) {
		// TODO Auto-generated method stub
		return true;
	}

//	@Override
//	protected boolean keepObject(SelectedListItem obj, String mask) {
//		mask = mask.toLowerCase(Locale.getDefault());
//		Debug.trace("obj.name = " , obj.name.toLowerCase(Locale.getDefault()));
//		Debug.trace("obj.email = " , obj.email.toLowerCase(Locale.getDefault()));
//		Debug.trace("mask = " , mask);
//		return obj.name.toLowerCase(Locale.getDefault()).startsWith("$" + mask)
//				|| obj.name.toLowerCase(Locale.getDefault()).startsWith(mask)
//				|| obj.email.toLowerCase(Locale.getDefault()).startsWith(mask);
//	}

}
