package comTest.testStubs.spy;


import comTest.testStubs.fake.book.BookService;
import comTest.testStubs.fake.book.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BookServiceTestSpy {

    @Test
    public void should_save_a_book() {
        SpyReppsitory spyRepository = new SpyReppsitory();
        BookService bookService = new BookService(spyRepository);
        Book book1 = Book.builder().bookId(1).name("book-1").price(300).publishedDate(LocalDate.now()).build();
        Book book2 = Book.builder().bookId(2).name("book-2").price(30).publishedDate(LocalDate.now()).build();
        bookService.addBook(book1);
        spyRepository.verify(1, book1);
        bookService.addBook(book2);
        spyRepository.verify(1, book1);
        spyRepository.verify(2, book2);
    }
}
