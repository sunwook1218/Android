package com.hs.mobile.gw;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.BoardAdapter;
import com.hs.mobile.gw.adapter.ContactGroupListAdapter;
import com.hs.mobile.gw.adapter.ContactListAdapter;
import com.hs.mobile.gw.adapter.MailListAdapter;
import com.hs.mobile.gw.adapter.MyWorkGroupListAdapter;
import com.hs.mobile.gw.adapter.PersonalMailBoxListAdapter;
import com.hs.mobile.gw.adapter.SignListAdapter;
import com.hs.mobile.gw.adapter.squareplus.SpFolderListAdapter;
import com.hs.mobile.gw.adapter.squareplus.SpOrgGroupAdapter;
import com.hs.mobile.gw.adapter.squareplus.SpSquareListAdapter;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.gnb.GnbDataModel;
import com.hs.mobile.gw.gnb.Group;
import com.hs.mobile.gw..R;
import com.hs.mobile.gw.openapi.OrgTree;
import com.hs.mobile.gw.openapi.OrgTreeCallBack;
import com.hs.mobile.gw.openapi.square.MyWorkGroupMenuList;
import com.hs.mobile.gw.openapi.square.MyWorkGroupMenuListResult;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO;
import com.hs.mobile.gw.openapi.squareplus.SpGetSquareMenuList;
import com.hs.mobile.gw.openapi.squareplus.SpInitMySquareMenu;
import com.hs.mobile.gw.openapi.squareplus.callback.SpFolderListCallBack;
import com.hs.mobile.gw.openapi.squareplus.callback.SpSquareListCallBack;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderSquareVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpFolderVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareVO;
import com.hs.mobile.gw.openapi.vo.OrgTreeItemVO;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.sqlite.CustomIconDBHelper;
import com.hs.mobile.gw.util.AndroidUtils;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.FooterToolBar.ButtonPosition;
import com.hs.mobile.gw.view.FooterToolBar.IFooterToolBarListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.Mode;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshExpandableListView;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MenuListHelper {

	final String SECTION_LIST = "section-list";
	final String SECTION_NAME = "section-name";
	final String SECTION_OPEN_HIDDEN_MENU = "open-hidden-menu";
	final String VISIBLE_MENU_LIST = "visible-menu-list";
	final String HIDDEN_MENU_LIST = "hidden-menu-list";
	final String MENU_TYPE = "menu-type";
	final String MENU_ID = "menu-id";
	public static final String MENU_NAME = "menu-name";
	final String CUSTOM_VIEW_TYPE = "view-type";
	final String CUSTOM_MENU_FUNCTION = "function";
	final String ICON_TYPE = "icon-type";
	final String ICON_NAME = "icon-name";
	final String CUSTOM_MENU_TYPE = "C";
	final String PRODUCT_MENU_TYPE = "P";
	final String CUSTOM_ICON_TYPE = "C";
	public final String PRODUCT_ICON_TYPE = "P";
	final String VIEW_TYPE_BROWSER = "B";
	final String VIEW_TYPE_CUSTOM_WEBVIEW = "C";
	final String VIEW_TYPE_ANOTHER_APP = "A";
	final String ANOTHER_APP_PACKAGE_NAME = "package-name";
	final static String SCHEDULE_ORG = "original";
	final static String SCHEDULE_TM1 = "tmanager";
	final static String SCHEDULE_TM2 = "tmanager2";
	final static String SETTINGS_SET1 = "settings1";
	final static String SETTINGS_SET2 = "settings2";
	public static final String FROM_HOME_LINK = "from_home_link";
	public static final String FROM_HOME_COUNT = "from_home_count";
	// tkofs
	public HashMap<String, Group> groupMenuList;
    // tkofs
    public HashMap<String, JSONObject> menuData;
	public DateFormat lastUpdatedDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,
			Locale.getDefault());
	final String START_MENU_CUSTOM_ID = "custom_menu";
	public static final String PREF_KEY_INITIAL_MENU = "initial_menu";

	public enum WorkGroupType {
		FAVORITE("0", R.drawable.icon_square_favorite), PROCESS("1", R.drawable.icon_square_work_group), COMPLETE("2",
				R.drawable.icon_square_end_group);
		private String m_strType;
		private int m_nIconRes;

		private WorkGroupType(String s, int n) {
			m_strType = s;
			m_nIconRes = n;
		}

		public String getType() {
			return m_strType;
		}

		public int getIconRes() {
			return m_nIconRes;
		}
	}

	public enum SpSquareType {
		FAVORITE("0", R.drawable.sp_group_bookmark_on), ING("1", R.drawable.sp_group_bookmark), ORG("3", R.drawable.sp_group_bookmark), OPEN("5",
				R.drawable.sp_group_bookmark), END("2", R.drawable.icon_square_end_group) ;
		private String m_strType;
		private int m_nIconRes;

		private SpSquareType(String s, int n) {
			m_strType = s;
			m_nIconRes = n;
		}

		public String getType() {
			return m_strType;
		}
//
		public int getIconRes() {
			return m_nIconRes;
		}
	}

	public static enum MenuIDsMap {
		home_home, square_favorite, square_work_group, square_end_group, square_plus_favorite, square_plus_my_group,
		square_plus_org_group,square_plus_public_group, square_plus_end_group, mail_writing, mail_received, mail_sent,
		mail_deleted, mail_temp, mail_personal, mail_selfbox, board_custom, board_notice, board_recent, board_all, board_bookmark,
		approval_waiting, approval_progress, approval_personal, approval_archive, approval_draft, organization_chart,
		schedule_schdlist, schedule_reservelist, schedule_todolist, schedule_addschd, schedule_addtodo, schedule_search,
		contact_personal, contact_dept, contact_group, settings_absent, settings_absent2, settings_personal_info,
		settings_password, settings_logout, custom_webview_menu, approval_receipt_waiting, approval_reject, approval_archive_informal,
		approval_sending_process, gongram_waiting, gongram_complete, mailUnread, settings_notify_list
		, menu_show_all // 전체메뉴
		, home_gd_gongdan	// 공단지원현황
		, home_gd_ex_schedule	// 임원일정
	}

	public static HashMap<String, String> menuScriptFunctionMap;
    // 홈에서 게시물을 직접 클릭시 해당 메뉴명이 전체게시판 명에 덮어지는 문제때문에(로컬라이징문제로 변수저장)
    public static String boardAllName = "";
	static {
		menuScriptFunctionMap = new HashMap<String, String>();
		// 메일 쓰기
		menuScriptFunctionMap.put(MenuIDsMap.mail_writing.toString(), "javascript:showMailWrite();");
		// 조직도
		menuScriptFunctionMap.put(MenuIDsMap.organization_chart.toString(), "javascript:showOrgTree();");
		// 일정 추가
		menuScriptFunctionMap.put(MenuIDsMap.schedule_addschd.toString() + SCHEDULE_ORG,
				"javascript:popupSch6AddView();");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_addschd.toString() + SCHEDULE_TM1, "javascript:showSchAdd();");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_addschd.toString() + SCHEDULE_TM2, "javascript:showSch2Add();");
		// 할일 추가
		menuScriptFunctionMap.put(MenuIDsMap.schedule_addtodo.toString() + SCHEDULE_TM1, "javascript:showTodoAdd()");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_addtodo.toString() + SCHEDULE_TM2, "javascript:showTodo2Add()");
		// 일정 목록
		menuScriptFunctionMap.put(MenuIDsMap.schedule_schdlist.toString() + SCHEDULE_ORG, "javascript:showSch6List();");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_schdlist.toString() + SCHEDULE_TM1,
				"javascript:showSchList('schlist');");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_schdlist.toString() + SCHEDULE_TM2,
				"javascript:showSch2List('schlist');");
		// 예약목록
		menuScriptFunctionMap.put(MenuIDsMap.schedule_reservelist.toString() + SCHEDULE_TM1,
				"javascript:showSchList('equiplist');");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_reservelist.toString() + SCHEDULE_TM2,
				"javascript:showSch2List('equiplist');");
		// 할일 목록
		menuScriptFunctionMap.put(MenuIDsMap.schedule_todolist.toString() + SCHEDULE_TM1,
				"javascript:showSchList('todolist');");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_todolist.toString() + SCHEDULE_TM2,
				"javascript:showTodo2List('todolist');");
		// 검색
		menuScriptFunctionMap.put(MenuIDsMap.schedule_search.toString() + SCHEDULE_TM1,
				"javascript:showSchSearchTab('sch');");
		menuScriptFunctionMap.put(MenuIDsMap.schedule_search.toString() + SCHEDULE_TM2,
				"javascript:showSch2SearchTab('sch');");
		// 부재설정
		menuScriptFunctionMap.put(MenuIDsMap.settings_absent.toString() + SETTINGS_SET1,
				"javascript:showSettingsAbsence();");
		menuScriptFunctionMap.put(MenuIDsMap.settings_absent.toString() + SETTINGS_SET2,
				"javascript:showSettings2Absence();");
		// 사용자 정보
		menuScriptFunctionMap.put(MenuIDsMap.settings_personal_info.toString(), "javascript:showSettingsUserInfo();");
		// 암호 설정
		menuScriptFunctionMap.put(MenuIDsMap.settings_password.toString(), "javascript:showSettingsPassword();");
        // tkofs
        menuScriptFunctionMap.put(MenuIDsMap.menu_show_all.toString(), "javascript:menu_show_all();");
    }

	/*
	 * 여기서 메뉴를 생성하고, 메뉴를 클릭했을때 이벤트를 처리하고, 섹션을 클릭했을때 이벤트를 처리하고,
	 */

	FragmentActivity activity;
	LinearLayout menuContainer;
	JSONArray menuListData;
    JSONArray bbsMenuListData;
	LayoutInflater inflater;
    HashMap<String, View> parantMenuMap;
	HashMap<String, View> menuMap;

	CustomIconDBHelper db;
    private MainFragment m_view;
    private MainController m_controller;
    private View emptyView;

	public MenuListHelper(MainFragment f, LinearLayout menuContainer, JSONArray menuListData) {
		super();
		m_view = f;
		activity = f.getActivity();
		this.menuContainer = menuContainer;
		this.menuListData = menuListData;
		this.db = CustomIconDBHelper.getDBHelper(activity);
        this.parantMenuMap = new HashMap<String, View>();
		this.menuMap = new HashMap<String, View>();
		inflater = LayoutInflater.from(f.getActivity());
        // tkofs
        this.groupMenuList = new HashMap();
        // tkofs
        GnbDataModel.menuListHelper = this;
        /*try { // 아이콘 임시 ( 데이터에 아이콘 변경될 때 까지 )
            JSONObject iconJsonData = new JSONObject(new Icons().loadJSONFromAsset(activity));
            GnbDataModel.iconData = iconJsonData;
        } catch (Exception e) {
        }*/
    }

	public void createMenuList() {
		// menuContainer.removeAllViewsInLayout();
		if (menuListData != null) {
			JSONArray sectionData = menuListData;// .optJSONArray(SECTION_LIST);
			for (int i = 0; i < sectionData.length(); i++) {
                createLnbMenuList(sectionData.optJSONObject(i), menuContainer); // tkofs
            }
			//createMenuDivider(menuContainer);
			//createMenuDummy(menuContainer);
			//createMenuDummy(menuContainer);
		}
	}


