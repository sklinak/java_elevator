package elevator;

public class Request {

    public final int fromFloor;
    public final int toFloor;

    public Request(int fromFloor, int toFloor) {
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
    }

    public int direction() {
        return Integer.compare(toFloor, fromFloor);
    }

    @Override
    public String toString() {
        return "Request{from=" + fromFloor + ", to=" + toFloor + "}";
    }
}
