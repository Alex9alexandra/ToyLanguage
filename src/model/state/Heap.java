package model.state;

import exceptions.ReadFromHeapExceptions;
import exceptions.WriteToHeapExceptions;
import model.value.Value;

public interface Heap {
    int allocate(Value value);

    Value read(int address) throws ReadFromHeapExceptions;

    void write(int address, Value value) throws WriteToHeapExceptions;

    boolean containsAddress(int address);

    String toString();

    Heap deepCopy();
}
