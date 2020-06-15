public class AirportSecurity implements  ParkingLotObserver {


        private boolean capacityIsFull;

        public void capacityIsFull() {
            capacityIsFull=true;
        }
    @Override
    public void capacityIsAvailable() {
        capacityIsFull=false;
    }
        public boolean isCapacityFull() {
            return this.capacityIsFull;
        }

}
