import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {
    public int slotCapacity;
    public List<ParkingSlot> parkingSlots = new ArrayList<>();

    public ParkingLot(int slotCapacity){
        this.slotCapacity=slotCapacity;
        this.createSlots();
    }



    private void   createSlots(){
      for (int i = 1; i <= slotCapacity; i++) {
          parkingSlots.add(new ParkingSlot(i, null));
      }
    }

    public  int getAvailableSlots() throws ParkinLotException {
        return (IntStream.range(0, parkingSlots.size()).
                filter(data -> parkingSlots.get(data).vehicle == null).
                findFirst()
                .orElse(-1) + 1);


    }

    public boolean parkVehicleInSlot(Vehicle vehicle) throws ParkinLotException {
        int availableSlots=this.getAvailableSlots();
        System.out.println("value in park vehicle"+vehicle+ " slot number"+availableSlots);
        parkingSlots.get(availableSlots-1).vehicle=vehicle;
        return  true;
    }

}
