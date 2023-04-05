package comTest.testStubs.stub;

import comTest.testStubs.fake.book.Book;
import comTest.testStubs.fake.book.BookRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StubRepository implements BookRepository {


    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public List<Book> findOlderBooks(LocalDate date) {
        ArrayList<Book> books = new ArrayList<>();
        Book book1 = Book.builder().bookId(1).price(200).name("book-1").publishedDate(LocalDate.now()).build();
        Book book2 = Book.builder().bookId(2).price(400).name("book-2").publishedDate(LocalDate.now().minusDays(10)).build();

        books.add(book1);
        books.add(book2);
        return books;
    }
}
