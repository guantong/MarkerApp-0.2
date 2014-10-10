import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 1:53 PM
 * This MarkerApp class is the main class which can assess the assignments of students in target units.
 * The detailed guidline of the use of this class is found in "README_forstudents.txt" in the top directory.
 */
public class MarkerApp {

    // The assignment object to be shared in this class
    private Assignment assignment;
    private User user;

    /**
     * This function iteratively performs user actions according to user commends.
     * Depending on a particular input character, a function will be performed.
     * However, please ensure that each function performs correctly with appropriate
     * input parameters.
     */
    private void UILoop() {

        String line = "";
        while (true) {
            // Show a message to a user
            System.out.println("Enter Command:");

            // The system ask a user a commend
            line = new Scanner(System.in).nextLine().trim();

            // If the command is 'enter' or nothing, we do nothing.
            if (line.equalsIgnoreCase("")) continue;

            String commandList[] = line.split(" ");
            char command = commandList[0].charAt(0);
            String arguments[] = new String[commandList.length-1];
            System.arraycopy(commandList, 1, arguments, 0, arguments.length);

            boolean result = false;
            switch (command) {
                case 'q':
                // exit the program
                result = quitFn();
                break;
                case 'c':
                // create a marking report
                createFn(arguments);
                break;
                case 's':
                // save the current marking report
                saveFn(arguments);
                break;
                case 'l':
                // load the specified marking report
                loadFn(arguments);
                break;
                case 'm':
                // invork the marking process
                markFn(arguments);
                break;
                case 'p':
                // print the current status of marking
                statusFn(arguments);
                break;
                case 'r':
                // report the current marking status into a file
                reportFn(arguments);
                break;
                case 'o':
                //log out
                result = logout();
                break;
                case 'w':
                //withheld student mark
                withheld(arguments);  
                break;
                default:
                System.err.println(command + " is not a valid command! Try again!");
                break;
            }
            if (result) break;
        }
    }

    private boolean quitFn() {
        System.out.println("Quitting.  Hope you saved the assignment first.");
        return true;
    }

    private boolean createFn(String[] arguments) {
        if (arguments.length != 2) {
            System.out.print("You must specify a spec file and a student list");
            return false;
        } else {
            String assignFile = arguments[0];
            String studFile = arguments[1];
            assignment = new Assignment(assignFile, studFile);
            return false;
        }
    }

    private boolean markFn (String[] arguments) {
        if (assignment != null) assignment.markUI();
        else System.out.println("You must create or load an assignment first!");
        return false;
    }

    private boolean saveFn (String arguments[]) {
        String name = arguments[0];

        try {
            FileOutputStream fout = new FileOutputStream(name, true);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(assignment);
            oos.close();
            fout.close();

            System.out.println(name + " has been successfully saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean loadFn (String arguments[]) {
        String name = arguments[0];

        try {
            FileInputStream fin = new FileInputStream(name);
            ObjectInputStream  ois = new ObjectInputStream(fin);
            assignment = (Assignment) ois.readObject();
            ois.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean statusFn (String arguments[]) {
        if (assignment != null)
            assignment.printStatus(new PrintStream(System.out));
        else
            System.out.println("Please create or load an assignment file first!");
        return false;
    }

    private boolean reportFn (String arguments[]) {
        if (assignment != null)
            assignment.makeReports(arguments[0]);
        else
            System.out.println("Please create or load an assignment file first!");
        return false;
    }

    private boolean login(){

        String inputLine = "";
        String dir = System.getProperty("user.dir");
        String name = dir + "/user1.txt";
        ArrayList<User> users = new ArrayList<User>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String fields[] = line.split(",");

                User user = new User (fields[0], fields[1], fields[2]);
                users.add(user);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        boolean loginOK = false;

        while (loginOK == false){
            System.out.println("Enter User Id and password:");
            inputLine = new Scanner(System.in).nextLine();

            if (inputLine.equalsIgnoreCase("")) {
                continue;}

            String inputList[] = inputLine.split(" ");
            String userId = inputList[0];
            String password = inputList[1];

            for (User user: users){
                if (user.getUserId().equals(userId)
                && user.getPassword().equals(password)){
                    loginOK = true;
                    System.out.println("Login Success!");
                    this.user = user;
                    return true;
                }
            }
            System.out.println("UserId or password incorrect, try again!");
        }
        return false;
    }

    private boolean logout(){
        System.out.println("Logged off");
        String[] args = {};
        MarkerApp.main(args);
        //fixed enter command
        return true;
    }

    private boolean withheld(String arguments[]){
        if (validRole().equals("Lecturer")){
            if (assignment != null){
                if (arguments.length != 1) {
                    System.out.print("Perform withheld for one student at a time");
                    return false;
                } else {
                    String sId = arguments[0];
                    assignment.markAssignmentWithheld(sId);
                    return false;
                }
            }
            else{
                System.out.println("You must create or load an assignment first!");
                return false;
            }
        }
        else{
            System.out.println("You are not Lecturer!");
        }
        return false;
    }
    
    private String validRole(){
        return user.getRole();
    }
    
    public static void main(String args[]) {
        MarkerApp markerApp = new MarkerApp();
        
        if (markerApp.login() == true){
            markerApp.UILoop();
        }

    }
}
