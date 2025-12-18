package elevator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Elevator extends Thread {

    private final int id;
    private volatile int currentFloor = 0;
    private volatile boolean running = true;

    private final int capacity = 4;
    private int currentPassengers = 0;

    private final BlockingQueue<Integer> targets =
            new PriorityBlockingQueue<>();

    public Elevator(int id) {
        this.id = id;
        setName("Elevator-" + id);
    }

    public synchronized boolean tryAddPassengers() {
        if (currentPassengers < capacity) {
            currentPassengers++;
            return true;
        }
        return false;
    }

    public synchronized void removePassengers() {
        if (currentPassengers > 0) {
            currentPassengers--;
        }
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void addTarget(int floor) {
        targets.add(floor);
        log("Target added: floor " + floor);
    }

    public void shutdown() {
        running = false;
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (running) {
                Integer target = targets.take();
                moveTo(target);
            }
        } catch (InterruptedException ignored) {}
    }

    private void moveTo(int target) throws InterruptedException {
        log("Moving to floor " + target);

        while (currentFloor != target) {
            Thread.sleep(500);
            currentFloor += Integer.compare(target, currentFloor);
            log("Passing floor " + currentFloor);
        }

        arrive();
    }

    private void arrive() throws InterruptedException {
        log("Arrived");
        openDoors();
        removePassengers();
        closeDoors();
    }

    private void openDoors() throws InterruptedException {
        log("Doors opening");
        Thread.sleep(500);
    }

    private void closeDoors() throws InterruptedException {
        log("Doors closing");
        Thread.sleep(500);
    }

    private void log(String msg) {
        System.out.printf(
                "[Elevator %d | floor %d | passengers %d] %s%n",
                id, currentFloor, currentPassengers, msg
        );
    }
}
