package book.service;

import book.model.User;
import book.model.UserRole;
import book.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    void saveReader() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        userService.saveReader(user);
        assertEquals(1, userRepository.getAllReaders().size());
    }

    @Test
    void findReaderByName() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        userService.saveReader(user);
        User foundUser = userService.findReaderByName("John", "Doe");
        assertEquals(user, foundUser);
    }

    @Test
    void registerUser() {
        userService.registerUser("Jane", "Doe", "jane@example.com", "janedoe", "password", 2L);
        assertEquals(1, userRepository.getAllReaders().size());
    }

    @Test
    void isReaderExistsByEmail() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        userService.saveReader(user);
        assertTrue(userService.isReaderExistsByEmail("john@example.com"));
        assertFalse(userService.isReaderExistsByEmail("janedoe@example.com"));
    }

    @Test
    void isEmailValid() {
        // Implement test for isEmailValid method
        assertTrue(userService.isEmailValid("test@example.com"));
        assertFalse(userService.isEmailValid("invalid_email.com"));
    }

    @Test
    void isPasswordValid() {
        // Implement test for isPasswordValid method
        assertTrue(userService.isPasswordValid("ValidPass1@"));
        assertFalse(userService.isPasswordValid("short"));
    }
    @Test
    void findReaderByUsername() {
        // Implement test for findReaderByUsername method
        User user = new User("John", "Doe", "john.doe@example.com", "johndoe", "Password1!", 1L, UserRole.CLIENT);
        userRepository.addReader(user);
        assertEquals(user, userService.findReaderByUsername("johndoe"));
        assertNull(userService.findReaderByUsername("nonexistentuser"));
    }

    @Test
    void authenticate() {
        // Implement test for authenticate method
        User user = new User("John", "Doe", "john.doe@example.com", "johndoe", "Password1!", 1L, UserRole.CLIENT);
        userRepository.addReader(user);
        assertTrue(userService.authenticate("johndoe", "Password1!"));
        assertFalse(userService.authenticate("johndoe", "InvalidPassword"));
    }
}