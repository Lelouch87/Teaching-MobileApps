package com.example.chase.testapp1;

import java.util.EmptyStackException;
import java.util.Stack;

@SuppressWarnings("unused")
public class ArrayStack<E> extends Stack<E> {
    //Data Items
    private static final int INITIAL_CAPACITY = 10;
    private E[] theData;
    private int topOfStack = -1;

    /**
     * Default constructor, creates the array of the initial capacity
     */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        theData = (E[])new Object[INITIAL_CAPACITY];
    }

    /**
     * Puts an object on top of the stack
     * @param obj the object to be put on top
     * @return the object that was put on top
     */
    @Override
    public E push(E obj) {
        if (topOfStack == theData.length - 1) {
            reallocate(); //increase array size by 2
        }
        topOfStack++;
        theData[topOfStack] = obj;
        return obj;
    }

    /**
     * Looks at the data on top of the stack
     * @return the object on top of the stack
     */
    @Override
    public E peek() {
        if(empty()) {
            throw new EmptyStackException();
        }
        return theData[topOfStack];
    }

    /**
     * Checks to see if the stack is empty
     * @return true if empty, false if not
     */
    @Override
    public boolean empty() {
        return topOfStack == -1;
    }

    /**
     * Removes the object at the top of the stack
     * @return the object at the top of the stack
     */
    @Override
    public E pop() {
        if(empty()) {
            throw new EmptyStackException();
        }
        return theData[topOfStack--];
    }

    /**
     * Increases the capacity of the array by doubling it
     */
    @SuppressWarnings("unchecked")
    private void reallocate() {
        int newCapacity = 2 * theData.length;
        E[] newData = (E[])new Object[newCapacity];

        System.arraycopy(theData, 0, newData, 0, theData.length);

        theData = newData;
    }

    /**
     * A string representation of the ArrayStack
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        String arrow = " ==> ";
        StringBuilder sb = new StringBuilder();

        for (E item: theData) {
            sb.append(item);
            sb.append(arrow);
        }
        return sb.toString();
    }
}

