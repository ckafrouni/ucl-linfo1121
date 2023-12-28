package fundamentals;

import org.javagrader.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StackTest {
    List<Stack<Integer>> stacks;
    Stack<Integer> arrStack;
    Stack<Integer> linkStack;

    @BeforeEach
    void init() {
        stacks = Arrays.asList(
                new ArrayStack<>(),
                new LinkedStack<>()
        );
        arrStack = new ArrayStack<>();
        linkStack = new LinkedStack<>();
    }

    @Test
    void emptyArrayStack() {
        assertTrue(arrStack.empty());
    }

    @Test
    public void testSimpleStackArray() {
        Stack<Integer> stack = arrStack;
        stack.push(1);
        assertEquals(1, stack.peek());
        stack.push(2);
        stack.push(3);
        assertFalse(stack.empty());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
        assertTrue(stack.empty());
    }


    @Test
    public void testSimpleStackList() {
        Stack<Integer> stack = linkStack;
        stack.push(1);
        assertEquals(1, stack.peek());
        stack.push(2);
        stack.push(3);
        assertFalse(stack.empty());
        assertEquals(3, (int) stack.pop());
        assertEquals(2, (int) stack.pop());
        assertEquals(1, (int) stack.pop());
        assertTrue(stack.empty());
    }

}

