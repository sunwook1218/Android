package com.hs.mobile.gw.gnb;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.sqlite.CustomIcon;
import com.hs.mobile.gw.sqlite.CustomIconDBHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Item {

    public Activity activity;
    public String parentWindow;
    public String defaultOrientation;
    public LinearLayout container;
    public LayoutInflater layoutInflater;
    public LinearLayout layout;
    public ArrayList<LinearLayout> lineWrap;
    public ArrayList<LinearLayout> hideList;

    public Item(@Nullable Activity ac, LinearLayout viewGroup, JSONArray json, String sectionId, LayoutInflater inflater, String page) {
        container = viewGroup;
        layoutInflater = inflater;
        parentWindow = page;
        activity = ac;
        this.db = CustomIconDBHelper.getDBHelper(activity);
        try {
            lineWrap = new ArrayList();
            hideList = new ArrayList();
            if (json.length() > 0) createMenuItem(json);
        } catch (Exception e) { }
    }

    public Item(@Nullable Activity ac, LinearLayout viewGroup, JSONArray json, String sectionId, LayoutInflater inflater, String page, String orientation) {
        container = viewGroup;
        layoutInflater = inflater;
        parentWindow = page;
        defaultOrientation = orientation;
        activity = ac;
        this.db = CustomIconDBHelper.getDBHelper(activity);
        try {
            lineWrap = new ArrayList();
            hideList = new ArrayList();
            if (json.length() > 0) createMenuItem(json);
        } catch (Exception e) {
        }
    }

    /**
     * @param json
     * @name createMenuItem
     * @description - 짝수체크 및 메뉴 생성
     */
    public void createMenuItem(JSONArray json) {
        try {
            for (int i = 0; i < json.length(); ++i) {
                int g = i % 2;
                if(g == 0) createLinearLayout();

                ItemMenu(new JSONObject(json.get(i).toString()));
            }
        } catch (Exception e) {
        }
    }

    /**
     * @param
     * @name createLinearLayout
     * @description - 메뉴를 2개씩 담을 컨테이너 생성
     */
    private void createLinearLayout() {
        LinearLayout emptylayer = new LinearLayout(container.getContext());
        emptylayer.setOrientation(defaultOrientation != null ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
        emptylayer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(emptylayer);

        lineWrap.add(emptylayer);
        layout = emptylayer;
    }

    /**
     * @param object
     * @name ItemMenu
     * @description - 메뉴생성
     */
    private void ItemMenu(JSONObject object) {
        LinearLayout item = (LinearLayout) layoutInflater.inflate(R.layout.activity_gnb_menu_item, null);
        TextView name = (TextView) item.findViewById(R.id.item_name);
        //TextView menuCountView = (TextView) item.findViewById(R.id.badge_count);

        try { // 18.11.26
            if (parentWindow.equals("LNB")) {
                GnbDataModel.menuListHelper.addMenuMap(object.getString("menu-id"), object, item);
            }
            setIcon(item, object);
        } catch (Exception e) {
            ViewModel.Log(object, "tkofs_icon_err");
        }

        try {
            name.setText(object.getString("menu-name"));
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            item.setTag(object);
            layout.addView(item, param);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JSONObject menuData = (JSONObject) v.getTag();
                    MainFragment.menuListHelper.onMenuClick(v);
                    try {
                        if (parentWindow.equals("Sub") || parentWindow.equals("Main"))
                            activity.finish();
                    } catch (Exception e) {
                    }
                }
            });
            if (object.getBoolean("show-hide")) hideList.add(item);
        } catch (Exception e) {
        }
    }

    CustomIconDBHelper db;
    /**
     * @param v
     * @param id
     * @name setIcon
     * @description - 아이콘 적용
     */
    private void setIcon(View v, JSONObject object) {
        ImageView imgview = (ImageView) v.findViewById(R.id.item_icon);
        String iconName = object.optString("icon-name");
        if (GnbDataModel.menuListHelper.PRODUCT_ICON_TYPE.equalsIgnoreCase(object.optString("icon-type"))) {
            String iconNameWithoutExp = iconName.split("\\.")[0];
            imgview.setImageResource(
                    activity.getResources().getIdentifier(iconNameWithoutExp, "drawable", activity.getPackageName()));
        } else {
            CustomIcon icon = db.getIcon(iconName);
            if (icon == null) {
                imgview.setImageResource(R.drawable.icon_custom_default);
            } else {
                Bitmap iconBitMap = BitmapFactory.decodeByteArray(icon.getByteArray(), 0, icon.getByteArray().length);
                iconBitMap.setDensity(DisplayMetrics.DENSITY_XHIGH);
                imgview.setImageBitmap(iconBitMap);
            }
        }
    }

    /**
     * @param b
     * @name visibleChange
     * @description - 보이기, 숨기기 처리
     */
    public void visibleChange(Boolean b) {
        for (int i = 0; i < hideList.size(); ++i) {
            hideList.get(i).setVisibility(b ? View.VISIBLE : View.GONE);
        }
    }
}