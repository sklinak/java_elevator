# Elevator System Simulation
This is a Java program that simulates multiple elevators in a building. It handles passenger requests, assigns elevators, and moves them between floors. All elevators and the dispatcher run in separate threads, and thread safety is ensured with Java concurrency tools.

## Project Structure

```
java_elevator/
└── src/
    └── elevator/
        ├── ElevatorSystem.java   // Main class to run the simulation
        ├── Elevator.java         // Elevator thread, handles movement and doors
        ├── Dispatcher.java       // Dispatcher thread, assigns requests to elevators
        └── Request.java          // Represents a passenger request
```

## How it works

* **Elevators** move to floors requested by passengers.
* **Dispatcher** receives requests from a queue and chooses the closest available elevator.
* Requests have a `fromFloor` and a `toFloor`.
* Elevator capacity is limited, and thread-safe queues prevent conflicts.
* The program logs elevator movements, arrivals, and door operations to the console.

## How to run

1. Clone the repository:

```bash
git clone https://github.com/sklinak/java_elevator.git
cd java_elevator/src
```

2. Compile the code:

```bash
javac elevator/*.java
```

3. Run the program:

```bash
java elevator.ElevatorSystem
```

You will see logs like:

```
[Dispatcher] Received Request{from=0, to=5}
[Dispatcher] Assigned to Elevator-0
[Elevator 0 | floor 0 | passengers 1] Target added: floor 0
[Elevator 0 | floor 0 | passengers 1] Moving to floor 0
[Elevator 0 | floor 0 | passengers 1] Arrived
...
```

## Notes

* This is a console-based simulation.
* All elevators run independently, so requests are handled concurrently.
* You can change the number of elevators or floors in `ElevatorSystem.java`.
* The system is thread-safe and logs actions in real-time.
