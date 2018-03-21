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
        public Lunch() {
            index = new int[]{0,0,0,0};
            maincourse = new String[10];
            secondcourse = new String[10];
            sideorder = new String[10];
            dessert = new String[10];
            maincourse[0]="Non disponibile";
            secondcourse[0]="Non disponibile";
            sideorder[0]="Non disponibile";
            dessert[0]="Non disponibile";
        }

        public void increment_index(int i) {
            index[i]++;
        }

        public String[] getString(){
            if(maincourse!=null&&secondcourse!=null&&sideorder!=null&&dessert!=null) {
            }
            return null;
        }


        public int[] index;
        public String[] maincourse;
        public String[] secondcourse;
        public String[] sideorder;
        public String[] dessert;
    }
    public class Dinner {
        public Dinner() {
            index = new int[]{0,0,0,0};
            maincourse = new String[10];
            maincourse[0]="Non disponibile";
            secondcourse = new String[10];
            secondcourse[0]="Non disponibile";
            sideorder = new String[10];
            sideorder[0]="Non disponibile";
            dessert = new String[10];
            dessert[0]="Non disponibile";
        }

        public void increment_index(int i) {
            index[i]++;
        }

        public String[] getString(){
            if(maincourse!=null&&secondcourse!=null&&sideorder!=null&&dessert!=null) {
            }
            return null;
        }

        public int[] index;
        public String[] maincourse;
        public String[] secondcourse;
        public String[] sideorder;
        public String[] dessert;
    }
}
