package com.gl.newtitlegaolei;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gl.newtitlegaolei.Fragment.FragmentAttention;
import com.gl.newtitlegaolei.Fragment.FragmentHome;
import com.gl.newtitlegaolei.Fragment.FragmentMy;
import com.gl.newtitlegaolei.Fragment.FragmentVideo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_main_my;
    private Button btn_main_attention;
    private Button btn_main_video;
    private Button btn_main_home;
    private FragmentManager fragmentManager;
    private FragmentHome fragmentHome;
    private FragmentVideo fragmentVideo;
    private FragmentAttention fragmentAttention;
    private FragmentMy fragmentMy;
    private FragmentTransaction transaction;
    List<ShowFragment> fragmentList = new ArrayList<ShowFragment>();
    private ShowFragment showFragment;

    private Fragment fragment;
    FragmentMy myFragment;
    FragmentHome homeFreagment;
    FragmentVideo videoFragment;
    FragmentAttention attentionFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgit();
        initDianji();
        addfragment(new FragmentHome());
    }


    private void initWidgit() {
        btn_main_home = (Button) findViewById(R.id.btn_main_home);
        btn_main_video = (Button) findViewById(R.id.btn_main_video);
        btn_main_attention = (Button) findViewById(R.id.btn_main_attention);
        btn_main_my = (Button) findViewById(R.id.btn_main_my);

        fragmentManager = getSupportFragmentManager();
        fragmentHome = new FragmentHome();
        fragmentVideo = new FragmentVideo();
        fragmentAttention = new FragmentAttention();
        fragmentMy = new FragmentMy();
    }

    public void addlist() {
        btn_main_home.setSelected(true);
        for (int i = 0; i < 4; i++) {
            showFragment = new ShowFragment();
            switch (i) {
                case 0:
                    showFragment.fragment = fragmentHome;
                    break;
                case 1:
                    showFragment.fragment = fragmentVideo;
                    break;
                case 2:
                    showFragment.fragment = fragmentAttention;
                    break;
                case 3:
                    showFragment.fragment = fragmentMy;
                    break;
            }
            fragmentList.add(showFragment);
        }
    }

    public void addfragment(Fragment f) {
        FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
        if(!f.isAdded()){
            fr.replace(R.id.frag_main_content, f);
        }
        if(fragment!=null){
            fr.hide(fragment);
        }
       fr.show(f);
        fr.commit();
        fragment=f;
    }

    private void initDianji() {
        btn_main_home.setOnClickListener(this);
        btn_main_video.setOnClickListener(this);
        btn_main_attention.setOnClickListener(this);
        btn_main_my.setOnClickListener(this);
        addlist();
    }

    public void setStaue(int id){
        switch (id){
            case R.id.btn_main_my:
                btn_main_my.setSelected(true);
                btn_main_attention.setSelected(false);
                btn_main_video.setSelected(false);
                btn_main_home.setSelected(false);

                break;
            case R.id.btn_main_home:
                btn_main_my.setSelected(false);
                btn_main_attention.setSelected(false);
                btn_main_video.setSelected(false);
                btn_main_home.setSelected(true);

                break;
            case R.id.btn_main_video:
                btn_main_my.setSelected(false);
                btn_main_attention.setSelected(false);
                btn_main_video.setSelected(true);
                btn_main_home.setSelected(false);

                break;
            case R.id.btn_main_attention:
                btn_main_my.setSelected(false);
                btn_main_attention.setSelected(true);
                btn_main_video.setSelected(false);
                btn_main_home.setSelected(false);

                break;
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_main_home:
                setStaue(R.id.btn_main_home);
                if(homeFreagment!=null){
                    homeFreagment=new FragmentHome();
                }
                addfragment(new FragmentHome());
                btn_main_my.setTextColor(Color.BLACK);
                btn_main_attention.setTextColor(Color.BLACK);
                btn_main_home.setTextColor(Color.RED);
                btn_main_video.setTextColor(Color.BLACK);
                break;
            case R.id.btn_main_video:
                setStaue(R.id.btn_main_video);
                if(videoFragment!=null){
                    videoFragment=new FragmentVideo();
                }
                addfragment(new FragmentVideo());
                btn_main_my.setTextColor(Color.BLACK);
                btn_main_attention.setTextColor(Color.BLACK);
                btn_main_home.setTextColor(Color.BLACK);
                btn_main_video.setTextColor(Color.RED);
                break;
            case R.id.btn_main_attention:
                setStaue(R.id.btn_main_attention);
                if(attentionFragment!=null){
                    attentionFragment=new FragmentAttention();
                }
                addfragment(new FragmentAttention());
                btn_main_video.setTextColor(Color.BLACK);
                btn_main_home.setTextColor(Color.BLACK);
                btn_main_my.setTextColor(Color.BLACK);
                btn_main_attention.setTextColor(Color.RED);
                break;

            case R.id.btn_main_my:
                setStaue(R.id.btn_main_my);
                if(myFragment==null){
                    myFragment = new FragmentMy();
                }
                addfragment(myFragment);
                btn_main_attention.setTextColor(Color.BLACK);
                btn_main_video.setTextColor(Color.BLACK);
                btn_main_home.setTextColor(Color.BLACK);
                btn_main_my.setTextColor(Color.RED);
                break;
        }
    }


}

