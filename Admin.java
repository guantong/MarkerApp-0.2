import java.io.*;
import java.util.*;
/**
 * Write a description of class Lecturer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Admin
{
    // instance variables - replace the example below with your own
    private String lecturerid;
    private String unitcode;
    private String noUnit;
    private static int maxNoUnit = 3;
    private ArrayList<Admin> ls;

    /**
     * Constructor for objects of class Lecturer
     */
    public Admin(){

    }

    public Admin(String lecturerid, String unitcode, String noUnit){
        this.lecturerid = lecturerid;
        this.unitcode = unitcode;
        this.noUnit = noUnit;
    }

    public String getNoUnit() {
        return noUnit;
    }

    public void setNoUnit(String noUnit) {
        this.noUnit = noUnit;
    }

    public String getLecturerid() {
        return lecturerid;
    }

    public void setLecturerid(String lecturerid) {
        this.lecturerid = lecturerid;
    }

    public String getUnitCode() {
        return unitcode;
    }

    public void setUnitCode(String unitcode) {
        this.unitcode = unitcode;
    }
    
    public void readFile(String unitFileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(unitFileName));
            String line = "";

            while ((line = reader.readLine()) != null) {
                String fields[] = line.split(",");

                Admin l = new Admin(fields[0], fields[1], fields[2]);
                ls.add(l);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean assignLecturer(){
        String dir = System.getProperty("user.dir");
        String unitFileName = dir + "/assignlecturer.txt";

        ls = new ArrayList<Admin>();
        
        readFile(unitFileName);
        
        try {
            System.out.println("Please enter the lecturer id and unit code(e.g. 2000,fit2000)");
            String inputLine = new Scanner(System.in).nextLine();
            String inputs[] = inputLine.split(",");
            String lidi = inputs[0];
            String unitCodei = inputs[1];
            boolean found = false;
            boolean found2 = false;
            int currentNoOfUnit = 0;
            String x = "";
            int noOfUnit = 0;

            for (Admin l : ls){
                noOfUnit = Integer.parseInt(l.getNoUnit());
                if (l.getLecturerid().equalsIgnoreCase(inputs[0])
                && l.getUnitCode().equalsIgnoreCase(inputs[1])){
                    System.out.println("The lecturer was assigned to this unit already");
                    found = true;
                    break;
                }
            }
            
            for (Admin l : ls){
                noOfUnit = Integer.parseInt(l.getNoUnit());
                if (l.getLecturerid().equalsIgnoreCase(inputs[0])
                && !l.getUnitCode().equalsIgnoreCase(inputs[1])){
                    if (noOfUnit < maxNoUnit){                        
                        x = String.valueOf(noOfUnit+1);
                        currentNoOfUnit = noOfUnit+1;
                        l.setNoUnit(x);
                        found2 = true;
                        found = true;
                    }
                    if(noOfUnit == maxNoUnit)
                    {
                        System.out.println("Unable to assign lecturer to this unit, lecturer's max number of unit reached");
                        found = true;
                        break;
                    }
                }
            }
            
            if (found2 == true){
                if(currentNoOfUnit <= maxNoUnit){
                    Admin li = new Admin(lidi, unitCodei, x);
                    ls.add(li);
                    System.out.println("Additional unit assign to lecturer");
                    PrintWriter outWriter = new PrintWriter(unitFileName);
                    try{
                        for(Admin l : ls){ 
                            outWriter.println(l.getLecturerid() + "," + l.getUnitCode() + "," + l.getNoUnit());
                        }
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                    finally{
                        outWriter.close();
                    }
                    System.out.println("Assigned");
                    return true;
                }
                else{
                    System.out.println("Maximum no of unit reached");
                    return true;
                }
            }

            if (found == false){
                Admin li = new Admin(lidi, unitCodei, "1");
                ls.add(li);
                PrintWriter outWriter = new PrintWriter(unitFileName);
                try{
                    for(Admin l : ls){ 
                        outWriter.println(l.getLecturerid() + "," + l.getUnitCode() + "," + l.getNoUnit());
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                finally{
                    outWriter.close();
                }
                System.out.println("Assigned");
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String y[]){

    }
}
