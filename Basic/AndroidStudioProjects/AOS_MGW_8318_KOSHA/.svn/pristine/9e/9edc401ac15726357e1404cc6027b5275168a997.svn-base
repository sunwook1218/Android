package com.hs.mobile.gw.util.filecache;

import java.io.File;
import java.util.HashMap;

import com.hs.mobile.gw.util.Debug;

import android.content.Context;

public class FileCacheFactory {

	private static boolean initialized = false;

	private static FileCacheFactory instance = new FileCacheFactory();

	public static void initialize(Context context) {

		if (!initialized) {

			synchronized (instance) {

				if (!initialized) {
					instance.init(context);
					initialized = true;
				}
			}
		}
	}

	public static FileCacheFactory getInstance() {

		if (!initialized) {
			throw new IllegalStateException("Not initialized. You must call FileCacheFactory.initialize() before getInstance()");
		}

		return instance;
	}

	private HashMap<String, FileCache> cacheMap = new HashMap<String, FileCache>();

	private File cacheBaseDir;
	private File extCacheBaseDir;

	private FileCacheFactory() {

	}

	private void init(Context context) {
		cacheBaseDir = context.getCacheDir();
		extCacheBaseDir = context.getExternalCacheDir();
	}

	public FileCache create(String cacheName, int maxKbSizes) {

		synchronized (cacheMap) {

			FileCache cache = cacheMap.get(cacheName);

			if (cache != null) {
				try {
					throw new Exception(String.format("FileCache[%s] Aleady exists", cacheName));
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				} catch (java.util.IllegalFormatException e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				}
			}

			File cacheDir = new File(cacheBaseDir, cacheName);
			cache = new FileCacheImpl(cacheDir, maxKbSizes);
			cacheMap.put(cacheName, cache);

			return cache;
		}
	}
	
	public FileCache createExt(String cacheName, int maxKbSizes) {
		
		synchronized (cacheMap) {
			
			FileCache cache = cacheMap.get(cacheName);
			
			if (cache != null) {
				try {
					throw new Exception(String.format("FileCache[%s] Aleady exists", cacheName));
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				} catch (java.util.IllegalFormatException e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				}
			}
			
			File cacheDir = new File(extCacheBaseDir, cacheName);
			cache = new FileCacheImpl(cacheDir, maxKbSizes);
			cacheMap.put(cacheName, cache);
			
			return cache;
		}
	}

	public FileCache get(String cacheName) {

		synchronized (cacheMap) {

			FileCache cache = cacheMap.get(cacheName);

			if (cache == null) {

				try {
					throw new Exception(String.format("FileCache[%s] not founds.", cacheName));
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				} catch (java.util.IllegalFormatException e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Debug.trace(e.getMessage());
				}
			}
			return cache;
		}
	}

	public boolean has(String cacheName) {
		
		return cacheMap.containsKey(cacheName);
	}

}