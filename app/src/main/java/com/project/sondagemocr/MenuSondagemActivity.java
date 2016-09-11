package com.project.sondagemocr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.project.sondagemocr.Adapters.MyFragmentPagerAdapter;

public class MenuSondagemActivity extends AppCompatActivity implements View.OnClickListener  {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sondagem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fabMenuSondagem);
        floatingActionButton.setOnClickListener(this);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titles_tab)));

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        if(v == floatingActionButton){
            if(mViewPager.getCurrentItem() == 0) {
                Intent intent = new Intent(this, CadastroSondagemActivity.class);
                startActivity(intent);
            }else if(mViewPager.getCurrentItem() == 1){
                Intent intent = new Intent(this, CadastroSondagemModActivity.class);
                startActivity(intent);
            }

        }
    }
}
