package com.example.utente.ristoesux;

/**
 * Created by Utente on 17/03/2018.
 */

public class Canteen {
    public String id;
    public String name;
    public String address;
    public String city;
    public String phone;
    public String email;
    public String website;
        public class Type{
           public int id_t;
           public String name_t;
           public String desc_t;
        }
    public Type type;
    public String lunchTime;
    public String dinnerTime;
        public class Services{
            public int id_s;
            public String name_s;
            public String desc_s;
        }
    public Services[] services;
    public double latitude;
    public double longitude;
    public boolean active;
    public boolean menuAvailable;
    //public Link[] links;
    //public object waitTimeDate;
    //public object waitTime;
    public Menu menu;


    public class Lunch{
        public Maincourse[] maincourse;
        public Secondcourse[] secondcourse;
        public SideOrder[] sideorder;
        public Dessert[] dessert;
    }
    public class Dinner{
        public Maincourse[] maincourse;
        public Secondcourse[] secondcourse;
        public SideOrder[] sideorder;
        public Dessert[] dessert;

    }
    public class Menu{
        public Lunch lunch= new Lunch();
        public Dinner dinner = new Dinner();
    }

    public class Maincourse
    {
        public String id;
        public String name;
        public String link;
        public boolean vegetarian;
        public boolean celiac;
        public boolean froze;
        public boolean pdg;
    }

    public class Secondcourse
    {
        public String id;
        public String name;
        public String link;
        public boolean vegetarian;
        public boolean celiac;
        public boolean froze;
        public boolean pdg;
    }
    public class SideOrder
    {
        public String id;
        public String name;
        public String link;
        public boolean vegetarian;
        public boolean celiac;
        public boolean froze;
        public boolean pdg;
    }
    public class Dessert
    {
        public String id;
        public String name;
        public String link;
        public boolean vegetarian;
        public boolean celiac;
        public boolean froze;
        public boolean pdg;
    }



}
