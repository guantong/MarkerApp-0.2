import java.io.*;
import java.util.*;
/**
 * Write a description of class Unit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Unit
{
    // instance variables - replace the example below with your own
    private String unitCodeu;
    private String unitNameu;
    private ArrayList<Unit> units;

    /**
     * Constructor for objects of class Unit
     */
    public Unit()
    {
        // initialise instance variables
        unitCodeu = "";
        unitNameu = "";
    }

    public Unit(String unitcode, String unitname)
    {
        // initialise instance variables
        unitCodeu = unitcode;
        unitNameu = unitname;
    }

    public String getUnitCode() {
        return unitCodeu;
    }

    public void setUnitCode(String unitCode) {
        this.unitCodeu = unitCode;
    }

    public String getUnitName() {
        return unitNameu;
    }

    public void setUnitName(String unitName) {
        this.unitNameu = unitName;
    }

    public void printUnitList(){
        String dir = System.getProperty("user.dir");
        String unitFileName = dir + "/unitlist1.txt";

        readFile(unitFileName);

        for (Unit unit : units){
            System.out.println(unit.getUnitCode() + "," + unit.getUnitName());
        }
    }

    public void readFile(String unitFileName){
        units = new ArrayList<Unit>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(unitFileName));
            String line = "";

            while ((line = reader.readLine()) != null) {
                String fields[] = line.split(",");

                Unit unit = new Unit(fields[0], fields[1]);
                units.add(unit);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //create unit function
    public boolean createUnit(){
        String dir = System.getProperty("user.dir");
        String unitFileName = dir + "/unitlist1.txt";

        readFile(unitFileName);

        try {
            System.out.println("Please enter the unit code and unit name(e.g. FIT5000,SOFTWARE DEV)");
            String inputLine = new Scanner(System.in).nextLine();
            String inputs[] = inputLine.split(",");
            String unitCodei = inputs[0];
            String unitNamei = inputs[1];
            boolean found = false;

            //checking for duplicts in file
            for (Unit unit : units){
                if (unit.getUnitCode().equalsIgnoreCase(inputs[0])
                || unit.getUnitName().equalsIgnoreCase(inputs[1])){
                    System.out.println("Unit already exists");
                    found = true;
                    break;
                }
            }

            //add new unit to file
            if (found == false){
                Unit uniti = new Unit(unitCodei, unitNamei);
                units.add(uniti);
                PrintWriter outWriter = new PrintWriter(unitFileName);
                try{
                    for(Unit unit : units){ 
                        outWriter.println(unit.getUnitCode() + "," + unit.getUnitName());
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                finally{
                    outWriter.close();
                }
                System.out.println("New unit has been added");
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String args[]){

    }
}
