package book.lib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListUserTest {
    private ListUser<String> list;

    @BeforeEach
    void setUp() {
        list = new ListUser<>();
    }

    @Test
    void iterator() {
        assertNotNull(list.iterator());
    }

    @Test
    void add() {
        list.add("User1");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void remove() {
        list.add("User1");
        list.remove("User1");
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void size() {
        list.add("User1");
        list.add("User2");
        assertEquals(2, list.size());
    }
}