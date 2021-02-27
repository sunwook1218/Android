package com.hs.mobile.gw.fragment.squareplus;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.SpSelectFolderAdapter;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.openapi.squareplus.SpAddFolderSquareMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.SpDeleteFolderMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.SpMoveFolderSquareMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.SpUpdateFolderMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFolderCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFolderSquareCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderSquareVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderVO;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;

public class SpFolderSelectFragment extends CommonFragment implements OnClickListener, OnItemClickListener {

	public static final String ARG_KEY_SP_SELECT_TYPE = "arg_key_sp_select_type";
	public static final String ARG_KEY_SP_FOLDER_LIST = "arg_key_sp_folder_list";
	public static final String ARG_KEY_SP_FOLDER_ITEM = "arg_key_sp_folder_item";
	public static final String ARG_KEY_SP_SQUARE_ID = "arg_key_sp_square_id";
	public static final String ARG_KEY_SP_FOLDER_ID = "arg_key_sp_folder_id";

	Button cancelBtn;
	Button confirmBtn;
	TextView title;
	ListView folderListView;
	SpSelectFolderAdapter adapter;
	
	private SpSelectType currentSelectType;
	private List<SpFolderVO> folderListData;
	private String orgSquareId;
	private String orgFolderId;
	
	public enum SpSelectType {
		MODIFY("modify"), DELETE("delete"), SQUARE_MOVE("squareMove"), SQUARE_ADD("squareAdd");
		private String m_strType;

		private SpSelectType(String s) {
			m_strType = s;
		}

		public String getType() {
			return m_strType;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 전달 받은 데이터 확인
		if (getArguments() != null) {
			if (getArguments().getSerializable(ARG_KEY_SP_SELECT_TYPE) != null) {
				currentSelectType = (SpSelectType) getArguments().getSerializable(ARG_KEY_SP_SELECT_TYPE);
				if (currentSelectType == SpSelectType.SQUARE_ADD) {
					orgSquareId = (String) getArguments().getString(ARG_KEY_SP_SQUARE_ID);
					Debug.trace("orgSquareId = ", orgSquareId);
				}
				else if (currentSelectType == SpSelectType.SQUARE_MOVE) {
					orgSquareId = (String) getArguments().getString(ARG_KEY_SP_SQUARE_ID);
					orgFolderId = (String) getArguments().getString(ARG_KEY_SP_FOLDER_ID);
					Debug.trace("orgSquareId = ", orgSquareId);
					Debug.trace("orgFolderId = ", orgFolderId);
				}
			}
			
			if (getArguments().getSerializable(ARG_KEY_SP_FOLDER_LIST) != null) {
				folderListData = (List<SpFolderVO>) getArguments().get(ARG_KEY_SP_FOLDER_LIST);
				folderListData.remove(folderListData.size()-1);
				Debug.trace(folderListData.toString());
			}
		}
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_sp_folder_select, container, false);
		cancelBtn = (Button) v.findViewById(R.id.ID_BTN_CANCEL);
		cancelBtn.setOnClickListener(this);
		confirmBtn = (Button) v.findViewById(R.id.ID_BTN_CONFIRM);
		confirmBtn.setOnClickListener(this);
		title = (TextView) v.findViewById(R.id.middleNavibarTitle);

