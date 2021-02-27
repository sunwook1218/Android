package com.hs.mobile.gw.adapter.squareplus;

import java.util.ArrayList;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.util.Debug;

public class SpImagePagerAdapter extends PagerAdapter {
	Context mContext;
	ArrayList<SpAttachVO> images;
	SpContentsVO item;
	private ISpClickListener m_listener;
	boolean isClickable;
	
	public SpImagePagerAdapter(Context context, SpContentsVO item, ISpClickListener clickListener, boolean isClickable) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.item = item;
		ArrayList<SpAttachVO> images = new ArrayList<SpAttachVO>();
		for (SpAttachVO data : item.getAttachList()) {
			if (TextUtils.equals(data.getAttachType(), "1")) {
				images.add(data);
			}
		}
		this.images = images;
		this.m_listener = clickListener;
		this.isClickable = isClickable;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		Debug.trace("position = ", position);
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.sp_attach_thumbnail_view, null);

		ImageView view = (ImageView) v.findViewById(R.id.ID_IMG_ATTACH_THUMBNAIL);
		view.setScaleType(ScaleType.CENTER_CROP);
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isClickable) {
					m_listener.onOrgImgDown(images.get(position));
				}
				else {
					m_listener.onDetailView(item, false);
				}
			}
		});
		
		if (images.size() > 0) {
			//  호출
			if(images.get(position)!=null){
				if(MainModel.getInstance().getOpenApiService()!=null)
					MainModel.getInstance().getOpenApiService().drawThumbnail(images.get(position).getAttachId(), view, mContext.getResources());
			}
		}

		container.addView(v);
		return v;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return (view == object);
	}
}
