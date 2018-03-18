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
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
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
    private Menu menu_belzoni;
    private Menu menu_murialdo;
    private Menu menu_agripolis;
    private Menu menu_acli;
    private Menu menu_sanfrancesco;
    private Menu menu_piovego;
    private Menu menu_forcellini;




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
        new GetCanteens().execute();
        lv = (ListView) findViewById(R.id.list);
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
        private int servicecounter=0;
        private int linkcounter=0;
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
                int k = 1;
                try {
                    // Getting JSON Array node
                    JSONArray canteens = new JSONArray(jsonStr);
                    Log.e(TAG, "Array length " + canteens.length());
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
                        if (lunchTime.equalsIgnoreCase("null")) {
                            lunchTime = "Orario non disponibile";
                        }
                        String dinnerTime = c.getString("dinnerTime");
                        if (dinnerTime.equalsIgnoreCase("null")) {
                            dinnerTime = "Orario non disponibile";
                        }
                        JSONArray services = c.getJSONArray("services");
                        String[] id_s = new String[6];
                        String[] name_s = new String[6];
                        String[] description_s = new String[6];

                        for (int j = 0; j < services.length(); j++) {
                            JSONObject s = services.getJSONObject(j);
                            id_s[j] = s.getString("id");
                            name_s[j] = s.getString("name");
                            description_s[j] = s.getString("description");
                            servicecounter++;
                        }
                        String latitude = c.getString("latitude");
                        String longitude = c.getString("longitude");
                        String active = c.getString("active");
                        String menuAvailable = c.getString("menuAvailable");
                        JSONArray links = c.getJSONArray("links");
                        String[] id_l = new String[6];
                        String[] name_l = new String[6];
                        String[] description_l = new String[6];
                        String[] link_l = new String[6];
                        for (int j = 0; j < links.length(); j++) {
                            JSONObject l = links.getJSONObject(j);
                            id_l[j] = l.getString("id");
                            name_l[j] = l.getString("name");
                            description_l[j] = l.getString("description");
                            link_l[j] = l.getString("link");
                            linkcounter++;
                        }

                        String waitTimeDate = c.getString("waitTimeDate");
                        String waitTime = c.getString("waitTime");
                        if (waitTime == null) {
                            waitTime = "Tempo di attesa non disponibile";
                        } else {
                            waitTime = waitTime + " minuti";
                        }
                        if (Boolean.parseBoolean(menuAvailable) == true) {
                            Menu menu_x = new Menu();
                            JSONObject menu = c.getJSONObject("menu");
                            try {

                                JSONObject lunch = menu.getJSONObject("lunch");
                                JSONArray maincourse = lunch.getJSONArray("maincourse");

                                Log.e(TAG, "Array length " + maincourse.length());
                                for (int j = 0; j < maincourse.length(); j++) {
                                    JSONObject d = maincourse.getJSONObject(j);
                                    menu_x.lunch.lunch_maincourse[j] = new Order();
                                    menu_x.lunch.lunch_maincourse[j].id = d.getString("id");
                                    menu_x.lunch.lunch_maincourse[j].name = d.getString("name");
                                        /*menu_x.lunch.lunch_maincourse[j].link = d.getString("link");
                                        menu_x.lunch.lunch_maincourse[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.lunch.lunch_maincourse[j].celiac = d.getBoolean("celiac");
                                        menu_x.lunch.lunch_maincourse[j].frozen = d.getBoolean("frozen");
                                        menu_x.lunch.lunch_maincourse[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.lunch.increment_index("maincourse");

                                }
                                JSONArray secondcourse = lunch.getJSONArray("secondcourse");

                                Log.e(TAG, "Array length " + secondcourse.length());
                                for (int j = 0; j < secondcourse.length(); j++) {
                                    JSONObject d = secondcourse.getJSONObject(j);
                                    menu_x.lunch.lunch_secondcourse[j] = new Order();
                                    menu_x.lunch.lunch_secondcourse[j].id = d.getString("id");
                                    menu_x.lunch.lunch_secondcourse[j].name = d.getString("name");
                                        /*menu_x.lunch.lunch_secondcourse[j].link = d.getString("link");
                                        menu_x.lunch.lunch_secondcourse[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.lunch.lunch_secondcourse[j].celiac = d.getBoolean("celiac");
                                        menu_x.lunch.lunch_secondcourse[j].frozen = d.getBoolean("frozen");
                                        menu_x.lunch.lunch_secondcourse[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.lunch.increment_index("secondcourse");
                                }

                                JSONArray sideorder = lunch.getJSONArray("sideorder");

                                Log.e(TAG, "Array length " + sideorder.length());
                                for (int j = 0; j < sideorder.length(); j++) {
                                    JSONObject d = sideorder.getJSONObject(j);
                                    menu_x.lunch.lunch_sideorder[j] = new Order();
                                    menu_x.lunch.lunch_sideorder[j].id = d.getString("id");
                                    menu_x.lunch.lunch_sideorder[j].name = d.getString("name");
                                    menu_x.lunch.lunch_sideorder[j].link = d.getString("link");
                                        /*menu_x.lunch.lunch_sideorder[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.lunch.lunch_sideorder[j].celiac = d.getBoolean("celiac");
                                        menu_x.lunch.lunch_sideorder[j].frozen = d.getBoolean("frozen");
                                        menu_x.lunch.lunch_sideorder[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.lunch.increment_index("sideorder");
                                }

                                JSONArray dessert = lunch.getJSONArray("dessert");

                                Log.e(TAG, "Array length " + dessert.length());
                                for (int j = 0; j < dessert.length(); j++) {
                                    JSONObject d = dessert.getJSONObject(j);
                                    menu_x.lunch.lunch_dessert[j] = new Order();
                                    menu_x.lunch.lunch_dessert[j].id = d.getString("id");
                                    menu_x.lunch.lunch_dessert[j].name = d.getString("name");
                                    menu_x.lunch.lunch_dessert[j].link = d.getString("link");
                                        /*menu_x.lunch.lunch_dessert[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.lunch.lunch_dessert[j].celiac = d.getBoolean("celiac");
                                        menu_x.lunch.lunch_dessert[j].frozen = d.getBoolean("frozen");
                                        menu_x.lunch.lunch_dessert[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.lunch.increment_index("dessert");

                                }
                            }catch(JSONException g){}
                            try{
                                JSONObject dinner = menu.getJSONObject("dinner");
                                JSONArray maincourse_d;
                                maincourse_d = dinner.getJSONArray("maincourse");
                                Log.e(TAG, "Array length " + maincourse_d.length());
                                for (int j = 0; j < maincourse_d.length(); j++) {
                                    JSONObject d = maincourse_d.getJSONObject(j);
                                    menu_x.dinner.dinner_maincourse[j] = new Order();
                                    menu_x.dinner.dinner_maincourse[j].id = d.getString("id");
                                    menu_x.dinner.dinner_maincourse[j].name = d.getString("name");
                                        /*menu_x.dinner.dinner_maincourse[j].link = d.getString("link");
                                        menu_x.dinner.dinner_maincourse[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.dinner.dinner_maincourse[j].celiac = d.getBoolean("celiac");
                                        menu_x.dinner.dinner_maincourse[j].frozen = d.getBoolean("frozen");
                                        menu_x.dinner.dinner_maincourse[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.dinner.increment_index("maincourse");
                                }
                                JSONArray secondcourse_d = dinner.getJSONArray("secondcourse");

                                Log.e(TAG, "Array length " + secondcourse_d.length());
                                for (int j = 0; j < secondcourse_d.length(); j++) {
                                    JSONObject d = secondcourse_d.getJSONObject(j);
                                    menu_x.dinner.dinner_secondcourse[j] = new Order();
                                    menu_x.dinner.dinner_secondcourse[j].id = d.getString("id");
                                    menu_x.dinner.dinner_secondcourse[j].name = d.getString("name");
                                        /*menu_x.dinner.dinner_secondcourse[j].link = d.getString("link");
                                        menu_x.dinner.dinner_secondcourse[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.dinner.dinner_secondcourse[j].celiac = d.getBoolean("celiac");
                                        menu_x.dinner.dinner_secondcourse[j].frozen = d.getBoolean("frozen");
                                        menu_x.dinner.dinner_secondcourse[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.dinner.increment_index("secondcourse");
                                }

                                JSONArray sideorder_d = dinner.getJSONArray("sideorder");

                                Log.e(TAG, "Array length " + sideorder_d.length());
                                for (int j = 0; j < sideorder_d.length(); j++) {
                                    JSONObject d = sideorder_d.getJSONObject(j);
                                    menu_x.dinner.dinner_sideorder[j] = new Order();
                                    menu_x.dinner.dinner_sideorder[j].id = d.getString("id");
                                    menu_x.dinner.dinner_sideorder[j].name = d.getString("name");
                                        /*menu_x.dinner.dinner_sideorder[j].link = d.getString("link");
                                        menu_x.dinner.dinner_sideorder[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.dinner.dinner_sideorder[j].celiac = d.getBoolean("celiac");
                                        menu_x.dinner.dinner_sideorder[j].frozen = d.getBoolean("frozen");
                                        menu_x.dinner.dinner_sideorder[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.dinner.increment_index("sideorder");
                                }

                                JSONArray dessert_d = dinner.getJSONArray("dessert");

                                Log.e(TAG, "Array length " + dessert_d.length());
                                for (int j = 0; j < dessert_d.length(); j++) {
                                    JSONObject d = dessert_d.getJSONObject(j);
                                    menu_x.dinner.dinner_dessert[j] = new Order();
                                    menu_x.dinner.dinner_dessert[j].id = d.getString("id");
                                    menu_x.dinner.dinner_dessert[j].name = d.getString("name");
                                        /*menu_x.dinner.dinner_dessert[j].link = d.getString("link");
                                        menu_x.dinner.dinner_dessert[j].vegetarian = d.getBoolean("vegetarian");
                                        menu_x.dinner.dinner_dessert[j].celiac = d.getBoolean("celiac");
                                        menu_x.dinner.dinner_dessert[j].frozen = d.getBoolean("frozen");
                                        menu_x.dinner.dinner_dessert[j].pdg = d.getBoolean("pdg");*/
                                    menu_x.dinner.increment_index("dessert");
                                }
                            } catch (JSONException f) {
                            }
                        }



                        // tmp hash map for single contact
                        HashMap<String, String> canteen = new HashMap<>();
                        // adding each child node to HashMap key => value
                        canteen.put("id", id);
                        canteen.put("name", name);
                        canteen.put("address", address);
                        canteen.put("city", city);
                        canteen.put("phone", phone);
                        canteen.put("email", email);
                        canteen.put("website", website);
                        canteen.put("id_t", id_t);
                        canteen.put("name_t", name_t);
                        canteen.put("description_t", description_t);
                        canteen.put("lunchTime", lunchTime);
                        canteen.put("dinnerTime", dinnerTime);
                        for(int p=0;p<servicecounter;p++){
                            canteen.put("id_s"+p, id_s[p]);
                            canteen.put("name_s"+p, name_s[p]);
                            canteen.put("description_s"+p, description_s[p]);
                        }
                        canteen.put("latitude",latitude);
                        canteen.put("longitude",longitude);
                        canteen.put("active",active);
                        canteen.put("menuAvailable",menuAvailable);
                        for(int p=0;p<linkcounter;p++){
                            canteen.put("id_l"+p, id_l[p]);
                            canteen.put("name_l"+p, name_l[p]);
                            canteen.put("description_l"+p, description_l[p]);
                            canteen.put("link_l"+p, link_l[p]);
                        }
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
