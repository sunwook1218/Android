package com.hs.mobile.gw.openapi.square.vo;

public class SystemNewMessageLabel implements IContentsListItem {

	public static final int CONTENTS_TYPE = -2;

	@Override
	public ContentsObjectType getObjectType() {
		return ContentsObjectType.SYSTEM_NEW_MESSAGE_LABEL;
	}

}
