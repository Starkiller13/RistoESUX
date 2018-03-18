package com.example.utente.ristoesux;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout nDrawerLayout;
    private ActionBarDrawerToggle nToggle;
    private Toolbar nToolbar;
    private MenuItem tmpItem;
    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> canteenList;

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

        canteenList = new ArrayList<>(7);
        lv = (ListView) findViewById(R.id.list);
        new GetCanteens().execute();
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
    private class GetCanteens extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String uri = "http://mobile.esupd.gov.it/api/reiservice.svc/canteens";
            String jsonStr = sh.makeServiceCall(uri);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                int k=1;
                try {

                    // Getting JSON Array node
                    JSONArray canteens = new JSONArray(jsonStr);
                    Log.e(TAG,"Array length " + canteens.length());
                    // looping through All Contacts
                    for (int i = 0; i < canteens.length(); i++) {
                        JSONObject c = canteens.getJSONObject(i);
                        String id = c.getString("id");
                        String name = c.getString("name");
                        String address = c.getString("address");
                        String city = c.getString("city");
                        String phone = c.getString("phone");
                        String email = c.getString("email");
                        String website = c.getString("website");
                        JSONObject type = c.getJSONObject("type");
                        String id_t = type.getString("id");
                        String name_t = type.getString("name");
                        String description_t = type.getString("description");
                        String lunchTime = c.getString("lunchTime");
                        if(lunchTime==null) {
                            lunchTime = "Orario non disponibile";
                        }
                        String dinnerTime = c.getString("dinnerTime");
                        if(dinnerTime==null) {
                            dinnerTime = "Orario non disponibile";
                        }
                        /*JSONArray services = c.getJSONArray("services");
                            String[] id_s= new String[3];
                            String[] name_s= new String[3];
                            String[] description_s= new String[3];
                            for (int j = 0; j < services.length(); j++) {
                                id_s[j]=services.getString(j);
                                name_s[j]=services.getString(j+1);
                                description_s[j]=services.getString(j+2);
                            }*/
                        String latitude = c.getString("latitude");
                        String longitude = c.getString("longitude");
                        boolean active = c.getBoolean("active");
                        boolean menuAvailable = c.getBoolean("menuAvailable");
                        /*JSONArray links = c.getJSONArray("links");
                            String[] id_l= new String[3];
                            String[] name_l= new String[3];
                            String[] description_l= new String[3];
                            String[] link_l= new String[3];
                            for (int j = 0; j < services.length(); j++) {
                                id_l[j]=services.getString(j);
                                name_l[j]=services.getString(j+1);
                                description_l[j]=services.getString(j+2);
                                link_l[j]=services.getString(j+3);
                            }
                           */
                        String waitTimeDate = c.getString("waitTimeDate");
                        String waitTime = c.getString("waitTime");
                            if(waitTime==null){
                                waitTime="Tempo di attesa non disponibile";
                            }else{
                                waitTime=waitTime + " minuti";
                            }
                        /*JSONObject menu = new JSONObject("menu");
                            JSONObject lunch= menu.getJSONObject("lunch");
                                JSONArray maincourse = lunch.getJSONArray("maincourse");
                            JSONObject dinner= menu.getJSONObject("dinner");
                        */

                        // tmp hash map for single contact
                        HashMap<String, String> canteen = new HashMap<>();
                        // adding each child node to HashMap key => value
                        canteen.put("id", id);
                        canteen.put("name", name);
                        canteen.put("lunchTime", lunchTime);
                        canteen.put("dinnerTime", dinnerTime);

                        // adding contact to contact list
                        canteenList.add(canteen);
                        Log.e(TAG,"Iterazione: " + k);
                        k++;
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, canteenList,
                    R.layout.list_item, new String[]{"name","lunchTime","dinnerTime"},
                    new int[]{R.id.nome, R.id.lunchTime, R.id.dinnerTime});

            lv.setAdapter(adapter);
        }

    }
}
