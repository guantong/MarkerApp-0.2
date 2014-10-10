import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: ybkan
 * Date: 7/08/14
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssignmentSpec implements Serializable {

    private String unit;
    private String name;
    private String assignment_fname;
    private HashMap<String, Criterion> criteria_list;

    AssignmentSpec (String unit, String name) {
        this.unit = unit;
        this.name = name;
        criteria_list = new HashMap<String, Criterion>();
    }

    AssignmentSpec (String assignment_fname) {
        this.assignment_fname = assignment_fname;
        criteria_list = new HashMap<String, Criterion>();
        parse(assignment_fname);
    }

    public void addCriterion(Criterion c) {
        if (!criteria_list.containsKey(c.getName())) {
             criteria_list.put(c.getName(), c);
        } else {
            System.out.println("The criterion, " + c.getName() + ", does already exist!");
        }
    }

    public Criterion getCriterion(String name) {
        if (criteria_list.containsKey(name))
            return criteria_list.get(name);
        else {
            System.out.println("This is no criterion named:" + name);
            return null;
        }
    }

    public ArrayList<Criterion> getCriteria() {
        return new ArrayList<Criterion>(criteria_list.values());
    }

    public void printName() {
        System.out.println(this.unit);
    }

    public String getUnit() {
        return this.unit;
    }

    public String getName() {
        return this.name;
    }

    public void parse (String assignment_fname) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(assignment_fname));
            String line = "";
            unit = reader.readLine();
            name = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String fields[] = line.split(",");
                Criterion c = new Criterion(fields[0], Float.parseFloat(fields[1]), true);
                criteria_list.put(c.getName(), c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        String dir = System.getProperty("user.dir");
        AssignmentSpec as = new AssignmentSpec(dir + "/assign1.txt");
        as.printName();
        Criterion c = as.getCriterion("Criterion 1");
        System.out.println(c.display());
    }
}
