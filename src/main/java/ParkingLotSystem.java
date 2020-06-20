import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotSystem {
    private  int actualcapacity;
    private List vehicles;
    private List <ParkingLotObserver> observers;
    private AirportSecurity seucrity;
    private Map<Integer, Object> parkingSlotMap;
    int countOFVehicles=0;


    public ParkingLotSystem(int capacity) {
        this.observers=new ArrayList<>();
        this.vehicles=new ArrayList();
        parkingSlotMap= new HashMap<>();
        this.actualcapacity=capacity;
        createSlot();
    }
    private void createSlot() {
        for (int i = 0; i < actualcapacity; i++) {
            parkingSlotMap.put(i+1 , null);
        }
    }
    public void parkVehicle(Object vehicle) throws ParkinLotException {
        if(this.vehicles.size() == this.actualcapacity)
        {
            for (ParkingLotObserver observer:observers
                 ) {
observer.capacityIsFull();
            }

            throw   new ParkinLotException("parking lot  is full");
        }

if(this.isVehicleParked(vehicle))
    throw new ParkinLotException("Vehicle Already parked");
        int availableSlot = getAvailableSlot();
        addVehicle(availableSlot,vehicle);
        countOFVehicles++;
        System.out.println("value of index0"+this.vehicles.indexOf(vehicle));

    }

    private boolean addVehicle(int availableSlot, Object vehicle) {
        this.parkingSlotMap.put(availableSlot, vehicle);
        return true;

    }

    public int getAvailableSlot() {
        int position = 0;
        for (int pos = 1; pos <=this.actualcapacity; pos++) {
            if (parkingSlotMap.get(pos) == null) {
                position = pos;
                break;
            }
        }
        System.out.println("position"+position);
        return position;
    }



    public boolean unParkVehicle(Object vehicle) {
        if(vehicle==null) return  false;
        if(this.vehicles.contains(vehicle)){
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observer:observers
                 ) {
observer.capacityIsAvailable();
            }
            return  true;
        }
        return false;
    }
    public void registerParkingLotObservers(ParkingLotObserver observer) {
        this.observers.add(observer);

    }

    public boolean isVehicleParked(Object vehicle) {
        if(this.parkingSlotMap.values().contains(vehicle)) {
return  true;
        }
            return  false;
    }

    public void setCapacity(int capacity) {
this.actualcapacity=capacity;
    }


}