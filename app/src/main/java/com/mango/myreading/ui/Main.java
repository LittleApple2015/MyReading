package com.mango.myreading.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import com.mango.myreading.R;
import com.mango.myreading.ui.fragment.GuokeFragment;
import com.mango.myreading.ui.fragment.NewsFragment;
import com.mango.myreading.ui.fragment.StoryFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/9/7 0007.
 */
public class Main extends FragmentActivity{

    private ViewPager m_vp;
    private GuokeFragment guokeFragment;
    private StoryFragment storyFragment;
    private NewsFragment fictionFragment;

    //页面列表
    private ArrayList<Fragment> fragmentList;
    //标题列表
    ArrayList<String> titleList    = new ArrayList<String>();
    //通过pagerTabStrip可以设置标题的属性
    private PagerTabStrip pagerTabStrip;

    //private PagerTitleStrip pagerTitleStrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //toolbar.setLogo(R.drawable.tango);
        //toolbar.setTitle("MyReading");

        m_vp = (ViewPager)findViewById(R.id.viewpager);

        pagerTabStrip=(PagerTabStrip) findViewById(R.id.pagertab);


        //设置下划线的颜色
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_dark));/*
        //设置背景的颜色
        pagerTabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

		pagerTitleStrip=(PagerTitleStrip) findViewById(R.id.pagertab);

        //设置背景的颜色
        pagerTitleStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

*/
        guokeFragment = new GuokeFragment();
        storyFragment = new StoryFragment();
        fictionFragment = new NewsFragment();

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(guokeFragment);
        fragmentList.add(storyFragment);
        fragmentList.add(fictionFragment);

        titleList.add("果壳科技 ");
        titleList.add("故事会");
        titleList.add("网易新闻 ");

        try{
            m_vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        public  MyViewPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
