public class AirportSecurity implements  ParkingLotObserver {


        private boolean capacityIsFull;

        public void capacityIsFull() {
            capacityIsFull=true;
        }

        public boolean isCapacityFull() {
            return this.capacityIsFull;
        }

}
