public class ParkingSlot {
    public int slotNUmber;
    public Vehicle vehicle;

    public ParkingSlot(int slotNumber, Vehicle vehicle){
        System.out.println("value in constuctor"+vehicle);
        this.vehicle=vehicle;
        this.slotNUmber=slotNumber;

    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotNUmber=" + slotNUmber +
                ", vehicle=" + vehicle +
                '}';
    }
}
