package comTest.testStubs.mock;


import comTest.testStubs.fake.book.Book;
import comTest.testStubs.fake.book.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BookServiceTestMock {

    @Test
    public void should_save_a_book() {
        MockReppsitory mockReppsitory = new MockReppsitory();
        BookService bookService = new BookService(mockReppsitory);
        Book book1 = Book.builder().bookId(1).name("book-1").price(300).publishedDate(LocalDate.now()).build();
        Book book2 = Book.builder().bookId(2).name("book-2").price(300).publishedDate(LocalDate.now()).build();
        bookService.addBook(book1);
        Assertions.assertEquals(1, mockReppsitory.getTimesCalled());
        Assertions.assertEquals(book1, mockReppsitory.getRecentlyCalledWith());
        Assertions.assertTrue(mockReppsitory.getTimesCalled()>0);

    }
}
