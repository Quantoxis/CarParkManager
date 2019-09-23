package carparkmanager;


/**
 *
 * @author Andrew
 */
public class Revenue {

    int numCar;
    double cost = 3.3;
    double revenue;
    double total;


    public Revenue() {


    }

    public int getNumCars() {
     
        return numCar;
    }

    public void setNumCars(int newNumCar) {
        this.numCar = newNumCar;
    }
    public double getCost(){
        return cost;
    }
    
    public void setCost(double newCost){
        this.cost = newCost;
    }
    
    public double getAddTotal(){
        return cost * numCar;
    }
    
    public void setAddTotal(double newTotal){
        this.total = newTotal;
    }
    


    
//    public int getNumberOfCars(){
//        return numCar;
//    }
//    
//    public void setNumberOfCars(int newNumCar){
//        this.numCar = newNumCar;
//    }
}
