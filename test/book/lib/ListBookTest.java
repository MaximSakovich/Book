package book.lib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListBookTest {
    private ListBook<String> list;

    @BeforeEach
    void setUp() {
        list = new ListBook<>();
    }

    @Test
    void iterator() {
        assertNotNull(list.iterator());
    }

    @Test
    void add() {
        list.add("Book1");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    void remove() {
        list.add("Book1");
        list.remove("Book1");
        assertTrue(list.isEmpty());
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
    }

    @Test
    void size() {
        list.add("Book1");
        list.add("Book2");
        assertEquals(2, list.size());
    }

}