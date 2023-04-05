package comTest.testStubs.spy;

import comTest.testStubs.fake.book.BookRepository;
import comTest.testStubs.fake.book.Book;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class SpyReppsitory implements BookRepository {

    int timesCalled=0;
    Book recentlyCalledWith=null;
    ArrayList<Book> books = new ArrayList();

    @Override
    public List<Book> findAll() {
        return null;
    }

    // Verify method being called
    // Verify method was called with the right object
    @Override
    public void save(Book book) {
        timesCalled++;
        recentlyCalledWith=book;
        books.add(book);
    }

    public void verify(int times, Book book) {
        Assertions.assertEquals(times, timesCalled);
        Assertions.assertEquals(book, recentlyCalledWith);
    }

    @Override
    public List<Book> findOlderBooks(LocalDate date) {
        return null;
    }
}
