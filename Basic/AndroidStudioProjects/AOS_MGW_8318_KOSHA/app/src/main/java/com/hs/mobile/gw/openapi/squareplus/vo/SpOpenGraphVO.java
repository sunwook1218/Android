package com.hs.mobile.gw.openapi.squareplus.vo;

import java.io.BufferedInputStream;
import java.io.Serializable;

import org.json.JSONObject;

import com.hs.mobile.gw.openapi.vo.DefaultVO;

public class SpOpenGraphVO extends DefaultVO<JSONObject> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String openGraphId;		// OG ID
	private String contentsId;		// 연결된 ContentsId

	private String domain;			// 사용하는 도메인명
	private String url;				// 사용하는 URL
	private String html;			// html 태그
	private String title;			// 사이트 제목
	private String image;			// Image URL
	private String description;		// 설명

	private BufferedInputStream imageInputStream;

	public SpOpenGraphVO() {
		super(null);
	}

	public SpOpenGraphVO(JSONObject json) {
		super(json);
	}

	public String getOpenGraphId() {
		return openGraphId;
	}

	public void setOpenGraphId(String openGraphId) {
		this.openGraphId = openGraphId;
	}

	public String getContentsId() {
		return contentsId;
	}

	public void setContentsId(String contentsId) {
		this.contentsId = contentsId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public BufferedInputStream getImageInputStream() {
		return imageInputStream;
	}

	public void setImageInputStream(BufferedInputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}

	public boolean hasImage() {
		if (image == null || "".equals(image)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String toString() {
		return "{" + domain + ", " + title + "}";
	}
}
