package ataie.sina.dong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import ataie.sina.dong.adapters.Nav_Adapter;
import ataie.sina.dong.fragments.Friends;
import ataie.sina.dong.fragments.Home;
import ataie.sina.dong.fragments.Personal;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Nav_Adapter navAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetupViews();
        Handle_Bottom_Navs();
    }


    void SetupViews(){
        viewPager=findViewById(R.id.pager);
        tabLayout=findViewById(R.id.tablayout);
    }

    void Handle_Bottom_Navs(){
        viewPager.setAdapter(navAdapter);
        navAdapter=new Nav_Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        navAdapter.addfragment(new Friends());
        navAdapter.addfragment(new Home());
        navAdapter.addfragment(new Personal());
        viewPager.setAdapter(navAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Set_Tablayout_Icons();
        viewPager.setCurrentItem(1);

    }

    void Set_Tablayout_Icons(){
        tabLayout.getTabAt(0).setIcon(R.drawable.people);
        tabLayout.getTabAt(1).setIcon(R.drawable.home);
        tabLayout.getTabAt(2).setIcon(R.drawable.user);

    }





}