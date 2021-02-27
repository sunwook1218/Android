package com.hs.mobile.gw.util;

import java.util.LinkedList;
import java.util.Queue;

import android.content.res.Resources;
import android.widget.ImageView;


public class ImgDownQueue {

	public Queue<Info> imgDownQueue;
	public Info info;
	
	public ImgDownQueue() {
		this.imgDownQueue = new LinkedList<Info>();
	}
	
	public Queue<Info> getQueue() {
		return imgDownQueue;
	}
	
	public void setInfo(String id, ImageView view, Resources res, Type type) {
		info = new Info(id, view, res, type);
	}
	
	public Info getInfo() {
		return info;
	}
	
	public enum Type {
		USER_PHOTO, THUMBNAIL;
	}

	public class Info {

		private String id;
		private ImageView view;
		private Resources res;
		private Type type;

		public Info(String id, ImageView view, Resources res, Type type) {
			this.setId(id);
			this.setView(view);
			this.setRes(res);
			this.setType(type);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public ImageView getView() {
			return view;
		}

		public void setView(ImageView view) {
			this.view = view;
		}

		public Resources getRes() {
			return res;
		}

		public void setRes(Resources res) {
			this.res = res;
		}
		
		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}
	}
}
