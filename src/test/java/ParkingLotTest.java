import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle=null;
    Vehicle vehicle2=null;
    Vehicle vehicle3 = null;
    ParkingLot parkingLot1;
    ParkingLot parkingLot2;
    ParkingLot parkingLot3;



    @Before
    public  void setUp() throws  Exception{
        parkingLotSystem = new ParkingLotSystem(1);
        vehicle3 = new Vehicle(Vehicle.Drivertype.NORMAL);
        vehicle=new Vehicle(Vehicle.Drivertype.NORMAL);
        vehicle2=new Vehicle(Vehicle.Drivertype.NORMAL);
        parkingLot1 = new ParkingLot(3);
        parkingLot2 = new ParkingLot(2);
        parkingLot3 = new ParkingLot(2);
        parkingLotSystem = new ParkingLotSystem(parkingLot1,parkingLot2,parkingLot3);
    }

    @Test public void  givenAVehicle_WhenParked_ShouldReturnTrue(){

        boolean isparked = false;
        try {
             parkingLotSystem.parkVehicle((Vehicle) vehicle);
              isparked=parkingLotSystem.isVehicleParked((Vehicle) vehicle);
            Assert.assertTrue(isparked);
        } catch (ParkinLotException e) {

        }

    }

    @Test public void givenAVehicle_WhenUnParked_ShouldReturnTrue(){
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem(1);
        try {
            parkingLotSystem.parkVehicle((Vehicle) vehicle);
        } catch (ParkinLotException e) {

        }
        boolean isUnParked = parkingLotSystem.unParkVehicle( vehicle);
        System.out.println("is parked"+isUnParked);
        Assert.assertTrue(isUnParked);
    }
    @Test public  void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse(){
//        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.parkVehicle( vehicle);
//            parkingLotSystem.parkVehicle( vehicle2);





            parkingLotSystem.parkVehicle( vehicle2);
        } catch (ParkinLotException e) {
            Assert.assertEquals("Vehicle Already parked",e.getMessage());

        }

    }
    @Test public void giveAVehicle_WhenAlreadyUnParked_ShouldReturnFalse(){
        try {
            parkingLotSystem.parkVehicle( vehicle);
        } catch (ParkinLotException e) {

        }
        parkingLotSystem.unParkVehicle( vehicle);
        parkingLotSystem.unParkVehicle( vehicle);
    }
    @Test public void givenWhenParkinLotIsFull_MustInformTheOwner(){
//       Object vehicle2=new Vehicle();
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObservers(parkingLotOwner);
        try {
            parkingLotSystem.parkVehicle( vehicle);
            parkingLotSystem.parkVehicle( vehicle2);
        } catch (ParkinLotException e) {
        }
        boolean capacityFull = parkingLotOwner.isCapacityFull();
        Assert.assertTrue(capacityFull);
    }
    @Test
    public  void givenCapacityIs2_ShouldBeAbleToPark2Vehicles(){
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.parkVehicle( vehicle);
            parkingLotSystem.parkVehicle( vehicle2);
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
            parkingLotSystem.parkVehicle((Vehicle) vehicle);
            parkingLotSystem.parkVehicle((Vehicle) vehicle2);
        } catch (ParkinLotException e) {
        }
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertTrue(capacityFull);

    }
    @Test public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldOnformTheOwner(){
//        Object vehicle2=new Object();
        parkingLotSystem.setCapacity(2);

        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        parkingLotSystem.registerParkingLotObservers(parkingLotOwner);
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle( vehicle2);


        } catch (ParkinLotException e) {
        }
        parkingLotSystem.unParkVehicle( vehicle);
        boolean capacityFull = parkingLotOwner.isCapacityFull();
        Assert.assertFalse(capacityFull);


    }

    @Test public  void  givenACarToParkAttenderCanParkTheCar() throws ParkinLotException {



        parkingLotSystem.setCapacity(4);
        parkingLotSystem.parkVehicle((Vehicle) vehicle);
        parkingLotSystem.parkVehicle((Vehicle) vehicle2);

        parkingLotSystem.parkVehicle((Vehicle) vehicle3);



        int availableSlot = parkingLotSystem.getAvailableSlot();
        Assert.assertEquals(4,availableSlot);


    }
    @Test
    	    public void givenVehicle_WhenFound_ShouldReturnVehicleAtPosition() throws ParkinLotException {


        parkingLotSystem.setCapacity(4);

        parkingLotSystem.parkVehicle( vehicle);
        parkingLotSystem.parkVehicle( vehicle2);

//        parkingLotSystem.parkVehicle((Vehi vehicle3);

        int vehiclePosition = parkingLotSystem.findVehicle(vehicle2);
        Assert.assertEquals(2, vehiclePosition);
    }
    @Test
    public void givenVehicle_WhenParked_ShouldGiveParkingCharge() throws ParkinLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.parkVehicle( vehicle);
        Assert.assertEquals(10, parkingLotSystem.getParkingSlip( vehicle), 0.0);
    }
    @Test
         public void givenVehicle_WhenParked_ShouldDistributeEvenly() throws ParkinLotException {

           parkingLotSystem.evenlyPark(vehicle);

        parkingLotSystem.evenlyPark(vehicle2);
        parkingLotSystem.evenlyPark(vehicle3);

//Object value=parkingLot1.parkingSlots.get(0).vehicle;
//        System.out.println("valie of park1"+value);
//        Assert.assertEquals(parkingLot1.parkingSlots.get(0).vehicle,vehicle);
        Assert.assertEquals(vehicle,parkingLot1.parkingSlots.get(0).vehicle);
        Assert.assertEquals(vehicle2,parkingLot2.parkingSlots.get(0).vehicle);
        Assert.assertEquals(vehicle3,parkingLot3.parkingSlots.get(0).vehicle);

    }

    @Test public void givenVehicle_WhenDriverIsHandicap_ShouldAssignNearestSpace() throws ParkinLotException {
      Vehicle vehicle4= new Vehicle(Vehicle.Drivertype.HANDICAP);
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.parkVehicle(vehicle2);

        parkingLotSystem.parkVehicle(vehicle4);

        Assert.assertEquals(vehicle, parkingLot1.parkingSlots.get(0).vehicle);
        Assert.assertEquals(vehicle2, parkingLot2.parkingSlots.get(0).vehicle);
        Assert.assertEquals(vehicle4, parkingLot1.parkingSlots.get(1).vehicle);


    }
}
