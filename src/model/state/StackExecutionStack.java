package model.state;

import exceptions.StackReadingFromEmptyStackException;
import model.statement.Statement;

import java.util.LinkedList;
import java.util.List;

public class StackExecutionStack<T> implements ExecutionStack<T> {
    private final List<T> stack = new LinkedList<>();

    @Override
    public void push(T statement) {
        stack.addFirst(statement);
    }

    @Override
    public T pop() throws StackReadingFromEmptyStackException {
        if (stack.isEmpty())
            throw new StackReadingFromEmptyStackException("Reading from an empty stack");
        return stack.removeFirst();
    }

    @Override
    public String toString() {

        StringBuilder answer = new StringBuilder("Execution Stack:\n");
        LinkedList<T> copy = new LinkedList<>(stack);
        while (!copy.isEmpty()) {
            T stmt = copy.removeFirst();
            answer.append(stmt.toString()).append("\n");
        }
        return answer.toString();
    }

    @Override
    public Boolean isEmpty() {
        return stack.isEmpty();
    }

}
