package book.repository;

import book.lib.ListBook;
import book.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        ListBook<Book> books = new ListBook<>();
        bookRepository = new BookRepository(books);
    }

    @Test
    void addBook() {
        Book book = new Book("Title", "Author", 123L, false);
        bookRepository.addBook(book);
        assertEquals(1, bookRepository.getAllBooks().size());
    }

    @Test
    void removeBook() {
        Book book = new Book("Title", "Author", 123L, false);
        bookRepository.addBook(book);
        bookRepository.removeBook(book);
        assertEquals(0, bookRepository.getAllBooks().size());
    }

    @Test
    void getAllBooks() {
        assertEquals(0, bookRepository.getAllBooks().size());
    }



    @Test
    void getBookById() {
        Book book = new Book("Title", "Author", 123L, false);
        bookRepository.addBook(book);
        Book retrievedBook = bookRepository.getBookById(123L);
        assertNotNull(retrievedBook);
        assertEquals("Title", retrievedBook.getTitle());
    }

    @Test
    void getBookById_NotFound() {
        Book book = new Book("Title", "Author", 123L, false);
        bookRepository.addBook(book);
        Book retrievedBook = bookRepository.getBookById(456L);
        assertNull(retrievedBook);
    }
    @Test
    void addBook_UpdatesBookList() {
        Book book = new Book("Title", "Author", 123L, false);
        bookRepository.addBook(book);
        assertEquals(1, bookRepository.getAllBooks().size());
    }

    @Test
    void removeBook_RemovesFromList() {
        Book book = new Book("Title", "Author", 123L, false);
        bookRepository.addBook(book);
        bookRepository.removeBook(book);
        assertEquals(0, bookRepository.getAllBooks().size());
    }
}