package com.project.sondagemocr;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
        toolbar.setTitle("Consulta Sondagens");
        toolbar.setSubtitle("Sondagens de Alunos e Modelos");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Ativa tela anterior
        if (item.getItemId() == android.R.id.home) {
            finish();//fecha tela atual
        }

        return super.onOptionsItemSelected(item);
    }



}
