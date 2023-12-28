package fundamentals;

import org.javagrader.ConditionalOrderingExtension;
import org.javagrader.Grade;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


import java.util.*;


public class CircularLinkedListTest {
    CircularLinkedList<Integer> lst;

    @BeforeEach
    void init() {
        lst = new CircularLinkedList<>();
    }

    @Test
    public void testEmpty() {
        assertTrue(lst.isEmpty());
    }

    @Test
    public void testEnqueueOne() {
        lst.enqueue(1);
        assertFalse(lst.isEmpty());
    }

    @Test
    public void removeFirst() {
        int firstIndex = 0;
        int firstValue = 11;
        lst.enqueue(firstValue);
        int removedValue = lst.remove(firstIndex);
        assertTrue(lst.isEmpty());
        assertEquals(firstValue, removedValue);
    }

    @Test
    public void removeSecond() {
        int secondIndex = 1;
        int secondValue = 22;
        lst.enqueue(1);
        lst.enqueue(secondValue);
        lst.enqueue(3);
        assertEquals(3, lst.size());
        int removedValue = lst.remove(secondIndex);
        assertFalse(lst.isEmpty());
        assertEquals(secondValue, removedValue);
    }

    @Test
    public void simpleTestWithoutRemove(){
        // Build a circular linked list as follows
        // 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9
        List<Integer> correct = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            lst.enqueue(i);
            correct.add(i);
        }
        Iterator<Integer> aIter = lst.iterator();
        Iterator<Integer> bIter = correct.iterator();
        assertEquals(correct.size(),lst.size());
        while (bIter.hasNext()) {
            assertTrue(aIter.hasNext());
            assertEquals(bIter.next(),aIter.next());
        }
    }

    @Test
    public void simpleTestWithRemoving(){
        // Build a circular linked list as follows
        // 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 .. -> 49
        List<Integer> correct = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            lst.enqueue(i);
            correct.add(i);
        }

        // Remove 0, 10, [End], 25, 30
        lst.remove(0);
        correct.remove(0);
        lst.remove(10);
        correct.remove(10);
        lst.remove(correct.size() - 1);
        correct.remove(correct.size() - 1);
        lst.remove(25);
        correct.remove(25);
        lst.remove(30);
        correct.remove(30);


        Iterator<Integer> aIter = lst.iterator();
        Iterator<Integer> bIter = correct.iterator();
        assertEquals(correct.size(),lst.size());
        while (bIter.hasNext()) {
            assertTrue(aIter.hasNext());
            assertEquals(bIter.next(),aIter.next());
        }
    }

}
