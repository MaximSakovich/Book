package book.repository;

import book.lib.ListUser;
import book.model.User;

public class UserRepository {

    private final ListUser<User> readers;

    public UserRepository() {
        this.readers = new ListUser<>();
    }

    // Метод добавления читателя
    public void addReader(User user) {
        readers.add(user);
    }

    // Метод удаления читателя
    public void removeReader(User user) {
        readers.remove(user);
    }

   public ListUser<User> getAllReaders() {return readers;
   }

// Метод позволяет получить пользователя по имени и фамилии
    public User getReaderByName(String firstName, String lastName) {
        for (User user : readers) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                return user;
            }
        }
        return null;
    }
    // Метод позволяет получить пользователя по логину
    public User getReaderByUsername(String username) {
        for (User user : readers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}