import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle=null;



    @Test public void  givenAVehicle_WhenParked_ShouldReturnTrue(){

        boolean isparked = parkingLotSystem.parkVehicle(new Object());
        Assert.assertTrue(isparked);
    }

    @Test public void givenAVehicle_WhenUnParked_ShouldReturnTrue(){
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.parkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assert.assertTrue(isUnParked);
    }

}
