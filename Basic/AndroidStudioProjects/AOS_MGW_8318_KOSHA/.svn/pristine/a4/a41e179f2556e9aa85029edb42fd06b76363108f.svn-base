package com.hs.mobile.gw.openapi.square.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SystemDateVO implements IContentsListItem {

	public static final int CONTENTS_TYPE = -1;
	private Date m_date;

	public SystemDateVO(long createDate) {
		m_date = new Date(createDate);
	}

	public String getDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
		return sdf.format(m_date);
	}

	public Date getTime() {
		return m_date;
	}

	@Override
	public ContentsObjectType getObjectType() {
		return ContentsObjectType.SYSTEM_DATE;
	}

}
