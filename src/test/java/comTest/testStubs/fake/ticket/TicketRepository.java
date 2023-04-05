package comTest.testStubs.fake.ticket;

import java.util.List;

public interface TicketRepository {
    // get all the books by querying DB
    public List<Ticket> findAll();
    // save the book object into DB
    public void save(Ticket ticket);
}
