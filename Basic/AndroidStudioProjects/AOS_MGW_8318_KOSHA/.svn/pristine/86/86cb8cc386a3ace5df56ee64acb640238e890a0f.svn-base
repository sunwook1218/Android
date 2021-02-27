package com.hs.mobile.gw.fragment.mailwrite;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract.Contacts;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.fragment.CommonFragmentController;
import com.hs.mobile.gw.fragment.CustomWebViewFragment;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.fragment.mailwrite.MailWriteModel.IMailWriteValueChangeListener;
import com.hs.mobile.gw.fragment.mailwrite.SendEmailParameterFactory.Builder;
import com.hs.mobile.gw.openapi.ReadEmail;
import com.hs.mobile.gw.openapi.ReadEmailResult;
import com.hs.mobile.gw.openapi.ReadEmailResult.Channel.AttachItem;
import com.hs.mobile.gw.openapi.SearchAddress;
import com.hs.mobile.gw.openapi.SearchAddressResult;
import com.hs.mobile.gw.openapi.SendEmail;
import com.hs.mobile.gw.openapi.SendMailResult;
import com.hs.mobile.gw.openapi.square.vo.ShowMailEditorViewVO;
import com.hs.mobile.gw.openapi.square.vo.ShowMailEditorViewVO.ReadType;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.util.TextViewUtils;
import com.hs.mobile.gw.view.ContactsCompletionView;
import com.hs.mobile.gw.view.DndListView;
import com.hs.mobile.gw.view.DndListView.DropListener;
import com.hs.mobile.gw.view.SelectedListItem;
import com.hs.mobile.gw.view.SelectedListItem.ObjectType;
import com.hs.mobile.gw.view.SelectedListItem.RecipientType;
import com.hs.mobile.gw.view.tokenautocomplete.TokenCompleteTextView.TokenDeleteStyle;
import com.hs.mobile.gw.view.tokenautocomplete.TokenListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MailWriteController extends CommonFragmentController<MailWriteFragment, MailWriteModel> implements OnClickListener,
		TextWatcher, OnItemClickListener, TokenListener, OnFocusChangeListener, DndListView.DragListener, DropListener,
		IMailWriteValueChangeListener, OnCheckedChangeListener, DragListAdapter.RemoveListener, View.OnTouchListener {
	protected static final int PHONE_ADDRESS = 0;
	protected static final int ORG = 1;
	private boolean isTouchable = true;

	public MailWriteController(MailWriteFragment view, MailWriteModel model) {
		super(view, model);
	}

	public void init() {
		// 드레그 앤 드롭 리스트 뷰 관련
		getView().m_dndLayout.setVisibility(View.GONE);
		getView().m_originalCbLayout.setVisibility(View.GONE);
		getView().m_originalTvLayout.setVisibility(View.GONE);
		getView().m_originalReceivedAttachFileAreaLayout.setVisibility(View.GONE);
		getView().m_cbOriginal.setChecked(false);
		getView().m_scrollView.setOnTouchListener(this);

		getView().m_originalAttachOuterLayout.setOnClickListener(this);
		getView().m_edTo.setOnFocusChangeListener(this);
		getView().m_edCC.setOnFocusChangeListener(this);
		getView().m_edBCC.setOnFocusChangeListener(this);

		getView().m_ccAndBccVisibleLayout.setFocusable(true);
		getView().m_ccAndBccVisibleLayout.setFocusableInTouchMode(true);
		getView().m_edContent.setFocusable(true);
		getView().m_edContent.setFocusableInTouchMode(true);
		getView().contentLayout.setFocusable(true);
		getView().contentLayout.setFocusableInTouchMode(true);

		getView().tvCC.setOnClickListener(this);
		getView().tvCC.setFocusable(true);
		getView().tvCC.setFocusableInTouchMode(true);
		getView().tvBCC.setFocusable(true);
		getView().tvBCC.setFocusableInTouchMode(true);
		getView().tvBCC.setOnClickListener(this);
		getView().m_dndLayout.setOnFocusChangeListener(this);
		getView().m_dndLayout.setOnClickListener(this);

		getView().m_edTo.addTextChangedListener(this);
		getView().m_edCC.addTextChangedListener(this);
		getView().m_edBCC.addTextChangedListener(this);
		getView().btnCancel.setOnClickListener(this);
		getView().btnSendMail.setOnClickListener(this);
		getView().m_btnAddTo.setOnClickListener(this);
		getView().m_btnAddCC.setOnClickListener(this);
		getView().m_btnAddBCC.setOnClickListener(this);
		getView().m_btnMoreTo.setOnClickListener(this);
		getView().m_btnMoreCC.setOnClickListener(this);
		getView().m_btnMoreBCC.setOnClickListener(this);
		getView().m_cbSelfMail.setOnCheckedChangeListener(this);
		getView().contentLayout.setOnClickListener(this);
		getView().m_ccAndBccVisibleLayout.setOnClickListener(this);

		getView().m_edTo.setThreshold(2);
		getView().m_edTo.setDeletionStyle(TokenDeleteStyle.Clear);
		getView().m_edCC.setThreshold(2);
		getView().m_edCC.setDeletionStyle(TokenDeleteStyle.Clear);
		getView().m_edBCC.setThreshold(2);
		getView().m_edBCC.setDeletionStyle(TokenDeleteStyle.Clear);

		getModel().setSelectedListAdapter(new MailWriteFilteredArrayAdapter(getContenxt(), R.layout.person_layout, getModel().mData));

		// 자동완성 관련 초기 설정.
		getView().m_edTo.setOnItemClickListener(this);
		getView().m_edCC.setOnItemClickListener(this);
		getView().m_edBCC.setOnItemClickListener(this);
		getView().m_edTo.setAdapter(getModel().getSelectedListAdapter());
		getView().m_edCC.setAdapter(getModel().getSelectedListAdapter());
		getView().m_edBCC.setAdapter(getModel().getSelectedListAdapter());
		getView().m_edTo.setTokenListener(this);
		getView().m_edCC.setTokenListener(this);
		getView().m_edBCC.setTokenListener(this);

		getView().m_edTo.performBestGuess(false);
		getView().m_edCC.performBestGuess(false);
		getView().m_edBCC.performBestGuess(false);

		// 각 컨트롤 들의 초기 값.
		getView().m_btnAddTo.setVisibility(View.GONE);
		getView().m_btnAddCC.setVisibility(View.GONE);
		getView().m_btnAddBCC.setVisibility(View.GONE);
		getView().m_originalAttachOuterLayout.setVisibility(View.GONE);

		getView().m_lvDnd.setDragListener(this);
		getView().m_lvDnd.setDropListener(this);
		LinearLayout empryLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.list_item_mail_drag, null);
		((TextView) empryLayout.findViewById(R.id.ID_TV_NAME)).setText(R.string.message_empty_list);
		((TextView) empryLayout.findViewById(R.id.ID_TV_DEPARTMENT)).setVisibility(View.GONE);
		((ImageButton) empryLayout.findViewById(R.id.ID_BTN_DELETE)).setVisibility(View.INVISIBLE);
		((ImageView) empryLayout.findViewById(R.id.dragicon)).setVisibility(View.INVISIBLE);
		getView().m_dndLayout.addView(empryLayout);
		getView().m_lvDnd.setEmptyView(empryLayout);
		getModel().setCCandBCCVisible(false);

		if (getArguments() != null && getArguments().getSerializable(MailWriteFragment.INTENT_KEY_SHOW_MAIL_EDITOR_VIEW_VO) != null) {
			getModel().m_argument = (ShowMailEditorViewVO) getArguments().getSerializable(MailWriteFragment.INTENT_KEY_SHOW_MAIL_EDITOR_VIEW_VO);

			if (getModel().m_argument.getType() == ReadType.NEW) {
				if (getModel().m_argument.getStrSelectedlist() != null) {
					String strSelectedList = getModel().m_argument.getStrSelectedlist();
					try {
						JSONArray items = new JSONObject(strSelectedList).optJSONArray("selectedlist");
						getView().m_edTo.clear();
						if (items != null) {
							for (int i = 0; i < items.length(); ++i) {
								SelectedListItem item = new SelectedListItem(items.getJSONObject(i));
								getView().m_edTo.addObject(item, item.name);
							}
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				String boxid = getModel().m_argument.getEtcBoxId();
				if (getModel().m_argument.getBoxId() != null) {
					boxid = getModel().m_argument.getBoxId().toString();
				}
				
			    ReadEmailResult callback = new ReadEmailResult() {
					@Override
					public void onResponse(String strRet) {
						super.onResponse(strRet);
						if (!this.channel.isEmpty()) {
							Channel ch = this.channel.get(0);
							getView().m_originalTvLayout.setVisibility(View.VISIBLE);
							getView().m_originalCbLayout.setVisibility(View.VISIBLE);
							getView().m_cbOriginal.setChecked(true);
							getView().m_cbSecure.setChecked(Boolean.parseBoolean(ch.security));
							getView().m_cbSecure.setClickable(Boolean.parseBoolean(ch.security) ? false : true);
							getView().m_strOrgMessage = ch.body;
							getModel().m_strMailID = ch.mailid;
							getModel().m_strBoxId = ch.boxid;
							getView().m_tvOriginal.setText(Html.fromHtml(getView().m_strOrgMessage.trim()));
							getView().m_edTitle.setText(ch.title);
							switch (getModel().m_argument.getType()) {
								case FORWARD:
									if (ch.attaches.size() > 0) {
										getView().m_originalAttachOuterLayout.setVisibility(View.VISIBLE);
										createAttachCheckBox(ch.attaches);
									}
									break;
								case REPLY:
									if (!TextUtils.isEmpty(ch.senderid) && !TextUtils.equals(ch.senderid, "[]")) {
										getView().m_edTo.addObject(new SelectedListItem(ch.senderid, "", ObjectType.USER.toString(), ch.sender));
									} else {
										// senderid가 없으면 외부메일로 인식한다.
										getView().m_edTo.addObject(new SelectedListItem("", ch.sender, ObjectType.EMAIL.toString(), ch.sender));
									}

									// 답장 및 전체답장시에는 첨부파일을 첨부하지 않는다.
									if (ch.attaches.size() > 0) {
										getView().m_originalAttachOuterLayout.setVisibility(View.GONE);
										ch.attaches.clear();
									}
									break;
								case REPLYALL:
									// 답장 및 전체답장시에는 첨부파일을 첨부하지 않는다.
									if (ch.attaches.size() > 0) {
										getView().m_originalAttachOuterLayout.setVisibility(View.GONE);
										ch.attaches.clear();
									}

									if (TextUtils.isEmpty(this.channel.get(0).to) && TextUtils.isEmpty(this.channel.get(0).cc) && TextUtils.isEmpty(this.channel.get(0).bcc)) {
										getView().m_edTo.addObject(new SelectedListItem(ch.senderid, "", ObjectType.USER.toString(), ch.sender));
									} else {
										String[] arr = this.channel.get(0).to.split(";");
										for (int index = 0; index < arr.length; index++) {

											if (TextUtils.isEmpty(arr[index])) continue;

											String to = arr[index];

//                                            Debug.trace("To 값은 : " + to);

											if(!TextUtils.isEmpty(HMGWServiceHelper.mail) && to.contains(HMGWServiceHelper.mail)){
//                                                Debug.trace("mail 값은 : " + HMGWServiceHelper.mail);
												arr[index] = "";
											}
											if(!TextUtils.isEmpty(HMGWServiceHelper.userId) && to.contains(HMGWServiceHelper.userId)) {
//                                                Debug.trace("mail 값은 : " + HMGWServiceHelper.userId);
												arr[index] = "";
											}

										}

										addContactsCompletionView(arr, getView().m_edTo);
										if (!TextUtils.isEmpty(this.channel.get(0).cc)) {
											getModel().setCCandBCCVisible(true);
											arr = this.channel.get(0).cc.split(";");
											addContactsCompletionView(arr, getView().m_edCC);
										} else {
											getView().m_edCC.clear();
										}
										if (!TextUtils.isEmpty(this.channel.get(0).bcc)) {
											getModel().setCCandBCCVisible(true);
											arr = this.channel.get(0).bcc.split(";");
											addContactsCompletionView(arr, getView().m_edBCC);
										} else {
											getView().m_edBCC.clear();
										}
									}
									break;
								default:
									break;
							}
						}
					}

					private void addContactsCompletionView(String[] arr, ContactsCompletionView edCCV) {
						for (String s : arr) {
							if (!TextUtils.isEmpty(s)){
								String[] arr2 = s.split(":");
								for (ObjectType t : ObjectType.values()) {
									if (arr2[0].startsWith(t.toString())) {
										switch (t) {
											case CONTACTS_GROUP:
											case MAIL_GROUP:
											case POSITION:
												break;
											case USER:
											case DEPARTMENT_WITH_CHILDREN:
											case DEPARTMENT: {
												String strId = arr2[0].replace(t.toString(), "");
												edCCV.addObject(new SelectedListItem(strId, "", t.toString(), arr2[1]));
											}
												break;
											case EMAIL: {
												String strId = arr2[0].replace(t.toString(), "");
												edCCV.addObject(new SelectedListItem("", arr2[1], t.toString(), arr2[1].trim()));
											}
												break;
										}
										break;
									}
								}
							}
						}
					}
				};
				
				if (TextUtils.isEmpty(getModel().m_argument.getStrPasswd())) {
					new ApiLoader(new ReadEmail(), getActivity(), callback, getModel().m_argument.getStrMessageId(), boxid, getModel().m_argument.getType()
							.getCode(), "readbymid").execute();
				}
				else {
					new ApiLoader(new ReadEmail(), getActivity(), callback, getModel().m_argument.getStrMessageId(), boxid, getModel().m_argument.getType()
							.getCode(), "readbymid", getModel().m_argument.getStrPasswd()).execute();
				}
			}
		}
	}

	@Override
	public void drop(int from, int to) {
		List<Object> arr = ((DragListAdapter) getView().m_lvDnd.getAdapter()).getData();
		Object tmp = arr.get(from);
		arr.remove(arr.get(from));
		arr.add(to, tmp);
		if (getView().m_selectedPrecipientEditText != null) {
			for (Object o : getView().m_selectedPrecipientEditText.getObjects()) {
				getView().m_selectedPrecipientEditText.removeObject(o);
			}
			for (Object o : arr) {
				getView().m_selectedPrecipientEditText.addObject(o);
			}
		}
		((BaseAdapter) getView().m_lvDnd.getAdapter()).notifyDataSetChanged();
	}

	@Override
	public void drag(int from, int to) {

	}

	@Override
	public void remove(int which) {
		// TODO Auto-generated method stub
		List<Object> arr = ((DragListAdapter) getView().m_lvDnd.getAdapter()).getData();
		if (getView().m_selectedPrecipientEditText != null) {
			Debug.trace("EditText size = ", getView().m_selectedPrecipientEditText.getObjects().size());
			for (Object o : getView().m_selectedPrecipientEditText.getObjects()) {
				getView().m_selectedPrecipientEditText.removeObject(o);
			}
			arr.remove(which);
			for (Object o : arr) {
				getView().m_selectedPrecipientEditText.addObject(o);
			}
			Debug.trace("size = ", arr.size());
		}
		((BaseAdapter) getView().m_lvDnd.getAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		switch (v.getId()) {
		case R.id.ID_ED_TO:
			if (hasFocus) {
				getView().m_btnAddTo.setVisibility(View.VISIBLE);
				getView().m_btnMoreTo.setVisibility(View.GONE);
				hideInnerLayout();
			} else {
				getView().m_btnAddTo.setVisibility(View.GONE);
				getView().m_btnMoreTo.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.ID_ED_CC:
			if (hasFocus) {
				getView().m_btnAddCC.setVisibility(View.VISIBLE);
				getView().m_btnMoreCC.setVisibility(View.GONE);
				hideInnerLayout();

			} else {
				getView().m_btnAddCC.setVisibility(View.GONE);
				getView().m_btnMoreCC.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.ID_ED_BCC:
			if (hasFocus) {
				getView().m_btnAddBCC.setVisibility(View.VISIBLE);
				getView().m_btnMoreBCC.setVisibility(View.GONE);
				hideInnerLayout();
			} else {
				getView().m_btnAddBCC.setVisibility(View.GONE);
				getView().m_btnMoreBCC.setVisibility(View.VISIBLE);
			}
			break;
		}
	}

	@Override
	public void onTokenAdded(Object token) {
		if (token != null && token instanceof SelectedListItem) {
			Debug.trace("onTokenAdded", ((SelectedListItem) token).name);
		}
		updateTokenConfirmation();
	}

	@Override
	public void onTokenRemoved(Object token) {
		if (token != null && token instanceof SelectedListItem) {
			Debug.trace("onTokenRemoved", ((SelectedListItem) token).name);
		}
		updateTokenConfirmation();
	}

	@Override
	public synchronized void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		InputMethodManager imm = (InputMethodManager) getView().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getView().m_edTo.getWindowToken(), 0);
		if(getView().m_edTo.isFocused()) imm.restartInput(getView().m_edTo);
		if(getView().m_edCC.isFocused()) imm.restartInput(getView().m_edCC);
		if(getView().m_edBCC.isFocused()) imm.restartInput(getView().m_edBCC);

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// Debug.trace("onTextChanged", s, start, before, count);
		String str = s.toString();
		if (!TextUtils.isEmpty(str) && str.contains(",")) {
			str = str.substring(str.lastIndexOf(",") + 1, str.length());
		}
		if (str.trim().length() >= 2) {
			Debug.trace("substring", str);
			getView().resetNetwork();
			SearchAddress api = new SearchAddress();
			final String query = str.trim();
			getView().addNetworkRequst(api);
			MainModel.getInstance().setLoadingProgressShow(false);
			new ApiLoader(api, getActivity(), new SearchAddressResult() {
				@Override
				public void onResponse(String strRet) {
					super.onResponse(strRet);
					getModel().mData.clear();
					if (data.size() > 0) {
						getModel().mData.addAll(this.data);
						// m_edTo.showDropDown();
					}
					getModel().getSelectedListAdapter().getFilter().filter(query, null);
					getModel().getSelectedListAdapter().notifyDataSetChanged();
					MainModel.getInstance().setLoadingProgressShow(true);
				}
			}, query).execute();
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.middleBackButton:
				MainFragment.getController().showMainMenu(v);
				break;
			case R.id.ID_BTN_CANCEL:
				PopupUtil.showOkCancelDialog(getActivity(), R.string.mail_write_cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// tkofs
						ViewModel.Log("mail canclekey pressed", "tkofs_mail");
						JSONObject cmenuview = (JSONObject) MainFragment.pressedMenuView.getTag();
						//if (cmenuview.optString("menu-id").equals("home_home")) {
							MainFragment.pressedMenuView.performClick();
							//MainFragment.pressedMenuView = null;
						//}
						getActivity().finish();
					}
				});
				break;
			case R.id.ID_BTN_SENDMAIL:
				SendEmail sendMail = new SendEmail();
				Builder builder = new Builder();
				if (getView().m_cbSelfMail.isChecked()) {
					builder.setMySelfMail(getView().m_cbSelfMail.isChecked());
					List<Object> objects = new ArrayList<Object>();
					objects.add(new SelectedListItem(HMGWServiceHelper.userId, "", ObjectType.USER.toString(), HMGWServiceHelper.name));
					builder.addRecipientList(objects, RecipientType.TO);
				} else {
					builder.addRecipientList(getView().m_edTo.getObjects(), RecipientType.TO);
					builder.addRecipientList(getView().m_edCC.getObjects(), RecipientType.CC);
					builder.addRecipientList(getView().m_edBCC.getObjects(), RecipientType.BCC);
				}

				builder.setTitle(getView().m_edTitle.getText().toString().trim());
				builder.setContent(Html.toHtml(TextViewUtils.removeUnderlines(getView().m_edContent.getText())));
				Debug.trace("getContent = " + Html.toHtml(TextViewUtils.removeUnderlines(getView().m_edContent.getText())));
				builder.setOriginalMessage(getView().m_strOrgMessage);
				builder.setWithOriginalMessage(getView().m_cbOriginal.isChecked());
				builder.setSecure(getView().m_cbSecure.isChecked());
				builder.setEmergency(getView().m_cbEmergency.isChecked());
				builder.setExcludingSender(getView().m_cbExcludingSender.isChecked());
				if (getModel().m_attachCbSelecteHashMap.size() > 0) {
					Iterator<AttachItem> iterator = getModel().m_attachCbSelecteHashMap.keySet().iterator();
					boolean bAttachEnable = false;
					while (iterator.hasNext()) {
						AttachItem item = iterator.next();
						if (getModel().m_attachCbSelecteHashMap.get(item)) {
							bAttachEnable = true;
							builder.addReceivedAttachFile(item);
						}
					}
					if (bAttachEnable) {
						builder.setReceivedAttachFileCount(getModel().m_attachCbFileHashMap.size());
						builder.setOriginalAttachFileData(getModel().m_attachItems);
						builder.setNumber(getModel().m_strMailID);
						builder.setBoxId(getModel().m_strBoxId);
					}
				}
				SendmailParameterVO sendmailParameter = builder.create();
				sendMail.setParams(sendmailParameter.getParams());
				String[] values = sendmailParameter.getValues();
				if (values != null && validateParam(sendmailParameter)) {
					new ApiLoader(sendMail, getActivity(), new SendMailResult() {
						@Override
						public void onResponse(String strRet) {
							super.onResponse(strRet);
							Debug.trace(strRet);
							ViewModel.Log(MainFragment.pressedMenuView, "tkofs_mailwrite");
							if (MainFragment.pressedMenuView != null) {
								MainFragment.pressedMenuView.performClick();
								//MainFragment.menuListHelper.onMenuClick(MainFragment.pressedMenuView);
							}
							PopupUtil.showDialog(getActivity(), R.string.mail_write_done, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//MainFragment.getController().hideWebviewTabbar();
									//MainFragment.getController().hideWebview(false);

									// tkofs


									getActivity().finish();
								}
							});
						}
					}, values).execute();
				}
				break;
			case R.id.ID_BTN_TO_ADD:
				// SEOJAEHWA : MGW-2551 전달, 답장 시 조직도 노출되도록 수정
//				if (getModel().m_argument != null && getModel().m_argument.isPopup()) {
//					PopupUtil.showDialog(getActivity(), R.string.search_mail_org_not_support);
//					return;
//				}
				String[] str = new String[]{getResources().getString(R.string.select_precipient_by_phone),
						getResources().getString(R.string.label_menu_orgchart)};
				new AlertDialog.Builder(getActivity()).setItems(str, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
							case PHONE_ADDRESS:
								if (getView().hasContactsPermission()) {
									Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
									getView().startActivityForResult(intent, MainModel.REQ_PHONE_TO_SELECT);
								}
								else {
									getView().requestContactsPermission();
								}
								break;
							case ORG:
								JSONArray jsonArray_TO = new JSONArray();
								for (Object item : getView().m_edTo.getObjects()) {
									SelectedListItem data = (SelectedListItem) item;
									jsonArray_TO.put(data.toORGJsonObject());
								}
								String selectedTO = jsonArray_TO.toString().replace("'", "").trim();
								getMainModel().showSubActivity(
										getView(),
										SubActivityType.ORG_CHART,
										new BundleUtils.Builder().add(CustomWebViewFragment.ARG_KEY_URL,
												"javascript:showOrgSelectForApp('mail','to','{\"selectedlist\":" + selectedTO + "}');").build());
								break;
						}
					}
				}).setCancelable(true).show();
				break;
			case R.id.ID_BTN_CC_ADD:
				// SEOJAEHWA : MGW-2551 전달, 답장 시 조직도 노출되도록 수정
