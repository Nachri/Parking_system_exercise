package pl.connectis.egzamin.parking;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static pl.connectis.egzamin.parking.SpotStatus.*;

public class Parking {


    private int maxSpots;

    private Spot[] spots;
    private String name;
    private String address;


    public Parking(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setMaxSpots(int maxSpots) {
        this.maxSpots = maxSpots;
    }

    public Spot[] getSpots() {
        return spots;
    }

    public void setSpots(Spot... spots) {
        this.spots = spots;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public int takenSpots() {
        int i = 0;
        for (Spot spot : this.spots) {
            if (spot.isOccupied() == OCCUPIED) i++;
        }
        return i;
    }

    public int freeSpots() {
        int i;
        i = maxSpots - this.takenSpots();
        return i;
    }

    private Spot nextFreeSpot() {
        for (Spot spot : spots) {
            if (spot.isOccupied() == FREE) return spot;
        }
        return null;
    }

    public void giveTicket() {

        if (this.freeSpots() == 0) {
            System.out.println("Brak wolnych miejsc.");
        } else {
            Ticket newTicket = Ticket.getInstance();
            Spot spot = this.nextFreeSpot();
            newTicket.setSpot(spot);
            Ticket.getTickets().add(newTicket);
            spot.setOccupiedStatus(OCCUPIED);
            System.out.println("Bilet nr: " + newTicket.getTicketID() + " miejsce: " + newTicket.getSpot().getSpotNo()
                    + " data: "
                    + newTicket.getStart().toLocalDate()
                    + " godzina: " +
                    newTicket.getStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        }

    }

    public void printFreeSpots() {
        System.out.println("Wolnych miejsc jest: " + this.freeSpots() + " na " + maxSpots);
    }

    public void endTicket(int spotNo, int ticketID) {
        for (Ticket ticket : Ticket.getTickets()) {
            if (ticket.getSpot().getSpotNo() == spotNo && ticket.getTicketID() == ticketID) {
                ticket.endTicket();
                System.out.println("Dziękujemy. Do zapłaty: " + ticket.getTariff() / 100 + " zł za czas " + ticket.getStart().until(ticket.getEnd(), ChronoUnit.HOURS));
            }
        }
    }

    public void endTicket(int spotNo, int ticketID, String end) {
        for (Ticket ticket : Ticket.getTickets()) {
            if (ticket.getSpot().getSpotNo() == spotNo && ticket.getTicketID() == ticketID) {
                ticket.endTicket(end);
                System.out.println("Dziękujemy. Do zapłaty: " + ticket.getTariff() / 100 + " zł za czas " + ticket.getStart().until(ticket.getEnd(), ChronoUnit.HOURS));
            }
        }
    }

    public void monthlySpotsUsage(int year, Month... months) {
        System.out.println("Zestawienie użycia za rok " + year);

        for (Month month : months) {
            long count = 0;
            for (Ticket ticket : Ticket.getTickets()) {
                if (ticket.getEnd() != null &&
                        ticket.getEnd().getYear() == year &&
                        ticket.getEnd().getMonth() == month) {
                    count++;
                }
            }
            System.out.println("Miesiąc " + month.toString() + " Zajętość: " + count);
        }
    }

    public void monthlyRevenue(int year, Month... months) {
        System.out.println("Zestawienie dochodów za rok " + year);

        for (Month month : months) {
            long revenue = 0;
            for (Ticket ticket : Ticket.getTickets()) {
                if (ticket.getEnd() != null &&
                        ticket.getEnd().getYear() == year &&
                        ticket.getEnd().getMonth() == month) {
                    revenue += ticket.getTariff();
                }
            }
            System.out.println("Miesiąc " + month.toString() + " Dochód: " + revenue / 100 + " zł");
        }
    }
}