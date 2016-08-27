package com.project.sondagemocr.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.project.sondagemocr.DissiFragment;
import com.project.sondagemocr.FraseFragment;
import com.project.sondagemocr.IdentificacaoFragment;
import com.project.sondagemocr.MonoFragment;
import com.project.sondagemocr.PoliFragment;
import com.project.sondagemocr.ResultadoFragment;
import com.project.sondagemocr.TriFragment;


public class MyFragmentPagerStateAdapter extends FragmentStatePagerAdapter{

    private String[] mTabTitles_cadastro_sondagem;

    public MyFragmentPagerStateAdapter(FragmentManager fm, String[] mTabTitles_cadastro_sondagem) {
        super(fm);
        this.mTabTitles_cadastro_sondagem = mTabTitles_cadastro_sondagem;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new IdentificacaoFragment();
            case 1:
                return new PoliFragment();
            case 2:
                return new TriFragment();
            case 3:
                return new DissiFragment();
            case 4:
                return new MonoFragment();
            case 5:
                return new FraseFragment();
            case 6:
                return new ResultadoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.mTabTitles_cadastro_sondagem.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles_cadastro_sondagem[position];
    }
}
