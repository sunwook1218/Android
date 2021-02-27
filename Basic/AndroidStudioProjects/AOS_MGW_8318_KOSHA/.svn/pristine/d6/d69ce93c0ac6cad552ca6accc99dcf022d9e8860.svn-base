package com.hs.mobile.gw.util;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class TextViewUtils {

	public static void hideKeyBoard(Context c, View v) {
		if (c != null && v != null) {
			((InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

	public static String getText(EditText ed) {
		return ed == null ? "" : ed.getText().toString().trim();
	}

	public static void showKeyboard(Context c, View v) {
		if (c != null && v != null) {
			((InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
		}
	}

	public static Spannable removeUnderlines(Spannable p_Text) {
		UnderlineSpan[] spans = p_Text.getSpans(0, p_Text.length(), UnderlineSpan.class);
		for (UnderlineSpan span : spans) {
			p_Text.removeSpan(span);
		}
		return p_Text;
	}
	
	private static void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span)
	{
	    int start = strBuilder.getSpanStart(span);
	    int end = strBuilder.getSpanEnd(span);
	    int flags = strBuilder.getSpanFlags(span);
	    ClickableSpan clickable = new ClickableSpan() {
	        public void onClick(View view) {
	            // Do something with span.getURL() to handle the link click...
	        }
	    };
	    strBuilder.setSpan(clickable, start, end, flags);
	    strBuilder.removeSpan(span);
	}

	// 연구 중..
	public static void setTextViewHTML(TextView text, String html)
	{
	    CharSequence sequence = Html.fromHtml(html);
	    SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
	    URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
//	    String regex = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$";
//        Pattern p = Pattern.compile(regex);
	    for(URLSpan span : urls) {
	        makeLinkClickable(strBuilder, span);
	    }
	    text.setText(strBuilder);
	    text.setMovementMethod(LinkMovementMethod.getInstance());       
	}
}
