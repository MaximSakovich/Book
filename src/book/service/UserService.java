package book.service;

import book.lib.ListUser;
import book.model.User;
import book.model.UserRole;
import book.repository.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveReader(User user) {
      userRepository.addReader(user);
   }

    public User findReaderByName(String firstName, String lastName) {
        return userRepository.getReaderByName(firstName, lastName);
    }

    public void registerUser(String firstname, String lastname, String email, String username, String password, Long id) {
        User newUser = new User(firstname, lastname, email, username, password, id, UserRole.CLIENT);
        userRepository.addReader(newUser);
    }

    public boolean isReaderExistsByEmail(String email) {
        for (User user : userRepository.getAllReaders()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public ListUser<User> findAllReaders() {
        return userRepository.getAllReaders();
    }

    // Метод проверки электронной почты
    public boolean isEmailValid(String email) {
            int indexAt = email.indexOf('@');
            if (indexAt == -1 || indexAt != email.lastIndexOf('@')) return false;
            if (email.indexOf('.', indexAt) == -1) return false;
            if (email.lastIndexOf('.') >= email.length() - 2) return false;
            for (int i = 0; i < email.length(); i++) {
                char c = email.charAt(i);
                if (!(Character.isAlphabetic(c) || Character.isDigit(c) || c == '.'
                        || c == '_' || c == '-' || c == '@')) {
                    return false;
                }
            }
            return true;
        }

//Метод проверки пароля
    public boolean isPasswordValid(String password) {
        // Пароль должен содержать как минимум 8 символов
        // и иметь хотя бы одну цифру, одну прописную и одну заглавную букву,
        // а также один специальный символ из списка !%$@&.
        if (password.length() < 8) {
            return false;
        }

        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecial = false;

        String specialCharacters = "!%$@&";

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (specialCharacters.indexOf(c) != -1) {
                hasSpecial = true;
            }
        }

        return hasDigit && hasLower && hasUpper && hasSpecial;
    }

    public boolean authenticate(String username, String password) {
        for (User user : userRepository.getAllReaders()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User findReaderByUsername(String username) {
        for (User user : userRepository.getAllReaders()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    //Метод определяющий права пользователей
    public void displayUserPermissions(User user) {
        if (user.getRole() == UserRole.ADMIN) {
            System.out.println("Пользователь " + user.getUsername() + " имеет права администратора.");
        } else if (user.getRole() == UserRole.CLIENT) {
            System.out.println("Пользователь " + user.getUsername() + " имеет права клиента.");
        } else {
            System.out.println("Неправильная роль пользователя.");
        }
    }

}