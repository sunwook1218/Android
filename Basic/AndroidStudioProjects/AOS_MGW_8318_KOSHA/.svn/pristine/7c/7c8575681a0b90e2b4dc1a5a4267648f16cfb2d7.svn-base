package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.ISpCompletionViewListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;

public class SpHashTagView extends LinearLayout {
	private List<SpTagVO> tagList = new ArrayList<>();
	private ISpCompletionViewListener mListener;
	private SpCompletionView spCompletionView;

	public SpHashTagView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public SpHashTagView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public SpHashTagView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.sp_hashtag, this);
		spCompletionView = (SpCompletionView) findViewById(R.id.ID_COMPLETION_VIEW);
	}

	public void setInputMode(boolean b) {
		spCompletionView.setTagAddButton(b);
		spCompletionView.setData("", mListener);
	}

	public void setData(SpContentsVO item, ISpCompletionViewListener listener) {
		mListener = listener;
		spCompletionView.setFontColor(0xFF7086D3);
		spCompletionView.setBackgroundColor(0xFFF3F3F3);
		String tag=null;
		if(item.getTagList()!=null) tag = tagListToString(item.getTagList());
		spCompletionView.setData(tag, mListener);
	}

	public void setListener(ISpCompletionViewListener listener) {
		mListener = listener;
	}

	private String tagListToString(List<SpTagVO> tagList) {
		StringBuilder sb = new StringBuilder();
		for (int i = tagList.size() - 1; i >= 0; --i) {
			sb.append("#{");
			sb.append(tagList.get(i).getTagName());
			sb.append("} ");
		}
		return sb.toString();
	}

	public void appendTag(SpTagVO tagVO) {
		for (SpTagVO tag : tagList) {
			if (TextUtils.equals(tag.getTagName(), tagVO.getTagName())) {
				return;
			}
		}
		spCompletionView.setFontColor(0xFF7086D3);
		spCompletionView.setBackgroundColor(0xFFF3F3F3);
		tagList.add(tagVO);
		Collections.reverse(tagList);
		spCompletionView.setData(tagListToString(tagList), mListener);
	}

	public void removeTag(String tagString) {
		SpTagVO removeTag = null;
		for (SpTagVO tag : tagList) {
			if (TextUtils.equals(tag.getTagName(), tagString)) {
				removeTag = tag;
			}
		}
		if (removeTag != null) {
			tagList.remove(removeTag);
			spCompletionView.setData(tagListToString(tagList), mListener);
		}
	}
	
	public boolean isTagEmpty() {
		if (tagList != null && tagList.size() > 0) {
			return false;
		}
		return true;
	}
}
