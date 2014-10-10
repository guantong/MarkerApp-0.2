import java.io.Serializable;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 1:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentedCriterionMark extends CriterionMark implements Serializable {

    private String comment;

    CommentedCriterionMark(Criterion criterion, float grade, String comment) {
        super(criterion, grade);
        this.comment = comment;
    }

    public void getMark() {
        super.getMark();
        System.out.print ("Enter comment:");
        comment = new Scanner(System.in).nextLine();
    }

    public String formattedMark () {
        return super.formattedMark() + "\tComment:" + comment + "\n";
    }

    public static void main (String args[]) {
        Criterion c = new Criterion("rubbish", 10, true);
        CommentedCriterionMark ccm = new CommentedCriterionMark(c, CriterionMark.UNMARKED, "");
        ccm.getMark();
        System.out.println(ccm.formattedMark());
    }
}
