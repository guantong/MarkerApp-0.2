
/**
 * Write a description of class User here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class User
{
    // instance variables - replace the example below with your own
    private String userId;
    private String password;
    private String role;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String userId, String password, String role){
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    public static void main (String args[]) {
//         String dir = System.getProperty("user.dir");
//         String name = dir + "/user1.txt";
// 
//         User u = new User(name);

    }
}
