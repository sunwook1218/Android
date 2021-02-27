package com.hs.mobile.gw.view;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
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
public class SquareMemberCompletionView extends TokenCompleteTextView {

	public SquareMemberCompletionView(Context context) {
		super(context);
	}

	public SquareMemberCompletionView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareMemberCompletionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected View getViewForObject(Object object) {
		LinearLayout view = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.contact_token,
				(ViewGroup) SquareMemberCompletionView.this.getParent(), false);
		if (object instanceof SquareMemberVO) {
			SquareMemberVO selectedListItem = (SquareMemberVO) object;
			((TextView) view.findViewById(R.id.name)).setText(selectedListItem.memberName);
		}
		return view;
	}

	@Override
	protected Object defaultObject(String completionText) {
		// Stupid simple example of guessing if we have an email or not
		int index = completionText.indexOf('@');
		if (index == -1) {
			return new SquareMemberVO(completionText, "", "");
		} else {
			return new SquareMemberVO(completionText, "", "");
		}
	}
}
