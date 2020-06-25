

public class Vehicle {
    public enum Drivertype {
        HANDICAP,
        NORMAL;
    }
    public Drivertype driver;


    public Vehicle(Drivertype driver){
        this.driver=driver;
    }

}
