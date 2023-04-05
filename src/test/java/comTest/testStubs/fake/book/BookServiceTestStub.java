package comTest.testStubs.fake.book;


import comTest.testStubs.stub.StubRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class BookServiceTestStub {

    @Test
    public void should_update_price_per_discount() {
        StubRepository stubRepository = new StubRepository();
        BookService bookService = new BookService(stubRepository);
        List<Book> books = bookService.applyDiscountOnOlderBooks(LocalDate.now(), 20);
        Assertions.assertEquals(160, books.get(0).getPrice());
    }

}
