package elevator;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ElevatorSystem {

    public static void main(String[] args) throws InterruptedException {

        int elevatorsCount = 3;

        BlockingQueue<Request> requestQueue =
                new LinkedBlockingQueue<>();

        List<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < elevatorsCount; i++) {
            Elevator e = new Elevator(i);
            elevators.add(e);
            e.start();
        }

        Dispatcher dispatcher =
                new Dispatcher(elevators, requestQueue);
        dispatcher.start();

        requestQueue.put(new Request(0, 5));
        Thread.sleep(1000);
        requestQueue.put(new Request(3, 9));
        Thread.sleep(1500);
        requestQueue.put(new Request(10, 2));
        Thread.sleep(1000);
        requestQueue.put(new Request(1, 7));
        Thread.sleep(500);
        requestQueue.put(new Request(4, 8));
    }
}
