/**
 * @Autor LIKEARTS
 * @date 18.11.19
 */

package com.hs.mobile.gw.main;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Button;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.hsuco.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tabs {

    public ArrayList<Button> trigger;
    public Boolean triggerMode = true;
    private TabLayout tabs;
    private ArrayList<String> tabName;

    public Tabs(View v) {
        init(v);
    }

    private void init(View v) {
        //3탭기능 구성
        tabs = (TabLayout) v.findViewById(R.id.tabs);
        tabs.setElevation(0);
    }

    /**
     * @param json
     * @name setAppendData
     * @description - 데이터를 받아서 Tab을 생성한다.
     */
    public void setAppendData(JSONArray json) {
        JSONObject jBtnObj;
        if (json != null) {
            int boardCnt = json.length();
            for (int i = 0; i < boardCnt; i++) {
                try {
                    jBtnObj = json.getJSONObject(i);
                    String menuName = jBtnObj.getString("menu-name");

                    tabs.addTab(tabs.newTab().setText(menuName));
                } catch (Exception e) {
                }
            }
            new TabRemoveSpace().wrapTabIndicatorToTitle(tabs, 20, 20);
        }
    }

    /**
     * @param buttons
     * @name setTrigger
     * @description - 상호작용할 버튼을 배열로 받음
     */
    public void setTrigger(ArrayList<Button> buttons) {
        trigger = buttons;
    }

    /**
     * @param index
     * @name triggerSync
     * @description - HomeFragment 기존 아이템과 상호작용 중 인덱스
     * 싱크가 맞지 않을경우 동일하게 적용
     */
    public void triggerSync(int index) {
        if (tabs.getSelectedTabPosition() != index) {
            triggerMode = false;
            tabs.getTabAt(index).select();
        }
    }

    public void setFragment(FragmentManager fragment) {
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //선택된 탭 번호 반환
                int position = tab.getPosition();
                ViewModel.Log("curentpage : " + position+"/"+trigger.size());

                trigger.get(position).performClick();
                triggerMode = true;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

}