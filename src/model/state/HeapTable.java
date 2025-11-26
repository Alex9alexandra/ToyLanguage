package model.state;

import exceptions.ReadFromHeapExceptions;
import exceptions.WriteToHeapExceptions;
import model.value.Value;

import java.util.HashMap;
import java.util.Map;

public class HeapTable implements Heap{
    private Map<Integer,Value> heap;
    private int nextFreeAddress;

    public HeapTable()
    {
        heap=new HashMap<>();
        nextFreeAddress=1;
    }

    @Override
    public int allocate(Value value) {
        int adder=nextFreeAddress;
        heap.put(adder,value);
        nextFreeAddress++;
        return adder;
    }

    @Override
    public Value read(int address) throws ReadFromHeapExceptions {
        if(!heap.containsKey(address))
            throw new ReadFromHeapExceptions("The address "+address+" is not allocated in the heap.");
        return heap.get(address);
    }

    @Override
    public void write(int address, Value value) throws WriteToHeapExceptions {
        if(!heap.containsKey(address))
            throw new WriteToHeapExceptions("The address "+address+" is not allocated in the heap.");
        heap.put(address,value);
    }

    @Override
    public boolean containsAddress(int address) {
        return heap.containsKey(address);
    }

    @Override
    public Heap deepCopy() {
        HeapTable copy=new HeapTable();
        for(Map.Entry<Integer,Value> entry:heap.entrySet())
        {
            copy.heap.put(entry.getKey(),entry.getValue().deepCopy());
        }
        copy.nextFreeAddress=this.nextFreeAddress;
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer,Value> entry : heap.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }
}
