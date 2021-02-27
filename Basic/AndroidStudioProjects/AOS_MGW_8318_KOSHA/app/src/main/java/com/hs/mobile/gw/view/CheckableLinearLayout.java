package com.hs.mobile.gw.view;

import com.hs.mobile.gw.util.Debug;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

/*
 * This class is useful for using inside of ListView that needs to have checkable items.
 */
public class CheckableLinearLayout extends LinearLayout implements Checkable {
	public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CheckableLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CheckableLinearLayout(Context context) {
		super(context);
	}

	private Checkable _checkbox;

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		// find checked text view
		findCheckable(this);
//		Debug.trace("_checkbox", _checkbox);
	}

	private void findCheckable(View v) {
		if (v instanceof ViewGroup) {
			ViewGroup vg = (ViewGroup) v;
			for (int i = 0; i < vg.getChildCount(); ++i) {
				findCheckable(vg.getChildAt(i));
			}
		} else {
			if (v instanceof Checkable && !v.equals(this)) {
				_checkbox = (Checkable) v;
			}
		}
	}

	@Override
	public boolean isChecked() {
		return _checkbox != null ? _checkbox.isChecked() : false;
	}

	@Override
	public void setChecked(boolean checked) {
		if (_checkbox != null) {
			_checkbox.setChecked(checked);
		}
	}

	@Override
	public void toggle() {
		if (_checkbox != null) {
			_checkbox.toggle();
		}
	}
}