/**
     * @param sectionData
     * @param parent
     * @Autor likearts
     * @Date 18.11.23
     */
    private void createLnbMenuList(JSONObject sectionData, LinearLayout parent) {
        String sectionId = sectionData.optString(SECTION_ID);
        JSONArray visibleMenuListData = sectionData.optJSONArray(VISIBLE_MENU_LIST);
		ViewModel.Log("tkofs_menu", "sectionId : " + sectionId);
        /*if(sectionId.equals("board")){
            for (int i = 0; i < bbsMenuListData.length(); i++) {
                JSONObject tempJson = bbsMenuListData.optJSONObject(i);
                try {
                    tempJson.put(GnbDataModel.menuListHelper.MENU_TYPE, "P");
                    tempJson.put(GnbDataModel.menuListHelper.MENU_NAME, tempJson.optString("@text"));
                    tempJson.put(GnbDataModel.menuListHelper.MENU_ID, "board_custom_all");
                    tempJson.put(GnbDataModel.menuListHelper.ICON_TYPE, "C");
                    tempJson.put(GnbDataModel.menuListHelper.ICON_NAME, "icon_custom_sample_001.png");
                    tempJson.put(GnbDataModel.menuListHelper.CUSTOM_MENU_FUNCTION, tempJson.optString("@id"));

                    sectionData.optJSONArray(VISIBLE_MENU_LIST).put(tempJson);
                } catch (JSONException e) {
                }
            }
        }*/

        // 118.11.26 홈일경우
        if (sectionId.equals("home")) {
			ViewModel.Log("tkofs_menu", "sectionId : " + sectionId);
            try {
                JSONObject homeData = visibleMenuListData.optJSONObject(0);
				m_view.lnbLogo.setTag(homeData);
				addMenuMap(homeData.optString("menu-id"), homeData, m_view.lnbLogo);
				m_view.lnbLogo.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						onMenuClick(v);
					}
				});
            } catch (Exception e) {
				ViewModel.Log("tkofs_menu", "home menu err : " + sectionId);
            }
        }

        groupMenuList.put(sectionId, new Group(GnbDataModel.mainActivity, sectionData, "LNB", parent, "VERTICAL"));
    }

    /**
     * @param mid
     * @Autor likearts
     * @Date 18.11.22
     * @currentViewMenuList
     * @description - 페이지가 변경될 때
     * - 좌측에 관련 메뉴만 보이도록
     */
    public void currentViewMenuList(String mid) {
        Boolean match = false;
        if (groupMenuList.size() > 0) {
            try {
                for (String key : groupMenuList.keySet()) {
                    if ("home".equals(key)) continue;
                    Group cgroup = groupMenuList.get(key);
                    cgroup.setVisibleChange(false);
                    // || cgroup.getSectionId().equals(mid)
                    if(cgroup.getShowMenuId(mid) ) {
                        cgroup.setVisibleChange(true);
                        match = true;
                    }
                }
                // 그룹이 없는 메뉴일경우 좌측에 전체 메뉴 보여줌
                if (!match) {
                    for (String key : groupMenuList.keySet()) {
                        if ("home".equals(key)) continue;
                        // 숨김메뉴가 아닐경우에만
                        if(!groupMenuList.get(key).hideGroup) groupMenuList.get(key).setVisibleChange(true);
                    }
                }
            } catch (Exception e) {
                //..
            }
        }
    }
	
	/**
     * @param menuID
     * @param menuData
     * @param "data"
     * @Autor likearts
     * @Date 18.11.26
     * @name addMenuMap
     */
    public void addMenuMap(String menuID, JSONObject menuData, View menu) {
        try {
            if (menuMap.get(menuID) == null) {
                if (TextUtils.equals(MenuIDsMap.board_custom.toString(), menuID)) {
                    String customMenuFunction = menuData.optString(CUSTOM_MENU_FUNCTION);

                    menuMap.put(menuID + customMenuFunction, menu);
                } else {
                    menuMap.put(menuID, menu);
                }
            }
        } catch (Exception e) {
        }
    }
	
	/*
	 * 섹션
	 */
	/*private void createSectionView(JSONObject sectionData, LinearLayout parent) {
		final LinearLayout sectionView = (LinearLayout) inflater.inflate(R.layout.template_section, parent, false);
		TextView sectionNameView = (TextView) sectionView.findViewById(R.id.menu_section_title);
		Button sectionToggleButton = (Button) sectionView.findViewById(R.id.menu_section_toggle_icon);
		LinearLayout visibleMenuListView = (LinearLayout) sectionView.findViewById(R.id.visible_menu_list);
		final LinearLayout hiddenMenuListView = (LinearLayout) sectionView.findViewById(R.id.hidden_menu_list);

		String sectionName = sectionData.optString(SECTION_NAME);
		boolean isHiddenMenuOpened = sectionData.optBoolean(SECTION_OPEN_HIDDEN_MENU);
		JSONArray visibleMenuListData = sectionData.optJSONArray(VISIBLE_MENU_LIST);
		JSONArray hiddenMenuListData = sectionData.optJSONArray(HIDDEN_MENU_LIST);
		boolean useHiddenMenu = hiddenMenuListData != null && hiddenMenuListData.length() > 0 ? true : false; // 숨김

		if (useHiddenMenu) {
			sectionView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (v.isSelected()) {
						hiddenMenuListView.setVisibility(View.GONE);
					} else {
						hiddenMenuListView.setVisibility(View.VISIBLE);
					}
					v.setSelected(!v.isSelected());
				}
			});
			sectionToggleButton.setVisibility(View.VISIBLE);// 토글 버튼 보이기
			sectionToggleButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					sectionView.performClick();
				}
			});

			if (isHiddenMenuOpened) { // 숨김 아이콘 보이기 및 아이콘 선택됨으로 변경
				sectionToggleButton.setSelected(true);
				sectionView.setSelected(true);
				hiddenMenuListView.setVisibility(View.VISIBLE);
			}
		}
		sectionNameView.setText(sectionName); // 섹션 이름 세팅

		// 보이는 메뉴 추가
		if (visibleMenuListData != null) {
			for (int i = 0; i < visibleMenuListData.length(); i++) {
				// SEOJAEHWA: 사용자 메뉴 건과 관련해 menuIDsMap 에 없어도 표시 되어야 함 - P타입 제외
				String menuType = visibleMenuListData.optJSONObject(i).optString(MENU_TYPE);
				if (menuType.equals(PRODUCT_MENU_TYPE) && !isExistInMenuIds(visibleMenuListData.optJSONObject(i))) {
					continue;
				}

				String menuID = visibleMenuListData.optJSONObject(i).optString(MENU_ID);
				if (menuID.equals(MenuIDsMap.approval_receipt_waiting.name()) && !HMGWServiceHelper.appr_auth) {
					continue;
				}

				createMenuView(visibleMenuListData.optJSONObject(i), visibleMenuListView);
				if ((i + 1) != visibleMenuListData.length()) {
					createMenuDivider(visibleMenuListView);
				}
			}
		}
		if (hiddenMenuListData != null) {
			if (useHiddenMenu) {
				// 숨김 메뉴 추가
				createMenuDivider(hiddenMenuListView);
				for (int i = 0; i < hiddenMenuListData.length(); i++) {
					// SEOJAEHWA: 사용자 메뉴 건과 관련해 menuIDsMap 에 없어도 표시 되어야 함 - P타입 제외
					String menuType = hiddenMenuListData.optJSONObject(i).optString(MENU_TYPE);
					if (menuType.equals(PRODUCT_MENU_TYPE) && !isExistInMenuIds(hiddenMenuListData.optJSONObject(i))) {
						continue;
					}

					String menuID = hiddenMenuListData.optJSONObject(i).optString(MENU_ID);
					if (menuID.equals(MenuIDsMap.approval_receipt_waiting.name()) && !HMGWServiceHelper.appr_auth) {
						continue;
					}

					createMenuView(hiddenMenuListData.optJSONObject(i), hiddenMenuListView);
					if ((i + 1) != hiddenMenuListData.length()) {
						createMenuDivider(hiddenMenuListView);
					}
				}
			}
		}

		parent.addView(sectionView);
	}*/

	/**
	 * SEOJAEHWA
	 * Check value exist in MenuIDsMap(Enum)
	 * @param menuData menu data
	 * @return if exist, return true, if not, return false.
	 */
	private boolean isExistInMenuIds(JSONObject menuData) {
		String menuID = menuData.optString(MENU_ID);
		for (MenuIDsMap id : MenuIDsMap.values()) {
			if (id.name().equals(menuID)) {
				if (menuID.equals(MenuIDsMap.approval_receipt_waiting.name())&& !HMGWServiceHelper.appr_auth) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * 메뉴
	 */
	/*private void createMenuView(JSONObject menuData, LinearLayout parent) {
		View menu = null;
		String menuType = menuData.optString(MENU_TYPE);
		String menuName = menuData.optString(MENU_NAME);
		String menuID = menuData.optString(MENU_ID);
		String iconType = menuData.optString(ICON_TYPE);
		String iconName = menuData.optString(ICON_NAME);
		ImageView iconView = null;
		TextView titleView = null;
		if (PRODUCT_MENU_TYPE.equalsIgnoreCase(menuType)) {
			MenuIDsMap productMenu;
			productMenu = MenuIDsMap.valueOf(menuID);
			switch (productMenu) {
			case mail_received:
			case board_recent:
			case approval_waiting:
			case approval_receipt_waiting:
			case gongram_waiting:
			case square_plus_my_group: {// 카운트가 있는 메뉴
				menu = inflater.inflate(R.layout.template_menu_with_count, parent, false);
				break;
			}
			default: {// 일반 메뉴
				menu = inflater.inflate(R.layout.template_menu, parent, false);
				break;
			}
			}
		} else {
			menu = inflater.inflate(R.layout.template_menu, parent, false);
		}

		iconView = (ImageView) menu.findViewById(R.id.menu_icon);
		titleView = (TextView) menu.findViewById(R.id.menu_title);

		titleView.setText(menuName);

		if (PRODUCT_ICON_TYPE.equalsIgnoreCase(iconType)) {
			String iconNameWithoutExp = iconName.split("\\.")[0];
			iconView.setImageResource(
					activity.getResources().getIdentifier(iconNameWithoutExp, "drawable", activity.getPackageName()));
		} else {
			CustomIcon icon = db.getIcon(iconName);
			if (icon == null) {
				iconView.setImageResource(R.drawable.icon_custom_default);
			} else {
				Bitmap iconBitMap = BitmapFactory.decodeByteArray(icon.getByteArray(), 0, icon.getByteArray().length);
				iconBitMap.setDensity(DisplayMetrics.DENSITY_XHIGH);
				iconView.setImageBitmap(iconBitMap);
			}
		}

		menu.setTag(menuData);

		menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onMenuClick(v);
			}
		});
		// 커스텀 게시판 사용시 HashMap에 유니크한 id로 지정하기위하여 board_custom[boardid]로 합쳐서 유니크
		// id로 사용 (ex : board_custom0000007vc)
		if (TextUtils.equals(MenuIDsMap.board_custom.toString(), menuID)) {
			String customMenuFunction = menuData.optString(CUSTOM_MENU_FUNCTION);
			menuMap.put(menuID + customMenuFunction, menu);
		} else
			menuMap.put(menuID, menu);

		parent.addView(menu);
	}*/

	/*
	 * 기타
	 */
	/*private void createMenuDivider(LinearLayout parent) {
		ImageView divider = (ImageView) inflater.inflate(R.layout.template_menu_divider, parent, false);
		parent.addView(divider);
	}*/

	/*private void createMenuDummy(LinearLayout parent) {
		LinearLayout dummy = (LinearLayout) inflater.inflate(R.layout.template_menu_dummy, parent, false);
		parent.addView(dummy);
	}*/

	public void setMenuCount(String menuID, String count) {
		View view = menuMap.get(menuID);
		if (view != null){
			TextView menuCountView = (TextView) view.findViewById(R.id.badge_count); // tkofs R.id.menu_count
			if (menuCountView != null) {
			if (count == null || count.equals("0") || Integer.parseInt(count) == 0) {
					menuCountView.setVisibility(View.GONE);
				} else {
					menuCountView.setVisibility(View.VISIBLE);
					menuCountView.setText(count);
				}
			}
		}
	}

    public void resetPopupValues(){
        // 18.12.28 likearts 팝업모드 초기화
        MainModel.getInstance().setPopupMode(false);
        MainModel.getInstance().setNavibarVisible(false); // 상단네비바 초기화
        ViewModel.backKey_Lock = false;
    }

	public void onMenuClick(View view, @Nullable String event) {
        resetPopupValues();
		JSONObject menuData = (JSONObject) view.getTag();
		ViewModel.Log(menuData.optString("menu-id"), "tkofs_mail_menu_helper");
		if (!menuData.optString("menu-id").equals("mail_writing")) {
			MainFragment.pressedMenuView = view; // 메일쓰기 아닐때만 pressedMenuView 업데이트
		}else{
			ViewModel.backKey_Lock = true; // 메일쓰기 페이지일경우 안드로이드 시스템 뒤로가기 버튼 이벤트 발생 막기
		}

		String menuIDString = menuData.optString(MENU_ID);
		String menuType = menuData.optString(MENU_TYPE);

		// tkofs 메뉴 그룹화
        //currentViewMenuList(menuIDString);

		ViewModel.Log("board_bookmark menuType " + menuType + "menuIDString " + menuIDString);
		
		if (!CUSTOM_MENU_TYPE.equalsIgnoreCase(menuType)) { // 제품 메뉴이면
			if (event != null) {
				try {
					menuData.put(MainActivity.PUSH_MSG_EVENT, event);
				} catch (JSONException e) {
					Debug.trace(e.getMessage());
				}
			} else {
				if (menuData.has(MainActivity.PUSH_MSG_EVENT)) {
					menuData.remove(MainActivity.PUSH_MSG_EVENT);
				}
			}
			
			MenuIDsMap menuID;
			menuID = MenuIDsMap.valueOf(menuIDString);
//			menuID = MenuIDsMap.mail_received;
			if (menuID == MenuIDsMap.approval_archive) {
				MainModel.getInstance().setApprovalArchiveMenu(true);
			} else {
				MainModel.getInstance().setApprovalArchiveMenu(false);
			}

			switch (menuID) {
				case settings_logout: {
					// tkofs 로그아웃 컨펌
					if(ViewModel.currentActivity != null){
						new AlertDialog.Builder(ViewModel.currentActivity).setTitle(R.string.menu_logout).setMessage(R.string.alert_message_app_logout)
								.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int which) {
										AndroidUtils.deleteSaveFolder(null);
										if(MainModel.getInstance().getHomeFragment() != null && MainModel.getInstance().getHomeFragment().getActivity() != null){
											m_controller.doLogout();
											ViewModel.currentActivity.finish();
											MainModel.getInstance().getHomeFragment().getActivity().finish();	// home 이면 홈종료
										}else{
											ViewModel.currentActivity.finish();
											m_controller.doLogout();
										}
									}
								}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
							}
						}).create().show();
					}

					}
					break;
				case home_home: {
					m_controller.hideLoadingImage();
					m_controller.hideWaitingProgressDialog();
					MainModel.getInstance().showSubActivity(activity, SubActivityType.HOME_HOME, null);
				}
					break;
				case mail_writing: {
					// MainFragment.isPopupMode = true;
					// String scriptFunction =
					// menuScriptFunctionMap.get(menuID.toString());
					// MainFragment.loadWebviewWithDelay(scriptFunction);
					// intent.putExtra(SubActivity.INTENT_KEY_VALUE, bd);
					MainModel.getInstance().setNavibarVisible(true); // tkofs
					MainModel.getInstance().showSubActivity(activity, SubActivityType.MAIL_WRITE, null);
				}
				break;
				case schedule_addschd:
				case schedule_addtodo: {
					MainModel.getInstance().setPopupMode(true);
					String scriptFunction = menuScriptFunctionMap.get(menuID.toString() + HMGWServiceHelper.schedule_type);
					m_controller.loadWebviewWithDelay(scriptFunction);
					break;
				}
				default: {
					MainModel.getInstance().setPopupMode(false);
					changeContentByProductMenu(menuID, menuData);
					break;
				}
			}
		} else {// 커스텀 메뉴이면
			String menuName = menuData.optString(MENU_NAME);
			String viewType = menuData.optString(CUSTOM_VIEW_TYPE);
			String function;
			if (VIEW_TYPE_ANOTHER_APP.equalsIgnoreCase(viewType)) {
				function = menuData.optString(ANOTHER_APP_PACKAGE_NAME);
			} else {
				function = menuData.optString(CUSTOM_MENU_FUNCTION);
			}
            MenuIDsMap menuID;
			try {
                menuID = MenuIDsMap.valueOf(menuIDString);
            } catch (Exception e) {
                menuID = MenuIDsMap.mail_received;
            }

            startCustomMenu(menuName, viewType, function);

		}

		try {
			menuData.putOpt("startServer", false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Debug.trace(e.getMessage());
		}
		view.setTag(menuData);
	}

	public void onMenuClick(View view) {
		onMenuClick(view, null);
	}

	final String RESERVED_WORD_EMPCODE = "{$EmpCode}";
	final String RESERVED_WORD_USERID = "{$UserID}";
	final String RESERVED_WORD_DEPTID = "{$DeptID}";
	final String RESERVED_WORD_USERKEY = "{$UserKey}";
	final String RESERVED_WORD_PASSWORD = "{$Password}"; // 비공개 예약어. 가이드 되지 않음.
															// 나중을 위해 미리 추가.
	final String PARAM_NAE_EMPCODE = "empcode";
	final String PARAM_NAE_USERID = "userid";
	final String PARAM_NAE_DEPTID = "deptid";
	final String PARAM_NAE_USERKEY = "userkey";
	final String PARAM_NAE_PASSWORD = "password";
	public ArrayList<MyWorkGroupMenuListItemVO> m_myWorkGroupListData;
	public String SECTION_ID = "section-id";
	public List<SpFolderVO> m_spFolderListData;
	public List<SpSquareVO> m_spSquareListData;
	public List<OrgTreeItemVO> m_spOrgGroupListData;
	public PullToRefreshListView m_lvSquareGroup;
	public PullToRefreshExpandableListView m_lvSquareExpandableGroup;
	private MyWorkGroupListAdapter m_myWorkGroupListAdapter;
	private SpSquareListAdapter m_spSquareListAdapter;
	public SpFolderListAdapter m_spFolderListAdapter;
	private SpOrgGroupAdapter m_spOrgGroupAdapter;

	private String replaceReservedWord(String inputString) {
		inputString = inputString.replace(RESERVED_WORD_EMPCODE, HMGWServiceHelper.empcode);
		inputString = inputString.replace(RESERVED_WORD_USERID, HMGWServiceHelper.userId);
		inputString = inputString.replace(RESERVED_WORD_DEPTID, HMGWServiceHelper.deptId);
		inputString = inputString.replace(RESERVED_WORD_USERKEY, HMGWServiceHelper.key);
		inputString = inputString.replace(RESERVED_WORD_PASSWORD, HMGWServiceHelper.password);
		return inputString;
	}

	private void startCustomMenu(String title, String viewType, String function) {
		Debug.trace("title:" + title + "/type:" + viewType + "/functin:" + function);
		function = replaceReservedWord(function);
		Debug.trace("URL Loading in customWebview: " + function);

		if (VIEW_TYPE_BROWSER.equalsIgnoreCase(viewType)) {
			Intent httpIntent = new Intent(Intent.ACTION_VIEW);
			httpIntent.setData(Uri.parse(function));
			activity.startActivity(httpIntent);
		} else if (VIEW_TYPE_CUSTOM_WEBVIEW.equalsIgnoreCase(viewType)) {
			initViewByMenuID(MenuIDsMap.custom_webview_menu);
			m_controller.setCustomWebviewNavibarTitle(title);
			m_controller.loadCustomURLInWebview(function);
		} else if (VIEW_TYPE_ANOTHER_APP.equalsIgnoreCase(viewType)) {
			Intent launchIntent = activity.getPackageManager().getLaunchIntentForPackage(function);
			if (isInstalledApplication(function)) {
				launchIntent.putExtra(PARAM_NAE_EMPCODE, HMGWServiceHelper.empcode);
				launchIntent.putExtra(PARAM_NAE_DEPTID, HMGWServiceHelper.deptId);
				launchIntent.putExtra(PARAM_NAE_USERID, HMGWServiceHelper.userId);
				launchIntent.putExtra(PARAM_NAE_USERKEY, HMGWServiceHelper.key);
				launchIntent.putExtra(PARAM_NAE_PASSWORD, HMGWServiceHelper.password);// 비공개
				activity.startActivity(launchIntent);
			} else {
				final String pkgName = function;
				PopupUtil.showDialog(activity, pkgName + activity.getString(R.string.error_app_not_installed));
			}
		}
	}

	// 커스텀메뉴 패키지명 설치 여부 확인
	public boolean isInstalledApplication(String packageName) {
		PackageManager pm = activity.getPackageManager();
		try {
			pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			Debug.trace(e.getMessage());
			return false;
		}
		return true;
	}

	public void changeContentByProductMenu(MenuIDsMap menuID, JSONObject menuData) {
		{// clean middle menu
			initViewByMenuID(menuID);
		}

		boolean bstartServer = menuData.optBoolean("startServer");
		String menuName = bstartServer ? HMGWServiceHelper.start_menu_title : menuData.optString(MENU_NAME);

		// SEOJAEWA : FCM 에서 click event 가 있으면 PopupWebView 를 보여줌
		String event = menuData.optString(MainActivity.PUSH_MSG_EVENT);
		//MyFirebaseMessagingService.showPushDetail(m_view, menuID, event);

		if("Y".equals(menuData.optString("mainbbsYN")) && MenuIDsMap.board_all.equals(menuID)){
			MainModel.getInstance().getMiddleNavibarTitleStack().push(boardAllName);
		}else{
			MainModel.getInstance().getMiddleNavibarTitleStack().push(menuName);
		}

        resetPopupValues();
		switch (menuID) {
                // ----------------------------------------------------------------------------------------<전체메뉴 보기>
                case menu_show_all: // 18.11.12 likearts
                {
                    MainModel.getInstance().setPopupMode(true);
                    //MainModel.getInstance().setCallByMenu(true);

                    String function = menuScriptFunctionMap.get(menuID.toString());
                    //Log.d( "function", function );
                    m_controller.loadWebviewWithDelay("file:///android_asset/www/lnb.html?frag=" + menuData.optString(FROM_HOME_LINK));
                    //m_controller.loadWebviewWithDelay(function);

                    // 상단바 보이기
                    MainModel.getInstance().setNavibarVisible(false);

                    break;
                }
                // ----------------------------------------------------------------------------------------<메일>
		case mail_sent:// 보낸 편지함
		{
			new MailListAdapter(m_view, ApiCode.mail_sendlist, 1, null, false);
			break;
		}
		case mail_received:// 받은 편지함
		{
			new MailListAdapter(m_view, ApiCode.mail_recvlist, 1, null, false);
			break;
		}
		case mail_deleted:// 지운 편지함
		{
			new MailListAdapter(m_view, ApiCode.mail_deletelist, 1, null, false);
			break;
		}
		case mail_personal:// 개인편지함
		{
			new PersonalMailBoxListAdapter(m_view, R.layout.template_mailboxlist);
			break;
		}
		case mail_temp:// 임시 보관함
		{
			new MailListAdapter(m_view, ApiCode.mail_templist, 1, null, false);
			break;
		}
		case mail_selfbox:// 내게 쓴 편지함
		{
			new MailListAdapter(m_view, ApiCode.mail_selfboxlist, 1, null, false);
			break;
		}
		// ---------------------------------------------------------------------------------------<게시판>
		case board_all:// 전체게시판
		{
			ViewModel.Log("board_all main", "tkofs_bbs_home");
			try {
				if (menuData.has(MenuListHelper.FROM_HOME_LINK)
						&& menuData.has(MenuListHelper.FROM_HOME_COUNT)
						&& menuData.get("mainbbsYN").equals("Y")) {
					if (!MainModel.getInstance().isTablet()) {
						m_view.getMiddleMenuContainer().setVisibility(View.GONE);
					}
					// 메인 boardList 바로가기
					new BoardAdapter(m_view, menuData.optString(CUSTOM_MENU_FUNCTION), ApiCode.board_folder, true,
							menuData.optString(MenuListHelper.FROM_HOME_LINK),
							menuData.optString(MenuListHelper.FROM_HOME_COUNT), false, null);

					menuData.put("mainbbsYN", "N");
				} else {
					new BoardAdapter(m_view, null, ApiCode.board_folder, true, false, null);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
				break;
		}
		case board_bookmark:// 즐겨찾기 게시판
		{
			try {
				// 메인에 즐겨찾기 게시판 추가시 tkofs
				if (menuData != null && menuData.has("folderId")
						&& menuData.has("brdType")
						&& menuData.get("mainbbsYN").equals("Y")) {

					new BoardAdapter(m_view, null, ApiCode.board_favfolder, true, false, null);
					m_controller.showBoardFolders(menuData.optString("folderId"), menuData.optString("text"), false, menuData.optString("brdType"));
					menuData.put("mainbbsYN", "N");
				} else {
					new BoardAdapter(m_view, null, ApiCode.board_favfolder, true, false, null);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		}
		case board_recent:// 최근게시물
		{
			try {
                    if (menuData != null && menuData.has(MenuListHelper.FROM_HOME_LINK)
                            && menuData.has(MenuListHelper.FROM_HOME_COUNT)
                            && menuData.get("mainbbsYN").equals("Y")) {
						if (!MainModel.getInstance().isTablet()) {
							m_view.getMiddleMenuContainer().setVisibility(View.GONE);
						}
						// 메인 boardList 바로가기
						new BoardAdapter(m_view, null, ApiCode.board_recent_memolist, false,
								menuData.optString(MenuListHelper.FROM_HOME_LINK),
								menuData.optString(MenuListHelper.FROM_HOME_COUNT), false, null);

						menuData.put("mainbbsYN", "N");
					/*} else if(!TextUtils.isEmpty(event)){	// tkofs 푸시로 들어오는 최근 게시물 보기.
						DefaultMessage.BoardClickEvent clickEvent = new Gson().fromJson(event, DefaultMessage.BoardClickEvent.class);
						String link = clickEvent.getLink();
						String commentCount = String.valueOf(clickEvent.getCommentCount());
						String departmentId = clickEvent.getDepartmentId();
						new BoardAdapter(m_view, null, ApiCode.board_recent_memolist, false, false, null);
						//m_controller.showBoardMemoDetails(link, Integer.parseInt(commentCount), departmentId);*/
                    } else {
						new BoardAdapter(m_view, null, ApiCode.board_recent_memolist, false, false, null);
                    }


			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		}
		case board_notice:// 공지사항 게시판
		{
			if (menuData != null && menuData.has(MenuListHelper.FROM_HOME_LINK) && menuData.has(MenuListHelper.FROM_HOME_COUNT)) {
				// 바로 가기 데이터가 있으면 바로가기
				if (!MainModel.getInstance().isTablet()) {
					m_view.getMiddleMenuContainer().setVisibility(View.GONE);
				}
				new BoardAdapter(m_view, HMGWServiceHelper.notice_bid, ApiCode.board_noticelist, true,
						menuData.optString(MenuListHelper.FROM_HOME_LINK),
						menuData.optString(MenuListHelper.FROM_HOME_COUNT), false, null);
			} else {
				new BoardAdapter(m_view, HMGWServiceHelper.notice_bid, ApiCode.board_noticelist, true, false, null);
			}
			break;
		}
		case board_custom:// 커스텀 게시판
        {
			try {
                    if (menuData != null && menuData.has(MenuListHelper.FROM_HOME_LINK)
                            && menuData.has(MenuListHelper.FROM_HOME_COUNT)
                            && menuData.get("mainbbsYN").equals("Y")) {
                        if (!MainModel.getInstance().isTablet()) {
                            m_view.getMiddleMenuContainer().setVisibility(View.GONE);
                        }
                        ViewModel.Log("board_custom main", "tkofs_bbs_home");
                        // 메인 boardList 바로가기
                        new BoardAdapter(m_view, menuData.optString(CUSTOM_MENU_FUNCTION), ApiCode.board_memolist, true,
                                menuData.optString(MenuListHelper.FROM_HOME_LINK),
                                menuData.optString(MenuListHelper.FROM_HOME_COUNT), false, null);
                    } else {
						ViewModel.Log("board_custom not main", "tkofs_bbs_home");
						new BoardAdapter(m_view, menuData.optString(CUSTOM_MENU_FUNCTION), ApiCode.board_memolist, true, false, null);
                    }
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		}
		// ---------------------------------------------------------------------------------------<결재>
		case approval_waiting:// 결재대기
		{
			new SignListAdapter(m_view, ApiCode.sign_waitlist, 1, null, null, null, false);
			break;
		}
		case approval_progress:// 결재진행
		{
			new SignListAdapter(m_view, ApiCode.sign_inprogess, 1, null, null, null, false);
			break;
		}
		case approval_archive_informal:// 비공식기록물대장
		{
			new SignListAdapter(m_view, ApiCode.complete_informal, 1, null, null, null, false);
			break;
		}
		case approval_reject:// 반려문서
		{
			new SignListAdapter(m_view, ApiCode.sign_reject, 1, null, null, null, false);
			break;
		}
		case approval_sending_process: // 발솔처리
		{
			new SignListAdapter(m_view, ApiCode.sending_process, 1, null, null, null, false);
			break;
		}
		
		case approval_receipt_waiting: // 접수대기
		{
			new SignListAdapter(m_view, ApiCode.sign_receipt_wait, 1, null, null, null, false);
			break;
		}
		case approval_archive:// 기록물대장(완료)
		{
			new SignListAdapter(m_view, ApiCode.sign_complete_box, 1, null, null, null, false);
			break;
		}
		case approval_personal:// 개인문서함
		{
			new SignListAdapter(m_view, ApiCode.sign_my_complete, 1, null, null, null, false); // 개인문서함
			break;
		}
		case approval_draft:// 기안한문서함
		{
			new SignListAdapter(m_view, ApiCode.sign_draft_box, 1, null, null, null, false); // 개인문서함
			break;
		}
		case gongram_waiting:
		{
			new SignListAdapter(m_view, ApiCode.sign_gongram_waiting, 1, null, null, null, false);
			break;
		}
		case gongram_complete:
		{
			new SignListAdapter(m_view, ApiCode.sign_gongram_complete, 1, null, null, null, false);
			break;
		}
		// ---------------------------------------------------------------------------------------<주소록>
		case contact_personal:// 개인 주소록
		{
			new ContactListAdapter(m_view, ApiCode.contact_user, 1, null, false);
			break;
		}
		case contact_dept:// 부서 주소록
		{
			new ContactListAdapter(m_view, ApiCode.contact_dept, 1, null, false);
			break;
		}
		case contact_group:// 주소록 그룹
		{
			new ContactGroupListAdapter(m_view, R.layout.template_contact_grouplist, ApiCode.contact_group_list, 1,
					null);
			break;
		}
		// ---------------------------------------------------------------------------------------<조직도>
		case organization_chart:// 조직도
		{
			String function = menuScriptFunctionMap.get(menuID.toString());
			m_controller.loadWebviewWithDelay(function);
			break;
		}
		// ---------------------------------------------------------------------------------------<일정>
		case schedule_schdlist: // 일정 목록
		case schedule_reservelist:// 설비예약목록
		case schedule_todolist: // 할일 목록
		case schedule_search:// 검색
		{
			ViewModel.Log(menuID.toString() + "::" + HMGWServiceHelper.schedule_type , "tkofs_mailclick");
			String function = menuScriptFunctionMap.get(menuID.toString() + HMGWServiceHelper.schedule_type);
			m_controller.loadWebviewWithDelay(function);
			break;
		}
		// ---------------------------------------------------------------------------------------<설정>
		case settings_absent:// 부재 설정
		{
			String function = menuScriptFunctionMap.get(menuID.toString() + HMGWServiceHelper.settings_type);
			m_controller.loadWebviewWithDelay(function);
			break;
		}
		case settings_personal_info:// 개인정보 설정
		case settings_password:// 암호 설정
		{
			String function = menuScriptFunctionMap.get(menuID.toString());
			m_controller.loadWebviewWithDelay(function);
			break;
		}
		case square_favorite:
			goToWorkGroupList(WorkGroupType.FAVORITE);
			break;
		case square_work_group:
			goToWorkGroupList(WorkGroupType.PROCESS);
			break;
		case square_end_group:
			goToWorkGroupList(WorkGroupType.COMPLETE);
			break;
		case square_plus_favorite:
			goToSquarePlusList(SpSquareType.FAVORITE);
			break;
		case square_plus_my_group:
			m_controller.initSpMenuButton();
			goToSquarePlusIngList(SpSquareType.ING);
			break;
		case square_plus_org_group:
			goToSquarePlusOrgList(SpSquareType.ORG,"");
			break;
		case square_plus_public_group:
			goToSquarePlusList(SpSquareType.OPEN);
			break;
		case square_plus_end_group:
			goToSquarePlusList(SpSquareType.END);
			break;
		default:
			break;
		}
		ViewModel.Log(MainModel.getInstance().getMiddleNavibarTitleStack().getTop(), "tkofs_mailnamestacktop");
		m_controller.setMiddleMenuTitle(MainModel.getInstance().getMiddleNavibarTitleStack().getTop());
		m_controller.closeMenu();
		MainModel.getInstance().setCallByMenu(false);
	}

	private void goToSquarePlusList(final SpSquareType t) {
		MainModel.getInstance().setCurrentSquarePlus(t);
		// 웹 컨테이너를 숨긴다.
		if (MainModel.getInstance().isTablet()) {
			MainFragment.mainRightContent.findViewById(R.id.webviewContainer).setVisibility(View.GONE);
			MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
		}
		// View를 만들어서 붙이는 방식으로 하는 것이 좋을 것 같다.
		m_spSquareListData = new ArrayList<SpSquareVO>();
		m_spSquareListAdapter = new SpSquareListAdapter(activity, m_spSquareListData, t);
		LinearLayout listLayout = new LinearLayout(activity);
		listLayout.setOrientation(LinearLayout.VERTICAL);
		m_lvSquareGroup = new PullToRefreshListView(activity, Mode.PULL_FROM_START);
		m_lvSquareGroup.getRefreshableView().setScrollingCacheEnabled(false);
		// PulltoRefreshListView 설정
		m_lvSquareGroup.getRefreshableView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		int[] colors = { 0xBBDDDDDD, 0xBBDDDDDD, 0xBBDDDDDD };
		m_lvSquareGroup.getRefreshableView().setDivider(null);
//		m_lvSquareGroup.getRefreshableView().setDividerHeight(1);
		m_lvSquareGroup.getRefreshableView().setScrollbarFadingEnabled(false);
		m_lvSquareGroup.getRefreshableView().setVerticalFadingEdgeEnabled(false);
		m_lvSquareGroup.getRefreshableView().setFadingEdgeLength(0);
		m_lvSquareGroup.getRefreshableView().setPadding(1, 1, 1, 1);
		m_lvSquareGroup.getRefreshableView().setClipToPadding(false);
		m_lvSquareGroup.getRefreshableView().addFooterView(new View(activity));
		m_lvSquareGroup.setAdapter(m_spSquareListAdapter);
        listLayout.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		int templateId = R.layout.template_blanklist;
		emptyView = LayoutInflater.from(activity).inflate(templateId, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.TOP;
		emptyView.setLayoutParams(params);
		((TextView) emptyView.findViewById(R.id.blankListMessage)).setGravity(Gravity.TOP);
		m_lvSquareGroup.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				Debug.trace("refresh!");
				HashMap<String, String> parameter = new HashMap<String, String>();
				parameter.put("squareType", t.getType());
				SpSquareListCallBack callback = new SpSquareListCallBack(activity, SpSquareVO.class) {
					@Override
					public void callback(String url, JSONObject json, AjaxStatus status) {
						super.callback(url, json, status);
						if (status.getCode() != 200) {
							m_lvSquareGroup.onRefreshComplete();
						}

						if (this.dataList == null || this.dataList.size() == 0) {
							m_lvSquareGroup.setEmptyView(emptyView);
						}

						m_spSquareListData.clear();
						m_spSquareListData.addAll(this.dataList);
						String msgStr = String.format(activity.getString(R.string.ptr_last_updated), lastUpdatedDateFormat.format(new Date()));
						MainFragment.mListFooter.setText(msgStr);

						if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
							m_spSquareListData.get(0).setSelected(true);
							MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(0));
						}

						m_lvSquareGroup.post(new Runnable() {
							@Override
							public void run() {
								m_spSquareListAdapter.notifyDataSetChanged();
								m_lvSquareGroup.onRefreshComplete();
							}
						});
					}
				};
				new ApiLoaderEx<>(new SpGetSquareMenuList(activity), activity, callback, parameter).execute();
			}
		});

		m_lvSquareGroup.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				position -= m_lvSquareGroup.getRefreshableView().getHeaderViewsCount();
				Debug.trace(m_spSquareListData.get(position).getTitle(),
						m_spSquareListData.get(position).getSquareId());
				m_spSquareListAdapter.getItem(position).setNewCount(0);
				MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(position));
				// 선택된거 모두 초기화
				for (SpSquareVO squareVO : m_spSquareListData) {
					squareVO.setSelected(false);
				}
				m_spSquareListData.get(position).setSelected(true);
				m_spSquareListAdapter.notifyDataSetChanged();
			}
		});

		m_lvSquareGroup.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
			@Override
			public void onLastItemVisible() {
				Debug.trace("onLastItemVisible");
			}
		});

		listLayout.addView(m_lvSquareGroup, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		listLayout.addView(emptyView);
//		m_lvSquareGroup.setEmptyView(emptyView);
		m_view.getMiddleMenuFlipper().addView(listLayout);
		m_controller.hideWebview(false);
		m_controller.closeMenu();
		m_controller.showMiddleMenuNextPage();

		loadSpSquareGroupList(t);

		MainFragment.mListFooter.setRightButtonImage(R.drawable.btn_btm_add);
		MainFragment.mListFooter.setFooterToolbarListener(new IFooterToolBarListener() {
			@Override
			public void onFooterToolBarClick(ButtonPosition bp, View v) {
				switch (bp) {
				case CENTER:
					break;
				case LEFT:
					HashMap<String, String> parameter = new HashMap<String, String>();
					parameter.put("squareType", t.getType());
					SpSquareListCallBack callback = new SpSquareListCallBack(activity, SpSquareVO.class) {
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);
							if (status.getCode() != 200) {
								m_lvSquareGroup.onRefreshComplete();
							}

							if (this.dataList == null || this.dataList.size() == 0) {
								m_lvSquareGroup.setEmptyView(emptyView);
							}

							m_spSquareListData.clear();
							m_spSquareListData.addAll(this.dataList);
							String msgStr = String.format(activity.getString(R.string.ptr_last_updated),
									lastUpdatedDateFormat.format(new Date()));
							MainFragment.mListFooter.setText(msgStr);
							if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
								m_spSquareListData.get(0).setSelected(true);
								MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(0));
							}
							m_lvSquareGroup.post(new Runnable() {
								@Override
								public void run() {
									m_spSquareListAdapter.notifyDataSetChanged();
								}
							});
						}
					};
					new ApiLoaderEx<>(new SpGetSquareMenuList(activity), activity, callback, parameter).execute();
					break;
				case RIGHT:
					MainModel.getInstance().showSubActivity(activity, SubActivityType.SP_CREATE, null);
					break;
				}
			}
		});
	}

	public void goToSquarePlusOrgList(final SpSquareType t,final String deptId) {
		MainModel.getInstance().setCurrentSquarePlus(t);
		// 웹 컨테이너를 숨긴다.
		if (MainModel.getInstance().isTablet()) {
			MainFragment.mainRightContent.findViewById(R.id.webviewContainer).setVisibility(View.GONE);
			MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
		}
		// View를 만들어서 붙이는 방식으로 하는 것이 좋을 것 같다.
		
		LinearLayout listLayout = new LinearLayout(activity);
		listLayout.setOrientation(LinearLayout.VERTICAL);
		
		final ArrayList<OrgTreeItemVO> spOrgGroupListData = new ArrayList<OrgTreeItemVO>();
		final SpOrgGroupAdapter spOrgGroupAdapter = new SpOrgGroupAdapter(m_view, spOrgGroupListData);
		
		final PullToRefreshListView m_lvSquareGroup = new PullToRefreshListView(activity, Mode.PULL_FROM_START);
		m_lvSquareGroup.getRefreshableView().setScrollingCacheEnabled(false);
		// PulltoRefreshListView 설정
		m_lvSquareGroup.getRefreshableView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		m_lvSquareGroup.getRefreshableView().setDivider(null);
		m_lvSquareGroup.getRefreshableView().setDividerHeight(1);
		m_lvSquareGroup.getRefreshableView().setScrollbarFadingEnabled(false);
		m_lvSquareGroup.getRefreshableView().setVerticalFadingEdgeEnabled(false);
		m_lvSquareGroup.getRefreshableView().setFadingEdgeLength(0);
		m_lvSquareGroup.getRefreshableView().setPadding(1, 1, 1, 1);
		m_lvSquareGroup.getRefreshableView().setClipToPadding(false);
		m_lvSquareGroup.getRefreshableView().addFooterView(new View(activity));
		m_lvSquareGroup.setAdapter(spOrgGroupAdapter);
		listLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		int templateId = R.layout.template_blanklist;
		emptyView = LayoutInflater.from(activity).inflate(templateId, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.TOP;
		emptyView.setLayoutParams(params);
		((TextView) emptyView.findViewById(R.id.blankListMessage)).setGravity(Gravity.TOP);
		m_lvSquareGroup.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				Debug.trace("refresh!");
				
				loadSpOrgGroupList(m_lvSquareGroup, spOrgGroupAdapter ,spOrgGroupListData, deptId);
				
			}
		});

