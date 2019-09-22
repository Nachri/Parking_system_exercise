import pl.connectis.egzamin.parking.Parking;
import pl.connectis.egzamin.parking.Spot;
import pl.connectis.egzamin.parking.Ticket;


import static java.time.Month.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){

        Parking parking = new Parking("Parking 01", "Al.Jerozolimskie 123, 05-123, Warszawa");
        parking.setMaxSpots(5);

        Spot[] spots = new Spot[5];
        for(int i =0;i<5;i++){
            spots[i] = new Spot();
        }
        parking.setSpots(spots);

        System.out.println(Arrays.toString(parking.getSpots()));

        parking.printFreeSpots();

        parking.monthlySpotsUsage(2019, JANUARY,FEBRUARY);

        parking.monthlyRevenue(2019, JANUARY,FEBRUARY);

        for(int i=0;i<4;i++) parking.giveTicket();

        parking.printFreeSpots();

        System.out.println(Arrays.toString(parking.getSpots()));

        parking.giveTicket();
        parking.giveTicket();

        System.out.println(Ticket.getTickets());

        parking.endTicket(2,2,"2019-09-23T08:00");
        parking.giveTicket();
        System.out.println(Ticket.getTickets());
        parking.endTicket(2,6);

        parking.monthlySpotsUsage(2019, JULY,SEPTEMBER);

        parking.monthlyRevenue(2019, JULY,SEPTEMBER);


    }
}