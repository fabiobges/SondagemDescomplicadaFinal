package com.project.sondagemocr.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.sondagemocr.FragmentSondagem;
import com.project.sondagemocr.FragmentSondagemModelo;
import com.project.sondagemocr.SondagemListFragment;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    private String[] mTabTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, String[] mTabTitles) {
        super(fm);
        this.mTabTitles = mTabTitles;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Fragment fragSondagem = new SondagemListFragment();
                return fragSondagem;
            case 1:
                return new FragmentSondagemModelo();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles[position];
    }
}
