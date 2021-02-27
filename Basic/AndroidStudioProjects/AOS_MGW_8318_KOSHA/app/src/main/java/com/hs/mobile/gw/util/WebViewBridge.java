package com.hs.mobile.gw.util;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.hs.mobile.gw.Define;

public class WebViewBridge {
    private final Handler handler = new Handler();
    private WebView webView;

    public WebViewBridge(WebView webView) {
        this.webView = webView;


    }

    @JavascriptInterface
    public void sendServerData(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Debug.trace("Sending ServerURL to javascript");
                webView.loadUrl("javascript:initializeServerInfo('"+ Define.SERVER_URL +"')");
            }
        });
    }
}
