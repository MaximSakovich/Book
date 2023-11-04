package book.service;


import book.lib.ListBook;
import book.model.Book;
import book.model.User;
import book.model.UserRole;
import book.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository(new ListBook<>());
        bookService = new BookService(bookRepository);
    }

    @Test
    void borrowBook() {
        // Implement test for borrowBook method
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        Book book = new Book("Title", "Author", 1L, false);
        bookService.borrowBook(user, book, "2023-11-04");
        assertTrue(book.isTaken());
    }

    @Test
    void findBooksByTitle() {
        // Implement test for findBooksByTitle method
        Book book1 = new Book("Title1", "Author1", 1L, false);
        Book book2 = new Book("Title2", "Author2", 2L, false);
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        assertEquals(1, bookService.findBooksByTitle("Title1").size());
        assertEquals(1, bookService.findBooksByTitle("Title2").size());
    }
    @Test
    void getBooksByAuthor() {
        // Implement test for getBooksByAuthor method
        Book book1 = new Book("Title1", "Author", 1L, false);
        Book book2 = new Book("Title2", "Author", 2L, false);
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        assertEquals(2, bookService.getBooksByAuthor("Author").size());
    }

    @Test
    void findBookById() {
        // Implement test for findBookById method
        Book book1 = new Book("Title1", "Author1", 1L, false);
        Book book2 = new Book("Title2", "Author2", 2L, false);
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        assertEquals(book1, bookService.findBookById(1));
        assertEquals(book2, bookService.findBookById(2));
    }
    @Test
    void changeBorrowDate() {
        // Implement test for changeBorrowDate method
        Book book = new Book("Title", "Author", 1L, true);
        String newDate = "2023-11-05";
        bookService.changeBorrowDate(book, newDate);
        assertEquals(newDate, book.getTakenDate());
    }

    @Test
    void getDaysBookHasBeenTaken() {
        // Implement test for getDaysBookHasBeenTaken method
        Book book = new Book("Title", "Author", 1L, true);
        book.setTakenDate("2023-10-25");
        long daysTaken = bookService.getDaysBookHasBeenTaken(book);
        assertEquals(10, daysTaken);
    }

    @Test
    void getAvailableBooks() {
        // Implement test for getAvailableBooks method
        Book book1 = new Book("Title1", "Author1", 1L, false);
        Book book2 = new Book("Title2", "Author2", 2L, true);
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        assertEquals(1, bookService.getAvailableBooks().size());
    }
    @Test
    void findBookByTitle() {
        // Implement test for findBookByTitle method
        Book book = new Book("Title", "Author", 1L, true);
        bookRepository.addBook(book);
        assertEquals(book, bookService.findBookByTitle("Title"));
    }

    @Test
    void getBorrowedBooks() {
        // Implement test for getBorrowedBooks method
        Book book1 = new Book("Title1", "Author1", 1L, true);
        Book book2 = new Book("Title2", "Author2", 2L, false);
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        assertEquals(1, bookService.getBorrowedBooks().size());
    }
    @Test
    void editBookInfo() {
        // Implement test for editBookInfo method
        Book book = new Book("Title", "Author", 1L, true);
        bookRepository.addBook(book);
        bookService.editBookInfo(book, "New Title", "New Author");
        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
    }
    @Test
    void findBookByTitleNew() {
        // Implement test for findBookByTitle method
        Book book = new Book("Title", "Author", 1L, true);
        bookRepository.addBook(book);
        assertEquals(book, bookService.findBookByTitle("Title"));
    }

    @Test
    void getAvailableBooksNew() {
        // Implement test for getAvailableBooks method
        Book book1 = new Book("Title1", "Author1", 1L, true);
        Book book2 = new Book("Title2", "Author2", 2L, false);
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        assertEquals(1, bookService.getAvailableBooks().size());
    }
}