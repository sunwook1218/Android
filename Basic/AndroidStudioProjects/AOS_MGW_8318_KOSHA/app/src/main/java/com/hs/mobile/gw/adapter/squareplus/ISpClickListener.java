package com.hs.mobile.gw.adapter.squareplus;

import com.hs.mobile.gw.fragment.squareplus.SpContentsDetailFragment.IFavoriteCallbak;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;

public interface ISpClickListener {

	void onDetailView(SpContentsVO item, boolean showKeyboard);

	void onFileView(SpAttachVO item);

	void onMoveToUrl(String url);

	void onFavoriteClick(SpAttachVO vo, IFavoriteCallbak favoriteCallback);

	void onFavoriteClick(SpContentsVO item, IFavoriteCallbak favoriteCallback);

	void onDeleteClick(SpAttachVO item);

	void onOrgImgDown(SpAttachVO spAttachVO);

	void onFavorClick(SpContentsVO item, int Type);

	void onNoticeView(SpContentsVO item);
}
