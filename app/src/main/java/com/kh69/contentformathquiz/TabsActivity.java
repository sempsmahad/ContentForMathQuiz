package com.kh69.contentformathquiz;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class TabsActivity extends AppCompatActivity {

    private ViewPager view_pager;
    private SectionsPagerAdapter viewPagerAdapter;
    private TabLayout tab_layout;
    public static Question sQuestion;
    public static final int[] tabIcon = {R.drawable.ic_katex, R.drawable.ic_latex};


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_icon);

        initToolbar();
        initComponent();

    }
    private void initToolbar() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        setupViewPager(view_pager);
        tab_layout.setupWithViewPager(view_pager);
        for (int i = 0; i < tabIcon.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.custom_tab,null);
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            view.findViewById(R.id.icon).setBackgroundResource(tabIcon[i]);
            if(tab!=null) tab.setCustomView(view);
        }

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(FragmentKatex.newInstance(), "Katex");    // index 0
        viewPagerAdapter.addFragment(FragmentLatex.newInstance(), "Latex");   // index 1
        viewPager.setAdapter(viewPagerAdapter);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public String getTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

}
