package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minorproject.minorproject.R;

/**
 * Created by Aakash on 23-04-2018.
 */

public class frag_vdigi extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vdigi_frag,container,false);
    }
}
