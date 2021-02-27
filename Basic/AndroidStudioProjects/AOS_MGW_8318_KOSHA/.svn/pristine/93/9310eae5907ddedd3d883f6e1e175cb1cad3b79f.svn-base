package com.hs.mobile.gw.view;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.view.SelectedListItem.ObjectType;
import com.hs.mobile.gw.view.tokenautocomplete.TokenCompleteTextView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Sample token completion view for basic contact info
 * 
 * Created on 9/12/13.
 * 
 * @author mgod
 */
public class ContactsCompletionView extends TokenCompleteTextView {

	public ContactsCompletionView(Context context) {
		super(context);
	}

	public ContactsCompletionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ContactsCompletionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected View getViewForObject(Object object) {
		LinearLayout view = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.contact_token,
				(ViewGroup) ContactsCompletionView.this.getParent(), false);
		if (object instanceof SelectedListItem) {
			SelectedListItem selectedListItem = (SelectedListItem) object;
			((TextView) view.findViewById(R.id.name)).setText(selectedListItem.name);
		}
		return view;
	}

	@Override
	protected Object defaultObject(String completionText) {
		// Stupid simple example of guessing if we have an email or not
		int index = completionText.indexOf('@');
		if (index == -1) {
//			return new SelectedListItem("", "", "", completionText);
			return null;
		} else {
			if (AndroidUtils.isEmail(completionText)){
				return new SelectedListItem("", completionText, ObjectType.EMAIL.toString(), completionText.substring(0, index));
			}
			else {
				return null;
			}
		}
	}
}
