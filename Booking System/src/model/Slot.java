package model;

public class Slot {

    String time;

    public void Slot(String time){
        this.time = time;
    }

    private void setTime(String time){this.time = time;}
    private String getTime(){return time;}
}
