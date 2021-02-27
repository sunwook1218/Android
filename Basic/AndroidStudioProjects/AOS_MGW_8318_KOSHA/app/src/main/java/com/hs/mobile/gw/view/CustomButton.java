package com.hs.mobile.gw.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button implements IFunctionAble{
	 
	String onclickFunction;
		
	public CustomButton(Context context) {
		super(context);		
	}
	
	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public String getOnclickFunction() {
		return onclickFunction;
	}
	public void setOnclickFunction(String onclickFunction) {
		this.onclickFunction = onclickFunction;
	}

	public void setLabel(String text) {
		this.setText(text);		
	}	
}
