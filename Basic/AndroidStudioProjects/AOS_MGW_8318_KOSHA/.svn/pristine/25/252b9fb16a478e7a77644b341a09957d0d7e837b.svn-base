package com.hs.mobile.gw.fragment.square.order;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.CommonFragment;
import com.hs.mobile.gw.fragment.square.order.SquareOrderModel.OrderFilter;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SquareOrderFragment extends CommonFragment {
	public static final String ARG_KEY_SQUARE_ID = "arg_key_square_id";
	private SquareOrderModel m_model;
	private SquareOrderController m_controller;
	public LinearLayout m_btnOrderAll;
	public LinearLayout m_btnOrderSend;
	public PullToRefreshListView m_lvOrder;
	private ImageView m_btnBack;
	public LinearLayout m_btnOrderReceive;
	private TextView m_btnSearch;
	private TextView m_btnOrderType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		m_model = new SquareOrderModel();
		m_controller = new SquareOrderController(this, m_model);
		m_controller.checkArgument();
		MainModel.getInstance().addContentsChangeListener(m_controller);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_square_order, container, false);
		m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
		m_btnOrderType = (TextView) v.findViewById(R.id.ID_BTN_TYPE);
		m_btnSearch = (TextView) v.findViewById(R.id.ID_BTN_SEARCH);
		m_btnOrderAll = (LinearLayout) v.findViewById(R.id.ID_LAY_L_ORDER_ALL);
		m_btnOrderSend = (LinearLayout) v.findViewById(R.id.ID_LAY_L_ORDER_SEND);
		m_btnOrderReceive = (LinearLayout) v.findViewById(R.id.ID_LAY_L_ORDER_RECEIVE);
		m_lvOrder = (PullToRefreshListView) v.findViewById(R.id.ID_LV_ORDER);
		m_btnBack.setOnClickListener(m_controller);
		m_btnOrderType.setOnClickListener(m_controller);
		m_btnSearch.setOnClickListener(m_controller);
		m_btnOrderAll.setOnClickListener(m_controller);
		m_btnOrderSend.setOnClickListener(m_controller);
		m_btnOrderReceive.setOnClickListener(m_controller);
		m_lvOrder.setEmptyView(v.findViewById(R.id.empty_list_item));
		m_model.createAdapter(getActivity(), m_controller);
		m_lvOrder.setAdapter(m_model.getWorkAdapter());
		m_lvOrder.setOnItemClickListener(m_controller);
		m_lvOrder.setOnRefreshListener(m_controller);
		m_lvOrder.getRefreshableView().setDividerHeight(0);
		m_lvOrder.setOnLastItemVisibleListener(m_controller);
		m_controller.setFilter(OrderFilter.ALL);
		return v;
	}

	@Override
	public void onDestroy() {
		MainModel.getInstance().removeContentsChangeListener(m_controller);
		super.onDestroy();
	}
}
