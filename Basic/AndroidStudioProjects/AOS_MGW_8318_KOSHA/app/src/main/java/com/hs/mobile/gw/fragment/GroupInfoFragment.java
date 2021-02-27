package com.hs.mobile.gw.fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.openapi.square.GroupInfo;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.ParticipantView;
import com.hs.mobile.gw.view.ParticipantView.IParticipantViewListener;
import com.hs.mobile.gw.view.ParticipantView.ParticipantItem;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupInfoFragment extends CommonFragment implements OnClickListener, IParticipantViewListener {
	private String m_strSquareId;
	private TextView m_tvGroupTitle;
	private TextView m_tvGroupInfo;
	private DateFormat m_yyyyMMdd = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
	private TextView m_tvGroupDescription;
	private ImageView m_btnBack;
	private ParticipantView m_participantView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			m_strSquareId = getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
		} else {
			getActivity().finish();
		}
	}

	@Override
	public void onResume() {
		ViewModel.Log("GrpFrag blue 해제", "bluetooth");
		super.onResume();
		SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(),
				GroupInfoVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				m_tvGroupTitle.setText(Html.fromHtml("<b><font color='#137FC8'>" + getString(R.string.label_square_group)
						+ " </font><font color='#000000'>" + getVO().title + "</font></b>"));
				m_tvGroupInfo.setText(Html.fromHtml(makeGroupInfoText(getVO())));
				m_tvGroupDescription.setText(getVO().description);
				m_participantView.setSquareMemberData(getVO().squareMemberList);
			}
		};
		callback.progress(PopupUtil.getProgressDialog(getActivity()));
		Map<String, String> params = new HashMap<String, String>();
		params.put("squareId", m_strSquareId);
		new ApiLoaderEx<JSONObject>(new GroupInfo(getActivity()), getActivity().getApplicationContext(), callback, params).execute();
	}

	protected String makeGroupInfoText(GroupInfoVO data) {
		StringBuilder sb = new StringBuilder();
		sb.append(getString(R.string.label_square_groupinfo_admin));
		sb.append(' ');
		sb.append("<b>");
		sb.append(data.authorName);
		sb.append("</b>");
		sb.append(" | ");
		sb.append(getString(R.string.label_square_groupinfo_start_date));
		sb.append(' ');
		Date createDate = new Date(data.createDate);
		sb.append("<b>");
		if (MainModel.getInstance().getDefaultDate().compareTo(createDate) == 0) {
			sb.append('-');
		} else {
			sb.append(m_yyyyMMdd.format(createDate));
		}
		sb.append("</b>");
		sb.append(" | ");
		sb.append(getString(R.string.label_square_groupinfo_due_date));
		sb.append(' ');
		Date dueDate = new Date(data.dueDate);
		sb.append("<b>");
		if (MainModel.getInstance().getDefaultDate().compareTo(dueDate) == 0) {
			sb.append(getString(R.string.label_square_setting_permanent));
		} else {
			sb.append(m_yyyyMMdd.format(dueDate));
		}
		sb.append("</b>");
		return sb.toString();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_group_info, container, false);
		super.createHead(v); // create sub header
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_tvGroupTitle = (TextView) v.findViewById(R.id.ID_TV_GROUP_TITLE);
		m_tvGroupInfo = (TextView) v.findViewById(R.id.ID_TV_GROUP_INFO);
		m_tvGroupDescription = (TextView) v.findViewById(R.id.ID_TV_GROUP_DESCRIPTION);
		m_participantView = (ParticipantView) v.findViewById(R.id.ID_PARTICIPANT_VIEW);
		m_participantView.setParticipantViewListener(this);
		m_btnBack.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		}
	}

	@Override
	public void onParticipantClick(ParticipantItem participantItem) {
		MainModel.getInstance().showSubActivity(
				getActivity(),
				SubActivityType.CUSTOM_WEBVIEW,
				new BundleUtils.Builder().add(CustomWebViewFragment.ARG_KEY_URL,
						"javascript:showUserDetailsPopup('" + participantItem.getSquareMember().memberId + "');").build());
	}
}
