package com.hs.mobile.gw.gnb;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.util.GridViewEx;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Group {

    public ImageView arrow;
    public JSONObject menuJson;
    public JSONArray mergeJsonArray;
    public String parentWindow;
    public Boolean visibleState = true;
    public String defaultOrientation;
    public String sectionId;
    public Boolean hideGroup = false;

    private Activity activity;
    private LinearLayout showHoldList;
    private LinearLayout showHideList;
    private GridViewEx showHideListGrid;
    private LinearLayout menuContainer;
    private LinearLayout groupContainer;
    private LinearLayout titleWrap;
    private Boolean toggleShow = true;
    private ArrayList<Item> showList;
    private LayoutInflater inflater;
    private ArrayList<String> filter;

    public Group(Activity ac, JSONObject json, String openPage) {
        if (!json.optString("section-id").equals("home")) {
            activity = ac;
            parentWindow = openPage;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            menuContainer = ac.findViewById(R.id.GNB_BTNS_CONTAINER);

            filter = new ArrayList();

            showList = new ArrayList();
            menuJson = json;
            drawMenuList(json);
        }
    }

    public Group(@Nullable Activity ac, JSONObject json, String openPage, @Nullable LinearLayout viewGroup) {
        if (!json.optString("section-id").equals("home")) {
            activity = ac;
            parentWindow = openPage;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            filter = new ArrayList();

            if (viewGroup != null) menuContainer = viewGroup;
            else menuContainer = (LinearLayout) ac.findViewById(R.id.GNB_BTNS_CONTAINER);

            showList = new ArrayList();
            menuJson = json;
            drawMenuList(json);
        }
    }

    public Group(@Nullable Activity ac, JSONObject json, String openPage, @Nullable LinearLayout viewGroup, @Nullable String orientation) {
        if (!json.optString("section-id").equals("home")) {
            sectionId = json.optString("section-id");
            activity = ac;
            parentWindow = openPage;
            defaultOrientation = orientation;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            filter = new ArrayList();
            ViewModel.Log("tkofs_menu", "viewGroup : " + viewGroup);
            if (viewGroup != null) menuContainer = viewGroup;
            else menuContainer = (LinearLayout) ac.findViewById(R.id.GNB_BTNS_CONTAINER);

            showList = new ArrayList();
            menuJson = json;
            drawMenuList(json);
        }
    }

    /**
     * @name groupTitle
     * @description - 대분류 생성
     */
    private void groupTitle(String name, int menuLayoutType) {
        if(menuLayoutType == 1){    // tkofs 그리드형
            groupContainer = (LinearLayout) inflater.inflate(R.layout.activity_gnb_gsty_group_container, null); // tkofs kosmes 그리드형
        }else{
            groupContainer = (LinearLayout) inflater.inflate(R.layout.activity_gnb_group_container, null);  // tkofs 기존 리스트형
        }
        menuContainer.addView(groupContainer, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        TextView title = (TextView) groupContainer.findViewById(R.id.gnb_group_title_text);
        title.setText(name);

        if(menuLayoutType == 0) {
            arrow = (ImageView) groupContainer.findViewById(R.id.ic_toggle_arrow);
        }
        titleWrap = (LinearLayout) groupContainer.findViewById(R.id.gnb_group_title_wrap);
    }

    /**
     * @name
     * @description - 그룹 타이틀 클릭시 토글 또는 링크 이동
     */
    private void GroupButton(Boolean b, JSONArray data) {
        if (b) { // 하위 메뉴가 1개 이상일경우 토글버튼
            titleWrap.setOnClickListener(new LinearLayout.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!toggleShow) return;
                    visibleState = !visibleState;
                    changeView(visibleState);
                }
            });
        }
        // 하위 메뉴가 1개 일경우 링크버튼
        else {
            try {
                GnbDataModel.menuListHelper.addMenuMap(data.optJSONObject(0).optString("menu-id"), data.optJSONObject(0), titleWrap);
                titleWrap.setTag(data.optJSONObject(0));
                titleWrap.setOnClickListener(new LinearLayout.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JSONObject menuData = (JSONObject) v.getTag();
                        if (menuData.optString("menu-id").equals("home_home")) {
                            if (parentWindow.equals("Main")) {
                                activity.finish();
                            } else {
                                MainFragment.menuListHelper.onMenuClick(v);
                                if (parentWindow.equals("Sub")) activity.finish();
                            }
                        } else {
                            MainFragment.menuListHelper.onMenuClick(v);
                        }
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    /**
     * @name setToggleState
     * @description - 접기시 숨김 메뉴가 없을경우 화살표 비노출
     */
    private void hideToggle() {
        if(ViewModel.menuLayoutType == 0){
            arrow.setVisibility(View.GONE);
        }
    }

    /**
     * @name JSONArray
     * @description - 보기 고정 목록과, 접기해당 메뉴 목록을 머지 한다
     */
    private JSONArray joinArray(JSONArray show, JSONArray hide) {
        JSONArray mergeAr = new JSONArray();
        try {
            if (show.length() > 0) {
                for (int a = 0; a < show.length(); ++a) mergeAr.put(show.optJSONObject(a));
                for (int i = 0; i < hide.length(); ++i) {
                    JSONObject currHide = hide.optJSONObject(i);
                    currHide.put("show-hide", true); // 토글 클릭시 숨김 처리 체크 값
                    mergeAr.put(currHide);
                }
            }
        } catch (Exception e) {
            System.out.println("## 머지 에러 ##");
        }
        return mergeAr;
    }

    /**
     * @name drawMenuList
     * @description - 하위 메뉴 생성
     */
    private void drawMenuList(JSONObject json) {
        // 숨김메뉴 필터
        //filter.add("외부메일");
        try {
            // 커스텀 메뉴나 별도 생성 메뉴경우 filter 를 통해 hidden 결정
            for (int j = 0; j < filter.size(); ++j) {
                if (json.getString("section-name").equals(filter.get(j))) {
                    json.put("group-hidden",true);
                    hideGroup = true;
                    break;
                }
            }

            // 고정 메뉴 목록이나 보기, 숨기기에 해당 메뉴가 있을경우 Group Container 생성
            JSONArray showArr = json.optJSONArray("visible-menu-list");
            JSONArray hideArr = json.optJSONArray("hidden-menu-list");

            if (showArr == null) {
                showArr = new JSONArray();
            } else {
                /*if ("board_all".equals(sectionId)) {    // 전체게시판 메뉴화 관련 tkofs
                    for (int i = 0; i < showArr.length(); i++) {
                        JSONObject tempJson = showArr.optJSONObject(i);
                        try {
                            tempJson.put(GnbDataModel.menuListHelper.MENU_TYPE, "P");
                            tempJson.put(GnbDataModel.menuListHelper.MENU_NAME, tempJson.optString("@text"));
                            tempJson.put(GnbDataModel.menuListHelper.MENU_ID, "board_custom_all");
                            tempJson.put(GnbDataModel.menuListHelper.ICON_TYPE, "C");
                            tempJson.put(GnbDataModel.menuListHelper.ICON_NAME, "icon_custom_sample_001.png");
                            tempJson.put(GnbDataModel.menuListHelper.CUSTOM_MENU_FUNCTION, tempJson.optString("@id"));
                        } catch (JSONException e) {
                        }
                    }
                }*/
            }

            if (hideArr == null) {
                toggleShow = false; // 토글 사용안함
                hideArr = new JSONArray();
            }
            if (showArr.length() > 0 || hideArr.length() > 0) {
                groupTitle(json.getString("section-name"), ViewModel.menuLayoutType);
                if(ViewModel.menuLayoutType == 1) {  // grid 형
                    showHideListGrid =  groupContainer.findViewById(R.id.m_show_list);
                }else{
                    showHideList = groupContainer.findViewById(R.id.m_show_list); // 보기,숨기
                }
                mergeJsonArray = joinArray(showArr, hideArr);

                /*try{  // 18.12.27 메뉴 예외처리 추가
                    String curMenuId = mergeJsonArray.optJSONObject(0).optString("menu-id");
                    if( "organization_chart".equals( curMenuId ) || "my_jobs".equals( curMenuId )
                        // ||"external_mail_received".equals( curMenuId )    // 외부메일은 전체메뉴에서 실행시 activity 가 꼬임.
                        ) {
                        pressShow = true;
                    }
                } catch(Exception e) {
                }*/


                if ( mergeJsonArray.length() > 0) {
                    if (!hideGroup) {
                        groupContainer.setVisibility(View.VISIBLE);
                        if(ViewModel.menuLayoutType == 1){  // grid 형
                            new ItemGrid(activity, showHideListGrid, mergeJsonArray, json.getString("section-id"), inflater, parentWindow, defaultOrientation);
                        }else{
                            showList.add(new Item(activity, showHideList, mergeJsonArray, json.getString("section-id"), inflater, parentWindow, defaultOrientation));
                        }
                    } // 하위메뉴 목록이 생성되지 않으면 대메뉴 숨김
                    else {
                        groupContainer.setVisibility(View.GONE);
                    }
                } else {
                    // 하위메뉴 목록이 생성되지 않았고 좌측메뉴가 아니면 대메뉴 숨김
                    if (!parentWindow.equals("LNB")) groupContainer.setVisibility(View.GONE);
                }

                if (!toggleShow) hideToggle();
                if(ViewModel.menuLayoutType == 0) {
                    GroupButton(mergeJsonArray.length() >= 2, mergeJsonArray);
                }
            }
        } catch (Exception e) {
            ViewModel.Log("itemgrid group error", "tkofs_menu_grid");
        }
    }

    public void changeView(Boolean state) {
        if (!toggleShow || menuJson == null) return;
        //arrow.setImageResource(state ? R.drawable.gnb_arrow_up : R.drawable.gnb_arrow_down);
        for (int i = 0; i < showList.size(); ++i) showList.get(i).visibleChange(state);
    }

    public void alignChange(Boolean state) {
        if (menuJson == null) return;
        if (showHideList.getChildCount() > 0) {
            for (int i = 0; i < showHideList.getChildCount(); ++i) {
                LinearLayout child = (LinearLayout) showHideList.getChildAt(i);
                child.setOrientation(state ? LinearLayout.HORIZONTAL : LinearLayout.VERTICAL);
            }
        }
    }

    public void setVisibleChange(Boolean state) {
        try {
            if (!state) {
                groupContainer.setVisibility(View.GONE);
            } else {
                groupContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            System.out.println("== :: Visible Error :: groupContainer : " + groupContainer + " ==");
        }
    }

    public String getSectionId() {
        return sectionId;
    }

    public JSONObject getData() {
        return menuJson;
    }

    public Boolean getShowMenuId(String mid) {
        Boolean show = false;
        try {
            for (int i = 0; i < mergeJsonArray.length(); ++i) {
                JSONObject cjson = mergeJsonArray.optJSONObject(i);
                if(cjson.getString("menu-id").equals(mid)) {
                    //if( !cjson.getString("menu-id").equals("board_all") ) {
                        show = true;
                    //}
                    break;
                }
            }
        } catch (Exception e) { }

        if(getSectionId().equals(mid) ) show = true;
        if(hideGroup) show = false;

        return show;
    }
}