		folderListView = (ListView) v.findViewById(R.id.ID_LV_SP_FOLDERLIST);
		adapter = new SpSelectFolderAdapter(getActivity(), folderListData);
		folderListView.setAdapter(adapter);
		folderListView.setSelector(new PaintDrawable(getResources().getColor(android.R.color.transparent)));
		folderListView.setOnItemClickListener(this);
		
		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ID_BTN_CANCEL:
			getActivity().setResult(Activity.RESULT_CANCELED);
			getActivity().finish();
			break;
		case R.id.ID_BTN_CONFIRM:
			switch (currentSelectType) {
			case MODIFY: {
				if (adapter.getSelectPosition() >= 0) {
					final EditText edit = new EditText(getActivity());
					DialogInterface.OnClickListener dlgClick = new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if (TextUtils.isEmpty(edit.getEditableText().toString())) {
								PopupUtil.showToastMessage(getActivity(), R.string.squareplus_folder_name_empty_message);
							} else {
								SpFolderCallBack spContentsCallBack = new SpFolderCallBack(getActivity(), SpFolderVO.class) {
									@Override
									public void callback(String url, JSONObject json, AjaxStatus status) {
										super.callback(url, json, status);
										Debug.trace(json.toString());
										if (this.item != null) {
											Intent intent = new Intent();
											intent.putExtra(ARG_KEY_SP_FOLDER_ITEM, item);
											intent.putExtra(ARG_KEY_SP_SELECT_TYPE, currentSelectType);
											getActivity().setResult(Activity.RESULT_OK, intent);
											getActivity().finish();
										}
									}
								};

								HashMap<String, String> params = new HashMap<>();
								params.put("folderName", edit.getEditableText().toString());
								params.put("folderId", folderListData.get(adapter.getSelectPosition()).getFolderId());
								new ApiLoaderEx<>(new SpUpdateFolderMySquareMenu(getActivity()), getActivity(), spContentsCallBack, params)
										.execute();
							}
						}
					};
					PopupUtil.showInputDialog(getActivity(), getString(R.string.squareplus_folder_create_message), edit, dlgClick);
				} else
					PopupUtil.showToastMessage(getActivity(), R.string.squareplus_folder_not_selected_message);
			}
				break;
			case DELETE: {
				if (adapter.getSelectPosition() >= 0) {
					DialogInterface.OnClickListener dlgClick = new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							SpFolderCallBack spContentsCallBack = new SpFolderCallBack(getActivity(), SpFolderVO.class) {
								@Override
								public void callback(String url, JSONObject json, AjaxStatus status) {
									super.callback(url, json, status);
									Debug.trace(json.toString());
									if (this.item != null) {
										Intent intent = new Intent();
										intent.putExtra(ARG_KEY_SP_FOLDER_ITEM, item);
										intent.putExtra(ARG_KEY_SP_SELECT_TYPE, currentSelectType);
										getActivity().setResult(Activity.RESULT_OK, intent);
										getActivity().finish();
									}
								}
							};

							HashMap<String, String> params = new HashMap<>();
							params.put("folderId", folderListData.get(adapter.getSelectPosition()).getFolderId());
							new ApiLoaderEx<>(new SpDeleteFolderMySquareMenu(getActivity()), getActivity(), spContentsCallBack, params)
									.execute();
						}
					};
					PopupUtil.showOkCancelDialog(getActivity(), R.string.squareplus_folder_delete_message, dlgClick);
				} else
					PopupUtil.showToastMessage(getActivity(), R.string.squareplus_folder_not_selected_message);
			}
				break;
			case SQUARE_MOVE: {
				if (adapter.getSelectPosition() >= 0) {
					DialogInterface.OnClickListener dlgClick = new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							SpFolderSquareCallBack spContentsCallBack = new SpFolderSquareCallBack(getActivity(), SpFolderSquareVO.class) {
								@Override
								public void callback(String url, JSONObject json, AjaxStatus status) {
									super.callback(url, json, status);
									Debug.trace(json.toString());
									if (this.item != null) {
										Intent intent = new Intent();
										intent.putExtra(ARG_KEY_SP_FOLDER_ITEM, item);
										intent.putExtra(ARG_KEY_SP_SELECT_TYPE, currentSelectType);
										getActivity().setResult(Activity.RESULT_OK, intent);
										getActivity().finish();
									}
								}
							};

							HashMap<String, String> params = new HashMap<>();
							params.put("sourceFolderId", orgFolderId);
							params.put("sourceId", orgSquareId);
							params.put("targetId", folderListData.get(adapter.getSelectPosition()).getFolderId());
							new ApiLoaderEx<>(new SpMoveFolderSquareMySquareMenu(getActivity()), getActivity(), spContentsCallBack, params)
									.execute();
						}
					};
					PopupUtil.showOkCancelDialog(getActivity(), R.string.squareplus_folder_move_message, dlgClick);
				} else
					PopupUtil.showToastMessage(getActivity(), R.string.squareplus_folder_not_selected_message);
			}
				break;
			case SQUARE_ADD: {
				if (adapter.getSelectPosition() >= 0) {
					DialogInterface.OnClickListener dlgClick = new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							SpFolderCallBack spContentsCallBack = new SpFolderCallBack(getActivity(), SpFolderVO.class) {
								@Override
								public void callback(String url, JSONObject json, AjaxStatus status) {
									super.callback(url, json, status);
									Debug.trace(json.toString());
									if (this.item != null) {
										Intent intent = new Intent();
										intent.putExtra(ARG_KEY_SP_FOLDER_ITEM, item);
										intent.putExtra(ARG_KEY_SP_SELECT_TYPE, currentSelectType);
										getActivity().setResult(Activity.RESULT_OK, intent);
										getActivity().finish();
									}
								}
							};
							
							HashMap<String, String> params = new HashMap<>();
							params.put("squareId", orgSquareId);
							params.put("targetId", folderListData.get(adapter.getSelectPosition()).getFolderId());
							new ApiLoaderEx<>(new SpAddFolderSquareMySquareMenu(getActivity()), getActivity(), spContentsCallBack, params)
							.execute();
						}
					};
					PopupUtil.showOkCancelDialog(getActivity(), R.string.squareplus_folder_move_message, dlgClick);
				} else
					PopupUtil.showToastMessage(getActivity(), R.string.squareplus_folder_not_selected_message);
			}
			break;

			default:
				break;
			}
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		adapter.setSelectPosition(position);
		adapter.notifyDataSetChanged();
	}
}
