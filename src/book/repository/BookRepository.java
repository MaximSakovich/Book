package book.repository;

import book.lib.ListBook;
import book.model.Book;


public class BookRepository {
    private ListBook<Book> books;


    public BookRepository(ListBook<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
        System.out.println("Книга успешно добавлена в репозиторий.");
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public ListBook<Book> getAllBooks() {
        return books;
    }

    // метод возвращает список книг, доступных для взятия, из общего списка книг.
    public ListBook<Book> getAvailableBooks() {
        ListBook<Book> availableBooks = new ListBook<>();
        for (Book book : books) {
            if (!book.isTaken()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public Book getBookById(Long id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}