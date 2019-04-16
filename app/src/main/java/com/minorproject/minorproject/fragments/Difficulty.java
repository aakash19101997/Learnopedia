package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.minorproject.minorproject.R;

public class Difficulty extends Fragment {

    String subject;

    Button button,button1,button2;

    public Difficulty(String subject){
        this.subject = subject;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diffculty,container,false);
        button = view.findViewById(R.id.easy);
        button1= view.findViewById(R.id.medium);
        button2 =view.findViewById(R.id.hard);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new frag_quiz_main(subject,"easy")).commit();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new frag_quiz_main(subject,"medium")).commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new frag_quiz_main(subject,"hard")).commit();
            }
        });

        return view;
    }
}
