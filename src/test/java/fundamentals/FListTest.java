package fundamentals;

import org.javagrader.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;


public class FListTest {
    FList<Integer> list;

    @BeforeEach
    void init() {
        list = FList.nil();
    }

    @Test
    public void testNil() {

        assertThrows(IllegalArgumentException.class, () -> {
            list.head();
        });
    }

    @Test
    public void testCons() {


        for (int i = 0; i < 10; i++) {
            list = list.cons(i);
        }
        for (int i = 9; i >= 0; i--) {
            assertEquals(Integer.valueOf(i), list.head());
            list = list.tail();
        }
        assertTrue(list.isEmpty());
    }

    @Test
    public void testMap() {


        for (int i = 0; i < 10; i++) {
            list = list.cons(i);
        }

        list = list.map(i -> i + 1);
        // will print 10,9,8 .. 1
        for (int i = 10; i >= 1; i--) {
            assertEquals(Integer.valueOf(i), list.head());
            list = list.tail();
        }
        assertTrue(list.isEmpty());
    }

    @Test
    public void testFilter() {


        for (int i = 0; i < 10; i++) {
            list = list.cons(i);
        }

        list = list.filter(i -> i % 2 == 0);
        // will print 8,6,4,2,0
        for (int i = 8; i >= 0; i -= 2) {
            assertEquals(Integer.valueOf(i), list.head());
            list = list.tail();
        }
        assertTrue(list.isEmpty());

    }

    @Test
    public void testIterator() {


        for (int i = 0; i < 10; i++) {
            list = list.cons(i);
        }
        Iterator<Integer> ite = list.iterator();
        int i = 9;
        while (ite.hasNext()) {
            Integer v = ite.next();
            assertEquals(Integer.valueOf(i), v);
            i--;
        }

    }

}
