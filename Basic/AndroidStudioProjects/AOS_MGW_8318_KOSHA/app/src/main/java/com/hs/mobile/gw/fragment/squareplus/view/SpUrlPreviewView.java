package com.hs.mobile.gw.fragment.squareplus.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.adapter.squareplus.ISpClickListener;
import com.hs.mobile.gw.openapi.squareplus.vo.SpOpenGraphVO;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

public class SpUrlPreviewView extends RelativeLayout {

	public SpUrlPreviewView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.sp_urlpreview, this);
	}

	public SpUrlPreviewView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.sp_urlpreview, this);
	}

	public SpUrlPreviewView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.sp_urlpreview, this);
	}

	public void setData(final SpOpenGraphVO item, final ISpClickListener listener) {
		((TextView) findViewById(R.id.ID_TV_PREVIEW_TITLE)).setText(item.getTitle());
		((TextView) findViewById(R.id.ID_TV_PREVIEW_CONTENT)).setText(item.getDescription());
		((TextView) findViewById(R.id.ID_TV_PREVIEW_URL)).setText(item.getDomain());

		ImageView urlThumbnail = (ImageView) findViewById(R.id.ID_IMG_PREVIEW_THUMBNAIL);
		UrlImageViewHelper.setUrlDrawable(urlThumbnail, item.getImage(), R.drawable.sp_no_image, new UrlImageViewCallback() {

			@Override
			public void onLoaded(ImageView imageView, Bitmap loadedDrawable, String url, boolean loadedFromCache) {
				// TODO Auto-generated method stub

				if (loadedDrawable == null) // 아이콘을 로드하지 못한 경우
					return;

				imageView.setImageBitmap(loadedDrawable);
			}
		});
		
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listener.onMoveToUrl(item.getUrl());
			}
		});

	}

}
