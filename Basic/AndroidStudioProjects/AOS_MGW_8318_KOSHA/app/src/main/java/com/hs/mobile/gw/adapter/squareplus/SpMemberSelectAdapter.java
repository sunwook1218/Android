package com.hs.mobile.gw.adapter.squareplus;

import java.util.ArrayList;
import java.util.List;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;
import com.hs.mobile.gw.view.NameAndDepartmentView;

public class SpMemberSelectAdapter extends BaseAdapter {
	public interface ISpSelectActivateListener {
		void onChangeSelection(List<SpSquareMemberVO> arrayList);
	}

	private ISpSelectActivateListener m_selectActivateListener;

	public void setSelectActivateListener(ISpSelectActivateListener selectActivateListener) {
		m_selectActivateListener = selectActivateListener;
	}

	private class ViewHolder implements OnCheckedChangeListener, Checkable {

		public ImageView m_imgProfile;
		public NameAndDepartmentView m_nameAndDepartmentView;
		public CheckBox m_cbMember;

		public ViewHolder(View v) {
			m_imgProfile = (ImageView) v.findViewById(R.id.ID_IMG_PROFILE);
			m_nameAndDepartmentView = (NameAndDepartmentView) v.findViewById(R.id.ID_NAME_AND_DEPARTMENT_VIEW);
			m_cbMember = (CheckBox) v.findViewById(R.id.ID_CB_MEMBER);
			m_cbMember.setFocusable(false);
			m_cbMember.setClickable(false);
			m_cbMember.setOnCheckedChangeListener(this);
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// Debug.trace("onCheckedChanged", (int) buttonView.getTag(),
			// isChecked);
			// Log.d("kangpual", "checked index :: " +
			// buttonView.getTag()+", isChecked::"+isChecked);
			mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
			if (m_selectActivateListener != null) {
				m_selectActivateListener.onChangeSelection(getCheckedItems());
			}
		}

		@Override
		public void setChecked(boolean checked) {
			m_cbMember.setChecked(checked);
		}

		@Override
		public boolean isChecked() {
			return m_cbMember.isChecked();
		}

		@Override
		public void toggle() {
			m_cbMember.toggle();
		}
	}

	private List<SpSquareMemberVO> m_data;
//	private List<SpSquareMemberVO> m_selectedMemberData;

	public SpMemberSelectAdapter(List<SpSquareMemberVO> data, List<SpSquareMemberVO> selectedMemberData) {
		m_data = data;
//		m_selectedMemberData = selectedMemberData;
	}

	private SparseBooleanArray mSparseBooleanArray = new SparseBooleanArray();

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SpSquareMemberVO getItem(int position) {
		return m_data.get(position);
	}

	public List<SpSquareMemberVO> getCheckedItems() {
		List<SpSquareMemberVO> mTempArry = new ArrayList<SpSquareMemberVO>();

		for (int i = 0; i < m_data.size(); i++) {
			if (mSparseBooleanArray.get(i)) {
				mTempArry.add(m_data.get(i));
			}
		}

		return mTempArry;
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SpSquareMemberVO squareMemberVO = m_data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			int templeteId = R.layout.list_item_member_select;
			convertView = LayoutInflater.from(parent.getContext()).inflate(templeteId, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(holder == null) return null; 
		holder.m_cbMember.setTag(position);
		// Log.d("kangpual", "---> ["+ position+"] name:"+
		// squareMember.memberName+", view checked:"+ cbmember.isChecked() +
		// ", array value:"+mSparseBooleanArray.get(position));
		if ((holder.m_cbMember.isChecked() ^ mSparseBooleanArray.get(position))) {
			holder.m_cbMember.setChecked(mSparseBooleanArray.get(position));
		}

		if (MainModel.getInstance().getOpenApiService()!=null && squareMemberVO != null) {
			MainModel.getInstance().getOpenApiService().drawUserPhoto(squareMemberVO.getMemberId(), holder.m_imgProfile, parent.getContext().getResources());
			holder.m_nameAndDepartmentView.setData(squareMemberVO.getMemberName(), squareMemberVO.getDeptName(), squareMemberVO.getPositionName(), squareMemberVO.getMemberRightsEnum());
		}

		return convertView;
	}

	public void clearCheck() {		
		mSparseBooleanArray.clear();
		notifyDataSetChanged();
		notifyDataSetInvalidated();
		if (m_selectActivateListener != null) {
			m_selectActivateListener.onChangeSelection(getCheckedItems());
		}
	}
}
