package com.hs.mobile.gw.view;

import org.json.JSONArray;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TabHost;

public class CustomTabHost extends TabHost{
	
	public JSONArray functions;
	
	public CustomTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);		
	}

	public CustomTabHost(Context context) {
		super(context);
	}

	public String getFunction(int index) {
		return functions.optString(index);
	}

	public void setFunctions(JSONArray functions) {
		this.functions = functions;
	}
	
}
