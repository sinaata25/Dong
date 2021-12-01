package ataie.sina.dong.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Nav_Adapter extends FragmentPagerAdapter {
    List<Fragment> list;

    public Nav_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        list=new ArrayList<>();

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addfragment(Fragment fragment){
        try {
            list.add(fragment);
        }catch (Exception e){
            Log.i("TAG", "Error pager add: "+e.getMessage());
        }


    }





}
