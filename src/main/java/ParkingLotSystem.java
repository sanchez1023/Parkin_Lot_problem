public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem() {

    }

    public boolean parkVehicle(Object vehicle) {
        if(this.vehicle!=null){
            return  false;
        }
        this.vehicle=vehicle;
return true;
    }


}