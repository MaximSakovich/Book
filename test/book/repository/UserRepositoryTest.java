package book.repository;

import book.model.User;
import book.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void addReader() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        userRepository.addReader(user);
        assertEquals(1, userRepository.getAllReaders().size());
    }

    @Test
    void removeReader() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        userRepository.addReader(user);
        userRepository.removeReader(user);
        assertEquals(0, userRepository.getAllReaders().size());
    }

    @Test
    void getAllReaders() {
        assertEquals(0, userRepository.getAllReaders().size());
    }

    @Test
    void getReaderByName() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        userRepository.addReader(user);
        User retrievedUser = userRepository.getReaderByName("John", "Doe");
        assertNotNull(retrievedUser);
        assertEquals("john@example.com", retrievedUser.getEmail());
    }

    @Test
    void getReaderByUsername() {
        User user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
        userRepository.addReader(user);
        User retrievedUser = userRepository.getReaderByUsername("johndoe");
        assertNotNull(retrievedUser);
        assertEquals("John", retrievedUser.getFirstName());
    }



    @Test
    void getReaderByName_NonexistentUser_ReturnsNull() {
        User retrievedUser = userRepository.getReaderByName("Nonexistent", "User");
        assertNull(retrievedUser);
    }

    @Test
    void getReaderByUsername_NonexistentUser_ReturnsNull() {
        User retrievedUser = userRepository.getReaderByUsername("nonexistentuser");
        assertNull(retrievedUser);
    }
}