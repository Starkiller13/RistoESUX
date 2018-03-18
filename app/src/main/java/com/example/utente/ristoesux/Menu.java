package com.example.utente.ristoesux;

/**
 * Created by Utente on 18/03/2018.
 */

public class Menu {
    public Menu(){
        lunch = new Lunch();
        dinner= new Dinner();
    }
    public Lunch lunch;
    public Dinner dinner;
    public class Lunch {
        public Lunch(){
            l_index_maincourse=0;
            l_index_secondcourse=0;
            l_index_sideorder=0;
            l_index_dessert=0;
            lunch_maincourse = new Order[10];
            lunch_secondcourse = new Order[10];
            lunch_sideorder = new Order[10];
            lunch_dessert = new Order[10];
        }
        public void increment_index(String index){
            if(index.equalsIgnoreCase("maincourse")){
                l_index_maincourse++;
            }else if(index.equalsIgnoreCase("secondcourse")){
                l_index_secondcourse++;
            }else if(index.equalsIgnoreCase("sideorder")){
                l_index_sideorder++;
            }else if(index.equalsIgnoreCase("dessert")){
                l_index_dessert++;
            }
        }
        private int l_index_maincourse;
        private int l_index_secondcourse;
        private int l_index_sideorder;
        private int l_index_dessert;
        public Order[] lunch_maincourse;
        public Order[] lunch_secondcourse;
        public Order[] lunch_sideorder;
        public Order[] lunch_dessert;
    }
    public class Dinner
    {
        public Dinner(){
            d_index_maincourse=0;
            d_index_secondcourse=0;
            d_index_sideorder=0;
            d_index_dessert=0;
            dinner_maincourse = new Order[10];
            dinner_secondcourse = new Order[10];
            dinner_sideorder = new Order[10];
            dinner_dessert = new Order[10];
        }
        public void increment_index(String index){
            if(index.equalsIgnoreCase("maincourse")){
                d_index_maincourse++;
            }else if(index.equalsIgnoreCase("secondcourse")){
                d_index_secondcourse++;
            }else if(index.equalsIgnoreCase("sideorder")){
                d_index_sideorder++;
            }else if(index.equalsIgnoreCase("dessert")){
                d_index_dessert++;
            }
        }
        private int d_index_maincourse;
        private int d_index_secondcourse;
        private int d_index_sideorder;
        private int d_index_dessert;
        public Order[] dinner_maincourse;
        public Order[] dinner_secondcourse;
        public Order[] dinner_sideorder;
        public Order[] dinner_dessert;
    }
}
