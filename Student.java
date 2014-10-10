import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Student implements Serializable {

    private String name;
    private String sId;
    private String email;

    Student(String name, String sId, String email) {
        this.name = name;

        if (isValidId(sId)) this.sId = sId;
        else System.err.println("Not a valid student id:" + sId);

        if (isValidEmail(email)) this.email = email;
        else System.err.println("Not a valid student email:" + email);
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return sId;
    }

    public String getEmail() {
        return email;
    }

    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9.]+@[a-zA-Z0-9.]+");
    }

    private boolean isValidId (String sId) {
        return sId.matches("[0-9]{8}");
    }

    public static void main (String args[]) {
        Student s = new Student("Fred", "12345678", "fred.nurk@monash.edu");
        System.out.println(s.getName() + ", " + s.getId() + ", " + s.getEmail());
    }
}
