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

public class frag_choose_sub extends Fragment {

    ImageView cut;
    Button btn,btn1,btn2,btn3,btn4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_sub,container,false);

        cut =  view.findViewById(R.id.cut);
        btn = view.findViewById(R.id.comp_d);
        btn1 = view.findViewById(R.id.algo);
        btn2 = view.findViewById(R.id.digital_logic);
        btn3 = view.findViewById(R.id.html);
        btn4 = view.findViewById(R.id.css);
        final String type = getArguments().getString("type");

        if(type.equals("quiz")){
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
        }
        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { getFragmentManager().beginTransaction().replace(R.id.frame,new HomeFrag()).commit();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case "text":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv1_fragment()).commit();
                        break;
                    case "video":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vcd()).commit();
                        break;
                    case "quiz":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new Difficulty("compiler")).commit();
                        break;
                }

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case "text":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv2_fragment()).commit();
                        break;
                    case "video":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_valgo()).commit();
                        break;
                    case "quiz":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new Difficulty("algorithm")).commit();
                        break;
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case "text":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv3_fragment()).commit();
                        break;
                    case "video":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vdigi()).commit();
                        break;
                    case "quiz":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new Difficulty("digital")).commit();
                        break;
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case "text":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv5_fragment()).commit();
                        break;
                    case "video":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vhtml()).commit();
                        break;
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case "text":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new wv6_fragment()).commit();
                        break;
                    case "video":
                        getFragmentManager().beginTransaction().replace(R.id.frame,new frag_vcss()).commit();
                        break;
                }
            }
        });
        return view;
    }
}
