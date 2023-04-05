package comTest.testStubs.fake.book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository {
    // get all the books by querying DB
    public List<Book> findAll();
    // save the book object into DB
    public void save(Book book);

    public List<Book> findOlderBooks(LocalDate date);
}
