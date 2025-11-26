package model.state;

import exceptions.ProgStateExecStackIsEmpty;
import model.statement.Statement;
import model.value.Value;

public class ProgramState {
    ExecutionStack<Statement> executionStack;
    SymbolTable<String, Value> symbolTable;
    Out<Value> out;
    Heap heap;
    Statement originalProgram;
    FileTable fileTable;

    public ProgramState(ExecutionStack<Statement> executionStack, SymbolTable<String, Value> symbolTable, Out<Value> out,FileTable fileTable,Heap heap, Statement program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.originalProgram = program.deepCopy();
        this.executionStack.push(program);
        this.heap=heap;
        this.fileTable = fileTable;
    }

    @Override
    public String toString() {
        return  executionStack.toString() + "\n"  + symbolTable.toString() +"\n"+ heap.toString() + "\n"  + fileTable.toString()+ "\n"+ out.toString() + "\n";
    }

    private Statement deepCopy(Statement program) {
        return program.deepCopy();
    }

    public void setExecutionStack(ExecutionStack<Statement> executionStack) {
        this.executionStack = executionStack;
    }

    public ExecutionStack<Statement> executionStack() {
        return executionStack;
    }

    public void setSymbolTable(SymbolTable<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public SymbolTable<String, Value> symbolTable() {
        return symbolTable;
    }

    public void setOut(Out<Value> out) {
        this.out = out;
    }

    public Out<Value> out() {
        return out;
    }

    public void setOriginalProgram(Statement program) {
        this.originalProgram = program;
    }

    public Statement originalProgram() {
        return originalProgram;
    }

    public FileTable fileTable() {
        return fileTable;
    }

    public void setFileTable(FileTable fileTable) {
        this.fileTable = fileTable;
    }

    public Heap heap() {
        return heap;
    }

    public void setHeap(Heap heap) {
        this.heap = heap;
    }



}

