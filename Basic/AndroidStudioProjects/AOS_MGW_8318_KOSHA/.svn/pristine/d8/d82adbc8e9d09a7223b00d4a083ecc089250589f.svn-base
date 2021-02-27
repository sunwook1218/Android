package com.hs.mobile.gw.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.androidquery.callback.AjaxStatus;
import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.MainModel.IActivityResultHandlerListener;
import com.hs.mobile.gw.MainModel.IChangeContentsListener;
import com.hs.mobile.gw.MainModel.IContentsDeleteListener;
import com.hs.mobile.gw.MainModel.ISquarePushListener;
import com.hs.mobile.gw.MainModel.ResultType;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.SubActivity.SubActivityType;
import com.hs.mobile.gw.adapter.SquareGroupContentsAdapter;
import com.hs.mobile.gw.adapter.SquareGroupContentsAdapter.ButtonType;
import com.hs.mobile.gw.adapter.SquareGroupContentsAdapter.ISquareGroupContentItemListener;
import com.hs.mobile.gw.openapi.square.AddFile;
import com.hs.mobile.gw.openapi.square.AddMessage;
import com.hs.mobile.gw.openapi.square.AddMessageResult;
import com.hs.mobile.gw.openapi.square.GetContents;
import com.hs.mobile.gw.openapi.square.GetMainContentsList;
import com.hs.mobile.gw.openapi.square.GetNewContentsList;
import com.hs.mobile.gw.openapi.square.SquareDefaultAjaxCallBack;
import com.hs.mobile.gw.openapi.square.SquareDefaultCallback;
import com.hs.mobile.gw.openapi.square.vo.AttachListItemVO;
import com.hs.mobile.gw.openapi.square.vo.ContentsVO;
import com.hs.mobile.gw.openapi.square.vo.FavoriteContentsType;
import com.hs.mobile.gw.openapi.square.vo.GroupInfoVO;
import com.hs.mobile.gw.openapi.square.vo.IContentsListItem;
import com.hs.mobile.gw.openapi.square.vo.IContentsListItem.ContentsObjectType;
import com.hs.mobile.gw.openapi.square.vo.MainContentsListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO;
import com.hs.mobile.gw.openapi.square.vo.MyWorkGroupMenuListItemVO.Status;
import com.hs.mobile.gw.openapi.square.vo.SquareContentsType;
import com.hs.mobile.gw.openapi.square.vo.SquareDefaultVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO;
import com.hs.mobile.gw.openapi.square.vo.SquarePushVO.SquarePushAction;
import com.hs.mobile.gw.openapi.square.vo.SystemDateVO;
import com.hs.mobile.gw.openapi.square.vo.SystemNewMessageLabel;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.util.ApiLoader;
import com.hs.mobile.gw.util.ApiLoaderEx;
import com.hs.mobile.gw.util.BundleUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.PopupUtil;
import com.hs.mobile.gw.view.BookmarkAndOptionView.IBookmarkAndOptionViewListener;
import com.hs.mobile.gw.view.FileAttachArea.IFileAttachClickListener;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshBase.Mode;
import com.hs.mobile.gw.view.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class SquareContentsFragment extends CommonFragment implements OnScrollListener, OnClickListener, OnItemClickListener,
        IBookmarkAndOptionViewListener, IActivityResultHandlerListener, ISquareGroupContentItemListener, IChangeContentsListener,
        IFileAttachClickListener, ISquarePushListener {
    public static final String ARG_KEY_WORKGROUP_ITEM = "workGroupItem";
    public static final String ARG_KEY_CLICKED_BUTTON_TYPE = "clicked_button_type";
    private ImageView m_btnBack;
    private ImageView m_btnMenu;
    private TextView m_tvTitle;
    private ImageView m_btnMore;
    private PullToRefreshListView m_lvSquareContents;
    private Button m_btnUpload;
    private EditText m_edMessage;
    private MyWorkGroupMenuListItemVO m_squareData;
    protected ArrayList<IContentsListItem> m_data = new ArrayList<IContentsListItem>();
    private SquareGroupContentsAdapter m_adapter;
    private String m_strLastContentsId;
    private Button m_btnSend;
    private Comparator<MainContentsListItemVO> m_timebaseComparator = new Comparator<MainContentsListItemVO>() {
        @Override
        public int compare(MainContentsListItemVO lhs, MainContentsListItemVO rhs) {
            if (lhs.indexDate > rhs.indexDate)
                return 1;
            else if (lhs.indexDate < rhs.indexDate)
                return -1;
            else
                return 0;
        }
    };
    private LinearLayout m_inputLayout;

    private boolean mListRefreshNeededWhenResume = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 전달 받은 데이터 확인
        if (getArguments() != null) {
            if (getArguments().getSerializable(ARG_KEY_WORKGROUP_ITEM) != null) {
                m_squareData = (MyWorkGroupMenuListItemVO) getArguments().getSerializable(ARG_KEY_WORKGROUP_ITEM);
                Debug.trace(m_squareData.title);
                MainModel.getInstance().setCurrentSquareId(m_squareData.squareId);
                MainModel.getInstance().addContentsChangeListener(this);
                MainModel.getInstance().addSquarePushListener(this);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_square_contents, container, false);
		super.createHead(v); // create sub header
        m_btnBack = (ImageView) v.findViewById(R.id.ID_BTN_BACK);
        m_btnMenu = (ImageView) v.findViewById(R.id.ID_BTN_MENU);
        m_tvTitle = (TextView) v.findViewById(R.id.ID_TV_TITLE);
        m_btnMore = (ImageView) v.findViewById(R.id.ID_BTN_MORE);
        m_lvSquareContents = (PullToRefreshListView) v.findViewById(R.id.ID_LV_SQUARE_CONTENTS);
        m_btnUpload = (Button) v.findViewById(R.id.ID_BTN_UPLOAD);
        m_edMessage = (EditText) v.findViewById(R.id.ID_ED_MESSAGE);
        m_tvTitle.setText(m_squareData.title);
        m_inputLayout = (LinearLayout) v.findViewById(R.id.ID_LAY_L_INPUT);
        m_btnSend = (Button) v.findViewById(R.id.ID_BTN_SEND);

        if (MainModel.getInstance().isTablet()) {
            m_btnBack.setVisibility(View.GONE);
            m_btnMenu.setVisibility(View.GONE);
        }

        m_lvSquareContents.setMode(Mode.DISABLED);
        m_lvSquareContents.getRefreshableView().setDividerHeight(0);
        m_lvSquareContents.getRefreshableView().setScrollingCacheEnabled(false);
        m_lvSquareContents.getRefreshableView().setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        m_lvSquareContents.setFadingEdgeLength(0);
        m_adapter = new SquareGroupContentsAdapter(m_squareData.status, m_data, this, this);
        m_adapter.setBookMarkAndOptionViewListener(this);

        m_lvSquareContents.setAdapter(m_adapter);
        // 화면이 갱신되는동안 다시 그려지는 것을 막는다.
        m_lvSquareContents.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                return m_bDraw;
            }
        });
        m_lvSquareContents.setOnItemClickListener(this);
        m_btnBack.setOnClickListener(this);
        m_btnMenu.setOnClickListener(this);
        m_btnMore.setOnClickListener(this);
        m_btnUpload.setOnClickListener(this);
        m_btnSend.setOnClickListener(this);

        refreshList();

        return v;
    }

    private void refreshList() {
        final ArrayList<IContentsListItem> tempDataList = new ArrayList<IContentsListItem>();
        SquareDefaultAjaxCallBack<SquareDefaultVO> callBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(getActivity(),
                SquareDefaultVO.class) {
            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                super.callback(url, json, status);
                if (getVO().responseData != null && getVO().responseData.dataList != null) {
                    ArrayList<MainContentsListItemVO> dataList = new ArrayList<MainContentsListItemVO>();
                    for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
                        dataList.add(new MainContentsListItemVO(getVO().responseData.dataList.optJSONObject(i)));
                    }
                    sortData(dataList);
                    tempDataList.addAll(dataList);
                    m_strLastContentsId = getVO().responseData.lastViewId;
                    SquareDefaultAjaxCallBack<SquareDefaultVO> newContentsCallBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(
                            getActivity(), SquareDefaultVO.class) {
                        public void callback(String url, JSONObject json, AjaxStatus status) {
                            super.callback(url, json, status);
                            if (getVO().responseData != null && getVO().responseData.dataList != null) {
                                ArrayList<MainContentsListItemVO> newContentsDataList = new ArrayList<MainContentsListItemVO>();
                                for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
                                    newContentsDataList.add(new MainContentsListItemVO(getVO().responseData.dataList.optJSONObject(i)));
                                }
                                sortData(newContentsDataList);
                                if (newContentsDataList.size() > 0) {
                                    tempDataList.add(new SystemNewMessageLabel());
                                }
                                tempDataList.addAll(newContentsDataList);
                            }
                            m_data.clear();
                            m_data.addAll(addDateSeperator(tempDataList));
                            deleteDuplecateSystemDate(m_data);
                            m_adapter.notifyDataSetChanged();
                            m_lvSquareContents.getRefreshableView().setSelection(m_adapter.getCount() - 1);
                            m_lvSquareContents.setOnScrollListener(SquareContentsFragment.this);
                        }
                    };
                    Map<String, String> newContentsParams = new HashMap<String, String>();
                    newContentsParams.put("squareId", m_squareData.squareId);
                    newContentsCallBack.progress(PopupUtil.getProgressDialog(getActivity()));
                    // 안읽은 메시지를 긁어 온다.
                    new ApiLoaderEx<JSONObject>(new GetNewContentsList(getActivity()), getActivity(), newContentsCallBack,
                            newContentsParams).execute();
                }
            }
        };
        callBack.progress(PopupUtil.getProgressDialog(getActivity()));
        Map<String, String> params = new HashMap<String, String>();
        params.put("squareId", m_squareData.squareId);

        new ApiLoaderEx<JSONObject>(new GetMainContentsList(getActivity()), getActivity(), callBack, params).execute();
        MainModel.getInstance().authorizeInputLayout(m_squareData.status, m_inputLayout);
    }

    protected void sortData(ArrayList<MainContentsListItemVO> dataList) {
        // 시간 순으로 소팅
        Collections.sort(dataList, m_timebaseComparator);
        // Collections.reverse(dataList);
    }

    private ArrayList<IContentsListItem> addDateSeperator(ArrayList<IContentsListItem> data) {
        ArrayList<IContentsListItem> listData = new ArrayList<IContentsListItem>();
        // 날짜 데이터를 삽입한다.
        // 방향성을 두어 시간이 거꾸로 돌아가는 것을 방지한다.
        // 의견은 예외로 두어 처리하지 않는다.
        // m_addedDataSeperator 에 SystemDate와 SystemNewMessage 때문에 기존 데이터 길이보다
        // 길어지는 부분을 저장한다.
        int nDayOfMonth = 0;
        for (IContentsListItem item : data) {
            switch (item.getObjectType()) {
                case CONTENTS:
                    Calendar c = Calendar.getInstance();
                    c.setTimeInMillis(((MainContentsListItemVO) item).createDate);
                    if (c.get(Calendar.DAY_OF_MONTH) > nDayOfMonth
                            && !SquareContentsType.OPINION.equals(((MainContentsListItemVO) item).contentsType)) {
                        nDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
                        SystemDateVO sd = new SystemDateVO(((MainContentsListItemVO) item).createDate);
                        listData.add(sd);
                    }
                    listData.add(item);
                    break;
                case SYSTEM_DATE:
                    // 기존 Date가 있었으면 무시한다.
                    break;
                case SYSTEM_NEW_MESSAGE_LABEL:
                    Debug.trace("시스템 메시지 추가.");
                    listData.add(item);
                    break;
            }
        }
        return listData;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Nothing to do.
    }

    IContentsListItem currentFirstItemContentId = null;
    private boolean m_bDraw = true;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, final int totalItemCount) {
        if (firstVisibleItem == 0 && !m_lvSquareContents.isRefreshing() && !TextUtils.isEmpty(m_strLastContentsId)
                && !TextUtils.equals(m_strLastContentsId, "00000000")) {
            m_lvSquareContents.setRefreshing();
            View v = m_lvSquareContents.getChildAt(0);
            if (m_data.get(firstVisibleItem) instanceof SystemDateVO) {
                currentFirstItemContentId = m_data.get(firstVisibleItem + 1);
            } else {
                currentFirstItemContentId = m_data.get(firstVisibleItem);
            }
            final int top = (v == null) ? 0 : v.getTop();
            final ArrayList<IContentsListItem> tempDataList = new ArrayList<IContentsListItem>();
            SquareDefaultAjaxCallBack<SquareDefaultVO> callBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(getActivity(),
                    SquareDefaultVO.class) {
                @Override
                public void callback(String url, JSONObject json, AjaxStatus status) {
                    m_lvSquareContents.stopRefreshing();
                    super.callback(url, json, status);
                    if (getVO().responseData != null && getVO().responseData.dataList != null) {
                        ArrayList<MainContentsListItemVO> dataList = new ArrayList<MainContentsListItemVO>();
                        for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
                            dataList.add(new MainContentsListItemVO(getVO().responseData.dataList.optJSONObject(i)));
                        }
                        sortData(dataList);
                        m_strLastContentsId = getVO().responseData.lastViewId;
                        tempDataList.addAll(dataList);
                        m_data.addAll(0, addDateSeperator(tempDataList));
                        deleteDuplecateSystemDate(m_data);
                        m_bDraw = false;
                        m_adapter.notifyDataSetChanged();

                        m_lvSquareContents.post(new Runnable() {
                            @Override
                            public void run() {
                                int nFindItem = -1;
                                for (int i = 0; i < m_data.size(); ++i) {
                                    if (currentFirstItemContentId.equals(m_data.get(i))) {
                                        nFindItem = i;
                                    }
                                }
                                if (nFindItem != -1) {
                                    m_lvSquareContents.getRefreshableView().setSelectionFromTop(nFindItem, top);
                                }
                                m_bDraw = true;
                            }
                        });
                    } else {
                        if (totalItemCount > 0) {
                            m_lvSquareContents.getRefreshableView().setSelection(2);
                            return;
                        }
                    }
                }
            };
            callBack.progress(PopupUtil.getProgressDialog(getActivity()));
            Map<String, String> params = new HashMap<String, String>();
            params.put("squareId", m_squareData.squareId);
            params.put("lastViewContentsId", m_strLastContentsId);
            new ApiLoaderEx<JSONObject>(new GetMainContentsList(getActivity()), getActivity(), callBack, params).execute();
        }
    }

    protected void deleteDuplecateSystemDate(ArrayList<IContentsListItem> data) {
        SystemDateVO d = null;
        ArrayList<SystemDateVO> deleteDateList = new ArrayList<SystemDateVO>();
        for (IContentsListItem item : data) {
            ContentsObjectType objectType = item.getObjectType();
            if (objectType == ContentsObjectType.SYSTEM_DATE) {
                SystemDateVO systemDate = (SystemDateVO) item;
                if (d == null) {
                    d = systemDate;
                    continue;
                }
                if (TextUtils.equals(systemDate.getDateString(), d.getDateString())) {
                    deleteDateList.add(systemDate);
                } else {
                    d = systemDate;
                }
            }
        }
        m_data.removeAll(deleteDateList);
    }

    @Override
    public void onClick(View v) {
		MainModel.getInstance().setNavibarVisible(false); // 20.06.23 tkofs
        switch (v.getId()) {
            case R.id.ID_BTN_BACK:
                getActivity().finish();
                break;
            case R.id.ID_BTN_MENU:
                break;
            case R.id.ID_BTN_MORE:
                PopupMenu popupMenu = new PopupMenu(getActivity(), m_btnMore);
                popupMenu.getMenuInflater().inflate(R.menu.square_contents_list, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        MainModel.getInstance().setNavibarVisible(true); // 20.06.23 tkofs
                        switch (item.getItemId()) {
                            case R.id.ID_MENU_SEARCH:
                                MainModel.getInstance().showSubActivity(
                                        getActivity(),
                                        SubActivityType.SQUARE_SEARCH,
                                        new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_squareData.squareId)
                                                .add(MainModel.ARG_KEY_GROUP_STATUS, m_squareData.status).build());
                                break;
                            case R.id.ID_MENU_FAVORITE:
                                MainModel.getInstance().showSubActivity(
                                        getActivity(),
                                        SubActivityType.SQUARE_FAVORITE_LIST,
                                        new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_squareData.squareId)
                                                .add(MainModel.ARG_KEY_GROUP_STATUS, m_squareData.status).build());
                                break;
                            case R.id.ID_MENU_COMMAND:
                                MainModel.getInstance().showSubActivity(
                                        getActivity(),
                                        SubActivityType.SQUARE_ORDER,
                                        new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_squareData.squareId)
                                                .add(MainModel.ARG_KEY_GROUP_STATUS, m_squareData.status).build());
                                break;
                            case R.id.ID_MENU_FILE:
                                MainModel.getInstance().showSubActivity(
                                        getActivity(),
                                        SubActivityType.SQUARE_FILE,
                                        new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_squareData.squareId)
                                                .add(MainModel.ARG_KEY_GROUP_STATUS, m_squareData.status).build());
                                break;
                            case R.id.ID_MENU_SETTING:
                                // Debug.trace("R.id.ID_MENU_SETTING");
                                MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_SETTING,
                                        new BundleUtils.Builder().add(MainModel.ARG_KEY_SQUARE_ID, m_squareData.squareId).build());
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                break;
            case R.id.ID_BTN_UPLOAD:
                if (m_squareData.status == Status.IN_PROGRESS) {
                    MainModel.getInstance().showUploadDialog(this, m_squareData.squareId, false);
                }
                break;
            case R.id.ID_BTN_SEND:
                Debug.trace("전송");
                if (!TextUtils.isEmpty(m_edMessage.getText().toString().trim()) && m_squareData.status == Status.IN_PROGRESS) {
                    new ApiLoader(new AddMessage(), getActivity(), new AddMessageResult() {
                        @Override
                        public void onResponse(String strRet) {
                            super.onResponse(strRet);
                            if (this.responseHead.resultCode == SUCCESS) {
                                m_edMessage.getText().clear();
                                SquareContentsFragment.this.m_data.add(getData());
                                m_adapter.notifyDataSetChanged();
                                m_lvSquareContents.getRefreshableView().setSelection(m_adapter.getCount() - 1);
                            } else {
                                Debug.trace("전송 실패 팝업");
                            }
                        }
                    }, m_squareData.squareId, m_edMessage.getText().toString().trim()).execute();
                }
                break;
			default:	// tkofs
                getActivity().finish();super.onClick(v);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int nRealPosition = position - m_lvSquareContents.getRefreshableView().getHeaderViewsCount();
        if (m_data.get(nRealPosition) instanceof MainContentsListItemVO) {
            MainContentsListItemVO item = (MainContentsListItemVO) m_data.get(nRealPosition);
            Bundle build = new BundleUtils.Builder().add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO, item)
                    .add(MainModel.ARG_KEY_GROUP_STATUS, m_squareData.status).build();
            switch (item.contentsType) {
                case TOPIC:
                case COMMAND:
                case FILE_UPLOAD:
                case SYSTEM_MESSAGE:
                    if (MainModel.getInstance().isTablet()) {
                        getActivity().findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
                        MainModel.getInstance().showFragmentToTarget(getActivity(), new SquareContentsDetailFragment(), build,
                                R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT);
                    } else {
                        MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_CONTENTS_DETAIL, build);
                    }
                    break;
                default:
                    return;
            }
        }
    }

    @Override
    public void onDelete(final MainContentsListItemVO item) {
        MainModel.getInstance().deleteContents(getActivity(), item, new IContentsDeleteListener() {
            @Override
            public void onDeleteComplete(MainContentsListItemVO item) {
                MainModel.getInstance().notifyChanged(item, ChangeType.DELETE);
            }
        });
    }

    @Override
    public void onCopy(MainContentsListItemVO item) {
        MainModel.getInstance().copyBodyText(getActivity(), item);
    }

    @Override
    public void onModify(MainContentsListItemVO item) {
        MainModel.getInstance().modifyContents(getActivity(), item, new SquareDefaultCallback() {
            @Override
            public void onResponse(String strRet) {
                super.onResponse(strRet);
                MainContentsListItemVO item = new MainContentsListItemVO(getJsonObject().optJSONObject("responseData"));
                MainModel.getInstance().notifyChanged(item, ChangeType.MODIFY);
            }

            @Override
            public void onFailure(String err) {
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainModel.REQ_FROM_SQUARE_CONTENTS_DETAIL) {
            if (resultCode == Activity.RESULT_OK) {
            } else if (resultCode == Activity.RESULT_CANCELED) {
                getActivity().finish();
            }
        } else {
            if (resultCode == Activity.RESULT_OK) {
                MainModel.getInstance().activityResultMediaHandler(getActivity(), requestCode, resultCode, data, this);
            }
        }
    }

    @Override
    public void onLoadCompleteMedia(ResultType t, String strRet) {
        switch (t) {
            case FILE_PATH:
                if (MainModel.getInstance().checkFileSize(strRet) > 0) {
                    PopupUtil.showDialog(getActivity(), R.string.square_file_limit_message);
                    return;
                }
                AddFile api = new AddFile();
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(strRet);
                api.setFilePath(fileList);
                new ApiLoader(api, getActivity(), new SquareDefaultCallback() {
                    @Override
                    public void onResponse(String strRet) {
                        super.onResponse(strRet);
                        if (this.responseHead != null && this.responseHead.resultCode == SUCCESS) {
                            MainContentsListItemVO item = new MainContentsListItemVO(getJsonObject().optJSONObject("responseData"));
                            m_data.add(item);
                            m_adapter.notifyDataSetChanged();
                        }
                    }
                }, m_squareData.squareId).execute();
                break;
        }
    }

    @Override
    public void onItemClick(MainContentsListItemVO item, ButtonType t) {
        Bundle build = new BundleUtils.Builder().add(SquareContentsDetailFragment.ARG_KEY_CONTENTS_INFO, item)
                .add(ARG_KEY_CLICKED_BUTTON_TYPE, t.toString()).add(MainModel.ARG_KEY_GROUP_STATUS, m_squareData.status).build();
        if (MainModel.getInstance().isTablet()) {
            getActivity().findViewById(R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT).setVisibility(View.VISIBLE);
            // MainActivity.viewActionsContentView.findViewById(R.id.ID_LAY_L_WEBVIEW_LAYOUT).setVisibility(View.GONE);
            MainModel.getInstance().showFragmentToTarget(getActivity(), new SquareContentsDetailFragment(), build,
                    R.id.ID_LAY_L_FULLSCREEN_FRAGMENT_LAYOUT);
        } else {
            MainModel.getInstance().showSubActivity(getActivity(), SubActivityType.SQUARE_CONTENTS_DETAIL, build);
        }
    }

    @Override
    public void onFavoriteClick(String squareId, String contentsId, FavoriteContentsType favorityType, boolean flag,
                                SquareDefaultCallback squareDefaultCallback) {
        MainModel.getInstance().addFavoriteContents(getActivity(), squareDefaultCallback, squareId, contentsId, favorityType, flag);
    }

    @Override
    public void onDestroy() {
        MainModel.getInstance().removeContentsChangeListener(this);
        MainModel.getInstance().removeSquarePushListener(this);
        getMainModel().setCurrentSquareId(null);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        ViewModel.Log("SquFrg blue 해제", "bluetooth");
        super.onResume();

        if (mListRefreshNeededWhenResume) {
            refreshList();
        }
    }

    @Override
    public void onChange(ChangeType t, MainContentsListItemVO item) {
        switch (t) {
            case ADD:
                mListRefreshNeededWhenResume = true;
                break;
            case DELETE:
                if (item.contentsType.equals(SquareContentsType.TOPIC) || item.contentsType.equals(SquareContentsType.COMMAND)
                        || item.contentsType.equals(SquareContentsType.FILE_UPLOAD)) {
                    // 부모가 지워져서 자식들도 없애야 하는 경우 orgParentId와, contentsId가 같으면 부모 콘텐츠라고 판단한다.
                    if (item.originalParentId.equals(item.contentsId)) {
                        ArrayList<MainContentsListItemVO> removeList = new ArrayList<MainContentsListItemVO>();
                        for (int i = 0; i < m_data.size(); ++i) {
                            if (m_data.get(i).getObjectType() == ContentsObjectType.CONTENTS) {
                                MainContentsListItemVO mcitem = (MainContentsListItemVO) m_data.get(i);
                                if (mcitem.contentsType.equals(SquareContentsType.OPINION)) {
                                    if (TextUtils.equals(item.originalParentId, item.originalParentId)) {
                                        removeList.add(mcitem);
                                    }
                                }
                            }
                        }
                        m_data.removeAll(removeList);
                    }
                }
                m_data.remove(item);
                m_adapter.notifyDataSetChanged();
                m_adapter.notifyDataSetInvalidated();
                break;
            case MODIFY:
                int nPosition = m_data.lastIndexOf(item);
                if (nPosition != -1) {
                    m_data.remove(nPosition);
                    m_data.add(nPosition, item);
                    m_adapter.notifyDataSetChanged();
                    m_adapter.notifyDataSetInvalidated();
                }
                break;
        }
    }

    @Override
    public void onFileClick(AttachListItemVO item) {
        if (HMGWServiceHelper.doc_transform_server_use) {
            MainModel.getInstance().getOpenApiService().getDocHandlerResult(getActivity(),item);
        }
        else {
            // SEOJAEHWA : 권한체크 추가
            downloadFile(item.contentsId, item.attachId, getTargetFilePath(item.fileName, item.fileExt));
        }
    }

    @Override
    public void onPushReceive(final SquarePushVO vo) {
        if (!isAdded()) {
            return;
        }
        if (TextUtils.equals(vo.square_id, m_squareData.squareId)) {
            if (vo.square_action == SquarePushAction.CONTENTS_ADD || vo.square_action == SquarePushAction.COMMENT_ADD) {
                // 마지막 아이템에 표시중인 데이터의 parent_id를 비교한다.
                IContentsListItem lastItem = m_data.get(m_data.size() - 1);
                if (lastItem.getObjectType() == ContentsObjectType.CONTENTS
                        && TextUtils.equals(((MainContentsListItemVO) lastItem).parentId, vo.square_parent_id)) {
                    SquareDefaultAjaxCallBack<ContentsVO> contentsCallBack = new SquareDefaultAjaxCallBack<ContentsVO>(getActivity(),
                            ContentsVO.class) {
                        public void callback(String url, JSONObject json, AjaxStatus status) {
                            super.callback(url, json, status);
                            MainContentsListItemVO item = new MainContentsListItemVO(getVO().responseData.getJson());
                            m_data.add(item);
                            m_adapter.notifyDataSetChanged();
                        }
                    };
                    Map<String, String> contentsParams = new HashMap<String, String>();
                    contentsParams.put("squareId", vo.square_id);
                    contentsParams.put("contentsId", vo.square_contents_id);
                    new ApiLoaderEx<JSONObject>(new GetContents(getActivity()), getActivity(), contentsCallBack, contentsParams).execute();
                } else {
                    final ArrayList<IContentsListItem> tempDataList = new ArrayList<IContentsListItem>();
                    SquareDefaultAjaxCallBack<SquareDefaultVO> newContentsCallBack = new SquareDefaultAjaxCallBack<SquareDefaultVO>(
                            getActivity(), SquareDefaultVO.class) {
                        public void callback(String url, JSONObject json, AjaxStatus status) {
                            super.callback(url, json, status);
                            if (getVO().responseData != null && getVO().responseData.dataList != null) {
                                ArrayList<MainContentsListItemVO> newContentsDataList = new ArrayList<MainContentsListItemVO>();
                                for (int i = 0; i < getVO().responseData.dataList.length(); ++i) {
                                    MainContentsListItemVO addItem = new MainContentsListItemVO(
                                            getVO().responseData.dataList.optJSONObject(i));
                                    newContentsDataList.add(addItem);
                                }
                                sortData(newContentsDataList);
                                tempDataList.addAll(newContentsDataList);
                            }
                            m_data.addAll(addDateSeperator(tempDataList));
                            deleteDuplecateSystemDate(m_data);
                            m_adapter.notifyDataSetChanged();
                            m_lvSquareContents.getRefreshableView().setSelection(m_adapter.getCount() - 1);
                            m_lvSquareContents.setOnScrollListener(SquareContentsFragment.this);
                        }
                    };
                    Map<String, String> newContentsParams = new HashMap<String, String>();
                    newContentsParams.put("squareId", m_squareData.squareId);
                    new ApiLoaderEx<JSONObject>(new GetNewContentsList(getActivity()), getActivity(), newContentsCallBack,
                            newContentsParams).execute();
                }

            } else if (vo.square_action == SquarePushAction.CONTENTS_DELETE) {
                for (IContentsListItem content : m_data) {
                    if (content.getObjectType() == ContentsObjectType.CONTENTS) {
                        MainContentsListItemVO contentVO = (MainContentsListItemVO) content;
                        if (TextUtils.equals(contentVO.contentsId, vo.square_contents_id)) {
                            if (contentVO.contentsType.equals(SquareContentsType.TOPIC)
                                    || contentVO.contentsType.equals(SquareContentsType.COMMAND)
                                    || contentVO.contentsType.equals(SquareContentsType.FILE_UPLOAD)) {
                                ArrayList<MainContentsListItemVO> removeList = new ArrayList<MainContentsListItemVO>();
                                for (int i = 0; i < m_data.size() && m_data.get(i).getObjectType().equals(ContentsObjectType.CONTENTS); ++i) {
                                    MainContentsListItemVO mcitem = (MainContentsListItemVO) m_data.get(i);
                                    if (mcitem.contentsType.equals(SquareContentsType.OPINION)) {
                                        if (TextUtils.equals(mcitem.originalParentId, contentVO.originalParentId)) {
                                            removeList.add(mcitem);
                                        }
                                    }
                                }
                                m_data.removeAll(removeList);
                                m_adapter.notifyDataSetChanged();
                            } else {
                                m_data.remove(contentVO);
                                m_adapter.notifyDataSetChanged();
                                break;
                            }
                        }

                    }
                }
            } else if (vo.square_action == SquarePushAction.CONTENTS_MODIFY) {
                for (IContentsListItem content : m_data) {
                    if (content.getObjectType() == ContentsObjectType.CONTENTS) {
                        final MainContentsListItemVO contentVO = (MainContentsListItemVO) content;
                        if (TextUtils.equals(contentVO.contentsId, vo.square_contents_id)) {
                            SquareDefaultAjaxCallBack<ContentsVO> contentsCallBack = new SquareDefaultAjaxCallBack<ContentsVO>(
                                    getActivity(), ContentsVO.class) {
                                public void callback(String url, JSONObject json, AjaxStatus status) {
                                    super.callback(url, json, status);
                                    MainContentsListItemVO item = new MainContentsListItemVO(getVO().responseData.getJson());
                                    int n = m_data.indexOf(item);
                                    m_data.remove(item);
                                    m_data.add(n, item);
                                    m_adapter.notifyDataSetChanged();
                                }
                            };
                            Map<String, String> contentsParams = new HashMap<String, String>();
                            contentsParams.put("squareId", vo.square_id);
                            contentsParams.put("contentsId", vo.square_contents_id);
                            new ApiLoaderEx<JSONObject>(new GetContents(getActivity()), getActivity(), contentsCallBack, contentsParams)
                                    .execute();
                            break;
                        }
                    }
                }
            } else if (vo.square_action == SquarePushAction.GROUP_DELETE) {
                // 속해 있던 그룹이 삭제 된 경우
                new AlertDialog.Builder(getActivity()).setTitle(R.string.message_confirm_title)
                        .setMessage(R.string.square_group_deleted_message)
                        .setPositiveButton(R.string.label_confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        }).setCancelable(false).show();
            } else if (vo.square_action == SquarePushAction.GROUP_MODIFY || vo.square_action == SquarePushAction.SQUARE_MEMBER_CHANGE
                    || vo.square_action == SquarePushAction.SQURE_MEMBER_ADMIN_CHANGE) {
                SquareDefaultAjaxCallBack<GroupInfoVO> callback = new SquareDefaultAjaxCallBack<GroupInfoVO>(getActivity(),
                        GroupInfoVO.class) {
                    @Override
                    public void callback(String url, JSONObject json, AjaxStatus status) {
                        super.callback(url, json, status);
                        switch (vo.square_action) {
                            case GROUP_MODIFY:
                                // 그룹 Info가 바뀌었을 때는 타이틀이 바뀌었을 경우도 있으므로 타이틀을 GroupInfo로 부터 받은 정보로 교체해 준다.
                                m_tvTitle.setText(getVO().title);
                            case SQUARE_MEMBER_CHANGE:
                            case SQURE_MEMBER_ADMIN_CHANGE:
                                if (MainModel.getInstance().checkMember(getActivity(), getVO())) {
                                    SquareDefaultAjaxCallBack<ContentsVO> contentsCallBack = new SquareDefaultAjaxCallBack<ContentsVO>(
                                            getActivity(), ContentsVO.class) {
                                        public void callback(String url, JSONObject json, AjaxStatus status) {
                                            super.callback(url, json, status);
                                            MainContentsListItemVO item = new MainContentsListItemVO(getVO().responseData.getJson());
                                            m_data.add(item);
                                            m_adapter.notifyDataSetChanged();
                                        }
                                    };
                                    Map<String, String> contentsParams = new HashMap<String, String>();
                                    contentsParams.put("squareId", vo.square_id);
                                    contentsParams.put("contentsId", vo.square_contents_id);
                                    new ApiLoaderEx<JSONObject>(new GetContents(getActivity()), getActivity(), contentsCallBack, contentsParams)
                                            .execute();
                                }
                                break;
                        }
                    }
                };
                callback.progress(PopupUtil.getProgressDialog(getActivity()));
                MainModel.getInstance().loadGroupInfo(getActivity(), vo.square_id, callback);
            }
        }
    }
}