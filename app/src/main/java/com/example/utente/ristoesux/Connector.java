package com.example.utente.ristoesux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Utente on 17/03/2018.
 */

public class Connector {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static Canteen[] canteenInfoRetriever() throws JSONException {
        JSONObject obj=null;
        Canteen info[];
        try{
            obj = readJsonFromUrl("http://mobile.esupd.gov.it/api/reiservice.svc/canteens");
        }catch(IOException e){

        }
        info = new Canteen[7];
        String data = obj.toString();
        JSONArray jsonarray = new JSONArray(data);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            info[i].id = jsonobject.getString("id");
            info[i].name = jsonobject.getString("name");
            info[i].address = jsonobject.getString("address");
            info[i].city = jsonobject.getString("city");
            info[i].phone = jsonobject.getString("phone");
            info[i].email = jsonobject.getString("email");
            info[i].website = jsonobject.getString("website");
            info[i].type.id_t = Integer.parseInt(jsonobject.getString("type.id"));
            info[i].type.name_t = jsonobject.getString("type.name");
            info[i].type.desc_t = jsonobject.getString("type.description");
            info[i].lunchTime = jsonobject.getString("lunchTime");
            info[i].dinnerTime = jsonobject.getString("dinnerTime");
            info[i].latitude = Integer.parseInt(jsonobject.getString("latitude"));
            info[i].longitude = Integer.parseInt(jsonobject.getString("longitude"));
            info[i].active = Boolean.parseBoolean(jsonobject.getString("active"));
            info[i].menuAvailable = Boolean.parseBoolean(jsonobject.getString("menuAvailable"));
        }
        return info;

    }


}