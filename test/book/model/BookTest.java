package book.model;

import book.lib.ListBook;
import book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book("Title", "Author", 1L, false);
    }

    @Test
    void getReader_ReturnsReader() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        book.setReader(user);
        assertEquals(user, book.getReader());
    }

    @Test
    void getTitle_ReturnsTitle() {
        assertEquals("Title", book.getTitle());
    }

    @Test
    void setTitle_SetsTitle() {
        book.setTitle("New Title");
        assertEquals("New Title", book.getTitle());
    }

    @Test
    void getAuthor_ReturnsAuthor() {
        assertEquals("Author", book.getAuthor());
    }

    @Test
    void setAuthor_SetsAuthor() {
        book.setAuthor("New Author");
        assertEquals("New Author", book.getAuthor());
    }

    @Test
    void getId_ReturnsId() {
        assertEquals(1L, book.getId());
    }

    @Test
    void isTaken_ReturnsTakenStatus() {
        assertFalse(book.isTaken());
    }

    @Test
    void setTaken_SetsTakenStatus() {
        book.setTaken(true);
        assertTrue(book.isTaken());
    }

    @Test
    void getTakenDate_ReturnsTakenDate() {
        assertNull(book.getTakenDate());
    }

    @Test
    void setReader_SetsReader() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        book.setReader(user);
        assertEquals(user, book.getReader());
    }

    @Test
    void isTaken_ReturnsFalseByDefault() {
        assertFalse(book.isTaken());
    }

    @Test
    void setTaken_SetsTaken() {
        book.setTaken(true);
        assertTrue(book.isTaken());
    }

    @Test
    void setTakenDate_SetsTakenDate() {
        book.setTakenDate("2023-11-04");
        assertEquals("2023-11-04", book.getTakenDate());
    }
}