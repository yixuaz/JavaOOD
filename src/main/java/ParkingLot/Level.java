package ParkingLot;

import java.util.ArrayList;
import java.util.List;

class Level {
    private final List<ParkingSpot> spots;
    Level(int numOfSpots) {
        spots = new ArrayList<>(numOfSpots);
        for (int i = 0; i < numOfSpots; i++)
            spots.add(new ParkingSpot(VehicleSize.BIG));
    }
    boolean hasSpot(Vehicle v) {
        for (ParkingSpot parkingSpot : spots) {
            if(parkingSpot.fit(v)) return true;
        }
        return false;
    }
    public boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }

    public boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getUsedVehicle() == v) {
                s.release();
                return true;
            }
        }
        return false;
    }
}
