package com.cookandroid.talsuke;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    TabLayout MyTabs;
    ViewPager MyPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        setTitle("탈숙이");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyPage = (ViewPager)findViewById(R.id.MyPage);

        MyTabs.setupWithViewPager(MyPage);
        setUpViewPager(MyPage);
    }

    public void setUpViewPager(ViewPager viewpage){
        MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());

        Adapter.AddFragmentPage(new Page_1(), "Page 1");
        Adapter.AddFragmentPage(new Page_2(), "Page 2");
        Adapter.AddFragmentPage(new Page_3(), "Page 3");
        Adapter.AddFragmentPage(new Page_4(), "Page 3");
        Adapter.AddFragmentPage(new Page_5(), "Page 3");

        viewpage.setAdapter(Adapter);
    }

    public class MyViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> MyFragment = new ArrayList<>();
        private List<String> MyPageTitle = new ArrayList<>();

        public MyViewPageAdapter(FragmentManager manager) {
            super(manager);
        }

        public void AddFragmentPage(Fragment Frag, String Title){
            MyFragment.add(Frag);
            MyPageTitle.add(Title);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return MyPageTitle.get(position);
        }

        @Override
        public int getCount() {
            return 5;
        }



    }

}
