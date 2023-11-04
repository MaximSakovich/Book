package book.model;


import java.util.Date;

public class Book {
    private String title;
    private String author;
    private final Long id;
    private boolean isTaken;// взята ли книга кем-то в настоящее время

    private String takenDate;//дата выдачи книги читателю
    private User reader;

    public Book(String title, String author, Long id, boolean isTaken) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.isTaken = isTaken;
        this.takenDate = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", id=" + id +
                ", isTaken=" + isTaken +
                ", takenDate=" + takenDate +
                '}';
    }

    public User getReader() {
        return this.reader;
    }

    public void setReader(User reader) {
        this.reader = reader;
        this.isTaken = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public String getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(String takenDate) {
        this.takenDate = takenDate;
    }

}
