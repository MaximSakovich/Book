package book.view;

import book.lib.ListBook;
import book.lib.ListUser;
import book.model.Book;
import book.model.User;

import book.model.UserRole;
import book.repository.BookRepository;
import book.repository.UserRepository;
import book.service.BookService;
import book.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private final BookService bookService;
    private final UserService userService;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final Scanner scanner;

    public Menu(BookService bookService, UserService userService,
                BookRepository bookRepository, UserRepository userRepository) {
        this.bookService = bookService;
        this.userService = userService;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            // выводим команды меню
            System.out.println("1. Добавление книги");
            System.out.println("2. Список всех книг");
            System.out.println("3. Взятие книги из библиотеки с фиксацией даты");
            System.out.println("4. Возврат книги в библиотеку");
            System.out.println("5. Список всех свободных книг");
            System.out.println("6. Список всех книг, находящихся сейчас у читателей");
            System.out.println("7. Список всех по автору");
            System.out.println("8. Поиск книг по названию книги");
            System.out.println("9. Пользователи библиотеки");
            System.out.println("10. Авторизация пользователей");
            System.out.println("11. Регистрация пользователя");
            System.out.println("12. Список книг, которые сейчас у пользователя");
            System.out.println("13. Права доступа у пользователей, в зависимости от роли");
            System.out.println("14. Редактирование информации о книге");
            System.out.println("15. Посмотреть у кого находится книга, если занята");
            System.out.println("16. Дата, когда была книга взята на прокат (измените дату выдачи книги)");
            System.out.println("17. Получить информацию сколько дней книга находится у пользователя");
            System.out.println("0. Выход");

            while (!scanner.hasNextInt()) {
                System.err.println("Неверная команда, попробуйте снова.");
                scanner.next();
            }
            int command = scanner.nextInt();
            scanner.nextLine();
            if (command < 0 || command > 17) {
                System.err.println("Неверная команда, попробуйте снова.");
                continue;
            }

            switch (command) {
                case 0: {
                    System.out.println("Выход из программы...");
                    System.exit(0);
                }
                break;

                case 1: {
                    // 1. Добавление книги
                    System.out.println("Введите название книги:");
                    String title = scanner.nextLine();
                    System.out.println("Введите автора книги:");
                    String author = scanner.nextLine();
                    Book newBook = new Book(title, author, (long) (bookRepository.getAllBooks().size() + 1), false);
                    bookRepository.addBook(newBook);
                    System.out.println("Книга добавлена: " + newBook);
                }
                break;

                case 2: {
                    // 2. Список всех книг
                    ListBook<Book> allBooks = bookService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("В библиотеке нет доступных книг.");
                    } else {
                        System.out.println("Список всех книг:");
                        for (Book book : allBooks) {
                            System.out.println(book);
                        }
                    }
                }
                break;

                case 3: {
                    // Взятие книги из библиотеки с фиксацией даты
                    System.out.println("Введите ваше имя:");
                    String firstName = scanner.nextLine();
                    System.out.println("Введите вашу фамилию:");
                    String lastName = scanner.nextLine();
                    System.out.println("Введите название книги, которую вы хотите взять:");
                    String bookTitle = scanner.nextLine();
                    User reader = userService.findReaderByName(firstName, lastName);
                    if (reader == null) {
                        System.out.println("Читатель с именем и фамилией " + firstName + " " + lastName + " не найден.");
                    } else {
                        Book book = bookService.findBookByTitle(bookTitle);
                        if (book == null) {
                            System.out.println("Книга с названием " + bookTitle + " не найдена.");
                        } else {
                            LocalDate takenDate = LocalDate.now();
                            String takenDateStr = takenDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            bookService.borrowBook(reader, book, takenDateStr);
                            System.out.println("Книга взята читателем: " + firstName + " " + lastName);
                        }
                    }
                }
                break;

                case 4: {
                    // 4. Возврат книги в библиотеку
                    System.out.println("Введите ваше имя:");
                    String firstName = scanner.nextLine();
                    System.out.println("Введите вашу фамилию:");
                    String lastName = scanner.nextLine();
                    System.out.println("Введите название книги, которую вы хотите вернуть:");
                    String title = scanner.nextLine();

                    // Проверка, существует ли читатель с введенным именем
                    User user = userService.findReaderByName(firstName, lastName);
                    if (user == null) {
                        System.out.println("Читатель с именем " + firstName + lastName + " не найден.");
                    } else {
                        // Проверка, существует ли книга с введенным названием
                        Book book = bookService.findBookByTitle(title);
                        if (book == null) {
                            System.out.println("Книга с названием " + title + " не найдена.");
                        } else {
                            bookService.returnBook(user, book);
                            System.out.println("Книга " + title + " возвращена в библиотеку");
                        }
                    }
                }
                break;

                case 5: {
                    // 5. Список всех свободных книг
                    ListBook<Book> availableBooks = bookService.getAvailableBooks();
                    if (availableBooks.isEmpty()) {
                        System.out.println("Нет доступных книг в библиотеке.");
                    } else {
                        System.out.println("Список всех доступных книг:");
                        for (Book book : availableBooks) {
                            System.out.println(book);
                        }
                    }
                }
                break;

                case 6: {
                    // 6. Список всех книг, находящихся сейчас у читателей
                    ListBook<Book> borrowedBooks = bookService.getBorrowedBooks();
                    if (borrowedBooks.isEmpty()) {
                        System.out.println("В настоящее время у читателей нет взятых книг.");
                    } else {
                        System.out.println("Список книг, находящихся у читателей:");
                        for (Book book : borrowedBooks) {
                            User user = book.getReader();
                            System.out.println(book.getTitle() + " - " + user.getFirstName() + " " + user.getLastName());
                        }
                    }
                }
                break;

                case 7: {
                    // 7. Список всех по автору
                    System.out.println("Введите имя автора:");
                    String author = scanner.nextLine();
                    ListBook<Book> booksByAuthor = bookService.getBooksByAuthor(author);
                    if (booksByAuthor.isEmpty()) {
                        System.out.println("Нет книг данного автора.");
                    } else {
                        System.out.println("Список книг автора " + author + ":");
                        for (Book book : booksByAuthor) {
                            System.out.println(book);
                        }
                    }
                }
                break;

                case 8: {
                    // Поиск книг по названию книги
                    System.out.println("Введите название книги для поиска:");
                    String bookTitle = scanner.nextLine();
                    ListBook<Book> foundBooks = bookService.findBooksByTitle(bookTitle);
                    if (foundBooks.isEmpty()) {
                        System.out.println("Книги с названием " + bookTitle + " не найдены.");
                    } else {
                        System.out.println("Найденные книги по запросу " + bookTitle + ":");
                        for (Book book : foundBooks) {
                            System.out.println(book.toString());
                        }
                    }
                }
                break;

                case 9: {
                    // 9. Пользователи библиотеки
                    ListUser<User> allReaders = userService.findAllReaders();
                    if (allReaders.isEmpty()) {
                        System.out.println("В библиотеке нет зарегистрированных пользователей.");
                    } else {
                        System.out.println("Список пользователей библиотеки:");
                        for (User reader : allReaders) {
                            System.out.println(reader);
                        }
                    }
                }
                break;

                case 10: {
                    //10. Авторизация пользователей
                    System.out.println("Введите логин:");
                    String username = scanner.nextLine();
                    System.out.println("Введите пароль:");
                    String password = scanner.nextLine();
                    if (userService.authenticate(username, password)) {
                        System.out.println("Авторизация прошла успешно.");
                    } else {
                        System.out.println("Неверный логин или пароль.");
                    }
                }
                break;

                case 11: {
                    // 11 Регистрации пользователя
                    boolean validInput = false;
                    String email = "";
                    String password = "";
                    System.out.println("Введите логин пользователя:");
                    String username = scanner.nextLine();
                    System.out.println("Введите имя: ");
                    String firstname = scanner.nextLine();
                    System.out.println("Введите фамилию: ");
                    String lastname = scanner.nextLine();

                    while (!validInput) {

                        System.out.println("Введите email: ");
                        email = scanner.nextLine();
                        if (!userService.isEmailValid(email)) {
                            System.err.println("Неверный формат электронной почты. " +
                                    "Пожалуйста, введите корректный е-мейл.");
                        } else {
                            validInput = true;
                        }
                    }

                    validInput = false;
                    while (!validInput) {
                        System.out.println("Придумайте корректный пароль пользователя: ");
                        password = scanner.nextLine();
                        if (!userService.isPasswordValid(password)) {
                            System.err.println("Неверный формат пароля. Попробуйте еще раз!!!");
                            System.err.println("Требования к паролю: ");
                            System.err.println("Длина >= 8, мин 1 цифра, " +
                                    "маленькая буква, большая буква и спец.символ !%$@&");
                        } else {
                            validInput = true;
                        }
                    }
                    User newUser = new User(firstname, lastname, email, username, password,
                            (long) (userRepository.getAllReaders().size() + 1), UserRole.CLIENT);
                    userRepository.addReader(newUser);
                    System.out.println("Пользователь зарегистрирован: " + newUser);
                }
                break;

                case 12: {
                    // 12. Список книг, которые сейчас у пользователя
                    System.out.println("Введите логин пользователя:");
                    String username = scanner.nextLine();
                    User user = userService.findReaderByUsername(username);
                    if (user != null) {
                        ListBook<Book> borrowedBooks = bookService.getBorrowedBooks(user);
                        if (borrowedBooks.isEmpty()) {
                            System.out.println("У данного пользователя нет взятых книг.");
                        } else {
                            System.out.println("Книги, которые сейчас у пользователя " +
                                    user.getFirstName() + " " + user.getLastName() + ":");
                            for (Book book : borrowedBooks) {
                                if (book != null) {
                                    System.out.println(book.getTitle());
                                }
                            }
                        }
                    } else {
                        System.out.println("Пользователь не найден.");
                    }
                }
                break;

                case 13: {
                    // 13. Права доступа у пользователей, в зависимости от роли
                    System.out.println("Введите ваш логин:");
                    String username = scanner.nextLine();
                    User user = userService.findReaderByUsername(username);
                    if (user != null) {
                        System.out.println("Имя: " + user.getFirstName());
                        System.out.println("Фамилия: " + user.getLastName());
                        userService.displayUserPermissions(user);
                    } else {
                        System.out.println("Пользователь не найден.");
                    }
                    break;
                }

                case 14: {
                    // 14. Редактирование информации о книге
                    System.out.println("Введите логин администратора:");
                    String username = scanner.nextLine();
                    System.out.println("Введите пароль администратора:");
                    String password = scanner.nextLine();

                    User user = userService.findReaderByUsername(username);
                    if (user != null && user.getPassword().equals(password) && user.getRole() == UserRole.ADMIN) {
                        System.out.println("Введите ID книги, информацию о которой нужно отредактировать:");
                        Long bookId = scanner.nextLong();
                        scanner.nextLine();
                        Book bookToEdit = bookRepository.getBookById(bookId);
                        if (bookToEdit == null) {
                            System.out.println("Книга с указанным ID не найдена.");
                        } else {
                            System.out.println("Введите новое название книги:");
                            String newTitle = scanner.nextLine();
                            System.out.println("Введите нового автора книги:");
                            String newAuthor = scanner.nextLine();
                            bookService.editBookInfo(bookToEdit, newTitle, newAuthor);
                        }
                    } else {
                        System.out.println("Доступ запрещен. Неправильный логин или пароль.");
                    }
                    break;
                }

                case 15: {
                    // 15. Посмотреть у кого находится книга, если занята
                    System.out.println("Введите логин администратора:");
                    String username = scanner.nextLine();
                    System.out.println("Введите пароль администратора:");
                    String password = scanner.nextLine();

                    User user = userService.findReaderByUsername(username);
                    if (user != null && user.getPassword().equals(password) && user.getRole() == UserRole.ADMIN) {
                        System.out.println("Введите название книги:");
                        String bookTitle = scanner.nextLine();
                        Book book = bookService.findBookByTitle(bookTitle);
                        if (book != null) {
                            if (book.isTaken()) {
                                User reader = book.getReader();
                                System.out.println("Книга " + bookTitle + " находится у читателя: " +
                                        reader.getFirstName() + " " + reader.getLastName());
                            } else {
                                System.out.println("Книга " + bookTitle + " свободна и находится в библиотеке.");
                            }
                        } else {
                            System.out.println("Книга с названием " + bookTitle + " не найдена.");
                        }
                    } else {
                        System.out.println("Доступ запрещен. Неправильный логин или пароль.");
                    }
                    break;
                }

                    case 16: {
                        //16. Дата, когда была книга взята на прокат и смена даты
                        System.out.println("Введите логин администратора:");
                        String username = scanner.nextLine();
                        System.out.println("Введите пароль администратора:");
                        String password = scanner.nextLine();

                        User user = userService.findReaderByUsername(username);
                        if (user != null && user.getPassword().equals(password) && user.getRole() == UserRole.ADMIN) {
                            System.out.println("Введите название книги:");
                            String bookTitle = scanner.nextLine();
                            Book book = bookService.findBookByTitle(bookTitle);
                            if (book != null) {
                                if (book.isTaken()) {
                                    System.out.println("Дата, когда книга была взята на прокат: " + book.getTakenDate());
                                    System.out.println("Вы хотите изменить дату? (yes/no)");
                                    String choice = scanner.nextLine();
                                    if (choice.equals("yes")) {
                                        System.out.println("Введите новую дату в формате гггг-мм-дд:");
                                        String newDate = scanner.nextLine();
                                        bookService.changeBorrowDate2(book, newDate);
                                        System.out.println("Дата изменена успешно.");
                                    } else if (choice.equals("no")) {
                                        System.out.println("Действие отменено.");
                                    } else {
                                        System.out.println("Неверный ввод.");
                                    }
                                } else {
                                    System.out.println("Книга не находится у читателя.");
                                }
                            } else {
                                System.out.println("Книга не найдена.");
                            }
                        } else {
                            System.out.println("Доступ запрещен. Неправильный логин или пароль.");
                        }
                        break;
                    }

                    case 17: {
                        // Получить информацию о том, сколько дней книга находится у пользователя
                        System.out.println("Введите название книги:");
                        String bookTitle = scanner.nextLine();
                        Book book = bookService.findBookByTitle(bookTitle);
                        if (book != null) {
                            long daysTaken = bookService.getDaysBookHasBeenTaken(book);
                            if (daysTaken >= 0) {
                                System.out.println("Книга была взята на прокат " + daysTaken + " дней назад.");
                            } else {
                                System.out.println("Книга не взята на прокат.");
                            }
                        } else {
                            System.out.println("Книга не найдена.");
                        }
                    }
                    break;
                }
            }
        }

    public static void main(String[] args) {
        ListBook<Book> availableBooks = new ListBook<>();
        BookRepository bookRepository = new BookRepository(availableBooks);
        UserRepository userRepository = new UserRepository();
        BookService bookService = new BookService(bookRepository);
        UserService userService = new UserService(userRepository);

        Menu menu = new Menu(bookService, userService, bookRepository, userRepository);

        // Посетители библиотеки
        User user1 = new User("Maxim", "Sakovich", "maximsakovich@gmail.com",
                "maxim", "Password1!", 1L, UserRole.ADMIN);
        User user2 = new User("John", "Doe", "john.doe@example.com",
                "johndoe", "Password2!", 2L, UserRole.CLIENT);
        User user3 = new User("Jane", "Doe", "jane.doe@example.com",
                "janedoe", "Password3!", 3L, UserRole.CLIENT);
        userService.saveReader(user1);
        userService.saveReader(user2);
        userService.saveReader(user3);

        // Создание экземпляров книг
        Book book1 = new Book("Татуировщик Аушвица", "Гизер Моррис", 1L, false);
        Book book2 = new Book("На западном фронте без перемен", "Эрих Мария Ремарк", 2L, false);
        Book book3 = new Book("Инферно", "Дэн Браун", 3L, false);
        Book book4 = new Book("Маркетинг от А до Я", "Филлип Котлер", 4L, false);
        Book book5 = new Book("Четвертая промышленная революция", "Клаус Шваб", 5L, false);
        Book book6 = new Book("Уоррен Баффет - Лучший инвестор мира", "Элис Шрёдер", 6L, false);
        Book book7 = new Book("21 урок для 21 века", "Ювал Ной Харари", 7L, false);
        Book book8 = new Book("Степной волк", "Герман Гессе", 8L, false);
        Book book9 = new Book("Трудно быть богом", "Аркадий и Борис Стругацкие", 9L, false);
        Book book10 = new Book("48 Законов власти", "Роберт Грин", 10L, false);

        availableBooks.add(book1);
        availableBooks.add(book2);
        availableBooks.add(book3);
        availableBooks.add(book4);
        availableBooks.add(book5);
        availableBooks.add(book6);
        availableBooks.add(book7);
        availableBooks.add(book8);
        availableBooks.add(book9);
        availableBooks.add(book10);

        menu.showMenu();
    }
}

