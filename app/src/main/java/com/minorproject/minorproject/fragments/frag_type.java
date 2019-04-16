package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.minorproject.minorproject.R;

public class frag_type extends Fragment {
    ImageView cut;
    Button btn,btn1,btn2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_type,container,false);

        cut =  view.findViewById(R.id.cut);
        btn = view.findViewById(R.id.text);
        btn1 = view.findViewById(R.id.video);
        btn2 = view.findViewById(R.id.quiz);
        final String subject = getArguments().getString("subject");

        if(subject.equals("html")||subject.equals("css")){
            btn2.setVisibility(View.GONE);
        }

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { getFragmentManager().beginTransaction().replace(R.id.frame,new HomeFrag()).commit();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (subject){
                    case "compiler":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv1_fragment()).commit();
                        break;
                    case "algorithm":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv2_fragment()).commit();
                        break;
                    case "digital":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv3_fragment()).commit();
                        break;
                    case "html":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv5_fragment()).commit();
                        break;
                    case "css":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv6_fragment()).commit();
                        break;
                }

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (subject){
                    case "compiler":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vcd()).commit();
                        break;
                    case "algorithm":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_valgo()).commit();
                        break;
                    case "digital":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vdigi()).commit();
                        break;
                    case "html":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vhtml()).commit();
                        break;
                    case "css":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vcss()).commit();
                        break;
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (subject){
                    case "compiler":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new Difficulty("compiler")).commit();
                        break;
                    case "algorithm":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new Difficulty("algorithm")).commit();
                        break;
                    case "digital":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new Difficulty("digital")).commit();
                        break;
                }
            }
        });

        return view;

    }
}
