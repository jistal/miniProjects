package Model;

public class User {

    private String username, password, role;

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //  getters
    public String getUsername(){ return username; }
    public String getPassword(){ return password; }
    public String getRole() { return role; }


}
