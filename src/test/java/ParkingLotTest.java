import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle=null;

    @Before
    public  void setUp() throws  Exception{
        parkingLotSystem = new ParkingLotSystem(1);
        vehicle=new Object();
    }

    @Test public void  givenAVehicle_WhenParked_ShouldReturnTrue(){

        boolean isparked = false;
        try {
             parkingLotSystem.parkVehicle(vehicle);
              isparked=parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isparked);
        } catch (ParkinLotException e) {

        }

    }

    @Test public void givenAVehicle_WhenUnParked_ShouldReturnTrue(){
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1);
        try {
            parkingLotSystem.parkVehicle(vehicle);
        } catch (ParkinLotException e) {

        }
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assert.assertTrue(isUnParked);
    }
    @Test public  void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse(){
        try {
            parkingLotSystem.parkVehicle(vehicle);
        } catch (ParkinLotException e) {
            e.printStackTrace();
        }


        try {
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkinLotException e) {
            Assert.assertEquals("Parking lot is full",e.getMessage());

        }

    }
    @Test public void giveAVehicle_WhenAlreadyUnParked_ShouldReturnFalse(){
        try {
            parkingLotSystem.parkVehicle(vehicle);
        } catch (ParkinLotException e) {

        }
        parkingLotSystem.unParkVehicle(vehicle);
        parkingLotSystem.unParkVehicle(vehicle);
    }
    @Test public void givenWhenParkinLotIsFull_MustInformTheOwner(){
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObservers(parkingLotOwner);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkinLotException e) {
        }
        boolean capacityFull = parkingLotOwner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }
    @Test
    public  void givenCapacityIs2_ShouldBeAbleToPark2Vehicles(){
Object vehicle2=new Object();
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle2);
          boolean  isparked1=parkingLotSystem.isVehicleParked(vehicle);
            boolean  isparked2=parkingLotSystem.isVehicleParked(vehicle2);

            Assert.assertTrue(isparked1 && isparked2);
        } catch (ParkinLotException e) {
            Assert.assertEquals("Parking lot is full",e.getMessage());
        }
    }
    @Test public void givenWhenParkinLotIsFull_MustInformTheAirportSecurity(){
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObservers(airportSecurity);

        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkinLotException e) {
        }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);

    }

}
