package comTest.testStubs.fake.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookServiceTestFake {

    @Test
    public void should_return_all_books() {
        // implement triple a format arrange, act, assert
        // arrange
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        BookService bookService = new BookService(fakeBookRepository);
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
