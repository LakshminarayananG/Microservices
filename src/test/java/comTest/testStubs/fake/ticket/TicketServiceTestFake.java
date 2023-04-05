package comTest.testStubs.fake.ticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TicketServiceTestFake {

    @Test
    public void should_return_all_books() {
        // implement triple a format arrange, act, assert
        // arrange
        FakeTicketRepository fakeTicketRepository = new FakeTicketRepository();
        TicketService ticketService = new TicketService(fakeTicketRepository);
        // arrange data
        Ticket ticket1 = Ticket.builder().bookingId(1).name("Mani").bus("bus-1").gender("Male").timings("7:00PM").build();
        Ticket ticket2 = Ticket.builder().bookingId(2).name("Shivam").bus("bus-2").gender("Male").timings("9:00PM").build();

        ticketService.addTicket(ticket1);
        ticketService.addTicket(ticket2);

        // act
        List<Ticket> allTickets = ticketService.getAllTickets();

        // assert
        Assertions.assertEquals(2, allTickets.size());
        Assertions.assertEquals("bus-2", allTickets.get(1).getBus());
    }

}
