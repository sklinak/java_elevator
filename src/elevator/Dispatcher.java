package elevator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Dispatcher extends Thread {

    private final List<Elevator> elevators;
    private final BlockingQueue<Request> requests;

    public Dispatcher(List<Elevator> elevators,
                      BlockingQueue<Request> requests) {
        this.elevators = elevators;
        this.requests = requests;
        setName("Dispatcher");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Request req = requests.take();
                log("Received " + req);

                Elevator best = chooseElevator(req);

                if (!best.tryAddPassengers()) {
                    log("Elevator full, request rejected: " + req);
                    continue;
                }

                log("Assigned to " + best.getName());

                best.addTarget(req.fromFloor);
                best.addTarget(req.toFloor);
            }
        } catch (InterruptedException ignored) {}
    }

    private Elevator chooseElevator(Request r) {
        Elevator best = elevators.get(0);
        int minDistance = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            int dist = Math.abs(e.getCurrentFloor() - r.fromFloor);
            if (dist < minDistance) {
                minDistance = dist;
                best = e;
            }
        }
        return best;
    }

    private void log(String msg) {
        System.out.println("[Dispatcher] " + msg);
    }
}
