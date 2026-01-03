package model.state;

import exceptions.StackReadingFromEmptyStackException;

import java.util.List;

public interface ExecutionStack<T> {
    void push(T statement);

    T pop() throws StackReadingFromEmptyStackException;

    String toString();

    Boolean isEmpty();

    List<T> getContent();
}
