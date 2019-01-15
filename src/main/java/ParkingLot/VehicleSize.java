package ParkingLot;

public enum VehicleSize {
    BIG(2), COMPACT(1);
    int size;

    VehicleSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
