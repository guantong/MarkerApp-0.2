import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment implements Serializable {

    private AssignmentSpec assignmentSpec;
    private HashMap<String, Submission> submissions;

    public Assignment(String specFile, String listFile) {
        this.assignmentSpec = new AssignmentSpec(specFile);
        this.submissions = new HashMap<String, Submission>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(listFile));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String fields[] = line.split(",");
                System.out.println("Fields:" + fields[0] + ", " + fields[1] + ", " + fields[2]);
                Student student = new Student (fields[0], fields[1], fields[2]);
                Submission submission = new Submission(this.assignmentSpec, student);
                this.submissions.put(fields[1], submission);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void markAssignment (String sId) {
        if (submissions.containsKey(sId)) {
            submissions.get(sId).mark();
        } else {
            System.err.println("The assignment of '" + sId + "' does not exist!");
        }
    }
    
    //set student mark to withheld
    public void markAssignmentWithheld (String sId){
        if (submissions.containsKey(sId)) {
            submissions.get(sId).markWithheld();
        } else {
            System.err.println("The assignment of '" + sId + "' does not exist!");
        }
    }

    public void printStatus (PrintStream printStream) {
        Iterator it = submissions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Submission submission = (Submission) pair.getValue();
            Student s = submission.getSubmitter();
            printStream.println(s.getName() + ", " + s.getId() + ", " + submission.getStatus());
        }
    }

    public void markUI() {
        String id = "1";
        while (true) {
            System.out.println("Enter student id (0 to end):");
            String sId = new Scanner(System.in).nextLine().trim();
            if (sId.equalsIgnoreCase("0")) {
                break;
            }
            markAssignment(sId);
        }
    }

    public void makeReports(String path) {
        try {
            File path_dir = new File(path);
            if (!path_dir.exists()) path_dir.mkdir();

            Iterator it = submissions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Submission submission = (Submission) pair.getValue();
                Student s = submission.getSubmitter();
                PrintStream outfile = new PrintStream(new FileOutputStream(path + "/" + s.getName()), true);
                submission.report(outfile);
                outfile.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        String dir = System.getProperty("user.dir");
        String assignFile = dir + "/assign1.txt";
        String studFile = dir + "/studlist1.txt";

        Assignment assignment = new Assignment(assignFile, studFile);
        assignment.makeReports("./undonereports");
        assignment.markUI();
        assignment.makeReports("./reports");
    }
}
