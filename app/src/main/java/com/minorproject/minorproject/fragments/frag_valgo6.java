package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.minorproject.minorproject.R;

/**
 * Created by Aakash on 23-04-2018.
 */

public class frag_valgo6 extends Fragment {
    WebView w;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.avideo_frag, container, false);
        w = (WebView) v.findViewById(R.id.wv);
        w.getSettings().setJavaScriptEnabled(true);
        w.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return false;
            }
        });
        w.loadUrl("https://youtu.be/6LOwPhPDwVc");
        return v;
    }
}
