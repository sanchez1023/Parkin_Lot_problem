import java.time.LocalDateTime;
import java.util.*;

public class ParkingLotSystem<vehicle> {
    private  int actualcapacity;
    private List vehicles;
    private List <ParkingLotObserver> observers;
    private AirportSecurity seucrity;
    private Map<Integer, Object> parkingSlotMap;
    int countOFVehicles=0;
    private List<ParkingSlip> parkingSlips;
    List<ParkingLot> parkingLots = new ArrayList<>();



    public ParkingLotSystem(int capacity) {
        this.observers=new ArrayList<>();
        this.vehicles=new ArrayList();
        parkingSlotMap= new HashMap<>();
        this.actualcapacity=capacity;
        this.parkingSlips= new ArrayList();
        createSlot();
    }

    public ParkingLotSystem(ParkingLot ... parkingLots) {

      this.  parkingLots.addAll(Arrays.asList(parkingLots));
    }

    private void createSlot() {
        for (int i = 0; i < actualcapacity; i++) {
            parkingSlotMap.put(i+1 , null);
        }
    }
    public void parkVehicle(Vehicle vehicle) throws ParkinLotException {
        if(this.parkingSlotMap.size() == this.actualcapacity)
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
        addVehicle(availableSlot, vehicle);
        this.createParkingSlip(vehicle);

        countOFVehicles++;
        System.out.println("value of index0"+this.vehicles.indexOf(vehicle));

    }

    private boolean createParkingSlip( Vehicle vehicle) {
        ParkingSlip parkingSlip = new ParkingSlip(vehicle);
        parkingSlip.inTime = LocalDateTime.now();
        parkingSlip.id = UUID.randomUUID();
        parkingSlip.parkingCharge = 10;
        this.parkingSlips.add(parkingSlip);
        return true;
    }

    private boolean addVehicle(int availableSlot, Vehicle vehicle) {
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



    public boolean unParkVehicle(Vehicle vehicle) {
        System.out.println("value of vehicle"+this.vehicles.contains(vehicle));
        if(vehicle==null) return  false;
        if(this.parkingSlotMap.values().contains(vehicle)){
            System.out.println("in if");
            this.parkingSlotMap.values().remove(vehicle);
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

    public boolean isVehicleParked(Vehicle vehicle) {
        if(this.parkingSlotMap.values().contains(vehicle)) {
return  true;
        }
            return  false;
    }

    public void setCapacity(int capacity) {
this.actualcapacity=capacity;
    }

    public  int  findVehicle(Vehicle vehicle) throws ParkinLotException {
        int carPosition = getCarPosition(vehicle);
        System.out.println("get car position"+carPosition);
        if (carPosition != 0) {
            return carPosition;
        }
        return 0;
    }



        private int getCarPosition(Object vehicle) throws ParkinLotException {
            System.out.println("vehicle"+vehicle);
            return parkingSlotMap.entrySet().stream()
          .filter(entry -> vehicle.equals(entry.getValue()))
          .map(Map.Entry::getKey)
          .findFirst().get();
        }

    public int  getParkingSlip(Vehicle vehicle) {
        return (int) this.parkingSlips
                .stream()
                .filter(slip -> slip.vehicle.equals(vehicle))
                .findFirst()
                .get()
                .parkingCharge;
    }


    public boolean evenlyPark(Vehicle vehicle) throws ParkinLotException {
     return   getParkingSlots().parkVehicleInSlot(vehicle);

    }
    private ParkingLot getParkingSlots(){
        ParkingLot parkingLot=new ParkingLot(2);

        int max=Integer.MIN_VALUE;
        for(ParkingLot i:parkingLots){
            int emptyCount = getNumEmptySlot(i);
            if(max<emptyCount)
                max=emptyCount;
            parkingLot = i;


        }
        return  parkingLot;
    }

    private int getNumEmptySlot(ParkingLot parkingLot) {
        return (int) parkingLot.parkingSlots.stream().
                filter(parkingSlot -> parkingSlot.vehicle == null)
                .count();
    }
}