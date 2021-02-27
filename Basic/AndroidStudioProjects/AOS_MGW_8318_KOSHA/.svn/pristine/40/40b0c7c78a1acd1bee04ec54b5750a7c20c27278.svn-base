package com.hs.mobile.gw.adapter.squareplus;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.SpAttachVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.ImageHelper;


public class SpWriteThumbnailAdapter extends BaseAdapter {
	
	Context mContext;
	ArrayList<SpAttachVO> images;
	SpContentsVO item;
	IThumbnailClick listener;
	
	public interface IThumbnailClick {
		void onItemClick(SpAttachVO spAttachVO);
	}
	
	public void setClickListener (IThumbnailClick listener) {
		this.listener = listener; 
	}

	
	public SpWriteThumbnailAdapter(Context context, ArrayList<SpAttachVO> images) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.images = images;
	}
	
	public void setData(ArrayList<SpAttachVO> images) {
		this.images = images;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.size();
	}

	@Override
	public SpAttachVO getItem(int position) {
		// TODO Auto-generated method stub
		return images.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ImageView image;
		ImageView delete;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.sp_write_thumbnail_view, null);
			image = (ImageView) convertView.findViewById(R.id.ID_IV_SP_WRITE_THUMBNAIL);
			delete = (ImageView) convertView.findViewById(R.id.ID_IV_SP_THUMBNAIL_DELETE);
			
			convertView.setTag(R.id.ID_IV_SP_WRITE_THUMBNAIL, image);
			convertView.setTag(R.id.ID_IV_SP_THUMBNAIL_DELETE, delete);
		}
		else {
			image = (ImageView) convertView.getTag(R.id.ID_IV_SP_WRITE_THUMBNAIL);
			delete = (ImageView) convertView.getTag(R.id.ID_IV_SP_THUMBNAIL_DELETE);
		}
		
		if (images.get(position).isCreateState()) {
			
			Debug.trace(images.get(position).getTempFilePath());
			Bitmap bm = ImageHelper.resizeBitmap(image.getMeasuredWidth(), image.getMeasuredHeight(), images.get(position).getTempFilePath());
			
			image.setImageBitmap(bm);
			image.setScaleType(ScaleType.CENTER_CROP);
		}
		else {
			if(MainModel.getInstance().getOpenApiService()!=null)
				if(images.get(position)!=null) MainModel.getInstance().getOpenApiService().drawThumbnail(images.get(position).getAttachId(), image, mContext.getResources());
		}
		
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (listener != null)
					listener.onItemClick(images.get(position));
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				images.remove(position);
				notifyDataSetChanged();
			}
		});
		
		return convertView;
	}
	
	
	

}
