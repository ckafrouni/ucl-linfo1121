package fundamentals;

import org.javagrader.ConditionalOrderingExtension;
import org.javagrader.Grade;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StackWithTwoQueuesTest {
    StackWithTwoQueues<Integer> stack;

    @BeforeEach
    void init() {
        stack = new StackWithTwoQueues<>();
    }


    @Test
    public void simpleTest() {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertFalse(stack.empty());
        for (int i = 9; i >= 0; i--) {
            assertEquals(i, (int) stack.pop());
        }
        assertTrue(stack.empty());
    }

    @Test
    void pushPopOne() {
        stack.push(2);
        assertFalse(stack.empty());

        int popped = stack.pop();
        assertTrue(stack.empty());
        assertEquals(2, popped);
    }

    @Test
    void pushPopTwo() {
        stack.push(1);
        stack.push(2);
        assertFalse(stack.empty());

        int popped = stack.pop();
        assertFalse(stack.empty());
        assertEquals(2, popped);

        popped = stack.pop();
        assertTrue(stack.empty());
        assertEquals(1, popped);
    }

    @Test
    void peek() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.peek());

        stack.pop();

        assertEquals(2, stack.peek());

        stack.pop();

        assertEquals(1, stack.peek());

        stack.pop();



    }


}
