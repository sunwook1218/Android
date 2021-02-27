package com.hs.mobile.gw.view;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.MemberRights;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NameAndDepartmentView extends LinearLayout {

	private TextView m_tvName;

	public TextView getTvName() {
		return m_tvName;
	}

	private TextView m_tvDepartmentAndPosition;

	public TextView getTvDepartmentAndPosition() {
		return m_tvDepartmentAndPosition;
	}

	private TextView m_memberRights;

	public TextView getMemberRights() {
		return m_memberRights;
	}

	public NameAndDepartmentView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		LayoutInflater.from(getContext()).inflate(R.layout.name_and_department_layout, this);
		m_tvName = (TextView) findViewById(R.id.ID_TV_NAME);
		m_tvDepartmentAndPosition = (TextView) findViewById(R.id.ID_TV_DEPARTMENT);
		m_memberRights = (TextView) findViewById(R.id.ID_TV_RIGHTS);
	}

	public NameAndDepartmentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public NameAndDepartmentView(Context context) {
		super(context);
		initView();
	}

	public void setData(String name, String authorDeptName, String authorPositionName) {
		m_tvName.setText(name);
		if (TextUtils.equals(authorDeptName, "null")) {
			authorDeptName = "";
		}
		if (TextUtils.equals(authorPositionName, "null")) {
			authorPositionName = "";
		}
		m_tvDepartmentAndPosition.setText(authorDeptName + (!TextUtils.isEmpty(authorPositionName) ? "/" + authorPositionName : ""));
	}

	public void setData(String name, String authorDeptName, String authorPositionName, MemberRights memberRights) {
		setData(name, authorDeptName, authorPositionName);

		switch(memberRights) {
		case ADMIN_USER:
			m_memberRights.setVisibility(View.VISIBLE);
			m_memberRights.setText(R.string.label_squareplus_member_admin);
			m_memberRights.setBackgroundColor(getResources().getColor(R.color.red));
			break;
		case OBSERVER_USER:
			m_memberRights.setVisibility(View.VISIBLE);
			m_memberRights.setText(R.string.label_squareplus_member_observer);
			m_memberRights.setBackgroundColor(getResources().getColor(R.color.black));
			break;
		case NORMAL_USER:
			m_memberRights.setVisibility(View.GONE);
		default:
			break;
		}
	}
}
