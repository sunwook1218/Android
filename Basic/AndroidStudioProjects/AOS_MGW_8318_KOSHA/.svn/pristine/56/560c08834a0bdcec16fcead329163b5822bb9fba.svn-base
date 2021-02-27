package com.hs.mobile.gw.fragment.square.order;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IChangeContentsListener;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.fragment.CommonFragmentController;
import com.hs.mobile.gw.fragment.SquareContentsDetailFragment;
import com.hs.mobile.gw.fragment.square.order.SquareOrderModel.ISquareOrderResultListener;
import com.hs.mobile.gw.fragment.square.order.SquareOrderModel.OrderFilter;
import com.hs.mobile.gw.fragment.square.order.SquareOrderModel.OrderType;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SquareOrderController extends CommonFragmentController<SquareOrderFragment, SquareOrderModel> implements OnClickListener,
		IBookmarkAndOptionViewListener, OnItemClickListener, OnRefreshListener<ListView>, IChangeContentsListener,
		OnLastItemVisibleListener {

	public SquareOrderController(SquareOrderFragment view, SquareOrderModel model) {
		super(view, model);
	}

	public void checkArgument() {
		if (getView().getArguments() != null) {
			String strSquareId = null;
			if (getView().getArguments().containsKey(MainModel.ARG_KEY_SQUARE_ID)) {
				strSquareId = getView().getArguments().getString(MainModel.ARG_KEY_SQUARE_ID);
			}
			if (getView().getArguments().containsKey(MainModel.ARG_KEY_GROUP_STATUS)) {
				getModel().mGroupStatus = (Status) getView().getArguments().getSerializable(MainModel.ARG_KEY_GROUP_STATUS);
			}
			if (!TextUtils.isEmpty(strSquareId)) {
				getModel().mStrSquareId = strSquareId;
			}
		} else {
			getView().getActivity().finish();
		}
	}

	public void setFilter(OrderFilter t) {
		getModel().mOrderFilter = t;
		switch (t) {
		case ALL:
			getView().m_btnOrderAll.setSelected(true);
			getView().m_btnOrderSend.setSelected(false);
			getView().m_btnOrderReceive.setSelected(false);
			break;
		case SEND:
			getView().m_btnOrderAll.setSelected(false);
			getView().m_btnOrderSend.setSelected(true);
			getView().m_btnOrderReceive.setSelected(false);
			break;
		case RECEIVE:
			getView().m_btnOrderAll.setSelected(false);
			getView().m_btnOrderSend.setSelected(false);
			getView().m_btnOrderReceive.setSelected(true);
			break;
		}
		setOrderType(getModel().mOrderType);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ID_BTN_BACK:
			getActivity().finish();
			break;
		case R.id.ID_BTN_TYPE:
			String[] items = new String[] { 
					getString(R.string.favorite_file_type_all),
					getString(R.string.label_square_process_work),
					getString(R.string.label_square_delay_work),
					getString(R.string.label_square_complete_work)};
			new AlertDialog.Builder(getActivity()).setItems(items, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					OrderType ot = OrderType.values()[which];
					setOrderType(ot);
				}
			}).show();
			break;
		case R.id.ID_LAY_L_ORDER_ALL:
			getModel().mOrderType = OrderType.ALL;
			setFilter(OrderFilter.ALL);
			break;
		case R.id.ID_LAY_L_ORDER_SEND:
			getModel().mOrderType = OrderType.ALL;
			setFilter(OrderFilter.SEND);
			break;
		case R.id.ID_LAY_L_ORDER_RECEIVE:
			getModel().mOrderType = OrderType.ALL;
			setFilter(OrderFilter.RECEIVE);
			break;
		case R.id.ID_BTN_SEARCH:
			MainModel.getInstance().showSubActivity(
					getActivity(),
					SubActivityType.SQUARE_SEARCH,
					new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, getModel().mStrSquareId)
							.add(MainModel.ARG_KEY_GROUP_STATUS, getModel().mGroupStatus).build());
			break;
		}
	}

	protected void setOrderType(OrderType ot) {
		getModel().loadInstructionsList(getActivity(), getModel().mOrderFilter, ot, new ISquareOrderResultListener() {
			@Override
			public void onResult(SquareDefaultVO vo) {
				getModel().getWorkAdapter().notifyDataSetChanged();
				getView().m_lvOrder.onRefreshComplete();
			}
		});
	}

	@Override
	public void onDelete(MainContentsListItemVO item) {

	}

	@Override
	public void onCopy(MainContentsListItemVO item) {

	}

	@Override
	public void onModify(MainContentsListItemVO item) {

	}

	@Override
	public void onFavoriteClick(String squareId, String contentsId, FavoriteContentsType favorityType, boolean flag,
			SquareDefaultCallback squareDefaultCallback) {
		MainModel.getInstance().addFavoriteContents(getActivity(), squareDefaultCallback, squareId, contentsId, favorityType, flag);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		int nPosition = position - getView().m_lvOrder.getRefreshableView().getHeaderViewsCount();
		Bundle build = new BundleUtils.Builder()
				.add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO, getModel().getWorkAdapter().getItem(nPosition))
				.add(MainModel.ARG_KEY_GROUP_STATUS, getModel().mGroupStatus).build();
		MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_CONTENTS_DETAIL, build);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		setFilter(getModel().mOrderFilter);
	}

	@Override
	public void onChange(ChangeType t, MainContentsListItemVO item) {
		switch (t) {
		case ADD:
			getModel().m_workData.add(item);
			getModel().getWorkAdapter().notifyDataSetChanged();
			break;
		case DELETE:
			if (getModel().m_workData.contains(item)) {
				getModel().m_workData.remove(item);
				getModel().getWorkAdapter().notifyDataSetChanged();
			}
			break;
		case MODIFY:
			if (getModel().m_workData.contains(item)) {
				int n = getModel().m_workData.indexOf(item);
				getModel().m_workData.remove(n);
				getModel().m_workData.add(n, item);
				getModel().getWorkAdapter().notifyDataSetChanged();
			}
			break;
		}
	}

	@Override
	public void onLastItemVisible() {
		if (!TextUtils.isEmpty(getModel().mLastContentsId)) {
			getModel().loadMoreData(getActivity(), new ISquareOrderResultListener() {
				@Override
				public void onResult(SquareDefaultVO vo) {
					getModel().getWorkAdapter().notifyDataSetChanged();
					getView().m_lvOrder.onRefreshComplete();
				}
			});
		}
	}
}
