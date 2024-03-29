package pl.connectis.egzamin.parking;

import static pl.connectis.egzamin.parking.SpotStatus.*;

public class Spot {

    private static int count;

    private SpotStatus occupiedStatus;

    private int spotNo;

    public Spot(){
        occupiedStatus = FREE;
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