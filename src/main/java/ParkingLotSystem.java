import java.util.ArrayList;
import java.util.List;

public class ParkingLotSystem {
    private  int actualcapacity;
    private List vehicles;
    private ParkingLotOwner owner;

    public ParkingLotSystem(int capacity) {
        this.vehicles=new ArrayList();

        this.actualcapacity=capacity;
    }

    public void parkVehicle(Object vehicle) throws ParkinLotException {
        if(this.vehicles.size() == this.actualcapacity)
        {   owner.capacityIsFull();

            throw   new ParkinLotException("parking lot full");
        }

if(this.isVehicleParked(vehicle))
    throw new ParkinLotException("Vehicle Already parked");
        this.vehicles.add(vehicle);

    }

    public boolean unParkVehicle(Object vehicle) {
        if(vehicle==null) return  false;
        if(this.vehicles.contains(vehicle)){
            this.vehicles.remove(vehicle);
            return  true;
        }
        return false;
    }
    public void registerOwner(ParkingLotOwner parkingLotOwner) {
        this.owner=parkingLotOwner;

    }

    public boolean isVehicleParked(Object vehicle) {
        if(this.vehicles.contains(vehicle)) {
return  true;
        }
            return  false;
    }

    public void setCapacity(int capacity) {
this.actualcapacity=capacity;
    }
}