package comTest.testStubs.fake.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeBookRepository implements BookRepository {

    ArrayList<Book> books = new ArrayList();

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }

    @Override
    public List<Book> findOlderBooks(LocalDate date) {
        return null;
    }


}
