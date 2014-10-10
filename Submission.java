import java.io.FileWriter;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Submission implements Serializable {

    public enum SubmissionStatusEnum {
        UNMARKED ("UNMARKED"),
        PARTLY_MARKED ("PARTIALLY MARKED"),
        MARKED ("MARKED"),
        SENT ("SENT"),
        WITHHELD ("WITHHELD");

        private String desc;
        SubmissionStatusEnum (String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return this.desc;
        }
    };

    private AssignmentSpec assignment;
    private Student submitter;
    private SubmissionStatusEnum status;
    private HashMap<String, CriterionMark> critmarks;

    Submission(AssignmentSpec as, Student s) {
        this.assignment = as;
        this.submitter = s;
        this.status = SubmissionStatusEnum.UNMARKED;
        critmarks = new HashMap<String, CriterionMark>();

        for (Criterion c: as.getCriteria()) {
            if (c.hasComment()) {
                this.critmarks.put(c.getName(), new CommentedCriterionMark(c, CriterionMark.UNMARKED, ""));
            } else {
                this.critmarks.put(c.getName(), new CriterionMark(c, CriterionMark.UNMARKED));
            }
        }
    }

    public String getStatus() {
        return status.desc;
    }

    public Student getSubmitter() {
        return submitter;
    }

    public void mark() {
        Iterator it = critmarks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            CriterionMark c = (CriterionMark) pair.getValue();
            c.getMark();
        }
        status = SubmissionStatusEnum.MARKED;
    }
    
    //set student mark to withheld
    public void markWithheld() {
        status = SubmissionStatusEnum.WITHHELD;
        System.out.println("Student mark status has set to Withheld.");
    }

    public void report (PrintStream printStream) {

        int totalMarks = 0;
        int maxMarks = 0;
        printHeader(printStream);
        if (status == SubmissionStatusEnum.MARKED) {

            Iterator it = critmarks.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                CriterionMark c = (CriterionMark) pair.getValue();
                totalMarks += c.getGrade();
                maxMarks += c.getCriterion().getMaxmark();
                printStream.print(c.formattedMark());
            }
        }
        printFooter(totalMarks, maxMarks, printStream);

    }

    private void printHeader(PrintStream printStream) {
        printStream.println(assignment.getUnit() + ", " + assignment.getName());
        printStream.println(submitter.getName() + ", " + submitter.getId());
    }

    private void printFooter(int totalMarks, int maxMarks, PrintStream printStream) {
        printStream.print("Total marks = " + totalMarks + "/" + maxMarks);
    }

    public static void main (String args[]) {
        String dir = System.getProperty("user.dir");
        AssignmentSpec as = new AssignmentSpec(dir + "/assign1.txt");
        Student student = new Student("Fred", "12345678", "fred.nurk@monash.edu");
        Submission submission = new Submission(as, student);
        submission.mark();
        submission.report(new PrintStream(System.out));
    }
}
