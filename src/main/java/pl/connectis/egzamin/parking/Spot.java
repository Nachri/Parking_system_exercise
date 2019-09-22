package pl.connectis.egzamin.parking;

public class Spot {

    private static int count;

    private SpotStatus occupiedStatus;

    private int spotNo;

    public Spot(){
        occupiedStatus = SpotStatus.FREE;
        spotNo = ++count;
    }

    public SpotStatus isOccupied() {
        return occupiedStatus;
    }

    public void setOccupiedStatus(SpotStatus occupiedStatus) {
        this.occupiedStatus = occupiedStatus;
    }

    public int getSpotNo() {
        return spotNo;
    }

    @Override
    public String toString() {
        return "Spot " + spotNo + " status " + occupiedStatus;
    }
}