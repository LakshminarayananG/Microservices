package comTest.testStubs.dummy;

import comTest.testStubs.fake.book.Book;
import comTest.testStubs.fake.book.FakeBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookServiceTestFake {

    @Test
    public void should_return_all_books() {
        // implement triple a format arrange, act, assert
        // arrange
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        DummyEmailService dummyEmailService = new DummyEmailService();
        BookService bookService = new BookService(fakeBookRepository, dummyEmailService);
        // arrange data
        Book book1 = Book.builder().bookId(1).price(200).name("book-1").build();
        Book book2 = Book.builder().bookId(2).price(400).name("book-2").build();

        bookService.addBook(book1);
        bookService.addBook(book2);

        // act
        List<Book> allBooks = bookService.getAllBooks();

        // assert
        Assertions.assertEquals(2, allBooks.size());
        Assertions.assertEquals("book-2", allBooks.get(1).getName());

    }

}