package model.state;

import exceptions.ReadFromHeapExceptions;
import exceptions.WriteToHeapExceptions;
import model.value.Value;

public interface Heap<V> {
    int allocate(V value);

    V read(int address) throws ReadFromHeapExceptions;

    void write(int address, V value) throws WriteToHeapExceptions;

    boolean containsAddress(int address);

    String toString();

    Heap<V> deepCopy();
}
