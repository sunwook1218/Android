package com.hs.mobile.gw.fragment.mailwrite;

import java.util.ArrayList;
import java.util.HashMap;

import com.hs.mobile.gw.openapi.ReadEmailResult;
import com.hs.mobile.gw.openapi.ReadEmailResult.Channel.AttachItem;
import com.hs.mobile.gw.openapi.square.vo.ShowMailEditorViewVO;
import com.hs.mobile.gw.view.SelectedListItem;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;

public class MailWriteModel {
	public interface IMailWriteValueChangeListener{
		void onCCandBCCLayoutVisibleChange(boolean b);
	}
	private IMailWriteValueChangeListener listener;
	private boolean bShowCCandBCC;
	private ArrayAdapter<SelectedListItem> selectedListAdapter;

	public ShowMailEditorViewVO m_argument;
	public ArrayList<SelectedListItem> mData = new ArrayList<SelectedListItem>();
	public HashMap<AttachItem, Boolean> m_attachCbSelecteHashMap = new HashMap<ReadEmailResult.Channel.AttachItem, Boolean>();
	public HashMap<CheckBox, AttachItem> m_attachCbFileHashMap = new HashMap<CheckBox, ReadEmailResult.Channel.AttachItem>();
	public String m_strMailID;
	protected String m_strBoxId;
	public ArrayList<AttachItem> m_attachItems;

	public void setMailWriteModelListener(IMailWriteValueChangeListener listener)
	{
		this.listener = listener;
	}
	
	public boolean isCCandBCCVisible() {
		return bShowCCandBCC;
	}

	public void setCCandBCCVisible(boolean visible) {
		this.bShowCCandBCC = visible;
		if(listener != null)
		{
			listener.onCCandBCCLayoutVisibleChange(bShowCCandBCC);
		}
	}

	public ArrayAdapter<SelectedListItem> getSelectedListAdapter() {
		return selectedListAdapter;
	}

	public void setSelectedListAdapter(ArrayAdapter<SelectedListItem> adapter) {
		this.selectedListAdapter = adapter;
	}
}
