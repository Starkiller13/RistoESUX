package com.example.utente.ristoesux;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    private Toolbar nToolbar;
    private MenuItem tmpItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nDrawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle= new ActionBarDrawerToggle(this, nDrawerLayout, R.string.open, R.string.close);
        nToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(nToolbar);
        NavigationView nView = (NavigationView) findViewById(R.id.nav_menu);
        nDrawerLayout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nView);
        /*Canteen[] info=null;
        try {
            info = Connector.canteenInfoRetriever();
        }catch(JSONException e) {}
        setContentView(R.layout.fragment_belzoni);
        TextView txt = (TextView) findViewById(R.id.textView3);
        txt.setText(info[0].name);*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(nToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer(MenuItem menuItem){
        if(tmpItem==null)
            tmpItem=menuItem;
        else
            tmpItem.setChecked(false);
        Fragment myFragment =null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.nav_1:
                fragmentClass=Belzoni.class;
                break;
            case R.id.nav_2:
                fragmentClass=Murialdo.class;
                break;
            case R.id.nav_3:
                fragmentClass=Forcellini.class;
                break;
            case R.id.nav_4:
                fragmentClass=Acli.class;
                break;
            case R.id.nav_5:
                fragmentClass=Agripolis.class;
                break;
            case R.id.nav_6:
                fragmentClass=SanFrancesco.class;
                break;
            case R.id.nav_7:
                fragmentClass=NordPiovego.class;
                break;
            default:
                fragmentClass=Belzoni.class;
        }
        try{
            myFragment = (Fragment) fragmentClass.newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frag_container, myFragment).commit();
        menuItem.setChecked(true);
        tmpItem=menuItem;
        setTitle(menuItem.getTitle());
        nDrawerLayout.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
