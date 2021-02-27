package com.hs.mobile.gw.gnb;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.sqlite.CustomIcon;
import com.hs.mobile.gw.sqlite.CustomIconDBHelper;
import com.hs.mobile.gw.util.GridViewEx;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemGrid {

    public Activity activity;
    public String parentWindow;
    public String defaultOrientation;
    public GridViewEx container;
    public LayoutInflater layoutInflater;
    public GridMenuAdapter gridMenuAdapter;
    public LinearLayout layout;
    public CustomIconDBHelper db;

    public ItemGrid(@Nullable Activity ac, GridViewEx viewGroup, JSONArray json, String sectionId, LayoutInflater inflater, String page, String orientation) {
        container = viewGroup;
        layoutInflater = inflater;
        parentWindow = page;
        defaultOrientation = orientation;
        activity = ac;
        this.db = CustomIconDBHelper.getDBHelper(activity);
        try {
            if (json.length() > 0) createMenuItem(json);
        } catch (Exception e) {
        }
    }



    /**
     * @param json
     * @name createMenuItem
     * @description -
     */
    public void createMenuItem(JSONArray json) {
        if (MainModel.getInstance().isTablet() || activity.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
            container.setNumColumns(8);
        } else {
            container.setNumColumns(4);
        }
        ArrayList<MenuButtionItem> itemMenus = null;
        try {
            itemMenus = new ArrayList<MenuButtionItem>();
            for (int i = 0; i < json.length(); i++) {
                itemMenus.add(new MenuButtionItem(json.getJSONObject(i)));
            }
        } catch (Exception e) {
            ViewModel.Log(json, "tkofs_grid_menu_err");
        }
        // 메뉴 그리드 아답터 생성
        gridMenuAdapter = new GridMenuAdapter(itemMenus);
        container.setAdapter(gridMenuAdapter);
        container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.m_show_list:
                        //ViewModel.Log(m_gridMenuAdapter.getItem(position).m_strMenuID, "tkofs_grid_click");
                        /*MainFragment.menuListHelper.showMenuByID(m_gridMenuAdapter.getItem(position).m_strMenuID);
                        //if (!m_gridMenuAdapter.getItem(position).m_strMenuID.equals(MenuListHelper.MenuIDsMap.external_mail_received.toString())) {
                        getActivity().finish();
                        //}
                        */
                        MainFragment.menuListHelper.onMenuClick(view);
                        if (!"settings_logout".equals(gridMenuAdapter.getItem(position).m_strMenuID) && (parentWindow.equals("Sub") || parentWindow.equals("Main")))
                            activity.finish();
                        break;
                }
            }
        });
    }


    public static class MenuButtionItem {
        public String iconName;
        public String iconType;
        public int m_nCount = -1;
        public String m_strButtonName;
        public String m_strMenuID;
        public JSONObject menuData;

        public MenuButtionItem(JSONObject menuData) {
            this.menuData = menuData;
            this.iconName = menuData.optString("icon-name");
            this.iconType = menuData.optString("icon-type");
            this.m_strButtonName = menuData.optString("menu-name");
            this.m_strMenuID = menuData.optString("menu-id");
        }
    }

    public class GridMenuAdapter extends BaseAdapter {

        private ArrayList<MenuButtionItem> m_data;
        private HashMap<String, View> convertView;

        public GridMenuAdapter(ArrayList<MenuButtionItem> buttonItems) {
            this.m_data = buttonItems;
            this.convertView = new HashMap<String, View>();
        }

        @Override
        public int getCount(){
            return m_data == null ? 0 : m_data.size();
        }

        @Override
        public MenuButtionItem getItem(int position) {
            return m_data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void setConvertView(int position, View v) {
            convertView.put("P" + position, v);
        }

        public View getConvertView(int position) {
            return convertView.get("P" + position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            if (convertView == null) {
                if (getConvertView(position) != null) {
                    v = getConvertView(position);
                } else {
                    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lnb_item_menu_list, null);
                    setConvertView(position, v);
                }
            } else {
                v = convertView;
            }

            final View fv = v;
            v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    AbsListView.LayoutParams p = (AbsListView.LayoutParams) fv.getLayoutParams();
                    p.height = fv.getWidth();
                    fv.setLayoutParams(p);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        fv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        fv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                }
            });

            try { // 18.11.26 전체메뉴인경우 menuMap 추가
                if(getItem(position).m_strMenuID.equals("board_all")){
                    String menuname = getItem(position).menuData.optString("menu-name");
                    ViewModel.Log(menuname, "tkofs_lnb");
                }
                if (parentWindow.equals("LNB")) {
                    GnbDataModel.menuListHelper.addMenuMap(
                            getItem(position).m_strMenuID, getItem(position).menuData, getConvertView(position));
                }else{
                    GnbDataModel.menuCountMap.put(getItem(position).m_strMenuID, getConvertView(position));
                }
            } catch (Exception e) {
            }

            v.setTag(getItem(position).menuData);

            // tkofs 이미지
            ImageView iv = v.findViewById(R.id.ID_IMG_HOME_MENU_ICON);
            String iconName = getItem(position).menuData.optString("icon-name");
            if (GnbDataModel.menuListHelper.PRODUCT_ICON_TYPE.equalsIgnoreCase(getItem(position).menuData.optString("icon-type"))) {
                String iconNameWithoutExp = iconName.split("\\.")[0];
                iv.setImageResource(
                        activity.getResources().getIdentifier(iconNameWithoutExp, "drawable", activity.getPackageName()));
            } else {
                CustomIcon icon = db.getIcon(iconName);
                if (icon == null) {
                    iv.setImageResource(R.drawable.icon_custom_default);
                } else {
                    Bitmap iconBitMap = BitmapFactory.decodeByteArray(icon.getByteArray(), 0, icon.getByteArray().length);
                    iconBitMap.setDensity(DisplayMetrics.DENSITY_XHIGH);
                    iv.setImageBitmap(iconBitMap);
                }
            }
            ((TextView) v.findViewById(R.id.ID_TV_NAME)).setText(getItem(position).m_strButtonName);
            /*if (m_countVo != null) {
                if (m_countVo.mtrlNew != 0 && TextUtils.equals(getItem(position).m_strMenuID, "board_recent")) {
                    (v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.VISIBLE);
                    ((TextView) v.findViewById(R.id.ID_TV_COUNT)).setText(String.valueOf(m_countVo.mtrlNew));
                } else if (m_countVo.apprWait != 0 && TextUtils.equals(getItem(position).m_strMenuID, "approval_waiting")) {
                    (v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.VISIBLE);
                    ((TextView) v.findViewById(R.id.ID_TV_COUNT)).setText(String.valueOf(m_countVo.apprWait));
                } else {
                    ( v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.GONE);
                }
            } else {
                (v.findViewById(R.id.ID_TV_COUNT)).setVisibility(View.GONE);
            }*/
            return v;
        }
    }
}