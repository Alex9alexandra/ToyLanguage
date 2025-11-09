package model.state;

import java.util.ArrayList;
import java.util.List;

public class ListOut<T> implements Out<T> {
    private final List<T> outputList;

    public ListOut() {
        outputList = new ArrayList<>();
    }

    @Override
    public void add(T value) {
        outputList.add(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Output:\n");
        for (T value : outputList) {
            sb.append(value.toString()).append("\n");
        }
        return sb.toString();
    }
}
