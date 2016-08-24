package com.project.sondagemocr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sondagem Descomplicada");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.principal_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
 /*
        MenuItem item1 = menu.add(0, 1, 0, "Cadastrar Sondagem");
        item1.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item2 = menu.add(0, 2, 1, "Consultar Sondagem");
        item2.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item3 = menu.add(0, 3, 2, "Consultar Turma");
        item3.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item4 = menu.add(0, 4, 3, "Relatórios");
        item4.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item5 = menu.add(0, 5, 4, "Gráficos");
        item5.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);

        MenuItem item6 = menu.add(0, 6, 5, "Sair");
        item6.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent intent= new Intent();

        switch(id) {
            case R.id.item_sondagens:
                Toast.makeText(this, "Sondagens",Toast.LENGTH_SHORT).show();
                intent = new Intent(this,MenuSondagemActivity.class);
                break;
            case R.id.item_turmas:
                Toast.makeText(this, "Turmas", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,CadastroTurmaActivity.class);
                break;
            case R.id.item_relatorio:
                Toast.makeText(this, "Relatórios", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,null);
                break;
            case R.id.item_sair:
                Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,MainActivity.class);
                break;

        }

        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        Intent intent= new Intent();

        switch(id) {
            case R.id.item_sondagens:
                Toast.makeText(this, "Sondagens",Toast.LENGTH_SHORT).show();
                intent = new Intent(this,MenuSondagemActivity.class);
                break;
            case R.id.item_turmas:
                Toast.makeText(this, "Turmas", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,CadastroTurmaActivity.class);
                break;
            case R.id.item_relatorio:
                Toast.makeText(this, "Relatórios", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,null);
                break;
            case R.id.item_sair:
                Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,MainActivity.class);
                break;

        }

        startActivity(intent);


        return true;
    }
}
