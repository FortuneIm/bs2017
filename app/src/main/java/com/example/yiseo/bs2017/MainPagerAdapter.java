package com.example.yiseo.bs2017;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MainPagerAdapter extends FragmentPagerAdapter{

    private static int PAGE_NUMBER = 2;

    public MainPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return tabBookShelf.newInstance();
            case 1:
                return tabWishList.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return PAGE_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "MyBookShelf";
            case 1:
                return "WishList";
            default:
                return null;
        }
    }
}
