package model.state;

import exceptions.StackReadingFromEmptyStackException;

public interface ExecutionStack<T> {
    void push(T statement);

    T pop() throws StackReadingFromEmptyStackException;

    String toString();

    Boolean isEmpty();

}
