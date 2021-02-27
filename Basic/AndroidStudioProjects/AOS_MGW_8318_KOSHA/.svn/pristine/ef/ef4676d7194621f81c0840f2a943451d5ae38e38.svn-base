package com.hs.mobile.gw.fragment.squareplus.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.hs.mobile.gw.MainModel;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.SpanType;
import com.hs.mobile.gw.fragment.squareplus.view.SpCompletionView.SpanableText;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsMentionVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpContentsVO;
import com.hs.mobile.gw.openapi.squareplus.vo.SpTagVO;
import com.hs.mobile.gw.util.Debug;
import com.hs.mobile.gw.view.tokenautocomplete.TokenListener;

public class SpWriteCompletionView extends MultiAutoCompleteTextView implements
		TextView.OnEditorActionListener {
	public static final String MENTION_REGEX = "@[{]u(\\d+)[}]"; // 멘션 정규식
	public static final String TAG_REGEX = "#[{](.*?)[}]"; // 태그 정규식
	// 시스템 메시지 REGEX
	public static final String DEPT_REGEX = "%[{]d(\\d+)[}]"; // 부서 정규식
	public static final String USER_REGEX = "%[{]u(\\d+)[}]"; // 사용자 정규식
	public static final String SQUARE_REGEX = "%[{]g(.*?)[}]"; // 그룹 정규식
	
	public static final String URL_REGEX = "(http(s)?:\\/\\/|www.)[a-zA-Z0-9@:%._\\\\+~#=\\/?]{2,256}"; // http(s), www 정규식
	private int fontColor = 0xFF7086D3;
	private TokenListener listener;
	private TokenSpanWatcher spanWatcher;
	private TokenTextWatcher textWatcher;
	private ArrayList<Object> objects = new ArrayList<>();
	private boolean focusChanging = false;
	private boolean savingState = false;
	private SpContentsVO data;
	private List<SpContentsMentionVO> mentionsDatas;
	public interface IUpdateUrlPreviewLisenter {
		void updateUrlPreview (ArrayList<String> urlList);
	}
	
	IUpdateUrlPreviewLisenter urlLisenter;
	public void setUpdateUrlPreviewLisenter (IUpdateUrlPreviewLisenter lisenter){
		this.urlLisenter = lisenter;
	}
	
	public enum SpanType {
		MENTION, TAG, USER, DEPT, SQUARE, URL
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
		public Object token;
	}
	public SpWriteCompletionView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SpWriteCompletionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SpWriteCompletionView(Context context) {
		super(context);
		init();
	}

	private void init() {
		spanWatcher = new TokenSpanWatcher();
		textWatcher = new TokenTextWatcher();
		Editable text = getText();
		if (text != null) {
			Debug.trace(text.toString(), text.length());
			
			addTextChangedListener(textWatcher);
		}
	}
	
	public void removeTextChangedListener() {
		if (textWatcher != null) {
			removeTextChangedListener(textWatcher);
		}
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		return false;
	}

	private void updateHint() {
		Editable text = getText();
		CharSequence hintText = getHint();
		if (text == null || hintText == null) {
			return;
		}
	}

	private void clearSelections() {

		Editable text = getText();
		if (text == null)
			return;
//		CompletionSpan[] tokens = text.getSpans(0, text.length(),
//				CompletionSpan.class);
		invalidate();
	}

	public void setData(SpContentsVO contentsVO) {
		data = contentsVO;
		setMentionData(data.getMentionList());
	}
	public void setMentionData(List<SpContentsMentionVO> mentions) {
		mentionsDatas = mentions;
	}
	public void addMentionData(SpContentsMentionVO mention) {
		mentionsDatas.add(mention);
	}

	ArrayList<SpanableText> tempSpans = new ArrayList<SpWriteCompletionView.SpanableText>(); 
	private class TokenTextWatcher implements TextWatcher {

		protected void removeToken(CompletionSpan token, Editable text) {
			Debug.trace("TokenTextWatcher::removeToken", token.getToken(),
					text.toString());
			text.removeSpan(token);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			setSelection(start+count);
			getText().setSpan(spanWatcher, 0, getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

		@Override
		public void afterTextChanged(Editable s) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			Debug.trace("TokenTextWatcher::onTextChanged", s, start, before, count);
			if (before == count || start == 0 && before == 0)
				return;
			Editable text = getText();
			if (text == null)
				return;
			
			if (count - before == 1) {
				char c = s.charAt(start);
				Debug.trace("c = ", c);
				
				if (c == '\n' || c == ' ') {
					Debug.trace("\\n or space");
					Pattern p = Pattern.compile(URL_REGEX);
					Matcher m = p.matcher(text);
					ArrayList<String> urlList = new ArrayList<String>();
					while (m.find()) {
						Debug.trace("match - " ,m.group(0).trim());
						urlList.add(m.group(0).trim());
					}
					
					if (urlLisenter != null) {
						urlLisenter.updateUrlPreview(urlList);
					}
				}
			}
			
//			clearSelections();
//			updateHint();
			
			tempSpans.clear();
			CompletionSpan[] spans = text.getSpans(0, s.length(), CompletionSpan.class);
			
			for (CompletionSpan token : spans) {
				
				if (token != null) {
					int position = start + count;
					int spanStart = text.getSpanStart(token);
					int spanEnd = text.getSpanEnd(token);
					
					Debug.trace("spanStart = ", spanStart, "position = ", position, "spanEnd = ", spanEnd);
					if (spanStart <= position && position <= spanEnd) {
						if (before > count) { // text del
							if (token.getToken() instanceof SpContentsMentionVO
									&& spanEnd - spanStart < ((SpContentsMentionVO) token.getToken()).getItemName().length() + 1) {
								Debug.trace("1" ,((SpContentsMentionVO) token.getToken()).getItemName());
								removeToken(token, text);
							} else if (token.getToken() instanceof SpTagVO
									&& spanEnd - spanStart < ((SpTagVO) token.getToken()).getTagName().length() + 1) {
								Debug.trace("2" , ((SpTagVO) token.getToken()).getTagName());
								removeToken(token, text);
							}
						} else if (count - before == 1 && spanStart < position) { // text add
							if (token.getToken() instanceof SpContentsMentionVO
									&& position - spanStart <= ((SpContentsMentionVO) token.getToken()).getItemName().length() + 1) {
								Debug.trace("3" , ((SpContentsMentionVO) token.getToken()).getItemName());
								removeToken(token, text);
							} else if (token.getToken() instanceof SpTagVO
									&& position - spanStart <= ((SpTagVO) token.getToken()).getTagName().length() + 1) {
								Debug.trace("4" , ((SpTagVO) token.getToken()).getTagName());
								removeToken(token, text);
							}
						} else if (count - before > 1 && spanStart < position && position < spanEnd) { // tag or mention add
							if (token.getToken() instanceof SpContentsMentionVO
									&& position - spanStart <= ((SpContentsMentionVO) token.getToken()).getItemName().length() + 1 + count) {
								Debug.trace("5" , ((SpContentsMentionVO) token.getToken()).getItemName());
								removeToken(token, text);
							} else if (token.getToken() instanceof SpTagVO
									&& position - spanStart <= ((SpTagVO) token.getToken()).getTagName().length() + 1 + count) {
								Debug.trace("6" , ((SpTagVO) token.getToken()).getTagName());
								removeToken(token, text);
							}
						}
					}
				}
			}
			
			spans = text.getSpans(0, s.length(), CompletionSpan.class);
			
			for (CompletionSpan token : spans) {
				if (token.getToken() instanceof SpContentsMentionVO) {
					String name = "@" + ((SpContentsMentionVO) token.getToken()).getItemName();
					int startSpan = text.getSpanStart(token);
					int endSpan = startSpan + name.length();
					SpanableText spanable = new SpanableText(name, startSpan, endSpan, SpanType.MENTION);
					spanable.token = token.getToken();
					tempSpans.add(spanable);
				}
				else if (token.getToken() instanceof SpTagVO) {
					String name = "#" + ((SpTagVO) token.getToken()).getTagName();
					int startSpan = text.getSpanStart(token);
					int endSpan = startSpan + name.length();
					SpanableText spanable = new SpanableText(name, startSpan, endSpan, SpanType.TAG);
					spanable.token = token.getToken();
					tempSpans.add(spanable);
				}
				text.removeSpan(token);
			}
			
			for(SpanableText info : tempSpans) {
				Debug.trace(info.start, " - ", info.end);
				text.setSpan(new CompletionSpan(fontColor, info.token), info.start, info.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
	}

	private class TokenSpanWatcher implements SpanWatcher {

		@Override
		public void onSpanAdded(Spannable text, Object what, int start, int end) {
			Debug.trace("onSpanAdded::", text, start, end);
			if (what instanceof CompletionSpan && !savingState
					&& !focusChanging) {
				CompletionSpan token = (CompletionSpan) what;
				objects.add(token.getToken());

				if (listener != null)
					listener.onTokenAdded(token.getToken());
			}
		}

		@Override
		public void onSpanRemoved(Spannable text, Object what, int start,
				int end) {
			Debug.trace("onSpanRemoved::", text, start, end);

			if (what instanceof CompletionSpan && !savingState
					&& !focusChanging) {
				CompletionSpan token = (CompletionSpan) what;
				if (objects.contains(token.getToken())) {
					objects.remove(token.getToken());
				}

				if (listener != null)
					listener.onTokenRemoved(token.getToken());
			}
		}

		@Override
		public void onSpanChanged(Spannable text, Object what, int ostart,
				int oend, int nstart, int nend) {
			Debug.trace("onSpanChanged::", text, ostart, oend, nstart, nend);
			
			if (what instanceof CompletionSpan) {
				if (oend - ostart == 1) {
					tempSpans.clear();
					CompletionSpan[] spans = text.getSpans(0, text.length(), CompletionSpan.class);

					for (CompletionSpan token : spans) {
						if (token.getToken() instanceof SpContentsMentionVO) {
							String name = "@" + ((SpContentsMentionVO) token.getToken()).getItemName();
							int startSpan = text.getSpanStart(token);
							int endSpan = startSpan + name.length();
							SpanableText spanable = new SpanableText(name, startSpan, endSpan, SpanType.MENTION);
							spanable.token = token.getToken();
							tempSpans.add(spanable);
						} else if (token.getToken() instanceof SpTagVO) {
							String name = "#" + ((SpTagVO) token.getToken()).getTagName();
							int startSpan = text.getSpanStart(token);
							int endSpan = startSpan + name.length();
							SpanableText spanable = new SpanableText(name, startSpan, endSpan, SpanType.TAG);
							spanable.token = token.getToken();
							tempSpans.add(spanable);
						}
						text.removeSpan(token);
					}

					for (SpanableText info : tempSpans) {
						Debug.trace(info.start, " - ", info.end);
						text.setSpan(new CompletionSpan(fontColor, info.token), info.start, info.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					}
				}
			}
		}
	}

	public void removeTag(String tagString) {
		Editable text = getText();
		CompletionSpan[] spans = text.getSpans(0, text.length(),
				CompletionSpan.class);
		for (CompletionSpan token : spans) {
			if (token.getToken() instanceof SpTagVO) {
				if (TextUtils.equals(((SpTagVO) token.getToken()).getTagName(),
						tagString)) {
					text.removeSpan(token);
				}
			}
		}
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

		p = Pattern.compile(TAG_REGEX);
		m = p.matcher(text);
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.TAG));
		}

		p = Pattern.compile(USER_REGEX);
		m = p.matcher(text);
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.USER));
			str.setSpan(new ForegroundColorSpan(fontColor), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}

		p = Pattern.compile(DEPT_REGEX);
		m = p.matcher(text);
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.DEPT));
			str.setSpan(new ForegroundColorSpan(fontColor), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		p = Pattern.compile(SQUARE_REGEX);
		m = p.matcher(text);
		while (m.find()) {
			arr.add(new SpanableText(m.group(1).trim(), m.start(), m.end(), SpanType.SQUARE));
			str.setSpan(new ForegroundColorSpan(fontColor), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		
		if (arr != null) {
			Ascending asc = new Ascending();
			Collections.sort(arr, asc);

			for (SpanableText st : arr) {
				if (st.type == SpanType.MENTION) {
					try {
						str.setSpan(
								new CompletionSpan(fontColor, mentionsDatas.get(mentionsDatas.indexOf(new SpContentsMentionVO(st.strText)))),
								st.start, st.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					} catch (IndexOutOfBoundsException e) {
						str.setSpan(new ForegroundColorSpan(fontColor), st.start, st.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					}
				} else if (st.type == SpanType.TAG) {
					SpTagVO tagVO = null;
					for (SpTagVO tag : data.getTagList()) {
						if (tag.getTagName().equals(st.strText)) {
							tagVO = tag;
							break;
						}
					}
					str.setSpan(new CompletionSpan(fontColor, tagVO), st.start, st.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//						str.setSpan(new ForegroundColorSpan(fontColor), st.start, st.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else {
					str.setSpan(new ForegroundColorSpan(fontColor), st.start, st.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}
		}

		SpannableStringBuilder replaceText = replaceText(str, arr);
		super.setText(replaceText, BufferType.SPANNABLE);
	}
	
	private class Ascending implements Comparator<SpanableText> {
	    @Override
	    public int compare(SpanableText o1, SpanableText o2) {
	        return Integer.valueOf(o1.start).compareTo(Integer.valueOf(o2.start));
	    }
	}

	private SpannableStringBuilder replaceText(Spannable text, ArrayList<SpanableText> arr) {
		SpannableStringBuilder ssb = new SpannableStringBuilder(text);
		CompletionSpan[] spans = ssb.getSpans(0, text.length(), CompletionSpan.class);
		for (int i = 0; i < spans.length; ++i) {
			final SpanableText st = arr.get(i);
			// ssb.setSpan(
			// new ClickableSpan() {
			// @Override
			// public void onClick(View widget) {
			// switch (st.type) {
			// case MENTION:
			// for (SpContentsMentionVO item : data
			// .getMentionList()) {
			// if (st.strText.contains(item.getItemId())) {
			// Debug.trace(item.getItemId(),
			// item.getItemName(),
			// item.getItemText());
			// listener.onUserClick(item.getItemId());
			// }
			// }
			// break;
			// case TAG: {
			// String replace = st.strText.substring(2);
			// replace = (String) replace.subSequence(0,
			// replace.length() - 1);
			// Debug.trace(replace);
			// listener.onTagClick(replace);
			// }
			// break;
			// case USER:
			// listener.onUserClick(st.strText.replace("%{u",
			// "").replace("}", ""));
			// break;
			// }
			// }
			//
			// @Override
			// public void updateDrawState(TextPaint ds) {
			// // super.updateDrawState(ds);
			// ds.setUnderlineText(false);
			// }
			// }, ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]),
			// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			switch (st.type) {
			case MENTION:
				ssb.replace(ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]), findMentionText(arr.get(i)));
				break;
			case TAG:
				String replace = "#" + st.strText;
				ssb.replace(ssb.getSpanStart(spans[i]), ssb.getSpanEnd(spans[i]), replace);
				break;
			}
		}
		return ssb;
	}
	private CharSequence findMentionText(SpanableText spanableText) {
		for (SpContentsMentionVO item : mentionsDatas) {
			if (spanableText.strText.contains(item.getItemId())) {
				return "@" + item.getItemName();
			}
		}
		return "이름없음";
	}
}
