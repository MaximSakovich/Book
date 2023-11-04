package book.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest {

    @Test
    void values() {
        UserRole[] roles = UserRole.values();
        assertEquals(2, roles.length);
        assertEquals(UserRole.ADMIN, roles[0]);
        assertEquals(UserRole.CLIENT, roles[1]);
    }

    @Test
    void valueOf() {
        UserRole role1 = UserRole.valueOf("ADMIN");
        assertEquals(UserRole.ADMIN, role1);

        UserRole role2 = UserRole.valueOf("CLIENT");
        assertEquals(UserRole.CLIENT, role2);
    }
}