package book.service;

import book.lib.ListBook;
import book.model.Book;
import book.model.User;
import book.repository.BookRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Метод взятия книги в библиотеке с фиксацией даты
    public void borrowBook(User reader, Book book, String date) {
        if (book.isTaken()) {
            System.out.println("Книга уже взята другим читателем.");
        } else {
            book.setTaken(true);
            book.setTakenDate(date);
            book.setReader(reader);
        }
    }

    // Метод возврата книги в библиотеку
    public void returnBook(User reader, Book book) {
        book.setTaken(false);
        book.setTakenDate("");
        //book.setReader(null);
    }

    public ListBook<Book> findBooksByTitle(String title) {
        ListBook<Book> booksByTitle = new ListBook<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.getTitle().equals(title)) {
                booksByTitle.add(book);
            }
        }
        return booksByTitle;
    }

    public ListBook<Book> getBooksByAuthor(String author) {
        ListBook<Book> booksByAuthor = new ListBook<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.getAuthor().equals(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    public ListBook<Book> searchBooksByTitle(String title) {
        ListBook<Book> booksByTitle = new ListBook<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.getTitle().contains(title)) {
                booksByTitle.add(book);
            }
        }
        return booksByTitle;
    }

    // Добавленные методы
    public Book findBookByTitle(String title) {
        for (Book book : bookRepository.getAllBooks()) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Book findBookById(int id) {
        for (Book book : bookRepository.getAllBooks()) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    public ListBook<Book> getBorrowedBooks() {
        ListBook<Book> borrowedBooks = new ListBook<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.isTaken()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    public ListBook<Book> getBorrowedBooks(User user) {
        ListBook<Book> borrowedBooks = new ListBook<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.isTaken() && book.getReader() != null && book.getReader().equals(user)) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    public void editBookInfo(Book book, String newTitle, String newAuthor) {
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        System.out.println("Информация о книге успешно отредактирована.");
    }
    public void changeBorrowDate(Book book, String date) {
        book.setTakenDate(date);
    }
    public void changeBorrowDate2(Book book, String newDate) {
        book.setTakenDate(newDate);
    }

    public long getDaysBookHasBeenTaken(Book book) {
        if (!book.isTaken()) {
            return 0;
        }
        try {
            String takenDateStr = book.getTakenDate();
            if (takenDateStr == null || takenDateStr.isEmpty()) {
                return 0;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate takenDate = LocalDate.parse(takenDateStr, formatter);
            LocalDate currentDate = LocalDate.now();
            return ChronoUnit.DAYS.between(takenDate, currentDate);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка разбора даты: " + e.getMessage());
            return 0;
        }
    }

    public ListBook<Book> getAvailableBooks() {
        ListBook<Book> availableBooks = new ListBook<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (!book.isTaken()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    public ListBook<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}