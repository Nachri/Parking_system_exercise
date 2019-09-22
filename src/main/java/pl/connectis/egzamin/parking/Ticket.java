package pl.connectis.egzamin.parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


import static pl.connectis.egzamin.parking.Tariffs.*;

public class Ticket {

    private static int count;
    private static List<Ticket> tickets = new ArrayList<>();

    private Spot spot;

    private final int ticketID;
    private final LocalDateTime start;
    private LocalDateTime end;
    private Tariffs tariffType;
    private long tariff;

    private Ticket() {
        this.ticketID = ++count;
        this.start = LocalDateTime.now();
    }

    public static List<Ticket> getTickets() {
        return tickets;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public long getTariff() {
        return tariff;
    }

    public int getTicketID() {
        return ticketID;
    }

    public static Ticket getInstance(){
        return new Ticket();
    }

    public void endTicket() {
        this.end = LocalDateTime.now();
        long stayTime = this.start.until(this.end, ChronoUnit.HOURS);
        System.out.println(stayTime);

        if (stayTime <= 6) {
            this.tariffType = UP_TO_6H;
        } else if (stayTime <= 12) {
            this.tariffType = UP_TO_12H;
        } else if (stayTime <= 24) {
            this.tariffType = UP_TO_24H;
        } else {
            this.tariffType = ABOVE_24H;
        }

        this.tariff = this.tariffType.getTariff() * stayTime;
        this.spot.setOccupiedStatus(SpotStatus.FREE);
    }

    public void endTicket(String end){

        this.end = LocalDateTime.parse(end);
        long stayTime = this.start.until(this.end, ChronoUnit.HOURS);

        if (stayTime <= 6) {
            this.tariffType = UP_TO_6H;
        } else if (stayTime <= 12) {
            this.tariffType = UP_TO_12H;
        } else if (stayTime <= 24) {
            this.tariffType = UP_TO_24H;
        } else {
            this.tariffType = ABOVE_24H;
        }

        this.tariff = this.tariffType.getTariff() * stayTime;
        this.spot.setOccupiedStatus(SpotStatus.FREE);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "spot=" + spot.getSpotNo() +
                ", ticketID=" + ticketID +
                ", start=" + start +
                ", end=" + end +
                ", tariffType=" + tariffType +
                ", tariff=" + tariff +
                '}';
    }
}