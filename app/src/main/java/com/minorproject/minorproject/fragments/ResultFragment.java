package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.minorproject.minorproject.R;

public class ResultFragment extends Fragment {
    TextView t1, t2, t3;
    Button power,start;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_result,container,false);
        t1 = (TextView) v.findViewById(R.id.textView4);
        t2 = (TextView) v.findViewById(R.id.textView5);
        t3 = (TextView) v.findViewById(R.id.textView6);
        power = v.findViewById(R.id.power);


        String questions = getArguments().getString("total");
        String correct = getArguments().getString("correct");
        String wrong = getArguments().getString("incorrect");

        t1.setText(questions);
        t2.setText(correct);
        t3.setText(wrong);

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new HomeFrag()).commit();
            }
        });


        return v;
    }
}