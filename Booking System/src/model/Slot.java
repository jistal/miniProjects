package model;

public class Slot {

    String time, name;
    int categoryID;


    public Slot(String name, String time, int categoryID){
        this.time = time;
        this.categoryID = categoryID;
        this.name = name;
    }

    public void setTime(String time){this.time = time;}
    public String getTime(){return time;}

    public void setCategoryID(int categoryID){ this.categoryID = categoryID;}
    public int getCategoryID() { return categoryID; }
}
