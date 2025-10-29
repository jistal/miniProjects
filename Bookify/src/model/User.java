package model;

public class User {

    private int id;
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // setters n getters

    public void setID(int id){ this.id = id; }
    public int getID(){ return id; }

    public void setUsername(String username) { this.username = username; }
    public String getUsername(){ return username; }

    public void setPassword(String password) { this.password = password; }
    public String getPassword(){ return password; }

    public void setRole(String role) { this.role = role; }
    public String getRole() { return role; }


}