//		m_lvSquareGroup.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				position -= m_lvSquareGroup.getRefreshableView().getHeaderViewsCount();
//				Debug.trace("");
//				Debug.trace(m_spSquareListData.get(position).getTitle(),
//						m_spSquareListData.get(position).getSquareId());
//				m_spSquareListAdapter.getItem(position).setNewCount(0);
//				MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(position));
//				// 선택된거 모두 초기화
//				for (SpSquareVO squareVO : m_spSquareListData) {
//					squareVO.setSelected(false);
//				}
//				m_spSquareListData.get(position).setSelected(true);
//				m_spSquareListAdapter.notifyDataSetChanged();
//			}
//		});

		m_lvSquareGroup.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
			@Override
			public void onLastItemVisible() {
				Debug.trace("onLastItemVisible");
			}
		});

		listLayout.addView(m_lvSquareGroup, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		listLayout.addView(emptyView);
		m_view.getMiddleMenuFlipper().addView(listLayout);
		m_controller.hideWebview(false);
		m_controller.closeMenu();
		m_controller.showSquareMiddleMenuNextPage();

		loadSpOrgGroupList(m_lvSquareGroup, spOrgGroupAdapter ,spOrgGroupListData, deptId);

		MainFragment.mListFooter.hideRightButton();
		MainFragment.mListFooter.setFooterToolbarListener(new IFooterToolBarListener() {
			@Override
			public void onFooterToolBarClick(ButtonPosition bp, View v) {
				switch (bp) {
				case CENTER:
					break;
				case LEFT:
					loadSpOrgGroupList(m_lvSquareGroup, spOrgGroupAdapter ,spOrgGroupListData, deptId);
					break;
				case RIGHT:
					break;
				}
			}
		});
	}
	// 진행중인 그룹은 항상 그룹이 최소 1개가 존재한다. (서버 무결성)
	private void goToSquarePlusIngList(final SpSquareType t) {
		MainModel.getInstance().setCurrentSquarePlus(t);
		// 웹 컨테이너를 숨긴다.
		if (MainModel.getInstance().isTablet()) {
			MainFragment.mainRightContent.findViewById(R.id.webviewContainer).setVisibility(View.GONE);
			MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
		}
		// View를 만들어서 붙이는 방식으로 하는 것이 좋을 것 같다.
		m_spSquareListData = new ArrayList<SpSquareVO>();
		m_spFolderListData = new ArrayList<SpFolderVO>();
		m_spFolderListAdapter = new SpFolderListAdapter(activity, m_spFolderListData);
		LinearLayout listLayout = new LinearLayout(activity);
		listLayout.setOrientation(LinearLayout.VERTICAL);
		m_lvSquareExpandableGroup = new PullToRefreshExpandableListView(activity, Mode.PULL_FROM_START);
		m_lvSquareExpandableGroup.getRefreshableView().setScrollingCacheEnabled(false);
		// PulltoRefreshListView 설정
		m_lvSquareExpandableGroup.getRefreshableView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//		int[] colors = { 0xFFe0e0e0, 0xFFe0e0e0, 0xFFe0e0e0 };
		m_lvSquareExpandableGroup.getRefreshableView().setDivider(null);
//		m_lvSquareExpandableGroup.getRefreshableView().setDividerHeight(1);
		m_lvSquareExpandableGroup.getRefreshableView().setScrollbarFadingEnabled(false);
		m_lvSquareExpandableGroup.getRefreshableView().setVerticalFadingEdgeEnabled(false);
		m_lvSquareExpandableGroup.getRefreshableView().setFadingEdgeLength(0);
		m_lvSquareExpandableGroup.getRefreshableView().setPadding(1, 1, 1, 1);
		m_lvSquareExpandableGroup.getRefreshableView().setClipToPadding(false);
		m_lvSquareExpandableGroup.getRefreshableView().addFooterView(new View(activity));
//		m_lvSquareExpandableGroup.setAdapter((ListAdapter) m_spFolderListAdapter);
		m_lvSquareExpandableGroup.getRefreshableView().setAdapter(m_spFolderListAdapter);
		m_lvSquareExpandableGroup.getRefreshableView().setGroupIndicator(null);
//		ImageView imgIndicator = (ImageView) m_lvSquareExpandableGroup.getRefreshableView().getSelectedView().setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				
//			}
//		});

        listLayout.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		int templateId = R.layout.template_blanklist;
		emptyView = LayoutInflater.from(activity).inflate(templateId, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.TOP;
		emptyView.setLayoutParams(params);
		((TextView) emptyView.findViewById(R.id.blankListMessage)).setGravity(Gravity.TOP);
		m_lvSquareExpandableGroup.setOnRefreshListener(new OnRefreshListener<ExpandableListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
				Debug.trace("refresh!");
				HashMap<String, String> parameter = new HashMap<String, String>();
				parameter.put("squareType", t.getType());
				SpSquareListCallBack spSquareListCallBack = new SpSquareListCallBack(activity, SpSquareVO.class) {
					@Override
					public void callback(String url, JSONObject json, AjaxStatus status) {
						super.callback(url, json, status);

						if (status.getCode() != 200) {
							m_lvSquareExpandableGroup.onRefreshComplete();
							return;
						} else {
							// 스퀘어목록을 폴더용으로 변환시킨다.
							final SpFolderVO folderVO = convertSquareToFolder(this.dataList);

							SpFolderListCallBack spFolderListCallBack = new SpFolderListCallBack(activity, SpFolderVO.class){
								@Override
								public void callback(String url, JSONObject json, AjaxStatus status) {
									super.callback(url, json, status);

									if (status.getCode() != 200) {
										m_lvSquareExpandableGroup.onRefreshComplete();
									}

									m_spFolderListData.clear();
									m_spFolderListAdapter.notifyDataSetChanged();
									m_spFolderListData.addAll(this.dataList);
									m_spFolderListData.add(folderVO);

									String msgStr = String.format(activity.getString(R.string.ptr_last_updated), lastUpdatedDateFormat.format(new Date()));
									MainFragment.mListFooter.setText(msgStr);

									if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
										try {
											int index = 0;
											while (m_spFolderListData.get(index).getFolderSquareVOList().size() == 0) {
												index++;
											}

											m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO().setSelected(true);
											MainModel.getInstance().goToGroup(activity, m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO());
										} catch (NullPointerException e) {
											Debug.trace(e);
										} catch (IndexOutOfBoundsException e) {
											Debug.trace(e);
										} catch (Exception e) {
											Debug.trace(e);
										}

//										MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(0));
									}
			
									m_lvSquareExpandableGroup.post(new Runnable() {
										@Override
										public void run() {
											m_spFolderListAdapter.notifyDataSetChanged();

											for (int i=0; i<m_spFolderListData.size(); i++) {
												m_lvSquareExpandableGroup.getRefreshableView().expandGroup(i);
											}

											m_lvSquareExpandableGroup.onRefreshComplete();
										}
									});
								}
							};

							{ // API명
								HashMap<String,String> params = new HashMap<>();
								new ApiLoaderEx<>(new SpInitMySquareMenu(activity), activity, spFolderListCallBack, params).execute();
							}
						}
					}

					private SpFolderVO convertSquareToFolder(List<SpSquareVO> squareList) {
						SpFolderVO folderVO = new SpFolderVO();
						folderVO.setFolderName(activity.getString(R.string.label_squareplus_nofolder_square));
						List<SpFolderSquareVO> folderSquareList = new ArrayList<SpFolderSquareVO>();

						for (SpSquareVO squareVO : squareList) {
							if (!squareVO.isFolder()) {
								SpFolderSquareVO folderSquareVO = new SpFolderSquareVO();
								folderSquareVO.setSquareVO(squareVO);
								folderSquareList.add(folderSquareVO);
							}
						}

						folderVO.setFolderSquareVOList(folderSquareList);

						return folderVO;
					}
				};
				new ApiLoaderEx<>(new SpGetSquareMenuList(activity), activity, spSquareListCallBack, parameter).execute();
			}
		});

		m_lvSquareExpandableGroup.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
			@Override
			public void onLastItemVisible() {
				Debug.trace("onLastItemVisible");
			}
		});

		

		// 그룹이 닫힐 경우
