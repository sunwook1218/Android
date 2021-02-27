package com.hs.mobile.gw.util;

import java.util.ArrayList;

// --util--
public class TitleStack{
	ArrayList<String> stack = new ArrayList<String>();

	public void push(String str) {
		stack.add(str);
	}

	public void pop() {
		stack.remove(stack.size() - 1);
	}

	public String getTop() {
		String str = null;
		if (stack.size() == 0) return str;
		str = stack.get(stack.size() - 1);
//			str = stack.get(stack.size());
		return str;
	}

	public void clean() {
		stack.clear();
	}
}