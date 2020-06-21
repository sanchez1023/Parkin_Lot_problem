import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingSlip {
    public Vehicle vehicle;

    public LocalDateTime inTime;
    public UUID id;
    public Object parkingCharge;

    public ParkingSlip(Vehicle vehicle) {
        this.vehicle=vehicle;
    }
}
