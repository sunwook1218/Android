package com.hs.mobile.gw.view;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.gnb.GnbDataModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.service.HMGWServiceHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class MiddleMenuContainer extends LinearLayout implements View.OnClickListener {

	private ViewFlipper middleMenuFlipper;

	public MiddleMenuContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		View view = (View) LayoutInflater.from(getContext()).inflate(R.layout.middle_menu_container, this);
		middleMenuFlipper = (ViewFlipper) findViewById(R.id.middle_list_menu);
		
		createHead(view); // create sub header
	}

	public MiddleMenuContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MiddleMenuContainer(Context context) {
		super(context);
		init();
	}

/**
     * @param container
     * @Autor tkofs
     * @Date 20.06.23
     * @name createHead
     */
    public void createHead(View container) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fragment_sub_header_content, null);

        LinearLayout subContainer = (LinearLayout) container.findViewById(R.id.sub_head_container);
        subContainer.addView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        v.findViewById(R.id.BTN_SUB_LNB_OPEN).setOnClickListener(this);
        v.findViewById(R.id.BTN_SUB_HOME).setOnClickListener(this);
        v.findViewById(R.id.BTN_SUB_HOME_LY).setOnClickListener(this);
        v.findViewById(R.id.btn_sub_haeder_logo).setOnClickListener(this);

        try { // 팝업 모드이거나, 헤더 숨김 값이 있을경우 헤더 숨김 tkofs
            if (MainModel.getInstance().isPopupMode() || MainModel.getInstance().getNavibarVisible()) {
                //View subheader = v.findViewById(R.id.sub_head_container);
                subContainer.setVisibility(View.GONE);
            } else {
                subContainer.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            ViewModel.Log("[MiddleMenuContainer] 헤더 숨김 에러 > " + e);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BTN_SUB_LNB_OPEN:
                ViewModel.Log(HMGWServiceHelper.mgw_menu, "tkofs_menu_grid_middle");
                GnbDataModel.mainActivity.mOnPopupClick(v);
                break;
            case R.id.BTN_SUB_HOME:
            case R.id.btn_sub_haeder_logo:
            case R.id.BTN_SUB_HOME_LY:
                MainFragment.menuListHelper.showMenuByID("home_home");
                break;
        }
    }
    
	public ViewFlipper getMiddleMenuFlipper() {
		return middleMenuFlipper;
	}

}
