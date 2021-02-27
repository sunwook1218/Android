package com.hs.mobile.gw.view;

import com.hs.mobile.gw.adapter.MGWBaseAdapter;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.service.OpenAPIService;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;

public class OnScrollListView extends PullToRefreshListView implements OnScrollListener {
    ProgressDialog progressDialog;
    MGWBaseAdapter mAdapter = null;
    OnScrollListView listView = null;

    public OnScrollListView(Context context) {
        super(context);
        this.setOnScrollListener(this);
        this.listView = this;
        listView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.setLoadingInProgress(true)) {
                            MainFragment.getController().hideListFooter();
                            mAdapter.initData();
                        }
                        listView.onRefreshComplete();
                    }
                }, 1);

            }
        });
    }

    public MGWBaseAdapter getMGWBaseAdapter() {
        return mAdapter;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        mAdapter = (MGWBaseAdapter) adapter;
        HeaderViewListAdapter ha = new HeaderViewListAdapter(null, null, adapter);
        super.setAdapter(ha);
        this.setOnItemClickListener((MGWBaseAdapter) adapter);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int count = (totalItemCount - visibleItemCount) - 0;//0개의 아이템 전에 미리 추가 데이터 로딩
        if (firstVisibleItem >= count && totalItemCount != 0) {
            if (mAdapter.isLoadingInProgress()) {
                //처리중이면 무시
                return;
            }
            if (OpenAPIService.isSessionExpired) {
                return;
            }
            if (mAdapter.hasMoreData() && mAdapter.setLoadingInProgress(false)) {
                mAdapter.getMoreData();
            } else if (!mAdapter.hasMoreData()) {
                MainFragment.getController().hideListFooter();
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
//        setSelection(0);
//    }
}
