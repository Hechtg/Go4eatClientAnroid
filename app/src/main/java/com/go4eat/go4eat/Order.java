package com.go4eat.go4eat;

/**
 * Created by Guy Hecht on 03/06/2017.
 */

public class Order {
    private String[] dishIngridients;
    private String date, dishTitle, key, notes, provider, time;
    private int userId;

    public Order(){
    }

    public Order(String[] dishIngridients, String date, String dishTitle, String key, String notes, String provider, String time, int userId){
        this.dishIngridients = dishIngridients;
        this.date = date;
        this.dishTitle = dishTitle;
        this.key = key;
        this.notes = notes;
        this.provider = provider;
        this.time = time;
        this.userId = userId;
    }

    public String[] getDishIngridients() {
        return dishIngridients;
    }

    public void setDishIngridients(String[] dishIngridients) {
        this.dishIngridients = dishIngridients;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDishTitle() {
        return dishTitle;
    }

    public void setDishTitle(String dishTitle) {
        this.dishTitle = dishTitle;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    @Override
    public String toString(){
        String str =
                date
                +"\t"+time
                +"\n\n"+provider
                +"\n\n"+dishTitle+":"
                +"\n";

      if(dishIngridients != null)
          str+= dishIngridients.toString();
      else
          str+= "No Ingridients specified!";
          str+= "\n\n\n"+"NOTES:\n"
                +notes
                +"\n\nOrder ID:"+key;
     return str;
    }

}

