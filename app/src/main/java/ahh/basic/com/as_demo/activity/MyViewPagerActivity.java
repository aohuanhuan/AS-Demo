package ahh.basic.com.as_demo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ahh.basic.com.as_demo.R;
import ahh.basic.com.as_demo.adapter.MyViewPagerAdapter;
import ahh.basic.com.as_demo.fragment.PagerFragment1;
import ahh.basic.com.as_demo.fragment.PagerFragment2;
import ahh.basic.com.as_demo.fragment.PagerFragment3;
import ahh.basic.com.as_demo.fragment.PagerFragment4;
import ahh.basic.com.as_demo.fragment.PagerFragment5;
import ahh.basic.com.as_demo.fragment.PagerFragment6;

/**
 * Created by Administrator on 2017/8/2.
 */
public class MyViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewpager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        List<Fragment> fragmentList = new ArrayList<>();

        PagerFragment1 pagerFragment1 = new PagerFragment1();
        PagerFragment2 pagerFragment2 = new PagerFragment2();
        PagerFragment3 pagerFragment3 = new PagerFragment3();
        PagerFragment4 pagerFragment4 = new PagerFragment4();
        PagerFragment5 pagerFragment5 = new PagerFragment5();
        PagerFragment6 pagerFragment6 = new PagerFragment6();

        fragmentList.add(pagerFragment1);
        fragmentList.add(pagerFragment2);
        fragmentList.add(pagerFragment3);
        fragmentList.add(pagerFragment4);
        fragmentList.add(pagerFragment5);
        fragmentList.add(pagerFragment6);

        List<String> titles = new ArrayList<>();
        titles.add("第一页");
        titles.add("第二页");
        titles.add("第三页");
        titles.add("第四页");
        titles.add("第五页");
        titles.add("第六页");


        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(myViewPagerAdapter);

        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        Toast.makeText(this, "当前是第 " + (position + 1) + " 页。", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }
}