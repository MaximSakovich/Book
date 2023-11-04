package book.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "john@example.com", "johndoe", "password", 1L, UserRole.CLIENT);
    }

    @Test
    void getFirstName_ReturnsFirstName() {
        assertEquals("John", user.getFirstName());
    }

    @Test
    void setFirstName_SetsFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    void getLastName_ReturnsLastName() {
        assertEquals("Doe", user.getLastName());
    }

    @Test
    void setLastName_SetsLastName() {
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    void getEmail_ReturnsEmail() {
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void setEmail_SetsEmail() {
        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    void getUsername_ReturnsUsername() {
        assertEquals("johndoe", user.getUsername());
    }

    @Test
    void setUsername_SetsUsername() {
        user.setUsername("janesmith");
        assertEquals("janesmith", user.getUsername());
    }

    @Test
    void getPassword_ReturnsPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    void setPassword_SetsPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    void getRole_ReturnsRole() {
        assertEquals(UserRole.CLIENT, user.getRole());
    }

    @Test
    void setRole_SetsRole() {
        user.setRole(UserRole.ADMIN);
        assertEquals(UserRole.ADMIN, user.getRole());
    }

    @Test
    void getId_ReturnsId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void testToString() {
        String expectedString = "User{firstName='John', lastName='Doe', email='john@example.com', username='johndoe', password='password', id=1}";
        assertEquals(expectedString, user.toString());
    }
}