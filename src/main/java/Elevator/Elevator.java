package Elevator;

import java.util.Queue;

public class Elevator {
    private static final int DEFAULT_MAX_CAPACITY = 14;
    private static final int DEFAULT_MAX_FLOOR = 10;
    private static final int DEFAULT_INITIAL_LOCATION = 1;

    private final int maxCapacity;

    private final int maxFloor;

    private int load;

    private int location;

    private boolean isGoingUp;

    private int[] requests;

    private static int ID = 0;

    private int id = 0;

    public Elevator(int maxCapacity, int maxFloor) {
        this.maxCapacity = maxCapacity;
        this.maxFloor = maxFloor;
        load = 0;
        isGoingUp = true;
        requests = new int[maxFloor];
        this.id = ID++;
        location = id % 2 == 0 ? DEFAULT_INITIAL_LOCATION : maxFloor;
    }

    public Elevator() {
        this(DEFAULT_MAX_CAPACITY, DEFAULT_MAX_FLOOR);
    }



    public boolean isEmpty() {
        return load == 0;
    }

    public boolean isFull() {
        return load >= maxCapacity;
    }

    public int getLocation() {
        return location;
    }

    public int move() {
        if (isGoingUp) {
            return (location + 1 <= maxFloor) ? ++location : location;
        } else {
            return location - 1 >= 1 ? --location : location;
        }
    }

    public boolean changeMovingDirection() {
        isGoingUp = !isGoingUp;
        return isGoingUp;
    }

    public int load(Queue<Integer> curQueue) {
        int numNewLoad = curQueue.size();
        if (numNewLoad == 0) return 0;
        if (load + numNewLoad > maxCapacity) {
            return 0;
        }
        for (int requestedFloor : curQueue) {
            requests[requestedFloor - 1] += 1;
        }
        load += numNewLoad;

        System.out.println("elevator"+ id + " load " + numNewLoad+" people on "+location+" floor");
        System.out.println("elevator"+ id + " have " + load +" people");
        curQueue.clear();
        return numNewLoad;
    }

    public int unload() {

        int numReq = requests[location - 1];
        if (numReq > 0) {
            load -= numReq;
            requests[location - 1] = 0;
            System.out.println("elevator"+ id + " unload " + numReq+" people on "+location+" floor");
            System.out.println("elevator"+ id + " have " + load +" people");
            return numReq;
        }
        return 0;
    }
}
