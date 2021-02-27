package com.hs.mobile.gw.openapi;

import com.hs.mobile.gw.util.Debug;

public class MGWException extends Exception {

	private GWErrorCode mErrorCode;

	public MGWException(GWErrorCode valueOfCode) {
		mErrorCode = valueOfCode;
	}

	@Override
	public void printStackTrace() {
//		super.printStackTrace();
		Debug.trace("GWError code:", mErrorCode.getCode());
	}

	@Override
	public String getMessage() {
		return "GWError code: "+ mErrorCode.getCode();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
