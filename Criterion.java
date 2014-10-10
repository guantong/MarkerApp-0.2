import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 12:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Criterion implements Serializable {
    private String name;
    private float maxmark;
    private boolean hcomment;

    Criterion(String name, float maxmark, boolean hcomment) {
        this.name = name;
        this.maxmark = maxmark;
        this.hcomment = hcomment;
    }

    public String getName() {
        return name;
    }

    public boolean hasComment() {
        return hcomment;
    }

    public float getMaxmark() {
        return maxmark;
    }

    public String display() {
        return name + ", " + maxmark + ", " + hcomment;
    }

    public static void main(String args[]) {
        Criterion criterion = new Criterion("criterion1", 10, true);
        System.out.println("criterion = " + criterion.getName());
        if (criterion.hasComment()) System.out.println("criterion has comments");
    }
}
