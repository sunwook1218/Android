package com.hs.mobile.gw.util;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import android.content.Context;

public class PicassoCache {

	/**
	 * Static Picasso Instance
	 */
	private static Picasso picassoInstance = null;

	/**
	 * Get Singleton Picasso Instance
	 * 
	 * @param context
	 *            application Context
	 * @return Picasso instance
	 */
	public static Picasso getPicassoInstance(Context context) {

		if (picassoInstance == null) {
			Downloader downloader = new OkHttpDownloader(context, Integer.MAX_VALUE);
			Picasso.Builder builder = new Picasso.Builder(context);
			builder.downloader(downloader);
			picassoInstance = builder.build();
			return picassoInstance;
		} else {
			return picassoInstance;
		}
	}

}
