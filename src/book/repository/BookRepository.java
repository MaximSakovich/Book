package book.repository;

import book.lib.ListBook;
import book.model.Book;


public class BookRepository {
    private final ListBook<Book> books;


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

    public Book getBookById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
}