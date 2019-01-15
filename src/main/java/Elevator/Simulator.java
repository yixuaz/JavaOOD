package Elevator;

import java.util.*;

public class Simulator {
    //how many floors
    private final int floors;

    //all user requests
    private List<Queue<Integer>> requests;

    // all elevators in system
    private List<Elevator> elevators;

    //current elevator scheduler we use
    private Scheduler scheduler;

    public Simulator(int floors, int numElevators, List<Queue<Integer>> initialRequests,
                     Scheduler scheduler, int elevatorCapacity) {
        this.floors = floors;
        this.requests = initialRequests;
        this.elevators = new ArrayList<Elevator>();
        for (int i = 0; i < numElevators; i++) {
            Elevator ele = new Elevator(elevatorCapacity, floors);
            elevators.add(ele);
        }
        this.scheduler = scheduler;
    }

    private void generateRequest() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

    }
    public int search(int[][] array) {
        int max = Integer.MIN_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            pq.offer(new int[]{array[i][0],i,0});
            max = Math.max(array[i][0], max);
        }
        while (pq.size() == array.length) {
            int[] min = pq.poll();
            if (max == min[0]) {
                return max;
            }
            if (min[2] + 1 < array[min[1]].length) {
                max = Math.max(array[min[1]][min[2] + 1], max);
                pq.offer(new int[]{array[min[1]][min[2] + 1], min[1], min[2] + 1});
            }
        }
        return -1;
    }

    private void schedule() {
        scheduler.schedule(requests, elevators, floors);
    }

    private void elevatorOp() {
        for (Elevator e : elevators) {
            if (!e.isEmpty()) {
                e.unload();
            }
            if (!e.isFull()) {
                int location  = e.getLocation();
                e.load(requests.get(location - 1));
            }
            e.move();
        }
    }

    //handle to start the simulation
    public void simulate(int steps) throws InterruptedException {
        for (int i = 0; i < steps; i++) {
            generateRequest();
            schedule();
            elevatorOp();
            Thread.sleep(10);
        }
    }
    // entry point of the simulation program
    public static void main(String[] args) throws InterruptedException {
        SimpleScheduler simpleScheduler = new SimpleScheduler();
        int floors = 10;
        int numElevators = 2;
        List<Queue<Integer>> initialRequests = new ArrayList<>();
        for (int i = 0; i < floors; i++) {
            Queue<Integer> currQueue = new LinkedList<>();
            Random r = new Random();
            int req = r.nextInt(10);
            currQueue.offer(req + 1);
            initialRequests.add(currQueue);
        }
        Simulator simulator = new Simulator(floors, numElevators, initialRequests, simpleScheduler, 14);
        simulator.simulate(20);
    }

    private static class SimpleScheduler implements Scheduler {

        @Override
        public void schedule(List<Queue<Integer>> requests, List<Elevator> elevators, int floor) {
            for (Elevator e : elevators) {
                if (e.getLocation() == 1 || e.getLocation() == floor) {
                    e.changeMovingDirection();
                }
            }
        }
    }
}
