package com.minorproject.minorproject.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minorproject.minorproject.R;
import com.minorproject.minorproject.models.Questions;

/**
 * Created by Aakash on 22-04-2018.
 */

@SuppressLint("ValidFragment")
public class frag_quiz_main extends Fragment {
    Button btn1,btn2,btn3,btn4;
    TextView textView,timer;
    Context context;
    TextView skip;
    int total_ques = 10;
    int total= 0;
    int correct= 0;
    int wrong=0;
    int skipped = 0;
    DatabaseReference databaseReference;
    String subject,difficulty;
    //RelativeLayout relativeLayout;
    //Button power,start;

    //TextView textView1,textView2,textView3,textView4,textView5,textView6;
    //TextView statistics;

    @SuppressLint("ValidFragment")
    public frag_quiz_main(String subject,String difficulty) {
        this.subject = subject;
        this.difficulty = difficulty;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.quiz_main_frag,container,false);

        btn1 = (Button)v.findViewById(R.id.btn1);
        btn2 = (Button)v.findViewById(R.id.btn2);
        btn3 = (Button)v.findViewById(R.id.btn3);
        btn4 = (Button)v.findViewById(R.id.btn4);

        textView = (TextView)v.findViewById(R.id.text);
        timer = (TextView) v.findViewById(R.id.timer);
        skip = (TextView) v.findViewById(R.id.skip);

        updateQuestion();

        reverseTimer(120,timer);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skipped ++;
                updateQuestion();
            }
        });
        return v;
    }


    private void updateQuestion() {
        total++;
        if(total > total_ques){
            ResultFragment resultFragment = new ResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("total",String.valueOf(total-1-skipped));
            bundle.putString("correct",String.valueOf(correct));
            bundle.putString("incorrect",String.valueOf(wrong));
            resultFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame, resultFragment).commit();
        }
        else{
            databaseReference = FirebaseDatabase.getInstance().getReference().child("questions").child(subject).child(difficulty).child(String.valueOf(total));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Questions question = dataSnapshot.getValue(Questions.class);

                    textView.setText(question.getQuestion());

                    btn1.setText(question.getOption1());
                    btn2.setText(question.getOption2());
                    btn3.setText(question.getOption3());
                    btn4.setText(question.getOption4());



                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(btn1.getText().toString().trim().equals(question.getAnswer())){
                                btn1.setTextColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn1.setTextColor(Color.parseColor("#000000"));
                                        updateQuestion();
                                    }
                                },800);
                            }
                            else{
                                wrong++;
                                btn1.setTextColor(Color.RED);
                                if(btn2.getText().toString().trim().equals(question.getAnswer())){
                                    btn2.setTextColor(Color.GREEN);
                                }
                                else if(btn3.getText().toString().trim().equals(question.getAnswer())){
                                    btn3.setTextColor(Color.GREEN);
                                }
                                else if(btn4.getText().toString().trim().equals(question.getAnswer())){
                                    btn4.setTextColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn1.setTextColor(Color.parseColor("#000000"));
                                        btn2.setTextColor(Color.parseColor("#000000"));
                                        btn3.setTextColor(Color.parseColor("#000000"));
                                        btn4.setTextColor(Color.parseColor("#000000"));
                                        updateQuestion();
                                    }
                                },800);
                            }
                        }
                    });

                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(btn2.getText().toString().trim().equals(question.getAnswer())){
                                btn2.setTextColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn2.setTextColor(Color.parseColor("#000000"));
                                        updateQuestion();
                                    }
                                },800);
                            }
                            else{
                                wrong++;
                                btn2.setTextColor(Color.RED);
                                if(btn1.getText().toString().trim().equals(question.getAnswer())){
                                    btn1.setTextColor(Color.GREEN);
                                }
                                else if(btn3.getText().toString().trim().equals(question.getAnswer())){
                                    btn3.setTextColor(Color.GREEN);
                                }
                                else if(btn4.getText().toString().trim().equals(question.getAnswer())){
                                    btn4.setTextColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn1.setTextColor(Color.parseColor("#000000"));
                                        btn2.setTextColor(Color.parseColor("#000000"));
                                        btn3.setTextColor(Color.parseColor("#000000"));
                                        btn4.setTextColor(Color.parseColor("#000000"));
                                        updateQuestion();
                                    }
                                },800);
                            }
                        }
                    });


                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(btn3.getText().toString().trim().equals(question.getAnswer())){
                                btn3.setTextColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btn3.setTextColor(Color.parseColor("#000000"));
                                        updateQuestion();
                                    }
                                },800);
                            }
                            else{
                                wrong++;
                                btn3.setTextColor(Color.RED);
                                if(btn2.getText().toString().trim().equals(question.getAnswer())){
                                    btn2.setTextColor(Color.GREEN);
                                }
                                else if(btn1.getText().toString().trim().equals(question.getAnswer())){
                                    btn1.setTextColor(Color.GREEN);
                                }
                                else if(btn4.getText().toString().trim().equals(question.getAnswer())){
                                    btn4.setTextColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn1.setTextColor(Color.parseColor("#000000"));
                                        btn2.setTextColor(Color.parseColor("#000000"));
                                        btn3.setTextColor(Color.parseColor("#000000"));
                                        btn4.setTextColor(Color.parseColor("#000000"));
                                        updateQuestion();
                                    }
                                },800);
                            }
                        }
                    });

                    btn4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) { if(btn4.getText().toString().trim().equals(question.getAnswer())){
                            btn4.setTextColor(Color.GREEN);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    btn4.setTextColor(Color.parseColor("#000000"));
                                    updateQuestion();
                                }
                            },800);
                        }
                        else{
                            wrong++;
                            btn4.setTextColor(Color.RED);
                            if(btn2.getText().toString().trim().equals(question.getAnswer())){
                                btn2.setTextColor(Color.GREEN);
                            }
                            else if(btn3.getText().toString().trim().equals(question.getAnswer())){
                                btn3.setTextColor(Color.GREEN);
                            }
                            else if(btn1.getText().toString().trim().equals(question.getAnswer())){
                                btn1.setTextColor(Color.GREEN);
                            }

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    btn1.setTextColor(Color.parseColor("#000000"));
                                    btn2.setTextColor(Color.parseColor("#000000"));
                                    btn3.setTextColor(Color.parseColor("#000000"));
                                    btn4.setTextColor(Color.parseColor("#000000"));
                                    updateQuestion();
                                }
                            },800);
                        }

                        }
                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void reverseTimer(int seconds, final  TextView tv){
        new CountDownTimer(seconds*1000+1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished/1000);
                int minutes = seconds/60;
                seconds = seconds%60;
                tv.setText(String.format("%02d",minutes)+":"+String.format("%02d",seconds));

            }

            @Override
            public void onFinish() {

                tv.setText("Time Up.");


                if(total<=total_ques){

                    if(isAdded()){
                        ResultFragment resultFragment = new ResultFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("total",String.valueOf(total-1-skipped));
                        bundle.putString("correct",String.valueOf(correct));
                        bundle.putString("incorrect",String.valueOf(wrong));
                        resultFragment.setArguments(bundle);

                        if(total<= total_ques) {


                            getFragmentManager().beginTransaction().replace(R.id.frame, resultFragment).commit();


                        }

                    }}
            }


        }.start();
    }
}
