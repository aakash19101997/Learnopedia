package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minorproject.minorproject.R;
import com.minorproject.minorproject.models.Fb_form;

public class frag_feedback extends Fragment  {
    EditText etname,etmobile,etcomments;
    ImageButton imgbtn1,imgbtn2,imgbtn3,imgbtn4,imgbtn5;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;
    ImageButton submit;
    DatabaseReference dr;
    TextView tvrating;
    String name,mobile,rating,comments,recommend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feedback_form,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etname=view.findViewById(R.id.etname);
        etmobile=view.findViewById(R.id.etmobile);
        etcomments=view.findViewById(R.id.etcomments);
        dr= FirebaseDatabase.getInstance().getReference("fbform");
        tvrating = view.findViewById(R.id.tvrating);
        imgbtn1 = view.findViewById(R.id.imgbtn1);
        imgbtn2 = view.findViewById(R.id.imgbtn2);
        imgbtn3 = view.findViewById(R.id.imgbtn3);
        imgbtn4 = view.findViewById(R.id.imgbtn4);
        imgbtn5 = view.findViewById(R.id.imgbtn5);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);
        btn8 = view.findViewById(R.id.btn8);
        btn9 = view.findViewById(R.id.btn9);
        btn10 = view.findViewById(R.id.btn10);
        submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etname.getText().toString().trim();
                mobile = etmobile.getText().toString().trim();
                comments = etcomments.getText().toString().trim();
                if (name.isEmpty()) {
                    etname.setError("Name is required");
                    etname.requestFocus();
                    return;
                }
                if (mobile.isEmpty()) {
                    etmobile.setError("Mobile number is required");
                    etmobile.requestFocus();
                    return;
                }
                if (comments.isEmpty()) {
                    etcomments.setError("Comments are required");
                    etcomments.requestFocus();
                    return;
                }
                if (rating==null) {
                    tvrating.setError("Give Rating");
                    tvrating.requestFocus();
                    //Toast.makeText(getActivity(), "Rating is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (recommend==null) {
                    tvrating.setError("Give Reommendation");
                    tvrating.requestFocus();
                    //Toast.makeText(getActivity(), "Recommendation is required", Toast.LENGTH_SHORT).show();
                    return;
                }


                String id = dr.push().getKey();
                Fb_form f = new Fb_form(name, mobile, rating, comments, recommend);
                dr.child(id).setValue(f);
                Toast.makeText(getActivity(), "FEEDBACK SUBMITTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                etcomments.setText(null);
                etmobile.setText(null);
                etname.setText(null);
                rating = null;
                recommend = null;

            }
        });

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "1";
            }
        });
        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "2";
            }
        });

        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "3";
            }
        });
        imgbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "4";
            }
        });
        imgbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = "5";
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "1";
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "2";
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "3";
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "4";
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "5";
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "6";
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "7";
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "8";
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "9";
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend = "10";
            }
        });

    }

}
