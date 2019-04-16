package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.minorproject.minorproject.R;

public class Frag_webview extends Fragment {

    WebView wv1;
    ImageView cut;

    public Frag_webview(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wv, container, false);
        wv1 = (WebView) v.findViewById(R.id.myWebView);
        cut = v.findViewById(R.id.cut);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new HomeFrag()).commit();
                FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
                floatingActionButton.show();
            }
        });
        String url = getArguments().getString("url");
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return false;
            }
        });wv1.loadUrl(url);
        return v;
    }
}
