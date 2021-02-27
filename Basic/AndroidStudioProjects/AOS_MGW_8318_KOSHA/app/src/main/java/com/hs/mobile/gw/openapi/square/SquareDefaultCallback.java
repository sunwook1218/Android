package com.hs.mobile.gw.openapi.square;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.util.DefaultCallback;
import com.hs.mobile.gw.util.INetworkFailListener;

import android.text.TextUtils;

@Deprecated
public abstract class SquareDefaultCallback extends DefaultCallback {
	public static final int SUCCESS = 0;
	public static final int FAIL = -1;
	public static final int SESSION_EXPIRE = 20;
	public static final int REQUEST_WORNG = 9999;
	private static INetworkFailListener m_networkFailListener;

	public class ResponseHeader {

		public int resultCode = -1;
		public String resultMessage;

		public ResponseHeader(JSONObject json) {
			resultCode = Integer.valueOf(json.optString("resultCode"));
			resultMessage = json.optString("resultMessage");
		}

		public ResponseHeader(int nResultCode, String strResultMessage) {
			resultCode = nResultCode;
			resultMessage = strResultMessage;
		}
	}

	public class ResponseData {
		public int totalCount = 0;
		public int pageSize = 0;
		public int pageNum = 0;
		public String lastViewId = "";
		public JSONArray dataList;

		public ResponseData(JSONObject json) {
			if (json == null) {
				return;
			}
			totalCount = json.optInt("totalCount", 0);
			pageSize = json.optInt("pageSize", 0);
			pageNum = json.optInt("pageNum", 0);
			lastViewId = json.optString("lastViewId");
			dataList = json.optJSONArray("dataList");
		}
	}

	public ResponseData responseData;
	public ResponseHeader responseHead;

	@Override
	public void onResponse(String strRet) {
		super.onResponse(strRet);
		if (getJsonObject() != null) {
			responseHead = new ResponseHeader(getJsonObject().optJSONObject("responseHead"));
			if (responseHead != null && responseHead.resultCode == SUCCESS) {
				responseData = new ResponseData(getJsonObject().optJSONObject("responseData"));
			} else if (responseHead != null) {
				Debug.trace("Error", responseHead.resultCode, responseHead.resultMessage);
				onFailure(responseHead.resultCode + "\n" + responseHead.resultMessage);
				return;
			} else {
				String strError = "";
				// XML Document 객체 생성
				InputSource is = new InputSource(new StringReader(strRet));
				try {
					Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
					// xpath 생성
					XPath xpath = XPathFactory.newInstance().newXPath();
					strError = xpath.evaluate("//error/code", document);
					strError += "\n";
					strError += xpath.evaluate("//error/message", document);
				} catch (SAXException e) {
					Debug.trace(e.getMessage());
				} catch (IOException e) {
					Debug.trace(e.getMessage());
				} catch (ParserConfigurationException e) {
					Debug.trace(e.getMessage());
				} catch (XPathExpressionException e) {
					Debug.trace(e.getMessage());
				}
				onFailure(strError);
				return;
			}
		} else {
			if(!TextUtils.isEmpty(strRet) && strRet.equals("[]\r\n"))
			{
				return;
			}
			String strError = "";
			// XML Document 객체 생성
			InputSource is = new InputSource(new StringReader(strRet));
			try {
				Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
				// xpath 생성
				XPath xpath = XPathFactory.newInstance().newXPath();
				strError = xpath.evaluate("//error/code", document);
				strError += "\n";
				strError += xpath.evaluate("//error/message", document);
			} catch (SAXException e) {
				Debug.trace(e.getMessage());
				strError = strRet;
			} catch (IOException e) {
				Debug.trace(e.getMessage());
				strError = strRet;
			} catch (ParserConfigurationException e) {
				Debug.trace(e.getMessage());
				strError = strRet;
			} catch (XPathExpressionException e) {
				Debug.trace(e.getMessage());
				strError = strRet;
			}
			onFailure(strError);
			return;
		}
	}

	@Override
	public void onFailure(String strErr) {
		super.onFailure(strErr);
		if (m_networkFailListener != null) {
			m_networkFailListener.onNetworkFail(strErr);
		}
	}

	public static void setDefaultNetworkFailListener(INetworkFailListener listener) {
		m_networkFailListener = listener;
	}
}
