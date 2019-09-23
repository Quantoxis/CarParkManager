package carparkmanager;

/**
 *
 * @author Andrew
 */
public class Car{

    private double charge = 0;
    private double costPerHour = 1.40;
    private int hoursOfStay = 0;
    private String registration = "";
    Vehicle v = new Vehicle();

    public void setCar(String rn, double c, double cH, double hs) {
        rn = registration;
        c = charge;
        cH = costPerHour;

    }

    public void Car(String rn, double c, double cH) {
        registration = rn;
        costPerHour = cH;
        charge = c;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String newRegistration) {
        this.registration = newRegistration;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double newCostPerHour) {
        this.costPerHour = newCostPerHour;
    }
    
    public double getHoursOfStay(){
        return hoursOfStay;
    }
    
    public void setHoursOfStay(int newHoursOfStay){
        this.hoursOfStay = newHoursOfStay;
    }

//    public double getCharge() {
//        
//        charge = v.getCharge();
//        return charge;
//    }
}