//		m_lvSquareExpandableGroup.getRefreshableView().setOnChildClickListener(new OnChildClickListener() {
//			@Override
//			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//				MainModel.getInstance().goToGroup(activity, m_spFolderListData.get(groupPosition).getFolderSquareVOList().get(childPosition).getSquareVO());
//				return false;
//			}
//		});

		listLayout.addView(m_lvSquareExpandableGroup, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		listLayout.addView(emptyView);
//		m_lvSquareExpandableGroup.setEmptyView(emptyView);
		m_view.getMiddleMenuFlipper().addView(listLayout);
		m_controller.hideWebview(false);
		m_controller.closeMenu();
		m_controller.showMiddleMenuNextPage();

		loadSpSquareIngList(t);

		MainFragment.mListFooter.setRightButtonImage(R.drawable.btn_btm_add);
		MainFragment.mListFooter.setFooterToolbarListener(new IFooterToolBarListener() {
			@Override
			public void onFooterToolBarClick(ButtonPosition bp, View v) {
				switch (bp) {
				case CENTER:
					break;
				case LEFT:
					Debug.trace("refresh!");
					HashMap<String, String> parameter = new HashMap<String, String>();
					parameter.put("squareType", t.getType());
					SpSquareListCallBack spSquareListCallBack = new SpSquareListCallBack(activity, SpSquareVO.class) {
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);

							if (status.getCode() != 200) {
								m_lvSquareExpandableGroup.onRefreshComplete();
								return;
							} else {
								// 스퀘어목록을 폴더용으로 변환시킨다.
								final SpFolderVO folderVO = convertSquareToFolder(this.dataList);

								SpFolderListCallBack spFolderListCallBack = new SpFolderListCallBack(activity, SpFolderVO.class){
									@Override
									public void callback(String url, JSONObject json, AjaxStatus status) {
										super.callback(url, json, status);

										if (status.getCode() != 200) {
											m_lvSquareExpandableGroup.onRefreshComplete();
										}

										m_spFolderListData.clear();
										m_spFolderListAdapter.notifyDataSetChanged();
										m_spFolderListData.addAll(this.dataList);
										m_spFolderListData.add(folderVO);

										String msgStr = String.format(activity.getString(R.string.ptr_last_updated), lastUpdatedDateFormat.format(new Date()));
										MainFragment.mListFooter.setText(msgStr);

										if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
											try {
												int index = 0;
												while (m_spFolderListData.get(index).getFolderSquareVOList().size() == 0) {
													index++;
												}

												m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO().setSelected(true);
												MainModel.getInstance().goToGroup(activity, m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO());
											} catch (NullPointerException e) {
												Debug.trace(e);
											} catch (IndexOutOfBoundsException e) {
												Debug.trace(e);
											} catch (Exception e) {
												Debug.trace(e);
											}

//											MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(0));
										}

										m_lvSquareExpandableGroup.post(new Runnable() {
											@Override
											public void run() {
												m_spFolderListAdapter.notifyDataSetChanged();

												for (int i=0; i<m_spFolderListData.size(); i++) {
													m_lvSquareExpandableGroup.getRefreshableView().expandGroup(i);
												}

												m_lvSquareExpandableGroup.onRefreshComplete();
											}
										});
									}
								};

								{ // API명
									HashMap<String,String> params = new HashMap<>();
									new ApiLoaderEx<>(new SpInitMySquareMenu(activity), activity, spFolderListCallBack, params).execute();
								}
							}
						}

						private SpFolderVO convertSquareToFolder(List<SpSquareVO> squareList) {
							SpFolderVO folderVO = new SpFolderVO();
							folderVO.setFolderName(activity.getString(R.string.label_squareplus_nofolder_square));
							List<SpFolderSquareVO> folderSquareList = new ArrayList<SpFolderSquareVO>();

							for (SpSquareVO squareVO : squareList) {
								if (!squareVO.isFolder()) {
									SpFolderSquareVO folderSquareVO = new SpFolderSquareVO();
									folderSquareVO.setSquareVO(squareVO);
									folderSquareList.add(folderSquareVO);
								}
							}

							folderVO.setFolderSquareVOList(folderSquareList);

							return folderVO;
						}
					};
					new ApiLoaderEx<>(new SpGetSquareMenuList(activity), activity, spSquareListCallBack, parameter).execute();
					break;
				case RIGHT:
					MainModel.getInstance().showSubActivity(activity, SubActivityType.SP_CREATE, null);
					break;
				}
			}
		});
	}

	private void goToWorkGroupList(final WorkGroupType t) {
		MainModel.getInstance().setCurrentSquare(t);
		// 웹 컨테이너를 숨긴다.
		if (MainModel.getInstance().isTablet()) {
			MainFragment.mainRightContent.findViewById(R.id.webviewContainer).setVisibility(View.GONE);
			MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
		}
		// View를 만들어서 붙이는 방식으로 하는 것이 좋을 것 같다.
		m_myWorkGroupListData = new ArrayList<MyWorkGroupMenuListItemVO>();
		m_myWorkGroupListAdapter = new MyWorkGroupListAdapter(m_myWorkGroupListData, t);
		LinearLayout listLayout = new LinearLayout(activity);
		listLayout.setOrientation(LinearLayout.VERTICAL);
		m_lvSquareGroup = new PullToRefreshListView(activity, Mode.PULL_FROM_START);
		m_lvSquareGroup.getRefreshableView().setScrollingCacheEnabled(false);
		// PulltoRefreshListView 설정
		m_lvSquareGroup.getRefreshableView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		int[] colors = { 0xBBDDDDDD, 0xBBDDDDDD, 0xBBDDDDDD };
		m_lvSquareGroup.getRefreshableView().setDivider(new GradientDrawable(Orientation.RIGHT_LEFT, colors));
		m_lvSquareGroup.getRefreshableView().setDividerHeight(1);
		m_lvSquareGroup.getRefreshableView().setScrollbarFadingEnabled(false);
		m_lvSquareGroup.getRefreshableView().setVerticalFadingEdgeEnabled(false);
		m_lvSquareGroup.getRefreshableView().setFadingEdgeLength(0);
		m_lvSquareGroup.getRefreshableView().setPadding(1, 1, 1, 1);
		m_lvSquareGroup.getRefreshableView().setClipToPadding(false);
		m_lvSquareGroup.getRefreshableView().addFooterView(new View(activity));
		m_lvSquareGroup.setAdapter(m_myWorkGroupListAdapter);
        listLayout.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		int templeteId = R.layout.template_blanklist;
		View emptyView = LayoutInflater.from(activity).inflate(templeteId, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.TOP;
		emptyView.setLayoutParams(params);
		((TextView) emptyView.findViewById(R.id.blankListMessage)).setGravity(Gravity.TOP);
		m_lvSquareGroup.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh(PullToRefreshBase refreshView) {
				Debug.trace("refresh!");
				new ApiLoader(new MyWorkGroupMenuList(), activity, new MyWorkGroupMenuListResult() {
					@Override
					public void onResponse(String strRet) {
						super.onResponse(strRet);
						new ApiLoader(new MyWorkGroupMenuList(), activity, new MyWorkGroupMenuListResult() {
							@Override
							public void onResponse(String strRet) {
								super.onResponse(strRet);
								m_myWorkGroupListData.clear();
								m_myWorkGroupListData.addAll(dataList);
								m_myWorkGroupListAdapter.notifyDataSetChanged();
								m_lvSquareGroup.onRefreshComplete();
								String msgStr = String.format(activity.getString(R.string.ptr_last_updated),
										lastUpdatedDateFormat.format(new Date()));
								m_view.mListFooter.setText(msgStr);
							}

							@Override
							public void onFailure(String strErr) {
								super.onFailure(strErr);
								m_lvSquareGroup.onRefreshComplete();
							}
						}, t.getType()).execute();
					}
				}, t.getType()).execute();
			}
		});
		m_lvSquareGroup.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				position -= m_lvSquareGroup.getRefreshableView().getHeaderViewsCount();
				Debug.trace(m_myWorkGroupListData.get(position).title, m_myWorkGroupListData.get(position).squareId);
				m_myWorkGroupListAdapter.getItem(position).newCount = 0;
				MainModel.getInstance().goToGroup(activity, m_myWorkGroupListData.get(position));
				m_myWorkGroupListAdapter.notifyDataSetChanged();
			}
		});
		m_lvSquareGroup.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {
			@Override
			public void onLastItemVisible() {
				Debug.trace("onLastItemVisible");
			}
		});
		listLayout.addView(m_lvSquareGroup, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		listLayout.addView(emptyView);
		m_lvSquareGroup.setEmptyView(emptyView);
		m_view.getMiddleMenuFlipper().addView(listLayout);
		m_controller.hideWebview(false);
		m_controller.closeMenu();
		m_controller.showMiddleMenuNextPage();

		loadSquareGroupList(t);

		m_view.mListFooter.setRightButtonImage(R.drawable.btn_btm_add);
		m_view.mListFooter.setFooterToolbarListener(new IFooterToolBarListener() {
			@Override
			public void onFooterToolBarClick(ButtonPosition bp, View v) {
				switch (bp) {
				case CENTER:
					break;
				case LEFT:
					new ApiLoader(new MyWorkGroupMenuList(), activity, new MyWorkGroupMenuListResult() {
						@Override
						public void onResponse(String strRet) {
							super.onResponse(strRet);
							m_myWorkGroupListData.clear();
							m_myWorkGroupListData.addAll(dataList);
							Debug.trace("#10");
							m_myWorkGroupListAdapter.notifyDataSetChanged();
							String msgStr = String.format(activity.getString(R.string.ptr_last_updated),
									lastUpdatedDateFormat.format(new Date()));
							m_view.mListFooter.setText(msgStr);
						}
					}, t.getType()).execute();
					break;
				case RIGHT:
					MainModel.getInstance().showSubActivity(activity, SubActivityType.MAKE_NEW_WORK_GROUP, null);
					break;
				}
			}
		});
	}

	public void loadSquareGroupList(final WorkGroupType t) {
		new ApiLoader(new MyWorkGroupMenuList(), activity, new MyWorkGroupMenuListResult() {
			@Override
			public void onResponse(String strRet) {
				super.onResponse(strRet);
				m_myWorkGroupListData.clear();
				m_myWorkGroupListData.addAll(dataList);
				String msgStr = String.format(activity.getString(R.string.ptr_last_updated),
						lastUpdatedDateFormat.format(new Date()));
				m_view.mListFooter.setText(msgStr);
				if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
					MainModel.getInstance().goToGroup(activity, m_myWorkGroupListData.get(0));
				}
				m_lvSquareGroup.post(new Runnable() {
					@Override
					public void run() {
						m_myWorkGroupListAdapter.notifyDataSetChanged();
					}
				});
			}

			@Override
			public void onFailure(String strErr) {
				super.onFailure(strErr);
				m_lvSquareGroup.onRefreshComplete();
			}
		}, t.getType()).execute();
	}
	
	public void loadSpOrgGroupList(final PullToRefreshListView lvSquareGroup,final SpOrgGroupAdapter adapter,final ArrayList<OrgTreeItemVO> spOrgGroupListData,String deptId) {

		OrgTreeCallBack callback = new OrgTreeCallBack() {
			@Override
			public void onResponse(String strRet) {
				// TODO Auto-generated method stub
				super.onResponse(strRet);
				
				
				if (data != null) {
					lvSquareGroup.onRefreshComplete();
					spOrgGroupListData.clear();
					spOrgGroupListData.addAll(data);
					//api 쏘고 콜백
				}
				else
					lvSquareGroup.setEmptyView(emptyView);
				
				lvSquareGroup.post(new Runnable() {
					@Override
					public void run() {
						adapter.notifyDataSetChanged();
						lvSquareGroup.onRefreshComplete();
					}
				});
			}
		};
		
		if (TextUtils.isEmpty(deptId))
			deptId = "000000000";

		new ApiLoader(new OrgTree(), activity, callback, "org", "dept", "list", "onelevel", deptId, HMGWServiceHelper.key).execute();
	}

	public void loadSpSquareGroupList(final SpSquareType t) {
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("squareType", t.getType());
		SpSquareListCallBack callback = new SpSquareListCallBack(activity, SpSquareVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);
				if (status.getCode() != 200) {
					m_lvSquareGroup.onRefreshComplete();
				}

				if (this.dataList == null || this.dataList.size() == 0) {
					m_lvSquareGroup.setEmptyView(emptyView);
				}

				m_spSquareListData.clear();
				m_spSquareListData.addAll(this.dataList);
				String msgStr = String.format(activity.getString(R.string.ptr_last_updated),
						lastUpdatedDateFormat.format(new Date()));
				MainFragment.mListFooter.setText(msgStr);
				if (MainModel.getInstance().isTablet() && dataList.size() > 0) {
					m_spSquareListData.get(0).setSelected(true);
					MainModel.getInstance().goToGroup(activity, m_spSquareListData.get(0));
				}
				m_lvSquareGroup.post(new Runnable() {
					@Override
					public void run() {
						m_spSquareListAdapter.notifyDataSetChanged();
					}
				});
			}
		};
		new ApiLoaderEx<>(new SpGetSquareMenuList(activity), activity, callback, parameter).execute();
	}

	/**
	 * 스퀘어 플러스 진행중인 그룹은 진행중인그룹을 먼져 가져온 후 해당 데이터를 folderList화 시켜서 folder templete에다가 모두 넣는다.
	 * @param t
	 */
	public void loadSpSquareIngList(final SpSquareType t) {
		loadSpSquareIngList(t, null);
	}

	public void loadSpSquareIngList(final SpSquareType t, final SpSquareVO squareVO) {
		SpSquareListCallBack spSquareListCallBack = new SpSquareListCallBack(activity, SpSquareVO.class) {
			@Override
			public void callback(String url, JSONObject json, AjaxStatus status) {
				super.callback(url, json, status);

				if (status.getCode() != 200) {
					m_lvSquareExpandableGroup.onRefreshComplete();
					return;
				} else {
					// 스퀘어목록을 폴더용으로 변환시킨다.
					final SpFolderVO folderVO = convertSquareToFolder(this.dataList);

					SpFolderListCallBack spFolderListCallBack = new SpFolderListCallBack(activity, SpFolderVO.class){
						@Override
						public void callback(String url, JSONObject json, AjaxStatus status) {
							super.callback(url, json, status);

							if (status.getCode() != 200) {
								m_lvSquareExpandableGroup.onRefreshComplete();
							}

							m_spFolderListData.clear();
							m_spFolderListAdapter.notifyDataSetChanged();
							m_spFolderListData.addAll(this.dataList);
							m_spFolderListData.add(folderVO);
							String msgStr = String.format(activity.getString(R.string.ptr_last_updated),
									lastUpdatedDateFormat.format(new Date()));
							MainFragment.mListFooter.setText(msgStr);

							// squareVO를 받을 경우 해당 그룹으로 이동시켜준다.
							if (MainModel.getInstance().isTablet() && m_spFolderListData.size() > 0) {
								int index = 0;

								if (squareVO == null) {
									// 폴더에 있는 그룹을 열때 첫번째 폴더에 그룹이 아무것도 없는것을 고려
									try {
										while (m_spFolderListData.get(index).getFolderSquareVOList().size() == 0) {
											index++;
										}

										m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO().setSelected(true);
										MainModel.getInstance().goToGroup(activity, m_spFolderListData.get(index).getFolderSquareVOList().get(0).getSquareVO());
									} catch (NullPointerException e) {
										Debug.trace(e);
									} catch (IndexOutOfBoundsException e) {
										Debug.trace(e);
									} catch (Exception e) {
										Debug.trace(e);
									}
								} else {
									MainModel.getInstance().goToGroup(activity, squareVO);
								}
							}

							m_lvSquareExpandableGroup.post(new Runnable() {
								@Override
								public void run() {
									m_spFolderListAdapter.notifyDataSetChanged();

									for (int i=0; i<m_spFolderListData.size(); i++) {
										m_lvSquareExpandableGroup.getRefreshableView().expandGroup(i);
									}
								}
							});
						}
					};

					{ // API명
						HashMap<String,String> params = new HashMap<>();
						new ApiLoaderEx<>(new SpInitMySquareMenu(activity), activity, spFolderListCallBack, params).execute();
					}
				}
			}
			
			private SpFolderVO convertSquareToFolder(List<SpSquareVO> squareList) {
				SpFolderVO folderVO = new SpFolderVO();
				folderVO.setFolderName(activity.getString(R.string.label_squareplus_nofolder_square));
				List<SpFolderSquareVO> folderSquareList = new ArrayList<SpFolderSquareVO>();

				for (SpSquareVO squareVO : squareList) {
					if (!squareVO.isFolder()) {
						SpFolderSquareVO folderSquareVO = new SpFolderSquareVO();
						folderSquareVO.setSquareVO(squareVO);
						folderSquareList.add(folderSquareVO);
					}
				}

				folderVO.setFolderSquareVOList(folderSquareList);

				return folderVO;
			}
		};

		spSquareListCallBack.progress(PopupUtil.getProgressDialog(activity));

		{
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("squareType", t.getType());
			new ApiLoaderEx<>(new SpGetSquareMenuList(activity), activity, spSquareListCallBack, params).execute();
		}

	}

	public void initViewByMenuID(MenuIDsMap menuID) {
		if (MainModel.getInstance().isTablet()) {
			Debug.trace("getBackStackEntryCount = " + m_view.getFragmentManager().getBackStackEntryCount());
			if (m_view.getFragmentManager().getBackStackEntryCount() > 0) {
				m_view.getFragmentManager().popBackStack();
			}

			View layout = MainFragment.mainRightContent.findViewById(R.id.ID_LAY_L_FRAGMENT_LAYOUT);
			layout.setVisibility(View.GONE);
			MainFragment.mainRightContent.findViewById(R.id.webviewContainer).setVisibility(View.VISIBLE);
		}

		MainModel.getInstance().setCallByMenu(true);
		// 웹툴바 숨김
		m_controller.hideWebviewToolbar();
		// 웹뷰 네비바 타이틀 초기화
		m_controller.setWebviewNavibarTitle("");
		// 웹뷰 네비바 버튼 초기화
		m_view.initWebviewNavibar();
		// 웹뷰에 로딩되어있는 화면 숨김
		if (MainModel.getInstance().isWebViewLoaded()) {
			MainModel.getInstance().getWebViewFragment()
					.loadUrl("javascript:$.mobile.changePage('#_TEMPLATE_empty_page');");
		}
		// 스퀘어 뷰 초기화
		m_controller.clearSquareContentsFromTablet();
		// 결재 필터 숨김
		(m_view.getMiddleMenuContainer().findViewById(R.id.signFilterContainer)).setVisibility(View.GONE);
		// 중간 리스트 초기화
		m_view.getMiddleMenuFlipper().removeAllViews();
		// 스퀘어 리스트 초기화
		if (m_lvSquareGroup != null) {
			m_lvSquareGroup = null;
		}
		// 네비바 타이틀 스텍 초기화
		MainModel.getInstance().getMiddleNavibarTitleStack().clean();
		// 메일 검색 숨김
		m_view.mMailSearchMenu.setVisibility(View.GONE);
		// 메일검색 초기화
		m_controller.cancelMailSearch(null);
		// 게시물 검색 숨김
		m_view.mBoardSearchMenu.setVisibility(View.GONE);
		// 게시물 검색 초기화
		m_controller.cancelBoardSearch(null);
		// 주소록 검색 숨김
		m_view.mContactSearchMenu.setVisibility(View.GONE);
		// 결재 검색 숨김
		m_view.mSignSearchMenu.setVisibility(View.GONE);
		// 결재 검색 초기화
		m_controller.cancelSignSearch(null);
		// 주소록 검색 초기화
		m_controller.cancelContactSearch(null);
		// 이전 버튼 숨김
		(m_view.getMiddleMenuContainer().findViewById(R.id.middleBackButton)).setVisibility(View.GONE);
		// 메일 편집버튼 숨김
		m_controller.hideEditMailButton();
		// 스퀘어플러스 메뉴 숨김
		m_controller.hideSpMenuButton();
		// 글쓰기 버튼 숨김
		m_controller.hideWriteButton();
		// 발송처리 부서 선택 숨김
		m_controller.hideSendingDeptButton();		
		// Enable search field
		(m_view.mMailSearchMenu.findViewById(R.id.inputKeywordMail)).setEnabled(true);
		m_view.customWebViewContainer.setVisibility(View.GONE);

		// 메일삭제 메뉴를 숨김
		m_view.mDeleteMailFooter.setVisibility(View.GONE);
		// 주소록 저장 메뉴도 숨김
		m_view.mSaveContactFooter.setVisibility(View.GONE);
		// 기본 하단메류를 보임
		m_view.mListFooter.setVisibility(View.VISIBLE);

		switch (menuID) {
		// Web으로 바로가는 메뉴들
            case custom_webview_menu:
            case mail_writing:
            case organization_chart:
            case schedule_addschd:
            case schedule_addtodo:
            case schedule_reservelist:
            case schedule_schdlist:
            case schedule_todolist:
            case schedule_search:
            case settings_absent:
            case settings_password:
            case menu_show_all:
            case settings_personal_info: {
                if (MainModel.getInstance().isTablet()) {
                    m_view.getMiddleMenuContainer().setVisibility(View.GONE); // 테블릿에서
                    // 위의
                    // 메뉴들은
                    // 중간
                    // 리스트뷰를
                    // 숨긴다.
                    MainModel.getInstance().getWebViewFragment().getBtnTogleMiddleList().setVisibility(View.GONE);
                } else {
                }
                break;
            }
            default: {
                if (MainModel.getInstance().isTablet()
                        && MainModel.getInstance().getWebViewFragment().getBtnTogleMiddleList() != null) {
                    MainModel.getInstance().getWebViewFragment().getBtnTogleMiddleList().setSelected(false);
                    MainModel.getInstance().getWebViewFragment().getBtnTogleMiddleList().setVisibility(View.VISIBLE);
                }
                m_view.getMiddleMenuContainer().setVisibility(View.VISIBLE);
            }
        }
    }

	public void refreshContentsByPressedMenu(View menuView) {
		onMenuClick(menuView);
	}

	public void startMenuByID(String menuIDString, String menuName, String url, String function) {
		if (START_MENU_CUSTOM_ID.equalsIgnoreCase(menuIDString)) {
			startCustomMenu(menuName, VIEW_TYPE_CUSTOM_WEBVIEW, url);
		} else {
			MenuIDsMap menuID;
			menuID = MenuIDsMap.valueOf(menuIDString);
			switch (menuID) {
			case mail_writing:
			case schedule_addschd:
			case schedule_addtodo:
			case settings_logout:
				menuID = MenuIDsMap.mail_received;
				break;
			default:
				break;
			}
//			menuID = MenuIDsMap.mail_received;

			View view = null;
			if (menuID.equals(MenuIDsMap.board_custom)) {
				for (String key : menuMap.keySet()) {
					Debug.trace(key + ", " + function);
					if (key.startsWith(menuID.toString()) && key.endsWith(function)) {
						view = menuMap.get(key);
						break;
					}
				}
			} else {
				Debug.trace("menuID : " + menuID);
				view = menuMap.get(menuID.toString());
			}

			if (view == null) {
				return;
			}
			
			JSONObject menuData = (JSONObject) view.getTag();
			try {
				menuData.put("startServer", true);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Debug.trace(e.getMessage());
			}
			view.setTag(menuData);
			
			view.setSelected(true);
			onMenuClick(view);
		}
	}

	/**
	 * 메인 notice 목록에 즐겨찾기 게시판이 추가된경우
	 *
	 * @param brdType
	 * @param folderId
	 */
	public void showMenuByBbsID(String menuIDString, String folderId, String brdType, String text) {
		View view = menuMap.get(menuIDString);

		// 공지사항의 상세 뷰이므로 Back-key를 눌렀을 때 공지사항 리스트로 가게 하기 위해
		try {
			((JSONObject) view.getTag()).put("folderId", folderId);
			((JSONObject) view.getTag()).put("brdType", brdType);
			((JSONObject) view.getTag()).put("text", text);
			((JSONObject) view.getTag()).put("mainbbsYN", "Y");
		} catch (JSONException e) {
		}

		view.setSelected(true);
		onMenuClick(view);
	}
	/**
     * 좌측메뉴 서브메뉴만 보여줌에 따라 게시판경우 메뉴가 안보이는문제를 해결하기 위해
     *
     * @param menuIDString
     * @param menuName
     * @param link
     * @param function
     */
    public void showMenuByBbsID(String menuIDString, String menuName, String link, String commentcount, String function) {

        View view = null;
        if (menuIDString.equals(MenuIDsMap.board_custom.toString())) {    // 고객 용청에따라 전체 게시판 메뉴를 게시판 하위로 변경 0105
            view = menuMap.get(MenuIDsMap.board_all.toString());    // 커스텀이면 전체게시판
            //boardAllName = ((JSONObject) view.getTag()).optString(MENU_NAME);
        } else {
            view = menuMap.get(menuIDString);
        }
        if (view == null) {
            return;
        }

        // 공지사항의 상세 뷰이므로 Back-key를 눌렀을 때 공지사항 리스트로 가게 하기 위해
        try {
			boardAllName = menuName;
            //((JSONObject) view.getTag()).put(MENU_NAME, menuName);
            ((JSONObject) view.getTag()).put(FROM_HOME_LINK, link);
            ((JSONObject) view.getTag()).put(FROM_HOME_COUNT, commentcount);
            switch (menuIDString) {
                case "board_notice":    // 공지사항
                    break;
                case "board_recent":    // 최근게시물
                    ((JSONObject) view.getTag()).put("mainbbsYN", "Y");
                    break;
                default:
                    ((JSONObject) view.getTag()).put("mainbbsYN", "Y");
                    ((JSONObject) view.getTag()).put(CUSTOM_MENU_FUNCTION, function);
            }
        } catch (JSONException e) {
        }

        view.setSelected(true);
        onMenuClick(view);
    }
	public void showMenuByID(String menuIDString, @Nullable String event) {
		ViewModel.Log("showMenuByID start " + menuIDString, "tkofs_grid_click");
		MenuIDsMap menuID;
        try {
            menuID = MenuIDsMap.valueOf(menuIDString);
			ViewModel.Log("showMenuByID menuID " + menuID, "tkofs_grid_click");
            switch (menuID) {
                case mail_writing:
                case schedule_addschd:
                case schedule_addtodo:
                case settings_logout:
				case mailUnread:
                    menuID = MenuIDsMap.mail_received;
                    break;
			default:
				break;
		}
        } catch (Exception e) {
            menuID = MenuIDsMap.board_custom;
        }

		View view = menuMap.get(menuID.toString());
		if (view == null) {
			ViewModel.Log("showMenuByID view is null ", "tkofs_grid_click");
			return;
		}
		view.setSelected(true);
		onMenuClick(view, event);
	}

	public void showMenuByID(String menuIDString) {
		showMenuByID(menuIDString, null);
	}

	public void setController(MainController controller) {
		m_controller = controller;
	}
}
