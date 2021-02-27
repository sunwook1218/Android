package com.hs.mobile.gw.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hs.mobile.gw.ext.ViewModel;
import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.MainFragment;
import com.hs.mobile.gw.service.HMGWServiceHelper;
import com.hs.mobile.gw.service.OpenAPIService.ApiCode;
import com.hs.mobile.gw.util.DateUtils;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.OnScrollListView;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class BoardAdapter extends MGWBaseAdapter {

    public String folderId = null;
    public String brdType = null;
    public String deptId = "";
    int folderLayout;
    int memoLayout;
    int boardHeaderLayout;
    int boardMemoHeaderLayout;
    int folderCount = 0;
    boolean getSubFolders = true;

    public class DataForWrite {

        public String folderId;
        public String title;
        public boolean ANONBOARD;
        public String brdType;
        public String deptId;

    }

    DataForWrite dataForWrite = new DataForWrite();

    private String openLink;

    private String count;
	
    public BoardAdapter(MainFragment f, String folderId, ApiCode apiCode, boolean getSubFolders, boolean isSearch, String brdType) {
        super(f);
        this.folderLayout = R.layout.template_boardlist;
        this.memoLayout = R.layout.template_board_memolist;
        this.boardHeaderLayout = R.layout.template_board_header;
        this.boardMemoHeaderLayout = R.layout.template_board_memolist_header;
        this.apiCode = apiCode;
        this.currentPno = initPno = 1;
        this.mSrcArray = null;
        this.folderId = folderId;
        this.mAdapter = this;
        this.getSubFolders = getSubFolders;
        this.isSearchMode = isSearch;
        this.brdType = brdType;

        if (setLoadingInProgress(true)) {
            initData();
        }
    }

    public BoardAdapter(MainFragment f, String folderId, ApiCode apiCode, boolean getSubFolders, String openLink, String count, boolean isSearch, String brdType) {
        super(f);
        this.folderLayout = R.layout.template_boardlist;
        this.memoLayout = R.layout.template_board_memolist;
        this.boardHeaderLayout = R.layout.template_board_header;
        this.boardMemoHeaderLayout = R.layout.template_board_memolist_header;
        this.apiCode = apiCode;
        this.currentPno = initPno = 1;
        this.mSrcArray = null;
        this.folderId = folderId;
        this.mAdapter = this;
        this.getSubFolders = getSubFolders;
        this.openLink = openLink;
        this.count = count;
        this.isSearchMode = isSearch;
        this.brdType = brdType;

        if (setLoadingInProgress(true)) {
            initData();
        }
    }
	
	public BoardAdapter(MainFragment f, String folderId, ApiCode apiCode, boolean getSubFolders, boolean isSearch, String brdType, String deptId) {
        super(f);
        this.folderLayout = R.layout.template_boardlist;
        this.memoLayout = R.layout.template_board_memolist;
        this.boardHeaderLayout = R.layout.template_board_header;
        this.boardMemoHeaderLayout = R.layout.template_board_memolist_header;
        this.apiCode = apiCode;
        this.currentPno = initPno = 1;
        this.mSrcArray = null;
        this.folderId = folderId;
        this.mAdapter = this;
        this.getSubFolders = getSubFolders;
        this.isSearchMode = isSearch;
        this.brdType = brdType;
        this.deptId = deptId;

        if (setLoadingInProgress(true)) {
            initData();
        }
    }
    
    public void setParameters(List<NameValuePair> params) {
		this.extraParams = params;
	}
    
    public void showSearchBoard(String folderId) {
		getController().showSearchBoard(folderId);
	}

    /*
     * 레이아웃 타입이 여러개인경우 아래의 getViewTypeCount(), getItemViewType() 를 Override
     * 해야한다. 내부적으로 어떻게 동작하는지 모르겠지만...
     */
    int mLayoutCnt = 0;

    @Override
    public int getViewTypeCount() {
        return mLayoutCnt;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mLayoutCnt) {
            case 1: { // 데이터 없음 | 최근게시물 | 즐겨찾기 게시판
                return 0;
            }
            case 2: { // 게시판이나 게시물만 있음
                if (position == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
            case 4: {// 게시판 게시물 둘다 있음
                if (position == 0) {
                    return 0;
                } else if (position > 0 && position < (folderCount + 1)) {
                    return 1;
                } else if (position == (folderCount + 1)) {
                    return 2;
                } else {
                    return 3;
                }
            }
            default: {
                return 0;
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
   		if(apiCode != ApiCode.dept_board_folders) {
			showSearchBoard(folderId);
		}
    	
        JSONObject item = mSrcArray.get(position);

            if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
                convertView = LayoutInflater.from(parent.getContext()).inflate(emptyLayout, parent, false);
                return convertView;
            } else if (item.optBoolean("isBoardFolder")) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(folderLayout, parent, false);
            } else if (item.optBoolean("isDeptBoardFolder")) {
            	convertView = LayoutInflater.from(parent.getContext()).inflate(folderLayout, parent, false);
        	} else if (item.optBoolean("isBoardListHeader")) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(boardHeaderLayout , parent, false);
            } else if (item.optBoolean("isMemoListHeader")) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(boardMemoHeaderLayout, parent, false);
            } else {
                convertView = LayoutInflater.from(parent.getContext()).inflate(memoLayout, parent, false);
            }
//            }

//            if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
//            	convertView = LayoutInflater.from(parent.getContext()).inflate(emptyLayout, parent, false);
//                return convertView;
//            }

        if (item.optBoolean("isBoardFolder")) {
            TextView titleView = (TextView) convertView.findViewById(R.id.boardfolder_title);
            String title = item.optString("@text").trim();
            titleView.setText(title);
        } else if (item.optBoolean("isDeptBoardFolder")) {
            TextView titleView = (TextView) convertView.findViewById(R.id.boardfolder_title);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.boardfolder_img);
            MarginLayoutParams marginParams = (MarginLayoutParams) imageView.getLayoutParams();
            marginParams.setMargins(60 * Integer.parseInt(item.optString("@depth")),0,0,0);
            String title = item.optString("@text").trim();
            titleView.setText(title);
        } else if (item.optBoolean("isBoardListHeader") || item.optBoolean("isMemoListHeader")) {
            // nothing to do
        } else {
            ImageView statusView = (ImageView) convertView.findViewById(R.id.board_memo_status);
            ImageView emergencyView = (ImageView) convertView.findViewById(R.id.board_memo_emergency);
            ImageView modifiedView = (ImageView) convertView.findViewById(R.id.board_memo_modified);
            ImageView attachView = (ImageView) convertView.findViewById(R.id.board_memo_attach);
            TextView titleView = (TextView) convertView.findViewById(R.id.board_memo_title);
            TextView brdnameView = (TextView) convertView.findViewById(R.id.extrainfo_brdname);
            TextView authorView = (TextView) convertView.findViewById(R.id.extrainfo_author);
            TextView pubdateView = (TextView) convertView.findViewById(R.id.extrainfo_pubdate);
            TextView cmntcntView = (TextView) convertView.findViewById(R.id.extrainfo_cmntcnt);

            String title = item.optString("title").trim();
            String brdname = item.optString("brdname").trim();
            String author = item.optString("author").trim();
            String pubDate = item.optString("pubDate").trim();
            int commentcount = item.optInt("commentcount");
            String statusString = item.optString("status");



            brdnameView.setText(brdname);
            authorView.setText(author);
            pubdateView.setText(DateUtils.getDateStringFromGWDate(pubDate));

            if (commentcount > 0) {
                cmntcntView.setText("[" + commentcount + "]");
            } else {
                cmntcntView.setText("");
            }
            /*
             * statusString : /img/bbs/BR0AEM.GIF B : Board의 준말 접두어 R0 : 오늘
			 * 게시되고 안 읽은 게시물 R1 : 최근 게시물중 안 읽은 게시물 R2 : 최근 게시물중 읽은 게시물 R3 :
			 * 오래된 게시물(오래된 게시물은 읽음 유무를 구분하지 않음) A : 게시물에 첨부가 있음 E : 긴급 게시물 M
			 * : 수정 이력이 있는 게시물
			 */

            statusString = Uri.parse(statusString).getLastPathSegment();
            if(statusString != null){
                statusString = statusString.substring(0, statusString.lastIndexOf("."));
            }else{
                statusString = "";
            }
/*            if(statusString.contains("ANN")){
                ViewModel.Log(statusString, "tkofs_ann2");
            }*/
            // tkofs 안읽은 게시물 bold 처리
            if (HMGWServiceHelper.list_escape_title_use) {
                titleView.setText(Html.fromHtml(title));
                if (statusString.contains("R0")) {
                    titleView.setTypeface(null, Typeface.BOLD);
                }else{
                    titleView.setTypeface(null, Typeface.NORMAL);
                }
            }
            else {
                titleView.setText(title);
                if (statusString.contains("R0")) {
                    titleView.setTypeface(null, Typeface.BOLD);
                }else{
                    titleView.setTypeface(null, Typeface.NORMAL);
                }
            }

            if (statusString.length() > 3) {
                boolean attach = statusString.contains("A");
                boolean emergency = statusString.contains("E");
                boolean modified = statusString.contains("M");

                // 첨부 표시 아이콘
                if (attach) {
                    attachView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ic_mail_attach));
                } else {
                    attachView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ic_mail_noattach));
                }
                // 긴급 아이콘
                if (emergency) {
                    emergencyView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.icon_add_e));
                } else {
                    emergencyView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
                }
                // 수정 아이콘
                if (modified) {
                    modifiedView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.icon_add_m));
                } else {
                    modifiedView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
                }
            } else {
                attachView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ic_mail_noattach));
                emergencyView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
                modifiedView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(R.drawable.ico_dummy));
            }

            int imageResourceID;
            if (statusString.contains("R0")) {
                imageResourceID = R.drawable.icon_board_r0;
            } else if (statusString.contains("R1")) {
                imageResourceID = R.drawable.icon_board_r1;
            } else if (statusString.contains("R2")) {
                imageResourceID = R.drawable.icon_board_r2;
            } else if (statusString.contains("ANN")) {
                imageResourceID = R.drawable.icon_board_ann;
            } else {
                imageResourceID = R.drawable.icon_board_r3;
            }
            statusView.setImageDrawable((BitmapDrawable) getMainFragment().getResources().getDrawable(imageResourceID));

            if (selectedItemPosition != -1 && position == (selectedItemPosition - 1)) {
                convertView.setBackgroundResource(R.drawable.default_list_selected);
            } else {
                convertView.setBackgroundResource(R.drawable.style_list_background);
            }
        }

        return convertView;
    }

    @Override
    public void initData() {
        mLayoutCnt = 0;
        isFirstPage = true;
        isShowFirstRow = true;
        currentPno = initPno;
        maxPno = -1;
        mSrcArray = null;
        mSrcArray = new ArrayList<JSONObject>();
        loadData();
    }

    @Override
    public void loadData() {
        new LoadFolderDataTask().execute();
    }

    private class LoadFolderDataTask extends AsyncTask<String, String, String> {

        private LoadFolderDataTask() {
            super();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mAdapter.setLoadingFinished();
            if (!TextUtils.isEmpty(openLink) && !TextUtils.isEmpty(count)) {
                // 게시글 바로 가기
                for (int i = 0; i < mSrcArray.size(); ++i) {
                    JSONObject jobj = mSrcArray.get(i);
                    if (TextUtils.equals(jobj.optString("link").trim(), openLink)) {
                        showItem(i + 1);
                        break;
                    }
                }
            }
            openLink=null;
        }

        // 게시판(폴더)와 게시글(메모)를 한번에 가져오기
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... param) {
            try {
                JSONArray folderData = null;
                mSrcTmpArray = new ArrayList<JSONObject>();
                if (isFirstPage && apiCode != ApiCode.board_recent_memolist && getSubFolders && !isSearchMode) { // 첫페이지
                    // 이고,
                    // 최근게시물이
                    // 아니고,
                    // 하위폴더
                    // 가져오기이면
                    // 폴더
                	if(MainModel.getInstance().getOpenApiService()==null) return null;
                	
                    if (apiCode == ApiCode.board_favfolder) {
                    	if(MainModel.getInstance().getOpenApiService().getBoardFavorFolders(getActivity())!=null){
                    		folderData = MainModel.getInstance().getOpenApiService().getBoardFavorFolders(getActivity());
                    	}
                    } else if (apiCode == ApiCode.board_folder) {
                    	if(MainModel.getInstance().getOpenApiService().getBoardFolders(getActivity(), folderId)!=null){
                    		folderData = MainModel.getInstance().getOpenApiService().getBoardFolders(getActivity(), folderId);
                    	}
                    } else if (apiCode == ApiCode.dept_board_folders) {
                    	if(MainModel.getInstance().getOpenApiService().getDeptBoardFolderList(getActivity(), folderId)!=null){
                    		folderData = MainModel.getInstance().getOpenApiService().getDeptBoardFolderList(getActivity(), folderId);
                    	}
                    } 
                    if (folderData != null) {
                        folderCount = folderData.length();
                        if (folderCount != 0) { // 게시판 헤더: [하위게시판]
                            if (apiCode == ApiCode.board_favfolder) {
                                mLayoutCnt++;
                            } else {
                                JSONObject header = new JSONObject();
                                header.put("isBoardListHeader", true);
                                mSrcArray.add(header);
                                mLayoutCnt += 2;
                            }
                        }
                        if (apiCode == ApiCode.dept_board_folders) {
	                        for (int i = 0; i < folderData.length(); i++) {
	                            JSONObject item = (JSONObject) folderData.get(i);
	                            item.put("isDeptBoardFolder", true);
	                            mSrcArray.add(item);
	                        }                        	
                        }else {
	                        for (int i = 0; i < folderData.length(); i++) {
	                            JSONObject item = (JSONObject) folderData.get(i);
	                            item.put("isBoardFolder", true);
	                            mSrcArray.add(item);
	                        }
                        }
                    }
                }
                JSONObject memoData = null;
                { // 메모
                    ViewModel.Log(apiCode, "tkofs_bbs");
                    ViewModel.Log(currentPno, "tkofs_bbs");
                    ViewModel.Log(folderId==null?"":folderId, "tkofs_bbs");
                    ViewModel.Log(deptId==null?"":deptId, "tkofs_bbs");
                	if (isSearchMode) {
                		if(MainModel.getInstance().getOpenApiService()!=null){
                			memoData = MainModel.getInstance().getOpenApiService().getSearchBoardList(getActivity(), currentPno, folderId, extraParams);
                		}
                	} else if (apiCode == ApiCode.board_recent_memolist) {// 최근게시물이면
                		if(MainModel.getInstance().getOpenApiService()!=null){
                			memoData = MainModel.getInstance().getOpenApiService().getBoardMemoList(getActivity(), ApiCode.board_recent_memolist, currentPno, folderId, brdType, deptId);
                		}
                    } else if (apiCode == ApiCode.board_noticelist) {
                    	if(MainModel.getInstance().getOpenApiService()!=null){
                    		memoData = MainModel.getInstance().getOpenApiService().getBoardMemoList(getActivity(), ApiCode.board_noticelist, currentPno, folderId, brdType, deptId);
                        }
                    } else if (apiCode == ApiCode.dept_board_memolist) {
                    	if(MainModel.getInstance().getOpenApiService()!=null){
                    		memoData = MainModel.getInstance().getOpenApiService().getBoardMemoList(getActivity(), ApiCode.dept_board_memolist, currentPno, folderId, brdType, deptId);
//                    		memoData = MainModel.getInstance().getOpenApiService().getDeptBoardMemoList(getActivity(), ApiCode.dept_board_memolist, currentPno, folderId, deptId);
                        }
                    } else if (apiCode == ApiCode.dept_board_folders) {
                    	memoData = null;
                    } else {
                    	if(MainModel.getInstance().getOpenApiService()!=null){
                    		memoData = MainModel.getInstance().getOpenApiService().getBoardMemoList(getActivity(), ApiCode.board_memolist, currentPno, folderId, brdType, deptId);
                    	}
                    }
                    if (memoData != null) {
                        ViewModel.Log(memoData, "tkofs_bbs");
                        if (currentPno == initPno) {
                            total = memoData.getJSONObject("channel").getInt("total");
                            pageSize = memoData.getJSONObject("channel").getInt("pagesize");
                            currentPno = memoData.getJSONObject("channel").getInt("pageno");
                        }
                        // 쓰기 권한이 있으면 버튼 세팅
                        if (memoData.getJSONObject("channel").optBoolean("auth_write")
                                && memoData.getJSONObject("channel").optBoolean("auth_read")) {
                            dataForWrite = new DataForWrite();
                            dataForWrite.folderId = folderId;
                            dataForWrite.brdType= brdType;
                            dataForWrite.deptId = deptId;
                            dataForWrite.title = memoData.getJSONObject("channel").optString("title");
                            // 암호 처리 플래그 처리
                            dataForWrite.ANONBOARD = TextUtils.equals(memoData.getJSONObject("channel").optString("ANONBOARD"), "true");
                        } else {
                            dataForWrite = null;
                        }

                        updateOtherViews();

                        if (total != 0) {
                            if (currentPno == initPno) { // 게시물 헤더: [게시물]
                                if (apiCode == ApiCode.board_recent_memolist) {
                                    mLayoutCnt++;
                                } else {
                                    JSONObject header = new JSONObject();
                                    header.put("isMemoListHeader", true);
                                    mSrcArray.add(header);
                                    mLayoutCnt += 2;
                                }
                            }

                            JSONArray jArray = memoData.getJSONObject("channel").optJSONArray("item");
                            if (jArray == null) {
                                JSONObject item = memoData.getJSONObject("channel").optJSONObject("item");
                                mSrcTmpArray.add(item);
                            } else {
                                for (int i = 0; i < jArray.length(); i++) {
                                    mSrcTmpArray.add((JSONObject) jArray.get(i));
                                }
                            }
                        }
                    }

                    if (mSrcTmpArray.size() > 0) {
                        mSrcArray.addAll(mSrcTmpArray);
                    }

                    if (folderCount == 0 && total == 0) {// 데이터가 없으면..
                        mLayoutCnt++;
                        setForEmptyList();
                    }
                }

                // 가져온 데이터가 있고 리스트가 비었으면
                if ((folderData != null || memoData != null) && listView == null) {
                    listView = new OnScrollListView(getMainFragment().getActivity());
                    listView.setAdapter(mAdapter);
                    setListView();
                    addListViewToMiddleFlipper();
                } else {
                    mAdapter.updateListview();
                }

                if (isShowFirstRow) {
                    showFirstRow();
                    isShowFirstRow = false;
                }

            } catch (JSONException e) {
            	Debug.trace(e.getMessage());
            }
            return null;
        }
    }

    @Override
    public void getMoreData() {
        currentPno++;
        loadData();
    }

    @Override
    public boolean hasMoreData() {
        if (maxPno == -1 && total != 0 && pageSize != 0) {
            maxPno = (int) Math.ceil((double) total / (double) pageSize);
        }
        if (currentPno == maxPno && total > pageSize) {
            notiLastPage();
        } else {
            notifiedLastPage = false;
        }
        return currentPno < maxPno;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!isRefreshing()) {
            showItem(position);
        }
    }

    @Override
    public void showFirstRow() {
		/*
		 * 게시물이 있고 최근게시물 이면 1 아니면 folderCount
		 */
        if (MainModel.getInstance().isTablet() && total > 0 && !MainModel.getInstance().isPopupMode()) {
            int position;
            if (apiCode == ApiCode.board_recent_memolist) {
                position = 1;
            } else {
                position = (folderCount == 0 ? 2 : folderCount + 3);
            }
            showItem(position);
        }
    }

    @Override
    public void showItem(int position) {
        selectedItemPosition = position;
        JSONObject item = (JSONObject) getItem(selectedItemPosition);
        if (item.optBoolean("isEmptyList")) {// 빈 리스트이면
            return;
        }

        if (item.optBoolean("isBoardFolder")) {
        	String brdType = item.optString("@brdType").trim();
            String title = item.optString("@text").trim();
            String boardId = item.optString("@id");
            boolean getSubFolders = true;
			
			if(brdType.equals("7")) {
            	getController().showDeptFolders(boardId, title, getSubFolders, brdType);
            	return;
            }
			
            if (apiCode == ApiCode.board_favfolder) {
                getSubFolders = false;
            }
            getController().showBoardFolders(boardId, title, getSubFolders, brdType);
        } else if (item.optBoolean("isDeptBoardFolder")) {
        	
            String title = item.optString("@text").trim();
            String deptId = item.optString("@id");
            String boardId = item.optString("@brdid");
            boolean getSubFolders = false;
           
            getController().showDeptBoardMemoList(boardId, deptId, title, getSubFolders, "7");
            
        } else if (item.optBoolean("isBoardListHeader") || item.optBoolean("isMemoListHeader")) {
            // nothing to do
        } else {
            String author = item.optString("author");
            int cmntCnt = item.optInt("commentcount");
            // Hide the tool-bar beforehand, if the tool-bar will be hidden
            // after calling web-view.
            if (!TextUtils.equals(author, HMGWServiceHelper.uname) && cmntCnt == 0) {
                MainFragment.getController().hideWebviewToolbar();
            }
            String link = item.optString("link").trim();
            getController().showBoardMemoDetails(link, cmntCnt, this.deptId);
            setBoardItemToRead();
        }
        mAdapter.updateListview();
    }
    
    public void setBoardItemToRead(JSONObject item) {
		String status = item.optString("status");
		if (TextUtils.isEmpty(status)) {
            return;
        }
		status = Uri.parse(status).getLastPathSegment();
		status = status.substring(0, status.lastIndexOf("."));

		if (status.indexOf("R0") > -1) {
			try {
				item.putOpt("status", item.optString("status").replace("R0", "R2"));
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			}
		}
		else if (status.indexOf("R1") > -1) {
			try {
				item.putOpt("status", item.optString("status").replace("R1", "R2"));
			} catch (JSONException e) {
				Debug.trace(e.getMessage());
			}
		}
	}

	public void setBoardItemToRead() {
		JSONObject item = (JSONObject) getItem(selectedItemPosition);
		setBoardItemToRead(item);
		mAdapter.updateListview();
	}

    @Override
    public void updateOtherViews() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dataForWrite == null) {
                    getController().hideWriteButton();
                    getController().setRefreshForListFooter();
                } else {
                    getController().setWriteButtonForBoard(dataForWrite);
                }
            }
        });
    }

    @Override
    public void deleteItemByID(String id) {
        int nextShowIndex = -1;
        int arraySize = mSrcArray.size();
        for (int i = 0; i < arraySize; i++) {
            JSONObject item = mSrcArray.get(i);
            if (TextUtils.equals(id, item.optString("guid"))) {
                if (arraySize > (i + 1)) { // 다음 목록이 더 있으면
                    nextShowIndex = i;
                } else {
                    if (arraySize == 1) { // 마지막 목록이었으면
                        nextShowIndex = -1;// 없음
                    } else {
                        nextShowIndex = i - 1;
                    }
                }
                mSrcArray.remove(i);
                mAdapter.updateListview();
                if (nextShowIndex == -1) {
                    showEmptyPage();
                } else {
                    JSONObject nextItem = mSrcArray.get(nextShowIndex);
                    String link = nextItem.optString("link").trim();
                    int cmntCnt = item.optInt("commentcount");
                    getController().showBoardMemoDetails(link, cmntCnt, this.deptId);
                    setBoardItemToRead();
                }
                break;
            }
        }
    }
}
