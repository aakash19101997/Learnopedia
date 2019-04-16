package com.minorproject.minorproject.activities;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.minorproject.minorproject.R;
import com.minorproject.minorproject.adapters.ExpandableListAdapter1;
import com.minorproject.minorproject.fragments.Difficulty;
import com.minorproject.minorproject.fragments.Discussion;
import com.minorproject.minorproject.fragments.HomeFrag;
import com.minorproject.minorproject.fragments.contact;
import com.minorproject.minorproject.fragments.frag_downloads;
import com.minorproject.minorproject.fragments.frag_feedback;
import com.minorproject.minorproject.fragments.frag_valgo;
import com.minorproject.minorproject.fragments.frag_valgo1;
import com.minorproject.minorproject.fragments.frag_valgo2;
import com.minorproject.minorproject.fragments.frag_valgo3;
import com.minorproject.minorproject.fragments.frag_valgo4;
import com.minorproject.minorproject.fragments.frag_valgo5;
import com.minorproject.minorproject.fragments.frag_valgo6;
import com.minorproject.minorproject.fragments.frag_vcd;
import com.minorproject.minorproject.fragments.frag_vcd1;
import com.minorproject.minorproject.fragments.frag_vcd2;
import com.minorproject.minorproject.fragments.frag_vcd3;
import com.minorproject.minorproject.fragments.frag_vcd4;
import com.minorproject.minorproject.fragments.frag_vcd5;
import com.minorproject.minorproject.fragments.frag_vcss;
import com.minorproject.minorproject.fragments.frag_vcss1;
import com.minorproject.minorproject.fragments.frag_vcss2;
import com.minorproject.minorproject.fragments.frag_vdigi;
import com.minorproject.minorproject.fragments.frag_vdigi1;
import com.minorproject.minorproject.fragments.frag_vdigi2;
import com.minorproject.minorproject.fragments.frag_vdigi3;
import com.minorproject.minorproject.fragments.frag_vdigi4;
import com.minorproject.minorproject.fragments.frag_vhtml;
import com.minorproject.minorproject.fragments.frag_vhtml1;
import com.minorproject.minorproject.fragments.frag_vhtml2;
import com.minorproject.minorproject.fragments.wv1_fragment;
import com.minorproject.minorproject.fragments.wv2_fragment;
import com.minorproject.minorproject.fragments.wv3_fragment;
import com.minorproject.minorproject.fragments.wv4_fragment;
import com.minorproject.minorproject.fragments.wv5_fragment;
import com.minorproject.minorproject.fragments.wv6_fragment;
import com.minorproject.minorproject.models.ModelMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ExpandableListAdapter1 expandableListAdapter;
    ExpandableListView expandableListView;
    List<ModelMenu> headerList = new ArrayList<>();
    HashMap<ModelMenu, List<ModelMenu>> childList = new HashMap<>();
     FloatingActionButton fab;
     TextView textView;

     NavigationView navigationView;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               FragmentManager fragmentManager = getFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.frame,new Discussion());
               fragmentTransaction.commit();
               fab.hide();
            }
        });

        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);

        textView = headerView.findViewById(R.id.textView);

        String name =FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        String phoneNumber  = "Welcome " + FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();

        if(name==null){
            textView.setText(phoneNumber);
        }
        else {
            String con = "Welcome "+name;
        textView.setText(con);}
        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction().replace(R.id.frame,new HomeFrag()).commit();
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter1(this,headerList,childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        ModelMenu model = headerList.get(groupPosition);
                        FragmentManager fm=getFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();

                        switch (model.id){
                            case "home":
                                fab.show();
                                ft.replace(R.id.frame, new HomeFrag());
                                break;
                            case "logout":
                                FirebaseAuth.getInstance().signOut();

                                Intent intent = new Intent(NavActivity.this,SignInActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                                break;

                            case "download":
                                ft.replace(R.id.frame, new frag_downloads());
                                break;

                        }
                        ft.commit();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    ModelMenu model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    if (model.id.length() > 0) {
                        FragmentManager fm=getFragmentManager();
                        FragmentTransaction ft=fm.beginTransaction();

                        switch (model.id) {
                            case "comp_d":
                                // Handle the camera action
                                fab.hide();
                                ft.replace(R.id.frame, new wv1_fragment());
                                break;
                            case "algo":
                                fab.hide();
                                ft.replace(R.id.frame, new wv2_fragment());
                                break;
                            case "digital_logic":
                                fab.hide();
                                ft.replace(R.id.frame, new wv3_fragment());
                                break;
                            case "vcomp_d":
                                ft.replace(R.id.frame, new frag_vcd());

                                break;
                            case "valgo":

                                ft.replace(R.id.frame, new frag_valgo());
                                break;
                            case "vdigital_logic":
                                ft.replace(R.id.frame, new frag_vdigi());
                                break;
                            case "qcomp_d":
                                ft.replace(R.id.frame, new Difficulty("compiler"));
                                break;
                            case "qalgo":
                                ft.replace(R.id.frame, new Difficulty("algorithm"));
                                break;
                            case "qdigital_logic":
                                ft.replace(R.id.frame, new Difficulty("digital"));
                                break;
                            case "contact_us":
                                ft.replace(R.id.frame, new contact());
                                break;
                            case "feedback":
                                ft.replace(R.id.frame, new frag_feedback());
                                break;
                            case "calculator":
                                Intent intent = new Intent(NavActivity.this, CalculatorActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                break;
                            case "dictionary":
                                Intent i = new Intent(NavActivity.this, DictionaryActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                break;

                            case "live_editor":
                                ft.replace(R.id.frame, new wv4_fragment());
                                break;

                            case "vhtml":
                                ft.replace(R.id.frame,new frag_vhtml());
                                break;
                            case "html":
                                ft.replace(R.id.frame,new wv5_fragment());
                                break;
                            case "css":
                                ft.replace(R.id.frame,new wv6_fragment());
                                break;
                            case "vcss":
                                ft.replace(R.id.frame,new frag_vcss());
                                break;

                        }



                        ft.commit();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        expandableListView.collapseGroup(groupPosition);
                    }
                }

                return false;
            }
        });
    }

    private void prepareMenuData() {

        ModelMenu menuModel = new ModelMenu("Home","home","home",false,true);
        headerList.add(menuModel);
        menuModel = new ModelMenu("Subjects", "subjects","text", true,true); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        List<ModelMenu> childModelsList = new ArrayList<>();
        ModelMenu childModel = new ModelMenu("Compiler Design","comp_d",null ,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("Algorithms","algo" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("Digital","digital_logic" ,null,false, false);
        childModelsList.add(childModel);


        childModel = new ModelMenu("HTML","html" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("CSS","css" ,null,false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }


        menuModel = new ModelMenu("Videos","videos","video",true,true); //Menu of Java Tutorials
        headerList.add(menuModel);
       childModelsList = new ArrayList<>();
        childModel = new ModelMenu("Compiler Design","vcomp_d" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("Algorithms","valgo" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("Digital","vdigital_logic" ,null,false, false);
        childModelsList.add(childModel);
        childModel = new ModelMenu("HTML","vhtml" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("CSS","vcss" ,null,false, false);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new ModelMenu("Quiz","quiz","quiz",true,true);
        headerList.add(menuModel);
      childModel = new ModelMenu("Compiler Design","qcomp_d" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("Algorithms","qalgo" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("Digital","qdigital_logic" ,null,false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        childModelsList = new ArrayList<>();
        menuModel = new ModelMenu("Tools","tool","tool",true,true);
        headerList.add(menuModel);
        childModel = new ModelMenu("Calculator","calculator" ,null,false, false);
        childModelsList.add(childModel);
        childModel = new ModelMenu("Dictionary","dictionary" ,null,false, false);
        childModelsList.add(childModel);
        childModel = new ModelMenu("Live Editor","live_editor" ,null,false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        childModelsList = new ArrayList<>();
        menuModel = new ModelMenu("Communicate","contact","communicate",true,true);
        headerList.add(menuModel);
        childModel = new ModelMenu("Feedback Form","feedback" ,null,false, false);
        childModelsList.add(childModel);

        childModel = new ModelMenu("Contact us","contact_us" ,null,false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new ModelMenu("Downloads","download","downloads",false,true);
        headerList.add(menuModel);

        menuModel = new ModelMenu("Logout","logout","logout",false,true);
        headerList.add(menuModel);



    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.eduacationappico)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(NavActivity.this,SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        if (id == R.id.comp_d) {
            // Handle the camera action

        } else if (id == R.id.algo) {

        } else if (id == R.id.digital_logic) {

        } else if (id == R.id.vcomp_d) {


        }
        else if(id==R.id.valgo)
        {


        }
        else if(id==R.id.vdigital_logic)
        {

        }
        else if(id==R.id.qcomp_d)
        {

        }
        else if(id==R.id.qalgorithms)
        {

        }
        else if(id==R.id.qdigital_logic)
        {

        }

        else if (id == R.id.contact_us) {

        }
        else if(id==R.id.feedback){

        }

        ft.commit();
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.hide();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void videoClick(View v)
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        switch (v.getId())
        {
            case R.id.cd1: ft.replace(R.id.frame,new frag_vcd1());
                break;

            case R.id.cd2:ft.replace(R.id.frame,new frag_vcd2());
                break;

            case R.id.cd3:ft.replace(R.id.frame,new frag_vcd3());
                break;

            case R.id.cd4:ft.replace(R.id.frame,new frag_vcd4());
                break;

            case R.id.cd5:ft.replace(R.id.frame,new frag_vcd5());
                break;

        }
        ft.commit();


    }
    public void algoClick(View v)
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        switch (v.getId())
        {
            case R.id.a1: ft.replace(R.id.frame,new frag_valgo1());
                break;

            case R.id.a2:ft.replace(R.id.frame,new frag_valgo2());
                break;

            case R.id.a3:ft.replace(R.id.frame,new frag_valgo3());
                break;

            case R.id.a4:ft.replace(R.id.frame,new frag_valgo4());
                break;

            case R.id.a5:ft.replace(R.id.frame,new frag_valgo5());
                break;
            case R.id.a6:ft.replace(R.id.frame,new frag_valgo6());
                break;

        }
        ft.commit();


    }
    public void vdigiClick(View v){
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        switch (v.getId())
        {
            case R.id.d1: ft.replace(R.id.frame,new frag_vdigi1());
                break;

            case R.id.d2:ft.replace(R.id.frame,new frag_vdigi2());
                break;

            case R.id.d3:ft.replace(R.id.frame,new frag_vdigi3());
                break;

            case R.id.d4:ft.replace(R.id.frame,new frag_vdigi4());
                break;

        }
        ft.commit();

    }
    public void homeClick(View v) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.h1: ft.replace(R.id.frame,new frag_vcd());
            break;
            case R.id.h2: ft.replace(R.id.frame,new frag_valgo());
            break;
            case R.id.h3: ft.replace(R.id.frame,new frag_vdigi());
            break;
            case R.id.h4: ft.replace(R.id.frame,new frag_vhtml());
                break;
            case R.id.h5: ft.replace(R.id.frame,new frag_vcss());
                break;
        }
        ft.commit();
    }

    public void vhtmlclick(View view){
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        switch (view.getId())
        {
            case R.id.d1: ft.replace(R.id.frame,new frag_vhtml1());
                break;

            case R.id.d2:ft.replace(R.id.frame,new frag_vhtml2());
                break;


        }
        ft.commit();
    }

    public void vcssclick(View view){
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        switch (view.getId())
        {
            case R.id.d1: ft.replace(R.id.frame,new frag_vcss1());
                break;

            case R.id.d2:ft.replace(R.id.frame,new frag_vcss2());
                break;


        }
        ft.commit();
    }
}
