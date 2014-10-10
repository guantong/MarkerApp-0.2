import java.io.Serializable;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 1:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CriterionMark implements Serializable {
    public static float UNMARKED = -1;
    private Criterion criterion;
    private float grade;

    CriterionMark () {

    }

    CriterionMark (Criterion c, float grade) {
        this.criterion = c;
        this.grade = grade;
    }

    public void getMark() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Criterion:" + criterion.getName());
        while (!(grade >= 0 && grade <= criterion.getMaxmark())) {
            System.out.print("Enter mark between 0 and " + criterion.getMaxmark() + ":");
            grade = Float.parseFloat(scanner.nextLine());
        }
    }

    public float getGrade() {
        return grade;
    }

    public String formattedMark() {
        return "\t" + criterion.getName() + "\t" + grade + "/" + criterion.getMaxmark() + "\n";
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public static void main (String args[]) {
        Criterion c = new Criterion("rubbish", 10, true);
        CriterionMark cm = new CriterionMark(c, CriterionMark.UNMARKED);
        cm.getMark();
        System.out.println(cm.formattedMark());
    }

}
