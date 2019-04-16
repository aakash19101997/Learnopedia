package com.minorproject.minorproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.minorproject.minorproject.R;

public class HomeFrag extends Fragment {
    int counter = 0;
    View rootView;
    Button comp,algo,nptel,edx,oracle,redhat,digital,text,video,quiz,html,css;
    private ImageSwitcher pic_image_switch;
    private Handler pic_image_switch_handler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_home,container,false);
        pic_image_switch = (ImageSwitcher) rootView.findViewById(R.id.img_switcher);
        comp = rootView.findViewById(R.id.comp_d);
        algo = rootView.findViewById(R.id.algo);
        nptel = rootView.findViewById(R.id.nptel);
        edx = rootView.findViewById(R.id.edx);
        redhat = rootView.findViewById(R.id.redhat);
        oracle = rootView.findViewById(R.id.oracle);
        digital = rootView.findViewById(R.id.digital_logic);
        text = rootView.findViewById(R.id.text);
        video = rootView.findViewById(R.id.video);
        quiz = rootView.findViewById(R.id.quiz);
        html = rootView.findViewById(R.id.html);
        css = rootView.findViewById(R.id.css);


        nptel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFrag("https://nptel.ac.in/");
            }
        });
        edx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFrag("https://www.edx.org/");

            }
        });

        oracle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { changeFrag("https://www.oracle.com/in/index.html");
            }
        });


        redhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { changeFrag("https://www.redhat.com/en");
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu("text");
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu("video");
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu("quiz");
            }
        });

        algo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu1("algorithm");
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu1("compiler");
            }
        });
        digital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu1("digital");
            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu1("html");
            }
        });

        css.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submenu1("css");
            }
        });

        pic_image_switch.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });

        pic_image_switch_handler = new Handler(Looper.getMainLooper());

        pic_image_switch_handler.post(new Runnable() {
            @Override
            public void run() {
                switch (counter) {
                    case 0:
                        pic_image_switch.setImageResource(R.drawable.download);
                        break;
                    case 1:
                        pic_image_switch.setImageResource(R.drawable.mai);
                        break;
                    case 2:
                        pic_image_switch.setImageResource(R.drawable.mai1);
                        break;
                }
                counter += 1;
                if (counter == 3) {
                    counter = 0;
                }
                pic_image_switch.postDelayed(this, 3000);
            }
        });


        return rootView;
    }

    private void submenu1(String text) {
        frag_type frag_type = new frag_type();
        Bundle bundle = new Bundle();
        bundle.putString("subject",text);
        frag_type.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame, frag_type).commit();

    }


    private void submenu(String text) {
        frag_choose_sub frag_choose_sub = new frag_choose_sub();
        Bundle bundle = new Bundle();
        bundle.putString("type",text);
        frag_choose_sub.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame, frag_choose_sub).commit();
    }

    public void changeFrag(String url){
        FloatingActionButton floatingActionButton = getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();
        Frag_webview fragment = new Frag_webview();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();

    }

}
