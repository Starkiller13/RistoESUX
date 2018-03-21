package com.example.utente.ristoesux;

import android.app.Activity;
import android.content.Context;
import android.location.OnNmeaMessageListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FragmentMensa extends Fragment implements MainActivity.OnUpdateListener, MainActivity.OnMenuItemChecked {
    private TextView a;
    private String TAG = FragmentMensa.class.getSimpleName();
    private TextView b;
    private TextView c;
    private TextView d;
    private Menu[] x;
    private View v;
    String[] k;
    private String mensa;
    private final String[] MENSE = new String[]{"Belzoni","Murialdo","Forcellini","Acli","Agripolis","SanFrancesco","NordPiovego"};

    public FragmentMensa() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment, container, false);
        a = (TextView) v.findViewById(R.id.list_1);
        k = menuScomposer(x,indexSelector());
        a.setText(k[0]);
        return  v;
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

    }

    @Override
    public void onUpdate(Menu[] a) {
        if(a!=null)
            x=a;
    }
    @Override
    public void onCheck(String item) {
        mensa = item;
        Log.e(TAG, mensa);
    }
    public int indexSelector(){
        int i=-1;
        while(mensa!=null){
        for(int r=0;r<MENSE.length;r++){
            if(mensa.equalsIgnoreCase(MENSE[r]));
                i=r;
            break;
        }}
        return i;
    }
    public String[] menuScomposer(Menu[] y, int index){
        String[] xxx = new String[8];
        if(y!=null) {
            for (int i = 0; i < 8; i++) {
                if (i == 0) {
                    String f="";

                    if(y[index].lunch.index[0]>0) {
                        for (int l = 0; l < y[index].lunch.index[0]; l++) {
                            f = f + "/n" + y[index].lunch.maincourse[l];
                        }
                        xxx[0] = f;
                    }else{
                        xxx[0] = "Non Disponibile";
                    }
                    Log.e(TAG,"Diogay");
                }
                if (i == 1) {
                    String f="";
                    if(y[index].lunch.index[1]>0) {
                        for (int l = 0; l < y[index].lunch.index[1]; l++) {
                            f = f + "/n" + y[index].lunch.secondcourse[l];
                        }
                        xxx[1] = f;
                    }else{
                        xxx[1] = "Non Disponibile";
                    }
                }
                if (i == 2) {
                    String f="";
                    if(y[index].lunch.index[2]>0) {
                        for (int l = 0; l < y[index].lunch.index[2]; l++) {
                            f = f + "/n" + y[index].lunch.sideorder[l];
                        }
                        xxx[2] = f;
                    }else{
                        xxx[2] = "Non Disponibile";
                    }
                }
                if (i == 3) {
                    String f="";
                    if(y[index].lunch.index[3]>0) {
                        for (int l = 0; l < y[index].lunch.index[3]; l++) {
                            f = f + "/n" + y[index].lunch.dessert[l];
                        }
                        xxx[3] = f;
                    }else{
                        xxx[3] = "Non Disponibile";
                    }
                }
                if (i == 4) {
                    String f="";
                    if(y[index].dinner.index[0]>0) {
                        for (int l = 0; l < y[index].dinner.index[0]; l++) {
                            f = f + "/n" + y[index].dinner.maincourse[l];
                        }
                        xxx[4] = f;
                    }else{
                        xxx[4] = "Non Disponibile";
                    }
                }
                if (i == 5) {
                    String f="";
                    if(y[index].dinner.index[1]>0) {
                        for (int l = 0; l < y[index].dinner.index[1]; l++) {
                            f = f + "/n" + y[index].dinner.secondcourse[l];
                        }
                        xxx[5] = f;
                    }else{
                        xxx[5] = "Non Disponibile";
                    }
                }
                if (i == 6) {
                    String f="";
                    if(y[index].dinner.index[2]>0) {
                        for (int l = 0; l < y[index].dinner.index[2]; l++) {
                            f = f + "/n" + y[index].dinner.sideorder[l];
                        }
                        xxx[6] = f;
                    }else{
                        xxx[6] = "Non Disponibile";
                    }
                }
                if (i == 7) {
                    String f="";
                    if(y[index].dinner.index[3]>0) {
                        for (int l = 0; l < y[index].dinner.index[3]; l++) {
                            f = f + "/n" + y[index].dinner.dessert[l];
                        }
                        xxx[7] = f;
                    }else{
                        xxx[7] = "Non Disponibile";
                    }
                }
            }
        }
        else
            xxx = new String[]{"diogay"};
        return xxx;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
