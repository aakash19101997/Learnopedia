package com.minorproject.minorproject.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minorproject.minorproject.R;

//import com.example.dictionary.ItemDetailActivity.SearchDescription;

public class ItemDetailFragment extends Fragment {



    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {

            Bundle bundle = getActivity().getIntent().getExtras();
            String keyword  = bundle.getString("keyword");
            appBarLayout.setTitle(keyword);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView;
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        textView = rootView.findViewById(R.id.item_detail);

       Bundle bundle = getActivity().getIntent().getExtras();
       String result = bundle.getString("answer");

       textView.setText(result);

        return rootView;
    }

}