//				if (getModel().m_argument != null && getModel().m_argument.isPopup()) {
//					PopupUtil.showDialog(getActivity(), R.string.search_mail_org_not_support);
//					return;
//				}
				JSONArray jsonArray_CC = new JSONArray();
				for (Object item : getView().m_edCC.getObjects()) {
					SelectedListItem data = (SelectedListItem) item;
					jsonArray_CC.put(data.toORGJsonObject());
				}
				String selectedCC = jsonArray_CC.toString().replace("'", "").trim();
				getMainModel().showSubActivity(
						getView(),
						SubActivityType.ORG_CHART,
						new BundleUtils.Builder().add(CustomWebViewFragment.ARG_KEY_URL,
								"javascript:showOrgSelectForApp('mail','cc','{\"selectedlist\":" + selectedCC + "}');").build());
				break;
			case R.id.ID_BTN_BCC_ADD:
				// SEOJAEHWA : MGW-2551 전달, 답장 시 조직도 노출되도록 수정
//				if (getModel().m_argument != null && getModel().m_argument.isPopup()) {
//					PopupUtil.showDialog(getActivity(), R.string.search_mail_org_not_support);
//					return;
//				}
				JSONArray jsonArray_BCC = new JSONArray();
				for (Object item : getView().m_edBCC.getObjects()) {
					SelectedListItem data = (SelectedListItem) item;
					jsonArray_BCC.put(data.toORGJsonObject());
				}
				String selectedBCC = jsonArray_BCC.toString().replace("'", "").trim();
				getMainModel().showSubActivity(
						getView(),
						SubActivityType.ORG_CHART,
						new BundleUtils.Builder().add(CustomWebViewFragment.ARG_KEY_URL,
								"javascript:showOrgSelectForApp('mail','bcc','{\"selectedlist\":" + selectedBCC + "}');").build());
				break;
			case R.id.ID_BTN_TO_MORE: {
				if (getView().m_dndLayout.getVisibility() == View.VISIBLE) {
					hideInnerLayout();
					isTouchable = true;
				} else {
					isTouchable = false;
					getView().m_scrollView.fullScroll(View.FOCUS_UP);
					getView().m_dndLayout.setVisibility(View.VISIBLE);
					TextViewUtils.hideKeyBoard(getActivity(), getView().m_edTo);
					getView().m_toLayout.requestFocus();
					getView().m_toLayout.clearFocus();
					FrameLayout.LayoutParams p = (android.widget.FrameLayout.LayoutParams) getView().m_dndLayout.getLayoutParams();
					p.topMargin = getView().m_toLayout.getBottom();
					getView().m_dndLayout.setLayoutParams(p);
					getView().m_lvDnd.setAdapter(new DragListAdapter(getView().m_edTo.getObjects()));
					DragListAdapter adapter = (DragListAdapter) getView().m_lvDnd.getAdapter();
					adapter.setRemoveListener(this);
					getView().m_selectedPrecipientEditText = getView().m_edTo;
				}
			}
			break;
			case R.id.ID_BTN_CC_MORE:
				if (getView().m_dndLayout.getVisibility() == View.VISIBLE) {
					hideInnerLayout();
					isTouchable = true;
				} else {
					isTouchable = false;

					getView().m_scrollView.fullScroll(View.FOCUS_UP);
					getView().m_dndLayout.setVisibility(View.VISIBLE);
					TextViewUtils.hideKeyBoard(getActivity(), getView().m_edCC);
					getView().m_ccLayout.requestFocus();
					getView().m_ccLayout.clearFocus();
					getView().m_scrollView.post(new Runnable() {
						@Override
						public void run() {
							FrameLayout.LayoutParams p = (android.widget.FrameLayout.LayoutParams) getView().m_dndLayout.getLayoutParams();
							getView().m_innerLinearLayout.scrollTo(0, getView().m_ccLayout.getBottom());
							p.topMargin = getView().m_ccLayout.getHeight();
							getView().m_dndLayout.setLayoutParams(p);
							getView().m_lvDnd.setAdapter(new DragListAdapter(getView().m_edCC.getObjects()));
							DragListAdapter adapter = (DragListAdapter) getView().m_lvDnd.getAdapter();
							adapter.setRemoveListener(MailWriteController.this);
							getView().m_selectedPrecipientEditText = getView().m_edCC;
						}
					});
				}
				break;
			case R.id.ID_BTN_BCC_MORE:
				if (getView().m_dndLayout.getVisibility() == View.VISIBLE) {
					hideInnerLayout();
					isTouchable = true;
				} else {
					isTouchable = false;
					getView().m_scrollView.fullScroll(View.FOCUS_UP);
					getView().m_dndLayout.setVisibility(View.VISIBLE);
					TextViewUtils.hideKeyBoard(getActivity(), getView().m_edBCC);
					getView().m_bccLayout.requestFocus();
					getView().m_bccLayout.clearFocus();
					getView().m_scrollView.post(new Runnable() {
						@Override
						public void run() {
							FrameLayout.LayoutParams p = (android.widget.FrameLayout.LayoutParams) getView().m_dndLayout.getLayoutParams();
							getView().m_innerLinearLayout.scrollTo(0, getView().m_bccLayout.getBottom());
							p.topMargin = getView().m_bccLayout.getHeight();
							getView().m_dndLayout.setLayoutParams(p);
							getView().m_lvDnd.setAdapter(new DragListAdapter(getView().m_edBCC.getObjects()));
							DragListAdapter adapter = (DragListAdapter) getView().m_lvDnd.getAdapter();
							adapter.setRemoveListener(MailWriteController.this);
							getView().m_selectedPrecipientEditText = getView().m_edBCC;
						}
					});
				}
				break;
			case R.id.ID_LAY_L_CONTENT:
				TextViewUtils.showKeyboard(getActivity(), getView().m_edContent);
				break;
			case R.id.ID_TV_CC:
			case R.id.ID_TV_BCC:
				getModel().setCCandBCCVisible(false);
				break;
			case R.id.ID_LAY_L_CC_AND_BCC_BTN:
				getModel().setCCandBCCVisible(true);
				break;
			case R.id.ID_LAY_L_ORIGINAL_ATTACH_FILE:
				toggleVisibleAttachFile();
				break;
		}
	}

	private void updateTokenConfirmation() {
		StringBuilder sb = new StringBuilder("Current tokens:\n");
		for (Object token : getView().m_edTo.getObjects()) {
			sb.append(((SelectedListItem) token).name);
			sb.append('\n');
		}
	}

	private void hideInnerLayout() {
		if (getView().m_dndLayout.getVisibility() == View.VISIBLE) {
			getView().m_dndLayout.setVisibility(View.GONE);
			getView().m_innerLinearLayout.scrollTo(0, 0);
		}
	}

	private void toggleVisibleAttachFile() {
		getView().m_originalReceivedAttachFileAreaLayout
				.setVisibility(getView().m_originalReceivedAttachFileAreaLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
	}

	private boolean validateParam(SendmailParameterVO sendmailParameter) {
		for (int i = 0; i < sendmailParameter.getParams().length; ++i) {
			// 제목 검사.
			if (TextUtils.equals(sendmailParameter.getParams()[i], "subject")) {
				if (TextUtils.isEmpty(sendmailParameter.getValues()[i])) {
					PopupUtil.showDialog(getActivity(), R.string.mail_write_empty_title);
					return false;
				}
			}
			// 받는 사람 검사.
			else if (TextUtils.equals(sendmailParameter.getParams()[i], "to")) {
				if (TextUtils.isEmpty(sendmailParameter.getValues()[i])) {
					PopupUtil.showDialog(getActivity(), R.string.mail_write_empty_to);
					return false;
				}
			}
		}
		return true;
	}

	private void setAttachIndicatorText(int nCurrent, int nTotal) {
		getView().m_tvOriginalAttachIndicator.setText(getString(R.string.label_mail_write_origin_attach_inticator, nCurrent, nTotal));
	}

	protected void createAttachCheckBox(ArrayList<AttachItem> items) {
		getModel().m_attachItems = items;
		getView().m_originalReceivedAttachFileAreaLayout.setVisibility(View.VISIBLE);
		for (int i = 0; i < items.size(); ++i) {
			LinearLayout attachFileLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.list_item_cb_attach, null);
			CheckBox cb = (CheckBox) attachFileLayout.findViewById(R.id.ID_CB_ATTACH_FILE);
			cb.setChecked(true);
			cb.setButtonDrawable(R.drawable.style_btn_checkbox_blue);
			cb.setOnCheckedChangeListener(this);

			((TextView) attachFileLayout.findViewById(R.id.ID_TV_NAME)).setText(getString(R.string.label_mail_write_origin_attach_cb,
					items.get(i).att_title.trim(), items.get(i).att_size.trim()));
			getModel().m_attachCbFileHashMap.put(cb, items.get(i));
			getModel().m_attachCbSelecteHashMap.put(items.get(i), true);
			getView().m_originalReceivedAttachFileAreaLayout.addView(attachFileLayout, LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
		}
		setAttachIndicatorText(items.size(), items.size());
	}

	@Override
	public void onCCandBCCLayoutVisibleChange(boolean b) {
		if (b) {
			getView().m_ccAndBccVisibleLayout.setVisibility(View.GONE);
			getView().m_ccLayout.setVisibility(View.VISIBLE);
			getView().m_bccLayout.setVisibility(View.VISIBLE);
		} else {
			getView().m_ccAndBccVisibleLayout.setVisibility(View.VISIBLE);
			getView().m_ccLayout.setVisibility(View.GONE);
			getView().m_bccLayout.setVisibility(View.GONE);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		if (buttonView.getId() == R.id.ID_CB_ATTACH_FILE) {
			getModel().m_attachCbSelecteHashMap.put(getModel().m_attachCbFileHashMap.get(buttonView), isChecked);
			int nCurrent = 0;
			Iterator<AttachItem> iterator = getModel().m_attachCbSelecteHashMap.keySet().iterator();
			while (iterator.hasNext()) {
				AttachItem next = iterator.next();
				if (getModel().m_attachCbSelecteHashMap.get(next)) {
					nCurrent++;
				}
			}
			setAttachIndicatorText(nCurrent, getModel().m_attachCbSelecteHashMap.size());
		} else if (buttonView.getId() == R.id.ID_CB_SELF_MAIL) {
			if (isChecked) {
				getView().m_toLayout.setVisibility(View.GONE);
				getView().m_ccbccLayout.setVisibility(View.GONE);
			} else {
				getView().m_toLayout.setVisibility(View.VISIBLE);
				getView().m_ccbccLayout.setVisibility(View.VISIBLE);
			}

		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return isTouchable? false:true;
	}
}
