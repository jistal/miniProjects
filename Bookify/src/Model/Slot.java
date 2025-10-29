package Model;

public class Slot {

    String slot, username;
    int categoryID;
    Boolean isBooked;


    public Slot(String slot, int categoryID, boolean isBooked){
        this.slot = slot;
        this.categoryID = categoryID;
        this.isBooked = isBooked;
    }

    // getters
    public String getSlot(){ return slot; }
    public int getCategoryID() { return categoryID; }
    public Boolean getAvailability() { return isBooked; };
    public String getUsername(){ return username; }

}
