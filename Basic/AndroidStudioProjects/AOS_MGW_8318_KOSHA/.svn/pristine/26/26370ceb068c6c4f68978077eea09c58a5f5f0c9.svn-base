package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.hs.mobile.gw.hsuco.R;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpSquareMemberVO;

public class SpCompletionView extends TextView {
	public interface ISpCompletionViewListener {

		void onMentionClick(SpContentsMentionVO item);

		void onTagClick(String replace);

		void onUserClick(String itemId);

		void onBtnTagAddClick();
	}

	private ISpCompletionViewListener listener;

	public enum SpanType {
		MENTION, TAG, USER, DEPT, SQUARE
	}
	
	@Override
	public void scrollTo(int x, int y) {
		// TODO Auto-generated method stub
	}

	public class SpanableText {
		private SpanType type;

		public SpanableText(String t, int s, int e, SpanType st) {
			strText = t;
			start = s;
			end = e;
			type = st;
		}

		public String strText;
		public int start;
		public int end;
	}

	public static final String MENTION_REGEX = "@[{]u(\\d+)[}]"; // 멘션 정규식
	public static final String TAG_REGEX = "#[{](.*?)[}]"; // 태그 정규식
	// 시스템 메시지 REGEX
	public static final String DEPT_REGEX = "%[{]d(\\d+)[}]"; // 부서 정규식
	public static final String USER_REGEX = "%[{]u(\\d+)[}]"; // 사용자 정규식
	public static final String SQUARE_REGEX = "%[{]g(.*?)[}]"; // 그룹 정규식
	private SpContentsVO data;
	private boolean tagEnable = true;
	private int fontColor = 0xFF7086D3;
	private int backgroundColor = -1;
	private boolean tagAddButton = false;

	public SpCompletionView(Context context) {
		super(context);
		setMovementMethod(LinkMovementMethod.getInstance());
	}

	public SpCompletionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setMovementMethod(LinkMovementMethod.getInstance());
	}

	public SpCompletionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		if (text == null) {
			return;
		}

		Pattern p = Pattern.compile(MENTION_REGEX);
		Matcher m = p.matcher(text);
		SpannableString str = new SpannableString(text);
		ArrayList<SpanableText> arr = new ArrayList<>();
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.MENTION));
		}

		if (tagEnable) {
			p = Pattern.compile(TAG_REGEX);
			m = p.matcher(text);
			while (m.find()) {
				arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.TAG));
			}
		}

		p = Pattern.compile(USER_REGEX);
		m = p.matcher(text);
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.USER));
		}

		p = Pattern.compile(DEPT_REGEX);
		m = p.matcher(text);
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.DEPT));
		}

		p = Pattern.compile(SQUARE_REGEX);
		m = p.matcher(text);
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.SQUARE));
		}

		if (arr != null) {
			Ascending asc = new Ascending();
			Collections.sort(arr, asc);

			for (SpanableText st : arr) {
				str.setSpan(new ForegroundColorSpan(fontColor), st.start, st.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				if (st.type == SpanType.TAG) {
					if (backgroundColor != -1) {
						str.setSpan(new BackgroundColorSpan(backgroundColor), st.start, st.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					}
				}
			}
		}

		Spannable replaceText = replaceText(str, arr);
		SpannableStringBuilder ssb = new SpannableStringBuilder(replaceText);
		if (tagAddButton) {
			Drawable d = getResources().getDrawable(R.drawable.btn_plus);
			d.setBounds(0, 0, d.getIntrinsicWidth() / 3, d.getIntrinsicHeight() / 3);
			ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
			ClickableSpan cs = new ClickableSpan() {

				@Override
				public void onClick(View widget) {
					if (listener != null)
						listener.onBtnTagAddClick();
				}
			};
			SpannableString ss = new SpannableString("add");
			ss.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			ss.setSpan(cs, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			ssb.append(ss);
		}
		super.setText(ssb, BufferType.SPANNABLE);
	}
	
	private class Ascending implements Comparator<SpanableText> {
	    @Override
	    public int compare(SpanableText o1, SpanableText o2) {
	        return Integer.valueOf(o1.start).compareTo(Integer.valueOf(o2.start));
	    }
	}

	private Spannable replaceText(Spannable text, ArrayList<SpanableText> arr) {
		SpannableStringBuilder ssb = new SpannableStringBuilder(text);
		ForegroundColorSpan[] spans = ssb.getSpans(0, text.length(),
				ForegroundColorSpan.class);
		
		for (int i = 0; i < spans.length; ++i) {
			final SpanableText st = arr.get(i);
			ssb.setSpan(new ClickableSpan() {
				@Override
				public void onClick(View widget) {
					if (listener == null) {
						return;
					}
					switch (st.type) {
					case MENTION:
//						for (SpContentsMentionVO item : data.getMentionList()) {
//							if (st.strText.contains(item.getItemId())) {
//								Debug.trace(item.getItemId(), item.getItemName(), item.getItemText());
//								listener.onUserClick(item.getItemId());
//							}
//						}
						listener.onUserClick(st.strText);
						break;
					case TAG: {
						listener.onTagClick(st.strText);
					}
						break;
					case USER:
						listener.onUserClick(st.strText);
						break;
					}
				}

				@Override
				public void updateDrawState(TextPaint ds) {
					// super.updateDrawState(ds);
					ds.setUnderlineText(false);
				}
			}, ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			switch (st.type) {
			case MENTION:
				ssb.replace(ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]), findMentionText(arr.get(i)));
				break;
			case TAG: {
				String replace = "#" +st.strText;
				ssb.replace(ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]), replace);
			}
				break;
			case USER:
			case DEPT: {
				String replace = st.strText;
				for (SpSquareMemberVO member : data.getSystemMessageMemberList()) {
					if (TextUtils.equals(member.getMemberId(), replace)) {
						if (!TextUtils.isEmpty(member.getMemberName()))
							replace = member.getMemberName();
						else
							replace = "";
						break;
					}
				}
				ssb.replace(ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]), replace);
			}
				break;
			case SQUARE:
				String replace = st.strText;
				ssb.replace(ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]), replace);

				break;
			}
		}
		return ssb;
	}

	class MyClickableSpan extends ClickableSpan {// extend ClickableSpan

		String clicked;

		public MyClickableSpan(String string) {
			super();
			clicked = string;
		}

		public void onClick(View tv) {
			// Toast.makeText(MainActivity.this, clicked,
			// Toast.LENGTH_SHORT).show();
		}

		public void updateDrawState(TextPaint ds) {// override updateDrawState
			ds.setUnderlineText(false); // set to false to remove underline
		}
	}

	private CharSequence findMentionText(SpanableText spanableText) {
		for (SpContentsMentionVO item : data.getMentionList()) {
			if (spanableText.strText.contains(item.getItemId())) {
				return "@" + item.getItemName();
			}
		}
		return "이름없음";
	}

	public void setData(SpContentsVO item, ISpCompletionViewListener listener) {
		data = item;
		this.listener = listener;
		setText(data.getBody(), BufferType.SPANNABLE);
	}

	public void setData(String data, ISpCompletionViewListener listener) {
		this.listener = listener;
		setText(data, BufferType.SPANNABLE);
	}

	public void setTagEnable(boolean b) {
		tagEnable = b;
	}

	public boolean isTagEnable() {
		return tagEnable;
	}

	public void setFontColor(int fontColor) {
		this.fontColor = fontColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setTagAddButton(boolean b) {
		tagAddButton = b;
	}
}