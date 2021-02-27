package com.hs.mobile.gw.adapter;

import java.util.ArrayList;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.square.vo.MemberRight;
import com.hs.mobile.gw.openapi.square.vo.SquareMemberVO;
import com.hs.mobile.gw.view.NameAndDepartmentView;

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

public class MemberSelectAdapter extends BaseAdapter {
	public interface ISelectActivateListener {
		void onChangeSelection(ArrayList<SquareMemberVO> arrayList);
	}

	private ISelectActivateListener m_selectActivateListener;

	public void setSelectActivateListener(ISelectActivateListener selectActivateListener) {
		m_selectActivateListener = selectActivateListener;
	}

	private class ViewHolder implements OnCheckedChangeListener, Checkable {

		public ImageView m_imgAdmin;
		public ImageView m_imgProfile;
		public NameAndDepartmentView m_nameAndDepartmentView;
		public CheckBox m_cbMember;

		public ViewHolder(View v) {
			m_imgProfile = (ImageView) v.findViewById(R.id.ID_IMG_PROFILE);
			m_imgAdmin = (ImageView) v.findViewById(R.id.ID_IMG_MASTER);
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

	private ArrayList<SquareMemberVO> m_data;
	private ArrayList<SquareMemberVO> m_selectedMemberData;

	public MemberSelectAdapter(ArrayList<SquareMemberVO> data, ArrayList<SquareMemberVO> selectedMemberData) {
		m_data = data;
		m_selectedMemberData = selectedMemberData;
	}

	private SparseBooleanArray mSparseBooleanArray = new SparseBooleanArray();

	@Override
	public int getCount() {
		return m_data == null ? 0 : m_data.size();
	}

	@Override
	public SquareMemberVO getItem(int position) {
		return m_data.get(position);
	}

	public ArrayList<SquareMemberVO> getCheckedItems() {
		ArrayList<SquareMemberVO> mTempArry = new ArrayList<SquareMemberVO>();
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
		SquareMemberVO squareMember = m_data.get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_member_select, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.m_cbMember.setTag(position);
		// Log.d("kangpual", "---> ["+ position+"] name:"+
		// squareMember.memberName+", view checked:"+ cbmember.isChecked() +
		// ", array value:"+mSparseBooleanArray.get(position));
		if ((holder.m_cbMember.isChecked() ^ mSparseBooleanArray.get(position))) {
			holder.m_cbMember.setChecked(mSparseBooleanArray.get(position));
		}

		holder.m_nameAndDepartmentView.setData(squareMember.memberName, squareMember.deptName, squareMember.positionName);
		if (squareMember.memberRights == MemberRight.ADMIN_USER) {
			holder.m_imgAdmin.setVisibility(View.VISIBLE);
		} else {
			holder.m_imgAdmin.setVisibility(View.GONE);
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
