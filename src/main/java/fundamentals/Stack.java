package fundamentals;

import java.util.EmptyStackException;

/**
 * Author: Pierre Schaus
 * <p>
 * You have to implement the interface using
 * - a simple linkedList as internal structure
 * - a growing array as internal structure
 */
public interface Stack<E> {

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    public boolean empty();

    /**
     * Returns the first element of the stack, without removing it from the stack
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() throws EmptyStackException;

    /**
     * Remove the first element of the stack and returns it
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() throws EmptyStackException;

    /**
     * Adds an element to the stack
     *
     * @param item the item to add
     */
    public void push(E item);

}

/**
 * Implement the Stack interface above using a simple linked list.
 * You should have at least one constructor withtout argument.
 * You are not allowed to use classes from java.util
 *
 * @param <E>
 */
class LinkedStack<E> implements Stack<E> {

    private Node<E> top;        // the node on the top of the stack
    private int size;        // size of the stack

    // helper linked list class
    private class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public LinkedStack() {
        this.top = new Node<>(null, null);
        this.size = 0;
    }

    @Override
    public boolean empty() {
        // TODO Implement empty method
        return size == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        // TODO Implement peek method
        if (empty()) throw new EmptyStackException();

        return top.item;
    }

    @Override
    public E pop() throws EmptyStackException {
        // TODO Implement pop method
        if (empty()) throw new EmptyStackException();

        E popped = top.item;
        top = top.next;
        size--;

        return popped;
    }

    @Override
    public void push(E item) {
        // TODO Implement push method
        top = new Node<>(item, top);
        size++;
    }
}


/**
 * Implement the Stack interface above using an array as internal representation
 * The capacity of the array should double when the number of elements exceed its length.
 * You should have at least one constructor withtout argument.
 * You are not allowed to use classes from java.util
 *
 * @param <E>
 */
class ArrayStack<E> implements Stack<E> {

    private E[] array;        // array storing the elements on the stack
    private int size;        // size of the stack

    public ArrayStack() {
        array = (E[]) new Object[10];
    }

    @Override
    public boolean empty() {
        // TODO Implement empty method
        return size == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        // TODO Implement peek method
        if (empty()) throw new EmptyStackException();

        return array[size - 1];
    }

    @Override
    public E pop() throws EmptyStackException {
        // TODO Implement pop method
        if (empty()) throw new EmptyStackException();

        E popped = array[--size];
        if (array.length / 4 > size)
            resize(array.length / 2);

        return popped;
    }

    @Override
    public void push(E item) {
        // TODO Implement push method
        if (size == array.length)
            resize(array.length * 2);

        array[size++] = item;
    }

    private void resize(int newSize) {
        if (newSize < 0) throw new IllegalArgumentException();
        E[] newArr = (E[]) new Object[newSize];
        System.arraycopy(array, 0, newArr, 0, this.size);
        array = newArr;
    }
}

