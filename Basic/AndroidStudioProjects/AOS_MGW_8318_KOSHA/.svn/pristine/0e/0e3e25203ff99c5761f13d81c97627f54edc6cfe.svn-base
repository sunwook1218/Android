package com.hs.mobile.gw.util;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;

public class BundleUtils {
	public static class Builder {
		Bundle bundle;

		public Builder() {
			bundle = new Bundle();
		}

		public Builder(Bundle bd) {
			if (bd != null) {
				bundle = bd;
			} else {
				bundle = new Bundle();
			}
		}

		public Builder add(String strKey, boolean b) {
			bundle.putBoolean(strKey, b);
			return this;
		}

		public Bundle build() {
			return bundle;
		}

		public Builder add(String strKey, String string) {
			bundle.putString(strKey, string);
			return this;
		}

		public Builder add(String key, Serializable value) {
			bundle.putSerializable(key, value);
			return this;
		}
		
		public Builder add(String key, ArrayList<String> value){
			bundle.putStringArrayList(key, value);
			return this;
		}

	}
}
