package ParkingLot;

class ParkingSpot {
    private final VehicleSize vehicleSize;
    private Vehicle usedVehicle;

    public ParkingSpot(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public boolean fit(Vehicle v) {
        if (usedVehicle != null) return false;
        return v.getSize().getSize() <= vehicleSize.getSize();
    }

    public void park(Vehicle v) {
        usedVehicle = v;
    }

    public Vehicle release() {
        Vehicle ret = usedVehicle;
        usedVehicle = null;
        return ret;
    }

    public Vehicle getUsedVehicle() {
        return usedVehicle;
    }
}
