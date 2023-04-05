package comTest.testStubs.fake.ticket;

import java.util.ArrayList;
import java.util.List;

public class FakeTicketRepository implements TicketRepository {

    ArrayList<Ticket> tickets = new ArrayList();

    @Override
    public List<Ticket> findAll() {
        return tickets;
    }

    @Override
    public void save(Ticket ticket) {
        tickets.add(ticket);
    }
}
